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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import eu.numberfour.n4js.hlc.N4jsc.ExitCodeException;

/**
 * Basic tests for N4jsc, like checking command line options or simple compile.
 */
public class N4jscBasicTest extends AbstractN4jscTest {

	/**
	 * Prepare tests.
	 */
	@Before
	public void setupWorkspace() throws IOException {
		setupWorkspace(TEST_DATA_SET__BASIC);
	}

	/**
	 * normal compile all test, due to error in dependency will break the build with exitcode == 2
	 */
	@Test(expected = ExitCodeException.class)
	public void testMainArgsCompileAll() throws ExitCodeException {
		System.out.println(logMethodname());
		System.out.println("just for reference base-path is: " + new File(".").getAbsolutePath());

		String proot = TARGET + "/" + WSP;

		String[] args = { "-pl", proot, "-t", "allprojects" };

		try {
			new N4jsc().doMain(args);
		} catch (ExitCodeException e) {
			assertEquals("Wrong exit code", N4jsc.EXITCODE_COMPILE_ERROR, e.getExitCode());
			throw e;
		}
	}

	/**
	 * normal compile all test with flag "--keepCompiling"
	 */
	@Test
	public void testMainArgsCompileAllKeepCompiling() {
		System.out.println(logMethodname());
		System.out.println("just for reference base-path is: " + new File(".").getAbsolutePath());

		String proot = TARGET + "/" + WSP;

		String[] args = { "-pl", proot, "-t", "allprojects" };

		try {
			new N4jsc().doMain(args);
			assertFalse("Line should not have been reached, ExitCodeException expected.", true);
		} catch (ExitCodeException e) {
			assertEquals(N4jsc.EXITCODE_COMPILE_ERROR, e.getExitCode());
		}
		// Assert that 13 files are compiled.
		assertFilesCompiledToES(13, proot);
	}

	/**
	 * normal compile test
	 */
	@Test
	public void testMainArgsProjectRoot() throws ExitCodeException {
		System.out.println(logMethodname());
		System.out.println("just for reference base-path is: " + new File(".").getAbsolutePath());

		String proot = TARGET + "/" + WSP;

		// absolute filename
		String file1 = proot + "/" + "PSingle/src/a/A.n4js";

		String[] args = { "-pl", proot, file1 };

		new N4jsc().doMain(args);

	}

	/**
	 * test missing parameter-operand for projectroot, expecting Exception
	 */
	@Test(expected = N4jsc.ExitCodeException.class)
	public void testMainArgsProjectRoot_broken() throws ExitCodeException {
		System.out.println(logMethodname());

		String[] args = { "-pl" };

		new N4jsc().doMain(args);

	}

	/**
	 * Test successful exit, Exception is expected but with Error-Code 0
	 */
	@Test
	public void testMainHelp() {
		System.out.println(logMethodname());
		String[] args = { "-h" };
		try {
			new N4jsc().doMain(args);
			assertTrue("Should have printed help and exited before", false);
		} catch (ExitCodeException e) {
			assertEquals("Wrong exit code.", N4jsc.EXITCODE_SUCCESS, e.getExitCode());
		}
	}

	/**
	 * Test debug output before help. This test doesn't run a compile but because of the "-help" option should
	 */
	@Test
	public void testMainDebugHelp() {
		System.out.println(logMethodname());
		String[] args = { "-h", "--debug", "--preferences", "xxx", "-t", "allprojects" }; // , "more1", "more2", "more3"
		// };
		try {
			new N4jsc().doMain(args);
			assertTrue("Should have printed help and exited before", false);
		} catch (ExitCodeException e) {
			assertEquals("Wrong exit code (not 0).", N4jsc.EXITCODE_SUCCESS, e.getExitCode());
		}
	}

	/**
	 * Simple test of compiling a project and running a class from that compiled code with NODEJS.
	 *
	 * @throws ExitCodeException
	 *             in error cases ( not expected )
	 */
	@Test
	public void testCompileP1_And_Run_A_WithNodeRunner() throws ExitCodeException {
		System.out.println(logMethodname());

		String proot = TARGET + "/" + WSP;

		// Project
		String projectP1 = "P1";
		String pathToP1 = proot + "/" + projectP1;

		// absolute src filename
		String fileA = proot + "/" + projectP1 + "/src/A.n4js";

		String[] args = { "-pl", proot,
				"-t", "projects", pathToP1,
				"-rw", "nodejs",
				"-r", fileA,
				"-v"
		};

		new N4jsc().doMain(args);
	}

	/**
	 * Trying to run an uncompiled module: should result in a failure
	 *
	 * @throws ExitCodeException
	 *             expected
	 */
	@Test(expected = ExitCodeException.class)
	public void test_Run_Not_Compiled_A_WithNodeRunner() throws ExitCodeException {
		System.out.println(logMethodname());

		String proot = TARGET + "/" + WSP;

		// Project
		String projectP1 = "P1";
		String pathToP1 = proot + "/" + projectP1;

		// absolute src filename
		String fileA = pathToP1 + "/src/A.n4js";

		String[] args = { "-pl", proot,
				"-t", "dontcompile",
				"-rw", "nodejs",
				"-r", fileA,
				"-v"
		};

		try {
			new N4jsc().doMain(args);
		} catch (ExitCodeException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			// check the expected exit code of 7:
			assertEquals("Exit with wrong exitcode.", N4jsc.EXITCODE_RUNNER_STOPPED_WITH_ERROR, e.getExitCode());
			throw e;
		}
	}
}
