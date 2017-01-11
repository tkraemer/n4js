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
package eu.numberfour.n4js.runner.extension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;

import com.google.common.base.Splitter;
import com.google.inject.Singleton;

/**
 * This class collect test file extensions from extensions to extension point.
 */
@Singleton
public class RunnableExtensionsRegistry {
	private final static Logger log = Logger.getLogger(RunnableExtensionsRegistry.class);

	/* The extension point to runnable file extensions */
	private static final String RUNNABLE_FILE_EXTENSIONS_POINT_ID = "eu.numberfour.n4js.runner.runnableFileExtensions";
	private static final String ATT_FILE_EXTENSION = "extensions";
	private boolean isInitialized = false;
	private final Collection<String> runnableFileExtensions = new ArrayList<>();

	/**
	 * Register a runnable file extension. This method should only be invoked by client code directly in headless mode.
	 * When running in Eclipse, test file extensions will be registered via the 'runnableFileExtensions' extension
	 * point.
	 */
	public void register(String runnableFileExtension) {
		runnableFileExtensions.add(runnableFileExtension);
	}

	/**
	 * Return test file extensions
	 */
	public Collection<String> getRunnableFileExtensions() {
		if (!isInitialized) {
			initialize();
		}
		return Collections.unmodifiableCollection(runnableFileExtensions);
	}

	/**
	 * Read information from extensions defined in plugin.xml files
	 */
	private void initialize() {
		if (isInitialized) {
			throw new IllegalStateException("may invoke method initialize() only once");
		}
		isInitialized = true;

		final IExtensionRegistry registry = RegistryFactory.getRegistry();
		if (registry != null) {
			final IConfigurationElement[] configElems = registry
					.getConfigurationElementsFor(RUNNABLE_FILE_EXTENSIONS_POINT_ID);

			for (IConfigurationElement elem : configElems) {
				try {
					runnableFileExtensions.addAll(Splitter.on(',').trimResults().omitEmptyStrings()
							.splitToList(elem.getAttribute(ATT_FILE_EXTENSION)));
				} catch (Exception ex) {
					log.error("Error while reading extensions for extension point " + RUNNABLE_FILE_EXTENSIONS_POINT_ID,
							ex);
				}
			}
		}
	}

	/**
	 * Reset the list of test file extensions to empty
	 */
	public void reset() {
		isInitialized = false;
		runnableFileExtensions.clear();
	}
}
