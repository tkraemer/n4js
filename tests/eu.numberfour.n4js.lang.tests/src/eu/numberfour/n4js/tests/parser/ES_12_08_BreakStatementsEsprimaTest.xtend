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

import eu.numberfour.n4js.n4JS.Block
import eu.numberfour.n4js.n4JS.BooleanLiteral
import eu.numberfour.n4js.n4JS.BreakStatement
import eu.numberfour.n4js.n4JS.LabelledStatement
import eu.numberfour.n4js.n4JS.WhileStatement
import org.junit.Test

class ES_12_08_BreakStatementsEsprimaTest extends AbstractParserTest {

	@Test
	def void testBreak_Simple() {
		val script = 'while (true) { break }'.parseSuccessfully
		val statement = script.scriptElements.head as WhileStatement

		val bool = statement.expression as BooleanLiteral
		assertEquals(true, bool.^true)
		val block = statement.statement as Block
		assertEquals(1, block.statements.size)
		val breakStmt = block.statements.get(0) as BreakStatement
		assertNull(breakStmt.label)
	}

	@Test
	def void testBreak_SimpleASI() {
		val script = 'while (true) { break }'.parseSuccessfully
		val statement = script.scriptElements.head as WhileStatement

		val bool = statement.expression as BooleanLiteral
		assertEquals(true, bool.^true)
		val block = statement.statement as Block
		assertEquals(1, block.statements.size)
		val breakStmt = block.statements.get(0) as BreakStatement
		assertNull(breakStmt.label)
	}

	@Test
	def void testBreak_LabelASI() {
		val script = 'done: while (true) { break done }'.parseSuccessfully
		val labeledStatement = script.scriptElements.head as LabelledStatement
		val statement = labeledStatement.statement as WhileStatement

		val bool = statement.expression as BooleanLiteral
		assertEquals(true, bool.^true)
		val block = statement.statement as Block
		assertEquals(1, block.statements.size)
		val breakStmt = block.statements.get(0) as BreakStatement
		assertEquals("done", breakStmt.label.name)
	}

	@Test
	def void testBreak_Label() {
		val script = 'done: while (true) { break done; }'.parseSuccessfully
		val labeledStatement = script.scriptElements.head as LabelledStatement
		val statement = labeledStatement.statement as WhileStatement

		val bool = statement.expression as BooleanLiteral
		assertEquals(true, bool.^true)
		val block = statement.statement as Block
		assertEquals(1, block.statements.size)
		val breakStmt = block.statements.get(0) as BreakStatement
		assertEquals("done", breakStmt.label.name)
	}

	@Test
	def void testBreak_LabelProto() {
		val script = '__proto__: while (true) { break __proto__; }'.parseSuccessfully
		val labeledStatement = script.scriptElements.head as LabelledStatement
		val statement = labeledStatement.statement as WhileStatement

		val bool = statement.expression as BooleanLiteral
		assertEquals(true, bool.^true)
		val block = statement.statement as Block
		assertEquals(1, block.statements.size)
		val breakStmt = block.statements.get(0) as BreakStatement
		assertEquals("__proto__", breakStmt.label.name)
	}

}
