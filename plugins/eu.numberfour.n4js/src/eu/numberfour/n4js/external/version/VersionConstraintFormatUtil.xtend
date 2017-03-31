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

import eu.numberfour.n4js.n4mf.VersionConstraint

import static extension eu.numberfour.n4js.external.version.DeclaredVersionFormatUtil.npmFormat
import static extension java.util.Objects.nonNull

/**
 * Formats {@link VersionConstraint} according to npm install command format.
 * 
 * Creates string representation of the project dependency version that has to be provided with npm package name.
 * <ul>
 * examples:
 * <li>no version: <code>sax</code></li>
 * <li>simple version: <code>sax@0.1.1</code></li>
 * <li>constrained version: <code>sax@">=0.1.0 <0.2.0"</code></li>
 * </ul>
 * 
 * @see <a href="https://docs.npmjs.com/cli/install">npm install</a>
 */
class VersionConstraintFormatUtil {
	static def String npmFormat(VersionConstraint it) 
		'''«IF nonNull»«formatExisting»«ENDIF»'''

	private static def formatExisting(VersionConstraint it) 
		'''@«IF upperVersion.nonNull»«constrainedFormat»«ELSE»«simpleFormat»«ENDIF»'''

	private static def constrainedFormat(VersionConstraint it) 
		'''">«IF isExclLowerBound»=«ENDIF»«lowerVersion.npmFormat» <«IF isExclUpperBound»=«ENDIF»«upperVersion.npmFormat»"'''

	private static def simpleFormat(VersionConstraint it) 
		'''«lowerVersion.npmFormat»'''
}
