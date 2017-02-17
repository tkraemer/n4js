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
import java.util.List;
import java.util.TreeMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import eu.numberfour.n4js.scoping.utils.AbstractDescriptionWithError;
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.validation.IssueCodes;
import eu.numberfour.n4js.xtext.scoping.IEObjectDescriptionWithError;

/**
 * This description wraps a member of a composed type that is not present in all contained types or is somehow
 * incompatible so that it cannot be composed into a single valid composed member.
 */
public abstract class ComposedMemberDescriptionWithError extends AbstractDescriptionWithError {

	/**
	 * Use a tree map to ensure stable error messages.
	 */
	class MapOfIndexes<T_Key> extends TreeMap<T_Key, List<Integer>> {
		void add(T_Key key, int index) {
			if (key != null) {
				List<Integer> indexes = this.get(key);
				if (indexes == null) {
					indexes = new ArrayList<>(max);
					this.put(key, indexes);
				}
				indexes.add(index);
			}
		}

		int numberOf(T_Key key) {
			List<Integer> indexes = this.get(key);
			if (indexes == null) {
				return 0;
			}
			return indexes.size();
		}

		String getScopeNamesForKey(T_Key key) {
			List<Integer> indexes = this.get(key);
			if (indexes == null) {
				return null;
			}
			StringBuilder strb = new StringBuilder();
			for (int i : indexes) {
				if (strb.length() != 0) {
					strb.append(", ");
				}
				strb.append(getNameForSubScope(i));
			}
			return strb.toString();
		}

		int firstIndex(T_Key key) {
			List<Integer> indexes = this.get(key);
			if (indexes == null) {
				return -1;
			}
			return indexes.get(0);
		}

	}

	protected final ComposedTypeRef composedTypeRef;
	protected final IScope[] subScopes;
	protected final boolean writeAccess;
	/** length of scopes */
	protected final int max;

	protected String message;
	protected String code;

	/**
	 * Creates a new instance of this wrapping description.
	 *
	 * @param delegate
	 *            the decorated description.
	 */
	public ComposedMemberDescriptionWithError(IEObjectDescription delegate,
			ComposedTypeRef composedTypeRef, IScope[] subScopes, boolean writeAccess) {
		super(delegate);
		this.composedTypeRef = composedTypeRef;
		this.subScopes = subScopes;
		max = subScopes.length;
		this.writeAccess = writeAccess;
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

	private boolean initialize() {
		if (message == null) {
			IEObjectDescription[] descriptions = new IEObjectDescription[subScopes.length];
			MapOfIndexes<String> indexesPerMemberType = new MapOfIndexes<>(); // use string here, since EnumLiteral is
																				// not a TMember!
			MapOfIndexes<String> indexesPerCode = new MapOfIndexes<>();
			List<String> missingFrom = new ArrayList<>();
			final QualifiedName name = getName();
			boolean readOnlyField = false;
			for (int i = 0; i < max; i++) {
				IEObjectDescription description = subScopes[i].getSingleElement(name);
				if (description != null) {
					descriptions[i] = description;
					EObject eobj = description.getEObjectOrProxy();
					String type = getMemberTypeName(eobj);
					indexesPerMemberType.add(type, i);
					if (description instanceof IEObjectDescriptionWithError) {
						String subCode = ((IEObjectDescriptionWithError) description).getIssueCode();
						indexesPerCode.add(subCode, i);
					}
					if ("field".equals(type)) {
						readOnlyField |= !((TField) eobj).isWriteable();
					}
				} else {
					missingFrom.add(getNameForSubScope(i));
				}
			}

			return //
			initMissingFrom(missingFrom)
					|| initDifferentMemberTypes(indexesPerMemberType, name, readOnlyField)
					|| initSubMessages(descriptions, indexesPerCode)
					|| initDefault();
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

	protected abstract boolean initDifferentMemberTypes(MapOfIndexes<String> indexesPerMemberType,
			final QualifiedName name,
			boolean readOnlyField);

	protected abstract boolean initMissingFrom(List<String> missingFrom);

	protected abstract boolean initDefault();

	private String getNameForSubScope(int idx) {
		if (idx < composedTypeRef.getTypeRefs().size()) {
			final TypeRef typeRef = composedTypeRef.getTypeRefs().get(idx);
			if (typeRef != null)
				return typeRef.getTypeRefAsString();
		}
		return null;
	}
}
