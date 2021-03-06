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
package eu.numberfour.n4js.tests.projectModel;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 */
@RunWith(Suite.class)
@SuiteClasses({
		EclipseArchivePluginTest.class,
		EclipseBasedInternalWorkspacePluginTest.class,
		EclipseProjectPluginTest.class,
		N4JSEclipseCorePluginTest.class })
public class AllTests {
	// empty
}
