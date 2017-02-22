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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * Helper class to encapsulate the rules for merging several members of a union type's contained types into a single,
 * common member that can be accessed via a property access expression.
 * <p>
 * After creating an instance, members can be merged via method {@link #merge(RuleEnvironment, TMember)} and afterwards
 * method {@link #isValid()} will tell if a valid composed member can be formed and, if so, it can be created by calling
 * {@link #create(String)}.
 */
public class UnionMemberDescriptor extends ComposedMemberDescriptor {

	/**
	 * Constructor. The Resource and the N4JSTypeSystem will be used for type inference, etc. during merging of the
	 * members.
	 *
	 * @param writeAccess
	 *            <code>true</code> if the composed member is intended for write-access, <code>false</code> if it is
	 *            intended for read-access. This is only required in the case that we have only fields but those fields
	 *            are of different type, because then we will combine them into a getter or setter.
	 */
	public UnionMemberDescriptor(boolean writeAccess, Resource resource, N4JSTypeSystem ts) {
		super(writeAccess, resource, ts);
	}

	@Override
	protected TypeRef getTypeRef(N4JSTypeSystem pts, List<TypeRef> pTypeRefs, Resource pTesource) {
		return super.getSimplifiedUnion(pts, pTypeRefs, pTesource);
	}

	@Override
	protected TypeRef getTypeRefComplement(N4JSTypeSystem pts, List<TypeRef> pTypeRefs, Resource pTesource) {
		return super.getSimplifiedIntersection(pts, pTypeRefs, pTesource);
	}

	/**
	 * True iff all members merged via method {@link #merge(RuleEnvironment, TMember)} can be combined to a valid
	 * composed member.
	 */
	@Override
	public boolean isValid() {
		// must be non-empty and complete
		if (empty || oneIsMissing)
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
					|| (curr.isOptional() && next != null && !next.isOptional())
					// only last fpar may be variadic:
					|| (curr.isVariadic() && next != null))
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
			// combining fields and accessors will yield the same result as just having the accessors
			if (isField(kind) && isAccessor(nextKind))
				kind = nextKind;
			else if (isAccessor(kind) && isField(nextKind))
				nextKind = kind;
			// remember if we have encountered multiple types
			multipleKinds |= (nextKind != kind);
		}
	}

	@Override
	protected void mergeAccessibility(MemberAccessModifier nextAccessibility) {
		if (accessibility == null) {
			accessibility = MemberAccessModifier.PUBLIC; // initialize variable
		}
		if (nextAccessibility != null) {
			// remember the minimum accessibility we have encountered
			if (nextAccessibility.getValue() < accessibility.getValue())
				accessibility = nextAccessibility;
		}
	}

	@Override
	protected void checkAndHandleVoidTypeRefs() {
		List<TypeRef> nonVoids = new LinkedList<>();
		for (TypeRef tr : typeRefs) {
			if (!TypeUtils.isVoid(tr))
				nonVoids.add(tr);
		}
		if (typeRefs.size() > nonVoids.size()) {
			// delete non-voids only iff void type exists
			for (TypeRef vds : nonVoids) {
				typeRefs.remove(vds);
			}
		}
	}

	@Override
	protected MemberType getActualKind(MemberType memberType, boolean writeAccessFlag) {
		MemberType actualKind = memberType;
		// turn fields into a getter or setter (depending on read or write-access) *if* they have different types
		if (isField(memberType)) {
			final TypeRef compo = getSimplifiedCompositionOfTypeRefs();
			// if the simplified union is a union type, the types cannot be all equal!
			if (compo != null && compo instanceof ComposedTypeRef) {
				if (writeAccess) {
					actualKind = MemberType.SETTER;
				} else {
					actualKind = MemberType.GETTER;
				}
			}
		}
		return actualKind;
	}
}
