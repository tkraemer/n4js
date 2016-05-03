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
	 * Sugar for {@link #unselect(Iterable)}. Marks the selected working sets to be invisible.
	 *
	 * @param first
	 *            the first working set to be marked as unselected.
	 * @param others
	 *            other optional working sets to be marked as invisible.
	 */
	void unselect(WorkingSet first, WorkingSet... others);

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

}
