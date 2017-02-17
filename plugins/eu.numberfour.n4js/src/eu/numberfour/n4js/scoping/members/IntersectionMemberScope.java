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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import eu.numberfour.n4js.xtext.scoping.IEObjectDescriptionWithError;

/**
 *
 */
public class IntersectionMemberScope extends ComposedMemberScope {

	/**
	 * Constructor
	 */
	public IntersectionMemberScope(ComposedTypeRef composedTypeRef, EObject context, List<IScope> subScopes,
			N4JSTypeSystem ts) {
		super(composedTypeRef, context, subScopes, ts);
	}

	/**
	 * Check if the elements of the subScopes cause errors. Handle these errors according to union/intersection types.
	 */
	@Override
	protected IEObjectDescription getCheckedDescription(String name, TMember member) {
		IEObjectDescription description = EObjectDescription.create(member.getName(), member);

		QualifiedName qn = QualifiedName.create(name);
		boolean allDescrWithError = true;
		MemberType lastCMT = null;
		for (IScope currSubScope : subScopes) {
			IEObjectDescription subDescription = currSubScope.getSingleElement(qn);

			if (subDescription != null) {
				TMember subScopeMember = (TMember) subDescription.getEObjectOrProxy();
				MemberType newCMT = getCollapsedMemberType(subScopeMember);
				if (lastCMT != null && lastCMT != newCMT) {
					return createComposedMemberDescriptionWithErrors(description);
				}
				lastCMT = newCMT;
			}

			boolean descrWithError = description == null || subDescription instanceof IEObjectDescriptionWithError;
			allDescrWithError &= descrWithError;
		}
		if (allDescrWithError) {
			return createComposedMemberDescriptionWithErrors(description);
		}

		return description;
	}

	private MemberType getCollapsedMemberType(TMember tMember) {
		switch (tMember.getMemberType()) {
		case FIELD:
			return MemberType.FIELD;
		case GETTER:
			return MemberType.FIELD;
		case SETTER:
			return MemberType.FIELD;
		case METHOD:
			return MemberType.METHOD;
		default:
			return null;
		}
	}

	@Override
	protected IEObjectDescription createComposedMemberDescriptionWithErrors(IEObjectDescription result) {
		return new IntersectionMemberDescriptionWithError(result, composedTypeRef, subScopes, writeAccess);
	}

	@Override
	protected ComposedMemberDescriptor getComposedMemberDescriptor(final Resource resource) {
		return new IntersectionMemberDescriptor(writeAccess, resource, ts);
	}
}
