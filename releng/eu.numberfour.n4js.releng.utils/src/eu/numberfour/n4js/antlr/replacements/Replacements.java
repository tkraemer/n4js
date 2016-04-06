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
package eu.numberfour.n4js.antlr.replacements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

/**
 * Replacement methods used by injectors to replace snippets in the generated grammar and lexer, and by fragments to
 * replace snippets in the generated java parser.
 */
public class Replacements {

	/**
	 * Returns the simple name of the top level class defined in the given grammar content.
	 */
	public static String getGrammarClass(String grammarContent) {
		Matcher m = Pattern.compile("public\\ class\\ ([a-zA-Z0-9_]+)\\ ").matcher(grammarContent);
		if (m.find()) {
			return m.group(1);
		}
		return "unknown grammar class";
	}

	/**
	 * Replaces all matches of the given pattern in the given input string. If no match was found, an
	 * {@link IllegalStateException} is raised.
	 *
	 * @see String#replaceAll(String, String)
	 */
	public static String replaceAll(String in, String pattern, String replacement) {
		String result = in.replaceAll(pattern, replacement);
		if (result.equals(in)) {
			Thread.dumpStack();
			throw new IllegalStateException("Did not find '" + pattern + "' in grammar");
		}
		return result;
	}

	/**
	 * Replaces all matches of the given pattern in the given input string. If no match was found, an
	 * {@link IllegalStateException} is raised.
	 *
	 * @see String#replaceFirst(String, String)
	 */
	public static String replaceFirst(String in, String pattern, String replacement) {
		String result = in.replaceFirst(pattern, replacement);
		if (result.equals(in)) {
			Thread.dumpStack();
			throw new IllegalStateException("Did not find '" + pattern + "' in grammar");
		}
		return result;
	}

	/**
	 * Replaces all matches of the given lookup in the given input string. If no match was found, an
	 * {@link IllegalStateException} is raised.
	 *
	 * @see String#replace(CharSequence, CharSequence)
	 */
	public static String replace(String in, String lookup, String replacement) {
		String result = in.replace(lookup, replacement);
		if (result.equals(in)) {
			Thread.dumpStack();
			throw new IllegalStateException("Did not find '" + lookup + "' in grammar");
		}
		return result;
	}

	/**
	 * Applies a replacement, which is a string containing both the lookup (non-regex) and the replacement.These two
	 * strings are separated via
	 *
	 * <pre>
	 * _________________________________REPLACE_WITH_________________________________
	 * </pre>
	 *
	 * The replacement file has to be in the same folder as this class.
	 */
	public static String applyReplacement(String in, String replacementName) {
		try {
			String replacementBase = Replacements.class.getPackage().getName().replace(".", "/");
			String replacementResourceName = replacementBase + "/" + replacementName;
			URL url = Replacements.class.getClassLoader().getResource(replacementResourceName);
			if (url == null) {
				throw new NullPointerException(
						"cannot replacement :: " + replacementResourceName);
			}
			// normalize string to run on Windows and Mac/Unix
			String replacement = Resources.toString(url, Charsets.UTF_8).replace("\r\n", "\n");

			String[] findReplace = replacement
					.split("\n_________________________________REPLACE_WITH_________________________________\n");

			return replace(in, findReplace[0], findReplace[1]);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
