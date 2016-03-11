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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.google.inject.Inject;

import eu.numberfour.n4js.n4JS.extensions.ExpressionExtensions;
import eu.numberfour.n4js.scoping.utils.DynamicPseudoScope;
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;
import eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef;
import eu.numberfour.n4js.ts.types.FieldAccessor;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TGetter;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TypesFactory;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.typeinference.N4JSTypeInferencer;
import eu.numberfour.n4js.utils.ContainerTypesHelper;
import eu.numberfour.n4js.utils.EcoreUtilN4;
import eu.numberfour.n4js.validation.JavaScriptVariant;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * Scope implementation for ComposedTypeRefs, i.e. union types and intersection types.
 */
public class ComposedMemberScope extends MemberScope {

	/**
	 * Emulates a factory w/o FactoryModuleBuilder
	 */
	public static class ComposedMemberScopeFactory {

		@Inject
		ContainerTypesHelper containerTypesHelper;

		/**
		 * Creates an instance of this class for the given ComposedTypeRef.
		 */
		public IScope create(ComposedTypeRef composedTypeRef, EObject context, List<IScope> subScopes,
				boolean isIntersection, N4JSTypeInferencer typeInferencer) {
			if (JavaScriptVariant.getVariant(context).isECMAScript()) { // cf. sec. 13.1
				return new DynamicPseudoScope();
			}

			// TODO add support for intersection types
			if (isIntersection)
				return IScope.NULLSCOPE;
			return new ComposedMemberScope(containerTypesHelper, composedTypeRef, context, subScopes, typeInferencer);
		}
	}

	private final ComposedTypeRef composedTypeRef;
	private final IScope[] subScopes;

	private final N4JSTypeInferencer typeInferencer;

	private ComposedMemberScope(ContainerTypesHelper containerTypesHelper, ComposedTypeRef composedTypeRef,
			EObject context,
			List<IScope> subScopes, N4JSTypeInferencer typeInferencer) {
		super(containerTypesHelper, IScope.NULLSCOPE, Collections.emptyList(), context, false);
		this.composedTypeRef = composedTypeRef;
		this.subScopes = subScopes.toArray(new IScope[subScopes.size()]);
		this.typeInferencer = typeInferencer;
	}

	@Override
	protected IEObjectDescription getSingleLocalElementByName(QualifiedName name) {
		// only overriding this method to use our own implementation of AbstractDescriptionWithError
		final IEObjectDescription result = super.getSingleLocalElementByName(name);
		final EObject eObjOrProxy = result != null ? result.getEObjectOrProxy() : null;
		if (isErrorPlaceholder(eObjOrProxy))
			return createUncommonDescriptionWrapper(result);
		return result;
	}

	@Override
	protected List<TMember> getMembers() {
		// collect all names from subScopes
		final Set<String> names = new HashSet<>();
		for (IScope currSubScope : subScopes) {
			try {
				for (IEObjectDescription currDesc : currSubScope.getAllElements()) {
					names.add(currDesc.getName().getLastSegment());
				}
			} catch (UnsupportedOperationException e) {
				// according to API doc of IScope#getAllElements(), scopes are free to throw an
				// UnsupportedOperationException --> therefore we catch and ignore this here
			}
		}
		// build combined members for these names
		// (for the "current" read/write-access derived from the context,
		// see MemberScope#getSingleLocalElementByName(QualifiedName))
		final boolean accessForWriteOperation = ExpressionExtensions.isLeftHandSide(context);
		final List<TMember> result = new ArrayList<>();
		for (String currName : names) {
			final TMember currM = findMember(currName, accessForWriteOperation, false);
			// don't include invalid combined members
			// (otherwise they would appear in code completion proposals, etc.)
			if (currM != null && !isErrorPlaceholder(currM))
				result.add(currM);
		}
		return result;
	}

	@Override
	protected TMember findMember(String name, boolean writeAccess, boolean isStaticAccess) {
		return getOrCreateComposedMember(name, writeAccess, isStaticAccess);
	}

	private TMember getOrCreateComposedMember(String memberName, boolean writeAccess, boolean isStaticAccess) {
		final TMember member = getComposedMember(memberName, writeAccess, isStaticAccess);
		if (member == null)
			return createComposedMember(memberName, writeAccess, isStaticAccess);
		return member;
	}

	private TMember getComposedMember(String memberName, boolean writeAccess, boolean isStaticAccess) {
		for (TMember currM : getCacheHolder(composedTypeRef).getCachedComposedMembers())
			if (memberName.equals(currM.getName()) &&
					hasCorrectAccess(currM, writeAccess, isStaticAccess))
				return currM;
		return null;
	}

	/**
	 * Key method of entire scoping for union types. This creates a new TMember as a combination of all members of the
	 * given name in the union type's contained types. If those members cannot be combined into a single valid member,
	 * this method creates a dummy placeholder.
	 */
	private TMember createComposedMember(String memberName, boolean writeAccess, boolean isStaticAccess) {
		// check all subScopes for a member of the given name and
		// merge the properties of the existing members into 'composedMember'
		final ComposedMemberDescriptor composedMember = new ComposedMemberDescriptor(
				writeAccess, EcoreUtilN4.getResource(context, composedTypeRef), typeInferencer);
		for (int idx = 0; idx < subScopes.length; idx++) {
			final RuleEnvironment GwithSubstitutions = typeInferencer.createRuleEnvironmentForContext(
					composedTypeRef.getTypeRefs().get(idx),
					EcoreUtilN4.getResource(context, composedTypeRef));
			composedMember.merge(
					GwithSubstitutions,
					findMemberInSubScope(idx, memberName, writeAccess, isStaticAccess));
		}
		// produce result
		if (!composedMember.isEmpty()) {
			// at least one of the subScopes had an element of that name
			final TMember result;
			if (composedMember.isValid()) {
				// success case:
				// 1) ALL of the subScopes have an element for that name and
				// 2) they can be merged into a valid composed member
				result = composedMember.create(memberName);
			} else {
				// some of the subScopes do not have an element for that name OR
				// they do not form a valid composed member (e.g. they are of different kind)
				// -> produce a specific error message explaining the incompatibility
				// (this error placeholder will be wrapped with a UncommonMemberDescription
				// in #getSingleLocalElementByName(QualifiedName) above)
				result = createErrorPlaceholder(memberName, writeAccess);
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

	/**
	 * Cached composed members must be stored in a resource. However, due to copying during type variable substitution,
	 * we might be dealing with a dangling (i.e. not contained in a resource) copy of another ComposedTypeRef contained
	 * in the AST or type model. Therefore, follow the chain defined by property 'originalComposedTypeRef' until we find
	 * the first ComputedTypeRef that is contained in a resource.
	 * <p>
	 * See also Xsemantics rule 'substTypeVariablesInComposedTypeRef'.
	 */
	private ComposedTypeRef getCacheHolder(ComposedTypeRef ctr) {
		while (ctr.eResource() == null && ctr.getOriginalComposedTypeRef() != null)
			ctr = ctr.getOriginalComposedTypeRef();
		// if (ctr.eResource() == null) {
		// // error is necessary since EMF catches all exceptions in EcoreUtil#resolved
		// throw new AssertionError("cacheHolder must be contained in a resource");
		// }
		return ctr;
	}

	/**
	 * To avoid having to do all computation over and over in case no valid composed member can be built, we also create
	 * a member in the error case as a placeholder.
	 * <p>
	 * Note: we need to be able to store error place holders and/or successfully composed members for read- and
	 * write-access independently (i.e. we might have, for example, a valid composed member for read access but an error
	 * placeholder for write access); therefore we have to use getters/setters for error place holders.
	 */
	private TMember createErrorPlaceholder(String memberName, boolean writeAccess) {
		if (writeAccess) {
			return TypeUtils.createTSetter(memberName, null, TypeRefsFactory.eINSTANCE.createUnknownTypeRef());
		} else {
			final TGetter g = TypesFactory.eINSTANCE.createTGetter();
			g.setName(memberName);
			g.setDeclaredTypeRef(TypeRefsFactory.eINSTANCE.createUnknownTypeRef());
			return g;
		}
	}

	/**
	 * Returns true iff 'obj' was created by method {@link #createErrorPlaceholder(String, boolean)}.
	 */
	private boolean isErrorPlaceholder(EObject obj) {
		return obj != null && !obj.eIsProxy() &&
				obj instanceof FieldAccessor &&
				TypeUtils.getMemberTypeRef((FieldAccessor) obj) instanceof UnknownTypeRef;
	}

	/**
	 * Searches for a member of the given name and for the given access in the sub-scope with index 'subScopeIdx'.
	 */
	private TMember findMemberInSubScope(int subScopeIdx, String name, boolean writeAccess, boolean isStaticAccess) {
		final IEObjectDescription currElem = getSubScope(subScopeIdx).getSingleElement(QualifiedName.create(name));
		if (currElem != null) {
			final EObject objOrProxy = currElem.getEObjectOrProxy();
			if (objOrProxy != null && !objOrProxy.eIsProxy() && objOrProxy instanceof TMember) {
				final TMember currM = (TMember) objOrProxy;
				if (hasCorrectAccess(currM, writeAccess, isStaticAccess)
						|| (currM instanceof TField && hasCorrectAccess(currM, !writeAccess, isStaticAccess)))
					return currM;
			}
		}
		return null;
	}

	private IScope getSubScope(int idx) {
		return idx >= 0 && idx < subScopes.length ? subScopes[idx] : null;
	}

	private IEObjectDescription createUncommonDescriptionWrapper(IEObjectDescription result) {
		return new UncommonMemberDescription(result, composedTypeRef, subScopes);
	}

	private static boolean hasCorrectAccess(TMember currM, boolean writeAccess, boolean staticAccess) {
		return ((!writeAccess && currM.isReadable()) || (writeAccess && currM.isWriteable())) &&
				staticAccess == currM.isStatic();
	}

	/**
	 * Tells if the given member is a "composed member", i.e. if it is a virtual member created to represent the union
	 * of intersection of several other members in the context of a union or intersection type.
	 * <p>
	 * All code introducing special handling of such members should use this method, so that it will be easy to locate
	 * these places in case scoping of union/intersection types is modified.
	 */
	public static final boolean isComposedMember(TMember member) {
		return member.eContainer() instanceof ComposedTypeRef
				&& member.eContainingFeature() == TypeRefsPackage.eINSTANCE.getComposedTypeRef_CachedComposedMembers();
	}

	/**
	 * This clears all cached TMembers in EMF property {@link ComposedTypeRef#getCachedComposedMembers()
	 * cachedComposedMembers()} in astElement and the entire AST below astElement.
	 * <p>
	 * IMPORTANT: this must be called whenever parts of the AST are being reused (when doing partial parsing).
	 * <p>
	 * TODO: consider alternative of moving the cache to TModule OR improve how this is called from N4JSLinker
	 */
	public static final void clearCachedComposedMembers(EObject astElement) {
		if (astElement instanceof ComposedTypeRef)
			astElement.eUnset(TypeRefsPackage.Literals.COMPOSED_TYPE_REF__CACHED_COMPOSED_MEMBERS);
		final TreeIterator<EObject> iter = astElement.eAllContents();
		while (iter.hasNext()) {
			final EObject currObj = iter.next();
			if (currObj instanceof ComposedTypeRef) {
				// clear the cache of composed members
				currObj.eUnset(TypeRefsPackage.Literals.COMPOSED_TYPE_REF__CACHED_COMPOSED_MEMBERS);
				iter.prune();
			}
		}
	}
}
