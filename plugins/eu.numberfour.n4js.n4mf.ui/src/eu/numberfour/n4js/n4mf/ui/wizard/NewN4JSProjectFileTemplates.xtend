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
	
	static def getSourceFileWithTestGreeter(String projectName) '''
		import { Assert } from "n4/mangel/assert/Assert"
		
		export public class Test_«projectName» {
			@Test
			test(){
				Assert.isTrue(true, "Testing works!")
			}
			
		}
		//right click this module and select "Run As -> Test in Node.js" to see the test results
	'''

	private static def simpleManifestContents(String projectName, String projectTypeForManifest, List<String> sources,
		List<String> externals, List<String> tests, String outputFolder
	) {
		'''
		ArtifactId: «projectName»
		VendorId: eu.numberfour
		ProjectName: "«projectName»"
		VendorName: "NumberFour AG"
		ProjectType: «projectTypeForManifest»
		ProjectVersion: 0.0.1-SNAPSHOT
		//output folder (e.g. compiled files, etc.)
		Output: "«outputFolder»"
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
		'''
	}

	private static def libraryManifestFragment(String implementationId, List<String> implementedProjects) {
		'''
		«IF implementationId !== null && implementationId.trim.length >0»«"\n"»ImplementationId: «implementationId»«ENDIF»
		«IF !implementedProjects.empty »ImplementedProjects {«
			FOR String api : implementedProjects SEPARATOR ","»«"\n"»    «api»«ENDFOR»
		}«ENDIF»
		'''
	}
	
	private static def testManifestFragment(List<String> testedProjects) {
		'''
		«IF testedProjects !== null && !testedProjects.empty
		»TestedProjects {«
			FOR String project : testedProjects SEPARATOR ","»«"\n"»    «project»«ENDFOR»
		}«
		ENDIF»'''
	}
	private static def projectDependenciesManifestFragment(List<String> projectDependencies) {
		'''
		«IF projectDependencies !== null && !projectDependencies.empty
		»ProjectDependencies {«
			FOR String dependency : projectDependencies SEPARATOR ","»«"\n"»    «dependency»«ENDFOR»
		}«
		ENDIF»'''
	}

	/**
	 * Returns the manifest contents for the given project info 
	 */
	static def getManifestContents(N4MFProjectInfo projectInfo)
		'''
		«simpleManifestContents(projectInfo.projectName, projectInfo.projectTypeForManifest, projectInfo.sourceFolders,
								projectInfo.externalSourceFolders, projectInfo.testSourceFolders, projectInfo.outputFolder)»
		«libraryManifestFragment(projectInfo.implementationId, projectInfo.implementedProjects)»
		«testManifestFragment(projectInfo.getTestedProjects)»
		«projectDependenciesManifestFragment(projectInfo.projectDependencies)»
		'''
}
