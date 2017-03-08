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

import java.io.File;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import eu.numberfour.n4js.binaries.nodejs.NodeProcesBuilder;
import eu.numberfour.n4js.utils.process.OutputRedirection;
import eu.numberfour.n4js.utils.process.ProcessExecutionCommand;
import eu.numberfour.n4js.utils.process.ProcessExecutor;
import eu.numberfour.n4js.utils.process.ProcessResult;

/**
 * Factory for commands used to interact with external binaries.
 */
@Singleton
public class BinaryCommandFactory {

	@Inject
	private ProcessExecutor processExecutor;

	@Inject
	private NodeProcesBuilder nodeProccessBuilder;

	/**
	 * Creates command that will execute external node process that will command npm to install given package.
	 *
	 * @param installPath
	 *            path where package is supposed to be installed
	 * @param packageName
	 *            name of the package to install
	 * @param saveDependency
	 *            flag if installed package should be saved in package.json of the install path
	 */
	public ProcessExecutionCommand createInstallPackageCommand(File installPath,
			String packageName, boolean saveDependency) {
		return new ProcessExecutionCommand() {
			private static final String COMMAND_NAME = "npm_install";

			@Override
			public ProcessResult execute() {
				ProcessBuilder processBuilder = nodeProccessBuilder.getNpmInstallProcessBuilder(installPath,
						packageName, saveDependency);
				return processExecutor.createAndExecute(processBuilder, COMMAND_NAME, OutputRedirection.REDIRECT);
			}
		};
	}

	/**
	 * Creates command that will execute external node process that will command npm to uninstall given package.
	 *
	 * @param uninstallPath
	 *            path where package is supposed to be installed
	 * @param packageName
	 *            name of the package to uninstall
	 * @param saveDependency
	 *            flag if uninstalled package should be saved in package.json of the uninstall path
	 */
	public ProcessExecutionCommand createUninstallPackageCommand(File uninstallPath,
			String packageName, boolean saveDependency) {
		return new ProcessExecutionCommand() {
			private static final String COMMAND_NAME = "npm_uninstall";

			@Override
			public ProcessResult execute() {
				ProcessBuilder processBuilder = nodeProccessBuilder.getNpmUninstallProcessBuilder(uninstallPath,
						packageName, saveDependency);
				return processExecutor.createAndExecute(processBuilder, COMMAND_NAME, OutputRedirection.REDIRECT);
			}
		};
	}

	/**
	 * Creates command that will execute external node process to resolve the main module.
	 *
	 * @param packageRoot
	 *            package name to resolve.
	 */
	public ProcessExecutionCommand createResolveMainModuleCommand(File packageRoot) {
		return new ProcessExecutionCommand() {
			private static final String COMMAND_NAME = "node_require_resolve";

			@Override
			public ProcessResult execute() {
				ProcessBuilder processBuilder = nodeProccessBuilder.prepareMainModuleResolveProcessBuilder(packageRoot);
				return processExecutor.createAndExecute(processBuilder, COMMAND_NAME, OutputRedirection.SUPPRESS);
			}
		};
	}

	/**
	 * Creates {@link ProcessExecutionCommand} responsible for binary version check
	 *
	 * @param binary
	 *            to be invoked
	 */
	public ProcessExecutionCommand checkBinaryVersionCommand(Binary binary) {
		return new ProcessExecutionCommand() {

			@Override
			public ProcessResult execute() {
				ProcessBuilder processBuilder = nodeProccessBuilder.createVersionCheckProcess(binary);
				return processExecutor.createAndExecute(processBuilder, binary.getLabel(), OutputRedirection.SUPPRESS);
			}
		};
	}
}
