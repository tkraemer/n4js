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
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory
import eu.numberfour.n4js.ts.types.InferenceVariable
import eu.numberfour.n4js.ts.types.TFormalParameter
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.util.Variance
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.constraints.InferenceContext
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import it.xsemantics.runtime.RuleEnvironment
import java.util.Map

/**
 * {@link PolyProcessor} delegates here for processing array literals.
 * 
 * @see PolyProcessor#inferType(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,ASTMetaInfoCache)
 * @see PolyProcessor#processExpr(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,TypeRef,InferenceContext,ASTMetaInfoCache)
 */
@Singleton
package class PolyProcessor_CallExpression extends AbstractPolyProcessor {

	@Inject
	private PolyProcessor polyProcessor;

	@Inject
	private N4JSTypeSystem ts;

	/**
	 * BEFORE CHANGING THIS METHOD, READ THIS:
	 * {@link PolyProcessor#processExpr(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,TypeRef,InferenceContext,ASTMetaInfoCache)}
	 */
	def package TypeRef processCallExpression(RuleEnvironment G, ParameterizedCallExpression callExpr,
		TypeRef expectedTypeRef, InferenceContext infCtx, ASTMetaInfoCache cache) {

		val target = callExpr.target;
		val targetTypeRef = ts.type(G, target).value; // IMPORTANT: do not use #processExpr() here (if target is a PolyExpression, it has been processed in a separate, independent inference!)
		if (!(targetTypeRef instanceof FunctionTypeExprOrRef))
			return TypeRefsFactory.eINSTANCE.createUnknownTypeRef;
		val F = targetTypeRef as FunctionTypeExprOrRef;
		val funcTypeVars = F.typeVars;

		val isPoly = F.generic && callExpr.typeArgs.size < F.typeVars.size

		if (!isPoly) {
			val result = ts.type(G, callExpr).value;
			// do not store in cache (TypeProcessor responsible for storing types of non-poly expressions in cache)
			return result;
		}

		// create an inference variable for each type parameter of F
		val Map<TypeVariable, InferenceVariable> typeParam2infVar = newLinkedHashMap // type parameter of F -> inference variable
		for (typeParam : F.typeVars) {
			typeParam2infVar.put(typeParam, infCtx.newInferenceVariable);
		}

		//
		// (1) derive constraints from the bounds of the type parameters
		//

		for (TypeVariable currTypeVar : funcTypeVars) {
			// don't use currTypeVar.getDeclaredUpperBound() in next line!
			val currUB = F.getTypeVarUpperBound(currTypeVar) ?: N4JSLanguageUtils.getTypeVariableImplicitUpperBound(G);
			// constraint: currTypeVar <: current upper bound
			val leftTypeRef = TypeUtils.createTypeRef(currTypeVar);
			val leftTypeRefSubst = leftTypeRef.subst(G, typeParam2infVar);
			val rightTypeRef = currUB;
			val rightTypeRefSubst = rightTypeRef.subst(G, typeParam2infVar);
			infCtx.addConstraint(leftTypeRefSubst, rightTypeRefSubst, Variance.CO);
		}

		//
		// (2) derive constraints from matching type of each provided argument to type of corresponding fpar
		//

		val int argsSize = callExpr.getArguments().size();
		for (var i = 0; i < argsSize; i++) {
			val Expression arg = callExpr.getArguments().get(i)?.expression;
			val TFormalParameter curr_fpar = F.getFparForArgIdx(i);
			if (arg !== null && curr_fpar !== null) {
				val fparTypeRef = curr_fpar.getTypeRef();
				val fparTypeRefSubst = fparTypeRef.subst(G, typeParam2infVar);
				val TypeRef argType = polyProcessor.processExpr(G, arg, fparTypeRefSubst, infCtx, cache);
				if (argType !== null) {
					// constraint: argType <: fpar.type
					infCtx.addConstraint(fparTypeRefSubst, argType, Variance.CONTRA);
					// (note: no substitution in argType required, because it cannot contain any of the new inference
					// variables introduced above)
				}
			} else if (arg !== null) {
				// more arguments provided than fpars available
				// -> this is an error case, but make sure to process the surplus arguments to avoid
				// inconsistencies later on (cache misses etc.)
				polyProcessor.processExpr(G, arg, null, infCtx, cache);
			}
		}

		//
		// (3) derive constraints from matching expected return type to return type of function
		// --> not required here (will be done by caller)
		//

		// create temporary type (i.e. may contain inference variables)
		val resultTypeRefRaw = F.getReturnTypeRef();
		val resultTypeRef = resultTypeRefRaw.subst(G, typeParam2infVar);

		// register onSolved handlers to add final types to cache (i.e. may not contain inference variables)
		infCtx.onSolved [ solution |
			if (solution.present) {
				// success case:
				cache.storeType(callExpr, resultTypeRef.applySolution(G, solution.get));
				val inferredTypeArgs = typeParam2infVar.values.map[solution.get.get(it)].toList;
				cache.storeInferredTypeArgs(callExpr, inferredTypeArgs);
			} else {
				// failure case (unsolvable constraint system)
				// to avoid leaking inference variables, replace them by their original type parameter
				val fakeSolution = newHashMap;
				for (e : typeParam2infVar.entrySet) {
					fakeSolution.put(e.value, TypeUtils.createTypeRef(e.key));
				}
				cache.storeType(callExpr, resultTypeRef.applySolution(G, fakeSolution));
				cache.storeInferredTypeArgs(callExpr, #[]);
			}
			// PolyProcessor#isResponsibleFor(TypableElement) claims responsibility of AST nodes of type 'Argument'
			// contained in a ParameterizedCallExpression which is poly, so we are responsible for storing the types of
			// those 'Argument' nodes in cache
			// (note: compare this with similar handling of 'ArrayElement' nodes in PolyProcessor_ArrayLiteral)
			for (arg : callExpr.arguments) {
				val expr = arg?.expression;
				if (expr!==null) {
					val exprType = getFinalResultTypeOfNestedPolyExpression(expr);
					if (exprType!==null) {
						cache.storeType(arg, exprType);
					}
				}
			}
		];

		// return temporary type of callExpr (i.e. may contain inference variables)
		return resultTypeRef;
	}
}
