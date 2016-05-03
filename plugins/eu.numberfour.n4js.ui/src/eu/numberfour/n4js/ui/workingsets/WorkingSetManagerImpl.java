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

import static java.util.Collections.emptyList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 *
 */
public abstract class WorkingSetManagerImpl implements WorkingSetManager {

	@Override
	public Iterable<WorkingSet> getWorkingSets() {
		return emptyList();
	}

	@Override
	public Iterable<WorkingSet> getAllWorkingSets() {
		return emptyList();
	}

	@Override
	public IStatus save(IProgressMonitor monitor) {
		return Status.OK_STATUS;
	}

	@Override
	public void select(Iterable<WorkingSet> workingSets) {
		// NOOP
	}

	@Override
	public void select(WorkingSet first, WorkingSet... others) {
		// NOOP
	}

	@Override
	public void unselect(Iterable<WorkingSet> workingSets) {
		// NOOP
	}

	@Override
	public void remove(WorkingSet first, WorkingSet... others) {
		// NOOP
	}

}
