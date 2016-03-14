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
package eu.numberfour.n4js.utils;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.n4mf.utils.N4MFConstants;

/**
 *
 */
public enum ResourceType {
	/** Raw file has extension <code>.js</code> */
	JS,
	/** Raw file has extension <code>.n4js</code> */
	N4JS,
	/** Raw file has extension <code>.n4jsd</code> */
	N4JSD,
	/** Raw file has extension <code>.n4mf</code> */
	N4MF,

	// test files extensions

	/** Raw file has extension <code>.js.xt</code> */
	JSXT,
	/** Raw file has extension <code>.n4js.xt</code> */
	N4JSXT,
	/** Raw file has extension <code>.n4jsd.xt</code> */
	N4JSDXT,
	/** Raw file has extension <code>.n4mf.xt</code> */
	N4MFXT,
	/**
	 * Raw file has extension <code>.xt</code> but was not recognized as any of the other known resource types with
	 * double extension
	 */
	XT,

	// fall back

	/** Not recognized. Provided data could be invalid, e.g. <code>null</code>. */
	UNKOWN;

	private static Logger LOGGER = Logger.getLogger(ResourceType.class);
	private final static String EXT_JS = N4JSGlobals.JS_FILE_EXTENSION;
	private final static String EXT_N4JS = N4JSGlobals.N4JS_FILE_EXTENSION;
	private final static String EXT_N4JSD = N4JSGlobals.N4JSD_FILE_EXTENSION;
	private final static String EXT_N4MF = N4MFConstants.N4MF_FILE_EXTENSION;
	private final static String EXT_XT = "xt";
	private final static String END_JS_XT = "." + N4JSGlobals.JS_FILE_EXTENSION + "." + EXT_XT;
	private final static String END_N4JS_XT = "." + N4JSGlobals.N4JS_FILE_EXTENSION + "." + EXT_XT;
	private final static String END_N4JSD_XT = "." + N4JSGlobals.N4JSD_FILE_EXTENSION + "." + EXT_XT;
	private final static String END_N4MF_XT = "." + N4MFConstants.N4MF_FILE_EXTENSION + "." + EXT_XT;

	/**
	 * Based on {@link URI} of the provided {@link EObject eObject} determines type of the resource. Delegates to
	 * {@link #getResourceType(URI)}. Null safe (in that case returns {@link #UNKOWN} ).
	 */
	public static ResourceType getResourceType(EObject eObject) {
		if (eObject == null) {
			return ResourceType.UNKOWN;
		}
		return getResourceType(eObject.eResource());
	}

	/**
	 * Based on {@link URI} of the provided {Resource resource} determines type of the resource. Delegates to
	 * {@link #getResourceType(URI)}. Null safe (in that case returns {@link #UNKOWN} ).
	 */
	public static ResourceType getResourceType(Resource resource) {
		if (resource == null) {
			return ResourceType.UNKOWN;
		}
		return getResourceType(resource.getURI());
	}

	/**
	 * Based on the provided {@link URI} determines type of the resource. Null safe (in that case returns
	 * {@link #UNKOWN} ).
	 */
	public static ResourceType getResourceType(URI uri) {
		if (uri == null) {
			return ResourceType.UNKOWN;
		}
		switch (uri.fileExtension().toLowerCase()) {
		case EXT_JS:
			return ResourceType.JS;
		case EXT_N4JS:
			return ResourceType.N4JS;
		case EXT_N4JSD:
			return ResourceType.N4JSD;
		case EXT_N4MF:
			return ResourceType.N4JSD;
		case EXT_XT:
			/* for xpect tests check hidden extension */
			String uriAsString = uri.toString().toLowerCase();
			ResourceType type = ResourceType.XT;
			if (uriAsString.endsWith(END_JS_XT)) {
				type = ResourceType.JSXT;
			} else if (uriAsString.endsWith(END_N4JS_XT)) {
				type = N4JSXT;
			} else if (uriAsString.endsWith(END_N4JSD_XT)) {
				type = N4JSDXT;
			} else if (uriAsString.endsWith(END_N4MF_XT)) {
				type = N4MFXT;
			} else {
				LOGGER.info("Unknown xt file type : " + uriAsString);
			}
			return type;
		default:
			return ResourceType.UNKOWN;
		}
	}

	/**
	 * Convenience method for checking if a given resource is an XPECT type with hidden extension.
	 *
	 * @return true if given resource is based on file with double extension known
	 */
	public static boolean xtHidesOtherExtension(ResourceType resourceType) {
		return JSXT.equals(resourceType)
				|| N4JSXT.equals(resourceType)
				|| N4JSDXT.equals(resourceType)
				|| N4MFXT.equals(resourceType);
	}

}
