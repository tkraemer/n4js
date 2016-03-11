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
package eu.numberfour.n4js.tester.tests.server;

import static com.google.common.collect.Lists.newArrayList;
import static eu.numberfour.n4js.tester.tests.TesterConstants.PORT;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import eu.numberfour.n4js.tester.TesterFacade;
import eu.numberfour.n4js.tester.TesterModule;
import eu.numberfour.n4js.tester.domain.ID;
import eu.numberfour.n4js.tester.domain.TestTree;
import eu.numberfour.n4js.tester.server.HttpServerManager;
import eu.numberfour.n4js.tester.tests.InjectedModules;
import eu.numberfour.n4js.tester.tests.JUnitGuiceClassRunner;
import eu.numberfour.n4js.tester.tests.MockTesterModule;

/**
 * Class for testing the {@link TesterFacade} runtime behavior.
 */
@RunWith(JUnitGuiceClassRunner.class)
@InjectedModules(baseModules = { TesterModule.class }, overrides = { MockTesterModule.class })
public class TesterFacadeTest {

	@Inject
	private HttpServerManager serverManager;

	@Inject
	private TesterFacade testerFacade;

	/***/
	@Before
	public void before() {
		serverManager.stopServer(PORT);
	}

	/***/
	@After
	public void after() {
		serverManager.stopServer(PORT);
	}

	/***/
	@Test
	public void testEnsureServerIsRunningPrepareSession() {
		testerFacade.prepareTestSession(new TestTree(new ID("sessionId"), newArrayList()));
	}

	/***/
	@Test
	public void testPrepareSessionWithoutRunningServer() {
		serverManager.stopServer(PORT);
		testerFacade.prepareTestSession(new TestTree(new ID("sessionId"), newArrayList()));
	}
}
