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
package eu.numberfour.n4js.antlr.delimiters;

import java.nio.charset.Charset;

import eu.numberfour.n4js.antlr.internal.AntlrCodeQualityHelper;

/**
 * A {@link org.eclipse.xtext.generator.parser.antlr.ex.ca.ContentAssistParserGeneratorFragment} with support for
 * encoding and custom line breaks.
 */
public class ContentAssistParserGeneratorFragment extends
org.eclipse.xtext.generator.parser.antlr.ex.ca.ContentAssistParserGeneratorFragment {

	@Override
	protected void suppressWarnings(String absoluteLexerGrammarFileName, String absoluteParserGrammarFileName,
			Charset encoding) {
		super.suppressWarnings(absoluteLexerGrammarFileName, absoluteParserGrammarFileName, encoding);
		String javaParserFileName = absoluteParserGrammarFileName.replaceAll("\\.g$", getParserFileNameSuffix());
		AntlrCodeQualityHelper.stripUnnecessaryComments(
				absoluteLexerGrammarFileName.replaceAll("\\.g$", getLexerFileNameSuffix()),
				javaParserFileName,
				encoding);
		AntlrCodeQualityHelper.removeDuplicateBitsets(javaParserFileName, encoding);
	}

	@Override
	protected String getTemplate() {
		return org.eclipse.xtext.generator.parser.antlr.ex.ca.ContentAssistParserGeneratorFragment.class.getName()
				.replaceAll("\\.", "::");
	}

}
