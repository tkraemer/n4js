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
package eu.numberfour.n4js.typesystem.constraints;

import static eu.numberfour.n4js.ts.types.util.Variance.CO;
import static eu.numberfour.n4js.ts.types.util.Variance.CONTRA;
import static eu.numberfour.n4js.ts.types.util.Variance.INV;
import static eu.numberfour.n4js.typesystem.TypeVarUtils.typeRef;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Pair;

import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ConstructorTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ExistentialTypeRef;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef;
import eu.numberfour.n4js.ts.typeRefs.IntersectionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory;
import eu.numberfour.n4js.ts.typeRefs.UnionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.Wildcard;
import eu.numberfour.n4js.ts.types.ContainerType;
import eu.numberfour.n4js.ts.types.PrimitiveType;
import eu.numberfour.n4js.ts.types.TFormalParameter;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.TypeVariable;
import eu.numberfour.n4js.ts.types.util.AllSuperTypesCollector;
import eu.numberfour.n4js.ts.types.util.Variance;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions;
import eu.numberfour.n4js.typesystem.StructuralTypingComputer;
import eu.numberfour.n4js.typesystem.StructuralTypingComputer.StructTypingInfo;
import eu.numberfour.n4js.typesystem.SubtypeComputer;
import eu.numberfour.n4js.typesystem.TypeSystemHelper;
import eu.numberfour.n4js.utils.StructuralMembersTriple;
import eu.numberfour.n4js.utils.StructuralMembersTripleIterator;
import eu.numberfour.n4js.utils.StructuralTypesHelper;
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * A Reducer owns no state. Instead, it's a collaborator of its {@link InferenceContext inference context}, operating on
 * the {@link BoundSet bound set} of that inference context, by lowering constraints (as summarized below) into simpler
 * bounds.
 * <p>
 * The entry points to the functionality provided by this class are:
 * <ul>
 * <li>{@link #reduce(TypeConstraint) reducing a subtyping constraint}</li>
 * <li>reducing a compatibility constraint (involving an expression)</li>
 * </ul>
 * <p>
 * Why have a dedicated class that owns no state? So as to have a clean interface (along the lines of separation of
 * concerns) for the inference context to interact with.
 */
public class Reducer {

	private static final boolean DEBUG = InferenceContext.DEBUG;

	private final InferenceContext ic;

	private final N4JSTypeSystem ts;
	private final TypeSystemHelper tsh;
	private final RuleEnvironment G;

	/**
	 * @see Reducer
	 */
	public Reducer(InferenceContext ic, N4JSTypeSystem ts, TypeSystemHelper tsh, RuleEnvironment G) {
		this.ic = ic;
		this.ts = ts;
		this.tsh = tsh;
		this.G = G;
	}

	/**
	 * Convenience method, forwards to {@link BoundSet#addBound(boolean)}.
	 *
	 * @return true iff FALSE was added for the first time (adding TRUE never requires a new round of incorporation to
	 *         follow)
	 */
	private boolean addBound(boolean b) {
		return ic.currentBounds.addBound(b);
	}

	/**
	 * Convenience method, creates a new {@link TypeBound} and forwards to {@link BoundSet#addBound(TypeBound)}.
	 *
	 * TODO(mg) clarify what happens if both args are TypeVariable. When does that occur?
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean addBound(TypeVariable infVar, TypeRef bound, Variance variance) {
		assert ic.isInferenceVariable(infVar);
		return ic.currentBounds.addBound(new TypeBound(infVar, bound, variance));
	}

	// ###############################################################################################################
	// REDUCTION

	/**
	 * Convenience method for {@link #reduce(TypeArgument, TypeArgument, Variance)}, accepting a {@link TypeConstraint}.
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	public boolean reduce(TypeConstraint constraint) {
		if (constraint == TypeConstraint.TRUE) {
			return addBound(true);
		} else if (constraint == TypeConstraint.FALSE) {
			return addBound(false);
		} else {
			return reduce(constraint.left, constraint.right, constraint.variance);
		}
	}

	/**
	 * Reduction (in all its variants) takes place (1) at the very start of solving; and (2) for new constraints
	 * discovered during a round of incorporation.
	 * <p>
	 * Always invoke this method instead of the more specific overloads, both for readability and to ensure completeness
	 * of log messages.
	 *
	 * TODO Implementation restrictions:
	 * <ul>
	 * <li>this method is a no-op in case of null argument.</li>
	 * <li>this method aborts inference unless both arguments are TypeRef-s or both are Wildcard-s.</li>
	 * </ul>
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	public boolean reduce(TypeArgument left, TypeArgument right, Variance variance) {
		if (DEBUG) {
			log("reducing: " + TypeConstraint.toString(left, right, variance));
		}
		if (someNull(left, right)) {
			return false;
		}
		if ((left instanceof TypeRef) && (right instanceof TypeRef)) {
			// both TypeRefs
			return reduceTypeRef((TypeRef) left, (TypeRef) right, variance);
		}
		// at least one wildcard
		if ((left instanceof Wildcard) && (right instanceof Wildcard)) {
			// both Wildcards
			return reduceWildcard((Wildcard) left, (Wildcard) right, variance);
		}
		// a Wildcard and a TypeRef, in any order
		return abort(left, right, variance); // TODO probably wrong like this? cf. IDE-1653
	}

	/**
	 * Calling this method amounts to adding FALSE to the {@link BoundSet} with the side-effect of logging.
	 */
	private boolean abort(EObject left, EObject right, Variance variance) {
		if (DEBUG) {
			log("GIVING UP ON: " + TypeConstraint.toString(left, right, variance));
		}
		return addBound(false);
	}

	/**
	 * Is any of the arguments null? If so, log.
	 */
	private boolean someNull(TypeArgument a, TypeArgument b) {
		boolean result = (a == null || b == null);
		if (result) {
			if (DEBUG) {
				log("ignoring constraint due to null values");
			}
		}
		return result;
	}

	/**
	 * TODO Implementation restrictions:
	 * <ul>
	 * <li>this method is a no-op in case of null argument.</li>
	 * <li>this method is a no-op in case of ComputedTypeRef argument.</li>
	 * </ul>
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceTypeRef(TypeRef left, TypeRef right, Variance variance) {
		if (someNull(left, right)) {
			return true;
		}

		final boolean isLeftProper = ic.isProper(left);
		final boolean isRightProper = ic.isProper(right);
		if (isLeftProper && isRightProper) {
			return reduceProper(left, right, variance);
		}

		final boolean leftInfVar = ic.isInferenceVariable(left);
		final boolean rightInfVar = ic.isInferenceVariable(right);
		if (leftInfVar || rightInfVar) {
			if (leftInfVar) {
				return addBound((TypeVariable) left.getDeclaredType(), right, variance);
			} else {
				return addBound((TypeVariable) right.getDeclaredType(), left, variance.inverse());
			}
		}

		if (((variance == Variance.CO || variance == Variance.INV)
				&& (right.isUseSiteStructuralTyping() || right.isDefSiteStructuralTyping()))
				||
				((variance == Variance.CONTRA || variance == Variance.INV)
						&& (left.isUseSiteStructuralTyping() || left.isDefSiteStructuralTyping()))) {
			return reduceStructuralTypeRef(left, right, variance);
		}

		/*
		 * FIXME Does the user of the constraint-solver lower Wildcards into ExistentialTypeRefs, or not? Depending on
		 * that, reduction should take place in reduceWildcard() or in this method. The bounds (upper and lower) of an
		 * existential-type have to be formulated as CO and CONTRA TypeBounds. The encoding can't transform an
		 * ExistentialTypeRef as TypeVariable (or InferenceVariable) because the constraint solver isn't free to choose
		 * an instantiation for an existential type.
		 */

		// none of the args is a TypeVariable
		if (left instanceof ComposedTypeRef) {
			return reduceComposedTypeRef(right, (ComposedTypeRef) left, variance.inverse());
		}
		if (right instanceof ComposedTypeRef) {
			return reduceComposedTypeRef(left, (ComposedTypeRef) right, variance);
		}

		// none of the args is a TypeVariable or a ComposedTypeRef
		if (left instanceof ClassifierTypeRef && right instanceof ClassifierTypeRef) {
			return reduceClassifierTypeRef((ClassifierTypeRef) left, (ClassifierTypeRef) right, variance);
		} else if (left instanceof FunctionTypeExprOrRef && right instanceof FunctionTypeExprOrRef) {
			return reduceFunctionTypeExprOrRef((FunctionTypeExprOrRef) left, (FunctionTypeExprOrRef) right, variance);
		} else if (left instanceof ParameterizedTypeRef && right instanceof ParameterizedTypeRef) {
			return reduceParameterizedTypeRef((ParameterizedTypeRef) left, (ParameterizedTypeRef) right, variance);
		} else {
			// different subtypes of TypeRef on left and right side
			if (right instanceof ParameterizedTypeRef
					&& right.getDeclaredType() == RuleEnvironmentExtensions.anyType(G)) {
				// a constraint like ⟨ {function(number):α} <: any ⟩
				return addBound(true);
			} else {
				// in all other cases
				return abort(left, right, variance);
			}
		}
	}

	/**
	 * Leaving aside the trivial case of a wildcard compared to itself, this method divides the input into the following
	 * cases based on whether it's lower or upper bounds:
	 * <ul>
	 * <li>{@code (? super TL) op (? super TR)} are reduced as {@code TL == TR}
	 * <li>{@code (? super TL) op wildcard-right} are reduced as {@code TL == bottom}
	 * <li>{@code wildcard-left op (? super TR)} are reduced as {@code bottom == TR}
	 * </ul>
	 * Otherwise, declared upper bounds are considered:
	 * <ul>
	 * <li>{@code (? extends TL) op (? extends TR)} are reduced as {@code TL == TR}
	 * <li>{@code (? extends TL) op wildcard-right} are reduced as {@code TL == top}
	 * <li>{@code wildcard-left op (? extends TR)} are reduced as {@code top == TR}
	 * </ul>
	 * Otherwise, (ie, unbound wildcard):
	 * <ul>
	 * <li>{@code (?) op (?)} are reduced as {@code TRUE}
	 * </ul>
	 * TODO: note the relation to ExistentialTypeRef!
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceWildcard(Wildcard left, Wildcard right,
			/* TODO IDE-1653 */ @SuppressWarnings("unused") Variance variance) {
		// TODO don't ignore the `variance` argument
		// each occurrence of '?' can be seen as a fresh variable,
		// ie occurrences in different wildcards denote in general different values.
		if (left == right) {
			// trivial ==, <:, and :> of a wildcard to itself.
			return false;
		}
		boolean wasAdded = false;
		final TypeRef lbLeft = left.getDeclaredLowerBound();
		final TypeRef lbRight = right.getDeclaredLowerBound();
		if (lbLeft != null || lbRight != null) {
			TypeRef lbLeftOrBottom = (lbLeft != null) ? lbLeft : bottom();
			TypeRef lbRightOrBottom = (lbRight != null) ? lbRight : bottom();
			wasAdded |= reduce(lbLeftOrBottom, lbRightOrBottom, INV);
		}
		final TypeRef ubLeft = left.getDeclaredUpperBound();
		final TypeRef ubRight = right.getDeclaredUpperBound();
		if (ubLeft != null || ubRight != null) {
			TypeRef ubLeftOrTop = (ubLeft != null) ? ubLeft : top();
			TypeRef ubRightOrTop = (ubRight != null) ? ubRight : top();
			wasAdded |= reduce(ubLeftOrTop, ubRightOrTop, INV);
		}
		return wasAdded;
	}

	private TypeRef bottom() {
		return RuleEnvironmentExtensions.bottomTypeRef(G);
	}

	private TypeRef top() {
		return RuleEnvironmentExtensions.topTypeRef(G);
	}

	/**
	 * This method adds either TRUE or FALSE to the bound set after asking the type system.
	 *
	 * @return true iff FALSE was added (adding TRUE requires no new round of incorporation to follow)
	 */
	private boolean reduceProper(TypeRef left, TypeRef right, Variance variance) {
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// FIXME very hacky recursion guard!!!
		final Pair<String, Pair<TypeRef, TypeRef>> key = Pair.of("xxxGuard", Pair.of(left, right));
		if (G.get(key) != null) {
			return true;
		}
		final RuleEnvironment G2 = RuleEnvironmentExtensions.wrap(G);
		G2.add(key, Boolean.TRUE);
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		switch (variance) {
		case CO: {
			return addBound(ts.subtypeSucceeded(G2, left, right));
		}
		case CONTRA: {
			return addBound(ts.subtypeSucceeded(G2, right, left));
		}
		case INV: {
			return addBound(ts.equaltypeSucceeded(G2, left, right));
		}
		}
		// actually unreachable, each case above returns something
		throw new IllegalStateException("unreachable");
	}

	/**
	 * Cases to handle:
	 * <ul>
	 * <li>if {@code left == right} , then recurse: {@code left} is equivalent to the composed type given by
	 * {@code right} if it's both a subtype and a supertype of that composed type.</li>
	 * <li>if {@code right} is union, {@link #reduceUnionTypeExpression(TypeRef, UnionTypeExpression, Variance)}</li>
	 * <li>if {@code right} is intersection,
	 * {@link #reduceIntersectionTypeExpression(TypeRef, IntersectionTypeExpression, Variance)}</li>
	 * </ul>
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceComposedTypeRef(TypeRef left, ComposedTypeRef right, Variance variance) {
		boolean wasAdded = false;
		if (variance == INV) {
			wasAdded |= reduceComposedTypeRef(left, right, Variance.CO); // n.b.: not invoking #reduce() here!
			wasAdded |= reduceComposedTypeRef(left, right, Variance.CONTRA); // n.b.: not invoking #reduce() here!
			return wasAdded;
		}
		if (right instanceof UnionTypeExpression) {
			return reduceUnionTypeExpression(left, (UnionTypeExpression) right, variance);
		}
		if (right instanceof IntersectionTypeExpression) {
			return reduceIntersectionTypeExpression(left, (IntersectionTypeExpression) right, variance);
		}
		throw new IllegalStateException("shouldn't get here");
	}

	/**
	 * Cases, argument "right" is union:
	 * <ul>
	 * <li>A type is a subtype of a union if it is a subtype of one or more elements of the union (as a special case,
	 * the elements of a union are subtypes of that union)</li>
	 * <li>A type is a supertype of a union if it is a supertype of each element of the union</li>
	 * </ul>
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceUnionTypeExpression(TypeRef left, UnionTypeExpression right, Variance variance) {
		boolean wasAdded = false;
		switch (variance) {
		case CO: {
			return reduceSubtypeOfUnion(left, right);
		}
		case CONTRA: {
			// L :> union{R1,R2} --> L:>R1 && L:>R2
			for (TypeRef currRight : right.getTypeRefs()) {
				wasAdded |= reduce(left, currRight, CONTRA);
			}
			return wasAdded;
		}
		case INV: {
			// should have been handled by invoker
			throw new IllegalStateException("shouldn't get here");
		}
		}
		// unreachable code, each branch above returned something
		throw new IllegalStateException("shouldn't get here");
	}

	/**
	 * (L <: union{R1,R2}) iff (L<:R1 || L<:R2)
	 *
	 * That requires backtracking. Easier when the union type has been canonicalized (ie, its direct elements are
	 * disjoint) ie fan-out is "only" n. Otherwise fan-out is 2^n.
	 *
	 * IMPORTANT: Why dedicate a method to this reduction? So as to bring to the forefront (and document) the
	 * heuristics, special-case handling, and approximations chosen in this implementation.
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceSubtypeOfUnion(TypeRef left, UnionTypeExpression right) {
		EList<TypeRef> unionElems = right.getTypeRefs();
		if (unionElems.isEmpty()) {
			// union{} (ie, of the empty list of elements) stands for BOTTOM
			return reduce(left, bottom(), CO);
		}
		// --------------------------------------------------
		// try approximations (ie, safe but not complete)
		// --------------------------------------------------
		// FIXME improve readability of following code and document the reasoning behind each heuristic
		int idx = -1;
		if (idx == -1 && left instanceof FunctionTypeExprOrRef) {
			// choose first function type except those of which we already know they cannot match!
			for (int i = 0; i < unionElems.size(); i++) {
				final TypeRef currElem = unionElems.get(i);
				if (currElem instanceof FunctionTypeExprOrRef) {
					final boolean mightMatch = mightBeSubtypeOf((FunctionTypeExprOrRef) left,
							(FunctionTypeExprOrRef) currElem);
					if (mightMatch) {
						idx = i;
						break;
					}
				}
			}
		}
		if (left instanceof ParameterizedTypeRef && !ic.isInferenceVariable(left)) {
			final Type leftDecl = left.getDeclaredType();
			if (idx == -1 && leftDecl != null) {
				for (int i = 0; i < unionElems.size(); i++) {
					final TypeRef currElem = unionElems.get(i);
					if (leftDecl == currElem.getDeclaredType()) {
						idx = i;
						break;
					}
				}
			}
			if (idx == -1 && leftDecl instanceof PrimitiveType) {
				// choose first naked inference variable (if any)
				// (note: same as below, but has higher priority for primitive types than next heuristic)
				for (int i = 0; i < unionElems.size(); i++) {
					final TypeRef currElem = unionElems.get(i);
					if (ic.isInferenceVariable(currElem)) {
						idx = i;
						break;
					}
				}
			}
			if (idx == -1 && leftDecl instanceof ContainerType<?>) {
				for (int i = 0; i < unionElems.size(); i++) {
					final TypeRef currElem = unionElems.get(i);
					if (AllSuperTypesCollector.collect((ContainerType<?>) leftDecl) // FIXME PERFORMANCE!
							.contains(currElem.getDeclaredType())) {
						idx = i;
						break;
					}
				}
			}
		}
		if (idx == -1) {
			// choose first naked inference variable (if any)
			for (int i = 0; i < unionElems.size(); i++) {
				final TypeRef currElem = unionElems.get(i);
				if (ic.isInferenceVariable(currElem)) {
					idx = i;
					break;
				}
			}
		}
		if (idx == -1) {
			// choose 'any' (if present)
			for (int i = 0; i < unionElems.size(); i++) {
				final TypeRef currElem = unionElems.get(i);
				if (currElem.getDeclaredType() == RuleEnvironmentExtensions.anyType(G)) {
					idx = i;
					break;
				}
			}
		}
		if (idx == -1) {
			// choose 'Object' (if present)
			for (int i = 0; i < unionElems.size(); i++) {
				final TypeRef currElem = unionElems.get(i);
				if (currElem.getDeclaredType() == RuleEnvironmentExtensions.objectType(G)) {
					idx = i;
					break;
				}
			}
		}
		if (idx == -1) {
			// choose 'N4Object' (if present)
			for (int i = 0; i < unionElems.size(); i++) {
				final TypeRef currElem = unionElems.get(i);
				if (currElem.getDeclaredType() == RuleEnvironmentExtensions.n4ObjectType(G)) {
					idx = i;
					break;
				}
			}
		}
		if (idx == -1)
			idx = 0;
		return reduce(left, unionElems.get(idx), CO);
	}

	/**
	 * Cases, argument "right" is intersection:
	 * <ul>
	 * <li>A type is a subtype of an intersection if it is a subtype of each element of the intersection</li>
	 * <li>A type is a supertype of an intersection if it is a supertype of one or more elements of the intersection (as
	 * a special case, the elements of the intersection are supertypes of that intersection)</li>
	 * </ul>
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceIntersectionTypeExpression(TypeRef left, IntersectionTypeExpression right,
			Variance variance) {
		boolean wasAdded = false;
		switch (variance) {
		case CO: {
			// L <: intersection{R1,R2} --> L<:R1 && L<:R2
			for (TypeRef currRight : right.getTypeRefs()) {
				wasAdded |= reduce(left, currRight, CO);
			}
			return wasAdded;
		}
		case CONTRA: {
			return reduceSupertypeOfIntersection(left, right);
		}
		case INV: {
			// should have been handled by invoker
			throw new IllegalStateException("shouldn't get here");
		}
		}
		// unreachable code, each branch above returned something
		throw new IllegalStateException("shouldn't get here");
	}

	// FIXME change method #reduceSupertypeOfIntersection() to be in line with above method #reduceSubtypeOfUnion()
	// (ideally without code duplication)

	/**
	 * L :> intersection{R1,R2} --> L:>R1 || L:>R2
	 *
	 * That requires backtracking. Easier when the intersection type has been canonicalized (ie, its direct elements are
	 * non-redundant) ie fan-out is "only" n. Otherwise fan-out is 2^n.
	 *
	 * IMPORTANT: Why dedicate a method to this reduction? So as to bring to the forefront (and document) the
	 * heuristics, special-case handling, and approximations chosen in this implementation.
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceSupertypeOfIntersection(TypeRef left, IntersectionTypeExpression right) {
		EList<TypeRef> intersectElems = right.getTypeRefs();
		if (intersectElems.isEmpty()) {
			// intersection{} (ie, of the empty list of elements) stands for TOP
			return reduce(top(), left, CO);
		}
		// (1) try finding an R_i component such that L :> R_i
		TypeRef leftProper = ic.isProper(left) ? left : null;
		if (null == leftProper) {
			// --------------------------------------------------
			// (1) try approximations (ie, safe but not complete)
			// --------------------------------------------------
			// over-constrain by picking arbitrarily the first elem (which isn't guaranteed to succeed)
			return reduce(intersectElems.get(0), left, CO);
			// an alternative is to under-constrain, namely adopt L :> tsh.meet(G, {R1, R2})
		}
		TypeRef rightProper = ic.isProper(right) ? right : null;
		if (null != rightProper) {
			if (rightProper.isBottomType()) {
				// the intersection is uninhabited, ie it's bottom
				// ------------------------------------------
				// (5) any type is trivially super of bottom. Instead of canonicalizing the intersection (and lose
				// information) we tighten instead the constraint as follows: `left :> union(original-elems)`
				// which in turn reduces to `left :> R1 && left :> R2`
				// That's the bound reduced in place of the original one.
				// ------------------------------------------
				boolean wasReduced = false;
				for (TypeRef elem : right.getTypeRefs()) {
					wasReduced = reduce(left, elem, CONTRA);
				}
				return wasReduced;
			}
			// ------------------------------------------
			// (2) an intersection is a subtype of itself
			// ------------------------------------------
			return addBound(ts.subtypeSucceeded(G, leftProper, rightProper));
		}
		// intersectElems will be partitioned into proper and non-proper.
		List<TypeRef> nonProperElems = new ArrayList<>();
		for (TypeRef interElem : intersectElems) {
			// ---------------------------------------------------
			// (3) try finding an R_i component such that L :> R_i
			// ---------------------------------------------------
			TypeRef interElemProper = ic.isProper(interElem) ? interElem : null;
			if (null == interElemProper) {
				nonProperElems.add(interElem);
			} else {
				// subtype check possible
				if (ts.subtypeSucceeded(G, interElemProper, leftProper)) {
					// positive result, reduction is complete (TRUE added)
					return false; // no bounds added during this invocation
				}
				// negative result, element can be ignored for the purpose of reducing L :> intersection
			}
		}
		if (nonProperElems.isEmpty()) {
			// all elems of the intersection were tested, none is a subtype of L
			return abort(leftProper, rightProper, CONTRA);
		}
		if (nonProperElems.size() == 1) {
			return reduce(nonProperElems.get(0), left, CO);
		}
		// --------------------------------------------------
		// (4) try approximations (ie, safe but not complete)
		// --------------------------------------------------
		return reduce(nonProperElems.get(0), left, CO);
	}

	/**
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceClassifierTypeRef(ClassifierTypeRef left, ClassifierTypeRef right, Variance variance) {
		final TypeRef leftStatic = TypeUtils.copy(left.getTypeRef());
		final TypeRef rightStatic = TypeUtils.copy(right.getTypeRef());
		if (!(left instanceof ConstructorTypeRef) && !(right instanceof ConstructorTypeRef)) {
			// both sides are plain ClassifierTypeRefs
			return reduce(leftStatic, rightStatic, variance);
		} else {
			// at least one side is ConstructorTypeRef
			return reduce(leftStatic, rightStatic, Variance.INV); // FIXME reconsider
		}
	}

	/**
	 * The typing constraint {@code fun-type-left op fun-type-right} is reduced by:
	 * <ul>
	 * <li>reducing each pair of corresponding formals; as well as</li>
	 * <li>reducing the pair of return types.</li>
	 * </ul>
	 * Regarding variance, a function A can be used in place of E (an expected function) if A's formals are as general
	 * as those of E and the return type of E is as general as that of A.
	 * <p>
	 * IMPORTANT: the implementation of this method has to be kept consistent with
	 * {@link SubtypeComputer#isSubtypeFunction(RuleEnvironment, FunctionTypeExprOrRef, FunctionTypeExprOrRef)} and esp.
	 * <code>#primIsSubtypeFunction()</code>.
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceFunctionTypeExprOrRef(FunctionTypeExprOrRef left, FunctionTypeExprOrRef right,
			Variance variance) {
		if (left.isGeneric() || right.isGeneric()) {
			final FunctionTypeExprOrRef leftNonGen = ic.newInferenceVariablesFor(left);
			final FunctionTypeExprOrRef rightNonGen = ic.newInferenceVariablesFor(right);
			return reduceFunctionTypeExprOrRef(leftNonGen, rightNonGen, variance);
		}
		boolean wasAdded = false;
		// derive constraints for types of fpars
		final Iterator<TFormalParameter> valueParsIt = right.getFpars().iterator();
		for (TFormalParameter keyPar : left.getFpars()) {
			if (valueParsIt.hasNext()) {
				wasAdded |= reduce(keyPar.getTypeRef(), valueParsIt.next().getTypeRef(),
						variance.mult(Variance.CONTRA));
			}
		}
		// derive constraints for return types
		final boolean isVoidLeft = TypeUtils.isVoidReturnType(left);
		final boolean isVoidRight = TypeUtils.isVoidReturnType(right);
		if (isVoidLeft && isVoidRight) {
			// void on both sides:
			wasAdded |= addBound(true);
		} else if ((variance == Variance.CO && isVoidRight) || (variance == Variance.CONTRA && isVoidLeft)) {
			// we have a constraint like:
			// ⟨ {function():α} <: {function():void} ⟩
			// --> α is not constrained in any way --> just add bound TRUE
			wasAdded |= addBound(true);
		} else if (isVoidLeft || isVoidRight) {
			// we have a constraint like:
			// ⟨ {function():void} <: {function():α} ⟩ or ⟨ {function():void} = {function():α} ⟩
			// --> we're doomed, unless the non-void return type is optional
			final TypeRef nonVoidReturnType = isVoidLeft ? right.getReturnTypeRef() : left.getReturnTypeRef();
			wasAdded |= addBound(TypeUtils.isOptional(nonVoidReturnType)); // n.b.: #isOptional() is null-safe
		} else {
			wasAdded |= reduce(left.getReturnTypeRef(), right.getReturnTypeRef(), variance.mult(Variance.CO));
		}
		// derive constraints for declared this types
		final TypeRef leftThis = left.getDeclaredThisType();
		final TypeRef rightThis = right.getDeclaredThisType();
		if (leftThis != null || rightThis != null) {
			if (leftThis == null && rightThis != null) {
				if (variance == CO) {
					wasAdded |= addBound(true);
				} else {
					wasAdded |= abort(left, right, variance);
				}
			} else if (leftThis != null && rightThis == null) {
				if (variance == CONTRA) {
					wasAdded |= addBound(true);
				} else {
					wasAdded |= abort(left, right, variance);
				}
			} else if (leftThis != null && rightThis != null) {
				wasAdded |= reduce(leftThis, rightThis, variance.mult(Variance.CONTRA));
			}
		}
		return wasAdded;
	}

	/**
	 * For {@code C<S1...Sn> <: D<T1...Tn>} to hold it must be the case that {@code C <: D} and moreover for each
	 * {@code 1 <= i <= n} according to the shape of Si and Ti some of the following conditions must hold:
	 * <ul>
	 * <li>{@code T <: T}
	 * <li>{@code T <: ? extends T}
	 * <li>{@code T <: ? super T}
	 * <li>{@code if T<:S then (? extends T) <: (? extends S)}
	 * <li>{@code if S<:T then (? super T) <: (? super S)}
	 * <li>{@code ? extends T <: T}
	 * <li>{@code if X<:Y then (? extends X) <: (? super Y)}
	 * </ul>
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceParameterizedTypeRef(ParameterizedTypeRef left, ParameterizedTypeRef right,
			Variance variance) {
		// FIXME support for structural typing (i.e. if right is structural!)
		final TypeRef leftRaw = TypeUtils.createTypeRef(left.getDeclaredType());
		final TypeRef rightRaw = TypeUtils.createTypeRef(right.getDeclaredType()); // note: enforcing nominal here!
		if ((variance == CO && !ts.subtypeSucceeded(G, leftRaw, rightRaw))
				|| (variance == CONTRA && !ts.subtypeSucceeded(G, rightRaw, leftRaw))
				|| (variance == INV && !ts.equaltypeSucceeded(G, leftRaw, rightRaw))) {
			return abort(left, right, variance);
		}
		//
		// here we have a situation like G<IV> <-> G<string> which may result from
		// code like:
		//
		// class G<T> {}
		// function <IV> f(G<IV> p) : void {}
		// var G<string> gstr;
		// f(gstr); // "expected type <-> actual type" will lead to "G<IV> <-> G<string>"
		//
		// resulting in the constraint "IV = string".
		//
		// However, it is not always that simple. The type argument corresponding to the
		// inference variable ('string' corresponding to 'IV' in the above example) might
		// not be held by the ParameterizedTypeRef but may instead be contained somewhere
		// in the inheritance hierarchy of 'value'. For example:
		//
		// class G<T> {}
		// function <IV> f(G<IV> p) : void {}
		// class C extends G<string> {}
		// var C c;
		// f(c); // will lead to "G<IV> <-> C"
		//
		// Of course, the inheritance hierarchy might be arbitrarily complex and it is the
		// job of method #addSubstitutions(RuleEnv,TypeRef) in GenericsComputer to deal with
		// that. So, we have to be careful to reuse that method here to not duplicate logic!
		//
		// Solution:
		// We derive a constraint for the above situations in several steps
		// G<IV> <-> C
		// (map each type argument of 'left' to corresponding type parameter of 'left')
		// IV <-> T
		// (perform type variable substitution on the right side based on the substitutions
		// defined by the right side, i.e. substitutions obtained by calling #addSubstitutions(RuleEnv,'right'))
		// IV <-> string
		boolean wasAdded = false;
		final RuleEnvironment Gx = RuleEnvironmentExtensions.newRuleEnvironment(G);
		tsh.addSubstitutions(Gx, right);
		final Type leftType = left.getDeclaredType();
		final List<TypeArgument> leftArgs = left.getTypeArgs();
		final List<TypeVariable> leftParams = leftType.getTypeVars();
		final int len = Math.min(leftArgs.size(), leftParams.size());
		for (int idx = 0; idx < len; ++idx) {
			final TypeArgument leftArg = leftArgs.get(idx);
			final TypeVariable leftParam = leftParams.get(idx);
			if (RuleEnvironmentExtensions.hasSubstitutionFor(Gx, leftParam)) {
				final TypeArgument leftParamSubst = ts.substTypeVariables(Gx, typeRef(leftParam)).getValue();
				if (leftArg instanceof Wildcard) {
					final TypeRef ub = ((Wildcard) leftArg).getDeclaredUpperBound();
					if (ub != null) {
						wasAdded |= reduce(ub, ts.upperBound(G, leftParamSubst).getValue(), CONTRA);
					}
					final TypeRef lb = ((Wildcard) leftArg).getDeclaredLowerBound();
					if (lb != null) {
						wasAdded |= reduce(lb, ts.lowerBound(G, leftParamSubst).getValue(), CO);
					}
				} else if (leftParamSubst instanceof ExistentialTypeRef) {
					// TODO IDE-1653 reconsider this entire case
					// re-open the existential type, because we assume it was closed only while adding substitutions
					// TODO this is wrong if right.typeArgs already contained an ExistentialTypeRef! (but might be
					// an non-harmful over approximation)
					final Wildcard w = ((ExistentialTypeRef) leftParamSubst).getWildcard();
					final TypeRef ub = w.getDeclaredUpperBound();
					if (ub != null) {
						wasAdded |= reduce(ub, ts.upperBound(G, leftArg).getValue(), CONTRA);
					}
					final TypeRef lb = w.getDeclaredLowerBound();
					if (lb != null) {
						wasAdded |= reduce(lb, ts.lowerBound(G, leftArg).getValue(), CO);
					}
				} else {
					if (!(leftArg instanceof TypeRef)) {
						throw new UnsupportedOperationException("unsupported subtype of TypeArgument: "
								+ leftArg.getClass().getName());
					}
					wasAdded |= reduce(leftArg, leftParamSubst, variance.mult(INV));
				}
			}
		}
		return wasAdded;
	}

	private boolean reduceStructuralTypeRef(TypeRef left, TypeRef right, Variance variance) {
		if (variance == Variance.CONTRA) {
			return reduceStructuralTypeRef(right, left, Variance.CO);
		}
		// now, variance is either CO or INV

		final StructuralTypingComputer stc = tsh.getStructuralTypingComputer();
		final RuleEnvironment G2 = RuleEnvironmentExtensions.wrap(G);
		final StructTypingInfo infoFaked = new StructTypingInfo(G2, left, right,
				left.getTypingStrategy(), right.getTypingStrategy()); // <- G2 will be changed!

		boolean wasAdded = false;
		final StructuralTypesHelper structTypesHelper = tsh.getStructuralTypesHelper();
		final StructuralMembersTripleIterator iter = structTypesHelper.getMembersTripleIterator(G2, left, right, false);
		while (iter.hasNext()) {
			final StructuralMembersTriple next = iter.next();
			final TMember l = next.getLeft();
			final TMember r = next.getRight();
			if (l == null || r == null) {
				// ignore missing members
				// (Note: we're ignoring this altogether, here. We could check if the existing member is optional and
				// otherwise add bound FALSE, but this is not our job. There are other validations checking that and
				// commencing with type inference here produces better error messages.)
				continue;
			}
			final TypeConstraint constraint = stc.reduceMembers(l, r, variance, infoFaked);
			if (containsReopenedExistentialType(G2, constraint)) { // note: using G2 here, not G!
				// by completely ignoring all constraints that contain a re-opened ExistentialTypeRef we might lose some
				// information; but otherwise we would have to deal with this all throughout InferenceContext,
				// Reducer, BoundSet
				// TODO reconsider handling of re-opened ExistentialTypeRefs in InferenceContext, IDE-1653
				continue;
			}
			wasAdded |= reduce(constraint);
		}
		return wasAdded;
	}

	private boolean mightBeSubtypeOf(FunctionTypeExprOrRef left, FunctionTypeExprOrRef right) {
		// step 1: replace all inference variables by UnknownTypeRef
		final TypeRef unknown = TypeRefsFactory.eINSTANCE.createUnknownTypeRef();
		final RuleEnvironment G_temp = RuleEnvironmentExtensions.newRuleEnvironment(G);
		for (TypeVariable iv : ic.getInferenceVariables()) {
			RuleEnvironmentExtensions.addTypeMapping(G_temp, iv, unknown);
		}
		final TypeArgument leftSubst = ts.substTypeVariables(G_temp, left).getValue();
		final TypeArgument rightSubst = ts.substTypeVariables(G_temp, right).getValue();
		return ts.subtypeSucceeded(G, leftSubst, rightSubst);
	}

	private void log(final String message) {
		ic.log(message);
	}

	private static final boolean containsReopenedExistentialType(RuleEnvironment someG, TypeConstraint constraint) {
		return constraint != null
				&& (RuleEnvironmentExtensions.isExistentialTypeToBeReopened(someG, constraint.left, true)
						|| RuleEnvironmentExtensions.isExistentialTypeToBeReopened(someG, constraint.right, true));
	}
}
