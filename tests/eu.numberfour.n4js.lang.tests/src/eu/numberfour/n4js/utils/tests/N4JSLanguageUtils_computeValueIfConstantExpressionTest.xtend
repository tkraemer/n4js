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

/**
 * Test for method {@link N4JSLanguageUtils#computeValueIfConstantExpressionTest(Expression)}.
 */
class N4JSLanguageUtils_computeValueIfConstantExpressionTest extends AbstractN4JSLanguageUtilsTest {

	@Test
	def public void testSimple() {
		assertValueOfConstExpr("null", N4JSLanguageUtils.VALUE_NULL);
		assertValueOfConstExpr("undefined", N4JSLanguageUtils.VALUE_UNDEFINED);
		assertValueOfConstExpr("false", false);
		assertValueOfConstExpr("true", true);
		assertValueOfConstExpr("42", BigDecimal.valueOf(42));
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
		assertValueOfConstExpr("39 + 3", BigDecimal.valueOf(42));
		assertValueOfConstExpr("1.2 + 3.4", BigDecimal.valueOf(4.6));
		assertValueOfConstExpr("'1.2' + 3.4", "1.23.4");
		assertValueOfConstExpr("1.2 + '3.4'", "1.23.4");
		assertValueOfConstExpr("'Hello ' + \"world\" + `!`", "Hello world!");
	}

	@Test
	def public void testSubtraction() {
		assertValueOfConstExpr("45 - 3", BigDecimal.valueOf(42));
		assertValueOfConstExpr("1.2 - 3.4", BigDecimal.valueOf(-2.2));
	}

	@Test
	def public void testUnaryExpression() {
		assertValueOfConstExpr("+3", BigDecimal.valueOf(3));
		assertValueOfConstExpr("-3", BigDecimal.valueOf(-3));
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
