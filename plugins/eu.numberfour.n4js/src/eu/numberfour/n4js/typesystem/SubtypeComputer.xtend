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
package eu.numberfour.n4js.typesystem

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.numberfour.n4js.typeinference.N4JSTypeInferencer
import eu.numberfour.n4js.typesystem.constraints.Variance
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.TypeArgument
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TFormalParameter
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.UndefModifier
import eu.numberfour.n4js.ts.utils.TypeUtils
import it.xsemantics.runtime.RuleEnvironment
import java.util.List
import org.eclipse.xtext.util.CancelIndicator

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import eu.numberfour.n4js.typesystem.constraints.InferenceContext

/**
 * Contains some helper methods to compute if type A is a subtype of type B.
 * Note that the main logic for subtype computation is contained in file
 * n4js.xsemantics. For structural typing there is a separate helper class
 * called {@link StructuralTypingComputer}.
 */
@Singleton
class SubtypeComputer extends TypeSystemHelperStrategy {

	@Inject
	private N4JSTypeInferencer typeInferencer;
	@Inject
	private N4JSTypeSystem ts;
	@Inject
	private TypeSystemHelper tsh;

	/**
	 * Returns true iff function/method 'left' is a subtype of function/method 'right'.
	 */
	def boolean isSubtypeFunction(RuleEnvironment G, FunctionTypeExprOrRef left, FunctionTypeExprOrRef right) {
		val leftTypeVars = left.typeVars;
		val rightTypeVars = right.typeVars;

		if (leftTypeVars.empty && rightTypeVars.empty) {

			// both non-generic
			return primIsSubtypeFunction(G, left, right);

		} else if (!leftTypeVars.empty && rightTypeVars.empty) {

			// left is generic, right is non-generic
			// (i.e. cases like: {function<T>(T):T} <: {function(string):string})
			// rationale: if there exists a valid binding of left's type variables
			// so that bound(left) <: right, then left <: right

			val infCtx = new InferenceContext(ts, tsh, CancelIndicator.NullImpl, G); // start with no inference variables
			val left_withInfVars = infCtx.newInferenceVariablesFor(left); // create an inference variable for each type param in left
			// assuming 'left' was {function<T>(T):T}, then left_withInfVars is now: {function(α):α} (non-generic!)
			infCtx.addConstraint(left_withInfVars, right, Variance.CO);
			val solution = infCtx.solve; // will give us something like α->string
			if (solution !== null) {
				val G_solution = G.newRuleEnvironment;
				solution.entrySet.forEach[G_solution.addTypeMapping(key,value)];
				val leftSubst = typeInferencer.substituteTypeVariables(G_solution, left_withInfVars);
				if (leftSubst instanceof FunctionTypeExprOrRef) {
					return primIsSubtypeFunction(G, leftSubst, right);
				}
			}
			return false;

		} else {

			// at least one is generic
			// require same number of type parameters (an thus both have to be generic)
			if (leftTypeVars.size !== rightTypeVars.size)
				return false;

			//
			// STEP #1: apply ordinary subtype rules for functions
			//
			/*
			 * Here we substitute the type variables in the right function with matching (as in: same index)
			 * type variables of the left function. That is, the following substitution is applied before the
			 * function types are compared (here illustrated with a method override scenario):
			 *
			 * class Super {
			 *   <T> T m(T t); // {function(T):T} *1
			 * }
			 * class Sub extends Super {
			 *   <U> U m(object o); // {function(object):U} *2
			 * }
			 *
			 * The signature *1 from Super is converted to a signature {function(U):U} which is then compared
			 * to the subtype's method *2 {function(object):U}.
			 *
			 * The last step is to check the bounds of the type variables themselves.
			 */
			val G2 = G.wrap
			for (i : 0 ..< leftTypeVars.size) {
				G2.addTypeMapping(rightTypeVars.get(i), TypeUtils.createTypeRef(leftTypeVars.get(i)))
			}
			val TypeRef rightSubst = typeInferencer.substituteTypeVariables(G2, right);
			if (!(rightSubst instanceof FunctionTypeExprOrRef &&
				primIsSubtypeFunction(G, left, rightSubst as FunctionTypeExprOrRef)))
				return false;

			//
			// STEP #2: check type variable bounds
			//
			// the following is required in a method override scenario where 'left' is the overriding
			// and 'right' the overridden method
			if (left.declaredType?.eContainer instanceof TClassifier)
				addSubstitutions(G2, TypeUtils.createTypeRef(left.declaredType.eContainer as TClassifier));

			return isMatchingTypeVariableBounds(G2, leftTypeVars, rightTypeVars);
		}
	}

	/**
	 * Contains the core logic for subtype relation of functions/methods but <em>without</em>
	 * taking into account type variables of generic functions/methods. Generic functions are handled
	 * in method {@link #isSubtypeFunction(RuleEnvironment,FunctionTypeExprOrRef,FunctionTypeExprOrRef)}.
	 */
	private def boolean primIsSubtypeFunction(RuleEnvironment G, FunctionTypeExprOrRef left, FunctionTypeExprOrRef right) {

		// return type
		if (right.returnTypeRef !== null) {

			// f():void <: f():void --> true
			// f():B    <: f():void --> true
			// f():B?   <: f():void --> true
			// f():void <: f():A    --> false
			// f():B    <: f():A    --> B <: A
			// f():B?   <: f():A    --> false (!)
			// f():void <: f():A?   --> true (!)
			// f():B    <: f():A?   --> B <: A
			// f():B?   <: f():A?   --> B <: A
			if (right.returnTypeRef.declaredType !== G.voidType) {
				if (left.returnTypeRef.declaredType !== G.voidType) {

					// both are non-void
					if (left.returnTypeRef.undefModifier == UndefModifier.OPTIONAL &&
						!(right.returnTypeRef.undefModifier == UndefModifier.OPTIONAL)) {
						return false;
					} else if (!isSubtype(G, left.returnTypeRef, right.returnTypeRef)) {
						return false;
					}
				} else {

					// left is void, right is non-void
					if (!(right.returnTypeRef.undefModifier == UndefModifier.OPTIONAL))
						return false;
				}
			}
		}

		// formal parameters
		val k = left.fpars.size;
		val n = right.fpars.size;
		if (k <= n) {
			if (k > 0) {
				var i = 0;
				while (i < k) {
					val R = right.fpars.get(i);
					val L = left.fpars.get(i);

					if ((R.variadic || R.optional) && !(L.optional || L.variadic)) {
						return false;
					}

					if (!isSubtype(G, R.typeRef, L.typeRef))
						return false;
					i = i + 1;
				}
				val L = left.fpars.get(k - 1);
				if (L.variadic) {
					while (i < n) {
						val R = right.fpars.get(i);
						if (!isSubtype(G, R.typeRef, L.typeRef))
							return false;
						i = i + 1;
					}
				}
			}
		} else { // k>n

			// {function(A, A...)} <: {function(A)}
			var i = 0;
			while (i < n) {
				val R = right.fpars.get(i);
				val L = left.fpars.get(i);

				if ((R.variadic || R.optional) && !(L.optional || L.variadic)) {
					return false;
				}

				if (!isSubtype(G, R.typeRef, L.typeRef))
					return false;
				i = i + 1;
			}
			val TFormalParameter R = if (n > 0) {
					right.fpars.get(n - 1)
				} else {
					// if right hand side has no parameters at all, e.g. {function(A?)} <: {function()}
					null
				};
			while (i < k) {
				val L = left.fpars.get(i);
				if (! (L.optional || L.variadic)) {
					return false;
				}
				if (R !== null && R.variadic) {
					if (!isSubtype(G, R.typeRef, L.typeRef))
						return false;
				}
				i = i + 1;
			}
		}

		// declaredThisType
		// contra-variant behavior:
		val rThis = right.declaredThisType
		val lThis = left.declaredThisType
		if (rThis !== null) {
			return lThis === null || isSubtype(G, rThis, lThis);
		} else {

			// Should fail:
			if (lThis !== null) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Checks bounds of type variables in left and right.
	 * Upper bound on left side must be a super type of upper bound on right side.
	 */
	private def boolean isMatchingTypeVariableBounds(RuleEnvironment G, List<TypeVariable> left,
		List<TypeVariable> right) {

		// check type variable bounds
		for (var i = 0; i < right.size; i++) {
			val leftTypeVar = left.get(i)
			val rightTypeVar = right.get(i)
			val leftUpperBound = if (leftTypeVar.declaredUpperBounds.empty)
					G.anyTypeRef
				else
					createIntersectionType(G, leftTypeVar.declaredUpperBounds);
			val rightUpperBound = if (rightTypeVar.declaredUpperBounds.empty)
					G.anyTypeRef
				else
					createIntersectionType(G, rightTypeVar.declaredUpperBounds);
			val rightUpperBoundSubst = typeInferencer.substituteTypeVariables(G, rightUpperBound);

			// leftUpperBound must be a super(!) type of rightUpperBound,
			// i.e. rightUpperBound <: leftUpperBound
			if (!G.isSubtype(rightUpperBoundSubst, leftUpperBound)) {
				return false
			}
		}
		return true
	}

	private def boolean isSubtype(RuleEnvironment G, TypeArgument left, TypeArgument right) {
		ts.subtype(G, left, right).value ?: false
	}
}