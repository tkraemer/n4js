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
package eu.numberfour.n4js.tests.syntaxcoloring

import com.google.inject.Inject
import eu.numberfour.n4js.N4JSUiInjectorProvider
import org.eclipse.jface.text.rules.ITokenScanner
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.ui.editor.model.XtextDocument
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert
import org.eclipse.jface.text.rules.Token

/**
 */
@RunWith(XtextRunner)
@InjectWith(N4JSUiInjectorProvider)
class TemplateAwareTokenScannerPluginTest extends Assert {
	@Inject XtextDocument document
	@Inject ITokenScanner scanner

	def void tokenize(String input) {
		document.set(input)
		scanner.setRange(document, 0, input.length)
	}

	@Test
	def void testNoSubstLiteral() {
		tokenize("`text`")
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(0, scanner.tokenOffset)
		assertEquals("`text`".length, scanner.tokenLength)
		assertSame(Token.EOF, scanner.nextToken)
	}

	@Test
	def void testTemplateStart() {
		tokenize("`text${")
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(0, scanner.tokenOffset)
		assertEquals("`text".length, scanner.tokenLength)
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals("`text".length, scanner.tokenOffset)
		assertEquals("${".length, scanner.tokenLength)
		assertSame(Token.EOF, scanner.nextToken)
	}

	@Test
	def void testTemplateEmptySubst() {
		tokenize("`${}text`")
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(0, scanner.tokenOffset)
		assertEquals(1, scanner.tokenLength)
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(1, scanner.tokenOffset)
		assertEquals("${".length, scanner.tokenLength)
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(3, scanner.tokenOffset)
		assertEquals("}".length, scanner.tokenLength)
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(4, scanner.tokenOffset)
		assertEquals("text`".length, scanner.tokenLength)
		assertSame(Token.EOF, scanner.nextToken)
	}

	@Test
	def void testTemplateSubstSubst() {
		tokenize("`${a}text`")
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(0, scanner.tokenOffset)
		assertEquals(1, scanner.tokenLength)
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(1, scanner.tokenOffset)
		assertEquals("${".length, scanner.tokenLength)
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(3, scanner.tokenOffset)
		assertEquals("a".length, scanner.tokenLength)
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(4, scanner.tokenOffset)
		assertEquals("}".length, scanner.tokenLength)
		assertNotSame(Token.EOF, scanner.nextToken)
		assertEquals(5, scanner.tokenOffset)
		assertEquals("text`".length, scanner.tokenLength)
		assertSame(Token.EOF, scanner.nextToken)
	}
}
