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

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Iterables.size;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.difference;
import static eu.numberfour.n4js.resource.UserdataMapper.USERDATA_KEY_SERIALIZED_SCRIPT;

import java.io.File;
import java.net.URI;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.builder.builderState.IBuilderState;
import org.eclipse.xtext.builder.builderState.impl.ResourceDescriptionImpl;
import org.eclipse.xtext.junit4.ui.util.IResourcesSetupUtil;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.BiMap;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;

import eu.numberfour.n4js.external.libraries.ExternalLibrariesActivator;
import eu.numberfour.n4js.preferences.ExternalLibraryPreferenceStore;
import eu.numberfour.n4js.ts.types.TypesPackage;
import eu.numberfour.n4js.ui.building.ResourceDescriptionWithoutModuleUserData;
import eu.numberfour.n4js.ui.internal.ContributingResourceDescriptionPersister;

/**
 * Test for checking that no {@link ResourceDescriptionWithoutModuleUserData customized resource description} instances
 * are leaked into the {@link IBuilderState builder state Xtext index} because those cannot be persisted.
 *
 * Way to manually reproduce the issue:
 * <ol>
 * <li>Start up IDE with empty workspace but available N4JS built-ins.</li>
 * <li>Create a test project with the 'greeter' test.</li>
 * <li>Run test verify everything is fine, then gracefully shutdown the application.</li>
 * <li>Start application, then crash.</li>
 * <li>Start application, due to the crash the index content is gone, so externals will be re-initialized automatically.
 * </li>
 * <li>After the re-initialization, everything should be fine, run 'greeter' test to verify.</li>
 * <li>Gracefully stop application. &rarr; <b>That point some index entries will not be persisted.</b></li>
 * <li>Start application, no built-in re-initialization will run (there was a graceful shutdown), but due to some
 * missing index entries there will be a compiler error.</li>
 * </ol>
 *
 * <p>
 * Since a plug-in UI test cannot test multiple application startup and VM crash we have to test is deep down at the
 * builder state and EMF based persister level.
 *
 * <p>
 * To make the test fail comment out
 * {@code eu.numberfour.n4js.ui.building.N4JSGenerateImmediatelyBuilderState.setResourceDescriptionsData(ResourceDescriptionsData)}
 * method.
 *
 */
@SuppressWarnings("restriction")
public class GH_120_XtextIndexPersistence_PluginUITest extends AbstractIDEBUG_Test {

	private static final String PROJECT_NAME = "GH-120";

	@Inject
	private IBuilderState builderState;

	@Inject
	private ContributingResourceDescriptionPersister persister;

	@Inject
	private ExternalLibraryPreferenceStore externalLibraryPreferenceStore;

	/**
	 * Initializes the N4JS built-in libraries. Does not matter before or after the test project import.
	 */
	@Before
	public void loadBuiltIns() {
		final BiMap<URI, String> locations = ExternalLibrariesActivator.EXTERNAL_LIBRARIES_SUPPLIER.get();
		for (final URI location : locations.keySet()) {
			externalLibraryPreferenceStore.add(location);
		}
		final IStatus result = externalLibraryPreferenceStore.save(new NullProgressMonitor());
		assertTrue("Error while saving external library preference changes.", result.isOK());
		waitForAutoBuild();
	}

	/**
	 * Cleans the external library content.
	 */
	@After
	@Override
	public void tearDown() throws Exception {
		unLoadBuiltIns();
		super.tearDown();
	}

	private void unLoadBuiltIns() {
		final BiMap<URI, String> locations = ExternalLibrariesActivator.EXTERNAL_LIBRARIES_SUPPLIER.get();
		for (final URI location : locations.keySet()) {
			externalLibraryPreferenceStore.remove(location);
		}
		final IStatus result = externalLibraryPreferenceStore.save(new NullProgressMonitor());
		assertTrue("Error while saving external library preference changes.", result.isOK());
		waitForAutoBuild();
	}

	@Override
	protected ProjectImporter getProjectImporter() {
		return new ProjectImporter(new File(new File("probands/" + PROJECT_NAME + "/").getAbsolutePath()));
	}

	/***/
	@Test
	public void checkNoCustomResourceDescriptionsLeaksToBuilderState() throws CoreException {
		final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(PROJECT_NAME);
		assertTrue("Test project is not accessible.", project.isAccessible());

		// Since we do not know whether the built-in initialization or the test project import happened earlier...
		// Make sure both test module and manifest get into the index.
		IResourcesSetupUtil.fullBuild();
		waitForAutoBuild();
		assertMarkers("Expected exactly zero issues.", project, 0);

		final Resource resource = persister.createResource();
		assertNotNull("Test resource was null.", resource);

		final Set<org.eclipse.emf.common.util.URI> beforeCrashBuilderState = from(
				builderState.getAllResourceDescriptions())
						.transform(desc -> desc.getURI()).toSet();
		final int builderStateBeforeReloadSize = Iterables.size(beforeCrashBuilderState);
		final FluentIterable<IEObjectDescription> beforeTModulesInBuilderState = from(
				builderState.getAllResourceDescriptions()).transformAndConcat(desc -> desc.getExportedObjects())
						.filter(desc -> desc.getEClass() == TypesPackage.eINSTANCE.getTModule());
		int beforeTModulesInBuilderStateSize = size(beforeTModulesInBuilderState);
		int beforeTModulesInBuilderStateWithUserDataSize = size(
				beforeTModulesInBuilderState.filter(desc -> null != desc.getUserData(USERDATA_KEY_SERIALIZED_SCRIPT)));

		persister.saveToResource(resource, builderState.getAllResourceDescriptions());
		final Iterable<EObject> beforeCrashResource = newArrayList(resource.getContents());
		final int persistedBeforeReloadSize = resource.getContents().size();

		// Imitate VM crash with force built-in unload and reload.
		unLoadBuiltIns();
		IResourcesSetupUtil.fullBuild();
		waitForAutoBuild();

		// Test module issues:
		// Cannot resolve import target :: resolving simple module import : found no matching modules
		// Couldn't resolve reference to IdentifiableElement 'Assert'.
		// Couldn't resolve reference to TExportableElement 'Assert'.
		// Couldn't resolve reference to TModule 'n4.mangel.assert.Assert'.
		// Import of Assert cannot be resolved.

		// Manifest issues:
		// Project does not exist with artifact ID: eu.numberfour.mangelhaft.
		// Project does not exist with artifact ID: eu.numberfour.mangelhaft.assert.
		// Project with test fragment should depend on eu.numberfour.mangelhaft.
		assertMarkers("Expected exactly 8 issues.", project, 8);

		loadBuiltIns();
		IResourcesSetupUtil.fullBuild();
		waitForAutoBuild();
		resource.getContents().clear();

		assertMarkers("Expected exactly zero issues.", project, 0);

		final Set<org.eclipse.emf.common.util.URI> afterCrashBuilderState = from(
				builderState.getAllResourceDescriptions())
						.transform(desc -> desc.getURI()).toSet();
		final int builderStateAfterReloadSize = Iterables.size(afterCrashBuilderState);

		final FluentIterable<IEObjectDescription> afterTModulesInBuilderState = from(
				builderState.getAllResourceDescriptions()).transformAndConcat(desc -> desc.getExportedObjects())
						.filter(desc -> desc.getEClass() == TypesPackage.eINSTANCE.getTModule());
		int afterTModulesInBuilderStateSize = size(afterTModulesInBuilderState);
		int afterTModulesInBuilderStateWithUserDataSize = size(
				afterTModulesInBuilderState.filter(desc -> null != desc.getUserData(USERDATA_KEY_SERIALIZED_SCRIPT)));

		persister.saveToResource(resource, builderState.getAllResourceDescriptions());
		final Iterable<EObject> afterCrashResource = newArrayList(resource.getContents());
		final int persistedAfterReloadSize = resource.getContents().size();

		assertTrue("Expected same number of persisted and available resource description before and after crash. Was:"
				+ "\nBuilder state before reload size: " + builderStateBeforeReloadSize
				+ "\nBuilder state after reload size: " + builderStateAfterReloadSize
				+ "\nPersisted before reload size: " + persistedBeforeReloadSize
				+ "\nPersisted after reload size: " + persistedAfterReloadSize
				+ "\nDifferences: "
				+ printDiff(beforeCrashBuilderState, afterCrashBuilderState, beforeCrashResource, afterCrashResource),
				builderStateBeforeReloadSize == builderStateAfterReloadSize
						&& persistedBeforeReloadSize == persistedAfterReloadSize
						&& builderStateBeforeReloadSize == persistedBeforeReloadSize);

		assertTrue(
				"Expected same number for EObject descriptions for TModules before and after crash. Before was: "
						+ beforeTModulesInBuilderStateSize + " after was: " + afterTModulesInBuilderStateSize,
				beforeTModulesInBuilderStateSize == afterTModulesInBuilderStateSize);

		assertTrue(
				"Expected same number for EObject descriptions for TModules with serialized user data before and after crash. Before was: "
						+ beforeTModulesInBuilderStateWithUserDataSize + " after was: "
						+ afterTModulesInBuilderStateWithUserDataSize,
				beforeTModulesInBuilderStateWithUserDataSize == afterTModulesInBuilderStateWithUserDataSize);

	}

	private String printDiff(Set<org.eclipse.emf.common.util.URI> beforeCrashBuilderState,
			Set<org.eclipse.emf.common.util.URI> afterCrashBuilderState, Iterable<EObject> beforeCrashResource,
			Iterable<EObject> afterCrashResource) {

		return "\n--------------------------------------------------------------------------------"
				+ "\n\t### Before crash difference in builder state and resource: "
				+ Joiner.on("\n\t\t- ").join(difference(beforeCrashBuilderState, getContent(beforeCrashResource)))
				+ "\n\t### After crash difference in builder state and resource: "
				+ Joiner.on("\n\t\t- ").join(difference(afterCrashBuilderState, getContent(afterCrashResource)))
				+ "\n--------------------------------------------------------------------------------";

	}

	private Set<org.eclipse.emf.common.util.URI> getContent(Iterable<? extends EObject> contents) {
		return from(contents).filter(ResourceDescriptionImpl.class).transform(desc -> desc.getURI()).toSet();
	}

}
