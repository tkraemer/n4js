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
import eu.numberfour.n4js.ts.types.util.Variance
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.n4JS.FormalParameter
import eu.numberfour.n4js.ts.types.TFormalParameter
import eu.numberfour.n4js.ts.types.InferenceVariable
import org.eclipse.xtext.EcoreUtil2
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.FunctionDefinition
import org.eclipse.emf.ecore.util.EcoreUtil
import eu.numberfour.n4js.n4JS.Block
import eu.numberfour.n4js.n4JS.ArrowFunction
import eu.numberfour.n4js.n4JS.N4JSPackage
import java.util.Map
import java.util.HashMap

/**
 * {@link PolyProcessor} delegates here for processing array literals.
 * 
 * @see PolyProcessor#inferType(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,ASTMetaInfoCache)
 * @see PolyProcessor#processExpr(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,TypeRef,InferenceContext,ASTMetaInfoCache)
 */
@Singleton
package class PolyProcessor_FunctionExpression extends AbstractPolyProcessor {

	@Inject
	private N4JSTypeSystem ts;

	@Inject
	private TypeSystemHelper tsh;

	/**
	 * BEFORE CHANGING THIS METHOD, READ THIS:
	 * {@link PolyProcessor#processExpr(RuleEnvironment,eu.numberfour.n4js.n4JS.Expression,TypeRef,InferenceContext,ASTMetaInfoCache)}
	 */
	def package TypeRef processFunctionExpression(RuleEnvironment G, FunctionExpression funExpr, TypeRef expectedTypeRef,
		InferenceContext infCtx, ASTMetaInfoCache cache) {

		val fun = funExpr.definedType as TFunction; // types builder will have created this already

		if (!funExpr.isPoly) { // funExpr has declared types on all fpars and explicitly declared return type
			// can't use xsemantics here, because it would give us a DeferredTypeRef
			// return ts.type(G, funExpr).getValue();
			val funTE = TypeUtils.createFunctionTypeExpression(null, #[], fun.fpars, fun.returnTypeRef); // FIXME support for declared this type!!
			// do not store in cache (TypeProcessor responsible for storing types of non-poly expressions in cache)
			return funTE;
		}

		// prepare temporary result type reference
		val funTE = TypeRefsFactory.eINSTANCE.createFunctionTypeExpression;

		if (fun.declaredThisType !== null) {
			funTE.declaredThisType = TypeUtils.copy(fun.declaredThisType);
		}

		if (!fun.typeVars.empty) {
			funTE.ownedTypeVars += fun.typeVars.map[tv|TypeUtils.copy(tv)];
		}


		processFormalParameters(G, funExpr, expectedTypeRef, infCtx, cache, funTE);

		// return type (also introduce inference variable for return type, if needed)
		var TypeRef returnTypeRef;
		if (funExpr.returnTypeRef !== null) {
			// explicitly declared return type
			// -> take the type reference created by the types builder (but wrap in Promise if required)
			returnTypeRef = TypeUtils.copy(fun.returnTypeRef);
		} else {
			// undeclared return type
			// -> create infVar for return type IFF funExpr contains return statements OR is single expr arrow function; otherwise use VOID as return type
			assertTrueIfRigid(cache, "return type of TFunction in TModule should be a DeferredTypeRef",
				fun.returnTypeRef instanceof DeferredTypeRef);

			if (funExpr.isReturningValue) {
				// introduce new inference variable for (inner) return type
				val iv = infCtx.newInferenceVariable;
				returnTypeRef = TypeUtils.createTypeRef(iv);
			} else {
				// void
				returnTypeRef = G.voidTypeRef;
			}
			// to obtain outer return type: wrap in Promise if asynchronous and not Promise already
			// for the time being, see N4JS Specification, Section 6.4.1 "Asynchronous Functions")
			returnTypeRef = N4JSLanguageUtils.makePromiseIfAsync(funExpr, returnTypeRef, G.builtInTypeScope);
			// to obtain outer return type: wrap in Generator if it is a generator function
			// see N4JS Specification, Section 6.3.1 "Generator Functions")
			returnTypeRef = N4JSLanguageUtils.makeGeneratorIfGeneratorFunction(funExpr, returnTypeRef, G.builtInTypeScope);
		}
		funTE.returnTypeRef = returnTypeRef;

		// create temporary type (i.e. may contain inference variables)
		val resultTypeRef = if (fun.typeVars.empty) {
				funTE
			} else {
				// if fun is generic, we have to replace the type variables of fun by those of result1
				val Gx = G.newRuleEnvironment;
				Gx.addTypeMappings(fun.typeVars, funTE.ownedTypeVars.map[TypeUtils.createTypeRef(it)]);
				ts.substTypeVariables(Gx, funTE).value as FunctionTypeExpression;
			};

		// register onSolved handlers to add final types to cache (i.e. may not contain inference variables)
		infCtx.onSolved [ solution |
			val solution2 = if (solution.present) solution.get else infCtx.createPseudoSolution(G.anyTypeRef);
			val resultSolved = resultTypeRef.applySolution(G, solution2) as FunctionTypeExprOrRef;
			cache.storeType(funExpr, resultSolved);
			fun.replaceDeferredTypeRefs(resultSolved);
			// store types of fpars in cache ...
			for (currFpar : funExpr.fpars) {
				// delegate to Xsemantics rule typeFormalParameter (because it contains special handling for 'this'-type
				// and variadic that we do not want to duplicate here)
				val currTypeRef = askXsemanticsForType(G, null, currFpar).getValue()
				cache.storeType(currFpar, currTypeRef);
			}
		];

		// return temporary type of funExpr (i.e. may contain inference variables)
		return resultTypeRef;
	}
	
	/**
	 * Process formal parameters and also introduce inference variables for types of fpars, where needed.
	 */
	private def processFormalParameters(RuleEnvironment G, FunctionExpression funExpr, TypeRef expectedTypeRef,
		InferenceContext infCtx, ASTMetaInfoCache cache, FunctionTypeExpression funTE
	) {
		val fun = funExpr.definedType as TFunction; // types builder will have created this already
		val expectedFunctionTypeExprOrRef = if (expectedTypeRef instanceof FunctionTypeExprOrRef) { expectedTypeRef };
		
		// first, new type refs for each formal parameter are created
		val len = Math.min(funExpr.fpars.size, fun.fpars.size);
		val Map<FormalParameter,TFormalParameter> typeRefMap = new HashMap();
		for (var i = 0; i < len; i++) {
			val fparAST = funExpr.fpars.get(i);
			val fparT = fun.fpars.get(i);
			val fparTCopy = TypeUtils.copy(fparT); // use the TFormalParameter created by the types builder as a basis
			funTE.fpars += fparTCopy;
			typeRefMap.put(fparAST, fparTCopy);
			
			if (fparAST.declaredTypeRef === null) {
				assertTrueIfRigid(cache, "type of formal parameter in TModule should be a DeferredTypeRef",
					fparTCopy?.typeRef instanceof DeferredTypeRef);
				
				// Deferred type refs have to be resolved here
				val iv = infCtx.newInferenceVariable;
				fparTCopy.typeRef = TypeUtils.createTypeRef(iv); // <-- set new inference variable as type
			}
		}
		
		// Now, go through the map and check for deferred types.
		// If any, include them into the constraint problem.
		for (Map.Entry<FormalParameter,TFormalParameter> fparPair : typeRefMap.entrySet) {
			val fparAST = fparPair.key;
			val fparTCopy = fparPair.value;
			if (fparAST.declaredTypeRef === null) {
				val iv = fparTCopy.typeRef.declaredType as InferenceVariable;
				addConstraintForDefaultInitializers(fparAST, fparTCopy, G, iv, infCtx, typeRefMap);
				inferOptionalityFromExpectedFpar(funExpr, funTE, expectedFunctionTypeExprOrRef, fparAST, fparTCopy);
			}
		}
	}

	/**
	 * When a function expression contains an initializer (in a default parameter),
	 * the type of this initializer is taken into account when calculating the parameter's type.
	*/
	private def addConstraintForDefaultInitializers(FormalParameter fparAST, TFormalParameter fparT,
		RuleEnvironment G, InferenceVariable iv, InferenceContext infCtx, Map<FormalParameter,TFormalParameter> typeRefMap
	){
		if(fparAST.hasInitializerAssignment) {
			// Check if the initializer refers to other fpars
			val allFPars = (fparAST.eContainer as FunctionDefinition).fpars;
			val allRefs = EcoreUtilN4.getAllContentsOfTypeStopAt(fparAST, IdentifierRef, N4JSPackage.Literals.FUNCTION_OR_FIELD_ACCESSOR__BODY);
			var referenceToFunctionsParameter = false;
			for (IdentifierRef ir : allRefs) {
				val id = ir.getId();
				if (allFPars.contains(id)) {
					referenceToFunctionsParameter = true;
				}
			}
			var refIsInitializer = false;
			if (allRefs.size === 1) {
				val ref = allRefs.get(0);
				refIsInitializer = ref.eContainmentFeature === N4JSPackage.Literals.FORMAL_PARAMETER__INITIALIZER;
			}
			
			if (refIsInitializer) {
				val refID = allRefs.get(0).getId();
				val fparTCopy = typeRefMap.get(refID);
				infCtx.addConstraint(TypeUtils.createTypeRef(iv), TypeUtils.copy(fparTCopy.typeRef), Variance.CONTRA);
			} else if (!referenceToFunctionsParameter) {
				val context = if (fparT.eContainer instanceof ContainerType<?>)
						TypeUtils.createTypeRef(fparT.eContainer as ContainerType<?>) else null;
				val G_withContext = ts.createRuleEnvironmentForContext(context, G.contextResource);
				val fieldTypeRef = askXsemanticsForType(G_withContext, null, fparAST).value; // delegate to Xsemantics rule typeN4FieldDeclaration
				val fieldTypeRefSubst = ts.substTypeVariables(G_withContext, fieldTypeRef).value;
				val fieldTypeRefSane = tsh.sanitizeTypeOfVariableFieldProperty(G, fieldTypeRefSubst);
				infCtx.addConstraint(TypeUtils.createTypeRef(iv), TypeUtils.copy(fieldTypeRefSane), Variance.CONTRA);
			}
		}
	}
	
	/**
	 * if the corresponding fpar in the type expectation is optional, we make the fpar in the
	 * function expression optional as well
	 * Example:
	 * 		let fun: {function(string=)} = function(p) {};
	 */
	private def inferOptionalityFromExpectedFpar(FunctionExpression funExpr, FunctionTypeExpression funTE,
		FunctionTypeExprOrRef expectedFunctionTypeExprOrRef, FormalParameter fparAST, TFormalParameter fparTCopy
	) {
		if (expectedFunctionTypeExprOrRef !== null) {
			val int fparIdx = funExpr.fpars.indexOf(fparAST);
			val fparExpected = expectedFunctionTypeExprOrRef.getFparForArgIdx(fparIdx);
			if (fparExpected !== null && fparExpected.optional && !fparExpected.variadic) {
				funTE.fpars.last.hasInitializerAssignment = true;
				EcoreUtilN4.doWithDeliver(false, [
					fparAST.hasInitializerAssignment = true;
					fparTCopy.hasInitializerAssignment = true;
				], fparAST, fparTCopy);
			}
		}
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
