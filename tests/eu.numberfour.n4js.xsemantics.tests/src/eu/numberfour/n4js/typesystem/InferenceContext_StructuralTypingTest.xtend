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

import eu.numberfour.n4js.N4JSInjectorProvider
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Ignore
import org.junit.runner.RunWith

/**
 * Here we test constraints like
 * <pre>⟨ A&lt;string> <: ~B&lt;α> ⟩</pre>
 * or
 * <pre>⟨ A&lt;string> <: ~Object with { α fieldInA; } ⟩</pre>.
 */
@Ignore
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProvider)
class InferenceContext_StructuralTypingTest extends AbstractInferenceContextTest {

	// TODO add tests for handling of structural types in InferenceContext
	// for some inspiration, see file
	// /eu.numberfour.n4js.xpect.tests/model/typesystem/TypeArgumentInference_StructAndNomTypes.n4js.xt
}
