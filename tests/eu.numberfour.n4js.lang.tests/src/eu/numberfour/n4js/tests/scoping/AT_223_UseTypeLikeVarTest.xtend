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
package eu.numberfour.n4js.tests.scoping

import com.google.inject.Inject
import com.google.inject.Provider
import eu.numberfour.n4js.N4JSInjectorProvider
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.Script
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.resource.XtextResourceSet
import org.junit.Test
import org.junit.runner.RunWith

/**
 */
@InjectWith(N4JSInjectorProvider)
@RunWith(XtextRunner)
class AT_223_UseTypeLikeVarTest {

	@Inject Provider<XtextResourceSet> resourceSetProvider
	@Inject extension ParseHelper<Script>
	@Inject extension ValidationTestHelper

	@Test
	def void test_01() {
		val script = '''
			class A{}
			var a = new A();
		'''.parse
		script.assertNoErrors
	}
	@Test
	def void test_02() {
		val rs = resourceSetProvider.get
		'''
			export class A {}
		'''.parse(URI.createURI("A.js"), rs)
		val script = '''
			import { A } from "A"
			var a = new A();
		'''.parse(URI.createURI("B.js"), rs)
		script.assertNoErrors
	}
	@Test
	def void test_03() {
		val rs = resourceSetProvider.get
		'''
			export class A {}
		'''.parse(URI.createURI("A.js"), rs)
		val script = '''
			import { A } from "A"
			var a;
			if (a instanceof A) {
			}
		'''.parse(URI.createURI("B.js"), rs)
		script.assertNoErrors
	}
	@Test
	def void test_04() {
		val script = '''
			function foo() {};
			var a = foo;
			foo = "Hello";
			a();
		'''.parse
		script.assertError(N4JSPackage.Literals.STRING_LITERAL, "eu.numberfour.n4js.xsemantics.SubtypeParameterizedTypeRef")
	}
}
