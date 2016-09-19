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
package eu.numberfour.n4js.tester.tests;

import eu.numberfour.n4js.tester.server.HttpServerManager;
import eu.numberfour.n4js.tester.server.resources.ResourceRouterServlet;

/**
 * Contains constants for the tests.
 */
public abstract class TesterConstants {

	/** The host for the server. */
	public static final String HOST = "localhost";
	/** The port for the server. */
	// public static final int PORT = 8080;
	/** The URL for the jetty instance. */
	// public static final String HOST_AND_PORT = "http://" + HOST + ":" + PORT;
	/** Context root for the RESTful web service. */
	public static final String CONTEXT_ROOT = HttpServerManager.CONTEXT_ROOT;
	/** Context path for the resource router servlet. */
	public static final String CONTEXT_PATH = ResourceRouterServlet.CONTEXT_PATH;
	/** URL for the resources. */
	// public static final String URL = HOST_AND_PORT + CONTEXT_ROOT + CONTEXT_PATH;

	/** A valid configuration for the server. */
	// public static final Map<String, Object> VALID_CONFIG = ImmutableMap.<String, Object> builder()
	// .put(HTTP_PORT, PORT)
	// .build();

	private TesterConstants() {
	}

}
