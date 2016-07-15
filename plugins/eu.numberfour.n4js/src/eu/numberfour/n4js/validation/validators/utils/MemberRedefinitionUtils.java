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
package eu.numberfour.n4js.validation.validators.utils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.google.common.collect.Iterables;

import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TClassifier;
import eu.numberfour.n4js.ts.types.TInterface;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TypesPackage;

/**
 * A util class for member redefinition validation.
 */
public class MemberRedefinitionUtils {

	/**
	 * Filters the given overridden members to only contain members which are metatype compatible with the overriding
	 * member.
	 */
	public static Iterable<TMember> getMetatypeCompatibleOverriddenMembers(TMember overridingMember,
			Iterable<TMember> overriddenMembers) {
		return Iterables.filter(overriddenMembers, member -> isMetaTypeCompatible(overridingMember, member));
	}

	/**
	 * Returns {@code true} if the two members are metatype compatible.
	 *
	 * @param m1
	 *            The first member
	 * @param m2
	 *            The second member
	 */
	public static boolean isMetaTypeCompatible(TMember m1, TMember m2) {
		boolean result = m1.getMemberType() == m2.getMemberType();

		if (!result) {
			switch (m1.getMemberType()) {
			case METHOD:
				break; // initial test covers this case
			case FIELD:
				result = m2.getMemberType() == MemberType.GETTER || m2.getMemberType() == MemberType.SETTER;
				break;
			case SETTER:
			case GETTER:
				result = m2.getMemberType() == MemberType.FIELD;
				break;
			}
		}
		return result;
	}

	/**
	 * Returns the fitting verb for a redefinition of all of the redefined members by the current classifier.
	 *
	 * @param redefinedMembers
	 *            The redefined members
	 * @param redefiningClassifier
	 *            The classifier which redefines the given members
	 */
	public static String getRedefinitionVerb(Iterable<TMember> redefinedMembers, TClassifier redefiningClassifier) {
		Set<?> overriddenClassifierTypes = StreamSupport.stream(redefinedMembers.spliterator(), false)
				.map(member -> member.getContainingType().eClass())
				.collect(Collectors.toSet());

		boolean isOverridingSuperclassMember = overriddenClassifierTypes.contains(TypesPackage.eINSTANCE.getTClass());
		boolean isOverridingObjectPrototypeMembers = overriddenClassifierTypes
				.contains(TypesPackage.eINSTANCE.getTObjectPrototype());

		boolean isImplementing = overriddenClassifierTypes.contains(TypesPackage.eINSTANCE.getTInterface());
		boolean isOverriding = isOverridingSuperclassMember || isOverridingObjectPrototypeMembers;
		boolean isInterfaceExtendingInterface = redefiningClassifier instanceof TInterface;

		if (isOverriding && isImplementing) {
			return "overriding/implementing";
		} else if (isOverriding || isInterfaceExtendingInterface) {
			return "overriding";
		} else if (isImplementing) {
			return "implementing";
		} else {
			// General case, should not happen
			return "redefining";
		}
	}

	/* Non-instantiable util class. */
	private MemberRedefinitionUtils() {
	}
}
