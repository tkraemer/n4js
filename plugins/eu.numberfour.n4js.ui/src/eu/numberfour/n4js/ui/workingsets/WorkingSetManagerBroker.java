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

import java.util.Collection;

/**
 * Representation of a broker for registered {@link WorkingSetManager working set manager} instances.
 */
public interface WorkingSetManagerBroker extends IMementoAware {

	/**
	 * Returns with all the available working set managers.
	 *
	 * @return a view of all available {@link WorkingSetManager working set manager} instances.
	 */
	Collection<WorkingSetManager> getWorkingSetManagers();

	/**
	 * Sets the active working set manager.
	 *
	 * @param workingSetManager
	 *            the working set manager that has to be selected as the active one.
	 */
	void setActiveManager(final WorkingSetManager workingSetManager);

	/**
	 * Returns with {@code true} if the working set manager argument is the currently active one. Otherwise returns with
	 * {@code false}.
	 *
	 * @param workingSetManager
	 *            the working set manager to test whether it is the currently active one or not.
	 * @return {@code true} if the argument is the currently active one, otherwise {@code false}
	 */
	boolean isActiveManger(final WorkingSetManager workingSetManager);

	/**
	 * Returns with the active working set manager. Could return with {@code null} if no active working set manager is
	 * available.
	 *
	 * @return the active working set manager, or {@code null}, if not yet available.
	 */
	WorkingSetManager getActiveManger();

	/**
	 * Returns with {@code true} if working sets are configured to be shown as top level elements in the common
	 * navigator.
	 *
	 * @return {@code true} if working sets are the top level elements, otherwise returns with {@code false}.
	 */
	boolean isWorkingSetTopLevel();

	/**
	 * Sets the boolean flag whether working sets or projects have to be show as top level elements in the navigator. If
	 * {@code true}, then working sets are configured to be the top level elements, if {@code false}, then projects.
	 *
	 * @param b
	 *            the boolean flag whether working sets has to be top level elements or not. {@code true} if the working
	 *            sets should be the top level elements in the navigator.
	 */
	void setWorkingSetTopLevel(boolean b);

	/**
	 * Adds a top level element configuration changed listener to the broker. Listeners will be notified when the
	 * configuration has been changed via {@link #setWorkingSetTopLevel(boolean)} method. Has no effect if the identical
	 * listener has been already added.
	 *
	 * @param listener
	 *            the listener to add. Should not be {@code null}.
	 */
	void addTopLevelElementChangedListener(TopLevelElementChangedListener listener);

	/**
	 * Removed a top level element configuration changed listener from the broker. Has no effect if the listener
	 * argument was not added to this broker previously.
	 *
	 * @param listener
	 *            the listener to remove. Should not be {@code null}.
	 */
	void removeTopLevelElementChangedListener(TopLevelElementChangedListener listener);

	/**
	 * Asynchronously refreshes the navigator content.
	 */
	void refreshNavigator();

}