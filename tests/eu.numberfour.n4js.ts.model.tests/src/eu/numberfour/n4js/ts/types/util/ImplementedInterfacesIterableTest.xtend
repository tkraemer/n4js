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
package eu.numberfour.n4js.ts.types.util

import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*
import static extension eu.numberfour.n4js.ts.types.util.TypesTestUtils.*
import eu.numberfour.n4js.ts.types.util.SuperInterfacesIterable

/**
 */
@RunWith(XtextRunner)
class ImplementedInterfacesIterableTest {

	@Test
	def void testNoInterfacesOrRoles() {
		val A = clazz("A");
		val iter = new SuperInterfacesIterable(A);
		assertFalse(iter.iterator().hasNext())
	}

	@Test
	def void testDirectlyImplementedInterface() {
		val I = interf("I");
		val A = clazz("A").impl(I);
		val iter = new SuperInterfacesIterable(A);
		assertTrue(iter.iterator().hasNext())
		assertEquals(iter.size, 1)
		assertEquals(I, iter.iterator().next());
	}

	@Test
	def void testIndirectlyImplementedInterface() {
		val I = interf("I");
		val B = clazz("B").impl(I);
		val A = clazz("A").ext(B);
		val iter = new SuperInterfacesIterable(A);

		assertTrue(iter.iterator().hasNext())
		assertEquals(iter.size, 1)
		assertEquals(I, iter.iterator().next());
	}


	@Test
	def void recursiveInterfacesTest() {
		val I2 = interf("I2");
		val I1 = interf("I1").ext(I2)
		I2.ext(I1)

		val A = clazz("A").impl(I1)
		val iter = new SuperInterfacesIterable(A);

		assertTrue(iter.iterator().hasNext())
		assertEquals(iter.size, 2)

	}



}
