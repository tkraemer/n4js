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
import org.eclipse.xtext.preferences.IntegerKey

/**
 * 
 */
class N4JSFormatterPreferenceKeys extends FormatterPreferenceKeys {
	public static val BooleanKey FORMAT_PARENTHESIS = new BooleanKey("format.parenthesis", false);
	public static val BooleanKey FORMAT_SURROUND_PAREN_CONTENT_WITH_SPACE = new BooleanKey("format.surround_paren_content_with_space", false);
	public static val IntegerKey FORMAT_MAX_CONSECUTIVE_NEWLINES = new IntegerKey("format.max_consecutive_newlines",10);
	public static val BooleanKey FORMAT_SWITCH_CASES_HAVE_SPACE_IN_FRONT_OF_COLON = new BooleanKey("format.switch_cases_have_space_in_front_of_colon", false);
	public static val BooleanKey FORMAT_AUTO_WRAP_IN_FRONT_OF_LOGICAL_OPERATOR= new BooleanKey("format.auto_wrap_in_front_of_logical_operator", true);
	
	
}
