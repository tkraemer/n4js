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

import com.google.common.collect.Lists
import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.typeRefs.TypeTypeRef
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TypingStrategy
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4jsx.helpers.ReactHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute
import eu.numberfour.n4jsx.n4JSX.JSXSpreadAttribute
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

	// Source: http://www.w3schools.com/tags/
	private static final List<String> htmlTags = Arrays.asList(
		"a","abbr","address","area","article","aside","audio",
		"b","base","bdi","bdo","blockquote","body","br","button",
		"canvas","caption","cite","code","col","colgroup",
		"datalist","dd","del","details","dfn","dialog","div","dl","dt",
		"em","embed","fieldset",
		"figcaption","figure","footer","form","h1","h2","h3","h4","h5","h6","head","header","hr","html",
		"i","iframe","img","input","ins",
		"kbd","keygen",
		"label","legend","li","link",
		"main","map","mark","menu","menuitem","meta","meter",
		"nav","noscript",
		"object","ol","optgroup","option",
		"p","param","pre","progress",
		"q","rp","rt","ruby",
		"s","samp",
		"script","section","select",
		"small","source","span","strong","style","sub","summary","sup",
		"table","tbody","td","textarea","tfoot","th","thead","time","title","tr","track",
		"u","ul",
		"var","video",
		"wbr"
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
	 * This method checks that JSXElement bind to a valid React component function or class React component declaration
	 * See Req. 241103
	 */
	@Check
	def public void checkOpeningClosingElementMismatch(JSXElement jsxElem) {
		val openingName = jsxElem?.jsxElementName?.expression?.refName;
		val closingName = jsxElem?.jsxClosingName?.expression?.refName;

		if ((jsxElem.jsxClosingName !== null) && !(openingName == closingName)) {
			// Only check if the closing element exists, e.g. not null
			val message = IssueCodes.
				getMessageForJSXELEMENT_OPENING_CLOSING_ELEMENT_NOT_MATCH(openingName, closingName);
			addIssue(
				message,
				jsxElem,
				N4JSXPackage.Literals.JSX_ELEMENT__JSX_CLOSING_NAME,
				IssueCodes.JSXELEMENT_OPENING_CLOSING_ELEMENT_NOT_MATCH
			);
		}
	}

	/**
	 * This method checks that JSXElement bind to a valid React component function or class React component declaration
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
				// See Req. IDE-241118 
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
				// See Req. IDE-241115 
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
	 * Check that a React function/class component should start with an upper case
	 * See Req. 241101 
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
	 * See Req. IDE-241116 
	 */
	def private void checkFunctionTypeExprOrRef(JSXElement jsxElem, FunctionTypeExprOrRef exprTypeRef) {
		val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE;
		val elementClassTypeRef = reactHelper.lookUpReactClassifier(jsxElem, reference, ReactHelper.REACT_ELEMENT,
			ReactHelper.REACT_MODULE);
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
	 * See Req. IDE-241116 
	 */
	def private void checkTypeTypeRefConstructor(JSXElement jsxElem, TypeTypeRef exprTypeRef) {
		val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
		val componentClassTypeRef = reactHelper.lookUpReactClassifier(jsxElem, reference, ReactHelper.REACT_COMPONENT,
			ReactHelper.REACT_MODULE)
		if (componentClassTypeRef === null)
			return;

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
	 * Check the type conformity of types of spread operator's attributes against "props" types
	 * See Req. IDE-241119
	 */
	@Check
	def public void checkTypeConformityOFJSXSpreadAttribute(JSXSpreadAttribute spreadAttribute) {
		val expr = spreadAttribute.expression;
		val propsType = (spreadAttribute.eContainer as JSXElement).propsType
		if (propsType === null) 
			return;
		
		val G = spreadAttribute.newRuleEnvironment
		// Retrieve fields or getters in props type
		val fieldsOrGettersInProps = tsh.structuralTypesHelper.collectStructuralMembers(G, propsType,
			TypingStrategy.STRUCTURAL).filter[m | (m instanceof TField) || (m instanceof TGetter)];

		val exprTypeResult = ts.type(G, expr);
		if (exprTypeResult.failed)
			return;
		// Retrieve attributes (either field or getter) in spread operator type	
		val attributessInSpreadOperatorType = tsh.structuralTypesHelper.collectStructuralMembers(G, exprTypeResult.value,
				TypingStrategy.STRUCTURAL).filter[m | (m instanceof TField) || (m instanceof TGetter)];

		// Type check each attribute in spreader operator against the corresponding props type's field/getter
		attributessInSpreadOperatorType.forEach [ attributeInSpreadOperator |
			val attributeInSpreadOperatorTypeRef = attributeInSpreadOperator.typeRefOfFieldOrGetter;
			val fieldOrGetterInProps = fieldsOrGettersInProps.findFirst[fieldOrGetter | attributeInSpreadOperator.name == fieldOrGetter.name];
			
			if (fieldOrGetterInProps !== null) {
				val fieldOrGetterInPropsTypeRef = fieldOrGetterInProps.typeRefOfFieldOrGetter;
				val result = ts.subtype(G, attributeInSpreadOperatorTypeRef, fieldOrGetterInPropsTypeRef);
				if (result.failed) {
					val message = IssueCodes.getMessageForJSXSPREADATTRIBUTE_WRONG_SUBTYPE(attributeInSpreadOperator.name,
						attributeInSpreadOperatorTypeRef.typeRefAsString, fieldOrGetterInPropsTypeRef.typeRefAsString);
					addIssue(
						message,
						spreadAttribute,
						N4JSXPackage.Literals.JSX_SPREAD_ATTRIBUTE__EXPRESSION,
						IssueCodes.JSXSPREADATTRIBUTE_WRONG_SUBTYPE
					);
				}
			}
		];
	}
	
	/**
	 * Returns the type of a field or return type of a getter
	 */
	def private typeRefOfFieldOrGetter(TMember member) {
		if (member instanceof TField) {
			return member.typeRef;
		} else if (member instanceof TGetter) {
			return member.declaredTypeRef;
		} else {
			throw new IllegalArgumentException(member + " must be either a TField or TGetter");
		}
	}

	/**
	 * Check that non-optional fields of "props" should be specified in JSX element
	 * See Req. IDE-241117
	 */
	def private void checkAllNonOptionalFieldsAreSpecified(JSXElement jsxElem, TypeRef exprTypeRef) {
		val jsxPropertyAttributes = jsxElem.jsxAttributes;
		// First, collect all normal properties in JSX element 
		val allAttributesInJSXElement = Lists.newArrayList(jsxPropertyAttributes.filter(typeof(JSXPropertyAttribute)).map[a | a.property]);
		val propsType = jsxElem.propsType;
		if (propsType === null)
			return;

		val G = jsxElem.newRuleEnvironment;
		// Then collect attributes in spread operators
		val attributesInSpreadOperator = Lists.newArrayList(jsxPropertyAttributes.filter(typeof(JSXSpreadAttribute)).map [ spreadAttribute |
			val exprTypeRefResult = ts.type(G, spreadAttribute.expression);
			if (!exprTypeRefResult.failed) {
				return tsh.structuralTypesHelper.collectStructuralMembers(G, exprTypeRefResult.value, TypingStrategy.STRUCTURAL).filter [ m |
					(m instanceof TField) || (m instanceof TGetter)
				]
			} else {
				Lists.newArrayList
			}
		]).flatten;
		allAttributesInJSXElement.addAll(attributesInSpreadOperator)
		
		// Retrieve all non-optional fields or getters in "props" type
		val nonOptionalFieldsOrGettersInProps = 
				tsh.structuralTypesHelper.collectStructuralMembers(G, propsType, TypingStrategy.STRUCTURAL).filter[m | 
					(m instanceof TField || m instanceof TGetter) && !m.isOptional
				];
		//Calculate the set of unspecified non-optional properties 	
		val String missingFieldsStringRep = nonOptionalFieldsOrGettersInProps.filter [ fieldOrGetter |
			!(allAttributesInJSXElement.exists[attribute | attribute.name == fieldOrGetter.name])
		].map [ fieldOrGetter |	fieldOrGetter.name ].join(",");

		if (!missingFieldsStringRep.isEmpty) {
			val message = IssueCodes.
				getMessageForJSXPROPERTY_ATTRIBUTE_NON_OPTIONAL_PROPERTY_NOT_SPECIFIED(missingFieldsStringRep);
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
