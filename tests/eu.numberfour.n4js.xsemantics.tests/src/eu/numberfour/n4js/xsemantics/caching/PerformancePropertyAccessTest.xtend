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
package eu.numberfour.n4js.xsemantics.caching

import eu.numberfour.n4js.N4JSInjectorProvider
import eu.numberfour.n4js.xsemantics.caching.AbstractTypesystemForPerformanceTest
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test performance involving typing expressions
 */
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProvider)
class PerformancePropertyAccessTest extends AbstractTypesystemForPerformanceTest {

	@Test
	def void testPropAccess5() {
		5.propAccess
	}

	@Test
	def void testPropAccess10() {
		10.propAccess
	}

	@Test(timeout=3500)
	def void testPropAccess100() {
		// DON'T try to do that without caching :)
		100.propAccess
	}

	def protected propAccess(int n) {
		/*
		 * obj.p.p.p.p  ...  .p.p.p;
		 */
		'''
			var obj: any+;
			obj«FOR i : 1..n».p«ENDFOR»;
		'''.assertValidate(0)
	}

}
