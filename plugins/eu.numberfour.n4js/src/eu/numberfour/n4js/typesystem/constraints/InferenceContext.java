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

import static eu.numberfour.n4js.ts.types.util.Variance.INV;
import static eu.numberfour.n4js.typesystem.TypeVarUtils.typeRef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.xtext.util.CancelIndicator;

import com.google.common.base.Optional;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.Range;

import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions;
import eu.numberfour.n4js.typesystem.TypeSystemHelper;
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.TypeVariable;
import eu.numberfour.n4js.ts.types.TypesFactory;
import eu.numberfour.n4js.ts.types.util.Variance;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.utils.CharDiscreteDomain;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * An inference context orchestrates the process from the point where {@link TypeConstraint subtype} and compatibility
 * constraints are added, till a {@link #solve() solution} is computed for inference variables. This class relies on
 * collaborators:
 * <ul>
 * <li>{@link Reducer} to lower high-level constraints into simpler bounds</li>
 * <li>{@link BoundSet} for {@link TypeBound} bookkeeping including incorporation</li>
 * </ul>
 * <p>
 * The last step of the process, instantiating inference variables, is performed by this class without delegation.
 * <p>
 * Details in N4JS Design doc, Appendix "Type Inference via Constraint-Solving"
 */
public class InferenceContext {

	static final boolean DEBUG = false;

	private final N4JSTypeSystem ts;
	private final TypeSystemHelper tsh;
	private final CancelIndicator cancelIndicator;
	private final RuleEnvironment G;

	/**
	 * A non-null (by construction) array of non-null type variables. New type variables may be added over time via
	 * {@link #addInferenceVariables(TypeVariable...)}.
	 */
	private TypeVariable[] inferenceVariables;

	/**
	 * An append-only list of constraints that may also be cleared.
	 */
	private final ConstraintBuffer constraints;

	/**
	 * Lowers higher-level constraints into bounds.
	 */
	private final Reducer redu;

	/**
	 * Collaborator that takes care of adding and incorporating bounds.
	 */
	final BoundSet currentBounds;

	private boolean onSolvedActionsExecuted = false;
	private final List<Consumer<Optional<Map<TypeVariable, TypeRef>>>> onSolvedActions = new ArrayList<>();

	/**
	 * Sets up a new, empty inference context for the given inference variables.
	 *
	 * @param G
	 *            a rule environment used for subtype checking, etc. during constraint resolution. This rule environment
	 *            will not be changed by the inference context AND it is expected to <b>not</b> be changed by anyone
	 *            else during the life-time of the inference context.
	 * @param inferenceVariables
	 *            the meta variables to be inferred.
	 */
	public InferenceContext(N4JSTypeSystem ts, TypeSystemHelper tsh, CancelIndicator cancelIndicator,
			RuleEnvironment G, TypeVariable... inferenceVariables) {
		Objects.requireNonNull(ts);
		Objects.requireNonNull(tsh);
		Objects.requireNonNull(cancelIndicator);
		Objects.requireNonNull(G);
		this.ts = ts;
		this.tsh = tsh;
		this.cancelIndicator = cancelIndicator;
		this.G = G;
		if (null == inferenceVariables) {
			this.inferenceVariables = new TypeVariable[0];
		} else {
			this.inferenceVariables = Arrays.copyOf(inferenceVariables, inferenceVariables.length);
		}
		this.constraints = new ConstraintBuffer();
		this.redu = new Reducer(this, ts, tsh, G);
		this.currentBounds = new BoundSet(this, G, ts, this.redu);
	}

	/**
	 * Register the given action to be executed after this constraint system has been solved. In the success case, the
	 * solution is passed to the given action; in the failure case the argument will be {@link Optional#absent()}. The
	 * InferenceContext will guarantee that each such action is executed only once.
	 */
	public void onSolved(Consumer<Optional<Map<TypeVariable, TypeRef>>> action) {
		onSolvedActions.add(action);
	}

	/**
	 * Once a contradiction has been detected, constraint solving can quit early: no solution exists anyway.
	 * <p>
	 * Note that this method is not guaranteed to always return true if the constraint system is unsolvable; it just
	 * reports about easy to detect special cases (i.e. after the type bound FALSE has been added to
	 * {@link #currentBounds}) to allow for performance tweaks.
	 */
	public boolean isDoomed() {
		return currentBounds.hasBoundFALSE() || cancelIndicator.isCanceled();
	}

	/**
	 * Returns the inference variables of the receiving context.
	 */
	public TypeVariable[] getInferenceVariables() {
		return inferenceVariables;
	}

	@SuppressWarnings("hiding")
	private void addInferenceVariables(TypeVariable... inferenceVariables) {
		if (inferenceVariables == null || inferenceVariables.length == 0)
			return;
		// TODO use a list instead of array for this.inferenceVariables (sticking to array to keep changes minimal)
		this.inferenceVariables = Stream.concat(Stream.of(this.inferenceVariables), Stream.of(inferenceVariables))
				.toArray(len -> new TypeVariable[len]);
	}

	/**
	 * Introduce a newly generated inference variable to the constraint system of the receiving inference context.
	 */
	public TypeVariable newInferenceVariable() {
		return newInferenceVariable(false);
	}

	private TypeVariable newInferenceVariable(boolean internal) {
		final TypeVariable iv = TypesFactory.eINSTANCE.createTypeVariable();
		final String name = internal ? "_" + unusedNameGeneratorInternal.next() : unusedNameGenerator.next();
		iv.setName(name);
		addInferenceVariables(iv);
		return iv;
	}

	/**
	 * Same as {@link #newInferenceVariable()}, but creates <code>n</code> inference variables in one step.
	 */
	public TypeVariable[] newInferenceVariables(int n) {
		return newInferenceVariables(n, false);
	}

	private TypeVariable[] newInferenceVariables(int n, boolean internal) {
		final TypeVariable[] result = new TypeVariable[n];
		for (int i = 0; i < n; i++)
			result[i] = newInferenceVariable(internal);
		return result;
	}

	/**
	 * Introduces newly generated inference variables for each type parameter of the given function type (if generic)
	 * and returns a non-generic function type in which all type variables owned by the given function type are replaced
	 * by those inference variables; simply returns the given function type unchanged if it already is non-generic.
	 * Returns given function type unchanged in case of error (so returned function type may actually be generic, but
	 * only in error cases).
	 * <p>
	 * Example: given a function type such as
	 *
	 * <pre>
	 * {function&lt;T,S>(G&lt;T,string>):S}
	 * </pre>
	 *
	 * this method will add two new type variables α', β' and return
	 *
	 * <pre>
	 * {function(G&lt;α',string>):β'}
	 * </pre>
	 *
	 * Note that the returned function type is non-generic.
	 */
	public FunctionTypeExprOrRef newInferenceVariablesFor(FunctionTypeExprOrRef funTypeRef) {
		if (!funTypeRef.isGeneric())
			return funTypeRef;
		final List<TypeVariable> typeParams = funTypeRef.getTypeVars(); // NOTE: typeParam may contain null entries!
		final TypeVariable[] newInfVars = newInferenceVariables(typeParams.size(), true);
		final List<? extends TypeRef> newInfVarsRefs = Stream.of(newInfVars).map(TypeUtils::createTypeRef)
				.collect(Collectors.toList());
		final RuleEnvironment G_params2infVars = RuleEnvironmentExtensions.newRuleEnvironment(G); // new, empty RE
		RuleEnvironmentExtensions.addTypeMappings(G_params2infVars, typeParams, newInfVarsRefs);
		final TypeArgument left_withInfVars = ts.substTypeVariables(G_params2infVars, funTypeRef).getValue();
		if (left_withInfVars instanceof FunctionTypeExprOrRef)
			return (FunctionTypeExprOrRef) left_withInfVars;
		// in case of substitution error: return original funTypeRef
		return funTypeRef;
	}

	private final UnusedNameGenerator unusedNameGenerator = new UnusedNameGenerator();
	private final UnusedNameGenerator unusedNameGeneratorInternal = new UnusedNameGenerator();

	private static final class UnusedNameGenerator {
		private Iterator<Character> unusedNames = null;
		private int unusedNamesOverflows = 0;

		public String next() {
			if (unusedNames == null) {
				unusedNames = ContiguousSet.create(Range.closed('\u03B1', '\u03B9'), CharDiscreteDomain.chars())
						.iterator();
			}

			final String next;
			if (unusedNamesOverflows == 0) {
				next = unusedNames.next().toString();
			} else {
				final StringBuffer sb = new StringBuffer();
				sb.append(unusedNames.next().toString());
				for (int i = 0; i < unusedNamesOverflows; i++)
					sb.append('\'');
				next = sb.toString();
			}

			if (!unusedNames.hasNext()) {
				unusedNames = null;
				unusedNamesOverflows++;
			}
			return next;
		}
	}

	/**
	 * Convenience method. Same as {@link #isInferenceVariable(TypeVariable)}, but taking a type reference.
	 */
	public boolean isInferenceVariable(TypeRef typeRef) {
		return typeRef != null && typeRef.getDeclaredType() instanceof TypeVariable
				&& isInferenceVariable((TypeVariable) typeRef.getDeclaredType());
	}

	/**
	 * Is the argument an inference variable?
	 */
	public boolean isInferenceVariable(TypeVariable typeVar) {
		return (typeVar != null) && org.eclipse.xtext.util.Arrays.contains(inferenceVariables, typeVar);
	}

	/**
	 * Does the argument refer to a <em>proper</em> type? Mentioning an inference variable makes it "non-proper" (even
	 * if an instantiation exists for it).
	 */
	public boolean isProper(TypeArgument typeRef) {
		if (null == typeRef) {
			return false;
		}
		Stream<TypeVariable> mentionedInfVars = currentBounds.collectedInferenceVars(typeRef);
		boolean hasInfVars = mentionedInfVars.iterator().hasNext();
		return !hasInfVars;
	}

	@SuppressWarnings("javadoc")
	protected void log(final String message) {
		System.out.println("[" + Integer.toHexString(System.identityHashCode(this)) + "] " + message);
	}

	@Override
	public String toString() {
		return constraints.toString();
	}

	/**
	 * Add a type constraint to this inference context. When done adding constraints, call {@link #solve()}.
	 */
	public void addConstraint(TypeArgument left, TypeArgument right, Variance variance) {
		addConstraint(new TypeConstraint(left, right, variance));
	}

	/**
	 * Add a type constraint to this inference context. When done adding constraints, call {@link #solve()}.
	 */
	public void addConstraint(TypeConstraint constraint) {
		constraints.addConstraint(constraint);
	}

	/**
	 * This method asks the {@link BoundSet} to compute instantiations for those inference variables with bounds (ie, an
	 * unbounded inference variable isn't included in the result).
	 * <p>
	 * This method returns
	 * <ul>
	 * <li>null, if no solution was found</li>
	 * <li>otherwise, a read-only view of the underlying instantiations found by the {@link BoundSet}. Given that the
	 * underlying map is mutable, the view should be read before further solving is performed by the {@link BoundSet} or
	 * the {@link InferenceContext}.</li>
	 * </ul>
	 */
	public Map<TypeVariable, TypeRef> solve() {
		if (DEBUG) {
			log("====== ====== ====== ====== ====== ====== ====== ====== ====== ====== ====== ======");
			log("solving the following constraint set:");
			constraints.log(this);
			log("inference variables: "
					+ Stream.of(inferenceVariables).map(iv -> str(iv)).collect(Collectors.joining(", ")));
		}

		// ---------------------------------------------------------------------------
		// REDUCTION
		// new constraints added during reduction or incorporation will be reduced immediately (see #addConstraint()
		// above) so no need for recursion, here
		if (DEBUG) {
			log("****** Reduction");
		}
		for (final TypeConstraint constraint : constraints.asReadOnlyList()) {
			redu.reduce(constraint);
			if (isDoomed()) {
				break;
			}
		}
		// clearing the list of constraints is ok given their information has been transferred to the bound set,
		// to which bounds are added but never removed.
		constraints.clear();

		// ---------------------------------------------------------------------------
		// INCORPORATION
		if (DEBUG) {
			log("****** Incorporation");
		}
		if (!isDoomed()) {
			currentBounds.incorporate();
		}

		// ---------------------------------------------------------------------------
		// RESOLUTION
		if (DEBUG) {
			log("****** Resolution");
		}
		final BoundSet solution = !isDoomed() ? resolve(inferenceVariables) : null;
		if (DEBUG) {
			if (solution == null) {
				log("NO SOLUTION FOUND");
			} else {
				log("SOLUTION:");
				solution.dumpInstantiations();
			}
		}

		// perform onSolved actions
		final Map<TypeVariable, TypeRef> solutionAsMap = solution != null ? solution.getInstantiations() : null;
		if (!onSolvedActionsExecuted) {
			onSolvedActionsExecuted = true;
			for (Consumer<Optional<Map<TypeVariable, TypeRef>>> action : onSolvedActions) {
				action.accept(Optional.fromNullable(solutionAsMap));
			}
		}

		// return result
		return solutionAsMap;
	}

	// ###############################################################################################################
	// RESOLUTION

	/**
	 * @return null if no solution was found for the <i>bounded</i> subset of the argument, {@link #currentBounds}
	 *         otherwise (containing instantiations for that bounded subset).
	 */
	private BoundSet resolve(TypeVariable[] toResolveRaw) {

		final TypeVariable[] toResolve = toResolveRaw;// boundedSubsetOf(toResolveRaw);

		Set<TypeVariable> variableSet;
		while ((variableSet = getSmallestVariableSet(currentBounds, toResolve)) != null) {
			final int oldNumUninstantiated = numUninstantiatedVariables(inferenceVariables);
			assert(oldNumUninstantiated <= inferenceVariables.length);
			for (TypeVariable variable : variableSet) {
				if (DEBUG) {
					log("======");
					log("trying to resolve inference variable: " + str(variable));
					log("based on this bound set:");
					currentBounds.log();
				}
				final TypeRef instantiation = chooseInstantiation(variable);
				if (instantiation == null) {
					if (DEBUG) {
						log("NO INSTANTIATION found for inference variable: " + str(variable));
					}
					return null;
				} else {
					if (DEBUG) {
						log("choosing instantiation " + str(variable) + " := " + str(instantiation));
					}
					instantiate(variable, instantiation);
				}
			}
			// FIXME Propagation of instantiations. See discussion at the top of this file.
			currentBounds.incorporate();
			if (isDoomed()) {
				return null;
			}
			for (TypeVariable variable : variableSet) {
				assert(currentBounds.hasConsistentBounds(variable));
			}
		}

		for (TypeVariable v : toResolve) {
			assert currentBounds.isInstantiated(v) : "resolve() found no instantiation for " + str(v);
		}
		return currentBounds;
	}

	/**
	 * Add bound {@code variable = proper}.
	 */
	private void instantiate(TypeVariable variable, TypeRef proper) {
		assert isInferenceVariable(variable) : "Attempt to instantiate non-inference var " + str(variable);
		assert!(currentBounds.isInstantiated(variable)) : "Attempt to re-instantiate var " + str(variable);
		assert isProper(proper);
		// add bound `variable = proper`
		redu.reduce(typeRef(variable), proper, INV);
	}

	/**
	 * @return those type variables of the argument for which no instantiation is tracked by {@link #currentBounds}
	 */
	private Stream<TypeVariable> uninstantiatedSubsetOf(TypeVariable[] vs) {
		return Arrays.stream(vs).filter(v -> !(currentBounds.isInstantiated(v)));
	}

	private int numUninstantiatedVariables(TypeVariable[] infVars) {
		return (int) uninstantiatedSubsetOf(infVars).count();
	}

	/**
	 * If all bounds (for the set of variables to be resolved) were equalities then resolution would be simpler
	 * (topological sort over the dependsOn relation).
	 * <p>
	 * In order to deal with inequalities, a two-attempt process is followed (Sec 18.4 of JLS8, second page). This
	 * method implements the first of those attempts, which can itself be subdivided into two steps:
	 * <ul>
	 * <li>in case the variable to be solved for (typeVar) has some proper lower bounds, adopt their LUB as candidate
	 * instantiation (lower bounds as collected from the bound set, ie from bounds of the form `alpha :> L` or `alpha =
	 * L`).</li>
	 * <li>otherwise, if typeVar has proper upper bounds, adopt their GLB as candidate instantiation.</li>
	 * <li>otherwise fail the attempt altogether (one or more variables missing candidate instantiation make this first
	 * attempt fail).</li>
	 * </ul>
	 *
	 * @return a proper (LUB or GLB) for the argument.
	 */
	// package visibility: called by BoundSet during compaction phase
	/* package */ TypeRef chooseInstantiation(TypeVariable typeVar) {
		if (!isInferenceVariable(typeVar)) {
			throw new IllegalArgumentException("not an inference variable: " + typeVar);
		}
		final TypeRef[] lowerBounds = currentBounds.collectLowerBounds(typeVar, true, true);
		if (lowerBounds.length > 0) {
			// TODO IDE-1653 reconsider:
			// take upper bound of all lower bounds
			// (if we have a type bound `α :> ? extends A` this will give us A as a lower bound for α)
			for (int i = 0; i < lowerBounds.length; i++) {
				lowerBounds[i] = ts.upperBound(G, lowerBounds[i]).getValue();
			}
			final TypeRef result = tsh.createUnionType(G, lowerBounds);
			assert isProper(result) : "Not a proper LUB: " + str(result);
			return result;
		} else {
			final TypeRef[] upperBounds = currentBounds.collectUpperBounds(typeVar, true, true);
			if (upperBounds.length > 0) {
				// TODO IDE-1653 should we here take lower bound of all upperBounds? (for consistency with above)
				final TypeRef result2 = tsh.createIntersectionType(G, upperBounds);
				assert isProper(result2) : "Not a proper GLB: " + str(result2);
				return result2;
			} else {
				// neither lower nor upper bounds found -> typeVar is unconstrained
				return RuleEnvironmentExtensions.anyTypeRef(G);
			}
		}
	}

	/**
	 * This method iterates once over the uninstantiated variables among the argument (neither this method nor its
	 * callees instantiate any inference variables in the process). For each uninstantiated variable the uninstantiated
	 * variables it depends on are recursively added. After iteration is over, the smallest such set is returned.
	 * <p>
	 * As specified in Sec 18.4 of JLS8, this method returns a non-empty set S of uninstantiated variables such that
	 * <ul>
	 * <li>if an element alpha depends on the resolution of another variable beta, then either beta is instantiated or
	 * beta is part of S</li>
	 * <li>there exists no non-empty proper subset of S with this property</li>
	 * </ul>
	 * <p>
	 * The resulting set, if non-null, is guaranteed to be non-empty.
	 */
	private Set<TypeVariable> getSmallestVariableSet(BoundSet bounds, TypeVariable[] subSet) {
		Set<TypeVariable> deferred = null;
		Set<TypeVariable> result = null;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < subSet.length; ++i) {
			final TypeVariable currentVariable = subSet[i];
			if (!bounds.isInstantiated(currentVariable)) {

				// Defer an unbounded currentVariable IFF all other iv that depend on currentVariable have
				// proper bounds AND only proper bounds.
				//
				// For example:
				// α :> β
				// α :> A
				// Because α depends on β, we would first instantiate β (to any) and then choose α=intersection{A,any}.
				// With the following code, we defer processing of β until α has been instantiated to A.
				if (bounds.isUnbounded(currentVariable)) {
					final boolean gotAtLeastOneDependantIV = Stream.of(subSet)
							.anyMatch(iv -> currentBounds.dependsOnResolutionOf(iv, currentVariable));
					if (gotAtLeastOneDependantIV) {
						final boolean defer = Stream.of(subSet)
								.filter(iv -> currentBounds.dependsOnResolutionOf(iv, currentVariable))
								.allMatch(iv -> {
									final List<TypeBound> bs = currentBounds.getBounds(iv).stream()
											.filter(b -> !(b.left == iv
													&& b.right.getDeclaredType() == currentVariable))
											.collect(Collectors.toList());
									return !bs.isEmpty()
											&& bs.stream().allMatch(
													b -> InferenceContext.this.isProper(b.right));
								});
						if (defer) {
							if (deferred == null) {
								deferred = new HashSet<>();
							}
							deferred.add(currentVariable);
							continue;
						}
					}
				}

				final Set<TypeVariable> set = new LinkedHashSet<>();
				if (addDependencies(bounds, set, currentVariable, min)) {
					final int cur = set.size();
					if (cur == 1) {
						return set; // 'set' contains only currentVariable -> no need to remove deferred variables
					}
					if (cur < min) {
						result = set;
						min = cur;
					}
				}
			}
		}
		if ((null == result) || result.isEmpty()) {
			return null;
		}
		// deferred variables may have been added via #addDependencies() above, so remove them
		if (deferred != null) {
			// note: because deferred variables can only end up in 'result' via #addDependencies(), we can safely
			// remove all deferred variables and be sure that 'result' won't be empty afterwards
			result.removeAll(deferred);
		}
		return result;
	}

	/**
	 * Adds transitive closure of all uninstantiated inference variables from <code>boundSet</code> on which the given
	 * inference variable <code>currentVariable</code> depends directly or indirectly.
	 * <p>
	 * This method returns not one but two values:
	 * <ul>
	 * <li>set-of-variables containing uninstantiated variables and those it depends on, as described in
	 * {@link #getSmallestVariableSet(BoundSet, TypeVariable[])}</li>
	 * <li>return value: true iff the returned set-of-variables improves on the best-yet answer (as measured by number
	 * of elements, less is better)</li>
	 * </ul>
	 */
	private boolean addDependencies(BoundSet boundSet, Set<TypeVariable> variableSet, TypeVariable currentVariable,
			int min) {
		if (variableSet.size() >= min) {
			// the candidate answer-set is already not better than best-yet answer-set (ie, larger or same size)
			return false;
		}
		if (boundSet.isInstantiated(currentVariable)) {
			// FIXME reconsider this case! better throw exception or at least return false?
			// an answer-set contains only uninstantiated variables, the candidate variable isn't
			return true;
		}
		// add currentVariable
		if (!variableSet.add(currentVariable)) {
			// candidate variable was already visited for this answer-set. It has been added beforehand, in fact.
			return true;
		}
		// for all (uninstantiated) variables on which the current one depends, recurse
		for (TypeVariable nextVariable : inferenceVariables) {
			if (nextVariable != currentVariable) {
				if (!(boundSet.isInstantiated(nextVariable))) {
					if (boundSet.dependsOnResolutionOf(currentVariable, nextVariable)
							&& !addDependencies(boundSet, variableSet, nextVariable, min)) {
						// one uninstantiated variable too many (that currentVariable depends on)
						// causes the current variableSet to be discarded
						return false;
					}
				}
			}
		}
		return true;
	}

	private static String str(TypeVariable v) {
		return v.getTypeAsString();
	}

	private static String str(TypeArgument targ) {
		return targ.getTypeRefAsString();
	}

}
