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

import eu.numberfour.n4js.N4JSInjectorProvider
import eu.numberfour.n4js.typesystem.AbstractTypeSystemHelperTests
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import static eu.numberfour.n4js.typesystem.SubTypeRelationForTest.*

/**
 * N4JS Spec Test: 4.3.2. Type Variables
 */
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProvider)
class N4_03_2_TypeVariablesTest extends AbstractTypeSystemHelperTests {

	final static CharSequence scriptPrefix =
		'''
			class A{}
			class B extends A{}
			class C extends B{}
		'''


	@Test
	def void testTypeVarInGenericWithoutBounds() {
		assembler.setScriptPrefix('''
			«scriptPrefix»
			class G<T> {
				typevar: T;
				foo(): void {
					this.typevar;
				}
			}
		''')
		assertSubTypeOfRef("typevar", _sub, "any");
	}


	@Test
	def void testTypeVarInGenericWithBound() {
		assembler.setScriptPrefix('''
			«scriptPrefix»
			class G<T extends A> {
				typevar: T;
			 	foo(): void {
					this.typevar;
				}
			}
		''')
		assertSubTypeOfRef("typevar", _sub, "A");
	}

	@Test
	def void testTypeVarInGenericFunctionWOBounds() {
		assembler.setScriptPrefix('''
			«scriptPrefix»
			function <T> foo(typevar: T) {
				typevar;
			}
		''')
		assertSubTypeOfRef("typevar", _sub, "any");
	}

	@Test
	def void testTypeVarInGenericFunctionWithBounds() {
		assembler.setScriptPrefix('''
			«scriptPrefix»
			function <T extends A> foo(typevar: T) {
				typevar;
			}
		''')
		assertSubTypeOfRef("typevar", _sub, "A");
	}


}
