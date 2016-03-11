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
import static java.io.File.separator;

import java.io.File;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Contains constants for {@code Node.js} binaries all over the system.
 */
/* default */ abstract class NodeBinariesConstants {

	private static final Logger LOGGER = Logger.getLogger(NodeBinariesConstants.class);

	private static final boolean LOG_SYSTEM_PROPERTIES = false;

	private static final boolean LOG_ENV_VARIABLES = false;

	private static final boolean LOG_TO_STD_OUT = false;

	/**
	 * VM argument for configuring the default {@code Node.js} path. That default {@code Node.js} binary path will be
	 * used when no user specified binary location is given. This VM argument could come handy when for instance running
	 * when there is no way to provide any user specific binary path settings and the default {@code Node.js} path is
	 * differs from the expected one.
	 *
	 * <p>
	 * When the using this VM argument make sure that absolute path is given and the path points to the folder which
	 * contains the {@code Node.js} binary.
	 *
	 * <p>
	 * On Windows systems the file separators (backslashes [&#x5c;]) have to escaped, hence double backslashes has to be
	 * used.
	 *
	 * <p>
	 * For instance on Unix system the usage should be something like below:
	 *
	 * <pre>
	 * -Deu.numberfour.n4js.defaultNodePath=/absolute/path/to/the/node/binary/folder
	 * </pre>
	 *
	 * and on Windows systems the following pattern should be followed:
	 *
	 * <pre>
	 * -Deu.numberfour.n4js.defaultNodePath=C:\\absolute\\path\\to\\the\\node\binary\\folder
	 * </pre>
	 */
	private static final String DEFAULT_NODE_PATH_VM_ARG = "eu.numberfour.n4js.defaultNodePath";

	/**
	 * Jenkins environment variable for the {@code Node.js} binary path. Points to the actual binary (with an absolute
	 * path) instead of pointing to the folder containing the binary.
	 *
	 * <p>
	 * Even if it is available the {@link #DEFAULT_NODE_PATH_VM_ARG <code>eu.numberfour.n4js.defaultNodePath</code>} VM
	 * argument might override this configuration.
	 */
	private static final String NODEJS_PATH_ENV = "NODEJS_PATH";

	/** The built-in default {@code Node.js} path if the above VM argument is not specified. */
	private static final String BUILT_IN_DEFAULT_NODE_PATH = isWindows()
			? new File("C:" + separator + "Program Files" + separator + "nodejs").getAbsolutePath()
			: new File(separator + "usr" + File.separator + "local" + separator + "bin").getAbsolutePath();

	/**
	 * Returns with the default path where all binaries will be looked up for {@code Node.js}. This default binary path
	 * value is used when no specific user configuration is given. It varies based on the operating system. This default
	 * path location can be configured via the {@link #DEFAULT_NODE_PATH_VM_ARG
	 * <code>eu.numberfour.n4js.defaultNodePath</code>} VM argument.
	 * <ul>
	 * <li>On Windows systems: {@code C:\Program Files\nodejs}</li>
	 * <li>On Unix systems: {@code /usr/local/bin}</li>
	 * </ul>
	 */
	/* default */ static final String DEFAULT_NODE_PATH;

	static {

		logSystemProperties();
		logEnvironmentVariables();

		String nodePathCandidate = System.getProperty(DEFAULT_NODE_PATH_VM_ARG);

		if (isNullOrEmptyOrNullString(nodePathCandidate)) {

			info("No user specified default Node.js path was configured via '" + DEFAULT_NODE_PATH_VM_ARG
					+ "' VM argument.");
			final String jenkinsNodeJsPath = tryGetNodeJsPathEnvVariable();

			if (!isNullOrEmptyOrNullString(jenkinsNodeJsPath)) {
				info("Node.js path has been configured via '" + NODEJS_PATH_ENV + "' environment variable.");
				info("Default Node.js path is: '" + jenkinsNodeJsPath + "'.");
				final File nodeBinary = new File(jenkinsNodeJsPath);
				if (!nodeBinary.isFile()) {
					warn("Cannot locate node binary at: '" + nodeBinary
							+ "'. Falling back to default. Node.js binary was: " + nodeBinary + ".");
				} else {
					final File nodeFolder = nodeBinary.getParentFile();
					if (null == nodeFolder || !nodeFolder.isDirectory()) {
						warn("Cannot locate parent folder for Node.js binary: '" + nodeBinary
								+ "'. Falling back to default.");
					} else {
						nodePathCandidate = nodeFolder.getAbsolutePath();
						info("User provided Node.js path will be used as the default one: " + nodePathCandidate
								+ " based on the '" + NODEJS_PATH_ENV + "' environment variable.");
					}
				}
			}

		} else {
			info("User specified default Node.js path will be used: '" + nodePathCandidate
					+ ".' based on the '" + DEFAULT_NODE_PATH_VM_ARG + "' VM argument.");
		}

		if (isNullOrEmptyOrNullString(nodePathCandidate)) {
			nodePathCandidate = BUILT_IN_DEFAULT_NODE_PATH;
			info("No user specified default Node.js path was configured. Falling back to default path: "
					+ nodePathCandidate);
		}

		DEFAULT_NODE_PATH = nodePathCandidate;
	}

	/**
	 * Tries to get the {@code NODEJS_PATH} variable from the available environment variables, if it does not exist,
	 * then falls back to system properties.
	 */
	private static String tryGetNodeJsPathEnvVariable() {
		final String nodeJsPath = System.getenv(NODEJS_PATH_ENV);
		if (isNullOrEmptyOrNullString(nodeJsPath)) {
			return System.getProperty(NODEJS_PATH_ENV);
		}
		return nodeJsPath;
	}

	/**
	 * Returns with {@code true} if one of the followings are {@code true}
	 * <ul>
	 * <li>Argument {@code s} is {@code null} or</li>
	 * <li>Argument {@code s} is an empty string or</li>
	 * <li>Argument {@code s} contains only whitespaces or</li>
	 * <li>Argument {@code s} equals with the <i>null</i> string.</li>
	 * </ul>
	 * Otherwise returns with {@code false}.
	 */
	private static boolean isNullOrEmptyOrNullString(final String s) {
		return null == s || 0 == s.trim().length() || "null".equals(s);
	}

	private static void logSystemProperties() {
		if (LOG_SYSTEM_PROPERTIES) {
			info("---------------------- System Properties ----------------------------------");
			final Properties p = System.getProperties();
			final Enumeration<Object> keys = p.keys();
			while (keys.hasMoreElements()) {
				final String key = (String) keys.nextElement();
				final String value = (String) p.get(key);
				info(key + ": " + value);
			}
			info("---------------------- End of System Properties ----------------------------------");
		}
	}

	private static void logEnvironmentVariables() {
		if (LOG_ENV_VARIABLES) {
			info("---------------------- Environment Variables ----------------------------------");
			for (final Entry<String, String> e : System.getenv().entrySet()) {
				info(e.getKey() + ": " + e.getValue());
			}
			info("---------------------- End of Environment Variables ----------------------------------");
		}
	}

	private static void info(final Object message) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.info(message);
		}
		if (LOG_TO_STD_OUT) {
			System.out.println(message);
		}
	}

	private static void warn(final Object message) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.warn(message);
		}
		if (LOG_TO_STD_OUT) {
			System.out.println(message);
		}
	}

	private NodeBinariesConstants() {
		// No initialization.
	}

}
