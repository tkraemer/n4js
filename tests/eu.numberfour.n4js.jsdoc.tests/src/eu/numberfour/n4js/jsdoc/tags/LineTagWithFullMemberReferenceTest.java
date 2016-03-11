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
package eu.numberfour.n4js.jsdoc.tags;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.eclipse.emf.common.util.EList;
import org.junit.Test;

import eu.numberfour.n4js.jsdoc.DocletParser;
import eu.numberfour.n4js.jsdoc.TagDictionary;
import eu.numberfour.n4js.jsdoc.dom.ContentNode;
import eu.numberfour.n4js.jsdoc.dom.Doclet;
import eu.numberfour.n4js.jsdoc.dom.FullMemberReference;
import eu.numberfour.n4js.jsdoc.dom.LineTag;

/**
 */
public class LineTagWithFullMemberReferenceTest {

	@SuppressWarnings("javadoc")
	@Test
	public void test_fullRef_simpleName() {
		String in = "/** foo."
				+ "\n * @testee n4/model/collections/DataSet.DataSet.each"
				+ "\n */";
		AbstractLineTagDefinition tagDef = new LineTagWithFullMemberReference("testee");
		DocletParser docletParser = new DocletParser(new TagDictionary<>(Arrays.asList(tagDef)),
				new TagDictionary<AbstractInlineTagDefinition>());
		Doclet doclet = docletParser.parse(in);

		LineTag lineTag = doclet.getLineTags().get(0);

		EList<ContentNode> contents = lineTag.getValueByKey(LineTagWithFullElementReference.REF)
				.getContents();
		FullMemberReference ref = (FullMemberReference) contents.get(0);

		assertEquals("n4/model/collections/DataSet", ref.getModuleName());
		assertEquals("DataSet", ref.getTypeName());
		assertEquals("each", ref.getMemberName());
	}

	@SuppressWarnings("javadoc")
	@Test
	public void test_fullRef_symbolName() {
		String in = "/** foo."
				+ "\n * @testee n4/model/collections/DataSet.DataSet.#iterator"
				+ "\n */";
		AbstractLineTagDefinition tagDef = new LineTagWithFullMemberReference("testee");
		DocletParser docletParser = new DocletParser(new TagDictionary<>(Arrays.asList(tagDef)),
				new TagDictionary<AbstractInlineTagDefinition>());
		Doclet doclet = docletParser.parse(in);

		LineTag lineTag = doclet.getLineTags().get(0);

		EList<ContentNode> contents = lineTag.getValueByKey(LineTagWithFullElementReference.REF)
				.getContents();
		FullMemberReference ref = (FullMemberReference) contents.get(0);

		assertEquals("n4/model/collections/DataSet", ref.getModuleName());
		assertEquals("DataSet", ref.getTypeName());
		assertEquals("#iterator", ref.getMemberName());
	}
}
