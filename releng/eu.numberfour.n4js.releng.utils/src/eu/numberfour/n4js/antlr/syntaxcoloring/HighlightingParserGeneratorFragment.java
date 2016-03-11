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
package eu.numberfour.n4js.antlr.syntaxcoloring;

import java.io.IOException;
import java.nio.charset.Charset;

import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.generator.Generator;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.generator.NewlineNormalizer;
import org.eclipse.xtext.generator.parser.antlr.ex.common.AbstractAntlrGeneratorFragmentEx;
import org.eclipse.xtext.generator.parser.antlr.ex.common.AntlrFragmentHelper;
import org.eclipse.xtext.generator.parser.antlr.ex.common.KeywordHelper;
import org.eclipse.xtext.generator.parser.antlr.postProcessing.SuppressWarningsProcessor;

/**
 * Converts the Xtext grammar to an Antlr grammar that does allow to transform consumed token types to other token
 * types.
 */
public class HighlightingParserGeneratorFragment extends AbstractAntlrGeneratorFragmentEx {

	@Override
	public void registerNaming(Naming naming) {
		super.registerNaming(naming);
		super.setFragmentHelper(new HighlightingFragmentHelper(naming));
	}

	@Override
	public void setFragmentHelper(AntlrFragmentHelper fragmentHelper) {
		throw new UnsupportedOperationException();
	}

	@Override
	public HighlightingFragmentHelper getFragmentHelper() {
		return (HighlightingFragmentHelper) super.getFragmentHelper();
	}

	@Override
	public void generate(final Grammar grammar, XpandExecutionContext ctx) {
		KeywordHelper helper = new KeywordHelper(grammar, getOptions().isIgnoreCase());
		super.generate(grammar, ctx);
		final String srcGenPath = ctx.getOutput().getOutlet(Generator.SRC_GEN_UI).getPath();
		final String encoding = getEncoding(ctx, Generator.SRC_GEN_UI);
		final String lexerBaseFileName = ctx.getOutput().getOutlet(Generator.SRC_GEN).getPath() + "/"
				+ getFragmentHelper().getLexerGrammarFileName(grammar).replace('.', '/');
		String libPath = lexerBaseFileName.substring(0, lexerBaseFileName.lastIndexOf('/'));
		String absoluteLexerFileName = lexerBaseFileName + ".g";

		String absoluteParserFileName = srcGenPath + "/"
				+ getFragmentHelper().getHighlightingParserGrammarFileName(grammar).replace('.', '/') + ".g";
		addAntlrParam("-fo");
		addAntlrParam(absoluteParserFileName.substring(0, absoluteParserFileName.lastIndexOf('/')));
		addAntlrParam("-lib");
		addAntlrParam(libPath);
		getAntlrTool().runWithEncodingAndParams(absoluteParserFileName, encoding, getAntlrParams());
		Charset charset = Charset.forName(encoding);
		simplifyUnorderedGroupPredicatesIfRequired(grammar, absoluteParserFileName, charset);
		splitParserAndLexerIfEnabled(absoluteLexerFileName, absoluteParserFileName, charset);
		String generatedParserClass = absoluteParserFileName.replaceAll("\\.g$", getParserFileNameSuffix());
		suppressWarningsImpl(generatedParserClass, charset);
		normalizeLineDelimitersImpl(generatedParserClass, charset);
		normalizeTokens(absoluteParserFileName, charset);
		helper.discardHelper(grammar);
	}

	@Override
	protected void splitLexerClassFile(String filename, Charset encoding) throws IOException {
		// nothing to do
	}

	private void suppressWarningsImpl(String javaFile, Charset encoding) {
		String content = readFileIntoString(javaFile, encoding);
		content = new SuppressWarningsProcessor().process(content);
		writeStringIntoFile(javaFile, content, encoding);
	}

	private void normalizeLineDelimitersImpl(String textFile, Charset encoding) {
		String content = readFileIntoString(textFile, encoding);
		content = new NewlineNormalizer(getLineDelimiter()) {
			// Antlr tries to outsmart us by using a line length that depends on the system
			// line delimiter when it splits a very long String (encoded DFA) into a
			// string concatenation
			// Here we join these lines again.
			@Override
			public String normalizeLineDelimiters(CharSequence cs) {
				String result = super.normalizeLineDelimiters(cs);
				result = result.replaceAll("\"\\+\\n\\s+\"", "");
				return result;
			}
		}.normalizeLineDelimiters(content);
		writeStringIntoFile(textFile, content, encoding);
	}

	@Override
	public void checkConfiguration(Issues issues) {
		super.checkConfiguration(issues);
		if (getOptions().isBacktrackLexer() && getOptions().isIgnoreCase())
			issues.addError("Backtracking lexer and ignorecase cannot be combined for now.");
	}

}
