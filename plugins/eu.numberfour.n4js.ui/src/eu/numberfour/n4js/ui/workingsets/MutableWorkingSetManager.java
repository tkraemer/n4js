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
package eu.numberfour.n4js.ui.workingsets;

/**
 * Configurable working set representation.
 */
public interface MutableWorkingSetManager extends WorkingSetManager {

	/**
	 * Configures the state of the working set manager.
	 */
	void configure();

	/**
	 * Associated the working set arguments with the current manager instance by adding them to the manager. Has no side
	 * effect if the argument working sets are already managed by this instance.
	 *
	 * @param first
	 *            the working set to be added to this manager.
	 * @param others
	 *            other working sets to be added to this manager.
	 */
	void add(WorkingSet first, WorkingSet... others);

	/**
	 * Detach the working set arguments from the current manager instance by removing them to the manager. Has no side
	 * effect if the argument working sets are not managed by this instance.
	 *
	 * @param first
	 *            the working set to be removed from this manager.
	 * @param others
	 *            other working sets to be removed from this manager.
	 */
	void remove(WorkingSet first, WorkingSet... others);

	/**
	 * Performs an update on an existing working set.
	 * 
	 * @param oldState
	 *            the old state of an existing working set.
	 * @param newState
	 *            the new state of an existing working set.
	 */
	void update(WorkingSet oldState, WorkingSet newState);

}
