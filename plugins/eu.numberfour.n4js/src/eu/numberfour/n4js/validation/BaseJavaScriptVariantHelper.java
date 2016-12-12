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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.google.inject.Inject;

import eu.numberfour.n4js.resource.XpectAwareFileExtensionCalculator;

/**
 * The N4JS type system and validation is used not only for pure N4JS, but in the context of certain variants as plain
 * JavaScript in unrestricted or strict mode, type definition files (N4JSD) etc. It also is re-used by sublanguage (such
 * as JSX). Instead of replacing the validators (or other components) for these variants (which may be an additional
 * technique), the variant helper allows fine-grained control of what kind of constraints to check. An instance of the
 * helper is to be provided by the injector; N4Js uses the {@link N4JSJavaScriptVariantHelper}, sub-languages may bind
 * to a different implementation.
 */
public class BaseJavaScriptVariantHelper implements JavaScriptVariantHelper {

	/**
	 * Default variant mode: JS
	 */
	public static final String EXT_JS = "js";

	/**
	 * This file extension calculator calculates file extensions of resource
	 */
	@Inject
	protected XpectAwareFileExtensionCalculator fileExtensionCalculator;

	/**
	 * This class defines all validation features
	 */
	private static class ValidationFeatureBase {
	}

	static class ValidationFeature<T> extends ValidationFeatureBase {
		public T get(Map<FileExtensionValidationFeaturePair, Object> table, String extension) {
			return (T) table.get(new FileExtensionValidationFeaturePair(extension, this));
		}
	}

	/**
	 * Dynamic pseudo scope should be activated?
	 */
	public static final ValidationFeature<Boolean> DYNAMIC_PSEUDO_SCOPE = new ValidationFeature<>();
	/**
	 * Missing implementation is allowed?
	 */
	public static final ValidationFeature<Boolean> ALLOW_MISSING_IMPLEMENTATION = new ValidationFeature<>();
	/**
	 * Override annotation should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_OVERRIDE_ANNOTATION = new ValidationFeature<>();
	/**
	 * Type declaration should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_TYPE_DECLARATION = new ValidationFeature<>();
	/**
	 * Member declaration should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_MEMBER_DECLARATION = new ValidationFeature<>();
	/**
	 * Variable should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_VARIABLE = new ValidationFeature<>();
	/**
	 * Method reference should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_METHOD_REFERENCE = new ValidationFeature<>();
	/**
	 * Call expression should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_CALL_EXPRESSION = new ValidationFeature<>();
	/**
	 * New expression should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_NEW_EXPRESSION = new ValidationFeature<>();
	/**
	 * Indexed access expression should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_INDEXED_ACCESS_EXPRESSION = new ValidationFeature<>();
	/**
	 * Function name should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_FUNCTION_NAME = new ValidationFeature<>();
	/**
	 * Function return should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_FUNCTION_RETURN = new ValidationFeature<>();
	/**
	 * Function expression in expression statement should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_FUNCTION_EXPRESSION_IN_EXRESSION_STATEMENTMEMBER_DECLARATION = new ValidationFeature<>();
	/**
	 * Constant has initializer?
	 */
	public static final ValidationFeature<Boolean> CONSTANT_HAS_INITIALIZER = new ValidationFeature<>();
	/**
	 * No N4JS in runtime or lib should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_NO_N4JS_IN_RUNTIME_ENV_OR_LIB = new ValidationFeature<>();
	/**
	 * Wrong read/write should be allowed?
	 */
	public static final ValidationFeature<Boolean> ALLOW_WRONG_READ_WRITE = new ValidationFeature<>();
	/**
	 * Type inference should be doomed
	 */
	public static final ValidationFeature<Boolean> DOOM_TYPE_INTERFENCE = new ValidationFeature<>();
	/**
	 * Annotation should be allowed
	 */
	public static final ValidationFeature<Boolean> ALLOW_ANNOTATION = new ValidationFeature<>();
	/**
	 * Final fields has initializer?
	 */
	public static final ValidationFeature<Boolean> CHECK_FINAL_FIELDS_IS_INITIALIZED = new ValidationFeature<>();
	/**
	 * Name start with dollar should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_NAME_START_WITH_DOLLAR = new ValidationFeature<>();
	/**
	 * Missing body should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_MISSING_BODY = new ValidationFeature<>();
	/**
	 * Type matches should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_TYPE_MATCHES_EXPECTED_TYPE = new ValidationFeature<>();
	/**
	 * Dynamic types should be enforced?
	 */
	public static final ValidationFeature<Boolean> ENFORCE_DYNAMIC_TYPES = new ValidationFeature<>();
	/**
	 * The variant is type aware?
	 */
	public static final ValidationFeature<Boolean> TYPE_AWARE = new ValidationFeature<>();
	/**
	 * The variant has global objecT?
	 */
	public static final ValidationFeature<Boolean> HAS_GLOBAL_OBJECT = new ValidationFeature<>();
	/**
	 * Exported elements with visibility higher than private should be checked?
	 */
	public static final ValidationFeature<Boolean> CHECK_EXPORTED_WHEN_VISIBILITY_HIGHER_THAN_PRIVATE = new ValidationFeature<>();
	/**
	 * Variant is unrestricted mode?
	 */
	public static final ValidationFeature<Boolean> UNRESTRICTED_MODE = new ValidationFeature<>();
	/**
	 * Variant is external mode?
	 */
	public static final ValidationFeature<Boolean> EXTERNAL_MODE = new ValidationFeature<>();
	/**
	 * Variant is N4JS mode?
	 */
	public static final ValidationFeature<Boolean> IS_N4JS_MODE = new ValidationFeature<>();
	/**
	 * Variant is plain JS mode?
	 */
	public static final ValidationFeature<Boolean> IS_PLAIN_JS = new ValidationFeature<>();
	/**
	 * String representation of variant mode, e.g. "n4js", "js"
	 */
	public static final ValidationFeature<String> VARIANT_MODE_STRINGREP = new ValidationFeature<>();

	/**
	 * This class encapsulates a pair of file extension and validation feature and should serve as keys for
	 * {@link JavaScriptVariantHelper}
	 */
	private static class FileExtensionValidationFeaturePair {
		private final String fileExtension;
		private final ValidationFeatureBase validationFeature;

		/**
		 * Constructor
		 *
		 * @param fileExtension
		 *            the file extension in the key
		 * @param validationFeature
		 *            the validation feature in the key
		 */
		public FileExtensionValidationFeaturePair(String fileExtension, ValidationFeatureBase validationFeature) {
			this.fileExtension = fileExtension;
			this.validationFeature = validationFeature;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof FileExtensionValidationFeaturePair) {
				FileExtensionValidationFeaturePair otherKey = (FileExtensionValidationFeaturePair) obj;
				return fileExtension.equals(otherKey.fileExtension)
						&& validationFeature.equals(otherKey.validationFeature);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return fileExtension.hashCode() + 31 * validationFeature.hashCode();
		}
	}

	private final Map<FileExtensionValidationFeaturePair, Object> table = new HashMap<>();
	/**
	 * Next element in the chain of responsibility
	 */
	protected BaseJavaScriptVariantHelper next;

	/**
	 * Add maps (file extension, validation feature) -> value
	 *
	 */
	protected <T> void addEntry(String fileExtension, ValidationFeature<T> feature, T value) {
		table.put(new FileExtensionValidationFeaturePair(fileExtension, feature), value);
	}

	/**
	 * Return the validation feature
	 *
	 */
	protected <T> T get(String fileExtension, ValidationFeature<T> feature) {
		T result = feature.get(table, fileExtension);
		if (result != null) {
			return result;
		}
		if (next != null) {
			return next.get(fileExtension, feature);
		}
		return null;
		// throw new RuntimeException("Cannot process this request");
	}

	/**
	 * Return true if dynamic pseudo scope should be activated.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean activateDynamicPseudoScope(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), DYNAMIC_PSEUDO_SCOPE);
		return r == null ? true : r;
	}

	/**
	 * Return true if missing implementation is allowed, for instance in external mode.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean allowMissingImplementation(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), ALLOW_MISSING_IMPLEMENTATION);
		return r == null ? false : r;
	}

	/**
	 * Return true if override annotation should be checked, e.g. if mode is N4JS.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkOverrideAnnotation(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), ALLOW_MISSING_IMPLEMENTATION);
		return r == null ? false : r;
	}

	/**
	 * Return true if type declaration should be checked, e.g. if the mode is N4JS.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkTypeDeclaration(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_TYPE_DECLARATION);
		return r == null ? false : r;
	}

	/**
	 * Return true if type declaration should be checked, e.g. if the mode is N4JS.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkMemberDeclaration(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_MEMBER_DECLARATION);
		return r == null ? false : r;
	}

	/**
	 * Return true if variable declaration should be checked, e.g. if the mode is N4JS.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkVariable(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_VARIABLE);
		return r == null ? false : r;
	}

	/**
	 * Return true if method reference should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkMethodReference(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_METHOD_REFERENCE);
		return r == null ? false : r;
	}

	/**
	 * Return true if call expression should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkCallExpression(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_CALL_EXPRESSION);
		return r == null ? false : r;
	}

	/**
	 * Return true if new expression should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckNewExpression(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_NEW_EXPRESSION);
		return r == null ? false : r;
	}

	/**
	 * Return true if indexed access expression should be checked, only in N4JS mode.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckIndexedAccessExpression(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_INDEXED_ACCESS_EXPRESSION);
		return r == null ? false : r;
	}

	/**
	 * Return true if function name should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckFunctionName(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_FUNCTION_NAME);
		return r == null ? false : r;
	}

	/**
	 * Return true if function return should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckFunctionReturn(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_FUNCTION_RETURN);
		return r == null ? false : r;
	}

	/**
	 * Return true if function expression in expression statement should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckFunctionExpressionInExpressionStatement(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj),
				CHECK_FUNCTION_EXPRESSION_IN_EXRESSION_STATEMENTMEMBER_DECLARATION);
		return r == null ? true : r;
	}

	/**
	 * Return true if a constant declaration has an initializer.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean constantHasInitializer(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CONSTANT_HAS_INITIALIZER);
		return r == null ? false : r;
	}

	/**
	 * Return true if it should be check that no N4JS in runtime environment or lib.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requirecheckNoN4jsInRuntimeEnvOrLib(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_NO_N4JS_IN_RUNTIME_ENV_OR_LIB);
		return r == null ? false : r;
	}

	/**
	 * Return true if wrong read/write should be allowed, e.g. in plain JS mode.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean allowWrongReadWrite(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), ALLOW_WRONG_READ_WRITE);
		return r == null ? true : r;
	}

	/**
	 * Return true if type inference should be doomed.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean doomTypeInference(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), DOOM_TYPE_INTERFENCE);
		return r == null ? true : r;
	}

	/**
	 * Return true if annotation should be allowed.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean allowAnnotation(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), ALLOW_ANNOTATION);
		return r == null ? false : r;
	}

	/**
	 * Return true if it must be checked that a final field is initialized.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckFinalFieldIsInitialized(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_FINAL_FIELDS_IS_INITIALIZED);
		return r == null ? false : r;
	}

	/**
	 * Return true if it must be checked if a name starts with dollar.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckNameStartsWithDollar(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_NAME_START_WITH_DOLLAR);
		return r == null ? false : r;
	}

	/**
	 * Return true if it is required to check if body of a member is missing.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckForMissingBody(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_MISSING_BODY);
		return r == null ? true : r;
	}

	/**
	 * Return true if it is required to check type matches.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckTypeMatchesExpectedType(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), CHECK_TYPE_MATCHES_EXPECTED_TYPE);
		return r == null ? false : r;
	}

	/**
	 * Enforce dynamic types in call cases even without explicit modifier. This is usually the case for plain
	 * ECMAScript.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean enforceDynamicTypes(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), ENFORCE_DYNAMIC_TYPES);
		return r == null ? true : r;
	}

	/**
	 * Returns true if the variant is type aware, N4JS is type aware, plain JS is not.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean isTypeAware(EObject eobj) { // e.g. in N4JS
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), TYPE_AWARE);
		return r == null ? false : r;
	}

	/**
	 * Returns true if the variant has global object.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean hasGlobalObject(EObject eobj) { // e.g. in unrestricted ECMAScript mode
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), HAS_GLOBAL_OBJECT);
		return r == null ? true : r;
	}

	/**
	 * Return true exported should be checked in case the visibility is higher than private. This is not true for plain
	 * JS files.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckExportedWhenVisibilityHigherThanPrivate(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj),
				CHECK_EXPORTED_WHEN_VISIBILITY_HIGHER_THAN_PRIVATE);
		return r == null ? false : r;
	}

	/**
	 * Returns true if the mode is unrestricted.
	 */
	@Override
	public boolean isUnrestrictedMode(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), UNRESTRICTED_MODE);
		return r == null ? true : r;
	}

	/**
	 * Returns true if the script is defined in a N4JSD module (external mode).
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean isExternalMode(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), EXTERNAL_MODE);
		return r == null ? false : r;
	}

	/**
	 * Returns true if the script is defined in a N4JS module.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean isN4JSMode(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), IS_N4JS_MODE);
		return r == null ? false : r;
	}

	/**
	 * Return true if the script is a plain JS.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 *
	 */
	@Override
	public boolean isPlainJS(EObject eobj) {
		Boolean r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), IS_PLAIN_JS);
		return r == null ? true : r;
	}

	/**
	 * Returns the variant mode.
	 *
	 * @param eobj
	 *            The EObject providing the context in to find out the variant mode.
	 */
	@Override
	public String variantMode(EObject eobj) {
		String r = get(fileExtensionCalculator.getXpectAwareFileExtension(eobj), VARIANT_MODE_STRINGREP);
		return r == null ? EXT_JS : r;
	}

}
