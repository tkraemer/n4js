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
package eu.numberfour.n4js.ui.navigator;

import static com.google.common.collect.Maps.newConcurrentMap;
import static com.google.common.collect.Sets.newHashSet;
import static org.eclipse.core.resources.ResourcesPlugin.getWorkspace;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.IPipelinedTreeContentProvider2;
import org.eclipse.ui.navigator.PipelinedShapeModification;
import org.eclipse.ui.navigator.PipelinedViewerUpdate;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import eu.numberfour.n4js.preferences.ExternalLibraryPreferenceStore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.ui.navigator.internal.N4JSProjectExplorerHelper;
import eu.numberfour.n4js.utils.Arrays2;

/**
 * Customized content provider for tuning the Project Explorer view with N4JS specific content.
 */
@Singleton
public class N4JSProjectExplorerContentProvider extends WorkbenchContentProvider
		implements ExternalLibraryPreferenceStore.StoreUpdatedListener, IPipelinedTreeContentProvider2 {

	private static final Logger LOGGER = Logger.getLogger(N4JSProjectExplorerContentProvider.class);

	@Inject
	private N4JSProjectExplorerHelper helper;

	private final Map<IProject, Object[]> virtualNodeCache = newConcurrentMap();

	/**
	 * Creates a new content provider for the navigator with N4JS content support.
	 *
	 * @param store
	 *            the preference store;
	 */
	@Inject
	public N4JSProjectExplorerContentProvider(final ExternalLibraryPreferenceStore store) {
		store.addListener(this);
		getWorkspace().addResourceChangeListener(new IResourceChangeListener() {

			@Override
			public void resourceChanged(final IResourceChangeEvent event) {

				if (null == event || null == event.getDelta()) {
					return;
				}

				try {
					event.getDelta().accept(delta -> {

						final IResource resource = delta.getResource();
						if (resource instanceof IFile) {
							final IFile file = (IFile) resource;
							if (IN4JSProject.N4MF_MANIFEST.equals(file.getName())) {
								final IProject project = file.getProject();
								if (null != project && project.isAccessible()) {
									virtualNodeCache.remove(project);
								}
							}
						}

						return true;
					});

				} catch (final CoreException e) {
					LOGGER.error("Error while refreshing virtual nodes in navigator.", e);
				}
			}
		});
	}

	@Override
	public Object[] getChildren(final Object element) {

		final Object[] children = super.getChildren(element);

		if (element instanceof IProject && ((IProject) element).isAccessible()) {
			return Arrays2.add(children, getVirtualNodes((IProject) element));
		}

		return children;
	}

	@Override
	public void storeUpdated(final ExternalLibraryPreferenceStore store, final IProgressMonitor monitor) {
		final IProject[] projects = getWorkspace().getRoot().getProjects();
		final SubMonitor subMonitor = SubMonitor.convert(monitor, 1).newChild(projects.length);
		virtualNodeCache.clear();
		for (final IProject project : projects) {
			getVirtualNodes(project);
			subMonitor.worked(1);
		}
	}

	private Object[] getVirtualNodes(final IProject project) {
		Object[] objects = virtualNodeCache.get(project);
		if (null == objects) {
			synchronized (project) {
				objects = virtualNodeCache.get(project);
				if (null == objects) {
					objects = helper.getVirtualNodesForProject(project);
					virtualNodeCache.put(project, objects);
				}
			}
		}
		return objects;
	}

	// ------------------------------------------------------------------------------------
	// IPipelinedTreeContentProvider2
	// ------------------------------------------------------------------------------------

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getPipelinedChildren(final Object aParent, final Set theCurrentChildren) {
		theCurrentChildren.addAll(newHashSet(getChildren(aParent)));
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getPipelinedElements(final Object anInput, final Set theCurrentElements) {
		theCurrentElements.addAll(newHashSet(getElements(anInput)));
	}

	@Override
	public Object getPipelinedParent(final Object anObject, final Object aSuggestedParent) {
		return getParent(anObject);
	}

	@Override
	public PipelinedShapeModification interceptAdd(final PipelinedShapeModification anAddModification) {
		return null;
	}

	@Override
	public PipelinedShapeModification interceptRemove(final PipelinedShapeModification aRemoveModification) {
		return null;
	}

	@Override
	public boolean interceptRefresh(final PipelinedViewerUpdate aRefreshSynchronization) {
		return true;
	}

	@Override
	public boolean interceptUpdate(final PipelinedViewerUpdate anUpdateSynchronization) {
		return true;
	}

	@Override
	public void init(final ICommonContentExtensionSite aConfig) {
		// NOOP
	}

	@Override
	public void restoreState(final IMemento aMemento) {
		// NOOP
	}

	@Override
	public void saveState(final IMemento aMemento) {
		// NOOP
	}

	@Override
	public boolean hasPipelinedChildren(final Object anInput, final boolean currentHasChildren) {
		return currentHasChildren || getChildren(anInput).length > 0;
	}

}
