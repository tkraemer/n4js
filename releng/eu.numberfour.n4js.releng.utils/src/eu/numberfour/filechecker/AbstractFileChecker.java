/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.filechecker;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

/**
 * FIXME move more logic to this abstract base class
 */
/* package */ abstract class AbstractFileChecker {

	protected static final String[] REPOS = { "n4js", "n4js-n4" }; // FIXME remove all references to "n4js-n4"
	protected static final String[] REPOS_MANDATORY = { "n4js" };

	/** Folders that are disregarded entirely (contained files won't even be counted as "ignored files"). */
	protected static final String[] DISREGARDED_FOLDERS = {
			".git",
			"bin"
	};

	/** Optional file in the root of a git repository that declares some files as third-party files. */
	protected static final String FILE_NAME__THIRD_PARTY = "third-party.txt";

	protected abstract boolean isIgnored(Path path, String pathStr);

	protected abstract void checkFile(Path path, String content, boolean isRegisteredAsThirdParty, Report report);

	protected abstract void checkFolder(Path path, int depth, Report report);

	// ################################################################################################################

	protected static Path[] findRepoPaths(String[] args) {
		final Path rootPath = findRootPath(args);
		for (String repoMandatory : REPOS_MANDATORY) {
			if (!rootPath.resolve(repoMandatory).toFile().isDirectory()) {
				System.out.println("ERROR: root folder doesn't contain mandatory sub folder \"" + repoMandatory + "\"");
				System.exit(1);
			}
		}
		final Path[] repoPaths = Arrays.asList(REPOS).stream().map((repoName) -> rootPath.resolve(repoName))
				.toArray((n) -> new Path[n]);
		return repoPaths;
	}

	/**
	 * Same as {@link #findRootPathFailSafe(String[])}, but will show error messages and exit in case of error. Always
	 * returns a non-<code>null</code> path that points to an existing folder.
	 */
	protected static Path findRootPath(String[] args) {
		final Path rootPath = findRootPathFailSafe(args);
		if (rootPath == null || !rootPath.toFile().exists() || !rootPath.toFile().isDirectory()) {
			System.out.println("ERROR: root path not found or does not point to a folder");
			System.out.println("Root path must either be given as first command line argument\n"
					+ "OR the current working directory must lie in an N4JS git repository.");
			System.exit(1);
		}
		return rootPath;
	}

	/**
	 * The root path is expected to point to the folder containing the git repositories listed in {@link #REPOS}. It
	 * must be provided as the first command line argument OR it will be derived from the current working directory.
	 *
	 * Returns <code>null</code> in case of error.
	 */
	protected static Path findRootPathFailSafe(String[] args) {
		try {
			if (args.length > 0) {
				// take root path from 1st command line argument
				return new File(args[0]).getCanonicalFile().toPath();
			} else {
				// derive root path from current working directory
				File curr = new File(".").getCanonicalFile();
				while (curr != null && curr.isDirectory()
						&& !org.eclipse.xtext.util.Arrays.contains(REPOS, curr.getName())) {
					curr = curr.getParentFile();
				}
				return curr != null ? curr.getParentFile().toPath() : null;
			}
		} catch (IOException e) {
			return null;
		}
	}

	// ################################################################################################################

	protected static final class Report {
		public final Path path;
		public final List<String> problems = new ArrayList<>();

		public Report(Path path) {
			this.path = path;
		}
	}

	protected boolean run(Path... repoPaths) {
		System.out.println("=====================================================================================");

		final AtomicInteger count = new AtomicInteger(0);
		final AtomicInteger ignored = new AtomicInteger(0);
		final AtomicInteger checked = new AtomicInteger(0);
		final AtomicInteger checkedThirdParty = new AtomicInteger(0);
		final Map<Path, Report> validFiles = new LinkedHashMap<>();
		final Map<Path, Report> invalidFiles = new LinkedHashMap<>();
		final Map<Path, Throwable> erroneousFiles = new LinkedHashMap<>();

		try {
			for (Path repoPath : repoPaths) {
				System.out.println("Asserting file integrity in " + repoPath);
				final Set<Path> thirdPartyFiles = readListOfThirdPartyFiles(repoPath);
				System.out.print("Checking files ...");
				Files.walk(repoPath).forEachOrdered((path) -> {
					File file = path.toFile();
					String pathStr = getCanonicalPath(file);

					if (isBelowFolder(pathStr, DISREGARDED_FOLDERS)) {
						return; // completely ignore these folders (do not even count the files)
					}

					count.incrementAndGet();

					if (isIgnored(path, pathStr)) {

						ignored.incrementAndGet();

					} else {

						checked.incrementAndGet();

						try {

							final Report report = new Report(path);
							if (path.toFile().isDirectory()) {
								checkFolder(path, path.getNameCount() - repoPath.getNameCount(), report);
							} else {
								final String content = readFile(path);
								final boolean isThirdParty = thirdPartyFiles.contains(path);
								if (isThirdParty) {
									checkedThirdParty.incrementAndGet();
								}
								checkFile(path, content, isThirdParty, report);
							}
							(report.problems.isEmpty() ? validFiles : invalidFiles).put(path, report);

						} catch (Throwable th) {
							erroneousFiles.put(path, th);
							// do not abort entirely, continue with next file
						}
					}
				});
				System.out.println(" done.");
			}
		} catch (IOException e) {
			System.out.println("ERROR while walking folder tree:");
			e.printStackTrace();
			System.out.println("ABORTING");
			return false;
		}

		System.out.println("-------------------------------------------------------------------------------------");
		if (!invalidFiles.isEmpty()) {
			final Multimap<String, Path> pathsPerError = LinkedHashMultimap.create();
			invalidFiles.values().stream()
					.forEachOrdered((r) -> r.problems.stream().forEachOrdered((err) -> pathsPerError.put(err, r.path)));
			final List<String> errors = new ArrayList<>(pathsPerError.keySet());
			Collections.sort(errors);
			for (String err : errors) {
				final Collection<Path> paths = pathsPerError.get(err);
				System.out.println("PROBLEM in " + paths.size() + " files: " + err);
				for (Path path : paths) {
					System.out.println("    " + path);
				}
			}
		} else {
			System.out.println("No problems.");
		}
		if (!erroneousFiles.isEmpty()) {
			System.out.flush();
			sleep(500);
			for (Entry<Path, Throwable> e : erroneousFiles.entrySet()) {
				System.err.println("ERROR processing file: " + e.getKey());
				e.getValue().printStackTrace();
			}
			System.err.flush();
			sleep(500);
		}
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Checked " + checked + " files, including " + checkedThirdParty + " third-party files ("
				+ ignored + " ignored; " + count + " total).");
		System.out.println("Valid files: " + validFiles.size());
		System.out.println("Invalid files: " + invalidFiles.size());
		System.out.println("Erroneous files: " + erroneousFiles.size());
		System.out.println("=====================================================================================");
		return invalidFiles.isEmpty() && erroneousFiles.isEmpty();
	}

	/**
	 * <pre>
	 * #
	 * # List of files and folders with third-party copyright.
	 * #
	 * #
	 * # This file is processed automatically by FileChecker.java to ensure the below information is kept up-to-date.
	 * #
	 * # Format:
	 * # every non-empty line in this file either starts with '#' and is then a comment (to be ignored) or must
	 * # contain the relative path to a file with third-party copyright. If a path ends in "/**" it must point to
	 * # a folder and its contents are declared to be third-party files. All paths must be relative to the folder
	 * # containing this file.
	 * #
	 * </pre>
	 */
	private static Set<Path> readListOfThirdPartyFiles(Path rootPath) throws IOException {
		System.out.println("Reading list of third-party files from \"" + FILE_NAME__THIRD_PARTY + "\" ...");
		final Path thirdPartyList = rootPath.resolve(FILE_NAME__THIRD_PARTY);
		if (!thirdPartyList.toFile().exists()) {
			// note: providing a third-party.txt file is optional, so no error here:
			System.out.println("    no such file found, assuming 0 third-party files.");
			return Collections.emptySet();
		}
		final List<String> lines = Files.readAllLines(thirdPartyList, StandardCharsets.UTF_8);
		// trim all lines
		lines.replaceAll((l) -> l.trim());
		// remove empty lines and comments
		lines.removeIf((l) -> l.length() == 0 || l.startsWith("#"));
		// make sure all paths are relative
		if (lines.stream().anyMatch((l) -> l.startsWith("/") || l.startsWith("\\")))
			throw new IOException("paths in " + FILE_NAME__THIRD_PARTY + " must be relative, i.e. not start with '/'");
		// make sure all files/folders exist & are of correct type
		final List<Path> paths = lines.stream().map((l) -> rootPath.resolve(l)).collect(Collectors.toList());
		int files = 0;
		int folders = 0;
		for (Path p : paths) {
			if (p.endsWith("**")) {
				// folder
				final Path parent = p.getParent();
				if (!parent.toFile().exists()) {
					throw new IOException("folder does not exist: " + parent);
				}
				if (!parent.toFile().isDirectory()) {
					throw new IOException("not a folder: " + parent);
				}
				folders++;
			} else {
				// file
				if (!p.toFile().exists()) {
					throw new IOException("file does not exist: " + p);
				}
				if (!p.toFile().isFile()) {
					throw new IOException("not a file: " + p);
				}
				files++;
			}
		}
		// replace folders by their contained files
		for (int i = 0; i < paths.size(); i++) {
			final Path p = paths.get(i);
			if (p.endsWith("**")) {
				final Path parent = p.getParent();
				final List<Path> allFiles = getAllContainedFiles(parent);
				if (allFiles.isEmpty()) {
					throw new IOException("folder is empty: " + parent);
				}
				paths.remove(i);
				paths.addAll(i, allFiles);
				i += allFiles.size() - 1;
			}
		}
		System.out.println("    " + files + " files and " + folders + " folders (for a total of " + paths.size()
				+ " files) declared as third-party artifacts.");
		return new HashSet<>(paths); // order does not matter, so don't need LinkedHashSet
	}

	// ################################################################################################################

	protected static boolean containsTrailingWhiteSpace(String str) {
		int idx = 0;
		while (idx < str.length()) {
			idx = str.indexOf('\n', idx);
			if (idx < 0) {
				break;
			}
			char ch = idx > 0 ? str.charAt(idx - 1) : 'X';
			if (ch == '\r') {
				ch = idx > 1 ? str.charAt(idx - 2) : 'X';
			}
			if (ch != '\n' && Character.isWhitespace(ch)) {
				return true;
			}
			idx++;
		}
		return false;
	}

	protected static String getCanonicalPath(File file) {
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	protected static boolean hasExtension(Path path, String... extensions) {
		final Path namePath = path.getFileName();
		if (namePath == null) {
			return false;
		}
		final String name = namePath.toString();
		for (String ext : extensions) {
			if (name.endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

	protected static boolean isFile(String pathStr, String... fileNames) {
		for (String fileName : fileNames) {
			if (pathStr.endsWith("/" + fileName)) {
				return true;
			}
		}
		return false;
	}

	protected static boolean isBelowFolder(String pathStr, String... folderNames) {
		for (String folderName : folderNames) {
			if (pathStr.contains("/" + folderName + "/")) {
				return true;
			}
		}
		return false;
	}

	protected static boolean inN4Repo(Path path) {
		return isBelowFolder(path.toString(), "n4js-n4");
	}

	protected boolean containsFileWithName(Path path, String fileName) {
		final File file = path.toFile();
		final File[] files = file.listFiles();
		return files != null && Stream.of(files).anyMatch(f -> fileName.equals(f.getName()));
	}

	protected boolean containsPattern(String str, Pattern pattern) {
		return pattern.matcher(str).find();
	}

	protected static String fixFileEnding(String content) {
		if (content.length() > 0) {
			int endIndex = content.length();
			while (endIndex > 0 && content.charAt(endIndex - 1) == '\n') {
				--endIndex;
			}
			content = content.substring(0, endIndex) + '\n';
		}
		return content;
	}

	protected static String trimTrailingWhiteSpace(String content) {
		return content.replaceAll("[ \\t\\x0B\\f\\r]+\\n", "\n");
	}

	protected static List<Path> getAllContainedFiles(Path path) throws IOException {
		return Files.walk(path).filter(p -> p.toFile().isFile()).collect(Collectors.toList());
	}

	protected static String readFile(Path path) throws IOException {
		return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
	}

	protected static void writeFile(Path path, String content) {
		try {
			Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// ignore
		}
	}
}
