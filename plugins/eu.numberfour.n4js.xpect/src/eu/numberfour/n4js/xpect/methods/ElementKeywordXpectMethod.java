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
package eu.numberfour.n4js.xpect.methods;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.xpect.XpectImport;
import org.xpect.expectation.IStringExpectation;
import org.xpect.expectation.StringExpectation;
import org.xpect.parameter.ParameterParser;
import org.xpect.runner.Xpect;

import com.google.inject.Inject;

import eu.numberfour.n4js.validation.N4JSElementKeywordProvider;
import eu.numberfour.n4js.xpect.common.N4JSOffsetAdapter;
import eu.numberfour.n4js.xpect.common.N4JSOffsetAdapter.IEObjectCoveringRegion;

/**
 * This class provides the Xpect method 'elementKeyword' to test the kind of AST nodes. The expected value of kind is
 * identical to that shown on the left side of the the dialog when hovering the mouse over an element in the editor. See
 * {@code
 * N4JSHoverProvider}.
 */
@XpectImport(N4JSOffsetAdapter.class)
public class ElementKeywordXpectMethod {
	@Inject
	N4JSElementKeywordProvider keywordProvider;

	@Inject
	EObjectAtOffsetHelper offsetHelper;

	/**
	 * Test the element keyword of an element. Examples of element keyword are getter, setter, field etc.
	 */
	@ParameterParser(syntax = "('at' arg1=OFFSET)?")
	@Xpect
	public void elementKeyword(
			@StringExpectation IStringExpectation expectation,
			IEObjectCoveringRegion offset) {

		EObject context = offset.getEObject();
		// Get the cross-referenced element at the offset.
		// Identical behavior as in hover in the IDE! See class N4JSHoverProvider
		EObject crossReferencedElement = offsetHelper
				.resolveCrossReferencedElementAt((XtextResource) context.eResource(), offset.getOffset());

		String actual = calculateElementKeyword(crossReferencedElement);
		expectation.assertEquals(actual);
	}

	private String calculateElementKeyword(EObject crossReferencedElement) {
		if (crossReferencedElement == null)
			return null;
		return keywordProvider.keyword(crossReferencedElement);
	}
}
