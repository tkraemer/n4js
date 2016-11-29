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
package eu.numberfour.n4js.antlr

import com.google.common.io.Files
import com.google.inject.Inject
import eu.numberfour.n4js.antlr.n4js.NoLineTerminatorHandlingInjector
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import org.eclipse.xtext.util.internal.Log
import org.eclipse.xtext.xtext.generator.model.IXtextGeneratorFileSystemAccess
import org.eclipse.xtext.xtext.generator.parser.antlr.AntlrGrammar
import org.eclipse.xtext.xtext.generator.parser.antlr.ContentAssistGrammarNaming
import org.eclipse.xtext.xtext.generator.parser.antlr.GrammarNaming
import org.eclipse.xtext.xtext.generator.parser.antlr.KeywordHelper
import org.eclipse.xtext.xtext.generator.parser.antlr.XtextAntlrGeneratorFragment2

import static eu.numberfour.n4js.antlr.replacements.Replacements.applyReplacement

/**
 * Customization of the {@link XtextAntlrGeneratorFragment2} applying some massaging
 *  of the Parser implementation generated by ANTLR.
 */
@Log
class N4JSAntlrGeneratorFragment2 extends XtextAntlrGeneratorFragment2 {
	
	@Inject GrammarNaming productionNaming
	@Inject ContentAssistGrammarNaming caNaming
	
	override protected generateProductionGrammar() {
		super.generateProductionGrammar()
		
		val extension naming = productionNaming
		val absoluteParserFileName = projectConfig.runtime.srcGen.path + '/' + grammar.getParserGrammar.grammarFileName
		
		massageGrammar(absoluteParserFileName, codeConfig.encoding)
	}
	
	override protected cleanupParserTokensFile(AntlrGrammar lexerGrammar, AntlrGrammar parserGrammar, KeywordHelper helper, IXtextGeneratorFileSystemAccess fsa) {
		if (fsa === projectConfig.runtime.srcGen) {
			super.cleanupParserTokensFile(lexerGrammar, parserGrammar, helper, fsa)
			
		} else if (fsa === projectConfig.genericIde.srcGen) {
			val extension naming = caNaming
			super.normalizeTokens(fsa, grammar.parserGrammar.tokensFileName)
		}
	}
	
	def private void massageGrammar(String absoluteParserFileName, String encoding) {
		try {
			val javaFile = absoluteParserFileName.replaceAll("\\.g$", getParserFileNameSuffix());

			val content = Files.toString(new File(javaFile), Charset.forName(encoding));
			val normalizedContent = content.replace("\r\n", "\n");

			val newContent = fixIdentifierAsKeywordWithEOLAwareness(normalizedContent);

			if (normalizedContent.equals(newContent)) {
				LOG.warn("Replacement not found in " + javaFile);
				// throw new IllegalStateException("Replacement not found in " + javaFile);
			}

			if (!content.equals(newContent)) {
				Files.write(newContent, new File(javaFile), Charset.forName(encoding));
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	def protected String getParserFileNameSuffix() {
		return ".java";
	}

	def protected String getLexerFileNameSuffix() {
		return ".java";
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
	 * 
	 * TODO IDE-2406 clarify/document design decision
	 */
	def private String fixIdentifierAsKeywordWithEOLAwareness(String normalizedContent) {
		val String c1 = applyReplacement(normalizedContent, "ruleNoLineTerminator.java.replacement");
		val String c2 = applyReplacement(c1, "rulePrimaryExpression.java.replacement");
		return c2;
	}
}