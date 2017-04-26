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

import java.nio.file.Path;
import java.util.regex.Pattern;

import com.google.common.base.Joiner;

/**
 * Sniffs through all files and checks them for integrity (copyright headers, formatting, Unix line endings, etc.).
 */
public class FileChecker extends AbstractFileChecker {

	/** Name used as vendor (in manifest.mf) and provider (in feature.xml). */
	private static final String PROVIDER_NAME = "Eclipse N4JS Project";
	private static final String PROVIDER_NAME_N4 = "NumberFour AG";

	private static final String FILE_NAME__DOT_PROJECT = ".project";

	private static final String FILE_NAME__PLUGIN_PROPERTIES = "plugin.properties";
	private static final String FILE_NAME__MANIFEST_MF = "MANIFEST.MF";
	private static final String FILE_NAME__FEATURE_PROPERTIES = "feature.properties";
	private static final String FILE_NAME__FEATURE_XML = "feature.xml";

	/**
	 * Name of file containing the Eclipse Foundation Software User Agreement, see
	 * https://www.eclipse.org/legal/epl/notice.php
	 */
	private static final String FILE_NAME__NOTICE_HTML = "notice.html";

	/** Name of file with legal information to be placed in root folder of every bundle (except feature bundles). */
	private static final String FILE_NAME__ABOUT_HTML = "about.html";
	private static final String FILE_NAME__ABOUT_HTML_TEMPLATE = "about.html_TEMPLATE";

	/** Name of file with same content as "about.html" but in feature bundles. */
	private static final String FILE_NAME__LICENSE_HTML = "license.html";

	/** Name of file containing the Eclipse Public License. */
	private static final String FILE_NAME__EPL = "EPL-1.0.html";

	/** Extensions of files that should be checked more thoroughly. */
	private static final String[] FILE_EXTENSIONS = { ".java", ".xtend", ".xtext", ".xcore", ".xsemantics", ".xml",
			".mwe2", ".adoc" };

	/** These files will be ignored. May contain '/' but should not start or end with '/'. */
	private static final String[] IGNORED_FILES = {
			".antlr-generator-3.2.0-patch.jar", // downloaded by xtext but under git-ignore, so not in repository
			FILE_NAME__THIRD_PARTY
	};

	/** All contents of these folders will be ignored. May contain '/' but should not start or end with '/'. */
	private static final String[] IGNORED_FOLDERS = {
			"src-gen", "xtend-gen", "xsemantics-gen",
			"emf-gen", "grammar-gen", "model/generated",
			"generated-docs",
			"eu.numberfour.n4js.jsdoc2spec.tests/testresourcesADoc",
			"tools/eu.numberfour.n4js.hlc/target/wsp", // temporary test data under git-ignore, so not in repository
	};

	/** Words disallowed outside of copyright headers. Each word should start with a single capitalized letter. */
	private static final String[] BANNED_WORDS = {
			"Copyright",
			"License",
			"numberfour.jira.com",
			"jira.numberfour.eu",

			// FIXME add more banned words (at least temporarily to prepare initial contribution):
			// "NumberFour",
			// "Number Four",
	};

	/** Endings of the paths (i.e. file names) that <b>can</b> contain {@link #BANNED_WORDS} */
	private static final String[] BANNED_WORDS_WHITELIST = {

			/* myself ;-) */
			AbstractFileChecker.class.getSimpleName() + ".java",
			FileChecker.class.getSimpleName() + ".java",

			/* eclipse copyrights */
			FILE_NAME__NOTICE_HTML,
			FILE_NAME__ABOUT_HTML,
			FILE_NAME__ABOUT_HTML_TEMPLATE,
			FILE_NAME__LICENSE_HTML,
			FILE_NAME__EPL,
			"epl-v10.html",
			"asl-v20.txt",

			/* open source copyrights */
			"LICENSE",
			"LICENSE.md",
			"license",
			"license.adoc",
			"licenses.adoc",
			"README.md",
			"README.adoc",
			"readme.md",
			"package.json",

			/* N4JS documentation specific */
			"n4js/docs/eu.numberfour.n4js.doc/src/userguides/index.adoc",
			"n4js/docs/eu.numberfour.n4js.spec/N4JSSpec.adoc",
			"docs/index.html",
			"acronyms.adoc",

			/* our documentation includes some files with their own licenses */
			"scripts/highlight.min.js",
			"styles/adoc-readthedocs.css",
			"styles/foundation.css",
			"styles/foundation.css",

			/* markdown, license at the bottom */
			"n4js-libraries/eu.numberfour.mangelhaft.reporter.ide/messages.md",

			/* shell scripts, shebang before license */
			"n4js-libraries/n4js-node/src/js/n4js-cli.js",
			"plugins/eu.numberfour.n4js.runner/res/ide-nodejs-env/n4js-node/n4js-cli.js",
			"plugins/eu.numberfour.n4js.external.libraries/runtime/n4js-node/src/js/n4js-cli.js",
			"n4js-libraries/n4js-node-mangelhaft/src/js/n4js-mangelhaft-cli.js",
			"plugins/eu.numberfour.n4js.runner/res/ide-nodejs-env/n4js-node-mangelhaft/n4js-mangelhaft-cli.js",
			"plugins/eu.numberfour.n4js.external.libraries/runtime/n4js-node-mangelhaft/src/js/n4js-mangelhaft-cli.js",
			"n4js-libraries/n4js-node-mangelhaft/tests/npm-test.sh",
			"plugins/eu.numberfour.n4js.external.libraries/runtime/n4js-node-mangelhaft/tests/npm-test.sh",

			/* templates */
			"plugins/eu.numberfour.n4js.external.libraries/src/eu/numberfour/n4js/external/libraries/PackageJson.java",
			"plugins/eu.numberfour.n4js.external.libraries/src/eu/numberfour/n4js/external/libraries/TargetPlatformFactory.xtend",
			"plugins/eu.numberfour.n4js.npmexporter/src/eu/numberfour/n4js/npmexporter/PackageJsonData.java",
			"plugins/eu.numberfour.n4js.runner/schema/eu.numberfour.n4js.runner.runners.exsd",
			"plugins/eu.numberfour.n4js.utils/schema/eu.numberfour.n4js.utils.fileExtensions.exsd",
			"plugins/eu.numberfour.n4js.common.unicode/src/eu/numberfour/n4js/common/unicode/generator/UnicodeGrammarGenerator.xtend",

			/* tests */
			"tests/eu.numberfour.n4js.n4ide.spec.tests/xpect-test/Ch05_04_01_02__Organize_Imports/organize_imports/gh103/GH103.txt",
			"tests/eu.numberfour.n4js.smoke.tests/src/eu/numberfour/n4js/smoke/tests/GeneratedSmokeTestCases2.xtend",
			"tests/eu.numberfour.n4js.lang.tests/src/eu/numberfour/n4js/tests/contentassist/NodeModelTokenSourceTest.xtend",
			"tests/eu.numberfour.n4js.lang.tests/src/eu/numberfour/n4js/npmexporter/PackageJasonTemplateTest.xtend",

			/* update site */
			"releng/eu.numberfour.n4js.targetplatform/N4JS.setup",
			"releng/eu.numberfour.n4js.targetplatform/eu.numberfour.n4js.targetplatform.target",

			/* product info needs to mention license */
			"builds/eu.numberfour.n4js.product.build/eu.numberfour.ide.product.product",
	};

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

	/** JS files must start with this header (derived from {@link #COPYRIGHT_TEXT}). */
	private static final String COPYRIGHT_HEADER_JS = ("/*\n"
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

	private static final String PATTERN_FEATURE_TAG = Pattern.quote("<feature")
			+ "\\s.*"
			+ Pattern.quote("provider-name=\"%providerName\"")
			+ "\\s+"
			+ Pattern.quote("license-feature=\"org.eclipse.license\"")
			+ "\\s+"
			+ Pattern.quote("license-feature-version=\"1.0.1.v20140414-1359\"")
			+ ".*"
			+ Pattern.quote(">");

	private static final String PATTERN_COPYRIGHT_TAG = Pattern.quote("<copyright>")
			+ "\\s*"
			+ Pattern.quote(Joiner.on("\n").join(COPYRIGHT_TEXT))
			+ "\\s*"
			+ Pattern.quote("</copyright>");

	private static final String PATTERN_LICENSE_TAG = Pattern.quote("<license url=\"%licenseURL\">")
			+ "\\s*"
			+ Pattern.quote("%license")
			+ "\\s*"
			+ Pattern.quote("</license>");

	private static final Pattern PATTERN_FEATURE_TAG_COMPILED = Pattern.compile(PATTERN_FEATURE_TAG, Pattern.DOTALL);
	private static final Pattern PATTERN_COPYRIGHT_TAG_COMPILED = Pattern.compile(PATTERN_COPYRIGHT_TAG);
	private static final Pattern PATTERN_LICENSE_TAG_COMPILED = Pattern.compile(PATTERN_LICENSE_TAG);

	// ################################################################################################################

	@Override
	protected boolean isIgnored(Path path, String pathStr) {
		if (path.endsWith("pom.xml"))
			return false; // never ignore pom.xml!
		if (isFile(pathStr, IGNORED_FILES))
			return true; // ignore ignored files
		if (isBelowFolder(pathStr, IGNORED_FOLDERS))
			return true; // ignore files in ignored folders
		if (hasExtension(path, ".prefs"))
			return true; // ignore Eclipse preferences
		if (hasExtension(path, ".bib"))
			return true; // ignore BibTeX files
		return false;
	}

	// ################################################################################################################

	/**
	 * Invoked for every file for which {@link #isIgnored(Path, String)} returns <code>false</code>.
	 */
	@Override
	protected void checkFile(Path path, String content, boolean isRegisteredAsThirdParty, Report report) {

		if (!isRegisteredAsThirdParty) {
			if (path.endsWith(FILE_NAME__PLUGIN_PROPERTIES) || path.endsWith(FILE_NAME__FEATURE_PROPERTIES)) {
				checkFilePluginOrFeatureProperties(path, content, report);
			} else if (path.endsWith(FILE_NAME__MANIFEST_MF)) {
				checkFileManifestMF(path, content, report);
			} else if (path.endsWith(FILE_NAME__FEATURE_XML)) {
				checkFileFeatureXML(path, content, report);
			}
		}

		if (hasExtension(path, ".xml")) { // FIXME apply ordinary checks to .xml files (by removing this special case)

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

			if (!isRegisteredAsThirdParty && !inN4Repo(path) && !canContainBannedWord(path)) {
				final String bannedWord = containsBannedWord(path, content);
				if (bannedWord != null) {
					report.problems.add("must not contain banned word '" + bannedWord + "'");
				}
			}
		}
	}

	private void checkFilePluginOrFeatureProperties(Path path, String content, Report report) {
		final String kind = path.getFileName().toString().startsWith("feature.") ? "feature" : "plugin";
		final String pluginName = path.getName(path.getNameCount() - 2).toString();
		final String providerName = inN4Repo(path) ? PROVIDER_NAME_N4 : PROVIDER_NAME;
		if (!content.contains(kind + "Name = " + pluginName)) {
			report.problems.add("property " + kind + "Name missing or value != name of containing folder");
		}
		if (!content.contains("providerName = " + providerName)) {
			report.problems.add("property providerName missing or does not have value \"" + providerName + "\"");
		}
	}

	private void checkFileManifestMF(Path path, String content, Report report) {
		final String bundleSymbolicName = path.getName(path.getNameCount() - 3).toString();
		final String bundleSymbolicNamePropertyAndValue = "Bundle-SymbolicName: " + bundleSymbolicName;
		if (!content.contains(bundleSymbolicNamePropertyAndValue + "\n")
				&& !content.contains(bundleSymbolicNamePropertyAndValue + ";")) {
			report.problems.add("property 'Bundle-SymbolicName' missing or has incorrect value");
		}
		if (!content.contains("Bundle-Name: %pluginName")) {
			report.problems.add("property 'Bundle-Name' missing or does not have value \"%pluginName\"");
		}
		if (!content.contains("Bundle-Vendor: %providerName")) {
			report.problems.add("property 'Bundle-Vendor' missing or does not have value \"%providerName\"");
		}
	}

	/** Check some required tags in feature.xml files and their values. */
	private void checkFileFeatureXML(Path path, String content, Report report) {
		if (inN4Repo(path)) {
			return; // don't check this in N4 repo
		}
		if (!containsPattern(content, PATTERN_FEATURE_TAG_COMPILED)) {
			report.problems.add("tag 'feature' missing or attributes 'provider-name', 'license-feature' are incorrect");
		}
		if (!containsPattern(content, PATTERN_COPYRIGHT_TAG_COMPILED)) {
			report.problems.add("tag 'copyright' missing or has an incorrect value");
		}
		if (!containsPattern(content, PATTERN_LICENSE_TAG_COMPILED)) {
			report.problems.add("tag 'license' missing or has an incorrect value");
		}
	}

	// ################################################################################################################

	/**
	 * Invoked for every folder for which {@link #isIgnored(Path, String)} returns <code>false</code>.
	 */
	@Override
	protected void checkFolder(Path path, int depth, Report report) {
		if (depth == 0) {
			checkFolderRepositoryRoot(path, report);
		} else if (depth == 2 && !isBelowFolder(path.toString(), "n4js/n4js-libraries")) {
			checkFolderBundleRoot(path, report);
		}
	}

	/** See Section 4.1 "Software User Agreement" at https://www.eclipse.org/legal/guidetolegaldoc.php */
	private void checkFolderRepositoryRoot(Path path, Report report) {
		if (inN4Repo(path)) {
			return; // don't check this in N4 repo
		}
		assertContainsFileWithName(path, FILE_NAME__NOTICE_HTML, report);
		assertContainsFileWithName(path, FILE_NAME__EPL, report);
	}

	private void checkFolderBundleRoot(Path path, Report report) {

		if (!containsFileWithName(path, FILE_NAME__DOT_PROJECT)) {
			report.problems.add("folder on level 2 does not contain an Eclipse '.project' file");
		}

		if (inN4Repo(path)) {
			if (isBelowFolder(path.toString(), "features")) {
				// feature bundles
				// nothing to check here
			} else {
				// all other bundles
				assertContainsFileWithName(path, FILE_NAME__PLUGIN_PROPERTIES, report);
			}
		} else {
			if (isBelowFolder(path.toString(), "features")) {
				// feature bundles
				// See Section 4.3 Features Licenses and Feature Update Licenses
				// at https://www.eclipse.org/legal/guidetolegaldoc.php
				//
				// NOTE: we do not use "license.html" files; instead we use the 'license-feature' attributes of the
				// 'feature' tag in the "feature.xml" file (checked in #checkFileFeatureXML())
				if (containsFileWithName(path, FILE_NAME__LICENSE_HTML)) {
					report.problems.add("feature bundles should not contain a '" + FILE_NAME__LICENSE_HTML
							+ "' file (because we are using property license-feature in feature.xml)");
				}
				if (containsFileWithName(path, FILE_NAME__ABOUT_HTML)) {
					report.problems.add("feature bundles should not contain an '" + FILE_NAME__ABOUT_HTML + "' file");
				}
				assertContainsFileWithName(path, FILE_NAME__FEATURE_PROPERTIES, report);
			} else {
				// all other bundles
				// See https://www.eclipse.org/legal/guidetolegaldoc.php#Abouts
				assertContainsFileWithName(path, FILE_NAME__ABOUT_HTML, report);
				assertContainsFileWithName(path, FILE_NAME__PLUGIN_PROPERTIES, report);
			}
		}
	}

	// ################################################################################################################
	// Utility Methods

	private static boolean hasCorrectCopyrightHeader(Path path, String content) {
		return beginIndexWithoutCopyrightHeader(path, content) > 0;
	}

	private void assertContainsFileWithName(Path path, String fileName, Report report) {
		if (!containsFileWithName(path, fileName)) {
			report.problems.add("folder missing required file: " + fileName);
		}
	}

	private static int beginIndexWithoutCopyrightHeader(Path path, String content) {
		if (hasExtension(path, ".xml")) {
			return startsWithCopyrightHeader(content, COPYRIGHT_HEADER_XML) ? COPYRIGHT_HEADER_XML.length() : 0;
		} else if (hasExtension(path, ".adoc")) {
			return startsWithCopyrightHeader(content, COPYRIGHT_HEADER_ADOC) ? COPYRIGHT_HEADER_ADOC.length() : 0;
		} else if (hasExtension(path, ".js") || hasExtension(path, ".n4js") || hasExtension(path, ".n4jsd")
				|| hasExtension(path, ".n4mf")) {
			return startsWithCopyrightHeader(content, COPYRIGHT_HEADER_JS) ? COPYRIGHT_HEADER_JS.length() : 0;
		} else {
			return startsWithCopyrightHeader(content, COPYRIGHT_HEADER) ? COPYRIGHT_HEADER.length() : 0;
		}
	}

	private static boolean startsWithCopyrightHeader(String content, String header) {
		return content.startsWith(header) || content.startsWith(header.replace(" 2016 ", " 2017 ")); // FIXME
	}

	private static boolean canContainBannedWord(Path path) {
		for (String whitelisted : BANNED_WORDS_WHITELIST) {
			if (path.endsWith(whitelisted)) {
				return true;
			}
		}
		return false;
	}

	private static String containsBannedWord(Path path, String content) {
		return containsWord(path, content, true, BANNED_WORDS);
	}

	private static String containsWord(Path path, String content, boolean skipCopyrightHeader, String... words) {
		if (skipCopyrightHeader) {
			final int beginIndex = beginIndexWithoutCopyrightHeader(path, content);
			content = content.substring(beginIndex);
			if (hasExtension(path, ".xcore")) {
				// FIXME consider cleaning this up
				content = content.replace("copyrightFields=\"false\",", "");
				content = content.replace(COPYRIGHT_GEN_MODEL_PROPERTY, "");
				content = content.replace(COPYRIGHT_GEN_MODEL_PROPERTY.replace(" 2016 ", " 2017 "), ""); // FIXME
			}
		}
		for (String word : words) {
			if (content.contains(word) || content.contains(word.toLowerCase()) // FIXME use containsIgnoreCase()
					|| content.contains(word.toUpperCase())) {
				return word;
			}
		}
		return null;
	}

	// ################################################################################################################

	/** Main method. */
	public static void main(String[] args) {
		final Path[] repoPaths = findRepoPaths(args);
		final boolean success = new FileChecker().run(repoPaths);
		System.exit(success ? 0 : 1);
	}
}
