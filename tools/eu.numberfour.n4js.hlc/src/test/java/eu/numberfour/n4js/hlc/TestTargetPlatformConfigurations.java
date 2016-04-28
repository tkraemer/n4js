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

import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import eu.numberfour.n4js.hlc.N4jsc.ExitCodeException;
import eu.numberfour.n4js.hlc.N4jsc.Type;
import eu.numberfour.n4js.utils.io.FileDeleter;

/**
 * Downloads, installs, compiles and runs 'express'.
 */
public class TestTargetPlatformConfigurations extends BaseN4jscExternalTest {

	@Override
	protected Map<String, String> getNpmDependencies() {
		return singletonMap("express", "^4.13.4");
	}

	// ===== normal failures

	/**
	 * Test failure when compiling without target platform file.
	 */
	@Test
	public void testCompileFailsIfNoTargetPlatformFile() throws IOException {
		System.out.println(name.getMethodName());
		setupWorkspace("external_with_n4jsd_tpt");
		final String wsRoot = TARGET + "/" + WSP;

		final String[] args = {
				// "--targetPlatformFile", getTargetPlatformFile().getAbsolutePath(),
				"--targetPlatformInstallLocation", getTargetPlatformInstallLocation().getAbsolutePath(),
				"--debug",
				"--verbose",
				"--projectlocations", wsRoot,
				"-t", Type.allprojects.toString()
		};
		try {
			new N4jsc().doMain(args);
			fail("Expecting exit code: " + N4jsc.EXITCODE_CONFIGURATION_ERROR);
		} catch (final ExitCodeException e) {
			assertEquals(N4jsc.EXITCODE_CONFIGURATION_ERROR, e.getExitCode());
		}
	}

	/**
	 * Test failure when compiling without target platform file.
	 */
	@Test
	public void testCompileFailsIfNoInstallLocation() throws IOException {
		System.out.println(name.getMethodName());
		setupWorkspace("external_with_n4jsd_tpt");
		final String wsRoot = TARGET + "/" + WSP;

		final String[] args = {
				"--targetPlatformFile", getTargetPlatformFile().getAbsolutePath(),
				// "--targetPlatformInstallLocation", getTargetPlatformInstallLocation().getAbsolutePath(),
				"--debug",
				"--verbose",
				"--projectlocations", wsRoot,
				"-t", Type.allprojects.toString()
		};
		try {
			new N4jsc().doMain(args);
			fail("Expecting exit code: " + N4jsc.EXITCODE_CONFIGURATION_ERROR);
		} catch (final ExitCodeException e) {
			assertEquals(N4jsc.EXITCODE_CONFIGURATION_ERROR, e.getExitCode());
		}
	}

	// test install location management

	/**
	 * Test creating install location.
	 */
	@Test
	public void testCompileCreatesInstallLocation() throws IOException {
		System.out.println(name.getMethodName());
		setupWorkspace("external_with_n4jsd_tpt");
		final String wsRoot = TARGET + "/" + WSP;

		// force creating install location
		FileDeleter.delete(getTargetPlatformInstallLocation());

		final String[] args = {
				"--targetPlatformFile", getTargetPlatformFile().getAbsolutePath(),
				"--targetPlatformInstallLocation", getTargetPlatformInstallLocation().getAbsolutePath(),
				"--debug",
				"--verbose",
				"--projectlocations", wsRoot,
				"-t", Type.allprojects.toString()
		};
		try {
			new N4jsc().doMain(args);
		} catch (final ExitCodeException e) {
			assertTrue("install location was not created", getTargetPlatformInstallLocation().exists());
			assertEquals(N4jsc.EXITCODE_SUCCESS, e.getExitCode());
		}
	}

	/**
	 * Test cleaning install location.
	 */
	@Test
	public void testCompileCleanInstallLocation() throws IOException {
		System.out.println(name.getMethodName());
		setupWorkspace("external_with_n4jsd_tpt");
		final String wsRoot = TARGET + "/" + WSP;

		// force creating install location
		File testFile = new File(getTargetPlatformInstallLocation(), "tst.txt");
		testFile.createNewFile();
		assertTrue("setup error, test file should exist yet at " + testFile.getAbsolutePath(), testFile.exists());

		final String[] args = {
				"--targetPlatformFile", getTargetPlatformFile().getAbsolutePath(),
				"--targetPlatformInstallLocation", getTargetPlatformInstallLocation().getAbsolutePath(),
				"--debug",
				"--verbose",
				"--projectlocations", wsRoot,
				"-t", Type.allprojects.toString()
		};
		try {
			new N4jsc().doMain(args);
		} catch (final ExitCodeException e) {
			assertFalse("install location was not cleaned, test file exists at " + testFile.getAbsolutePath(),
					testFile.exists());
			assertEquals(N4jsc.EXITCODE_SUCCESS, e.getExitCode());
		}
	}

	// ===== skip installation

	/**
	 * Test skip install when compiling without target platform file.
	 */
	@Test
	public void testCompileFailsIfNoTargetPlatformFileWithSkipped() throws IOException {
		System.out.println(name.getMethodName());
		setupWorkspace("external_with_n4jsd_tpt");
		final String wsRoot = TARGET + "/" + WSP;

		final String[] args = {
				// "--targetPlatformFile", getTargetPlatformFile().getAbsolutePath(),
				"--targetPlatformInstallLocation", getTargetPlatformInstallLocation().getAbsolutePath(),
				"--debug",
				"--verbose",
				"--projectlocations", wsRoot,
				"-t", Type.allprojects.toString(),
				"--targetPlatformSkipInstall"
		};
		try {
			new N4jsc().doMain(args);
			fail("Expecting exit code: " + N4jsc.EXITCODE_CONFIGURATION_ERROR);
		} catch (final ExitCodeException e) {
			assertEquals(N4jsc.EXITCODE_COMPILE_ERROR, e.getExitCode());
		}
	}

	/**
	 * Test skip install when compiling without target platform file.
	 */
	@Test
	public void testCompileFailsIfNoInstallLocationWithSkipped() throws IOException {
		System.out.println(name.getMethodName());
		setupWorkspace("external_with_n4jsd_tpt");
		final String wsRoot = TARGET + "/" + WSP;

		final String[] args = {
				"--targetPlatformFile", getTargetPlatformFile().getAbsolutePath(),
				// "--targetPlatformInstallLocation", getTargetPlatformInstallLocation().getAbsolutePath(),
				"--debug",
				"--verbose",
				"--projectlocations", wsRoot,
				"-t", Type.allprojects.toString(),
				"--targetPlatformSkipInstall"
		};
		try {
			new N4jsc().doMain(args);
			fail("Expecting exit code: " + N4jsc.EXITCODE_CONFIGURATION_ERROR);
		} catch (final ExitCodeException e) {
			assertEquals(N4jsc.EXITCODE_COMPILE_ERROR, e.getExitCode());
		}
	}

	/**
	 * Test skip install forced
	 */
	@Test
	public void testCompileForceSkippInstall() throws IOException {
		System.out.println(name.getMethodName());
		setupWorkspace("external_with_n4jsd_tpt");
		final String wsRoot = TARGET + "/" + WSP;

		final String[] args = {
				// "--targetPlatformFile", getTargetPlatformFile().getAbsolutePath(),
				// "--targetPlatformInstallLocation", getTargetPlatformInstallLocation().getAbsolutePath(),
				"--debug",
				"--verbose",
				"--projectlocations", wsRoot,
				"-t", Type.allprojects.toString()
		};
		try {
			new N4jsc().doMain(args);
			fail("Expecting exit code: " + N4jsc.EXITCODE_CONFIGURATION_ERROR);
		} catch (final ExitCodeException e) {
			assertEquals(N4jsc.EXITCODE_COMPILE_ERROR, e.getExitCode());
		}
	}

	/**
	 * Test skip install combined with forced
	 */
	@Test
	public void testCompileSkippInstallAndForceSkipInstall() throws IOException {
		System.out.println(name.getMethodName());
		setupWorkspace("external_with_n4jsd_tpt");
		final String wsRoot = TARGET + "/" + WSP;

		final String[] args = {
				// "--targetPlatformFile", getTargetPlatformFile().getAbsolutePath(),
				// "--targetPlatformInstallLocation", getTargetPlatformInstallLocation().getAbsolutePath(),
				"--debug",
				"--verbose",
				"--projectlocations", wsRoot,
				"-t", Type.allprojects.toString(),
				"--targetPlatformSkipInstall"
		};
		try {
			new N4jsc().doMain(args);
		} catch (final ExitCodeException e) {
			assertEquals(N4jsc.EXITCODE_COMPILE_ERROR, e.getExitCode());
		}
	}

}
