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

import static eu.numberfour.n4js.hlc.IncompleteApiImplementationTest.runCaptureOut;
import static eu.numberfour.n4js.runner.SystemLoaderInfo.COMMON_JS;
import static java.util.Collections.singletonMap;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import eu.numberfour.n4js.hlc.N4jsc.ExitCodeException;
import eu.numberfour.n4js.hlc.N4jsc.Type;
import eu.numberfour.n4js.hlc.helper.N4CliHelper;

/**
 * Downloads, installs, compiles and runs 'express'.
 */
public class InstallCompileRunN4jscExternalTest extends BaseN4jscExternalTest {

	@Override
	protected Map<String, String> getNpmDependencies() {
		return singletonMap("express", "^4.13.4");
	}

	/**
	 * Test for checking the npm support in the headless case by downloading third party package, importing it and
	 * running it with Common JS.
	 */
	@Test
	public void testCompileAndRunWithExternalDependencies() throws IOException, ExitCodeException {
		System.out.println(name.getMethodName());
		setupWorkspace("external");
		final String wsRoot = TARGET + "/" + WSP;
		final String fileToRun = wsRoot + "/external.project/src/Main.n4js";

		final String[] args = {
				"--systemLoader", COMMON_JS.getId(),
				"--targetPlatformFile", getTargetPlatformFile().getAbsolutePath(),
				"--targetPlatformInstallLocation", getTargetPlatformInstallLocation().getAbsolutePath(),
				"-rw", "nodejs",
				"-r", fileToRun,
				"--debug",
				"--verbose",
				"--projectlocations", wsRoot,
				"-t", Type.allprojects.toString()
		};
		final String out = runCaptureOut(args);
		N4CliHelper.assertExpectedOutput(
				"express properties: application, request, response, Route, Router, query, static", out);
	}

}
