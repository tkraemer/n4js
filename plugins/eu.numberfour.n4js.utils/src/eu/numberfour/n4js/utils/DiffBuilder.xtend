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

import com.google.common.base.Preconditions
import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import java.util.List
import java.util.function.Function

/**
 * Builder for creating a {@link Diff} via a fluent API.
 */
abstract class DiffBuilder<F, T> {
	
	/**
	 * The initial, ordered state of the relevant items.
	 */
	protected val List<T> oldItems;
	
	/**
	 * The initial, ordered state of all items.
	 */
	protected val List<T> oldAllItems;
	
	/**
	 * The items that have been added.
	 */
	protected val List<T> addedItems;
	
	/**
	 * The removed items.
	 */
	protected val List<T> deletedItems;
	
	/**
	 * Map of edited items. Keys are old state, values are the new state.
	 */
	protected val BiMap<T, T> editedItems;
	
	new(F f) {
		oldItems = oldItemsFunction.apply(f);
		oldAllItems = allOldItemsFunction.apply(f);
		Preconditions.checkState(
			oldAllItems.containsAll(oldItems), 
			'''Not all old items is not a subset of all old items. Old items: «oldItems». All old items: «oldAllItems».'''
		);
		addedItems = newArrayList();
		deletedItems = newArrayList();
		editedItems = HashBiMap.create();
	}
	
	def add(T item) {
		if (!oldAllItems.contains(item) && !addedItems.contains(item)) {
			addedItems.add(item);
		}
		deletedItems.remove(item);
		return this;
	}
	
	def delete(T item) {
		val index = addedItems.indexOf(item);
		if (index >= 0) {
			addedItems.remove(index);
		} else {
			// Original state before the edition
			val originalItem = editedItems.inverse.remove(item);
			deletedItems.add(originalItem);
		}
		return this;
	}
	
	def edit(T oldState, T newState) {
		val index = addedItems.indexOf(oldState);
		if (index >= 0) {
			addedItems.remove(index);
			addedItems.add(index, newState);
		} else {
			editedItems.put(oldState, newState);
		}
		return this;
	}
	
	def Diff<T> build(T[] newItems, T[] newAllItems) {
		return new Diff(oldItems, oldAllItems, addedItems, deletedItems, editedItems, newItems, newAllItems);
	}
	
	/**
	 * Function from extracting the initial, ordered state of the relevant from the subject.
	 */
	protected def abstract Function<F, T[]> getOldItemsFunction();
	
	/**
	 * Function from extracting the initial, ordered state of all items from the subject.
	 */
	protected def abstract Function<F, T[]> getAllOldItemsFunction();
	
}
	