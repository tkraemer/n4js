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
import eu.numberfour.n4js.utils.EcoreUtilN4
import eu.numberfour.n4jsx.n4JSX.JSXElement
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.IScopeProvider
import eu.numberfour.n4js.ts.types.TClassifier
import org.eclipse.xtext.util.IResourceScopeCache
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.EcoreUtil2

/**
 * Adds support for typing JSX elements.
 */
class N4JSXUnsupportedExpressionTypeHelper extends DefaultUnsupportedExpressionTypeHelper {

	@Inject
	IScopeProvider scopeProvider
	@Inject 
	IQualifiedNameConverter qualifedNameConverter
	
	private static val REACT_ELEMENT = "react.Element";
	
	@Inject
	private IResourceScopeCache resourceScopeCacheHelper;
	
	override typeExpression(Expression expression, RuleEnvironment G) {
		if (expression instanceof JSXElement) {
			val classifierReactElement = resourceScopeCacheHelper.get(REACT_ELEMENT, expression.eResource, [
				val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
				val IScope scope = scopeProvider.getScope(expression, reference)
				val IEObjectDescription eod = scope.allElements.findFirst[e|
						val classifier = e.EObjectOrProxy;	
					if (classifier instanceof TClassifier) {
						return "Element"==classifier.exportedName && "react"==classifier.containingModule.qualifiedName
					}
					return false;
				]
				
				val classifier = eod.EObjectOrProxy as TClassifier;
				if (classifier.eIsProxy) {
					EcoreUtil2.resolve(classifier, expression.eResource)
				}				
				return classifier;
			]);
			
			if (classifierReactElement===null) {
				throw new IllegalStateException("React.Element not found");
			}
			val typeRef = TypeUtils.createTypeRef(classifierReactElement)
			return typeRef
		} else {
			return super.typeExpression(expression, G)
		}
	}
}
