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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.base.Joiner;

import eu.numberfour.n4js.ts.typeRefs.IntersectionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TFormalParameter;
import eu.numberfour.n4js.ts.types.TGetter;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TMemberWithAccessModifier;
import eu.numberfour.n4js.ts.types.TMethod;
import eu.numberfour.n4js.ts.types.TSetter;
import eu.numberfour.n4js.ts.types.TypesFactory;
import eu.numberfour.n4js.ts.types.UndefModifier;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import it.xsemantics.runtime.RuleEnvironment;

/** TODO doc */
public class IntersectionMemberDescriptor {

	private final boolean writeAccess;
	private final Resource resource;
	private final N4JSTypeSystem ts;

	// the following fields will contain the characteristics of the combined member
	// and will be updated in the #mergeXYZ() methods below
	private boolean empty = true;
	private MemberType kind;
	private boolean multipleKinds;
	private boolean readOnlyField;
	private MemberAccessModifier accessibility = MemberAccessModifier.PUBLIC;
	/** used for type of fields and return type of getters and methods */
	private final List<TypeRef> typeRefs = new ArrayList<>();
	private final List<FparDescriptor> fpars = new ArrayList<>();

	// ---

	// the simplified union of the types in field 'typeRefs' (see above) is required at various points, so we cache this
	private TypeRef cachedSimplifiedIntersectionOfTypeRefs;

	private class FparDescriptor {
		private final List<String> names = new ArrayList<>();
		@SuppressWarnings("hiding")
		private final List<TypeRef> typeRefs = new ArrayList<>();
		private boolean optional = true;
		private boolean variadic = true;
		// astInitializer is not merged
		private boolean hasInitializerAssignment = true;

		public TFormalParameter create() {
			final TFormalParameter fpar = TypesFactory.eINSTANCE.createTFormalParameter();
			fpar.setName(Joiner.on("_").join(this.names));
			List<TypeRef> typeRefsToUse = this.typeRefs;
			// if we are creating a setter, include the main typeRefs
			// (required to include types of fields in case of a field/setter combination)
			if (isSetter(kind)) {
				typeRefsToUse = new ArrayList<>(this.typeRefs);
				typeRefsToUse.addAll(IntersectionMemberDescriptor.this.typeRefs);
			}
			fpar.setTypeRef(ts.createSimplifiedIntersection(typeRefsToUse,
					IntersectionMemberDescriptor.this.resource));
			if (this.optional && null != fpar.getTypeRef())
				fpar.getTypeRef().setUndefModifier(UndefModifier.OPTIONAL);
			fpar.setVariadic(this.variadic);
			fpar.setHasInitializerAssignment(this.hasInitializerAssignment);
			return fpar;
		}
	}

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
		this.writeAccess = writeAccess;
		this.ts = ts;
		this.resource = resource;
	}

	private TypeRef getSimplifiedIntersectionOfTypeRefs() {
		if (cachedSimplifiedIntersectionOfTypeRefs == null)
			cachedSimplifiedIntersectionOfTypeRefs = ts.createSimplifiedIntersection(typeRefs, resource);
		return cachedSimplifiedIntersectionOfTypeRefs;
	}

	/**
	 * Merges the next member by updating the internal state to reflect the characteristics of the composed member. This
	 * method may be invoked with <code>null</code> as the second argument to signify "missing members", i.e. member not
	 * present in one of the types contained in the union type. If second argument is <code>null</code>, the first
	 * argument will be ignored and {@link #isValid()} will return true.
	 * <p>
	 * IMPORTANT: the implementation of this class assumes that only members are added for either read- or write access;
	 * this means if a single type contained in the union contains both a getter and setter, only either the getter or
	 * setter should be passed to this method, depending on access method.
	 */
	public void merge(RuleEnvironment G, TMember nextMember) {
		if (nextMember != null) {
			empty = false;
			mergeKind(nextMember.getMemberType());
			mergeReadOnlyField(nextMember instanceof TField ? !((TField) nextMember).isWriteable() : false);
			// mergeConst(nextMember instanceof TField ? ((TField) nextMember).isConst() : false);
			mergeAccessibility(nextMember.getMemberAccessModifier());
			final TypeRef nextMemberTypeRef = TypeUtils.getMemberTypeRef(nextMember);
			if (nextMemberTypeRef != null) {
				mergeTypeRef(ts.substTypeVariablesInTypeRef(G, nextMemberTypeRef));
			}
			if (nextMember instanceof TSetter) {
				mergeFpar(G, 0, ((TSetter) nextMember).getFpar());
			} else if (nextMember instanceof TMethod) {
				final List<TFormalParameter> nextFpars = ((TMethod) nextMember).getFpars();
				for (int fparIdx = 0; fparIdx < nextFpars.size(); fparIdx++)
					mergeFpar(G, fparIdx, nextFpars.get(fparIdx));
			}
		}
	}

	private void mergeKind(MemberType nextKind) {
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

	private void mergeReadOnlyField(boolean nextReadOnlyField) {
		// remember if we have encountered at least one const field
		readOnlyField |= nextReadOnlyField;
	}

	private void mergeAccessibility(MemberAccessModifier nextAccessibility) {
		if (nextAccessibility != null) {
			// remember the minimum accessibility we have encountered
			if (nextAccessibility.getValue() < accessibility.getValue())
				accessibility = nextAccessibility;
		}
	}

	private void mergeTypeRef(TypeRef nextTypeRef) {
		if (nextTypeRef != null) {
			// remember ALL types we have encountered (so we do not actually merge anything here)
			typeRefs.add(TypeUtils.copyIfContained(nextTypeRef));
		}
	}

	private void mergeFpar(RuleEnvironment G, int fparIdx, TFormalParameter nextFpar) {
		if (nextFpar != null) {
			// make sure we have an entry in 'fpars' for a formal parameter at index 'fparIdx'
			while (fpars.size() <= fparIdx)
				fpars.add(null);
			if (fpars.get(fparIdx) == null)
				fpars.set(fparIdx, new FparDescriptor());
			// merge properties of formal parameter 'nextFpar' into that entry
			final FparDescriptor desc = fpars.get(fparIdx);
			final String nextName = nextFpar.getName();
			if (nextName != null && !desc.names.contains(nextName))
				desc.names.add(nextName); // collect all fpar names (without duplicates)
			if (nextFpar.getTypeRef() != null) {
				final TypeRef nextFparTypeRef = ts.substTypeVariablesInTypeRef(G, nextFpar.getTypeRef());
				desc.typeRefs.add(TypeUtils.copyIfContained(nextFparTypeRef)); // collect all fpar types
			}
			desc.optional &= nextFpar.isOptional(); // remember if ALL were optional
			desc.variadic &= nextFpar.isVariadic(); // remember if ALL were variadic
			desc.hasInitializerAssignment &= nextFpar.isHasInitializerAssignment(); // remember if ALL had an
																					// initializer assignment
		}
	}

	/**
	 * True iff method {@link #merge(RuleEnvironment, TMember)} has never be invoked with a non-null value as second
	 * argument.
	 */
	public boolean isEmpty() {
		return empty;
	}

	/**
	 * True iff all members merged via method {@link #merge(RuleEnvironment, TMember)} can be combined to a valid
	 * composed member.
	 */
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

	/**
	 * Creates the composed members. Returns <code>null</code> if the members merged via method
	 * {@link #merge(RuleEnvironment, TMember)} do not form a valid composed member (i.e. if method {@link #isValid()}
	 * returns false).
	 */
	public TMember create(String name) {
		if (!isValid())
			return null;

		MemberType actualKind = kind;
		// turn fields into a getter or setter (depending on read or write-access) *if* they have different types
		if (isField(kind)) {
			final TypeRef intersection = getSimplifiedIntersectionOfTypeRefs();
			// if the simplified intersection is an intersection type, the types cannot be all equal!
			if (intersection != null && intersection instanceof IntersectionTypeExpression) {
				if (writeAccess) {
					actualKind = MemberType.SETTER;
					final FparDescriptor fpar = new FparDescriptor();
					fpar.names.add("arg0");
					fpar.typeRefs.addAll(typeRefs);
					fpars.add(fpar);
				} else {
					actualKind = MemberType.GETTER;
				}
			}
		}

		final TMember intersectiondMember = createMemberOfKind(actualKind);

		intersectiondMember.setName(name);

		if (intersectiondMember instanceof TField)
			((TField) intersectiondMember).setDeclaredFinal(readOnlyField);

		if (intersectiondMember instanceof TMemberWithAccessModifier)
			((TMemberWithAccessModifier) intersectiondMember).setDeclaredMemberAccessModifier(accessibility);

		if (intersectiondMember instanceof TField)
			TypeUtils.setMemberTypeRef(intersectiondMember, typeRefs.get(0));
		else if (intersectiondMember instanceof TGetter || intersectiondMember instanceof TMethod)
			TypeUtils.setMemberTypeRef(intersectiondMember, getSimplifiedIntersectionOfTypeRefs());

		if (intersectiondMember instanceof TSetter) {
			if (!fpars.isEmpty())
				((TSetter) intersectiondMember).setFpar(fpars.get(0).create());
		} else if (intersectiondMember instanceof TMethod) {
			for (FparDescriptor currFparDesc : fpars)
				((TMethod) intersectiondMember).getFpars().add(currFparDesc.create());
		}

		return intersectiondMember;
	}

	private static boolean isField(MemberType kind) {
		return kind == MemberType.FIELD;
	}

	private static boolean isSetter(MemberType kind) {
		return kind == MemberType.SETTER;
	}

	private static boolean isAccessor(MemberType kind) {
		return kind == MemberType.GETTER || kind == MemberType.SETTER;
	}

	private static TMember createMemberOfKind(MemberType kind) {
		switch (kind) {
		case FIELD:
			return TypesFactory.eINSTANCE.createTField();
		case GETTER:
			return TypesFactory.eINSTANCE.createTGetter();
		case SETTER:
			return TypesFactory.eINSTANCE.createTSetter();
		case METHOD:
			return TypesFactory.eINSTANCE.createTMethod();
		default:
			throw new IllegalArgumentException("unknown MemberType: " + kind);
		}
	}
}
