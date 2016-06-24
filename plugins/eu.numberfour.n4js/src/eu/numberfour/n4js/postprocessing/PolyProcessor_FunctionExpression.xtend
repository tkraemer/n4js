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
import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.ts.typeRefs.DeferredTypeRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.constraints.InferenceContext
import eu.numberfour.n4js.utils.EcoreUtilN4
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import it.xsemantics.runtime.RuleEnvironment

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 */
@Singleton
package class PolyProcessor_FunctionExpression extends AbstractPolyProcessor {

	@Inject
	private N4JSTypeSystem ts;

	def package TypeRef processFunctionExpression(RuleEnvironment G, FunctionExpression funExpr, TypeRef expectedTypeRef,
		InferenceContext infCtx, ASTMetaInfoCache cache) {

		val fun = funExpr.definedType as TFunction; // types builder will have created this already
		if (!funExpr.isPoly) { // funExpr has declared types on all fpars and explicitly declared return type
			// can't use xsemantics here, because it would give us a DeferredTypeRef
			// return ts.type(G, funExpr).getValue();
			val retTypeRef = N4JSLanguageUtils.makePromiseIfAsync(funExpr, funExpr.returnTypeRef,
				G.predefinedTypes.builtInTypeScope); // FIXME support for declared this type!!
			val result = TypeUtils.createFunctionTypeExpression(null, #[], fun.fpars, retTypeRef);
			// do not store in cache (TypingASTWalker responsible for storing types of non-poly expressions in cache)
			return result;
		}

		val result1 = TypeRefsFactory.eINSTANCE.createFunctionTypeExpression;

		if (fun.declaredThisType !== null) {
			result1.declaredThisType = TypeUtils.copy(fun.declaredThisType);
		}

		if (!fun.typeVars.empty) {
			result1.ownedTypeVars += fun.typeVars.map[tv|TypeUtils.copy(tv)];
		}

		// fpars
		val len = Math.min(funExpr.fpars.size, fun.fpars.size);
		for (var i = 0; i < len; i++) {

			val fparAST = funExpr.fpars.get(i);
			val fparT = fun.fpars.get(i);

			if (fparAST.declaredTypeRef !== null) {
				// explicitly declared type
				result1.fpars += TypeUtils.copy(fparT); // use the TFormalParameter created by the types builder as a basis
			} else {
				// undeclared type
				assertTrueIfRigid("type of formal parameter in TModule should be a DeferredTypeRef",
					fparT?.typeRef instanceof DeferredTypeRef);
				val fparResult = TypeUtils.copy(fparT); // <-- will have a DeferredTypeRef as type
				val iv = infCtx.newInferenceVariable;
				fparResult.typeRef = TypeUtils.createTypeRef(iv); // <-- set new inference variable as type
				result1.fpars += fparResult;
			}
		}

		// return type
		var TypeRef returnTypeRef;
		if (funExpr.returnTypeRef !== null) {
			// explicitly declared return type
			returnTypeRef = TypeUtils.copy(fun.returnTypeRef); // take the one from the types builder (already wrapped in Promise, if required)
		} else {
			// undeclared return type
			// -> create infVar for return type IFF funExpr contains return statements OR is single expr arrow function; otherwise use VOID as return type
			assertTrueIfRigid("return type of TFunction in TModule should be a DeferredTypeRef",
				fun.returnTypeRef instanceof DeferredTypeRef);

			if (funExpr.isReturningValue) {
				// introduce new inference variable for (inner) return type
				val iv = infCtx.newInferenceVariable;
				returnTypeRef = TypeUtils.createTypeRef(iv);
			} else {
				// void
				returnTypeRef = G.voidTypeRef;
			}
			// to obtain outer return type: wrap in Promise if asynchronous
			// (note: we even do this for void functions, producing the weird Promise<void,?>; but this is intended
			// for the time being, see N4JS Specification, Section 6.4.1 "Asynchronous Functions")
			returnTypeRef = N4JSLanguageUtils.makePromiseIfAsync(funExpr, returnTypeRef,
				G.predefinedTypes.builtInTypeScope);
		}
		result1.returnTypeRef = returnTypeRef;

		// if fun is generic, we now have to replace the type variables of fun by those of result1
		val result = if (fun.typeVars.empty) {
				result1
			} else {
				val Gx = G.newRuleEnvironment;
				Gx.addTypeMappings(fun.typeVars, result1.ownedTypeVars.map[TypeUtils.createTypeRef(it)]);
				ts.substTypeVariables(Gx, result1).value as FunctionTypeExpression;
			};

		infCtx.onSolved [ solution |
			val solution2 = if (solution.present) solution.get else infCtx.createPseudoSolution(G.anyTypeRef);
			val resultSolved = result.applySolution(G, solution2) as FunctionTypeExprOrRef;
			cache.storeType(funExpr, resultSolved);
			fun.replaceDeferredTypeRefs(resultSolved);
			// store types of fpars in cache ...
			for (currFpar : funExpr.fpars) {
				cache.storeType(currFpar,
// delegate to Xsemantics rule typeFormalParameter (because it contains special handling for 'this'-type and variadic)
askXsemanticsForType(G, currFpar).getValue() // FIXME clean this up!
//					TypeUtils.copy(currFpar.definedTypeElement.typeRef)
					);
			}
		];

		return result;
	}

	/**
	 * Replaces all DeferredTypeRefs in the given TFunction (i.e. in the fpars' types and the return type)
	 * by the corresponding types in 'result'. Argument 'result' must not contain any DeferredTypeRefs and,
	 * when this method returns, also the given TFunction 'fun' won't contain DeferredTypeRefs anymore.
	 * Will throw exception if 'fun' and 'result' do not match (e.g. 'result' has fewer fpars than 'fun').
	 */
	def protected void replaceDeferredTypeRefs(TFunction fun, FunctionTypeExprOrRef result) {
		val len = fun.fpars.length; // note: we do not take Math.min here (fail fast)
		for (var i = 0; i < len; i++) {
			val funFpar = fun.fpars.get(i);
			if (funFpar.typeRef instanceof DeferredTypeRef) {
				val idx = i;
				EcoreUtilN4.doWithDeliver(false, [
					funFpar.typeRef = TypeUtils.copy(result.fpars.get(idx).typeRef);
				], funFpar);
			}
		}
		if (fun.returnTypeRef instanceof DeferredTypeRef) {
			EcoreUtilN4.doWithDeliver(false, [
				fun.returnTypeRef = TypeUtils.copy(result.returnTypeRef);
			], fun);
		}
	}
}
