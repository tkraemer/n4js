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

import eu.numberfour.n4js.N4JSGlobals;

/**
 * This class collect test file extensions from extensions to extension point.
 */
public class TestFileExtensionsRegistry {
	private final static Logger log = Logger.getLogger(TesterRegistry.class);

	/* The extension point to test file extensions */
	private static final String TEST_FILE_EXTENSIONS_POINT_ID = "eu.numberfour.n4js.tester.testFileExtensions";
	private static final String ATT_FILE_EXTENSION = "fileExtension";
	private boolean isInitialized = false;
	private final Collection<String> testFileExtensions = new ArrayList<>();

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
		} else {
			// Headless compiler does not use extension points. Set default file extension of tests to .n4js
			// TODO: better way to handle this?
			testFileExtensions.add(N4JSGlobals.N4JS_FILE_EXTENSION);
		}

	}

}
