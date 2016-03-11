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
package eu.numberfour.n4js.external.libraries;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.notNull;
import static com.google.common.base.Suppliers.memoize;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static eu.numberfour.n4js.utils.git.GitUtils.getMasterBranch;
import static java.lang.Boolean.parseBoolean;
import static org.eclipse.core.runtime.IStatus.ERROR;
import static org.eclipse.core.runtime.IStatus.INFO;
import static org.eclipse.core.runtime.Platform.inDebugMode;
import static org.eclipse.core.runtime.Platform.inDevelopmentMode;
import static org.eclipse.xtext.util.Tuples.pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.xtext.util.Pair;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import eu.numberfour.n4js.utils.git.GitUtils;
import eu.numberfour.n4js.utils.io.FileDeleter;

/**
 * Activator for the bundle that holds all the external/built-in libraries.
 */
@SuppressWarnings("restriction")
public class ExternalLibrariesActivator implements BundleActivator {

	private static final Logger LOGGER = Logger.getLogger(ExternalLibrariesActivator.class);

	/**
	 * Property configured and available only in the N4JS IDE product. If the associated value of the property can be
	 * parsed to {@code true} value via the {@link Boolean#parseBoolean(String)}. This property is here to control
	 * presence of the built in libraries or the type definitions repository.
	 */
	public static final String INCLUDES_BUILT_INS_PRODUCT_PROPERTY = "includesBuiltInLibraries";

	/**
	 * System property that is checked only and if only the N4JS IDE product is running, it is configured to include the
	 * built-in libraries but the application is running either in {@link Platform#inDebugMode() debug mode} or in
	 * {@link Platform#inDevelopmentMode() development mode}, in other words not in production mode. If the N4JS IDE
	 * product is not in production mode, then if the {@link Boolean#parseBoolean(String) boolean value} of this
	 * property is {@code true}, then the built-in libraries (such as N4JS Runtime and Mangelhaft) will be included.
	 * Otherwise they will not be.
	 */
	public static final String INCLUDES_BUILT_INS_SYSTEM_PROPERTY = "eu.numberfour.n4js.includesBuiltInLibraries";

	/** Unique name of the N4 language category. */
	public static final String LANG_CATEGORY = "lang";

	/** Unique name of the N4 runtime category. */
	public static final String RUNTIME_CATEGORY = "runtime";

	/** Unique name of the Mangelhaft category. */
	public static final String MANGELHAFT_CATEGORY = "mangelhaft";

	/** Unique name of the {@code npm} category. */
	public static final String N4_NPM_CATEGORY = "node_modules";

	/** Unique name of the root local git clone folder for N4JS definition files. */
	public static final String N4_GIT_ROOT = "n4jsd";

	/** URL pointing to the remote git repository that stores the N4JS definition files. */
	public static final String N4_GIT_REMOTE_URL = "https://github.com/NumberFour/" + N4_GIT_ROOT + ".git";

	/** Unique name of the root npm folder for N4JS. */
	private static final String N4_NPM_ROOT = ".n4npm";

	private static final Function<URL, URL> URL_TO_FILE_URL_FUNC = url -> {
		try {
			return FileLocator.toFileURL(url);
		} catch (final IOException e) {
			final String message = "Error while converting URL to file URL. " + url;
			LOGGER.error(message, e);
			throw new RuntimeException(message, e);
		}
	};

	private static final Function<URL, URI> URL_TO_URI_FUNC = url -> {
		if ("file".equals(url.getProtocol())) {
			return new File(url.getFile()).toURI();
		} else {
			final String message = "Unexpected protocol while trying to convert URL to URI." + url;
			LOGGER.error(message);
			return null;
		}
	};

	private static final Function<File, File> FILE_TO_CANONICAL_FILE = file -> {
		try {
			return file.getCanonicalFile();
		} catch (final IOException e) {
			final String message = "Error while getting the canonical file. " + file;
			LOGGER.error(message, e);
			throw new RuntimeException(message, e);
		}
	};

	/**
	 * An iterable of folder names that holds external and/or built-in N4JS libraries.
	 */
	private static final Iterable<String> EXTERNAL_LIBRARY_FOLDER_NAMES = ImmutableList.<String> builder()
			.add(LANG_CATEGORY)
			.add(RUNTIME_CATEGORY)
			.add(MANGELHAFT_CATEGORY)
			.add(N4_NPM_CATEGORY)
			.build();

	/**
	 * Unique symbolic name of the bundle that where this activator belongs to.
	 */
	public static final String PLUGIN_ID = "eu.numberfour.n4js.external.libraries";

	/**
	 * Provides a mapping between the unique names of the external N4JS libraries and the human readable names.
	 */
	public static final Map<String, String> EXTERNAL_LIBRARY_NAMES = ImmutableMap.<String, String> builder()
			.put(LANG_CATEGORY, "N4JS Language")
			.put(RUNTIME_CATEGORY, "N4JS Runtime")
			.put(MANGELHAFT_CATEGORY, "Mangelhaft")
			.put(N4_NPM_CATEGORY, N4_NPM_CATEGORY)
			.build();

	/**
	 * Supplies a one to one mapping between the available external built-in N4JS library root locations and the unique
	 * keywords for the libraries.
	 */
	public static final Supplier<BiMap<URI, String>> EXTERNAL_LIBRARIES_SUPPLIER = memoize(
			() -> getExternalLibraries());

	/**
	 * Supplies the {@code .n4npm/node_modules} folder location form the worksapce's {@code .metadata} folder. This
	 * could be missing if the {@link Platform platform} is not running.
	 */
	public static final Supplier<File> N4_NPM_FOLDER_SUPPLIER = memoize(() -> getOrCreateNpmFolder());

	/**
	 * Supplies the local git repository root folder location. Ensures that the remote git repository is cloned in case
	 * of its absence. If the local git repository already exists, then it performs a hard reset on the {@code HEAD} of
	 * the {@code master} branch.
	 */
	public static final Supplier<File> N4_GIT_FOLDER_SUPPLIER = GitCloneSupplier.SUPPLIER;

	/** Shared private bundle context. */
	private static BundleContext context;

	/**
	 * Returns with the bundle context instance.
	 *
	 * @return the shared bundle context instance.
	 */
	public static BundleContext getContext() {
		return context;
	}

	@Override
	public void start(final BundleContext bundleContext) throws Exception {
		context = bundleContext;
		N4_NPM_FOLDER_SUPPLIER.get();
		// Client code can still clone the repository on demand. (Mind plug-in UI tests.)
		if (requiresInfrastructureForLibraryManager()) {
			new Thread(() -> {
				N4_GIT_FOLDER_SUPPLIER.get();
			}).start();
		}
	}

	@Override
	public void stop(final BundleContext bundleContext) throws Exception {
		context = null;
	}

	/**
	 * Returns with {@code true} if all the followings are {@code true}
	 * <p>
	 * <ul>
	 * <li>The {@link Platform#isRunning() platform is running}.</li>
	 * <li>The platforms runs a {@link IProduct product}.</li>
	 * <li>The platforms runs the N4JS IDE product and it is configured to include built-in libraries.</li>
	 * <ul>
	 * <li>The N4JS IDE runs in production mode {@code OR}</li>
	 * <li>The N4JS IDE runs in either {@link Platform#inDebugMode() debug mode} or {@link Platform#inDevelopmentMode()
	 * development mode} and the {@link #INCLUDES_BUILT_INS_SYSTEM_PROPERTY} is configured to be {@code true}</li>
	 * </ul>
	 * </ul>
	 * Otherwise returns with {@code false} and neither built-in libraries nor local git repository for the N4JS
	 * definition files has to be set up .
	 *
	 * @return {@code true} if the infrastructure is required for the built-in and NPM support.
	 */
	public static boolean requiresInfrastructureForLibraryManager() {
		if (Platform.isRunning()) {
			final IProduct product = Platform.getProduct();
			if (null != product) {
				if (parseBoolean(product.getProperty(INCLUDES_BUILT_INS_PRODUCT_PROPERTY))) {
					// Runs in *non-production* mode and the system property is NOT set to include the built-ins.
					if ((inDebugMode() || inDevelopmentMode())
							&& !parseBoolean(System.getProperty(INCLUDES_BUILT_INS_SYSTEM_PROPERTY))) {
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}

	private static BiMap<URI, String> getExternalLibraries() {

		final Bundle bundle = context.getBundle();
		checkNotNull(bundle, "Bundle was null. Does the platform running?");

		final Iterable<Pair<URI, String>> uriNamePairs = from(EXTERNAL_LIBRARY_FOLDER_NAMES)
				.transform(name -> bundle.getResource(name))
				.filter(notNull())
				.transform(URL_TO_FILE_URL_FUNC)
				.transform(URL_TO_URI_FUNC)
				.filter(notNull())
				.transform(uri -> new File(uri))
				.transform(FILE_TO_CANONICAL_FILE)
				.transform(file -> file.toURI())
				.transform(uri -> pair(uri, new File(uri).getName()));

		final Map<URI, String> uriMappings = newLinkedHashMap();

		// NPMs first to be able to shadow runtime libraries and Mangelhaft from NPMs.
		final File targetPlatformInstallLocation = N4_NPM_FOLDER_SUPPLIER.get();
		final File nodeModulesFolder = new File(targetPlatformInstallLocation, N4_NPM_CATEGORY);
		uriMappings.put(nodeModulesFolder.toURI(), N4_NPM_CATEGORY);

		for (final Pair<URI, String> pair : uriNamePairs) {
			uriMappings.put(pair.getFirst(), pair.getSecond());
		}

		return ImmutableBiMap.copyOf(uriMappings);

	}

	private static File getOrCreateNpmFolder() {
		final File targetPlatform = getOrCreateNestedFolder(N4_NPM_ROOT);
		final File nodeModulesFolder = new File(targetPlatform, N4_NPM_CATEGORY);
		if (!nodeModulesFolder.exists()) {
			checkState(nodeModulesFolder.mkdir(), "Error while creating " + nodeModulesFolder + " folder.");
		}
		checkState(nodeModulesFolder.isDirectory(), "Expecting directory but was a file: " + nodeModulesFolder + ".");

		final File targetPlatformFile = new File(targetPlatform, PackageJson.PACKAGE_JSON);
		if (!targetPlatformFile.exists()) {
			try {
				checkState(targetPlatformFile.createNewFile(), "Error while creating default target platform file.");
				try (final FileWriter fw = new FileWriter(targetPlatformFile)) {
					fw.write(PackageJson.createN4Default().toString());
					fw.flush();
				}
			} catch (final IOException e) {
				final String message = "Error while initializing default target platform file.";
				LOGGER.error(message, e);
				throw new RuntimeException(message, e);
			}
		}
		checkState(targetPlatformFile.isFile(),
				"Expecting file as the target platform file: " + targetPlatformFile + ".");

		return targetPlatform;
	}

	private static void log(final IStatus status) {
		if (null != status && Platform.isRunning() && null != context) {
			final Bundle bundle = context.getBundle();
			if (null != bundle) {
				Platform.getLog(bundle).log(status);
			}
		}
	}

	private static IStatus createError(final String message, final Throwable t) {
		return new Status(ERROR, PLUGIN_ID, message, t);
	}

	private static IStatus createInfo(final String message) {
		return new Status(INFO, PLUGIN_ID, message);
	}

	private static synchronized File getOrCreateNestedFolder(String path) {
		checkState(Platform.isRunning(), "Expected running platform.");
		final Bundle bundle = context.getBundle();
		checkNotNull(bundle, "Bundle was null. Does the platform running?");
		final File targetPlatform = InternalPlatform.getDefault().getStateLocation(bundle).append(path).toFile();
		if (!targetPlatform.exists()) {
			checkState(targetPlatform.mkdirs(), "Error while creating " + targetPlatform + " folder.");
		}
		checkState(targetPlatform.isDirectory(), "Expecting director but was a file: " + targetPlatform + ".");
		return targetPlatform;
	}

	/**
	 * Clones the remote git repository with the N4JS definition files.
	 */
	private static enum GitCloneSupplier implements Supplier<File> {

		SUPPLIER;

		private boolean successfullyCloned = false;

		@Override
		public File get() {
			if (!successfullyCloned) {
				synchronized (GitCloneSupplier.class) {
					if (!successfullyCloned) {
						final File gitRoot = getOrCreateNestedFolder(N4_GIT_ROOT);
						try {
							GitUtils.hardReset(N4_GIT_REMOTE_URL, gitRoot.toPath(), getMasterBranch(), true);
							GitUtils.pull(gitRoot.toPath());
							log(createInfo(
									"Local N4JS type definition files have been successfully prepared for npm support."));
							successfullyCloned = true;
							return gitRoot;
						} catch (final Exception e) {
							final String message = "Error occurred while preparing local git repository for N4JS type definition files.";
							LOGGER.error(message, e);
							log(createError(message, e));
							try {
								FileDeleter.delete(gitRoot.toPath()); // Clean up folder.
							} catch (final IOException e2) {
								final String message2 = "Error while cleaning up local git clone after failed clone.";
								LOGGER.error(message2, e2);
								log(createError(message2, e2));
							}
							successfullyCloned = false;
							return gitRoot;
						} finally {
							getOrCreateNestedFolder(N4_GIT_ROOT); // Make sure root is there even if clone failed.
						}
					}
				}
			}
			return getOrCreateNestedFolder(N4_GIT_ROOT);
		}

	}
}
