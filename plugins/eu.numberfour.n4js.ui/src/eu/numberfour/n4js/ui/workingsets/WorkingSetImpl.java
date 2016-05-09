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

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

/**
 *
 */
public abstract class WorkingSetImpl implements WorkingSet, Predicate<IProject> {

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
		this.name = name;
		this.manager = manager;
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
	public IAdaptable[] getElements() {
		final IProject[] projects = getAllProjects();
		if (OTHERS_WORKING_SET_LABEL.equals(name)) {

			// No other working sets are available at all.
			final WorkingSet[] allWorkingSets = manager.getAllWorkingSets();
			if (allWorkingSets.length == 1) {
				return projects;
			} else {

				// We have to exclude all those projects that are associated with at least one other working set.
				final FluentIterable<WorkingSet> others = from(asList(allWorkingSets))
						.filter(ws -> !OTHERS_WORKING_SET_LABEL.equals(ws.getName()));

				// Mapping between non-built-in working sets and their elements.
				final Map<WorkingSet, Collection<IAdaptable>> elementMapping = Maps.toMap(others,
						ws -> newHashSet(ws.getElements()));

				final IProject[] elements = new IProject[projects.length];
				int elementCount = 0;
				for (int i = 0, size = projects.length; i < size; i++) {
					final IProject project = projects[i];
					// If the project is not assigned to any other working set, then assign it to the built-in one.
					if (!Iterables.any(others, ws -> elementMapping.get(ws).contains(project))) {
						elements[elementCount++] = project;
					}
				}

				return Arrays.copyOfRange(elements, 0, elementCount);
			}

		} else {
			final IProject[] elements = new IProject[projects.length];
			int elementCount = 0;
			for (int i = 0, size = projects.length; i < size; i++) {
				final IProject project = projects[i];
				if (apply(project)) {
					elements[elementCount++] = project;
				}
			}
			return Arrays.copyOfRange(elements, 0, elementCount);
		}
	}

	/**
	 * Sugar for getting all workspace projects from the workspace root.
	 *
	 * @return all projects from the workspace root.
	 */
	protected IProject[] getAllProjects() {
		return ResourcesPlugin.getWorkspace().getRoot().getProjects();
	}

}
