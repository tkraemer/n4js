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

import static eu.numberfour.n4js.ts.types.MemberType.FIELD;
import static eu.numberfour.n4js.ts.types.MemberType.GETTER;
import static eu.numberfour.n4js.ts.types.MemberType.METHOD;
import static eu.numberfour.n4js.ts.types.MemberType.SETTER;

import java.util.Iterator;
import java.util.List;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * Interface for creating composed members.
 */
abstract public class ComposedMemberDescriptor implements ComposedMemberCreator {
	/** The data holder of aggregated siblings on which the new {@link TMember} is based upon */
	final protected ComposedMemberAggregate cma;
	/** The descriptor to create the new {@link TMember} */
	final protected ComposedMemberCreator specialMemberDescriptor;
	/** The new {@link MemberType} */
	final protected MemberType memberType;

	ComposedMemberDescriptor(ComposedMemberAggregate cma) {
		this.cma = cma;
		this.memberType = getNewMemberType();
		this.specialMemberDescriptor = (memberType != null) ? getSpecialMemberDescriptor() : null;
	}

	/** Returns the {@link MemberType} of the new composed {@link TMember}. */
	abstract protected MemberType getNewMemberType();

	/** Returns the descriptor which creates the {@link TMember}. */
	abstract protected ComposedMemberCreator getSpecialMemberDescriptor();

	/** Returns true iff there exist one or more siblings on which a new composed {@link TMember} is based upon. */
	public boolean isEmpty() {
		return cma.isEmpty();
	}

	@Override
	public TMember create(String name) {
		if (specialMemberDescriptor == null)
			return null;
		return specialMemberDescriptor.create(name);
	}

	/**
	 * Compares the return types of the new {@link TMember}.
	 * <p>
	 * Returns true iff no new composition type is created. That is, all return {@link TypeRef}s of the siblings are
	 * equal.
	 */
	protected boolean allTypeRefAreEqual() {
		N4JSTypeSystem ts = cma.getTypeSystem();
		List<TypeRef> allTypeRefs = cma.getTypeRefsOfMemberType(METHOD, FIELD, GETTER, SETTER);
		TypeRef ist = ts.createSimplifiedIntersection(allTypeRefs, cma.getResource());

		Iterator<TypeRef> typeRefIt = allTypeRefs.iterator();
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
