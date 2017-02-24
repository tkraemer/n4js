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
package eu.numberfour.n4js.scoping.members;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.xbase.lib.Pair;

import com.google.common.base.Joiner;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef;
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TFormalParameter;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TMethod;
import eu.numberfour.n4js.ts.types.TSetter;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import it.xsemantics.runtime.RuleEnvironment;

/**
 *
 */
public class ComposedMemberAggregate {

	/////////////////////////// Static Part ///////////////////////////

	private static ComposedMemberAggregate currCMA;

	/**
	 *
	 */
	public static void init(boolean writeAccess, Resource resource, N4JSTypeSystem ts) {
		Objects.isNull(currCMA);
		currCMA = new ComposedMemberAggregate(writeAccess, resource, ts);
	}

	/**
	 *
	 */
	public static void addMember(TMember member, RuleEnvironment G) {
		Objects.nonNull(currCMA);
		Pair<TMember, RuleEnvironment> pair = null;
		if (member != null) {
			pair = new Pair<>(member, G);
		}
		currCMA.siblings.add(pair); // adds null to indicate missing members
	}

	/**
	 *
	 */
	public static ComposedMemberAggregate get() {
		ComposedMemberAggregate lastCMA = currCMA;
		currCMA = new ComposedMemberAggregate(lastCMA.isWriteAccess, lastCMA.resource, lastCMA.ts);
		return lastCMA;
	}

	/////////////////////////// Non-Static Part ///////////////////////////

	// finals get set in constructor
	private final boolean isWriteAccess;
	private final Resource resource;
	private final N4JSTypeSystem ts;
	private final List<Pair<TMember, RuleEnvironment>> siblings;

	// non-finals are set in initMemberAggregate()
	private boolean isInitialized = false;
	private boolean isEmpty = true;
	private boolean isSiblingMissing = false;
	private boolean hasMultipleMemberTypes = false;
	private boolean hasFieldMemberType = false;
	private boolean hasGetterMemberType = false;
	private boolean hasSetterMemberType = false;
	private boolean hasMethodMemberType = false;
	private boolean hasNonMethodMemberType = false;
	private boolean onlyMethodMemberTypes = true;
	private boolean onlyFieldMemberTypes = true;
	private boolean onlyGetterMemberTypes = true;
	private boolean onlySetterMemberTypes = true;
	private boolean hasReadOnlyField = false;
	private boolean onlyReadOnlyFields = true;
	private final boolean hasValidationProblem = false;
	private MemberAccessModifier accessibilityMin = MemberAccessModifier.PUBLIC;
	private MemberAccessModifier accessibilityMax = MemberAccessModifier.PRIVATE;
	private final Map<MemberType, List<TypeRef>> typeRefsMap = new HashMap<>();
	private final List<TypeRef> typeRefs = new ArrayList<>();
	private final List<TypeRef> methodTypeRefsVoid = new ArrayList<>();
	private final List<TypeRef> methodTypeRefsNonVoid = new ArrayList<>();
	private final Map<TypeRef, RuleEnvironment> typeRef2G = new HashMap<>();

	private final List<FParAggregate> fParameters = new ArrayList<>();
	private boolean isVariadicButLastFParIsDifferent = false;

	/***/
	public static class FParAggregate {
		// is set in initMemberAggregate()
		private final List<Pair<TFormalParameter, RuleEnvironment>> fpSiblings = new ArrayList<>();

		// is set in initFParAggregate()
		private final List<String> names = new LinkedList<>();
		private boolean allOptional = true;
		private boolean allNonOptional = true;
		private boolean hasValidationProblem = false;
		private final List<TypeRef> typeRefs = new ArrayList<>();
		private final List<TypeRef> typeRefsVariadic = new ArrayList<>();
		private final List<TypeRef> typeRefsVariadicAccumulated = new ArrayList<>();

		/***/
		public String getName() {
			return Joiner.on("_").join(names);
		}

		/***/
		public boolean allOptional() {
			return allOptional;
		}

		/***/
		public boolean allNonOptional() {
			return allNonOptional;
		}

		/***/
		public boolean hasValidationProblem() {
			return hasValidationProblem;
		}

		/***/
		public List<TypeRef> getTypeRefs() {
			return typeRefs;
		}

		/***/
		public List<TypeRef> getTypeRefsVariadic() {
			return typeRefsVariadic;
		}

		/***/
		public List<TypeRef> getTypeRefsVariadicAccumulated() {
			return typeRefsVariadicAccumulated;
		}
	}

	private ComposedMemberAggregate(boolean writeAccess, Resource resource, N4JSTypeSystem ts) {
		this.isWriteAccess = writeAccess;
		this.resource = resource;
		this.ts = ts;
		this.siblings = new LinkedList<>();
	}

	/////////////////////////// Init Methods ///////////////////////////

	synchronized private void initMemberAggregate() {
		if (isInitialized)
			return;

		this.isSiblingMissing = siblings.contains(null);

		MemberType lastMType = null;
		for (Pair<TMember, RuleEnvironment> pair : siblings) {
			if (pair == null)
				continue;
			this.isEmpty = false;

			TMember member = pair.getKey();
			RuleEnvironment G = pair.getValue();

			lastMType = handleMemberTypes(lastMType, member);
			handleReadOnlyField(member);
			handleAccessibility(member);
			handleTypeRefLists(member, G);
			handleFParameters(member, G);
		}

		// init: fParameters
		List<TypeRef> currVariadicAccumulated = new LinkedList<>();
		for (FParAggregate fpAggr : fParameters) {
			initFParAggregate(fpAggr);

			// handle: typeRefsVariadicAccumulated
			currVariadicAccumulated.addAll(fpAggr.typeRefsVariadic);
			fpAggr.typeRefsVariadicAccumulated.addAll(currVariadicAccumulated);
		}

		handleIsVariadicButLastFParIsDifferent();

		handleValidationProblems();

		this.isInitialized = true;
	}

	private void initFParAggregate(FParAggregate fpAggr) {
		for (Pair<TFormalParameter, RuleEnvironment> fpPair : fpAggr.fpSiblings) {
			TFormalParameter tFpar = fpPair.getKey();
			RuleEnvironment G = fpPair.getValue();

			// handle: name
			final String nextName = tFpar.getName();
			if (nextName != null && !fpAggr.names.contains(nextName)) {
				fpAggr.names.add(nextName);
			}

			// handle: typeRef lists
			TypeRef typeRefSubst = ts.substTypeVariablesInTypeRef(G, tFpar.getTypeRef());
			if (typeRefSubst != null && !(typeRefSubst instanceof UnknownTypeRef)) {
				TypeRef typeRefCopy = TypeUtils.copyIfContained(typeRefSubst);
				fpAggr.typeRefs.add(typeRefCopy);
				if (tFpar.isVariadic()) {
					fpAggr.typeRefsVariadic.add(typeRefCopy);
				}
			}

			// handle: optional
			fpAggr.allOptional &= tFpar.isOptional();
			fpAggr.allNonOptional &= !tFpar.isOptional();
		}
	}

	/////////////////////////// Helper Init Methods ///////////////////////////

	private MemberType handleMemberTypes(MemberType lastMType, TMember member) {
		MemberType currMType = member.getMemberType();
		lastMType = (lastMType == null) ? currMType : lastMType;
		hasMultipleMemberTypes |= currMType != lastMType;
		hasFieldMemberType |= currMType == MemberType.FIELD;
		hasGetterMemberType |= currMType == MemberType.GETTER;
		hasSetterMemberType |= currMType == MemberType.SETTER;
		hasMethodMemberType |= currMType == MemberType.METHOD;
		hasNonMethodMemberType |= currMType != MemberType.METHOD;
		onlyMethodMemberTypes &= currMType == MemberType.METHOD;
		onlyFieldMemberTypes &= currMType == MemberType.FIELD;
		onlyGetterMemberTypes &= currMType == MemberType.GETTER;
		onlySetterMemberTypes &= currMType == MemberType.SETTER;
		lastMType = currMType;
		return lastMType;
	}

	private void handleReadOnlyField(TMember member) {
		boolean isReadOnly = false;
		if (member instanceof TField) {
			TField tField = (TField) member;
			isReadOnly = !tField.isWriteable();
		}
		hasReadOnlyField |= isReadOnly;
		onlyReadOnlyFields &= isReadOnly && (member instanceof TField);
	}

	private void handleAccessibility(TMember member) {
		MemberAccessModifier currAccessibility = member.getMemberAccessModifier();
		if (accessibilityMax.getValue() < currAccessibility.getValue())
			accessibilityMax = currAccessibility;
		if (accessibilityMin.getValue() > currAccessibility.getValue())
			accessibilityMin = currAccessibility;
	}

	private void handleTypeRefLists(TMember member, RuleEnvironment G) {
		TypeRef typeRef = TypeUtils.getMemberTypeRef(member);
		TypeRef typeRefSubst = ts.substTypeVariablesInTypeRef(G, typeRef);
		if (typeRefSubst != null && !(typeRefSubst instanceof UnknownTypeRef)) {
			TypeRef typeRefCopy = TypeUtils.copyIfContained(typeRefSubst);
			typeRefs.add(typeRefCopy);
			typeRef2G.put(typeRefCopy, G);
			if (member.getMemberType() == MemberType.METHOD) {
				if (TypeUtils.isVoid(typeRefCopy)) {
					methodTypeRefsVoid.add(typeRefCopy);
				} else {
					methodTypeRefsNonVoid.add(typeRefCopy);
				}
			}
			MemberType currMType = member.getMemberType();
			if (!typeRefsMap.containsKey(currMType)) {
				typeRefsMap.put(currMType, new LinkedList<>());
			}
			List<TypeRef> typeRefsOfMemberType = typeRefsMap.get(currMType);
			typeRefsOfMemberType.add(typeRefCopy);
		}
	}

	private void handleFParameters(TMember member, RuleEnvironment G) {
		EList<TFormalParameter> fpars = null;
		if (member instanceof TMethod) {
			TMethod method = (TMethod) member;
			fpars = method.getFpars();
		}
		if (member instanceof TSetter) {
			TSetter setter = (TSetter) member;
			fpars = new BasicEList<>();
			fpars.add(setter.getFpar());
		}
		if (fpars != null) {
			for (int i = 0; i < fpars.size(); i++) {
				TFormalParameter fpar = fpars.get(i);
				if (fParameters.size() <= i) {
					fParameters.add(new FParAggregate());
				}
				FParAggregate fpAggr = fParameters.get(i);
				Pair<TFormalParameter, RuleEnvironment> fpPair = new Pair<>(fpar, G);
				fpAggr.fpSiblings.add(fpPair);
			}
		}
	}

	private void handleIsVariadicButLastFParIsDifferent() {
		boolean result = false;
		if (!fParameters.isEmpty()) {
			FParAggregate lastFpar = fParameters.get(fParameters.size() - 1);
			List<TypeRef> variadics = lastFpar.getTypeRefsVariadicAccumulated();

			// case: the last fpar is not everywhere optional, but variadics exist
			if (!lastFpar.allOptional() && !variadics.isEmpty())
				result = true;

			// case: the last fpar has a different type than the variadics
			for (TypeRef lfpTypeRef : lastFpar.getTypeRefs()) {
				for (TypeRef variTypeRef : variadics) {
					if (variTypeRef.getDeclaredType() != lfpTypeRef.getDeclaredType()) {
						result = true;
						break;
					}
				}
			}
		}

		isVariadicButLastFParIsDifferent = result;
	}

	private void handleValidationProblems() {
		for (Pair<TMember, RuleEnvironment> pair : siblings) {
			if (pair == null)
				continue;

			TMember member = pair.getKey();
			if (member instanceof TMethod) {
				TMethod tMethod = (TMethod) member;

				for (int i = 0; i < tMethod.getFpars().size(); i++) {
					TFormalParameter currFP = tMethod.getFpars().get(i);
					if (currFP.isVariadic() && tMethod.getFpars().size() > i + 1) {
						FParAggregate currFPA = fParameters.get(i);
						currFPA.hasValidationProblem = true;
						return;
					}
				}
			}
		}
	}

	/////////////////////////// Access Methods ///////////////////////////

	/***/
	public boolean isWriteAccess() {
		return isWriteAccess;
	}

	/***/
	public N4JSTypeSystem getTypeSystem() {
		return ts;
	}

	/***/
	public Resource getResource() {
		return resource;
	}

	/***/
	public boolean isEmpty() {
		initMemberAggregate();
		return isEmpty;
	}

	/***/
	public boolean isSiblingMissing() {
		initMemberAggregate();
		return isSiblingMissing;
	}

	/***/
	public boolean hasMultipleMemberTypes() {
		initMemberAggregate();
		return hasMultipleMemberTypes;
	}

	/***/
	public boolean hasFieldMemberType() {
		initMemberAggregate();
		return hasFieldMemberType;
	}

	/***/
	public boolean hasGetterMemberType() {
		initMemberAggregate();
		return hasGetterMemberType;
	}

	/***/
	public boolean hasSetterMemberType() {
		initMemberAggregate();
		return hasSetterMemberType;
	}

	/***/
	public boolean hasMethodMemberType() {
		initMemberAggregate();
		return hasMethodMemberType;
	}

	/***/
	public boolean hasNonMethodMemberType() {
		initMemberAggregate();
		return hasNonMethodMemberType;
	}

	/***/
	public boolean onlyMethodMemberTypes() {
		initMemberAggregate();
		return onlyMethodMemberTypes;
	}

	/***/
	public boolean onlyFieldMemberTypes() {
		initMemberAggregate();
		return onlyFieldMemberTypes;
	}

	/***/
	public boolean onlyGetterMemberTypes() {
		initMemberAggregate();
		return onlyGetterMemberTypes;
	}

	/***/
	public boolean onlySetterMemberTypes() {
		initMemberAggregate();
		return onlySetterMemberTypes;
	}

	/***/
	public boolean hasReadOnlyField() {
		initMemberAggregate();
		return hasReadOnlyField;
	}

	/***/
	public boolean onlyReadOnlyFields() {
		initMemberAggregate();
		return onlyReadOnlyFields;
	}

	/***/
	public MemberAccessModifier getAccessabilityMin() {
		initMemberAggregate();
		return accessibilityMin;
	}

	/***/
	public MemberAccessModifier getAccessabilityMax() {
		initMemberAggregate();
		return accessibilityMax;
	}

	/***/
	public boolean hasValidationProblem() {
		initMemberAggregate();
		return hasValidationProblem;
	}

	/***/
	public List<TypeRef> getTypeRefsOld() {
		initMemberAggregate();
		return typeRefs;
	}

	/***/
	public List<TypeRef> getTypeRefsOfMemberType(MemberType... memberTypes) {
		initMemberAggregate();
		if (memberTypes == null)
			return Collections.emptyList();

		List<TypeRef> resultTypeRefs = new LinkedList<>();
		for (MemberType memberType : memberTypes) {
			if (typeRefsMap.containsKey(memberType)) {
				resultTypeRefs.addAll(typeRefsMap.get(memberType));
			}
		}
		return resultTypeRefs;
	}

	/***/
	public List<TypeRef> getMethodTypeRefsNonVoid() {
		initMemberAggregate();
		return methodTypeRefsNonVoid;
	}

	/***/
	public List<TypeRef> getMethodTypeRefsVoid() {
		initMemberAggregate();
		return methodTypeRefsVoid;
	}

	/***/
	public RuleEnvironment getRuleEnvironmentForTypeRef(TypeRef typeRef) {
		initMemberAggregate();
		return typeRef2G.get(typeRef);
	}

	/***/
	public boolean isVariadicButLastFParIsDifferent() {
		initMemberAggregate();
		return isVariadicButLastFParIsDifferent;
	}

	/***/
	public List<FParAggregate> getFParAggregates() {
		initMemberAggregate();
		return fParameters;
	}
}
