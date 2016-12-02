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
 * This class provides the constraints to be checked for N4JSX and JSX sub-languages. If the files do not end with
 * .n4jsx or .jsx, the behavior the same as N4JS's default behavior defined in {@link DefaultJavaScriptVariantHelper}
 */
@Singleton
public class N4JSXJavaScriptVariantHelper extends DefaultJavaScriptVariantHelper {

	/**
	 * This enum represents resource types for N4JSX language
	 */
	public enum N4JSXResourceType {
		/** Raw file has extension <code>.n4jsx</code> or <code>.n4jsx.xt</code> */
		N4JSX,
		/** Raw file has extension <code>.jsx</code> or <code>.jsx.xt</code> */
		JSX,
		/** Raw file has other extension */
		OTHER;

		/**
		 * Retrieve the resource type of an URI, either N4JSX, JSX or OTHER
		 */
		public static N4JSXResourceType getResourceType(URI uri) {
			if (uri == null)
				return N4JSXResourceType.OTHER;

			ResourceType resourceType = ResourceType.getResourceType(uri);
			if (resourceType != ResourceType.UNKOWN && resourceType != ResourceType.XT) {
				return N4JSXResourceType.OTHER;
			} else {
				String fileExtension = uri.fileExtension();

				if (fileExtension == null) {
					LOGGER.info("URI has no file extension " + uri);
					return N4JSXResourceType.OTHER;
				} else {
					fileExtension = fileExtension.toLowerCase();
				}

				switch (fileExtension) {
				case EXT_N4JSX:
					return N4JSXResourceType.N4JSX;
				case EXT_JSX:
					return N4JSXResourceType.JSX;
				case EXT_XT:
					N4JSXResourceType resourceTypeWithinXT = getXtHiddenType(uri);
					if (resourceTypeWithinXT.equals(N4JSXResourceType.OTHER))
						return N4JSXResourceType.OTHER;
					else
						return resourceTypeWithinXT;
				default:
					return N4JSXResourceType.OTHER;
				}
			}
		}

		/**
		 * Retrieve the resource type of an EObject, either N4JSX, JSX or OTHER
		 */
		public static N4JSXResourceType getResourceType(EObject eobj) {
			if (eobj == null)
				return N4JSXResourceType.OTHER;

			Resource resource = eobj.eResource();
			if (resource == null)
				return N4JSXResourceType.OTHER;
			URI uri = resource.getURI();
			return getResourceType(uri);
		}

		/**
		 * For Xpect resources return type hidden by the xt extension.
		 */
		private static N4JSXResourceType getXtHiddenType(URI uri) {
			if (uri == null) {
				return N4JSXResourceType.OTHER;
			}

			String uriAsString = uri.toString().toLowerCase();
			if (uriAsString.endsWith(END_JSX_XT)) {
				return N4JSXResourceType.JSX;
			} else if (uriAsString.endsWith(END_N4JSX_XT)) {
				return N4JSXResourceType.N4JSX;
			} else
				return N4JSXResourceType.OTHER;
		}
	}

	private static Logger LOGGER = Logger.getLogger(ResourceType.class);

	private final static String EXT_N4JSX = "n4jsx";
	private final static String EXT_JSX = "jsx";
	private final static String EXT_XT = "xt";
	private final static String END_N4JSX_XT = "." + EXT_N4JSX + "." + EXT_XT;
	private final static String END_JSX_XT = "." + EXT_JSX + "." + EXT_XT;

	/**
	 * Activate dynamic pseudo scope for JSX
	 */
	@Override
	public boolean activateDynamicPseudoScope(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return true;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		}
		return super.activateDynamicPseudoScope(eobj);
	}

	/**
	 * Only N4JSD allows missing implementation
	 */
	@Override
	public boolean allowMissingImplementation(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX) || resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		}
		return super.allowMissingImplementation(eobj);
	}

	/**
	 * Activate checking override annotation N4JSX
	 */
	@Override
	public boolean checkOverrideAnnotation(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.checkOverrideAnnotation(eobj);
	}

	/**
	 * Activate checking type declaration for N4JSX
	 */
	@Override
	public boolean checkTypeDeclaration(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.checkTypeDeclaration(eobj);
	}

	/**
	 * Activate checking type declaration for N4JSX
	 */
	@Override
	public boolean checkMemberDeclaration(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.checkMemberDeclaration(eobj);
	}

	/**
	 * Activate checking variable declaration for N4JSX
	 */
	@Override
	public boolean checkVariable(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.checkVariable(eobj);
	}

	/**
	 * Activate checking method reference for N4JSX
	 */
	@Override
	public boolean checkMethodReference(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.checkMethodReference(eobj);
	}

	/**
	 * Activate checking call expression for N4JSX
	 */
	@Override
	public boolean checkCallExpression(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.checkCallExpression(eobj);
	}

	/**
	 * Activate checking new expression for N4JSX
	 */
	@Override
	public boolean requireCheckNewExpression(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.requireCheckNewExpression(eobj);
	}

	/**
	 * Activate checking indexed access expression for N4JSX
	 */
	@Override
	public boolean requireCheckIndexedAccessExpression(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.requireCheckIndexedAccessExpression(eobj);
	}

	/**
	 * Activate checking function name for N4JSX
	 */
	@Override
	public boolean requireCheckFunctionName(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.requireCheckFunctionName(eobj);
	}

	/**
	 * Activate checking function return for N4JSX
	 */
	@Override
	public boolean requireCheckFunctionReturn(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.requireCheckFunctionReturn(eobj);
	}

	/**
	 * Activate function expression in expression statement for N4JSX
	 */
	@Override
	public boolean requireCheckFunctionExpressionInExpressionStatement(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return true;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		}

		return super.requireCheckFunctionExpressionInExpressionStatement(eobj);
	}

	/**
	 * Constant declaration has an initializer should be checked in all modes except N4JSD
	 */
	@Override
	public boolean constantHasInitializer(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX) || resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.constantHasInitializer(eobj);
	}

	/**
	 * Activate checking no N4JS in runtime environment or lib for N4JSX
	 */
	@Override
	public boolean requirecheckNoN4jsInRuntimeEnvOrLib(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.requirecheckNoN4jsInRuntimeEnvOrLib(eobj);
	}

	/**
	 * Allow wrong read/write for JSX
	 */
	@Override
	public boolean allowWrongReadWrite(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return true;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		}
		return super.allowWrongReadWrite(eobj);
	}

	/**
	 * Allow doomed type inference for JSX
	 */
	@Override
	public boolean doomTypeInference(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return true;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		}
		return super.doomTypeInference(eobj);
	}

	/**
	 * Allow annotation for N4JSX
	 */
	@Override
	public boolean allowAnnotation(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.allowAnnotation(eobj);
	}

	/**
	 * Activate checking final field is initialized for N4JSX
	 */
	@Override
	public boolean requireCheckFinalFieldIsInitialized(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.requireCheckFinalFieldIsInitialized(eobj);
	}

	/**
	 * Activate checking name starts with dollar N4JSX
	 */
	@Override
	public boolean requireCheckNameStartsWithDollar(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.requireCheckNameStartsWithDollar(eobj);
	}

	/**
	 * Activate checking missing body for N4JSX
	 */
	@Override
	public boolean requireCheckForMissingBody(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.requireCheckForMissingBody(eobj);
	}

	/**
	 * Activate checking type matches for N4JSX
	 */
	@Override
	public boolean requireCheckTypeMatchesExpectedType(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.requireCheckTypeMatchesExpectedType(eobj);
	}

	/**
	 * Enforce dynamic types for JSX
	 */
	@Override
	public boolean enforceDynamicTypes(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return true;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		}
		return super.enforceDynamicTypes(eobj);
	}

	/**
	 * N4JSX is type aware
	 */
	@Override
	public boolean isTypeAware(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.isTypeAware(eobj);
	}

	/**
	 * JSX has global scope
	 */
	@Override
	public boolean hasGlobalObject(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return true;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		}
		return super.hasGlobalObject(eobj);
	}

	/**
	 * Activate checking exported when visibility higher than private for N4JSX but deactivate for JSX
	 */
	@Override
	public boolean requireCheckExportedWhenVisibilityHigherThanPrivate(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.requireCheckExportedWhenVisibilityHigherThanPrivate(eobj);
	}

	/**
	 * JSX is unrestricted mode
	 */
	@Override
	public boolean isUnrestrictedMode(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return true;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		}
		return super.isUnrestrictedMode(eobj);
	}

	/**
	 * N4JSD is external mode
	 */
	@Override
	public boolean isExternalMode(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX) || resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		}
		return super.isExternalMode(eobj);
	}

	/**
	 * Consider N4JSX as N4JS mode
	 */
	@Override
	public boolean isN4JSMode(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return true;
		}
		return super.isN4JSMode(eobj);
	}

	/**
	 * Return true if the script is a plain JS
	 *
	 */
	@Override
	public boolean isPlainJS(EObject eobj) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(eobj);
		if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		} else if (resourceType.equals(N4JSXResourceType.JSX)) {
			return true;
		}
		return super.isPlainJS(eobj);
	}

	/**
	 * Returns the variant mode
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
