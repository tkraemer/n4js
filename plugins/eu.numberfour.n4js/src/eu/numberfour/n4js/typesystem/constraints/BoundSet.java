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

import static eu.numberfour.n4js.typesystem.constraints.Variance.CO;
import static eu.numberfour.n4js.typesystem.constraints.Variance.CONTRA;
import static eu.numberfour.n4js.typesystem.constraints.Variance.INV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions;
import eu.numberfour.n4js.typesystem.TypeVarUtils;
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.TypeVariable;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * A bound-set:
 * <ul>
 * <li>manages a growing set of bounds, classified by the their "left" {@link TypeVariable}</li>
 * <li>supports {@link #incorporate() incorporation}, the process that analyzes pairs of existing bounds to derive a
 * third one (implied by the former) which becomes part of the bound set</li>
 * <li>once a bound has been added, it's never removed</li>
 * </ul>
 * <p>
 * A bound-set participates as collaborator supporting {@link InferenceContext type inference}.
 */
class BoundSet {

	private static final boolean DEBUG = InferenceContext.DEBUG;

	private final InferenceContext ic;
	private final RuleEnvironment G;
	private final N4JSTypeSystem ts;

	private final Reducer redu;

	/**
	 * Because the step of adding a "mirroring bound" mustn't be skipped, the only method entitled to manipulate this
	 * map is {@link #addBound(TypeBound)}.
	 */
	private final Map<TypeVariable, Set<TypeBound>> boundsPerInfVar;

	private final List<TypeBound> incorporatedBounds;
	private final Map<TypeVariable, TypeRef> instantiations;

	/**
	 * Flag to escalate a contradiction (once set, it stays set). Checked frequently via {@link #hasBoundFALSE()} to
	 * avoid hopeless work.
	 */
	private boolean haveBoundFALSE;

	/**
	 * Flag to indicate at least one {@link TypeUtils#isRawTypeRef(TypeRef) raw type reference} has been added to the
	 * bound set. Used for optimization purposes.
	 */
	private boolean haveRawTypeRef;

	BoundSet(InferenceContext ic, RuleEnvironment G, N4JSTypeSystem ts, Reducer redu) {
		this.ic = ic;
		this.G = G;
		this.ts = ts;
		this.redu = redu;
		this.boundsPerInfVar = new LinkedHashMap<>();
		this.incorporatedBounds = new ArrayList<>();
		this.instantiations = new LinkedHashMap<>();
	}

	/**
	 * Once a contradiction has been detected, constraint solving can quit early: no solution exists anyway.
	 */
	public boolean hasBoundFALSE() {
		return haveBoundFALSE;
	}

	protected void log() {
		getAllBounds().forEachOrdered(b -> b.log(ic));
	}

	/**
	 * Returns the number of type bounds in this bound set.
	 */
	public int size() {
		int result = 0;
		for (Set<TypeBound> s : boundsPerInfVar.values()) {
			result += s.size();
		}
		return result;
	}

	/**
	 * @return the non-null, possibly empty, set of all bounds constraining the argument.
	 */
	public Set<TypeBound> getBounds(TypeVariable infVar) {
		final Set<TypeBound> result = boundsPerInfVar.get(infVar);
		return result != null ? result : Collections.emptySet();
	}

	/**
	 * Does the argument appear as "left" in one ore more bounds?
	 * <ul>
	 * <li>Whether the argument occurs anywhere else (in particular as type argument) isn't checked.</li>
	 * <li>Skipping adding a "mirroring bound" results in one of the vars involved being regarded as "unbound".</li>
	 * </ul>
	 */
	public boolean isBounded(TypeVariable infVar) {
		return !(getBounds(infVar).isEmpty());
	}

	/**
	 * Same as {@link #isBounded(TypeVariable)}, but inverted. Provided for readability reasons.
	 */
	public boolean isUnbounded(TypeVariable infVar) {
		return getBounds(infVar).isEmpty();
	}

	private TypeBound[] getAllBoundsArr() {
		return getAllBounds().toArray(TypeBound[]::new);
	}

	private Stream<TypeBound> getAllBounds() {
		return boundsPerInfVar.values().stream().flatMap(bounds -> bounds.stream());
	}

	public BoundSet copy() {
		final BoundSet result = new BoundSet(this.ic, this.G, this.ts, this.redu);
		for (final Map.Entry<TypeVariable, Set<TypeBound>> currE : boundsPerInfVar.entrySet()) {
			result.boundsPerInfVar.put(currE.getKey(), new LinkedHashSet<>(currE.getValue()));
		}
		result.incorporatedBounds.addAll(incorporatedBounds);
		result.instantiations.putAll(instantiations);
		return result;
	}

	/**
	 * Adds special type bounds TRUE or FALSE. A bound TRUE will be ignored and a bound FALSE will immediately render
	 * the containing constraint system unsolvable, short-circuiting all further reduction, incorporation or resolution
	 * work.
	 */
	public boolean addBound(boolean b) {
		if (DEBUG) {
			log(">>> ADD bound: " + Boolean.toString(b).toUpperCase());
		}
		if (b == false && !haveBoundFALSE) {
			haveBoundFALSE = true;
			return true;
		}
		return false; // when adding TRUE always return false here (restarting new round of incorporation not required)
	}

	/**
	 * Adding a bound doesn't trigger incremental incorporation, simplification.
	 * <p>
	 * For an explanation of why a "mirroring bound" is added (for bounds involving two variables only) see
	 * {@link #dependsOnResolutionOf(TypeVariable, TypeVariable)}
	 * <p>
	 *
	 * @return true iff a new bound was added (this signals a round of incorporation should follow)
	 */
	public boolean addBound(TypeBound bound) {
		if (bound.isTrivial()) {
			// adding a trivial bound (eg, `alpha <: alpha`) would cause `true == isBounded(alpha)`
			return false;
		}
		boolean wasAdded = internal_addBound(bound);
		if (DEBUG) {
			String msg = wasAdded ? "  (ADDED)" : "  (not added; already present)";
			log(">>> ADD bound: " + bound + msg);
		}
		return wasAdded;
	}

	/**
	 * The argument can later be retrieved only by its LHS type variable. Additionally, for an argument of the form
	 * `typeVar = U` with U proper, an instantiation is recorded.
	 * <p>
	 * This is the only way that an instantiation can be recorded, ie for an instantiation to be put into effect there's
	 * no way around calling a reduction method. For an example see {@link InferenceContext#instantiate}.
	 *
	 * @return true iff a new bound was added (this signals a round of incorporation should follow)
	 */
	private boolean internal_addBound(TypeBound bound) {
		assert ic.isInferenceVariable(bound.left);
		// TODO(mg) a bound `v <: T` with T final amounts to `v = T`
		Set<TypeBound> bounds = boundsPerInfVar.get(bound.left);
		if (bounds == null) {
			bounds = new LinkedHashSet<>();
			boundsPerInfVar.put(bound.left, bounds);
		}
		final boolean wasAdded = bounds.add(bound);
		if (wasAdded) {
			if (TypeUtils.isRawTypeRef(bound.right)) {
				haveRawTypeRef = true;
			}
		}
		if (bound.variance == INV) {
			if (ic.isProper(bound.right) && !TypeUtils.isRawTypeRef(bound.right)) {
				// TODO(mg) assert that not yet instantiated, or RHS denotes the same type as before
				instantiations.put(bound.left, bound.right);
			}
		}
		return wasAdded;
	}

	/**
	 * Removes the given type bound.
	 *
	 * @param bound
	 *            the bound to be removed; identified by object identity!
	 */
	private void removeBound(TypeBound bound) {
		final Set<TypeBound> bounds = boundsPerInfVar.get(bound.left);
		if (bounds != null) {
			bounds.removeIf(b -> b == bound);
		}
		incorporatedBounds.remove(bound);
	}

	/**
	 * For the given typeVar, collect the TypeRef-s appearing as RHS in any bounds. From those, only the isProper subset
	 * is returned if so requested by the second argument.
	 */
	public TypeRef[] collectBounds(TypeVariable typeVar, boolean onlyProper, boolean resolveRawTypes) {
		return collectBounds(typeVar, onlyProper, resolveRawTypes, b -> true);
	}

	/**
	 * For the given typeVar, collect the TypeRef-s appearing as RHS in bounds of the forms `typeVar = TypeRef` and
	 * `typeVar :> TypeRef`. From those, only the isProper subset is returned if so requested by the second argument.
	 */
	public TypeRef[] collectLowerBounds(TypeVariable typeVar, boolean onlyProper, boolean resolveRawTypes) {
		return collectBounds(typeVar, onlyProper, resolveRawTypes, b -> b.variance == CONTRA || b.variance == INV);
	}

	/**
	 * For the given typeVar, collect the TypeRef-s appearing as RHS in bounds of the forms `typeVar = TypeRef` and
	 * `typeVar <: TypeRef`. From those, only the isProper subset is returned if so requested by the second argument.
	 */
	public TypeRef[] collectUpperBounds(TypeVariable typeVar, boolean onlyProper, boolean resolveRawTypes) {
		return collectBounds(typeVar, onlyProper, resolveRawTypes,
				b -> (b.variance == CO || b.variance == INV) && !(b.variance == CO && b.right.isTopType()));
	}

	private TypeRef[] collectBounds(TypeVariable typeVar, boolean onlyProper, boolean resolveRawTypes,
			Predicate<? super TypeBound> predicate) {
		final Set<TypeBound> bounds = resolveRawTypes ? resolveRawTypes(getBounds(typeVar)) : getBounds(typeVar);
		Stream<TypeRef> result = bounds.stream()
				.filter(predicate)
				.map(b -> b.right);
		if (onlyProper) {
			result = result.filter(t -> ic.isProper(t));
		}
		return toArray(result);
	}

	/**
	 * Handle raw types in given type bounds.
	 * <p>
	 * Raw types can occur during type inference due to ClassifierTypeRef. The basic idea of this method is that a raw
	 * type is removed if at least one non-raw type for the same declared type exists: if we have A and A&lt;string> as
	 * upper bounds, we can remove A because it does not add any information.
	 * <p>
	 * FIXME revise handling of raw types (inefficient implementation, does not handle all cases (eg. nested raw types))
	 */
	private Set<TypeBound> resolveRawTypes(Set<TypeBound> typeBounds) {
		if (!haveRawTypeRef) // this is the 98% case, so we optimize for that
			return typeBounds;
		if (typeBounds.isEmpty())
			return typeBounds;
		final List<TypeBound> result = new ArrayList<>(typeBounds);
		final Set<Type> genTypesWithNonRawTypeRefs = new HashSet<>();
		final Set<TypeBound> boundsWithRawTypeRef = new HashSet<>();
		// collect
		for (TypeBound tb : result) {
			if (tb.right instanceof ParameterizedTypeRef) {
				final ParameterizedTypeRef ptr = (ParameterizedTypeRef) tb.right;
				final Type declType = ptr.getDeclaredType();
				if (declType != null && declType.isGeneric()) {
					final boolean isRaw = TypeUtils.isRawTypeRef(ptr);
					if (isRaw) {
						boundsWithRawTypeRef.add(tb);
					} else {
						genTypesWithNonRawTypeRefs.add(declType);
					}
				}
			}
		}
		// remove all raw type bounds for which at least 1 other non-raw typeRef exists
		for (TypeBound currTB : boundsWithRawTypeRef) {
			if (genTypesWithNonRawTypeRefs.contains(currTB.right.getDeclaredType())) {
				result.remove(currTB);
			}
		}
		// remaining raw types must be sanitized
		final int len = result.size();
		for (int i = 0; i < len; i++) {
			result.set(i, result.get(i).sanitizeRawTypeRef());
		}
		return new LinkedHashSet<>(result); // FIXME performance leak?
	}

	private static TypeRef[] toArray(Stream<TypeRef> s) {
		return s.toArray(TypeRef[]::new);
	}

	/**
	 * This method returns the subset of the proper upper bounds (for the type variable in question) that aren't
	 * supertypes of the {@code candidateValue}
	 */
	public List<TypeRef> wrongUpperBoundsFor(TypeVariable typeVar, TypeRef candidateValue) {
		Stream<TypeRef> ubs = Arrays.stream(collectUpperBounds(typeVar, true, false));
		return ubs.filter(ub -> !(ts.subtypeSucceeded(G, candidateValue, ub))).collect(Collectors.toList());
	}

	public boolean hasWrongUpperBoundsFor(TypeVariable typeVar, TypeRef candidateValue) {
		return !(wrongUpperBoundsFor(typeVar, candidateValue).isEmpty());
	}

	/**
	 * Does the instantiation of the argument abide by bounds of the forms `alpha <: U`, `alpha :> U` where U is a
	 * proper type?
	 */
	public boolean hasConsistentBounds(TypeVariable alpha) {
		TypeRef value = instantiations.get(alpha);
		return !(hasWrongLowerBoundsFor(alpha, value)) && !(hasWrongUpperBoundsFor(alpha, value));
	}

	/**
	 * This method returns the subset of the proper lower bounds (for the type variable in question) that aren't
	 * subtypes of the {@code candidateValue}
	 */
	public List<TypeRef> wrongLowerBoundsFor(TypeVariable typeVar, TypeRef candidateValue) {
		Stream<TypeRef> lbs = Arrays.stream(collectLowerBounds(typeVar, true, false));
		return lbs.filter(lb -> !(ts.subtypeSucceeded(G, lb, candidateValue))).collect(Collectors.toList());
	}

	public boolean hasWrongLowerBoundsFor(TypeVariable typeVar, TypeRef candidateValue) {
		return !(wrongLowerBoundsFor(typeVar, candidateValue).isEmpty());
	}

	/**
	 * Was an instantiation recorded for the argument?
	 */
	public boolean isInstantiated(TypeVariable infVar) {
		return (null != infVar) && instantiations.containsKey(infVar);
	}

	/**
	 * Is the first argument constrained by the second? (irrespective of whether that second argument is instantiated)
	 *
	 * <p>
	 * If so, either:
	 * <ul>
	 * <li>the latter should be instantiated before the former; or</li>
	 * <li>both should be instantiated together (problem is, this capability isn't supported in the algorithm of Sec 18
	 * of JLS8, instead one variable at a time is instantiated).</li>
	 * </ul>
	 *
	 * <p>
	 * This method relies on "mirroring bounds" having been added beforehand, for bounds involving two variables only.
	 * Given bound {@code alpha op beta}, method {@link #addBound(TypeBound)} also adds the (in principle redundant)
	 * "mirroring bound" {@code beta inverse-of-op alpha}, so that (as mandated by Sec 18 of JLS8)
	 *
	 * <pre>
	 * true == dependsOnResolutionOf(alpha, beta) == dependsOnResolutionOf(beta, alpha)
	 * </pre>
	 *
	 * via plain lookup (of either alpha or beta) in {@link #boundsPerInfVar}.
	 *
	 * <p>
	 * Another disadvantage of skipping adding a "mirroring bound" is that the RHS variable is regarded as "unbound" by
	 * {@link BoundSet#isBounded(TypeVariable)}.
	 */
	public boolean dependsOnResolutionOf(TypeVariable alpha, TypeVariable beta) {
		for (final TypeBound currBound : getBounds(alpha)) {
			if (TypeUtils.isOrContainsRefToTypeVar(currBound.right, new TypeVariable[] { beta })) {
				return true;
			}
		}
		return false;
	}

	public void dumpInstantiations() {
		for (Entry<TypeVariable, TypeRef> e : instantiations.entrySet()) {
			log(String.valueOf(e.getKey().getTypeAsString()) + " -> " + e.getValue().getTypeRefAsString());
		}
	}

	protected void log(final String message) {
		ic.log(message);
	}

	// ###############################################################################################################
	// INCORPORATION

	/**
	 * This method inspects bounds pairwise, recording that so as to avoid inspecting the same pair in follow-up rounds
	 * of incorporation. The "inspection" may result in a new constraint, computed by
	 * {@link #combined(TypeBound, TypeBound)}, which is then reduced.
	 *
	 * @return false as soon as a contradiction is detected, true otherwise.
	 */
	public boolean incorporate() {
		boolean updated;
		do {
			updated = false;
			final TypeBound[] bounds = getAllBoundsArr();
			final int len = bounds.length;
			if (len < 2) {
				return true;
			}
			for (int i = 0; i < len; ++i) {
				final TypeBound boundI = bounds[i];
				final boolean isIncorporatedI = incorporatedBounds.contains(boundI);
				for (int j = i + 1; j < len; ++j) {
					final TypeBound boundJ = bounds[j];
					final boolean isIncorporatedJ = incorporatedBounds.contains(boundJ);
					final boolean bothAlreadyIncorporated = (isIncorporatedI && isIncorporatedJ);
					if (!bothAlreadyIncorporated) {
						if (DEBUG) {
							log("--- incorporating:  " + boundI + "  |  " + boundJ);
						}
						TypeConstraint newConstraint = combined(boundI, boundJ);
						if (newConstraint != null) {
							// incorporation has triggered reduction (by producing a new constraint)
							// reduction may trigger incorporation (provided it adds bounds)
							updated = ored(updated, redu.reduce(newConstraint));
						}
						if (ic.isDoomed()) {
							return false;
						}
					}
				}
				if (!isIncorporatedI) {
					// TODO(mg) performance: use instead a sticky-bit in TypeBound
					incorporatedBounds.add(boundI);
				}
			}
		} while (updated);
		return true;
	}

	private static boolean ored(boolean a, boolean b) {
		return a || b;
	}

	/**
	 * In terms of JLS8, this method embodies the implication rules listed in Sec. 18.3.1 (the other implication rules
	 * in JLS8 take as input capture conversion constraints)
	 */
	private TypeConstraint combined(TypeBound boundI, TypeBound boundJ) {
		switch (boundI.variance) {
		case INV:
			switch (boundJ.variance) {
			case INV:
				// both equalities
				return combineSameSame(boundI, boundJ);
			case CO:
			case CONTRA:
				// equality and inequality
				return combineSameSubSuper(boundI, boundJ);
			}
			break;
		case CO:
			switch (boundJ.variance) {
			case INV:
				// equality and inequality
				return combineSameSubSuper(boundJ, boundI);
			case CONTRA:
				// inequalities of different direction
				return combineSuperAndSub(boundJ, boundI);
			case CO:
				// inequalities of same direction
				return combineEqualSupers(boundI, boundJ);
			}
			break;
		case CONTRA:
			switch (boundJ.variance) {
			case INV:
				// equality and inequality
				return combineSameSubSuper(boundJ, boundI);
			case CO:
				// inequalities of different direction
				return combineSuperAndSub(boundI, boundJ);
			case CONTRA:
				// inequalities of same direction
				return combineEqualSupers(boundI, boundJ);
			}
		}
		// actually unreachable, each case above returns something
		throw new IllegalStateException("unreachable");
	}

	/**
	 * Both bounds are equalities.
	 * <ul>
	 * <li>If both variables match, match their RHS</li>
	 * <li>otherwise, attempt to substitute the (proper) RHS of a variable for the occurrences of that variable in the
	 * other RHS</li>
	 * </ul>
	 */
	private TypeConstraint combineSameSame(TypeBound boundS, TypeBound boundT) {
		assert boundS.variance == INV;
		assert boundT.variance == INV;
		if (boundS.left == boundT.left) {
			// `alpha = S` and `alpha = T` implies `S = T`
			return new TypeConstraint(boundS.right, boundT.right, INV);
		}
		// both variables are different
		// try to substitute a proper RHS in the RHS of the other bound, to make it a proper type itself
		TypeConstraint newConstraint = combineSameSameWithProperType(boundS, boundT);
		if (newConstraint != null) {
			return newConstraint;
		}
		newConstraint = combineSameSameWithProperType(boundT, boundS);
		if (newConstraint != null) {
			return newConstraint;
		}
		return null;
	}

	/*
	 * `alpha = U` and `beta = T` where (1) alpha and beta differ; (2) U is proper type; implies `beta = T[alpha:=U]`
	 * The substitution is actually performed only if alpha occurs in T. Otherwise returns null.
	 */
	private TypeConstraint combineSameSameWithProperType(TypeBound boundLeft, TypeBound boundRight) {
		assert boundLeft.variance == INV;
		assert boundRight.variance == INV;
		assert boundLeft.left != boundRight.left;
		final TypeVariable alpha = boundLeft.left;
		final TypeRef replacement = boundLeft.right;
		if (canSubstituteAlphaWithReplacementInAnother(alpha, replacement, boundRight.right)) {
			final TypeRef left = typeRef(boundRight.left);
			final TypeRef right = substituteInferenceVariable(boundRight.right, alpha, replacement);
			removeBound(boundRight); // avoid exponential growth of bound FIXME why is this necessary?
			return new TypeConstraint(left, right, INV);
		}
		return null;
	}

	private static TypeRef typeRef(TypeVariable infVar) {
		return TypeUtils.createTypeRef(infVar, new TypeArgument[0]);
	}

	/*
	 * Substitution `another[alpha:=replacement]` makes sense provided `replacement` is proper and `alpha` occurs in
	 * `another`. This method checks those conditions and thus can be reused whenever the question arises as to the
	 * feasibility of that substitution.
	 */
	private boolean canSubstituteAlphaWithReplacementInAnother(TypeVariable alpha, TypeRef replacement,
			TypeRef another) {
		return ic.isProper(replacement) && TypeVarUtils.occursIn(alpha, another);
	}

	/**
	 * Of the type variables in the argument, return those that are inference variables.
	 */
	public Stream<TypeVariable> collectedInferenceVars(TypeArgument t) {
		return TypeVarUtils.collectedTypeVars(t).filter(v -> ic.isInferenceVariable(v));
	}

	/**
	 * @return typeRef[infVar:=typeArg]
	 */
	private TypeRef substituteInferenceVariable(TypeRef typeRef, TypeVariable infVar, TypeArgument typeArg) {
		final RuleEnvironment Gtemp = RuleEnvironmentExtensions.wrap(this.G);
		RuleEnvironmentExtensions.addTypeMapping(Gtemp, infVar, typeArg);
		final TypeRef result = (TypeRef) this.ts.substTypeVariables(Gtemp, typeRef).getValue();
		// note: infVar may still occur in result, if infVar->typeArg is not a valid type mapping!
		// assert!(TypeVarUtils.occursIn(infVar, result));
		return result;
	}

	/**
	 * The first bound is an equality, while the second isn't.
	 *
	 * <ul>
	 * <li>(1) `alpha = S` and `alpha ineq T` implies `S ineq T`</li>
	 * <li>(2) `alpha = S` and `beta ineq alpha` implies `beta ineq S`</li>
	 * <li>(3) `alpha = gamma` and `gamma ineq T` implies `alpha ineq T`</li>
	 * <li>(4) `alpha = gamma` and `beta ineq gamma` implies `beta ineq alpha`</li>
	 * <li>(5) `alpha = U` (where U is proper) and `beta ineq T` implies `beta ineq T[alpha:=U]`</li>
	 * </ul>
	 */
	private TypeConstraint combineSameSubSuper(TypeBound boundS, TypeBound boundT) {
		assert boundS.variance == INV;
		assert boundT.variance != INV;
		final TypeVariable alpha = boundS.left;
		final TypeRef s = boundS.right;
		if (alpha == boundT.left) {
			// (1) `alpha = S` and `alpha ineq T` implies `S ineq T`
			// the eq-RHS of alpha gets substituted for alpha in the other bound
			return new TypeConstraint(s, boundT.right, boundT.variance);
			// in case T contains inference vars (possibly alpha itself)
			// in a next round of incorporation `asProper()` will substitute them.
		}
		// both bounds have different inference variables
		if (alpha == boundT.right.getDeclaredType()) {
			// (2) `alpha = S` and `beta ineq alpha` implies `beta ineq S`
			return new TypeConstraint(boundT.right, s, boundT.variance);
		}
		if (ic.isInferenceVariable(s)) {
			// known so far: first bound of the form `alpha = gamma`
			final TypeVariable gamma = (TypeVariable) s.getDeclaredType();
			final TypeRef alphaTR = typeRef(alpha);
			if (gamma == boundT.left) {
				// (3) `alpha = gamma` and `gamma ineq T` implies `alpha ineq T`
				return new TypeConstraint(alphaTR, boundT.right, boundT.variance);
			}
			if (gamma == boundT.right.getDeclaredType()) {
				// (4) `alpha = gamma` and `beta ineq gamma` implies `beta ineq alpha`
				final TypeRef betaTR = typeRef(boundT.left);
				return new TypeConstraint(betaTR, alphaTR, boundT.variance);
			}
		}
		// known so far: first bound of the form `alpha = S` with S non-inference-var
		final TypeRef u = s;
		if (canSubstituteAlphaWithReplacementInAnother(alpha, u, boundT.right)) {
			// (5) `alpha = U` (where U is proper) and `beta ineq T` implies `beta ineq T[alpha:=U]`
			final TypeRef betaTR = typeRef(boundT.left);
			final TypeRef right = substituteInferenceVariable(boundT.right, alpha, u);
			removeBound(boundT); // avoid exponential growth of bound FIXME why is this necessary?
			return new TypeConstraint(betaTR, right, boundT.variance);
		}
		return null;
	}

	/**
	 * Inequalities of different direction.
	 *
	 * <ul>
	 * <li>(1) transitive rule, using LHS as bridge</li>
	 * <li>(2) transitive rule, using RHS as bridge</li>
	 * </ul>
	 */
	private TypeConstraint combineSuperAndSub(TypeBound boundS, TypeBound boundT) {
		assert boundS.variance != INV;
		assert boundT.variance != INV;
		assert boundS.variance != boundT.variance;
		TypeVariable alpha = boundS.left;
		if (alpha == boundT.left) {
			// Subcase (1a) alpha <: S and alpha :> T implies S :> T
			// Subcase (1b) alpha :> S and alpha <: T implies S <: T
			return new TypeConstraint(boundS.right, boundT.right, boundT.variance);
		}
		// known so far: `alpha ineq S` and `beta opposite-ineq T` where alpha and beta are different
		if (ic.isInferenceVariable(boundS.right)) {
			final TypeVariable gamma = (TypeVariable) boundS.right.getDeclaredType();
			if (ic.isInferenceVariable(boundT.right)) {
				TypeVariable rightOfRight = (TypeVariable) boundT.right.getDeclaredType();
				if (gamma == rightOfRight) {
					// Subcase (2a) alpha <: gamma and beta :> gamma implies alpha <: beta
					// Subcase (2b) alpha :> gamma and beta <: gamma implies alpha :> beta
					return new TypeConstraint(typeRef(alpha), typeRef(boundT.left), boundS.variance);
				}
			}
		}
		return null;
	}

	/**
	 * Inequalities of same direction.
	 *
	 * <ul>
	 * <li>(1) transitive rule, both vars on second bound</li>
	 * <li>(2) transitive rule, both vars on first bound</li>
	 * </ul>
	 */
	private static TypeConstraint combineEqualSupers(TypeBound boundS, TypeBound boundT) {
		assert boundS.variance != INV;
		assert boundT.variance != INV;
		assert boundS.variance == boundT.variance;
		final TypeVariable alpha = boundS.left;
		final TypeVariable beta = boundT.left;
		if (alpha == boundT.right.getDeclaredType()) {
			// Subcase (1a) alpha <: S and beta <: alpha implies beta <: S
			// Subcase (1b) alpha :> S and beta :> alpha implies beta :> S
			return new TypeConstraint(typeRef(beta), boundS.right, boundS.variance);
		}
		// known so far: `alpha ineq_1 S` and `beta ineq_1 T` where alpha and T are different
		if (boundS.right.getDeclaredType() == beta) {
			// Subcase (2a) alpha <: beta and beta <: T implies alpha <: T
			// Subcase (2b) alpha :> beta and beta :> T implies alpha :> T
			return new TypeConstraint(typeRef(alpha), boundT.right, boundS.variance);
		}
		return null;
	}

	/**
	 * This method is to be called after this {@link BoundSet} has been solved.
	 *
	 * This method returns a read-only view of the underlying {@link #instantiations}. Given that the underlying map is
	 * mutable, the caller of this method is best advised to iterate over that result (thus consuming it) before further
	 * work is performed on the {@link BoundSet} or the {@link InferenceContext}.
	 */
	public Map<TypeVariable, TypeRef> getInstantiations() {
		return Collections.unmodifiableMap(instantiations);
	}

}
