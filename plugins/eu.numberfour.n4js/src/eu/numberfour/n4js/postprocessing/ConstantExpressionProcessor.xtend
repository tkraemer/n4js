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

import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.AdditiveExpression
import eu.numberfour.n4js.n4JS.BinaryLogicalExpression
import eu.numberfour.n4js.n4JS.BooleanLiteral
import eu.numberfour.n4js.n4JS.ConditionalExpression
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.MultiplicativeExpression
import eu.numberfour.n4js.n4JS.NullLiteral
import eu.numberfour.n4js.n4JS.NumericLiteral
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.ParenExpression
import eu.numberfour.n4js.n4JS.RegularExpressionLiteral
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.n4JS.TemplateLiteral
import eu.numberfour.n4js.n4JS.TemplateSegment
import eu.numberfour.n4js.n4JS.UnaryExpression
import eu.numberfour.n4js.ts.types.TEnum
import eu.numberfour.n4js.ts.types.TEnumLiteral
import eu.numberfour.n4js.utils.ConstantValue
import eu.numberfour.n4js.utils.ConstantValue.ValueBoolean
import eu.numberfour.n4js.utils.ConstantValue.ValueNumber
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EObject

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 *
 */
class ConstantExpressionProcessor {

	/**
	 * If the given AST node is an expression that is directly required to be a constant expression (cf.
	 * {@link N4JSLanguageUtils#isRequiredToBeConstantExpression(Expression)}, this method will evaluate the constant
	 * expression and store the evaluation result in the given cache; otherwise, this method will do nothing.
	 * <p>
	 * Current cases of expressions that are required to be constant expressions (according to N4JS language
	 * specification):
	 * <ul>
	 * <li>expressions in computed property names.
	 * </ul>
	 */
	def public void evaluateConstantExpression(RuleEnvironment G, EObject node, ASTMetaInfoCache cache, int indentLevel) {
		if(node instanceof Expression) {
			if(N4JSLanguageUtils.isRequiredToBeConstantExpression(node)) {
				val evalResult = computeValueIfConstantExpression(G, node);
				cache.storeEvaluationResult(node, evalResult);
			}
		}
	}

	/** Make constant expression evaluation publicly available, but <b>ONLY FOR TESTING</b>! */
	def public static ConstantValue ONLY_FOR_TESTING__computeValueIfConstantExpression(RuleEnvironment G, Expression expr) {
		return computeValueIfConstantExpression(G, expr);
	}

	/**
	 * FIXME documentation
	 */
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, Expression expr) {
		return switch(expr) {
			NullLiteral:
				ConstantValue.NULL
			IdentifierRef case N4JSLanguageUtils.isUndefinedLiteral(G, expr):
				ConstantValue.UNDEFINED
			BooleanLiteral:
				ConstantValue.of(expr.isTrue)
			NumericLiteral:
				ConstantValue.of(expr.value)
			StringLiteral:
				ConstantValue.of(expr.value)
			RegularExpressionLiteral:
				null // not a constant expression
			TemplateSegment:
				ConstantValue.of(expr.rawValue)
			ParenExpression:
				return computeValueIfConstantExpression(G, expr.expression)
			default:
				null
		};
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, TemplateLiteral expr) {
		val buff = new StringBuilder;
		for(seg : expr.segments) {
			val segValue = computeValueIfConstantExpression(G, seg);
			if(segValue!==null) {
				buff.append(segValue.toString);
			} else {
				return null;
			}
		}
		return ConstantValue.of(buff.toString);
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, UnaryExpression expr) {
		val value = computeValueIfConstantExpression(G, expr.expression);
		return switch(expr.op) {
			case NOT: if(value instanceof ValueBoolean) ConstantValue.negate(value)
			case POS: if(value instanceof ValueNumber) value
			case NEG: if(value instanceof ValueNumber) ConstantValue.negate(value)
			case VOID: ConstantValue.UNDEFINED
			default: null
		};
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, AdditiveExpression expr) {
		val leftValue = computeValueIfConstantExpression(G, expr.lhs);
		val rightValue = computeValueIfConstantExpression(G, expr.rhs);
		return switch(expr.op) {
			case ADD: ConstantValue.add(leftValue, rightValue)
			case SUB: ConstantValue.subtract(leftValue, rightValue)
		};
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, MultiplicativeExpression expr) {
		val leftValue = computeValueIfConstantExpression(G, expr.lhs);
		val rightValue = computeValueIfConstantExpression(G, expr.rhs);
		return switch(expr.op) {
			case TIMES: ConstantValue.multiply(leftValue, rightValue)
			case DIV: ConstantValue.divide(leftValue, rightValue)
			case MOD: ConstantValue.remainder(leftValue, rightValue)
		};
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, BinaryLogicalExpression expr) {
		val leftValue = computeValueIfConstantExpression(G, expr.lhs);
		val rightValue = computeValueIfConstantExpression(G, expr.rhs);
		return switch(expr.op) {
			case AND: ConstantValue.and(leftValue, rightValue)
			case OR: ConstantValue.or(leftValue, rightValue)
		};
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, ConditionalExpression expr) {
		val value = computeValueIfConstantExpression(G, expr.expression);
		val trueValue = computeValueIfConstantExpression(G, expr.trueExpression);
		val falseValue = computeValueIfConstantExpression(G, expr.falseExpression);
		if(value instanceof ValueBoolean) {
			return if(value.getValue().booleanValue()) {
				trueValue
			} else {
				falseValue
			};
		}
		return null;
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, ParameterizedPropertyAccessExpression expr) {
		// is expr an access to a built-in symbol, e.g. Symbol.iterator?
		val builtInSymbol = G.getAccessedBuiltInSymbol(expr);
		if(builtInSymbol!==null) {
			return ConstantValue.of(builtInSymbol);
		}
		// all other cases:
		val prop = expr.property;
		val propParent = prop?.eContainer;
		return switch(prop) {
			TEnumLiteral case propParent instanceof TEnum && AnnotationDefinition.STRING_BASED.hasAnnotation(propParent as TEnum):
				ConstantValue.of(prop.valueOrName)
// FIXME:
//			TField case prop.const && prop.eResource===expr.eResource: { // only if in same resource!!! (otherwise we would have to store the initExpr's value in the TModule
//				val initExpr = (prop.astElement as N4FieldDeclaration).expression;
//				computeValueIfConstantExpression(G, initExpr)
//			}
			default:
				null
		};
	}
}
