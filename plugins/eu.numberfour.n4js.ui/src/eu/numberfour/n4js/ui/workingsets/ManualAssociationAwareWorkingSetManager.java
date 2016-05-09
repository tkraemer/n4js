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
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.utils.Diff;

/**
 * Working set manager implementation for managing working sets that have been manually configured by picking and
 * associating workspace projects with one ore more working sets.
 */
public class ManualAssociationAwareWorkingSetManager extends WorkingSetManagerImpl implements MutableWorkingSetManager {

	private static final Logger LOGGER = Logger.getLogger(ManualAssociationAwareWorkingSetManager.class);

	private static final String ORDERED_ASSOCIATIONS_KEY = ".orderedAssociations";

	private static final String KEY_VALUE_SEPARATOR = "$";

	/**
	 * Ordered multimap of working set and workspace project associations.
	 */
	private final Multimap<String, String> orderedProjectAssociations = LinkedHashMultimap.create();

	@Inject
	private Provider<WorkingSetManualAssociationWizard> wizardProvider;

	@Override
	public String getLabel() {
		return "Manual";
	}

	@Override
	public IStatus saveState(final IProgressMonitor monitor) {
		final IStatus superSaveResult = super.saveState(monitor);

		if (superSaveResult.isOK()) {

			final Preferences node = getPreferences();
			node.put(ORDERED_ASSOCIATIONS_KEY, Joiner.on(SEPARATOR).withKeyValueSeparator(KEY_VALUE_SEPARATOR)
					.join(orderedProjectAssociations.asMap()));

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
			final String orderedFilters = node.get(ORDERED_ASSOCIATIONS_KEY, EMPTY_STRING);
			if (!Strings.isNullOrEmpty(orderedFilters)) {
				orderedProjectAssociations.clear();
				final Map<String, String> storedMap = Splitter.on(SEPARATOR).withKeyValueSeparator(KEY_VALUE_SEPARATOR)
						.split(orderedFilters);

				for (final Entry<String, String> entry : storedMap.entrySet()) {
					final List<String> values = Arrays.asList(entry.getValue().split(KEY_VALUE_SEPARATOR));
					orderedProjectAssociations.putAll(entry.getKey(), values);
				}
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
			orderedProjectAssociations.clear();

			for (final WorkingSet workingSet : diff.getNewAllItems()) {
				final ManualAssociationWorkingSet associationWorkingSet = (ManualAssociationWorkingSet) workingSet;
				orderedProjectAssociations.putAll(associationWorkingSet.getName(), associationWorkingSet.projectNames);
			}

			discardWorkingSetState();

		}
	}

	@Override
	protected List<WorkingSet> initializeWorkingSets() {
		checkState(orderedProjectAssociations.keySet().size() == orderedWorkingSetNames.size(),
				"Expected same number of working set names as working set filters."
						+ "\nNames were: " + Iterables.toString(orderedWorkingSetNames)
						+ "\nAssociations were: " + orderedProjectAssociations);

		if (orderedProjectAssociations.isEmpty()) {
			orderedProjectAssociations.putAll(ORDERED_ASSOCIATIONS_KEY, newArrayList());
			orderedWorkingSetNames.add(OTHERS_WORKING_SET_LABEL);
		}

		final int size = orderedProjectAssociations.keySet().size();
		final WorkingSet[] workingSets = new WorkingSet[size];
		for (int i = 0; i < size; i++) {
			final String name = orderedWorkingSetNames.get(i);
			final Collection<String> projectNames = orderedProjectAssociations.get(name);
			workingSets[i] = new ManualAssociationWorkingSet(projectNames, name, this);
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
	 * Working set that represents manual workspace project - working set associations.
	 */
	protected static final class ManualAssociationWorkingSet extends WorkingSetImpl {

		private final Collection<String> projectNames;

		/**
		 * Creates a new working set manager with the given name, associated project names and the container manager.
		 *
		 * @param projectNames
		 *            a collection of project names that were associated with the working set.
		 * @param name
		 *            the name of the working set.
		 * @param manager
		 *            the container manager where this working set belongs to.
		 */
		protected ManualAssociationWorkingSet(final Iterable<String> projectNames, final String name,
				final WorkingSetManager manager) {

			super(name, manager);
			this.projectNames = newHashSet(projectNames);
		}

		@Override
		public boolean apply(final IProject project) {
			return projectNames.contains(project.getName());
		}

		/**
		 * Returns with a view of projects that are associated with the working set.
		 *
		 * @return a collection of associated projects.
		 */
		public Collection<IProject> getAssociatedProjects() {
			return from(Arrays.asList(getAllProjects()))
					.filter(p -> projectNames.contains(p.getName()))
					.toSet();
		}

		@Override
		public String toString() {
			return getName() + " " + Iterables.toString(projectNames);
		}

	}

}
