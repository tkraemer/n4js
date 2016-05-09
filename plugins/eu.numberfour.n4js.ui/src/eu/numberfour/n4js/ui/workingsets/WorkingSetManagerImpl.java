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
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static eu.numberfour.n4js.ui.workingsets.WorkingSet.OTHERS_WORKING_SET_LABEL;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

import eu.numberfour.n4js.utils.Diff;
import eu.numberfour.n4js.utils.StatusHelper;

/**
 * Base working set manager which does not allow working set addition, removal and/or edition. The order of the working
 * sets can be modified. This implementation is not thread safe. It is the clients responsibility to synchronize on it
 * when thread safety is required.
 */
public abstract class WorkingSetManagerImpl implements WorkingSetManager {

	private static final Logger LOGGER = Logger.getLogger(WorkingSetManagerImpl.class);

	/**
	 * Separator used when persisting key value pairs into the OSGi preference store.
	 */
	protected static final String SEPARATOR = "#";

	/**
	 * An empty string for representing not yet persisted configuration.
	 */
	protected static final String EMPTY_STRING = "";

	private static final String ORDERED_NAMES_KEY = ".orderedNames";
	private static final String VISIBLE_NAMES_KEY = ".visibleNames";

	/**
	 * List of all working sets. Used internally for caching purposes.
	 */
	private List<WorkingSet> allWorkingSets = null;

	/**
	 * List of visible working sets. Used internally for caching purposes.
	 */
	private List<WorkingSet> visibleWorkingSets = null;

	/**
	 * Ordered list of the working set names.
	 */
	protected final List<String> orderedWorkingSetNames = newArrayList();

	/**
	 * A collection of working set names that are configured to be visible on the UI.
	 */
	protected final Collection<String> visibleWorkingSetNames = newHashSet();

	/**
	 * Status helper for creating {@link IStatus status} instances in a convenient way.
	 */
	@Inject
	protected StatusHelper statusHelper;

	@Override
	public String getId() {
		return getClass().getName();
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
		visibleWorkingSetNames.addAll(from(workingSets).transform(ws -> ws.getName()).toList());
	}

	@Override
	public void unselect(final Iterable<WorkingSet> workingSets) {
		visibleWorkingSetNames.removeAll(from(workingSets).transform(ws -> ws.getName()).toList());
	}

	@Override
	public IStatus saveState(final IProgressMonitor monitor) {

		final Preferences node = getPreferences();

		// Save ordered labels.
		node.put(ORDERED_NAMES_KEY, Joiner.on(SEPARATOR).join(orderedWorkingSetNames));

		// Save visible labels.
		node.put(VISIBLE_NAMES_KEY, Joiner.on(SEPARATOR).join(visibleWorkingSetNames));

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

		// Restore ordered labels.
		final String orderedLabels = node.get(ORDERED_NAMES_KEY, EMPTY_STRING);
		if (!Strings.isNullOrEmpty(orderedLabels)) {
			orderedWorkingSetNames.clear();
			orderedWorkingSetNames.addAll(Arrays.asList(orderedLabels.split(SEPARATOR)));
		}

		// Restore visible labels.
		final String visibleLabels = node.get(VISIBLE_NAMES_KEY, EMPTY_STRING);
		if (!Strings.isNullOrEmpty(visibleLabels)) {
			visibleWorkingSetNames.clear();
			visibleWorkingSetNames.addAll(Arrays.asList(visibleLabels.split(SEPARATOR)));
		}

		discardWorkingSetState();

		return statusHelper.OK();
	}

	@Override
	public void updateState(final Diff<WorkingSet> diff) {

		if (!diff.isEmpty()) {
			// Deselect all.
			visibleWorkingSetNames.clear();

			// Select visible ones.
			select(Arrays.asList(diff.getNewItems()));

			// Update order.
			orderedWorkingSetNames.clear();
			for (final WorkingSet workingSet : diff.getNewAllItems()) {
				orderedWorkingSetNames.add(workingSet.getName());
			}

			discardWorkingSetState();
		}

	}

	/**
	 * Orders by case sensitive name order, if no explicit ordering is specified yet. The
	 * {@link WorkingSet#OTHERS_WORKING_SET_LABEL Others} reserved working set name is considered to be the first one.
	 */
	@Override
	public int compare(final WorkingSet left, final WorkingSet right) {
		if (left == null) {
			return right == null ? 0 : 1;
		}

		final String rightName = right.getName();
		final String leftName = left.getName();

		checkNotNull(leftName, "The name of the working set must not be null. Working set: " + left);
		checkNotNull(rightName, "The name of the working set must not be null. Working set: " + right);

		if (orderedWorkingSetNames.isEmpty()) {

			if (OTHERS_WORKING_SET_LABEL.equals(leftName)) {
				return OTHERS_WORKING_SET_LABEL.equals(rightName) ? 0 : -1;
			}

			if (OTHERS_WORKING_SET_LABEL.equals(rightName)) {
				return OTHERS_WORKING_SET_LABEL.equals(leftName) ? 0 : 1;
			}

			return leftName.compareTo(rightName);
		}

		return orderedWorkingSetNames.indexOf(leftName) - orderedWorkingSetNames.indexOf(rightName);
	}

	/**
	 * Initializes and returns with all available working sets. The returning list includes the hidden ones as well.
	 *
	 * @return a list of all available working sets.
	 */
	protected abstract List<WorkingSet> initializeWorkingSets();

	/**
	 * Discards the state of cached working sets.
	 */
	protected void discardWorkingSetState() {
		allWorkingSets = null;
		visibleWorkingSets = null;
	}

	/**
	 * Returns with all working sets. On demand, creates them by calling {@link #initializeWorkingSets()} internally.
	 *
	 * @return a list of all working sets.
	 */
	protected List<WorkingSet> getOrCreateAllWorkingSets() {

		if (allWorkingSets != null) {
			return allWorkingSets;
		}

		final List<WorkingSet> workingSets = initializeWorkingSets();

		Collections.sort(workingSets, this);

		allWorkingSets = workingSets;

		return allWorkingSets;
	}

	/**
	 * Returns with a list of all visible working sets. On demand creates them, otherwise returns with the cached
	 * instances.
	 *
	 * @return a list of visible working set instances.
	 */
	protected List<WorkingSet> getOrCreateVisibleWorkingSets() {

		if (visibleWorkingSets != null) {
			return visibleWorkingSets;
		}

		if (allWorkingSets == null) {
			allWorkingSets = getOrCreateAllWorkingSets();
		}

		if (visibleWorkingSetNames.isEmpty()) {
			visibleWorkingSets = newArrayList(allWorkingSets);
		} else {
			visibleWorkingSets = from(allWorkingSets).filter(ws -> visibleWorkingSetNames.contains(ws.getName()))
					.toList();
		}

		return visibleWorkingSets;
	}

}
