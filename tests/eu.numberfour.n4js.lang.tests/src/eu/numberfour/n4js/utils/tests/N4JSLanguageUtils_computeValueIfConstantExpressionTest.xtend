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

import eu.numberfour.n4js.utils.ConstantValue
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import java.math.BigDecimal
import org.junit.Test

/**
 * Test for method {@link N4JSLanguageUtils#computeValueIfConstantExpressionTest(Expression)}.
 */
class N4JSLanguageUtils_computeValueIfConstantExpressionTest extends AbstractN4JSLanguageUtilsTest {

	@Test
	def public void testSimple() {
		assertValueOfConstExpr("null", ConstantValue.NULL);
		assertValueOfConstExpr("undefined", ConstantValue.UNDEFINED);
		assertValueOfConstExpr("true", ConstantValue.TRUE);
		assertValueOfConstExpr("false", ConstantValue.FALSE);
		assertValueOfConstExpr("true", true);
		assertValueOfConstExpr("false", false);
		assertValueOfConstExpr("42", 42);
		assertValueOfConstExpr("42", 42.0);
		assertValueOfConstExpr("42", BigDecimal.valueOf(42));
		assertValueOfConstExpr("42", BigDecimal.valueOf(42.0));
		assertValueOfConstExpr("42.1", 42.1);
		assertValueOfConstExpr("42.1", BigDecimal.valueOf(42.1));
		assertValueOfConstExpr("'hello'", "hello");
	}

	@Test
	def public void testTemplateStrings() {
		assertValueOfConstExpr("`hello`", "hello");
		assertValueOfConstExpr("`hel${  }lo`", "hello");
		assertValueOfConstExpr("`${`Hello`} ${`world`}!`", "Hello world!");
	}

	@Test
	def public void testAddition() {
		assertValueOfConstExpr("39 + 3", 42);
		assertValueOfConstExpr("1.2 + 3.4", 4.6);
		assertValueOfConstExpr("'1.2' + 3.4", "1.23.4");
		assertValueOfConstExpr("1.2 + '3.4'", "1.23.4");
		assertValueOfConstExpr("'Hello ' + \"world\" + `!`", "Hello world!");
	}

	@Test
	def public void testSubtraction() {
		assertValueOfConstExpr("45 - 3", 42);
		assertValueOfConstExpr("1.2 - 3.4", -2.2);
	}

	@Test
	def public void testMultiplication() {
		assertValueOfConstExpr("21 * 2", 42);
		assertValueOfConstExpr("-21 * 2", -42);
		assertValueOfConstExpr("21 * -2", -42);
		assertValueOfConstExpr("-21 * -2", 42);
	}

	@Test
	def public void testDivision() {
		assertValueOfConstExpr("21 / 2", 10.5);
		assertValueOfConstExpr("-21 / 2", -10.5);
		assertValueOfConstExpr("21 / -2", -10.5);
		assertValueOfConstExpr("-21 / -2", 10.5);

		assertValueOfConstExpr("0 / 1", 0);
		assertValueOfConstExpr("0 / -1", 0); // TODO support for -0 in constant expressions

		assertValueOfConstExpr("-21 / 0", null); // TODO support for Infinity in constant expressions
	}

	@Test
	def public void testModulo() {
		assertValueOfConstExpr("5 % 4", 1);
		assertValueOfConstExpr("-5 % 4", -1);
		assertValueOfConstExpr("5 % -4", 1);
		assertValueOfConstExpr("-5 % -4", -1);
	}

	@Test
	def public void testUnaryExpression() {
		assertValueOfConstExpr("+3", 3);
		assertValueOfConstExpr("-3", -3);
		assertValueOfConstExpr("!true", false);
		assertValueOfConstExpr("!false", true);
	}

	@Test
	def public void testLogicalExpressions() {
		assertValueOfConstExpr("false && false", false);
		assertValueOfConstExpr("false && true", false);
		assertValueOfConstExpr("true && false", false);
		assertValueOfConstExpr("true && true", true);
		assertValueOfConstExpr("false || false", false);
		assertValueOfConstExpr("false || true", true);
		assertValueOfConstExpr("true || false", true);
		assertValueOfConstExpr("true || true", true);
		assertValueOfConstExpr("!(true && false)", true);
		assertValueOfConstExpr("!(true || false)", false);
		assertValueOfConstExpr("true && false ? 'yep' : 'nope'", 'nope');
		assertValueOfConstExpr("true || false ? 'yep' : 'nope'", 'yep');
	}

	@Test
	def public void testEnums() {
		val enumStringBased = '''
			@StringBased
			enum Color { RED, GREEN: '*green*', BLUE }
		''';
		assertValueOfConstExpr(enumStringBased, "Color.RED", "RED");
		assertValueOfConstExpr(enumStringBased, "Color.GREEN", "*green*");
		assertValueOfConstExpr(enumStringBased, "Color.RED + Color.GREEN + Color.BLUE", "RED*green*BLUE");
		assertValueOfConstExpr(enumStringBased, "`_${Color.RED}_${Color.GREEN}_${Color.BLUE}_`", "_RED_*green*_BLUE_");
		
		// non-@StringBased enums are not treated as constant expressions:
		val enumPlain = '''
			enum Color { RED, GREEN: '*green*', BLUE }
		''';
		assertValueOfConstExpr(enumPlain, "Color.RED + Color.GREEN + Color.BLUE", null);
	}
}
