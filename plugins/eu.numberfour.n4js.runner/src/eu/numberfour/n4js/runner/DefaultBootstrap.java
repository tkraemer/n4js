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
package eu.numberfour.n4js.runner;

import static com.google.common.base.Preconditions.checkState;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;

import eu.numberfour.n4js.utils.io.FileDeleter;

/**
 * Manages default bootstrap code, i.e. three default init modules and one default exec module. To be replaced by a
 * library manager that can provide full N4JS projects packaged as NFARs.
 */
public class DefaultBootstrap {

	/** File name of the Node.js environment. */
	protected static final String IDE_NODEJS_ENV = "ide-nodejs-env";

	/**
	 * Returns with the path for the default Node.js environment location as a string.
	 *
	 * @return the path pointing to the default Node.js environment.
	 */
	public static final String getDefaultNodeEnvPath() {
		URL resourceUrl = null;
		try {
			resourceUrl = getResource(IDE_NODEJS_ENV);
			if (Platform.isRunning()) {
				resourceUrl = FileLocator.toFileURL(resourceUrl);
			}

			final URLConnection connection = resourceUrl.openConnection();
			if (connection instanceof JarURLConnection) {
				return recursivelyCopyContent((JarURLConnection) connection);
			}

			return new File(resourceUrl.toURI()).getCanonicalFile().getAbsolutePath().replace("\\", "\\\\");
		} catch (final Exception e) {
			System.err.println("Platform is running: " + Platform.isRunning());
			if (Platform.isRunning()) {
				System.err.println(FileLocator.find(resourceUrl));
				try {
					System.err.println(FileLocator.toFileURL(resourceUrl));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			System.err.println("After : " + Platform.isRunning());
			System.err.println("Resource URL was: " + resourceUrl);
			e.printStackTrace();
			throw new RuntimeException("Error while getting NODE_PATH.", e);
		}
	}

	private static String recursivelyCopyContent(JarURLConnection connection) throws IOException {
		final File tempFolder = getTempFolder().toFile();
		final File rootFolder = new File(tempFolder, IDE_NODEJS_ENV);
		if (rootFolder.exists()) {
			FileDeleter.delete(rootFolder.toPath());
		}
		checkState(rootFolder.mkdir(), "Error while creating folder for Node.js environment. " + rootFolder);
		checkState(rootFolder.isDirectory(), "Expected directory but got a file: " + rootFolder);
		rootFolder.deleteOnExit();

		try (final JarFile jarFile = connection.getJarFile()) {
			for (final Enumeration<JarEntry> em = jarFile.entries(); em.hasMoreElements(); /**/) {
				final JarEntry entry = em.nextElement();
				// Do not process anything which is not under the Node.js environment folder
				if (!entry.getName().startsWith(IDE_NODEJS_ENV)) {
					continue;
				}
				final String fileName = entry.getName();// .substring(connection.getEntryName().length());
				final File newResource = new File(tempFolder, fileName);
				if (entry.isDirectory()) {
					if (!newResource.exists()) {
						checkState(newResource.mkdir(), "Error while creating new folder at: " + newResource);
					}
				} else {
					checkState(newResource.createNewFile(), "Error while creating new file at: " + newResource);
					try (final InputStream is = jarFile.getInputStream(entry)) {
						com.google.common.io.Files.copy(() -> is, newResource);
					}
				}
				newResource.deleteOnExit();
			}
		}
		return rootFolder.getCanonicalFile().getAbsolutePath().replace("\\", "\\\\");
	}

	/**
	 * Returns with the URL of the resource given its unique name. The URL may reference an absent file.
	 *
	 * @param resourceName
	 *            the name of the resource.
	 * @return the URL referencing to the resource given with its resource name.
	 */
	private static URL getResource(String resourceName) {
		return DefaultBootstrap.class.getResource("/" + resourceName);
	}

	private static Path getTempFolder() {
		final String tempFolder = System.getProperty("java.io.tmpdir");
		final File file = new File(tempFolder);
		if (!file.exists() || !file.canWrite()) {
			throw new RuntimeException("Cannot access temporary directory under: " + tempFolder);
		}
		return file.toPath();
	}
}
