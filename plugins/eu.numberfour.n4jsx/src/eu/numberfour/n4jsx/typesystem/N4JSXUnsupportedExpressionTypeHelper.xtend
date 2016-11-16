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

import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.typesystem.DefaultUnsupportedExpressionTypeHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import it.xsemantics.runtime.RuleEnvironment

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * Adds support for typing JSX elements.
 */
class N4JSXUnsupportedExpressionTypeHelper extends DefaultUnsupportedExpressionTypeHelper {
	override typeExpression(Expression expression, RuleEnvironment G) {
		switch (expression) {
			JSXElement: G.anyTypeRef
			default: super.typeExpression(expression, G) 
		}
	}
}