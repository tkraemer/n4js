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
 * Implements the method {@link #getMemberCreator()} which returns a new {@link MemberCreator} for a given
 * {@link MemberType}.
 */
public class UnionMemberCreator extends ComposedMemberCreator {

	UnionMemberCreator(ComposedMemberAggregate cma) {
		super(cma);
	}

	@Override
	protected MemberCreator getMemberCreator() {
		switch (memberType) {
		case METHOD:
			return new MethodCreator.UnionMethodCreator(cma);
		case FIELD:
			return new FieldCreator.UnionFieldCreator(cma);
		case GETTER:
			return new GetterCreator.UnionGetterCreator(cma);
		case SETTER:
			return new SetterCreator.UnionSetterCreator(cma);
		}
		return null;
	}

	@Override
	protected MemberType getNewMemberType() {
		// mix of all memberTypes
		if (cma.hasMethodMemberType() && cma.hasNonMethodMemberType()) {
			return null; // inValid
		}
		if (cma.onlyMethodMemberTypes()) {
			return METHOD;
		}
		// mix of all non-method memberTypes
		if (cma.onlyGetterMemberTypes()) {
			return GETTER;
		}
		if (cma.onlySetterMemberTypes()) {
			return SETTER;
		}
		if (cma.onlyFieldMemberTypes()) {
			if (allTypeRefAreEqual()) {
				return FIELD;
			} else {
				if (cma.isWriteAccess()) {
					return SETTER;
				} else {
					return GETTER;
				}
			}
		}
		// mix of all non-method memberTypes
		if (!cma.hasGetterMemberType()) {
			return SETTER;
		}
		if (!cma.hasSetterMemberType()) {
			return GETTER;
		}
		return null; // inValid
	}

	@Override
	public boolean isEmpty() {
		return cma.isEmpty();
	}

	@Override
	public boolean isValid() {
		if (cma.isSiblingMissing())
			return false;
		if (specialMemberCreator == null)
			return false;
		return specialMemberCreator.isValid();
	}

}
