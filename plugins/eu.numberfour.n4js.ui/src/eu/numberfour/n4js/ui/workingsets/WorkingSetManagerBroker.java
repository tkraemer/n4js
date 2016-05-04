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
import static com.google.common.base.Suppliers.memoize;
import static com.google.common.collect.Sets.newHashSet;
import static eu.numberfour.n4js.ui.workingsets.WorkingSetManager.EXTENSION_POINT_ID;
import static java.util.Collections.emptyMap;
import static org.eclipse.core.runtime.Platform.getExtensionRegistry;
import static org.eclipse.core.runtime.Platform.isRunning;
import static org.eclipse.core.runtime.Status.OK_STATUS;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import eu.numberfour.n4js.ui.utils.UIUtils;
import eu.numberfour.n4js.utils.Arrays2;
import eu.numberfour.n4js.utils.StatusHelper;

/**
 *
 */
@Singleton
public class WorkingSetManagerBroker {

	private static final Logger LOGGER = Logger.getLogger(WorkingSetManagerBroker.class);

	private static final String CLASS_ATTRIBUTE = "class";
	private static final String QUALIFIER = WorkingSetManagerBroker.class.getName();
	private static final String ACTIVE_MANAGER_KEY = QUALIFIER + ".activeManager";
	private static final String IS_WORKINGSET_TOP_LEVEL_KEY = QUALIFIER + ".isWorkingSetTopLevel";

	private final Injector injector;
	private final StatusHelper statusHelper;
	private final AtomicReference<WorkingSetManager> activeWorkingSetManager;
	private final AtomicBoolean workingSetTopLevel;
	private final Supplier<Map<String, WorkingSetManager>> contributions;
	private final Collection<TopLevelElementChangedListener> topLevelElementChangeListeners;

	/**
	 * Creates a new working set broker instance with the given injector and status helper arguments. The injector is
	 * used to inject members into the available contributions. Also restores its most recent state from the preference
	 * store.
	 *
	 * @param injector
	 *            the injector for initializing the contributions.
	 * @param statusHelper
	 *            convenient way to create {@link IStatus status} instances.
	 *
	 */
	@Inject
	public WorkingSetManagerBroker(final Injector injector, final StatusHelper statusHelper) {
		this.injector = injector;
		this.statusHelper = statusHelper;
		this.activeWorkingSetManager = new AtomicReference<>();
		this.workingSetTopLevel = new AtomicBoolean(false);
		this.contributions = initContributions();
		topLevelElementChangeListeners = newHashSet();
		restoreState();
	}

	/**
	 * Returns with all the available working set managers.
	 *
	 * @return a view of all available {@link WorkingSetManager working set manager} instances.
	 */
	public Collection<WorkingSetManager> getWorkingSetManagers() {
		return Collections.unmodifiableCollection(contributions.get().values());
	}

	/**
	 * Sets the active working set manager.
	 *
	 * @param workingSetManager
	 *            the working set manager that has to be selected as the active one.
	 */
	public void setActive(WorkingSetManager workingSetManager) {
		checkNotNull(workingSetManager, "workingSetManager");
		activeWorkingSetManager.set(workingSetManager);
		saveState();
		if (workingSetManager.equals(activeWorkingSetManager.get())) {
			refreshNavigator();
		}
	}

	/**
	 * Returns with {@code true} if the working set manager argument is the currently active one. Otherwise returns with
	 * {@code false}.
	 *
	 * @param workingSetManager
	 *            the working set manager to test whether it is the currently active one or not.
	 * @return {@code true} if the argument is the currently active one, otherwise {@code false}
	 */
	public boolean isActive(WorkingSetManager workingSetManager) {
		return Objects.equal(workingSetManager, activeWorkingSetManager.get());
	}

	/**
	 * Returns with the active working set manager. Could return with {@code null} if no active working set manager is
	 * available.
	 *
	 * @return the active working set manager, or {@code null}, if not yet available.
	 */
	public WorkingSetManager getActive() {
		return activeWorkingSetManager.get();
	}

	/**
	 * Returns with {@code true} if working sets are configured to be shown as top level elements in the common
	 * navigator.
	 *
	 * @return {@code true} if working sets are the top level elements, otherwise returns with {@code false}.
	 */
	public boolean isWorkingSetTopLevel() {
		return workingSetTopLevel.get();
	}

	/**
	 * Sets the boolean flag whether working sets or projects have to be show as top level elements in the navigator. If
	 * {@code true}, then working sets are configured to be the top level elements, if {@code false}, then projects.
	 *
	 * @param b
	 *            the boolean flag whether working sets has to be top level elements or not.
	 */
	public void setWorkingSetTopLevel(boolean b) {
		if (b != workingSetTopLevel.get()) {
			workingSetTopLevel.set(b);
			saveState();
			for (final TopLevelElementChangedListener listener : topLevelElementChangeListeners) {
				listener.topLevelElementChanged(workingSetTopLevel.get());
			}
			refreshNavigator();
		}
	}

	/**
	 * Saves the state of the current working set broker.
	 *
	 * @param monitor
	 *            the monitor for the save operation.
	 * @return status representing the outcome of the save operation.
	 */
	public IStatus save(IProgressMonitor monitor) {
		final Collection<WorkingSetManager> managers = getWorkingSetManagers();
		final SubMonitor subMonitor = SubMonitor.convert(monitor, managers.size() + 1);
		final MultiStatus error = statusHelper.createMultiError("Error occurred while saving state.");
		for (WorkingSetManager manager : managers) {
			final IStatus result = manager.save(subMonitor.newChild(1));
			if (!result.isOK()) {
				error.add(result);
			}
		}
		final IStatus result = saveState();
		if (!result.isOK()) {
			error.add(result);
		}
		return Arrays2.isEmpty(error.getChildren()) ? statusHelper.OK() : error;
	}

	/**
	 * Adds a top level element configuration changed listener to the broker. Listeners will be notified when the
	 * configuration has been changed via {@link #setWorkingSetTopLevel(boolean)} method. Has no effect if the identical
	 * listener has been already added.
	 *
	 * @param listener
	 *            the listener to add. Should not be {@code null}.
	 */
	public void addTopLevelElementChangedListener(TopLevelElementChangedListener listener) {
		topLevelElementChangeListeners.add(checkNotNull(listener, "listener"));
	}

	/**
	 * Removed a top level element configuration changed listener from the broker. Has no effect if the listener
	 * argument was not added to this broker previously.
	 *
	 * @param listener
	 *            the listener to remove. Should not be {@code null}.
	 */
	public void removeTopLevelElementChangedListener(TopLevelElementChangedListener listener) {
		topLevelElementChangeListeners.remove(checkNotNull(listener, "listener"));
	}

	private void refreshNavigator() {
		if (PlatformUI.isWorkbenchRunning()) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			if (window != null) {
				IWorkbenchPage page = window.getActivePage();
				if (page != null) {
					IViewPart view = page.findView(ProjectExplorer.VIEW_ID);
					if (view instanceof ProjectExplorer) {
						CommonViewer viewer = ((ProjectExplorer) view).getCommonViewer();
						UIUtils.getDisplay().asyncExec(() -> viewer.refresh(true));
					}
				}
			}
		}
	}

	private IStatus saveState() {
		final IEclipsePreferences node = InstanceScope.INSTANCE.getNode(QUALIFIER);
		final WorkingSetManager active = getActive();
		final String activeId = active == null ? null : active.getId();
		node.put(ACTIVE_MANAGER_KEY, Strings.nullToEmpty(activeId));
		try {
			node.flush();
			return OK_STATUS;
		} catch (final BackingStoreException e) {
			final String message = "Unexpected error when trying to persist working set broker state.";
			LOGGER.error(message, e);
			return statusHelper.createError(message, e);
		}
	}

	private void restoreState() {
		final Preferences node = InstanceScope.INSTANCE.getNode(QUALIFIER);

		// Top level element.
		workingSetTopLevel.set(node.getBoolean(IS_WORKINGSET_TOP_LEVEL_KEY, false));

		// Active selection.
		final String value = node.get(ACTIVE_MANAGER_KEY, "");
		final WorkingSetManager workingSetManager = contributions.get().get(value);
		setActive(workingSetManager);
	}

	private Supplier<Map<String, WorkingSetManager>> initContributions() {

		return memoize(() -> {

			if (!isRunning()) {
				return emptyMap();
			}

			Builder<String, WorkingSetManager> builder = ImmutableMap.builder();
			IConfigurationElement[] elements = getExtensionRegistry().getConfigurationElementsFor(EXTENSION_POINT_ID);
			for (IConfigurationElement element : elements) {
				try {
					WorkingSetManager manager = (WorkingSetManager) element.createExecutableExtension(CLASS_ATTRIBUTE);
					injector.injectMembers(manager);
					builder.put(manager.getId(), manager);
				} catch (CoreException e) {
					LOGGER.error("Error while trying to instantiate working set manager.", e);
				}
			}

			return builder.build();
		});
	}

}
