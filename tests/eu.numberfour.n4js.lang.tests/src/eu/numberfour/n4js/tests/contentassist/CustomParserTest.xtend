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
package eu.numberfour.n4js.tests.contentassist

import com.google.inject.Inject
import com.google.inject.Provider
import eu.numberfour.n4js.N4JSInjectorProvider
import eu.numberfour.n4js.ui.contentassist.ContentAssistTokenTypeMapper
import eu.numberfour.n4js.ui.contentassist.CustomN4JSParser
import eu.numberfour.n4js.ui.contentassist.TokenSourceFactory
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.parser.antlr.IUnorderedGroupHelper
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement
import org.junit.runner.RunWith
import org.eclipse.xtext.xbase.lib.util.ReflectExtensions
import org.eclipse.xtext.ide.editor.contentassist.antlr.RequiredRuleNameComputer

/**
 */
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProvider)
class CustomParserTest extends AbstractContentAssistParserTest implements Provider<IUnorderedGroupHelper> {

	@Inject IUnorderedGroupHelper unorderedGroupHelper
	@Inject TokenSourceFactory tokenSourceFactory
	@Inject ContentAssistTokenTypeMapper tokenMapper
	@Inject RequiredRuleNameComputer ruleNameComputer

	@Inject extension ReflectExtensions

	def getParser() {
		return new CustomN4JSParser => [
			it.grammarAccess = grammarAccess
			it.tokenSourceFactory = tokenSourceFactory
			it.unorderedGroupHelper = this
			it.initializeTokenTypes(tokenMapper, grammarAccess)
			it.set('requiredRuleNameComputer', ruleNameComputer)
		]
	}

	override getFollowElements(INode node, int start, int end) {
		parser.getFollowElements(node, start, end, true)
	}

	override getFollowElements(FollowElement prev) {
		parser.getFollowElements(prev)
	}

	override get() {
		return unorderedGroupHelper
	}

}
