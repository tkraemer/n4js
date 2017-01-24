/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.utils.collections

import java.util.List
import java.util.Objects
import java.util.Set

/**
 * Utility functions for collections
 */
class Collections2 {
	/**
	 * Private constructor to prevent instantiation.
	 */
	private new() {}
	
		/**
	 * Concatenate the given lists while omitting duplicates.
	 *
	 * @param listA
	 *            the first list, must not be <code>null</code>
	 * @param listB
	 *            the second list, must not be <code>null</code>
	 * @return the unique concatenation of the given lists
	 */
	def static <T> List<T> concatUnique(List<T> listA, List<T> listB) {
		Objects.requireNonNull(listA);
		Objects.requireNonNull(listB);

		val Set<T> result = newLinkedHashSet(listA);
		result.addAll(listB);
		return newLinkedList(result);
	}
}
