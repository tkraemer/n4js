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

import java.util.List;

import eu.numberfour.n4js.scoping.members.MethodDescriptor.FParDescriptorCreator;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.TSetter;
import eu.numberfour.n4js.ts.types.TypesFactory;

/**
 *
 */
abstract class SetterDescriptor implements ComposedMemberDescriptorNew {
	final ComposedMemberAggregate cma;

	SetterDescriptor(ComposedMemberAggregate cma) {
		this.cma = cma;
	}

	abstract MemberAccessModifier getAccessability();

	abstract TypeRef getReturnTypeRefComposition();

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public TSetter create(String name) {
		TSetter setter = TypesFactory.eINSTANCE.createTSetter();
		setter.setName(name);
		setter.setDeclaredMemberAccessModifier(getAccessability());
		StandaloneFPar fpar = new StandaloneFPar("arg0", cma.getTypeRefs());
		setter.setFpar(fpar.create());
		return setter;
	}

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
			return cma.getTypeSystem().createSimplifiedUnion(cma.getTypeRefs(), cma.getResource());
		}
	}

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
			return cma.getTypeSystem().createSimplifiedIntersection(cma.getTypeRefs(), cma.getResource());
		}

	}

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
