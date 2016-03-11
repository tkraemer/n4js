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

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.core.runtime.Status.OK_STATUS;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.xtext.xbase.lib.StringExtensions;

import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.binaries.nodejs.NpmBinary;
import eu.numberfour.n4js.utils.OSInfo;
import eu.numberfour.n4js.utils.StatusHelper;

/**
 */
public class NpmConnector {

	private static Logger LOGGER = Logger.getLogger(NpmConnector.class);
	private static String NL = System.lineSeparator();

	@Inject
	private StatusHelper statusHelper;

	@Inject
	private OutputStreamPrinterThreadProvider printerThreadProvider;

	@Inject
	private Provider<NpmBinary> npmBinaryProvider;

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
	public IStatus installPackage(File installPath, String packageName) throws IOException, InterruptedException {
		if (packageName == null || packageName.trim().isEmpty()) {
			return statusHelper.createError("Malformed npm package name: '" + packageName + "'.");
		}
		return runProcess(getNpmInstallProcessBuilder(installPath, packageName, true));
	}

	/**
	 * Prepares process builder for "npm install" command. Returned process builder is configured for data passed in and
	 * is operating system aware configuration. Changing its internal state is not recommended.
	 *
	 * @param installPath
	 *            location on which npm command should be invoked
	 * @param packageName
	 *            package to install (might be space separated list of names)
	 * @param save
	 *            instructs npm to save installed packages in package.json (if available)
	 * @return configured, operating system aware process builder for "npm install" command
	 */
	private ProcessBuilder getNpmInstallProcessBuilder(File installPath, String packageName, boolean save) {
		if (OSInfo.isWindows()) {
			return getNpmInstallWinProcessBuilder(installPath, packageName, save);
		}
		return getNpmInstallNixProcessBuilder(installPath, packageName, save);
	}

	/** @see #getNpmInstallProcessBuilder */
	private ProcessBuilder getNpmInstallNixProcessBuilder(File installPath, String packageName, boolean save) {
		String saveCommand = save ? " --save" : "";

		NpmBinary npmBinary = npmBinaryProvider.get();
		ProcessBuilder pb = new ProcessBuilder("sh", "-l", "-c",
				npmBinary.getCommandWithAbsolutePath() + " install " + packageName + saveCommand);
		pb.directory(installPath);
		pb.redirectErrorStream(false);
		npmBinary.updateEnvironment(pb.environment());
		return pb;
	}

	/**
	 * @see #getNpmInstallProcessBuilder
	 */
	private ProcessBuilder getNpmInstallWinProcessBuilder(File installPath, String packageName, boolean save) {
		NpmBinary npmBinary = npmBinaryProvider.get();
		List<String> commands = newArrayList("cmd", "/c", npmBinary.getCommandWithAbsolutePath(), "install",
				packageName);
		if (save) {
			commands.add("--save");
		}
		ProcessBuilder pb = new ProcessBuilder(commands);
		pb.directory(installPath);
		pb.redirectErrorStream(false);
		npmBinary.updateEnvironment(pb.environment());
		return pb;
	}

	/**
	 * Executes process described by the passed {@link ProcessBuilder}. Returns with the exit code of the executed
	 * process.
	 */
	private IStatus runProcess(ProcessBuilder pb) throws IOException, InterruptedException {
		Process process = null;
		try {

			process = pb.start();

			try (OutputStreamPrinterThread stdOutThread = printerThreadProvider.getPrinterThreadForStdOut(process);
					OutputStreamPrinterThread stdErrThread = printerThreadProvider.getPrinterThreadForStdErr(process)) {

				int exitCode = process.waitFor();
				stdOutThread.join(5L);
				stdErrThread.join(5L);

				ProcessOutput captureOutput = new ProcessOutput(stdOutThread.toString(), stdErrThread.toString());
				final String processLog = captureOutput.toString();
				if (LOGGER.isDebugEnabled()) {
					if (!StringExtensions.isNullOrEmpty(processLog)) {
						LOGGER.info(processLog);
					}
				}

				process.destroy();
				if (0 != exitCode) {
					final Throwable cause = captureOutput.toThrowable();
					if (null != cause) {
						return statusHelper.createError(cause.getMessage(), cause);
					} else {
						return statusHelper.createError(processLog, cause);
					}
				} else {
					return OK_STATUS;
				}

			}

		} finally {
			if (process != null && process.isAlive()) {
				process.destroyForcibly();// try force close
				process = null;// and forget
			}
		}
	}

	/** Container for storing output information about an executed process. */
	private static final class ProcessOutput {

		private final String std;
		private final String err;

		public ProcessOutput(String std, String err) {
			this.std = Strings.nullToEmpty(std);
			this.err = Strings.nullToEmpty(err);
		}

		@Override
		public String toString() {
			return std + NL + err;
		}

		private Throwable toThrowable() {
			if (Strings.isNullOrEmpty(err)) {
				return null;
			}
			final Exception exc = new Exception("Error while installing npm package." + NL + NL + err);
			exc.setStackTrace(new StackTraceElement[0]);
			return exc;
		}

	}

}
