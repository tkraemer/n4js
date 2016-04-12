/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.postprocessing

import com.google.inject.Inject
import eu.numberfour.n4js.misc.DestructureHelper
import eu.numberfour.n4js.n4JS.ArrayLiteral
import eu.numberfour.n4js.n4JS.N4JSASTUtils
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.util.Variance
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.typesystem.constraints.InferenceContext
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem
import it.xsemantics.runtime.RuleEnvironment
import java.util.Arrays
import java.util.List

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 */
class PolyProcessor_ArrayLiteral extends AbstractPolyProcessor {

	@Inject
	private PolyProcessor polyProcessor;

	@Inject
	private N4JSTypeSystem ts;
	@Inject
	private TypeSystemHelper tsh;
	@Inject
	private DestructureHelper destructureHelper;


	def package TypeRef processArrayLiteral(RuleEnvironment G, InferenceContext infCtx, ArrayLiteral arrLit, TypeRef expectedTypeRef) {
		// always poly

		val numOfElems = arrLit.elements.size;

		// the following will give us #[ T ] for an expectedTypeRef of the form Array<T> or Iterable<T>,
		// and #[ T1, T2, ..., TN ] for an expectedTypeRef of the form IterableN<T1,T2,...,TN>,
		// and #[] for any other kind of expectedTypeRef
		val expectedElemTypeRefs = {
			if(expectedTypeRef!==null)
				destructureHelper.extractIterableElementTypesUBs(G, expectedTypeRef).toList // will have len>1 only if expectation is IterableN
			else
newArrayList
//				#[] // no or invalid type expectation
		};

// TODO IDE-1726: temporary fall-back to bogus old behavior to avoid too many breaking changes in task IDE-1702
// update as of IDE-2137: raw type no longer used; the following special handling should be removed
val isEmptyOrAllPadding = numOfElems===0 || !arrLit.elements.exists[expression!==null];
val isCaseOfBogusArrayRawType = isEmptyOrAllPadding && expectedElemTypeRefs.empty;
if(isCaseOfBogusArrayRawType) {
	val result = G.arrayTypeRef(G.anyTypeRef); // RAW TYPE!!! (update as of IDE-2137: no longer using raw type here!)
	storeInCache(arrLit, TypeUtils.copy(result));
	return result;
}

// hack: faking an expectation of IterableN<...> here
// TODO instead we should get such an expectation in these cases from expectedType judgment!
val isValueToBeDestructured = N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit);
if(isValueToBeDestructured) {
	while(expectedElemTypeRefs.size < numOfElems)
		expectedElemTypeRefs.add(G.anyTypeRef);
}

// experimental performance tweak:
val haveUsableExpectedType = !expectedElemTypeRefs.empty;
if(!haveUsableExpectedType) {
	// no type expectation or some entirely wrong type expectation (i.e. other than Array, Iterable, IterableN)
	// -> just derive type from elements (and do not introduce a new inference variable for this ArrayLiteral!)
	val elemTypeRefs = newArrayList;
	arrLit.elements.filter[expression!==null].forEach[arrElem|
		elemTypeRefs += polyProcessor.processExpr(G, infCtx, arrElem.expression, null);
	]
	infCtx.onSolved[solution|
		val betterElemTypeRefs = arrLit.elements.filter[expression!==null].map[
			getFinalResultTypeOfNestedPolyExpression(expression)
		].toList;
		storeInCache(arrLit, buildFallbackTypeForArrayLiteral(false, 1, betterElemTypeRefs, expectedElemTypeRefs, G));
	]
	return G.arrayTypeRef(tsh.createUnionType(G, elemTypeRefs));
}

		// choose correct number of type arguments in our to-be-created resultTypeRef
		// (e.g., 1 for Array<T> and 3 for Iterable3<T1,T2,T3>)
		val resultLen = Math.max(
			Math.min(
				Math.min(
					expectedElemTypeRefs.size,  // use number of type arguments provided by type expectation as a basis
					numOfElems // ... but never more than we have elements in the array literal
				),
				BuiltInTypeScope.ITERABLE_N__MAX_LEN // ... and never more than the max. allowed number of type arguments for IterableN
			),
			1 // ... but at least 1 (even if numOfElems is 0, for example)
		);

		// create resultTypeRef, i.e. type of the array literal:
		// Array<T> (where T is a new inference variable) or
		// IterableN<T1,T2,...,TN> (where T1,...TN are new inference variables, N>=2)
		val isIterableN = resultLen >= 2;
		val TypeVariable[] resultInfVars = infCtx.newInferenceVariables(resultLen);
		val TypeRef resultTypeRef = TypeUtils.createTypeRef(
			if(isIterableN) G.iterableNType(resultLen) else G.arrayType,
			resultInfVars.map[TypeUtils.createTypeRef(it)]
		);

		// for each array element, add a constraint to ensure that its corresponding infVar in result type will be
		// a super type of the array element's expression
		for(var idxElem=0;idxElem<numOfElems;idxElem++) {
			val currElem = arrLit.elements.get(idxElem);
			if(currElem?.expression===null) {
				// currElem is null, or has no expression (broken AST), or is an ArrayPadding element
				// -> ignore (no constraint to add)
			} else {
				// currElem is a valid ArrayElement with an expression
				// -> add constraint currElemTypeRef <: Ti (Ti being the corresponding inf. variable in resultTypeRef)
				val idxResult = Math.min(idxElem, resultLen-1);
				val currResultInfVar = resultInfVars.get(idxResult);
				val currElemTypeRef = polyProcessor.processExpr(G, infCtx, currElem.expression, null);
				infCtx.addConstraint(currElemTypeRef, TypeUtils.createTypeRef(currResultInfVar), Variance.CO);
			}
		}

// not required here (will be done by caller):
//		val expectedTypeRef = ts.expectedTypeIn(G, arrLit.eContainer(), arrLit).getValue();
//
//		// Array<T> <: expectedTypeRef
//		infCtx.addConstraint(arrTypeRef, expectedTypeRef, Variance.CO, #[]);

		infCtx.onSolved[solution|
			if(solution.present) {
				// success case
				storeInCache(arrLit, resultTypeRef.applySolution(G, solution.get))
			} else {
				// failure case (unsolvable constraint system)
				val betterElemTypeRefs = arrLit.elements.map[
					if(expression!==null) getFinalResultTypeOfNestedPolyExpression(expression) else G.anyTypeRef
				];
				storeInCache(arrLit, buildFallbackTypeForArrayLiteral(
					isIterableN, resultLen, betterElemTypeRefs, expectedElemTypeRefs, G));
			}
		]

		return resultTypeRef;
	}


	def private TypeRef buildFallbackTypeForArrayLiteral(boolean isIterableN, int resultLen,
			List<TypeRef> elemTypeRefs, List<TypeRef> expectedElemTypeRefs, RuleEnvironment G) {
		if(isIterableN) {
			val typeArgs = newArrayOfSize(resultLen);
			for(var i=0;i<resultLen;i++) {
				val isLastElem = i===resultLen-1;
				if(isLastElem && elemTypeRefs.size > resultLen) {
					// special case:
					// we are at the last element AND we actually have more elements than we expect elements
					// -> have to check all remaining elements against the last expectation!

					val allRemainingElementTypeRefs = newArrayList;
					val currExpectedElemTypeRef = expectedElemTypeRefs.get(i);

					// if all remaining elements are a subtype of the last expectation, then use expectation, otherwise form union
					var boolean allMatch = true;
					for(var j=i;j<elemTypeRefs.size;j++) {

						val currElementTypeRef = elemTypeRefs.get(j);
						allRemainingElementTypeRefs.add(currElementTypeRef);

						if(allMatch) { // don't try further subtype checks if already failed
							val actualIsSubtypeOfExpected = ts.subtypeSucceeded(G, currElementTypeRef, currExpectedElemTypeRef);
							if(!actualIsSubtypeOfExpected) {
								allMatch = false;
							}
						}
					}
					if(allMatch) {
						// use expected type
						typeArgs.set(i, currExpectedElemTypeRef);
					} else {
						// use actual types (will lead to follow-up errors caught by validations)
						typeArgs.set(i, tsh.createUnionType(G, allRemainingElementTypeRefs));
					}
				} else {
					val currElemTypeRef = elemTypeRefs.get(i);
					val currExpectedElemTypeRef = expectedElemTypeRefs.get(i);
					val actualIsSubtypeOfExpected = ts.subtypeSucceeded(G, currElemTypeRef, currExpectedElemTypeRef);
					if(actualIsSubtypeOfExpected) {
						// use expected type
						typeArgs.set(i, currExpectedElemTypeRef);
					} else {
						// use actual type (will lead to follow-up errors caught by validations)
						typeArgs.set(i, currElemTypeRef);
					}
				}
			}
			if(elemTypeRefs.size > resultLen) {
				// replace last entry in 'typeArgs' with union of all remaining in elemTypeRefs
				val remaining = Arrays.copyOfRange(elemTypeRefs, resultLen-1, elemTypeRefs.size);
				typeArgs.set(resultLen-1, tsh.createUnionType(G, remaining));
			}
			return G.iterableNTypeRef(resultLen, typeArgs);
		} else {
			val unionOfElemTypes = tsh.createUnionType(G, elemTypeRefs);
			return G.arrayTypeRef(unionOfElemTypes);
		}
	}
}
