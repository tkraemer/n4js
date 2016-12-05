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

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Singleton;

import eu.numberfour.n4js.utils.ResourceType;
import eu.numberfour.n4js.validation.DefaultJavaScriptVariantHelper;

/**
 * This class defines the constraints to be checked for different variants for N4JSX sub-language TODO: This should be
 * improved to support .JSX files as well
 */
@Singleton
public class N4JSXJavaScriptVariantHelper extends DefaultJavaScriptVariantHelper {

	private static Logger LOGGER = Logger.getLogger(ResourceType.class);

	private final static String EXT_N4JSX = "n4jsx";
	private final static String EXT_XT = "xt";
	private final static String END_N4JSX_XT = "." + EXT_N4JSX + "." + EXT_XT;

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
	 * Type inference should NOT be doomed for N4JSX
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
	public boolean hasGlobalObject(EObject eobj) { // e.g. in unrestricted
													// ECMAScript mode
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
	 * Check if we are in N4JS code mode (N4JSX is considered as N4JS)
	 */
	@Override
	public boolean isN4JSMode(EObject eobj) {
		// TODO: This method re-implements the logics in class ResourceType,
		// consider refactor ResourceType to make it more extensible
		if (eobj == null)
			return super.isN4JSMode(eobj);

		Resource resource = eobj.eResource();
		if (resource == null)
			return super.isN4JSMode(eobj);

		URI uri = resource.getURI();
		if (uri == null)
			return super.isN4JSMode(eobj);

		String fileExtension = uri.fileExtension();
		if (fileExtension == null) {
			LOGGER.info("URI has no file extension " + uri);
			return super.isN4JSMode(eobj);
		} else {
			fileExtension = fileExtension.toLowerCase();
			boolean isN4JSExtension = isN4JSXExtension(fileExtension);
			if (isN4JSExtension) {
				return isN4JSExtension;
			} else {
				return super.isN4JSMode(eobj);
			}
		}
	}

	/**
	 * Check if a file extension is N4JSX
	 */
	private boolean isN4JSXExtension(String fileExtension) {
		switch (fileExtension) {
		case EXT_N4JSX:
			return true;
		case EXT_XT:
			String fileExtensionWithoutXT = getXtHiddenType(fileExtension);
			return isN4JSXExtension(fileExtensionWithoutXT);
		default:
			return false;
		}
	}

	/**
	 * For Xpect resources return type hidden by the xt extension.
	 */
	private String getXtHiddenType(String fileExtension) {
		if (fileExtension == null) {
			return "";
		}

		String fileExtensionLowerCase = fileExtension.toLowerCase();
		if (fileExtensionLowerCase.endsWith(END_N4JSX_XT)) {
			return EXT_N4JSX;
		}
		return "";
	}

	/**
	 * Returns the variant mode
	 */
	@Override
	public String variantMode(EObject eobj) {
		return "n4jsx";
	}

}
