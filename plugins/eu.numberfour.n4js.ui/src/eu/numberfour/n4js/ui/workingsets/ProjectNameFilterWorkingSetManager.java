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

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static eu.numberfour.n4js.ui.workingsets.WorkingSet.OTHERS_WORKING_SET_LABEL;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.utils.Diff;

/**
 * Working set manager that groups projects to contained working sets by applying a regular expression filter on the
 * project names.
 */
public class ProjectNameFilterWorkingSetManager extends ImmutableWorkingSetManager implements MutableWorkingSetManager {

	private static final Logger LOGGER = Logger.getLogger(ProjectNameFilterWorkingSetManager.class);

	private static final String ANY_MATCH = ".*";

	private static final String ORDERED_FILTERS_KEY = ".orderedFilters";

	/**
	 * Ordered list of the project name filters.
	 */
	private final List<String> orderedWorkingSetFilters = newArrayList();

	@Inject
	private Provider<WorkingSetProjectNameFilterWizard> wizardProvider;

	@Override
	public String getId() {
		return ProjectNameFilterWorkingSetManager.class.getName();
	}

	@Override
	public String getLabel() {
		return "Project Name Filter";
	}

	@Override
	public IStatus saveState(final IProgressMonitor monitor) {
		final IStatus superSaveResult = super.saveState(monitor);

		if (superSaveResult.isOK()) {

			final Preferences node = getPreferences();
			node.put(ORDERED_FILTERS_KEY, Joiner.on(SEPARATOR).join(orderedWorkingSetFilters));

			try {
				node.flush();
			} catch (final BackingStoreException e) {
				final String message = "Error occurred while saving state to preference store.";
				LOGGER.error(message, e);
				return statusHelper.createError(message, e);
			}

			return statusHelper.OK();
		}

		return superSaveResult;
	}

	@Override
	public IStatus restoreState(final IProgressMonitor monitor) {
		final IStatus superRestoreResult = super.restoreState(monitor);

		if (superRestoreResult.isOK()) {

			final Preferences node = getPreferences();
			final String orderedFilters = node.get(ORDERED_FILTERS_KEY, EMPTY_STRING);
			if (!Strings.isNullOrEmpty(orderedFilters)) {
				orderedWorkingSetFilters.clear();
				orderedWorkingSetFilters.addAll(Arrays.asList(orderedFilters.split(SEPARATOR)));
			}

			discardWorkingSetState();
			return statusHelper.OK();

		}

		return superRestoreResult;
	}

	@Override
	public void updateState(final Diff<WorkingSet> diff) {
		super.updateState(diff);
		if (!diff.isEmpty()) {
			// Update ordered filters.
			orderedWorkingSetFilters.clear();

			for (final WorkingSet workingSet : diff.getNewAllItems()) {
				final ProjectNameFilterWorkingSet nameFilterWorkingSet = (ProjectNameFilterWorkingSet) workingSet;
				orderedWorkingSetFilters.add(nameFilterWorkingSet.getFilter().pattern());
			}

			discardWorkingSetState();

		}
	}

	@Override
	protected List<WorkingSet> initializeWorkingSets() {
		checkState(orderedWorkingSetFilters.size() == orderedWorkingSetLabels.size(),
				"Expected same number of working set labels as working set filters. Labels was: "
						+ Iterables.toString(orderedWorkingSetLabels)
						+ " Filters was: " + Iterables.toString(orderedWorkingSetFilters) + ".");

		if (orderedWorkingSetFilters.isEmpty()) {
			orderedWorkingSetFilters.add(ANY_MATCH);
			orderedWorkingSetLabels.add(OTHERS_WORKING_SET_LABEL);
		}

		final int size = orderedWorkingSetFilters.size();
		final WorkingSet[] workingSets = new WorkingSet[size];
		for (int i = 0; i < size; i++) {
			final String regex = orderedWorkingSetFilters.get(i);
			final String label = orderedWorkingSetLabels.get(i);
			workingSets[i] = new ProjectNameFilterWorkingSet(Pattern.compile(regex), label, this);
		}

		return Arrays.asList(workingSets);
	}

	@Override
	public WorkingSetNewWizard createNewWizard() {
		return wizardProvider.get();
	}

	@Override
	public WorkingSetEditWizard createEditWizard() {
		return wizardProvider.get();
	}

	/**
	 * Working set implementation with project name filter support.
	 */
	protected static final class ProjectNameFilterWorkingSet implements WorkingSet {

		private final Pattern filter;
		private final String label;
		private final WorkingSetManager manager;

		/**
		 * Creates a new working set manager with the given label, project name pattern and the container manager.
		 *
		 * @param filter
		 *            the project name filter.
		 * @param label
		 *            the label of the working set.
		 * @param manager
		 *            the container manager where this working set belongs to.
		 */
		protected ProjectNameFilterWorkingSet(final Pattern filter, final String label,
				final WorkingSetManager manager) {

			this.filter = filter;
			this.label = label;
			this.manager = manager;
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public IAdaptable[] getElements() {

			final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			if (OTHERS_WORKING_SET_LABEL.equals(label)) {

				// No other working sets are available at all.
				final WorkingSet[] allWorkingSets = manager.getAllWorkingSets();
				if (allWorkingSets.length == 1) {
					return projects;
				} else {

					// We have to exclude all those projects that are associated with at least one other working set.
					final FluentIterable<WorkingSet> others = from(Arrays.asList(allWorkingSets))
							.filter(ws -> !OTHERS_WORKING_SET_LABEL.equals(ws.getLabel()));

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
					if (filter.matcher(project.getName()).matches()) {
						elements[elementCount++] = project;
					}
				}
				return Arrays.copyOfRange(elements, 0, elementCount);
			}

		}

		@Override
		public WorkingSetManager getWorkingSetManager() {
			return manager;
		}

		/**
		 * Returns with the project name filter pattern.
		 *
		 * @return the project name filter pattern.
		 */
		public Pattern getFilter() {
			return filter;
		}

		@Override
		public String toString() {
			return getLabel() + " [" + filter + "]";
		}

	}

}
