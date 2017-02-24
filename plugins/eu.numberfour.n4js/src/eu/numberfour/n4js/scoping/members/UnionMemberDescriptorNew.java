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

import java.util.Iterator;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import it.xsemantics.runtime.RuleEnvironment;

/**
 *
 */
public class UnionMemberDescriptorNew implements ComposedMemberDescriptorNew {
	final private ComposedMemberAggregate cma;
	final ComposedMemberDescriptorNew specialMemberDescriptor;
	final MemberType memberType;

	UnionMemberDescriptorNew(ComposedMemberAggregate cma) {
		this.cma = cma;
		this.memberType = getNewMemberType();
		this.specialMemberDescriptor = (memberType != null) ? getSpecialMemberDescriptor() : null;
	}

	private ComposedMemberDescriptorNew getSpecialMemberDescriptor() {
		switch (memberType) {
		case METHOD:
			return new MethodDescriptor.UnionMethod(cma);
		case FIELD:
			return new FieldDescriptor.UnionField(cma);
		case GETTER:
			return new GetterDescriptor.UnionGetter(cma);
		case SETTER:
			return new SetterDescriptor.UnionSetter(cma);
		}
		return null;
	}

	private MemberType getNewMemberType() {
		// mix of all memberTypes
		if (cma.hasMethodMemberType() && cma.hasNonMethodMemberType()) {
			return null; // inValid
		}
		if (cma.onlyMethodMemberTypes()) {
			return MemberType.METHOD;
		}
		// mix of all non-method memberTypes
		if (cma.onlyGetterMemberTypes()) {
			return MemberType.GETTER;
		}
		if (cma.onlySetterMemberTypes()) {
			return MemberType.SETTER;
		}
		if (cma.onlyFieldMemberTypes()) {
			if (allTypeRefAreEqual()) {
				return MemberType.FIELD;
			} else {
				if (cma.isWriteAccess()) {
					return MemberType.SETTER;
				} else {
					return MemberType.GETTER;
				}
			}
		}
		// mix of all non-method memberTypes
		if (!cma.hasGetterMemberType()) {
			return MemberType.SETTER;
		}
		if (!cma.hasSetterMemberType()) {
			return MemberType.GETTER;
		}
		return null; // inValid
	}

	@Override
	public boolean isEmpty() {
		return cma.isEmpty();
	}

	@Override
	public boolean isValid() {
		if (cma.isSiblingMissing())
			return false;
		if (specialMemberDescriptor == null)
			return false;
		return specialMemberDescriptor.isValid();
	}

	@Override
	public TMember create(String name) {
		if (specialMemberDescriptor == null)
			return null;
		return specialMemberDescriptor.create(name);
	}

	private boolean allTypeRefAreEqual() {
		N4JSTypeSystem ts = cma.getTypeSystem();
		TypeRef ist = ts.createSimplifiedUnion(cma.getTypeRefs(), cma.getResource());

		Iterator<TypeRef> typeRefIt = cma.getTypeRefs().iterator();
		while (typeRefIt.hasNext()) {
			TypeRef firstNonNullTypeRef = typeRefIt.next();
			if (firstNonNullTypeRef != null) {
				RuleEnvironment G = cma.getRuleEnvironmentForTypeRef(firstNonNullTypeRef);
				boolean equalTypeRefs = ts.equaltypeSucceeded(G, firstNonNullTypeRef, ist);
				return equalTypeRefs;
			}
		}
		return true;
	}

}
