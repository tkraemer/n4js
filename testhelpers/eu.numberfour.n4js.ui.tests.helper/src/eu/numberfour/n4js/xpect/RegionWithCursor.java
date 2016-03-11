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
package eu.numberfour.n4js.xpect;

import org.xpect.text.Region;

/**
 * Region with global Cursor. The cursor is not necessarily inside of the region.
 */
public class RegionWithCursor extends Region {
	private final int globalCursorOffset;

	/**
	 * @param offset
	 *            region start
	 * @param lenght
	 *            region length
	 * @param globalCursorOffset
	 *            cursor position
	 */
	public RegionWithCursor(int offset, int lenght, int globalCursorOffset) {
		super(offset, lenght);
		this.globalCursorOffset = globalCursorOffset;
	}

	/**
	 * Empty Selection
	 *
	 * @param globalCursorOffset2
	 *            position
	 */
	public RegionWithCursor(int globalCursorOffset2) {
		this(globalCursorOffset2, 0, globalCursorOffset2);
	}

	/**
	 *
	 * @return cursor position
	 */
	public int getGlobalCursorOffset() {
		return globalCursorOffset;
	}

	/**
	 *
	 * @return true if selection length gt 0
	 */
	public boolean hasSelection() {
		return getLength() > 0;
	}

	@Override
	public String toString() {
		return "Region[" + (hasSelection() ? "" + getOffset() + " + " + getLength()
				+ "" : "empty")
				+ "]withCursorAt<" + getGlobalCursorOffset()
				+ ">	"
				+ ";";
	}
}
