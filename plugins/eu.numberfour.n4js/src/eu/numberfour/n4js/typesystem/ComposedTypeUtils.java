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
package eu.numberfour.n4js.typesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import eu.numberfour.n4js.ts.typeRefs.IntersectionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory;
import eu.numberfour.n4js.ts.typeRefs.UnionTypeExpression;
import eu.numberfour.n4js.ts.types.TClass;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.typeinference.N4JSTypeInferencer;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * Union and intersection types (in the context of Java 8 type inference) are covered among others in:
 *
 * Designing Type Inference for Typed Object-Oriented Languages. Daniel Smith, Rice University.
 * http://www.cs.rice.edu/~javaplt/papers/dlsmith-phd-thesis.pdf
 */
public class ComposedTypeUtils {

	/**
	 * Provided {@code original} is a union or intersection, return its canonicalization. Otherwise return it unchanged.
	 *
	 * TODO: Canonicalization could be done as last step in the factory of union types,
	 * {@link N4JSTypeInferencer#createSimplifiedUnion}
	 */
	public static TypeRef canonicalize(TypeRef original, boolean strict, N4JSTypeSystem ts, RuleEnvironment G) {
		if (original instanceof UnionTypeExpression) {
			return canonicalize((UnionTypeExpression) original, ts, G);
		}
		if (original instanceof IntersectionTypeExpression) {
			return canonicalize((IntersectionTypeExpression) original, strict, ts, G);
		}
		return original;
	}

	/**
	 * Applied simplifications:
	 * <ul>
	 * <li>the empty union is equivalent to bottom</li>
	 * <li>a unary union is equivalent to its single element</li>
	 * <li>Provided S <: T then a value conforms to (S union T) iff it conforms to T.</li>
	 * </ul>
	 * This method applies the above simplification rules, the result contains only disjoint direct elements.
	 * <p>
	 * Note: this method doesn't apply itself recursively to any union types that might be nested inside the argument.
	 *
	 * @see SimplifyComputer
	 */
	private static TypeRef canonicalize(UnionTypeExpression original, N4JSTypeSystem ts, RuleEnvironment G) {
		if (null == original) {
			return null;
		}
		assert original.getTypeArgs().isEmpty();
		final int originalSize = original.getTypeRefs().size();
		if (originalSize == 0) {
			return bottom(G);
		}
		if (originalSize == 1) {
			return original.getTypeRefs().get(0);
		}
		boolean wasChanged = false;
		List<TypeRef> disjoint = new ArrayList<>();
		for (TypeRef incoming : original.getTypeRefs()) {
			List<TypeRef> toRemove = subtypesOf(disjoint, incoming, ts, G);
			if (!(toRemove.isEmpty())) {
				// the incoming element covers those it is about to replace
				wasChanged = true;
				disjoint.removeAll(toRemove);
				disjoint.add(incoming);
			} else {
				// the incoming element doesn't cover any elements,
				// now the question is: does any element cover it?
				final boolean alreadyAccountedFor = disjoint.stream().anyMatch(
						elem -> isSubtypeOf(incoming, elem, ts, G));
				if (alreadyAccountedFor) {
					// incoming is part of original, but not of the result, thus wasChanged.
					wasChanged = true;
				} else {
					// incoming is part of original, thus "wasChanged" is left as is.
					disjoint.add(incoming);
				}
			}
		}
		assert !disjoint.isEmpty();
		if (disjoint.size() == 1) {
			return disjoint.get(0);
		}
		if (!wasChanged) {
			return original;
		}
		assert disjoint.size() < originalSize;
		// create a new UnionTypeExpression with "disjoint" as direct elements
		UnionTypeExpression simplified = TypeRefsFactory.eINSTANCE.createUnionTypeExpression();
		for (TypeRef d : disjoint) {
			simplified.getTypeRefs().add(TypeUtils.copyIfContained(d));
		}
		simplified.setNullModifier(original.getNullModifier());
		simplified.setUndefModifier(original.getUndefModifier());
		return simplified;
	}

	/**
	 * This method returns the subset of elems which are proper subtypes of theOther
	 */
	private static List<TypeRef> subtypesOf(List<TypeRef> elems, TypeRef theOther, N4JSTypeSystem ts,
			RuleEnvironment G) {
		return elems.stream().filter(e -> isSubtypeOf(e, theOther, ts, G)).collect(Collectors.toList());
	}

	/**
	 * Is fst a proper subtype of snd? In other words, (fst <: snd) and (fst != snd)
	 */
	private static boolean isSubtypeOf(TypeRef fst, TypeRef snd, N4JSTypeSystem ts, RuleEnvironment G) {
		return ts.subtypeSucceeded(G, fst, snd) && !(ts.equaltypeSucceeded(G, fst, snd));
	}

	/**
	 * Applied simplifications:
	 * <ul>
	 * <li>the empty union is equivalent to bottom</li>
	 * <li>a unary union is equivalent to its single element</li>
	 * <li>provided S <: T then a value conforms to (S intersection T) iff it conforms to S.</li>
	 * <li>provided S and T are elements of an intersection, with S and T known to be disjoint (ie, two unrelated
	 * classes) then the intersection is uninhabited (ie, equivalent to bottom). To recap, the N4JS spec states (4.10.2
	 * Intersection Type) "An intersection type may contain only one class, but several interfaces."</li>
	 * </ul>
	 * This method applies the above simplification rules, the result contains only non-redundant direct elements.
	 * <p>
	 * Note: this method doesn't apply itself recursively to any intersection types that might be nested inside the
	 * argument.
	 *
	 * @see SimplifyComputer
	 */
	private static TypeRef canonicalize(IntersectionTypeExpression original, boolean strict, N4JSTypeSystem ts,
			RuleEnvironment G) {
		if (null == original) {
			return null;
		}
		assert original.getTypeArgs().isEmpty();
		final int originalSize = original.getTypeRefs().size();
		if (originalSize == 0) {
			return top(G);
		}
		if (originalSize == 1) {
			return original.getTypeRefs().get(0);
		}
		boolean wasChanged = false;
		List<TypeRef> outgoing = new ArrayList<>();
		for (TypeRef incoming : original.getTypeRefs()) {
			List<TypeRef> toRemove = supertypesOf(outgoing, incoming, ts, G);
			if (!(toRemove.isEmpty())) {
				// the incoming element is more specific than those it is about to replace
				wasChanged = true;
				outgoing.removeAll(toRemove);
				outgoing.add(incoming);
			} else {
				// the incoming element isn't more specific than any elements,
				// now the question is: is any element more specific?
				final boolean alreadyAccountedFor = outgoing.stream().anyMatch(
						elem -> isSubtypeOf(elem, incoming, ts, G));
				if (alreadyAccountedFor) {
					// incoming is part of original, but not of the result, thus wasChanged.
					wasChanged = true;
				} else {
					// incoming is part of original, thus "wasChanged" is left as is.
					outgoing.add(incoming);
				}
			}
		}
		assert !outgoing.isEmpty();
		if (outgoing.size() == 1) {
			return outgoing.get(0);
		}
		if (strict && hasDisjointElements(outgoing, ts, G)) {
			return null;
		}
		if (!wasChanged) {
			return original;
		}
		assert outgoing.size() < originalSize;
		// create a new IntersectionTypeExpression with "outgoing" as direct elements
		IntersectionTypeExpression simplified = TypeRefsFactory.eINSTANCE.createIntersectionTypeExpression();
		for (TypeRef d : outgoing) {
			simplified.getTypeRefs().add(TypeUtils.copyIfContained(d));
		}
		simplified.setNullModifier(original.getNullModifier());
		simplified.setUndefModifier(original.getUndefModifier());
		return simplified;
	}

	/**
	 * Does the list contain at least two types that are known to be disjoint? In practice, this method checks only for
	 * classes unrelated over the extends-hierarchy.
	 */
	private static boolean hasDisjointElements(List<TypeRef> elems, N4JSTypeSystem ts, RuleEnvironment G) {
		if (elems.size() < 2) {
			return false;
		}
		TypeRef[] arr = elems.stream().toArray(TypeRef[]::new);
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (areUnrelatedClasses(arr[i], arr[j], ts, G)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Are the arguments known to be classes such that neither is a subtype of the other?
	 */
	private static boolean areUnrelatedClasses(TypeRef a, TypeRef b, N4JSTypeSystem ts, RuleEnvironment G) {
		if ((a.getDeclaredType() instanceof TClass) && (b.getDeclaredType() instanceof TClass)) {
			if (ts.equaltypeSucceeded(G, a, b)) {
				return false;
			}
			if (ts.subtypeSucceeded(G, a, b)) {
				return false;
			}
			if (ts.subtypeSucceeded(G, b, a)) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * This method returns the subset of elems which are proper supertypes of theOther
	 */
	private static List<TypeRef> supertypesOf(List<TypeRef> elems, TypeRef theOther, N4JSTypeSystem ts,
			RuleEnvironment G) {
		return elems.stream().filter(e -> isSubtypeOf(theOther, e, ts, G)).collect(Collectors.toList());
	}

	private static TypeRef bottom(RuleEnvironment G) {
		return RuleEnvironmentExtensions.bottomTypeRef(G);
	}

	private static TypeRef top(RuleEnvironment G) {
		return RuleEnvironmentExtensions.topTypeRef(G);
	}

}
