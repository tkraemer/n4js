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

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Sets.newHashSet;

import java.io.File;
import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IBuildConfiguration;

import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.utils.resources.ExternalProject;

/**
 * Representation of an external N4JS project.
 */
/* default */class N4JSExternalProject extends ExternalProject {

	/* default */static final String NATURE_ID = "org.eclipse.xtext.ui.shared.xtextNature";
	/* default */static final String BUILDER_ID = "org.eclipse.xtext.ui.shared.xtextBuilder";

	private final IN4JSProject externalPackage;
	private final Collection<IBuildConfiguration> referencedBuildConfigs;

	/* default */ N4JSExternalProject(final File file, final IN4JSProject externalPackage) {
		super(file, NATURE_ID, BUILDER_ID);
		this.externalPackage = externalPackage;
		referencedBuildConfigs = newHashSet();
	}

	@Override
	public IBuildConfiguration[] internalGetReferencedBuildConfigs(final String configName,
			final boolean includeMissing) {

		final IBuildConfiguration[] filteredConfigs = from(referencedBuildConfigs)
				.filter(config -> !includeMissing ? config.getProject().exists() : true)
				.toArray(IBuildConfiguration.class);
		return filteredConfigs;
	}

	/**
	 * Attach the argument as a referenced build configuration to the project.
	 *
	 * @param config
	 *            the configuration to attach.
	 * @return {@link Set#add(Object)}.
	 */
	/* default */ boolean add(final IBuildConfiguration config) {
		return referencedBuildConfigs.add(config);
	}

	/**
	 * Returns with all direct dependency artifact IDs of the project extracted from the wrapped {@link IN4JSProject
	 * external package}.
	 *
	 * @return an iterable of direct dependency artifact IDs.
	 */
	/* default */ Iterable<String> getAllDirectDependencyIds() {
		return from(externalPackage.getAllDirectDependencies()).transform(dep -> dep.getArtifactId()).toSet();
	}

}
