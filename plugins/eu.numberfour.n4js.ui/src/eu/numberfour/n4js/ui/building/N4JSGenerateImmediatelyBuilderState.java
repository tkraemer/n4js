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
package eu.numberfour.n4js.ui.building;

import static com.google.common.collect.Sets.newLinkedHashSet;
import static eu.numberfour.n4js.projectModel.IN4JSProject.N4MF_MANIFEST;
import static eu.numberfour.n4js.ui.internal.N4JSActivator.EU_NUMBERFOUR_N4JS_N4JS;

import java.security.MessageDigest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.BuildType;
import org.eclipse.xtext.builder.clustering.ClusteringBuilderState;
import org.eclipse.xtext.builder.clustering.CurrentDescriptions;
import org.eclipse.xtext.builder.impl.BuildData;
import org.eclipse.xtext.builder.impl.RegistryBuilderParticipant;
import org.eclipse.xtext.builder.impl.RegistryBuilderParticipant.DeferredBuilderParticipant;
import org.eclipse.xtext.builder.impl.ToBeBuilt;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.inject.Inject;
import com.google.inject.Injector;

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.external.ExternalLibraryWorkspace;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.resource.N4JSResourceDescriptionManager;
import eu.numberfour.n4js.ts.types.TModule;
import eu.numberfour.n4js.ui.building.instructions.IBuildParticipantInstruction;
import eu.numberfour.n4js.ui.internal.ContributingResourceDescriptionPersister;
import eu.numberfour.n4js.ui.internal.N4JSActivator;
import eu.numberfour.n4js.ui.projectModel.IN4JSEclipseCore;

/**
 * Produces the compiled js files immediately after the validation in order save CPU cycles, e.g. the file is already
 * loaded and linked.
 *
 * <p>
 * N4JSGenerateImmediatelyBuilderState resp. its super class ClusteringBuilderState is the back bone of the Xtext build.
 * It performs creating / up- dating the Xtext Index as well as delegating to all registered builder participants. The
 * main adaption here is to set our class N4JSStatefulBuilderParticipant as adapter to the resource set.
 *
 * <p>
 * This part describes how the incremental builder works as of 26.04.2016. This also introduces a brief description how
 * the incremental builder was changes in the scope of GH-134.
 *
 * <p>
 * Assume the following three projects <b>PA</b>, <b>PB</b> and <b>PC</b> where <b>PB</b> depends on <b>PA</b> and
 * <b>PC</b> depends on <b>PB</b>, hence <b>PC</b> transitively depends on <b>PA</b>. Each project has one single module
 * with one single public exported class, <b>PA</b> has {@code A}, <b>PB</b> has {@code B} and <b>PC</b> has {@code C}.
 * Class {@code A} is extended by {@code B} and class {@code C} extends {@code B}, hence {@code C} has an implicit
 * {@code A} super type.
 *
 * <p>
 * The classes have the below implementations:
 *
 * <pre>
 * export public class A { public foo(): void { } }
 *
 * export public class B extends A { }
 *
 * export public class C extends B { public bar(): { this.foo(); } }
 * </pre>
 *
 * Also let assume our workspace contains no other projects and any external libraries are available. Furthermore, the
 * workspace contains neither validation warnings nor errors. Our intention is to get rid of the public {@code foo}
 * method from class {@code A} and we expect a validation error in class project <b>PC</b> at class {@code C}. After
 * getting rid of the method {@code foo} in class {@code A} and saving the editor content the incremental builder kicks
 * in and we arrive in the {@link ClusteringBuilderState}.
 *
 * First iteration with project <b>PA</b> (as of 26.04.2016):
 * <p>
 * <ol>
 * <li>{@link ToBeBuilt} contains one URI that has to be updated. The URI of the module with class {@code A}.</li>
 * <li>After calculating all other available resource URIs we will have a set of all workspace URIs but the module that
 * contains class {@code A}.</li>
 * <li>All deltas is initially empty at this point and the build queue contains module A and the manifest of project
 * <b>PA</b>.</li>
 * <li>While iterating through the build queue we recognize that class {@code A} in module A has changed, hence the
 * corresponding serialized {@link TModule} state differs between the old and the new state, hence we put module A into
 * the changed deltas.</li>
 * <li>After processing all elements in the current build queue, we have to queue all affected resources as well via the
 * {@link #queueAffectedResources(Set, IResourceDescriptions, CurrentDescriptions, Collection, Collection, BuildData, IProgressMonitor)
 * queueAffectedResources} method.</li>
 * <li>This method will consider module B for class {@code B} as an affected one (since {@code B} imports the
 * {@link QualifiedName FQN} of class {@code A} into {@code B} and the is a direct dependency between the container
 * projects) <b>AND</b> will wrap the {@link ResourceDescriptionsData} into a custom resource description delta that
 * hides the obsolete serialized {@link TModule} information.</li>
 * <li>The clustering builder after re-validating and re-generating module A will return with a set of deltas containing
 * module A and the manifest of project <b>PA</b>.</li>
 * </ol>
 *
 * <p>
 * Due to the changed deltas and the {@link IProjectDescription#getDynamicReferences() dynamic} project references we
 * will arrive in the clustering builder state again with the project <b>PB</b>. The {@link ToBeBuilt} instance will be
 * empty but from the previous cycle the module for class {@code B} has been queued. Since in the previous cycle we have
 * "invalidated" the serialized {@link TModule} information for module B we will consider class {@code B} as a changed
 * one and based on the above described workflow we will rebuild module B and queue module C.
 *
 * <p>
 * Introduced changes with GH-134:
 * <p>
 * The serialized {@link TModule} information is not considered any more when checking whether a
 * {@link ResourceDescriptionsData} represents a change or not. That user data information will still exist on the
 * {@link IEObjectDescription} of the module and can be invalidated by wrapping it into a custom resource description
 * delta but will be ignored when comparing the user data keys values. Instead of that, a unique {@link MessageDigest}
 * is calculated from the serialized {@link TModule} and the will be involved in the comparison logic. Based on the
 * above example, module B with class {@code B} will <b>NOT</b> be considered as a change in the second iteration, hence
 * class {@code C} will not be queued, hence revalidated and no validation errors would be generated for class {@code C}
 * which is the proper behavior and our expectation as well.</li>
 *
 * <p>
 * To tackle the transitive dependency issues we have to modify the order of the processed resources when queuing the
 * affected ones. To achieve that the resource URIs are being processed after a topological sort (based on the project
 * dependencies). After the sorting it is ensured that module A, then B finally C will be the order when visiting all
 * resource URIs for the affected ones. Furthermore the {@link N4JSResourceDescriptionManager} will consider not only
 * direct dependencies but will check if there is a direct dependency to an already enqueued resource, if so, then
 * queues the current candidate as well.
 *
 *
 */
@SuppressWarnings("restriction")
public class N4JSGenerateImmediatelyBuilderState extends ClusteringBuilderState {

	@Inject
	private RegistryBuilderParticipant builderParticipant;

	@Inject
	private ContributingResourceDescriptionPersister descriptionPersister;

	private final Supplier<IN4JSEclipseCore> core = Suppliers.memoize(() -> getN4jsEclipseCore());

	private IN4JSEclipseCore getN4jsEclipseCore() {
		return N4JSActivator.getInstance().getInjector(EU_NUMBERFOUR_N4JS_N4JS).getInstance(IN4JSEclipseCore.class);
	}

	/**
	 * After the load phase, checks whether the underlying index content is empty or a recovery builder was scheduled,
	 * if so, populates the index content with the external libraries as well.
	 */
	@Override
	public synchronized void load() {
		super.load();
		// On the very first startup there will be recovery build.
		if (descriptionPersister.isRecoveryBuildRequired()) {
			descriptionPersister.scheduleRecoveryBuildOnContributions();
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * Initializes an adapter which is attached to the builder's resource set. This adapter will be used later on to
	 * process each delta after the corresponding resource was validated.
	 *
	 * @param buildData
	 *            the data that should be considered for the update
	 * @param newData
	 *            the new resource descriptions as they are to be persisted (the new index after the build). Initially
	 *            contains the old resource descriptions.
	 * @param monitor
	 *            The progress monitor
	 * @return A list of deltas describing all changes made by the build.
	 */
	@Override
	protected Collection<Delta> doUpdate(BuildData buildData, ResourceDescriptionsData newData,
			IProgressMonitor monitor) {

		IProject project = getProject(buildData);
		try {
			BuildType buildType = N4JSBuildTypeTracker.getBuildType(project);
			IBuildParticipantInstruction instruction;
			if (buildType == null) {
				instruction = IBuildParticipantInstruction.NOOP;
			} else {
				instruction = findJSBuilderParticipant().prepareBuild(project, buildType);
			}
			// removed after the build automatically;
			// the resource set is discarded afterwards, anyway
			buildData.getResourceSet().eAdapters().add(instruction);
		} catch (CoreException e) {
			handleCoreException(e);
		}
		return super.doUpdate(buildData, newData, monitor);
	}

	@Override
	protected void updateMarkers(Delta delta, ResourceSet resourceSet, IProgressMonitor monitor) {
		super.updateMarkers(delta, resourceSet, monitor);
		if (resourceSet != null) { // resourceSet is null during clean build
			IBuildParticipantInstruction instruction = (IBuildParticipantInstruction) EcoreUtil.getAdapter(
					resourceSet.eAdapters(), IBuildParticipantInstruction.class);
			if (instruction == null) {
				throw new IllegalStateException();
			}
			try {
				instruction.process(delta, resourceSet, monitor);
			} catch (CoreException e) {
				handleCoreException(e);
			}
		}
	}

	@Override
	protected void clearResourceSet(final ResourceSet resourceSet) {
		N4JSResourceSetCleanerUtils.clearResourceSet(resourceSet);
	}

	private IProject getProject(BuildData buildData) {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(buildData.getProjectName());
		if (null == project || !project.isAccessible()) {
			final IProject externalProject = getExternalLibraryWorkspace().getProject(buildData.getProjectName());
			if (null != externalProject && externalProject.exists()) {
				project = externalProject;
			}
		}
		return project;
	}

	private N4JSBuilderParticipant findJSBuilderParticipant() {
		ImmutableList<IXtextBuilderParticipant> all = builderParticipant.getParticipants();
		for (IXtextBuilderParticipant candidate : all) {
			if (candidate instanceof DeferredBuilderParticipant) {
				DeferredBuilderParticipant dbp = (DeferredBuilderParticipant) candidate;
				if (isParticipating(dbp)) {
					IXtextBuilderParticipant delegate = dbp.getDelegate();
					if (delegate instanceof N4JSBuilderParticipant) {
						return (N4JSBuilderParticipant) delegate;
					}
				}
			}
			// N4JSBuilderParticipant is never directly used, it's always delegated to via an DeferredBuilderParticipant
		}
		throw new IllegalStateException();
	}

	// cannot be injected with annotation as there will be wrong injection context
	// @Inject FileExtensionProvider fileExtensionProvider
	// doesn't work neither
	// FileExtensionProvider fileExtensionProvider =
	// N4JSActivator.getInstance().getInjector(N4JSActivator.EU_NUMBERFOUR_IDE_N4JS_N4JS).getProvider(FileExtensionProvider.class).get();

	/**
	 * Check if given build participant is supporting given file type
	 */
	private boolean isParticipating(DeferredBuilderParticipant dbp) {
		// TODO switch hardcoded extensions to FileExtensionProvider query
		for (String ext : N4JSGlobals.ALL_N4_FILE_EXTENSIONS) {
			if (dbp.isParticipating(ext)) {
				return true;
			}
		}
		return false;
	}

	private void handleCoreException(CoreException e) {
		N4JSActivator.getInstance().getLog()
				.log(new Status(IStatus.ERROR, N4JSActivator.EU_NUMBERFOUR_N4JS_N4JS, e.getMessage(), e));
	}

	/**
	 * Overriding this method to make sure that resources of all affected URIs are fully re-loaded if needed, instead of
	 * only loading the TModule from the corresponding resource description.
	 * <p>
	 * This is required in case the URIs in an affected resource contain indices of a changed resource; just loading the
	 * TModule from the user data won't update these indices. For details see the example provided in IDEBUG-347.
	 * <p>
	 * NOTE: this should be removed once the URI scheme has been changed to use names instead of indices.
	 */
	@Override
	protected void queueAffectedResources(
			Set<URI> allRemainingURIs,
			IResourceDescriptions oldState,
			CurrentDescriptions newState,
			Collection<Delta> changedDeltas,
			Collection<Delta> allDeltas,
			BuildData buildData,
			final IProgressMonitor monitor) {

		// If 'all deltas' is empty and no remaining URIs are available we will not hit the resource description manager
		// anyway.
		if (!allDeltas.isEmpty() && !allRemainingURIs.isEmpty()) {
			final Collection<URI> copyAllRemainingURIs = newLinkedHashSet(allRemainingURIs);
			final IN4JSEclipseCore eclipseCore = core.get();
			final Multimap<IN4JSProject, URI> projectMapping = Multimaps.index(copyAllRemainingURIs,
					uri -> {
						return eclipseCore.findProject(uri).orNull();
					});
			allRemainingURIs.clear();
			final IN4JSProject[] sortedProjects = eclipseCore.getAllAccessibleProjectsSorted();
			for (int i = sortedProjects.length - 1; i >= 0; i--) {
				final IN4JSProject p = sortedProjects[i];
				final Collection<URI> collection = projectMapping.get(p);
				if (null != collection) {
					allRemainingURIs.addAll(collection);
					copyAllRemainingURIs.removeAll(collection);
				}
			}

			// Just add all unvisited URIs, consider manifest deletion.
			allRemainingURIs.addAll(copyAllRemainingURIs);
		}

		// don't wanna copy super-class method, so using this helper to get the set of affected URIs:
		final Set<URI> affectedURIs = new HashSet<>(allRemainingURIs);

		super.queueAffectedResources(allRemainingURIs, oldState, newState, changedDeltas, allDeltas, buildData,
				monitor);

		// affected URIs have been removed from allRemainingURIs by super class
		affectedURIs.removeAll(allRemainingURIs);

		for (URI currAffURI : affectedURIs) {
			final IResourceDescription resDesc = this.getResourceDescription(currAffURI);
			if (!N4MF_MANIFEST.equals(currAffURI.lastSegment())) {

				/*-
				 * This logic here is required to get rid of the invalid serialized TModules information from the index
				 * which are working with an index based approach. Consider the below example:
				 *
				 * -------Module A------
				 *1    //class XYZ { }
				 *2    function foo() { }
				 *3    export public class A { }
				 *
				 * -------Module B------
				 *1    import { A } from "A"
				 *2    import { C } from "C"
				 *3
				 *4    var arrCC : Array<A>;
				 *5    var t2 : C = new C();
				 *6    t2.m(arrCC);
				 *
				 * -------Module C------
				 *1    import { A } from "A"
				 *2
				 *3    export public class C {
				 *4        m(param : Array<A>) { }
				 *5    }
				 *
				 *
				 * Commenting out line 1 in module A will trigger rebuild of A, and related module B and C in this order.
				 * When loading module B, module C has to be resolved as it imports it, quickly jump to module C and load
				 * class A from module A, class A used to have index 1 (in the serialized TModule in the Xtext index) as
				 * it was the second top level element, but that is not true any more, because 'foo' was just commented out,
				 * so index 1 in module A is not class A any more but 'foo'. With this, line 6 in module B will fail,
				 * because it will think that the method 'm' accepts an array of 'foo' and not A any more.
				 *
				 * The following code will be executed after A was processed and B and C are the "affectedURIs". With this
				 * code, we make sure that the cached TModule of C (in the user data of C's resource description) won't be
				 * used while processing B during proxy resolution.
				 */
				newState.register(new DefaultResourceDescriptionDelta(resDesc,
						new ResourceDescriptionWithoutModuleUserData(resDesc)));
			}
		}
	}

	private ExternalLibraryWorkspace getExternalLibraryWorkspace() {
		final Injector injector = N4JSActivator.getInstance().getInjector(EU_NUMBERFOUR_N4JS_N4JS);
		return injector.getInstance(ExternalLibraryWorkspace.class);
	}

}
