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

import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TMember;

/**
 * Implements the method {@link #getNewMemberType()} which selects the correct {@link MemberType} for a new composed
 * {@link TMember}. Refer to N4JS-Spec for an overview table.
 * <p>
 * Implements the method {@link #getMemberFactory()} which returns a new {@link MemberFactory} for a given
 * {@link MemberType}.
 */
public class UnionMemberFactory extends ComposedMemberFactory {

	UnionMemberFactory(ComposedMemberInfo cma) {
		super(cma);
	}

	@Override
	protected MemberFactory getMemberFactory() {
		switch (memberType) {
		case METHOD:
			return new MethodFactory.UnionMethodFactory(cmi);
		case FIELD:
			return new FieldFactory.UnionFieldFactory(cmi);
		case GETTER:
			return new GetterFactory.UnionGetterFactory(cmi);
		case SETTER:
			return new SetterFactory.UnionSetterFactory(cmi);
		}
		return null;
	}

	@Override
	protected MemberType getNewMemberType() {
		// mix of all memberTypes
		if (cmi.hasMethodMemberType() && cmi.hasNonMethodMemberType()) {
			return null; // inValid
		}
		if (cmi.onlyMethodMemberTypes()) {
			return METHOD;
		}
		// mix of all non-method memberTypes
		if (cmi.onlyGetterMemberTypes()) {
			return GETTER;
		}
		if (cmi.onlySetterMemberTypes()) {
			return SETTER;
		}
		if (cmi.onlyFieldMemberTypes()) {
			if (allTypeRefAreEqual()) {
				return FIELD;
			} else {
				if (cmi.isWriteAccess()) {
					return SETTER;
				} else {
					return GETTER;
				}
			}
		}
		// mix of all non-method memberTypes
		if (!cmi.hasGetterMemberType()) {
			return SETTER;
		}
		if (!cmi.hasSetterMemberType()) {
			return GETTER;
		}
		return null; // inValid
	}

	@Override
	public boolean isEmpty() {
		return cmi.isEmpty();
	}

	@Override
	public boolean isValid() {
		if (cmi.isSiblingMissing())
			return false;
		if (specialMemberFactory == null)
			return false;
		return specialMemberFactory.isValid();
	}

}
