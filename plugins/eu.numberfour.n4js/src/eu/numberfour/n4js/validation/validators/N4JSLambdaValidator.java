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
package eu.numberfour.n4js.validation.validators;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

import eu.numberfour.n4js.n4JS.ArrowFunction;
import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression;
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.utils.LambdaUtils;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions;
import eu.numberfour.n4js.utils.ContainerTypesHelper;
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator;
import eu.numberfour.n4js.validation.IssueCodes;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleEnvironment;

/**
 */
public class N4JSLambdaValidator extends AbstractN4JSDeclarativeValidator {

	@Inject
	private N4JSTypeSystem ts;

	@Inject
	ContainerTypesHelper containerTypesHelper;

	/**
	 * NEEEDED
	 *
	 * when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	@Override
	public void register(EValidatorRegistrar registrar) {
		// nop
	}

	/**
	 * A top-level arrow function can't include uses of <code>arguments</code> and <code>this</code> as they lack an
	 * outer lexical context that would provide bindings for them.
	 */
	@Check
	public void checkTopLevelLambda(ArrowFunction arrowFun) {
		if (LambdaUtils.isTopLevelLambda(arrowFun)) {
			rejectUsagesOfThisInTopLevelLambda(arrowFun);
		}
	}

	/**
	 * Rejects uses of 'this' in top-level functions, which due to their top-level nature can't capture any 'this' from
	 * the enclosing context (same goes for 'arguments', by the way).
	 * <p>
	 * Precondition: the argument is a top-level lambda.
	 */
	private void rejectUsagesOfThisInTopLevelLambda(ArrowFunction topLevelLambda) {
		assert LambdaUtils.isLambda(topLevelLambda);
		Iterator<EObject> thisUsages = LambdaUtils.thisLiterals(topLevelLambda.getBody());
		while (thisUsages.hasNext()) {
			EObject thisUsage = thisUsages.next();
			String message = IssueCodes.getMessageForKEY_THIS_REJECTED_IN_TOP_LEVEL_LAMBDA();
			addIssue(message, thisUsage, IssueCodes.KEY_THIS_REJECTED_IN_TOP_LEVEL_LAMBDA);
		}
	}

	/**
	 * For a single-expr lambda, this method checks conformance between:
	 * <ul>
	 * <li>the type inferred solely based on the lambda's body, and</li>
	 * <li>the return-type-component of the expected-type for the lambda</li>
	 * </ul>
	 * <p>
	 * The check above can be subdivided into two cases, depending on whether first item above is void or not:
	 * <ul>
	 * <li>for void-inferred body, handled by
	 * {@link #errorOnVoidWhereNonOptionalValueExpected(ArrowFunction, FunctionTypeExprOrRef)}</li>
	 * <li>otherwise, handled by {@link #errorOnNonConformantReturnType(ArrowFunction, FunctionTypeExprOrRef, TypeRef)}
	 * </li>
	 * </ul>
	 */
	@Check
	public void checkArrowFunction(ArrowFunction fe) {
		if (fe.isSingleExprImplicitReturn()) {
			Expression singleExpr = fe.implicitReturnExpr();
			if (singleExpr == null) { // due to parse error, expression may be null
				return;
			}
			TypeRef bodyInferredType = typeInferencer.tau(singleExpr);
			if (bodyInferredType != null) {
				if (TypeUtils.isVoid(bodyInferredType)) {
					// special handling for void body in single expression arrow function (only required because the
					// Poor man's return type inferencer infers an incorrect 'any' in this case)
					// TODO remove this special handling once the Poor man's return type inferencer is improved
					// (compare with ordinary function expressions)
					FunctionTypeExprOrRef feExpectedType = expectedTypeForLambda(fe);
					if (feExpectedType != null)
						errorOnVoidWhereNonOptionalValueExpected(fe, feExpectedType);
				} else {
					// standard case
					FunctionTypeExprOrRef feActualType = actualTypeForLambda(fe);
					if (feActualType != null)
						errorOnNonConformantReturnType(fe, feActualType, bodyInferredType);
				}
			}
		}
	}

	/**
	 * We've typed an arrow function of the single-expr variety back in
	 * {@link eu.numberfour.n4js.typesbuilder.N4JSFunctionDefinitionTypesBuilder}'s
	 * <code>createTFunction(FunctionExpression, TModule, boolean)</code>. With the information available there, we've
	 * made as if each single-expr arrow-function has an any-valued body. That heuristic over-approximates and thus gets
	 * it right "most of the time" (granted, a more precise type would be desirable).
	 *
	 * <p>
	 * However, such body can also be void-typed: a {@link ParameterizedCallExpression} can be the single expression of
	 * a single-expr arrow-function. Moreover, such lambda may occur in a context where a function is expected with
	 * non-void return type. We raise an error upon detecting that combination of AST shape and its context.
	 *
	 * <p>
	 * Precondition: the body of the single-expr lambda has void type.
	 */
	private void errorOnVoidWhereNonOptionalValueExpected(ArrowFunction fe, FunctionTypeExprOrRef funExpectedType) {
		if (funExpectedType == null) {
			// skipping raising an error due to lack of information
			return;
		}
		TypeRef expectedRetType = funExpectedType.getReturnTypeRef();
		if (TypeUtils.isVoid(expectedRetType)) {
			// The expected type for the lambda has void as return type component
			// expected and actual return-type match exactly
			return;
		}
		// A lambda is expected with non-void return type
		if (TypeUtils.isOptional(expectedRetType)) {
			// provided the non-void expected return-type is optional,
			// a void-returning lambda will do.
			return;
		}
		// the body evals to void, and some value is expected
		String message = IssueCodes.getMessageForFUN_SINGLE_EXP_LAMBDA_IMPLICIT_RETURN_ALLOWED_UNLESS_VOID();
		addIssue(message, fe.implicitReturnExpr(),
				IssueCodes.FUN_SINGLE_EXP_LAMBDA_IMPLICIT_RETURN_ALLOWED_UNLESS_VOID);
	}

	/**
	 * Catch situations where the just inferred type of the lambda's body doesn't conform to the corresponding component
	 * of the expected type.
	 * <p>
	 * This comprises two sub-cases:
	 * <ul>
	 * <li>the arrow-function has been tagged async: in this case the type inferred for arrow function is a
	 * <code>Promise&lt;T, ?&gt;</code>. The inferred type for the lambda body should conform to that <code>T</code>.
	 * Details in {@link #unPromisedReturnTypeForAsyncArrowFunction(ArrowFunction, FunctionTypeExprOrRef)}</li>
	 * <li>otherwise: just check whether the inferred type for the body conforms to the return-part of the type inferred
	 * for the arrow function</li>
	 * </ul>
	 */
	private void errorOnNonConformantReturnType(ArrowFunction fe, FunctionTypeExprOrRef funActualType,
			TypeRef bodyInferredType) {
		Expression singleExpr = fe.implicitReturnExpr();
		// not using SubtypeComputer#isSubtypeFunction() because fe.getDefinedType() is an approximation
		TypeRef expectedReturnType = unPromisedReturnTypeForAsyncArrowFunction(fe, funActualType);
		if (null == expectedReturnType) {
			return;
		}
		RuleEnvironment G = RuleEnvironmentExtensions.newRuleEnvironment(singleExpr);
		Result<Boolean> isSubtype = ts.subtype(G, bodyInferredType, expectedReturnType);
		if (isSubtype != null) {
			if (isSubtype.failed() || !isSubtype.getValue()) {
				// the body evals to some non-void type that doesn't conform to that being expected
				String message = IssueCodes.getMessageForTYS_NO_SUBTYPE(bodyInferredType.getTypeRefAsString(),
						expectedReturnType.getTypeRefAsString());
				addIssue(message, fe.implicitReturnExpr(), IssueCodes.TYS_NO_SUBTYPE);
			}
		}
	}

	/**
	 * An async arrow function has a body that evaluates to some type T which should conform to the type-hint the
	 * developer may have specified at the AST level. Additionally, there's the return type that the types builder
	 * computed for the {@link eu.numberfour.n4js.ts.types.TFunction} which for an async function is a
	 * <code>Promise&lt;T, ?&gt;</code>.
	 * <p>
	 * We would reject a valid program if we checked for subtyping the inferred type for the body against the return
	 * type given by TFunction (because in general T is not a subtype of <code>Promise&lt;T, ?&gt;</code>).
	 * <p>
	 * This method returns for a non-async lambda the return type of the expected function type. For an async lambda the
	 * first component of that type Promise is returned.
	 */
	private TypeRef unPromisedReturnTypeForAsyncArrowFunction(ArrowFunction fe,
			FunctionTypeExprOrRef funType) {
		if (fe.isAsync()) {
			// we have an async arrow function
			TypeRef retTypeAsPerFunType = funType.getReturnTypeRef();
			BuiltInTypeScope builtInTypeScope = BuiltInTypeScope.get(fe.eResource().getResourceSet());
			// normally we expect Promise as expected-return-type
			if (builtInTypeScope.getPromiseType() == retTypeAsPerFunType.getDeclaredType()) {
				// now we need not that promise type but the "promised" type it wraps
				TypeRef promisedType = (TypeRef) retTypeAsPerFunType.getTypeArgs().get(0);
				return promisedType;
			}
		}
		return funType.getReturnTypeRef();
	}

	/**
	 * This method returns the expected type for the arrow function given as argument.
	 * <p>
	 * It's important not to use the return type as approximated in the arrow's
	 * {@link eu.numberfour.n4js.ts.types.TFunction} because it's too rough, or even buggy. To recap: that
	 * <code>TFunction</code> was set by the type builder, and with the information available at the time the best
	 * approx it could make for the function's return type is invariably <code>any</code>. Instead, it is inferred by
	 * the injected {@link eu.numberfour.n4js.typeinference.N4JSTypeInferencer}. See also the comment in method
	 * {@link #checkArrowFunction(ArrowFunction)}.
	 *
	 * @return the expected type for the argument, otherwise null if for whatever reason it couldn't be inferred.
	 */
	private FunctionTypeExprOrRef expectedTypeForLambda(ArrowFunction fe) {
		RuleEnvironment G = RuleEnvironmentExtensions.newRuleEnvironment(fe);
		Result<TypeRef> tr = ts.expectedTypeIn(G, fe.eContainer(), fe);
		return asFunctionType(tr);
	}

	private FunctionTypeExprOrRef actualTypeForLambda(ArrowFunction fe) {
		RuleEnvironment G = RuleEnvironmentExtensions.newRuleEnvironment(fe);
		Result<TypeRef> tr = ts.type(G, fe);
		return asFunctionType(tr);
	}

	/**
	 * @return the non-null {@link FunctionTypeExprOrRef} given by the argument, null otherwise.
	 */
	private FunctionTypeExprOrRef asFunctionType(Result<TypeRef> rtr) {
		if (!rtr.failed()) {
			TypeRef tr = rtr.getValue();
			if (tr instanceof FunctionTypeExprOrRef) {
				return (FunctionTypeExprOrRef) tr;
			}
		}
		return null;
	}

}
