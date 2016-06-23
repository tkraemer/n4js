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

import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import eu.numberfour.n4js.N4JSInjectorProviderWithFilteredValidator

/**
 * N4JS Spec Test: 4.7.2. Functions and FunctionType, Type Inference
 */
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProviderWithFilteredValidator)
class N4_7_2_JudgmentSubtypeRefForFunctionsTest extends AbstractJudgmentSubtypeTest {

	@Test
	def void testSubTypeFunctionRefsSpecExample() {
		assertSubType("{function():void}", "{function():void}");
		assertSubType("{function(A):A}", "{function(A):A}");
		assertSubType("{function(A):void}", "{function(B):void}");
		assertSubType("{function():B}", "{function():A}");
		assertSubType("{function(A):B}", "{function(B):A}");
		assertSubType("{function(A):A}", "{function(B):void}", true); // !
		assertSubType("{function(A):A}", "{function(B):any}");
		assertSubType("{function(A):A}", "{function(B)}");
		assertSubType("{function(A):void}", "{function(B):any}", false); // !
		assertSubType("{function(A):void}", "{function(B)}");
		assertSubType("{function(A):void}", "{function(B):A}", false);
	}

	@Test
	def void testSubTypeFunctionRefsSpecExampleVarOpt() {
		assertSubType("{function(A)}", "{function(B)}");
		assertSubType("{function(...A)}", "{function(A)}");
		assertSubType("{function(A, A)}", "{function(A)}", false);
		assertSubType("{function(A)}", "{function(A,A)}", true); // !
		assertSubType("{function(A, ...A)}", "{function(A)}");
		assertSubType("{function(A)}", "{function(A,...A)}", true); // !
		assertSubType("{function(A, ...A)}", "{function(B)}");
		assertSubType("{function(A?)}", "{function(A?)}");
		assertSubType("{function(...A)}", "{function(...A)}");
		assertSubType("{function(A?)}", "{function(A)}");
		assertSubType("{function(A)}", "{function(A?)}", false);
		assertSubType("{function(...A)}", "{function(A?)}");
		assertSubType("{function(A?)}", "{function(...A)}", true); // !
		assertSubType("{function(A,...A)}", "{function(...A)}", false);
		assertSubType("{function(A,A?)}", "{function(...A)}", false);
		assertSubType("{function(A?,...A)}", "{function(...A)}");
		assertSubType("{function(...A)}", "{function(A?,...A)}");
		assertSubType("{function(...A)}", "{function(A?)}");
		assertSubType("{function(A?,A?)}", "{function(...A)}", true); // !
		assertSubType("{function(A?,A?,A?)}", "{function(...A)}", true); // !
	}

	@Test
	def void testSubTypeFunctionRefsSpecExampleVarOptWithNoneOnRHS() {
		assertSubType("{function(A?)}", "{function()}");
		assertSubType("{function(...A)}", "{function()}");
		assertSubType("{function(A?,A?)}", "{function()}");
		assertSubType("{function(A?,...A)}", "{function()}");
		assertSubType("{function(A)}", "{function()}", false);
	}

//	@Test
//	def void testSingle() {
//		assertSubType("{function(A?,A...)}", "{function(A...)}");
//	}

	@Test
	def void testSubTypeFunctionRefsSpecExampleThis() {
		// IDE-802: so X is not known in the Context of Test,
		// replacing X with (! A;B;C;) D;E here.

		assertSubType("{@This(A) function():void}", "{@This(D) function():void}", false);
		assertSubType("{@This(B) function():void}", "{@This(A) function():void}", false);
		assertSubType("{@This(A) function():void}", "{@This(B) function():void}");
		assertSubType("{@This(any) function():void}", "{@This(D) function():void}");
		assertSubType("{function():void}", "{@This(D) function():void}");
		assertSubType("{@This(A) function():void}", "{@This(any) function():void}", false);
		assertSubType("{@This(A) function():void}", "{function():void}", false);
	}

}
