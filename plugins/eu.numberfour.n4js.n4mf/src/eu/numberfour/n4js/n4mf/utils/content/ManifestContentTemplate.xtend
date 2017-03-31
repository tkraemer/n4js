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
package eu.numberfour.n4js.n4mf.utils.content

/**
 * Template for creating formatted content for N4MF files.
 */
class ManifestContentTemplate {

	//TODO add missing manifest fields
	// - MainModule
	// - InitModules
	// - ExecModule
	// - Libraries
	// - Resources
	// - ModuleFilters
	// - ModuleLoader
	// - TestedProjects
	/**
	 * Creates and returns with the N4 manifest content based on the given arguments.
	 * @param projectId the projectId of the project.
	 * @param type the type of the N4 project.
	 * @param extendedRE the optional extended runtime environment.
	 * @param projectDependencies an iterable of direct project dependnencies for the N4 project.
	 * @param providedRL an iterable of provided runtime libraries.
	 * @param requiredRL an iterable of required runtime libraries.
	 * @return the N4 manifest content as a string.
	 */
	static def String fromData(
		String projectId,
		String projectType,
		String projectVersion,
		String projectVendorId,
		String projectVendorName,
		String extendedRuntimeEnvironment,
		Iterable<String> projectDependencies,
		Iterable<String> providedRL,
		Iterable<String> requiredRL,
		String implementationId,
		Iterable<String> implementedProjects,
		String output,
		Iterable<String> sourcesSource,
		Iterable<String> sourcesExternal,
		Iterable<String> sourcesTest
	) '''
		«'''ProjectId'''.fromValue(projectId)»
		«'''ProjectType'''.fromValue(projectType)»
		«'''ProjectVersion'''.fromValue(projectVersion)»
		«'''VendorId'''.fromValue(projectVendorId)»
		«'''VendorName'''.fromValue(projectVendorName)»
		«'''ProvidedRuntimeLibraries'''.fromValues(providedRL)»
		«'''ProjectDependencies'''.fromValues(projectDependencies)»
		«'''RequiredRuntimeLibraries'''.fromValues(requiredRL)»
		«'''ExtendedRuntimeEnvironment'''.fromValue(extendedRuntimeEnvironment)»
		«'''ImplementationId: '''.fromValue(implementationId)»
		«'''ImplementedProjects'''.fromValues(implementedProjects)»
		«'''Output'''.fromValue(output)»
		«'''Sources'''.fromNestedValues(#[
			'''source'''.fromValues(sourcesSource),
			'''external'''.fromValues(sourcesExternal),
			'''test'''.fromValues(sourcesTest)])»
	'''

	private static def fromValue(CharSequence name, String it)
		'''«IF !isNullOrEmpty»«name»: «it»«ENDIF»'''

	private static def fromNestedValues(CharSequence name, Iterable<? extends CharSequence> values)
		'''«name.fromValues(values.map[toString].filter[!isNullOrEmpty])»'''

	private static def fromValues(CharSequence name, Iterable<? extends CharSequence> it)
		'''«IF !isNullOrEmpty»
		«name» {
			«FOR i : it SEPARATOR ","»
				«i»
			«ENDFOR»
		}«ENDIF»'''

}
