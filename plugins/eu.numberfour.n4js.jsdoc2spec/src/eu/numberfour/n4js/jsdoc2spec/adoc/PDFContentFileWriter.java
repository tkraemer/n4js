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

import static eu.numberfour.n4js.jsdoc2spec.adoc.FileSystem.NL;

import java.io.File;

/**
 * The pdfBook.adoc file consists mainly of include macros. Each include macro inlines one adoc file that contains the
 * generated documentation of one N4JS module. Since each of these module adoc file starts with an additional header,
 * the include macro used here omits the first four lines. Also, since each module adoc file uses its own heading
 * hierarchy, the hierarchy level is adjusted by modifying the <code>:leveloffset:</code> variable. The
 * <code>:docinfo:</code> variable is used to include html headers, when compiling the pdfBook.adoc to an html or xml
 * file. The html header specifies the correct stylesheets.<br/>
 * <br/>
 * A typical pdfBook.adoc could look like this:
 *
 * <pre>
:docinfo:
:toc:

= Content of Repository stdlib_api

== Source Folder packages:eu.numberfour.stdlib.model.base.api:src/n4js
=== Package n4/lang
==== Module FixedPoint6
:leveloffset: 3
include::modules/stdlib_api#packages/eu.numberfour.stdlib.model.base.api#src.n4js/n4.lang/FixedPoint6.adoc[lines=4..-1]
:leveloffset: 0

==== Module MathContext
:leveloffset: 3
include::modules/stdlib_api#packages/eu.numberfour.stdlib.model.base.api#src.n4js/n4.lang/MathContext.adoc[lines=4..-1]
:leveloffset: 0

=== Package n4/model
==== Module AttributeDefaultState
:leveloffset: 3
include::modules/stdlib_api#packages/eu.numberfour.stdlib.model.base.api#src.n4js/n4.model/AttributeDefaultState.adoc[lines=4..-1]
:leveloffset: 0
 * </pre>
 */
class PDFContentFileWriter extends IndexEntryWriter {

	@Override
	protected String getFileName() {
		return "stdlib_api_book.adoc";
	}

	@Override
	protected void appendEntry(IndexEntry ie) {
		if (lastIE == null)
			printHeading(strb, ie);

		if (lastIE != null && lastIE.adocPath.equals(ie.adocPath))
			return; // one include per adoc file

		if (ie.project.endsWith("tests"))
			return; // don't include tests into the docu

		boolean superDirChanged = false;
		if (superDirChanged || !isEqualSourceFolder(ie, lastIE)) {
			superDirChanged = true;

			strb.append("== Source Folder ");
			strb.append(ie.path);
			strb.append(":");
			strb.append(ie.project);
			strb.append(":");
			strb.append(ie.folder);
			strb.append(NL);
		}
		if (superDirChanged || !isEqualPackage(ie, lastIE)) {
			superDirChanged = true;

			strb.append("=== Package ");
			strb.append(ie.packageName);
			strb.append(NL);
		}

		appendElement(ie);
	}

	private boolean isEqualSourceFolder(IndexEntry ie1, IndexEntry ie2) {
		if (ie1 == null || ie2 == null)
			return false;

		boolean equalSourceFolder = true;
		equalSourceFolder &= ie1.path.equals(ie2.path);
		equalSourceFolder &= ie1.project.equals(ie2.project);
		equalSourceFolder &= ie1.folder.equals(ie2.folder);

		return equalSourceFolder;
	}

	private boolean isEqualPackage(IndexEntry ie1, IndexEntry ie2) {
		if (ie1 == null || ie2 == null)
			return false;

		return ie1.packageName.equals(ie2.packageName);
	}

	private void appendElement(IndexEntry ie) {
		strb.append("==== Module ");
		strb.append(ie.moduleName);
		strb.append(NL);
		strb.append("include::");
		strb.append(FileSystem.DIR_MODULES + File.separator + ie.adocPath);
		int headerLength = SpecModuleFile.getHeaderLength();
		strb.append("[leveloffset=3, lines=" + headerLength + "..-1]" + NL);
		strb.append(NL);
	}

	static private void printHeading(StringBuilder strb, IndexEntry ie) {
		strb.append("include::{find}config.adoc[]" + NL);
		strb.append(":docinfodir: ../headers/apiBook" + NL);
		strb.append(":linkattrs:" + NL);
		strb.append(":docinfo:" + NL);
		strb.append(":toc: left");
		strb.append(NL + NL);
		strb.append("= Content of Repository ");
		strb.append(ie.repository);
		strb.append(NL + NL);
	}

}
