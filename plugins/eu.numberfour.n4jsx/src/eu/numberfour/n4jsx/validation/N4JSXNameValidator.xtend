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
import eu.numberfour.n4js.n4JS.FunctionDeclaration
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.N4TypeDeclaration
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeTypeRef
import eu.numberfour.n4js.ts.types.IdentifiableElement
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.validation.validators.N4JSNameValidator
import eu.numberfour.n4jsx.helpers.ReactLookupHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import eu.numberfour.n4jsx.n4JSX.JSXElementName
import eu.numberfour.n4jsx.n4JSX.N4JSXPackage
import java.util.Arrays
import java.util.List
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * Validation of React functional component names
 */
class N4JSXNameValidator extends N4JSNameValidator {

	@Inject
	protected N4JSTypeSystem ts;

	@Inject
	private ReactLookupHelper reactHelper;

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

	@Check
	def void checkValidFunctionalComponent(JSXElement jsxElement) {
		if (jsxElement.jsxElementName !== null) {
			val JSXElementName jsxElementName = jsxElement.jsxElementName
			// TODO: Handle property access case
			if (!(jsxElementName.expression instanceof IdentifierRef))
				return;
			val IdentifierRef idRef = jsxElementName.expression as IdentifierRef;
			if (idRef.idAsText !== null && idRef.idAsText.length > 0) {
				if (Character::isUpperCase(idRef.idAsText.charAt(0))) {
					// Check if it binds to a valid React component
					if (idRef.id === null)
						return
					var id = idRef.id;
					id = EcoreUtil2.resolve(id, jsxElement.eResource) as IdentifiableElement;
					
					var isFunction = true
					var isClass = true
					if (id.eIsProxy) {
						isFunction = false
						isClass = false
					}  else {
						val G = idRef.id.newRuleEnvironment
						val r = ts.type(G, id)
						val tr = r.value

						if (tr === null)
							return;

						isFunction = tr instanceof FunctionTypeExpression || tr instanceof FunctionTypeRef;
						isClass = tr instanceof TypeTypeRef && (tr as TypeTypeRef).constructorRef;
						
					} 
					
					if (!(isFunction || isClass)) {
						val message = IssueCodes.getMessageForJSXELEMENT_NOT_BIND_TO_REACT_COMPONENT(idRef.idAsText);
						addIssue(
							message,
							jsxElement,
							N4JSXPackage.Literals.JSX_ELEMENT__JSX_ELEMENT_NAME,
							IssueCodes.JSXELEMENT_NOT_BIND_TO_REACT_COMPONENT
						);
					}
				} else {
					if (!htmlTags.contains(idRef.idAsText)) {
						val message = IssueCodes.getMessageForHTMLTAG_UNKNOWN(idRef.idAsText);
						addIssue(
							message,
							jsxElement,
							N4JSXPackage.Literals.JSX_ELEMENT__JSX_ELEMENT_NAME,
							IssueCodes.HTMLTAG_UNKNOWN
						);
					}

				}
			}
		}
	}

	//@Check
	override void checkN4TypeDeclaration(N4TypeDeclaration n4TypeDeclaration) {
		super.checkN4TypeDeclaration(n4TypeDeclaration)
		if (n4TypeDeclaration instanceof N4ClassDeclaration) {
			n4TypeDeclaration.checkReactClassComponentBinding
		}
	}

	/**
	 * This method checks React class component should not start with lower case
	 */
	private def void checkReactClassComponentBinding(N4ClassDeclaration classDecl) {
		if (reactHelper.isClassDeclarationAReactComponent(classDecl)) {
			if (classDecl.name !== null && classDecl.name.length > 0 &&
				Character::isLowerCase(classDecl.name.charAt(0))) {
				val message = IssueCodes.
					getMessageForREACT_CLASS_COMPONENT_CANNOT_START_WITH_LOWER_CASE(classDecl.name);
				addIssue(
					message,
					classDecl,
					N4JSPackage.Literals.N4_TYPE_DECLARATION__NAME,
					IssueCodes.REACT_CLASS_COMPONENT_CANNOT_START_WITH_LOWER_CASE
				);
			}
		}
	}

	/**
	 * This method checks React functional component should not start with lower case
	 */
	//@Check
	def void checkReactFunctionalComponentBinding(FunctionDeclaration funcDecl) {
		if (reactHelper.isFunctionDeclarationAReactComponent(funcDecl)) {
			if (funcDecl.name !== null && funcDecl.name.length > 0 && Character::isLowerCase(funcDecl.name.charAt(0))) {
				val message = IssueCodes.
					getMessageForREACT_FUNCTIONAL_COMPONENT_CANNOT_START_WITH_LOWER_CASE(funcDecl.name);
				addIssue(
					message,
					funcDecl,
					N4JSPackage.Literals.FUNCTION_DECLARATION__NAME,
					IssueCodes.REACT_FUNCTIONAL_COMPONENT_CANNOT_START_WITH_LOWER_CASE
				);
			}
		}
	}
}
