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
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.utils.EcoreUtilN4
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2

/**
 *
 */
@Singleton
class ComputedNameProcessor {

	def public void computedName(RuleEnvironment G, LiteralOrComputedPropertyName nameDecl, ASTMetaInfoCache cache, int indentLevel) {
		if(nameDecl.hasComputedPropertyName) {
			val name = getPropertyNameFromExpression(G, nameDecl.expression);
			if(name!==null) {
				// 1) cache the computed name in the LiteralOrComputedPropertyName AST node
				EcoreUtilN4.doWithDeliver(false, [
					nameDecl.computedName = name;
				], nameDecl);
				// 2) set the computed name in the types model element
				val owner = nameDecl.eContainer;
				val typeElem = N4JSASTUtils.getCorrespondingTypeModelElement(owner);
				if(typeElem instanceof IdentifiableElement) {
					EcoreUtilN4.doWithDeliver(false, [
						typeElem.name = name;
					], typeElem);
				}
			} else {
				// invalid name expression (i.e. not a constant expression)
				// -> remove the types model element from the TModule
				val owner = nameDecl.eContainer;
				discardTypeModelElement(owner);
			}
		}
	}

	def private String getPropertyNameFromExpression(RuleEnvironment G, Expression expr) {
		val value = N4JSLanguageUtils.computeValueIfConstantExpression(G, expr);
		if(value instanceof String) {
			return value;
		} else if(value instanceof TField) {
			return N4JSLanguageUtils.SYMBOL_IDENTIFIER_PREFIX + value.name;
		}
		return null;
	}

	def private void discardTypeModelElement(EObject astNode) {
		val elem = N4JSASTUtils.getCorrespondingTypeModelElement(astNode);
		if(elem===null) {
			throw new IllegalArgumentException("given AST node does not have a type model element to discard");
		}
		if(elem instanceof SyntaxRelatedTElement) {
			elem.astElement = null;
		}
		EcoreUtilN4.doWithDeliver(false, [
			switch(astNode) {
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
