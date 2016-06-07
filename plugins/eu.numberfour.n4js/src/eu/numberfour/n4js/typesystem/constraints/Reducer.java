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
import static eu.numberfour.n4js.typesystem.constraints.Reducer.BooleanOp.CONJUNCTION;
import static eu.numberfour.n4js.typesystem.constraints.Reducer.BooleanOp.DISJUNCTION;

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
import eu.numberfour.n4js.ts.types.TClassifier;
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
 * Contains all logic for reduction, i.e. for reducing a {@link TypeConstraint} into simpler {@link TypeBound}s. A
 * {@code Reducer} does not own any state. Instead, it's a collaborator of its {@link InferenceContext inference
 * context}, operating on the {@link BoundSet bound set} of that inference context.
 */
/* package */ final class Reducer {

	private static final boolean DEBUG = InferenceContext.DEBUG;

	private final InferenceContext ic;

	private final RuleEnvironment G;
	private final N4JSTypeSystem ts;
	private final TypeSystemHelper tsh;

	enum BooleanOp {
		CONJUNCTION, DISJUNCTION
	}

	/**
	 * Creates an instance.
	 */
	public Reducer(InferenceContext ic, RuleEnvironment G, N4JSTypeSystem ts, TypeSystemHelper tsh) {
		this.ic = ic;
		this.G = G;
		this.ts = ts;
		this.tsh = tsh;
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
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean addBound(TypeVariable infVar, TypeRef bound, Variance variance) {
		assert ic.isInferenceVariable(infVar);
		return ic.currentBounds.addBound(new TypeBound(infVar, bound, variance));
	}

	/**
	 * Add bound <code>FALSE</code>, thus making the inference context {@link InferenceContext#isDoomed() doomed}.
	 */
	private boolean giveUp(EObject left, EObject right, Variance variance) {
		if (DEBUG) {
			log("GIVING UP ON: " + TypeConstraint.toString(left, right, variance));
		}
		return addBound(false);
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
	 * Reduces the the type constraint defined by the given left-hand and right-hand side and the given variance.
	 * <p>
	 * Always invoke this method instead of the more specific <code>#reduce*()</code> methods, both for readability and
	 * to ensure completeness of log messages.
	 *
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	public boolean reduce(TypeArgument left, TypeArgument right, Variance variance) {
		if (DEBUG) {
			log("reducing: " + TypeConstraint.toString(left, right, variance));
		}
		if (left == null || right == null) {
			if (DEBUG) {
				log("ignoring constraint due to null values");
			}
			return false;
		}
		if ((left instanceof TypeRef) && (right instanceof TypeRef)) {
			// both TypeRefs
			return reduceTypeRef((TypeRef) left, (TypeRef) right, variance);
		}
		// at least one wildcard
		if ((left instanceof Wildcard) && (right instanceof Wildcard)) {
			// both wildcards
			return reduceWildcard((Wildcard) left, (Wildcard) right, variance);
		}
		// a wildcard and a TypeRef, in any order
		return giveUp(left, right, variance); // TODO probably wrong like this? cf. IDE-1653
	}

	/**
	 * Reduces a set of type constraints with the same left-hand side and variance.
	 * <p>
	 * If <code>operator</code> is {@link BooleanOp#CONJUNCTION CONJUNCTION}, then this is merely a convenience method
	 * for invoking method {@link #reduce(TypeArgument, TypeArgument, Variance)} several times. If <code>operator</code>
	 * is {@link BooleanOp#DISJUNCTION DISJUNCTION}, then this is more tricky.
	 * <p>
	 * We could easily implement disjunctions of type constraints and bounds using backtracking (start with adding the
	 * first disjoint constraint/bound and continue; if constraint system unsolvable, go back to previous state and add
	 * second disjoint constraint/bound and continue; and so on until a solution is found). However, we do not want to
	 * do this, for performance reasons.
	 * <p>
	 * Therefore, this method applies heuristics to choose the "most promising" of the disjoint constraints and
	 * continues only with that single constraint; all other possible paths are ignored. This is an over-approximation
	 * (we might overlook valid solutions, but a solution we find is never invalid).
	 */
	private boolean reduce(TypeRef left, List<TypeRef> rights, Variance variance, BooleanOp operator) {
		if (operator == CONJUNCTION) {
			// simple case: simply call #reduce() several times
			boolean wasAdded = false;
			for (TypeRef currRight : rights) {
				wasAdded |= reduce(left, currRight, variance);
			}
			return wasAdded;
		} else /* operator == DISJUNCTION */ {
			// tricky case (because we want to avoid backtracking)
			final int rightsSize = rights.size();
			if (rightsSize == 0) {
				return false;
			} else if (rightsSize == 1) {
				return reduce(left, rights.get(0), variance);
			} else {
				// choose the "most promising" of the disjoint constraints and continue with that (and simply ignore the
				// other possible paths)
				int idx = -1;
				if (idx == -1 && left instanceof FunctionTypeExprOrRef) {
					// choose first function type (except those for which it is obvious they cannot match)
					for (int i = 0; i < rightsSize; i++) {
						final TypeRef currElem = rights.get(i);
						if (currElem instanceof FunctionTypeExprOrRef) {
							final FunctionTypeExprOrRef leftCasted = (FunctionTypeExprOrRef) left;
							final FunctionTypeExprOrRef currElemCasted = (FunctionTypeExprOrRef) currElem;
							final boolean mightMatch = (variance == CO && mightBeSubtypeOf(leftCasted, currElemCasted))
									|| (variance == CONTRA && mightBeSubtypeOf(currElemCasted, leftCasted))
									|| (variance == INV && mightBeSubtypeOf(leftCasted, currElemCasted)
											&& mightBeSubtypeOf(currElemCasted, leftCasted));
							if (mightMatch) {
								idx = i;
								break;
							}
						}
					}
				}
				if (idx == -1 && left instanceof ParameterizedTypeRef && !ic.isInferenceVariable(left)) {
					final Type leftDecl = left.getDeclaredType();
					if (idx == -1 && leftDecl != null) {
						// choose first matching declared type
						for (int i = 0; i < rightsSize; i++) {
							final TypeRef currElem = rights.get(i);
							if (leftDecl == currElem.getDeclaredType()) {
								idx = i;
								break;
							}
						}
					}
					if (idx == -1 && leftDecl instanceof PrimitiveType) {
						// choose first naked inference variable (if any)
						// (note: same as below, but has higher priority for primitive types than next heuristic)
						idx = chooseFirstInferenceVariable(rights);
					}
					if (idx == -1 && variance == CO && leftDecl instanceof ContainerType<?>) {
						// choose first supertype of left
						final List<TClassifier> superTypesOfLeft = AllSuperTypesCollector
								.collect((ContainerType<?>) leftDecl);
						for (int i = 0; i < rightsSize; i++) {
							final TypeRef currElem = rights.get(i);
							final Type currElemDecl = currElem.getDeclaredType();
							if (currElemDecl != null && superTypesOfLeft.contains(currElemDecl)) {
								idx = i;
								break;
							}
						}
					}
					if (idx == -1 && variance == CONTRA && leftDecl != null) {
						// choose first subtype of left
						for (int i = 0; i < rightsSize; i++) {
							final TypeRef currElem = rights.get(i);
							final Type currElemDecl = currElem.getDeclaredType();
							if (currElemDecl instanceof ContainerType<?>) {
								// TODO improve performance by using a super class iterator or super interfaces iterator
								// depending on type of leftDecl
								final List<TClassifier> superTypesOfCurrElem = AllSuperTypesCollector
										.collect((ContainerType<?>) currElemDecl);
								if (superTypesOfCurrElem.contains(leftDecl)) {
									idx = i;
									break;
								}
							}
						}
					}
				}
				if (idx == -1) {
					// choose first naked inference variable (if present)
					idx = chooseFirstInferenceVariable(rights);
				}
				if (idx == -1 && variance == CO) {
					// choose the top type 'any' or one of the pseudo-top types: Object, N4Object
					if (idx == -1)
						idx = chooseFirstWithDeclTypeOf(rights, RuleEnvironmentExtensions.topType(G));
					if (idx == -1)
						idx = chooseFirstWithDeclTypeOf(rights, RuleEnvironmentExtensions.objectType(G));
					if (idx == -1)
						idx = chooseFirstWithDeclTypeOf(rights, RuleEnvironmentExtensions.n4ObjectType(G));
				}
				if (idx == -1 && variance == CONTRA) {
					// choose the bottom type 'undefined' or one of the pseudo-bottom types: null
					if (idx == -1)
						idx = chooseFirstWithDeclTypeOf(rights, RuleEnvironmentExtensions.bottomType(G));
					if (idx == -1)
						idx = chooseFirstWithDeclTypeOf(rights, RuleEnvironmentExtensions.nullType(G));
				}
				if (idx == -1) {
					// simply choose the first member type (yes, we're pretty desperate at this point)
					idx = 0;
				}
				return reduce(left, rights.get(idx), variance);
			}
		}
	}

	private final int chooseFirstInferenceVariable(List<TypeRef> typeRefs) {
		final int typeRefsSize = typeRefs.size();
		for (int i = 0; i < typeRefsSize; i++) {
			final TypeRef currTypeRef = typeRefs.get(i);
			if (ic.isInferenceVariable(currTypeRef)) {
				return i;
			}
		}
		return -1;
	}

	private final int chooseFirstWithDeclTypeOf(List<TypeRef> typeRefs, Type declType) {
		final int typeRefsSize = typeRefs.size();
		for (int i = 0; i < typeRefsSize; i++) {
			final TypeRef currElem = typeRefs.get(i);
			if (currElem != null && currElem.getDeclaredType() == declType) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceTypeRef(TypeRef left, TypeRef right, Variance variance) {
		final boolean isLeftProper = ic.isProper(left);
		final boolean isRightProper = ic.isProper(right);
		if (isLeftProper && isRightProper) {
			return reduceProper(left, right, variance);
		}

		final boolean isLeftInfVar = ic.isInferenceVariable(left);
		final boolean isRightInfVar = ic.isInferenceVariable(right);
		if (isLeftInfVar || isRightInfVar) {
			if (isLeftInfVar) {
				return addBound((TypeVariable) left.getDeclaredType(), right, variance);
			} else {
				return addBound((TypeVariable) right.getDeclaredType(), left, variance.inverse());
			}
		}

		final boolean isLeftStructural = left.isUseSiteStructuralTyping() || left.isDefSiteStructuralTyping();
		final boolean isRightStructural = right.isUseSiteStructuralTyping() || right.isDefSiteStructuralTyping();
		if ((isLeftStructural && (variance == CONTRA || variance == INV))
				|| (isRightStructural && (variance == CO || variance == INV))) {
			return reduceStructuralTypeRef(left, right, variance);
		}
		// note: one side might still be structural, but we can ignore this
		// (e.g. given ⟨ S <: N ⟩ with S being structural, N nominal, we have a plain nominal subtype relation)

		if (left instanceof ComposedTypeRef) {
			return reduceComposedTypeRef(right, (ComposedTypeRef) left, variance.inverse());
		}
		if (right instanceof ComposedTypeRef) {
			return reduceComposedTypeRef(left, (ComposedTypeRef) right, variance);
		}

		if (left instanceof ClassifierTypeRef && right instanceof ClassifierTypeRef) {
			return reduceClassifierTypeRef((ClassifierTypeRef) left, (ClassifierTypeRef) right, variance);
		} else if (left instanceof FunctionTypeExprOrRef && right instanceof FunctionTypeExprOrRef) {
			return reduceFunctionTypeExprOrRef((FunctionTypeExprOrRef) left, (FunctionTypeExprOrRef) right, variance);
		} else if (left instanceof ParameterizedTypeRef && right instanceof ParameterizedTypeRef) {
			return reduceParameterizedTypeRef((ParameterizedTypeRef) left, (ParameterizedTypeRef) right, variance);
		} else {
			// different subtypes of TypeRef on left and right side
			if (left instanceof ParameterizedTypeRef
					&& left.getDeclaredType() == RuleEnvironmentExtensions.bottomType(G)) {
				// a constraint like ⟨ undefined <: {function(number):α} ⟩
				return addBound(true);
			} else if (right instanceof ParameterizedTypeRef
					&& right.getDeclaredType() == RuleEnvironmentExtensions.topType(G)) {
				// a constraint like ⟨ {function(number):α} <: any ⟩
				return addBound(true);
			} else {
				// in all other cases
				return giveUp(left, right, variance);
			}
		}
	}

	/**
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	// TODO IDE-1653 reconsider handling of wildcards in Reducer#reduceWildcard()
	private boolean reduceWildcard(Wildcard left, Wildcard right, @SuppressWarnings("unused") Variance variance) {
		if (left == right) {
			// trivial ==, <:, and :> of a wildcard to itself.
			return false;
		}
		boolean wasAdded = false;
		final TypeRef lbLeft = left.getDeclaredLowerBound();
		final TypeRef lbRight = right.getDeclaredLowerBound();
		if (lbLeft != null || lbRight != null) {
			// ⟨ ? super L Φ ? ⟩ implies `L = bottom`
			// ⟨ ? super L Φ ? extends R ⟩ implies `L = bottom`
			// ⟨ ? super L Φ ? super R ⟩ implies `L = R`
			final TypeRef lbLeftOrBottom = (lbLeft != null) ? lbLeft : bottom();
			final TypeRef lbRightOrBottom = (lbRight != null) ? lbRight : bottom();
			wasAdded |= reduce(lbLeftOrBottom, lbRightOrBottom, INV);
		}
		final TypeRef ubLeft = left.getDeclaredUpperBound();
		final TypeRef ubRight = right.getDeclaredUpperBound();
		if (ubLeft != null || ubRight != null) {
			// ⟨ ? extends L Φ ? ⟩ implies `L = top`
			// ⟨ ? extends L Φ ? super R ⟩ implies `L = top`
			// ⟨ ? extends L Φ ? extends R ⟩ implies `L = R`
			final TypeRef ubLeftOrTop = (ubLeft != null) ? ubLeft : top();
			final TypeRef ubRightOrTop = (ubRight != null) ? ubRight : top();
			wasAdded |= reduce(ubLeftOrTop, ubRightOrTop, INV);
		}
		return wasAdded;
	}

	/**
	 * @return true iff FALSE was added (adding TRUE requires no new round of incorporation to follow)
	 */
	private boolean reduceProper(TypeRef left, TypeRef right, Variance variance) {
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// recursion guard
		final Pair<String, Pair<TypeRef, TypeRef>> key = Pair.of(RuleEnvironmentExtensions.GUARD_REDUCER_REDUCE_PROPER,
				Pair.of(left, right));
		if (G.get(key) != null) {
			return true;
		}
		final RuleEnvironment G2 = RuleEnvironmentExtensions.wrap(G);
		G2.add(key, Boolean.TRUE);
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		switch (variance) {
		case CO:
			return addBound(ts.subtypeSucceeded(G2, left, right));
		case CONTRA:
			return addBound(ts.subtypeSucceeded(G2, right, left));
		case INV:
			return addBound(ts.equaltypeSucceeded(G2, left, right));
		}
		throw new IllegalStateException("unreachable"); // actually unreachable, each case above returns
	}

	/**
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceComposedTypeRef(TypeRef left, ComposedTypeRef right, Variance variance) {
		if (variance == INV) {
			boolean wasAdded = false;
			wasAdded |= reduceComposedTypeRef(left, right, CO); // n.b.: not invoking #reduce() here!
			wasAdded |= reduceComposedTypeRef(left, right, CONTRA); // n.b.: not invoking #reduce() here!
			return wasAdded;
		}
		if (right instanceof UnionTypeExpression) {
			return reduceUnion(left, (UnionTypeExpression) right, variance);
		}
		if (right instanceof IntersectionTypeExpression) {
			return reduceIntersection(left, (IntersectionTypeExpression) right, variance);
		}
		throw new IllegalStateException("shouldn't get here");
	}

	/**
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceUnion(TypeRef left, UnionTypeExpression right, Variance variance) {
		switch (variance) {
		case CO:
			// ⟨ L <: union{R1,R2} ⟩ implies `L <: R1` or(!) `L <: R2`
			// we've got a disjunction of several type bounds -> tricky case!
			return reduce(left, right.getTypeRefs(), CO, DISJUNCTION);
		case CONTRA:
			// ⟨ L :> union{R1,R2} ⟩ implies `L :> R1` and `L :> R2`
			// we've got a conjunction of several type bounds -> standard case
			return reduce(left, right.getTypeRefs(), CONTRA, CONJUNCTION);
		case INV:
			throw new IllegalStateException("shouldn't get here"); // should have been handled by invoker
		}
		throw new IllegalStateException("unreachable"); // actually unreachable, each case above returns or throws
	}

	/**
	 * @return true iff new bounds were added (this signals a round of incorporation should follow)
	 */
	private boolean reduceIntersection(TypeRef left, IntersectionTypeExpression right, Variance variance) {
		switch (variance) {
		case CO:
			// ⟨ L <: intersection{R1,R2} ⟩ implies `L <: R1` and `L <: R2`
			// we've got a conjunction of several type bounds -> standard case
			return reduce(left, right.getTypeRefs(), CO, CONJUNCTION);
		case CONTRA:
			// ⟨ L :> intersection{R1,R2} ⟩ implies `L :> R1` or(!) `L :> R2`
			// we've got a disjunction of several type bounds -> tricky case!
			// return reduce(left, right.getTypeRefs(), CONTRA, DISJUNCTION);
			return reduceSupertypeOfIntersection(left, right);
		case INV:
			throw new IllegalStateException("shouldn't get here"); // should have been handled by invoker
		}
		throw new IllegalStateException("unreachable"); // actually unreachable, each case above returns or throws
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
			return giveUp(leftProper, rightProper, CONTRA);
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
		final TypeRef leftStatic = TypeUtils.copy(left.getStaticTypeRef());
		final TypeRef rightStatic = TypeUtils.copy(right.getStaticTypeRef());
		if (!(left instanceof ConstructorTypeRef) && !(right instanceof ConstructorTypeRef)) {
			// both sides are plain ClassifierTypeRefs
			return reduce(leftStatic, rightStatic, variance);
		} else {
			// at least one side is ConstructorTypeRef
			return reduce(leftStatic, rightStatic, INV); // FIXME reconsider
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
						variance.mult(CONTRA));
			}
		}
		// derive constraints for return types
		final boolean isVoidLeft = TypeUtils.isVoidReturnType(left);
		final boolean isVoidRight = TypeUtils.isVoidReturnType(right);
		if (isVoidLeft && isVoidRight) {
			// void on both sides:
			wasAdded |= addBound(true);
		} else if ((variance == CO && isVoidRight) || (variance == CONTRA && isVoidLeft)) {
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
			wasAdded |= reduce(left.getReturnTypeRef(), right.getReturnTypeRef(), variance.mult(CO));
		}
		// derive constraints for declared this types
		final TypeRef leftThis = left.getDeclaredThisType();
		final TypeRef rightThis = right.getDeclaredThisType();
		if (leftThis != null || rightThis != null) {
			if (leftThis == null && rightThis != null) {
				if (variance == CO) {
					wasAdded |= addBound(true);
				} else {
					wasAdded |= giveUp(left, right, variance);
				}
			} else if (leftThis != null && rightThis == null) {
				if (variance == CONTRA) {
					wasAdded |= addBound(true);
				} else {
					wasAdded |= giveUp(left, right, variance);
				}
			} else if (leftThis != null && rightThis != null) {
				wasAdded |= reduce(leftThis, rightThis, variance.mult(CONTRA));
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
			return giveUp(left, right, variance);
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
				final TypeArgument leftParamSubst = ts.substTypeVariables(Gx, TypeUtils.createTypeRef(leftParam))
						.getValue();
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
		if (variance == CONTRA) {
			return reduceStructuralTypeRef(right, left, CO);
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

	private static final boolean containsReopenedExistentialType(RuleEnvironment someG, TypeConstraint constraint) {
		return constraint != null
				&& (RuleEnvironmentExtensions.isExistentialTypeToBeReopened(someG, constraint.left, true)
						|| RuleEnvironmentExtensions.isExistentialTypeToBeReopened(someG, constraint.right, true));
	}

	private TypeRef bottom() {
		return RuleEnvironmentExtensions.bottomTypeRef(G);
	}

	private TypeRef top() {
		return RuleEnvironmentExtensions.topTypeRef(G);
	}

	private void log(final String message) {
		ic.log(message);
	}
}
