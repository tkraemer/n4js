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
package eu.numberfour.n4js.validation

import com.google.inject.Inject
import com.google.inject.Provider
import eu.numberfour.n4js.N4JSInjectorProvider
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.StringLiteral
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.resource.XtextResourceSet
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

/**
 */
@InjectWith(N4JSInjectorProvider)
@RunWith(XtextRunner)
class JavaScriptVariantTest {

	@Inject Provider<XtextResourceSet> resourceSetProvider;
	@Inject extension ParseHelper<Script>

	@Test
	def void testN4JSRecognition() {
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.n4js, "some/test.n4js", '''"here"''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.n4js, "some/test.N4JS", '''"here"''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.n4js, "some/test.n4js.xt", '''"here"''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.n4js, "some/test.n4js", '''
			function foo() {
				"use strict"
				"here"
			}''');
	}

	@Test
	def void testStrictRecognition() {
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.strict, "some/test.js", '''
			"use strict"
			"here"''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.strict, "some/test.JS", '''
			"use strict"
			"here"''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.strict, "some/test.js.xt", '''
			"use strict"
			"here"''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.strict, "some/test.js", '''
			function foo() {
				"use strict"
				"here"
			}''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.strict, "some/test.js", '''
			function foo() {
				"nothere"
			}
			function bar() {
				"use strict"
				"here"
			}''');
	}

	@Test
	def void testUnrestrictedRecognition() {
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.unrestricted, "some/test.js", '''
			"here"''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.unrestricted, "some/test.JS", '''
			"here"''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.unrestricted, "some/test.js.xt", '''
			"here"''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.unrestricted, "some/test.js", '''
			function foo() {
				"here"
			}''');
		assertVariant(JavaScriptVariantHelper.JavaScriptVariant.unrestricted, "some/test.js", '''
			function foo() {
				"use strict"
				"nothere"
			}
			function bar() {
				"here"
			}''');
	}

	/**
	 * Asserts that a given position, marked by a string literal "here", is in expected variant.
	 */
	def assertVariant(JavaScriptVariantHelper.JavaScriptVariant expectedVariant, String filePath, CharSequence src) {
		val rs = resourceSetProvider.get
		val script= src.parse(URI.createFileURI(filePath), rs);
		val location = script.eAllContents.filter(StringLiteral).findFirst["here"==it.value];
		assertNotNull("Bogus test, did not find string literal \"here\"")
		val variant = JavaScriptVariantHelper.JavaScriptVariant.getVariant(location)
		assertEquals(expectedVariant, variant);
		switch (expectedVariant) {
			case JavaScriptVariantHelper.JavaScriptVariant.n4js: {
				assertTrue(JavaScriptVariantHelper.JavaScriptVariant.n4js.isActive(location));
				assertFalse(JavaScriptVariantHelper.JavaScriptVariant.strict.isActive(location));
				assertFalse(JavaScriptVariantHelper.JavaScriptVariant.unrestricted.isActive(location));
			}
			case JavaScriptVariantHelper.JavaScriptVariant.strict: {
				assertFalse(JavaScriptVariantHelper.JavaScriptVariant.n4js.isActive(location));
				assertTrue(JavaScriptVariantHelper.JavaScriptVariant.strict.isActive(location));
				assertFalse(JavaScriptVariantHelper.JavaScriptVariant.unrestricted.isActive(location));
			}
			case JavaScriptVariantHelper.JavaScriptVariant.unrestricted: {
				assertFalse(JavaScriptVariantHelper.JavaScriptVariant.n4js.isActive(location));
				assertFalse(JavaScriptVariantHelper.JavaScriptVariant.strict.isActive(location));
				assertTrue(JavaScriptVariantHelper.JavaScriptVariant.unrestricted.isActive(location));
			}
			default: throw new IllegalArgumentException(expectedVariant.toString)
		}
	}

}
