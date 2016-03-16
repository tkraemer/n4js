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
package eu.numberfour.n4js.ui.wizard.model;

import java.beans.PropertyChangeListener;

/**
 * A property listenable objects.
 *
 */
public interface PropertyChangeListenable {
	/**
	 * Add a new property change listener
	 *
	 * @param listener
	 *            Listener to add
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * Remove a property change Listener
	 *
	 * @param listener
	 *            Listener to remove
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener);
}