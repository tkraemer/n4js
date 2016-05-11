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

import static com.google.common.base.Strings.nullToEmpty;
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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.RepositoryCache;
import org.eclipse.egit.core.RepositoryUtil;
import org.eclipse.egit.core.internal.util.ResourceUtil;
import org.eclipse.jgit.lib.Repository;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

import com.google.common.base.Splitter;
import com.google.inject.Inject;

/**
 * Working set manager based on Git repositories.
 */
@SuppressWarnings("restriction")
public class GitRepositoryAwareWorkingSetManager extends WorkingSetManagerImpl {

	@Inject
	private WorkingSetManagerBroker workingSetManagerBroker;

	private final RepositoryCache repositoryCache;
	private final IPreferenceChangeListener repositoryChangeListener;

	/**
	 * Sole constructor for creating the working set manager. Internally initializes the cache for repositories.
	 */
	public GitRepositoryAwareWorkingSetManager() {
		repositoryCache = Activator.getDefault().getRepositoryCache();
		repositoryChangeListener = new IPreferenceChangeListener() {

			@Override
			public void preferenceChange(final PreferenceChangeEvent event) {
				if (!RepositoryUtil.PREFS_DIRECTORIES.equals(event.getKey())) {
					return;
				}

				final String newValue = (String) event.getNewValue();
				final String oldValue = (String) event.getOldValue();

				if (!orderedWorkingSetNames.isEmpty() && !visibleWorkingSetNames.isEmpty()) {

					// Deletion
					if (newValue == null) {

						final String name = getRepositoryName(oldValue);
						orderedWorkingSetNames.remove(name);
						visibleWorkingSetNames.remove(name);

						// Addition
					} else if (oldValue == null) {

						final String name = getRepositoryName(newValue);
						orderedWorkingSetNames.add(name);
						visibleWorkingSetNames.add(name);

					} // Update is not handled

				}

				discardWorkingSetState();
				saveState(new NullProgressMonitor());

				if (workingSetManagerBroker.isWorkingSetTopLevel()) {
					final WorkingSetManager activeManger = workingSetManagerBroker.getActiveManger();
					if (activeManger != null) {
						if (activeManger.getId().equals(getId())) {
							workingSetManagerBroker.refreshNavigator();
						}
					}
				}

			}

			private String getRepositoryName(final String localUrl) {
				final List<String> fragments = Splitter.on("/")
						.trimResults()
						.omitEmptyStrings()
						.splitToList(nullToEmpty(localUrl));
				return fragments.get(fragments.size() - 2);
			}

		};

		final IEclipsePreferences gitNode = InstanceScope.INSTANCE.getNode(Activator.getPluginId());
		gitNode.addPreferenceChangeListener(repositoryChangeListener);

		final BundleContext context = Activator.getDefault().getBundle().getBundleContext();
		context.addBundleListener(new BundleListener() {

			@Override
			public void bundleChanged(final BundleEvent event) {
				if (BundleEvent.STOPPING == event.getType()) {
					gitNode.removePreferenceChangeListener(repositoryChangeListener);
				}
			}

		});
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
		private String name;

		private GitRepositoryWorkingSet(/* nullable */final Repository repository, final WorkingSetManager manager) {
			if (repository == null) {
				rootUri = null;
				name = OTHERS_WORKING_SET_LABEL;
			} else {
				final File directory = repository.getDirectory().getParentFile();
				rootUri = toUriString(directory.toURI());
				name = directory.getName();
			}
			this.manager = manager;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public IAdaptable[] getElements() {
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
			return getName();
		}

	}

}
