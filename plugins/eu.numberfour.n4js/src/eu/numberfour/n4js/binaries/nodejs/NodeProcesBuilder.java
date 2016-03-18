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
package eu.numberfour.n4js.binaries.nodejs;

import static eu.numberfour.n4js.utils.OSInfo.isWindows;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.google.inject.Singleton;

import eu.numberfour.n4js.binaries.Binary;

/**
 * Factory for the processes interacting with Node.js Takes care of OS specific data.
 */
@Singleton
public class NodeProcesBuilder {
	private static String[] WIN_SHELL_COMAMNDS = { "cmd", "/c" };
	private static String[] NIX_SHELL_COMAMNDS = { "sh", "-c" };

	@Inject
	private Provider<NodeJsBinary> nodeBinaryProvider;
	@Inject
	private Provider<NpmBinary> npmBinaryProvider;

	/**
	 * Prepares process builder configured to invoke Node.js for main module resolution.
	 *
	 * @param packageRoot
	 *            package name to resolve.
	 * @return configured process builder
	 */
	public ProcessBuilder prepareMainModuleResolveProcessBuilder(File packageRoot) {
		final Builder<String> builder = ImmutableList.<String> builder();
		NodeJsBinary nodeBinary = nodeBinaryProvider.get();
		if (isWindows()) {
			builder.add(WIN_SHELL_COMAMNDS);
			builder.add("\"" + nodeBinary.getBinaryAbsolutePath() + "\" ");
			builder.add("-e");
			builder.add("console.log(require.resolve('" + packageRoot.getName() + "'));");
		} else {
			builder.add(NIX_SHELL_COMAMNDS);
			builder.add("\"" + nodeBinary.getBinaryAbsolutePath()
					+ "\" -e \"console.log(require.resolve('" + packageRoot.getName() + "'));\"");
		}

		return create(builder.build(), nodeBinary, packageRoot, false);
	}

	/**
	 * Prepares process builder for "npm install" command.
	 *
	 * @param installPath
	 *            location on which npm command should be invoked
	 * @param packageName
	 *            package to install (might be space separated list of names)
	 * @param save
	 *            instructs npm to save installed packages in package.json (if available)
	 * @return configured, operating system aware process builder for "npm install" command
	 */
	public ProcessBuilder getNpmInstallProcessBuilder(File installPath, String packageName, boolean save) {
		Builder<String> builder = ImmutableList.<String> builder();
		NpmBinary npmBinary = npmBinaryProvider.get();
		String saveCommand = save ? " --save" : "";

		if (isWindows()) {
			builder.add(WIN_SHELL_COMAMNDS);
			builder.add(npmBinary.getBinaryAbsolutePath(), "install", packageName + saveCommand);
		} else {
			builder.add(NIX_SHELL_COMAMNDS);
			builder.add(npmBinary.getBinaryAbsolutePath() + " install " + packageName + saveCommand);

		}

		return create(builder.build(), npmBinary, installPath, false);
	}

	/**
	 * Prepares process builder for process checking version of the provided binary.
	 *
	 * Note this process will not use binary providers, but provided binary directly. This is due to the fact, that this
	 * Implementation will be used to reconfigure binary providers themselves.
	 *
	 * @param binary
	 *            to be invoked
	 * @return configured process
	 */
	public ProcessBuilder createVersionCheckProcess(final Binary binary) {
		final Builder<String> builder = ImmutableList.<String> builder();
		if (isWindows()) {
			builder.add(WIN_SHELL_COMAMNDS);
		} else {
			builder.add(NIX_SHELL_COMAMNDS);
		}
		// Escaping path for example C:\Program Files\nodejs will be "C:\Program Files\nodejs".
		builder.add("\"" + binary.getBinaryAbsolutePath() + "\" " + binary.getVersionArgument());

		return create(builder.build(), binary, null, false);
	}

	private ProcessBuilder create(List<String> commands, Binary binary, File workingDirectory,
			boolean redirectErrorStream) {
		final ProcessBuilder processBuilder = new ProcessBuilder(commands);
		processBuilder.redirectErrorStream(redirectErrorStream);
		binary.updateEnvironment(processBuilder.environment());
		if (workingDirectory != null)
			processBuilder.directory(workingDirectory);
		return processBuilder;
	}

}
