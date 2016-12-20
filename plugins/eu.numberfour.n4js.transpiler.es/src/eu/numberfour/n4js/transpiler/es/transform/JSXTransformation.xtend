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
package eu.numberfour.n4js.transpiler.es.transform

import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.PropertyNameValuePair
import eu.numberfour.n4js.transpiler.Transformation
import eu.numberfour.n4js.transpiler.im.IdentifierRef_IM
import eu.numberfour.n4jsx.n4JSX.JSXAttribute
import eu.numberfour.n4jsx.n4JSX.JSXChild
import eu.numberfour.n4jsx.n4JSX.JSXElement
import eu.numberfour.n4jsx.n4JSX.JSXExpression
import eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute
import eu.numberfour.n4jsx.n4JSX.JSXSpreadAttribute
import java.util.List

import static eu.numberfour.n4js.transpiler.TranspilerBuilderBlocks.*

/**
 * 
 */
class JSXTransformation extends Transformation {


	override assertPreConditions() {
	}

	override assertPostConditions() {
	}

	override analyze() {
		// ignore
	}

	override transform() {
		collectNodes(state.im, JSXElement, false).forEach[transformJSXElement];
	}

	def private void transformJSXElement(JSXElement elem) {
		replace(elem, convertJSXElement(elem));
	}
	def private ParameterizedCallExpression convertJSXElement(JSXElement elem) {
		return _CallExpr(
			_PropertyAccessExpr(steFor_React, steFor_createElement),
			(
				#[
					elem.tagNameFromElement,
					convertJSXAttributes(elem.jsxAttributes)
				]
				+ elem.jsxChildren.map[convertJSXChild]
			)
		);
	}

	def private Expression convertJSXChild(JSXChild child) {
		switch(child) {
			JSXElement:
				convertJSXElement(child)
			JSXExpression:
				child.expression
		}
	}

	def private Expression convertJSXAttributes(List<JSXAttribute> attrs) {
		if(attrs.isEmpty) {
			_NULL
		} else {
			val propsSimple = _ObjLit(attrs.filter(JSXPropertyAttribute).map[convertJSXAttribute]);
			val propsSpread = attrs.filter(JSXSpreadAttribute).map[expression].toList;
			if(propsSpread.isEmpty) {
				propsSimple
			} else {
				_CallExpr(_PropertyAccessExpr(steFor_Object,steFor_assign), #[ propsSimple ] + propsSpread)
			}
		}
	}

	def private PropertyNameValuePair convertJSXAttribute(JSXPropertyAttribute attr) {
		_PropertyNameValuePair(
			attr.nameFromPropertyAttribute,
			attr.valueExpressionFromPropertyAttribute)
	}

	def private Expression getTagNameFromElement(JSXElement elem) {
		val nameExpr = elem.jsxElementName.expression;
		if(nameExpr instanceof IdentifierRef_IM) {
			val id = nameExpr.id_IM;
			if(id===null) {
				return _StringLiteral(nameExpr.idAsText);
			}
		}
		return nameExpr;
	}

	def private String getNameFromPropertyAttribute(JSXPropertyAttribute attr) {
		val prop = attr.property;
		if(prop!==null && !prop.eIsProxy) {
			return prop.name;
		}
		return attr.propertyAsText;
	}
	def private Expression getValueExpressionFromPropertyAttribute(JSXPropertyAttribute attr) {
		return attr.jsxAttributeValue ?: _TRUE;
	}
}
