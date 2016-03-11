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
package eu.numberfour.n4js.organize.imports

import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import org.eclipse.xtext.nodemodel.util.NodeModelUtils

/**
 * Utility to find actual name that was used for given reference.
 */
class RefNameUtil {
	/**
	 * Finds name that is used as identifier.
	 */
	def public static String findIdentifierName(IdentifierRef ref) {
		NodeModelUtils.findActualNodeFor(ref).leafNodes.filter[!hidden].map[text].join
	}

	/**
	 * Finds the name in the ParameterizedTypeRef.
	 * @return null if no connection to AST
	 */
	def public static String findTypeName(ParameterizedTypeRef ref) {
		val astNode = NodeModelUtils.findActualNodeFor(ref)
		if (astNode !== null) {
			astNode.leafNodes.filter[!hidden].map[text].join
		} else {
			null
		}
	}
}
