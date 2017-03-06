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
package eu.numberfour.n4js.utils.tests

import com.google.inject.Inject
import eu.numberfour.n4js.N4JSInjectorProviderWithIssueSuppression
import eu.numberfour.n4js.N4JSParseHelper
import eu.numberfour.n4js.n4JS.ExpressionStatement
import eu.numberfour.n4js.n4JS.ParenExpression
import eu.numberfour.n4js.postprocessing.CompileTimeExpressionProcessor
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.util.Variance
import eu.numberfour.n4js.utils.CompileTimeValue
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.runner.RunWith

import static org.junit.Assert.*

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 *
 */
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProviderWithIssueSuppression)
abstract class AbstractN4JSLanguageUtilsTest {

	@Inject private extension N4JSParseHelper parseHelper;
	@Inject private extension ValidationTestHelper;
	@Inject private CompileTimeExpressionProcessor compileTimeExpressionProcessor;


	def protected void assertVarianceOfPosition(CharSequence code, Variance expectedVariance) {
		assertVarianceOfPosition(code, expectedVariance, false);
	}
	def protected void assertVarianceOfPositionRelativeToItsRoot(CharSequence code, Variance expectedVariance) {
		assertVarianceOfPosition(code, expectedVariance, true);
	}
	def private void assertVarianceOfPosition(CharSequence code, Variance expectedVariance, boolean relativeToItsRoot) {
		val script = code.parse;
		script.assertNoParseErrors;
		val issues = script.validate;
		assertTrue(issues.toString, issues.empty);
		val ref2TypeVars = script.eAllContents.filter(ParameterizedTypeRef).filter[declaredType instanceof TypeVariable].toList;
		assertEquals("expected exactly one reference to a type variable, but found: " + ref2TypeVars, 1, ref2TypeVars.size);
		val ref2TypeVar = ref2TypeVars.head;
		val computedVariance = if(relativeToItsRoot) {
			N4JSLanguageUtils.getVarianceOfPositionRelativeToItsRoot(ref2TypeVar)
		} else {
			N4JSLanguageUtils.getVarianceOfPosition(ref2TypeVar)
		};
		assertEquals(expectedVariance, computedVariance);
	}


	def protected void assertValueOfCompileTimeExpr(CharSequence expression, Object expectedValue) {
		assertValueOfCompileTimeExpr("", expression, expectedValue);
	}
	def protected void assertValueOfCompileTimeExpr(CharSequence preamble, CharSequence expression, Object expectedValue) {
		val script = '''
			«preamble»;
			(«expression»);
		'''.parse;
		script.assertNoParseErrors;
		val issues = script.validate;
		assertTrue(issues.toString, issues.empty);
		val lastStatement = script.scriptElements.last as ExpressionStatement;
		val expressionInAST = (lastStatement.expression as ParenExpression).expression;
		assertNotNull(expressionInAST);
		val G = script.newRuleEnvironment;
		val computedValue = compileTimeExpressionProcessor.ONLY_FOR_TESTING__computeValueIfCompileTimeExpression(G, expressionInAST);
		assertNotNull(computedValue);
		if(expectedValue===null) {
			assertFalse("expected an invalid value but got a valid one", computedValue.valid);
		} else {
			assertTrue("expected a valid value but got an invalid one", computedValue.valid);
			val expectedCompileTimeValue = CompileTimeValue.of(expectedValue);
			assertEquals(expectedCompileTimeValue, computedValue);
		}
	}
}
