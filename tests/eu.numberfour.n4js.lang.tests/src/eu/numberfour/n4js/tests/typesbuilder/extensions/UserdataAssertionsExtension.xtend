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
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.util.StringInputStream

import static org.junit.Assert.*

/**
 */
class UserdataAssertionsExtension {

	def assertSerializedUserdata(Iterable<IEObjectDescription> eoDescs, CharSequence expectedTypesSerialization, boolean enableUserDataCompare) {
		val syntaxEoDesc = eoDescs.head
		val fromUserData = new XMIResourceImpl()
		fromUserData.load(new StringInputStream(syntaxEoDesc.getUserData(UserdataMapper::USERDATA_KEY_SERIALIZED_SCRIPT), "UTF-8"), null)
		if(enableUserDataCompare) {
			compareUserData(syntaxEoDesc, fromUserData, expectedTypesSerialization.toString)
		}
	}

	def compareUserData(IEObjectDescription syntaxEoDesc, XMIResourceImpl fromUserData, String expectedTypesSerialization) {
		assertEquals("Stored user data " + syntaxEoDesc, expectedTypesSerialization, OrderedEmfFormatter.objToStr(fromUserData.contents.head))
	}
}
