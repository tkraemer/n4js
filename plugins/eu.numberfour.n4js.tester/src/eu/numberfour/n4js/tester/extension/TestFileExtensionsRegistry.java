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
package eu.numberfour.n4js.tester.extension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;

import com.google.inject.Singleton;

/**
 * This class collect test file extensions from extensions to extension point.
 */
@Singleton
public class TestFileExtensionsRegistry {
	private final static Logger log = Logger.getLogger(TesterRegistry.class);

	/* The extension point to test file extensions */
	private static final String TEST_FILE_EXTENSIONS_POINT_ID = "eu.numberfour.n4js.tester.testFileExtensions";
	private static final String ATT_FILE_EXTENSION = "fileExtension";
	private boolean isInitialized = false;
	private final Collection<String> testFileExtensions = new ArrayList<>();

	/**
	 * Register a test file extension. This method should only be invoked by client code directly in headless mode. When
	 * running in Eclipse, test file extensions will be registered via the 'testFileExtension' extension point.
	 */
	public void register(String testFileExtension) {
		testFileExtensions.add(testFileExtension);
	}

	/**
	 * Return test file extensions
	 */
	public Collection<String> getTestFileExtensions() {
		if (!isInitialized) {
			initialize();
		}
		return Collections.unmodifiableCollection(testFileExtensions);
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
					.getConfigurationElementsFor(TEST_FILE_EXTENSIONS_POINT_ID);

			for (IConfigurationElement elem : configElems) {
				try {
					testFileExtensions.add(elem.getAttribute(ATT_FILE_EXTENSION));
				} catch (Exception ex) {
					log.error("Error while reading extensions for extension point " + TEST_FILE_EXTENSIONS_POINT_ID,
							ex);
				}
			}
		}
	}

}
