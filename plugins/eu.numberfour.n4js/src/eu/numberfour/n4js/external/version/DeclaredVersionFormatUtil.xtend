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
package eu.numberfour.n4js.external.version

import eu.numberfour.n4js.n4mf.DeclaredVersion

import static extension java.util.Objects.nonNull

/** 
 * Formats {@link DeclaredVersion} according to npm format.
 *
 * @see <a href="https://github.com/npm/node-semver#versions">node-semver#versions</a>
 */
class DeclaredVersionFormatUtil {
	static def npmFormat(DeclaredVersion it) 
		'''«IF nonNull»«simpleFormat»«ENDIF»'''

	private static def simpleFormat(DeclaredVersion it) 
		'''«major».«minor».«micro»«IF !qualifier.isNullOrEmpty»-«qualifier»«ENDIF»'''
}
