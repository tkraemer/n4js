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
package eu.numberfour.n4js.ui.organize.imports;

import java.util.Collection;

import com.google.common.collect.Multimap;

/**
 */
public class N4JSOrganizeImportsHelper {

	/**
	 * Turn this Multimap into a two-dimensional array the first index giving the page, second the choices of this page.
	 *
	 * @param multimap
	 *            name to many options
	 * @return two-dim Array of T
	 */
	public static <T> Object[][] createOptions(Multimap<String, T> multimap) {
		Object[][] ret = new Object[multimap.keySet().size()][];

		int page = 0;
		for (String key : multimap.keySet()) {
			Collection<T> values = multimap.get(key);
			ret[page] = new Object[values.size()];
			int option = 0;
			for (T ns : values) {
				ret[page][option] = ns;
				option++;
			}
			page++;
		}

		return ret;
	}

}
