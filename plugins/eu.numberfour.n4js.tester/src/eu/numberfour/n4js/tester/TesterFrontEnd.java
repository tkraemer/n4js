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
package eu.numberfour.n4js.tester;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.emf.common.util.URI;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import eu.numberfour.n4js.runner.IExecutor;
import eu.numberfour.n4js.runner.RunConfiguration;
import eu.numberfour.n4js.runner.RunnerFrontEnd;
import eu.numberfour.n4js.tester.domain.TestTree;
import eu.numberfour.n4js.tester.extension.ITesterDescriptor;
import eu.numberfour.n4js.tester.extension.TesterRegistry;
import eu.numberfour.n4js.tester.internal.DefaultTestTreeTransformer;

/**
 */
@Singleton
public class TesterFrontEnd {

	@Inject
	private RunnerFrontEnd runnerFrontEnd;

	@Inject
	private TesterRegistry testerRegistry;

	@Inject
	private TestDiscoveryHelper testDiscoveryHelper;

	@Inject
	private DefaultTestTreeTransformer testTreeTransformer;

	@Inject
	private ObjectMapper objectMapper;

	@Inject
	private TesterFacade testerFacade;

	/**
	 * Similar to {@link RunnerFrontEnd#canRun(String, URI)}, but for testing.
	 */
	public boolean canTest(@SuppressWarnings("unused") final URI resourceToTest) {
		return true; // FIXME IDE-1393 consider re-using IsTestableLocationPropertyTester
	}

	/**
	 * Sugar for creating a new configuration from the existing configuration. All properties are copied expect the
	 * {@link TestTree#getSessionId() session ID} of the test tree.
	 *
	 * @param configuration
	 *            the configuration to replicate.
	 * @return a new test configuration with different test tree session ID.
	 */
	public TestConfiguration createConfiguration(final TestConfiguration configuration) {
		return createConfiguration(configuration.getTesterId(), configuration.getImplementationId(),
				configuration.getUserSelection());
	}

	/**
	 * Similar to {@link RunnerFrontEnd#createConfiguration(String, String, URI)}, but for testing.
	 *
	 * @param testerId
	 *            ID of the tester to use.
	 * @param implementationId
	 *            implementation ID to use or <code>null</code>. See {@link RunConfiguration#getImplementationId() here}
	 *            for details.
	 * @param moduleToTest
	 *            URI referencing a resource to test. Can be an N4JS project, or a folder or file within an N4JS project
	 *            for which {@link #canTest(URI)} returns true.
	 * @return the new run configuration.
	 */
	public TestConfiguration createConfiguration(String testerId, String implementationId, final URI moduleToTest) {
		final ITesterDescriptor testerDesc = testerRegistry.getDescriptor(testerId);
		final ITester tester = testerDesc.getTester();

		final TestConfiguration config = tester.createConfiguration();
		config.setName(computeConfigurationName(testerId, moduleToTest));
		config.setTesterId(testerId);
		config.setRunnerId(tester.getRunnerIdForTesting());
		config.setRuntimeEnvironment(testerDesc.getEnvironment());
		config.setImplementationId(implementationId);
		config.setUserSelection(moduleToTest);

		computeDerivedValues(config);

		return config;
	}

	/**
	 * Similar to {@link RunnerFrontEnd#createConfiguration(Map)}, but for testing.
	 */
	public TestConfiguration createConfiguration(Map<String, Object> values) {
		final String testerId = RunConfiguration.getString(values, TestConfiguration.TESTER_ID, false);
		final ITester tester = testerRegistry.getTester(testerId);

		final TestConfiguration config = tester.createConfiguration();
		config.writePersistentValues(values);

		computeDerivedValues(config);

		return config;
	}

	/**
	 * Similar to {@link RunnerFrontEnd#computeDerivedValues(RunConfiguration)}, but for testing.
	 */
	public void computeDerivedValues(TestConfiguration config) {
		// A) compute derived values for the run(!) configuration (will delegate to runner)
		runnerFrontEnd.computeDerivedValues(config);

		// B) compute derived values for the test configuration

		// B.1) compute the test tree (note: will create a new session ID every time this is called)
		final TestTree testTree = testDiscoveryHelper.collectTests(config.getUserSelection());
		config.setTestTree(testTree);

		// B.2) pass test tree as execution data
		try {
			final String testTreeAsJSON = objectMapper.writeValueAsString(testTreeTransformer.apply(testTree));
			config.setExecutionData(TestConfiguration.EXEC_DATA_KEY__TEST_TREE, testTreeAsJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// B.3) delegate further computation to the specific tester implementation
		ITester tester = testerRegistry.getTester(config);
		tester.prepareConfiguration(config);
	}

	/**
	 * Similar to {@link RunnerFrontEnd#run(String, String, URI)}, but for testing.
	 */
	public Process test(String testerId, String implementationId, URI resourceToTest) {
		return test(createConfiguration(testerId, implementationId, resourceToTest));
	}

	/**
	 * Similar to {@link RunnerFrontEnd#run(RunConfiguration)}, but for testing.
	 */
	public Process test(TestConfiguration config) {
		return test(config, runnerFrontEnd.createDefaultExecutor());
	}

	/**
	 * Similar to {@link RunnerFrontEnd#run(RunConfiguration, IExecutor)}, but for testing.
	 */
	public Process test(TestConfiguration config, IExecutor executor) {
		final TestTree testTree = config.getTestTree();

		// prepare HTTP server for receiving test results
		testerFacade.prepareTestSession(testTree);

		// actually launch the test
		ITester tester = testerRegistry.getTester(config);
		return tester.test(config, executor, runnerFrontEnd);
	}

	/**
	 * Create a default name for a new test configuration with the given runnerId and moduleToTest.
	 */
	private String computeConfigurationName(String testerId, URI moduleToTest) {
		String modulePath = moduleToTest.path();
		modulePath = stripStart(modulePath, "/", "resource/", "plugin/");
		final String moduleName = modulePath.replace('/', '-');
		final String runnerName = testerRegistry.getDescriptor(testerId).getName();
		return moduleName + " (" + runnerName + ")";
	}

	private static final String stripStart(String str, String... prefixesToStrip) {
		for (String currPrefix : prefixesToStrip) {
			if (str.startsWith(currPrefix)) {
				str = str.substring(currPrefix.length());
			}
		}
		return str;
	}

}
