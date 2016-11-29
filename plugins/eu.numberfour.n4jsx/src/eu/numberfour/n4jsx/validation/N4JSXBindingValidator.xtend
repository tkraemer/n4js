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
import eu.numberfour.n4jsx.helpers.ReactLookupHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute
import eu.numberfour.n4jsx.n4JSX.N4JSXPackage
import it.xsemantics.runtime.RuleEnvironment
import java.util.Arrays
import java.util.List
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.scoping.IScopeProvider
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * Validation of names, cf N4JS Spec, Chapter 3.4., Constraints 3 and 4
 */
class N4JSXBindingValidator extends AbstractN4JSDeclarativeValidator {
	@Inject protected N4JSTypeSystem ts;
	@Inject protected TypeSystemHelper tsh
	@Inject ReactLookupHelper reactLookupHelper;
	@Inject IScopeProvider scopeProvider

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

		val G = expr.newRuleEnvironment;
		val exprResult = ts.type(G, expr);
		if (exprResult.failed)
			return

		val TypeRef exprTypeRef = exprResult.value
		var isFunction = exprTypeRef instanceof FunctionTypeExprOrRef;
		var isClass = exprTypeRef instanceof TypeTypeRef && (exprTypeRef as TypeTypeRef).constructorRef;

		if (!isFunction && !isClass) {
			val String refName = expr.refName

			if ((refName !== null) && Character::isLowerCase(refName.charAt(0))) {
				// If the JSX element name is an identifier and starts with lower case, warning if it is unknown HTML tag
				if (!htmlTags.contains(refName)) {
					val message = IssueCodes.getMessageForHTMLTAG_UNKNOWN(refName);
					addIssue(
						message,
						jsxElem,
						N4JSXPackage.Literals.JSX_ELEMENT__JSX_ELEMENT_NAME,
						IssueCodes.HTMLTAG_UNKNOWN
					);
				} else {
					return;
				}
			} else {
				val message = IssueCodes.
					getMessageForREACT_ELEMENT_NOT_FUNCTION_OR_CLASS_ERROR(exprTypeRef.typeRefAsString);
				addIssue(message, expr, IssueCodes.REACT_ELEMENT_NOT_FUNCTION_OR_CLASS_ERROR);
				return;
			}
		}

		if (exprTypeRef instanceof FunctionTypeExprOrRef) {
			checkFunctionTypeExprOrRef(G, jsxElem, expr, exprTypeRef);
		}

		if (exprTypeRef instanceof TypeTypeRef && (exprTypeRef as TypeTypeRef).constructorRef) {
			checkTypeTypeRefConstructor(G, jsxElem, expr, exprTypeRef as TypeTypeRef);
		}

		// Furthermore, check that all non-optional fields of the properties type are used
		checkAllNonOptionalFieldsAreSpecified(G, jsxElem, exprTypeRef);

	}

	def private void checkAllNonOptionalFieldsAreSpecified(RuleEnvironment G, JSXElement jsxElement,
		TypeRef exprTypeRef) {
		val jsxPropertyAttributes = jsxElement.jsxAttributes;
		val properties = jsxPropertyAttributes.map[a|(a as JSXPropertyAttribute).property];
		val propsType = jsxElement.getPropsType(exprTypeRef);
		if (propsType === null)
			return;

		val nonOptionalFieldsInPropsType = tsh.structuralTypesHelper.collectStructuralMembers(G, propsType,
			TypingStrategy.STRUCTURAL).filter[m|(m instanceof TField) && !m.isOptional];
		// println("fieldsInPropsType = " + fieldsInPropsType);
		nonOptionalFieldsInPropsType.forEach [ member |
			val field = member as TField;
			if (!(properties.contains(field))) {
				val message = IssueCodes.getMessageForJSXPROPERTY_ATTRIBUTE_NON_OPTIONAL_PROPERTY_NOT_SPECIFIED(field.name);
				addIssue(
					message,
					jsxElement,
					N4JSXPackage.Literals.JSX_ELEMENT__JSX_ELEMENT_NAME,
					IssueCodes.JSXPROPERTY_ATTRIBUTE_NON_OPTIONAL_PROPERTY_NOT_SPECIFIED
				);
			}
		]
	}

	def private getPropsType(JSXElement jsxElement, TypeRef exprTypeRef) {
		val RuleEnvironment G = jsxElement.newRuleEnvironment

		if (exprTypeRef instanceof TypeTypeRef && (exprTypeRef as TypeTypeRef).constructorRef) {
			// The JSX name is of type class constructor
			val tclass = tsh.getStaticType(G, exprTypeRef as TypeTypeRef);
			val EReference referenceParameterizedTypeRef = TypeRefsPackage.Literals.
				PARAMETERIZED_TYPE_REF__DECLARED_TYPE;
			val tComponentClassifier = reactLookupHelper.lookUpReactClassifier(jsxElement,
				referenceParameterizedTypeRef, "Component", "react");
			val reactComponentProps = tComponentClassifier.typeVars.get(0);
			tsh.addSubstitutions(G, TypeUtils.createTypeRef(tclass));
			ts.substTypeVariablesInTypeRef(G, TypeUtils.createTypeRef(reactComponentProps));

			val reactComponentPropsTypeRef = G.get(reactComponentProps);
			if (reactComponentPropsTypeRef !== null && (reactComponentPropsTypeRef instanceof TypeRef))
				return reactComponentPropsTypeRef as TypeRef;

		} else if (exprTypeRef instanceof FunctionTypeExprOrRef) {
			if (exprTypeRef.fpars.length > 0) {
				val tPropsParam = exprTypeRef.fpars.get(0);
				return tPropsParam.typeRef
			}
		}
		return null;
	}

	/**
	 * This method implementation the validation case when JSX element refers to a function type expression or function ref
	 */
	def private void checkFunctionTypeExprOrRef(RuleEnvironment G, JSXElement jsxElem, Expression expr,
		FunctionTypeExprOrRef exprTypeRef) {
		// If the JSX element refers to a function via name, the name must not be lower case
		val String refName = expr.refName

		if ((refName !== null) && (!refName.isEmpty) && Character::isLowerCase(refName.charAt(0))) {
			val message = IssueCodes.getMessageForREACT_FUNCTIONAL_COMPONENT_CANNOT_START_WITH_LOWER_CASE(refName);
			addIssue(
				message,
				jsxElem,
				N4JSXPackage.Literals.JSX_ELEMENT__JSX_ELEMENT_NAME,
				IssueCodes.REACT_FUNCTIONAL_COMPONENT_CANNOT_START_WITH_LOWER_CASE
			);
		}

		// Check if the function conforms to React functional component, i.e. its return type is Element
		val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE;
		val elementClassTypeRef = reactLookupHelper.lookUpReactClassifier(jsxElem, reference, "Element", "react");
		if (elementClassTypeRef === null)
			return;

		val result = ts.subtype(G, exprTypeRef.returnTypeRef, TypeUtils.createTypeRef(elementClassTypeRef));
		if (result.failed) {
			val message = IssueCodes.
				getMessageForREACT_ELEMENT_FUNCTION_NOT_REACT_ELEMENT_ERROR(exprTypeRef.returnTypeRef.typeRefAsString);
			addIssue(message, expr, IssueCodes.REACT_ELEMENT_FUNCTION_NOT_REACT_ELEMENT_ERROR);
		}
	}

	/**
	 * This method implementation the validation case when JSX element refers to a class
	 */
	def private void checkTypeTypeRefConstructor(RuleEnvironment G, JSXElement jsxElem, Expression expr,
		TypeTypeRef exprTypeRef) {
		// If the JSX element refers to a class via name, the name must not be lower case 	
		val String refName = expr.refName

		if ((refName !== null) && (!refName.isEmpty) && Character::isLowerCase(refName.charAt(0))) {
			val message = IssueCodes.getMessageForREACT_CLASS_COMPONENT_CANNOT_START_WITH_LOWER_CASE(refName);
			addIssue(
				message,
				jsxElem,
				N4JSXPackage.Literals.JSX_ELEMENT__JSX_ELEMENT_NAME,
				IssueCodes.REACT_CLASS_COMPONENT_CANNOT_START_WITH_LOWER_CASE
			);
		}

		// Check if the class is a valid React component, i.e. extends React.Component
		val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
		val componentClassTypeRef = reactLookupHelper.lookUpReactClassifier(jsxElem, reference, "Component", "react")
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
