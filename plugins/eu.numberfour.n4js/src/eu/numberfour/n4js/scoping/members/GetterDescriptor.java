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

import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TGetter;
import eu.numberfour.n4js.ts.types.TypesFactory;
import eu.numberfour.n4js.ts.utils.TypeUtils;

/**
 *
 */
abstract class GetterDescriptor implements ComposedMemberDescriptor {
	final ComposedMemberAggregate cma;

	GetterDescriptor(ComposedMemberAggregate cma) {
		this.cma = cma;
	}

	abstract MemberAccessModifier getAccessability();

	abstract TypeRef getReturnTypeRefComposition();

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public TGetter create(String name) {
		TGetter getter = TypesFactory.eINSTANCE.createTGetter();
		TypeRef typeRef = getReturnTypeRefComposition();
		TypeUtils.setMemberTypeRef(getter, typeRef);
		getter.setName(name);
		getter.setDeclaredMemberAccessModifier(getAccessability());
		return getter;
	}

	static class IntersectionGetter extends GetterDescriptor {
		IntersectionGetter(ComposedMemberAggregate cma) {
			super(cma);
		}

		@Override
		MemberAccessModifier getAccessability() {
			return cma.getAccessabilityMax();
		}

		@Override
		TypeRef getReturnTypeRefComposition() {
			List<TypeRef> typeRefs = cma.getTypeRefsOfMemberType(MemberType.GETTER, MemberType.FIELD);
			return cma.getTypeSystem().createSimplifiedIntersection(typeRefs, cma.getResource());
		}
	}

	static class UnionGetter extends GetterDescriptor {
		UnionGetter(ComposedMemberAggregate cma) {
			super(cma);
		}

		@Override
		MemberAccessModifier getAccessability() {
			return cma.getAccessabilityMin();
		}

		@Override
		TypeRef getReturnTypeRefComposition() {
			List<TypeRef> typeRefs = cma.getTypeRefsOfMemberType(MemberType.GETTER, MemberType.FIELD);
			return cma.getTypeSystem().createSimplifiedUnion(typeRefs, cma.getResource());
		}
	}

}
