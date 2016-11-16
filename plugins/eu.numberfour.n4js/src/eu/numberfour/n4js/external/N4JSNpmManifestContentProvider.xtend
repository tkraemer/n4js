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
package eu.numberfour.n4js.external

import com.google.common.base.Strings

/**
 * Stripped down copy of test utility for generating manifests.
 *
 * see eu.numberfour.n4js.runner.tests.ManifestContentProvider
 */
class N4JSNpmManifestContentProvider {

	/**
	 * Creates and returns with the N4 manifest content based on the given arguments.
	 * @param projectId the name of the project.
	 * @param outputFolder the name of the folder with js files
	 * @param externalFolder the name of the folder with n4js and n4jsd files
	 */
	def String getContent(String projectId, String outputFolder, String externalFolder, String main)
	'''
		ProjectId: «projectId»
		ProjectType: library
		ProjectVersion: 0.0.1-SNAPSHOT
		VendorId: npm
		VendorName: "npm"
		Output: "«outputFolder»"«
		IF Strings.isNullOrEmpty(main) === false»
		MainModule: "«main»"«
		ENDIF»
		ModuleLoader: commonjs
		Sources {
			external {
				"«externalFolder»"
			}
		}
	'''

}
