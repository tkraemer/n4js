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
package eu.numberfour.n4js.typesbuilder

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.numberfour.n4js.n4JS.FunctionDeclaration
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.types.TModule
import eu.numberfour.n4js.ts.types.TypeAccessModifier
import eu.numberfour.n4js.ts.types.TypesFactory
import eu.numberfour.n4js.ts.utils.TypeUtils

/**
 * Type builder for function declaration or expression builder.
 */
// TODO we temporarily create a BuiltInTypeScope in order to get primitive types. This may be changed by passing in this scope, one this method is called by the typesystem
@Singleton
public class N4JSFunctionDefinitionTypesBuilder extends AbstractFunctionDefinitionTypesBuilder {

	@Inject extension N4JSFormalParameterTypesBuilder
	@Inject extension N4JSTypesBuilderHelper

	/**
	 * Creates TFunction for the given function declaration and adds it to the modules top level types
	 * (as function declarations are only allowed on top level).
	 *
	 * @param functionDecl declaration for which the TFunction is created, must not be linked to a TFunction yet (i.e. its defined type must be null).
	 * @param target the module to which the newly created TFunction is added
	 */
	def package void createTFunction(FunctionDeclaration functionDecl, TModule target, boolean preLinkingPhase) {
		if (functionDecl.definedType !== null && ! functionDecl.definedType.eIsProxy) {
			throw new IllegalStateException("TFunction already created for FunctionDeclaration");
		}

		if (functionDecl.name === null) {
			return;
		}

		val builtInTypeScope = BuiltInTypeScope.get(functionDecl.eResource.resourceSet)
		val functionType = functionDecl.createAndLinkTFunction(preLinkingPhase)

		functionType.addFormalParameters(functionDecl, builtInTypeScope, preLinkingPhase)
		functionType.setTypeAccessModifier(functionDecl)
		functionType.setProvidedByRuntime(functionDecl, preLinkingPhase)
		functionType.setReturnType(functionDecl, builtInTypeScope, preLinkingPhase)
		functionType.addTypeVariables(functionDecl, preLinkingPhase)
		functionType.copyAnnotations(functionDecl, preLinkingPhase)
		functionType.declaredAsync = functionDecl.async // TODO change to declaredAsync one the annotation is gone

		// set container
		target.topLevelTypes += functionType
	}

	def private setTypeAccessModifier(TFunction fun, FunctionDeclaration funDecl) {
		setTypeAccessModifier(funDecl, [TypeAccessModifier modifier|fun.declaredTypeAccessModifier = modifier], funDecl.declaredModifiers,
			getAllAnnotations(funDecl))
	}

	/**
	 * Creates TFunction for the given function expression and adds it to the module. Note that this method applies
	 * only to expressions that define a function, not to function type expressions that merely define a function
	 * type (the latter are represented in the AST <em>and</em> TModule by a node of type <code>FunctionTypeExpression</code>
	 * from Types.xcore).
	 * <p>
	 * Creating a TFunction for a function expression becomes a bit tricky when type inference has to be used to
	 * infer the types of one or more formal parameters and/or the return value. These are the steps involved:
	 * <ol>
	 * <li>method {@link #createTFunction(FunctionExpression,TModule,boolean)} creates an initial TFunction in
	 *     which the type of every fpar may(!) be a ComputedTypeRef and the return type may(!) be a ComputedTypeRef.
	 *     ComputedTypeRefs are only used if there is no declared type available.
	 * <li>when the first(!) of these ComputedTypeRefs is resolved (and only for the first!), then method
	 *     {@link #resolveTFunction(ComputedTypeRef,TFunction,FunctionExpression,BuiltInTypeScope)}
	 *     is invoked. This method will handle the resolution of all ComputedTypeRefs of the given TFunction in
	 *     one step (to avoid unnecessary repeated inference of the expected type; note: caching does not help
	 *     here, because we call judgment 'expectedTypeIn' and not 'type').
	 * </ol>
	 */
	def package void createTFunction(FunctionExpression functionExpr, TModule target, boolean preLinkingPhase) {
		if (functionExpr.definedType !== null && ! functionExpr.definedType.eIsProxy) {
			throw new IllegalStateException("TFunction already created for FunctionExpression");
		}

		val builtInTypeScope = BuiltInTypeScope.get(functionExpr.eResource.resourceSet)
		val functionType = functionExpr.createAndLinkTFunction(preLinkingPhase)

		functionType.addFormalParametersWithInferredType(functionExpr, builtInTypeScope, preLinkingPhase)
		functionType.setReturnTypeWithInferredType(functionExpr, builtInTypeScope, preLinkingPhase)
		functionType.addTypeVariables(functionExpr, preLinkingPhase)

		functionType.copyAnnotations(functionExpr, preLinkingPhase)

		// set container
		target.internalTypes += functionType
	}

	/**
	 * Same as {@link AbstractFunctionDefinitionTypesBuilder#addFormalParameters(TFunction,FunctionDefinition,BuiltInTypeScope,boolean)},
	 * but uses a ComputedTypeRef as the fpar's type if the type has to be inferred.
	 */
	def private addFormalParametersWithInferredType(TFunction functionType, FunctionExpression functionExpr,
				BuiltInTypeScope builtInTypeScope, boolean preLinkingPhase) {
		functionType.fpars.addAll(
			functionExpr.fpars.map[it.createFormalParameter(
				TypeUtils.createDeferredTypeRef, //TypeUtils.createComputedTypeRef([resolveAllComputedTypeRefsInTFunction(functionType,functionExpr,builtInTypeScope)]),
				builtInTypeScope,
				preLinkingPhase
			)].filterNull);
	}

	/**
	 * Same as {@link AbstractFunctionDefinitionTypesBuilder#setReturnType(TFunction,FunctionDefinition,BuiltInTypeScope,boolean)},
	 * but uses a ComputedTypeRef as the return type if the type has to be inferred.
	 */
	def private setReturnTypeWithInferredType(TFunction functionType, FunctionExpression functionExpr,
				BuiltInTypeScope builtInTypeScope, boolean preLinkingPhase) {
		val inferredReturnTypeRef =
			if (functionExpr.returnTypeRef === null) {
				/*
				 * TODO IDE-1579 this branch skips makePromiseIfAsync.
				 * Question: could this result in 'void' as inferred return type (for an async method)?
				 */
				TypeUtils.createDeferredTypeRef //TypeUtils.createComputedTypeRef([resolveAllComputedTypeRefsInTFunction(functionType,functionExpr,builtInTypeScope)])
			} else {
				N4JSLanguageUtils.makePromiseIfAsync(functionExpr, functionExpr.returnTypeRef, builtInTypeScope)
			};
		setCopyOfReference([TypeRef typeRef | functionType.returnTypeRef = typeRef], inferredReturnTypeRef,
			preLinkingPhase)
	}

	def private TFunction createAndLinkTFunction(FunctionDefinition functionDef, boolean preLinkingPhase) {
		val functionType = TypesFactory::eINSTANCE.createTFunction();
		if(functionDef instanceof FunctionDeclaration) {
			functionType.exportedName = functionDef.exportedName;
			functionType.external = functionDef.external;
		}
		functionType.name = functionDef.name; // maybe null in case of function expression
		functionType.declaredAsync = functionDef.isAsync // TODO change to declaredAsync when annotation is removed

		functionType.linkThisTypeAnnotation(functionDef,preLinkingPhase)

		// link
		functionType.astElement = functionDef
		functionDef.definedType = functionType

		return functionType
	}
}
