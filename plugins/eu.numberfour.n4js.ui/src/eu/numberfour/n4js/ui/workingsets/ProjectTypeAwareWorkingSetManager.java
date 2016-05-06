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

import static com.google.common.base.Strings.nullToEmpty;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static eu.numberfour.n4js.n4mf.ProjectType.API;
import static org.eclipse.xtext.ui.XtextProjectHelper.hasBuilder;
import static org.eclipse.xtext.ui.XtextProjectHelper.hasNature;
import static org.eclipse.xtext.util.Strings.toFirstUpper;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

import eu.numberfour.n4js.n4mf.ProjectType;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.utils.Diff;
import eu.numberfour.n4js.utils.StatusHelper;

/**
 *
 */
public class ProjectTypeAwareWorkingSetManager implements WorkingSetManager {

	private static final Logger LOGGER = Logger.getLogger(ProjectTypeAwareWorkingSetManager.class);

	private static final String SEPARATOR = "#";
	private static final String ORDERED_LABELS_KEY = ".orderedLabels";
	private static final String VISIBLE_LABELS_KEY = ".visibleLabels";

	private final List<String> orderedWorkingSetLabels = newArrayList();
	private final Collection<String> visibleWorkingSetLabels = newHashSet();

	private List<WorkingSet> allWorkingSets = null;
	private List<WorkingSet> visibleWorkingSets = null;

	@Inject
	private IN4JSCore core;

	@Inject
	private StatusHelper statusHelper;

	@Override
	public String getId() {
		return ProjectTypeAwareWorkingSetManager.class.getName();
	}

	@Override
	public String getLabel() {
		return "N4JS Working Set";
	}

	@Override
	public WorkingSet[] getWorkingSets() {
		return Iterables.toArray(getOrCreateVisibleWorkingSets(), WorkingSet.class);
	}

	@Override
	public WorkingSet[] getAllWorkingSets() {
		return Iterables.toArray(getOrCreateAllWorkingSets(), WorkingSet.class);
	}

	@Override
	public void select(final Iterable<WorkingSet> workingSets) {
		visibleWorkingSetLabels.addAll(from(workingSets).transform(ws -> ws.getLabel()).toList());
	}

	@Override
	public void unselect(final Iterable<WorkingSet> workingSets) {
		visibleWorkingSetLabels.removeAll(from(workingSets).transform(ws -> ws.getLabel()).toList());
	}

	@Override
	public IStatus saveState(final IProgressMonitor monitor) {

		final Preferences node = getPreferences();

		// Save ordered labels.
		node.put(ORDERED_LABELS_KEY, Joiner.on(SEPARATOR).join(orderedWorkingSetLabels));

		// Save visible labels.
		node.put(VISIBLE_LABELS_KEY, Joiner.on(SEPARATOR).join(visibleWorkingSetLabels));

		try {
			node.flush();
		} catch (final BackingStoreException e) {
			final String message = "Error occurred while saving state to preference store.";
			LOGGER.error(message, e);
			return statusHelper.createError(message, e);
		}

		return statusHelper.OK();
	}

	@Override
	public IStatus restoreState(final IProgressMonitor monitor) {

		final Preferences node = getPreferences();

		final String orderedLabels = node.get(ORDERED_LABELS_KEY, "");
		if (!Strings.isNullOrEmpty(orderedLabels)) {
			orderedWorkingSetLabels.clear();
			orderedWorkingSetLabels.addAll(Arrays.asList(orderedLabels.split(SEPARATOR)));
		}

		final String visibleLabels = node.get(VISIBLE_LABELS_KEY, "");
		if (!Strings.isNullOrEmpty(visibleLabels)) {
			visibleWorkingSetLabels.clear();
			visibleWorkingSetLabels.addAll(Arrays.asList(visibleLabels.split(SEPARATOR)));
		}

		discardWorkingSetState();

		return statusHelper.OK();
	}

	@Override
	public void updateState(final Diff<WorkingSet> diff) {
		// Deselect all.
		visibleWorkingSetLabels.clear();

		// Select visible ones.
		select(Arrays.asList(diff.getNewItems()));

		// Update order.
		orderedWorkingSetLabels.clear();
		for (final WorkingSet workingSet : diff.getNewAllItems()) {
			orderedWorkingSetLabels.add(workingSet.getLabel());
		}

		discardWorkingSetState();
	}

	@Override
	public int compare(final WorkingSet left, final WorkingSet right) {
		if (left == null) {
			return right == null ? 0 : 1;
		}

		final String rightId = right.getLabel();
		final String leftId = left.getLabel();

		if (orderedWorkingSetLabels.isEmpty()) {
			return leftId.compareTo(rightId);
		}

		return orderedWorkingSetLabels.indexOf(leftId) - orderedWorkingSetLabels.indexOf(rightId);
	}

	private void discardWorkingSetState() {
		allWorkingSets = null;
		visibleWorkingSets = null;
	}

	private List<WorkingSet> getOrCreateAllWorkingSets() {

		if (allWorkingSets != null) {
			return allWorkingSets;
		}

		final List<WorkingSet> workingSets = newArrayList(from(Arrays.asList(ProjectType.values()))
				.transform(type -> new ProjectTypeWorkingSet(type, core, ProjectTypeAwareWorkingSetManager.this))
				.filter(WorkingSet.class));

		Collections.sort(workingSets, this);

		allWorkingSets = workingSets;

		return allWorkingSets;
	}

	private List<WorkingSet> getOrCreateVisibleWorkingSets() {

		if (visibleWorkingSets != null) {
			return visibleWorkingSets;
		}

		if (allWorkingSets == null) {
			allWorkingSets = getOrCreateAllWorkingSets();
		}

		if (visibleWorkingSetLabels.isEmpty()) {
			visibleWorkingSets = newArrayList(allWorkingSets);
		} else {
			visibleWorkingSets = from(allWorkingSets).filter(ws -> visibleWorkingSetLabels.contains(ws.getLabel()))
					.toList();
		}

		return visibleWorkingSets;
	}

	private static final class ProjectTypeWorkingSet implements WorkingSet {

		private final ProjectType type;
		private final IN4JSCore core;
		private final WorkingSetManager manager;

		private ProjectTypeWorkingSet(/* nullable */ final ProjectType type, final IN4JSCore core,
				final WorkingSetManager manager) {

			this.type = type;
			this.core = core;
			this.manager = manager;
		}

		@Override
		public String getLabel() {

			if (null == type) {
				return "Others";
			}

			return API.equals(type)
					? API.getLiteral()
					: toFirstUpper(nullToEmpty(type.getLiteral()).replaceAll("_", " ").toLowerCase());
		}

		@Override
		public IAdaptable[] getElements() {
			final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			final IProject[] elements = new IProject[projects.length];
			int elementCount = 0;
			for (int i = 0, size = projects.length; i < size; i++) {
				final IProject project = projects[i];
				if (project.isAccessible() && hasNature(project) && hasBuilder(project)) {
					final IN4JSProject n4Project = core.findProject(toUri(project)).orNull();
					if (n4Project != null && type.equals(n4Project.getProjectType())) {
						elements[elementCount++] = project;
					}
				}
			}
			return Arrays.copyOfRange(elements, 0, elementCount);
		}

		@Override
		public WorkingSetManager getWorkingSetManager() {
			return manager;
		}

		private URI toUri(final IProject project) {
			return URI.createPlatformResourceURI(project.getName(), true);
		}

		@Override
		public String toString() {
			return null == type ? "Others" : getLabel();
		}

	}

}
