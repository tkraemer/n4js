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

/**
 * Helper object for capturing the data used added to the created manifest content.
 */
public class ManifestContentData {
	/** Holds value for {@code 'ProjectId'} n4mf content. */
	public String projectId;
	/** Holds value for {@code 'ProjectType'} n4mf content. */
	public String projectType;
	/** Holds value for {@code 'ProjectVersion'} n4mf content. */
	public String projectVersion;
	/** Holds value for {@code 'VendorId'} n4mf content. */
	public String projectVendorId;
	/** Holds value for {@code 'VendorName'} n4mf content. */
	public String projectVendorName;
	/** Holds value for {@code 'ExtendedRuntimeEnvironment'} n4mf content. */
	public String extendedRuntimeEnvironment;
	/** Holds values for {@code 'ProjectDependencies'} n4mf content. */
	public Iterable<String> projectDependencies;
	/** Holds values for {@code 'ProvidedRuntimeLibraries'} n4mf content. */
	public Iterable<String> providedRL;
	/** Holds values for {@code 'RequiredRuntimeLibraries'} n4mf content. */
	public Iterable<String> requiredRL;
	/** Holds value for {@code 'ImplementationId'} n4mf content. */
	public String implementationId;
	/** Holds values for {@code 'ImplementedProjects'} n4mf content. */
	public Iterable<String> implementedProjects;
	/** Holds value for {@code 'Output'} n4mf content. */
	public String output;
	/** Holds values for {@code 'source'} n4mf content. */
	public Iterable<String> sourcesSource;
	/** Holds values for {@code 'external'} n4mf content. */
	public Iterable<String> sourcesExternal;
	/** Holds values for {@code 'test'} n4mf content. */
	public Iterable<String> sourcesTest;

}
