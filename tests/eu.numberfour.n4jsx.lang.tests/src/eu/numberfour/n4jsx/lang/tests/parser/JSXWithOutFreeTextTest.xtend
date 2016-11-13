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
package eu.numberfour.n4jsx.lang.tests.parser

import eu.numberfour.n4jsx.tests.helper.parser.AbstractN4JSXParserTest
import org.junit.Test

class JSXWithOutFreeTextTest extends AbstractN4JSXParserTest {

	@Test
	def void testEmpty_01() {
		'<div />'.parseSuccessfully
	}
	
}
