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
package eu.numberfour.n4js.runner;

import static eu.numberfour.n4js.N4JSGlobals.JS_FILE_EXTENSION;
import static eu.numberfour.n4js.N4JSGlobals.N4JS_FILE_EXTENSION;
import static eu.numberfour.n4js.utils.io.FileUtils.getTempFolder;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import eu.numberfour.n4js.generator.common.CompilerUtils;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.runner.RunnerHelper.ApiUsage;
import eu.numberfour.n4js.runner.extension.IRunnerDescriptor;
import eu.numberfour.n4js.runner.extension.RunnerRegistry;
import eu.numberfour.n4js.runner.extension.RuntimeEnvironment;

/**
 * Entry point for running N4JS code. All client code should only use this class and the corresponding class
 * <code>RunnerFrontEndUI</code> for running code within Eclipse.
 */
@Singleton
public class RunnerFrontEnd {

	@Inject
	private CompilerUtils compilerUtils;

	@Inject
	private RunnerHelper runnerHelper;

	@Inject
	private RuntimeEnvironmentsHelper hRuntimeEnvironments;

	@Inject
	private RunnerRegistry runnerRegistry;

	/**
	 * Returns true iff the runner with the given id can run the given moduleToRun. Takes same arguments as
	 * {@link #createConfiguration(String, String, URI)}.
	 */
	@SuppressWarnings("unused")
	public boolean canRun(String runnerId, URI moduleToRun) {
		// FIXME IDE-1393 implement this method and call it from SupportingRunnerPropertyTester
		throw new UnsupportedOperationException();
	}

	/**
	 * Create a new run configuration from scratch for running the given moduleToRun.
	 *
	 * @param runnerId
	 *            identifier of the runner to use.
	 * @param implementationId
	 *            implementation ID to use or <code>null</code>. See {@link RunConfiguration#getImplementationId() here}
	 *            for details.
	 * @param moduleToRun
	 *            the module to execute. When running in Eclipse, this will be a platform resource URI, in the headless
	 *            case it will be a file URI.
	 * @return the run configuration.
	 */
	public RunConfiguration createConfiguration(String runnerId, String implementationId, URI moduleToRun) {
		return createConfiguration(runnerId, implementationId, null, moduleToRun);
	}

	/**
	 * Create a new run configuration from scratch for running the given moduleToRun.
	 *
	 * @param runnerId
	 *            identifier of the runner to use.
	 * @param implementationId
	 *            implementation ID to use or <code>null</code>. See {@link RunConfiguration#getImplementationId() here}
	 *            for details.
	 * @param systemLoader
	 *            the application specific unique ID of the javascript system loader. For more details about the
	 *            available options, check {@link SystemLoaderInfo}. If {@code null}, then the default System.js loader
	 *            will be used.
	 * @param moduleToRun
	 *            the module to execute. When running in Eclipse, this will be a platform resource URI, in the headless
	 *            case it will be a file URI.
	 * @return the run configuration.
	 */
	public RunConfiguration createConfiguration(String runnerId, String implementationId, String systemLoader,
			URI moduleToRun) {

		final IRunnerDescriptor runnerDesc = runnerRegistry.getDescriptor(runnerId);
		final IRunner runner = runnerDesc.getRunner();

		final RunConfiguration config = runner.createConfiguration();
		config.setName(runnerHelper.computeConfigurationName(runnerId, moduleToRun));
		config.setRunnerId(runnerId);
		config.setRuntimeEnvironment(runnerDesc.getEnvironment());
		config.setImplementationId(implementationId);
		config.setUserSelection(moduleToRun);
		if (null != SystemLoaderInfo.fromString(systemLoader)) {
			config.setSystemLoader(systemLoader);
		}

		computeDerivedValues(config);

		return config;
	}

	/**
	 * Restores a run configuration that was previously "serialized" with method
	 * {@link RunConfiguration#readPersistentValues()}. This method should only be used by
	 * <code>RunConfigurationConverter</code>.
	 */
	public RunConfiguration createConfiguration(Map<String, Object> values) {
		final String runnerId = RunConfiguration.getString(values, RunConfiguration.RUNNER_ID, false);
		final IRunner runner = runnerRegistry.getRunner(runnerId);

		final RunConfiguration config = runner.createConfiguration();
		config.writePersistentValues(values);

		computeDerivedValues(config);

		return config;
	}

	/**
	 * Create runner-config customized for this Xpect test. cf. eu.numberfour.n4js.xpect.XpectN4JSES5TranspilerHelper
	 */
	public RunConfiguration createXpectOutputTestConfiguration(String runnerId,
			String userSelectionNodePathResolvableTargetFileName, SystemLoaderInfo systemLoader) {

		final IRunnerDescriptor runnerDesc = runnerRegistry.getDescriptor(runnerId);
		final IRunner runner = runnerDesc.getRunner();

		final RunConfiguration config = runner.createConfiguration();
		config.setName(runnerId + "__" + userSelectionNodePathResolvableTargetFileName);
		config.setRuntimeEnvironment(runnerDesc.getEnvironment());
		config.setImplementationId(null);
		config.setRunnerId(runnerId);
		config.setSystemLoader(systemLoader.getId());

		config.setUseDefaultBootstrap(true);

		config.setCoreProjectPaths(Lists.newArrayList(getTempFolder().toString()));

		config.setExecutionData(RunConfiguration.EXEC_DATA_KEY__USER_SELECTION,
				userSelectionNodePathResolvableTargetFileName);
		config.setExecutionData(RunConfiguration.EXEC_DATA_KEY__INIT_MODULES, config.getInitModules());

		IRunner preparedRunner = runnerRegistry.getRunner(config);
		preparedRunner.prepareConfiguration(config);

		return config;
	}

	/**
	 * Computes all derived values in the given run configuration from the primary values (see {@link RunConfiguration
	 * here} for details on primary and derived values).
	 * <p>
	 * This method is called from methods {@link #createConfiguration(String, String, URI)} and
	 * {@link #createConfiguration(Map)}, so client code usually does not need to call it directly.
	 */
	public void computeDerivedValues(RunConfiguration config) {

		// 1) for all API projects among the direct and indirect dependencies we have to provide a mapping
		// from the artifactId of the API project to the artifactId of the implementation project to be used
		final ApiUsage apiUsage = runnerHelper
				.getProjectExtendedDepsAndApiImplMapping(config.getRuntimeEnvironment(),
						config.getUserSelection(), config.getImplementationId(), true);

		final List<IN4JSProject> deps = apiUsage.projects;
		final Map<IN4JSProject, IN4JSProject> apiImplProjectMapping = apiUsage.concreteApiImplProjectMapping;

		config.setApiImplProjectMappingFromProjects(apiImplProjectMapping);

		// 2) collect paths to all output folders of all N4JS projects (dependencies) with the compiled code
		// (these are the .../es5/ folders)
		final List<IN4JSProject> depsImpl = deps.stream().map(p -> {
			final IN4JSProject p2 = apiImplProjectMapping.get(p);
			final IN4JSProject p3 = p2 != null ? p2 : p;
			return p3;
		}).collect(Collectors.toList());
		final Collection<String> coreProjectPaths = runnerHelper.getCoreProjectPaths(depsImpl);
		config.setCoreProjectPaths(coreProjectPaths);

		// 3) collect custom(!) init modules based on selected runtime environment
		final List<String> initModulePaths = getInitModulesPathsFrom(config.getRuntimeEnvironment());
		config.setInitModules(initModulePaths);

		// 4) find compiled .js file to be executed first (the "executionModule" of the runtime environment)
		Optional<String> executionModule = getExecModulePathFrom(config.getRuntimeEnvironment());
		if (executionModule.isPresent()) {
			config.setExecModule(executionModule.get());
		}

		// 5) find out if runner should use default bootstrap code
		final boolean useDefaultBootstrap = config.getInitModules().isEmpty()
				&& (config.getExecModule() == null || config.getExecModule().isEmpty());
		config.setUseDefaultBootstrap(useDefaultBootstrap);

		// 6) create execution data from user selection, init modules, and apiImplProjectMapping
		/*
		 * this should transform user selection into execution data, but concrete runner/tester may transform this
		 * further into its own representation.
		 *
		 * Testers need to pass test discovery result. Runners need to pass module/class/method selection.
		 */
		final URI userSelection = config.getUserSelection();
		if (userSelection != null && (userSelection.toString().endsWith("." + N4JS_FILE_EXTENSION)
				|| userSelection.toString().endsWith("." + JS_FILE_EXTENSION))) {
			final String userSelection_targetFileName = compilerUtils.getTargetFileName(userSelection, null);
			config.setExecutionData(RunConfiguration.EXEC_DATA_KEY__USER_SELECTION, userSelection_targetFileName);
		} else {
			// this can happen if the RunConfiguration 'config' is actually a TestConfiguration, because then the user
			// selection is allowed to point to a project or folder (and method CompilerUtils#getModuleName() above
			// would throw an exception)
			config.setExecutionData(RunConfiguration.EXEC_DATA_KEY__USER_SELECTION, null);
		}
		config.setExecutionData(RunConfiguration.EXEC_DATA_KEY__INIT_MODULES, config.getInitModules());
		config.setExecutionData(RunConfiguration.EXEC_DATA_KEY__PROJECT_NAME_MAPPING,
				config.getApiImplProjectMapping());

		// 7) delegate further computation to the specific runner implementation
		IRunner runner = runnerRegistry.getRunner(config);
		runner.prepareConfiguration(config);
	}

	/**
	 * Collect init modules from given runtime environment (and environments it extends).
	 */
	private List<String> getInitModulesPathsFrom(RuntimeEnvironment runtimeEnvironment) {
		Optional<IN4JSProject> findRuntimeEnvironmentProject = hRuntimeEnvironments
				.findRuntimeEnvironmentProject(runtimeEnvironment);
		if (findRuntimeEnvironmentProject.isPresent()) {
			Set<IN4JSProject> environemntWithAncestors = hRuntimeEnvironments
					.getEnvironemntWithAncestors(findRuntimeEnvironmentProject.get());
			return runnerHelper.getInitModulePaths(new ArrayList<>(environemntWithAncestors));
		}
		return new ArrayList<>();
	}

	/**
	 * Find exec module from given runtime environment.
	 */
	private Optional<String> getExecModulePathFrom(RuntimeEnvironment runtimeEnvironment) {
		Optional<IN4JSProject> findRuntimeEnvironmentProject = hRuntimeEnvironments
				.findRuntimeEnvironmentProject(runtimeEnvironment);
		if (findRuntimeEnvironmentProject.isPresent()) {
			List<IN4JSProject> env = new ArrayList<>();
			env.add(findRuntimeEnvironmentProject.get());
			return runnerHelper.getExecModuleURI(env);
		}
		return Optional.absent();
	}

	/**
	 * Convenience method. Creates a run configuration with {@link #createConfiguration(String, String, URI)} and
	 * immediately passes it to {@link #run(RunConfiguration)} in order to launch the moduleToRun.
	 */
	public Process run(String runnerId, String implementationId, URI moduleToRun) {
		return run(createConfiguration(runnerId, implementationId, moduleToRun));
	}

	/**
	 * Convenience method. Creates a run configuration with {@link #createConfiguration(String, String, String, URI)}
	 * and immediately passes it to {@link #run(RunConfiguration)} in order to launch the moduleToRun.
	 */
	public Process run(String runnerId, String implementationId, String systemLoader, URI moduleToRun) {
		return run(createConfiguration(runnerId, implementationId, systemLoader, moduleToRun));
	}

	/**
	 * Convenience method. Same as {@link #run(RunConfiguration, IExecutor)}, but uses the default executor returned my
	 * method {@link #createDefaultExecutor()} that will delegate to {@link Runtime#exec(String[], String[], File)}.
	 */
	public Process run(RunConfiguration config) {
		return run(config, createDefaultExecutor());
	}

	/**
	 * Launches the given run configuration. The specific runner to use is defined by the runnerId in the given run
	 * configuration and the given executor will be used for launching any external tools.
	 */
	public Process run(RunConfiguration config, IExecutor executor) {
		return runnerRegistry.getRunner(config).run(config, executor);
	}

	/**
	 * Returns a new default executor that will delegate execution to {@link Runtime#exec(String[], String[], File)}.
	 * This executor is mainly intended for headless case, but may be useful in the UI case as well. However, the main
	 * executor to be used in the UI case is returned by method <code>RunnerFrontEndUI#createUIExecutor()</code>.
	 */
	public IExecutor createDefaultExecutor() {
		return new IExecutor() {
			@Override
			public Process exec(String[] cmdLine, File workingDirectory, Map<String, String> envp)
					throws ExecutionException {

				ProcessBuilder pb = new ProcessBuilder(cmdLine);
				pb.environment().putAll(envp);
				pb.directory(workingDirectory);
				pb.inheritIO();

				// Special case for running from Java-code & capturing output of run-result:
				String errFile = System.getProperty("eu.numberfour.n4js.runner.RunnerFrontEnd.ERRORFILE", "");
				String outFile = System.getProperty("eu.numberfour.n4js.runner.RunnerFrontEnd.OUTPUTFILE", "");
				if (errFile != null && errFile.length() > 0) {
					pb.redirectError(Redirect.to(new File(errFile)));
				}
				if (outFile != null && outFile.length() > 0) {
					pb.redirectOutput(Redirect.to(new File(outFile)));
				}

				try {
					return pb.start();
				} catch (IOException e) {
					throw new ExecutionException(e);
				}
			}
		};
	}
}
