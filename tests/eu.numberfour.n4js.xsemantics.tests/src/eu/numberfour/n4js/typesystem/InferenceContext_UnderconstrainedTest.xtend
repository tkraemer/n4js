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
class InferenceContext_UnderconstrainedTest extends AbstractInferenceContextTest {


	@Test
	def void test_underconstrained_empty() {
		script.assertSolution(
			#[
				// empty
			],
			alpha -> _G.anyTypeRef
		)
	}

	@Test
	def void test_underconstrained_reflexive01() {
		script.assertSolution(
			#[
				constraint(alpha,'<:',alpha)
			],
			alpha -> _G.anyTypeRef
		)
	}

	@Test
	def void test_underconstrained_reflexive02() {
		script.assertSolution(
			#[
				constraint(alpha,'=',alpha)
			],
			alpha -> _G.anyTypeRef
		)
	}

	@Test
	def void test_underconstrained_dependency01() {
		script.assertSolution(
			#[
				constraint(alpha,'<:',beta)
			],
			alpha -> _G.anyTypeRef,
			beta -> _G.anyTypeRef
		)
	}

	@Test
	def void test_underconstrained_dependency02() {
		script.assertSolution(
			#[
				constraint(alpha,'=',beta)
			],
			alpha -> _G.anyTypeRef,
			beta -> _G.anyTypeRef
		)
	}

	@Test
	def void test_underconstrained_transitive() {
		script.assertSolution(
			#[
				constraint(alpha,'<:',beta),
				constraint(beta,'<:',gamma)
			],
			alpha -> _G.anyTypeRef,
			beta -> _G.anyTypeRef,
			gamma -> _G.anyTypeRef
		)
	}
}
