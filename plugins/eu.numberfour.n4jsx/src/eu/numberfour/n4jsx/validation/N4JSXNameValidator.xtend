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
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.N4TypeDeclaration
import eu.numberfour.n4js.validation.validators.N4JSNameValidator
import eu.numberfour.n4jsx.helpers.ReactLookupHelper
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

/**
 * Validation of React functional component names
 */
class N4JSXNameValidator extends N4JSNameValidator {
	
	@Inject
	private ReactLookupHelper reactHelper;
	
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
	override void checkN4TypeDeclaration(N4TypeDeclaration n4TypeDeclaration) {
		super.checkN4TypeDeclaration(n4TypeDeclaration)
		if (n4TypeDeclaration instanceof N4ClassDeclaration) {
			n4TypeDeclaration.checkReactClassComponentBinding
		}
	}
	
	/**
	 * This method checks React class component naming convention
	 */
	private def void checkReactClassComponentBinding(N4ClassDeclaration classDecl) {
		if (reactHelper.isClassDeclarationAReactComponent(classDecl)) {
			if (classDecl.name !== null && classDecl.name.length > 0 && Character::isLowerCase(classDecl.name.charAt(0))) {
				val message = IssueCodes.getMessageForREACT_CLASS_COMPONENT_CANNOT_START_WITH_LOWER_CASE(classDecl.name);
				addIssue(message, 
					classDecl,
					N4JSPackage.Literals.N4_TYPE_DECLARATION__NAME,
					IssueCodes.REACT_CLASS_COMPONENT_CANNOT_START_WITH_LOWER_CASE
				);
			}
		}
	}

	/**
	 * This method checks React functional component naming convention
	 */
	@Check
	def void checkReactFunctionalComponentBinding(FunctionDeclaration funcDecl) {
		if (reactHelper.isFunctionDeclarationAReactComponent(funcDecl)) {
			if (funcDecl.name !== null && funcDecl.name.length > 0 && Character::isLowerCase(funcDecl.name.charAt(0))) {
				val message = IssueCodes.getMessageForREACT_FUNCTIONAL_COMPONENT_CANNOT_START_WITH_LOWER_CASE(funcDecl.name);
				addIssue(message, 
					funcDecl, 
					N4JSPackage.Literals.FUNCTION_DECLARATION__NAME, 
					IssueCodes.REACT_FUNCTIONAL_COMPONENT_CANNOT_START_WITH_LOWER_CASE
				);
			}
		}
	}	
}
