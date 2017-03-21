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

import static java.util.Collections.emptyList;

import java.io.File;
import java.net.URI;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.binaries.BinariesPreferenceStore;
import eu.numberfour.n4js.binaries.BinariesValidator;
import eu.numberfour.n4js.binaries.Binary;
import eu.numberfour.n4js.utils.Version;

/**
 * Representation of a {@code npmrc}. While not being binary itself, it exploits current design to allow user to add
 * extra configuration to the other binaries, in particular it reconfigures calls to the {@code npm}.
 *
 * Note, that {@code npm} is not binary itself, but an executable (script) file added by the {@code npm} library.
 */
public class NpmrcBinary implements Binary {

	private static final String NPM_CONFIG_USERCONFIG = "NPM_CONFIG_userconfig";

	/** don't access directly, use {@link #getDefaultNpmrcPath()} */
	private String memoizedCalculatedNpmrcPath = null;

	@Inject
	private BinariesValidator validator;

	@Inject
	private Provider<NpmBinary> npmBinaryProvider;

	@Inject
	private BinariesPreferenceStore preferenceStore;

	@Override
	public String getId() {
		return NpmrcBinary.class.getName();
	}

	@Override
	public String getLabel() {
		return NodeBinariesConstants.NPMRC_LABEL;
	}

	@Override
	public Version getMinimumVersion() {
		return Version.MISSING;
	}

	@Override
	public String getBinaryAbsolutePath() {
		return getUserNodePathOrDefault() + File.separator + NodeBinariesConstants.NPMRC_BINARY_NAME;
	}

	@Override
	public String getVersionArgument() {
		return NodeBinariesConstants.VERSION_ARGUMENT;
	}

	@Override
	public Binary getParent() {
		return npmBinaryProvider.get();
	}

	@Override
	public Iterable<Binary> getChildren() {
		return emptyList();
	}

	@Override
	public Map<String, String> updateEnvironment(final Map<String, String> environment) {
		final String additionalNodePath = getUserNodePathOrDefault() + File.separator
				+ NodeBinariesConstants.NPMRC_BINARY_NAME;

		// overwrite
		environment.put(NPM_CONFIG_USERCONFIG, additionalNodePath);

		return environment;
	}

	@Override
	public URI getUserConfiguredLocation() {
		return preferenceStore.getPath(this);
	}

	@Override
	public IStatus validate() {
		final Binary parent = getParent();
		if (null != parent) {
			final IStatus parentStatus = parent.validate();
			if (!parentStatus.isOK()) {
				return parentStatus;
			}
		}
		return validator.validateBinaryFile(this);
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
		if (!(obj instanceof NpmrcBinary)) {
			return false;
		}
		final NpmrcBinary other = (NpmrcBinary) obj;
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
		return null == userConfiguredLocation ? getDefaultNpmrcPath()
				: new File(userConfiguredLocation).getAbsolutePath();
	}

	private String getDefaultNpmrcPath() {
		if (memoizedCalculatedNpmrcPath == null) {
			memoizedCalculatedNpmrcPath = System.getProperty("user.home");
		}
		return memoizedCalculatedNpmrcPath;
	}
}
