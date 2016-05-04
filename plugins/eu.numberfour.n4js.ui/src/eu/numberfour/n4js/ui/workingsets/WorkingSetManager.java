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

import java.util.Comparator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.util.Strings;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import eu.numberfour.n4js.ui.internal.N4JSActivator;

/**
 *
 */
public interface WorkingSetManager extends Comparator<WorkingSet> {

	/**
	 * The unique ID of the {@code workingSetManager} extension point.
	 */
	String EXTENSION_POINT_ID = N4JSActivator.getInstance().getBundle().getSymbolicName()
			+ "." + Strings.toFirstLower(WorkingSetManager.class.getSimpleName());

	/**
	 * An empty array of working sets.
	 */
	WorkingSet[] EMPTY_ARRAY = new WorkingSet[0];

	/**
	 * Returns with the unique identifier of the working set manager.
	 *
	 * @return the unique ID.
	 */
	String getId();

	/**
	 * Returns with the human readable name of the working set manager.
	 *
	 * @return the human readable name of the manager.
	 */
	String getLabel();

	/**
	 * Returns with the image of the working set manager. By default returns with an {@link Optional#absent() absent}
	 * image, hence no image will be visible for the working set manager on the UI. Clients may override this method to
	 * contribute custom implementations.
	 *
	 * @return the image for the working set manager.
	 */
	default Optional<Image> getImage() {
		return Optional.absent();
	}

	/**
	 * Returns with an iterable of visible working sets that are manager by the current instance. Invisible working sets
	 * are excluded from the returning result.
	 *
	 * @return an iterable of visible working sets.
	 */
	WorkingSet[] getWorkingSets();

	/**
	 * Returns with an iterable of all working sets that are manager by the current instance. Invisible working sets are
	 * included in the returning result.
	 *
	 * @return an iterable of all working sets. Including visible and invisible ones as well.
	 */
	WorkingSet[] getAllWorkingSets();

	/**
	 * Saves the state of the working set manager.
	 *
	 * @param monitor
	 *            the monitor for the save operation.
	 * @return status representing the outcome of the save operation.
	 */
	IStatus save(IProgressMonitor monitor);

	/**
	 * Marks the argument working sets as a selected one. Selected working sets will be visible for the CNF (Common
	 * Navigator Framework). This method has no side effect if an already selected working set is marked to be selected
	 * again. It will keep its selected state as it was before calling that method. Also this method ignores all those
	 * working sets that are not managed by the current manager instance.
	 *
	 * @param workingSets
	 *            the working sets that has to be marked as selected ones.
	 */
	void select(Iterable<WorkingSet> workingSets);

	/**
	 * Sugar for {@link #select(Iterable)}. Marks the selected working sets to be visible.
	 *
	 * @param first
	 *            the first working set to be marked as selected.
	 * @param others
	 *            other optional working sets to be marked as visible.
	 */
	default void select(WorkingSet first, WorkingSet... others) {
		select(Lists.asList(first, others));
	}

	/**
	 * Marks the argument working sets as a unselected one. Unselected working sets will be invisible for the CNF
	 * (Common Navigator Framework). This method has no side effect if an already invisible working set is marked to be
	 * unselected again. It will keep its selected state as it was before calling that method. Also this method ignores
	 * all those working sets that are not managed by the current manager instance.
	 *
	 * @param workingSets
	 *            the working sets that has to be marked as unselected ones.
	 */
	void unselect(Iterable<WorkingSet> workingSets);

	/**
	 * Sugar for {@link #unselect(Iterable)}. Marks the selected working sets to be invisible.
	 *
	 * @param first
	 *            the first working set to be marked as unselected.
	 * @param others
	 *            other optional working sets to be marked as invisible.
	 */
	default void unselect(WorkingSet first, WorkingSet... others) {
		unselect(Lists.asList(first, others));
	}

	/**
	 * Configures the state of the working set manager.
	 */
	default void configure() {
		new WorkingSetConfigurationDialog(this).open();
	}

	/**
	 * By default returns with {@code 0}. Clients may override it.
	 *
	 * <p>
	 * {@inheritDoc}
	 */
	@Override
	default int compare(WorkingSet left, WorkingSet right) {
		return 0;
	}

	/**
	 * The NOOP instance. Does nothing at all.
	 */
	WorkingSetManager NOOP = new WorkingSetManager() {

		@Override
		public String getId() {
			return WorkingSetManager.class.getName() + ".NOOP";
		}

		@Override
		public String getLabel() {
			return "";
		}

		@Override
		public WorkingSet[] getWorkingSets() {
			return EMPTY_ARRAY;
		}

		@Override
		public WorkingSet[] getAllWorkingSets() {
			return EMPTY_ARRAY;
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
		public void configure() {
			// NOOP
		}

		@Override
		public void unselect(WorkingSet first, WorkingSet... others) {
			// NOOP
		}

	};

}
