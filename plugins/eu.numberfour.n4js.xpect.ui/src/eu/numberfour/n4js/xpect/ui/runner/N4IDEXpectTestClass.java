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

import org.junit.runner.RunWith;
import org.xpect.XpectImport;
import org.xpect.runner.IXpectURIProvider;
import org.xpect.runner.XpectRunner;
import org.xpect.runner.XpectSuiteClasses;
import org.xpect.xtext.lib.tests.ResourceDescriptionTest;
import org.xpect.xtext.lib.tests.ValidationTest;

import eu.numberfour.n4js.xpect.OutputXpectMethod;
import eu.numberfour.n4js.xpect.TypeXpectMethod;
import eu.numberfour.n4js.xpect.linking.LinkingXpectMethod;
import eu.numberfour.n4js.xpect.scoping.ScopeXpectMethod;
import eu.numberfour.n4js.xpect.ui.runner.N4IDEXpectTestFilesCollector.N4IDEXpectTestURIProvider;
import eu.numberfour.n4js.xpect.ui.xpectmethods.NoerrorsXpectMethod;

/**
 * Main and only entry point for executing xpect in the product. Uses custom setup {@link N4IDEXpectFileSetup}.
 * Additionally custom files collector {@link N4IDEXpectTestFilesCollector} configures custom {@link IXpectURIProvider}
 * - {@link N4IDEXpectTestURIProvider} that will be used by {@link XpectRunner}
 */
@XpectSuiteClasses({
		ValidationTest.class,
		NoerrorsXpectMethod.class,
		OutputXpectMethod.class,
		TypeXpectMethod.class,
		ScopeXpectMethod.class,
		ResourceDescriptionTest.class,
		LinkingXpectMethod.class
})
@RunWith(XpectRunner.class)
@XpectImport({ N4IDEXpectFileSetup.class })
@N4IDEXpectTestFilesCollector
public class N4IDEXpectTestClass {
	// noop
}
