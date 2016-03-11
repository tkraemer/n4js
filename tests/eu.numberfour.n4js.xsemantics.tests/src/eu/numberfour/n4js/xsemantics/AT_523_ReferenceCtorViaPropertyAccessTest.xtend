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
import eu.numberfour.n4js.N4JSInjectorProvider
import eu.numberfour.n4js.validation.JavaScriptVariant
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

/*
 */
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProvider)
class AT_523_ReferenceCtorViaPropertyAccessTest extends AbstractTypesystemTest {

	@Inject
	extension ValidationTestHelper


	@Test
	def void testReferenceToConstructorViaInstance() {
		'''
			class C {}
			var c: C;
			var ctor = c.constructor;				// reference via instance of C
			var resultCtor: constructor{C} = ctor;
			var resultNewInstance: C = new ctor();
		'''.assertValidationErrors(
		'''
		A reference to method constructor is created detached from a (correct) this-instance.
		'''
		)
	}

	@Test
	def void testReferenceToConstructorViaClass() {
		'''
			class C {}
			var ctor = C;				// static reference
			var resultCtor: constructor{C} = ctor;
			var resultNewInstance: C = new ctor();
		'''.assertValidationErrors(
		'''
		'''
		)
	}

	@Test
	def void testMiscellaneous() {
		// this test case was taken from the task description and comments of IDE-523
		'''
			class C {}
			var x = C
			var c: C;
			var y = c.constructor
			var z1 = new y()
			var z2 = new C()


			//The type of x and y should be constructor{C}

			function fun(ctor: constructor{C}) {
			}

			fun(x)
			fun(y)


			//The type of z1 and z2 should be C

			var result1: C = z1;
			var result2: C = z2;
		'''.assertValidationErrors(
		'''
		A reference to method constructor is created detached from a (correct) this-instance.
		'''
		)
	}

	def private assertValidationErrors(CharSequence input, CharSequence expectedErrors) {
		val script = createScript(JavaScriptVariant.n4js,input.toString)
		val issues = script.validate();
		issues.assertErrorMessages(expectedErrors)
	}
}
