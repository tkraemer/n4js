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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import eu.numberfour.n4js.generator.headless.IssueMatcher;
import eu.numberfour.n4js.generator.headless.N4HeadlessCompiler;
import eu.numberfour.n4js.generator.headless.N4JSCompileException;
import eu.numberfour.n4js.hlc.N4jsc.ExitCodeException;

/**
 * Prototypical test for access control testing. This test only covers some examples in order to explore how to connect
 * test expectations with the actual compiler errors as reported by the headless compiler.
 */
public class AccessControlTest extends AbstractN4jscTest {

	/**
	 * Test overriding a protected field of a public class within a subclass in the same module. Expects no errors.
	 */
	@Test
	public void testSameModuleExtendsOverrideProtectedFieldOfPublicClass() throws IOException {
		setupWorkspace("access-control/SameModuleExtendsOverrideProtectedFieldOfPublicClass");

		String proot = TARGET + "/" + WSP;

		String[] args = { "-pl", proot,
				"-t", "allprojects",
				"-v"
		};

		try {
			// compile
			new N4jsc().doMain(args);
		} catch (ExitCodeException e) {
			fail("Unexpected exit code exception: " + e.getMessage());
		}
	}

	private static void assertHasIssue(N4JSCompileException e, IssueMatcher matcher) {
		assertTrue(e.hasExactly(matcher));
	}

	/**
	 * Test overriding a private field of a public class within a subclass in the same project. Expects an error.
	 */
	@Test
	public void testSameProjectExtendsOverridePrivateFieldOfPublicClass() throws IOException {
		File root = setupWorkspace("access-control/SameProjectExtendsOverridePrivateFieldOfPublicClass");
		List<File> pProjectRoots = Arrays.asList(root);

		try {
			// compile
			N4HeadlessCompiler hlc = N4HeadlessCompiler.injectAndSetup(null);
			hlc.compileAllProjects(pProjectRoots);
			fail("Expected compilation errors.");
		} catch (N4JSCompileException e) {
			assertHasIssue(e, new IssueMatcher()
					.message("The field B.field cannot override private field A.field.").at(5, 10, "B.n4js"));
		}
	}
}
