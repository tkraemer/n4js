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
import eu.numberfour.n4js.n4JS.ArrowFunction
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.GenericDeclaration
import eu.numberfour.n4js.n4JS.N4GetterDeclaration
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.utils.N4JSLanguageUtils

/**
 * Base class for functions and methods
 */
package class AbstractFunctionDefinitionTypesBuilder {

	@Inject extension N4JSTypesBuilderHelper
	@Inject extension N4JSFormalParameterTypesBuilder


	def protected addFormalParameters(TFunction functionType, FunctionDefinition functionDef,
				BuiltInTypeScope builtInTypeScope, boolean preLinkingPhase) {
		functionType.fpars.addAll(
			functionDef.fpars.map[createFormalParameter(builtInTypeScope, preLinkingPhase)].filterNull);
	}

	/*
	 * Transforms type variables from declaration (MethodDeclaration of FunctionDeclaration) to TFunction's type variables.
	 */
	def protected addTypeVariables(TFunction functionType, GenericDeclaration genericDecl, boolean preLinkingPhase) {
		addCopyOfReferences([params|functionType.typeVars += params], genericDecl.typeVars, preLinkingPhase)
	}

	def protected setReturnType(TGetter getterType, N4GetterDeclaration getterDef,
				BuiltInTypeScope builtInTypeScope, boolean preLinkingPhase) {
		val inferredReturnTypeRef =
			if (getterDef.declaredTypeRef === null) {
				if (!preLinkingPhase) {
					if(getterType.isAbstract) {
						builtInTypeScope.anyTypeRef
					} else {
						inferReturnTypeFromReturnStatements(getterDef, builtInTypeScope)
					}
				}
			} else {
				getterDef.declaredTypeRef
			};
		setCopyOfReference([TypeRef typeRef | getterType.declaredTypeRef = typeRef], inferredReturnTypeRef,
			preLinkingPhase)
	}

	def protected setReturnType(TFunction functionType, FunctionDefinition functionDef,
				BuiltInTypeScope builtInTypeScope, boolean preLinkingPhase) {
		val inferredReturnTypeRef =
			if (functionDef.returnTypeRef === null) {
				if (!preLinkingPhase)
					inferReturnTypeFromReturnStatements(functionDef, builtInTypeScope)
			} else {
				functionDef.returnTypeRef
			};
		setCopyOfReference([TypeRef typeRef | functionType.returnTypeRef = typeRef],
			N4JSLanguageUtils.makePromiseIfAsync(functionDef, inferredReturnTypeRef, builtInTypeScope),
			preLinkingPhase)
	}

	/**
	 * Poor man's return type inferencer
	 */
	// TODO improve that
	def protected ParameterizedTypeRef inferReturnTypeFromReturnStatements(FunctionDefinition definition, BuiltInTypeScope builtInTypeScope) {
		val hasNonVoidReturn = definition.body!==null && definition.body.hasNonVoidReturn;
		if (hasNonVoidReturn) {
			return builtInTypeScope.anyTypeRef
		} else {
			/*
			 * No Return statements usually implies void as result type for the FunctionDefinition,
			 * except for those representing arrow functions of the single-expression variety,
			 * whose result type is heuristically approximated as 'any'.
			 *
			 * FIXME that single-expr in an arrow function may well be an invocation to
			 * a void-method, in which case the 'any' choice is wrong.
			 */
			if (isSingleExprArrowFunction(definition)) {
				return builtInTypeScope.anyTypeRef
			} else {
				return builtInTypeScope.voidTypeRef
			}
		}
	}

	private def isSingleExprArrowFunction(FunctionDefinition definition) {
		switch definition {
			ArrowFunction: definition.isSingleExprImplicitReturn
			default: false
		}
	}

	/**
	 * Poor man's return type inferencer
	 */
	// TODO improve that
	def protected ParameterizedTypeRef inferReturnTypeFromReturnStatements(N4GetterDeclaration definition, BuiltInTypeScope builtInTypeScope) {
		val hasNonVoidReturn = definition.body!==null && definition.body.hasNonVoidReturn;
		if (hasNonVoidReturn) {
			builtInTypeScope.anyTypeRef
		} else {
			builtInTypeScope.voidTypeRef
		}
	}
}
