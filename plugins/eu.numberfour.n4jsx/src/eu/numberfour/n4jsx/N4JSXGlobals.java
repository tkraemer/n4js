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
package eu.numberfour.n4jsx;

/**
 * Global hook for static information about the current setup.
 *
 * Start up code must initialize static attributes here.
 *
 */
public class N4JSXGlobals {

	/**
	 * Files extension of JSX source files (<b>not</b> including the separator dot).
	 */
	public static final String JSX_FILE_EXTENSION = "jsx";

	/**
	 * Files extension of N4JSX source files (<b>not</b> including the separator dot).
	 */
	public static final String N4JSX_FILE_EXTENSION = "n4jsx";
}
