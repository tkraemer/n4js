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

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import eu.numberfour.n4js.hlc.N4jsc.ExitCodeException;
import eu.numberfour.n4js.hlc.helper.N4CliHelper;

/**
 * AT for <a href="https://jira.numberfour.eu/browse/IDEBUG-695">IDEBUG-695</a>.
 */
public class AT_IDEBUG_695_CannotSetFinalFieldInCtorForStaticPolyfillsTest extends AbstractN4jscTest {

	static String WS_IDEBUG_695 = "IDEBUG-695";

	/**
	 * Setup workspace.
	 */
	@BeforeClass
	public static void setupWorkspace() throws IOException, ExitCodeException {
		setupWorkspace(WS_IDEBUG_695);
		final String wsRoot = TARGET + "/" + WSP;
		// Compile
		final String[] args = { "-pl", wsRoot,
				"-t", "allprojects",
				"-v"
		};
		new N4jsc().doMain(args);
	}

	/***/
	@Test
	public void compileCheckFinalFieldCanBeSetInInheritedCtor_ExpectCanBeSet() throws ExitCodeException, IOException {
		System.out.println(logMethodname());

		final String wsRoot = TARGET + "/" + WSP;
		final String fileToRun = wsRoot + "/IDEBUG-695/src/Main.n4js";
		final String[] args = { "-pl", wsRoot,
				"-t", "dontcompile",
				"-rw", "nodejs",
				"-r", fileToRun,
				"--debug",
				"-v"
		};

		// Run
		final String out = runCaptureOut(args);
		N4CliHelper.assertExpectedOutput(
				"A.a == 5: true",
				out);

	}

}
