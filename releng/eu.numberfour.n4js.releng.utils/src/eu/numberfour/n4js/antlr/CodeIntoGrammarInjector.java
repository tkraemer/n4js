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

/**
 * Common interface for components that process Antlr grammars. Subclasses are added as post processing steps by the MWE
 * script. E.g.,
 *
 * <pre>
 * fragment = eu.numberfour.n4js.antlr.AntlrGeneratorWithCustomKeywordLogic auto-inject {
 *     antlrTool = eu.numberfour.n4js.antlr.AntlrToolFacadeWithInjectedCode {
 *         step = eu.numberfour.n4js.antlr.n4js.AutomaticSemicolonInjector {}
 *         step = eu.numberfour.n4js.antlr.n4js.RegExDisambiguationInjector auto-inject {}
 *         ...
 *     }
 *     ...
 * </pre>
 */
public interface CodeIntoGrammarInjector {

	/**
	 * Process the Antlr parser grammar and return the tuned version.
	 */
	String processParserGrammar(String grammarContent);

	/**
	 * Process the Antlr lexer grammar and return the tuned version.
	 */
	String processLexerGrammar(String grammarContent);

}
