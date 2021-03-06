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
package eu.numberfour.n4js.xpect.ui.runner;

import org.eclipse.xtext.resource.XtextResource;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.xpect.XpectImport;
import org.xpect.expectation.IStringExpectation;
import org.xpect.runner.IXpectURIProvider;
import org.xpect.runner.Xpect;
import org.xpect.runner.XpectRunner;
import org.xpect.runner.XpectSuiteClasses;
import org.xpect.xtext.lib.setup.FileSetupContext;
import org.xpect.xtext.lib.setup.ThisResource;
import org.xpect.xtext.lib.tests.ValidationTest;

import eu.numberfour.n4js.xpect.ui.runner.N4IDEXpectTestFilesCollector.N4IDEXpectTestURIProvider;
import eu.numberfour.n4js.xpect.ui.xpectmethods.LineFeedAwareStringExpectation;
import eu.numberfour.n4js.xpect.ui.xpectmethods.NoerrorsXpectMethod;

/**
 * Main and only entry point for executing xpect in the product. Uses custom setup {@link N4IDEXpectFileSetup}.
 * Additionally custom files collector {@link N4IDEXpectTestFilesCollector} configures custom {@link IXpectURIProvider}
 * - {@link N4IDEXpectTestURIProvider} that will be used by {@link XpectRunner}
 */
@XpectSuiteClasses({
	ValidationTest.class,
	NoerrorsXpectMethod.class
})
@RunWith(XpectRunner.class)
@XpectImport({ N4IDEXpectFileSetup.class })
@N4IDEXpectTestFilesCollector
public class N4IDEXpectTestClass {

	/** dummy test method */
	@Xpect
	private void tst2(@LineFeedAwareStringExpectation(ignoreLineEndings = true) IStringExpectation expectation,
			@ThisResource XtextResource resource, org.xpect.setup.ISetupInitializer<Object> init,
			FileSetupContext fileSetupContext) {

		Assert.assertNotNull("init was null", init);
		Assert.assertNotNull("resource was null", resource);
		Assert.assertNotNull("fileSetupContext was null", fileSetupContext);
		Assert.assertNotNull("expectation was null", expectation);

		expectation
		.assertEquals("\"N4IDEXpectTestClass.dummyExpectation line 1\"\n\"N4IDEXpectTestClass.dummyExpectation line 2\"");
	}

}
