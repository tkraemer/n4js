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
package eu.numberfour.n4js.antlr.internal;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

import eu.numberfour.n4js.antlr.generator.NewlineNormalizer;

/**
 */
public class AntlrCodeQualityHelper {
	private final static Logger LOGGER = Logger.getLogger(AntlrCodeQualityHelper.class);

	/**
	 * Remove all unnecessary comments from the java code that was produced by Antlr
	 */
	public static void stripUnnecessaryComments(String javaFile, Charset encoding) throws IOException {
		LOGGER.debug("### stripUnnecessaryComments from " + javaFile);
		String content = Files.toString(new File(javaFile), encoding);
		content = new NewlineNormalizer().toUnixLineDelimiter(content);
		content = content.replaceAll(
				"(?m)^(\\s+)// .*/(\\w+\\.g:.*)$",
				"$1// $2");
		content = content.replaceAll(
				"(public String getGrammarFileName\\(\\) \\{ return \").*/(\\w+\\.g)(\"; \\})",
				"$1$2$3");
		Files.write(content, new File(javaFile), encoding);
	}

	/**
	 * Remove all unnecessary comments from the lexer and the parser
	 */
	public static void stripUnnecessaryComments(String lexer, String parser, Charset encoding) {
		try {
			LOGGER.debug("### stripUnnecessaryComments from " + lexer + ", " + parser);
			stripUnnecessaryComments(lexer, encoding);
			stripUnnecessaryComments(parser, encoding);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// public static final BitSet FOLLOW_Tilde_in_synpred90_InternalN4JSParser17129 = new BitSet(new
	// long[]{0x0000000000000002L});
	// ...........................1- ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 2-
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	private static final Pattern bitsetPattern = Pattern.compile(
			"^\\s+public static final BitSet (FOLLOW_\\w+) = (.*);$", Pattern.MULTILINE);

	/**
	 * Remove duplicate bitset declarations to reduce the size of the static initializer but keep the bitset
	 * FOLLOW_ruleExpression_in_ruleExpressionStatement with a simplified name.
	 */
	public static void removeDuplicateBitsets(String javaFile, Charset encoding) {
		try {
			//
			String content = Files.toString(new File(javaFile), encoding);
			StringBuilder newContent = new StringBuilder(content.length());
			Matcher matcher = bitsetPattern.matcher(content);
			int offset = 0;
			Map<String, String> bitsets = Maps.newHashMap();
			Map<String, String> namesToReplace = Maps.newHashMap();
			while (matcher.find(offset)) {
				String originalFieldName = matcher.group(1);
				String synthesizedFieldName = "FOLLOW_" + (bitsets.size() + 1);
				String bitset = matcher.group(2);
				String existing = bitsets.putIfAbsent(bitset, synthesizedFieldName);
				if (existing == null) {
					existing = synthesizedFieldName;
					newContent.append(content, offset, matcher.start(1));
					newContent.append(synthesizedFieldName);
					newContent.append(" = ");
					newContent.append(bitset);
				}
				namesToReplace.put(originalFieldName, existing);
				if (originalFieldName.startsWith("FOLLOW_ruleExpression_in_ruleExpressionStatement")) {
					newContent.append(content, offset, matcher.start(1));
					newContent.append("FOLLOW_ruleExpression_in_ruleExpressionStatement = ");
					newContent.append(existing);
				}
				offset = matcher.end(2);
			}
			newContent.append(content, offset, content.length());
			content = newContent.toString();
			newContent = new StringBuilder(content.length());
			String rawFollowPattern = "\\bFOLLOW_\\w+\\b";
			Pattern followPattern = Pattern.compile(rawFollowPattern);
			Matcher followMatcher = followPattern.matcher(content);
			Set<String> doNotReplace = Sets.newHashSet(bitsets.values());
			doNotReplace.add("FOLLOW_ruleExpression_in_ruleExpressionStatement");
			offset = 0;
			while (followMatcher.find(offset)) {
				String replaceMe = followMatcher.group();
				String replaceBy = namesToReplace.get(replaceMe);
				if (replaceBy == null) {
					if (!doNotReplace.contains(replaceMe))
						throw new IllegalStateException(replaceMe);
					replaceBy = replaceMe;
				}
				newContent.append(content, offset, followMatcher.start());
				newContent.append(replaceBy);
				offset = followMatcher.end();
			}
			newContent.append(content, offset, content.length());
			Files.write(newContent, new File(javaFile), encoding);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
