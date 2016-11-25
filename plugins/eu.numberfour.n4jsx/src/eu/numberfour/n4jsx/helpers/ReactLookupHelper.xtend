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
package eu.numberfour.n4jsx.helpers

import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.FunctionDeclaration
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.IScopeProvider

/**
 * This helper provides utilities for looking up React definitions
 */
class ReactLookupHelper {
	
	@Inject
	protected N4JSTypeSystem ts;
	
	@Inject
	IScopeProvider scopeProvider	

	def lookUpReactClassifier (EObject context, EReference reference, String reactName, String reactModuleName) {
		val IScope scope = scopeProvider.getScope(context, reference)
		val IEObjectDescription eod = scope.allElements.findFirst [ e |
			val classifier = e.EObjectOrProxy;
			if (classifier instanceof TClassifier) {
				return reactName == classifier.exportedName && reactModuleName == classifier.containingModule.qualifiedName
			}
			return false;
		]
		
		if (eod === null) 
			return null

		val classifier = eod.EObjectOrProxy;
		if (classifier.eIsProxy) {
			EcoreUtil2.resolve(classifier, context.eResource)
		}
		return classifier as TClassifier;
	}
	
	/**
	 * This methods check if a function declaration defines a React component 
	 * by checking whether its return type is a subtype of React.Element
	 */
	def isFunctionDeclarationAReactComponent(FunctionDeclaration funcDecl) {
		val funcReturnTypeRef = funcDecl.returnTypeRef;
		// Lookup React.Element type
		val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
		val elementClassTypeRef = lookUpReactClassifier(funcDecl, reference, "Element", "react")
		if (elementClassTypeRef === null)
			return false
		
		val G = RuleEnvironmentExtensions.newRuleEnvironment(funcDecl);
		val result = ts.subtype(G, funcReturnTypeRef, TypeUtils.createTypeRef(elementClassTypeRef))
		return result.value !== null && result.value		
	}
	
	def isClassDeclarationAReactComponent(N4ClassDeclaration classDecl) {
		val classTypeRef = TypeUtils.createTypeRef(classDecl.definedType)
		//Lookup React.Component type
		val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
		val componentClassTypeRef = lookUpReactClassifier(classDecl, reference, "Component", "react")
		if (componentClassTypeRef === null)
			return false
		
		val G = RuleEnvironmentExtensions.newRuleEnvironment(classDecl);
		val result = ts.subtype(G, classTypeRef, TypeUtils.createTypeRef(componentClassTypeRef))
		return (result.value !== null && result.value)
		
	}

}
