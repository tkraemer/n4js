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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

/**
 * Test for regular expression handling in replacements for generated code.
 */
public class PlaceholderReplacerTest {

	@Test
	public void testNamedGroupSimple() {
		assertReplacement("-AmB-", "-XmY-", "X##(?<a1>\\w+)##Y", "A##${a1}##B");
	}

	@Test
	public void testNamedGroupWithSuroundings() {
		assertReplacement("(?<a1>\\w+)-AmB-(?<a1>\\w+)", "(?<a1>\\w+)-XmY-(?<a1>\\w+)", "X##(?<a1>\\w+)##Y",
				"A##${a1}##B");
	}

	@Test
	public void testRepeatedNamedGroup() {
		assertReplacement("-AmBmC-", "-XmYmZ-", "X##(?<a1>\\w+)##Y##\\k<a1>##Z", "A##${a1}##B##${a1}##C");
		assertNotFound("-XmYnZ-", "X##(?<a1>\\w+)##Y##\\k<a1>##Z");
	}

	@Test
	public void testRealWorld1() throws IOException {
		String testBase = Replacements.class.getPackage().getName().replace(".", "/");
		String in = Resources.toString(Resources.getResource(testBase + "/" + "replacerIn.txt"), Charsets.UTF_8);
		String repPattern = Resources.toString(Resources.getResource(testBase + "/" + "simplePattern.txt"),
				Charsets.UTF_8);
		PlaceholderReplacer pr = new PlaceholderReplacer(repPattern, "");
		Pattern pattern = pr.searchPattern;
		Matcher matcher = pattern.matcher(in);
		assertTrue("Pattern not found", matcher.find());
	}

	// Only used for quick test after modifying the replacements
	// @Test
	public void testReplacementSyntax() throws IOException {
		new PlaceholderReplacer("ruleSemi.g.replacement");
		new PlaceholderReplacer("ruleNoLineTerminator.java.replacement");
		new PlaceholderReplacer("rulePrimaryExpression.java.replacement");
	}

	void assertReplacement(String expectedOutput, String in, String pattern, String replacement) {
		PlaceholderReplacer pr = new PlaceholderReplacer(pattern, replacement);
		String actualOutput = pr.replaceExactlyOnce(in);
		assertEquals(expectedOutput, actualOutput);
	}

	void assertNotFound(String in, String pattern) {
		PlaceholderReplacer pr = new PlaceholderReplacer(pattern, "");

		try {
			String actualOutput = pr.replaceExactlyOnce(in);
			fail("Pattern '" + pattern + "' was found, did not expected that.");
		} catch (IllegalStateException ex) {
			assertTrue(ex.getMessage().startsWith("Pattern not found!"));
		}

	}

}
