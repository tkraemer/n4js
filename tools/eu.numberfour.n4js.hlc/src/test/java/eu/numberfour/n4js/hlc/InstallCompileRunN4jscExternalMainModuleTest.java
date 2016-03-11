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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import eu.numberfour.n4js.hlc.N4jsc.ExitCodeException;
import eu.numberfour.n4js.hlc.N4jsc.Type;
import eu.numberfour.n4js.hlc.helper.N4CliHelper;

/**
 * Downloads, installs, compiles and runs several packages that are known to be problematic in terms of how they define
 * main module.
 */
public class InstallCompileRunN4jscExternalMainModuleTest extends BaseN4jscExternalTest {

	@Override
	protected Map<String, String> getNpmDependencies() {
		Map<String, String> deps = new HashMap<>();
		// main is "./lib/bar", but there is lib folder and lib.js file
		deps.put("bar", "^0.1.2");
		// no main
		deps.put("body-parser", "^1.15.0");
		// main is "index.js"
		deps.put("express", "^4.13.4");
		// main is "lib", there is index.js in lib folder
		deps.put("jade", "^1.11.0");
		// main is "./lib/index"
		deps.put("karma", "^0.13.21");
		// main is "lodash.js"
		deps.put("lodash", "^4.6.0");

		return deps;
	}

	/**
	 * Test for checking the npm support in the headless case by downloading third party package, importing it and
	 * running it with Common JS.
	 */
	@Ignore("Disabled test due to insufficient jenkins user privileges to checkout n4jsd-sandbox repository for N4JS definitions files.")
	@Test
	public void testCompileAndRunWithExternalDependencies() throws IOException, ExitCodeException {
		System.out.println(name.getMethodName());
		setupWorkspace("externalmm");
		final String wsRoot = TARGET + "/" + WSP;
		final String fileToRun = wsRoot + "/external.project.mm/src/Main.n4js";

		final String[] args = {
				"--projectlocations", wsRoot,
				"-t", Type.allprojects.toString(),
				"--systemLoader", COMMON_JS.getId(),
				"--targetPlatformFile", getTargetPlatformFile().getAbsolutePath(),
				"--targetPlatformInstallLocation", getTargetPlatformInstallLocation().getAbsolutePath(),
				"-rw", "nodejs",
				"-r", fileToRun,
				"--debug",
				"--verbose"
		};
		final String out = runCaptureOut(args);
		StringBuilder message = new StringBuilder();
		message
				.append("express imported").append("\n")
				.append("jade imported").append("\n")
				.append("lodash imported").append("\n")
				.append("karma imported").append("\n")
				.append("bar imported").append("\n")
				.append("body-parser imported").append("\n");

		N4CliHelper.assertExpectedOutput(
				message.toString(), out);
	}

}
