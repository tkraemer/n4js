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
package eu.numberfour.n4js.tester.internal;

import static eu.numberfour.n4js.tester.TesterModuleDefaults.HTTP_SERVER_PORT_KEY;
import static eu.numberfour.n4js.tester.server.HttpServerManager.HTTP_PORT;
import static java.util.Collections.singletonMap;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import eu.numberfour.n4js.tester.TesterFacade;
import eu.numberfour.n4js.tester.domain.TestTree;
import eu.numberfour.n4js.tester.fsm.TestFsmRegistry;
import eu.numberfour.n4js.tester.server.HttpServerManager;

/**
 * Facade implementation for setting up the infrastructure for a test session.
 */
@Singleton
public class TesterFacadeImpl implements TesterFacade {

	private final TestFsmRegistry fsmRegistry;
	private final HttpServerManager serverManager;
	private final InternalTestTreeRegistry treeRegistry;
	private final int port;

	@Inject
	TesterFacadeImpl(final TestFsmRegistry fsmRegistry, final HttpServerManager serverManager,
			final InternalTestTreeRegistry treeRegistry, @Named(HTTP_SERVER_PORT_KEY) final int port) {

		this.fsmRegistry = fsmRegistry;
		this.serverManager = serverManager;
		this.treeRegistry = treeRegistry;
		this.port = port;
	}

	@Override
	public void prepareTestSession(final TestTree tree) {
		if (!serverManager.isRunning(port)) {
			serverManager.startServer(singletonMap(HTTP_PORT, port));
		}

		final String sessionId = tree.getSessionId().getValue();
		fsmRegistry.registerFsm(sessionId);
		treeRegistry.registerTestTree(tree);
	}

	@Override
	public void shutdownFramework() {
		serverManager.stopServer(port);
	}
}
