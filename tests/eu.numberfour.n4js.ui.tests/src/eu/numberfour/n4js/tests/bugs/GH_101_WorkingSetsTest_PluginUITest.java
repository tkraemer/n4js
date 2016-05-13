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
import static com.google.common.collect.Iterables.toArray;
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
import static java.util.regex.Pattern.compile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
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
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;

import eu.numberfour.n4js.n4mf.ProjectType;
import eu.numberfour.n4js.ui.navigator.N4JSProjectExplorerProblemsDecorator;
import eu.numberfour.n4js.ui.navigator.internal.ShowHiddenWorkingSetsDropDownAction;
import eu.numberfour.n4js.ui.workingsets.ManualAssociationAwareWorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.ProjectNameFilterAwareWorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.ProjectNameFilterAwareWorkingSetManager.ProjectNameFilterWorkingSet;
import eu.numberfour.n4js.ui.workingsets.ProjectTypeAwareWorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.ProjectTypeAwareWorkingSetManager.ProjectTypeWorkingSet;
import eu.numberfour.n4js.ui.workingsets.WorkingSet;
import eu.numberfour.n4js.ui.workingsets.WorkingSetDiffBuilder;
import eu.numberfour.n4js.ui.workingsets.WorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.WorkingSetManagerBrokerImpl;
import eu.numberfour.n4js.ui.workingsets.internal.HideWorkingSetAction;
import eu.numberfour.n4js.utils.Arrays2;
import eu.numberfour.n4js.utils.Diff;

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
		waitIdle();
		projectExplorer = (ProjectExplorer) showView(ProjectExplorer.VIEW_ID);
		waitForUiThread();
		assertNotNull("Cannot show Project Explorer.", projectExplorer);
		commonViewer = projectExplorer.getCommonViewer();
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		broker.resetState();
		waitIdle();

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

		final Multimap<ProjectType, String> typeNamesMapping = HashMultimap.create();
		typeNamesMapping.putAll(LIBRARY, newArrayList("L1", "L2", "L3"));
		typeNamesMapping.putAll(TEST, newArrayList("T1", "T2"));
		typeNamesMapping.putAll(RUNTIME_ENVIRONMENT, newArrayList("RE1", "RE2", "RE3", "RE4"));
		typeNamesMapping.putAll(RUNTIME_LIBRARY, newArrayList("RL1"));

		for (final Entry<ProjectType, Collection<String>> entry : typeNamesMapping.asMap().entrySet()) {
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
		waitIdle();

		final TreeItem[] treeItems = commonViewer.getTree().getItems();
		final int expectedItemCount = ProjectType.values().length + 1;
		assertTrue("Expected exactly " + expectedItemCount + " items in the Project Explorer. Input was: "
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
				expectedProjectNames = typeNamesMapping.get(type);
			}
			assertEquals("Child item count mismatch: " + treeItem, expectedProjectNames.size(),
					treeItem.getItemCount());
			for (final TreeItem child : treeItem.getItems()) {
				final String childText = child.getText();
				assertTrue("Unexpected tree item label: " + childText + ". Expected any of: "
						+ Iterables.toString(expectedProjectNames),
						expectedProjectNames.contains(childText));
			}
		}

	}

	/***/
	@SuppressWarnings("restriction")
	@Test
	public void testProjectNameFilterWorkingSetGrouping() throws CoreException {

		final Multimap<String, String> filterNamesMapping = LinkedHashMultimap.create();
		filterNamesMapping.putAll(OTHERS_WORKING_SET_ID, newArrayList(
				"eu.numberfour.mangelhaft",
				"eu.numberfour.mangelhaft.assert",
				"eu.numberfour.mangelhaft.assert.test",
				"eu.numberfour.mangelhaft.mangeltypes",
				"eu.numberfour.mangelhaft.reporter.console",
				"eu.numberfour.mangelhaft.reporter.html",
				"eu.numberfour.mangelhaft.reporter.ide",
				"eu.numberfour.mangelhaft.reporter.ide.test",
				"eu.numberfour.mangelhaft.reporter.xunit",
				"eu.numberfour.mangelhaft.runner.html",
				"eu.numberfour.mangelhaft.runner.ide",
				"eu.numberfour.mangelhaft.runner.node",
				"eu.numberfour.mangelhaft.test"));

		filterNamesMapping.putAll(".*-runtime-.*", newArrayList(
				"n4js-runtime-es2015",
				"n4js-runtime-esnext",
				"n4js-runtime-fetch",
				"n4js-runtime-html5",
				"n4js-runtime-n4",
				"n4js-runtime-n4-tests",
				"n4js-runtime-node",
				"n4js-runtime-node-tests",
				"n4js-runtime-v8"));

		filterNamesMapping.putAll(".*stdlib.*", newArrayList(
				"eu.numberfour.stdlib.format",
				"eu.numberfour.stdlib.format.api",
				"eu.numberfour.stdlib.format.api-tests",
				"eu.numberfour.stdlib.i18n.api",
				"eu.numberfour.stdlib.i18n.api-tests",
				"eu.numberfour.stdlib.jtl",
				"eu.numberfour.stdlib.jtl.api",
				"eu.numberfour.stdlib.jtl.api-tests",
				"eu.numberfour.stdlib.jtl.tests",
				"eu.numberfour.stdlib.model.base",
				"eu.numberfour.stdlib.model.base.api",
				"eu.numberfour.stdlib.model.base.api-tests",
				"eu.numberfour.stdlib.model.base.tests",
				"eu.numberfour.stdlib.model.common",
				"eu.numberfour.stdlib.model.common.api",
				"eu.numberfour.stdlib.model.common.api-tests",
				"eu.numberfour.stdlib.model.common.tests",
				"eu.numberfour.stdlib.model.core",
				"eu.numberfour.stdlib.model.core.api",
				"eu.numberfour.stdlib.model.core.api-tests",
				"eu.numberfour.stdlib.model.core.zoo.berlin",
				"eu.numberfour.stdlib.model.gen",
				"eu.numberfour.stdlib.model.gen.api",
				"eu.numberfour.stdlib.model.gen.api-tests",
				"eu.numberfour.stdlib.notificationCenter.api",
				"eu.numberfour.stdlib.notificationCenter.api-tests",
				"eu.numberfour.stdlib.npm-dependencies",
				"eu.numberfour.stdlib.transaction",
				"eu.numberfour.stdlib.transaction.api",
				"eu.numberfour.stdlib.transaction.api-tests",
				"eu.numberfour.stdlib.util"));

		filterNamesMapping.putAll(".*stdlib.*api.*", newArrayList(
				"eu.numberfour.stdlib.format.api",
				"eu.numberfour.stdlib.format.api-tests",
				"eu.numberfour.stdlib.i18n.api",
				"eu.numberfour.stdlib.i18n.api-tests",
				"eu.numberfour.stdlib.jtl.api",
				"eu.numberfour.stdlib.jtl.api-tests",
				"eu.numberfour.stdlib.model.base.api",
				"eu.numberfour.stdlib.model.base.api-tests",
				"eu.numberfour.stdlib.model.common.api",
				"eu.numberfour.stdlib.model.common.api-tests",
				"eu.numberfour.stdlib.model.core.api",
				"eu.numberfour.stdlib.model.core.api-tests",
				"eu.numberfour.stdlib.model.gen.api",
				"eu.numberfour.stdlib.model.gen.api-tests",
				"eu.numberfour.stdlib.notificationCenter.api",
				"eu.numberfour.stdlib.notificationCenter.api-tests",
				"eu.numberfour.stdlib.transaction.api",
				"eu.numberfour.stdlib.transaction.api-tests"));

		filterNamesMapping.putAll(".*stdlib.*api.*test.*", newArrayList(
				"eu.numberfour.stdlib.format.api-tests",
				"eu.numberfour.stdlib.i18n.api-tests",
				"eu.numberfour.stdlib.jtl.api-tests",
				"eu.numberfour.stdlib.model.base.api-tests",
				"eu.numberfour.stdlib.model.common.api-tests",
				"eu.numberfour.stdlib.model.core.api-tests",
				"eu.numberfour.stdlib.model.gen.api-tests",
				"eu.numberfour.stdlib.notificationCenter.api-tests",
				"eu.numberfour.stdlib.transaction.api-tests"));

		final IWorkspaceDescription workspaceDescription = ResourcesPlugin.getWorkspace().getDescription();
		final boolean autoBuild = workspaceDescription.isAutoBuilding();
		try {
			// No need for the build at all.
			workspaceDescription.setAutoBuilding(false);
			for (final String projectName : filterNamesMapping.values()) {
				org.eclipse.xtext.junit4.ui.util.JavaProjectSetupUtil.createSimpleProject(projectName);
			}
		} finally {
			workspaceDescription.setAutoBuilding(autoBuild);
		}

		activateWorkingSetManager(ProjectNameFilterAwareWorkingSetManager.class);
		final WorkingSetManager manager = broker.getActiveManager();
		final WorkingSetDiffBuilder builder = new WorkingSetDiffBuilder(manager);
		final List<WorkingSet> workingSets = newArrayList();

		final WorkingSet other = new ProjectNameFilterWorkingSet(compile(OTHERS_WORKING_SET_ID), OTHERS_WORKING_SET_ID,
				manager);
		builder.add(other);
		workingSets.add(other);

		for (final String workingSetId : filterNamesMapping.keySet()) {
			final WorkingSet workingSet = new ProjectNameFilterWorkingSet(compile(workingSetId), workingSetId, manager);
			builder.add(workingSet);
			workingSets.add(workingSet);
		}

		final Diff<WorkingSet> diff = builder.build(toArray(workingSets, WorkingSet.class),
				toArray(workingSets, WorkingSet.class));

		manager.updateState(diff);
		broker.refreshNavigator();
		waitIdle();

		commonViewer.expandToLevel(2);
		waitIdle();

		final TreeItem[] treeItems = commonViewer.getTree().getItems();
		final int expectedItemCount = filterNamesMapping.keySet().size();
		assertTrue("Expected exactly " + expectedItemCount + " items in the Project Explorer. Input was: "
				+ Arrays.toString(treeItems),
				treeItems.length == expectedItemCount);

		final List<ProjectNameFilterWorkingSet> workingSetsFromTree = from(asList(treeItems))
				.transform(item -> item.getData())
				.filter(ProjectNameFilterWorkingSet.class)
				.toList();

		assertEquals("Mismatching number of working sets.", expectedItemCount, workingSetsFromTree.size());

		for (final TreeItem treeItem : treeItems) {
			final Pattern filter = ((ProjectNameFilterWorkingSet) treeItem.getData()).getFilter();
			final Collection<String> expectedProjectNames = filterNamesMapping.get(filter.pattern());
			assertEquals("Child item count mismatch: " + treeItem, expectedProjectNames.size(),
					treeItem.getItemCount());
			for (final TreeItem child : treeItem.getItems()) {
				final String childText = child.getText();
				assertTrue("Unexpected tree item label: " + childText + ". Expected any of: "
						+ Iterables.toString(expectedProjectNames),
						expectedProjectNames.contains(childText));
			}
		}

	}

	/***/
	@Test
	public void testHideShowWorkingSets() {
		activateWorkingSetManager(ProjectTypeAwareWorkingSetManager.class);
		TreeItem[] treeItems = commonViewer.getTree().getItems();
		final int expectedItemCount = ProjectType.values().length + 1;
		assertTrue("Expected exactly " + expectedItemCount + " items in the Project Explorer. Input was: "
				+ Arrays.toString(treeItems),
				treeItems.length == expectedItemCount);

		List<ProjectTypeWorkingSet> workingSets = from(asList(treeItems)).transform(item -> item.getData())
				.filter(ProjectTypeWorkingSet.class)
				.toList();

		assertEquals("Mismatching number of working sets.", expectedItemCount, workingSets.size());

		List<ProjectTypeWorkingSet> workingSetsToHide = workingSets;
		final HideWorkingSetAction hideAction = new HideWorkingSetAction();

		commonViewer.setSelection(new StructuredSelection(workingSets.toArray()));
		waitIdle();

		treeItems = commonViewer.getTree().getItems();
		final List<ProjectTypeWorkingSet> selectedWorkingSets = from(asList(treeItems))
				.transform(item -> item.getData())
				.filter(ProjectTypeWorkingSet.class)
				.toList();

		assertEquals(workingSetsToHide, selectedWorkingSets);
		hideAction.selectionChanged(commonViewer.getStructuredSelection());
		waitIdle();

		assertFalse("Expected disabled action.", hideAction.isEnabled());

		workingSetsToHide = newArrayList(workingSets.subList(0, 3));

		commonViewer.setSelection(new StructuredSelection(workingSetsToHide.toArray()));
		hideAction.selectionChanged(commonViewer.getStructuredSelection());
		waitIdle();

		assertTrue("Expected enabled action.", hideAction.isEnabled());

		hideAction.run();
		waitIdle();

		treeItems = commonViewer.getTree().getItems();
		workingSets = from(asList(treeItems)).transform(item -> item.getData())
				.filter(ProjectTypeWorkingSet.class)
				.toList();

		assertEquals("Mismatching number of working sets.", expectedItemCount - workingSetsToHide.size(),
				workingSets.size());

		for (final WorkingSet workingSet : workingSetsToHide) {
			assertTrue("Working set must not be visible in the navigator: " + workingSet,
					!workingSets.contains(workingSet));
		}

		IContributionItem showHiddenWorkingSetsItem = from(
				Arrays.asList(projectExplorer.getViewSite().getActionBars().getToolBarManager().getItems()))
						.firstMatch(i -> ShowHiddenWorkingSetsDropDownAction.class.getName().equals(i.getId()))
						.orNull();

		assertNotNull("Expected visible toolbar item, since there are hidden working sets", showHiddenWorkingSetsItem);
		assertTrue("Expected a type of " + ActionContributionItem.class,
				showHiddenWorkingSetsItem instanceof ActionContributionItem);

		final IAction action = ((ActionContributionItem) showHiddenWorkingSetsItem).getAction();
		assertTrue("Expected a type of " + ShowHiddenWorkingSetsDropDownAction.class,
				action instanceof ShowHiddenWorkingSetsDropDownAction);
		final ShowHiddenWorkingSetsDropDownAction showHiddenWorkingSetsAction = (ShowHiddenWorkingSetsDropDownAction) action;

		Menu menu = showHiddenWorkingSetsAction.getMenu(commonViewer.getControl());

		assertTrue(
				"Expected " + workingSetsToHide.size()
						+ " menu item plus a separator plus an item for showing all hidden elements.",
				workingSetsToHide.size() + 2 == menu.getItemCount());

		menu.getItem(0).notifyListeners(SWT.Selection, null);
		waitIdle();
		workingSetsToHide.remove(0);

		treeItems = commonViewer.getTree().getItems();
		workingSets = from(asList(treeItems)).transform(item -> item.getData())
				.filter(ProjectTypeWorkingSet.class)
				.toList();

		assertEquals("Mismatching number of working sets.", expectedItemCount - workingSetsToHide.size(),
				workingSets.size());

		menu = showHiddenWorkingSetsAction.getMenu(commonViewer.getControl());
		assertTrue(
				"Expected " + workingSetsToHide.size()
						+ " menu item plus a separator plus an item for showing all hidden elements.",
				workingSetsToHide.size() + 2 == menu.getItemCount());

		menu.getItem(menu.getItemCount() - 1).notifyListeners(SWT.Selection, null);
		waitIdle();

		treeItems = commonViewer.getTree().getItems();
		assertTrue("Expected exactly " + expectedItemCount + " items in the Project Explorer. Input was: "
				+ Arrays.toString(treeItems),
				treeItems.length == expectedItemCount);

		showHiddenWorkingSetsItem = from(
				Arrays.asList(projectExplorer.getViewSite().getActionBars().getToolBarManager().getItems()))
						.firstMatch(i -> ShowHiddenWorkingSetsDropDownAction.class.getName().equals(i.getId()))
						.orNull();

		assertNull("Expected not visible toolbar item, since all working sets are visible.", showHiddenWorkingSetsItem);

	}

	private void activateWorkingSetManager(final Class<? extends WorkingSetManager> clazz) {
		final WorkingSetManager manager = from(broker.getWorkingSetManagers())
				.firstMatch(m -> m.getId().equals(clazz.getName()))
				.orNull();
		checkNotNull(manager, "Working set manager does not exist with ID: " + clazz);
		broker.setActiveManager(manager);
		broker.setWorkingSetTopLevel(true);
		waitIdle();
	}

}
