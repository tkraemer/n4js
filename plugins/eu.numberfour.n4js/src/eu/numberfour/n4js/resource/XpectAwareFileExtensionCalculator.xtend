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
import org.apache.log4j.Logger
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject

/**
 * This class provides methods for calculating file extensions. The calculation takes into account Xpect file extension
 * .xt
 */
 @Singleton
public class XpectAwareFileExtensionCalculator {
	
	private final static String EXT_XT = ".xt";

	private static Logger LOGGER = Logger.getLogger(XpectAwareFileExtensionCalculator);
	
	def public String getXpectAwareFileExtension(EObject eob) {
		return eob?.eResource?.URI?.getXpectAwareFileExtension;
	}
	
	/**
	 * Return the file extension of an URI
	 */
	def public String getXpectAwareFileExtension(URI uri) {
//		if (uri === null) {
//			return "";
//		}
//		
//		var String fileExtension = uri.fileExtension();
//		if (fileExtension === null) {
//			LOGGER.info("URI has no file extension " + uri);
//			return "";
//		} else {
//			fileExtension = fileExtension.toLowerCase();
//		}		
//		
		val uriAsString = uri.toString().toLowerCase();
		val fileExt = uriAsString.getXpectAwareFileExtensionRec;
		return fileExt;
//		
//		val s = uriAsString.split(".");
//		
//		if (s.length <= 1) {
//			LOGGER.info("URI has no file extension " + uri);
//			return "";
//		} else 	if (s.length == 2) {
//			return s.get(s.length - 1);
//		} else {
//			// s.length > 2
//			if (s.get(s.length - 1) != EXT_XT) {
//				return s.get(s.length - 1);
//			}  	else {
//				return s.get(s.length - 2);
//			}
//		}			
	}
	
	
	def private String getXpectAwareFileExtensionRec(String fileName) {
		if (fileName.endsWith(EXT_XT)) {
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
