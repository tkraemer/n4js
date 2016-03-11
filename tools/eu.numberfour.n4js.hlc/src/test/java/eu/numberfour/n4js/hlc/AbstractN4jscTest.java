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
package eu.numberfour.n4js.hlc;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.SKIP_SUBTREE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.After;

import eu.numberfour.n4js.validation.helper.N4JSLanguageConstants;

/**
 */
public class AbstractN4jscTest {

	/**
	 * name of sub-directory of the compiled result. Used to count files having this string as directory name on it's
	 * {@link #assertFilesCompiledToES(int, String)}
	 */
	private static final String SUBGENERATOR_PATH = N4JSLanguageConstants.TRANSPILER_SUBFOLDER_FOR_TESTS;
	/** name of target folder */
	protected static final String TARGET = "target";
	/** name of workspace sub-folder (inside target folder) */
	protected static final String WSP = "wsp";
	/** name of package containing the test resources */
	protected static final String FIXTURE = "src/test/resources";

	/** name of default test data set */
	protected static final String TEST_DATA_SET__BASIC = "basic";
	/** name of test data set for launching testers from the command line */
	protected static final String TEST_DATA_SET__TESTERS = "testers";

	/**
	 * Copy a fresh fixture to the workspace area. Deleting old leftovers from former tests.
	 */
	protected static void setupWorkspace(String testDataSet) throws IOException {
		File wsp = new File(TARGET, WSP);
		File fixture = new File(FIXTURE, testDataSet);
		// clean
		System.out.println("Workspace : " + wsp.getAbsolutePath());
		// Files.deleteIfExists(wsp.toPath());
		if (wsp.exists()) {
			Files.walkFileTree(wsp.toPath(), new TreeDeleter());
		}
		// copy
		Files.walkFileTree(fixture.toPath(), new TreeCopier(fixture.toPath(), wsp.toPath()));
	}

	/**
	 * Flush outputs after tests.
	 */
	@After
	public void flush() {
		System.out.flush();
		System.err.flush();
		System.out.println("======= ======= ======= ======= ======= ======= ======= ======= ======= ======= ");
		System.out.flush();

	}

	/** Wrapper for FileVisitor */
	private static class Counter {
		public int i = 0;

		public int inc() {
			return i++;
		}
	}

	/**
	 * @param i
	 *            expected number i of "*.js" files in all "es5" sub folders
	 * @param workspaceroot
	 *            subtree to search in passed as argument to {@link File}
	 */
	protected void assertFilesCompiledToES(int i, String workspaceroot) {
		File f = new File(workspaceroot);
		final Counter c = new Counter();
		try {
			Files.walkFileTree(f.toPath(), new FileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

					// skip src
					if ("src".equals(dir.getFileName())) {
						return FileVisitResult.SKIP_SUBTREE;
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (file.getFileName().toString().endsWith(".js")) {
						for (int j = 0; j < file.getNameCount() - 1; j++) {
							if (SUBGENERATOR_PATH.equals(file.getName(j).toString())) {
								c.inc();
								return FileVisitResult.CONTINUE;
							}
						}
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					if (SUBGENERATOR_PATH.equals(dir.getFileName()))
						return FileVisitResult.SKIP_SIBLINGS;
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue("Could not parse file tree of path '" + workspaceroot + "' exc = " + e.getMessage(), false);
		}
		assertEquals("Files expected", i, c.i);
	}

	// = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = =
	// Static File helper methods.
	// = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = =
	/**
	 * Determine class.method of the direct caller by inspecting the current thread's stacktrace.
	 *
	 * @return Class + Method name of the caller.
	 */
	protected static String logMethodname() {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
		String name = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "";
		return "INSIDE of " + name;
	}

	// = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = =
	// Static File helper classes.
	// = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = = 0 = = 00=0====0== = =
	/**
	 *
	 */
	private static class TreeCopier implements FileVisitor<Path> {
		private final Path source;
		private final Path target;

		TreeCopier(Path source, Path target) {
			this.source = source;
			this.target = target;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
			CopyOption[] options = new CopyOption[0];

			Path newdir = target.resolve(source.relativize(dir));
			try {
				Files.copy(dir, newdir, options);
			} catch (FileAlreadyExistsException x) {
				// ignore
			} catch (IOException x) {
				System.err.format("Unable to create: %s: %s%n", newdir, x);
				return SKIP_SUBTREE;
			}
			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			Files.copy(file, target.resolve(source.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
			return CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) {
			if (exc instanceof FileSystemLoopException) {
				System.err.println("cycle detected: " + file);
			} else {
				System.err.format("Unable to copy: %s: %s%n", file, exc);
			}
			return CONTINUE;
		}
	}

	/**
	 *
	 */
	private static class TreeDeleter implements FileVisitor<Path> {

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			Files.delete(file);
			System.out.println("Deleting file: " + file);
			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			System.out.println("ouch what happened ? " + exc);
			return CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			Files.delete(dir);
			System.out.println("Deleting dir: " + dir);
			return CONTINUE;
		}
	}
}
