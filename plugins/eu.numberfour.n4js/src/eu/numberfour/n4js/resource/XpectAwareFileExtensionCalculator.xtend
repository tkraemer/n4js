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
package eu.numberfour.n4js.resource

import com.google.inject.Singleton
import eu.numberfour.n4js.N4JSGlobals
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject

/**
 * This class provides methods for calculating file extensions. The calculation takes into account Xpect file extension
 * .xt
 */
 @Singleton
public class XpectAwareFileExtensionCalculator {
	
	def public String getXpectAwareFileExtension(EObject eob) {
		if ((eob === null) || (eob.eResource === null)) {
			return "";
		}
		return eob.eResource.URI.getXpectAwareFileExtension;
	}
	
	/**
	 * Return the file extension of an URI
	 */
	def public String getXpectAwareFileExtension(URI uri) {
		if (uri === null) {
			return "";
		}
		val uriAsString = uri.toString().toLowerCase();
		val fileExt = uriAsString.getXpectAwareFileExtensionRec;
		return fileExt;
	}
	
	def private String getXpectAwareFileExtensionRec(String fileName) {
		if (fileName.endsWith(N4JSGlobals.XT_FILE_EXTENSION)) {
			return fileName.substring(0, fileName.lastIndexOf('.')).xpectAwareFileExtensionRec
		} else {
			val i = fileName.lastIndexOf('.');
			if (i >= 0) {
				return fileName.substring(i+1);	
			} else {
				return "";
			}
		} 
	}
}
