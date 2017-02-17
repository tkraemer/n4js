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
import org.eclipse.xtext.scoping.IScope;

import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import eu.numberfour.n4js.utils.EcoreUtilN4;
import it.xsemantics.runtime.RuleEnvironment;

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
	 * Key method of entire scoping for union types. This creates a new TMember as a combination of all members of the
	 * given name in the union type's contained types. If those members cannot be combined into a single valid member,
	 * this method creates a dummy placeholder.
	 */
	@Override
	TMember createComposedMember(String memberName) {
		// check all subScopes for a member of the given name and
		// merge the properties of the existing members into 'composedMember'
		final Resource resource = EcoreUtilN4.getResource(context, composedTypeRef);
		final ComposedMemberDescriptor composedMemberDescr = getComposedMemberDescriptor(resource);
		for (int idx = 0; idx < subScopes.length; idx++) {
			final IScope subScope = subScopes[idx];
			final TypeRef typeRef = composedTypeRef.getTypeRefs().get(idx);
			final Resource res = EcoreUtilN4.getResource(context, composedTypeRef);
			final RuleEnvironment GwithSubstitutions = ts.createRuleEnvironmentForContext(typeRef, res);
			final TMember member = findMemberInSubScope(subScope, memberName);
			composedMemberDescr.merge(GwithSubstitutions, member);
		}
		// produce result
		if (!composedMemberDescr.isEmpty()) {
			// at least one of the subScopes had an element of that name
			final TMember result;
			if (composedMemberDescr.isValid()) {
				// success case:
				// 1) ALL of the subScopes have an element for that name and
				// 2) they can be merged into a valid composed member
				result = composedMemberDescr.create(memberName);
			} else {
				// some of the subScopes do not have an element for that name OR
				// they do not form a valid composed member (e.g. they are of different kind)
				// -> produce a specific error message explaining the incompatibility
				// (this error placeholder will be wrapped with a UncommonMemberDescription
				// in #getSingleLocalElementByName(QualifiedName) above)
				result = createErrorPlaceholder(memberName);
			}
			// add composed member to ComposedTypeRef (without notifications to avoid cache-clear)
			final ComposedTypeRef cacheHolder = getCacheHolder(composedTypeRef);
			EcoreUtilN4.doWithDeliver(false, () -> {
				cacheHolder.getCachedComposedMembers().add(result);
			}, cacheHolder);
			return result;
		} else {
			// none of the subScopes has an element of that name
			// -> produce the ordinary "Cannot resolve reference ..." error by returning 'null'
			return null;
		}
	}

}
