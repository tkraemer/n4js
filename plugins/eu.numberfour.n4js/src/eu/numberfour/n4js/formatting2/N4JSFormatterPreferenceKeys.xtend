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
package eu.numberfour.n4js.formatting2

import org.eclipse.xtext.formatting2.FormatterPreferenceKeys
import org.eclipse.xtext.preferences.BooleanKey

/**
 * 
 */
class N4JSFormatterPreferenceKeys extends FormatterPreferenceKeys {
	public static val BooleanKey FORMAT_PARENTHESIS = new BooleanKey("format.parenthesis", false)
}
