/*
 * generated by Xtext
 */
package eu.numberfour.n4js.formatting2;

import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.ImportDeclaration
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.N4InterfaceDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.services.N4JSGrammarAccess
import eu.numberfour.n4js.ts.formatting2.TypeExpressionsFormatter
import org.eclipse.xtext.formatting2.IFormattableDocument

import static eu.numberfour.n4js.formatting2.N4JSFormatterPreferenceKeys.*

class N4JSFormatter extends TypeExpressionsFormatter {

	@Inject extension N4JSGrammarAccess

	def dispatch void format(Script script, extension IFormattableDocument document) {
		val extension generic = new N4JSGenericFormatter(_n4JSGrammarAccess, textRegionExtensions)
		if (getPreference(FORMAT_PARENTHESIS)) {
			script.formatParenthesisBracketsAndBraces(document)
		}
		script.formatSemicolons(document)
		script.formatColon(document)

		for (element : script.scriptElements) {
			element.append[setNewLines(1, 1, 2); autowrap]
			element.format
		}
	}

	def dispatch format(N4ClassDeclaration clazz, extension IFormattableDocument document) {
		for (member : clazz.ownedMembersRaw) {
			member.append[setNewLines(1, 1, 2)]
			member.format
		}
	}

	def dispatch format(N4InterfaceDeclaration clazz, extension IFormattableDocument document) {
		for (member : clazz.ownedMembersRaw) {
			member.append[setNewLines(1, 1, 2)]
			member.format
		}
	}

	def dispatch format(N4MethodDeclaration method, extension IFormattableDocument document) {
		method.body.regionFor.keyword("{").prepend[oneSpace; newLines = 0]
		for (child : method.eContents) {
			child.format;
		}
	}

	def dispatch void format(ParameterizedPropertyAccessExpression exp, extension IFormattableDocument document) {
		exp.regionFor.keyword(".").prepend[noSpace].append[noSpace; autowrap]
		exp.target.format
	}

	def dispatch void format(ImportDeclaration decl, extension IFormattableDocument document) {
		decl.regionFor.keyword("{").prepend[oneSpace].append[noSpace]
		decl.regionFor.keyword("}").prepend[noSpace].append[oneSpace; newLines = 0]
	}

}
