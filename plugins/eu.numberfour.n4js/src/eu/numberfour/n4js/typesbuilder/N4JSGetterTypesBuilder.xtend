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
import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.N4GetterDeclaration
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef
import eu.numberfour.n4js.ts.types.MemberAccessModifier
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.ts.types.TypesFactory
import eu.numberfour.n4js.ts.utils.TypeUtils

/**
 */
package class N4JSGetterTypesBuilder extends AbstractFunctionDefinitionTypesBuilder {

	@Inject extension N4JSTypesBuilderHelper

	def package TGetter createGetter(N4GetterDeclaration n4Getter, TClassifier classifierType, boolean preLinkingPhase) {
		if (n4Getter.name === null) {
			return null
		}
		val getterType = TypesFactory::eINSTANCE.createTGetter
		getterType.name = n4Getter.name
		getterType.declaredAbstract = n4Getter.abstract
		getterType.declaredStatic = n4Getter.declaredStatic
		getterType.declaredFinal = n4Getter.declaredFinal
		getterType.declaredOverride = AnnotationDefinition.OVERRIDE.hasAnnotation(n4Getter);

		getterType.hasNoBody = n4Getter.body ===null&& !AnnotationDefinition.PROVIDES_DEFAULT_IMPLEMENTATION.hasAnnotation(n4Getter);

		// TODO if possible, remove, see AbstractFunctionDefinitionTypesBuilder
		val builtInTypeScope = BuiltInTypeScope.get(n4Getter.eResource.resourceSet)

		getterType.setMemberAccessModifier(n4Getter)
		getterType.setReturnTypeConsideringThis(n4Getter, builtInTypeScope, preLinkingPhase)

		getterType.copyAnnotations(n4Getter, preLinkingPhase)

		getterType.astElement = n4Getter
		n4Getter.definedGetter = getterType
		getterType;
	}

	def private setMemberAccessModifier(TGetter getterType, N4GetterDeclaration n4Getter) {
		setMemberAccessModifier([MemberAccessModifier modifier |
			getterType.declaredMemberAccessModifier = modifier
		], n4Getter.declaredModifiers, n4Getter.annotations)
	}

	/**
	 * Sets the return type. If the declared return type is 'this', a ComputedTypeRef will
	 * be created to generate a bound this type.
	 */
	def private setReturnTypeConsideringThis(TGetter getterType, N4GetterDeclaration getterDecl,
				BuiltInTypeScope builtInTypeScope, boolean preLinkingPhase) {
		// TODO: explicitly differentiate between declared and inferred type
		if(getterDecl.declaredTypeRef instanceof ThisTypeRef) {
			// special case: TypingASTWalker will create a BoundThisTypeRef via Xsemantics judgment 'thisTypeRef'
			getterType.declaredTypeRef = TypeUtils.createDeferredTypeRef
		}
		else {
			// standard case
			getterType.setReturnType(getterDecl, builtInTypeScope, preLinkingPhase)
		}
	}
}
