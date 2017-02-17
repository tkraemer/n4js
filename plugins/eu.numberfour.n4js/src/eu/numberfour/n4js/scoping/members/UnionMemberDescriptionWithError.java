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

import java.util.List;

import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.google.common.base.Joiner;

import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.validation.IssueCodes;
import eu.numberfour.n4js.xtext.scoping.IEObjectDescriptionWithError;

/**
 * This description wraps a member of a union type that is not present in all contained types or is somehow incompatible
 * so that it cannot be composed into a single valid composed member.
 */
public class UnionMemberDescriptionWithError extends ComposedMemberDescriptionWithError {

	/**
	 * Creates a new instance of this wrapping description.
	 *
	 * @param delegate
	 *            the decorated description.
	 */
	public UnionMemberDescriptionWithError(IEObjectDescription delegate,
			ComposedTypeRef composedTypeRef, IScope[] subScopes, boolean writeAccess) {
		super(delegate, composedTypeRef, subScopes, writeAccess);
	}

	@Override
	protected boolean initMessageAndCode(List<String> missingFrom, MapOfIndexes<String> indexesPerMemberType,
			QualifiedName name, boolean readOnlyField, IEObjectDescription[] descriptions,
			MapOfIndexes<String> indexesPerCode) {

		return initMissingFrom(missingFrom)
				|| initDifferentMemberTypes(indexesPerMemberType, name, readOnlyField)
				|| initSubMessages(descriptions, indexesPerCode)
				|| initDefault();
	}

	private boolean initDifferentMemberTypes(MapOfIndexes<String> indexesPerMemberType, final QualifiedName name,
			boolean readOnlyField) {
		if (indexesPerMemberType.size() > 1) {
			int numberOfFields = indexesPerMemberType.numberOf("field");
			// check for three special cases of error 'multipleKinds' that require a more informative message
			if (numberOfFields + indexesPerMemberType.numberOf("getter") == max) {
				if (writeAccess) {
					message = IssueCodes.getMessageForUNI_INVALID_COMBINATION("getters", name, "read-only");
					code = IssueCodes.UNI_INVALID_COMBINATION;
					return true;
				} else {
					return false; // access would be ok, there must be another reason
				}
			}
			if (numberOfFields + indexesPerMemberType.numberOf("setter") == max) {
				if (writeAccess) {
					if (readOnlyField) {
						message = IssueCodes.getMessageForUNI_INVALID_COMBINATION_SETTER_VS_READ_ONLY_FIELD(name);
						code = IssueCodes.UNI_INVALID_COMBINATION_SETTER_VS_READ_ONLY_FIELD;
					} else {
						message = IssueCodes.getMessageForUNI_INVALID_COMBINATION("setters", name, "write-only");
						code = IssueCodes.UNI_INVALID_COMBINATION;
					}
					return true;
				}
				{
					return false; // access would be ok, there must be another reason
				}
			}

			StringBuilder strb = new StringBuilder();
			for (String memberTypeName : indexesPerMemberType.keySet()) {
				String foundScopes = indexesPerMemberType.getScopeNamesForKey(memberTypeName);
				if (strb.length() != 0) {
					strb.append("; ");
				}
				strb.append(memberTypeName + " in " + foundScopes);
			}
			message = IssueCodes.getMessageForUNI_MULTIPLE_KINDS(name, "union", strb.toString());
			code = IssueCodes.UNI_MULTIPLE_KINDS;
			return true;
		}
		return false;
	}

	private boolean initSubMessages(IEObjectDescription[] descriptions, MapOfIndexes<String> indexesPerCode) {
		// all subScopes returned same code:
		if (indexesPerCode.size() == 1 && indexesPerCode.numberOf(indexesPerCode.firstKey()) == max) {
			code = indexesPerCode.firstKey();
			int index = indexesPerCode.firstIndex(code);
			message = ((IEObjectDescriptionWithError) descriptions[index]).getMessage();
			String scopeName = getNameForSubScope(index);
			message.replace(scopeName, "all types");
			return true;
		}

		if (indexesPerCode.size() > 0) {
			StringBuilder strb = new StringBuilder();
			for (String subCode : indexesPerCode.keySet()) {
				int index = indexesPerCode.firstIndex(subCode);
				String submessage = ((IEObjectDescriptionWithError) descriptions[index])
						.getMessage();
				String scopeName = getNameForSubScope(index);

				String allScopeNames = indexesPerCode.getScopeNamesForKey(subCode);
				String completeSubMessage;
				if (submessage.contains(scopeName)) {
					completeSubMessage = submessage.replace(scopeName, allScopeNames);
				} else {
					if (submessage.endsWith("."))
						submessage = submessage.substring(0, submessage.length() - 1);
					completeSubMessage = IssueCodes.getMessageForUNI_SUBMESSAGES(allScopeNames, submessage);
				}
				if (strb.length() > 0) {
					strb.append(" ");
				}
				strb.append(completeSubMessage);
			}
			message = strb.toString();
			code = IssueCodes.UNI_SUBMESSAGES;

			return true;
		}

		return false;

	}

	private boolean initMissingFrom(List<String> missingFrom) {
		if (!missingFrom.isEmpty()) {
			message = IssueCodes.getMessageForUNI_MISSING(getName().getLastSegment(),
					Joiner.on(", ").join(missingFrom));
			code = IssueCodes.UNI_MISSING;
			return true;
		} else {
			return false;
		}
	}

	private boolean initDefault() {
		final String memberName = getName().getLastSegment();
		message = IssueCodes.getMessageForUNI_UNCOMMON(memberName);
		code = IssueCodes.UNI_UNCOMMON;
		return true;
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
