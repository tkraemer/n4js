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
package eu.numberfour.n4js.binaries;

import static eu.numberfour.n4js.utils.OSInfo.isWindows;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.eclipse.core.runtime.Status.OK_STATUS;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.inject.Inject;

import eu.numberfour.n4js.utils.StatusHelper;
import eu.numberfour.n4js.utils.Version;
import eu.numberfour.n4js.utils.process.ProcessResult;
import eu.numberfour.n4js.utils.process.ProcessResultFactory;

/**
 * Class for validating {@link Binary binaries} with respect to their existence, accessibility and version.
 */
public class BinariesValidator {

	private static final Logger LOGGER = Logger.getLogger(BinariesValidator.class);

	/**
	 * Default timeout for the integration test script running in seconds.
	 */
	private static final long DEFAULT_PROCESS_TIMEOUT_IN_SECONDS = 30L;

	@Inject
	private StatusHelper status;

	@Inject
	private ProcessResultFactory processResultFactory;

	/**
	 * Validates the availability, accessibility and version of the given binary. Returns with a status representing the
	 * outcome of the validation process.
	 *
	 * @param binary
	 *            the binary to validate.
	 * @return a status representing the outcome of the validation process.
	 */
	public IStatus validate(final Binary binary) {

		final File file = new File(binary.getCommandWithAbsolutePath());
		if (!file.exists()) {
			return error(binary, "'" + binary.getLabel() + "' binary does not exist at " + file
					+ ". Please check your preferences.");
		}
		if (!file.isFile()) {
			return error(binary, "Invalid '" + binary.getLabel() + "' configuration. Expected file at: " + file
					+ ". Got a directory instead.");
		}
		if (!file.canExecute()) {
			return error(binary, "Cannot execute '" + binary.getLabel() + "' binary at: " + file + ".");
		}

		Process process = null;

		try {
			process = createProcessBuilder(binary).start();
			final ProcessResult result = processResultFactory.createNewResult(process);

			if (0 == result.getExitCode()) {

				final String stdOutString = result.getStdOutString();
				final Version currentVersion = Version.createFromString(stdOutString);
				if (!Version.isValid(currentVersion)) {
					return error(binary,
							"Cannot find current version of '" + binary.getLabel() + "' binary. Output was: "
									+ stdOutString);
				} else {
					final Version minimumVersion = binary.getMinimumVersion();
					if (0 < minimumVersion.compareTo(currentVersion)) {
						return error(binary,
								"The required minimum version of '" + binary.getLabel() + "' is '" + minimumVersion
										+ "'. Currently configured version is '" + currentVersion + "'.");
					}
					return OK_STATUS;
				}

			} else {
				return error(binary, "Expected exit code 0 when checking version of '" + binary.getLabel() + "' got "
						+ result.getExitCode() + "' instead.\n" + result.getStdErrString());
			}

		} catch (final IOException e) {
			final String message = "Unexpected error while validating '" + binary.getLabel() + ".\n" + e.getMessage();
			LOGGER.error(message, e);
			return error(binary, message, e);
		} finally {
			if (null != process) {
				info("Ensuring spawned '" + binary.getLabel() + "' process is terminated properly...");
				if (process.isAlive()) {
					try {
						process.destroyForcibly().waitFor(DEFAULT_PROCESS_TIMEOUT_IN_SECONDS, SECONDS);
					} catch (final InterruptedException e) {
						LOGGER.error("Error while trying to forcefully terminate '" + binary.getLabel() + "' process.",
								e);
					}
					if (!process.isAlive()) {
						info("Spawned '" + binary.getLabel() + "' process was successfully terminated.");
					} else {
						LOGGER.warn(
								"Cannot terminate '" + binary.getLabel() + "' subprocess. Termination timeouted after "
										+ DEFAULT_PROCESS_TIMEOUT_IN_SECONDS + " " + SECONDS + ".");
					}
				} else {
					info("Spawned '" + binary.getLabel() + "' process was successfully terminated.");
				}
			}
		}

	}

	private ProcessBuilder createProcessBuilder(final Binary binary) {
		final Builder<String> builder = ImmutableList.<String> builder();
		if (isWindows()) {
			builder.add("cmd");
			builder.add("/c");
		} else {
			builder.add("sh");
			builder.add("-c");
		}
		// Escaping path for example C:\Program Files\nodejs will be "C:\Program Files\nodejs".
		builder.add("\"" + binary.getCommandWithAbsolutePath() + "\" " + binary.getVersionArgument());
		final ProcessBuilder processBuilder = new ProcessBuilder(builder.build());
		binary.updateEnvironment(processBuilder.environment());
		return processBuilder;
	}

	private IStatus error(final Binary binary, final String message) {
		return error(binary, message, null);
	}

	private IStatus error(final Binary binary, final String message, Throwable t) {
		final IStatus delegate = status.createError(message, IllegalBinaryStateException.ISSUE_CODE, t);
		return new BinaryStatus(delegate, binary);
	}

	private void info(final Object message) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info(message);
		}
	}

}
