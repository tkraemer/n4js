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
package eu.numberfour.n4jsx.validation;

import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.IdentifierRef;
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression;
import eu.numberfour.n4js.ts.types.IdentifiableElement;
import eu.numberfour.n4js.ts.types.TClass;
import eu.numberfour.n4js.ts.types.TFunction;
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator;
import eu.numberfour.n4jsx.n4JSX.JSXElement;
import eu.numberfour.n4jsx.n4JSX.JSXElementName;

/**
 * Validation of names, cf N4JS Spec, Chapter 3.4., Constraints 3 and 4
 */
public class N4JSXBindingValidator extends AbstractN4JSDeclarativeValidator {
	/*
	 * @Inject private N4JSLanguageHelper languageHelper;
	 *
	 * @Inject private N4JSTypeSystem ts;
	 *
	 * @Inject private JavaScriptVariantHelper jsVariantHelper;
	 */

	/**
	 * NEEEDED
	 *
	 * when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	@Override
	public void register(EValidatorRegistrar registrar) {
		// nop
	}

	/**
	 *
	 */
	@Check
	public void checkReactElementBinding(JSXElement jsxElem) {
		JSXElementName elemName = jsxElem.getJsxElementName();
		Expression expr = elemName.getExpression();

		IdentifiableElement ie = null;
		if (expr instanceof IdentifierRef) {
			IdentifierRef idRef = (IdentifierRef) expr;
			ie = idRef.getId();
		}

		if (expr instanceof ParameterizedPropertyAccessExpression) {
			ParameterizedPropertyAccessExpression ppae = (ParameterizedPropertyAccessExpression) expr;
			ie = ppae.getProperty();
		}

		if (ie != null && ie.eContainer() != null) {
			boolean classOrFunction = false;
			classOrFunction |= ie instanceof TClass;
			classOrFunction |= ie instanceof TFunction;
			if (!classOrFunction) {
				String name = ie.getName();
				String message = IssueCodes.getMessageForREACT_ELEMENT_TYPE_ERROR(name);
				addIssue(message, expr, IssueCodes.REACT_ELEMENT_TYPE_ERROR);
			}
		}
	}
}
