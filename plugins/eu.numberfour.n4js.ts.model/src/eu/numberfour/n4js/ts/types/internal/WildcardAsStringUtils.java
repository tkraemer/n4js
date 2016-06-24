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
package eu.numberfour.n4js.ts.types.internal;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.Wildcard;

/**
 * In the {@link TypeArgument#getTypeRefAsString() #getTypeRefAsString()} method of {@link Wildcard}s, we want to show
 * implicit upper bounds (e.g. helpful in error messages). However, this means we will run into an infinite recursion
 * issue in cases like:
 *
 * <pre>
 * class C&lt;T extends C&lt;?>> {
 * }
 * </pre>
 *
 * To properly guard for infinite recursion in such cases, this class contains some special handling and method
 * {@link Wildcard#getTypeRefAsString()} delegates here.
 */
public final class WildcardAsStringUtils {

	private static final Set<Thread> threadsCurrentlyConvertingWildcardsWithImplicitUpperBounds = ConcurrentHashMap
			.newKeySet();

	/**
	 * Method {@link Wildcard#getTypeRefAsString()} delegates here. Should not be called by any other code!
	 */
	public static final String getTypeRefAsString(Wildcard w) {
		if (w.isImplicitUpperBoundInEffect()) {
			final Thread myThread = Thread.currentThread();
			final boolean wIsFirst = threadsCurrentlyConvertingWildcardsWithImplicitUpperBounds.add(myThread);
			try {
				return primGetTypeRefAsString(w, wIsFirst); // first shows the implicit bounds, all later ones don't
			} finally {
				if (wIsFirst)
					threadsCurrentlyConvertingWildcardsWithImplicitUpperBounds.remove(myThread);
			}
		} else {
			return primGetTypeRefAsString(w, false);
		}
	}

	private static final String primGetTypeRefAsString(Wildcard w, boolean showImplicitUpperBounds) {
		final StringBuilder sb = new StringBuilder();
		sb.append("?");
		final TypeRef declUpperBound = w.getDeclaredUpperBound();
		final List<TypeRef> upperBounds = showImplicitUpperBounds ? w.getDeclaredOrImplicitUpperBounds()
				: (declUpperBound != null ? Collections.singletonList(declUpperBound) : Collections.emptyList());
		final TypeRef lowerBound = w.getDeclaredLowerBound();
		if (!upperBounds.isEmpty()) {
			sb.append(" extends ");
			for (int i = 0; i < upperBounds.size(); i++) {
				if (i > 0)
					sb.append(" & ");
				sb.append(upperBounds.get(i).getTypeRefAsString());
			}
		} else if (lowerBound != null) {
			sb.append(" super ");
			sb.append(lowerBound.getTypeRefAsString());
		}
		return sb.toString();
	}

	private WildcardAsStringUtils() {
	}
}
