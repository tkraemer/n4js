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
package eu.numberfour.n4js.antlr;

import static eu.numberfour.n4js.antlr.replacements.Replacements.applyReplacement;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtend.expression.ExecutionContextImpl;
import org.eclipse.xtext.Grammar;

import com.google.common.io.Files;

import eu.numberfour.n4js.antlr.delimiters.AntlrGeneratorFragment;
import eu.numberfour.n4js.antlr.n4js.NoLineTerminatorHandlingInjector;

/**
 * We have to improve keyword handling, such that keywords are allowed with unicode chars.
 */
public class AntlrGeneratorWithCustomKeywordLogic extends AntlrGeneratorFragment {

	private final static Logger LOGGER = Logger.getLogger(AntlrGeneratorWithCustomKeywordLogic.class);

	@Override
	public void generate(final Grammar grammar, XpandExecutionContext ctx) {
		// Xtend AOP feature: replace specified extensions with custom
		// implementation for unicode keyword lexer rules
		ExecutionContextImpl casted = (ExecutionContextImpl) ctx;
		casted.registerExtensionAdvices("eu::numberfour::n4js::antlr::UnicodeAwareKeywords");

		super.generate(grammar, ctx);
	}

	private void massageGrammar(String absoluteParserFileName, String encoding) {
		try {
			String javaFile = absoluteParserFileName.replaceAll("\\.g$", getParserFileNameSuffix());

			String content = Files.toString(new File(javaFile), Charset.forName(encoding));
			String normalizedContent = content.replace("\r\n", "\n");

			String newContent = fixIdentifierAsKeywordWithEOLAwareness(normalizedContent);

			if (normalizedContent.equals(newContent)) {
				LOGGER.warn("Replacement not found in " + javaFile);
				// throw new IllegalStateException("Replacement not found in " + javaFile);
			}

			if (!content.equals(newContent)) {
				Files.write(newContent, new File(javaFile), Charset.forName(encoding));
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is part of the {@link NoLineTerminatorHandlingInjector}, fixing a problem with line-ending aware tokens like
	 * 'async'.
	 * <p>
	 * The problem likely (I do not know for sure) is as follows: In some cases, keywords are not reserved and may be
	 * used as identifiers as well. Examples are some N4JS specific keywords such as 'abstract' or 'project'. Now, these
	 * terminals can only occur as keywords in some very specific locations. This is different for 'async': Since it can
	 * be used as a modifier for function expressions or arrow functions, it can occur almost everywhere in the code. In
	 * order to distinguish keyword 'async' from its use as an identifier, no line terminator must follow in the first
	 * case. There are actually two problems: First, it is not directly possible to define "no line terminator here" in
	 * the Xtext grammar. Second, checking for the line terminator and the whole keyword vs. identifier handling seems
	 * to disable automatic semicolon insertation, at least partially. The result of the later problem is pretty bad:
	 * The parser may recognize an expression statement or identifier, but in the AST null is inserted instead. I assume
	 * this is because the ANTLR parser is not aware of the line-termintor-handling which partially disables its
	 * backtracking.
	 * <p>
	 * The solution is three fold:
	 * <ol>
	 * <li>In {@link NoLineTerminatorHandlingInjector} we rewrite the lexer rule to always reject the dummy token we
	 * need for the parser rule
	 * <li>Here, we rewrite the parser rule to fail if a line terminator was found
	 * <li>Also, we adapt the primary expression rule, to continue in case an 'async' keyword has been found.
	 * </ol>
	 * Some remarks on debugging: Note that the stack trace (and the debugger) have a limit of 64k lines. Then, the
	 * counter starts again! So breakpoints and stepping may be useless if you want to debug code after 64k lines.
	 */
	private String fixIdentifierAsKeywordWithEOLAwareness(String normalizedContent) {
		String c1 = applyReplacement(normalizedContent, "ruleNoLineTerminator.java.replacement");
		String c2 = applyReplacement(c1, "rulePrimaryExpression.java.replacement");
		return c2;
	}

	@Override
	protected void suppressWarnings(String absoluteLexerGrammarFileName, String absoluteParserGrammarFileName,
			Charset encoding) {
		super.suppressWarnings(absoluteLexerGrammarFileName, absoluteParserGrammarFileName, encoding);
		massageGrammar(absoluteParserGrammarFileName, encoding.name());
	}

}
