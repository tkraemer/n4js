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
package eu.numberfour.n4js.tests.externalPackages;

import static eu.numberfour.n4js.N4JSGlobals.N4JSD_FILE_EXTENSION;
import static eu.numberfour.n4js.N4JSGlobals.N4JS_FILE_EXTENSION;
import static eu.numberfour.n4js.projectModel.IN4JSProject.N4MF_MANIFEST;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Inject;

import eu.numberfour.n4js.external.ExternalLibrariesReloadHelper;
import eu.numberfour.n4js.external.NpmManager;
import eu.numberfour.n4js.external.TargetPlatformInstallLocationProvider;
import eu.numberfour.n4js.preferences.ExternalLibraryPreferenceStore;
import eu.numberfour.n4js.tests.builder.AbstractBuilderParticipantTest;
import eu.numberfour.n4js.tests.util.ProjectUtils;

/**
 * Test for checking whether the type definition files can be properly reloaded for registered {@code npm} packages.
 */
@SuppressWarnings("restriction")
public class ReloadTypeDefinitionsPluginUITest extends AbstractBuilderParticipantTest {

	private static final String NPM = "npm";
	private static final String PACKAGE_NAME = "bar";
	private static final String PACKAGE_VERSION = "0.1.2";
	private static final String LIB = "lib";
	private static final String PROBANDS = "probands";
	private static final String WORKSPACE_LOC = "IDE_2123";
	private static final String CLIENT = "Client";
	private static final String SRC_FOLDER = "src";

	@Inject
	private NpmManager npmManager;

	@Inject
	private ExternalLibrariesReloadHelper reloadHelper;

	@Inject
	private TargetPlatformInstallLocationProvider locationProvider;

	@Inject
	private ExternalLibraryPreferenceStore externalLibraryPreferenceStore;

	/**
	 * Updates the known external library locations with the {@code node_modules} folder.
	 */
	@Before
	public void setupWorkspace() throws Exception {
		final URI nodeModulesLocation = locationProvider.getTargetPlatformNodeModulesLocation();
		externalLibraryPreferenceStore.add(nodeModulesLocation);
		final IStatus result = externalLibraryPreferenceStore.save(new NullProgressMonitor());
		assertTrue("Error while saving external library preference changes.", result.isOK());
		waitForAutoBuild(false);
	}

	/**
	 * Tries to make sure the external libraries are cleaned from the Xtext index.
	 */
	@After
	@Override
	public void tearDown() throws Exception {
		final URI nodeModulesLocation = locationProvider.getTargetPlatformNodeModulesLocation();
		externalLibraryPreferenceStore.remove(nodeModulesLocation);
		final IStatus result = externalLibraryPreferenceStore.save(new NullProgressMonitor());
		assertTrue("Error while saving external library preference changes.", result.isOK());
		waitForAutoBuild(false);
		super.tearDown();
	}

	/**
	 * Imports {@value #CLIENT} project, checks for validation errors, installs {@code npm} package, checks for
	 * validation errors, modifies type definition file, refreshes npm packages, asserts validation errors.
	 */
	@Test
	@Ignore("IDEBUG-842")
	public void testInstallNpmThenRefresh() throws Exception {

		final File projectsRoot = new File(getResourceUri(PROBANDS, WORKSPACE_LOC));
		ProjectUtils.importProject(projectsRoot, CLIENT);
		waitForAutoBuild(false);

		final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(CLIENT);
		assertTrue(CLIENT + " project is not accessible.", project.isAccessible());
		final IFile clientModule = project.getFile(getResourceName(SRC_FOLDER, CLIENT + "." + N4JS_FILE_EXTENSION));
		assertTrue(clientModule + " client module is not accessible.", clientModule.isAccessible());

		final IFile manifest = project.getFile(getResourceName(N4MF_MANIFEST));
		assertTrue(manifest + " client module is not accessible.", manifest.isAccessible());

		// line 1: Cannot resolve import target :: resolving simple module import : found no matching modules
		// line 1: Couldn't resolve reference to TModule 'bar'.
		// line 1: Couldn't resolve reference to TExportableElement 'bar'.
		// line 3: Couldn't resolve reference to IdentifiableElement 'bar'.
		// line 5: Couldn't resolve reference to IdentifiableElement 'bar'.
		// line 1: Import of bar cannot be resolved.
		assertMarkers("Expected exactly six errors in client module.", clientModule, 6);

		// line 15: Project does not exist with artifact ID: bar.
		assertMarkers("Expected exactly one error in manifest.", manifest, 1);

		npmManager.installDependency(PACKAGE_NAME, new NullProgressMonitor());
		IResourcesSetupUtil.fullBuild();
		waitForAutoBuild(false);

		assertMarkers("Expected exactly zero errors in manifest.", manifest, 0);

		// line 3: Couldn't resolve reference to IdentifiableElement 'drinker'
		assertMarkers("Expected exactly one error in client module.", clientModule, 1);

		mimicTypeDefinitionFileUpdateOnRemoteGit();

		reloadHelper.reloadLibraries(true, new NullProgressMonitor());
		IResourcesSetupUtil.fullBuild();
		waitForAutoBuild(false);

		assertMarkers("Expected exactly zero errors in manifest.", manifest, 0);
		assertMarkers("Expected exactly zero errors in client module.", clientModule, 0);

	}

	/**
	 * Here instead of modifying the remote content and pull that to the local repository, we simply modify the content
	 * of the local clone.
	 */
	private void mimicTypeDefinitionFileUpdateOnRemoteGit() throws FileNotFoundException {
		final File gitRoot = new File(locationProvider.getTargetPlatformLocalGitRepositoryLocation());
		assertTrue("Git root folder does not exist at " + gitRoot + ".", gitRoot.isDirectory());

		final File npmRoot = new File(gitRoot, NPM);
		assertTrue("npm root folder does not exist at " + npmRoot + " in the local git clone.", npmRoot.isDirectory());

		final File barRoot = new File(npmRoot, PACKAGE_NAME);
		assertTrue(
				"Root folder for " + PACKAGE_NAME + "does not exist at " + barRoot + " in the local git clone.",
				barRoot.isDirectory());

		final File barDefinitionsRoot = new File(barRoot, PACKAGE_VERSION);
		assertTrue("Cannot find folder for version " + PACKAGE_VERSION + " for package " + PACKAGE_NAME + ". "
				+ barDefinitionsRoot, barDefinitionsRoot.isDirectory());

		final File barLibFolder = new File(barDefinitionsRoot, LIB);
		assertTrue("Cannot find folder for with the definition file. "
				+ barLibFolder, barLibFolder.isDirectory());

		final File definitionFile = new File(barLibFolder, PACKAGE_NAME + "." + N4JSD_FILE_EXTENSION);
		assertTrue("Cannot find definition file in local git clone." + definitionFile, definitionFile.isFile());

		try (PrintWriter pw = new PrintWriter(definitionFile)) {
			pw.write(NEW_DEFINITION_CONTENT);
			pw.flush();
		}

	}

	private static final String NEW_DEFINITION_CONTENT = "" +
			"export external public class ~Utils { }\n" +
			"export external public class ~Drinker { }\n" +
			"\n" +
			"export external public const utils: Utils;\n" +
			"export external public const drinker: Drinker;\n" +
			"\n" +
			"export default external public class __default {\n" +
			"	public const drinker: Drinker;\n" +
			"	public const utils: Utils;\n" +
			"}";

}
