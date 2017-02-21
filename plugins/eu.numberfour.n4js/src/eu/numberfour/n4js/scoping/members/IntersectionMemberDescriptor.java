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
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TFormalParameter;
import eu.numberfour.n4js.ts.types.TMember;
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

	@Override
	protected TypeRef getTypeRef(N4JSTypeSystem pts, List<TypeRef> pTypeRefs, Resource pTesource) {
		return super.getSimplifiedIntersection(pts, pTypeRefs, pTesource);
	}

	@Override
	protected TypeRef getTypeRefComplement(N4JSTypeSystem pts, List<TypeRef> pTypeRefs, Resource pTesource) {
		return super.getSimplifiedUnion(pts, pTypeRefs, pTesource);
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

			if (!isValidFpar(curr, next))
				return false;
		}
		return true;
	}

	private boolean isValidFpar(final FparDescriptor curr, final FparDescriptor next) {
		if (curr == null)
			return false;
		if (curr.isVariadic() && next != null)
			return false;

		// both are not null:
		// if (curr.variadic)
		// return false;
		// if (curr.isOptional() && !next.isOptional()) // not necessary: is handled by Tyoes.xcore#isOptional
		// return false;

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
	protected void mergeAccessibility(MemberAccessModifier nextAccessibility) {
		if (accessibility == null) {
			accessibility = MemberAccessModifier.PRIVATE; // initialize variable
		}
		if (nextAccessibility != null) {
			// remember the maximum accessibility we have encountered
			if (nextAccessibility.getValue() > accessibility.getValue())
				accessibility = nextAccessibility;
		}
	}

	@Override
	protected void mergeFparBooleans(TFormalParameter nextFpar, final FparDescriptor desc) {
		// remember if ANY had an initializer assignment
		desc.allOptional &= nextFpar.isOptional();
	}

	@Override
	protected MemberType getActualKind(MemberType memberType, boolean writeAccessFlag) {
		MemberType actualKind = kind;
		// turn fields into a getter or setter
		if (isField(memberType)) {
			if (writeAccessFlag) {
				actualKind = MemberType.SETTER;
			} else {
				actualKind = MemberType.GETTER;
			}
		}
		return actualKind;
	}
}
