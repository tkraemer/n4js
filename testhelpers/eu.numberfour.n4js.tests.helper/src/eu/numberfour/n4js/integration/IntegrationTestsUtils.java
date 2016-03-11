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
package eu.numberfour.n4js.integration;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Iterables.size;
import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;
import static eu.numberfour.n4js.utils.Arrays2.isEmpty;
import static eu.numberfour.n4js.utils.git.GitUtils.getMasterBranch;
import static eu.numberfour.n4js.utils.git.GitUtils.hardReset;
import static java.io.File.separator;
import static java.lang.System.getProperty;
import static java.util.Collections.singletonList;
import static org.apache.log4j.Logger.getLogger;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.google.common.base.Joiner;

/**
 * Helper class for executing integration and performance tests. Such as {@code Fabelhaft} and {@code stdlib} tests.
 * Contains constants and useful convenient helper methods for test execution and test suite setup
 */
public abstract class IntegrationTestsUtils {

	private static final Logger LOGGER = getLogger(IntegrationTestsUtils.class);

	/**
	 * Environment variable for the workspace. Available only and if only the test is being executed via Maven or
	 * Jenkins.
	 */
	private static final String WORKSPACE_ENV = "WORKSPACE";

	/**
	 * The remote URL for the Fabelhaft test data. Points to the {@code n4ideintegration} repository. Not used when test
	 * is being run by Jenkins.
	 */
	private static final String FABELHAFT_REMOTE_URL = "ssh://git@github.numberfour.eu/NumberFour/n4ideintegration.git";

	/**
	 * Directory name of checkout-place, a folder with this name may end up in user-home directory. Used for the
	 * integration tests.
	 */
	private static final String FABELHAFT_INTEGRATION_CHECKOUT_DIR_NAME = "checkout_n4js.swtbot.test_fabelhaft.snapshot";

	/**
	 * Root folder name of the checkout folder of the
	 * <ul>
	 * <li>{@code stdlib_api},</li>
	 * <li>{@code stdlib_n4js},</li>
	 * <li>{@code mangelhaft} and</li>
	 * <li>{@code datamodel}</li>
	 * </ul>
	 * repositories for the {@code stdlib} integration test.
	 */
	public static final String STDLIB_INTEGRATION_CHECKOUT_DIR_NAME = "checkout_eu.numberfour.n4js.hlc.test_stdlib_snapshot";

	/**
	 * Folder name of the {@code stdlib_api}.
	 **/
	private static final String STDLIB_API_SUBFOLDER_NAME = "stdlib_api";

	/**
	 * Folder name of the {@code stdlib_n4js}.
	 */
	private static final String STDLIB_N4JS_SUBFOLDER_NAME = "stdlib_n4js";

	/**
	 * Folder name of the {@code mangelhaft_n4js}.
	 */
	private static final String MANGELHAFT_N4JS_SUBFOLDER_NAME = "mangelhaft_n4js";

	/**
	 * Folder name of the {@code datamodel}.
	 */
	private static final String DATAMODEL_SUBFOLDER_NAME = "datamodel";

	/**
	 * Remote URI for the {@code stdlib_api} repository.
	 */
	private static final String STDLIB_API_REMOTE_URI = "ssh://git@github.numberfour.eu/NumberFour/stdlib_api.git";

	/**
	 * Remote URI for the {@code stdlib_n4js} repository.
	 */
	private static final String STDLIB_N4JS_REMOTE_URI = "ssh://git@github.numberfour.eu/NumberFour/stdlib_n4js.git";

	/**
	 * Remote URI for the {@code mangelhaft_n4js} repository.
	 */
	private static final String MANGELHAFT_N4JS_REMOTE_URI = "ssh://git@github.numberfour.eu/NumberFour/mangelhaft_n4js.git";

	/**
	 * Remote URI for the {@code datamodel} repository.
	 */
	private static final String DATAMODEL_REMOTE_URI = "ssh://git@github.numberfour.eu/NumberFour/datamodel.git";

	/**
	 * Directory name of checkout-place, a folder with this name may end up in user-home directory. Used for the
	 * performance tests.
	 */
	private static final String FABELHAFT_PERFORMANCE_CHECKOUT_DIR_NAME = "checkout_n4js.swtbot.test_fabelhaft.performance";

	/**
	 * The name of the branch for representing the frozen version of {@code Fabelhaft} test data in the
	 * {@code n4ideintegration} repository.
	 */
	private static final String FABELHAFT_PERFORMANCE_TEST_BRANCH_NAME = "n4ide_fabelhaft_performance_snapshot";

	/**
	 * The name of the branch for representing the frozen version of {@code stdlib} test data in the in the
	 * {@code stdlib_n4js}, {@code stdlib_api}, {@code mangelhaft_n4js} and {@code datamodel} repositories.
	 */
	public static final String STDLIB_INTEGRATION_TEST_BRANCH_NAME = "n4ide_stdlib_integration_snapshot";

	/**
	 * Environment variable for the integration tests which can be used if any arbitrary custom branch should be checked
	 * out and used instead of the default {@code master} one.
	 * <p>
	 * This should be given as a VM argument:
	 *
	 * <pre>
	 * -Dn4ide.fabelhaft.integration.snapshot=your_branch_name
	 * </pre>
	 */
	public static final String N4IDE_FABELHAFT_INTEGRATION_SNAPSHOT_ENV = "n4ide.fabelhaft.integration.snapshot";

	/**
	 * Environment variable for the integration tests which can be used if any arbitrary custom branch should be checked
	 * out and used instead of the default {@code n4ide_stdlib_integration_snapshot} one.
	 * <p>
	 * This should be given as a VM argument.
	 *
	 * <pre>
	 * -Dn4ide.fabelhaft.performance.snapshot=your_branch_name
	 * </pre>
	 */
	public static final String N4IDE_FABELHAFT_PERFORMANCE_SNAPSHOT_ENV = "n4ide.fabelhaft.performance.snapshot";

	/**
	 * Environment variable for the integration tests which can be used if any arbitrary custom branch should be checked
	 * out and used instead of the default {@code n4ide_stdlib_integration_snapshot} one.
	 * <p>
	 * This should be given as a VM argument.
	 *
	 * <pre>
	 * -Dn4ide.stdlib.integration.snapshot=your_branch_name
	 * </pre>
	 */
	public static final String N4IDE_STDLIB_INTEGRATION_SNAPSHOT_ENV = "n4ide.stdlib.integration.snapshot";

	/**
	 * Ensures that the test data source exists for the given test type. If the local repository does not exist, clones
	 * it.
	 *
	 * @param testType
	 *            the type of the test.
	 */
	public static void ensureTestDataExistsFor(final TestType testType) {
		ensureTestDataExistsFor(testType, true);
	}

	/**
	 * Ensures that the test data source exists for the given test type. If the local repository does not exist, clones
	 * it on demand.
	 *
	 * @param testType
	 *            the type of the test.
	 * @param cloneIfMissing
	 *            if {@code true}, clones the repository content if it does not exist locally.
	 */
	public static void ensureTestDataExistsFor(final TestType testType, final boolean cloneIfMissing) {
		testType.ensureTestDataExists(cloneIfMissing);
	}

	/**
	 * Returns with the test data checkout paths of the integration/performance test for the given test type.
	 *
	 * @param testType
	 *            the type of the test.
	 * @return the local checkout paths to the test data.
	 */
	public static Iterable<Path> getTestDataCheckoutPathFor(final TestType testType) {
		return testType.getLocalClonePaths();
	}

	/** Concatenates the paths (given as string) using the platform dependent path {@link File#separator separator}. */
	private static String concatPath(final String left, final String right, final String... others) {
		return new StringBuilder(left)
				.append(separator)
				.append(right)
				.append(isEmpty(others) ? "" : separator)
				.append(Joiner.on(separator).join(others))
				.toString();
	}

	/**
	 * Enumeration of {@code Fabelhaft} test types. Encapsulates the logic for ensuring that the {@code Fabelhaft} test
	 * data source is available for the test suits. Also internally wraps the working branches for the cloned
	 * repositories.
	 */
	public static enum TestType {

		/**
		 * {@code Fabelhaft} integration test type.
		 */
		FABELHAFT_INTEGRATION_TEST(
				FABELHAFT_REMOTE_URL,
				FABELHAFT_INTEGRATION_CHECKOUT_DIR_NAME,
				getMasterBranch(),
				N4IDE_FABELHAFT_INTEGRATION_SNAPSHOT_ENV),

		/**
		 * {@code Fabelhaft} performance test type.
		 */
		FABELHAFT_PERFORMANCE_TEST(
				FABELHAFT_REMOTE_URL,
				FABELHAFT_PERFORMANCE_CHECKOUT_DIR_NAME,
				FABELHAFT_PERFORMANCE_TEST_BRANCH_NAME,
				N4IDE_FABELHAFT_PERFORMANCE_SNAPSHOT_ENV),

		/**
		 * {@code stdlib} integration test type.
		 */
		STDLIB_INTEGRATION_TEST(
				newArrayList(
						STDLIB_API_REMOTE_URI,
						STDLIB_N4JS_REMOTE_URI,
						MANGELHAFT_N4JS_REMOTE_URI,
						DATAMODEL_REMOTE_URI),
				newArrayList(
						concatPath(STDLIB_INTEGRATION_CHECKOUT_DIR_NAME, STDLIB_API_SUBFOLDER_NAME),
						concatPath(STDLIB_INTEGRATION_CHECKOUT_DIR_NAME, STDLIB_N4JS_SUBFOLDER_NAME),
						concatPath(STDLIB_INTEGRATION_CHECKOUT_DIR_NAME, MANGELHAFT_N4JS_SUBFOLDER_NAME),
						concatPath(STDLIB_INTEGRATION_CHECKOUT_DIR_NAME, DATAMODEL_SUBFOLDER_NAME)),
				STDLIB_INTEGRATION_TEST_BRANCH_NAME,
				N4IDE_STDLIB_INTEGRATION_SNAPSHOT_ENV);

		/**
		 * An iterable of remote Git URIs which the current test type uses.
		 */
		private final Iterable<String> remoteUris;
		/**
		 * An iterable of local Git repository paths where the remote Git content should be cloned.
		 */
		private final Iterable<String> checkoutDirNames;
		/**
		 * The name of the branch where the current test type should work on by default.
		 */
		private final String branch;
		/**
		 * An environment variable that could be used to override the default {@link #branch} value.
		 */
		private final String alternativeBranchEnvVariable;

		private TestType(final String remoteUri, final String checkoutDirName, final String branch,
				final String alternativeBranchEnvVariable) {
			this(singletonList(remoteUri), singletonList(checkoutDirName), branch, alternativeBranchEnvVariable);
		}

		private TestType(final Iterable<String> remoteUris, final Iterable<String> checkoutDirNames,
				final String branch, final String alternativeBranchEnvVariable) {

			checkArgument(size(remoteUris) == size(checkoutDirNames), "Remote URI - checkout directory name mismatch.");

			this.remoteUris = remoteUris;
			this.checkoutDirNames = checkoutDirNames;
			this.branch = branch;
			this.alternativeBranchEnvVariable = alternativeBranchEnvVariable;
		}

		private void ensureTestDataExists(final boolean cloneIfMissing) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.info("Ensuring test data exist for " + this + " test type.");
				LOGGER.info("Desired branch: " + getBranch());
				LOGGER.info("Clone if missing: " + cloneIfMissing);
				LOGGER.info("Remote repository URIs: " + Arrays.toString(toArray(remoteUris, Object.class)));
				LOGGER.info("Local clone paths: " + Arrays.toString(toArray(checkoutDirNames, Object.class)));
				dumpEnvVariables();
			}
			hardReset(remoteUris, getLocalClonePaths(), getBranch(), cloneIfMissing);
		}

		private Iterable<Path> getLocalClonePaths() {
			final String workspace = getProperty(WORKSPACE_ENV, getProperty("user.home"));
			return from(checkoutDirNames)
					.transform(dirName -> new File(workspace + separator + dirName).getAbsoluteFile().toPath());
		}

		/**
		 * Returns with the name of the branch where the test data integrity should be ensured. This method may override
		 * the default {@link #branch} of the particular test type if it was reconfigured with the proper VM argument.
		 */
		private String getBranch() {
			final String reconfiguredBranch = System.getProperty(alternativeBranchEnvVariable);
			if (isNullOrEmpty(reconfiguredBranch) || "null".equals(reconfiguredBranch)) {
				LOGGER.info("No custom branch configuration is available for " + this + " test type.");
				LOGGER.info("Falling back to default '" + this.branch + "' branch.");
				return this.branch;
			} else {
				LOGGER.info("Custom branch configuration is available for " + this + " test type.");
				LOGGER.info(
						"Using '" + reconfiguredBranch + "' branch instead of the default '" + this.branch + "' one.");
				return reconfiguredBranch;
			}
		}

		/**
		 * Dumps the environment variables to the logger, if the log level is debug or lower.
		 */
		private void dumpEnvVariables() {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(new StringBuilder("\n==================== Environment variables =======================\n")
						.append(Joiner.on("").join(from(
								System.getenv().entrySet())
										.transform(entry -> "\n" + entry.getKey() + "=" + entry.getValue()))
								.toString())
						.append("\n==================================================================\n")
						.toString());
			}
		}

	}

}
