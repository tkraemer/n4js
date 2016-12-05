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

import eu.numberfour.n4js.utils.ResourceType;

/**
 * This class defines the constraints to be checked for different variants. N4JS uses this default implementation
 * Sub-language can either override this implementation or provides a new implementation by implementing the
 * {@link JavaScriptVariantHelper} interface
 */
public class DefaultJavaScriptVariantHelper implements JavaScriptVariantHelper {

	/**
	 * Return true if dynamic pseudo scope should be activated.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean activateDynamicPseudoScope(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if missing implementation is allowed, for instance in external mode.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean allowMissingImplementation(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.external;
	}

	/**
	 * Return true if override annotation should be checked, e.g. if mode is N4JS.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkOverrideAnnotation(EObject eobj) {
		return !(JavaScriptVariant.getVariant(eobj).isECMAScript());
	}

	/**
	 * Return true if type declaration should be checked, e.g. if the mode is N4JS.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkTypeDeclaration(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Return true if type declaration should be checked, e.g. if the mode is N4JS.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkMemberDeclaration(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Return true if variable declaration should be checked, e.g. if the mode is N4JS.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkVariable(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Return true if method reference should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkMethodReference(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/**
	 * Return true if call expression should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean checkCallExpression(EObject eobj) {
		return !JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if new expression should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckNewExpression(EObject eobj) {
		return !JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if indexed access expression should be checked, only in N4JS mode.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckIndexedAccessExpression(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/**
	 * Return true if function name should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckFunctionName(EObject eobj) {
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Return true if function return should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckFunctionReturn(EObject eobj) {
		return !JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if function expression in expression statement should be checked.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckFunctionExpressionInExpressionStatement(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if a constant declaration has an initializer.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean constantHasInitializer(EObject eobj) {
		return !JavaScriptVariant.external.isActive(eobj);
	}

	/**
	 * Return true if it should be check that no N4JS in runtime environment or lib.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requirecheckNoN4jsInRuntimeEnvOrLib(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/**
	 * Return true if wrong read/write should be allowed, e.g. in plain JS mode.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean allowWrongReadWrite(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if type inference should be doomed.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean doomTypeInference(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Return true if annotation should be allowed.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean allowAnnotation(EObject eobj) {
		JavaScriptVariant currentVariant = JavaScriptVariant.getVariant(eobj);
		return (currentVariant == JavaScriptVariant.n4js || currentVariant == JavaScriptVariant.external);
	}

	/**
	 * Return true if it must be checked that a final field is initialized.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckFinalFieldIsInitialized(EObject eobj) {
		return !JavaScriptVariant.external.isActive(eobj);
	}

	/**
	 * Return true if it must be checked if a name starts with dollar.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckNameStartsWithDollar(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
	}

	/**
	 * Return true if it is required to check if body of a member is missing.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckForMissingBody(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) != JavaScriptVariant.external;
	}

	/**
	 * Return true if it is required to check type matches.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean requireCheckTypeMatchesExpectedType(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) != JavaScriptVariant.unrestricted;
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
		return JavaScriptVariant.getVariant(eobj).isECMAScript();
	}

	/**
	 * Returns true if the variant is type aware, N4JS is type aware, plain JS is not.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean isTypeAware(EObject eobj) { // e.g. in N4JS
		return JavaScriptVariant.n4js.isActive(eobj);
	}

	/**
	 * Returns true if the variant has global object.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean hasGlobalObject(EObject eobj) { // e.g. in unrestricted ECMAScript mode
		return JavaScriptVariant.unrestricted.isActive(eobj);
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
		return JavaScriptVariant.n4js.isActive(eobj) || JavaScriptVariant.external.isActive(eobj);
	}

	/**
	 * Returns true if the mode is unrestricted.
	 */
	@Override
	public boolean isUnrestrictedMode(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.unrestricted;
	}

	/**
	 * Returns true if the script is defined in a N4JSD module (external mode).
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean isExternalMode(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.external;
	}

	/**
	 * Returns true if the script is defined in a N4JS module.
	 *
	 * @param eobj
	 *            The EObject providing the context for the check.
	 */
	@Override
	public boolean isN4JSMode(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj) == JavaScriptVariant.n4js;
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
		return ResourceType.getResourceType(eobj).equals(ResourceType.JS);
	}

	/**
	 * Returns the variant mode.
	 *
	 * @param eobj
	 *            The EObject providing the context in to find out the variant mode.
	 */
	@Override
	public String variantMode(EObject eobj) {
		return JavaScriptVariant.getVariant(eobj).toString();
	}

}
