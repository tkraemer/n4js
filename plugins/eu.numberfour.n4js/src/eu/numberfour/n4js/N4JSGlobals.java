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

import org.eclipse.emf.ecore.EReference;

import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;
import eu.numberfour.n4js.utils.M2MUriUtil;

/**
 * Global hook for static information about the current setup.
 *
 * Start up code must initialize static attributes here.
 *
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
	 * Files extension of N4TS source files (<b>not</b> including the separator dot).
	 */
	public static final String N4TS_FILE_EXTENSION = "n4ts";

	/**
	 * Unmodifiable list containing {@link #N4JSD_FILE_EXTENSION}, {@link #N4JS_FILE_EXTENSION},
	 * {@link #JS_FILE_EXTENSION}.
	 */
	public static final Collection<String> ALL_N4_FILE_EXTENSIONS = unmodifiableCollection(newLinkedHashSet(asList(
			N4JS_FILE_EXTENSION,
			N4JSD_FILE_EXTENSION,
			JS_FILE_EXTENSION)));

	/**
	 * Subtrees of the AST below these containment references will *not* be validated.
	 */
	public static final EReference[] AST_CONTAINMENT_REFERENCES_WITHOUT_VALIDATION = {
			TypeRefsPackage.eINSTANCE.getComposedTypeRef_CachedComposedMembers()
	};

	/**
	 * Fragment prefix for module-to-module URIs (a.k.a. m2m URIs). See {@link M2MUriUtil}.
	 */
	public static final String URI_FRAGMENT_PREFIX_M2M = "m2m!";

	private N4JSGlobals() {
		// private to prevent inheritance & instantiation.
	}

}
