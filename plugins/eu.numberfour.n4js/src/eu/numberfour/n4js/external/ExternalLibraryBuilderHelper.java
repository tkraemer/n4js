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
package eu.numberfour.n4js.external;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Maps.newHashMap;
import static org.eclipse.core.resources.IncrementalProjectBuilder.CLEAN_BUILD;
import static org.eclipse.core.resources.IncrementalProjectBuilder.FULL_BUILD;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.eclipse.core.internal.events.BuildManager;
import org.eclipse.core.internal.resources.BuildConfiguration;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.xtext.xbase.lib.Exceptions;

import com.google.common.base.Function;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import eu.numberfour.n4js.utils.Arrays2;
import eu.numberfour.n4js.utils.resources.ExternalProject;
import eu.numberfour.n4js.utils.resources.ExternalProjectBuildOrderProvider;

/**
 * Helper class for building projects outside from the {@link IWorkspace Eclipse workspace} directly with the
 * {@link BuildManager build manager}.
 */
@SuppressWarnings("restriction")
@Singleton
public class ExternalLibraryBuilderHelper {

	private static final Logger LOGGER = Logger.getLogger(ExternalLibraryBuilderHelper.class);

	/**
	 * Build argument to be able to distinguish between workspace and external library builds.
	 */
	public static final String EXTERNAL_BUILD = ExternalLibraryBuilderHelper.class + ".externalBuild";

	/**
	 * Function for converting a {@link IProject project} into the corresponding {@link IBuildConfiguration build
	 * configuration}.
	 */
	private static Function<IProject, IBuildConfiguration> TO_CONFIG_FUNC = project -> {
		try {
			return project.getActiveBuildConfig();
		} catch (final CoreException e) {
			LOGGER.error("Error while getting active build configuration for project: " + project, e);
			return new BuildConfiguration(project);
		}
	};

	/**
	 * Function for converting several {@link IProject project} instances into their corresponding
	 * {@link IBuildConfiguration build configuration} representations. The order of the build configuration elements
	 * will be identical with the order of the projects argument.
	 */
	private static Function<Iterable<? extends IProject>, IBuildConfiguration[]> TO_CONFIGS_FUNC = projects -> {
		return from(projects).transform(TO_CONFIG_FUNC).toArray(IBuildConfiguration.class);
	};

	@Inject
	private ExternalProjectsCollector collector;

	/**
	 * Performs a full build on all registered and available external libraries.
	 * <p>
	 * This is a blocking operation.
	 */
	public void build() {
		build(new NullProgressMonitor());
	}

	/**
	 * Sugar for {@link #build()} but the operation can be monitored via the monitor argument.
	 *
	 * @param monitor
	 *            the monitor for the progress. Must not be {@code null}.
	 */
	public void build(final IProgressMonitor monitor) {
		final Iterable<ExternalProject> projects = collector.collectExternalProjects();
		final IBuildConfiguration[] buildOrder = ExternalProjectBuildOrderProvider.getBuildOrder(projects);
		build(buildOrder, monitor);
	}

	/**
	 * Full builds the project.
	 *
	 * @param project
	 *            the project to build.
	 */
	public void build(final IProject project) {
		build(project, new NullProgressMonitor());
	}

	/**
	 * Performs a full build on the given project. Same as {@link #build(IProject)} but a monitor can be provided for
	 * the running process.
	 *
	 * @param project
	 *            the project to build.
	 * @param monitor
	 *            the monitor for the full build operation.
	 */
	public void build(final IProject project, final IProgressMonitor monitor) {
		build(new IBuildConfiguration[] { new BuildConfiguration(project) }, monitor);
	}

	/**
	 * Sugar for performing a full build on multiple {@link IProject project} instances.
	 *
	 * @param projects
	 *            the projects that has to be build.
	 * @param monitor
	 *            monitor for the build process.
	 */
	public void build(final Iterable<IProject> projects, final IProgressMonitor monitor) {
		build(TO_CONFIGS_FUNC.apply(projects), monitor);
	}

	/**
	 * Sugar for performing a full build on multiple {@link IProject project} instances.
	 *
	 * @param projects
	 *            the projects that has to be build.
	 */
	public void builds(final Iterable<IProject> projects) {
		build(TO_CONFIGS_FUNC.apply(projects), new NullProgressMonitor());
	}

	/**
	 * Full builds the projects given as an array of build configuration.
	 *
	 * @param buildOrder
	 *            the ordered build configuration representing the project to be built and the build order as well.
	 * @param monitor
	 *            the monitor for the progress. Must not be {@code null}.
	 */
	public void build(final IBuildConfiguration[] buildOrder, final IProgressMonitor monitor) {
		if (!Arrays2.isEmpty(buildOrder)) {
			final BuildManager buildManager = getBuildManager();
			LOGGER.info("Building external libraries: " + Arrays.toString(buildOrder));
			final SubMonitor subMonitor = SubMonitor.convert(monitor, buildOrder.length);
			for (final IBuildConfiguration configuration : buildOrder) {

				final Map<String, String> args = newHashMap();
				args.put(EXTERNAL_BUILD, Boolean.TRUE.toString());

				final Job job = new WorkspaceJob("Building external library: " + configuration.getProject()) {

					@Override
					public IStatus runInWorkspace(final IProgressMonitor unused) throws CoreException {
						buildManager.build(configuration, FULL_BUILD, N4JSExternalProject.BUILDER_ID, args,
								subMonitor.newChild(1));

						return Status.OK_STATUS;
					}

				};

				waitForWorkspaceLock(subMonitor);
				job.schedule();

				try {
					job.join();
				} catch (final InterruptedException e) {
					LOGGER.error("Error occurred while building external libraries.", e);
				}

			}
		}
	}

	/**
	 * Cleans all registered and available external libraries.
	 * <p>
	 * This is a blocking operation.
	 */
	public void clean() {
		clean(new NullProgressMonitor());
	}

	/**
	 * Sugar for {@link #clean()} but the operation can be monitored via the monitor argument.
	 *
	 * @param monitor
	 *            the monitor for the progress. Must not be {@code null}.
	 */
	public void clean(final IProgressMonitor monitor) {
		final Iterable<ExternalProject> projects = collector.collectExternalProjects();
		final IBuildConfiguration[] buildOrder = ExternalProjectBuildOrderProvider.getBuildOrder(projects);
		clean(buildOrder, monitor);
	}

	/**
	 * Cleans the given project without rebuilding it.
	 *
	 * @param project
	 *            the project that has to be cleaned (without rebuilding it).
	 */
	public void clean(final IProject project) {
		clean(project, new NullProgressMonitor());
	}

	/**
	 * Cleans the given project. Same as {@link #clean(IProject)} but additional {@link IProgressMonitor monitor} can be
	 * provided for the operation.
	 *
	 * @param project
	 *            the project that has to be cleaned (without rebuilding it).
	 * @param monitor
	 *            monitor for the clean process.
	 */
	public void clean(final IProject project, final IProgressMonitor monitor) {
		clean(new IBuildConfiguration[] { getBuildConfiguration(project) }, monitor);
	}

	/**
	 * Sugar for cleaning multiple {@link IProject project} instances.
	 *
	 * @param projects
	 *            the projects that has to be cleaned (without rebuilding it).
	 * @param monitor
	 *            monitor for the clean process.
	 */
	public void clean(final Iterable<IProject> projects, final IProgressMonitor monitor) {
		clean(TO_CONFIGS_FUNC.apply(projects), monitor);
	}

	/**
	 * Performs a clean (without rebuild) on the projects given as an array of build configuration. The clean order is
	 * identical with the order of the elements in the {@code buildOrder} argument.
	 *
	 * @param buildOrder
	 *            the ordered build configuration representing the order and the project to be cleaned.
	 * @param monitor
	 *            the monitor for the progress. Must not be {@code null}.
	 */
	public void clean(final IBuildConfiguration[] buildOrder, final IProgressMonitor monitor) {
		if (!Arrays2.isEmpty(buildOrder)) {
			final BuildManager buildManager = getBuildManager();
			LOGGER.info("Cleaning external libraries: " + Arrays.toString(buildOrder));
			final SubMonitor subMonitor = SubMonitor.convert(monitor, buildOrder.length);
			for (final IBuildConfiguration configuration : buildOrder) {

				final Map<String, String> args = newHashMap();
				args.put(EXTERNAL_BUILD, Boolean.TRUE.toString());

				final Job job = new WorkspaceJob("Cleaning external library: " + configuration.getProject()) {

					@Override
					public IStatus runInWorkspace(final IProgressMonitor unused) throws CoreException {
						buildManager.build(configuration, CLEAN_BUILD, N4JSExternalProject.BUILDER_ID, args,
								subMonitor.newChild(1));

						return Status.OK_STATUS;
					}

				};

				waitForWorkspaceLock(subMonitor);
				job.schedule();

				try {
					Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, monitor);
				} catch (final OperationCanceledException e) {
					LOGGER.info("User abort.");
				} catch (final InterruptedException e) {
					LOGGER.error("Error occurred while waiting for workspace job.", e);
				}

				try {
					job.join();
				} catch (final InterruptedException e) {
					LOGGER.error("Error occurred while cleaning external libraries.", e);
				}

			}
		}
	}

	private IBuildConfiguration getBuildConfiguration(final IProject project) {
		return TO_CONFIG_FUNC.apply(project);
	}

	private BuildManager getBuildManager() {
		final Workspace workspace = (Workspace) ResourcesPlugin.getWorkspace();
		return workspace.getBuildManager();
	}

	/**
	 * Jobs accessing this code should be configured as "system" jobs, to not interrupt autobuild jobs.
	 *
	 * @param monitor
	 *            the monitor for the wait process.
	 */
	private void waitForWorkspaceLock(final IProgressMonitor monitor) {
		// Wait for the workspace lock to avoid starting the external build.
		final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		try {
			Job.getJobManager().beginRule(root, monitor);
		} catch (final OperationCanceledException e) {
			return;
		} finally {
			Job.getJobManager().endRule(root);
		}
	}

	@SuppressWarnings("unused")
	private void waitForIdleBuildManager(final long timeout) {
		final long now = System.currentTimeMillis();
		while (System.currentTimeMillis() < now + timeout) {
			final BuildManager buildManager = getBuildManager();
			Field declaredField;
			try {
				declaredField = buildManager.getClass().getDeclaredField("building");
				declaredField.setAccessible(true);
				final Object object = declaredField.get(buildManager);
				if (Boolean.FALSE.equals(object)) {
					return;
				}
				LOGGER.warn("Build manager was busy. Waiting for its idle state...");
			} catch (final Exception e) {
				LOGGER.error("Error while checking whether build manager is idle or not.", e);
				return;
			}
		}
		Exceptions.sneakyThrow(new TimeoutException("Timeouted while waiting for idle build manager."));
	}

}
