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
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.typeRefs.TypeTypeRef
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TypingStrategy
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4jsx.helpers.ReactHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute
import eu.numberfour.n4jsx.n4JSX.N4JSXPackage
import java.util.Arrays
import java.util.List
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * Validation of React bindings including naming convention (components in upper case and HTML tags in lower case) 
 */
class N4JSXReactBindingValidator extends AbstractN4JSDeclarativeValidator {
	@Inject private N4JSTypeSystem ts;
	@Inject private TypeSystemHelper tsh
	@Inject private extension ReactHelper reactHelper;

	private static final List<String> htmlTags = Arrays.asList(
		"a",
		"abbr",
		"address",
		"area",
		"button",
		"div",
		"li",
		"ol"
	)

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
	 * This method checks if JSXElement binds to a valid React component function or class React component declaration
	 */
	@Check
	def public void checkReactElementBinding(JSXElement jsxElem) {
		val expr = jsxElem.jsxElementName.expression;
		val TypeRef exprTypeRef = reactHelper.getJSXElementBindingType(jsxElem);
		var isFunction = exprTypeRef instanceof FunctionTypeExprOrRef;
		var isClass = exprTypeRef instanceof TypeTypeRef && (exprTypeRef as TypeTypeRef).constructorRef;

		if (!isFunction && !isClass) {
			val String refName = expr.refName
			if ((refName !== null) && Character::isLowerCase(refName.charAt(0))) {
				// If the JSX element name starts with lower case, warning if it is unknown HTML tag
				if (!htmlTags.contains(refName)) {
					val message = IssueCodes.getMessageForHTMLTAG_UNKNOWN(refName);
					addIssue(
						message,
						jsxElem,
						N4JSXPackage.Literals.JSX_ELEMENT__JSX_ELEMENT_NAME,
						IssueCodes.HTMLTAG_UNKNOWN
					);
				}
			} else {
				// JSX element name starts with an upper case, error because it does not bind to a class or function 
				val message = IssueCodes.
					getMessageForREACT_ELEMENT_NOT_FUNCTION_OR_CLASS_ERROR(exprTypeRef.typeRefAsString);
				addIssue(message, expr, IssueCodes.REACT_ELEMENT_NOT_FUNCTION_OR_CLASS_ERROR);
			}
			return;
		}

		if (isFunction) {
			checkFunctionTypeExprOrRef(jsxElem, exprTypeRef as FunctionTypeExprOrRef);
			checkReactComponentShouldStartWithUppercase(jsxElem, true);
		}

		if (isClass) {
			checkTypeTypeRefConstructor(jsxElem, exprTypeRef as TypeTypeRef);
			checkReactComponentShouldStartWithUppercase(jsxElem, false);
		}

		// Furthermore, check that all non-optional fields of the properties type are used
		checkAllNonOptionalFieldsAreSpecified(jsxElem, exprTypeRef);
	}

	/**
	 * Check that a React component should start with an upper case 
	 */
	def private void checkReactComponentShouldStartWithUppercase(JSXElement jsxElem, boolean isFunctionalComponent) {
		val expr = jsxElem.jsxElementName.expression;
		val String refName = expr.refName
		if ((refName !== null) && (!refName.isEmpty) && Character::isLowerCase(refName.charAt(0))) {
			if (isFunctionalComponent) {
				val message = IssueCodes.getMessageForREACT_FUNCTIONAL_COMPONENT_CANNOT_START_WITH_LOWER_CASE(refName);
				addIssue(
					message,
					jsxElem,
					N4JSXPackage.Literals.JSX_ELEMENT__JSX_ELEMENT_NAME,
					IssueCodes.REACT_FUNCTIONAL_COMPONENT_CANNOT_START_WITH_LOWER_CASE
				);
			} else {
				val message = IssueCodes.getMessageForREACT_CLASS_COMPONENT_CANNOT_START_WITH_LOWER_CASE(refName);
				addIssue(
					message,
					jsxElem,
					N4JSXPackage.Literals.JSX_ELEMENT__JSX_ELEMENT_NAME,
					IssueCodes.REACT_CLASS_COMPONENT_CANNOT_START_WITH_LOWER_CASE
				);
			}
		}
	}

	/**
	 * The JSX element binds to a function or function expression, check that the return type is a subtype of React.Element 
	 */
	def private void checkFunctionTypeExprOrRef(JSXElement jsxElem, FunctionTypeExprOrRef exprTypeRef) {
		val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE;
		val elementClassTypeRef = reactHelper.lookUpReactClassifier(jsxElem, reference, ReactHelper.REACT_ELEMENT, ReactHelper.REACT_MODULE);
		if (elementClassTypeRef === null)
			return;

		val expr = jsxElem.jsxElementName.expression;
		val G = expr.newRuleEnvironment;
		val result = ts.subtype(G, exprTypeRef.returnTypeRef, TypeUtils.createTypeRef(elementClassTypeRef));
		if (result.failed) {
			val message = IssueCodes.
				getMessageForREACT_ELEMENT_FUNCTION_NOT_REACT_ELEMENT_ERROR(exprTypeRef.returnTypeRef.typeRefAsString);
			addIssue(
				message,
				expr,
				IssueCodes.REACT_ELEMENT_FUNCTION_NOT_REACT_ELEMENT_ERROR
			);
		}
	}

	/**
	 * The JSX element binds to a class, check that the class type is a subtype of React.Component 
	 */
	def private void checkTypeTypeRefConstructor(JSXElement jsxElem, TypeTypeRef exprTypeRef) {
		val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
		val componentClassTypeRef = reactHelper.lookUpReactClassifier(jsxElem, reference,
			ReactHelper.REACT_COMPONENT, ReactHelper.REACT_MODULE)
		if (componentClassTypeRef === null)
			return

		val expr = jsxElem.jsxElementName.expression;
		val G = expr.newRuleEnvironment;
		val tclass = tsh.getStaticType(G, exprTypeRef);
		val tclassTypeRef = TypeUtils.createTypeRef(tclass);
		val resultSubType = ts.subtype(G, tclassTypeRef, TypeUtils.createTypeRef(componentClassTypeRef))
		if (resultSubType.failed) {
			val message = IssueCodes.getMessageForREACT_ELEMENT_CLASS_NOT_REACT_ELEMENT_ERROR();
			addIssue(message, expr, IssueCodes.REACT_ELEMENT_CLASS_NOT_REACT_ELEMENT_ERROR);
		}
	}

	/**
	 * Check that non-optional fields of "props" should be specified in JSX element
	 */
	def private void checkAllNonOptionalFieldsAreSpecified(JSXElement jsxElem, TypeRef exprTypeRef) {
		val jsxPropertyAttributes = jsxElem.jsxAttributes;
		val properties = jsxPropertyAttributes.map[a|(a as JSXPropertyAttribute).property];
		val propsType = jsxElem.propsType;
		if (propsType === null)
			return;

		val G = jsxElem.newRuleEnvironment;
		val nonOptionalFieldsInPropsType = tsh.structuralTypesHelper.collectStructuralMembers(G, propsType,
			TypingStrategy.STRUCTURAL).filter[m|(m instanceof TField) && !m.isOptional];
		
		val String missingFields = nonOptionalFieldsInPropsType.filter[field|!(properties.contains(field))].map [field |
			field.name
		].join(",")

		if (!missingFields.isEmpty) {
			val message = IssueCodes.getMessageForJSXPROPERTY_ATTRIBUTE_NON_OPTIONAL_PROPERTY_NOT_SPECIFIED(missingFields);
			addIssue(
				message,
				jsxElem,
				N4JSXPackage.Literals.JSX_ELEMENT__JSX_ELEMENT_NAME,
				IssueCodes.JSXPROPERTY_ATTRIBUTE_NON_OPTIONAL_PROPERTY_NOT_SPECIFIED
			);
		}
	}

	/**
	 * Calculate the reference name of an expression, should be used only for expressions within JSX element!
	 */
	def private String getRefName(Expression expr) {
		var String refName = null;
		if (expr instanceof IdentifierRef) {
			refName = expr.idAsText;
		} else if (expr instanceof ParameterizedPropertyAccessExpression) {
			refName = expr.propertyAsText
		}
		return refName;
	}
}
