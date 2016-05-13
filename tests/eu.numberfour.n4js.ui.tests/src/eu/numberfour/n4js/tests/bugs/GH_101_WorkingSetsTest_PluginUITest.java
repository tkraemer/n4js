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
import static com.google.common.collect.Lists.newArrayList;
import static eu.numberfour.n4js.n4mf.ProjectType.LIBRARY;
import static eu.numberfour.n4js.n4mf.ProjectType.RUNTIME_ENVIRONMENT;
import static eu.numberfour.n4js.n4mf.ProjectType.RUNTIME_LIBRARY;
import static eu.numberfour.n4js.n4mf.ProjectType.TEST;
import static eu.numberfour.n4js.ui.navigator.N4JSProjectExplorerProblemsDecorator.ERROR;
import static eu.numberfour.n4js.ui.navigator.N4JSProjectExplorerProblemsDecorator.NO_ADORNMENT;
import static eu.numberfour.n4js.ui.navigator.N4JSProjectExplorerProblemsDecorator.WARNING;
import static eu.numberfour.n4js.ui.workingsets.WorkingSet.OTHERS_WORKING_SET_ID;
import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

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

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;

import eu.numberfour.n4js.n4mf.ProjectType;
import eu.numberfour.n4js.ui.navigator.N4JSProjectExplorerProblemsDecorator;
import eu.numberfour.n4js.ui.workingsets.ManualAssociationAwareWorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.ProjectTypeAwareWorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.ProjectTypeAwareWorkingSetManager.ProjectTypeWorkingSet;
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
		waitForUiThread();
		assertNotNull("Cannot show Project Explorer.", projectExplorer);
		commonViewer = projectExplorer.getCommonViewer();
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		broker.resetState();
		waitForJobs();

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
		final IProject project = createN4JSProject("P1", LIBRARY);
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

		assertMarkers("Expected exactly zero validation issues.", project, 0);

		adornmentFlag = decorator.computeAdornmentFlags(workingSet);
		assertEquals("Adornment mismatch.", NO_ADORNMENT, adornmentFlag);

	}

	/***/
	@Test
	public void testProjectTypeWorkingSetGrouping() throws CoreException {

		final Multimap<ProjectType, String> typeNameMapping = HashMultimap.create();
		typeNameMapping.putAll(LIBRARY, newArrayList("L1", "L2", "L3"));
		typeNameMapping.putAll(TEST, newArrayList("T1", "T2"));
		typeNameMapping.putAll(RUNTIME_ENVIRONMENT, newArrayList("RE1", "RE2", "RE3", "RE4"));
		typeNameMapping.putAll(RUNTIME_LIBRARY, newArrayList("RL1"));

		for (final Entry<ProjectType, Collection<String>> entry : typeNameMapping.asMap().entrySet()) {
			for (final String projectName : entry.getValue()) {
				createN4JSProject(projectName, entry.getKey());
			}
		}

		final Collection<String> othersProjectNames = newArrayList("O1", "O2");
		for (final String projectName : othersProjectNames) {
			createJSProject(projectName);
		}

		activateWorkingSetManager(ProjectTypeAwareWorkingSetManager.class);
		commonViewer.expandToLevel(2);
		waitForJobs();

		final TreeItem[] treeItems = commonViewer.getTree().getItems();
		final int expectedItemCount = ProjectType.values().length + 1;
		assertTrue("Expected exactly " + expectedItemCount + "items in the Project Explorer. Input was: "
				+ Arrays.toString(treeItems),
				treeItems.length == expectedItemCount);

		final List<ProjectTypeWorkingSet> workingSets = from(asList(treeItems)).transform(item -> item.getData())
				.filter(ProjectTypeWorkingSet.class)
				.toList();

		assertEquals("Mismatching number of working sets.", expectedItemCount, workingSets.size());

		for (final TreeItem treeItem : treeItems) {
			final ProjectType type = ((ProjectTypeWorkingSet) treeItem.getData()).getType();
			final Collection<String> expectedProjectNames;
			if (null == type) {
				expectedProjectNames = othersProjectNames;
			} else {
				expectedProjectNames = typeNameMapping.get(type);
			}
			assertEquals("Child item count mismatch: " + treeItem, expectedProjectNames.size() + ".",
					treeItem.getItemCount());
			for (final TreeItem child : treeItem.getItems()) {
				final String childText = child.getText();
				assertTrue("Unexpected tree item label: " + childText + ". Expected any of: "
						+ Iterables.toString(expectedProjectNames),
						expectedProjectNames.contains(childText));
			}
		}

	}

	private void activateWorkingSetManager(final Class<? extends WorkingSetManager> clazz) {
		final WorkingSetManager manager = from(broker.getWorkingSetManagers())
				.firstMatch(m -> m.getId().equals(clazz.getName()))
				.orNull();
		checkNotNull(manager, "Working set manager does not exist with ID: " + clazz);
		broker.setActiveManager(manager);
		broker.setWorkingSetTopLevel(true);
		waitForJobs();
	}

}
