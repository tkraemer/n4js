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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Throwables.getStackTraceAsString;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.difference;
import static eu.numberfour.n4js.projectModel.IN4JSProject.N4MF_MANIFEST;
import static eu.numberfour.n4js.utils.process.OutputStreamPrinterThread.OutputStreamType.STD_ERR;
import static eu.numberfour.n4js.utils.process.OutputStreamPrinterThread.OutputStreamType.STD_OUT;
import static java.lang.System.lineSeparator;
import static org.eclipse.core.runtime.Status.OK_STATUS;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.xtext.util.Pair;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import eu.numberfour.n4js.binaries.BinaryCommandFactory;
import eu.numberfour.n4js.binaries.IllegalBinaryStateException;
import eu.numberfour.n4js.binaries.nodejs.NpmBinary;
import eu.numberfour.n4js.external.libraries.PackageJson;
import eu.numberfour.n4js.utils.StatusHelper;
import eu.numberfour.n4js.utils.collections.Arrays2;
import eu.numberfour.n4js.utils.git.GitUtils;
import eu.numberfour.n4js.utils.process.OutputRedirection;
import eu.numberfour.n4js.utils.process.OutputStreamProvider;
import eu.numberfour.n4js.utils.process.ProcessResult;
import eu.numberfour.n4js.utils.resources.ExternalProject;

/**
 * Class for installing npm dependencies into the external library.
 */
@Singleton
public class NpmManager {

	private static Logger LOGGER = Logger.getLogger(NpmManager.class);

	private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {

		private final SimpleDateFormat delegate = new SimpleDateFormat("[dd-MM-yyyy hh:mm:ss] ");

		@Override
		public SimpleDateFormat get() {
			return delegate;
		}

		@Override
		public void set(final SimpleDateFormat value) {
			// Immutable.
		}

	};

	@Inject
	private BinaryCommandFactory commandFactory;

	@Inject
	private NpmPackageToProjectAdapter npmPackageToProjectAdapter;

	@Inject
	private TargetPlatformInstallLocationProvider locationProvider;

	@Inject
	private ExternalLibraryWorkspace externalLibraryWorkspace;

	@Inject
	private ExternalProjectsCollector collector;

	@Inject
	private StatusHelper statusHelper;

	@Inject
	private OutputStreamProvider osProvider;

	@Inject
	private Provider<NpmBinary> npmBinaryProvider;

	@Inject
	private RebuildWorkspaceProjectsScheduler scheduler;

	/**
	 * Installs the given npm package in a blocking fashion.
	 *
	 * @param packageName
	 *            the name of the package that has to be installed via package manager.
	 * @param monitor
	 *            the monitor for the blocking install process.
	 * @return a status representing the outcome of the install process.
	 */
	public IStatus installDependency(final String packageName, IProgressMonitor monitor)
			throws IllegalBinaryStateException {

		final NpmBinary npmBinary = npmBinaryProvider.get();
		final IStatus npmBinaryStatus = npmBinary.validate();
		if (!npmBinaryStatus.isOK()) {
			throw new IllegalBinaryStateException(npmBinary, npmBinaryStatus);
		}

		try {

			logInfo("================================================================");
			logInfo("Installing '" + packageName + "' npm package...");
			logInfo("================================================================");

			monitor = null == monitor ? new NullProgressMonitor() : monitor;
			monitor.beginTask("Installing '" + packageName + "' npm package...", 10);

			final Set<String> oldDependencies = from(
					externalLibraryWorkspace.getProjects(locationProvider.getTargetPlatformNodeModulesLocation()))
							.transform(p -> p.getName()).toSet();
			monitor.worked(1); // Intentionally cheating for better user experience.

			logInfo("Fetching '" + packageName + "' package... [1 of 4]");
			monitor.setTaskName("Fetching '" + packageName + "' package... [1 of 4]");
			try {
				final File targetInstallLocation = new File(locationProvider.getTargetPlatformInstallLocation());
				final IStatus installStatus = installPackage(targetInstallLocation, packageName);
				if (!installStatus.isOK()) {
					logError("Error occurred while installing '" + packageName + "' npm package.",
							installStatus.getException());
					return installStatus;
				}
			} catch (IOException | InterruptedException e) {
				final IStatus status = statusHelper
						.createError("Error occurred while installing npm package '" + packageName + "'.", e);
				logError(status);
				return status;
			}
			logInfo("Package '" + packageName + "' has been successfully fetched.");
			monitor.worked(2);

			logInfo("Calculating dependency changes... [2 of 4]");
			monitor.setTaskName("Calculating dependency changes... [2 of 4]");
			Map<String, String> afterDependencies = locationProvider.getTargetPlatformContent().dependencies;
			if (null == afterDependencies) {
				afterDependencies = newHashMap();
			}
			final Set<String> newDependencies = afterDependencies.keySet();
			final File nodeModulesFolder = new File(locationProvider.getTargetPlatformNodeModulesLocation());

			final Collection<String> deletedDependencies = difference(oldDependencies, newDependencies);
			final Collection<String> addedDependencies = difference(newDependencies, oldDependencies);
			final Iterable<java.net.URI> toBeDeleted = from(deletedDependencies)
					.transform(name -> new File(nodeModulesFolder, name).toURI());
			logInfo("Dependency changes have been successfully calculated.");
			monitor.worked(1);

			logInfo("Adapting npm package structure to N4JS project structure... [3 of 4]");
			monitor.setTaskName("Adapting npm package structure to N4JS project structure... [3 of 4]");
			final Pair<IStatus, Collection<File>> result = npmPackageToProjectAdapter.adaptPackages(addedDependencies);
			final IStatus adaptionStatus = result.getFirst();
			if (!adaptionStatus.isOK()) {
				logError(adaptionStatus);
				return adaptionStatus;
			}
			logInfo("Package structure has been successfully adapted to N4JS project structure.");
			monitor.worked(2);

			logInfo("Registering new projects... [4 of 4]");
			monitor.setTaskName("Registering new projects... [4 of 4]");
			// nothing to do in the headless case. TODO inject logic instead?
			if (Platform.isRunning()) {
				final Iterable<java.net.URI> toBeUpdated = from(result.getSecond()).transform(file -> file.toURI());
				final NpmProjectAdaptionResult adaptionResult = NpmProjectAdaptionResult.newOkResult(toBeUpdated,
						toBeDeleted);
				externalLibraryWorkspace.registerProjects(adaptionResult, monitor);
			}
			logInfo("Projects have been successfully registered.");

			logInfo("Package '" + packageName + "' has been successfully installed.");
			logInfo("================================================================");

			return OK_STATUS;

		} finally {
			monitor.done();
		}

	}

	/**
	 * Uninstalls the given npm package in a blocking fashion.
	 *
	 * @param packageName
	 *            the name of the package that has to be uninstalled via package manager.
	 * @param monitor
	 *            the monitor for the blocking uninstall process.
	 * @return a status representing the outcome of the uninstall process.
	 */
	public IStatus uninstallDependency(final String packageName, IProgressMonitor monitor)
			throws IllegalBinaryStateException {

		final NpmBinary npmBinary = npmBinaryProvider.get();
		final IStatus npmBinaryStatus = npmBinary.validate();
		if (!npmBinaryStatus.isOK()) {
			throw new IllegalBinaryStateException(npmBinary, npmBinaryStatus);
		}

		try {

			logInfo("================================================================");
			logInfo("Uninstalling '" + packageName + "' npm package...");
			logInfo("================================================================");

			monitor = null == monitor ? new NullProgressMonitor() : monitor;
			monitor.beginTask("Uninstalling '" + packageName + "' npm package...", 10);

			final Map<IProject, Collection<IProject>> beforeExternalsWithDependees = collector
					.collectExternalProjectDependents(externalLibraryWorkspace
							.getProjects(locationProvider.getTargetPlatformNodeModulesLocation()));

			monitor.worked(1); // Intentionally cheating for better user experience.

			logInfo("Removing '" + packageName + "' package... [1 of 4]");
			monitor.setTaskName("Removing '" + packageName + "' package... [1 of 4]");
			try {
				final File targetInstallLocation = new File(locationProvider.getTargetPlatformInstallLocation());
				final IStatus installStatus = uninstallPackage(targetInstallLocation, packageName);
				if (!installStatus.isOK()) {
					logError("Error occurred while uninstalling '" + packageName + "' npm package.",
							installStatus.getException());
					return installStatus;
				}
			} catch (IOException | InterruptedException e) {
				final IStatus status = statusHelper
						.createError("Error occurred while installing npm package '" + packageName + "'.", e);
				logError(status);
				return status;
			}

			logInfo("Package '" + packageName + "' has been successfully uninstalled.");
			monitor.worked(2);

			logInfo("Update external libraries state... [2 of 4]");
			monitor.setTaskName("Update external libraries state... [2 of 4]");
			externalLibraryWorkspace.updateState();
			monitor.worked(1);

			logInfo("Calculating dependency changes... [3 of 4]");
			monitor.setTaskName("Calculating dependency changes... [3 of 4]");

			final Map<IProject, Collection<IProject>> afterExternalsWithDependees = collector
					.collectExternalProjectDependents(externalLibraryWorkspace
							.getProjects(locationProvider.getTargetPlatformNodeModulesLocation()));

			final Set<IProject> affectedEclipseProjects = new HashSet<>();

			beforeExternalsWithDependees.forEach((p, deps) -> {
				if (!afterExternalsWithDependees.containsKey(p)) {
					// external project p was uninstalled
					Collection<IProject> collection = beforeExternalsWithDependees.get(p);
					if (collection != null) {
						// external project p had dependent workspace projects
						affectedEclipseProjects.addAll(collection);
					}
				}
			});

			logInfo("Dependency changes have been successfully calculated.");
			monitor.worked(2);

			logInfo("Scheduling build of affected projects... [4 of 4]");
			monitor.setTaskName("Scheduling build of projects... [4 of 4]");

			scheduler.scheduleBuildIfNecessary(affectedEclipseProjects);

			logInfo("Package '" + packageName + "' has been successfully uninstalled.");
			logInfo("================================================================");

			return OK_STATUS;

		} finally {
			monitor.done();
		}
	}

	/**
	 * Sugar for {@link #refreshInstalledNpmPackage(String, IProgressMonitor)} for all available {@code npm} packages.
	 * Refreshes the type definitions for all installed, available {@code npm} packages in the external workspace.
	 * Performs a {@code git pull} before the actual refresh process. Returns with an {@link IStatus status}
	 * representing the outcome of the refresh operation.
	 *
	 * @param monitor
	 *            the monitor for the progress.
	 * @return a status representing the outcome of the operation.
	 */
	public IStatus refreshInstalledNpmPackages(final IProgressMonitor monitor) {
		checkNotNull(monitor, "monitor");

		final Collection<String> packageNames = getAllNpmProjectsMapping().keySet();

		if (packageNames.isEmpty()) {
			return statusHelper.OK();
		}

		final SubMonitor subMonitor = SubMonitor.convert(monitor, packageNames.size() + 1);
		try {

			subMonitor.setTaskName("Refreshing cache for type definitions files...");

			performGitPull(subMonitor.newChild(1, SubMonitor.SUPPRESS_ALL_LABELS));

			// Initial pessimistic.
			final MultiStatus errorStatus = statusHelper
					.createMultiError("Multiple error occurred while refreshing definitions for npm packages.");
			for (final String packageName : packageNames) {
				final IStatus status = refreshInstalledNpmPackage(packageName, false, subMonitor.newChild(1));
				if (!status.isOK()) {
					logError(status);
					errorStatus.add(status);
				}
			}

			if (Arrays2.isEmpty(errorStatus.getChildren())) {
				return statusHelper.OK();
			} else if (1 == errorStatus.getChildren().length) {
				return errorStatus.getChildren()[0];
			} else {
				return errorStatus;
			}

		} finally {
			subMonitor.done();
		}

	}

	/**
	 * Refreshes the definitions (if any) for the given {@code npm} package by updating the definition file contents.
	 * Also updates the N4 manifest content by merging all fragments (if any) into it. This method will perform a
	 * {@code git pull} to ensure that the local repository clone is up to date. Returns with a {@link IStatus status}
	 * representing the outcome of the operation.
	 *
	 * @param packageName
	 *            the name of the {@code npm} package to update.
	 * @param monitor
	 *            the monitor for the update progress.
	 * @return a status representing the outcome of the refresh operation.
	 */
	public IStatus refreshInstalledNpmPackage(final String packageName, final IProgressMonitor monitor) {
		checkNotNull(packageName, "packageName");
		checkNotNull(monitor, "monitor");
		return refreshInstalledNpmPackage(packageName, true, monitor);
	}

	private IStatus refreshInstalledNpmPackage(final String packageName, final boolean performGitPull,
			final IProgressMonitor monitor) {

		final SubMonitor progress = SubMonitor.convert(monitor, 2);

		logInfo("================================================================");
		final String taskName = "Refreshing type definitions for '" + packageName + "' npm package...";
		logInfo(taskName);
		progress.setTaskName(taskName);

		try {

			final URI uri = getAllNpmProjectsMapping().get(packageName);
			if (null == uri) {
				// No project with the given package name. Nothing to do.
				return statusHelper.OK();
			}

			final File definitionsFolder = npmPackageToProjectAdapter.getNpmsTypeDefinitionsFolder(performGitPull);
			if (null == definitionsFolder) {
				// No definitions are available at the moment.
				return statusHelper.OK();
			}

			if (performGitPull) {
				performGitPull(progress.newChild(1));
			}

			final File packageRoot = new File(uri);
			final PackageJson packageJson = npmPackageToProjectAdapter.getPackageJson(packageRoot);
			final File manifest = new File(packageRoot, N4MF_MANIFEST);
			if (!manifest.isFile()) {
				String message = "Cannot locate N4 manifest for '" + packageName + "' package at '" + manifest + "'.";
				final IStatus error = statusHelper.createError(message);
				logError(error);
			}

			final IStatus status = npmPackageToProjectAdapter.addTypeDefinitions(
					packageRoot,
					packageJson,
					manifest,
					definitionsFolder);

			if (status.isOK()) {
				logInfo("Successfully refreshed the type definitions for '" + packageName + "' npm package.");
				logInfo("================================================================");
			} else {
				logError(status);
			}

			return status;

		} catch (final IOException e) {
			final String message = "Error while refreshing the definitions for '" + packageName + "' npm package.";
			final IStatus error = statusHelper.createError(message, e);
			logError(error);
			return error;
		} finally {
			monitor.done();
		}
	}

	/**
	 * A map of project (npm package) names to project location mappings.
	 */
	private Map<String, URI> getAllNpmProjectsMapping() {
		final URI nodeModulesLocation = locationProvider.getTargetPlatformNodeModulesLocation();
		final Map<String, URI> mappings = newHashMap();

		// Intentionally might include projects that are already in the workspace
		for (final IProject project : externalLibraryWorkspace.getProjects(nodeModulesLocation)) {
			if (project.isAccessible() && project instanceof ExternalProject) {
				final URI location = ((ExternalProject) project).getExternalResource().toURI();
				mappings.put(project.getName(), location);
			}
		}

		return ImmutableMap.copyOf(mappings);
	}

	private void performGitPull(final IProgressMonitor monitor) {
		final URI repositoryLocation = locationProvider.getTargetPlatformLocalGitRepositoryLocation();
		GitUtils.pull(new File(repositoryLocation).toPath(), monitor);
	}

	private void logInfo(final String message) {
		LOGGER.info(message);
		// Print writer is intentionally not released, its just a wrapper to log a message.
		final PrintWriter pw = new PrintWriter(osProvider.getOutputStream(STD_OUT, OutputRedirection.REDIRECT));
		pw.append(getTimestamp() + message + lineSeparator());
		pw.flush();
	}

	private void logError(final IStatus status) {
		logError(status.getMessage(), status.getException());
		if (status instanceof MultiStatus) {
			for (final IStatus child : status.getChildren()) {
				logError(child);
			}
		}
	}

	private void logError(final String message, final Throwable t) {
		LOGGER.error(message, t);
		// Print writer is intentionally not released, its just a wrapper to log a message.
		final PrintWriter pw = new PrintWriter(osProvider.getOutputStream(STD_ERR, OutputRedirection.SUPPRESS));
		pw.append(getTimestamp() + message + lineSeparator());
		if (null != t) {
			pw.append(getTimestamp() + getStackTraceAsString(t) + lineSeparator());
		}
		pw.flush();
	}

	private String getTimestamp() {
		return DATE_FORMAT.get().format(new Date());
	}

	/**
	 * Installs package under given name in specified location. Updates dependencies in the package.json of that
	 * location. If there is no package.json at that location npm errors will be logged to the error log. In that case
	 * npm usual still installs requested dependency (if possible).
	 *
	 * @param installPath
	 *            location in which package will be installed
	 * @param packageName
	 *            to be installed
	 *
	 * @throws IOException
	 *             if IO issues in npm process
	 * @throws InterruptedException
	 *             if interrupted when waiting for npm process
	 */
	private IStatus installPackage(File installPath, String packageName) throws IOException, InterruptedException {
		if (packageName == null || packageName.trim().isEmpty()) {
			return statusHelper.createError("Malformed npm package name: '" + packageName + "'.");
		}

		ProcessResult per = commandFactory.createInstallPackageCommand(installPath, packageName, true).execute();

		if (!per.isOK()) {
			final Throwable cause = per.toThrowable("Error while installing npm package.");
			if (null != cause) {
				return statusHelper.createError(cause.getMessage(), cause);
			} else {
				final String processLog = per.toString();
				return statusHelper.createError(processLog, cause);
			}
		} else {
			return OK_STATUS;
		}

	}

	/**
	 * Uninstalls package under given name in specified location. Updates dependencies in the package.json of that
	 * location. If there is no package.json at that location npm errors will be logged to the error log. In that case
	 * npm usual still uninstalls requested dependency (if possible).
	 *
	 * @param uninstallPath
	 *            location in which package will be uninstalled
	 * @param packageName
	 *            to be uninstalled
	 *
	 * @throws IOException
	 *             if IO issues in npm process
	 * @throws InterruptedException
	 *             if interrupted when waiting for npm process
	 */
	private IStatus uninstallPackage(File uninstallPath, String packageName) throws IOException, InterruptedException {
		if (packageName == null || packageName.trim().isEmpty()) {
			return statusHelper.createError("Malformed npm package name: '" + packageName + "'.");
		}

		ProcessResult per = commandFactory.createUninstallPackageCommand(uninstallPath, packageName, true).execute();

		if (!per.isOK()) {
			final Throwable cause = per.toThrowable("Error while uninstalling npm package.");
			if (null != cause) {
				return statusHelper.createError(cause.getMessage(), cause);
			} else {
				final String processLog = per.toString();
				return statusHelper.createError(processLog, cause);
			}
		} else {
			return OK_STATUS;
		}

	}

}
