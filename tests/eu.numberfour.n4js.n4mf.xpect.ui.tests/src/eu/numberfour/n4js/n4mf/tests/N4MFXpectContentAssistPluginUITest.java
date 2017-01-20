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
package eu.numberfour.n4js.n4mf.tests;

import static org.xpect.runner.XpectTestFiles.FileRoot.PROJECT;

import org.junit.runner.RunWith;
import org.xpect.runner.XpectRunner;
import org.xpect.runner.XpectSuiteClasses;
import org.xpect.runner.XpectTestFiles;

import eu.numberfour.n4js.xpect.ui.methods.ContentAssistXpectMethod;

/**
 * N4 manifest content assist UI tests.
 */
@XpectSuiteClasses(ContentAssistXpectMethod.class)
@RunWith(XpectRunner.class)
@XpectTestFiles(relativeTo = PROJECT, baseDir = "xpect-tests/contentAssist", fileExtensions = { "xt" })
public class N4MFXpectContentAssistPluginUITest {
	//
}
