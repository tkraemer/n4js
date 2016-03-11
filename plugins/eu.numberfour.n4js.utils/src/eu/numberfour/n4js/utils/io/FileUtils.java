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
package eu.numberfour.n4js.utils.io;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Convenient methods for {@code java.io.File}s.
 */
public abstract class FileUtils {

	private FileUtils() {
		// private.
	}

	/**
	 * Returns with the value of {@code System.getProperty("java.io.tmpdir")}. Never {@code null}.
	 */
	private static final String getTempDirValue() {
		return checkNotNull(System.getProperty("java.io.tmpdir"), "Null for java.io.tmpdir system property.");
	}

	/**
	 * Returns with the path of the user temporary folder. Never returns with {@code null}.
	 *
	 * @return the path to the temporary folder.
	 */
	public static Path getTempFolder() {
		final File file = new File(getTempDirValue());
		if (!file.exists() || !file.canWrite()) {
			throw new RuntimeException("Cannot access temporary directory under: " + getTempDirValue());
		}
		return file.toPath();
	}

	/**
	 * Creates a new directory with the given parent folder and folder name. The newly created folder will be deleted on
	 * graceful VM shutdown.
	 *
	 * @param parent
	 *            the path of the parent folder.
	 * @param folderName
	 *            the name of the folder.
	 * @return the path to the new directory.
	 */
	public static Path createDirectory(final Path parent, final String folderName) {
		final File file = new File(parent.toFile(), folderName);
		if (!file.exists()) {
			if (!file.mkdir()) {
				throw new RuntimeException(
						"Error while trying to create folder at " + parent + " with " + folderName + ".");
			}
		}
		file.deleteOnExit();
		return file.toPath();
	}

	/**
	 * Creates a new temp directory with the given parent. The newly created folder will be deleted on graceful VM
	 * shutdown.
	 *
	 * @param parent
	 *            the path of the parent folder.
	 * @return the path to the new directory.
	 */
	public static Path createDirectory(final Path parent) {
		File file;
		try {
			file = Files.createTempDirectory(parent, null).toFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (!file.exists()) {
			throw new RuntimeException(
					"Error while trying to create folder at " + parent + ".");
		}
		file.deleteOnExit();
		return file.toPath();
	}

	/**
	 * Creates a new temp directory in the java temp folder. The newly created folder will be deleted on graceful VM
	 * shutdown.
	 *
	 * @return the path to the new directory.
	 */
	public static Path createTempDirectory() {
		final File parent = new File(getTempDirValue());
		if (!parent.exists() || !parent.canWrite()) {
			throw new RuntimeException("Cannot access temporary directory under: " + getTempDirValue());
		}
		File child;
		try {
			child = Files.createTempDirectory(parent.toPath(), null).toFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (!child.exists()) {
			throw new RuntimeException(
					"Error while trying to create folder at " + parent + ".");
		}
		child.deleteOnExit();
		return child.toPath();
	}

}
