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
package eu.numberfour.n4js.tests.typesbuilder

import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.tests.parser.AbstractParserTest
import eu.numberfour.n4js.ts.types.TMethod
import org.junit.Test

class ClassWithConstructorTypesBuilderTest extends AbstractParserTest {

	@Test
	def void testConstructorSimple() {
		val script = '''
			public class C {
				public constructor() {}
				public m() {}
			}
		'''.parse

		assertTrue(script.eResource.errors.toString, script.eResource.errors.empty)

		val cdecl = script.scriptElements.get(0) as N4ClassDeclaration
		assertEquals("C", cdecl.name)

		val ctorDecl = cdecl.getOwnedMembers.get(0) as N4MethodDeclaration
		val ctor = ctorDecl.definedType as TMethod
		assertEquals("constructor", ctorDecl.name);
		assertTrue(ctorDecl.constructor)
		assertTrue(ctor.constructor)

		val mDecl = cdecl.getOwnedMembers.get(1) as N4MethodDeclaration
		val m = mDecl.definedType as TMethod
		assertEquals("m", mDecl.name);
		assertEquals("m", m.name);
		assertFalse(mDecl.constructor)
		assertFalse(m.constructor)
	}
}
