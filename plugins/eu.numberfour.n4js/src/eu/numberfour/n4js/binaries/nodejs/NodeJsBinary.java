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

import static com.google.common.base.Strings.isNullOrEmpty;
import static eu.numberfour.n4js.binaries.nodejs.NodeBinariesConstants.DEFAULT_NODE_PATH;
import static eu.numberfour.n4js.utils.OSInfo.isWindows;
import static java.util.Collections.singletonList;

import java.io.File;
import java.net.URI;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import eu.numberfour.n4js.binaries.BinariesPreferenceStore;
import eu.numberfour.n4js.binaries.BinariesValidator;
import eu.numberfour.n4js.binaries.Binary;
import eu.numberfour.n4js.utils.Version;

/**
 * Representation of a {@code Node.js} binary.
 */
@Singleton
public class NodeJsBinary implements Binary {

	/** The minimum {@code Node.js} version. */
	private static final Version MIN_VERSION = new Version(5, 0, 0);
	private static final String LABEL = "Node.js";
	private static final String BINARY_NAME = isWindows() ? "node.exe" : "node";
	private static final String VERSION_ARGUMENT = "-v";

	@Inject
	private BinariesValidator validator;

	@Inject
	private Provider<NpmBinary> npmBinaryProvider;

	@Inject
	private BinariesPreferenceStore preferenceStore;

	@Override
	public String getId() {
		return NodeJsBinary.class.getName();
	}

	@Override
	public String getLabel() {
		return LABEL;
	}

	@Override
	public String getDescription() {
		return "Node.js\u00AE configuration preference page. The root folder location of the Node.js\u00AE executable "
				+ "can be configured here. If not given, then the '" + NodeBinariesConstants.DEFAULT_NODE_PATH
				+ "' location will be used as the default location. The required minimum version for Node.js is '"
				+ getMinimumVersion() + "' and the required minimum version for npm is '"
				+ npmBinaryProvider.get().getMinimumVersion() + "'.";
	}

	@Override
	public Version getMinimumVersion() {
		return MIN_VERSION;
	}

	@Override
	public String getBinaryAbsolutePath() {
		return getUserNodePathOrDefault() + File.separator + BINARY_NAME;
	}

	@Override
	public String getVersionArgument() {
		return VERSION_ARGUMENT;
	}

	@Override
	public Binary getParent() {
		return null;
	}

	@Override
	public Iterable<Binary> getChildren() {
		return singletonList(npmBinaryProvider.get());
	}

	@Override
	public Map<String, String> updateEnvironment(final Map<String, String> environment) {
		final String additionalNodePath = getUserNodePathOrDefault();
		final String currentPathValue = environment.get(PATH);
		if (isNullOrEmpty(currentPathValue)) {
			environment.put(PATH, additionalNodePath);
		} else {
			environment.put(PATH, currentPathValue + File.pathSeparator + additionalNodePath);
		}
		return environment;
	}

	@Override
	public URI getUserConfiguredLocation() {
		return preferenceStore.getPath(this);
	}

	@Override
	public IStatus validate() {
		return validator.validate(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof NodeJsBinary)) {
			return false;
		}
		final NodeJsBinary other = (NodeJsBinary) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

	/**
	 * (non-API)
	 *
	 * Returns with a pair containing the user provided or the default location of the binary and a boolean value
	 * denoting whether the path is user provided
	 *
	 * @return the user configured absolute path to the binary or the default one.
	 */
	String getUserNodePathOrDefault() {
		final URI userConfiguredLocation = getUserConfiguredLocation();
		return null == userConfiguredLocation ? DEFAULT_NODE_PATH : new File(userConfiguredLocation).getAbsolutePath();
	}

}
