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
import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.typesystem.constraints.TypeConstraint
import eu.numberfour.n4js.typesystem.constraints.Variance
import eu.numberfour.n4js.utils.StructuralMembersTriple
import eu.numberfour.n4js.utils.StructuralTypesHelper
import eu.numberfour.n4js.validation.N4JSElementKeywordProvider
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem
import eu.numberfour.n4js.ts.typeRefs.ExistentialTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeArgument
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.FieldAccessor
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TN4Classifier
import eu.numberfour.n4js.ts.types.TSetter
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.TypingStrategy
import eu.numberfour.n4js.ts.utils.TypeCompareUtils
import it.xsemantics.runtime.Result
import it.xsemantics.runtime.RuleEnvironment
import java.util.List
import org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtext.EcoreUtil2

import static eu.numberfour.n4js.AnnotationDefinition.*
import static eu.numberfour.n4js.typesystem.StructuralTypingResult.*
import static eu.numberfour.n4js.utils.StructuralMembersPredicates.*
import static eu.numberfour.n4js.ts.types.TypingStrategy.*

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import static extension eu.numberfour.n4js.utils.N4JSLanguageUtils.*

/**
 */
@Singleton
class StructuralTypingComputer extends TypeSystemHelperStrategy {

	@Inject private N4JSTypeSystem ts;
	@Inject private N4JSElementKeywordProvider keywordProvider;
	@Inject private StructuralTypesHelper structuralTypesHelper;

	@Data
	public static class StructTypingInfo {
		val RuleEnvironment G;
		val TypeRef left;
		val TypeRef right;
		val TypingStrategy leftStrategy;
		val TypingStrategy rightStrategy;
		val List<String> missingMembers = newArrayList();
		val List<String> wrongMembers = newArrayList();
	}

	public def StructuralTypingResult isStructuralSubtype(RuleEnvironment G, TypeRef left, TypeRef right) {

		val rightStrategy = right.typingStrategy;
		val leftStrategy = left.typingStrategy;

		// shortcut, handle with care!
		if ((left.declaredType instanceof TN4Classifier || left.declaredType instanceof TypeVariable) // <-- IDEBUG-838
			&& left.typingStrategy === right.typingStrategy
			&& STRUCTURAL_FIELD_INITIALIZER !== leftStrategy && STRUCTURAL_FIELD_INITIALIZER !== rightStrategy
			&& left.declaredType === right.declaredType
			&& left.structuralMembers.empty && right.structuralMembers.empty
			&& left.typeArgs.empty) {
			return result(left, right, emptyList, emptyList);
		}

		// 61, 2a)
		if (!right.useSiteStructuralTyping && right.defSiteStructuralTyping) {
			if (ts.subtypeSucceeded(G, left, G.n4ObjectTypeRef)) {
				return failureDefSiteWithN4Object(
					'All N4Objects must explicitly extend/implement definition site structural type ' +
						right.typeRefAsString + '.');
			}
		}

		// recursion guard (see method #isStructuralSubtypingInProgressFor() for details)
		if (G.isStructuralSubtypingInProgressFor(left, right)) {
			return result(left, right, emptyList, emptyList);
		}
		val G2 = G.wrap; // don't pollute incoming rule environment with our recursion guard
		G2.rememberStructuralSubtypingInProgressFor(left, right);

		val info = new StructTypingInfo(G2, left, right, leftStrategy, rightStrategy); // we'll collect error messages in here

		val iter = structuralTypesHelper.getMembersTripleIterator(G2, left, right, true);
		while (iter.hasNext) {
			// check if left member can fulfill the structural requirement imposed by right member
			checkMembers(iter.next, info);
		}

		return result(left, right, info.missingMembers, info.wrongMembers);
	}

	def private void checkMembers(StructuralMembersTriple triple, StructTypingInfo info) {

		val leftMember = triple.left;
		val rightMember = triple.right;
		val leftOtherAccessor = triple.leftOtherAccessor;
		val leftStrategy = info.leftStrategy;
		val rightStrategy = info.rightStrategy;

		checkMembers(leftMember, rightMember, info, rightStrategy);

		switch (rightStrategy) {

			// For any ~r~ right members.
			case STRUCTURAL_READ_ONLY_FIELDS: {

				// For any readable, non-optional right members. Initialized fields does not count as optional.
				if (READABLE_FIELDS_PREDICATE.apply(rightMember) && !rightMember.optional) {

					// For ~w~ left members requires an explicit getter.
					if (STRUCTURAL_WRITE_ONLY_FIELDS === leftStrategy
						&& !GETTERS_PREDICATE.apply(leftMember)
						&& !GETTERS_PREDICATE.apply(leftOtherAccessor)) {

						info.wrongMembers.add(rightMember.name + " failed: readable field requires a getter in subtype.");

					// Otherwise any readable field is enough.
					} else if (!READABLE_FIELDS_PREDICATE.apply(leftMember) && !READABLE_FIELDS_PREDICATE.apply(leftOtherAccessor)) {
						info.wrongMembers.add(rightMember.name + " failed: readable field requires a readable field or a getter in subtype.");
					}
				}
			}

			// For any ~w~ right members.
			case STRUCTURAL_WRITE_ONLY_FIELDS: {

				// For any writable right members.
				if (WRITABLE_FIELDS_PREDICATE.apply(rightMember)) {

					// If left is either ~r~ or ~i~, then an explicit setter is required.
					if ((STRUCTURAL_READ_ONLY_FIELDS === leftStrategy || STRUCTURAL_FIELD_INITIALIZER === leftStrategy)
						&& !SETTERS_PREDICATE.apply(leftMember)
						&& !SETTERS_PREDICATE.apply(leftOtherAccessor)) {

						info.wrongMembers.add(rightMember.name + " failed: writable field requires a setter in subtype.");

					// Otherwise any writable field (data field or setter) is sufficient.
					} else if (!WRITABLE_FIELDS_PREDICATE.apply(leftMember) && !WRITABLE_FIELDS_PREDICATE.apply(leftOtherAccessor)) {
						info.wrongMembers.add(rightMember.name + " failed: writable field requires a writable field or a setter in subtype.");
					}
				}
			}

			// For any ~i~ right members.
			case STRUCTURAL_FIELD_INITIALIZER: {

				// For any non-optional and writable right members.
				// Important: unlike in case of ~r~, here we treat initialized fields, such as @Final ones as optional fields.
				if (WRITABLE_FIELDS_PREDICATE.apply(rightMember) && rightMember.mandatoryField) {

					// If left is ~w~, then getters are required.
					if (STRUCTURAL_WRITE_ONLY_FIELDS === leftStrategy
						&& !(GETTERS_PREDICATE.apply(leftMember) || GETTERS_PREDICATE.apply(leftOtherAccessor))) {

						info.wrongMembers.add(rightMember.name + " failed: non-optional writable field requires a getter in subtype.");

					// Otherwise any readable fields are fine.
					} else if (!(READABLE_FIELDS_PREDICATE.apply(leftMember) || READABLE_FIELDS_PREDICATE.apply(leftOtherAccessor))) {
						info.wrongMembers.add(rightMember.name + " failed: non-optional writable field requires a readable field or a getter in subtype.");
					}
				}
			}

			default: {

				// If the left member is ~r~, ~w~ and/or ~i~ type.
				if (STRUCTURAL_READ_ONLY_FIELDS === leftStrategy || STRUCTURAL_WRITE_ONLY_FIELDS === leftStrategy || STRUCTURAL_FIELD_INITIALIZER === leftStrategy) {
					switch (rightMember) {
						// If right is writable field, a getter/setter pair is mandatory on the left.
						case rightMember.writeableField: {
							if (!isGetterSetterPair(leftMember, leftOtherAccessor)) {
								info.wrongMembers.add(rightMember.name + " failed: writable field requires a getter/setter pair in subtype.");
							}
						}
						// If right is readable, we require an explicit getter.
						case READABLE_FIELDS_PREDICATE.apply(rightMember): {
							if (!(GETTERS_PREDICATE.apply(leftMember) || GETTERS_PREDICATE.apply(leftOtherAccessor))) {
								info.wrongMembers.add(rightMember.name + " failed: read-only field requires a getter in subtype.");
							}
						}
						// If there is a setter on the right, then we need a setter on the left.
						case SETTERS_PREDICATE.apply(rightMember) : {
							if (!(SETTERS_PREDICATE.apply(leftMember) || SETTERS_PREDICATE.apply(leftOtherAccessor))) {
								info.wrongMembers.add(rightMember.name + " failed: setter requires a setter in subtype.");
							}
						}
					}
				} else {
					// for a writable field on the right-hand side, require a getter/setter pair on the left
					if (rightMember.writeableField && leftMember instanceof FieldAccessor) {
						if (!(leftOtherAccessor instanceof TSetter)) {
							// special error message in case only either a getter XOR setter is supplied for a field
							info.wrongMembers.add(rightMember.name + " failed: writable field requires a field or a getter/setter pair in subtype.");
						} else {
							// check type of setter as usual
							checkMembers(leftOtherAccessor, rightMember, info, rightStrategy);
						}
					}

				}
			}

		}

	}

	/** Returns with {@code true} iff the  the arguments are a getter-setter accessor pair. */
	def private isGetterSetterPair(TMember firstLeft, TMember secondLeft) {
		(GETTERS_PREDICATE.apply(firstLeft) || GETTERS_PREDICATE.apply(secondLeft))
		&& (SETTERS_PREDICATE.apply(secondLeft) || SETTERS_PREDICATE.apply(secondLeft));
	}

	/**
	 * Checks if the member 'left' (may be <code>null</code> if not found) fulfills the structural requirement represented
	 * by member 'right'.  In the error case, the two lists of error messages in 'info' are updated accordingly.
	 * <p>
	 * NOTE: in case of a field on right-hand side, this method accepts a getter OR(!) a setter of appropriate type
	 * on left side; the requirement that BOTH a getter AND setter must be provided for a writable field must be
	 * checked outside this method.
	 */
	def private void checkMembers(TMember left, TMember right, StructTypingInfo info, TypingStrategy rightStrategy) {
		val G = info.G;

		// !!! keep the following aligned with below method #reduceMembers() !!!
		if (left === null) {
			// no corresponding member found on left side

			if ((null !== right && right.optional)
				|| (STRUCTURAL_FIELD_INITIALIZER === rightStrategy && (right.initializedField || right.optionalSetter))) {
				// nothing to do (rightMember is optional or initialized for ~i~ typing.)
			} else if (right?.containingType === info.G.objectType) {
				// ignore object members, can only happen if left is structural field type
			} else {
				// standard case of missing member on left side -> report error
				info.missingMembers.add(keywordProvider.keyword(right) + " " + right.name);
			}
		} else {
			// found a corresponding member
			// -> make sure types are compatible

			val mtypes = getMemberTypes(left, right, info);
			var subtypeResult = null as Result<Boolean>;

			// IDE-1780
			if (left.optional && !right.optional) {
				info.missingMembers.add(left.name + ' failed: non-optional member requires a corresponding non-optional member in the structural subtype.');
			} else if (right.writeableField && left instanceof TField) {
				// Fields are on both sides.
				// T_FL = T_FR
				if (left.readOnlyField
					&& STRUCTURAL_FIELD_INITIALIZER !== rightStrategy
					&& STRUCTURAL_READ_ONLY_FIELDS !== rightStrategy) {
					info.wrongMembers.add(right.name + " failed: field is read-only.");
				} else {
					subtypeResult = ts.equaltype(G, mtypes.key, mtypes.value);
				}
			} else if (right instanceof TSetter || left instanceof TSetter) {
				// Setter on one side (other side may be field or setter).
				// contra-variant
				subtypeResult = ts.supertype(G, mtypes.key, mtypes.value);
			} else {
				// Other cases such as methods and function expressions.
				// Only L<:R.
				subtypeResult = ts.subtype(G, mtypes.key, mtypes.value);
			}

			if (subtypeResult !== null && subtypeResult.failed) {
				info.wrongMembers.add(right.name + " " + subtypeResult.ruleFailedException.message);
			}
		}
	}

	/**
	 * Same as previous method, but instead of actually checking the types, we return a constraint. This would normally
	 * belong into class <code>InferenceContext</code>, but is placed here to keep it aligned with above method more
	 * easily.
	 */
	def public TypeConstraint reduceMembers(TMember left, TMember right, Variance variance, StructTypingInfo info) {
		if (variance === Variance.CONTRA) {
			// normalize variance (i.e. turn CONTRA into CO)
			return reduceMembers(right, left, Variance.CO, info);
		}

		// !!! keep the following aligned with above method #checkMembers() !!!
		if (left === null) {
			if ((null !== right && right.optional)
				|| (STRUCTURAL_FIELD_INITIALIZER === info?.rightStrategy && (right.initializedField || right.optionalSetter))) {
				return TypeConstraint.TRUE;
			} else if (right?.containingType === info.G.objectType) {
				return TypeConstraint.TRUE;
			} else {
				return TypeConstraint.FALSE;
			}
		} else {
			val mtypes = getMemberTypes(left, right, info);

			if (left.optional && !right.optional) {
				return TypeConstraint.FALSE;
			} else if (right.writeableField && left instanceof TField) {

				if (left.readOnlyField
					&& STRUCTURAL_FIELD_INITIALIZER !== info.rightStrategy
					&& STRUCTURAL_READ_ONLY_FIELDS !== info.rightStrategy) {
					return TypeConstraint.FALSE;
				} else {
					return new TypeConstraint(mtypes.key, mtypes.value, Variance.INV);
				}

			} else if (right instanceof TSetter || left instanceof TSetter) {
				return new TypeConstraint(mtypes.key, mtypes.value, variance.inverse);
			} else {
				return new TypeConstraint(mtypes.key, mtypes.value, variance);
			}
		}
	}

	/**
	 * Store a guard in the given rule environment to note that we are in the process of inferring
	 * left ~<: right.
	 * <p>
	 * IDEBUG-171:
	 * If we are already computing the structural subtype relation left ~<: right and we are again asked
	 * whether left ~<: right, then we simply want to return success.
	 * The rationale is that if we run into a cycle while checking the members' types, we can simply say the
	 * members causing the cycle won't render the overall evaluation false ("an uns soll es nicht scheitern").
	 * <p>
	 * EXAMPLE 1:
	 * <pre>
	 * class Element {
	 * 	public ~Element child;
	 * }
	 * var ~Element e1;
	 * var ~Element e2;
	 * //e1 = e2;   // remove comment to get stack overflow when disabling if-clause in #isSubtype()
	 * </pre>
	 * <p>
	 * EXAMPLE 2:
	 * <pre>
	 * class A {
	 * 	public ~B f;
	 * }
	 * class B {
	 * 	public ~A f;
	 * }
	 * var ~A a;
	 * var ~B b;
	 * //a = b;   // remove comment to get stack overflow when disabling if-clause in #isSubtype()
	 * </pre>
	 * Note how this is analogous to what EMF is doing when computing structural equality as explained
	 * in the paragraph on "populating a two way map" in the following EMF API documentation:
	 * {@link EqualityHelper}
	 */
	def private void rememberStructuralSubtypingInProgressFor(RuleEnvironment G, TypeRef left, TypeRef right) {
		G.add(GUARD_STRUCTURAL_TYPING_COMPUTER -> (left.wrap -> right.wrap), Boolean.TRUE);
	}

	def private boolean isStructuralSubtypingInProgressFor(RuleEnvironment G, TypeRef left, TypeRef right) {
		return G.get(GUARD_STRUCTURAL_TYPING_COMPUTER -> (left.wrap -> right.wrap)) !== null;
	}

	def private TypeCompareUtils.SemanticEqualsWrapper wrap(TypeRef typeRef) {
		new TypeCompareUtils.SemanticEqualsWrapper(typeRef);
	}

	def private Pair<TypeArgument, TypeArgument> getMemberTypes(TMember leftMember, TMember rightMember,
		StructTypingInfo info) {

		val G = info.G;
		val typeLeftRaw = ts.type(G, leftMember).value;
		val typeRightRaw = ts.type(G, rightMember).value;

		// replace bound type variables with type arguments
		val G_left = G.wrap;
		val G_right = G.wrap;
		G_left.addSubstitutions(info.left);
		G_right.addSubstitutions(info.right);

		// this is only a prototype implementation (see IDE-1256)
		val reopen = newArrayList;
		collectExistentialTypeRefs(G_right, reopen); // note: we only reopen on rhs

		val typeLeft = ts.substTypeVariables(G_left, typeLeftRaw).value;
		val typeRight = ts.substTypeVariables(G_right, typeRightRaw).value;

		reopen.forEach[ G.addExistentialTypeToBeReopened(it) ];

		return typeLeft -> typeRight;
	}

	/**
	 * Searches the values of all type variable mappings in the given rule environment for
	 * {@link ExistentialTypeRef}s and adds them to the given list.
	 */
	def private void collectExistentialTypeRefs(RuleEnvironment G, List<? super ExistentialTypeRef> addHere) {
		var next = G;
		while (next !== null) {
			for (entry : next.entrySet) {
				val key = entry.key;
				if (key instanceof TypeVariable) {
					val value = entry.value as TypeRef;
					if (value instanceof ExistentialTypeRef) {
						addHere.add(value);
					} else {
						addHere.addAll(EcoreUtil2.getAllContentsOfType(value,ExistentialTypeRef));
					}
				}
			}
			next = G.next;
		}
	}

	/**
	 * Returns with {@code true} if the member argument is
	 * <ul>
	 * <li>*NOT* {@link TMember#isOptional() optional} member,</li>
	 * <li>*NOT* {@link #isInitializedField(TMember) initialized field} and</li>
	 * <li>*NOT* {@link #isOptionalSetter(TMember) optional setter}.</li>
	 * </ul>
	 * Otherwise returns with {@code false}.
	 */
	def private isMandatoryField(TMember it) {
		null !== it && !optional && !initializedField && !optionalSetter;
	}

	/**
	 * Argument is an instance of a {@link TField field} and {@link TField#isHasExpression() has initializer expression}.
	 * This method is {@code null} safe.
	 */
	def private isInitializedField(TMember it) {
		if (it instanceof TField) { hasExpression } else false;
	}

	/**
	 * Returns {@code true} if the member argument is an instance of a {@link TSetter setter} and has
	 * {@link AnnotationDefinition#IDE_1996 IDE_1996} annotation. Otherwise returns with {@code false}.
	 */
	def private isOptionalSetter(TMember it) {
		it instanceof TSetter && PROVIDES_INITIALZER.hasAnnotation(it);
	}

}
