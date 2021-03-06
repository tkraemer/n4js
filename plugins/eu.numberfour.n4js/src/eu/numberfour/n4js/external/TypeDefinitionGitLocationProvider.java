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
package eu.numberfour.n4js.external;

import static com.google.common.base.Preconditions.checkNotNull;

import org.apache.log4j.Logger;

import com.google.inject.ImplementedBy;

import eu.numberfour.n4js.external.TypeDefinitionGitLocationProvider.TypeDefinitionGitLocationProviderImpl;

/**
 * Representation of a Git repository location provider.
 */
@ImplementedBy(TypeDefinitionGitLocationProviderImpl.class)
public interface TypeDefinitionGitLocationProvider {

	/**
	 * Returns with the Git location for the current provider.
	 *
	 * @return the Git location for the type definitions files.
	 */
	TypeDefinitionGitLocation getGitLocation();

	/**
	 * TypeDefinitionGitLocationProviderImpl implementation that points to the public Git repository with the type definitions. This repository is
	 * accessible via HTTPS and requires no SSH session factory creation.
	 */
	public static final class TypeDefinitionGitLocationProviderImpl implements TypeDefinitionGitLocationProvider {

		private static final Logger LOGGER = Logger.getLogger(TypeDefinitionGitLocationProviderImpl.class);

		private TypeDefinitionGitLocation gitLocation = TypeDefinitionGitLocation.PUBLIC_DEFINITION_LOCATION;

		@Override
		public TypeDefinitionGitLocation getGitLocation() {
			return gitLocation;
		}

		/**
		 * (non-API)
		 *
		 * Sets the Git location for the type definition files.
		 *
		 * <p>
		 * <b>NOTE:&nbsp;</b>this method is declared as public only for testing purposes. This must not be used from
		 * production code.
		 *
		 * @param gitLocation
		 *            the new value for the Git location.
		 */
		public void setGitLocation(final TypeDefinitionGitLocation gitLocation) {
			this.gitLocation = checkNotNull(gitLocation, "gitLocation");
			LOGGER.info("Git location for type definitions was set to '" + this.gitLocation + "'");
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((gitLocation == null) ? 0 : gitLocation.hashCode());
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
			if (!(obj instanceof TypeDefinitionGitLocationProviderImpl)) {
				return false;
			}
			final TypeDefinitionGitLocationProviderImpl other = (TypeDefinitionGitLocationProviderImpl) obj;
			if (gitLocation != other.gitLocation) {
				return false;
			}
			return true;
		}

	}

	/**
	 * Enumeration of Git repository location for type definition files.
	 */
	public static enum TypeDefinitionGitLocation {

		/**
		 * The default location for the type definition file. Used in the production code.
		 */
		PUBLIC_DEFINITION_LOCATION("n4jsd", "https://github.com/NumberFour/n4jsd.git"),

		/**
		 * Type definition location for testing purposes.
		 */
		TEST_DEFINITION_LOCATION("n4jsd-sandbox", "git@github.com:NumberFour/n4jsd-sandbox.git");

		private final String repositoryName;
		private final String remoteUrl;

		private TypeDefinitionGitLocation(final String repositoryName, final String remoteUrl) {
			this.repositoryName = repositoryName;
			this.remoteUrl = remoteUrl;
		}

		/**
		 * Returns with the unique name for the Git repository.
		 *
		 * @return the name of the repository.
		 */
		public String getRepositoryName() {
			return repositoryName;
		}

		/**
		 * Returns with the remote URL of the Git repository.
		 *
		 * @return the remote URL of the repository.
		 */
		public String getRepositoryRemoteURL() {
			return remoteUrl;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder();
			sb.append(repositoryName);
			sb.append(" URL: ");
			sb.append(remoteUrl);
			return sb.toString();
		}

	}

}
