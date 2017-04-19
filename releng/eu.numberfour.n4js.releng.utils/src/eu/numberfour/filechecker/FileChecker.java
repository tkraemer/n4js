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
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

/**
 * Sniffs through all files and checks them for integrity (copyright headers, formatting, line endings, etc.).
 */
public class FileChecker {

	private static final String[] REPOS = { "n4js", "n4js-n4" };
	private static final String[] REPOS_MANDATORY = { "n4js" };

	/** Extensions of files that should be checked more thoroughly. */
	private static final String[] FILE_EXTENSIONS = { ".java", ".xtend", ".xtext", ".xcore", ".xsemantics", ".xml",
			".mwe2", ".adoc" };

	/** These files will be ignored. May contain '/' but should not start or end with '/'. */
	private static final String[] IGNORED_FILES = {
			".antlr-generator-3.2.0-patch.jar" // downloaded by xtext but under git-ignore, so not in repository
	};

	/** All contents of these folders will be ignored. May contain '/' but should not start or end with '/'. */
	private static final String[] IGNORED_FOLDERS = {
			".git",
			"bin",
			"src-gen", "xtend-gen", "xsemantics-gen",
			"emf-gen", "grammar-gen",
			"generated-docs",
			"eu.numberfour.n4js.jsdoc2spec.tests/testresourcesADoc",
			"tools/eu.numberfour.n4js.hlc/target/wsp", // temporary test data under git-ignore, so not in repository
			// temporarily ignored folders FIXME remove the following folders!!!!
			"n4js/n4js-libraries",
			"plugins/eu.numberfour.n4js.external.libraries",
			"plugins/eu.numberfour.n4js.runner/res"
	};

	/** Words disallowed outside of copyright headers. Each word should start with a single capitalized letter. */
	private static final String[] BANNED_WORDS = { "Copyright", "License" };

	/** Optional file in the root of a git repository that declares some files as third-party files. */
	private static final String THIRD_PARTY_FILE_NAME = "third-party.txt";

	/** Files with an extension listed in {@link #FILE_EXTENSIONS} must start with this header. */
	private static final String[] COPYRIGHT_TEXT = {
			"Copyright (c) 2016 NumberFour AG.",
			"All rights reserved. This program and the accompanying materials",
			"are made available under the terms of the Eclipse Public License v1.0",
			"which accompanies this distribution, and is available at",
			"http://www.eclipse.org/legal/epl-v10.html",
			"",
			"Contributors:",
			"  NumberFour AG - Initial API and implementation",
	};

	/**
	 * Files with an extension listed in {@link #FILE_EXTENSIONS} must start with this header (derived from
	 * {@link #COPYRIGHT_TEXT}).
	 */
	private static final String COPYRIGHT_HEADER = ("/**\n"
			+ " * " + Joiner.on("\n * ").join(COPYRIGHT_TEXT) + "\n"
			+ " */").replace("\n * \n", "\n *\n");

	/** XML files must start with this header (derived from {@link #COPYRIGHT_TEXT}). */
	private static final String COPYRIGHT_HEADER_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
			+ "<!--\n"
			+ Joiner.on("\n").join(COPYRIGHT_TEXT) + "\n"
			+ "-->";

	/** ADOC files must start with this header (derived from {@link #COPYRIGHT_TEXT}). */
	private static final String COPYRIGHT_HEADER_ADOC = "////\n"
			+ Joiner.on("\n").join(COPYRIGHT_TEXT) + "\n"
			+ "////";

	/** XCORE files must contain this copyright notice directive (derived from {@link #COPYRIGHT_TEXT}). */
	private static final String COPYRIGHT_GEN_MODEL_PROPERTY = "copyrightText=\""
			+ Joiner.on("\\n").join(COPYRIGHT_TEXT) + "\"";

	// ################################################################################################################
	// this section contains the main code that performs the checking

	private boolean isIgnored(Path path, String pathStr) {
		final File file = path.toFile();
		if (path.endsWith("pom.xml"))
			return false; // never ignore pom.xml!
		if (file.isDirectory())
			return true; // ignore folders
		if (isFile(pathStr, IGNORED_FILES))
			return true; // ignore ignored files
		if (isBelowFolder(pathStr, IGNORED_FOLDERS))
			return true; // ignore files in ignored folders
		if (hasExtension(path, ".prefs"))
			return true; // ignore Eclipse preferences and property files
		return false;
	}

	private Report check(Path path, String content, boolean isRegisteredAsThirdParty) {
		final Report report = new Report(path);

		if (hasExtension(path, ".xml")) {

			// special case: .xml files

			if (!hasCorrectCopyrightHeader(path, content)) {
				report.problems.add("does not contain correct copyright header");
			}

		} else if (hasExtension(path, ".jar")) {

			// special case: .jar files

			if (!isRegisteredAsThirdParty) {
				report.problems.add("unregistered jar (might contain third party stuff)");
			}

		} else {

			if (hasExtension(path, FILE_EXTENSIONS)) {

				// checks for files with one of the extensions in FILE_EXTENSIONS

				final int len = content.length();
				final char charLast = len > 0 ? content.charAt(len - 1) : 0;
				final char char2ndToLast = len > 1 ? content.charAt(len - 2) : 0;

				if (content.contains("\r")) {
					report.problems.add("contains invalid line endings (i.e. contains carriage return: '\\r')");
				} else {
					if (len > 0 && (charLast != '\n' || char2ndToLast == '\n')) {
						report.problems.add("does not end with a single empty line");
						// writeFile(path, fixFileEnding(content));
					}
					if (containsTrailingWhiteSpace(content)) {
						report.problems.add("must not contain lines with trailing white-space");
						// writeFile(path, trimTrailingWhiteSpace(content));
					}
					if (!isRegisteredAsThirdParty && !hasCorrectCopyrightHeader(path, content)) {
						report.problems.add("does not contain correct copyright header");
					}
					if (!isRegisteredAsThirdParty && hasExtension(path, ".xcore")
							&& !content.contains(COPYRIGHT_GEN_MODEL_PROPERTY)) {
						report.problems.add(".xcore file does not contain correct 'copyrightText' genModel property");
					}
					if (!isRegisteredAsThirdParty && !hasExtension(path, ".adoc") && content.contains("@" + "author")) {
						report.problems.add("must not contain author tags");
					}
				}
			}

			// checks for all files

			if (!isRegisteredAsThirdParty && !path.endsWith("about.html")) {
				final String bannedWord = containsBannedWord(path, content);
				if (bannedWord != null) {
					report.problems.add("must not contain banned word '" + bannedWord + "'");
				}
			}
		}

		return report;
	}

	// ################################################################################################################

	/** Main method. */
	public static void main(String[] args) {
		final Path rootPath = findRootPath(args);
		if (rootPath == null || !rootPath.toFile().exists() || !rootPath.toFile().isDirectory()) {
			System.out.println("ERROR: not found or does not point to a folder.");
			System.out.println("Root path must either be given as first command line argument\n"
					+ "OR the current working directory must lie in an N4JS git repository.");
			System.exit(1);
			return; // required to make null-analysis happy
		}
		for (String repoMandatory : REPOS_MANDATORY) {
			if (!rootPath.resolve(repoMandatory).toFile().isDirectory()) {
				System.out.println("ERROR: root folder does not contain a sub folder \"" + repoMandatory + "\"");
				System.exit(1);
				return; // required to make null-analysis happy
			}
		}
		final Path[] repoPaths = Arrays.asList(REPOS).stream().map((repoName) -> rootPath.resolve(repoName))
				.toArray((n) -> new Path[n]);
		final boolean success = new FileChecker().run(repoPaths);
		System.exit(success ? 0 : 1);
	}

	private static final class Report {
		public final Path path;
		public final List<String> problems = new ArrayList<>();

		public Report(Path path) {
			this.path = path;
		}
	}

	private boolean run(Path... repoPaths) {
		System.out.println("=====================================================================================");

		final AtomicInteger count = new AtomicInteger(0);
		final AtomicInteger ignored = new AtomicInteger(0);
		final AtomicInteger checked = new AtomicInteger(0);
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

					count.incrementAndGet();

					if (isIgnored(path, pathStr)) {

						ignored.incrementAndGet();

					} else {

						checked.incrementAndGet();

						try {

							final String content = readFile(path);
							final Report report = check(path, content, thirdPartyFiles.contains(path));
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
			for (String err : pathsPerError.keySet()) {
				final Collection<Path> paths = pathsPerError.get(err);
				System.out.println("PROBLEM \"" + err + "\" in " + paths.size() + " files:");
				for (Path path : paths) {
					System.out.println("    " + path);
				}
			}
		} else {
			System.out.println("No problems.");
		}
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println("Checked " + checked + " files (" + ignored + " ignored; " + count + " total).");
		System.out.println("Valid files: " + validFiles.size());
		System.out.println("Invalid files: " + invalidFiles.size());
		System.out.println("Erroneous files: " + erroneousFiles.size());
		System.out.println("=====================================================================================");
		return invalidFiles.isEmpty() && erroneousFiles.isEmpty();
	}

	// ################################################################################################################

	/**
	 * The root path is expected to point to the folder containing the git repositories listed in {@link #REPOS}. It
	 * must be provided as the first command line argument OR it will be derived from the current working directory.
	 *
	 * Returns <code>null</code> in case of error.
	 */
	private static Path findRootPath(String[] args) {
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

	/**
	 * <pre>
	 * #
	 * # List of files with third-party copyright.
	 * #
	 * #
	 * # This file is processed automatically by FileChecker.java to ensure the below information is kept up-to-date.
	 * #
	 * # Format:
	 * # every non-empty line in this file either starts with '#' and is then a comment (to be ignored) or must
	 * # contain the relative path to a file with third-party copyright. The paths must be relative to the folder
	 * # containing this file.
	 * #
	 * </pre>
	 */
	private static Set<Path> readListOfThirdPartyFiles(Path rootPath) throws IOException {
		System.out.println("Reading list of third-party files from \"" + THIRD_PARTY_FILE_NAME + "\" ...");
		final Path thirdPartyList = rootPath.resolve(THIRD_PARTY_FILE_NAME);
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
			throw new IOException("paths in " + THIRD_PARTY_FILE_NAME + " must be relative, i.e. not start with '/'");
		// make sure files exist
		final List<Path> paths = lines.stream().map((l) -> rootPath.resolve(l)).collect(Collectors.toList());
		for (Path p : paths) {
			if (!p.toFile().exists()) {
				throw new IOException("file does not exist: " + p);
			}
			if (!p.toFile().isFile()) {
				throw new IOException("not a file: " + p);
			}
		}
		System.out.println("    " + paths.size() + " files declared as third-party files.");
		return new HashSet<>(paths); // order does not matter, so don't need LinkedHashSet
	}

	private static boolean containsTrailingWhiteSpace(String str) {
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

	private static String getCanonicalPath(File file) {
		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	private static boolean hasExtension(Path path, String... extensions) {
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

	private static boolean isFile(String pathStr, String... fileNames) {
		for (String fileName : fileNames) {
			if (pathStr.endsWith("/" + fileName)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isBelowFolder(String pathStr, String... folderNames) {
		for (String folderName : folderNames) {
			if (pathStr.contains("/" + folderName + "/")) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasCorrectCopyrightHeader(Path path, String content) {
		return beginIndexWithoutCopyrightHeader(path, content) > 0;
	}

	private static int beginIndexWithoutCopyrightHeader(Path path, String content) {
		if (hasExtension(path, ".xml")) {
			return startsWithCopyrightHeader(content, COPYRIGHT_HEADER_XML)
					? COPYRIGHT_HEADER_XML.length() : 0;
		} else if (hasExtension(path, ".adoc")) {
			return startsWithCopyrightHeader(content, COPYRIGHT_HEADER_ADOC)
					? COPYRIGHT_HEADER_ADOC.length() : 0;
		} else if (hasExtension(path, ".js") || hasExtension(path, ".n4js") || hasExtension(path, ".n4jsd")) {
			final String hdr = COPYRIGHT_HEADER.replace("/**\n", "/*\n");
			return startsWithCopyrightHeader(content, hdr) ? hdr.length() : 0;
		} else {
			return startsWithCopyrightHeader(content, COPYRIGHT_HEADER) ? COPYRIGHT_HEADER.length() : 0;
		}
	}

	private static boolean startsWithCopyrightHeader(String content, String header) {
		return content.startsWith(header) || content.startsWith(header.replace(" 2016 ", " 2017 ")); // FIXME
	}

	private static String containsBannedWord(Path path, String content) {
		return containsWord(path, content, true, BANNED_WORDS);
	}

	private static String containsWord(Path path, String content, boolean skipCopyrightHeader, String... words) {
		if (skipCopyrightHeader) {
			final int beginIndex = beginIndexWithoutCopyrightHeader(path, content);
			content = content.substring(beginIndex);
			if (hasExtension(path, ".xcore")) {
				content = content.replace("copyrightFields=\"false\",", "");
				content = content.replace(COPYRIGHT_GEN_MODEL_PROPERTY, "");
				content = content.replace(COPYRIGHT_GEN_MODEL_PROPERTY.replace(" 2016 ", " 2017 "), ""); // FIXME
			}
		}
		for (String word : words) {
			if (content.contains(word) || content.contains(word.toLowerCase())
					|| content.contains(word.toUpperCase())) {
				return word;
			}
		}
		return null;
	}

	@SuppressWarnings("unused")
	private static String fixFileEnding(String content) {
		if (content.length() > 0) {
			int endIndex = content.length();
			while (endIndex > 0 && content.charAt(endIndex - 1) == '\n') {
				--endIndex;
			}
			content = content.substring(0, endIndex) + '\n';
		}
		return content;
	}

	@SuppressWarnings("unused")
	private static String trimTrailingWhiteSpace(String content) {
		return content.replaceAll("[ \\t\\x0B\\f\\r]+\\n", "\n");
	}

	private static String readFile(Path path) throws IOException {
		return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
	}

	@SuppressWarnings("unused")
	private static void writeFile(Path path, String content) {
		try {
			Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
