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
package eu.numberfour.n4js.typesystem

import eu.numberfour.n4js.N4JSInjectorProvider
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before

/*
 * Tests for {@link TypeSystemHelper#join(RuleEnvironment, TypeRef...)} method with union types.
 */
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProvider)
class JoinComputer_IntersectionTypesTest extends AbstractTypeSystemHelperTests {

	@Before
	def void prepareTypeDefs() {
		setDefaultTypeDefinitions()
	}

	/*
	 * Test some assumptions.
	 */
	@Test
	def void testJoinAssumptions() {
		assertJoin("G<? extends A>", "G<A>", "G<B>");
		assertJoin("A", "A", "B");
		assertJoin("N4Object", "A", "D");

		// G is instanceof of N4OBject ;-)
		assertJoin("N4Object", "G<A>", "A");
	}

	@Test
	def void testJoinIntersectionWithElementFromIntersection() {
		assertJoin("A", "A", "intersection{A,B}");
		assertJoin("B", "B", "intersection{A,B}");
		assertJoin("D", "D", "intersection{A,D}");
		assertJoin("N4Object", "D", "intersection{A,D}", "A");
		assertJoin("A", "B", "intersection{A,B}", "A");
	}

	@Test
	def void testJoinWithIntersections() {
		assertJoin("intersection{A,D}", "intersection{A,D}", "intersection{A,D}");
		assertJoin("intersection{A,D}", "intersection{A,D,E}", "intersection{A,D}");
		assertJoin("intersection{A,D}", "intersection{A,D}", "intersection{B,D}");
		assertJoin("B", "intersection{A,B}", "intersection{A,B}");

	}

	@Test
	def void testJoinWithIntersectionAndGenerics() {
		assertJoin("G<A>", "G<A>", "intersection{G<A>,B}");
		assertJoin("intersection{A,G<A>}", "intersection{G<A>,A}", "intersection{G<A>,B}");
		assertJoin("G<A>", "intersection{G<A>,D}", "intersection{G<A>,B}");

	}

}
