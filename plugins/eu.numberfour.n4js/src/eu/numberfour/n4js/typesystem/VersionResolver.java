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

/**
 * Strategy for resolving the actual version of a type referenced by a given type reference.
 */
public interface VersionResolver {
	/**
	 * Resolves the version referenced by the given type reference. May return the given type argument or a different
	 * instance that is assignable to <code>T</code>. Type arguments are also allowed to allow easier use of this
	 * method.
	 *
	 * @param typeRef
	 *            the type reference
	 * @param contextVersion
	 *            the context version to use when determining the actual version of the type referenced by the actual
	 *            parameter
	 * @return a reference to the resolved version
	 */
	public <T extends TypeArgument> T resolveVersion(T typeRef, int contextVersion);
}
