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
import eu.numberfour.n4js.utils.EcoreUtilN4
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import eu.numberfour.n4js.validation.N4JSElementKeywordProvider
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import eu.numberfour.n4js.utils.CompileTimeValue.ValueBoolean
import eu.numberfour.n4js.utils.CompileTimeValue

/**
 *
 */
class CompileTimeExpressionProcessor {

	@Inject
	private N4JSElementKeywordProvider keywordProvider;

	/**
	 * If the given AST node is an expression that is directly required to be a compile-time expression (cf.
	 * {@link N4JSLanguageUtils#isRequiredToBeCompileTimeExpression(Expression)}, this method will evaluate the
	 * compile-time expression and store the evaluation result in the given cache; otherwise, this method will do
	 * nothing.
	 * <p>
	 * Current cases of expressions that are required to be compile-time expressions (according to N4JS language
	 * specification):
	 * <ul>
	 * <li>expressions in computed property names.
	 * </ul>
	 */
	def public void evaluateCompileTimeExpression(RuleEnvironment G, EObject node, ASTMetaInfoCache cache, int indentLevel) {
		if(node instanceof Expression) {
			if(N4JSLanguageUtils.isRequiredToBeCompileTimeExpression(node)) {
				val evalResult = computeValueIfCompileTimeExpression(G, node, keywordProvider);
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
			val value = computeValueIfCompileTimeExpression(G, expr, keywordProvider);
			val valueStr = CompileTimeValue.serialize(value);
			EcoreUtilN4.doWithDeliver(false, [
				elem.value = valueStr;
			], elem);
		}
	}

	/** Make compile-time expression evaluation publicly available, but <b>ONLY FOR TESTING</b>! */
	def public static CompileTimeValue ONLY_FOR_TESTING__computeValueIfCompileTimeExpression(RuleEnvironment G, Expression expr, N4JSElementKeywordProvider keywordProvider) {
		return computeValueIfCompileTimeExpression(G, expr, keywordProvider);
	}

	/**
	 * FIXME documentation
	 */
	def private static dispatch CompileTimeValue computeValueIfCompileTimeExpression(RuleEnvironment G, Expression expr, N4JSElementKeywordProvider keywordProvider) {
		return switch(expr) {
			NullLiteral:
				CompileTimeValue.NULL
			BooleanLiteral:
				CompileTimeValue.of(expr.isTrue)
			NumericLiteral:
				CompileTimeValue.of(expr.value)
			StringLiteral:
				CompileTimeValue.of(expr.value)
			TemplateSegment:
				CompileTimeValue.of(expr.rawValue)
			ParenExpression:
				computeValueIfCompileTimeExpression(G, expr.expression, keywordProvider)
			default:
				CompileTimeValue.error(keywordProvider.keywordWithIndefiniteArticle(expr) + " is never a compile-time expression", expr)
		};
	}
	def private static dispatch CompileTimeValue computeValueIfCompileTimeExpression(RuleEnvironment G, IdentifierRef expr, N4JSElementKeywordProvider keywordProvider) {
		if(N4JSLanguageUtils.isUndefinedLiteral(G, expr)) {
			return CompileTimeValue.UNDEFINED;
		}
		val id = expr.id;
		if(id!==null && !id.eIsProxy) {
			return computeValueIfConstFieldOrVariable(G, expr.eResource, id, expr, keywordProvider);
		}
		return CompileTimeValue.error();
	}
	def private static dispatch CompileTimeValue computeValueIfCompileTimeExpression(RuleEnvironment G, TemplateLiteral expr, N4JSElementKeywordProvider keywordProvider) {
		val buff = new StringBuilder;
		val invalidValues = newArrayList;
		for(seg : expr.segments) {
			val segValue = computeValueIfCompileTimeExpression(G, seg, keywordProvider);
			if(segValue.valid) {
				buff.append(segValue.toString);
			} else {
				invalidValues += segValue;
			}
		}
		if(!invalidValues.empty) {
			return CompileTimeValue.combineErrors(invalidValues);
		}
		return CompileTimeValue.of(buff.toString);
	}
	def private static dispatch CompileTimeValue computeValueIfCompileTimeExpression(RuleEnvironment G, UnaryExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val value = if(expr.expression!==null) computeValueIfCompileTimeExpression(G, expr.expression, keywordProvider);
		return switch(expr.op) {
			case NOT: CompileTimeValue.invert(value, expr.expression)
			case POS: value
			case NEG: CompileTimeValue.negate(value, expr.expression)
			case VOID: CompileTimeValue.UNDEFINED
			default: CompileTimeValue.error("invalid operator: " + expr.op, expr)
		};
	}
	def private static dispatch CompileTimeValue computeValueIfCompileTimeExpression(RuleEnvironment G, AdditiveExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val lhs = expr.lhs;
		val rhs = expr.rhs;
		val leftValue = if(lhs!==null) computeValueIfCompileTimeExpression(G, lhs, keywordProvider);
		val rightValue = if(rhs!==null) computeValueIfCompileTimeExpression(G, rhs, keywordProvider);
		return switch(expr.op) {
			case ADD: CompileTimeValue.add(leftValue, rightValue, expr)
			case SUB: CompileTimeValue.subtract(leftValue, rightValue, lhs, rhs)
			default: CompileTimeValue.error("invalid operator: " + expr.op, expr)
		};
	}
	def private static dispatch CompileTimeValue computeValueIfCompileTimeExpression(RuleEnvironment G, MultiplicativeExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val lhs = expr.lhs;
		val rhs = expr.rhs;
		val leftValue = if(lhs!==null) computeValueIfCompileTimeExpression(G, lhs, keywordProvider);
		val rightValue = if(rhs!==null) computeValueIfCompileTimeExpression(G, rhs, keywordProvider);
		return switch(expr.op) {
			case TIMES: CompileTimeValue.multiply(leftValue, rightValue, lhs, rhs)
			case DIV: CompileTimeValue.divide(leftValue, rightValue, lhs, rhs)
			case MOD: CompileTimeValue.remainder(leftValue, rightValue, lhs, rhs)
			default: CompileTimeValue.error("invalid operator: " + expr.op, expr)
		};
	}
	def private static dispatch CompileTimeValue computeValueIfCompileTimeExpression(RuleEnvironment G, BinaryLogicalExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val lhs = expr.lhs;
		val rhs = expr.rhs;
		val leftValue = if(lhs!==null) computeValueIfCompileTimeExpression(G, lhs, keywordProvider);
		val rightValue = if(rhs!==null) computeValueIfCompileTimeExpression(G, rhs, keywordProvider);
		return switch(expr.op) {
			case AND: CompileTimeValue.and(leftValue, rightValue, lhs, rhs)
			case OR: CompileTimeValue.or(leftValue, rightValue, lhs, rhs)
			default: CompileTimeValue.error("invalid operator: " + expr.op, expr)
		};
	}
	def private static dispatch CompileTimeValue computeValueIfCompileTimeExpression(RuleEnvironment G, ConditionalExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val condition = expr.expression;
		val trueExpr = expr.trueExpression;
		val falseExpr = expr.falseExpression;
		val conditionValue = if(condition!==null) computeValueIfCompileTimeExpression(G, condition, keywordProvider);
		val trueValue = if(trueExpr!==null) computeValueIfCompileTimeExpression(G, trueExpr, keywordProvider);
		val falseValue = if(falseExpr!==null) computeValueIfCompileTimeExpression(G, falseExpr, keywordProvider);
		val error = CompileTimeValue.combineErrors(
			CompileTimeValue.requireValueType(conditionValue, ValueBoolean, "condition must be a boolean", expr.expression),
			trueValue, falseValue);
		if(error!==null) {
			return error;
		}
		return if((conditionValue as ValueBoolean).getValue()) trueValue else falseValue;
	}
	def private static dispatch CompileTimeValue computeValueIfCompileTimeExpression(RuleEnvironment G, ParameterizedPropertyAccessExpression expr, N4JSElementKeywordProvider keywordProvider) {
		val targetExpr = expr.target;
		val targetElem = if(targetExpr instanceof IdentifierRef) targetExpr.id;
		// A) is 'expr' an access to a built-in symbol, e.g. Symbol.iterator?
		// NOTES:
		// 1) we would like to simply call RuleEnvironmentExtensions#getAccessedBuiltInSymbol(), but that method would
		//    invoke expr.getProperty() which, in turn, would trigger type inference; since we want to handle computed
		//    property names in an up-front step and not as part of the main AST traversal, we must avoid triggering
		//    type inference and hence cannot use RuleEnvironmentExtensions#getAccessedBuiltInSymbol().
		// 2) the following logic must be kept aligned to RuleEnvironmentExtensions#getAccessedBuiltInSymbol()!
		val sym = G.symbolObjectType;
		if(targetElem===sym) {
			val propName = expr.propertyAsText;
			val memberInSym = sym.ownedMembers.filter(TField).findFirst[static && name==propName];
			if(memberInSym!==null) {
				return CompileTimeValue.of(memberInSym);
			}
		}
		// B) is 'expr' an access to the literal of a @StringBased enum?
		if(targetElem instanceof TEnum) {
			if(AnnotationDefinition.STRING_BASED.hasAnnotation(targetElem)) {
				val propName = expr.propertyAsText;
				val litInEnum = targetElem.literals.findFirst[name==propName];
				if(litInEnum!==null) {
					return CompileTimeValue.of(litInEnum.valueOrName);
				}
			}
		}
		// C) all other cases:
		return CompileTimeValue.error("only references to enum literals or built-in symbols allowed", expr);
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

	def private static CompileTimeValue computeValueIfConstFieldOrVariable(RuleEnvironment G, Resource currentResource,
		IdentifiableElement targetElem, EObject astNodeForErrorMessage, N4JSElementKeywordProvider keywordProvider) {

		val targetElemIsConst = switch(targetElem) {
			TConstableElement: targetElem.const
			N4FieldDeclaration: targetElem.const
			VariableDeclaration: targetElem.const
		};
		if(!targetElemIsConst) {
			return CompileTimeValue.error(keywordProvider.keyword(targetElem) + " " + targetElem.name + " is not const", astNodeForErrorMessage);
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
				val valueOfTargetElem = computeValueIfCompileTimeExpression(G, expressionOfTargetElem, keywordProvider);
				if(!valueOfTargetElem.valid) {
					return CompileTimeValue.error(keywordProvider.keyword(targetElem) + " " + targetElem.name
						+ " is const but does not have a compile-time expression as initializer", astNodeForErrorMessage);
				}
				return valueOfTargetElem;
			}
		} else {
			// 'targetElem' is in another resource with an AST proxy
			// -> read value from TModule to avoid demand-loading of AST
			if(targetElem instanceof TConstableElement) {
				val valueOfTargetElem = CompileTimeValue.deserialize(targetElem.value);
				if(valueOfTargetElem===null) {
					return CompileTimeValue.error(keywordProvider.keyword(targetElem) + " " + targetElem.name
						+ " is const but does not have a compile-time expression as initializer", astNodeForErrorMessage);
				}
				return valueOfTargetElem;
			}
		}

		return CompileTimeValue.error("only references to const variables with a compile-time expression as initializer are allowed", astNodeForErrorMessage);
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
