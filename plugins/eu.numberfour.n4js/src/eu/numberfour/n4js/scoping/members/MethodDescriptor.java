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

import java.util.LinkedList;
import java.util.List;

import eu.numberfour.n4js.scoping.members.ComposedMemberAggregate.FParAggregate;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.TFormalParameter;
import eu.numberfour.n4js.ts.types.TMethod;
import eu.numberfour.n4js.ts.types.TypesFactory;
import eu.numberfour.n4js.ts.utils.TypeUtils;

/**
 * The abstract {@link MethodDescriptor} is the base class for the child classes {@link UnionMethod} and
 * {@link IntersectionMethod}. It implements the method {@link #create(String)} which gets its information through
 * abstract methods implemented in the child classes mentioned before The child classes are instantiated in
 * {@link IntersectionMemberDescriptor} and {@link UnionMemberDescriptor} respectively.
 * <p>
 * Additionally, the base class {@link FParDescriptorCreator} for formal parameters including its method
 * {@link FParDescriptorCreator#create()} is defined here. This class is used here to eventually create formal
 * parameters of methods, and moreover it is used in {@link SetterDescriptor} to create the formal parameter of setters.
 */
abstract class MethodDescriptor implements ComposedMemberDescriptor {
	final ComposedMemberAggregate cma;
	final List<FParDescriptor> fpas = new LinkedList<>();

	MethodDescriptor(ComposedMemberAggregate cma) {
		this.cma = cma;
		List<FParAggregate> fpars = cma.getFParAggregates();
		for (int fparIdx = 0; fparIdx < fpars.size(); fparIdx++) {
			final FParAggregate curr = fpars.get(fparIdx);
			fpas.add(getFParDescriptor(curr));
		}
	}

	abstract MemberAccessModifier getAccessability();

	abstract FParDescriptor getFParDescriptor(FParAggregate fpa);

	abstract TypeRef getReturnTypeRefComposition();

	abstract TypeRef getFParTypeRefComposition(List<TypeRef> typeRefsToUse);

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isValid() {
		if (cma.isEmpty())
			return false;

		for (FParDescriptor fpa : fpas) {
			if (!fpa.isValid()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public TMethod create(String name) {
		TMethod method = TypesFactory.eINSTANCE.createTMethod();
		method.setDeclaredMemberAccessModifier(getAccessability());
		method.setName(name);
		TypeUtils.setMemberTypeRef(method, getReturnTypeRefComposition());
		if (!fpas.isEmpty()) {
			boolean variFparNecessary = cma.isVariadicButLastFParIsDifferent();
			if (variFparNecessary) {
				List<FParAggregate> fpAggrs = cma.getFParAggregates();
				FParAggregate lastFPAggr = fpAggrs.get(cma.getFParAggregates().size() - 1);
				List<TypeRef> variadicTypeRefs = lastFPAggr.getTypeRefsVariadicAccumulated();
				FParDescriptor varpar = new NewLastVariadicFPar(variadicTypeRefs);
				fpas.add(varpar);
			}
		}
		for (FParDescriptor currFparDesc : fpas) {
			TFormalParameter tFPar = currFparDesc.create();
			method.getFpars().add(tFPar);
		}
		return method;
	}

	/** Base class for any formal parameter. It implements the {@link #create()} method. */
	static abstract class FParDescriptorCreator {

		abstract String getName();

		abstract List<TypeRef> getTypeRefs();

		abstract TypeRef getFParTypeRefComposition(List<TypeRef> typeRefsToUse);

		boolean isOptional() {
			return false;
		}

		boolean isValid() {
			return true;
		}

		boolean isVariadic() {
			return false;
		}

		TFormalParameter create() {
			final TFormalParameter fpar = TypesFactory.eINSTANCE.createTFormalParameter();
			fpar.setName(getName());
			List<TypeRef> typeRefsToUse = getTypeRefs();
			TypeRef paramCompTR1 = getFParTypeRefComposition(typeRefsToUse);
			TypeRef paramCompTR = paramCompTR1;
			fpar.setTypeRef(paramCompTR);
			fpar.setVariadic(isVariadic());
			fpar.setHasInitializerAssignment(isOptional());
			return fpar;
		}
	}

	/** Base class for formal parameters of composed methods. */
	abstract class FParDescriptor extends FParDescriptorCreator {
		final FParAggregate fpa;
		final int index;

		FParDescriptor(FParAggregate fpa) {
			this.fpa = fpa;
			this.index = cma.getFParAggregates().indexOf(fpa);
		}

		@Override
		abstract boolean isOptional();

		@Override
		TypeRef getFParTypeRefComposition(List<TypeRef> typeRefsToUse) {
			return MethodDescriptor.this.getFParTypeRefComposition(typeRefsToUse);
		}

		@Override
		String getName() {
			return fpa.getName();
		}

		@Override
		List<TypeRef> getTypeRefs() {
			List<TypeRef> typeRefsToUse = new LinkedList<>();
			typeRefsToUse.addAll(fpa.getTypeRefs());
			typeRefsToUse.addAll(fpa.getTypeRefsVariadicAccumulated());
			return typeRefsToUse;
		}

		@Override
		boolean isValid() {
			if (fpa == null)
				return false;
			// if (!fpa.getTypeRefsVariadic().isEmpty() && getNext() != null)
			if (fpa.hasValidationProblem())
				return false;
			return true;
		}

		FParDescriptor getPrev() {
			if (index <= 0)
				return null;
			return fpas.get(index - 1);
		}

		FParDescriptor getNext() {
			if (index + 1 >= fpas.size())
				return null;
			return fpas.get(index + 1);
		}

		boolean isLast() {
			if (index == fpas.size() - 1)
				return true;
			return false;
		}

		@Override
		boolean isVariadic() {
			if (isLast() && !fpa.getTypeRefsVariadicAccumulated().isEmpty())
				return true;
			return false;
		}
	}

	/** Class to instantiate an additional formal parameter which does not exist in any of the siblings. */
	class NewLastVariadicFPar extends FParDescriptor {
		final private String name = "vari";
		final private List<TypeRef> typeRefs;

		NewLastVariadicFPar(List<TypeRef> typeRefs) {
			super(null);
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
		boolean isOptional() {
			return false;
		}

		@Override
		public boolean isValid() {
			return true;
		}

		@Override
		boolean isVariadic() {
			return true;
		}
	}

	/** Class to implement logic with regard to methods in {@code Intersection Types}. */
	static class IntersectionMethod extends MethodDescriptor {
		IntersectionMethod(ComposedMemberAggregate cma) {
			super(cma);
		}

		@Override
		MemberAccessModifier getAccessability() {
			return cma.getAccessabilityMax();
		}

		@Override
		FParDescriptor getFParDescriptor(FParAggregate fpa) {
			return new IntersectionFPar(fpa);
		}

		@Override
		TypeRef getReturnTypeRefComposition() {
			List<TypeRef> typeRefs = cma.getMethodTypeRefsNonVoid();
			if (typeRefs.isEmpty()) {
				typeRefs.addAll(cma.getMethodTypeRefsVoid());
			}
			return cma.getTypeSystem().createSimplifiedIntersection(typeRefs, cma.getResource());
		}

		@Override
		protected TypeRef getFParTypeRefComposition(List<TypeRef> typeRefsToUse) {
			return cma.getTypeSystem().createSimplifiedUnion(typeRefsToUse, cma.getResource());
		}

		/** Class to implement logic with regard to formal parameter in {@code Intersection Type} methods. */
		class IntersectionFPar extends FParDescriptor {
			IntersectionFPar(FParAggregate fpa) {
				super(fpa);
			}

			@Override
			protected boolean isOptional() {
				if (isVariadic())
					return false;
				if (!fpa.allNonOptional())
					return true;

				FParDescriptor prevFpar = getPrev();
				if (prevFpar != null)
					return prevFpar.isOptional();

				return false;
			}
		}
	}

	/** Class to implement logic with regard to methods in {@code Union Types}. */
	static class UnionMethod extends MethodDescriptor {
		UnionMethod(ComposedMemberAggregate cma) {
			super(cma);
		}

		@Override
		MemberAccessModifier getAccessability() {
			return cma.getAccessabilityMin();
		}

		@Override
		FParDescriptor getFParDescriptor(FParAggregate fpa) {
			return new UnionFPar(fpa);
		}

		@Override
		TypeRef getReturnTypeRefComposition() {
			List<TypeRef> typeRefs = cma.getMethodTypeRefsVoid();
			if (typeRefs.isEmpty()) {
				typeRefs.addAll(cma.getMethodTypeRefsNonVoid());
			}
			return cma.getTypeSystem().createSimplifiedUnion(typeRefs, cma.getResource());
		}

		@Override
		protected TypeRef getFParTypeRefComposition(List<TypeRef> typeRefsToUse) {
			return cma.getTypeSystem().createSimplifiedIntersection(typeRefsToUse, cma.getResource());
		}

		/** Class to implement logic with regard to formal parameter in {@code Union Type} methods. */
		class UnionFPar extends FParDescriptor {
			UnionFPar(FParAggregate fpa) {
				super(fpa);
			}

			@Override
			protected boolean isOptional() {
				if (isVariadic())
					return false;
				if (fpa.allOptional())
					return true;

				FParDescriptor prevFpar = getPrev();
				if (prevFpar != null)
					return prevFpar.isOptional();

				return false;
			}
		}
	}

}
