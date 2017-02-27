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

import com.google.inject.Inject
import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.AdditiveExpression
import eu.numberfour.n4js.n4JS.BinaryLogicalExpression
import eu.numberfour.n4js.n4JS.BooleanLiteral
import eu.numberfour.n4js.n4JS.ConditionalExpression
import eu.numberfour.n4js.n4JS.ExportedVariableDeclaration
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.MultiplicativeExpression
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.NullLiteral
import eu.numberfour.n4js.n4JS.NumericLiteral
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.ParenExpression
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.n4JS.TemplateLiteral
import eu.numberfour.n4js.n4JS.TemplateSegment
import eu.numberfour.n4js.n4JS.UnaryExpression
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.ts.types.IdentifiableElement
import eu.numberfour.n4js.ts.types.SyntaxRelatedTElement
import eu.numberfour.n4js.ts.types.TConstableElement
import eu.numberfour.n4js.ts.types.TEnum
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TypesPackage
import eu.numberfour.n4js.utils.ConstantValue
import eu.numberfour.n4js.utils.ConstantValue.ValueBoolean
import eu.numberfour.n4js.utils.ConstantValue.ValueNumber
import eu.numberfour.n4js.utils.EcoreUtilN4
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import eu.numberfour.n4js.validation.N4JSElementKeywordProvider
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 *
 */
class ConstantExpressionProcessor {

	@Inject
	private N4JSElementKeywordProvider keywordProvider;

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
				val evalResult = computeValueIfConstantExpression(G, node, keywordProvider);
				cache.storeEvaluationResult(node, evalResult);
			} else {
				// optional cases:
				val parent = node.eContainer;
				if(parent instanceof ExportedVariableDeclaration) {
					storeValueOfExpressionInTModule(G, parent.definedVariable, node);
				} else if(parent instanceof N4FieldDeclaration) {
					storeValueOfExpressionInTModule(G, parent.definedField, node);
				}
			}
		}
	}

	def private void storeValueOfExpressionInTModule(RuleEnvironment G, TConstableElement elem, Expression expr) {
		if(elem!==null && elem.const) {
			val value = computeValueIfConstantExpression(G, expr, keywordProvider);
			val valueStr = ConstantValue.serialize(value);
			EcoreUtilN4.doWithDeliver(false, [
				elem.value = valueStr;
			], elem);
		}
	}

	/** Make constant expression evaluation publicly available, but <b>ONLY FOR TESTING</b>! */
	def public static ConstantValue ONLY_FOR_TESTING__computeValueIfConstantExpression(RuleEnvironment G, Expression expr, N4JSElementKeywordProvider keywordProvider) {
		return computeValueIfConstantExpression(G, expr, keywordProvider);
	}

	/**
	 * FIXME documentation
	 */
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, Expression expr, N4JSElementKeywordProvider keywordProvider) {
		return switch(expr) {
			NullLiteral:
				ConstantValue.NULL
			BooleanLiteral:
				ConstantValue.of(expr.isTrue)
			NumericLiteral:
				ConstantValue.of(expr.value)
			StringLiteral:
				ConstantValue.of(expr.value)
			TemplateSegment:
				ConstantValue.of(expr.rawValue)
			ParenExpression:
				computeValueIfConstantExpression(G, expr.expression, keywordProvider)
			default:
				ConstantValue.error(keywordProvider.keywordWithIndefiniteArticle(expr) + " is never a compile-time expression", expr)
		};
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, IdentifierRef expr, N4JSElementKeywordProvider keywordProvider) {
		if(N4JSLanguageUtils.isUndefinedLiteral(G, expr)) {
			return ConstantValue.UNDEFINED;
		}
		return computeValueIfConstantFieldOrVariable(G, expr.eResource, expr.id, expr, keywordProvider);
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, TemplateLiteral expr, N4JSElementKeywordProvider keywordProvider) {
		val buff = new StringBuilder;
		val invalidValues = newArrayList;
		for(seg : expr.segments) {
			val segValue = computeValueIfConstantExpression(G, seg, keywordProvider);
			if(segValue.valid) {
				buff.append(segValue.toString);
			} else {
				invalidValues += segValue;
			}
		}
		if(!invalidValues.empty) {
			return ConstantValue.combineErrors(invalidValues);
		}
		return ConstantValue.of(buff.toString);
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, UnaryExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val value = computeValueIfConstantExpression(G, expr.expression, keywordProvider);
		return switch(expr.op) {
			case NOT: if(value instanceof ValueBoolean) ConstantValue.negate(value, expr.expression)
			case POS: if(value instanceof ValueNumber) value
			case NEG: if(value instanceof ValueNumber) ConstantValue.negate(value, expr.expression)
			case VOID: ConstantValue.UNDEFINED
			default: ConstantValue.error("invalid operator: " + expr.op, expr)
		};
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, AdditiveExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val leftValue = computeValueIfConstantExpression(G, expr.lhs, keywordProvider);
		val rightValue = computeValueIfConstantExpression(G, expr.rhs, keywordProvider);
		return switch(expr.op) {
			case ADD: ConstantValue.add(leftValue, rightValue, expr)
			case SUB: ConstantValue.subtract(leftValue, rightValue, expr.lhs, expr.rhs)
			default: ConstantValue.error("invalid operator: " + expr.op, expr)
		};
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, MultiplicativeExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val leftValue = computeValueIfConstantExpression(G, expr.lhs, keywordProvider);
		val rightValue = computeValueIfConstantExpression(G, expr.rhs, keywordProvider);
		return switch(expr.op) {
			case TIMES: ConstantValue.multiply(leftValue, rightValue, expr.lhs, expr.rhs)
			case DIV: ConstantValue.divide(leftValue, rightValue, expr.lhs, expr.rhs)
			case MOD: ConstantValue.remainder(leftValue, rightValue, expr.lhs, expr.rhs)
			default: ConstantValue.error("invalid operator: " + expr.op, expr)
		};
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, BinaryLogicalExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val leftValue = computeValueIfConstantExpression(G, expr.lhs, keywordProvider);
		val rightValue = computeValueIfConstantExpression(G, expr.rhs, keywordProvider);
		return switch(expr.op) {
			case AND: ConstantValue.and(leftValue, rightValue, expr.lhs, expr.rhs)
			case OR: ConstantValue.or(leftValue, rightValue, expr.lhs, expr.rhs)
			default: ConstantValue.error("invalid operator: " + expr.op, expr)
		};
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, ConditionalExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val value = computeValueIfConstantExpression(G, expr.expression, keywordProvider);
		val trueValue = computeValueIfConstantExpression(G, expr.trueExpression, keywordProvider);
		val falseValue = computeValueIfConstantExpression(G, expr.falseExpression, keywordProvider);
		val error = ConstantValue.combineErrors(
			ConstantValue.requireValueType(value, ValueBoolean, "condition must be a boolean", expr.expression),
			trueValue, falseValue);
		if(error!==null) {
			return error;
		}
		return if((value as ValueBoolean).getValue()) trueValue else falseValue;
	}
	def private static dispatch ConstantValue computeValueIfConstantExpression(RuleEnvironment G, ParameterizedPropertyAccessExpression expr) {
		// is expr an access to a built-in symbol, e.g. Symbol.iterator?
		// NOTES:
		// 1) we would like to simply call RuleEnvironmentExtensions#getAccessedBuiltInSymbol(), but that method would
		//    invoke expr.getProperty() which, in turn, would trigger type inference; since we want to handle computed
		//    property names in an up-front step and not as part of the main AST traversal, we must avoid triggering
		//    type inference and hence cannot use RuleEnvironmentExtensions#getAccessedBuiltInSymbol().
		// 2) the following logic must be kept aligned to RuleEnvironmentExtensions#getAccessedBuiltInSymbol()!
		val targetExpr = expr.target;
		val targetElem = if(targetExpr instanceof IdentifierRef) targetExpr.id;
		val sym = G.symbolObjectType;
		if(targetElem===sym) {
			val propName = expr.propertyAsText;
			val memberInSym = sym.ownedMembers.filter(TField).findFirst[static && name==propName];
			if(memberInSym!==null) {
				return ConstantValue.of(memberInSym);
			}
		}
		if(targetElem instanceof TEnum) {
			if(AnnotationDefinition.STRING_BASED.hasAnnotation(targetElem)) {
				val propName = expr.propertyAsText;
				val litInEnum = targetElem.literals.findFirst[name==propName];
				if(litInEnum!==null) {
					return ConstantValue.of(litInEnum.valueOrName);
				}
			}
		}
		// all other cases:
		return ConstantValue.error("only references to enum literals or built-in symbols allowed", expr);
/*
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
			TField case prop.const:
				computeValueIfConstantFieldOrVariable(G, expr.eResource, prop)
			default:
				null
		};
*/
	}

	def private static ConstantValue computeValueIfConstantFieldOrVariable(RuleEnvironment G, Resource currentResource,
		IdentifiableElement targetElem, EObject astNodeForErrorMessage, N4JSElementKeywordProvider keywordProvider) {

		val targetElemIsConst = switch(targetElem) {
			TConstableElement: targetElem.const
			N4FieldDeclaration: targetElem.const
			VariableDeclaration: targetElem.const
		};
		if(!targetElemIsConst) {
			return ConstantValue.error(keywordProvider.keyword(targetElem) + " " + targetElem.name + " is not const", astNodeForErrorMessage);
		}
		
		if(targetElem.eResource===currentResource || hasLoadedASTElement(targetElem)) {
			// 'targetElem' is in same resource OR is in a different resource that already has a fully-loaded AST
			// -> compute value from the initializer expression of 'targetElem'
			val astNodeOfTargetElem = if(targetElem instanceof SyntaxRelatedTElement) {
				targetElem.astElement // NOTE: this will never trigger demand-loading of an AST, because above we ensured that
				// we are still in 'currentResource' OR method #hasLoadedASTElement() has returned true
			} else {
				targetElem // here we simply assume that elem is already an AST node
			};
			val expressionOfTargetElem = switch(astNodeOfTargetElem) {
				N4FieldDeclaration: astNodeOfTargetElem.expression
				VariableDeclaration: astNodeOfTargetElem.expression
			};
			if(expressionOfTargetElem!==null) {
				val valueOfTargetElem = computeValueIfConstantExpression(G, expressionOfTargetElem, keywordProvider);
				if(!valueOfTargetElem.valid) {
					return ConstantValue.error(keywordProvider.keyword(targetElem) + " " + targetElem.name
						+ " is const but does not have a compile-time expression as initializer", astNodeForErrorMessage);
				}
				return valueOfTargetElem;
			}
		} else {
			// 'targetElem' is in another resource with an AST proxy
			// -> read value from TModule to avoid demand-loading of AST
			if(targetElem instanceof TConstableElement) {
				val valueOfTargetElem = ConstantValue.deserialize(targetElem.value);
				if(valueOfTargetElem===null) {
					return ConstantValue.error(keywordProvider.keyword(targetElem) + " " + targetElem.name
						+ " is const but does not have a compile-time expression as initializer", astNodeForErrorMessage);
				}
				return valueOfTargetElem;
			}
		}
		return ConstantValue.error("only references to const variables with a compile-time expression as initializer are allowed", astNodeForErrorMessage);
	}

	/**
	 * Tells if given element has an AST element in an already loaded AST (i.e. it is safe to invoke method
	 * {@code #getASTElement()} without triggering a demand-load of the AST).
	 */
	def private static boolean hasLoadedASTElement(IdentifiableElement elem) {
		val astElemNonResolved = if(elem instanceof SyntaxRelatedTElement) {
			elem.eGet(TypesPackage.eINSTANCE.syntaxRelatedTElement_AstElement, false) as EObject
		};
		return astElemNonResolved!==null && !astElemNonResolved.eIsProxy;
	}
}
