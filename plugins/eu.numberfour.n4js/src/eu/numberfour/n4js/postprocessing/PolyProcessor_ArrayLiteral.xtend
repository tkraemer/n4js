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
import com.google.inject.Singleton
import eu.numberfour.n4js.misc.DestructureHelper
import eu.numberfour.n4js.n4JS.ArrayLiteral
import eu.numberfour.n4js.n4JS.N4JSASTUtils
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.util.Variance
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.typesystem.constraints.InferenceContext
import it.xsemantics.runtime.RuleEnvironment
import java.util.Arrays
import java.util.List

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * {@link PolyProcessor} delegates here for processing array literals.
 * 
 * @see PolyProcessor#inferType(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,ASTMetaInfoCache)
 * @see PolyProcessor#processExpr(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,TypeRef,InferenceContext,ASTMetaInfoCache)
 */
@Singleton
package class PolyProcessor_ArrayLiteral extends AbstractPolyProcessor {

	@Inject
	private PolyProcessor polyProcessor;

	@Inject
	private N4JSTypeSystem ts;
	@Inject
	private TypeSystemHelper tsh;
	@Inject
	private DestructureHelper destructureHelper;

	/**
	 * BEFORE CHANGING THIS METHOD, READ THIS:
	 * {@link PolyProcessor#processExpr(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,TypeRef,InferenceContext,ASTMetaInfoCache)}
	 */
	def package TypeRef processArrayLiteral(RuleEnvironment G, ArrayLiteral arrLit, TypeRef expectedTypeRef,
		InferenceContext infCtx, ASTMetaInfoCache cache) {

		// note: we do not have the case !arrLit.isPoly here, as in the other poly processors
		// (array literals are always poly, because they cannot be explicitly typed in N4JS)

		val numOfElems = arrLit.elements.size;

		// we have to analyze the type expectation:
		// 1. we have to know up-front whether we aim for an actual type of Array/Iterable or for IterableN
		// 2. we have to know if we have concrete expectations for the element type(s)
		// To do so, we prepare a helper variable 'expectedElemTypeRefs' and initialize it as follows:
		// #[ T ] for an expectedTypeRef of the form Array<T> or Iterable<T>,
		// #[ T1, T2, ..., TN ] for an expectedTypeRef of the form IterableN<T1,T2,...,TN>,
		// #[] for any other kind of expectedTypeRef
		val expectedElemTypeRefs = {
			if (expectedTypeRef !== null)
				destructureHelper.extractIterableElementTypesUBs(G, expectedTypeRef).toList // will have len>1 only if expectation is IterableN
			else
				newArrayList // no or invalid type expectation
		};

// hack: faking an expectation of IterableN<...> here
// TODO instead we should get such an expectation in these cases from expectedType judgment!
val isValueToBeDestructured = N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit);
if(isValueToBeDestructured) {
	while(expectedElemTypeRefs.size < numOfElems)
		expectedElemTypeRefs.add(G.anyTypeRef);
}

		// performance tweak:
		val haveUsableExpectedType = !expectedElemTypeRefs.empty;
		if (!haveUsableExpectedType && !TypeUtils.isInferenceVariable(expectedTypeRef)) {
			// no type expectation or some entirely wrong type expectation (i.e. other than Array, Iterable, IterableN)
			// -> just derive type from elements (and do not introduce a new inference variable for this ArrayLiteral!)
			val elemTypeRefs = newArrayList;
			arrLit.elements.filter[expression !== null].forEach [ arrElem |
				elemTypeRefs += polyProcessor.processExpr(G, arrElem.expression, null, infCtx, cache);
			]
			infCtx.onSolved [ solution |
				val betterElemTypeRefs = arrLit.elements.filter[expression !== null].map [
					getFinalResultTypeOfNestedPolyExpression(expression)
				].toList;
				cache.storeType(arrLit, buildFallbackTypeForArrayLiteral(false, 1, betterElemTypeRefs, expectedElemTypeRefs, G));
			]
			val unionOfElemTypes = if (!elemTypeRefs.empty) tsh.createUnionType(G, elemTypeRefs) else G.anyTypeRef;
			return G.arrayTypeRef(unionOfElemTypes);
		}

		// choose correct number of type arguments in our to-be-created resultTypeRef
		// (always 1 for Array<T> or Iterable<T> but N for IterableN<..>, e.g. 3 for Iterable3<T1,T2,T3>)
		val resultLen = Math.max(
			Math.min(
				Math.min(
					expectedElemTypeRefs.size, // use number of type arguments provided by type expectation as a basis
					numOfElems // ... but never more than we have elements in the array literal
				),
				BuiltInTypeScope.ITERABLE_N__MAX_LEN // ... and never more than the max. allowed number of type arguments for IterableN
			),
			1 // ... but at least 1 (even if numOfElems is 0, for example)
		);

		val isIterableN = resultLen >= 2;
		val TypeVariable[] resultInfVars = infCtx.newInferenceVariables(resultLen);

		// for each array element, add a constraint to ensure that its corresponding infVar in result type will be
		// a super type of the array element's expression
		for (var idxElem = 0; idxElem < numOfElems; idxElem++) {
			val currElem = arrLit.elements.get(idxElem);
			if (currElem?.expression === null) {
				// currElem is null, or has no expression (broken AST), or is an ArrayPadding element
				// -> ignore (no constraint to add)
			} else {
				// currElem is a valid ArrayElement with an expression
				// -> add constraint currElemTypeRef <: Ti (Ti being the corresponding inf. variable in resultTypeRef)
				val idxResult = Math.min(idxElem, resultLen - 1);
				val currResultInfVar = resultInfVars.get(idxResult);
				val currElemTypeRef = polyProcessor.processExpr(G, currElem.expression, TypeUtils.createTypeRef(currResultInfVar), infCtx, cache);
				infCtx.addConstraint(currElemTypeRef, TypeUtils.createTypeRef(currResultInfVar), Variance.CO);
			}
		}

		// create temporary type (i.e. may contain inference variables):
		// Array<T> (where T is a new inference variable) or
		// Iterable<T> (where T is a new inference variable) or
		// IterableN<T1,T2,...,TN> (where T1,...TN are new inference variables, N>=2)
		val TypeRef resultTypeRef = TypeUtils.createTypeRef(
			if (isIterableN) G.iterableNType(resultLen) else G.arrayType,
			resultInfVars.map[TypeUtils.createTypeRef(it)]
		);

		// register onSolved handlers to add final types to cache (i.e. may not contain inference variables)
		infCtx.onSolved [ solution |
			if (solution.present) {
				// success case
				cache.storeType(arrLit, resultTypeRef.applySolution(G, solution.get))
			} else {
				// failure case (unsolvable constraint system)
				val betterElemTypeRefs = arrLit.elements.map [
					if (expression !== null) getFinalResultTypeOfNestedPolyExpression(expression) else G.anyTypeRef
				];
				val fallback = buildFallbackTypeForArrayLiteral(isIterableN, resultLen, betterElemTypeRefs,
					expectedElemTypeRefs, G);
				cache.storeType(arrLit, fallback);
			}
			// PolyProcessor#isResponsibleFor(TypableElement) claims responsibility of AST nodes of type 'ArrayElement'
			// contained in an ArrayLiteral which is poly, so we are responsible for storing the types of those
			// 'ArrayElement' nodes in cache
			// (note: compare this with similar handling of 'Argument' nodes in PolyProcessor_CallExpression)
			for (arrElem : arrLit.elements) {
				val expr = arrElem?.expression;
				if (expr!==null) {
					val exprType = getFinalResultTypeOfNestedPolyExpression(expr);
					if (exprType!==null) {
						cache.storeType(arrElem, exprType);
					}
				}
			}
		];

		// return temporary type of arrLit (i.e. may contain inference variables)
		return resultTypeRef;
	}

	/**
	 * Makes a best effort for building a type in case something went awry. It's only non-trivial in case we have an
	 * expectation of IterableN.
	 */
	def private TypeRef buildFallbackTypeForArrayLiteral(boolean isIterableN, int resultLen,
		List<TypeRef> elemTypeRefs, List<TypeRef> expectedElemTypeRefs, RuleEnvironment G) {

		if (isIterableN) {
			val typeArgs = newArrayOfSize(resultLen);
			for (var i = 0; i < resultLen; i++) {
				val isLastElem = i === resultLen - 1;
				if (isLastElem && elemTypeRefs.size > resultLen) {
					// special case:
					// we are at the last element AND we actually have more elements than we expect elements
					// -> have to check all remaining elements against the last expectation!
					val allRemainingElementTypeRefs = newArrayList;
					val currExpectedElemTypeRef = expectedElemTypeRefs.get(i);

					// if all remaining elements are a subtype of the last expectation, then use expectation, otherwise form union
					var boolean allMatch = true;
					for (var j = i; j < elemTypeRefs.size; j++) {

						val currElementTypeRef = elemTypeRefs.get(j);
						allRemainingElementTypeRefs.add(currElementTypeRef);

						if (allMatch) { // don't try further subtype checks if already failed
							val actualIsSubtypeOfExpected = ts.subtypeSucceeded(G, currElementTypeRef,
								currExpectedElemTypeRef);
							if (!actualIsSubtypeOfExpected) {
								allMatch = false;
							}
						}
					}
					if (allMatch) {
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
					if (actualIsSubtypeOfExpected) {
						// use expected type
						typeArgs.set(i, currExpectedElemTypeRef);
					} else {
						// use actual type (will lead to follow-up errors caught by validations)
						typeArgs.set(i, currElemTypeRef);
					}
				}
			}
			if (elemTypeRefs.size > resultLen) {
				// replace last entry in 'typeArgs' with union of all remaining in elemTypeRefs
				val remaining = Arrays.copyOfRange(elemTypeRefs, resultLen - 1, elemTypeRefs.size);
				typeArgs.set(resultLen - 1, tsh.createUnionType(G, remaining));
			}
			return G.iterableNTypeRef(resultLen, typeArgs);
		} else {
			val unionOfElemTypes = if (!elemTypeRefs.empty) tsh.createUnionType(G, elemTypeRefs) else G.anyTypeRef;
			return G.arrayTypeRef(unionOfElemTypes);
		}
	}
}
