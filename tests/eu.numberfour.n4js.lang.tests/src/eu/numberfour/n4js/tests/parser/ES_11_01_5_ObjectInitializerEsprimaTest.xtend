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

import eu.numberfour.n4js.n4JS.AssignmentExpression
import eu.numberfour.n4js.n4JS.ExpressionStatement
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.IntLiteral
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.PropertyGetterDeclaration
import eu.numberfour.n4js.n4JS.PropertyNameKind
import eu.numberfour.n4js.n4JS.PropertyNameValuePair
import eu.numberfour.n4js.n4JS.PropertySetterDeclaration
import eu.numberfour.n4js.n4JS.ReturnStatement
import org.junit.Test

class ES_11_01_5_ObjectInitializerEsprimaTest extends AbstractParserTest {

	@Test
	def void testEmpty_01() {
		val script = 'x = {}'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		assertTrue(objectLiteral.propertyAssignments.empty)
	}

	@Test
	def void testEmpty_02() {
		val script = 'x = { }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		assertTrue(objectLiteral.propertyAssignments.empty)
	}

	@Test
	def void testSimplePropertyNameValuePair_01() {
		val script = 'x = { answer: 42 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val answer = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals("answer", answer.name)
		assertEquals(PropertyNameKind.IDENTIFIER, answer.kind)
		assertEquals(42, (answer.expression as IntLiteral).toInt)
	}

	@Test
	def void testSimplePropertyNameValuePair_01_TrailingComma() {
		val script = 'x = { answer: 42, }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val answer = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals("answer", answer.name)
		assertEquals(42, (answer.expression as IntLiteral).toInt)
	}

	@Test
	def void testSimplePropertyNameValuePair_01_TooManyTrailingCommas() {
		'x = { answer: 42,, }'.parseWithError
	}

	@Test
	def void testSimplePropertyNameValuePair_02() {
		val script = 'x = { get: 42 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val get = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals("get", get.name)
		assertEquals(42, (get.expression as IntLiteral).toInt)
	}

	@Test
	def void testSimplePropertyNameValuePair_03() {
		val script = 'x = { set: 43 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val set = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals(PropertyNameKind.IDENTIFIER, set.kind)
		assertEquals("set", set.name)
		assertEquals(43, (set.expression as IntLiteral).toInt)
	}

	@Test
	def void testSimplePropertyNameValuePair_04() {
		val script = 'x = { __proto__: 2 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val proto = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals("__proto__", proto.name)
		assertEquals(2, (proto.expression as IntLiteral).toInt)
	}

	@Test
	def void testNameIsKeyword_01() {
		val script = 'x = { if: 42 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val answer = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals(PropertyNameKind.IDENTIFIER, answer.kind)
		assertEquals("if", answer.name)
		assertEquals(42, (answer.expression as IntLiteral).toInt)
	}

	@Test
	def void testNameIsKeyword_02() {
		val script = 'x = { true: 42 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val answer = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals(PropertyNameKind.IDENTIFIER, answer.kind)
		assertEquals("true", answer.name)
		assertEquals(42, (answer.expression as IntLiteral).toInt)
	}

	@Test
	def void testNameIsKeyword_03() {
		val script = 'x = { false: 42 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val answer = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals(PropertyNameKind.IDENTIFIER, answer.kind)
		assertEquals("false", answer.name)
		assertEquals(42, (answer.expression as IntLiteral).toInt)
	}

	@Test
	def void testNameIsKeyword_04() {
		val script = 'x = { null: 42 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val answer = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals(PropertyNameKind.IDENTIFIER, answer.kind)
		assertEquals("null", answer.name)
		assertEquals(42, (answer.expression as IntLiteral).toInt)
	}

	@Test
	def void testNameIsString_01() {
		val script = 'x = { "answer": 42 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val answer = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals(PropertyNameKind.STRING, answer.kind)
		assertEquals("answer", answer.name)
		assertEquals(42, (answer.expression as IntLiteral).toInt)
	}

	@Test
	def void testNameIsString_02() {
		val script = 'x = { "__proto__": 2 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val proto = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals(PropertyNameKind.STRING, proto.kind)
		assertEquals("__proto__", proto.name)
		assertEquals(2, (proto.expression as IntLiteral).toInt)
	}

	@Test
	def void testTwoProperties() {
		val script = 'x = { x: 1, x: 2 }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val first = objectLiteral.propertyAssignments.head as PropertyNameValuePair
		assertEquals("x", first.name)
		assertEquals(1, (first.expression as IntLiteral).toInt)
		val last = objectLiteral.propertyAssignments.last as PropertyNameValuePair
		assertEquals("x", last.name)
		assertEquals(2, (last.expression as IntLiteral).toInt)
	}

	@Test
	def void testGetMethod_01() {
		val script = 'x = { get width() { return m_width } }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val getter = objectLiteral.propertyAssignments.head as PropertyGetterDeclaration
		assertEquals("width", getter.name)
		val body = getter.body
		val returnStatement = body.statements.head as ReturnStatement
		val returnValue = returnStatement.expression as IdentifierRef
		assertEquals("m_width", returnValue.text)
	}

	@Test
	def void testGetMethod_02() {
		val script = 'x = { get undef() {} }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val getter = objectLiteral.propertyAssignments.head as PropertyGetterDeclaration
		assertEquals("undef", getter.name)
		val body = getter.body
		assertTrue(body.statements.empty)
	}

	@Test
	def void testGetMethodNameIsKeyword_01() {
		val script = 'x = { get if() {} }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val getter = objectLiteral.propertyAssignments.head as PropertyGetterDeclaration
		assertEquals("if", getter.name)
		val body = getter.body
		assertEquals(true, body.statements.empty)
	}

	@Test
	def void testGetMethodNameIsKeyword_02() {
		val script = 'x = { get true() {} }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val getter = objectLiteral.propertyAssignments.head as PropertyGetterDeclaration
		assertEquals("true", getter.name)
		val body = getter.body
		assertEquals(true, body.statements.empty)
	}

	@Test
	def void testGetMethodNameIsKeyword_03() {
		val script = 'x = { get false() {} }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val getter = objectLiteral.propertyAssignments.head as PropertyGetterDeclaration
		assertEquals("false", getter.name)
		val body = getter.body
		assertEquals(true, body.statements.empty)
	}

	@Test
	def void testGetMethodNameIsKeyword_04() {
		val script = 'x = { get null() {} }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val getter = objectLiteral.propertyAssignments.head as PropertyGetterDeclaration
		assertEquals("null", getter.name)
		val body = getter.body
		assertEquals(true, body.statements.empty)
	}

	@Test
	def void testGetMethodNameIsString_01() {
		val script = 'x = { get "undef"() {} }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val getter = objectLiteral.propertyAssignments.head as PropertyGetterDeclaration
		assertEquals("undef", getter.name)
		val body = getter.body
		assertTrue(body.statements.empty)
	}

	@Test
	def void testGetMethodNameIsIntLiteral_01() {
		val script = 'x = { get 10() {} }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val getter = objectLiteral.propertyAssignments.head as PropertyGetterDeclaration
		assertEquals(PropertyNameKind.NUMBER, getter.kind)
		assertEquals("10", getter.name)
		val body = getter.body
		assertTrue(body.statements.empty)
	}

	@Test
	def void testSetMethod_01() {
		val script = 'x = { set width(w) { m_width = w } }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val setter = objectLiteral.propertyAssignments.head as PropertySetterDeclaration
		assertEquals("width", setter.name)
		assertEquals("w", setter.fpar.name)
		val body = setter.body
		val onlyStatement = body.statements.head as ExpressionStatement
		val innerAssignment = onlyStatement.expression as AssignmentExpression
		assertEquals("m_width", (innerAssignment.lhs as IdentifierRef).text)
		assertEquals("w", (innerAssignment.rhs as IdentifierRef).text)
	}

	@Test
	def void testSetMethodNameIsKeyword_01() {
		val script = 'x = { set if(w) { m_if = w } }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val setter = objectLiteral.propertyAssignments.head as PropertySetterDeclaration
		assertEquals("if", setter.name)
		assertEquals("w", setter.fpar.name)
		val body = setter.body
		val onlyStatement = body.statements.head as ExpressionStatement
		val innerAssignment = onlyStatement.expression as AssignmentExpression
		assertEquals("m_if", (innerAssignment.lhs as IdentifierRef).text)
		assertEquals("w", (innerAssignment.rhs as IdentifierRef).text)
	}

	@Test
	def void testSetMethodNameIsKeyword_02() {
		val script = 'x = { set true(w) { m_true = w } }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val setter = objectLiteral.propertyAssignments.head as PropertySetterDeclaration
		assertEquals("true", setter.name)
		assertEquals("w", setter.fpar.name)
		val body = setter.body
		val onlyStatement = body.statements.head as ExpressionStatement
		val innerAssignment = onlyStatement.expression as AssignmentExpression
		assertEquals("m_true", (innerAssignment.lhs as IdentifierRef).text)
		assertEquals("w", (innerAssignment.rhs as IdentifierRef).text)
	}

	@Test
	def void testSetMethodNameIsKeyword_03() {
		val script = 'x = { set false(w) { m_false = w } }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val setter = objectLiteral.propertyAssignments.head as PropertySetterDeclaration
		assertEquals("false", setter.name)
		assertEquals("w", setter.fpar.name)
		val body = setter.body
		val onlyStatement = body.statements.head as ExpressionStatement
		val innerAssignment = onlyStatement.expression as AssignmentExpression
		assertEquals("m_false", (innerAssignment.lhs as IdentifierRef).text)
		assertEquals("w", (innerAssignment.rhs as IdentifierRef).text)
	}

	@Test
	def void testSetMethodNameIsKeyword_04() {
		val script = 'x = { set null(w) { m_null = w } }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val setter = objectLiteral.propertyAssignments.head as PropertySetterDeclaration
		assertEquals("null", setter.name)
		assertEquals("w", setter.fpar.name)
		val body = setter.body
		val onlyStatement = body.statements.head as ExpressionStatement
		val innerAssignment = onlyStatement.expression as AssignmentExpression
		assertEquals("m_null", (innerAssignment.lhs as IdentifierRef).text)
		assertEquals("w", (innerAssignment.rhs as IdentifierRef).text)
	}

	@Test
	def void testSetMethodNameIsString() {
		val script = 'x = { set "null"(w) { m_null = w } }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val setter = objectLiteral.propertyAssignments.head as PropertySetterDeclaration
		assertEquals("null", setter.name)
		assertEquals("w", setter.fpar.name)
		val body = setter.body
		val onlyStatement = body.statements.head as ExpressionStatement
		val innerAssignment = onlyStatement.expression as AssignmentExpression
		assertEquals("m_null", (innerAssignment.lhs as IdentifierRef).text)
		assertEquals("w", (innerAssignment.rhs as IdentifierRef).text)
	}

	@Test
	def void testSetMethodNameIsIntValue() {
		val script = 'x = { set 10(w) { m_null = w } }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		val setter = objectLiteral.propertyAssignments.head as PropertySetterDeclaration
		assertEquals(PropertyNameKind.NUMBER, setter.kind)
		assertEquals("10", setter.name)
		assertEquals("w", setter.fpar.name)
		val body = setter.body
		val onlyStatement = body.statements.head as ExpressionStatement
		val innerAssignment = onlyStatement.expression as AssignmentExpression
		assertEquals("m_null", (innerAssignment.lhs as IdentifierRef).text)
		assertEquals("w", (innerAssignment.rhs as IdentifierRef).text)
	}

	@Test
	def void testGetAndSetMethodSameName() {
		val script = 'x = { get width() { return m_width }, set width(width) { m_width = width; } }'.parseSuccessfully
		val statement = script.scriptElements.head as ExpressionStatement
		val assignment = statement.expression as AssignmentExpression
		val identifier = assignment.lhs as IdentifierRef
		assertEquals('x', identifier.text)
		val objectLiteral = assignment.rhs as ObjectLiteral
		assertEquals(2, objectLiteral.propertyAssignments.size)
		val getter = objectLiteral.propertyAssignments.head as PropertyGetterDeclaration
		assertEquals("width", getter.name)
		val setter = objectLiteral.propertyAssignments.last as PropertySetterDeclaration
		assertEquals("width", setter.name)
		assertEquals("width", setter.fpar.name)
	}
}
