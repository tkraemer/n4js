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
 package eu.numberfour.n4js.xsemantics

import com.google.inject.Inject
import eu.numberfour.n4js.N4JSParseHelper
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.ScriptElement
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.n4JS.VariableStatement
import eu.numberfour.n4js.validation.JavaScriptVariant
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TypableElement
import eu.numberfour.n4js.ts.types.Type
import eu.numberfour.n4js.utils.Log
import it.xsemantics.runtime.Result
import it.xsemantics.runtime.RuleApplicationTrace
import it.xsemantics.runtime.RuleEnvironment
import it.xsemantics.runtime.TraceUtils
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.validation.Issue

import static eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import static org.junit.Assert.*

/*
 * Base class for type system tests (with xsemantics)
 */
@Log
abstract class AbstractTypesystemTest {

	@Inject extension TraceUtils;

	@Inject extension ValidationTestHelper

	@Inject protected N4JSTypeSystem ts;

	@Inject
	extension N4JSParseHelper

	def Script createScript(JavaScriptVariant variant, String src) {
		return src.parse(variant)
	}

	def assertTypeByName(String expectedTypeAsString, TypableElement expression) {
		val G = newRuleEnvironment(expression);
		val trace = new RuleApplicationTrace();
		var result = ts.type(G, trace, expression)
		if (result.ruleFailedException!==null) {
			fail(result.ruleFailedException.failureTraceAsString);
		}
		val typeExpr = result.value;
		assertEquals("Assert at " + expression + ", trace:\n" + trace.traceAsString, expectedTypeAsString, typeExpr.typeRefAsString);
	}


	/* Tests that the result's value is a ParameterizedTypeRef and the declared type must equal the given type. */
	def assertType(Result<TypeRef> result, Type expectedType) {
		result.assertNoFailure
		assertTrue(result.value instanceof ParameterizedTypeRef)
		assertEquals(expectedType, (result.value as ParameterizedTypeRef).declaredType)
	}

	def assertType(Result<TypeRef> result, Type expectedType, RuleApplicationTrace trace) {
		result.assertNoFailure(trace)
		assertTrue(result.value instanceof ParameterizedTypeRef)
		assertEquals(expectedType, (result.value as ParameterizedTypeRef).declaredType)
	}

	def TypeRef checkedType(RuleEnvironment G, TypableElement eobj) {
		val result = ts.type(G, eobj);
		if (result.ruleFailedException !== null) {
			fail(result.ruleFailedException.failureTraceAsString);
		}
		return result.value;
	}

	/*
	 * Tests that the inferred type of a given element equals the expected name,
	 */
	def void assertTypeName(String expectedTypeName, TypableElement elementToBeTypeInferred) {

		val G = newRuleEnvironment(elementToBeTypeInferred);
		var trace = new RuleApplicationTrace();
		val result = ts.type(G, trace, elementToBeTypeInferred);

		if (result.ruleFailedException !== null && ! trace.trace.empty) {
			logger.warn("Trace: " + trace.traceAsString)
		}
		result.assertNoFailure(trace)
		assertNotNull("No type inferred", result.value)
		assertEquals("Wrong type inferred", expectedTypeName, result.value.typeRefAsString)
	}

	/*
	 * Tests that the expected type of a given element, in the context of its container, equals the expected name,
	 */
	def void assertExpectedTypeName(String expectedTypeName, Expression element) {

		val G = newRuleEnvironment(element);
		var trace = new RuleApplicationTrace();
		val result = ts.expectedTypeIn(G,trace,element.eContainer, element);

		if (result.ruleFailedException!==null && ! trace.trace.empty) {
			logger.warn("Trace: " + trace.traceAsString)
		}
		result.assertNoFailure(trace)
		assertNotNull("No expected type inferred", result.value)
		assertEquals("Wrong expected type inferred", expectedTypeName, result.value.typeRefAsString)
	}

	/*
	 * Tests that inferring a type results in a failure; you can specify the expected
	 * error message of the whole type inference, and the expected main reason of the failure,
	 * i.e., the error message of the innermost failing rule
	 */
	def void assertTypeFailure(TypableElement elementToBeTypeInferred, String expectedFailure, String expectedMainReason) {

		val G = newRuleEnvironment(elementToBeTypeInferred);
		var trace = new RuleApplicationTrace();
		val result = ts.type(G,trace,elementToBeTypeInferred);

		result.assertFailure(trace, expectedFailure, expectedMainReason)
	}

	def void assertSubtype(RuleEnvironment G, TypeRef left, TypeRef right, boolean expectedResult) {
		assertNotNull("Left hand side must not be null", left)
		assertNotNull("Right hand side must not be null", right)

		var trace = new RuleApplicationTrace()
		var result = ts.subtype(G, trace, left, right)
		if (expectedResult) {
			result.assertNoFailure
			assertEquals(expectedResult, result.value)
		} else {
			result.assertFailure(trace)
		}
	}

	/* Tests that the result's value is a boolean and equals the expected result. */
	def void assertSubtype(Result<Boolean> result, boolean expectedResult) {
		if (expectedResult) {
			result.assertNoFailure
			assertEquals(expectedResult, result.value)
		} else {
			assertNotNull(result.ruleFailedException)
		}
	}

	def void assertNoFailure(Result<?> result) {
		result.assertNoFailure(null)
	}

	def void assertNoFailure(Result<?> result, RuleApplicationTrace trace) {
		if (result.ruleFailedException !== null) {
			System.err.println("failure trace:\n" + result.ruleFailedException.failureTraceAsString)
			assertNull(result.ruleFailedException.failureTraceAsString,
				result.ruleFailedException)
		}
		if (trace === null)
			assertNotNull(result.value)
		else {
			val traceAsString = trace.traceAsString
			if (result.value === null)
				System.err.println("Trace that lead to null result:\n" + traceAsString)
			assertNotNull("Result value null:\n" + traceAsString, result.value)
		}
	}

	def void assertFailure(Result<?> result, RuleApplicationTrace trace, String expectedFailureMessage) {
		result.assertFailure(trace, expectedFailureMessage, null)
	}

	def void assertFailure(Result<?> result, RuleApplicationTrace trace, String expectedFailureMessage, String mainReason) {
		result.assertFailure(trace)
		assertEquals(expectedFailureMessage, result.ruleFailedException.message)
		if (mainReason !== null)
			assertEquals(mainReason, result.ruleFailedException.failureTraceAsStrings.last)
	}

	def void assertFailure(Result<?> result, RuleApplicationTrace trace) {
		if (result.ruleFailedException === null) {
			System.err.println("unexpected success:\n" + trace.traceAsString)
			assertNotNull(trace.traceAsString, result.ruleFailedException)
		}
		assertNull(result.value)
	}

	def void assertIssueCount(int expectedNumber, List<Issue> issues) {
		if ((issues === null && expectedNumber == 0) || (issues !== null && issues.size() == expectedNumber)) {
			return
		}
		if (issues === null || issues.empty) {
			fail("Got no issues, expected " + expectedNumber)
		}
		val sb = new StringBuffer("Expected " + expectedNumber + " issues, got " + issues.size + ":");
		issues.forEach[sb.append("\n    " + it)]
		fail(sb.toString)
	}

	def void assertErrorMessages(List<Issue> issues, CharSequence expectedMessages) {
		assertEquals(expectedMessages.toString.trim, issues.map[message].join(System.getProperty("line.separator")))
	}

	def void assertNoValidationErrors(Script script) {
		val issues = script.validate();
		if (! issues.empty) {
			logger.info(issues.map[toString].join("\n\t"));
		}
		assertEquals(0, issues.filter[it.severity!=Severity.WARNING].size)
	}

	def variableStatementDeclaredType(ScriptElement e) {
		(e as VariableStatement).varDecl.head.declaredTypeRef
	}

	def variableDeclarations(Script script) {
		EcoreUtil2.getAllContentsOfType(script, VariableDeclaration)
	}

	def callExpressions(EObject e) {
		EcoreUtil2.getAllContentsOfType(e, ParameterizedCallExpression)
	}

	def classDeclarations(EObject e) {
		EcoreUtil2.getAllContentsOfType(e, N4ClassDeclaration)
	}
}
