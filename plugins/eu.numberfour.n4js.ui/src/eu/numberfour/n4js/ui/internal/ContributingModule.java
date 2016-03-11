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
package eu.numberfour.n4js.ui.internal;

import static com.google.inject.Scopes.SINGLETON;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.xtext.builder.builderState.IBuilderState;
import org.eclipse.xtext.builder.clustering.CurrentDescriptions;
import org.eclipse.xtext.builder.impl.IToBeBuiltComputerContribution;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.LiveShadowedResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.resource.impl.ResourceSetBasedResourceDescriptions;
import org.eclipse.xtext.ui.resource.IResourceSetInitializer;
import org.eclipse.xtext.ui.resource.IStorage2UriMapperContribution;
import org.eclipse.xtext.ui.shared.contribution.IEagerContribution;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.name.Names;

import eu.numberfour.n4js.binaries.BinariesPreferenceStore;
import eu.numberfour.n4js.binaries.BinariesProvider;
import eu.numberfour.n4js.binaries.BinariesValidator;
import eu.numberfour.n4js.binaries.OsgiBinariesPreferenceStore;
import eu.numberfour.n4js.binaries.nodejs.NodeJsBinary;
import eu.numberfour.n4js.binaries.nodejs.NpmBinary;
import eu.numberfour.n4js.external.EclipseExternalLibraryWorkspace;
import eu.numberfour.n4js.external.EclipseTargetPlatformInstallLocationProvider;
import eu.numberfour.n4js.external.ExternalLibraryBuildJobProvider;
import eu.numberfour.n4js.external.ExternalLibraryBuilderHelper;
import eu.numberfour.n4js.external.ExternalLibraryUriHelper;
import eu.numberfour.n4js.external.ExternalLibraryWorkspace;
import eu.numberfour.n4js.external.ExternalProjectCacheLoader;
import eu.numberfour.n4js.external.ExternalProjectProvider;
import eu.numberfour.n4js.external.ExternalProjectsCollector;
import eu.numberfour.n4js.external.ProjectStateChangeListener;
import eu.numberfour.n4js.external.RebuildWorkspaceProjectsScheduler;
import eu.numberfour.n4js.external.TargetPlatformInstallLocationProvider;
import eu.numberfour.n4js.internal.FileBasedExternalPackageManager;
import eu.numberfour.n4js.internal.InternalN4JSWorkspace;
import eu.numberfour.n4js.internal.N4JSModel;
import eu.numberfour.n4js.preferences.ExternalLibraryPreferenceStore;
import eu.numberfour.n4js.preferences.OsgiExternalLibraryPreferenceStore;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.ui.containers.CompositeStorage2UriMapperContribution;
import eu.numberfour.n4js.ui.containers.N4JSExternalLibraryStorage2UriMapperContribution;
import eu.numberfour.n4js.ui.containers.N4JSToBeBuiltComputer;
import eu.numberfour.n4js.ui.containers.NfarStorageMapper;
import eu.numberfour.n4js.ui.projectModel.IN4JSEclipseCore;
import eu.numberfour.n4js.ui.scoping.builtin.ScopeInitializer;
import eu.numberfour.n4js.utils.StatusHelper;
import eu.numberfour.n4js.utils.process.ProcessResultFactory;

/**
 */
@SuppressWarnings("restriction")
public class ContributingModule implements Module {

	@Override
	public void configure(Binder binder) {
		binder.bind(IToBeBuiltComputerContribution.class).to(N4JSToBeBuiltComputer.class);
		binder.bind(IStorage2UriMapperContribution.class).to(CompositeStorage2UriMapperContribution.class);
		binder.bind(IN4JSCore.class).to(IN4JSEclipseCore.class);
		binder.bind(IN4JSEclipseCore.class).to(N4JSEclipseCore.class);
		binder.bind(NfarStorageMapper.class);
		binder.bind(N4JSModel.class);
		binder.bind(InternalN4JSWorkspace.class).to(EclipseBasedN4JSWorkspace.class);
		binder.bind(EclipseBasedN4JSWorkspace.class);
		binder.bind(IWorkspaceRoot.class).toProvider(new Provider<IWorkspaceRoot>() {
			@Inject
			IWorkspace workspace;

			@Override
			public IWorkspaceRoot get() {
				return workspace.getRoot();
			}
		});
		binder.bind(StatusHelper.class);
		binder.bind(TargetPlatformInstallLocationProvider.class).to(EclipseTargetPlatformInstallLocationProvider.class)
				.in(SINGLETON);
		binder.bind(ExternalProjectCacheLoader.class);
		binder.bind(ExternalLibraryWorkspace.class).to(EclipseExternalLibraryWorkspace.class).in(SINGLETON);
		binder.bind(ProjectStateChangeListener.class);
		binder.bind(ExternalLibraryBuildJobProvider.class);
		binder.bind(ExternalLibraryBuilderHelper.class);
		binder.bind(N4JSExternalLibraryStorage2UriMapperContribution.class);
		binder.bind(ExternalProjectsCollector.class);
		binder.bind(ExternalProjectProvider.class);
		binder.bind(RebuildWorkspaceProjectsScheduler.class);
		binder.bind(N4JSEclipseModel.class);
		binder.bind(ExternalLibraryUriHelper.class);
		binder.bind(FileBasedExternalPackageManager.class);
		binder.bind(ExternalLibraryPreferenceStore.class).to(OsgiExternalLibraryPreferenceStore.class).in(SINGLETON);
		binder.bind(OsgiExternalLibraryPreferenceStore.class);
		binder.bind(XtextResourceSet.class);
		binder.bind(IEagerContribution.class).to(ProjectDescriptionLoadListener.class);
		binder.bind(ProjectDescriptionLoadListener.Strategy.class).to(N4MFProjectDependencyStrategy.class);
		binder.bind(IResourceSetInitializer.class).to(ScopeInitializer.class);
		binder.bind(ClassLoader.class).toInstance(getClass().getClassLoader());

		binder.bind(ResourceDescriptionsProvider.class);
		binder.bind(ResourceSetBasedResourceDescriptions.class);
		binder.bind(IResourceDescriptions.class)
				.annotatedWith(Names.named(ResourceDescriptionsProvider.LIVE_SCOPE))
				.to(LiveShadowedResourceDescriptions.class);
		binder.bind(IResourceDescriptions.class)
				.annotatedWith(Names.named(ResourceDescriptionsProvider.NAMED_BUILDER_SCOPE))
				.to(CurrentDescriptions.ResourceSetAware.class);
		binder.bind(IResourceDescriptions.class)
				.annotatedWith(Names.named(ResourceDescriptionsProvider.PERSISTED_DESCRIPTIONS))
				.to(IBuilderState.class);

		binder.bind(ProcessResultFactory.class).in(SINGLETON);
		binder.bind(BinariesPreferenceStore.class).to(OsgiBinariesPreferenceStore.class).in(SINGLETON);
		binder.bind(BinariesValidator.class).in(SINGLETON);
		binder.bind(BinariesProvider.class).in(SINGLETON);

		binder.bind(NodeJsBinary.class).in(SINGLETON);
		binder.bind(NpmBinary.class).in(SINGLETON);

	}

}
