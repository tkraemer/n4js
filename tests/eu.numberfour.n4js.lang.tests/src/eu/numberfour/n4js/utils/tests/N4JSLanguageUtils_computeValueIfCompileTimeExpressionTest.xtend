/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.utils.tests

import eu.numberfour.n4js.utils.N4JSLanguageUtils
import java.math.BigDecimal
import org.junit.Test
import eu.numberfour.n4js.utils.CompileTimeValue

/**
 * Test for method {@link N4JSLanguageUtils#computeValueIfCompileTimeExpressionTest(Expression)}.
 */
class N4JSLanguageUtils_computeValueIfCompileTimeExpressionTest extends AbstractN4JSLanguageUtilsTest {

	@Test
	def public void testSimple() {
		assertValueOfCompileTimeExpr("null", CompileTimeValue.NULL);
		assertValueOfCompileTimeExpr("undefined", CompileTimeValue.UNDEFINED);
		assertValueOfCompileTimeExpr("true", CompileTimeValue.TRUE);
		assertValueOfCompileTimeExpr("false", CompileTimeValue.FALSE);
		assertValueOfCompileTimeExpr("true", true);
		assertValueOfCompileTimeExpr("false", false);
		assertValueOfCompileTimeExpr("42", 42);
		assertValueOfCompileTimeExpr("42", 42.0);
		assertValueOfCompileTimeExpr("42", BigDecimal.valueOf(42));
		assertValueOfCompileTimeExpr("42", BigDecimal.valueOf(42.0));
		assertValueOfCompileTimeExpr("42.1", 42.1);
		assertValueOfCompileTimeExpr("42.1", BigDecimal.valueOf(42.1));
		assertValueOfCompileTimeExpr("'hello'", "hello");
	}

	@Test
	def public void testTemplateStrings() {
		assertValueOfCompileTimeExpr("`hello`", "hello");
		assertValueOfCompileTimeExpr("`hel${  }lo`", "hello");
		assertValueOfCompileTimeExpr("`${`Hello`} ${`world`}!`", "Hello world!");
	}

	@Test
	def public void testAddition() {
		assertValueOfCompileTimeExpr("39 + 3", 42);
		assertValueOfCompileTimeExpr("1.2 + 3.4", 4.6);
		assertValueOfCompileTimeExpr("'1.2' + 3.4", "1.23.4");
		assertValueOfCompileTimeExpr("1.2 + '3.4'", "1.23.4");
		assertValueOfCompileTimeExpr("'Hello ' + \"world\" + `!`", "Hello world!");
	}

	@Test
	def public void testSubtraction() {
		assertValueOfCompileTimeExpr("45 - 3", 42);
		assertValueOfCompileTimeExpr("1.2 - 3.4", -2.2);
	}

	@Test
	def public void testMultiplication() {
		assertValueOfCompileTimeExpr("21 * 2", 42);
		assertValueOfCompileTimeExpr("-21 * 2", -42);
		assertValueOfCompileTimeExpr("21 * -2", -42);
		assertValueOfCompileTimeExpr("-21 * -2", 42);
	}

	@Test
	def public void testDivision() {
		assertValueOfCompileTimeExpr("21 / 2", 10.5);
		assertValueOfCompileTimeExpr("-21 / 2", -10.5);
		assertValueOfCompileTimeExpr("21 / -2", -10.5);
		assertValueOfCompileTimeExpr("-21 / -2", 10.5);

		assertValueOfCompileTimeExpr("0 / 1", 0);
		assertValueOfCompileTimeExpr("0 / -1", 0); // TODO support for -0 in compile-time expressions

		assertValueOfCompileTimeExpr("-21 / 0", null); // TODO support for Infinity in compile-time expressions
	}

	@Test
	def public void testModulo() {
		assertValueOfCompileTimeExpr("5 % 4", 1);
		assertValueOfCompileTimeExpr("-5 % 4", -1);
		assertValueOfCompileTimeExpr("5 % -4", 1);
		assertValueOfCompileTimeExpr("-5 % -4", -1);
	}

	@Test
	def public void testUnaryExpression() {
		assertValueOfCompileTimeExpr("+3", 3);
		assertValueOfCompileTimeExpr("-3", -3);
		assertValueOfCompileTimeExpr("!true", false);
		assertValueOfCompileTimeExpr("!false", true);
	}

	@Test
	def public void testLogicalExpressions() {
		assertValueOfCompileTimeExpr("false && false", false);
		assertValueOfCompileTimeExpr("false && true", false);
		assertValueOfCompileTimeExpr("true && false", false);
		assertValueOfCompileTimeExpr("true && true", true);
		assertValueOfCompileTimeExpr("false || false", false);
		assertValueOfCompileTimeExpr("false || true", true);
		assertValueOfCompileTimeExpr("true || false", true);
		assertValueOfCompileTimeExpr("true || true", true);
		assertValueOfCompileTimeExpr("!(true && false)", true);
		assertValueOfCompileTimeExpr("!(true || false)", false);
		assertValueOfCompileTimeExpr("true && false ? 'yep' : 'nope'", 'nope');
		assertValueOfCompileTimeExpr("true || false ? 'yep' : 'nope'", 'yep');
	}

	@Test
	def public void testEnums() {
		val enumStringBased = '''
			@StringBased
			enum Color { RED, GREEN: '*green*', BLUE }
		''';
		assertValueOfCompileTimeExpr(enumStringBased, "Color.RED", "RED");
		assertValueOfCompileTimeExpr(enumStringBased, "Color.GREEN", "*green*");
		assertValueOfCompileTimeExpr(enumStringBased, "Color.RED + Color.GREEN + Color.BLUE", "RED*green*BLUE");
		assertValueOfCompileTimeExpr(enumStringBased, "`_${Color.RED}_${Color.GREEN}_${Color.BLUE}_`", "_RED_*green*_BLUE_");
		
		// non-@StringBased enums are not treated as compile-time expressions:
		val enumPlain = '''
			enum Color { RED, GREEN: '*green*', BLUE }
		''';
		assertValueOfCompileTimeExpr(enumPlain, "Color.RED + Color.GREEN + Color.BLUE", null);
	}
}
