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
package eu.numberfour.n4js.validation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;

import eu.numberfour.n4js.n4JS.Block;
import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.ExpressionStatement;
import eu.numberfour.n4js.n4JS.FunctionDefinition;
import eu.numberfour.n4js.n4JS.Script;
import eu.numberfour.n4js.n4JS.StringLiteral;
import eu.numberfour.n4js.utils.ResourceType;

/**
 *
 */
public class JavaScriptVariantHelper {

	enum JavaScriptVariant {

		/** Default non-strict JavaScript variant */
		unrestricted,
		/** Strict mode */
		strict,
		/** N4JS mode */
		n4js,
		/** external mode */
		external;

		/**
		 * Literal value to indicate strict mode: "use strict" (or 'use strict')
		 */
		public final static String STRICT_MODE_LITERAL_VALUE = "use strict";

		/**
		 * Returns the variant at the given code element.
		 */
		public static JavaScriptVariant getVariant(EObject eobj) {
			ResourceType resourceType = ResourceType.getResourceType(eobj);

			if (ResourceType.N4JS.equals(resourceType)) {
				return n4js;
			}
			if (isContainedInStrictFunctionOrScript(eobj)) {
				return strict;
			}
			if (ResourceType.N4JSD.equals(resourceType)) {
				return external;
			}
			return unrestricted;
		}

		/**
		 * Returns true, if the variant is active for the given model element. Note that the variants are mutually
		 * exclusive and that there is a variant precedence defined.
		 */
		public boolean isActive(EObject eobj) {
			return getVariant(eobj) == this;
		}

		private static boolean isContainedInStrictFunctionOrScript(EObject eobj) {
			if (eobj == null) {
				return false;
			}
			FunctionDefinition functionDef = EcoreUtil2.getContainerOfType(eobj, FunctionDefinition.class);
			if (functionDef != null) {
				Block block = functionDef.getBody();
				if (block != null && startsWithStrictMode(block.getStatements())) {
					return true;
				}
				return isContainedInStrictFunctionOrScript(functionDef.eContainer());
			}
			Script script = EcoreUtil2.getContainerOfType(eobj, Script.class);
			if (script != null) {
				if (startsWithStrictMode(script.getScriptElements())) {
					return true;
				}
			}
			return false;
		}

		private static boolean startsWithStrictMode(EList<? extends EObject> eList) {
			if (eList == null || eList.isEmpty()) {
				return false;
			}
			EObject first = eList.get(0);
			if (first instanceof ExpressionStatement) {
				Expression expr = ((ExpressionStatement) first).getExpression();
				if (expr instanceof StringLiteral) {
					boolean equalsStrictLiteral = STRICT_MODE_LITERAL_VALUE.equals(((StringLiteral) expr).getValue());
					return equalsStrictLiteral;
				}
			}
			return false;
		}

		/**
		 * Returns true, if current variant is either unrestricted or strict, that is, if no N4JS mode is active.
		 */
		public boolean isECMAScript() {
			return this == strict || this == unrestricted;
		}
	}

	/**
	 * Return true if dynamic pseudo scope should be activated
	 */
	public boolean activateDynamicPseudoScope(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if missing implementation is allowed, for instance in external mode
	 */
	public boolean allowMissingImplementation(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.external;
	}

	/***
	 * Return true if override annotation should be checked, e.g. if mode is N4JS
	 */
	public boolean checkOverrideAnnotation(EObject eobj) {
		return !(JavaScriptVariant.getVariant(eobj).isECMAScript());
	}

	/***
	 * Return true if type declaration should be checked, e.g. if the mode is N4JS
	 */
	public boolean checkTypeDeclaration(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/***
	 * Return true if type declaration should be checked, e.g. if the mode is N4JS
	 */
	public boolean checkMemberDeclaration(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/***
	 * Return true if variable declaration should be checked, e.g. if the mode is N4JS
	 */
	public boolean checkVariable(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/***
	 * Return true if method reference should be checked
	 */
	public boolean checkMethodReference(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/***
	 * Return true if call expression should be checked
	 */
	public boolean checkCallExpression(EObject eobj) {
		return !JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/***
	 * Return true if new expression should be checked
	 */
	public boolean requireCheckNewExpression(EObject eobj) {
		return !JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/***
	 * Return true if indexed access expression should be checked, only in N4JS mode
	 */
	public boolean requireCheckIndexedAccessExpression(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/***
	 * Return true if function name should be checked
	 */
	public boolean requireCheckFunctionName(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/***
	 * Return true if function return should be checked
	 */
	public boolean requireCheckFunctionReturn(EObject eobj) {
		return !JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/***
	 * Return true if function expression in expression statement should be checked
	 */
	public boolean requireCheckFunctionExpressionInExpressionStatement(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/***
	 * Return true if a constant declaration has an initializer
	 */
	public boolean constantHasInitializer(EObject eobj) {
		return !JavaScriptVariant.external.isActive(eobj);
	}

	/***
	 * Return true if it should be check that no N4JS in runtime environment or lib
	 */
	public boolean requirecheckNoN4jsInRuntimeEnvOrLib(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/***
	 * Return true if it should be check that no N4JS in runtime environment or lib
	 */
	public boolean allowWrongReadWrite(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/***
	 * Return true if type inference should be doomed
	 */
	public boolean doomTypeInference(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/***
	 * Return true if annotation should be allowed
	 */
	public boolean allowAnnotation(EObject eobj) {
		JavaScriptVariant currentVariant = JavaScriptVariant.getVariant(eobj);
		return (currentVariant == JavaScriptVariant.n4js || currentVariant == JavaScriptVariant.external);
	}

	/***
	 * Return true if it must be checked that a final field is initialized
	 */
	public boolean requireCheckFinalFieldIsInitialized(EObject eobj) {
		return !JavaScriptVariant.external.isActive(eobj);
	}

	/***
	 * Return true if it must be checked if a name starts with dollar
	 */
	public boolean requireCheckNameStartsWithDollar(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/***
	 * Return true if it is required to check if body of a member is missing
	 */
	public boolean requireCheckForMissingBody(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) != JavaScriptVariant.external;
	}

	/***
	 * Return true if it is required to check type matches
	 */
	public boolean requireCheckTypeMatchesExpectedType(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) != JavaScriptVariant.unrestricted;
	}

	/**
	 * Enforce dynamic types in call cases even without explicit modifier. This is usually the case for plain ECMAScript
	 */
	public boolean enforceDynamicTypes(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Returns true if the variant is type aware
	 */
	public boolean isTypeAware(EObject eobj) { // e.g. in N4JS
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Returns true if the variant has global object
	 */
	public boolean hasGlobalObject(EObject eobj) { // e.g. in unrestricted ECMAScript mode
		return JavaScriptVariant.unrestricted.isActive(eobj);
	}

}
