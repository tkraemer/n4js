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

import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.MultiplicativeExpression
import eu.numberfour.n4js.n4JS.MultiplicativeOperator
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.n4JS.ThrowStatement
import org.junit.Test
import eu.numberfour.n4js.n4JS.PropertyNameValuePair

class ES_12_13_ThrowStatementsEsprimaTest extends AbstractParserTest {

	@Test
	def void testThrow_Simple() {
		val script = 'throw x;'.parseSuccessfully
		val throwStmt = script.scriptElements.head as ThrowStatement
		val identifier = throwStmt.expression as IdentifierRef
		assertEquals("x", identifier.text)
	}

	@Test
	def void testThrow_Mult() {
		val script = 'throw x * y'.parseSuccessfully
		val throwStmt = script.scriptElements.head as ThrowStatement
		val expression = throwStmt.expression as MultiplicativeExpression
		val op = expression.op
		assertEquals(MultiplicativeOperator.TIMES, op)

		val left = expression.lhs as IdentifierRef
		assertEquals('x', left.text)
		val right = expression.rhs as IdentifierRef
		assertEquals('y', right.text)
	}


	@Test
	def void testThrow_ObjLit() {
		val script = 'throw { message: "Error" }'.parseSuccessfully
		val throwStmt = script.scriptElements.head as ThrowStatement
		val ol = throwStmt.expression as ObjectLiteral
		val prop = ol.propertyAssignments.get(0) as PropertyNameValuePair
		assertEquals("message", prop.name)
		assertEquals('"Error"', (prop.expression as StringLiteral).text)
	}


}
