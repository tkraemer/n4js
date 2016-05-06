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
package eu.numberfour.n4js.ui.workingsets;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.RepositoryCache;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.jgit.lib.Repository;

/**
 * Working set manager based on Git repositories.
 */
@SuppressWarnings("restriction")
public class GitRepositoryAwareWorkingSetManager extends ImmutableWorkingSetManager {

	private final RepositoryCache repositoryCache;

	/**
	 * Sole constructor for creating the working set manager. Internally initializes the cache for repositories.
	 */
	public GitRepositoryAwareWorkingSetManager() {
		repositoryCache = Activator.getDefault().getRepositoryCache();
	}

	@Override
	public String getId() {
		return GitRepositoryAwareWorkingSetManager.class.getName();
	}

	@Override
	public String getLabel() {
		return "Git Repository";
	}

	@Override
	protected List<WorkingSet> initializeWorkingSets() {
		final Collection<Repository> repositories = newArrayList(repositoryCache.getAllRepositories());
		repositories.add(null); // For 'Others'.
		return newArrayList(from(repositories)
				.transform(repository -> new GitRepositoryWorkingSet(repository, this)));
	}

	private static final class GitRepositoryWorkingSet implements WorkingSet {

		private final WorkingSetManager manager;
		private final String rootUri;
		private String label;

		private GitRepositoryWorkingSet(/* nullable */final Repository repository, final WorkingSetManager manager) {
			if (repository == null) {
				rootUri = null;
				label = OTHERS_WORKING_SET_LABEL;
			} else {
				final File directory = repository.getDirectory().getParentFile();
				rootUri = toUriString(directory.toURI());
				label = directory.getName();
			}
			this.manager = manager;
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public IAdaptable[] getElements() {
			if (label.contains("datamodel")) {
				System.out.println();
			}
			final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			final IProject[] elements = new IProject[projects.length];
			int elementCount = 0;
			for (int i = 0, size = projects.length; i < size; i++) {
				final IProject project = projects[i];
				if (null == rootUri) {
					if (!ResourceUtil.isSharedWithGit(project)) {
						elements[elementCount++] = project;
					}
				} else {
					if (toUriString(project.getLocationURI()).startsWith(rootUri)) {
						elements[elementCount++] = project;
					}
				}
			}
			return Arrays.copyOfRange(elements, 0, elementCount);
		}

		/**
		 * Returns with the {@link URI#toString()} of the argument. Trims the trailing forward slash if any.
		 */
		private String toUriString(final URI uri) {
			final String uriString = uri.toString();
			final int length = uriString.length();
			if (uriString.charAt(length - 1) == '/') {
				return uriString.substring(0, length - 1);
			}
			return uriString;
		}

		@Override
		public WorkingSetManager getWorkingSetManager() {
			return manager;
		}

		@Override
		public String toString() {
			return getLabel();
		}

	}

}
