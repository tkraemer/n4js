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
package eu.numberfour.n4js.n4mf.ui.wizard

import java.util.List

/**
 * Basic Xtend templates for new project wizard.
 */
class NewN4JSProjectFileTemplates {

	static def getSourceFileWithGreeterClass(String projectName, String safeProjectName) '''
		export class GreeterFor_«safeProjectName» {
		    public greet() {
		        console.log("Hello World from '«projectName»'!");
		    }
		}
		var greeter = new GreeterFor_«safeProjectName»();
		greeter.greet();
		//right click this module and select "Run As -> Launch in Node.js" to see
		//"Hello World from '«projectName»'!"

	'''

	// TODO introduce ParameterObject
	static def getManifestContents(String projectName, String projectTypeForManifest, List<String> sources,
		List<String> externals, List<String> tests, String out, String implID, List<String> apis) '''
		ArtifactId: «projectName»
		VendorId: eu.numberfour
		ProjectName: "«projectName»"
		VendorName: "NumberFour AG"
		ProjectType: «projectTypeForManifest»
		ProjectVersion: 0.0.1-SNAPSHOT
		//output folder (e.g. compiled files, etc.)
		Output: "«out»"
		//define project source folders
		Sources {
		    «IF !sources.empty»
		    //those are your project production sources (usually N4JS code)
		    source {
		        «FOR String fSRC : sources SEPARATOR ","»"«fSRC»"«ENDFOR»
		    }«ENDIF»
		    «IF !externals.empty»
		    //those are your project external sources (usually JS or N4JSD)
		    external {
		        «FOR String fEXT : externals SEPARATOR ","»"«fEXT»"«ENDFOR»
		    }«ENDIF»
		    «IF !tests.empty»
		    //those are your project test sources (usually N4JS code)
		    test {
		        «FOR String fTST : tests SEPARATOR ","»"«fTST»"«ENDFOR»
		    }«ENDIF»
		}
		«IF implID !== null && implID.trim.length >0»«"\n"»ImplementationId: «implID»«ENDIF»
		«IF !apis.empty »ImplementedProjects {«
			FOR String api : apis SEPARATOR ","»«"\n"»    «api»«ENDFOR»
		}«ENDIF»
	'''
}
