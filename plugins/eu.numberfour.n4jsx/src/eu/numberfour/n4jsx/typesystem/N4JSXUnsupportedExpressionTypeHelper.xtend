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
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.DefaultUnsupportedExpressionTypeHelper
import eu.numberfour.n4jsx.helpers.ReactHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EReference

/**
 * Adds support for typing JSX elements.
 */
class N4JSXUnsupportedExpressionTypeHelper extends DefaultUnsupportedExpressionTypeHelper {
	@Inject	ReactHelper reactLookupHelper;
		
	override typeExpression(Expression expression, RuleEnvironment G) {
		if (expression instanceof JSXElement) {
			val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
			val classifierReactElement = reactLookupHelper.lookUpReactClassifier(expression, reference, ReactHelper.REACT_ELEMENT, ReactHelper.REACT_MODULE);
			
			if (classifierReactElement===null) {
				throw new IllegalStateException(ReactHelper.REACT_MODULE + "." + ReactHelper.REACT_ELEMENT +  " not found");
			}
			val typeRef = TypeUtils.createTypeRef(classifierReactElement)
			return typeRef
		} else {
			return super.typeExpression(expression, G)
		}
	}
}
