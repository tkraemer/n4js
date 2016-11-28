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

import com.google.inject.Inject
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.typeRefs.TypeTypeRef
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4jsx.helpers.ReactLookupHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * Validation of names, cf N4JS Spec, Chapter 3.4., Constraints 3 and 4
 */
class N4JSXBindingValidator extends AbstractN4JSDeclarativeValidator {
	@Inject
	protected N4JSTypeSystem ts;
	@Inject
	protected TypeSystemHelper tsh
	@Inject
	ReactLookupHelper reactLookupHelper;

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
		val expr = jsxElem.jsxElementName.expression;
		val G = expr.newRuleEnvironment;
		val exprResult = ts.type(G, expr);
		if (exprResult.failed)
			return

		val TypeRef exprTypeRef = exprResult.value
		var isFunction = exprTypeRef instanceof FunctionTypeExprOrRef;
		var isClass = exprTypeRef instanceof TypeTypeRef && (exprTypeRef as TypeTypeRef).constructorRef;

		if (!isFunction && !isClass) {
			val message = IssueCodes.getMessageForREACT_ELEMENT_NOT_FUNCTION_OR_CLASS_ERROR(exprTypeRef.toString);
			addIssue(message, expr, IssueCodes.REACT_ELEMENT_NOT_FUNCTION_OR_CLASS_ERROR);
			return
		}

		if (exprTypeRef instanceof FunctionTypeExprOrRef) {
			// Check if the function conforms to React functional component, i.e. its return type is Element
			val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE;
			val elementClassTypeRef = reactLookupHelper.lookUpReactClassifier(jsxElem, reference, "Element", "react");
			if (elementClassTypeRef === null)
				return;

			val result = ts.subtype(G, exprTypeRef.returnTypeRef, TypeUtils.createTypeRef(elementClassTypeRef));
			if (result.failed) {
				val message = IssueCodes.getMessageForREACT_ELEMENT_FUNCTION_NOT_REACT_ELEMENT_ERROR(exprTypeRef.returnTypeRef.toString);
				addIssue(message, expr, IssueCodes.REACT_ELEMENT_FUNCTION_NOT_REACT_ELEMENT_ERROR);
			}
		}

		if (exprTypeRef instanceof TypeTypeRef) {
			if (!exprTypeRef.constructorRef) {
				return;
			}			
			// Check if the class is a valid React component, i.e. extends React.Component
			val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
			val componentClassTypeRef = reactLookupHelper.lookUpReactClassifier(jsxElem, reference, "Component",
				"react")
			if (componentClassTypeRef === null)
				return
				
			val tclass = tsh.getStaticType(G, exprTypeRef); 
			val tclassTypeRef = TypeUtils.createTypeRef(tclass);

			val resultSubType = ts.subtype(G, tclassTypeRef, TypeUtils.createTypeRef(componentClassTypeRef))
			if (resultSubType.failed) {
				val message = IssueCodes.getMessageForREACT_ELEMENT_CLASS_NOT_REACT_ELEMENT_ERROR();
				addIssue(message, expr, IssueCodes.REACT_ELEMENT_CLASS_NOT_REACT_ELEMENT_ERROR);
			}
		}
	}
}
