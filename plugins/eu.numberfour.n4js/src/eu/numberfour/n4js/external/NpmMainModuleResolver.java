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

import static eu.numberfour.n4js.utils.OSInfo.isWindows;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.log4j.Logger;
import org.eclipse.xtext.xbase.lib.StringExtensions;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import eu.numberfour.n4js.binaries.nodejs.NodeJsBinary;

/**
 * Resolves main module for given npm package.
 */
public class NpmMainModuleResolver {

	private static final Logger LOGGER = Logger.getLogger(NpmMainModuleResolver.class);
	private static final String NL = System.lineSeparator();

	@Inject
	private Provider<NodeJsBinary> nodeBinaryProvider;

	@Inject
	private OutputStreamPrinterThreadProvider printerThreadProvider;

	/**
	 * Calls node process to resolve main module of the provided npm package.
	 *
	 * @param packageRoot
	 *            package root folder
	 * @return string with absolute path to the package main module
	 * @throws IOException,
	 *             InterruptedException if cannot resolve main module
	 */
	public String resolveMainModule(File packageRoot)
			throws IOException, InterruptedException {

		ProcessRunResult result = runProcess(createProcessBuilder(packageRoot));
		if (result.exitCode == 0) {
			// happy case string with full path to the main module (terminated with line ending)
			return result.processOutput.std.trim();
		}
		throw new IOException(result.processOutput.err);
	}

	private ProcessBuilder createProcessBuilder(File packageRoot) {
		NodeJsBinary nodeBinary = nodeBinaryProvider.get();
		final Builder<String> builder = ImmutableList.<String> builder();
		if (isWindows()) {
			builder.add("cmd");
			builder.add("/c");
			builder.add("\"" + nodeBinary.getCommandWithAbsolutePath() + "\" ");
			builder.add("-e");
			builder.add("console.log(require.resolve('" + packageRoot.getName() + "'));");
		} else {
			builder.add("sh");
			builder.add("-c");
			builder.add("\"" + nodeBinary.getCommandWithAbsolutePath()
					+ "\" -e \"console.log(require.resolve('" + packageRoot.getName() + "'));\"");
		}

		final ProcessBuilder pb = new ProcessBuilder(builder.build());
		pb.directory(packageRoot);
		pb.redirectErrorStream(false);
		nodeBinary.updateEnvironment(pb.environment());
		return pb;
	}

	/**
	 * Executes process described by the passed {@link ProcessBuilder}. Returns with the exit code of the executed
	 * process.
	 *
	 */
	private ProcessRunResult runProcess(ProcessBuilder pb) throws IOException, InterruptedException {
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
				return new ProcessRunResult(exitCode, captureOutput);
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
	}

	private static final class ProcessRunResult {
		public final ProcessOutput processOutput;
		public final int exitCode;

		/**
		 *
		 */
		public ProcessRunResult(int exitCode, ProcessOutput processOutput) {
			this.exitCode = exitCode;
			this.processOutput = processOutput;
		}
	}
}
