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
package eu.numberfour.n4js.validation.validators.utils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 *
 */
public enum ResourceType {
	/** ".js" */
	JS,
	/** ".n4js" */
	N4JS,
	/** ".n4jsd" */
	N4JSD,
	/** ".js.xt" */
	JSXT,
	/** ".n4js.xt" */
	N4JSXT,
	/** ".n4jsd.xt" */
	N4JSDXT,
	/** not recognized */
	UNKOWN;

	/**
	 * Based on {@link URI} of the provided {@link EObject eObject} determines type of the resource.
	 */
	public static ResourceType getResourceType(EObject eObject) {
		if (eObject.eResource() == null) {
			return ResourceType.UNKOWN;
		}
		URI uri = eObject.eResource().getURI();
		if (uri == null) {
			return ResourceType.UNKOWN;
		}
		switch (uri.fileExtension().toLowerCase()) {
		case "js":
			return ResourceType.JS;
		case "n4js":
			return ResourceType.N4JS;
		case "n4jsd":
			return ResourceType.N4JSD;
		case "xt":
			/* for xpect tests check hidden extension */
			String uris = uri.toString().toLowerCase();
			ResourceType type = ResourceType.UNKOWN;
			if (uris.endsWith(".js.xt")) {
				type = ResourceType.JSXT;
			} else if (uris.endsWith(".n4js.xt")) {
				type = N4JSXT;
			} else if (uris.endsWith(".n4jsd.xt")) {
				type = N4JSDXT;
			}
			return type;
		default:
			return ResourceType.UNKOWN;
		}
	}
}
