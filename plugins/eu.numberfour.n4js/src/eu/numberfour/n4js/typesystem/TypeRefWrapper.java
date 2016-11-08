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

import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.Type;
import it.xsemantics.runtime.RuleEnvironment;

/**
 *
 */
public interface TypeRefWrapper {
	/**
	 * Wraps the given type and type arguments in a type reference.
	 *
	 * @param ruleEnvironment
	 *            the rule environment
	 * @param type
	 *            the type to wrap
	 * @param typeArgs
	 *            the type arguments
	 * @return the type reference
	 */
	public TypeRef wrapTypeInTypeRef(RuleEnvironment ruleEnvironment, Type type, TypeArgument... typeArgs);
}
