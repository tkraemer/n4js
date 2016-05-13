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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.FluentIterable.from;
import static eu.numberfour.n4js.ui.navigator.N4JSProjectExplorerProblemsDecorator.ERROR;
import static eu.numberfour.n4js.ui.navigator.N4JSProjectExplorerProblemsDecorator.NO_ADORNMENT;
import static eu.numberfour.n4js.ui.navigator.N4JSProjectExplorerProblemsDecorator.WARNING;
import static eu.numberfour.n4js.ui.workingsets.WorkingSet.OTHERS_WORKING_SET_ID;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.xtext.util.StringInputStream;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Inject;

import eu.numberfour.n4js.ui.navigator.N4JSProjectExplorerProblemsDecorator;
import eu.numberfour.n4js.ui.workingsets.ManualAssociationAwareWorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.WorkingSet;
import eu.numberfour.n4js.ui.workingsets.WorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.WorkingSetManagerBrokerImpl;
import eu.numberfour.n4js.utils.Arrays2;

/**
 * Class for testing the functionality of the N4JS working set support.
 */
public class GH_101_WorkingSetsTest_PluginUITest extends AbstractPluginUITest {

	@Inject
	private WorkingSetManagerBrokerImpl broker;

	private ProjectExplorer projectExplorer;

	private CommonViewer commonViewer;

	/**
	 * Asserts that the {@link IWorkbench workbench} is running.
	 */
	@BeforeClass
	public static void assertWorkbenchIsRunning() {
		assertTrue("Expected running workbench.", PlatformUI.isWorkbenchRunning());
	}

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		waitForJobs();
		projectExplorer = (ProjectExplorer) showView(ProjectExplorer.VIEW_ID);
		delay(1000L);
		assertNotNull("Cannot show Project Explorer.", projectExplorer);
		commonViewer = projectExplorer.getCommonViewer();
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		broker.resetState();
		waitForJobs();
		delay(1000L);

		final TreeItem[] treeItems = commonViewer.getTree().getItems();
		assertTrue("Expected empty Project Explorer. Input was: " + Arrays.toString(treeItems),
				Arrays2.isEmpty(treeItems));
	}

	/***/
	@Test
	public void testWorkingSetIsTheSingleRootInNavaigator() {
		activateWorkingSetManager(ManualAssociationAwareWorkingSetManager.class);

		final TreeItem[] treeItems = commonViewer.getTree().getItems();
		assertTrue("Expected exactly one item in the Project Explorer. Input was: " + Arrays.toString(treeItems),
				treeItems.length == 1);

		final Object data = treeItems[0].getData();
		assertTrue("Expected " + WorkingSet.class + " input in navigator. Was " + data,
				data instanceof WorkingSet);

		final WorkingSet workingSet = (WorkingSet) treeItems[0].getData();
		assertTrue("Expected working set with ID: " + OTHERS_WORKING_SET_ID + ". Was " + workingSet.getId(),
				OTHERS_WORKING_SET_ID.equals(workingSet.getId()));

	}

	/***/
	@Test
	public void testMarkerSupportForWorkingSets() throws CoreException, IOException {
		final IProject project = createJSProject("P1");
		assertTrue("Project " + project + " is not accessible.", project.isAccessible());

		configureProjectWithXtext(project);
		createTestFile(project.getFolder("src"), "A", "class a { }");

		final IFile moduleFile = project.getFolder("src").getFile("A.n4js");
		assertTrue("File is not accessible under src/A.n4js",
				moduleFile.isAccessible());

		// line 1: Class names should start with upper case letter.
		assertMarkers("Expected exactly one validation issue.", project, 1);

		activateWorkingSetManager(ManualAssociationAwareWorkingSetManager.class);

		final TreeItem[] treeItems = commonViewer.getTree().getItems();
		assertTrue("Expected exactly one item in the Project Explorer. Input was: " + Arrays.toString(treeItems),
				treeItems.length == 1);

		final Object data = treeItems[0].getData();
		assertTrue("Expected " + WorkingSet.class + " input in navigator. Was " + data,
				data instanceof WorkingSet);

		final WorkingSet workingSet = (WorkingSet) treeItems[0].getData();
		assertTrue("Expected working set with ID: " + OTHERS_WORKING_SET_ID + ". Was " + workingSet.getId(),
				OTHERS_WORKING_SET_ID.equals(workingSet.getId()));

		final N4JSProjectExplorerProblemsDecorator decorator = new N4JSProjectExplorerProblemsDecorator();
		int adornmentFlag = decorator.computeAdornmentFlags(workingSet);
		assertEquals("Adornment mismatch.", WARNING, adornmentFlag);

		try (final InputStream is = new StringInputStream("someBrokenContent")) {
			moduleFile.setContents(is, IResource.FORCE, null);
		}
		waitForAutoBuild();

		// line 1: Couldn't resolve reference to IdentifiableElement 'someBrokenContent'
		assertMarkers("Expected exactly one validation issue.", project, 1);

		adornmentFlag = decorator.computeAdornmentFlags(workingSet);
		assertEquals("Adornment mismatch.", ERROR, adornmentFlag);

		try (final InputStream is = new StringInputStream("class A { }")) {
			moduleFile.setContents(is, IResource.FORCE, null);
		}
		waitForAutoBuild();

		// line 1: Couldn't resolve reference to IdentifiableElement 'someBrokenContent'
		assertMarkers("Expected exactly zero validation issues.", project, 0);

		adornmentFlag = decorator.computeAdornmentFlags(workingSet);
		assertEquals("Adornment mismatch.", NO_ADORNMENT, adornmentFlag);

	}

	private void activateWorkingSetManager(final Class<? extends WorkingSetManager> clazz) {
		final WorkingSetManager manager = from(broker.getWorkingSetManagers())
				.firstMatch(m -> m.getId().equals(clazz.getName()))
				.orNull();
		checkNotNull(manager, "Working set manager does not exist with ID: " + clazz);
		broker.setActiveManager(manager);
		broker.setWorkingSetTopLevel(true);
		waitForJobs();
		delay(1000L);
	}

}
