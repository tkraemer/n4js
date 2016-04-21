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
package eu.numberfour.n4js.formatting2

import eu.numberfour.n4js.services.N4JSGrammarAccess
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.eclipse.xtext.formatting2.ITextReplacer
import org.eclipse.xtext.formatting2.ITextReplacerContext
import org.eclipse.xtext.formatting2.regionaccess.IHiddenRegion
import org.eclipse.xtext.formatting2.regionaccess.ISemanticRegion
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionExtensions
import org.eclipse.xtext.formatting2.regionaccess.ITextSegment

/**
 * 
 */
@FinalFieldsConstructor class N4JSGenericFormatter {

	val extension N4JSGrammarAccess
	val extension ITextRegionExtensions

	static val PRIO_1 = -10
	static val PRIO_2 = -9

	def void formatColon(EObject semanticElement, extension IFormattableDocument document) {
		for (colon : semanticElement.allRegionsFor.keywords(":")) {
			colon.prepend[noSpace; newLines = 0; priority = PRIO_2].append[oneSpace; priority = PRIO_1]
		}
	}

	/**
	 * Formats whitespace around already present semicolons (;) and inserts new semicolons where the parser expects them.
	 */
	def void formatSemicolons(EObject script, extension IFormattableDocument document) {
		for (region : script.allRegionsFor.ruleCallsTo(semiRule)) {
			val text = region.text
			val previous = region.previousHiddenRegion
			if (text == ";") {

				// there is already a ";" so let's format it
				region.prepend[noSpace; newLines = 0; highPriority]
			} else if (previous.containsComment) {

				// we're behind a comment - insert semicolon before the comment
				val insertAt = region.textRegionAccess.regionForOffset(previous.offset, 0)
				document.addReplacer(new InsertSemi(insertAt, ";"))
			} else if (text.trim.isEmpty) {

				// the text region only contains whitespace, e.g. \n - so let's insert a ;
				document.addReplacer(new InsertSemi(region, ";\n"))
			} else {

				// we probably are the comment, so let's prefix it with ;
				val insertAt = region.textRegionAccess.regionForOffset(region.offset, 0)
				document.addReplacer(new InsertSemi(insertAt, ";"))
			}
		}
	}

	/**
	 * Format whitespace around (), [], and {}
	 * 
	 * When multiple pairs of (), [], or {} open in the same line, indentation is only applied for the innermost pair. 
	 */
	def void formatParenthesisBracketsAndBraces(EObject script, extension IFormattableDocument document) {
		val all = newArrayList()
		all += script.allRegionsFor.keywordPairs("(", ")")
		all += script.allRegionsFor.keywordPairs("{", "}")
		all += script.allRegionsFor.keywordPairs("[", "]")

		val byLine = all.groupBy[key.lineRegions.head.offset]

		for (e : byLine.entrySet) {
			val bracePairsInSameLine = e.value.sortBy[key.offset]
			val outermost = bracePairsInSameLine.head
			val innermost = bracePairsInSameLine.last

			// keep track of lastOpen/lastClose to avoid formatting the HiddenRegion twice if that hidden region 
			// is located directly between two brackets. Then, first.append[] would collide with second.prepend[]. 
			var IHiddenRegion lastOpen = null
			var IHiddenRegion lastClose = null
			for (pair : bracePairsInSameLine) {
				val open = pair.key
				val close = pair.value
				if (open.previousHiddenRegion != lastOpen) {
					open.prepend[noSpace; priority = PRIO_1]
				}
				if (pair !== innermost) {
					open.append[noSpace; priority = PRIO_1]
					close.prepend[noSpace; priority = PRIO_1]
				}
				if (close.nextHiddenRegion != lastClose) {
					if (pair === outermost) {
//						close.appendNewLine(document)
					} else {
						close.append[noSpace; priority = PRIO_1]
					}
				}
				lastOpen = open.nextHiddenRegion
				lastClose = close.previousHiddenRegion
			}

			val open = innermost.key
			val close = innermost.value
			if (open.nextSemanticRegion == close) {
				open.append[noSpace; priority = PRIO_1]
			} // otherwise, if there is a newline before the innermost closing bracket, we want to format the surrounded tokens multiline style. 
			else if (close.previousHiddenRegion.isMultiline) {
				close.prepend[newLine; priority = PRIO_1].appendNewLine(document)
				open.append[newLine; priority = PRIO_1]
				innermost.interior[indent]
				for (comma : open.semanticElement.regionFor.keywords(",")) {
					comma.prepend[noSpace; priority = PRIO_1].append[newLine; priority = PRIO_1]
				}
			} // otherwise, format the tokens into a single line.
			else {
				close.prepend[oneSpace; priority = PRIO_1]
				open.append[oneSpace; priority = PRIO_1]
				for (comma : open.semanticElement.regionFor.keywords(",")) {
					comma.prepend[noSpace; priority = PRIO_1].append[oneSpace; priority = PRIO_1]
				}
			}
		}
	}

	def ISemanticRegion appendNewLine(ISemanticRegion appendAfter, extension IFormattableDocument doc) {
		val semi = appendAfter.nextSemanticRegion?.grammarElement
		if (semi instanceof RuleCall && (semi as RuleCall)?.rule == semiRule) {
			// noop, handled by eu.numberfour.n4js.formatting2.N4JSGenericFormatter.formatSemicolons(EObject, IFormattableDocument)
		} else {
			appendAfter.append[newLine; priority = PRIO_1]
		}
		return appendAfter
	}
}

@Accessors class InsertSemi implements ITextReplacer {
	val ITextSegment region
	val String text

	override createReplacements(ITextReplacerContext context) {
		context.addReplacement(region.replaceWith(text))
		return context
	}
}
