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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Base working set implementation.
 */
public abstract class WorkingSetImpl implements WorkingSet {

	private final String name;
	private final WorkingSetManager manager;

	/**
	 * Creates a new working set manager with the given name and the container manager.
	 *
	 * @param name
	 *            the name of the working set.
	 * @param manager
	 *            the container manager where this working set belongs to.
	 */
	protected WorkingSetImpl(final String name, final WorkingSetManager manager) {
		this.name = checkNotNull(name, "name");
		this.manager = checkNotNull(manager, "manager");
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public WorkingSetManager getWorkingSetManager() {
		return manager;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((manager.getId() == null) ? 0 : manager.getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof WorkingSetImpl)) {
			return false;
		}
		WorkingSetImpl other = (WorkingSetImpl) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (manager.getId() == null) {
			if (other.manager.getId() != null) {
				return false;
			}
		} else if (!manager.getId().equals(other.manager.getId())) {
			return false;
		}
		return true;
	}

}
