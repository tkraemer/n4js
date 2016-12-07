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

import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;

import com.google.inject.Singleton;

import eu.numberfour.n4js.validation.DefaultJavaScriptVariantHelper;

/**
 * This class provides the constraints to be checked for N4JSX and JSX sub-languages. If the files do not end with
 * .n4jsx or .jsx, the behavior the same as N4JS's default behavior defined in {@link DefaultJavaScriptVariantHelper}
 */
@Singleton
public class N4JSXJavaScriptVariantHelper extends DefaultJavaScriptVariantHelper {

	private enum ValidationFeature {
		/**
		 * Dynamic pseudo scope should be activated?
		 */
		DYNAMIC_PSEUDO_SCOPE,
		/**
		 * Missing implementation is allowed?
		 */
		ALLOW_MISSING_IMPLEMENTATION,
		/**
		 * Override annotation should be checked?
		 */
		CHECK_OVERRIDE_ANNOTATION,
		/**
		 * Type declaration should be checked?
		 */
		CHECK_TYPE_DECLARATION,
		/**
		 * Member declaration should be checked?
		 */
		CHECK_MEMBER_DECLARATION,
		/**
		 * Variable should be checked?
		 */
		CHECK_VARIABLE,
		/**
		 * Method reference should be checked?
		 */
		CHECK_METHOD_REFERENCE,
		/**
		 * Call expression should be checked?
		 */
		CHECK_CALL_EXPRESSION,
		/**
		 * New expression should be checked?
		 */
		CHECK_NEW_EXPRESSION,
		/**
		 * Indexed access expression should be checked?
		 */
		CHECK_INDEXED_ACCESS_EXPRESSION,
		/**
		 * Function name should be checked?
		 */
		CHECK_FUNCTION_NAME,
		/**
		 * Function return should be checked?
		 */
		CHECK_FUNCTION_RETURN,
		/**
		 * Function expression in expression statement should be checked?
		 */
		CHECK_FUNCTION_EXPRESSION_IN_EXRESSION_STATEMENTMEMBER_DECLARATION,
		/**
		 * Constant has initializer?
		 */
		CONSTANT_HAS_INITIALIZER,
		/**
		 * No N4JS in runtime or lib should be checked?
		 */
		CHECK_NO_N4JS_IN_RUNTIME_ENV_OR_LIB,
		/**
		 * Wrong read/write should be allowed?
		 */
		ALLOW_WRONG_READ_WRITE,
		/**
		 * Type inference should be doomed
		 */
		DOOM_TYPE_INTERFENCE,
		/**
		 * Annotation should be allowed
		 */
		ALLOW_ANNOTATION,
		/**
		 * Final fields has initializer?
		 */
		CHECK_FINAL_FIELDS_IS_INITIALIZED,
		/**
		 * Name start with dollar should be checked?
		 */
		CHECK_NAME_START_WITH_DOLLAR,
		/**
		 * Missing body should be checked?
		 */
		CHECK_MISSING_BODY,
		/**
		 * Type matches should be checked?
		 */
		CHECK_TYPE_MATCHES_EXPECTED_TYPE,
		/**
		 * Dynamic types should be enforced?
		 */
		ENFORCE_DYNAMIC_TYPES,
		/**
		 * The variant is type aware?
		 */
		TYPE_AWARE,
		/**
		 * The variant has global objecT?
		 */
		HAS_GLOBAL_OBJECT,
		/**
		 * Exported elements with visibility higher than private should be checked?
		 */
		CHECK_EXPORTED_WHEN_VISIBILITY_HIGHER_THAN_PRIVATE,
		/**
		 * Variant is unrestricted mode?
		 */
		UNRESTRICTED_MODE,
		/**
		 * Variant is external mode?
		 */
		EXTERNAL_MODE,
		/**
		 * Variant is N4JS mode?
		 */
		IS_N4JS_MODE,
		/**
		 * Variant is plain JS mode?
		 */
		IS_PLAIN_JS;

		/**
		 * Return true if this feature is activated for N4JSX files
		 */
		public boolean isN4JSXVariantActivated() {
			switch (this) {
			case DYNAMIC_PSEUDO_SCOPE:
				return false;
			case ALLOW_MISSING_IMPLEMENTATION:
				return false;
			case CHECK_OVERRIDE_ANNOTATION:
				return true;
			case CHECK_TYPE_DECLARATION:
				return true;
			case CHECK_MEMBER_DECLARATION:
				return true;
			case CHECK_VARIABLE:
				return true;
			case CHECK_METHOD_REFERENCE:
				return true;
			case CHECK_CALL_EXPRESSION:
				return true;
			case CHECK_NEW_EXPRESSION:
				return true;
			case CHECK_INDEXED_ACCESS_EXPRESSION:
				return true;
			case CHECK_FUNCTION_NAME:
				return true;
			case CHECK_FUNCTION_RETURN:
				return true;
			case CHECK_FUNCTION_EXPRESSION_IN_EXRESSION_STATEMENTMEMBER_DECLARATION:
				return true;
			case CONSTANT_HAS_INITIALIZER:
				return true;
			case CHECK_NO_N4JS_IN_RUNTIME_ENV_OR_LIB:
				return true;
			case ALLOW_WRONG_READ_WRITE:
				return false;
			case DOOM_TYPE_INTERFENCE:
				return false;
			case ALLOW_ANNOTATION:
				return true;
			case CHECK_FINAL_FIELDS_IS_INITIALIZED:
				return true;
			case CHECK_NAME_START_WITH_DOLLAR:
				return true;
			case CHECK_MISSING_BODY:
				return true;
			case CHECK_TYPE_MATCHES_EXPECTED_TYPE:
				return true;
			case ENFORCE_DYNAMIC_TYPES:
				return false;
			case TYPE_AWARE:
				return true;
			case HAS_GLOBAL_OBJECT:
				return false;
			case CHECK_EXPORTED_WHEN_VISIBILITY_HIGHER_THAN_PRIVATE:
				return true;
			case UNRESTRICTED_MODE:
				return false;
			case EXTERNAL_MODE:
				return false;
			case IS_N4JS_MODE:
				return true;
			case IS_PLAIN_JS:
				return false;
			}
			throw new UnsupportedOperationException("Unhandled Enum value " + this);
		}

		/**
		 * Return true if this feature is activated for JSX files
		 */
		public boolean isJSXVariantActivated() {
			switch (this) {
			case DYNAMIC_PSEUDO_SCOPE:
				return true;
			case ALLOW_MISSING_IMPLEMENTATION:
				return false;
			case CHECK_OVERRIDE_ANNOTATION:
				return false;
			case CHECK_TYPE_DECLARATION:
				return false;
			case CHECK_MEMBER_DECLARATION:
				return false;
			case CHECK_VARIABLE:
				return false;
			case CHECK_METHOD_REFERENCE:
				return false;
			case CHECK_CALL_EXPRESSION:
				return false;
			case CHECK_NEW_EXPRESSION:
				return false;
			case CHECK_INDEXED_ACCESS_EXPRESSION:
				return false;
			case CHECK_FUNCTION_NAME:
				return false;
			case CHECK_FUNCTION_RETURN:
				return false;
			case CHECK_FUNCTION_EXPRESSION_IN_EXRESSION_STATEMENTMEMBER_DECLARATION:
				return true;
			case CONSTANT_HAS_INITIALIZER:
				return true;
			case CHECK_NO_N4JS_IN_RUNTIME_ENV_OR_LIB:
				return false;
			case ALLOW_WRONG_READ_WRITE:
				return true;
			case DOOM_TYPE_INTERFENCE:
				return true;
			case ALLOW_ANNOTATION:
				return false;
			case CHECK_FINAL_FIELDS_IS_INITIALIZED:
				return false;
			case CHECK_NAME_START_WITH_DOLLAR:
				return false;
			case CHECK_MISSING_BODY:
				return false;
			case CHECK_TYPE_MATCHES_EXPECTED_TYPE:
				return false;
			case ENFORCE_DYNAMIC_TYPES:
				return true;
			case TYPE_AWARE:
				return false;
			case HAS_GLOBAL_OBJECT:
				return true;
			case CHECK_EXPORTED_WHEN_VISIBILITY_HIGHER_THAN_PRIVATE:
				return false;
			case UNRESTRICTED_MODE:
				return true;
			case EXTERNAL_MODE:
				return false;
			case IS_N4JS_MODE:
				return false;
			case IS_PLAIN_JS:
				return true;
			}
			throw new UnsupportedOperationException("Unhandled Enum value " + this);
		}
	}

	/**
	 * Return true if a validation feature should be activated for the current variant specified by the EOBject
	 *
	 * @param eobj
	 *            the EObject contained in the resource context
	 * @param validationFeature
	 *            the validation feature to be checked for activation *
	 * @param defaultBehavior
	 *            the default function in case the variant is neither N4JSX nor JSX
	 * @return true if the validation feature should be activated and false otherwise
	 */
	private boolean activateValidationFeature(EObject eobj, ValidationFeature validationFeature,
			Function<EObject, Boolean> defaultBehavior) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return validationFeature.isJSXVariantActivated();
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return validationFeature.isN4JSXVariantActivated();
		}
		return defaultBehavior.apply(eobj).booleanValue();
	}

	/**
	 * Activate dynamic pseudo scope for JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean activateDynamicPseudoScope(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.DYNAMIC_PSEUDO_SCOPE,
				super::activateDynamicPseudoScope);
	}

	/**
	 * Only N4JSD allows missing implementation
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean allowMissingImplementation(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.ALLOW_MISSING_IMPLEMENTATION,
				super::allowMissingImplementation);
	}

	/**
	 * Activate checking override annotation N4JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean checkOverrideAnnotation(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_OVERRIDE_ANNOTATION,
				super::checkOverrideAnnotation);

	}

	/**
	 * Activate checking type declaration for N4JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean checkTypeDeclaration(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_TYPE_DECLARATION,
				super::checkTypeDeclaration);
	}

	/**
	 * Activate checking type declaration for N4JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean checkMemberDeclaration(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_MEMBER_DECLARATION,
				super::checkMemberDeclaration);
	}

	/**
	 * Activate checking variable declaration for N4JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean checkVariable(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_VARIABLE,
				super::checkVariable);
	}

	/**
	 * Activate checking method reference for N4JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean checkMethodReference(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_METHOD_REFERENCE,
				super::checkMethodReference);
	}

	/**
	 * Activate checking call expression for N4JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean checkCallExpression(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_CALL_EXPRESSION,
				super::checkCallExpression);
	}

	/**
	 * Activate checking new expression for N4JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requireCheckNewExpression(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_NEW_EXPRESSION,
				super::requireCheckNewExpression);
	}

	/**
	 * Activate checking indexed access expression for N4JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requireCheckIndexedAccessExpression(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_INDEXED_ACCESS_EXPRESSION,
				super::requireCheckIndexedAccessExpression);
	}

	/**
	 * Activate checking function name for N4JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requireCheckFunctionName(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_FUNCTION_NAME,
				super::requireCheckFunctionName);
	}

	/**
	 * Activate checking function return for N4JSX.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requireCheckFunctionReturn(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_FUNCTION_RETURN,
				super::requireCheckFunctionReturn);
	}

	/**
	 * Activate function expression in expression statement for N4JSX.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requireCheckFunctionExpressionInExpressionStatement(EObject eobj) {
		return activateValidationFeature(eobj,
				ValidationFeature.CHECK_FUNCTION_EXPRESSION_IN_EXRESSION_STATEMENTMEMBER_DECLARATION,
				super::requireCheckFunctionExpressionInExpressionStatement);
	}

	/**
	 * Constant declaration has an initializer should be checked in all modes except N4JSD.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean constantHasInitializer(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CONSTANT_HAS_INITIALIZER,
				super::constantHasInitializer);
	}

	/**
	 * Activate checking no N4JS in runtime environment or lib for N4JSX.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requirecheckNoN4jsInRuntimeEnvOrLib(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_NO_N4JS_IN_RUNTIME_ENV_OR_LIB,
				super::requirecheckNoN4jsInRuntimeEnvOrLib);
	}

	/**
	 * Allow wrong read/write for JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean allowWrongReadWrite(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.ALLOW_WRONG_READ_WRITE,
				super::allowWrongReadWrite);
	}

	/**
	 * Allow doomed type inference for JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean doomTypeInference(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.DOOM_TYPE_INTERFENCE,
				super::doomTypeInference);
	}

	/**
	 * Allow annotation for N4JSX.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean allowAnnotation(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.ALLOW_ANNOTATION,
				super::allowAnnotation);
	}

	/**
	 * Activate checking final field is initialized for N4JSX.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requireCheckFinalFieldIsInitialized(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_FINAL_FIELDS_IS_INITIALIZED,
				super::requireCheckFinalFieldIsInitialized);
	}

	/**
	 * Activate checking name starts with dollar N4JSX.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requireCheckNameStartsWithDollar(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_NAME_START_WITH_DOLLAR,
				super::requireCheckNameStartsWithDollar);
	}

	/**
	 * Activate checking missing body for N4JSX.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requireCheckForMissingBody(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_MISSING_BODY,
				super::requireCheckForMissingBody);
	}

	/**
	 * Activate checking type matches for N4JSX.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requireCheckTypeMatchesExpectedType(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_TYPE_MATCHES_EXPECTED_TYPE,
				super::requireCheckTypeMatchesExpectedType);
	}

	/**
	 * Enforce dynamic types for JSX
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean enforceDynamicTypes(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.ENFORCE_DYNAMIC_TYPES,
				super::enforceDynamicTypes);
	}

	/**
	 * N4JSX is type aware but JSX is not.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean isTypeAware(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.TYPE_AWARE,
				super::isTypeAware);
	}

	/**
	 * JSX has global scope.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean hasGlobalObject(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.HAS_GLOBAL_OBJECT,
				super::hasGlobalObject);
	}

	/**
	 * Activate checking exported when visibility higher than private for N4JSX but deactivate for JSX.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean requireCheckExportedWhenVisibilityHigherThanPrivate(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.CHECK_EXPORTED_WHEN_VISIBILITY_HIGHER_THAN_PRIVATE,
				super::requireCheckExportedWhenVisibilityHigherThanPrivate);
	}

	/**
	 * JSX is unrestricted mode.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean isUnrestrictedMode(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.UNRESTRICTED_MODE,
				super::isUnrestrictedMode);
	}

	/**
	 * N4JSD is external mode.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean isExternalMode(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.EXTERNAL_MODE,
				super::isExternalMode);
	}

	/**
	 * Consider N4JSX as N4JS mode.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public boolean isN4JSMode(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.IS_N4JS_MODE,
				super::isN4JSMode);
	}

	/**
	 * Return true if the script is a plain JS.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 *
	 */
	@Override
	public boolean isPlainJS(EObject eobj) {
		return activateValidationFeature(eobj, ValidationFeature.IS_PLAIN_JS,
				super::isPlainJS);
	}

	/**
	 * Returns the variant mode.
	 *
	 * @param eobj
	 *            The EObject providing the context.
	 */
	@Override
	public String variantMode(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return "n4jsx";
		} else if (resourceType.equals(N4JSXResourceType.JSX)) {
			return "jsx";
		} else {
			return super.variantMode(eobj);
		}
	}

}
