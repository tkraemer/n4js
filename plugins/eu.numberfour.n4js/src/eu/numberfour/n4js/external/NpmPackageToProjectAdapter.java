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

import static com.google.common.collect.Sets.newHashSet;
import static eu.numberfour.n4js.external.libraries.PackageJson.PACKAGE_JSON;
import static eu.numberfour.n4js.n4mf.utils.N4MFConstants.N4MF_MANIFEST;
import static java.util.Collections.emptyList;
import static org.eclipse.core.runtime.Status.OK_STATUS;
import static org.eclipse.xtext.util.Tuples.pair;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.Pair;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.binaries.BinaryCommandFactory;
import eu.numberfour.n4js.external.libraries.PackageJson;
import eu.numberfour.n4js.n4mf.ProjectDescription;
import eu.numberfour.n4js.n4mf.resource.ManifestMerger;
import eu.numberfour.n4js.n4mf.utils.N4MFConstants;
import eu.numberfour.n4js.utils.Arrays2;
import eu.numberfour.n4js.utils.OSInfo;
import eu.numberfour.n4js.utils.StatusHelper;
import eu.numberfour.n4js.utils.Version;
import eu.numberfour.n4js.utils.git.GitUtils;
import eu.numberfour.n4js.utils.io.FileCopier;
import eu.numberfour.n4js.utils.io.FileDeleter;
import eu.numberfour.n4js.utils.process.ProcessResult;

/**
 * Adapts given npm package to n4js project form
 */
public class NpmPackageToProjectAdapter {

	private static Logger LOGGER = Logger.getLogger(NpmPackageToProjectAdapter.class);

	@Inject
	private N4JSNpmManifestContentProvider manifestContentProvider;

	@Inject
	private StatusHelper statusHelper;

	@Inject
	private TargetPlatformInstallLocationProvider installLocationProvider;

	@Inject
	private ManifestMerger manifestMerger;

	@Inject
	private BinaryCommandFactory commandFactory;

	@Inject
	private Provider<XtextResourceSet> resourceSetProvider;

	/** Default filter for manifest fragments */
	private final static FileFilter ONLY_MANIFEST_FRAGMENTS = new FileFilter() {
		private final static String MANIFEST_FRAGMENT = N4MFConstants.MANIFEST_FRAGMENT;

		@Override
		public boolean accept(File pathname) {
			return pathname.toPath().endsWith(MANIFEST_FRAGMENT);
		}
	};

	/**
	 * Adapts npm packages in provided folder to the N4JS project structure. Only package folders which match requested
	 * packages are adapted. It is expected that npm flattens packages structures, therefore it is assumed that other
	 * folders are dependencies (also transitive) of the requested packages.
	 *
	 * Requested npm packages already look like N4JS projects (i.e. have N4MF manifest file), those packages are not
	 * adapted (proper structure is assumed), but they will be returned in to the caller to allow further processing
	 * (i.e. passing them to the builder).
	 *
	 * Returned set of N4JS project folders will not include those installed by the npm but without matching names in
	 * provided set of expected packages. Those packages are treated as transitive dependencies and are not return to
	 * the caller.
	 *
	 * @param namesOfPackagesToAdapt
	 *            names of the expected packages
	 * @return folders of adapted npm packages
	 */
	public Pair<IStatus, Collection<File>> adaptPackages(Iterable<String> namesOfPackagesToAdapt) {

		final File nodeModulesFolder = new File(installLocationProvider.getTargetPlatformNodeModulesLocation());
		final Collection<String> names = newHashSet(namesOfPackagesToAdapt);
		final Collection<File> adaptedProjects = newHashSet();
		final File[] packageRoots = nodeModulesFolder.listFiles(packageName -> names.contains(packageName.getName()));

		// Initial pessimistic.
		final MultiStatus status = statusHelper.createMultiError("Error while adapting npm packages to N4JS format.");

		final File n4jsdsFolder = getNpmsTypeDefinitionsFolder();

		for (File packageRoot : packageRoots) {
			try {
				PackageJson packageJson = getPackageJson(packageRoot);
				final File manifest = new File(packageRoot, N4MF_MANIFEST);
				// looks like n4js project skip adaptation
				if (manifest.exists() && manifest.isFile()) {
					adaptedProjects.add(packageRoot);
					if (!names.remove(packageRoot.getName())) {
						throw new IOException("Unexpected error occurred while adapting '"
								+ packageRoot.getName() + "' npm package into N4JS format.");
					}
				} else {

					if (manifest.isDirectory()) {
						throw new IOException("The manifest location is occupied by the folder '" + manifest + "'.");
					}

					manifest.createNewFile();

					try {
						generateManifestContent(packageRoot, packageJson, manifest);
						adaptedProjects.add(packageRoot);
						if (!names.remove(packageRoot.getName())) {
							throw new IOException("Unexpected error occurred while adapting '" + packageRoot.getName()
									+ "' npm package into N4JS format.");
						}
					} catch (final Exception e) {
						try {
							FileDeleter.delete(manifest);
						} catch (IOException ioe) {
							// Intentionally swallowed to get the original cause.
							LOGGER.error("Error while trying to clean up corrupted " + manifest + " file.", e);
						}
						throw e;
					}

				}

				if (n4jsdsFolder != null) {
					addTypeDefinitions(packageRoot, packageJson, manifest, n4jsdsFolder);
				}

			} catch (final Exception e) {
				status.add(statusHelper.createError("Unexpected error occurred while adapting '" + packageRoot.getName()
						+ "' npm package into N4JS format.", e));
			}

		}

		if (Arrays2.isEmpty(status.getChildren())) {
			return pair(OK_STATUS, adaptedProjects);
		} else {
			return pair(status, emptyList());
		}

	}

	/**
	 * Reads, parses and returns with the content of the {@code package.json} file as a POJO for the given npm package
	 * root location.
	 *
	 * @param packageRoot
	 *            the root location of the npm package.
	 *
	 * @return the POJO instance that represents the read, parsed content of the {@code package.json} file.
	 *
	 * @throws IOException
	 *             if {@code package.json} file does not exists, hence the content cannot be read.
	 */
	PackageJson getPackageJson(File packageRoot) throws IOException {

		final File packageJsonResource = new File(packageRoot, PACKAGE_JSON);
		if (!packageJsonResource.exists() || !packageJsonResource.isFile()) {
			throw new IOException("Cannot read package.json content for package '" + packageJsonResource.getName()
					+ "' at '" + packageJsonResource + "'.");
		}
		return PackageJson.readValue(packageJsonResource.toURI());
	}

	private static String NPM_DEFINITIONS_FOLDER_NAME = "npm";

	private File getNpmsTypeDefinitionsFolder() {
		return getNpmsTypeDefinitionsFolder(true);
	}

	/**
	 * Returns with the root folder of all available npm package definitions. Or returns with {@code null} if no
	 * definitions are available. Also performs an on demand {@code git pull}.
	 *
	 * @param performGitPull
	 *            {@code true} if a git pull has to be performed in the local clone.
	 *
	 * @return the root folder of all npm package definitions or {@code null} if missing.
	 */
	File getNpmsTypeDefinitionsFolder(final boolean performGitPull) {

		File repositoryLocation = new File(installLocationProvider.getTargetPlatformLocalGitRepositoryLocation());

		if (performGitPull) {
			// pull changes
			GitUtils.pull(repositoryLocation.toPath());
		}

		final File definitionsRoot = new File(repositoryLocation, NPM_DEFINITIONS_FOLDER_NAME);
		if (definitionsRoot.exists() && definitionsRoot.isDirectory()) {
			return definitionsRoot;
		} else {
			LOGGER.error(
					"Cannot locate local git repository clone for N4JS definition files: " + definitionsRoot + ".");
			return null;
		}
	}

	/**
	 * Add type definitions (N4JSDs) to the npm package. Types are added only if matching version is found.
	 *
	 * This method suppresses any potential issues as adding type definitions to some npm package does not affect
	 * overall npm usage. Still, errors are {@link #LOGGER logged} to help troubleshooting potential issues and returns
	 * with an {@link IStatus status} instance that represents the problem if any.
	 *
	 * @param packageRoot
	 *            npm package folder.
	 * @param packageJson
	 *            {@link PackageJson package.json} of that package.
	 * @param manifest
	 *            file that will be adjusted according to manifest fragments.
	 * @param definitionsFolder
	 *            root folder for npm type definitions.
	 *
	 * @return a status representing the outcome of performed the operation.
	 */
	IStatus addTypeDefinitions(File packageRoot, PackageJson packageJson, File manifest,
			File definitionsFolder) {

		String packageName = packageRoot.getName();
		File packageN4JSDsRoot = new File(definitionsFolder, packageName);
		if (!(packageN4JSDsRoot.exists() && packageN4JSDsRoot.isDirectory())) {
			LOGGER.info("No type definitions found for '" + packageName + "' npm package.");
			return statusHelper.OK();
		}

		String packageJsonVersion = packageJson.getVersion();
		Version packageVersion = Version.createFromString(packageJsonVersion);
		String[] list = packageN4JSDsRoot.list();
		Set<Version> availableTypeDefinitionsVersions = new HashSet<>();
		for (int i = 0; i < list.length; i++) {
			String version = list[i];
			Version availableTypeDefinitionsVersion = Version.createFromString(version);
			if (!Version.MISSING.equals(availableTypeDefinitionsVersion)) {
				availableTypeDefinitionsVersions.add(availableTypeDefinitionsVersion);
			}
		}

		Version closestMatchingVersion = Version.findClosestMatching(availableTypeDefinitionsVersions, packageVersion);
		if (Version.MISSING.equals(closestMatchingVersion)) {
			LOGGER.info("No proper versions can be found for '" + packageName + "' npm package.");
			LOGGER.info("Desired version was: " + packageVersion + ".");
			if (availableTypeDefinitionsVersions.isEmpty()) {
				LOGGER.info("No versions were available.");
			} else if (1 == availableTypeDefinitionsVersions.size()) {
				final Version head = availableTypeDefinitionsVersions.iterator().next();
				LOGGER.info("The following version was available for '" + packageName + "': " + head + ".");
			} else {
				final String versions = Iterables.toString(availableTypeDefinitionsVersions);
				LOGGER.info("The following versions were available for '" + packageName + "': " + versions + ".");
			}
			return statusHelper.OK();
		}

		File packageVersionedN4JSD = new File(packageN4JSDsRoot, closestMatchingVersion.toString());
		if (!(definitionsFolder.exists() && definitionsFolder.isDirectory())) {
			final String message = "Cannot find type definitions folder for '" + packageName
					+ "' npm package for version '" + closestMatchingVersion + "'.";
			LOGGER.error(message);
			return statusHelper.createError(message);
		}

		try {
			FileCopier.copy(packageVersionedN4JSD.toPath(), packageRoot.toPath());
		} catch (IOException e) {
			final String message = "Error while trying to update type definitions content for '" + packageName
					+ "' npm package.";
			LOGGER.error(message);
			return statusHelper.createError(message, e);
		}

		// adjust manifest according to type definitions manifest fragments
		File[] manifestFragments = packageRoot.listFiles(ONLY_MANIFEST_FRAGMENTS);
		return adjustManifest(manifest, manifestFragments);
	}

	/**
	 * Adjust manifests based on provided manifest fragments.
	 *
	 * @param manifest
	 *            file to be adjusted
	 * @param manifestFragments
	 *            that will be used to adjust the manifest
	 */
	private IStatus adjustManifest(final File manifest, final File... manifestFragments) {

		if (Arrays2.isEmpty(manifestFragments)) {
			// Nothing to merge.
			return statusHelper.OK();
		}

		final URI manifestURI = URI.createFileURI(manifest.getAbsolutePath());

		ProjectDescription pd = null;
		for (int i = 0; i < manifestFragments.length; i++) {
			File fragment = manifestFragments[i];
			if (fragment.exists() && fragment.isFile()) {
				URI manifestFragmentURI = URI.createFileURI(fragment.getAbsolutePath());
				pd = manifestMerger.mergeContent(manifestFragmentURI, manifestURI);
				fragment.delete();
			} else {
				LOGGER.warn("Broken manifest fragment: " + fragment + ".");
			}
		}

		if (pd != null) {
			ResourceSet resourceSet = resourceSetProvider.get();
			Resource resource = resourceSet.getResource(manifestURI, true);
			List<EObject> contents = resource.getContents();
			contents.clear();
			contents.add(pd);
			try {
				resource.save(null);
				return statusHelper.OK();
			} catch (IOException e) {
				final String message = "Error while trying to write N4 manifest content for: " + manifestURI + ".";
				LOGGER.error(message, e);
				return statusHelper.createError(message, e);
			}
		} else {
			final String message = "Failed to merge N4 manifest fragments into '" + manifestURI + "'.";
			LOGGER.error(message);
			return statusHelper.createError(message);
		}
	}

	/**
	 * Writes contents of the {@link N4MFConstants#N4MF_MANIFEST manifest file} for a given npm package.
	 *
	 * @param projectFolder
	 *            root folder of the npm package in which manifest is written
	 * @param packageJSON
	 *            that will be used as manifest data source
	 * @param manifest
	 *            file to which contents should be written
	 */
	private void generateManifestContent(File projectFolder, PackageJson packageJSON, File manifest)
			throws IOException {

		String projectName = packageJSON.getName();
		String manifestMain = computeMainModule(projectFolder);

		if (!projectFolder.getName().equals(projectName)) {
			LOGGER.warn("project folder and project name are different : " + projectFolder.getName() + " <> + "
					+ packageJSON.getName());
		}

		try (FileWriter fw = new FileWriter(manifest)) {
			fw.write(manifestContentProvider.getContent(projectName, ".", ".", manifestMain));
		}
	}

	/**
	 */
	private String computeMainModule(File projectFolder) throws IOException {
		File main = new File(resolveMainModule(projectFolder));

		Path packagePath = projectFolder.toPath();
		Path packageMainModulePath = main.toPath();

		Path mainmoduleRelative = packagePath.relativize(packageMainModulePath);

		String mainSpecifier = mainmoduleRelative.toString();

		// strip extension
		int dotIndex = mainSpecifier.lastIndexOf('.');
		String ext = (dotIndex == -1) ? "" : mainSpecifier.substring(dotIndex);
		mainSpecifier = mainSpecifier.substring(0, (mainSpecifier.length() - ext.length()));

		// replace windows path separators
		if (OSInfo.isWindows())
			mainSpecifier = mainSpecifier.replace(File.separator, "/");

		// strip relative start part
		if (mainSpecifier.startsWith("./"))
			mainSpecifier = mainSpecifier.substring(2);

		return mainSpecifier;
	}

	/**
	 * Calls node process to resolve main module of the provided npm package.
	 *
	 * @param packageRoot
	 *            package root folder
	 * @return string with absolute path to the package main module
	 * @throws IOException
	 *             if cannot resolve main module
	 */
	private String resolveMainModule(File packageRoot)
			throws IOException {

		ProcessResult per = commandFactory.createResolveMainModuleCommand(packageRoot).execute();

		if (per.isOK()) {
			// happy case string with full path to the main module (terminated with line ending)
			return per.getStdOut().trim();
		}
		throw new IOException(per.toString());
	}

}
