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

import org.eclipse.core.runtime.IAdaptable;

/**
 * Representation of a working set. A working set holds a number of {@link IAdaptable adaptable} elements. A working set
 * is intended to group elements for presentation to the user or for operations on a set of elements. Each working set
 * must have a unique name per container working set manager.
 */
public interface WorkingSet {

	/**
	 * The reserved name of the default built-in working set.
	 */
	String OTHERS_WORKING_SET_LABEL = "Others";

	/**
	 * Returns with the name of the working set. Working sets that belong to the same {@link #getWorkingSetManager()
	 * working set manager} must have unique name.
	 *
	 * @return the name of the working set.
	 */
	String getName();

	/**
	 * The container manager of the working set.
	 *
	 * @return the container working set manager.
	 */
	WorkingSetManager getWorkingSetManager();

	/**
	 * Returns the elements that are contained in this working set.
	 *
	 * @return the working set elements.
	 */
	IAdaptable[] getElements();

}
