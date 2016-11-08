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
import eu.numberfour.n4js.ts.utils.TypeUtils;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * Default implementation of {@link TypeRefWrapper} that delegates to
 * {@link TypeUtils#wrapTypeInTypeRef(Type, TypeArgument...)}.
 */
public class N4JSTypeRefWrapper implements TypeRefWrapper {
	@Override
	public TypeRef wrapTypeInTypeRef(RuleEnvironment ruleEnvironment, Type type, TypeArgument... typeArgs) {
		return TypeUtils.wrapTypeInTypeRef(type, typeArgs);
	}
}
