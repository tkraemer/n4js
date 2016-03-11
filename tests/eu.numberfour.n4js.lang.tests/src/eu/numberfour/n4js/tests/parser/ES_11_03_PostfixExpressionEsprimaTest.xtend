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

import eu.numberfour.n4js.n4JS.ExpressionStatement
import org.junit.Test
import eu.numberfour.n4js.n4JS.PostfixExpression
import eu.numberfour.n4js.n4JS.PostfixOperator
import eu.numberfour.n4js.n4JS.IdentifierRef

class ES_11_03_PostfixExpressionEsprimaTest extends AbstractParserTest {

	@Test
	def void testInc_01() {
		val script = 'x++'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val postfix = statement.expression as PostfixExpression
		assertEquals(PostfixOperator.INC, postfix.op)
		val x = postfix.expression as IdentifierRef
		assertEquals('x', x.text)
	}

	@Test
	def void testInc_02() {
		val script = 'eval++'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val postfix = statement.expression as PostfixExpression
		assertEquals(PostfixOperator.INC, postfix.op)
		val eval = postfix.expression as IdentifierRef
		assertEquals('eval', eval.text)
	}

	@Test
	def void testInc_03() {
		val script = 'arguments++'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val postfix = statement.expression as PostfixExpression
		assertEquals(PostfixOperator.INC, postfix.op)
	}

	@Test
	def void testDec_01() {
		val script = 'x--'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val postfix = statement.expression as PostfixExpression
		assertEquals(PostfixOperator.DEC, postfix.op)
	}

	@Test
	def void testDec_02() {
		val script = 'eval--'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val postfix = statement.expression as PostfixExpression
		assertEquals(PostfixOperator.DEC, postfix.op)
	}

	@Test
	def void testDec_03() {
		val script = 'arguments--'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val postfix = statement.expression as PostfixExpression
		assertEquals(PostfixOperator.DEC, postfix.op)
	}

}
