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
package eu.numberfour.n4js.typesbuilder

import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.utils.TypeUtils

package class N4JSTypeVariableTypesBuilder {

	def package createTypeVariable(TypeVariable typeVariableFromN4, boolean preLinkingPhase) {
		if (typeVariableFromN4.name === null)
			return null
		TypeUtils.copyWithProxies(typeVariableFromN4)
	}
}
