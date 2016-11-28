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
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeRef
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.typeRefs.TypeTypeRef
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4jsx.helpers.ReactLookupHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef

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
		val elemName = jsxElem.getJsxElementName();
		val expr = elemName.getExpression();

//		//TODO: Handle property access case
//		if (!(expr instanceof IdentifierRef)) {
//			return;
//		}
//
//		val idRef = expr as IdentifierRef;
//		var ie = idRef.getId();
//
//		if (expr instanceof ParameterizedPropertyAccessExpression) {
//			val ppae = expr;
//			ie = ppae.getProperty();
//		}
//
//		if (ie !== null && ie.eContainer() !== null) {
			val G = expr.newRuleEnvironment 
			val exprRes = ts.type(G, expr)
			val tr = exprRes.value
			
			if (tr === null)
				return
						
			var classOrFunction = false;
			// hint: obtain the built-in type "Function" via G
			var isFunction = tr instanceof FunctionTypeExprOrRef; // <- replaec with subtype check tr <: Function
			var isClass = tr instanceof TypeTypeRef && (tr as TypeTypeRef).constructorRef;
			
			classOrFunction = isFunction || isClass;
//			val name = ie.getName();
val name = ""; // remove this
			if (!classOrFunction) {
				val message = IssueCodes.getMessageForREACT_ELEMENT_NOT_FUNCTION_OR_CLASS_ERROR(name);
				addIssue(message, expr, IssueCodes.REACT_ELEMENT_NOT_FUNCTION_OR_CLASS_ERROR);
				return
			}

			if (isFunction) {
				// Check if the function conforms to React functional component, i.e. its return type is Element
				val tfunction = tr.declaredType as TFunction
				
				if (tfunction.returnTypeRef instanceof ParameterizedTypeRef) {
					val typeRef = tfunction.returnTypeRef as ParameterizedTypeRef;

					//val G = RuleEnvironmentExtensions.newRuleEnvironment(jsxElem);
					val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE;
					val elementClassTypeRef = reactLookupHelper.lookUpReactClassifier(jsxElem, reference, "Element", "react");
					if (elementClassTypeRef === null)
						return;

					val result = ts.subtype(G, typeRef, TypeUtils.createTypeRef(elementClassTypeRef));
					if (result.failed) {
						val message = IssueCodes.getMessageForREACT_ELEMENT_FUNCTION_NOT_REACT_ELEMENT_ERROR(name);
						addIssue(message, expr, IssueCodes.REACT_ELEMENT_FUNCTION_NOT_REACT_ELEMENT_ERROR);
					}
				}
			}

			if (isClass) {
				// Check if the class is a valid React component, i.e. extends React.Component
				val tclass = tsh.getStaticType(G, tr as TypeTypeRef); //TODO nach oben verschieben
				val tclassTypeRef = TypeUtils.createTypeRef(tclass);

				val G2 = RuleEnvironmentExtensions.newRuleEnvironment(jsxElem);
				val EReference reference = TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
				val componentClassTypeRef = reactLookupHelper.lookUpReactClassifier(jsxElem, reference, "Component", "react")
				if (componentClassTypeRef === null)
					return

				val result = ts.subtype(G, tclassTypeRef, TypeUtils.createTypeRef(componentClassTypeRef))
				if (result.value === null || !result.value) {
					val message = IssueCodes.getMessageForREACT_ELEMENT_CLASS_NOT_REACT_ELEMENT_ERROR(name);
					addIssue(message, expr, IssueCodes.REACT_ELEMENT_CLASS_NOT_REACT_ELEMENT_ERROR);
				}
			}
//		}
	}
	
}
