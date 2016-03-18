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
package eu.numberfour.n4js.utils.process;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Process executor that either executes provided {@link Process} or uses provided {@link ProcessBuilder} to create
 * process. Obtained process is executed and {@link ProcessResult result} is returned.
 */
public class ProcessExecutor {

	@Inject
	private OutputStreamPrinterThreadProvider printerThreadProvider;

	private static final Logger LOGGER = Logger.getLogger(ProcessExecutor.class);

	private static final int ERROR_EXIT_CODE = -1;

	/**
	 * Default timeout for the integration test script running in seconds.
	 */
	private static final long DEFAULT_PROCESS_TIMEOUT_IN_SECONDS = 30L;

	/**
	 * Convenience method delegates to {@link #createAndExecute(ProcessBuilder, String)} with <code>null</code> as name}
	 */
	public ProcessResult createAndExecute(final ProcessBuilder processBuilder) {
		return createAndExecute(processBuilder, null);
	}

	/**
	 * Convenience method delegates to {@link #execute(Process, String)} with <code>null</code> as name}
	 */
	public ProcessResult execute(final Process process) {
		return execute(process, null);
	}

	/**
	 * Convenience method for clients that want to execute process from prepared {@link ProcessBuilder}. Delegate to
	 * {@link #execute(Process, String)}
	 *
	 * @param processBuilder
	 *            the {@link ProcessBuilder} used to create process
	 * @param name
	 *            name used in debug messages, null empty string used
	 * @return a new result object that represents the actual result of the created process execution
	 */
	public ProcessResult createAndExecute(final ProcessBuilder processBuilder, String name) {
		// prepare name to be used in log messages
		String pbName = name == null ? " " : " for process '" + name + "' ";

		if (processBuilder == null) {
			LOGGER.info("Provided process buidler" + pbName + "was null");
			return new ProcessResult(ERROR_EXIT_CODE, "", "");
		}

		try {
			return execute(processBuilder.start(), name);
		} catch (IOException e) {
			LOGGER.error("Cannot start process from process builder" + pbName, e);
			return new ProcessResult(ERROR_EXIT_CODE, "", "");
		}
	}

	/**
	 * Waits until the process terminates, the returns with a result wrapping the exit code of the terminated process
	 * and the standard output and error output of the process.
	 *
	 * @param process
	 *            the process to execute
	 * @param name
	 *            name used in debug messages, null empty string used
	 * @return a new result object that represents the actual result of the created process execution
	 */
	public ProcessResult execute(final Process process, String name) {
		// prepare name to be used in log messages
		name = name == null ? " " : " '" + name + "' ";

		int exitCode = ERROR_EXIT_CODE;
		try {

			if (process == null) {
				LOGGER.error("Started process" + name + "was null");
				return new ProcessResult(exitCode, "", "");
			}

			try (OutputStreamPrinterThread stdOutThread = printerThreadProvider.getPrinterThreadForStdOut(process);
					OutputStreamPrinterThread stdErrThread = printerThreadProvider.getPrinterThreadForStdErr(process)) {

				exitCode = process.waitFor();
				stdOutThread.join(5L);
				stdErrThread.join(5L);

				ProcessResult captureOutput = new ProcessResult(exitCode, stdOutThread.toString(),
						stdErrThread.toString());
				if (LOGGER.isDebugEnabled()) {
					final String processLog = captureOutput.toString();
					if (!StringExtensions.isNullOrEmpty(processLog)) {
						LOGGER.debug(processLog);
					}
				}

				process.destroy();
				return captureOutput;
			}

		} catch (final InterruptedException e) {
			LOGGER.error("Thread was interrupted while waiting for process" + name + " to end.", e);
			return new ProcessResult(exitCode, "", writeStackeTrace(e));

		} finally {
			if (process != null && process.isAlive()) {
				// try to force close
				try {
					process.destroyForcibly().waitFor(DEFAULT_PROCESS_TIMEOUT_IN_SECONDS, SECONDS);
				} catch (final InterruptedException e) {
					LOGGER.error("Error while trying to forcefully terminate" + name + "process.",
							e);
				}
				if (!process.isAlive()) {
					LOGGER.info("Spawned" + name + "process was successfully terminated.");
				} else {
					// there is nothing else we can do about it
					LOGGER.error(
							"Cannot terminate" + name + "subprocess. Termination timeouted after "
									+ DEFAULT_PROCESS_TIMEOUT_IN_SECONDS + " " + SECONDS + ".");
				}
			} else {
				LOGGER.info("Spawned " + name + "process was successfully terminated.");
			}
		}
	}

	private String writeStackeTrace(final Throwable t) {
		final StringBuilder sb = new StringBuilder(t.getClass().getName());
		for (final StackTraceElement element : t.getStackTrace()) {
			sb.append("\tat ").append(element.getClassName())
					.append(".").append(element.getMethodName())
					.append("(").append(element.getFileName())
					.append(":").append(element.getLineNumber())
					.append(")");
		}
		return sb.toString();
	}

}
