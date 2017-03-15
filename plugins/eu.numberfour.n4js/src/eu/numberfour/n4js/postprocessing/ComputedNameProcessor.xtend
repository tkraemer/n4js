/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.postprocessing

import com.google.inject.Singleton
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4GetterDeclaration
import eu.numberfour.n4js.n4JS.N4JSASTUtils
import eu.numberfour.n4js.n4JS.N4SetterDeclaration
import eu.numberfour.n4js.n4JS.PropertyGetterDeclaration
import eu.numberfour.n4js.n4JS.PropertyNameValuePair
import eu.numberfour.n4js.n4JS.PropertySetterDeclaration
import eu.numberfour.n4js.n4JS.TypeDefiningElement
import eu.numberfour.n4js.ts.types.IdentifiableElement
import eu.numberfour.n4js.ts.types.SyntaxRelatedTElement
import eu.numberfour.n4js.utils.EcoreUtilN4
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2

/**
 * Processing of {@link LiteralOrComputedPropertyName}s that have a computed property name, mainly setting property
 * {@link LiteralOrComputedPropertyName#getComputedName() 'computedName'}.
 * <p>
 * For details, see {@link ComputedNameProcessor#processComputedPropertyName(RuleEnvironment, LiteralOrComputedPropertyName, ASTMetaInfoCache, int)}.
 */
@Singleton
class ComputedNameProcessor {

	/**
	 * If the given 'nameDecl' has a computed property name, this method will
	 * <ol>
	 * <li>obtain its expression's compile-time value from the cache (the actual evaluation of the expression happened
	 * in {@link CompileTimeExpressionProcessor}),
	 * <li>derive the actual property name from that value (cf. {@link #getPropertyNameFromExpression(RuleEnvironment, Expression, ASTMetaInfoCache)}),
	 * <li>store this name in 'nameDecl' (for later use), and
	 * <li>store this name in the corresponding TModule element (if such a TModule element exists).
	 * </ol>
	 * <p>
	 * In case the compile-time value of the expression is invalid (i.e. the expression is not a valid compile-time
	 * expression) no actual name will be stored in 'nameDecl' and the corresponding TModule element will be removed
	 * from the TModule, entirely (if such a TModule element exists).
	 */
	def public void processComputedPropertyName(RuleEnvironment G, LiteralOrComputedPropertyName nameDecl,
		ASTMetaInfoCache cache, int indentLevel) {

		if (nameDecl.hasComputedPropertyName) {
			// obtain compile-time value of expression + derive a property name from the value
			val name = getPropertyNameFromExpression(G, nameDecl.expression, cache);
			if (name !== null) {
				// cache the computed name in the LiteralOrComputedPropertyName AST node
				EcoreUtilN4.doWithDeliver(false, [
					nameDecl.computedName = name;
				], nameDecl);
				// set the computed name in the types model element
				val owner = nameDecl.eContainer;
				val typeElem = N4JSASTUtils.getCorrespondingTypeModelElement(owner);
				if (typeElem instanceof IdentifiableElement) {
					EcoreUtilN4.doWithDeliver(false, [
						typeElem.name = name;
					], typeElem);
				}
			} else {
				// invalid name expression (i.e. not a constant expression)
				// -> remove the types model element from the TModule
				// (note: we have to do this for consistency with how the types builder handles elements that are
				// unnamed (usually due to a broken AST): in those cases, the types builder does not create a TModule
				// element)
				val owner = nameDecl.eContainer;
				discardTypeModelElement(owner);
			}
		}
	}

	/**
	 * Returns the property/member name to use for the given expression or <code>null</code> if the expression is not a
	 * valid constant expression.
	 * <p>
	 * IMPLEMENTATION NOTE: we can simply use #toString() on a valid value, even if we have a ValueBoolean, ValueNumber,
	 * or ValueSymbol.
	 * <p>
	 * For undefined, null, NaN, Infinity, booleans, and numbers: they are equivalent to their corresponding string
	 * literal, as illustrated in this snippet:
	 * <pre>
	 *     // plain Javascript
	 *
	 *     var obj = {
	 *         [undefined]: 'a',
	 *         [null]     : 'b',
	 *         41         : 'c',
	 *         [42]       : 'd',
	 *         [false]    : 'e',
	 *         [NaN]      : 'f',
	 *         [Infinity] : 'g'
	 *     };
	 *
	 *     console.log( obj[undefined] === obj['undefined']); // will print true!
	 *     console.log( obj[null]      === obj['null']     ); // will print true!
	 *     console.log( obj[41]        === obj['41']       ); // will print true!
	 *     console.log( obj[42]        === obj['42']       ); // will print true!
	 *     console.log( obj[false]     === obj['false']    ); // will print true!
	 *     console.log( obj[NaN]       === obj['NaN']      ); // will print true!
	 *     console.log( obj[Infinity]  === obj['Infinity'] ); // will print true!
	 * </pre>
	 * <p>
	 * For symbols: the #toString() method in ValueSymbol prepends {@link N4JSLanguageUtils#SYMBOL_IDENTIFIER_PREFIX},
	 * so we can simply use that.
	 */
	def private String getPropertyNameFromExpression(RuleEnvironment G, Expression expr, ASTMetaInfoCache cache) {
		val value = cache.getCompileTimeValue(expr);
		return if (value.valid) {
			value.toString // see API doc for why we can simply use #toString() here
		} else {
			null
		};
	}

	/**
	 * Discards the types model element corresponding to the given AST node. Throws exception is given AST node does not
	 * have a corresponding types model element.
	 */
	def private void discardTypeModelElement(EObject astNode) {
		val elem = N4JSASTUtils.getCorrespondingTypeModelElement(astNode);
		if (elem === null) {
			throw new IllegalArgumentException(
				"given AST node does not have a corresponding type model element to discard");
		}
		if (elem instanceof SyntaxRelatedTElement) {
			elem.astElement = null;
		}
		EcoreUtilN4.doWithDeliver(false, [
			switch (astNode) {
				TypeDefiningElement:
					astNode.definedType = null
				N4FieldDeclaration:
					astNode.definedField = null
				N4GetterDeclaration:
					astNode.definedGetter = null
				N4SetterDeclaration:
					astNode.definedSetter = null
				PropertyNameValuePair:
					astNode.definedField = null
				PropertyGetterDeclaration:
					astNode.definedGetter = null
				PropertySetterDeclaration:
					astNode.definedSetter = null
				// note: PropertyMethodDeclaration is a TypeDefiningElement (handled above)
				default:
					throw new UnsupportedOperationException("switch case missing for: " + astNode)
			};
		], astNode);
		EcoreUtilN4.doWithDeliver(false, [
			EcoreUtil2.remove(elem);
		], elem.eContainer);
	}
}
