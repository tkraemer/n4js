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
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import eu.numberfour.n4js.xtext.scoping.IEObjectDescriptionWithError;

/**
 *
 */
public class UnionMemberScope extends ComposedMemberScope {
	/**
	 * Constructor
	 */
	public UnionMemberScope(ComposedTypeRef composedTypeRef, EObject context, List<IScope> subScopes,
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
		for (IScope currSubScope : subScopes) {
			IEObjectDescription subDescription = currSubScope.getSingleElement(qn);

			boolean descrWithError = subDescription == null || subDescription instanceof IEObjectDescriptionWithError;
			if (descrWithError) {
				return createComposedMemberDescriptionWithErrors(description);
			}
		}

		return description;
	}

	@Override
	protected IEObjectDescription createComposedMemberDescriptionWithErrors(IEObjectDescription result) {
		return new UnionMemberDescriptionWithError(result, composedTypeRef, subScopes, writeAccess);
	}

	@Override
	protected ComposedMemberDescriptor getComposedMemberDescriptorNew(ComposedMemberAggregate cma) {
		return new UnionMemberDescriptor(cma);
	}
}
