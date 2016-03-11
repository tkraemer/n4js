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
package eu.numberfour.n4js;

import static com.google.common.collect.Sets.newLinkedHashSet;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableCollection;

import java.util.Collection;

/**
 * Global hook for static information about the current setup.
 *
 * Start up code must initialize static attributes herin.
 *
 * {@link #isHeadless()} indicates a non-eclipse, non-OSGi environment. (see N4jsc)
 */
public final class N4JSGlobals {

	/**
	 * Files extension of JS source files (<b>not</b> including the separator dot).
	 */
	public static final String JS_FILE_EXTENSION = "js";

	/**
	 * Files extension of N4JS source files (<b>not</b> including the separator dot).
	 */
	public static final String N4JS_FILE_EXTENSION = "n4js";

	/**
	 * Files extension of N4JS definition files (<b>not</b> including the separator dot).
	 */
	public static final String N4JSD_FILE_EXTENSION = "n4jsd";

	/**
	 * Unmodifiable list containing {@link #N4JSD_FILE_EXTENSION}, {@link #N4JS_FILE_EXTENSION},
	 * {@link #JS_FILE_EXTENSION}.
	 */
	public static final Collection<String> ALL_N4_FILE_EXTENSIONS = unmodifiableCollection(newLinkedHashSet(asList(
			N4JS_FILE_EXTENSION,
			N4JSD_FILE_EXTENSION,
			JS_FILE_EXTENSION)));

	private static boolean headless = false;

	private N4JSGlobals() {
		// private to prevent inheritance & instantiation.
	}

	/**
	 * Query if in headless mode (true).
	 *
	 * @return if running without eclipse, equinox & OSGi.
	 */
	public static boolean isHeadless() {
		return headless;
	}

	/**
	 * @param headless
	 *            set to true if in headless mode.
	 */
	public static void setHeadless(boolean headless) {
		N4JSGlobals.headless = headless;
	}
}
