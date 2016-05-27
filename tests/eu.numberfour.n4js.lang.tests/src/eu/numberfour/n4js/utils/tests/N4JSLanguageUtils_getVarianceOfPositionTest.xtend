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
package eu.numberfour.n4js.utils.tests

import com.google.inject.Inject
import eu.numberfour.n4js.N4JSInjectorProvider
import eu.numberfour.n4js.N4JSParseHelper
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.util.Variance
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static eu.numberfour.n4js.ts.types.util.Variance.*
import static org.junit.Assert.*

/**
 * Tests for method {@link N4JSLanguageUtils#getVarianceOfPosition(ParameterizedTypeRef)}.
 */
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProvider)
class N4JSLanguageUtils_getVarianceOfPositionTest {

	@Inject private extension N4JSParseHelper parseHelper;
	@Inject private extension ValidationTestHelper;


	@Test
	def public void testBaseCases() {
		"class C<T> { field: T; }".assertVarianceOfPosition(INV);
		"class C<T> { get g(): T {return null;} }".assertVarianceOfPosition(CO);
		"class C<T> { set s(p: T) {} }".assertVarianceOfPosition(CONTRA);
		"class C<T> { m(): T {return null;} }".assertVarianceOfPosition(CO);
		"class C<T> { m(p: T) {} }".assertVarianceOfPosition(CONTRA);
	}

	@Test
	def public void testPrivateVisibility() {
		"class C<T> { private field: T; }".assertVarianceOfPosition(null);
		"class C<T> { private get g(): T {return null;} }".assertVarianceOfPosition(null);
		"class C<T> { private set s(p: T) {} }".assertVarianceOfPosition(null);
		"class C<T> { private m(): T {return null;} }".assertVarianceOfPosition(null);
		"class C<T> { private m(p: T) {} }".assertVarianceOfPosition(null);
	}

	@Test
	def public void testFinalField() {
		"class C<T> { @Final public field: T = null; }".assertVarianceOfPosition(CO);
	}

	@Test
	def public void testPassingOnAsTypeArgument() {
		'''
			class G<S1,S2,S3> {}
			class C<T> extends G<string,T,number> {}
		'''.assertVarianceOfPosition(INV)
		'''
			class G<S1,out S2,S3> {}
			class C<T> extends G<string,T,number> {}
		'''.assertVarianceOfPosition(CO)
		'''
			class G<S1,in S2,S3> {}
			class C<T> extends G<string,T,number> {}
		'''.assertVarianceOfPosition(CONTRA)
	}


	def private void assertVarianceOfPosition(CharSequence code, Variance expectedVariance) {
		val script = code.parse;
		script.assertNoParseErrors;
		val issues = script.validate;
		assertTrue(issues.toString, issues.empty);
		val ref2TypeVars = script.eAllContents.filter(ParameterizedTypeRef).filter[declaredType instanceof TypeVariable].toList;
		assertEquals("expected exactly one reference to a type variable, but found " + ref2TypeVars + " reference",
			1, ref2TypeVars.size);
		val ref2TypeVar = ref2TypeVars.head;
		val computedVariance = N4JSLanguageUtils.getVarianceOfPosition(ref2TypeVar);
		assertEquals(expectedVariance, computedVariance);
	}
}
