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
package eu.numberfour.n4js.typesystem;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * This interface defines a variation point for extending the type system, in particular for extending the rules that
 * are used to type expressions. It can be used in sub languages such as N4JSX to type newly added expression types. It
 * cannot be used to override the typing of N4JS expressions, since it is only invoked when the N4JS type system
 * encounters expressions that it cannot handle itself.
 */
public interface UnsupportedExpressionTypeHelper {
	/**
	 * Types an expression.
	 *
	 * @param expression
	 *            the expression to type
	 * @param G
	 *            the rule environment
	 * @return a type reference
	 */
	public TypeRef typeExpression(Expression expression, RuleEnvironment G);
}
