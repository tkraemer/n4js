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
import eu.numberfour.n4js.n4JS.FunctionDeclaration
import eu.numberfour.n4js.n4JS.ExpressionStatement
import eu.numberfour.n4js.n4JS.ParenExpression
import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.n4JS.Block

class ES_13_FunctionDefinitionTest extends AbstractParserTest {

	/**
	 * This tests the absence of a predicate in the grammar that'd yield
	 * a FunctionExpression rather than a FunctionDeclaration
	 */
	@Test
	def void testFunctionDefinitionVar3() {
		val script = 'function testcase() {
		        "use strict";

		        try {
		            eval("var yield = 1;")
		            return false;
		        } catch (e) {
		            return e instanceof SyntaxError;
		        }
		}'.parseSuccessfully
		assertTrue(script.scriptElements.head instanceof FunctionDeclaration)
	}

	@Test
	def void testFunctionDeclarationInToplevelBlock() {
		val script = '{ function testcase() {} }'.parseSuccessfully
		assertTrue(script.eResource.errors.join('\n'), script.eResource.errors.isEmpty)
		assertTrue(script.scriptElements.head instanceof Block)
	}

	@Test
	def void testFunctionDeclarationInToplevelBlockN4JS() {
		val script = '{ function testcase() {} }'.parseN4js
		assertEquals(script.eResource.errors.join('\n'), 1, script.eResource.errors.size)
		assertTrue(script.scriptElements.head instanceof Block)
	}

	@Test
	def void testAnnotatedFunctionDeclaration() {
		val script = '@Annotation function testcase() {}'.parseSuccessfully
		assertTrue(script.scriptElements.head instanceof FunctionDeclaration)
	}

	@Test
	def void testFunctionExpression() {
		val script = '(function testcase() {})'.parseSuccessfully
		val expression = (script.scriptElements.head as ExpressionStatement).expression as ParenExpression
		assertTrue(expression.expression instanceof FunctionExpression)
	}

	@Test
	def void testAnnotatedFunctionExpression() {
		val script = '(@Dummy function testcase() {})'.parseSuccessfully
		val expression = (script.scriptElements.head as ExpressionStatement).expression as ParenExpression
		assertTrue(expression.expression instanceof FunctionExpression)
	}

}
