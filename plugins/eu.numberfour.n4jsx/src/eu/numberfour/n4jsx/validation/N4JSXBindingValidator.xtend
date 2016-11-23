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

import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4jsx.n4JSX.JSXElement
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

/**
 * Validation of names, cf N4JS Spec, Chapter 3.4., Constraints 3 and 4
 */
class N4JSXBindingValidator extends AbstractN4JSDeclarativeValidator {
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
	override void register(EValidatorRegistrar registrar) {
		// nop
	}

	/**
	 * This method checks JSXElement binding
	 */
	@Check
	def void checkReactElementBinding(JSXElement jsxElem) {
		val elemName = jsxElem.getJsxElementName();
		val expr = elemName.getExpression();
		
		if (!(expr instanceof IdentifierRef)) {
			return;
		}

		val idRef = expr as IdentifierRef;
		var ie = idRef.getId();

		if (expr instanceof ParameterizedPropertyAccessExpression) {
			val ppae = expr;
			ie = ppae.getProperty();
		}

		if (ie !== null && ie.eContainer() !== null) {
			var classOrFunction = false;
			var isFunction = ie instanceof TFunction;
			var isClass = ie instanceof TClass;
			classOrFunction = isFunction || isClass;
			val name = ie.getName();
			if (!classOrFunction) {
				val message = IssueCodes.getMessageForREACT_ELEMENT_NOT_FUNCTION_OR_CLASS_ERROR(name);
				addIssue(message, expr, IssueCodes.REACT_ELEMENT_NOT_FUNCTION_OR_CLASS_ERROR);
			}

			if (isFunction) {
				// Check if the function conforms to React functional component, i.e. its return type is Element
				val tfunction = ie as TFunction
				if (tfunction.returnTypeRef instanceof ParameterizedTypeRef) {
					val typeRef = tfunction.returnTypeRef as ParameterizedTypeRef
					if (typeRef.declaredType instanceof TClass) {
						val tClass = typeRef.declaredType as TClass
						if (tClass.exportedName == "Element") {
							return;
						}
					}
				}
				
				val message = IssueCodes.getMessageForREACT_ELEMENT_FUNCTION_NOT_REACT_ELEMENT_ERROR(name);
				addIssue(message, expr, IssueCodes.REACT_ELEMENT_FUNCTION_NOT_REACT_ELEMENT_ERROR);
				
			}
		}
	}
	
		
	
}
