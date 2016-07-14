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

import eu.numberfour.n4js.n4JS.BindingProperty
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4GetterDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.N4SetterDeclaration
import eu.numberfour.n4js.n4JS.PropertyGetterDeclaration
import eu.numberfour.n4js.n4JS.PropertyMethodDeclaration
import eu.numberfour.n4js.n4JS.PropertyNameKind
import eu.numberfour.n4js.n4JS.PropertyNameOwner
import eu.numberfour.n4js.n4JS.PropertyNameValuePair
import eu.numberfour.n4js.n4JS.PropertySetterDeclaration
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.ts.conversions.ComputedPropertyNameValueConverter
import org.junit.Test

/**
 * Tests the properties of PropertyNameOwner and LiteralOrComputedPropertyName.
 */
class PropertyNameOwnerTest extends AbstractParserTest {

	private static final String SYM_PREFIX = ComputedPropertyNameValueConverter.SYMBOL_IDENTIFIER_PREFIX;

	@Test
	def void testNameIsIdentifier() {
		val script = '''
			class C {
				field;
				get getter() {return null;}
				set setter(value) {}
				method() {}
			}
		'''.parseSuccessfully
		script.assertPropertyName(N4FieldDeclaration, PropertyNameKind.IDENTIFIER, "field");
		script.assertPropertyName(N4GetterDeclaration, PropertyNameKind.IDENTIFIER, "getter");
		script.assertPropertyName(N4SetterDeclaration, PropertyNameKind.IDENTIFIER, "setter");
		script.assertPropertyName(N4MethodDeclaration, PropertyNameKind.IDENTIFIER, "method");
	}

	@Test
	def void testNameIsIdentifier2() {
		val script = '''
			var ol = {
				prop: undefined,
				get pgetter() {return null;},
				set psetter(value) {},
				pmethod() {}
			};
		'''.parseSuccessfully
		script.assertPropertyName(PropertyNameValuePair, PropertyNameKind.IDENTIFIER, "prop");
		script.assertPropertyName(PropertyGetterDeclaration, PropertyNameKind.IDENTIFIER, "pgetter");
		script.assertPropertyName(PropertySetterDeclaration, PropertyNameKind.IDENTIFIER, "psetter");
		script.assertPropertyName(PropertyMethodDeclaration, PropertyNameKind.IDENTIFIER, "pmethod");
	}

	@Test
	def void testNameIsString() {
		val script = '''
			class C {
				'field';
				get 'getter'() {return null;}
				set 'setter'(value) {}
				'method'() {}
			}
		'''.parseSuccessfully
		script.assertPropertyName(N4FieldDeclaration, PropertyNameKind.STRING, "field");
		script.assertPropertyName(N4GetterDeclaration, PropertyNameKind.STRING, "getter");
		script.assertPropertyName(N4SetterDeclaration, PropertyNameKind.STRING, "setter");
		script.assertPropertyName(N4MethodDeclaration, PropertyNameKind.STRING, "method");
	}

	@Test
	def void testNameIsString2() {
		val script = '''
			var ol = {
				'prop': undefined,
				get 'pgetter'() {return null;},
				set 'psetter'(value) {},
				'pmethod'() {}
			};
		'''.parseSuccessfully
		script.assertPropertyName(PropertyNameValuePair, PropertyNameKind.STRING, "prop");
		script.assertPropertyName(PropertyGetterDeclaration, PropertyNameKind.STRING, "pgetter");
		script.assertPropertyName(PropertySetterDeclaration, PropertyNameKind.STRING, "psetter");
		script.assertPropertyName(PropertyMethodDeclaration, PropertyNameKind.STRING, "pmethod");
	}

	@Test
	def void testNameIsNumber() {
		val script = '''
			class C {
				39;
				get 40() {return null;}
				set 41(value) {}
				42() {}
			}
		'''.parseSuccessfully
		script.assertPropertyName(N4FieldDeclaration, PropertyNameKind.NUMBER, "39");
		script.assertPropertyName(N4GetterDeclaration, PropertyNameKind.NUMBER, "40");
		script.assertPropertyName(N4SetterDeclaration, PropertyNameKind.NUMBER, "41");
		script.assertPropertyName(N4MethodDeclaration, PropertyNameKind.NUMBER, "42");
	}

	@Test
	def void testNameIsNumber2() {
		val script = '''
			var ol = {
				39: undefined,
				get 40() {return null;},
				set 41(value) {},
				42() {}
			};
		'''.parseSuccessfully
		script.assertPropertyName(PropertyNameValuePair, PropertyNameKind.NUMBER, "39");
		script.assertPropertyName(PropertyGetterDeclaration, PropertyNameKind.NUMBER, "40");
		script.assertPropertyName(PropertySetterDeclaration, PropertyNameKind.NUMBER, "41");
		script.assertPropertyName(PropertyMethodDeclaration, PropertyNameKind.NUMBER, "42");
	}

	@Test
	def void testNameIsComputed() {
		val script = '''
			class C {
				['field'];
				get ['getter']() {return null;}
				set ['setter'](value) {}
				['method']() {}
			}
		'''.parseSuccessfully
		script.assertPropertyName(N4FieldDeclaration, PropertyNameKind.COMPUTED, "field");
		script.assertPropertyName(N4GetterDeclaration, PropertyNameKind.COMPUTED, "getter");
		script.assertPropertyName(N4SetterDeclaration, PropertyNameKind.COMPUTED, "setter");
		script.assertPropertyName(N4MethodDeclaration, PropertyNameKind.COMPUTED, "method");
	}

	@Test
	def void testNameIsComputed2() {
		val script = '''
			var ol = {
				['prop']: undefined,
				get ['pgetter']() {return null;},
				set ['psetter'](value) {},
				['pmethod']() {}
			};
		'''.parseSuccessfully
		script.assertPropertyName(PropertyNameValuePair, PropertyNameKind.COMPUTED, "prop");
		script.assertPropertyName(PropertyGetterDeclaration, PropertyNameKind.COMPUTED, "pgetter");
		script.assertPropertyName(PropertySetterDeclaration, PropertyNameKind.COMPUTED, "psetter");
		script.assertPropertyName(PropertyMethodDeclaration, PropertyNameKind.COMPUTED, "pmethod");
	}

	@Test
	def void testNameIsSymbol() {
		val script = '''
			class C {
				[Symbol.iterator]() {}
			}
		'''.parseSuccessfully
		script.assertPropertyName(N4MethodDeclaration, PropertyNameKind.COMPUTED, SYM_PREFIX + "iterator");
	}

	@Test
	def void testNameIsSymbol2() {
		val script = '''
			var ol = {
				[Symbol.iterator]() {}
			}
		'''.parseSuccessfully
		script.assertPropertyName(PropertyMethodDeclaration, PropertyNameKind.COMPUTED, SYM_PREFIX + "iterator");
	}

	@Test
	def void testNameOfBindingProperty() {
		'''
			var {prop: myVar} = {prop: undefined};
		'''.parseSuccessfully.assertPropertyName(BindingProperty, PropertyNameKind.IDENTIFIER, "prop");
		'''
			var {'prop': myVar} = {prop: undefined};
		'''.parseSuccessfully.assertPropertyName(BindingProperty, PropertyNameKind.STRING, "prop");
		'''
			var {42: myVar} = {prop: undefined};
		'''.parseSuccessfully.assertPropertyName(BindingProperty, PropertyNameKind.NUMBER, "42");
		'''
			var {['prop']: myVar} = {prop: undefined};
		'''.parseSuccessfully.assertPropertyName(BindingProperty, PropertyNameKind.COMPUTED, "prop");
		'''
			var {[Symbol.iterator]: myVar} = {prop: undefined};
		'''.parseSuccessfully.assertPropertyName(BindingProperty, PropertyNameKind.COMPUTED, SYM_PREFIX + "iterator");
	}

	/**
	 * Searches the 1st element of type 'elementType' and performs property-name-related assertions on that element.
	 */
	def private void assertPropertyName(Script script, Class<? extends PropertyNameOwner> elementType, PropertyNameKind expectedKind, String expectedName) {
		val element = script.eAllContents.filter(elementType).head;
		assertNotNull("no property name owner found of type: " + elementType.getName(), element);
		if (expectedKind === PropertyNameKind.IDENTIFIER
			|| expectedKind === PropertyNameKind.STRING
			|| expectedKind === PropertyNameKind.NUMBER) {

			assertSame(expectedKind, element.declaredName.kind);
			assertEquals(expectedName, element.declaredName.literalName);
			assertNull(element.declaredName.computedName);
			assertNull(element.declaredName.expression);
			assertEquals(expectedName, element.name);

		} else if(expectedKind === PropertyNameKind.COMPUTED) {

			assertSame(PropertyNameKind.COMPUTED, element.declaredName.kind);
			assertNull(element.declaredName.literalName);
			assertEquals(expectedName, element.declaredName.computedName);
			assertNotNull(element.declaredName.expression);
			assertEquals(expectedName, element.name);

		} else {
			throw new IllegalArgumentException();
		}
	}
}
