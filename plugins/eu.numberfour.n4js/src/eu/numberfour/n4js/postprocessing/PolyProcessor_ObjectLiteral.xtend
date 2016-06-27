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
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.PropertyAssignment
import eu.numberfour.n4js.n4JS.PropertyGetterDeclaration
import eu.numberfour.n4js.n4JS.PropertyMethodDeclaration
import eu.numberfour.n4js.n4JS.PropertyNameValuePair
import eu.numberfour.n4js.n4JS.PropertySetterDeclaration
import eu.numberfour.n4js.ts.typeRefs.DeferredTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.ts.types.FieldAccessor
import eu.numberfour.n4js.ts.types.InferenceVariable
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.TStructGetter
import eu.numberfour.n4js.ts.types.TStructMember
import eu.numberfour.n4js.ts.types.TStructuralType
import eu.numberfour.n4js.ts.types.TypingStrategy
import eu.numberfour.n4js.ts.types.util.Variance
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.typesystem.constraints.InferenceContext
import eu.numberfour.n4js.utils.EcoreUtilN4
import it.xsemantics.runtime.RuleEnvironment

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import java.util.List

/**
 * {@link PolyProcessor} delegates here for processing array literals.
 * 
 * @see PolyProcessor#inferType(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,ASTMetaInfoCache)
 * @see PolyProcessor#processExpr(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,TypeRef,InferenceContext,ASTMetaInfoCache)
 */
@Singleton
package class PolyProcessor_ObjectLiteral extends AbstractPolyProcessor {

	@Inject
	private PolyProcessor polyProcessor;

	@Inject
	private N4JSTypeSystem ts;
	@Inject
	private TypeSystemHelper tsh;

	/**
	 * BEFORE CHANGING THIS METHOD, READ THIS:
	 * {@link PolyProcessor#processExpr(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,TypeRef,InferenceContext,ASTMetaInfoCache)}
	 */
	def package TypeRef processObjectLiteral(RuleEnvironment G, ObjectLiteral objLit, TypeRef expectedTypeRef,
		InferenceContext infCtx, ASTMetaInfoCache cache) {

		if (!objLit.isPoly) {
			val result = ts.type(G, objLit).getValue();
			// do not store in cache (TypeProcessor responsible for storing types of non-poly expressions in cache)
			return result;
		}

		// performance tweak:
		val haveUsableExpectedType = expectedTypeRef !== null
				&& (expectedTypeRef.useSiteStructuralTyping || expectedTypeRef.defSiteStructuralTyping); // FIXME reconsider
		val quickMode = !haveUsableExpectedType;

		// for each property in the object literal:
		// a) introduce a new inference variable representing the property's type (except for methods)
		// b) add a constraint: expressionTypeRef <: iv
		// c) create a TStructMember to be used in the structural result type reference
		val List<TStructMember> tMembers = newArrayList;
		val List<Pair<PropertyAssignment,InferenceVariable>> prop2InfVar = newArrayList; // only used in standard mode
		val List<Pair<PropertyAssignment,TypeRef>> prop2FallbackType = newArrayList; // only used in quick mode
		for (pa : objLit.propertyAssignments) {
			if (pa !== null) {
				if (pa.isPoly) {
					// pa is poly
					val tMember = TypeUtils.copy(pa.definedMember);
					if (tMember !== null) {
						tMembers += tMember;
						if (!(tMember instanceof TMethod)) {
							val originalMemberType = tMember.typeOfMember;
							assertTrueIfRigid("type of " + pa.eClass.name + " in TModule should be a DeferredTypeRef",
								originalMemberType instanceof DeferredTypeRef);
							if (originalMemberType instanceof DeferredTypeRef) {
								if(!quickMode) {
									// standard mode:
									// create new inference variable for the to-be-inferred type of this property
									val iv = infCtx.newInferenceVariable;
									// set it as type in tMember
									tMember.typeOfMember = TypeUtils.createTypeRef(iv);
									// add a constraint for the initializer expression (if any)
									if (pa instanceof PropertyNameValuePair) {
										if (pa.expression !== null) {
											val exprTypeRef = polyProcessor.processExpr(G, pa.expression, null, infCtx, cache);
											infCtx.addConstraint(exprTypeRef, TypeUtils.createTypeRef(iv), Variance.CO); // exprTypeRef <: iv
										}
									}
									// remember for later
									prop2InfVar += pa -> iv;
								} else {
									// quick mode:
									// compute a fall-back type
									val fallbackType = switch (pa) {
										PropertyNameValuePair case pa.expression !== null:
											polyProcessor.processExpr(G, pa.expression, null, infCtx, cache)
										PropertyGetterDeclaration:
											pa.declaredTypeOfOtherAccessorInPair ?: G.anyTypeRef
										PropertySetterDeclaration:
											pa.declaredTypeOfOtherAccessorInPair ?: G.anyTypeRef
										default:
											G.anyTypeRef
									};
									// set it as type in tMember
									tMember.typeOfMember = TypeUtils.copy(fallbackType);
									// remember for later
									prop2FallbackType += pa -> fallbackType;
								}
							}
						}
					}
				} else {
					// pa is not poly
					// -> simply use a copy of the member generated by the types builder
					tMembers += TypeUtils.copy(pa.definedMember);
				}
			}
		}

		// add a constraint for each getter/setter pair reflecting the relation between the getter's and setter's type
		// (required to make the getter obtain its implicit type from the corresponding setter, and vice versa)
		if(!quickMode) { // not in quick mode
			for (tMember : tMembers) {
				if (tMember instanceof TStructGetter) {
					val tOtherInPair = findOtherAccessorInPair(tMember);
					if (tOtherInPair !== null) {
						val typeGetter = tMember.typeOfMember;
						val typeSetter = tOtherInPair.typeOfMember;
						if (TypeUtils.isInferenceVariable(typeGetter) || TypeUtils.isInferenceVariable(typeSetter)) {
							infCtx.addConstraint(typeGetter, typeSetter, Variance.CO);
						} else {
							// do not add a constraint if both types were explicitly declared
							// (then this constraint does not apply!!)
						}
					}
				}
			}
		}

		// create temporary type (i.e. may contain inference variables)
		val resultTypeRef = TypeUtils.createParameterizedTypeRefStructural(G.objectType, TypingStrategy.STRUCTURAL, tMembers);

		// register onSolved handlers to add final types to cache (i.e. may not contain inference variables)
		infCtx.onSolved [ solution |
			val prop2InfVarOrFallbackType = if(!quickMode) prop2InfVar else prop2FallbackType;
			for (e : prop2InfVarOrFallbackType) {
				val pa = e.key;
				val memberInTModule = pa.definedMember;
				val memberType = if (solution.present && !quickMode) {
						// success case (standard mode):
						val infVar = e.value as InferenceVariable; // processing prop2InfVar, so value is an infVar
						val fromSolution = solution.get.get(infVar);
						if (pa instanceof PropertyNameValuePair) {
							val fromCache = if (pa.expression instanceof ObjectLiteral) {
									getFinalResultTypeOfNestedPolyExpression(pa.expression)
								} else {
									null
								};
							if (fromCache !== null && ts.equaltypeSucceeded(G, fromCache, fromSolution)) {
								// tweak for nested ObjectLiterals in initializer expression of PropertyNameValuePairs:
								// the solution from the infCtx will be a StructuralTypeRef with 'genStructuralMembers'
								// but the result of the nested poly computation (via the cache) will give us a much more
								// efficient StructuralTypeRef with 'structuralType' pointing to the TStructuralType in the TModule
								fromCache
							} else {
								fromSolution
							}
						} else {
							fromSolution
						}
					} else if(solution.present && quickMode) {
						// success case (quick mode):
						val fallbackType = e.value as TypeRef; // processing prop2FallbackType, so value is a TypeRef
						val ttt = if (pa instanceof PropertyNameValuePair) {
								getFinalResultTypeOfNestedPolyExpression(pa.expression)
							} else {
								TypeUtils.copy(fallbackType).applySolution(G, solution.get)
							};
						ttt
					} else {
						// failure case (both modes):
						val ttt = if (pa instanceof PropertyNameValuePair) {
								getFinalResultTypeOfNestedPolyExpression(pa.expression)
							} else {
								G.anyTypeRef
							};
						ttt
					};
				val memberTypeSane = tsh.sanitizeTypeOfVariableFieldProperty(G, memberType);
				EcoreUtilN4.doWithDeliver(false, [
					memberInTModule.typeOfMember = TypeUtils.copy(memberTypeSane);
				], memberInTModule);
			}
			val resultFinal = TypeUtils.createParameterizedTypeRefStructural(G.objectType, TypingStrategy.STRUCTURAL,
				objLit.definedType as TStructuralType);
			cache.storeType(objLit, resultFinal);
			for (currAss : objLit.propertyAssignments) {
				if (currAss instanceof PropertyMethodDeclaration) {
					cache.storeType(currAss, TypeUtils.createTypeRef(currAss.definedMember));
				} else {
					cache.storeType(currAss, TypeUtils.copy(currAss.definedMember.typeOfMember));
				}
			}
		];

		// return temporary type of objLit (i.e. may contain inference variables)
		return resultTypeRef;
	}

	def private TypeRef getDeclaredTypeOfOtherAccessorInPair(eu.numberfour.n4js.n4JS.FieldAccessor accAST) {
		return (
			findOtherAccessorInPair(accAST.definedAccessor)?.astElement as eu.numberfour.n4js.n4JS.FieldAccessor
		)?.declaredTypeRef;
	}

	def private FieldAccessor findOtherAccessorInPair(FieldAccessor acc) {
		if (acc?.name !== null) {
			val type = acc.eContainer;
			if (type instanceof ContainerType<?>) {
				val lookForWriteAccess = acc instanceof TGetter;
				val result = type.findOwnedMember(acc.name, lookForWriteAccess, acc.static);
				if (result instanceof FieldAccessor) {
					return result;
				}
			}
		}
		return null;
	}
}
