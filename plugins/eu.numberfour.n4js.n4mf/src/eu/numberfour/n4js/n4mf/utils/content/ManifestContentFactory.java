/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.n4mf.utils.content;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.eclipse.xtext.xbase.lib.IterableExtensions.isNullOrEmpty;

import java.util.Arrays;
import java.util.stream.Stream;

import eu.numberfour.n4js.n4mf.ProjectType;

/**
 * Factory for creating manifest contents based on provided data.
 */
public class ManifestContentFactory {

	/**
	 * Simple manifest content, no consistency checks, no default values.
	 */
	public static String simpleContent(ManifestDataSetter... dataSetters) {
		final ManifestContentData mf = new ManifestContentData();
		Stream.of(dataSetters).forEach(setter -> setter.accept(mf));
		return getContent(mf);
	}

	/**
	 * Performs basic consistency check of the provided data, e.g. provided project id cannot be null or empty.
	 */
	public static String checkedContent(ManifestDataSetter... dataSetters) {
		final ManifestContentData mf = new ManifestContentData();
		Stream.of(dataSetters).forEach(setter -> setter.accept(mf));

		// simple checks
		if (isNullOrEmpty(mf.projectId))
			throw new RuntimeException("Manifest project id cannot be null nor empty.");

		if (isNullOrEmpty(mf.projectType))
			throw new RuntimeException("Manifest project type cannot be null nor empty.");

		if (isNullOrEmpty(mf.projectVersion))
			throw new RuntimeException("Manifest project version cannot be null nor empty.");

		if (isNullOrEmpty(mf.projectVendorId))
			throw new RuntimeException("Manifest project vendor id cannot be null nor empty.");

		if (isNullOrEmpty(mf.projectVendorName))
			throw new RuntimeException("Manifest project vendor name cannot be null nor empty.");

		if (isNullOrEmpty(mf.output))
			throw new RuntimeException("Manifest output cannot be null nor empty.");

		if (isNullOrEmpty(mf.sourcesSource) && isNullOrEmpty(mf.sourcesExternal) && isNullOrEmpty(mf.sourcesTest))
			throw new RuntimeException("Manifests sources cannot be null nor empty.");

		return getContent(mf);
	}

	/**
	 * Uses N4 defaults for some basic data if it is not provided by the caller.
	 */
	public static String n4Content(ManifestDataSetter... dataSetters) {
		final ManifestContentData mf = new ManifestContentData();
		Stream.of(dataSetters).forEach(setter -> setter.accept(mf));

		// simple checks
		if (isNullOrEmpty(mf.projectId))
			mf.projectId = "StubProject_" + System.currentTimeMillis();

		if (isNullOrEmpty(mf.projectType))
			mf.projectType = ProjectType.LIBRARY.getLiteral().toLowerCase();

		if (isNullOrEmpty(mf.projectVersion))
			mf.projectVersion = "0.0.1-SNAPSHOT";

		if (isNullOrEmpty(mf.projectVendorId))
			mf.projectVendorId = "eu.numberfour";

		if (isNullOrEmpty(mf.projectVendorName))
			mf.projectVendorName = "'NumberFour AG'";

		if (isNullOrEmpty(mf.output))
			mf.output = "'src-gen'";

		if (isNullOrEmpty(mf.sourcesSource))
			mf.sourcesSource = Arrays.asList("'src'");

		return getContent(mf);
	}

	/**
	 * Delegates to the template class to create actual contents representation.
	 *
	 * @return manifest content created from the provided data.
	 */
	private static String getContent(ManifestContentData data) {
		return ManifestContentTemplate.fromData(
				data.projectId,
				data.projectType,
				data.projectVersion,
				data.projectVendorId,
				data.projectVendorName,
				data.extendedRuntimeEnvironment,
				data.projectDependencies,
				data.providedRL,
				data.requiredRL,
				data.implementationId,
				data.implementedProjects,
				data.output,
				data.sourcesSource,
				data.sourcesExternal,
				data.sourcesTest);
	}
}