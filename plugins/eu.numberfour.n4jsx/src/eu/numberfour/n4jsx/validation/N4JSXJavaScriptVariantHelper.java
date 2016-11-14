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

import org.eclipse.emf.ecore.EObject;

import eu.numberfour.n4js.validation.JavaScriptVariantHelper;

/**
 * This class defines N4JSX validation features
 */
public class N4JSXJavaScriptVariantHelper implements JavaScriptVariantHelper {

	/**
	 * No dynamic pseudo scope for N4JSX
	 */
	@Override
	public boolean activateDynamicPseudoScope(EObject eobj) {
		return false;
	}

	/**
	 * N4JSX does not allow missing implementation
	 */
	@Override
	public boolean allowMissingImplementation(EObject eobj) {
		return false;
	}

	/**
	 * Override annotation should be checked for N4JSX
	 */
	@Override
	public boolean checkOverrideAnnotation(EObject eobj) {
		return true;
	}

	/**
	 * Type declaration should be checked for N4JSX
	 */
	@Override
	public boolean checkTypeDeclaration(EObject eobj) {
		return true;
	}

	/**
	 * Type declaration should be checked for N4JSX
	 */
	@Override
	public boolean checkMemberDeclaration(EObject eobj) {
		return true;
	}

	/**
	 * Variable declaration should be checked for N4JSX
	 */
	@Override
	public boolean checkVariable(EObject eobj) {
		return true;
	}

	/**
	 * Method reference should be checked for N4JSX
	 */
	@Override
	public boolean checkMethodReference(EObject eobj) {
		return true;
	}

	/**
	 * Call expression should be checked for N4JSX
	 */
	@Override
	public boolean checkCallExpression(EObject eobj) {
		return true;
	}

	/**
	 * New expression should be checked for N4JSX
	 */
	@Override
	public boolean requireCheckNewExpression(EObject eobj) {
		return true;
	}

	/**
	 * Indexed access expression should be checked for N4JSX
	 */
	@Override
	public boolean requireCheckIndexedAccessExpression(EObject eobj) {
		return true;
	}

	/**
	 * Function name should be checked for N4JSX
	 */
	@Override
	public boolean requireCheckFunctionName(EObject eobj) {
		return true;
	}

	/**
	 * Function return should be checked for N4JSX
	 */
	@Override
	public boolean requireCheckFunctionReturn(EObject eobj) {
		return true;
	}

	/**
	 * Function expression in expression statement should NOT be checked for N4JSX
	 */
	@Override
	public boolean requireCheckFunctionExpressionInExpressionStatement(EObject eobj) {
		return false;
	}

	/**
	 * Constant declaration has an initializer in N4JSX
	 */
	@Override
	public boolean constantHasInitializer(EObject eobj) {
		return true;
	}

	/**
	 * No N4JSX in runtime environment or lib should be checked
	 */
	@Override
	public boolean requirecheckNoN4jsInRuntimeEnvOrLib(EObject eobj) {
		return true;
	}

	/**
	 * Wrong read/write should NOT be allowed for N$JSX
	 */
	@Override
	public boolean allowWrongReadWrite(EObject eobj) {
		return false;
	}

	/**
	 * Type inference should NOT  be doomed for N4JSX
	 */
	@Override
	public boolean doomTypeInference(EObject eobj) {
		return false;
	}

	/**
	 * Annotation should be allowed for N4JSX
	 */
	@Override
	public boolean allowAnnotation(EObject eobj) {
		return true;
	}

	/**
	 * It must be checked that a final field is initialized for N4JSX
	 */
	@Override
	public boolean requireCheckFinalFieldIsInitialized(EObject eobj) {
		return true;
	}

	/**
	 * It must be checked if a name starts with dollar for N4JSX
	 */
	@Override
	public boolean requireCheckNameStartsWithDollar(EObject eobj) {
		return true;
	}

	/**
	 * It is required to check if body of a member is missing for N4JSX
	 */
	@Override
	public boolean requireCheckForMissingBody(EObject eobj) {
		return true;
	}

	/**
	 * It is required to check type matches for N4JSX
	 */
	@Override
	public boolean requireCheckTypeMatchesExpectedType(EObject eobj) {
		return true;
	}

	/**
	 * Do NOT enforce dynamic types in call cases even without explicit modifier in N4JSX
	 */
	@Override
	public boolean enforceDynamicTypes(EObject eobj) {
		return false;
	}

	/**
	 * The variant N4JSX is type aware
	 */
	@Override
	public boolean isTypeAware(EObject eobj) { // e.g. in N4JS
		return true;
	}

	/**
	 * The variant N4JSX does NOT have global object
	 */
	@Override
	public boolean hasGlobalObject(EObject eobj) { // e.g. in unrestricted ECMAScript mode
		return false;
	}

	/**
	 * The mode N4JSX is NOT unrestricted
	 */
	@Override
	public boolean isUnrestrictedMode(EObject eobj) {
		return false;
	}

	/**
	 * The N4JSX mode is NOT external
	 */
	@Override
	public boolean isExternalMode(EObject eobj) {
		return false;
	}

	/**
	 * N4JSX is actually defined in a N4JS module
	 */
	@Override
	public boolean isN4JSMode(EObject eobj) {
		return true;
	}

	/**
	 * Returns the variant mode
	 */
	@Override
	public String variantMode(EObject eobj) {
		return "n4jsx";
	}

}
