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

import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TypesFactory;
import eu.numberfour.n4js.ts.utils.TypeUtils;

/**
 *
 */
abstract class FieldDescriptor implements ComposedMemberDescriptorNew {
	final ComposedMemberAggregate cma;

	FieldDescriptor(ComposedMemberAggregate cma) {
		this.cma = cma;
	}

	abstract MemberAccessModifier getAccessability();

	abstract boolean isFinal();

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public TField create(String name) {
		TField field = TypesFactory.eINSTANCE.createTField();
		TypeRef typeRef = cma.getTypeRefs().get(0); // since this is a field, the typeRef is never a composed type
		TypeUtils.setMemberTypeRef(field, typeRef);
		field.setName(name);
		field.setDeclaredFinal(isFinal());
		field.setDeclaredMemberAccessModifier(getAccessability());
		return field;
	}

	static class IntersectionField extends FieldDescriptor {
		IntersectionField(ComposedMemberAggregate cma) {
			super(cma);
		}

		@Override
		MemberAccessModifier getAccessability() {
			return cma.getAccessabilityMax();
		}

		@Override
		boolean isFinal() {
			return cma.onlyReadOnlyFields();
		}
	}

	static class UnionField extends FieldDescriptor {
		UnionField(ComposedMemberAggregate cma) {
			super(cma);
		}

		@Override
		MemberAccessModifier getAccessability() {
			return cma.getAccessabilityMin();
		}

		@Override
		boolean isFinal() {
			return cma.hasReadOnlyField();
		}
	}

}
