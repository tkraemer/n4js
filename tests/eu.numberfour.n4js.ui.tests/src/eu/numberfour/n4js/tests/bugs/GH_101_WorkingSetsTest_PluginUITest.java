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
package eu.numberfour.n4js.tests.bugs;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Inject;

import eu.numberfour.n4js.ui.workingsets.WorkingSetManagerBroker;

/**
 * Class for testing the functionality of the N4JS working set support.
 */
public class GH_101_WorkingSetsTest_PluginUITest extends AbstractPluginUITest {

	@Inject
	private WorkingSetManagerBroker broker;
	private ProjectExplorer projectExplorer;

	/**
	 * Asserts that the {@link IWorkbench workbench} is running.
	 */
	@BeforeClass
	public static void assertWorkbenchIsRunning() {
		assertTrue("Expected running workbench.", PlatformUI.isWorkbenchRunning());
	}

	@Override
	@Before
	public void setUp() {
		waitForJobs();
		projectExplorer = (ProjectExplorer) showView(ProjectExplorer.VIEW_ID);
		delay(3000L);
	}

	@Test
	public void foo() {
		assertNotNull("Project Explorer is not visible.", projectExplorer);
		getActivePage().hideView(projectExplorer);
		waitForJobs();
		delay(3000L);
		projectExplorer = (ProjectExplorer) findView(ProjectExplorer.VIEW_ID);
		waitForJobs();
		delay(3000L);
		assertNull("Project Explorer must not be visible.", projectExplorer);

	}

}
