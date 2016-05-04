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

import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newLinkedHashSet;

import java.util.Collection;
import java.util.Map;

/**
 * Representation of a bunch of working set differences made by the working set manager configuration.
 */
public class WorkingSetDiff {

	private final WorkingSet[] initialWorkingSets;
	private final WorkingSet[] initialAllWorkingSets;
	private final Collection<WorkingSet> newWorkingSets;
	private final Collection<WorkingSet> deletedWorkingSets;
	private final Map<WorkingSet, WorkingSet> editedWorkingSets;

	/**
	 * Creates a new mutable working set difference instance.
	 * 
	 * @param manager
	 *            the manager which is the object of the modification.
	 */
	public WorkingSetDiff(WorkingSetManager manager) {
		initialWorkingSets = manager.getWorkingSets();
		initialAllWorkingSets = manager.getAllWorkingSets();
		newWorkingSets = newLinkedHashSet();
		deletedWorkingSets = newLinkedHashSet();
		editedWorkingSets = newHashMap();
	}

}
