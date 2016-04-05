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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.xtext.generator.parser.antlr.AntlrToolFacade;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

/**
 * Post processor for the generated Antlr grammar that injects code into the generated grammar file.
 *
 * See also <a href="https://raw.github.com/ohboyohboyohboy/antlr3/master/samples/other/ES3.g3">ES3.g3</a> and <a href=
 * "http://git.eclipse.org/c/dltk/org.eclipse.dltk.javascript.git/tree/plugins/org.eclipse.dltk.javascript.parser/src/org/eclipse/dltk/javascript/parser/JS.g"
 * >JS.g</a>
 */
public class AntlrToolFacadeWithInjectedCode extends AntlrToolFacade {

	private final static Logger LOGGER = Logger.getLogger(AntlrToolFacadeWithInjectedCode.class);
	private final List<CodeIntoGrammarInjector> steps = Lists.newArrayList();

	/**
	 * Adds another step to the processing chain.
	 */
	public void addStep(CodeIntoGrammarInjector step) {
		steps.add(step);
	}

	@Override
	public void runWithEncodingAndParams(String grammarFullPath, String explicitEncoding, String... furtherArgs) {
		injectCode(grammarFullPath);
		super.runWithEncodingAndParams(grammarFullPath, explicitEncoding, furtherArgs);
	}

	private void injectCode(String grammarFullPath) {
		LOGGER.info("### inject grammar " + grammarFullPath);

		try {
			String grammarContent = Files.toString(new File(grammarFullPath), Charsets.UTF_8);
			if (grammarFullPath.endsWith("Lexer.g")) {
				grammarContent = processLexerGrammar(grammarContent);
			} else if (grammarFullPath.endsWith("Parser.g")) {
				grammarContent = processParserGrammar(grammarContent);
			} else {
				throw new IllegalArgumentException(grammarFullPath);
			}
			File fileToWrite = new File(grammarFullPath);
			Files.write(grammarContent, fileToWrite, Charsets.UTF_8);
			if (fileToWrite.exists() && fileToWrite.isFile()) {
				LOGGER.info("### grammar file check OK :: " + fileToWrite.getAbsolutePath());
			} else {
				LOGGER.error("### grammar file check NOT OK :: " + fileToWrite.getAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private String processParserGrammar(String grammarContent) {
		String result = grammarContent;
		for (CodeIntoGrammarInjector step : steps) {
			result = step.processParserGrammar(result);
		}
		return result;
	}

	private String processLexerGrammar(String grammarContent) {
		String result = grammarContent;
		for (CodeIntoGrammarInjector step : steps) {
			result = step.processLexerGrammar(result);
		}
		return result;
	}

}
