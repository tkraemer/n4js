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

import static com.google.common.base.Suppliers.memoize;
import static eu.numberfour.n4js.ui.workingsets.WorkingSetManager.EXTENSION_POINT_ID;
import static java.util.Collections.emptyMap;
import static org.eclipse.core.runtime.Platform.getExtensionRegistry;
import static org.eclipse.core.runtime.Platform.isRunning;
import static org.eclipse.core.runtime.Status.OK_STATUS;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
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

import eu.numberfour.n4js.utils.Arrays2;
import eu.numberfour.n4js.utils.StatusHelper;

/**
 *
 */
@Singleton
public class WorkingSetBroker {

	private static final Logger LOGGER = Logger.getLogger(WorkingSetBroker.class);

	private static final String CLASS_ATTRIBUTE = "class";
	private static final String QUALIFIER = WorkingSetBroker.class.getName();
	private static final String CONFIGURATION_KEY = QUALIFIER + ".configuration";

	private final Injector injector;
	private final StatusHelper statusHelper;
	private final AtomicReference<WorkingSetManager> activeWorkingSetManager;
	private final Supplier<Map<String, WorkingSetManager>> contributions;

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
	public WorkingSetBroker(final Injector injector, final StatusHelper statusHelper) {
		this.injector = injector;
		this.statusHelper = statusHelper;
		this.activeWorkingSetManager = new AtomicReference<>();
		this.contributions = initContributions();
		final Preferences node = InstanceScope.INSTANCE.getNode(QUALIFIER);
		final String value = node.get(CONFIGURATION_KEY, "");
		final WorkingSetManager workingSetManager = contributions.get().get(value);
		setActive(workingSetManager);
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
		activeWorkingSetManager.set(workingSetManager);
		doSave();
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
		final IStatus result = doSave();
		if (!result.isOK()) {
			error.add(result);
		}
		return Arrays2.isEmpty(error.getChildren()) ? statusHelper.OK() : error;
	}

	private IStatus doSave() {
		final IEclipsePreferences node = InstanceScope.INSTANCE.getNode(QUALIFIER);
		final WorkingSetManager active = getActive();
		final String activeId = active == null ? null : active.getId();
		node.put(CONFIGURATION_KEY, Strings.nullToEmpty(activeId));
		try {
			node.flush();
			return OK_STATUS;
		} catch (final BackingStoreException e) {
			final String message = "Unexpected error when trying to persist working set broker state.";
			LOGGER.error(message, e);
			return statusHelper.createError(message, e);
		}
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
