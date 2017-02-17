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
package eu.numberfour.n4js.scoping.members;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TMemberWithAccessModifier;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import it.xsemantics.runtime.RuleEnvironment;

/** TODO doc */
public class IntersectionMemberDescriptor extends ComposedMemberDescriptor {

	/**
	 * Constructor. The Resource and the N4JSTypeSystem will be used for type inference, etc. during merging of the
	 * members.
	 *
	 * @param writeAccess
	 *            <code>true</code> if the composed member is intended for write-access, <code>false</code> if it is
	 *            intended for read-access. This is only required in the case that we have only fields but those fields
	 *            are of different type, because then we will combine them into a getter or setter.
	 */
	public IntersectionMemberDescriptor(boolean writeAccess, Resource resource, N4JSTypeSystem ts) {
		super(writeAccess, resource, ts);
	}

	/**
	 * Returns a simplified intersection TypeRef.
	 */
	@Override
	protected TypeRef getCompositionForMember(N4JSTypeSystem pts, List<TypeRef> pTypeRefs, Resource pTesource) {
		return pts.createSimplifiedIntersection(pTypeRefs, pTesource);
	}

	/**
	 * Returns a simplified union TypeRef.
	 */
	@Override
	protected TypeRef getCompositionForParameter(N4JSTypeSystem pts, List<TypeRef> pTypeRefs, Resource pTesource) {
		return pts.createSimplifiedUnion(pTypeRefs, pTesource);
	}

	/**
	 * True iff all members merged via method {@link #merge(RuleEnvironment, TMember)} can be combined to a valid
	 * composed member.
	 */
	@Override
	public boolean isValid() {
		// must be non-empty and complete
		if (empty)
			return false;
		// check kind
		// (note the tweak in #mergeKind() above, so even though we require multipleKinds===false here we allow
		// combining fields with getters/setters in some cases)
		if (kind == null || multipleKinds)
			return false;
		// disallow combination of read-only field + setter
		if (isSetter(kind) && readOnlyField)
			return false;
		// check fpars
		for (int fparIdx = 0; fparIdx < fpars.size(); fparIdx++) {
			final FparDescriptor curr = fpars.get(fparIdx);
			final FparDescriptor next = fparIdx + 1 < fpars.size() ? fpars.get(fparIdx + 1) : null;
			if (curr == null
					// optional fpars must come last:
					|| (curr.optional && next != null && !(next.optional || next.variadic))
					// only last fpar may be variadic:
					|| (curr.variadic && next != null))
				return false;
		}
		return true;
	}

	@Override
	protected void mergeKind(MemberType nextKind) {
		if (nextKind != null) {
			// if this is the first call to #mergeKind() -> initialize 'kind' variable
			if (kind == null)
				kind = nextKind;
			// special tweak:
			// combining fields and accessors will yield the same result as just having the fields
			if (isAccessor(kind) && isField(nextKind))
				kind = nextKind;
			else if (isField(kind) && isAccessor(nextKind))
				nextKind = kind;
			// remember if we have encountered multiple types
			multipleKinds |= (nextKind != kind);
		}
	}

	@Override
	public TMember create(String name) {
		if (!isValid())
			return null;

		final TMember composedMember = createMemberOfKind(kind);
		composedMember.setName(name);

		if (composedMember instanceof TField) {
			((TField) composedMember).setDeclaredFinal(readOnlyField);
		}

		if (composedMember instanceof TMemberWithAccessModifier) {
			TMemberWithAccessModifier accModMem = (TMemberWithAccessModifier) composedMember;
			accModMem.setDeclaredMemberAccessModifier(accessibility);
		}

		// FIXME: the following is not correct. We need to know about the intended RW-access to create the correct
		// typeRef:
		// For writing: union, for reading: intersection!
		TypeUtils.setMemberTypeRef(composedMember, getSimplifiedCompositionOfTypeRefs());

		setFPars(composedMember);

		return composedMember;
	}

}
