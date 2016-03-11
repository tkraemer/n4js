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
package eu.numberfour.n4js.validation.validators

import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.ExportDeclaration
import eu.numberfour.n4js.n4JS.FunctionDeclaration
import eu.numberfour.n4js.n4JS.N4EnumDeclaration
import eu.numberfour.n4js.n4JS.N4InterfaceDeclaration
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.validation.IssueCodes

/**
 * Collecting special validation logic only related to static polyfill modules.
 * IDE-1735
 */
public class StaticPolyfillValidatorExtension {

   /** $140 (Restriction on static-polyfilling): §140.1 only classes in staticPolyfillModule allowed. */
	public static def internalCheckNotInStaticPolyfillModule(N4InterfaceDeclaration n4InterfaceDeclaration, N4JSInterfaceValidator host) {
		if (AnnotationDefinition.STATIC_POLYFILL_MODULE.hasAnnotation(n4InterfaceDeclaration)) { // transetively inherited
			val msg = IssueCodes.messageForPOLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES
			host.addIssue(msg, n4InterfaceDeclaration, N4JSPackage.Literals.N4_TYPE_DECLARATION__NAME,
				IssueCodes.POLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES)
		}
	}


   /** $140 (Restriction on static-polyfilling): §140.1 only classes in staticPolyfillModule allowed. */
	public static def internalCheckNotInStaticPolyfillModule(N4EnumDeclaration n4EnumDecl, N4JSEnumValidator host) {
		if (AnnotationDefinition.STATIC_POLYFILL_MODULE.hasAnnotation(n4EnumDecl)) { // transetively inherited
			val msg = IssueCodes.messageForPOLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES
			host.addIssue(msg, n4EnumDecl, N4JSPackage.Literals.N4_TYPE_DECLARATION__NAME,
				IssueCodes.POLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES)
		}
	}

   /** $140 (Restriction on static-polyfilling): §140.1 only classes in staticPolyfillModule allowed. */
	public static def internalCheckNotInStaticPolyfillModule(FunctionDeclaration functionDeclaration, N4JSFunctionValidator host) {
		// top level functionDeclarations:
		var cont = functionDeclaration.eContainer;
		while ( cont instanceof ExportDeclaration ) cont = cont.eContainer;
		if( cont instanceof Script)
		{
			if (AnnotationDefinition.STATIC_POLYFILL_MODULE.hasAnnotation(functionDeclaration)) { // transetively inherited
				val msg = IssueCodes.messageForPOLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES
				host.addIssue(msg, functionDeclaration, N4JSPackage.Literals.FUNCTION_DECLARATION__NAME,
					IssueCodes.POLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES)
			}
		}
	}



}
