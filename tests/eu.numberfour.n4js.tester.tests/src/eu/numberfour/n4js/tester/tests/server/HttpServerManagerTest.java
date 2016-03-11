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

import static eu.numberfour.n4js.tester.tests.TesterConstants.PORT;
import static eu.numberfour.n4js.tester.tests.TesterConstants.VALID_CONFIG;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

import eu.numberfour.n4js.tester.TesterModule;
import eu.numberfour.n4js.tester.server.HttpServerManager;
import eu.numberfour.n4js.tester.tests.InjectedModules;
import eu.numberfour.n4js.tester.tests.JUnitGuiceClassRunner;
import eu.numberfour.n4js.tester.tests.MockTesterModule;

/**
 * Test for checking the runtime behavior of the {@link HttpServerManager HTTP server manager}.
 */
@RunWith(JUnitGuiceClassRunner.class)
@InjectedModules(baseModules = { TesterModule.class }, overrides = { MockTesterModule.class })
public class HttpServerManagerTest {

	@Inject
	private HttpServerManager manager;

	/***/
	@Test
	public void testStartServer() {
		assertFalse(isRunning(PORT));
		manager.startServer(VALID_CONFIG);
		assertTrue(isRunning(PORT));
		manager.stopServer(PORT);
		assertFalse(isRunning(PORT));
	}

	private boolean isRunning(final int port) {
		if (0 > port || 65535 < port) {
			throw new IllegalArgumentException("Invalid port number: " + port);
		}
		try (final ServerSocket ss = new ServerSocket(port); final DatagramSocket ds = new DatagramSocket(port)) {
			ss.setReuseAddress(true);
			ds.setReuseAddress(true);
			return false;
		} catch (final IOException e) {
			return true;
		}
	}

}
