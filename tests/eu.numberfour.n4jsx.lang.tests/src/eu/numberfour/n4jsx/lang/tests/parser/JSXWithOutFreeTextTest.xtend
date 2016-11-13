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

import eu.numberfour.n4js.n4JS.ExpressionStatement
import eu.numberfour.n4js.n4JS.FunctionDeclaration
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4jsx.n4JSX.JSXElement
import eu.numberfour.n4jsx.tests.helper.parser.AbstractN4JSXParserTest
import org.junit.Test

class JSXWithOutFreeTextTest extends AbstractN4JSXParserTest {

	@Test
	def void testSelfClosingTag() {
		val script = '''
			class Foo{}
			<div />
			function bar() {}
		'''.parseSuccessfully
		assertEquals(3, script.scriptElements.size)
		assertType(N4ClassDeclaration, script.scriptElements.get(0))
		assertType(FunctionDeclaration, script.scriptElements.get(2))
		
		val JSXElement jsxElement = (script.scriptElements.get(1) as ExpressionStatement).expression as JSXElement;
		assertTagName("div", jsxElement);
	}

	@Test
	def void testOpenCloseTag() {
		val script = '''
			class Foo{}
			<div></div>
			function bar() {}
		'''.parseSuccessfully
		assertEquals(3, script.scriptElements.size)
		assertType(N4ClassDeclaration, script.scriptElements.get(0))
		assertType(FunctionDeclaration, script.scriptElements.get(2))
		
		val JSXElement jsxElement = (script.scriptElements.get(1) as ExpressionStatement).expression as JSXElement;
		assertTagName("div", jsxElement);
	}
	
	@Test
	def void testOpenCloseTagWithNested() {
		val script = '''
			class Foo{}
			<div><Foo></Foo></div>
			function bar() {}
		'''.parseSuccessfully
		assertEquals(3, script.scriptElements.size)
		assertType(N4ClassDeclaration, script.scriptElements.get(0))
		assertType(FunctionDeclaration, script.scriptElements.get(2))
		
		val JSXElement jsxElement = (script.scriptElements.get(1) as ExpressionStatement).expression as JSXElement;
		assertTagName("div", jsxElement);
		assertTagName("Foo", jsxElement.jsxChildren.get(0));
		
	}
	
	
}
