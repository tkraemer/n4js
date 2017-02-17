/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.ui.organize.imports

import eu.numberfour.n4js.n4JS.Script
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.resource.XtextResource

/**
 * Simple utility for obtaining Script object from given resource.
 */
class XtextResourceUtils {

	/**
	 * @param xtextResource
	 *            the resource to process.
	 * @return Script instance or null
	 */
	public static def Script getScript(XtextResource xtextResource) {
		if (!xtextResource.getContents().isEmpty()) {
			val EObject eo = xtextResource.getContents().get(0);
			if (eo instanceof Script) {
				return eo;
			}
		}
		return null;
	}
}
