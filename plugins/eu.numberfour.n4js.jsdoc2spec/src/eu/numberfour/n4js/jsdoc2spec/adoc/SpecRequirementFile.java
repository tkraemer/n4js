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

import java.util.Arrays;
import java.util.Collection;

import eu.numberfour.n4js.jsdoc2spec.SpecFile;

/**
 * A {@link SpecRequirementFile} contains the documentation of one {@link SpecRequirementSection}.
 */
public class SpecRequirementFile extends SpecFile {
	/**
	 * Use header creates extra lines which are not supposed to be included in cases where a requirement is included in
	 * another document. Hence, the number of header lines has to be known by the including party to exclude them again.
	 */
	static final public boolean USE_HEADER = false;

	final private SpecRequirementSection requirement;
	private String header;
	private int headerLineCount;

	/**
	 * Constructor for a file
	 */
	public SpecRequirementFile(SpecRequirementSection specReq) {
		super(specReq.getFile());
		requirement = specReq;
	}

	/**
	 * Returns the name of the package, that is, the path of the module except the name of the module file.
	 */
	@Override
	public String getPackageDisplayName() {
		return "";
	}

	/**
	 * Returns the name of the module.
	 */
	public String getModuleName() {
		return "";
	}

	/**
	 * Adding additional regions to requirements does not occur.
	 */
	@Override
	public void add(SpecSection specElem) {
		throw new RuntimeException("Missing Implementation");
	}

	@Override
	public Collection<? extends SpecSection> getSpecSections() {
		return Arrays.asList(requirement);
	}

	/**
	 * Returns the content of the generated file.
	 */
	@Override
	public String getNewContent() {
		if (!USE_HEADER)
			return requirement.getGeneratedADocText();
		ensureHeader();
		return header + requirement.getGeneratedADocText();
	}

	/**
	 * Returns the start of the offset of the given {@code entry} in the generated file.
	 */
	@Override
	public int getOffsetStart(SpecSection entry) {
		if (!USE_HEADER)
			return 1; // exclude config file when including this requirement file
		ensureHeader();
		return headerLineCount;
	}

	/**
	 * Returns the end of the offset of the given {@code entry} in the generated file.
	 */
	@Override
	public int getOffsetEnd(SpecSection entry) {
		if (!USE_HEADER)
			return requirement.getGeneratedLineCount();
		ensureHeader();
		return headerLineCount + requirement.getGeneratedLineCount();
	}

	private String ensureHeader() {
		if (header != null)
			return header;

		header = "";
		header += "include::{find}config.adoc[]\n";
		header += "= Requirement " + requirement.getRequirementID();
		header += "\n\n";
		headerLineCount = StringCountUtils.countLines(header);
		return header;
	}
}
