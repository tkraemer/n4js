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

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import org.apache.log4j.Logger;

/**
 * For deleting a file resource or recursively a folder and it content.
 */
public class FileDeleter implements FileVisitor<Path> {

	private static final Logger LOGGER = Logger.getLogger(FileDeleter.class);

	/**
	 * Deletes a single file or recursively a folder with its content.
	 *
	 * @param resourceToDelete
	 *            path to the file resource or to the folder to delete.
	 * @throws IOException
	 *             in unrecognized cases.
	 */
	public static void delete(Path resourceToDelete) throws IOException {
		if (resourceToDelete.toFile().exists()) {
			Files.walkFileTree(resourceToDelete, new FileDeleter());
		}
	}

	/**
	 * Deletes a single file or recursively a folder with its content.
	 *
	 * @param resourceToDelete
	 *            the file resource or to the folder to delete.
	 * @throws IOException
	 *             in unrecognized cases.
	 * @see #delete(Path)
	 */
	public static void delete(File resourceToDelete) throws IOException {
		if (resourceToDelete.exists()) {
			delete(resourceToDelete.toPath());
		}
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		Files.delete(file);
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		LOGGER.error("Unexpected file visiting failure.", exc);
		return CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		Files.delete(dir);
		return CONTINUE;
	}

}
