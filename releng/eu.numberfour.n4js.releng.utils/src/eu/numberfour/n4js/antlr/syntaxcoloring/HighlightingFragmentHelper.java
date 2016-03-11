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

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.generator.Naming;
import org.eclipse.xtext.generator.parser.antlr.ex.common.AntlrFragmentHelper;

/**
 */
public class HighlightingFragmentHelper extends AntlrFragmentHelper {

	private final Naming naming;

	/**
	 * @param naming
	 *            the naming conventions
	 */
	public HighlightingFragmentHelper(Naming naming) {
		super(naming);
		this.naming = naming;
	}

	/**
	 * Returns the name of the grammar file for the highlighting parser.
	 */
	public String getHighlightingParserGrammarFileName(Grammar grammar) {
		return naming.basePackageUi(grammar) + ".editor.syntaxcoloring.Internal" + GrammarUtil.getSimpleName(grammar)
				+ "Parser";
	}

}
