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
public class IntersectionMemberDescriptorNew implements ComposedMemberDescriptorNew {
	final private ComposedMemberAggregate cma;
	final ComposedMemberDescriptorNew specialMemberDescriptor;
	final MemberType memberType;

	IntersectionMemberDescriptorNew(ComposedMemberAggregate cma) {
		this.cma = cma;
		this.memberType = getNewMemberType();
		this.specialMemberDescriptor = (memberType != null) ? getSpecialMemberDescriptor() : null;
	}

	private ComposedMemberDescriptorNew getSpecialMemberDescriptor() {
		switch (memberType) {
		case METHOD:
			return new MethodDescriptor.IntersectionMethod(cma);
		case FIELD:
			return new FieldDescriptor.IntersectionField(cma);
		case GETTER:
			return new GetterDescriptor.IntersectionGetter(cma);
		case SETTER:
			return new SetterDescriptor.IntersectionSetter(cma);
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
		if (allTypeRefAreEqual()) {
			return MemberType.FIELD;
		}
		// mix of all non-method memberTypes AND different return types
		if (cma.isWriteAccess()) {
			if (!cma.hasGetterMemberType()) {
				// return MemberType.FIELD;
			}
			return MemberType.SETTER;
		}
		if (!cma.isWriteAccess()) {
			if (!cma.hasSetterMemberType()) {
				// return MemberType.FIELD;
			}
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
		TypeRef ist = ts.createSimplifiedIntersection(cma.getTypeRefs(), cma.getResource());

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
