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
package eu.numberfour.n4js.tests.typesbuilder.extensions

import eu.numberfour.n4js.resource.UserdataMapper
import eu.numberfour.n4js.tests.typesbuilder.utils.OrderedEmfFormatter
import org.eclipse.xtext.resource.IEObjectDescription

import static org.junit.Assert.*
import eu.numberfour.n4js.resource.N4JSResource
import org.eclipse.emf.ecore.EObject
import java.util.List

/**
 */
class UserdataAssertionsExtension {
	
	def assertSerializedUserdata(Iterable<IEObjectDescription> eoDescs, CharSequence expectedTypesSerialization, boolean enableUserDataCompare, N4JSResource resource) {
		val syntaxEoDesc = eoDescs.head;
		
		val List<EObject> fromUserData = UserdataMapper.getDeserializedModulesFromDescription(syntaxEoDesc,resource.URI)
		
		if(enableUserDataCompare) {
			compareUserData(syntaxEoDesc, fromUserData, expectedTypesSerialization.toString)
		}
	}

	def compareUserData(IEObjectDescription syntaxEoDesc, List<EObject> fromUserData, String expectedTypesSerialization) {
		assertEquals("Stored user data " + syntaxEoDesc, expectedTypesSerialization, OrderedEmfFormatter.objToStr(fromUserData.head))
	}
}
