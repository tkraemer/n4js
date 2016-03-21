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
package eu.numberfour.n4js.tests.parser

import org.junit.Test

/**
 */
class SpreadOperatorParserTest extends AbstractParserTest {

	@Test
	def void testIDEBUG_719() {
		'''
			function myFunction(x, y, z) { }
			var args = [0, 1, 2];
			myFunction(...args);
		'''.parseSuccessfully;
	}

}