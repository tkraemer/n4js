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
package eu.numberfour.n4jsx.typesystem

import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.DefaultUnsupportedExpressionTypeHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.scoping.IGlobalScopeProvider
import org.eclipse.xtext.scoping.IScope
import eu.numberfour.n4js.ts.types.TypingStrategy
import org.eclipse.emf.ecore.util.EcoreUtil
import eu.numberfour.n4js.utils.EcoreUtilN4
import org.eclipse.emf.ecore.resource.ResourceSet

/**
 * Adds support for typing JSX elements.
 */
class N4JSXUnsupportedExpressionTypeHelper extends DefaultUnsupportedExpressionTypeHelper {

	@Inject
	IGlobalScopeProvider globalScoperProvider
	@Inject 
	IQualifiedNameConverter qualifedNameConverter
	
	override typeExpression(Expression expression, RuleEnvironment G) {
		if (expression instanceof JSXElement) {
			val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
			val IScope scope = globalScoperProvider.getScope(expression.eResource, reference, null)
			val eod = scope.getSingleElement(qualifedNameConverter.toQualifiedName("#/Element"))
			var elementClassT = eod.EObjectOrProxy as TClass 
			if (elementClassT.eIsProxy) {
				val ResourceSet resourceSet = EcoreUtilN4.getResourceSet(expression)
				elementClassT = EcoreUtil.resolve(elementClassT, resourceSet) as TClass
			}
			val typeRef = TypeUtils.createTypeRef(elementClassT)
			return typeRef
		} else {
			return super.typeExpression(expression, G)
		}
	}
}
