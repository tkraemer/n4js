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
import eu.numberfour.n4js.ts.typeRefs.VersionedReference;

/**
 * Strategy for resolving the actual version of a type referenced by a given type reference.
 */
public interface VersionResolver {
	/**
	 * Returns a type reference referencing the requested version of the type referenced by the given type reference. If
	 * the given versioned reference is not actually a subtype of {@link VersionedReference}, the given type reference
	 * is returned as is.
	 *
	 * @see #resolveVersion(TypeArgument, VersionedReference)
	 *
	 * @param typeRef
	 *            the type reference to resolve
	 * @param versionedReference
	 *            the versioned reference to obtain the context version from
	 * @return a reference to the resolved version of the type
	 */
	public default <T extends TypeArgument> T resolveVersion(T typeRef, TypeRef versionedReference) {
		if (versionedReference instanceof VersionedReference)
			return resolveVersion(typeRef, (VersionedReference) versionedReference);
		else
			return typeRef;
	}

	/**
	 * Returns a type reference referencing the requested version of the type referenced by the given type reference. If
	 * the given versioned reference does not have a requested version, the given type reference is returned as is.
	 *
	 * @see #resolveVersion(TypeArgument, int)
	 *
	 * @param typeRef
	 *            the type reference to resolve
	 * @param versionedReference
	 *            the versioned reference to obtain the context version from
	 * @return a reference to the resolved version of the type
	 */
	public default <T extends TypeArgument> T resolveVersion(T typeRef, VersionedReference versionedReference) {
		if (versionedReference.hasRequestedVersion())
			return resolveVersion(typeRef, versionedReference.getRequestedVersionOrZero());
		else
			return typeRef;
	}

	/**
	 * Returns a type reference referencing the requested version of the type referenced by the given type reference.
	 * May return the given type argument or a different instance that is assignable to <code>T</code>. Type arguments
	 * are also allowed to allow easier use of this method.
	 *
	 * @param typeRef
	 *            the type reference to resolve
	 * @param contextVersion
	 *            the context version to use when determining the actual version of the type referenced by the actual
	 *            parameter
	 * @return a reference to the resolved version of the type
	 */
	public <T extends TypeArgument> T resolveVersion(T typeRef, int contextVersion);
}
