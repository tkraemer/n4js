/*
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

/**
 * Extended information about where to insert new text.
 *
 */
public class InsertionPoint {

	/** Insertion point, a value of {@code -1} means not set. Zero-based offset. */
	public int offset = -1;

	/**
	 * Flag, if set indicates that the textRegion is just in Front of an active jsdoc region for the first statement
	 */
	public boolean isBeforeDocumentation = false;

	/** Lowest offset for possible insertion. Usually marked by existing ScriptAnnotation(s) or directives in prolog */
	public int notBeforeTotalOffset = 0;

	/** Highest possible insertion point in the document. */
	public int notAfterTotalOffset = Integer.MAX_VALUE;

}
