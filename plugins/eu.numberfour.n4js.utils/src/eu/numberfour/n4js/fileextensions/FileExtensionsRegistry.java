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
package eu.numberfour.n4js.fileextensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;

import com.google.common.base.Splitter;
import com.google.inject.Singleton;

/**
 * This class collect runnable file extensions from extensions to extension point.
 */
@Singleton
public class FileExtensionsRegistry {

	/**
	 * The type of file extension, e.g. runnable file extension.
	 */
	public static enum FileExtensionType {
		/**
		 * Transpilable file extension
		 */
		TRANSPILABLE_FILE_EXTENSION,
		/**
		 * Test file extension
		 */
		TEST_FILE_EXTENSION,
		/**
		 * Runnable file extension
		 */
		RUNNABLE_FILE_EXTENSION
	}

	private final static Logger log = Logger.getLogger(FileExtensionsRegistry.class);

	/* The extension point to file extensions */
	private static final String FILE_EXTENSIONS_POINT_ID = "eu.numberfour.n4js.utils.fileExtensions";
	private static final String ATT_TRANSPILABLE_FILE_EXTENSIONS = "transpilableFileExtensions";
	private static final String ATT_TEST_FILE_EXTENSIONS = "testFileExtensions";
	private static final String ATT_RUNNABLE_FILE_EXTENSIONS = "runnableFileExtensions";

	private static final String ATT_FILE_EXTENSION = "extensions";
	private boolean isInitialized = false;
	private final Collection<String> transpilableFileExtensions = new ArrayList<>();
	private final Collection<String> testFileExtensions = new ArrayList<>();
	private final Collection<String> runnableFileExtensions = new ArrayList<>();

	/**
	 * Register a file extension. This method should only be invoked by client code directly in headless mode. When
	 * running in Eclipse, file extensions will be registered via the 'fileExtensions' extension point.
	 */
	public void register(String fileExtension, FileExtensionType extensionType) {
		switch (extensionType) {
		case TRANSPILABLE_FILE_EXTENSION:
			transpilableFileExtensions.add(fileExtension);
			break;
		case TEST_FILE_EXTENSION:
			testFileExtensions.add(fileExtension);
			break;
		case RUNNABLE_FILE_EXTENSION:
			runnableFileExtensions.add(fileExtension);
		}
	}

	/**
	 * Return file extensions
	 */
	public Collection<String> getFileExtensions(FileExtensionType extensionType) {
		if (!isInitialized) {
			initialize();
		}
		switch (extensionType) {
		case TRANSPILABLE_FILE_EXTENSION:
			return Collections.unmodifiableCollection(transpilableFileExtensions);
		case TEST_FILE_EXTENSION:
			return Collections.unmodifiableCollection(testFileExtensions);
		case RUNNABLE_FILE_EXTENSION:
			return Collections.unmodifiableCollection(runnableFileExtensions);
		}
		throw new UnsupportedOperationException("This file extension type " + extensionType + " is not supported yet");
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
			final IExtension[] extensions = registry.getExtensionPoint(FILE_EXTENSIONS_POINT_ID).getExtensions();
			for (IExtension extension : extensions) {
				final IConfigurationElement[] configElems = extension.getConfigurationElements();
				for (IConfigurationElement elem : configElems) {
					try {
						List<String> fileExtensions = Splitter.on(',').trimResults().omitEmptyStrings()
								.splitToList(elem.getAttribute(ATT_FILE_EXTENSION));

						if (ATT_TRANSPILABLE_FILE_EXTENSIONS.equals(elem.getName())) {
							transpilableFileExtensions.addAll(fileExtensions);
						} else if (ATT_TEST_FILE_EXTENSIONS.equals(elem.getName())) {
							testFileExtensions.addAll(fileExtensions);
						} else if (ATT_RUNNABLE_FILE_EXTENSIONS.equals(elem.getName())) {
							runnableFileExtensions.addAll(fileExtensions);
						}
					} catch (Exception ex) {
						log.error("Error while reading extensions for extension point " + FILE_EXTENSIONS_POINT_ID, ex);
					}
				}
			}
		}
	}

	/**
	 * Reset the lists of file extensions to empty
	 */
	public void reset() {
		isInitialized = false;
		transpilableFileExtensions.clear();
		testFileExtensions.clear();
		runnableFileExtensions.clear();
	}
}
