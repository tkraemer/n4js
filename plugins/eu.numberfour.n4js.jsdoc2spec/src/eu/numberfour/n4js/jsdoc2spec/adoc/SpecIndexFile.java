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
package eu.numberfour.n4js.jsdoc2spec.adoc;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import eu.numberfour.n4js.jsdoc2spec.SpecFile;

/**
 * A {@link SpecIndexFile} contains the content of the index.idx file.
 */
public class SpecIndexFile extends SpecFile {
	private final String newContent;
	private final String packageDisplayName;

	/**
	 * Constructor for a file
	 */
	public SpecIndexFile(File file, String newContent) {
		this(file, newContent, "");
	}

	/**
	 * Constructor for a file
	 */
	public SpecIndexFile(File file, String newContent, String packageDisplayName) {
		super(file);
		this.newContent = newContent;
		this.packageDisplayName = packageDisplayName;
	}

	@Override
	public String getPackageDisplayName() {
		return packageDisplayName;
	}

	@Override
	public void add(SpecSection specElem) {
		// not used.
	}

	@Override
	public String getNewContent() {
		return newContent;
	}

	@Override
	public int getOffsetStart(SpecSection entry) {
		return 0;
	}

	@Override
	public int getOffsetEnd(SpecSection entry) {
		return StringCountUtils.countLines(newContent);
	}

	@Override
	public Collection<SpecSection> getSpecSections() {
		return Collections.emptyList();
	}

}
