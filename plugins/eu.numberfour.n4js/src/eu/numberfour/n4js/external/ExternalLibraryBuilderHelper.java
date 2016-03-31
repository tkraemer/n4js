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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Maps.newHashMap;
import static org.eclipse.core.resources.IncrementalProjectBuilder.CLEAN_BUILD;
import static org.eclipse.core.resources.IncrementalProjectBuilder.FULL_BUILD;
import static org.eclipse.core.resources.ResourcesPlugin.getWorkspace;
import static org.eclipse.core.runtime.jobs.Job.RUNNING;
import static org.eclipse.core.runtime.jobs.Job.WAITING;
import static org.eclipse.emf.common.util.URI.createPlatformResourceURI;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.eclipse.core.internal.events.BuildManager;
import org.eclipse.core.internal.resources.BuildConfiguration;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.internal.utils.Messages;
import org.eclipse.core.internal.utils.Policy;
import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
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
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
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

	private static final boolean DEBUG = true;

	static {

		if (DEBUG) {

			Policy.DEBUG_AUTO_REFRESH = true;

			// debug constants
			Policy.DEBUG_BUILD_DELTA = true;
			Policy.DEBUG_BUILD_FAILURE = true;
			Policy.DEBUG_BUILD_INTERRUPT = true;
			Policy.DEBUG_BUILD_INVOKING = true;
			Policy.DEBUG_BUILD_NEEDED = true;
			Policy.DEBUG_BUILD_NEEDED_STACK = true;
			Policy.DEBUG_BUILD_STACK = true;

			Policy.DEBUG_CONTENT_TYPE = true;
			Policy.DEBUG_CONTENT_TYPE_CACHE = true;
			Policy.DEBUG_HISTORY = true;
			Policy.DEBUG_NATURES = true;
			Policy.DEBUG_NOTIFICATIONS = true;
			Policy.DEBUG_PREFERENCES = true;

			// Get timing information for restoring data
			Policy.DEBUG_RESTORE = true;
			Policy.DEBUG_RESTORE_MARKERS = true;
			Policy.DEBUG_RESTORE_MASTERTABLE = true;

			Policy.DEBUG_RESTORE_METAINFO = true;
			Policy.DEBUG_RESTORE_SNAPSHOTS = true;
			Policy.DEBUG_RESTORE_SYNCINFO = true;
			Policy.DEBUG_RESTORE_TREE = true;

			// Get timing information for save and snapshot data
			Policy.DEBUG_SAVE = true;
			Policy.DEBUG_SAVE_MARKERS = true;
			Policy.DEBUG_SAVE_MASTERTABLE = true;

			Policy.DEBUG_SAVE_METAINFO = true;
			Policy.DEBUG_SAVE_SYNCINFO = true;
			Policy.DEBUG_SAVE_TREE = true;
			Policy.DEBUG_STRINGS = true;

		}

	}

	private static final Logger LOGGER = Logger.getLogger(ExternalLibraryBuilderHelper.class);

	/**
	 * Build argument to be able to distinguish between workspace and external library builds.
	 */
	public static final String EXTERNAL_BUILD_KEY = ExternalLibraryBuilderHelper.class + ".externalBuild";

	/**
	 * Timeout (in minutes) to wait for the idle auto build job after cleaning external workspace.
	 */
	private static final long AUTO_BUILD_JOB_WAIT_TIMEOUT_IN_MINUTES = 10L;

	/**
	 * Unique name of the {@code org.eclipse.core.internal.events.AutoBuildJob}.
	 */
	private static final String AUTO_BUILD_JOB_NAME = Messages.events_building_0;

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

	/**
	 * Predicate for filtering out all jobs without {@link Job#RUNNING running} and/or without {@link Job#WAITING
	 * waiting} state.
	 */
	private static final Predicate<Job> SCHEDULED_JOBS_PREDICATE = j -> RUNNING != j.getState()
			&& WAITING != j.getState();

	/**
	 * Predicate for filtering by name.
	 */
	private static final Predicate<Job> AUTO_BUILD_JOB_PREDICATE = j -> AUTO_BUILD_JOB_NAME.equals(j.getName());

	@Inject
	private ExternalProjectsCollector collector;

	@Inject
	private IN4JSCore core;

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
			ensureWorkspaceProjectsConfigured();
			final BuildManager buildManager = getBuildManager();
			LOGGER.info("Building external libraries: " + Arrays.toString(buildOrder));
			final SubMonitor subMonitor = SubMonitor.convert(monitor, buildOrder.length);
			for (final IBuildConfiguration configuration : buildOrder) {

				final Map<String, String> args = newHashMap();
				args.put(EXTERNAL_BUILD_KEY, Boolean.TRUE.toString());

				final Job job = new WorkspaceJob("Building external library: " + configuration.getProject()) {

					@Override
					public IStatus runInWorkspace(final IProgressMonitor unused) throws CoreException {

						LOGGER.info("Building external library: " + configuration.getProject());

						buildManager.build(configuration, FULL_BUILD, N4JSExternalProject.BUILDER_ID, args,
								subMonitor.newChild(1));

						return Status.OK_STATUS;
					}

				};

				waitForWorkspaceLock(subMonitor);
				final boolean autoBuild = isAutoBuild();

				try {

					toggleAutoBuild(false);
					job.schedule();

					try {
						job.join();
					} catch (final InterruptedException e) {
						LOGGER.error("Error occurred while building external libraries.", e);
					}

				} finally {
					toggleAutoBuild(autoBuild);
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
			ensureWorkspaceProjectsConfigured();
			final BuildManager buildManager = getBuildManager();
			LOGGER.info("Cleaning external libraries: " + Arrays.toString(buildOrder));
			final SubMonitor subMonitor = SubMonitor.convert(monitor, buildOrder.length);
			for (final IBuildConfiguration configuration : buildOrder) {

				final Map<String, String> args = newHashMap();
				args.put(EXTERNAL_BUILD_KEY, Boolean.TRUE.toString());

				final Job job = new WorkspaceJob("Cleaning external library: " + configuration.getProject()) {

					@Override
					public IStatus runInWorkspace(final IProgressMonitor unused) throws CoreException {

						LOGGER.info("Cleaning external library: " + configuration.getProject());

						buildManager.build(configuration, CLEAN_BUILD, N4JSExternalProject.BUILDER_ID, args,
								subMonitor.newChild(1));

						return Status.OK_STATUS;
					}

				};

				waitForWorkspaceLock(subMonitor);
				final boolean autoBuild = isAutoBuild();

				try {

					toggleAutoBuild(false);
					job.schedule();

					try {
						job.join();
					} catch (final InterruptedException e) {
						LOGGER.error("Error occurred while cleaning external libraries.", e);
					}

					// No matter what an auto build job will be performed on Eclipse workspace projects after running
					// a clean on the external libraries. Make sure to block until the autobuild job is done.
					waitForIdleAutoBuildJob(TimeUnit.MINUTES.toMillis(AUTO_BUILD_JOB_WAIT_TIMEOUT_IN_MINUTES));

				} finally {
					toggleAutoBuild(autoBuild);
				}

			}
		}
	}

	private IBuildConfiguration getBuildConfiguration(final IProject project) {
		return TO_CONFIG_FUNC.apply(project);
	}

	private BuildManager getBuildManager() {
		final Workspace workspace = (Workspace) getWorkspace();
		return workspace.getBuildManager();
	}

	/**
	 * Make sure the project description is available and cached for each workspace projects. This is important to avoid
	 * performing a workspace operation (with no scheduling rule) when setting the dynamic project references for each
	 * project.
	 */
	private void ensureWorkspaceProjectsConfigured() {
		for (final IProject project : getWorkspace().getRoot().getProjects()) {
			final org.eclipse.emf.common.util.URI uri = createPlatformResourceURI(project.getName(), true);
			final IN4JSProject n4Project = core.findProject(uri).get();
			if (null != n4Project) {
				n4Project.getArtifactId(); // This will trigger dynamic project reference update.
			}
		}
	}

	/**
	 * Jobs accessing this code should be configured as "system" jobs, to not interrupt autobuild jobs.
	 *
	 * @param monitor
	 *            the monitor for the wait process.
	 */
	/* default */ static void waitForWorkspaceLock(final IProgressMonitor monitor) {

		waitForIdleAutoBuildJob(TimeUnit.MINUTES.toMillis(AUTO_BUILD_JOB_WAIT_TIMEOUT_IN_MINUTES));

		// Wait for the workspace lock to avoid starting the external build.
		final IWorkspaceRoot root = getWorkspace().getRoot();
		try {
			Job.getJobManager().beginRule(root, monitor);
		} catch (final OperationCanceledException e) {
			return;
		} finally {
			Job.getJobManager().endRule(root);
		}
	}

	private static void waitForIdleAutoBuildJob(final long timeout) {

		checkArgument(timeout > 0, "Timeout must be a positive long.");
		final long now = System.currentTimeMillis();

		while (!getScheduledAutoBuildJobs().isEmpty()) {

			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace("Waiting for workspace build job to finish...");
			}

			try {
				Thread.sleep(100L);
			} catch (final Exception e) {
				LOGGER.error("Error while checking whether auto build job is idle or not.", e);
			}

			if (System.currentTimeMillis() > now + timeout) {
				Exceptions.sneakyThrow(new TimeoutException("Timeouted while waiting for idle auto build job."));
			}

		}
	}

	private static FluentIterable<Job> getScheduledAutoBuildJobs() {
		final FluentIterable<Job> allJobs = from(Arrays.asList(Job.getJobManager().find(null)));
		final FluentIterable<Job> autoBuildJobs = allJobs.filter(AUTO_BUILD_JOB_PREDICATE);
		final FluentIterable<Job> scheduledJobs = autoBuildJobs.filter(SCHEDULED_JOBS_PREDICATE);
		return scheduledJobs;
	}

	private static boolean isAutoBuild() {
		return getWorkspace().getDescription().isAutoBuilding();
	}

	private static void toggleAutoBuild(final boolean enabled) {
		IWorkspaceDescription description = getWorkspace().getDescription();
		description.setAutoBuilding(enabled);
		try {
			getWorkspace().setDescription(description);
		} catch (CoreException e) {
			throw new RuntimeException(e.getStatus().getMessage(), e);
		}
	}

}
