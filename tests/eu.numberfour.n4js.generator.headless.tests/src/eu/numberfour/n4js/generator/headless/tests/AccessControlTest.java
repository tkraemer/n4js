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
package eu.numberfour.n4js.generator.headless.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.xtext.validation.Issue;
import org.junit.Test;

import eu.numberfour.n4js.generator.headless.IssueCollector;
import eu.numberfour.n4js.generator.headless.N4HeadlessCompiler;
import eu.numberfour.n4js.generator.headless.N4JSCompileException;
import eu.numberfour.n4js.hlc.AbstractN4jscTest;
import eu.numberfour.n4js.issues.IssuesMatcher;

/**
 * Prototypical test for access control testing. This test only covers some examples in order to explore how to connect
 * test expectations with the actual compiler errors as reported by the headless compiler.
 */
public class AccessControlTest extends AbstractN4jscTest {

	/**
	 * Test overriding a protected field of a public class within a subclass in the same module. Expects no errors.
	 */
	@Test
	public void testSameModuleExtendsOverrideProtectedFieldOfPublicClass() {
		IssuesMatcher matcher = new IssuesMatcher();
		compileAndAssert("SameModuleExtendsOverrideProtectedFieldOfPublicClass", matcher);
	}

	/**
	 * Test overriding a private field of a public class within a subclass in the same project. Expects an error.
	 */
	@Test
	public void testSameProjectExtendsOverridePrivateFieldOfPublicClass() {
		IssuesMatcher matcher = new IssuesMatcher();
		matcher.add().message("The field B.field cannot override private field A.field.").at("B.n4js", 5, 10);

		compileAndAssert("SameProjectExtendsOverridePrivateFieldOfPublicClass", matcher);
	}

	/**
	 * Test overriding a private field of a public class within a subclass of the same vendor. Expects an error.
	 */
	@Test
	public void testSameVendorExtendsOverridePrivateFieldOfPublicClass() {
		IssuesMatcher matcher = new IssuesMatcher();
		matcher.add().message("The field B.field cannot override private field A.field.").at("B.n4js", 5, 10);

		compileAndAssert("SameVendorExtendsOverridePrivateFieldOfPublicClass", matcher);
	}

	/**
	 * Test multiple issues.
	 */
	@Test
	public void testMultipleIssues() {
		IssuesMatcher matcher = new IssuesMatcher();
		matcher.add().message("The field B.field cannot override private field A.field.").at("A.n4js", 8, 10);
		matcher.add().message("The method B.m cannot override private method A.m.").at("A.n4js", 11, 10);

		compileAndAssert("MultipleIssues", matcher);
	}

	private static String FIXTURE_ROOT = "testdata/accesscontrol";

	private static void assertIssues(Collection<Issue> issues, IssuesMatcher matchers) {
		assertTrue(matchers.matchesExactly(issues));
	}

	private void compileAndAssert(String projectRoot, IssuesMatcher matcher) {
		try {
			File root = setupWorkspace(FIXTURE_ROOT, projectRoot);
			List<File> projectRoots = Arrays.asList(root);

			IssueCollector issueCollector = new IssueCollector();
			try {
				// compile
				N4HeadlessCompiler hlc = N4HeadlessCompiler.injectAndSetup(null);
				hlc.compileAllProjects(projectRoots, issueCollector);
			} catch (N4JSCompileException e) {
				// nothing to do
			}

			assertIssues(issueCollector.getCollectedIssues(), matcher);
		} catch (IOException e) {
			fail("Unable to setup workspace: " + projectRoot);
		}
	}
}
