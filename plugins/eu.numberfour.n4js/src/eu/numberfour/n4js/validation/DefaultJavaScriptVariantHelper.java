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

import org.eclipse.emf.ecore.EObject;

/**
 * This class defines the constraints to be checked for different variants. N4JS uses this default implementation
 * Sub-language can either override this implementation or provides a new implementation by implementing the
 * {@link JavaScriptVariantHelper} interface
 */
public class DefaultJavaScriptVariantHelper implements JavaScriptVariantHelper {

	/**
	 * Return true if dynamic pseudo scope should be activated
	 */
	@Override
	public boolean activateDynamicPseudoScope(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if missing implementation is allowed, for instance in external mode
	 */
	@Override
	public boolean allowMissingImplementation(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.external;
	}

	/**
	 * Return true if override annotation should be checked, e.g. if mode is N4JS
	 */
	@Override
	public boolean checkOverrideAnnotation(EObject eobj) {
		return !(JavaScriptVariant.getVariant(eobj).isECMAScript());
	}

	/**
	 * Return true if type declaration should be checked, e.g. if the mode is N4JS
	 */
	@Override
	public boolean checkTypeDeclaration(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Return true if type declaration should be checked, e.g. if the mode is N4JS
	 */
	@Override
	public boolean checkMemberDeclaration(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Return true if variable declaration should be checked, e.g. if the mode is N4JS
	 */
	@Override
	public boolean checkVariable(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Return true if method reference should be checked
	 */
	@Override
	public boolean checkMethodReference(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/**
	 * Return true if call expression should be checked
	 */
	@Override
	public boolean checkCallExpression(EObject eobj) {
		return !JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if new expression should be checked
	 */
	@Override
	public boolean requireCheckNewExpression(EObject eobj) {
		return !JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if indexed access expression should be checked, only in N4JS mode
	 */
	@Override
	public boolean requireCheckIndexedAccessExpression(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/**
	 * Return true if function name should be checked
	 */
	@Override
	public boolean requireCheckFunctionName(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Return true if function return should be checked
	 */
	@Override
	public boolean requireCheckFunctionReturn(EObject eobj) {
		return !JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if function expression in expression statement should be checked
	 */
	@Override
	public boolean requireCheckFunctionExpressionInExpressionStatement(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if a constant declaration has an initializer
	 */
	@Override
	public boolean constantHasInitializer(EObject eobj) {
		return !JavaScriptVariant.external.isActive(eobj);
	}

	/**
	 * Return true if it should be check that no N4JS in runtime environment or lib
	 */
	@Override
	public boolean requirecheckNoN4jsInRuntimeEnvOrLib(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/**
	 * Return true if it should be check that no N4JS in runtime environment or lib
	 */
	@Override
	public boolean allowWrongReadWrite(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if type inference should be doomed
	 */
	@Override
	public boolean doomTypeInference(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if annotation should be allowed
	 */
	@Override
	public boolean allowAnnotation(EObject eobj) {
		JavaScriptVariant currentVariant = JavaScriptVariant.getVariant(eobj);
		return (currentVariant == JavaScriptVariant.n4js || currentVariant == JavaScriptVariant.external);
	}

	/**
	 * Return true if it must be checked that a final field is initialized
	 */
	@Override
	public boolean requireCheckFinalFieldIsInitialized(EObject eobj) {
		return !JavaScriptVariant.external.isActive(eobj);
	}

	/**
	 * Return true if it must be checked if a name starts with dollar
	 */
	@Override
	public boolean requireCheckNameStartsWithDollar(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/**
	 * Return true if it is required to check if body of a member is missing
	 */
	@Override
	public boolean requireCheckForMissingBody(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) != JavaScriptVariant.external;
	}

	/**
	 * Return true if it is required to check type matches
	 */
	@Override
	public boolean requireCheckTypeMatchesExpectedType(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) != JavaScriptVariant.unrestricted;
	}

	/**
	 * Enforce dynamic types in call cases even without explicit modifier. This is usually the case for plain ECMAScript
	 */
	@Override
	public boolean enforceDynamicTypes(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Returns true if the variant is type aware
	 */
	@Override
	public boolean isTypeAware(EObject eobj) { // e.g. in N4JS
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Returns true if the variant has global object
	 */
	@Override
	public boolean hasGlobalObject(EObject eobj) { // e.g. in unrestricted ECMAScript mode
		return JavaScriptVariant.unrestricted.isActive(eobj);
	}

	/**
	 * Returns true if the mode is unrestricted
	 */
	@Override
	public boolean isUnrestrictedMode(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.unrestricted;
	}

	/**
	 * Returns true if the script is defined in a N4JSD module (external mode)
	 */
	@Override
	public boolean isExternalMode(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.external;
	}

	/**
	 * Returns true if the script is defined in a N4JS module
	 */
	@Override
	public boolean isN4JSMode(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/**
	 * Returns the variant mode
	 */
	@Override
	public String variantMode(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).toString();
	}

}
