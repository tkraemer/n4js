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
package eu.numberfour.n4js.utils

import java.util.Map
import org.eclipse.xtend.lib.annotations.Data
import com.google.common.collect.Iterables

/**
 * Simple POJO for representing a difference. 
 */
@Data
class Diff<T> {
	
	/**
	 * The initial, ordered state of the relevant items.
	 */
	val T[] oldItems;
	
	/**
	 * The initial, ordered state of all items.
	 */
	val T[] oldAllItems;
	
	/**
	 * The items that have been added.
	 */
	val T[] addedItems;
	
	/**
	 * The removed items.
	 */
	val T[] deletedItems;
	
	/**
	 * Map of edited items. Keys are old state, values are the new state.
	 */
	val Map<T, T> editedItems;
	
	/**
	 * The final, ordered state of the relevant items.
	 */
	val T[] newItems;
	
	/**
	 * The final, ordered state of all items.
	 */
	val T[] newAllItems;
	
	@Override
	override toString() {
		'''Diff:
		-----------------------------
		Old items:     «Iterables.toString(oldItems)»
		Old all items: «Iterables.toString(oldAllItems)»
		Added items:   «Iterables.toString(addedItems)»
		Deleted items: «Iterables.toString(deletedItems)»
		Edited items:  «editedItems»
		New items:     «Iterables.toString(newItems)»
		New all items: «Iterables.toString(newAllItems)»
		-----------------------------
		''';
	}
	
}
