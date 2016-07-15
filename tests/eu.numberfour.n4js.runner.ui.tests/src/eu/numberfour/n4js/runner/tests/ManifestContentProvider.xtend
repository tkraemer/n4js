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
package eu.numberfour.n4js.runner.tests

import com.google.common.base.Optional
import eu.numberfour.n4js.n4mf.ProjectType
import eu.numberfour.n4js.runner.^extension.RuntimeEnvironment

import static com.google.common.base.CaseFormat.*
import static java.lang.String.valueOf
import static eu.numberfour.n4js.n4mf.ProjectType.API

/**
 * Class for providing content for various type of N4 manifests.
 */
class ManifestContentProvider {

	/**
	 * Creates and returns with the N4 manifest content based on the given arguments.
	 * @param artifactId the artifactId of the project.
	 * @param type the type of the N4 project.
	 * @param extendedRE the optional extended runtime environment.
	 * @param projectDependencies an iterable of direct project dependnencies for the N4 project.
	 * @param providedRL an iterable of provided runtime libraries.
	 * @param requiredRL an iterable of required runtime libraries.
	 * @return the N4 manifest content as a string.
	 */
	def String getContent(String artifactId, ProjectType type, Optional<RuntimeEnvironment> extendedRE,
		Iterable<String> projectDependencies, Iterable<String> providedRL, Iterable<String> requiredRL,
		Optional<String> implementationId, Iterable<String> implementedProjects
	) '''
		ArtifactId: «artifactId»
		VendorId: eu.numberfour
		ProjectName: "«artifactId»"
		VendorName: "NumberFour AG"
		ProjectType: «IF API == type»«API»«ELSE»«UPPER_UNDERSCORE.to(LOWER_CAMEL, valueOf(type))»«ENDIF»
		ProjectVersion: 0.0.1-SNAPSHOT
		«'''ProvidedRuntimeLibraries'''.getEnumeration(providedRL)»
		«'''ProjectDependencies'''.getEnumeration(projectDependencies)»
		«'''RequiredRuntimeLibraries'''.getEnumeration(requiredRL)»
		«IF extendedRE.present»ExtendedRuntimeEnvironment : «extendedRE.get.getArtifactId»«ENDIF»
		«IF implementationId.isPresent»ImplementationId: «implementationId.get»«ENDIF»
		«'''ImplementedProjects'''.getEnumeration(implementedProjects)»
		Output: "src-gen"
		Sources {
			source {
				"src"
			}
		}
	'''

	private def getEnumeration(CharSequence name, Iterable<String> it) '''
	«IF !empty»
	«name» {
		«FOR i : it SEPARATOR ","»
		«i»
		«ENDFOR»
	}
	«ENDIF»
	'''

}
