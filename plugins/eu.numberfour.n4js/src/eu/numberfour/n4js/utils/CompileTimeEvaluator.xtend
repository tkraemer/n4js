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
package eu.numberfour.n4js.utils

import com.google.inject.Inject
import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.AdditiveExpression
import eu.numberfour.n4js.n4JS.BinaryLogicalExpression
import eu.numberfour.n4js.n4JS.BooleanLiteral
import eu.numberfour.n4js.n4JS.ConditionalExpression
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.MultiplicativeExpression
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.NullLiteral
import eu.numberfour.n4js.n4JS.NumericLiteral
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.ParenExpression
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.n4JS.TemplateLiteral
import eu.numberfour.n4js.n4JS.TemplateSegment
import eu.numberfour.n4js.n4JS.UnaryExpression
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.postprocessing.ASTMetaInfoCacheHelper
import eu.numberfour.n4js.ts.types.IdentifiableElement
import eu.numberfour.n4js.ts.types.SyntaxRelatedTElement
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TConstableElement
import eu.numberfour.n4js.ts.types.TEnum
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TypesPackage
import eu.numberfour.n4js.utils.CompileTimeValue.EvalError
import eu.numberfour.n4js.utils.CompileTimeValue.ValueBoolean
import eu.numberfour.n4js.utils.CompileTimeValue.ValueInvalid
import eu.numberfour.n4js.validation.N4JSElementKeywordProvider
import eu.numberfour.n4js.validation.validators.N4JSExpressionValidator
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * Helper class to evaluate compile-time expressions.
 */
class CompileTimeEvaluator {

	@Inject
	private N4JSElementKeywordProvider keywordProvider;


	/**
	 * <b>
	 * IMPORTANT: CLIENT CODE SHOULD NOT CALL THIS METHOD!<br>
	 * Instead, read compile-time values from the cache using method {@link ASTMetaInfoCacheHelper#getEvaluationResult(Expression)}.<br>
	 * If the evaluation result of the expression you are interested in is not in the cache, add your use case to method
	 * {@link N4JSLanguageUtils#isProcessedAsCompileTimeExpression(Expression)}. Only expression for which this method
	 * returns <code>true</code> will be evaluated and cached.
	 * </b>
	 * <p>
	 * Computes and returns the value of the given expression as a {@link CompileTimeValue}. If the given expression is
	 * not a valid compile-time expression, the returned value will be {@link CompileTimeValue#isValid() invalid}. Never
	 * returns <code>null</code>.
	 */
	def public CompileTimeValue evaluateCompileTimeExpression(RuleEnvironment G, Expression expr) {
		return eval(G, expr, new RecursionGuard());
	}


	// ---------------------------------------------------------------------------------------------------------------


	def private dispatch CompileTimeValue eval(RuleEnvironment G, Expression expr, RecursionGuard<EObject> guard) {
		return switch (expr) {
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
				eval(G, expr.expression, guard)
			default:
				CompileTimeValue.error(keywordProvider.keywordWithIndefiniteArticle(expr)
					+ " is never a compile-time expression", expr)
		};
	}

	def private dispatch CompileTimeValue eval(RuleEnvironment G, IdentifierRef expr, RecursionGuard<EObject> guard) {
		if (N4JSLanguageUtils.isUndefinedLiteral(G, expr)) {
			return CompileTimeValue.UNDEFINED;
		}
		val id = expr.id;
		if (id !== null && !id.eIsProxy) {
			return obtainValueIfConstFieldOrVariable(G, id, expr, guard);
		}
		return CompileTimeValue.error();
	}

	def private dispatch CompileTimeValue eval(RuleEnvironment G, TemplateLiteral expr, RecursionGuard<EObject> guard) {
		val buff = new StringBuilder;
		val invalidValues = newArrayList;
		for (seg : expr.segments) {
			val segValue = eval(G, seg, guard);
			if (segValue.valid) {
				buff.append(segValue.toString);
			} else {
				invalidValues += segValue;
			}
		}
		if (!invalidValues.empty) {
			return CompileTimeValue.combineErrors(invalidValues);
		}
		return CompileTimeValue.of(buff.toString);
	}

	def private dispatch CompileTimeValue eval(RuleEnvironment G, UnaryExpression expr, RecursionGuard<EObject> guard) {
		val value = if (expr.expression !== null) eval(G, expr.expression, guard);
		return switch (expr.op) {
			case NOT: CompileTimeValue.invert(value, expr.expression)
			case POS: value
			case NEG: CompileTimeValue.negate(value, expr.expression)
			case VOID: CompileTimeValue.UNDEFINED
			default: CompileTimeValue.error("invalid operator: " + expr.op, expr)
		};
	}

	def private dispatch CompileTimeValue eval(RuleEnvironment G, AdditiveExpression expr, RecursionGuard<EObject> guard) {
		val lhs = expr.lhs;
		val rhs = expr.rhs;
		val leftValue = if (lhs !== null) eval(G, lhs, guard);
		val rightValue = if (rhs !== null) eval(G, rhs, guard);
		return switch (expr.op) {
			case ADD: CompileTimeValue.add(leftValue, rightValue, expr)
			case SUB: CompileTimeValue.subtract(leftValue, rightValue, lhs, rhs)
			default: CompileTimeValue.error("invalid operator: " + expr.op, expr)
		};
	}

	def private dispatch CompileTimeValue eval(RuleEnvironment G, MultiplicativeExpression expr, RecursionGuard<EObject> guard) {
		val lhs = expr.lhs;
		val rhs = expr.rhs;
		val leftValue = if (lhs !== null) eval(G, lhs, guard);
		val rightValue = if (rhs !== null) eval(G, rhs, guard);
		return switch (expr.op) {
			case TIMES: CompileTimeValue.multiply(leftValue, rightValue, lhs, rhs)
			case DIV: CompileTimeValue.divide(leftValue, rightValue, lhs, rhs)
			case MOD: CompileTimeValue.remainder(leftValue, rightValue, lhs, rhs)
			default: CompileTimeValue.error("invalid operator: " + expr.op, expr)
		};
	}

	def private dispatch CompileTimeValue eval(RuleEnvironment G, BinaryLogicalExpression expr, RecursionGuard<EObject> guard) {
		val lhs = expr.lhs;
		val rhs = expr.rhs;
		val leftValue = if (lhs !== null) eval(G, lhs, guard);
		val rightValue = if (rhs !== null) eval(G, rhs, guard);
		return switch (expr.op) {
			case AND: CompileTimeValue.and(leftValue, rightValue, lhs, rhs)
			case OR: CompileTimeValue.or(leftValue, rightValue, lhs, rhs)
			default: CompileTimeValue.error("invalid operator: " + expr.op, expr)
		};
	}

	def private dispatch CompileTimeValue eval(RuleEnvironment G, ConditionalExpression expr, RecursionGuard<EObject> guard) {
		val condition = expr.expression;
		val trueExpr = expr.trueExpression;
		val falseExpr = expr.falseExpression;
		val conditionValue = if (condition !== null) eval(G, condition, guard);
		val trueValue = if (trueExpr !== null) eval(G, trueExpr, guard);
		val falseValue = if (falseExpr !== null) eval(G, falseExpr, guard);
		val error = CompileTimeValue.combineErrors(
			CompileTimeValue.requireValueType(conditionValue, ValueBoolean, "condition must be a boolean",
				expr.expression), trueValue, falseValue);
		if (error !== null) {
			return error;
		}
		return if ((conditionValue as ValueBoolean).getValue()) trueValue else falseValue;
	}

	def private dispatch CompileTimeValue eval(RuleEnvironment G, ParameterizedPropertyAccessExpression expr, RecursionGuard<EObject> guard) {
		val targetExpr = expr.target;
		val targetElem = if (targetExpr instanceof IdentifierRef) targetExpr.id;
		val sym = G.symbolObjectType;
		if (targetElem === sym) {
			// A) is 'expr' an access to a built-in symbol, e.g. Symbol.iterator?
			// NOTES:
			// 1) we would like to simply call RuleEnvironmentExtensions#getAccessedBuiltInSymbol(), but that method would
			//    invoke expr.getProperty() which, in turn, would trigger type inference; since we want to handle computed
			//    property names in an up-front step and not as part of the main AST traversal, we must avoid triggering
			//    type inference and hence cannot use RuleEnvironmentExtensions#getAccessedBuiltInSymbol().
			// 2) the following logic must be kept aligned to RuleEnvironmentExtensions#getAccessedBuiltInSymbol()!
			val propName = expr.propertyAsText;
			val memberInSym = sym.ownedMembers.filter(TField).findFirst[static && name == propName];
			if (memberInSym !== null) {
				return CompileTimeValue.of(memberInSym);
			}
		} else if (targetElem instanceof TEnum) {
			// B) is 'expr' an access to the literal of a @StringBased enum?
			if (AnnotationDefinition.STRING_BASED.hasAnnotation(targetElem)) {
				val propName = expr.propertyAsText;
				val litInEnum = targetElem.literals.findFirst[name == propName];
				if (litInEnum !== null) {
					return CompileTimeValue.of(litInEnum.valueOrName);
				}
			}
		} else if (targetElem instanceof TClassifier) {
			// C) is 'expr' an access to a const field initialized by a compile-time expression?
			val memberName = expr.propertyAsText;
			val member = targetElem.ownedMembers.filterNull.findFirst[name == memberName && readable && static];
			// IMPORTANT: don't use "targetElem.findOwnedMember(memberName, false, true)" in previous line, because
			// #findOwnedMember() will create and cache a MemberByNameAndAccessMap, which will be incomplete if the
			// TClassifier contains members with unresolved computed property names!
			if (member instanceof TField && !(member as TField).hasComputedName) {
				return obtainValueIfConstFieldOrVariable(G, member, expr, guard);
			} else {
				// we get here in two cases:
				//
				// 1) member not found
				// -> there are a number of possible reasons:
				// 1.a) member has a computed property name which was not yet evaluated,
				// 1.b) member is inherited, consumed, polyfilled, etc.,
				// 1.c) member does not exist at all.
				// At this point, i.e. before computed names are processed, we cannot distinguish between these
				// cases. So we create a dummy error here that will be improved later.
				//
				// 2) member was found but is has a computed property name
				// -> for consistency with 1.a above, we have to raise an error also in this case
				return CompileTimeValue.error(new UnresolvedPropertyAccessError(expr));
			}
		}
		// D) all other cases:
		return CompileTimeValue.error("only references to const fields, literals of @StringBased enums, or built-in symbols allowed", expr);
	}


	// ---------------------------------------------------------------------------------------------------------------


	def private CompileTimeValue obtainValueIfConstFieldOrVariable(RuleEnvironment G, IdentifiableElement targetElem,
		EObject astNodeForErrorMessage, RecursionGuard<EObject> guard) {

		if (guard.tryNext(targetElem)) {
			try {
				return obtainValueIfConstFieldOrVariableUnguarded(G, targetElem, astNodeForErrorMessage, guard);
			} finally {
				guard.done(targetElem);
			}
		} else {
			return CompileTimeValue.error("cyclic definition of compile-time expression", astNodeForErrorMessage);
		}
	}

	def private CompileTimeValue obtainValueIfConstFieldOrVariableUnguarded(RuleEnvironment G,
		IdentifiableElement targetElem, EObject astNodeForErrorMessage, RecursionGuard<EObject> guard) {

		val targetElemIsConst = switch (targetElem) {
			TConstableElement: targetElem.const
			N4FieldDeclaration: targetElem.const
			VariableDeclaration: targetElem.const
		};
		if (!targetElemIsConst) {
			return CompileTimeValue.error(
				keywordProvider.keyword(targetElem) + " " + targetElem.name + " is not const",
				astNodeForErrorMessage);
		}

		val valueOfTargetElem = obtainValueOfTargetElement(G, astNodeForErrorMessage.eResource, targetElem, guard);
		if (valueOfTargetElem !== null) {
			if (valueOfTargetElem instanceof ValueInvalid) {
				val baseMsg = keywordProvider.keyword(targetElem) + " " + targetElem.name +
					" is const but does not have a compile-time expression as initializer";
				val msg = combineErrorMessageWithNestedErrors(baseMsg, valueOfTargetElem.errors);
				val feature = if (astNodeForErrorMessage instanceof ParameterizedPropertyAccessExpression) {
						N4JSPackage.eINSTANCE.parameterizedPropertyAccessExpression_Property
					};
				return CompileTimeValue.error(msg, astNodeForErrorMessage, feature);
			}
			return valueOfTargetElem;
		}
		return CompileTimeValue.error(
			"only references to const variables with a compile-time expression as initializer are allowed",
			astNodeForErrorMessage);
	}

	def private CompileTimeValue obtainValueOfTargetElement(RuleEnvironment G, Resource currentResource,
		IdentifiableElement targetElem, RecursionGuard<EObject> guard) {

		if (targetElem.eResource === currentResource || hasLoadedASTElement(targetElem)) {
			// 'targetElem' is in same resource OR is in a different resource that already has a fully-loaded AST
			// -> compute value from the initializer expression of 'targetElem'
			val astNodeOfTargetElem = if (targetElem instanceof SyntaxRelatedTElement) {
					targetElem.astElement // NOTE: this will never trigger demand-loading of an AST, because above we
					// ensured that we are still in 'currentResource' OR method #hasLoadedASTElement() has returned true
				} else {
					targetElem // here we simply assume that elem is already an AST node
				};
			val expressionOfTargetElem = switch (astNodeOfTargetElem) {
				N4FieldDeclaration: astNodeOfTargetElem.expression
				VariableDeclaration: astNodeOfTargetElem.expression
			};
			if (expressionOfTargetElem !== null) {
				return eval(G, expressionOfTargetElem, guard);
			}
		} else {
			// 'targetElem' is in another resource with an AST proxy
			// -> read value from TModule to avoid demand-loading of AST
			if (targetElem instanceof TConstableElement) {
				return CompileTimeValue.deserialize(targetElem.value);
			}
		}
		return null; // no value found
	}

	/**
	 * Tells if given element has an AST element in an already loaded AST (i.e. it is safe to invoke method
	 * {@code #getASTElement()} without triggering a demand-load of the AST).
	 */
	def private static boolean hasLoadedASTElement(IdentifiableElement elem) {
		val astElemNonResolved = if (elem instanceof SyntaxRelatedTElement) {
				elem.eGet(TypesPackage.eINSTANCE.syntaxRelatedTElement_AstElement, false) as EObject
			};
		return astElemNonResolved !== null && !astElemNonResolved.eIsProxy;
	}

	def private static String combineErrorMessageWithNestedErrors(String mainMessage, EvalError... nestedErrors) {
		if (nestedErrors.length == 0) {
			return mainMessage;
		} else if (nestedErrors.length == 1) {
			return mainMessage + ": " + nestedErrors.get(0).messageWithLocation;
		} else {
			return mainMessage + ":\n- " + nestedErrors.map[messageWithLocation].join("\n- ");
		}
	}


	// ---------------------------------------------------------------------------------------------------------------


	/**
	 * Special kind of {@link EvalError} used to denote cases in which the {@link CompileTimeEvaluator} cannot come up
	 * with the correct error message and thus delegates finding a proper message to the validation, i.e. to class
	 * {@link N4JSExpressionValidator}.
	 */
	public static final class UnresolvedPropertyAccessError extends EvalError {

		public new(ParameterizedPropertyAccessExpression astNode) {
			super("*** UnresolvedPropertyAccessError ***", astNode,
					N4JSPackage.eINSTANCE.getParameterizedPropertyAccessExpression_Property());
		}

		def public ParameterizedPropertyAccessExpression getAstNodeCasted() {
			return astNode as ParameterizedPropertyAccessExpression;
		}
	}
}
