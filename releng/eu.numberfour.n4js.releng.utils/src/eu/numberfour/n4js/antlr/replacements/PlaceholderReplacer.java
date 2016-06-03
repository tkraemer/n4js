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
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

/**
 * Utility class to translate search-and-replace patterns into regex and apply them. Both 'search' and 'replace' strings
 * can contain place-holders. Place-holders are identifier surrounded by double asterisks, e.g. **al1**.
 *
 * Working with place-holders instead of using RegEx directly provides the advantage that larger 'search' and 'replace'
 * strings do not need to be RegEx-escaped manually.
 *
 * @author Moritz Eysholdt
 */
public class PlaceholderReplacer {

	/**
	 * Separator in *.template files.
	 */
	private static final String SEPARATOR = "\n_________________________________REPLACE_WITH_________________________________\n";

	/**
	 * Syntax for place-holders as defined in *.template files.
	 */
	private final static Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\*\\*([a-zA-Z0-9]+)\\*\\*");

	private final Pattern searchPattern;
	private final String replacement;
	private final String source;

	/**
	 * A lightweight way to try out this class.
	 */
	public static void main(String[] args) {
		try {
			// expected: -AmB-
			System.out.println(new PlaceholderReplacer("X**a1**Y", "A**a1**B").replaceExactlyOnce("-XmY-"));

			System.out.println(new PlaceholderReplacer("rulePrimaryExpression.java.replacement"));
			System.out.println(new PlaceholderReplacer("ruleNoLineTerminator.java.replacement"));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * Load 'search' and 'replace' strings from a file from this class' directory.
	 */
	public PlaceholderReplacer(String replacementName) throws IOException {
		String replacementBase = Replacements.class.getPackage().getName().replace(".", "/");
		this.source = replacementBase + "/" + replacementName;
		URL url = Replacements.class.getClassLoader().getResource(source);
		if (url == null) {
			throw new NullPointerException("cannot replacement :: " + source);
		}
		// normalize string to run on Windows and Mac/Unix
		String fileContents = Resources.toString(url, Charsets.UTF_8).replace("\r\n", "\n");
		String[] split = fileContents.split(SEPARATOR);
		String searchString = split[0];
		String replacementString = split[1];
		Map<String, Integer> placeholderNameToIndex = Maps.newHashMap();
		this.searchPattern = createPattern(searchString, placeholderNameToIndex);
		this.replacement = createReplace(replacementString, placeholderNameToIndex);

	}

	/**
	 * A lightweight way to try this class without the need to create files.
	 */
	public PlaceholderReplacer(String searchString, String replacementString) {
		this.source = "(dummy)";
		Map<String, Integer> placeholderNameToIndex = Maps.newHashMap();
		this.searchPattern = createPattern(searchString, placeholderNameToIndex);
		this.replacement = createReplace(replacementString, placeholderNameToIndex);
	}

	private Pattern createPattern(String match, Map<String, Integer> placeholderNameToIndex) {
		Matcher matcher = PLACEHOLDER_PATTERN.matcher(match);
		int lastOffset = 0;
		StringBuilder result = new StringBuilder();
		int matchGroupIndex = 1;
		while (matcher.find()) {
			result.append(Pattern.quote(match.substring(lastOffset, matcher.start())));
			result.append("([a-zA-Z0-9]+)");
			String placeholderName = matcher.group(1);
			if (!placeholderNameToIndex.containsKey(placeholderName)) {
				placeholderNameToIndex.put(placeholderName, matchGroupIndex);
			}
			matchGroupIndex++;
			lastOffset = matcher.end();
		}
		result.append(Pattern.quote(match.substring(lastOffset)));
		return Pattern.compile(result.toString());
	}

	private String createReplace(String match, Map<String, Integer> placeholderNameToIndex) {
		Matcher matcher = PLACEHOLDER_PATTERN.matcher(match);
		int lastOffset = 0;
		StringBuilder result = new StringBuilder();
		while (matcher.find()) {
			result.append(Matcher.quoteReplacement(match.substring(lastOffset, matcher.start())));
			String placeholderName = matcher.group(1);
			Integer index = placeholderNameToIndex.get(placeholderName);
			if (index == null) {
				String msg = "Named placeholder '" + placeholderName + "' does not appear in match pattern";
				throw new IllegalStateException(msg);
			}
			result.append("$" + index);
			lastOffset = matcher.end();
		}
		result.append(Matcher.quoteReplacement(match.substring(lastOffset)));
		return result.toString();
	}

	@Override
	public String toString() {
		return searchPattern + "\n" + SEPARATOR + "\n" + replacement;
	}

	/**
	 * Applies the replacement onto this string. Throws exceptions if the searchString ins being found not at all or
	 * more than once.
	 *
	 * @param original
	 *            The string on which the replacements should be applied
	 * @return The string onto which the replacements have been applied
	 */
	public String replaceExactlyOnce(String original) {
		Matcher matcher = searchPattern.matcher(original);
		if (!matcher.find()) {
			String msg = "Pattern not found!\nTemplate:" + source + "\nPattern:\n" + searchPattern;
			throw new IllegalStateException(msg);
		}
		StringBuffer sb = new StringBuffer();
		matcher.appendReplacement(sb, replacement);
		if (matcher.find()) {
			String msg = "Pattern found more than once!\nTemplate:" + source + "\nPattern:\n" + searchPattern;
			throw new IllegalStateException(msg);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

}
