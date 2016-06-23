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
package eu.numberfour.n4js.typesystem

import eu.numberfour.n4js.N4JSInjectorProviderWithSuppressedValidator
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 */
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProviderWithSuppressedValidator)
class InferenceContext_GenericFunctionTypesTest extends AbstractInferenceContextTest {


	@Test
	def void test_baseCase() {

		// {function<T,S>(G2<T,S>):T} <: {function(G2<alpha,beta>):alpha}

		val T = createTypeVar("T");
		val S = createTypeVar("S");
		val left = functionType(#[T,S], T.ref, G2.of(T,S));
		val right = functionType(alpha.ref, G2.of(alpha,beta));

		script.assertSolution(
			#[
				constraint(left,'<:',right)
			],
			alpha -> _G.anyTypeRef,
			beta -> _G.anyTypeRef
		)
	}

	@Test
	def void test_returnTypeFixed() {

		// {function<T,S>(G2<T,S>):string} <: {function(G2<alpha,beta>):alpha}

		val T = createTypeVar("T");
		val S = createTypeVar("S");
		val left = functionType(#[T,S], _G.stringTypeRef, G2.of(T,S));
		val right = functionType(alpha.ref, G2.of(alpha,beta));

		script.assertSolution(
			#[
				constraint(left,'<:',right)
			],
			alpha -> _G.stringTypeRef,
			beta -> _G.anyTypeRef
		)
	}

	@Test
	def void test_fparFixed() {

		// {function<T,S>(G2<string,S>):T} <: {function(G2<alpha,beta>):alpha}

		val T = createTypeVar("T");
		val S = createTypeVar("S");
		val left = functionType(#[T,S], T.ref, G2.of(_G.stringType,S));
		val right = functionType(alpha.ref, G2.of(alpha,beta));

		script.assertSolution(
			#[
				constraint(left,'<:',right)
			],
			alpha -> _G.stringTypeRef,
			beta -> _G.anyTypeRef
		)
	}
}
