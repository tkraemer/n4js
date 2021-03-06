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
package eu.numberfour.n4js.tests.n4JS.destructuring

import com.google.inject.Inject
import eu.numberfour.n4js.N4JSInjectorProvider
import eu.numberfour.n4js.n4JS.ArrayLiteral
import eu.numberfour.n4js.n4JS.ForStatement
import eu.numberfour.n4js.n4JS.N4JSASTUtils
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.Script
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

/**
 * Destructuring patterns may appear in the AST as an ArrayLiteral or ObjectLiteral. This class tests the utility method
 * for finding out whether an ArrayLiteral or ObjectLiteral is a destructuring pattern.
 */
@InjectWith(N4JSInjectorProvider)
@RunWith(XtextRunner)
class IsDestructuringPatternTest {

	@Inject extension ParseHelper<Script>
	@Inject extension ValidationTestHelper


	@Test
	def void testArrayLiteralInAssignmentExpression() {
		val script = '''

			var a,b,c,arr;

			([a,b,c] = [1,2,3]);

			arr = [1,2,3];

		'''.parseAndValidate;

		val arrLit0 = script.eAllContents.filter(ArrayLiteral).toList.get(0);
		val arrLit1 = script.eAllContents.filter(ArrayLiteral).toList.get(1);
		val arrLit2 = script.eAllContents.filter(ArrayLiteral).toList.get(2);
		assertNotNull(arrLit0);
		assertNotNull(arrLit1);
		assertNotNull(arrLit2);

		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(arrLit0));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(arrLit1));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(arrLit2));

		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit0));
		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit1));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit2));
	}

	@Test
	def void testArrayLiteralInForStatement_plain() {
		val script = '''

			var a,b,c,arr;

			for([a,b,c] = [1,2,3] ; ; ) {}

		'''.parseAndValidate;

		val arrLit0 = script.eAllContents.filter(ArrayLiteral).toList.get(0);
		val arrLit1 = script.eAllContents.filter(ArrayLiteral).toList.get(1);
		assertNotNull(arrLit0);
		assertNotNull(arrLit1);

		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(arrLit0));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(arrLit1));

		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit0));
		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit1));
	}

	@Test
	def void testArrayLiteralInForStatement_of() {
		val script = '''

			var a,b,c,arr;

			for([a,b,c] of [[1,2,3]]) {}

		'''.parseAndValidate;

		val arrLit0 = script.eAllContents.filter(ForStatement).head.initExpr;
		val arrLit1 = script.eAllContents.filter(ForStatement).head.expression;
		val arrLit2 = arrLit1.eAllContents.filter(ArrayLiteral).head;
		assertNotNull(arrLit0);
		assertNotNull(arrLit1);
		assertNotNull(arrLit2);

		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(arrLit0));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(arrLit1));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(arrLit2));

		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit0));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit1)); // NOTE: this must return false!!!
		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit2)); // TODO unclear; see API doc of method
	}

	@Test
	def void testObjectLiteralInAssignmentExpression() {
		val script = '''

			var a,b,c,obj;

			({prop1:a,prop2:b,prop3:c} = {prop1:0,prop2:0,prop3:0});

			obj = {prop1:0,prop2:0,prop3:0};

		'''.parseAndValidate;

		val objLit0 = script.eAllContents.filter(ObjectLiteral).toList.get(0);
		val objLit1 = script.eAllContents.filter(ObjectLiteral).toList.get(1);
		val objLit2 = script.eAllContents.filter(ObjectLiteral).toList.get(2);
		assertNotNull(objLit0);
		assertNotNull(objLit1);
		assertNotNull(objLit2);

		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(objLit0));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(objLit1));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(objLit2));

		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(objLit0));
		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(objLit1));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(objLit2));
	}

	@Test
	def void testObjectLiteralInForStatement_plain() {
		val script = '''

			var a,b;

			for({prop1:a,prop2:b} = {prop1:1,prop2:2} ; ; ) {}

		'''.parseAndValidate;

		val objLit0 = script.eAllContents.filter(ObjectLiteral).toList.get(0);
		val objLit1 = script.eAllContents.filter(ObjectLiteral).toList.get(1);
		assertNotNull(objLit0);
		assertNotNull(objLit1);

		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(objLit0));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(objLit1));

		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(objLit0));
		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(objLit1));
	}

	@Test
	def void testObjectLiteralInForStatement_of() {
		val script = '''

			var a,b;

			for({prop1:a,prop2:b} of [{prop1:1,prop2:2}]) {}

		'''.parseAndValidate;

		val objLit0 = script.eAllContents.filter(ForStatement).head.initExpr;
		val arrLit0 = script.eAllContents.filter(ForStatement).head.expression;
		val objLit1 = arrLit0.eAllContents.filter(ObjectLiteral).head;
		assertNotNull(objLit0);
		assertNotNull(arrLit0);
		assertNotNull(objLit1);

		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(objLit0));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(arrLit0));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(objLit1));

		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(objLit0));
		assertFalse(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(arrLit0)); // NOTE: this must return false!!!
		assertTrue(N4JSASTUtils.isArrayOrObjectLiteralBeingDestructured(objLit1)); // TODO unclear; see API doc of method
	}


	def private Script parseAndValidate(CharSequence scriptSrc) {
		val script = scriptSrc.parse
		script.validate;
		script.assertNoIssues;
		return script;
	}
}
