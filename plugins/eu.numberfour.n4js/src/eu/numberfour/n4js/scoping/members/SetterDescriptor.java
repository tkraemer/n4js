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

import java.util.LinkedList;
import java.util.List;

import eu.numberfour.n4js.scoping.members.ComposedMemberAggregate.FParAggregate;
import eu.numberfour.n4js.scoping.members.MethodDescriptor.FParDescriptorCreator;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TSetter;
import eu.numberfour.n4js.ts.types.TypesFactory;

/**
 * The abstract {@link SetterDescriptor} is the base class for the child classes {@link UnionSetter} and
 * {@link IntersectionSetter}. It implements the method {@link #create(String)} which gets its information through
 * abstract methods implemented in the child classes mentioned before The child classes are instantiated in
 * {@link IntersectionMemberDescriptor} and {@link UnionMemberDescriptor} respectively.
 * <p>
 * This class also defines the class {@link StandaloneFPar} which is based upon the class {@link FParDescriptorCreator}
 * and reuses its method {@link FParDescriptorCreator#create()}.
 */
abstract class SetterDescriptor implements ComposedMemberCreator {
	final ComposedMemberAggregate cma;
	final StandaloneFPar fpar;

	SetterDescriptor(ComposedMemberAggregate cma) {
		this.cma = cma;
		String name = "arg0";
		List<TypeRef> typeRefs = new LinkedList<>();
		List<FParAggregate> fpars = cma.getFParAggregates();
		if (fpars != null && !fpars.isEmpty()) {
			FParAggregate firstFpar = fpars.get(0);
			name = firstFpar.getName();
			typeRefs.addAll(firstFpar.getTypeRefs());
		}
		this.fpar = new StandaloneFPar(name, typeRefs);
	}

	abstract MemberAccessModifier getAccessability();

	abstract TypeRef getReturnTypeRefComposition();

	@Override
	public TSetter create(String name) {
		TSetter setter = TypesFactory.eINSTANCE.createTSetter();
		setter.setName(name);
		setter.setDeclaredMemberAccessModifier(getAccessability());
		setter.setFpar(fpar.create());
		return setter;
	}

	/** Class to implement logic with regard to setters in {@code Intersection Types}. */
	static class IntersectionSetter extends SetterDescriptor {
		IntersectionSetter(ComposedMemberAggregate cma) {
			super(cma);
		}

		@Override
		public boolean isValid() {
			return !cma.onlyReadOnlyFields();
		}

		@Override
		MemberAccessModifier getAccessability() {
			return cma.getAccessabilityMax();
		}

		@Override
		TypeRef getReturnTypeRefComposition() {
			List<TypeRef> typeRefs = cma.getTypeRefsOfMemberType(MemberType.SETTER, MemberType.FIELD);
			return cma.getTypeSystem().createSimplifiedUnion(typeRefs, cma.getResource());
		}
	}

	/** Class to implement logic with regard to setters in {@code Union Types}. */
	static class UnionSetter extends SetterDescriptor {
		UnionSetter(ComposedMemberAggregate cma) {
			super(cma);
		}

		@Override
		public boolean isValid() {
			return !cma.hasReadOnlyField();
		}

		@Override
		MemberAccessModifier getAccessability() {
			return cma.getAccessabilityMin();
		}

		@Override
		TypeRef getReturnTypeRefComposition() {
			List<TypeRef> typeRefs = cma.getTypeRefsOfMemberType(MemberType.SETTER, MemberType.FIELD);
			return cma.getTypeSystem().createSimplifiedIntersection(typeRefs, cma.getResource());
		}

	}

	/** Class to create formal parameters of setters. */
	class StandaloneFPar extends FParDescriptorCreator {
		final String name;
		final List<TypeRef> typeRefs;

		StandaloneFPar(String name, List<TypeRef> typeRefs) {
			this.name = name;
			this.typeRefs = typeRefs;
		}

		@Override
		String getName() {
			return name;
		}

		@Override
		List<TypeRef> getTypeRefs() {
			return typeRefs;
		}

		@Override
		TypeRef getFParTypeRefComposition(List<TypeRef> typeRefsToUse) {
			return getReturnTypeRefComposition();
		}
	}
}
