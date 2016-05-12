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
import static com.google.common.collect.Sets.newHashSet;
import static eu.numberfour.n4js.ui.workingsets.WorkingSet.OTHERS_WORKING_SET_ID;
import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.graphics.Image;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.ui.ImageDescriptorCache.ImageRef;
import eu.numberfour.n4js.utils.Diff;

/**
 * Working set manager implementation for managing working sets that have been manually configured by picking and
 * associating workspace projects with one ore more working sets.
 */
public class ManualAssociationAwareWorkingSetManager extends WorkingSetManagerImpl implements MutableWorkingSetManager {

	private static final Logger LOGGER = Logger.getLogger(ManualAssociationAwareWorkingSetManager.class);

	private static final String ORDERED_ASSOCIATIONS_KEY = ".orderedAssociations";

	/**
	 * Ordered map of working set and workspace project associations.
	 */
	private final ProjectAssociation projectAssociations = new ProjectAssociation();

	@Inject
	private Provider<WorkingSetManualAssociationWizard> wizardProvider;

	@Inject
	private ObjectMapper mapper;

	@Override
	public String getLabel() {
		return "Manual Association";
	}

	@Override
	public Optional<Image> getImage() {
		return ImageRef.PROJECT_MODE.asImage();
	}

	@Override
	public IStatus saveState(final IProgressMonitor monitor) {
		final IStatus superSaveResult = super.saveState(monitor);

		if (superSaveResult.isOK()) {

			final Preferences node = getPreferences();

			try {
				final String associationString = mapper.writeValueAsString(projectAssociations);
				node.put(ORDERED_ASSOCIATIONS_KEY, associationString);
				node.flush();
			} catch (final BackingStoreException e) {
				final String message = "Error occurred while saving state to preference store.";
				LOGGER.error(message, e);
				return statusHelper.createError(message, e);
			} catch (final IOException e) {
				final String message = "Error occurred while serializing project associations.";
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
				try {
					final ProjectAssociation association = mapper.readValue(orderedFilters, ProjectAssociation.class);
					projectAssociations.clear();
					projectAssociations.putAll(association);
				} catch (final IOException e) {
					final String message = "Error occurred while deserializing project associations.";
					LOGGER.error(message, e);
					return statusHelper.createError(message, e);
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
			projectAssociations.clear();

			for (final WorkingSet workingSet : diff.getNewAllItems()) {
				final ManualAssociationWorkingSet associationWorkingSet = (ManualAssociationWorkingSet) workingSet;
				final Collection<String> projectNames = associationWorkingSet.getAssociatedProjectNames();
				final String id = associationWorkingSet.getId();
				if (OTHERS_WORKING_SET_ID.equals(id)) {
					projectAssociations.put(id, emptyList());
				} else {
					projectAssociations.put(id, projectNames);
				}
			}

			discardWorkingSetState();
			getWorkingSetManagerBroker().fireWorkingSetManagerUpdated(getId(), diff);

		}
	}

	@Override
	protected List<WorkingSet> initializeWorkingSets() {
		checkState(projectAssociations.keySet().size() == orderedWorkingSetIds.size(),
				"Expected same number of working set names as project associations."
						+ "\nNames were: " + Iterables.toString(orderedWorkingSetIds)
						+ "\nAssociations were: " + projectAssociations);

		if (projectAssociations.isEmpty()) {
			projectAssociations.put(OTHERS_WORKING_SET_ID, emptyList());
			orderedWorkingSetIds.add(OTHERS_WORKING_SET_ID);
		}

		final int size = projectAssociations.keySet().size();
		final WorkingSet[] workingSets = new WorkingSet[size];
		for (int i = 0; i < size; i++) {
			final String name = orderedWorkingSetIds.get(i);
			final Collection<String> projectNames = projectAssociations.get(name);
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
	 * Custom linked hash map implementation used as the manual project associations.
	 */
	public static final class ProjectAssociation extends LinkedHashMap<String, Collection<String>> {

		/**
		 * Creates a new project association with the default map capacity and load factor.
		 */
		public ProjectAssociation() {
			super(16, 0.75F);
		}

	}

	/**
	 * Working set that represents manual associations between workspace projects and working sets.
	 */
	public static final class ManualAssociationWorkingSet extends DefaultWorkingSetImpl {

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
		public ManualAssociationWorkingSet(final Iterable<String> projectNames, final String name,
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

		/**
		 * Returns with a view of project names that are associated with the working set.
		 *
		 * @return a collection of associated project names.
		 */
		public Collection<String> getAssociatedProjectNames() {
			return from(Arrays.asList(getAllProjects()))
					.filter(p -> projectNames.contains(p.getName()))
					.transform(p -> p.getName())
					.toSet();
		}

		@Override
		public String toString() {
			return super.toString() + " " + Iterables.toString(projectNames);
		}

	}

}
