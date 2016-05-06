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
import static eu.numberfour.n4js.ui.utils.UIUtils.getDisplay;
import static eu.numberfour.n4js.ui.workingsets.WorkingSetManager.EXTENSION_POINT_ID;
import static java.util.Collections.emptyMap;
import static org.eclipse.core.runtime.Platform.getExtensionRegistry;
import static org.eclipse.core.runtime.Platform.isRunning;
import static org.eclipse.core.runtime.Status.OK_STATUS;
import static org.eclipse.ui.PlatformUI.getWorkbench;
import static org.eclipse.ui.PlatformUI.isWorkbenchRunning;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.ISaveContext;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
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

import eu.numberfour.n4js.ui.internal.N4JSActivator;
import eu.numberfour.n4js.utils.Arrays2;
import eu.numberfour.n4js.utils.StatusHelper;

/**
 * Manages the available {@link WorkingSetManager working set manager} instances.
 */
@Singleton
public class WorkingSetManagerBroker implements IMementoAware {

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
		restoreState(new NullProgressMonitor());
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			final String pluginId = N4JSActivator.getInstance().getBundle().getSymbolicName();
			final IWorkspace workspace = ResourcesPlugin.getWorkspace();
			try {
				workspace.addSaveParticipant(pluginId, new SaveParticipantAdapter() {
					@Override
					public void saving(final ISaveContext context) throws CoreException {
						saveState(new NullProgressMonitor());
					}
				});
			} catch (final CoreException e) {
				LOGGER.error("Error occurred while attaching save participant to workspace.", e);
			}
		}
	}

	@Override
	public IStatus saveState(final IProgressMonitor monitor) {
		final Collection<WorkingSetManager> managers = getWorkingSetManagers();
		final SubMonitor subMonitor = SubMonitor.convert(monitor, managers.size() + 1);
		final MultiStatus error = statusHelper.createMultiError("Error occurred while saving state.");
		for (final WorkingSetManager manager : managers) {
			final IStatus result = manager.saveState(subMonitor.newChild(1));
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

	@Override
	public IStatus restoreState(final IProgressMonitor monitor) {
		final Collection<WorkingSetManager> managers = getWorkingSetManagers();
		final SubMonitor subMonitor = SubMonitor.convert(monitor, managers.size() + 1);
		final MultiStatus error = statusHelper.createMultiError("Error occurred while restoring state.");
		for (final WorkingSetManager manager : managers) {
			final IStatus result = manager.restoreState(subMonitor.newChild(1));
			if (!result.isOK()) {
				error.add(result);
			}
		}
		final IStatus result = restoreState();
		if (!result.isOK()) {
			error.add(result);
		}
		return Arrays2.isEmpty(error.getChildren()) ? statusHelper.OK() : error;
	}

	@Override
	public Preferences getPreferences() {
		return InstanceScope.INSTANCE.getNode(QUALIFIER);
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
	public void setActiveManager(final WorkingSetManager workingSetManager) {
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
	public boolean isActiveManger(final WorkingSetManager workingSetManager) {
		return Objects.equal(workingSetManager, activeWorkingSetManager.get());
	}

	/**
	 * Returns with the active working set manager. Could return with {@code null} if no active working set manager is
	 * available.
	 *
	 * @return the active working set manager, or {@code null}, if not yet available.
	 */
	public WorkingSetManager getActiveManger() {
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
	 *            the boolean flag whether working sets has to be top level elements or not. {@code true} if the working
	 *            sets should be the top level elements in the navigator.
	 */
	public void setWorkingSetTopLevel(final boolean b) {
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
	 * Adds a top level element configuration changed listener to the broker. Listeners will be notified when the
	 * configuration has been changed via {@link #setWorkingSetTopLevel(boolean)} method. Has no effect if the identical
	 * listener has been already added.
	 *
	 * @param listener
	 *            the listener to add. Should not be {@code null}.
	 */
	public void addTopLevelElementChangedListener(final TopLevelElementChangedListener listener) {
		topLevelElementChangeListeners.add(checkNotNull(listener, "listener"));
	}

	/**
	 * Removed a top level element configuration changed listener from the broker. Has no effect if the listener
	 * argument was not added to this broker previously.
	 *
	 * @param listener
	 *            the listener to remove. Should not be {@code null}.
	 */
	public void removeTopLevelElementChangedListener(final TopLevelElementChangedListener listener) {
		topLevelElementChangeListeners.remove(checkNotNull(listener, "listener"));
	}

	/**
	 * Asynchronously refreshes the navigator content.
	 */
	public void refreshNavigator() {
		if (isWorkbenchRunning()) {
			final IWorkbench workbench = getWorkbench();
			final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
			if (window != null) {
				final IWorkbenchPage page = window.getActivePage();
				if (page != null) {
					final IViewPart view = page.findView(ProjectExplorer.VIEW_ID);
					if (view instanceof ProjectExplorer) {
						final CommonViewer viewer = ((ProjectExplorer) view).getCommonViewer();
						getDisplay().asyncExec(() -> viewer.refresh(true));
					}
				}
			}
		}
	}

	private IStatus saveState() {

		final Preferences node = getPreferences();
		// Top level element.
		node.putBoolean(IS_WORKINGSET_TOP_LEVEL_KEY, workingSetTopLevel.get());

		// Active working set manager.
		final WorkingSetManager active = getActiveManger();
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

	private IStatus restoreState() {

		final Preferences node = getPreferences();
		// Top level element.
		workingSetTopLevel.set(node.getBoolean(IS_WORKINGSET_TOP_LEVEL_KEY, false));

		// Active working set manager.
		final String value = node.get(ACTIVE_MANAGER_KEY, "");
		final WorkingSetManager workingSetManager = contributions.get().get(value);
		setActiveManager(workingSetManager);

		return Status.OK_STATUS;
	}

	private Supplier<Map<String, WorkingSetManager>> initContributions() {

		return memoize(() -> {

			if (!isRunning()) {
				return emptyMap();
			}

			final Builder<String, WorkingSetManager> builder = ImmutableMap.builder();
			final IConfigurationElement[] elements = getExtensionRegistry()
					.getConfigurationElementsFor(EXTENSION_POINT_ID);
			for (final IConfigurationElement element : elements) {
				try {
					final WorkingSetManager manager = (WorkingSetManager) element
							.createExecutableExtension(CLASS_ATTRIBUTE);
					injector.injectMembers(manager);
					builder.put(manager.getId(), manager);
				} catch (final CoreException e) {
					LOGGER.error("Error while trying to instantiate working set manager.", e);
				}
			}

			return builder.build();
		});
	}

}
