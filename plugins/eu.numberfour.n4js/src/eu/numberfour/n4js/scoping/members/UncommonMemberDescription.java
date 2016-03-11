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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.google.common.base.Joiner;

import eu.numberfour.n4js.scoping.utils.AbstractDescriptionWithError;
import eu.numberfour.n4js.scoping.utils.WrongWriteAccessDescription;
import eu.numberfour.n4js.validation.IssueCodes;
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.TField;

/**
 * This description wraps a member of a union type that is not present in all contained types or is somehow incompatible
 * so that it cannot be composed into a single valid composed member.
 */
public class UncommonMemberDescription extends AbstractDescriptionWithError {

	private final ComposedTypeRef composedTypeRef;
	private final IScope[] subScopes;

	private String message;
	private String code;

	/**
	 * Creates a new instance of this wrapping description.
	 *
	 * @param delegate
	 *            the decorated description.
	 */
	public UncommonMemberDescription(IEObjectDescription delegate,
			ComposedTypeRef composedTypeRef, IScope[] subScopes) {
		super(delegate);
		this.composedTypeRef = composedTypeRef;
		this.subScopes = subScopes;
	}

	@Override
	public String getMessage() {
		initialize();
		return message;
	}

	@Override
	public String getIssueCode() {
		initialize();
		return code;
	}

	private void initialize() {
		if (message == null) {
			// create one of the specialized error messages (if applicable)
			final boolean haveSpecialMessage =
					initMissingFromOneOrMoreSubScopes() || initMultipleKinds();
			// otherwise create a default message
			if (!haveSpecialMessage)
				initDefault();
		}
	}

	private boolean initMissingFromOneOrMoreSubScopes() {
		final QualifiedName name = getName();
		final List<String> missingFrom = new ArrayList<>();
		for (int idx = 0; idx < subScopes.length; idx++) {
			if (!isPresentInSubScope(idx, name))
				missingFrom.add(getNameForSubScope(idx));
		}
		if (!missingFrom.isEmpty()) {
			message = IssueCodes
					.getMessageForUNI_MISSING(getName().getLastSegment(), Joiner.on(", ").join(missingFrom));
			code = IssueCodes.UNI_MISSING;
			return true;
		}
		return false;
	}

	private boolean initMultipleKinds() {
		// collect names of subScopes per member kind
		boolean readOnlyField = false;
		final Map<String, List<String>> subScopeNamesPerMemberKind = new HashMap<>();
		for (int idx = 0; idx < subScopes.length; idx++) {
			final IEObjectDescription currElem = subScopes[idx].getSingleElement(getName());
			final EObject currObj = currElem.getEObjectOrProxy();
			if (currObj != null && !currObj.eIsProxy()) {
				readOnlyField |= (currObj instanceof TField && !((TField) currObj).isWriteable());
				final String currKindStr = getMemberTypeName(currObj);
				List<String> currList = subScopeNamesPerMemberKind.get(currKindStr);
				if (currList == null) {
					currList = new ArrayList<>();
					subScopeNamesPerMemberKind.put(currKindStr, currList);
				}
				currList.add(getNameForSubScope(idx));
			}
		}
		// if more than two member kinds, create error message
		final Set<String> keySet = subScopeNamesPerMemberKind.keySet();
		if (keySet.size() > 1) {
			final String memberName = getName().getLastSegment();

			// check for three special cases of error 'multipleKinds' that require a more informative message
			if (keySet.size() == 2 && keySet.contains("field") && keySet.contains("getter")) {
				message = IssueCodes.getMessageForUNI_INVALID_COMBINATION("getters", memberName, "read-only");
				code = IssueCodes.UNI_INVALID_COMBINATION;
				return true;
			}
			else if (keySet.size() == 2 && keySet.contains("field") && keySet.contains("setter")) {
				if (readOnlyField) {
					message = IssueCodes.getMessageForUNI_INVALID_COMBINATION_SETTER_VS_READ_ONLY_FIELD(memberName);
					code = IssueCodes.UNI_INVALID_COMBINATION_SETTER_VS_READ_ONLY_FIELD;
					return true;
				}
				message = IssueCodes.getMessageForUNI_INVALID_COMBINATION("setters", memberName, "write-only");
				code = IssueCodes.UNI_INVALID_COMBINATION;
				return true;
			}

			final List<String> errMsgParts = new ArrayList<>();
			final List<String> kinds = new ArrayList<>(subScopeNamesPerMemberKind.keySet());
			Collections.sort(kinds); // sorting important here to produce consistent error messages
			for (String currKind : kinds) {
				errMsgParts.add(currKind + " in " + Joiner.on(", ").join(subScopeNamesPerMemberKind.get(currKind)));
			}
			message = IssueCodes.getMessageForUNI_MULTIPLE_KINDS(memberName, Joiner.on("; ").join(errMsgParts));
			code = IssueCodes.UNI_MULTIPLE_KINDS;
			return true;
		}
		return false;
	}

	private void initDefault() {
		final String memberName = getName().getLastSegment();
		message = IssueCodes.getMessageForUNI_UNCOMMON(memberName);
		code = IssueCodes.UNI_UNCOMMON;
	}

	/**
	 * Returns true iff the sub scope with index <code>idx</code> contains an element for the given name.
	 */
	private boolean isPresentInSubScope(int idx, QualifiedName name) {
		final IEObjectDescription currElem = subScopes[idx].getSingleElement(name);
		// must have an object description AND it must not be an error description
		if (currElem != null && !(currElem instanceof AbstractDescriptionWithError))
			return true;
		// special case:
		// if only the read/write access was wrong, then assume the element is present; this will produce more
		// consistent error messages for combinations between fields and getters / setters / read-only fields.
		if (currElem instanceof WrongWriteAccessDescription)
			return true;
		// no or no valid object description found
		return false;
	}

	private String getNameForSubScope(int idx) {
		if (idx < composedTypeRef.getTypeRefs().size()) {
			final TypeRef typeRef = composedTypeRef.getTypeRefs().get(idx);
			if (typeRef != null)
				return typeRef.getTypeRefAsString();
		}
		return null;
	}
}
