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

import static eu.numberfour.n4js.ts.types.MemberType.FIELD;
import static eu.numberfour.n4js.ts.types.MemberType.GETTER;
import static eu.numberfour.n4js.ts.types.MemberType.SETTER;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_CONSUMED_FIELD_ACCESSOR_PAIR_INCOMPLETE;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_CONSUMED_INHERITED_MEMBER_UNSOLVABLE_CONFLICT;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_CONSUMED_MEMBER_SOLVABLE_CONFLICT;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_CONSUMED_MEMBER_UNSOLVABLE_CONFLICT;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_IMPLEMENT_MEMBERTYPE_INCOMPATIBLE;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_MISSING_IMPLEMENTATION;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_MISSING_IMPLEMENTATION_EXT;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_NON_VISIBLE_ABSTRACT_MEMBERS;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_OVERRIDEN_CONCRETE_WITH_ABSTRACT;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_OVERRIDE_ANNOTATION;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_OVERRIDE_FIELD_REQUIRES_ACCESSOR_PAIR;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_OVERRIDE_FINAL;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_OVERRIDE_MEMBERTYPE_INCOMPATIBLE;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_OVERRIDE_NON_EXISTENT;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_OVERRIDE_PRIVATE;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_OVERRIDE_VISIBILITY;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_REDEFINED_MEMBER_TYPE_INVALID;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_REDEFINED_METHOD_TYPE_CONFLICT;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_REDEFINED_TYPE_NOT_SAME_TYPE;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_CONSUMED_FIELD_ACCESSOR_PAIR_INCOMPLETE;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_CONSUMED_INHERITED_MEMBER_UNSOLVABLE_CONFLICT;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_CONSUMED_MEMBER_SOLVABLE_CONFLICT;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_CONSUMED_MEMBER_UNSOLVABLE_CONFLICT;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_IMPLEMENT_MEMBERTYPE_INCOMPATIBLE;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_MISSING_IMPLEMENTATION;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_MISSING_IMPLEMENTATION_EXT;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_NON_VISIBLE_ABSTRACT_MEMBERS;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_OVERRIDEN_CONCRETE_WITH_ABSTRACT;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_OVERRIDE_ANNOTATION;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_OVERRIDE_FIELD_REQUIRES_ACCESSOR_PAIR;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_OVERRIDE_FINAL;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_OVERRIDE_MEMBERTYPE_INCOMPATIBLE;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_OVERRIDE_NON_EXISTENT;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_OVERRIDE_PRIVATE;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_OVERRIDE_VISIBILITY;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_REDEFINED_MEMBER_TYPE_INVALID;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_REDEFINED_METHOD_TYPE_CONFLICT;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_REDEFINED_TYPE_NOT_SAME_TYPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.util.Arrays;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.inject.Inject;

import eu.numberfour.n4js.n4JS.N4ClassifierDefinition;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.scoping.accessModifiers.MemberVisibilityChecker;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.ContainerType;
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.TClass;
import eu.numberfour.n4js.ts.types.TClassifier;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TInterface;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TModule;
import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.util.AccessModifiers;
import eu.numberfour.n4js.ts.types.util.MemberList;
import eu.numberfour.n4js.ts.types.util.NameStaticPair;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions;
import eu.numberfour.n4js.utils.ContainerTypesHelper;
import eu.numberfour.n4js.utils.ContainerTypesHelper.MemberCollector;
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator;
import eu.numberfour.n4js.validation.IssueUserDataKeys;
import eu.numberfour.n4js.validation.JavaScriptVariant;
import eu.numberfour.n4js.validation.validators.utils.MemberCube;
import eu.numberfour.n4js.validation.validators.utils.MemberMatrix;
import eu.numberfour.n4js.validation.validators.utils.MemberMatrix.SourceAwareIterator;
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * Implementation of constraints in chapter 5.4. Redefinition of Members.
 *
 * Remarks:
 * <ul>
 * <li>The implementation matches the constraints defined in the specification. Differences are caused by extended error
 * generation in case of not-consumed members.</li>
 * <li>Access modifiers are "fixed" before comparison in order to avoid strange (missing) errors.
 * </ul>
 */
public class N4JSMemberRedefinitionValidator extends AbstractN4JSDeclarativeValidator {

	private enum OverrideCompatibilityResult {
		COMPATIBLE, ERROR, ACCESSOR_PAIR
	}

	private enum RedefinitionType {
		overridden, implemented
	}

	@Inject
	private ContainerTypesHelper containerTypesHelper;
	@Inject
	private N4JSTypeSystem typeSystem;

	@Inject
	private MemberVisibilityChecker memberVisibilityChecker;

	private final static String TYPE_VAR_CONTEXT = "TYPE_VAR_CONTEXT";

	/**
	 * NEEDED, when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	@Override
	public void register(EValidatorRegistrar registrar) {
		// nop
	}

	/**
	 * Checks constraints defined in chapter 5.4. Redefinition of Members.
	 */
	@Check
	public void checkMemberRedefinitions(N4ClassifierDefinition n4ClassifierDefinition) { // NO_UCD (unused code)

		if (!(n4ClassifierDefinition.getDefinedType() instanceof TClassifier)) {
			return; // wrongly parsed
		}
		TClassifier tClassifier = (TClassifier) n4ClassifierDefinition.getDefinedType();
		getContext().put(TClassifier.class, tClassifier);

		RuleEnvironment g = RuleEnvironmentExtensions.newRuleEnvironment(tClassifier);
		getContext().put(RuleEnvironment.class, g);
		ParameterizedTypeRef classTypeRef = TypeUtils.createTypeRef(tClassifier); // the context for type variables
		getContext().put(TYPE_VAR_CONTEXT, classTypeRef);

		MemberCube memberCube = createMemberValidationList();

		if (tClassifier instanceof TClass) {
			constraints_60_MemberOverride(memberCube);
		}
		for (Entry<NameStaticPair, MemberMatrix> entry : memberCube.entrySet()) {
			MemberMatrix mm = entry.getValue();
			if (mm.hasImplemented()) {
				// first mix in
				if (holdConstraints_61_Consumption(mm)) {
					// then check if everything is implemented
					constraints_62_Implementation(mm);
				}
			}
			constraints_59_NonOverride(mm);
			constraints_42_AbstractMember(mm);
		}
		constraints_41_AbstractClass(tClassifier, memberCube);

	}

	/**
	 * Constraints 59: Non Override
	 */
	private boolean constraints_59_NonOverride(MemberMatrix mm) {
		if (mm.hasOwned()) {
			boolean bFoundWronglyDeclaredMember = false;
			for (TMember member : mm.owned()) {
				if (member.isDeclaredOverride()) {
					TMember m = mm.possibleOverrideCandidateOrError(member);
					if (m == null) {
						bFoundWronglyDeclaredMember = true;
						String message = getMessageForCLF_OVERRIDE_NON_EXISTENT(keywordProvider.keyword(member),
								member.getName());
						addIssue(message, member.getAstElement(), N4JSPackage.Literals.PROPERTY_NAME_OWNER__NAME,
								CLF_OVERRIDE_NON_EXISTENT);
					} else if (AccessModifiers.fixed(m) == MemberAccessModifier.PRIVATE) {
						bFoundWronglyDeclaredMember = true;
						String message = getMessageForCLF_OVERRIDE_PRIVATE(
								validatorMessageHelper.description(member),
								validatorMessageHelper.description(m));
						addIssue(message, member.getAstElement(), N4JSPackage.Literals.PROPERTY_NAME_OWNER__NAME,
								CLF_OVERRIDE_PRIVATE);
					}
				}
			}
			return bFoundWronglyDeclaredMember;

		}
		return true;
	}

	/**
	 * Constraints 60 (Override Compatible) Constraints defined in 5.4.1 Overriding of Members
	 */
	private void constraints_60_MemberOverride(MemberCube memberCube) {
		for (Entry<NameStaticPair, MemberMatrix> entry : memberCube.entrySet()) {
			MemberMatrix mm = entry.getValue();
			constraints_60_MemberOverride_checkEntry(mm);

		}
	}

	private void constraints_60_MemberOverride_checkEntry(MemberMatrix mm) {
		for (TMember m : mm.owned()) {
			for (TMember s : mm.inherited()) {
				// 1. override compatible
				if (overrideCompatible(RedefinitionType.overridden, m, s, false,
						mm) == OverrideCompatibilityResult.COMPATIBLE) {
					// avoid consequential errors

					// 2. accessor pair for fields
					if (s.isField() && m.isAccessor()) {
						if (!mm.hasOwnedAccessorPair()) {
							messageFieldOverrideNeedsAccessorPair(m, s);
							continue; // avoid consequential errors
						}
					}
					// 3. declared overridden
					if (!m.isDeclaredOverride()) {
						messageMissingOverrideAnnotation(m, s);
					}
				}
			}
		}
	}

	/**
	 * Constraints 61 Consumption of interface members, returns false if an error occurred which is not solvable in
	 * current classifier (i.e., incompatible meta types).
	 */
	private boolean holdConstraints_61_Consumption(MemberMatrix mm) {
		TClassifier currentType = getCurrentClassifier();
		MemberList<TMember> consumedMembers = new MemberList<>(2);
		for (TMember m : mm.implemented()) {
			boolean consume = true;
			for (SourceAwareIterator iter = mm.allMembers(); iter.hasNext();) {
				TMember m_ = iter.next();
				if (m_ == m && iter.isInterfaceMember()) {
					// the super class may implement the same interface, in that case the member should not be consumed
					// again. Thus, we only consume if both members stem from interfaces
					continue;
				}

				// 1. meta type
				if ((m.isMethod() && !m_.isMethod())
						|| (!m.isMethod() && !(m_.isAccessor() || m_.isField()))) {
					if (iter.isInterfaceMember()) {
						messageIncompatibleMembersToImplement(mm.implemented());
						return false;
					} else if (iter.isInheritedMember()) {
						messageIncompatibleInheritedMembersToImplement(m_, mm.implemented());
						return false;
					} else {
						return true;
					}
				}

				// 2 (abstract/owned), 3 (visibility) and 4 (type):
				boolean accessorPair = TypeUtils.isAccessorPair(m, m_);
				if (!accessorPair) {
					// 2. not abstract or owned
					if ((!m_.isAbstract() || m_.getContainingType() == currentType)) {
						consume = false;
						break;
					}
					// 3. access modifier
					if (AccessModifiers.less(m, m_)) {
						consume = false;
						break;
					}
					// 4. type
					if (!m_.isSetter()) {
						if (!isSubType(m, m_)) {
							consume = false;
							break;
						}
					}
					if (m_.isSetter() || m_.isField()) {
						if (!isSubType(m_, m)) {
							consume = false;
							break;
						}
					}
				}
			}
			if (consume) {
				if (!consumedMembers.contains(m)) { // in case an interface is (indirectly) redundantly implemented
					consumedMembers.add(m);
				}
			}
		}
		mm.addConsumed(consumedMembers);
		return true;
	}

	/**
	 * Constraints 62: Implementation compatible
	 */
	private void constraints_62_Implementation(MemberMatrix mm) {

		String missingAccessor = null;
		List<TMember> missingAccessors = new MemberList<>();
		List<TMember> conflictingMembers = new MemberList<>();

		TClassifier currentClassifier = getCurrentClassifier();
		boolean bMissingOverrideIssued = false;
		Set<TMember> ownedErroneousMembers = null; // avoid multiple errors on a single element (but not on
		// getter/setter pairs

		for (TMember m : mm.implemented()) {
			if (mm.consumed.contains(m)) { // m is mixed in, so it exits and we do not need other tests
				continue;
			}

			boolean bExistCompatibleMember = false;
			boolean bExistCompatibleGetter = false;
			boolean bExistCompatibleSetter = false;

			for (SourceAwareIterator iter = mm.ownedConsumedInheritedImplemented(); iter.hasNext();) {
				TMember m_ = iter.next();
				if (ownedErroneousMembers != null && !iter.isOwnedMember()) {
					break; // we found problems with owned members, we do not need more error messages.
				}
				// short cut and avoid multiple errors for single member
				if (m_ == m || ownedErroneousMembers != null && ownedErroneousMembers.contains(m_)) {
					if (iter.isInheritedMember()) { // consumed by super class and then inherited
						bExistCompatibleMember = true;
					}
					continue; // we do not break since we want to find possible consumption problems
				}

				// 1 & 2: is compatible
				OverrideCompatibilityResult compatibility = overrideCompatible(RedefinitionType.implemented, m_, m,
						!iter.isActualMember(), mm);
				if (compatibility == OverrideCompatibilityResult.ACCESSOR_PAIR) {
					continue;
				} else if (compatibility == OverrideCompatibilityResult.ERROR) {
					if (iter.isOwnedMember()) { // do not skip other errors for owned members, usually accessor pairs
						if (ownedErroneousMembers == null) {
							ownedErroneousMembers = new HashSet<>();
						}
						ownedErroneousMembers.add(m_);
					} else if (iter.isActualMember()) { // inherited member caused trouble, we see everything in the
						// error message already
						return;
					} else {
						break;
					}
				} else if (iter.isActualMember()) { // no error, mark as found and check override

					// mark found implementor
					if (m.isField()) {
						if (m_.isGetter()) {
							bExistCompatibleGetter = true;
						} else if (m_.isSetter()) {
							bExistCompatibleSetter = true;
						} else {
							bExistCompatibleMember = true;
						}
					} else {
						bExistCompatibleMember = true;
					}

					// 1 & 2 declared overridden
					if (!bMissingOverrideIssued && !m_.isDeclaredOverride()
							&& m_.getContainingType() == currentClassifier) {
						bMissingOverrideIssued = true;
						messageMissingImplementAnnotation(m_, mm.implemented());
					}
				}
			}

			if (bExistCompatibleGetter != bExistCompatibleSetter) {
				missingAccessor = bExistCompatibleGetter ? "setter" : "getter";
				missingAccessors.add(m);
			} else if (!bExistCompatibleMember && !(bExistCompatibleGetter && bExistCompatibleSetter)) {
				conflictingMembers.add(m);

			}
		}
		if (ownedErroneousMembers != null) {
			return; // avoid consequential errors
		}

		if (!conflictingMembers.isEmpty()) {
			messageConflictingMixins(conflictingMembers);
		} else if (!missingAccessors.isEmpty()) {
			messageMissingAccessor(missingAccessor, missingAccessors);
		}

	}

	/**
	 * Constraints 63 (Override Compatible) and relation overrideCompatible.
	 *
	 * @param m
	 *            the overriding member
	 * @param s
	 *            the overridden or implemented member
	 * @param consumptionConflict
	 *            check override or implementation override, the latter usually stems from a consumption conflict (so
	 *            that s did not get consumed in the first place)
	 * @param mm
	 *            member matrix, only to improve error message
	 * @return true if m is override compatible to s. Note that false does not necessarily means that an error occurred,
	 *         since e.g., a getter does not effect a setter
	 */
	private OverrideCompatibilityResult overrideCompatible(RedefinitionType redefinitionType, TMember m, TMember s,
			boolean consumptionConflict, MemberMatrix mm) {
		// 1. name and static modifier are always equal here, so we do not have to check that again

		if (TypeUtils.isAccessorPair(m, s)) {
			return OverrideCompatibilityResult.ACCESSOR_PAIR;
		}

		// 2. meta type
		boolean metaTypeMatches = s.getMemberType() == m.getMemberType();
		if (!metaTypeMatches) {
			switch (s.getMemberType()) {
			case METHOD:
				break; // initial test covers this case
			case FIELD:
				metaTypeMatches = m.getMemberType() == GETTER || m.getMemberType() == SETTER;
				break;
			case SETTER:
			case GETTER:
				metaTypeMatches = m.getMemberType() == FIELD;
				break;
			}
			if (!metaTypeMatches) {
				if (!consumptionConflict) { // avoid consequential errors
					messageOverrideMetaTypeIncompatible(redefinitionType, m, s, mm);
				}
				return OverrideCompatibilityResult.ERROR;
			}
		}

		// 3. s not final
		if (s.isFinal()) { // 2. final
			if (!consumptionConflict) { // avoid consequential errors
				messageOverrideFinal(redefinitionType, m, s);
			}
			return OverrideCompatibilityResult.ERROR;
		}

		// 4. s not const
		if (s instanceof TField) { // const only defined on TField & TStructuralField
			TField sF = (TField) s;
			if (sF.isConst()) { // 2. const
				// By GH-186 const redefinition is allowed.
				// if (!consumptionConflict) { // avoid consequential errors
				// messageOverrideConst(redefinitionType, m, sF);
				// }
				// return OverrideCompatibilityResult.ERROR;
			}
		}

		// 5. abstract
		if (m.isAbstract() && !s.isAbstract()) {
			if (!consumptionConflict) { // avoid consequential errors
				messageOverrideAbstract(redefinitionType, m, s);
			}
			return OverrideCompatibilityResult.ERROR;
		}

		// 6. type compatible
		if (!m.isSetter() && !s.isSetter()) { // in Method, Getter, Field
			Result<Boolean> result = isSubTypeResult(m, s);
			if (result.failed()) { // 4. subtype
				if (!consumptionConflict) { // avoid consequential errors
					messageOverrideMemberTypeConflict(redefinitionType, m, s, result, mm);
				}
				return OverrideCompatibilityResult.ERROR;
			}
		}

		if ((m.isSetter() || m.isField()) && !s.isGetter()) {
			Result<Boolean> result = isSubTypeResult(s, m);
			if (result.failed()) { // 4. subtype
				if (!consumptionConflict) { // avoid consequential errors
					messageOverrideMemberTypeConflict(redefinitionType, m, s, result, mm);
				}
				return OverrideCompatibilityResult.ERROR;
			}
		}

		// 7.1 visibility
		if (AccessModifiers.checkedLess(m, s)) { // fix modifiers in order to avoid strange behavior
			if (!consumptionConflict) { // avoid consequential errors
				messageOverrideVisibility(redefinitionType, m, s);
			}
			return OverrideCompatibilityResult.ERROR;
		}

		// 7.2 Special visibility handling of public@Internal and protected as they reduce each other
		MemberAccessModifier fixedLeft = AccessModifiers.fixed(m);
		MemberAccessModifier fixedRight = AccessModifiers.fixed(s);

		if ((fixedLeft == MemberAccessModifier.PROTECTED && fixedRight == MemberAccessModifier.PUBLIC_INTERNAL) ||
				(fixedLeft == MemberAccessModifier.PUBLIC_INTERNAL && fixedRight == MemberAccessModifier.PROTECTED)) {
			messageOverrideVisibility(redefinitionType, m, s);
			return OverrideCompatibilityResult.ERROR;
		}

		return OverrideCompatibilityResult.COMPATIBLE;
	}

	/**
	 * Constraints 42, 3 (Abstract Member)
	 */
	private boolean constraints_42_AbstractMember(MemberMatrix mm) {

		N4ClassifierDefinition classifierDefinition = getCurrentClassifierDefinition();
		TClassifier classifier = getCurrentClassifier();
		TModule contextModule = EcoreUtil2.getContainerOfType(classifier, TModule.class);

		Map<ParameterizedTypeRef, MemberList<TMember>> nonAccessibleAbstractMembersBySuperTypeRef = null;

		for (SourceAwareIterator iter = mm.actuallyInheritedAndMixedMembers(); iter.hasNext();) {
			TMember m = iter.next();
			if (m.isAbstract()) {
				if (!memberVisibilityChecker.isVisibleWhenOverriding(contextModule, classifier, classifier,
						m)) {
					ArrayList<ParameterizedTypeRef> superTypeRefs = FindClassifierInHierarchyUtils
							.findSuperTypesWithMember(
									classifierDefinition, m);
					for (ParameterizedTypeRef superTypeRef : superTypeRefs) {
						if (nonAccessibleAbstractMembersBySuperTypeRef == null) {
							nonAccessibleAbstractMembersBySuperTypeRef = new HashMap<>();
						}
						MemberList<TMember> nonAccessible = nonAccessibleAbstractMembersBySuperTypeRef
								.get(superTypeRef);
						if (nonAccessible == null) {
							nonAccessible = new MemberList<>();
							nonAccessibleAbstractMembersBySuperTypeRef.put(superTypeRef, nonAccessible);
						}
						nonAccessible.add(m);
					}
				}
			}
		}

		if (nonAccessibleAbstractMembersBySuperTypeRef != null) {
			for (Entry<ParameterizedTypeRef, MemberList<TMember>> entry : nonAccessibleAbstractMembersBySuperTypeRef
					.entrySet()) {
				ParameterizedTypeRef superTypeRef = entry.getKey();
				Type type = superTypeRef.getDeclaredType();
				String message = getMessageForCLF_NON_VISIBLE_ABSTRACT_MEMBERS(
						validatorMessageHelper.description(type),
						validatorMessageHelper.descriptions(entry.getValue()));
				addIssue(message, superTypeRef.eContainer(), superTypeRef.eContainingFeature(),
						CLF_NON_VISIBLE_ABSTRACT_MEMBERS);
			}
			return false;// avoid too many errors
		}

		return true;
	}

	/**
	 * Constraints 41 (Abstract Class)
	 */
	private boolean constraints_41_AbstractClass(TClassifier classifier, MemberCube memberCube) {
		List<TMember> abstractMembers = null;
		if (!classifier.isAbstract() && classifier instanceof TClass) {
			for (Entry<NameStaticPair, MemberMatrix> entry : memberCube.entrySet()) {
				MemberMatrix mm = entry.getValue();
				MemberList<TMember> l = new MemberList<>();
				Iterators.addAll(l, mm.actuallyInheritedAndMixedMembers());
				for (SourceAwareIterator iter = mm.actuallyInheritedAndMixedMembers(); iter.hasNext();) {
					TMember m = iter.next();
					if (m.isAbstract()) {
						if (abstractMembers == null) {
							abstractMembers = new ArrayList<>();
						}
						abstractMembers.add(m);
					}
				}
			}
		}
		if (abstractMembers != null) {
			messageMissingImplementations(abstractMembers);
			return false;
		}
		return true;
	}

	private void messageMissingImplementations(List<TMember> abstractMembers) {
		TClassifier classifier = getCurrentClassifier();
		if (JavaScriptVariant.getVariant(classifier) != JavaScriptVariant.external) {
			String message = getMessageForCLF_MISSING_IMPLEMENTATION(classifier.getName(),
					validatorMessageHelper.descriptions(abstractMembers));
			addIssue(message, CLF_MISSING_IMPLEMENTATION);
		} else { // to be removed, only temporary (IDE-1236)
			String message = getMessageForCLF_MISSING_IMPLEMENTATION_EXT(classifier.getName(),
					validatorMessageHelper.descriptions(abstractMembers));
			addIssue(message, CLF_MISSING_IMPLEMENTATION_EXT);
		}
	}

	private void addIssue(String message, String issueCode) {
		N4ClassifierDefinition classifier = getCurrentClassifierDefinition();
		EStructuralFeature nameFeature = classifier.eClass().getEStructuralFeature("name");
		addIssue(message, classifier, nameFeature, issueCode);
	}

	private void messageFieldOverrideNeedsAccessorPair(TMember overriding, TMember overridden) {
		if (overriding.getContainingType() == getCurrentClassifier()) {
			String missingAccessor = overriding.isGetter() ? "setter" : "getter";
			String message = getMessageForCLF_OVERRIDE_FIELD_REQUIRES_ACCESSOR_PAIR(
					validatorMessageHelper.descriptionDifferentFrom(overriding, overridden),
					validatorMessageHelper.descriptionDifferentFrom(overridden, overriding), missingAccessor);
			addIssue(message, overriding.getAstElement(), N4JSPackage.Literals.PROPERTY_NAME_OWNER__NAME,
					CLF_OVERRIDE_FIELD_REQUIRES_ACCESSOR_PAIR);
		} else {
			throw new IllegalStateException("must not happen as member is not consumed");
		}
	}

	private void messageMissingOverrideAnnotation(TMember overriding, TMember overridden) {
		if (overriding.getContainingType() == getCurrentClassifier()) {
			String message = getMessageForCLF_OVERRIDE_ANNOTATION(
					validatorMessageHelper.descriptionDifferentFrom(overriding, overridden),
					"overriding",
					validatorMessageHelper.descriptionDifferentFrom(overridden, overriding));
			addIssue(message, overriding.getAstElement(), N4JSPackage.Literals.PROPERTY_NAME_OWNER__NAME,
					CLF_OVERRIDE_ANNOTATION);
		} else {
			throw new IllegalStateException("must not happen as member is not consumed");
		}
	}

	private void messageOverrideMemberTypeConflict(RedefinitionType redefinitionType, TMember overriding,
			TMember overridden, Result<Boolean> result, MemberMatrix mm) {

		String message;
		String code;
		String redefinitionTypeName = redefinitionType.name();
		if (redefinitionType == RedefinitionType.implemented &&
				Iterables.contains(mm.implemented(), overriding)) {
			redefinitionTypeName = "consumed";
		}

		String overridingSource = "";
		if (redefinitionType == RedefinitionType.implemented &&
				Iterables.contains(mm.inherited(), overriding)) {
			overridingSource = "inherited ";
		}

		String extraMessage = cfOtherImplementedMembers(mm, overriding, overridden);

		if (overriding.isField() && overridden.isField()) {
			code = CLF_REDEFINED_TYPE_NOT_SAME_TYPE;
			message = getMessageForCLF_REDEFINED_TYPE_NOT_SAME_TYPE(
					overridingSource + validatorMessageHelper.descriptionDifferentFrom(overriding, overridden),
					redefinitionTypeName,
					validatorMessageHelper.descriptionDifferentFrom(overridden, overriding),
					extraMessage);
		} else if (overriding.isMethod() && overridden.isMethod()) {
			code = CLF_REDEFINED_METHOD_TYPE_CONFLICT;

			message = getMessageForCLF_REDEFINED_METHOD_TYPE_CONFLICT(
					overridingSource + validatorMessageHelper.descriptionDifferentFrom(overriding, overridden),
					redefinitionTypeName,
					validatorMessageHelper.descriptionDifferentFrom(overridden, overriding),
					validatorMessageHelper.trimTypesystemMessage(result),
					extraMessage);
		} else {
			code = CLF_REDEFINED_MEMBER_TYPE_INVALID;
			message = getMessageForCLF_REDEFINED_MEMBER_TYPE_INVALID(
					overridingSource + validatorMessageHelper.descriptionDifferentFrom(overriding, overridden),
					validatorMessageHelper.descriptionDifferentFrom(overridden, overriding),
					redefinitionTypeName,
					validatorMessageHelper.trimTypesystemMessage(result),
					extraMessage);
		}

		addIssueToMemberOrInterfaceReference(redefinitionType, overriding, overridden, message, code);
	}

	private void messageOverrideAbstract(RedefinitionType redefinitionType, TMember overriding, TMember overridden) {
		String message = getMessageForCLF_OVERRIDEN_CONCRETE_WITH_ABSTRACT(
				validatorMessageHelper.descriptionDifferentFrom(overriding, overridden),
				validatorMessageHelper.descriptionDifferentFrom(overridden, overriding));
		addIssueToMemberOrInterfaceReference(redefinitionType, overriding, overridden, message,
				CLF_OVERRIDEN_CONCRETE_WITH_ABSTRACT);
	}

	private void messageOverrideVisibility(RedefinitionType redefinitionType, TMember overriding, TMember overridden) {
		String message = getMessageForCLF_OVERRIDE_VISIBILITY(
				validatorMessageHelper.descriptionDifferentFrom(overriding, overridden),
				validatorMessageHelper.descriptionDifferentFrom(overridden, overriding));
		addIssueToMemberOrInterfaceReference(redefinitionType, overriding, overridden, message,
				CLF_OVERRIDE_VISIBILITY,
				IssueUserDataKeys.CLF_OVERRIDE_VISIBILITY.OVERRIDDEN_MEMBER_ACCESS_MODIFIER,
				overridden.getMemberAccessModifier().getName(),
				IssueUserDataKeys.CLF_OVERRIDE_VISIBILITY.OVERRIDDEN_MEMBER_NAME, overridden.getName(),
				IssueUserDataKeys.CLF_OVERRIDE_VISIBILITY.SUPER_CLASS_NAME, overridden.getContainingType().getName());

	}

	private void messageOverrideFinal(RedefinitionType redefinitionType, TMember overriding, TMember overridden) {
		String message = getMessageForCLF_OVERRIDE_FINAL(
				validatorMessageHelper.descriptionDifferentFrom(overriding, overridden),
				validatorMessageHelper.descriptionDifferentFrom(overridden, overriding));
		addIssueToMemberOrInterfaceReference(redefinitionType, overriding, overridden, message,
				CLF_OVERRIDE_FINAL,
				IssueUserDataKeys.CLF_OVERRIDE_FINAL.OVERRIDDEN_MEMBER_URI,
				EcoreUtil.getURI(overridden).toString());
	}

	/**
	 * @param mm
	 *            only for improving the error message in case of implementation problems.
	 */
	private void messageOverrideMetaTypeIncompatible(RedefinitionType redefinitionType, TMember overriding,
			TMember overridden, MemberMatrix mm) {
		if (redefinitionType == RedefinitionType.overridden) {
			String message = getMessageForCLF_OVERRIDE_MEMBERTYPE_INCOMPATIBLE(
					validatorMessageHelper.descriptionDifferentFrom(overriding, overridden),
					validatorMessageHelper.descriptionDifferentFrom(overridden, overriding));
			addIssueToMemberOrInterfaceReference(redefinitionType, overriding, overridden, message,
					CLF_OVERRIDE_MEMBERTYPE_INCOMPATIBLE);
		} else { // consumed method implicitly overrides:
			String message = getMessageForCLF_IMPLEMENT_MEMBERTYPE_INCOMPATIBLE(
					validatorMessageHelper.descriptionDifferentFrom(overriding, overridden),
					validatorMessageHelper.descriptionDifferentFrom(overridden, overriding),
					cfOtherImplementedMembers(mm, overridden));
			addIssueToMemberOrInterfaceReference(redefinitionType, overriding, overridden, message,
					CLF_IMPLEMENT_MEMBERTYPE_INCOMPATIBLE);
		}
	}

	private String cfOtherImplementedMembers(MemberMatrix mm, TMember... filteredMembers) {
		String others = validatorMessageHelper.descriptions(
				StreamSupport.stream(mm.implemented().spliterator(), false)
						.filter(m -> !Arrays.contains(filteredMembers, m))
						.collect(Collectors.toList()));
		if (others.length() == 0) {
			return "";
		}
		return " Also cf. " + others + ".";
	}

	private void messageIncompatibleMembersToImplement(Iterable<TMember> implementedMembers) {
		String message = getMessageForCLF_CONSUMED_MEMBER_UNSOLVABLE_CONFLICT(
				validatorMessageHelper.descriptions(implementedMembers));
		addIssue(message, CLF_CONSUMED_MEMBER_UNSOLVABLE_CONFLICT);
	}

	private void messageIncompatibleInheritedMembersToImplement(TMember inheritedMember,
			Iterable<TMember> implementedMembers) {
		String message = getMessageForCLF_CONSUMED_INHERITED_MEMBER_UNSOLVABLE_CONFLICT(
				validatorMessageHelper.description(inheritedMember),
				validatorMessageHelper.descriptions(implementedMembers));
		addIssue(message, CLF_CONSUMED_INHERITED_MEMBER_UNSOLVABLE_CONFLICT);
	}

	private void messageConflictingMixins(List<? extends TMember> conflictingMembers) {
		String message = getMessageForCLF_CONSUMED_MEMBER_SOLVABLE_CONFLICT(
				validatorMessageHelper.descriptions(conflictingMembers));
		addIssue(message, CLF_CONSUMED_MEMBER_SOLVABLE_CONFLICT);
	}

	private void messageMissingImplementAnnotation(TMember overriding, Iterable<TMember> implementedMembers) {
		if (overriding.getContainingType() == getCurrentClassifier()) {
			String redefinitionVerb = getCurrentClassifier() instanceof TInterface ? "overriding" : "implementing";

			String message = getMessageForCLF_OVERRIDE_ANNOTATION(
					validatorMessageHelper.description(overriding),
					redefinitionVerb,
					validatorMessageHelper.descriptions(implementedMembers));
			addIssue(message, overriding.getAstElement(), N4JSPackage.Literals.PROPERTY_NAME_OWNER__NAME,
					CLF_OVERRIDE_ANNOTATION);
		} else {
			throw new IllegalStateException("must not happen as member is not consumed");
		}
	}

	private void messageMissingAccessor(String missingAccessor, List<? extends TMember> conflictingMembers) {
		// CLF_IMPLEMENT_MIXIN_MISSING_ACCESSOR
		String message = getMessageForCLF_CONSUMED_FIELD_ACCESSOR_PAIR_INCOMPLETE(
				missingAccessor,
				validatorMessageHelper.descriptions(conflictingMembers));
		addIssue(message, CLF_CONSUMED_FIELD_ACCESSOR_PAIR_INCOMPLETE);
	}

	private void addIssueToMemberOrInterfaceReference(RedefinitionType redefinitionType, TMember overriding,
			TMember implemented,
			String message, String issueCode, String... issueData) {

		if (redefinitionType == RedefinitionType.overridden
				&& overriding.getContainingType() != getCurrentClassifier()) {
			throw new IllegalStateException("must not happen as member is not consumed");
		}

		TClassifier currentClassifier = getCurrentClassifier();
		if (overriding.getContainingType() == currentClassifier) {
			addIssue(message, overriding.getAstElement(), N4JSPackage.Literals.PROPERTY_NAME_OWNER__NAME, issueCode,
					issueData);
		} else {
			MemberCollector memberCollector = containerTypesHelper.fromContext(getCurrentClassifierDefinition());
			ContainerType<?> bequestingType = memberCollector.directSuperTypeBequestingMember(currentClassifier,
					implemented);
			Optional<ParameterizedTypeRef> optRef = StreamSupport
					.stream(getCurrentClassifierDefinition().getImplementedOrExtendedInterfaceRefs().spliterator(),
							false)
					.filter(ref -> ref.getDeclaredType() == bequestingType).findAny();
			ParameterizedTypeRef ref = optRef.get();
			EStructuralFeature feature = ref.eContainingFeature();
			List<?> list = (List<?>) getCurrentClassifierDefinition().eGet(feature);
			int index = list.indexOf(ref);
			addIssue(message, getCurrentClassifierDefinition(), feature, index, issueCode, issueData);
		}
	}

	private boolean isSubType(TMember left, TMember right) {
		return !isSubTypeResult(left, right).failed();
	}

	private Result<Boolean> isSubTypeResult(TMember left, TMember right) {
		ParameterizedTypeRef classTypeRef = getCurrentTypeContext();
		RuleEnvironment G = getCurrentRuleEnvironment();

		// will return type of value for fields, function type for methods, type of return value for getters, type of
		// parameter for setters
		TypeRef typeLeft = typeInferencer.tau(left, classTypeRef, false);
		TypeRef typeRight = typeInferencer.tau(right, classTypeRef, false);

		return typeSystem.subtype(G, typeLeft, typeRight);
	}

	private TClassifier getCurrentClassifier() {
		return (TClassifier) getContext().get(TClassifier.class);
	}

	private N4ClassifierDefinition getCurrentClassifierDefinition() {
		return (N4ClassifierDefinition) getCurrentObject();
	}

	private RuleEnvironment getCurrentRuleEnvironment() {
		return (RuleEnvironment) getContext().get(RuleEnvironment.class);
	}

	private ParameterizedTypeRef getCurrentTypeContext() {
		return (ParameterizedTypeRef) getContext().get(TYPE_VAR_CONTEXT);
	}

	private MemberCube createMemberValidationList() {
		MemberCollector memberCollector = containerTypesHelper.fromContext(getCurrentClassifierDefinition());
		TClassifier tClassifier = getCurrentClassifier();
		return new MemberCube(tClassifier, memberCollector);

	}

}
