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
package eu.numberfour.n4js.ui.validation;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.Token;
import org.eclipse.xtext.parser.antlr.ITokenDefProvider;
import org.eclipse.xtext.parser.antlr.Lexer;
import org.eclipse.xtext.parser.antlr.TokenTool;

import com.google.inject.Inject;

import eu.numberfour.n4js.services.N4JSGrammarAccess;

/**
 * A N4JS identifier validator which is based on the generated lexer.
 */
public final class N4JSLexerBasedIdentifierValidator {
	@Inject
	private Lexer n4jsLexer;
	@Inject
	private N4JSGrammarAccess grammerAccess;
	@Inject
	private ITokenDefProvider tokenDefProvider;

	/**
	 * Instantiates a new N4JSLexerBasedIdentifierValidator.
	 */
	public N4JSLexerBasedIdentifierValidator() {
	}

	/**
	 * Returns true if the given string is a valid N4JS identifier.
	 *
	 */
	public boolean isValidIdentifier(String identifier) {
		n4jsLexer.setCharStream(new ANTLRStringStream(identifier));

		// Retrieve IDENTIFIER rule name
		String identifierRuleName = grammerAccess.getIDENTIFIERRule().getName();

		// Get lexer rule name of the first token
		Token firstToken = n4jsLexer.nextToken();
		String lexerTokenName = tokenDefProvider.getTokenDefMap().get(firstToken.getType());

		if (lexerTokenName == null) {
			return false;
		}

		String lexerRuleName = TokenTool.getLexerRuleName(lexerTokenName);

		return lexerRuleName.equals(identifierRuleName.toUpperCase())
				&& firstToken.getText().equals(identifier);
	}
}