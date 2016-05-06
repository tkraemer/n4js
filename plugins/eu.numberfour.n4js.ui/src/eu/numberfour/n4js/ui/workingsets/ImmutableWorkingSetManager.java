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
public abstract class ImmutableWorkingSetManager implements WorkingSetManager {

	private static final Logger LOGGER = Logger.getLogger(ImmutableWorkingSetManager.class);

	private static final String SEPARATOR = "#";
	private static final String ORDERED_LABELS_KEY = ".orderedLabels";
	private static final String VISIBLE_LABELS_KEY = ".visibleLabels";

	/**
	 * List of all working sets. Used internally for caching purposes.
	 */
	private List<WorkingSet> allWorkingSets = null;

	/**
	 * List of visible working sets. Used internally for caching purposes.
	 */
	private List<WorkingSet> visibleWorkingSets = null;

	/**
	 * Ordered list of the working set labels.
	 */
	protected final List<String> orderedWorkingSetLabels = newArrayList();

	/**
	 * A collection of working set labels that are configured to be visible on the UI.
	 */
	protected final Collection<String> visibleWorkingSetLabels = newHashSet();

	/**
	 * Status helper for creating {@link IStatus status} instances in a convenient way.
	 */
	@Inject
	protected StatusHelper statusHelper;

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

		// Restore ordered labels.
		final String orderedLabels = node.get(ORDERED_LABELS_KEY, "");
		if (!Strings.isNullOrEmpty(orderedLabels)) {
			orderedWorkingSetLabels.clear();
			orderedWorkingSetLabels.addAll(Arrays.asList(orderedLabels.split(SEPARATOR)));
		}

		// Restore visible labels.
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

		if (!diff.isEmpty()) {
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

	}

	/**
	 * Orders by case sensitive label order, if no explicit ordering is specified yet. The
	 * {@link WorkingSet#OTHERS_WORKING_SET_LABEL Others} reserved working set label is considered to be the first one.
	 */
	@Override
	public int compare(final WorkingSet left, final WorkingSet right) {
		if (left == null) {
			return right == null ? 0 : 1;
		}

		final String rightLabel = right.getLabel();
		final String leftLabel = left.getLabel();

		checkNotNull(leftLabel, "The label of the working set must not be null. Working set: " + left);
		checkNotNull(rightLabel, "The label of the working set must not be null. Working set: " + right);

		if (orderedWorkingSetLabels.isEmpty()) {

			if (OTHERS_WORKING_SET_LABEL.equals(leftLabel)) {
				return OTHERS_WORKING_SET_LABEL.equals(rightLabel) ? 0 : -1;
			}

			if (OTHERS_WORKING_SET_LABEL.equals(rightLabel)) {
				return OTHERS_WORKING_SET_LABEL.equals(leftLabel) ? 0 : 1;
			}

			return leftLabel.compareTo(rightLabel);
		}

		return orderedWorkingSetLabels.indexOf(leftLabel) - orderedWorkingSetLabels.indexOf(rightLabel);
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

		if (visibleWorkingSetLabels.isEmpty()) {
			visibleWorkingSets = newArrayList(allWorkingSets);
		} else {
			visibleWorkingSets = from(allWorkingSets).filter(ws -> visibleWorkingSetLabels.contains(ws.getLabel()))
					.toList();
		}

		return visibleWorkingSets;
	}

}
