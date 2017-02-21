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
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.base.Joiner;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef;
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
abstract public class ComposedMemberDescriptor {

	final boolean writeAccess;
	final Resource resource;
	final N4JSTypeSystem ts;

	// the following fields will contain the characteristics of the combined member
	// and will be updated in the #mergeXYZ() methods below
	boolean empty = true;
	boolean oneIsMissing;
	MemberType kind;
	boolean multipleKinds;
	boolean readOnlyField;
	MemberAccessModifier accessibility;
	/** used for type of fields and return type of getters and methods */
	final List<TypeRef> typeRefs = new ArrayList<>();
	final List<FparDescriptor> fpars = new ArrayList<>();

	// ---

	// the simplified union of the types in field 'typeRefs' (see above) is required at various points, so we cache this
	private TypeRef cachedSimplifiedComposition;

	class FparDescriptor {
		final List<String> names = new ArrayList<>();
		@SuppressWarnings("hiding")
		final List<TypeRef> typeRefs = new ArrayList<>();
		final List<TypeRef> typeRefsVariadic = new ArrayList<>();
		boolean allOptional = true;

		boolean isOptional() {
			return allOptional && !isVariadic();
		}

		boolean isVariadic() {
			int idx = fpars.indexOf(this);
			if (idx != fpars.size() - 1) // not last? => not variadic!
				return false;

			for (int i = 0; i <= idx; i++) {
				FparDescriptor fparD = fpars.get(i);
				if (!fparD.typeRefsVariadic.isEmpty())
					return true;
			}
			return false;
		}

		String getName() {
			return Joiner.on("_").join(this.names);
		}

		List<TypeRef> getAllTypeRefsVariadic() {
			return getAllTypeRefsVariadic(fpars.size() - 1);
		}

		List<TypeRef> getAllTypeRefsVariadic(int idx) {
			List<TypeRef> variadics = new LinkedList<>();
			for (int i = 0; i <= idx; i++) {
				FparDescriptor fparD = fpars.get(i);
				variadics.addAll(fparD.typeRefsVariadic);
			}
			return variadics;
		}

		public TFormalParameter create() {
			int idx = fpars.indexOf(this);
			List<TypeRef> variadics = getAllTypeRefsVariadic(idx);
			FparDescriptor fparDescr = fpars.get(idx);
			TFormalParameter tfp = fparDescr.createAddVariadicTypes(variadics);
			return tfp;
		}

		private TFormalParameter createAddVariadicTypes(List<TypeRef> variadics) {
			final TFormalParameter fpar = TypesFactory.eINSTANCE.createTFormalParameter();
			fpar.setName(getName());
			List<TypeRef> typeRefsToUse = this.typeRefs;
			// if we are creating a setter, include the main typeRefs
			// (required to include types of fields in case of a field/setter combination)
			if (isSetter(kind)) {
				typeRefsToUse = new ArrayList<>(this.typeRefs);
				typeRefsToUse.addAll(ComposedMemberDescriptor.this.typeRefs);
			}
			typeRefsToUse.addAll(variadics);
			TypeRef paramCompTR = getTypeRefComplement(ts, typeRefsToUse, ComposedMemberDescriptor.this.resource);
			fpar.setTypeRef(paramCompTR);
			fpar.setVariadic(isVariadic());
			fpar.setHasInitializerAssignment(isOptional());
			return fpar;
		}

	}

	/**
	 * Merges the kind of a new member to the current kind
	 */
	abstract void mergeKind(MemberType nextKind);

	/**
	 * Updates the following attributes of {@code desc} with respect to the next formal parameter in the scope:
	 * {@code optional}, {@code variadic}, {@code hasInitializerAssignment}.
	 */
	abstract protected void mergeFparBooleans(TFormalParameter nextFpar, final FparDescriptor desc);

	/**
	 * True iff all members merged via method {@link #merge(RuleEnvironment, TMember)} can be combined to a valid
	 * composed member.
	 */
	abstract public boolean isValid();

	/**
	 * Returns the member TypeRef.
	 */
	abstract protected TypeRef getTypeRef(N4JSTypeSystem pts, List<TypeRef> pTypeRefs, Resource pTesource);

	/**
	 * Returns the member TypeRef.
	 */
	abstract protected TypeRef getTypeRefComplement(N4JSTypeSystem pts, List<TypeRef> pTypeRefs, Resource pTesource);

	/**
	 * Merges the accessibility of the composed member.
	 */
	abstract protected void mergeAccessibility(MemberAccessModifier nextAccessibility);

	/**
	 * Returns a simplified union TypeRef.
	 */
	protected TypeRef getSimplifiedUnion(N4JSTypeSystem pts, List<TypeRef> pTypeRefs, Resource pTesource) {
		return pts.createSimplifiedUnion(pTypeRefs, pTesource);
	}

	/**
	 * Returns a simplified union TypeRef.
	 */
	protected TypeRef getSimplifiedIntersection(N4JSTypeSystem pts, List<TypeRef> pTypeRefs, Resource pTesource) {
		return pts.createSimplifiedIntersection(pTypeRefs, pTesource);
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
	public ComposedMemberDescriptor(boolean writeAccess, Resource resource, N4JSTypeSystem ts) {
		this.writeAccess = writeAccess;
		this.ts = ts;
		this.resource = resource;
	}

	TypeRef getSimplifiedCompositionOfTypeRefs() {
		if (cachedSimplifiedComposition == null) {
			cachedSimplifiedComposition = getTypeRef(ts, typeRefs, resource);
		}
		return cachedSimplifiedComposition;
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
		} else
			oneIsMissing = true;
	}

	private void mergeReadOnlyField(boolean nextReadOnlyField) {
		// remember if we have encountered at least one const field
		readOnlyField |= nextReadOnlyField;
	}

	private void mergeTypeRef(TypeRef nextTypeRef) {
		if (nextTypeRef != null && !(nextTypeRef instanceof UnknownTypeRef)) {
			// remember ALL types we have encountered (so we do not actually merge anything here)
			typeRefs.add(TypeUtils.copyIfContained(nextTypeRef));
		}
	}

	private void mergeFpar(RuleEnvironment G, int fparIdx, TFormalParameter nextFpar) {
		if (nextFpar != null) {
			// init: Make sure we have an entry in 'fpars' for a formal parameter at index 'fparIdx'
			while (fpars.size() <= fparIdx) {
				fpars.add(null);
			}
			if (fpars.get(fparIdx) == null) {
				fpars.set(fparIdx, new FparDescriptor());
			}

			// merge properties of formal parameter 'nextFpar' into that entry
			final FparDescriptor desc = fpars.get(fparIdx);
			final String nextName = nextFpar.getName();
			if (nextName != null && !desc.names.contains(nextName)) {
				desc.names.add(nextName); // collect all fpar names (without duplicates)
			}
			if (nextFpar.getTypeRef() != null) {
				final TypeRef nextFparTypeRef = ts.substTypeVariablesInTypeRef(G, nextFpar.getTypeRef());
				if (nextFparTypeRef != null && !(nextFparTypeRef instanceof UnknownTypeRef)) {
					TypeRef typeRef = TypeUtils.copyIfContained(nextFparTypeRef);
					desc.typeRefs.add(typeRef); // collect all fpar types
					if (nextFpar.isVariadic()) {
						desc.typeRefsVariadic.add(typeRef);
					}
				}
			}
			mergeFparBooleans(nextFpar, desc);
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
	 * Creates the composed members. Returns <code>null</code> if the members merged via method
	 * {@link #merge(RuleEnvironment, TMember)} do not form a valid composed member (i.e. if method {@link #isValid()}
	 * returns false).
	 */
	public TMember create(String name) {
		if (!isValid())
			return null;

		MemberType actualKind = getActualKind(kind, writeAccess);

		final TMember composedMember = createMemberOfKind(actualKind);
		composedMember.setName(name);

		if (composedMember instanceof TField) {
			((TField) composedMember).setDeclaredFinal(readOnlyField);
		}

		if (composedMember instanceof TMemberWithAccessModifier) {
			TMemberWithAccessModifier accModMem = (TMemberWithAccessModifier) composedMember;
			accModMem.setDeclaredMemberAccessModifier(accessibility);
		}

		if (composedMember instanceof TField)
			TypeUtils.setMemberTypeRef(composedMember, typeRefs.get(0));
		else if (composedMember instanceof TGetter || composedMember instanceof TMethod)
			TypeUtils.setMemberTypeRef(composedMember, getSimplifiedCompositionOfTypeRefs());

		if (composedMember instanceof TSetter) {
			final FparDescriptor fpar = new FparDescriptor();
			fpar.names.add("arg0");
			fpar.typeRefs.addAll(typeRefs);
			TSetter tSetter = (TSetter) composedMember;
			fpars.add(fpar);
			tSetter.setFpar(fpar.create());
		} else if (composedMember instanceof TMethod) {
			if (!fpars.isEmpty()) {
				boolean variFparNecessary = isExtraVariadicFParNecessary();
				if (variFparNecessary) {
					final FparDescriptor varpar = new FparDescriptor();
					varpar.names.add("vari");
					fpars.add(varpar);
				}
			}
			for (FparDescriptor currFparDesc : fpars) {
				TMethod tMethod = (TMethod) composedMember;
				TFormalParameter tFPar = currFparDesc.create();
				tMethod.getFpars().add(tFPar);
			}
		}

		return composedMember;
	}

	private boolean isExtraVariadicFParNecessary() {
		FparDescriptor lastFpar = fpars.get(fpars.size() - 1);
		List<TypeRef> variadics = lastFpar.getAllTypeRefsVariadic();

		// case: the last fpar is not everywhere optional, but variadics exist
		if (!lastFpar.allOptional && !variadics.isEmpty())
			return true;

		// case: the last fpar has a different type than the variadics
		for (TypeRef lfpTypeRef : lastFpar.typeRefs) {
			for (TypeRef variTypeRef : variadics) {
				if (variTypeRef.getDeclaredType() != lfpTypeRef.getDeclaredType())
					return true;
			}
		}

		return false;
	}

	/**
	 * TODO: Calculate the actual kind from the current kind based on the writeAccess.
	 *
	 */
	protected abstract MemberType getActualKind(MemberType memberType, boolean writeAccessFlag);

	static boolean isField(MemberType kind) {
		return kind == MemberType.FIELD;
	}

	static boolean isSetter(MemberType kind) {
		return kind == MemberType.SETTER;
	}

	static boolean isAccessor(MemberType kind) {
		return kind == MemberType.GETTER || kind == MemberType.SETTER;
	}

	static TMember createMemberOfKind(MemberType kind) {
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
