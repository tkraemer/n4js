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
package eu.numberfour.n4js.n4mf.formatting

import com.google.inject.Inject
import eu.numberfour.n4js.n4mf.services.N4MFGrammarAccess
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter
import org.eclipse.xtext.formatting.impl.FormattingConfig
import org.eclipse.xtext.GrammarUtil
import org.eclipse.xtext.Keyword

/**
 * This class contains custom formatting description.
 *
 * see : http://www.eclipse.org/Xtext/documentation.html#formatting
 * on how and when to use it
 */
class N4MFFormatter extends AbstractDeclarativeFormatter {

	@Inject extension N4MFGrammarAccess grammarAccess

	override protected void configureFormatting(FormattingConfig c) {

		findKeywordPairs("{", "}").forEach [
			c.setIndentationIncrement.after(first)
			c.setLinewrap.after(first)
			c.setIndentationDecrement.before(second)
			c.setLinewrap.before(second)
		]

		findKeywords(".", "-").forEach[c.setNoSpace().around(it)]
		findKeywords(",", ":").filter[it !== simpleProjectDescriptionAccess.colonKeyword_0_1].forEach[c.setNoSpace().before(it)]
		c.setNoSpace.around(simpleProjectDescriptionAccess.colonKeyword_0_1)
		findKeywords(",").filter[it !== versionConstraintAccess.commaKeyword_0_2_0_0].forEach[c.setLinewrap.after(it)]
		findKeywords("(", "[").forEach[c.setNoSpace().after(it)]
		findKeywords(")", "]").forEach[c.setNoSpace().before(it)]
		c.setNoLinewrap.around(moduleFilterSpecifierAccess.inKeyword_1_0)

		val allLongerKeywords = getGrammar.rules.map[eAllContents.toList].flatten.filter(Keyword).filter[value.length > 1]
		val allAssignments = findAssignments(GrammarUtil.allRules(getGrammarAccess().getGrammar()))

		allLongerKeywords.forEach(keyw|allAssignments.forEach(ass|c.setLinewrap.between(ass, keyw)))
		findKeywords("}").forEach [c.setLinewrap.after(it)]

		c.setLinewrap(0, 1, 2).before(SL_COMMENTRule)
		c.setLinewrap(0, 1, 2).before(ML_COMMENTRule)
		c.setLinewrap(0, 1, 1).after(ML_COMMENTRule)
	}
}
