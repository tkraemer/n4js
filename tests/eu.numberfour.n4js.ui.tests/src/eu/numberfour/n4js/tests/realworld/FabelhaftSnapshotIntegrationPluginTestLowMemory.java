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
package eu.numberfour.n4js.tests.realworld;

import static com.google.common.base.Throwables.propagate;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.collect.Iterables.size;
import static eu.numberfour.n4js.integration.IntegrationTestsUtils.ensureTestDataExistsFor;
import static eu.numberfour.n4js.integration.IntegrationTestsUtils.getTestDataCheckoutPathFor;
import static eu.numberfour.n4js.integration.IntegrationTestsUtils.TestType.FABELHAFT_INTEGRATION_TEST;
import static eu.numberfour.n4js.tests.util.ProjectUtils.importProject;
import static java.io.File.separator;
import static java.lang.management.ManagementFactory.getMemoryMXBean;
import static java.util.Arrays.asList;
import static org.apache.log4j.Logger.getLogger;
import static org.eclipse.core.resources.IMarker.LOCATION;
import static org.eclipse.core.resources.IMarker.MESSAGE;
import static org.eclipse.core.resources.IMarker.SEVERITY;
import static org.eclipse.core.resources.IMarker.SEVERITY_ERROR;
import static org.eclipse.core.resources.IResource.DEPTH_INFINITE;
import static org.eclipse.core.resources.ResourcesPlugin.getWorkspace;
import static org.eclipse.ui.PlatformUI.isWorkbenchRunning;
import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.cleanBuild;
import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.cleanWorkspace;
import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.fullBuild;
import static org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil.root;
import static org.eclipse.xtext.ui.MarkerTypes.ANY_VALIDATION;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.runtime.CoreException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.base.Predicate;

/**
 * Test for checking the behavior of the clustering builder by building the full Fabelhaft snapshot code while the
 * application is running on low heap setting.
 * <p>
 * <b>NOTE:&nbsp;</b>This test requires the Fabelhaft snapshot to be manually cloned into the
 * <code>{USER_HOME}/checkout_n4js.swtbot.test_fabelhaft.snapshot</code> folder. Unlike in case of
 * {@code FabelhaftSnapshotIntegrationSwtBotTest} this test does not responsible to automatically clones the Fabelhaft
 * snapshot test data but performs a hard reset on the git repository (the test data) to ensure data consistency. The
 * main reason of this behavior is the heap settings. The automatically performed git clone requires high heap settings,
 * if the heap would be be set high enough to support the automatic git clone, then this test would not be able check
 * the behavior of the clustering builder properly.
 * <p>
 * For setting up the test data one has to perform the below commands in a shell:
 *
 * <pre>
 * mkdir ~/checkout_n4js.swtbot.test_fabelhaft.snapshot
 * git clone --depth 1 https://github.numberfour.eu/NumberFour/n4ideintegration.git ~/checkout_n4js.swtbot.test_fabelhaft.snapshot
 * </pre>
 * <p>
 * <b>NOTE:&nbsp;</b> This class intentionally breaks the {@code *Test.java} class naming convention to be excluded from
 * normal tests. This will be executed in a context of customized maven surefire profile.
 *
 * <pre>
 * -Dn4ide.fabelhaft.integration.snapshot=your_branch_name
 * </pre>
 */
//@formatter:off
@Ignore("IDE-1744") // FIXME IDE-1744
@SuppressWarnings("restriction")
public class FabelhaftSnapshotIntegrationPluginTestLowMemory /* This class intentionally breaks the *Test.java class naming convention to be excluded from normal tests. */{
	//@formatter:on

	private static final Logger LOGGER = getLogger(FabelhaftSnapshotIntegrationPluginTestLowMemory.class);

	private static final String SNAPSHOT_DIR_NAME = "fabelhaft_snapshot";

	private static final long HEAB_IN_MB = 320L;
	private static final long MAX_HEAP_THRESHOLD = HEAB_IN_MB * 1024L * 1024L;

	private static final Predicate<IMarker> ERROR_MARKER_PREDICATE = marker -> {
		try {
			final Object value = marker.getAttribute(SEVERITY);
			if (value instanceof Integer) {
				return SEVERITY_ERROR == ((Integer) value).intValue();
			}
		} catch (final CoreException e) {
			propagate(e);
		}
		return false;
	};

	/***/
	@BeforeClass
	public static void beforeClass() throws Exception {
		assertProperMaximumHepSetting();
		assertTrue("This test requires a running workbench.", isWorkbenchRunning());

		// Disable autobuild.
		toggleAutobuild(false);
		try {
			LOGGER.info("Cleaning workspace...");
			cleanWorkspace();
			LOGGER.info("Workspace cleaned.");
			LOGGER.info("Resetting test data.");
			ensureTestDataExistsFor(FABELHAFT_INTEGRATION_TEST, false);
			LOGGER.info("Test data was reseted.");
			LOGGER.info("Importing projects into workspace...");
			importProjects();
			LOGGER.info("Projects were successfully imported into workspace.");
		} finally {
			// Re-enable after imports done.
			toggleAutobuild(true);
		}

	}

	/**
	 * Toggle auto-build.
	 *
	 * @param enabled
	 *            {@code true} will enable auto-build.
	 */
	private static void toggleAutobuild(final boolean enabled) {
		final IWorkspace workspace = getWorkspace();
		final IWorkspaceDescription description = workspace.getDescription();
		description.setAutoBuilding(enabled);
		try {
			LOGGER.info("Turning auto-build " + (enabled ? "on" : "off") + "...");
			workspace.setDescription(description);
			LOGGER.info("Auto-build was successfully turned " + (enabled ? "on" : "off") + ".");
		} catch (final CoreException e) {
			throw new RuntimeException("Error while toggling auto-build", e);
		}
	}

	/***/
	@AfterClass
	public static void afterClass() throws Exception {
		LOGGER.info("Cleaning workspace...");
		cleanWorkspace();
		LOGGER.info("Workspace successfully cleaned.");
	}

	/***/
	@Test
	public void testFullCleanBuild() throws Exception {
		LOGGER.info("Running full clean build for all projects...");
		fullBuild();
		LOGGER.info("Full clean build successfully completed.");
		assertNoErrors();
	}

	private static void assertProperMaximumHepSetting() {
		final long maxHeapInBytes = getMemoryMXBean().getHeapMemoryUsage().getMax();
		if (MAX_HEAP_THRESHOLD < maxHeapInBytes) {
			final String message = "This test requires at most " + HEAB_IN_MB + " MB of maximum heap size. Was "
					+ maxHeapInBytes
					+ " byte.";
			LOGGER.warn(message);
			LOGGER.warn("Aborting test execution");
			fail(message);
		}
	}

	private static void importProjects() throws Exception {
		final File rootFolder = new File(
				getOnlyElement(getTestDataCheckoutPathFor(FABELHAFT_INTEGRATION_TEST)) + separator
						+ SNAPSHOT_DIR_NAME);
		assertTrue("Root folder directory does not exist at: " + rootFolder, rootFolder.exists());
		assertTrue("Root folder directory is actually not a directory: " + rootFolder, rootFolder.isDirectory());
		assertTrue("Root folder directory content cannot be read: " + rootFolder, rootFolder.canRead());
		assertTrue("No files were found in the directory: " + rootFolder, null != rootFolder.listFiles());

		for (final File file : rootFolder.listFiles()) {
			if (file.exists() && file.isDirectory()) {
				LOGGER.info("Importing " + file.getName() + " into workspace...");
				importProject(rootFolder, file.getName());
				LOGGER.info("Project " + file.getName() + " was successfully imported into the workspace.");
			}
		}
		LOGGER.info("Waiting for full-build to complete...");
		cleanBuild(); // using full build after imports.
		LOGGER.info("Auto-build successfully completed.");
	}

	private static void assertNoErrors() throws CoreException {
		final Iterable<IMarker> errorMarkers = getErrorMarkers();
		final int errorCount = size(errorMarkers);
		final String message = "Test failed. Expected exactly 0 errors, got " + errorCount + " instead.\n"
				+ toString(errorMarkers);
		if (0 != errorCount) {
			LOGGER.info(message);
			fail(message);
		}
	}

	private static Iterable<IMarker> getErrorMarkers() throws CoreException {
		return from(asList(root().findMarkers(ANY_VALIDATION, true, DEPTH_INFINITE))).filter(ERROR_MARKER_PREDICATE);
	}

	private static String toString(final Iterable<? extends IMarker> markers) {
		final StringBuilder sb = new StringBuilder();
		if (null != markers) {
			for (final IMarker marker : markers) {
				try {
					sb.append("\t");
					sb.append(marker.getAttribute(MESSAGE));
					sb.append(" [");
					sb.append(marker.getAttribute(LOCATION));
					sb.append("]\n");
				} catch (final CoreException e) {
					propagate(e);
				}
			}
		}
		return sb.toString();
	}

}
