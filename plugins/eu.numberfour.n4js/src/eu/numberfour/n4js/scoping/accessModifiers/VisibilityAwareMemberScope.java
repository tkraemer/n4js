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
package eu.numberfour.n4js.scoping.accessModifiers;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression;
import eu.numberfour.n4js.scoping.accessModifiers.MemberVisibilityChecker.MemberVisibility;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.TEnumLiteral;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.xtext.scoping.FilterWithErrorMarkerScope;
import eu.numberfour.n4js.xtext.scoping.IEObjectDescriptionWithError;

/**
 * Filters members that are not accessible from the given context (that is, from the location of the
 * {@link ParameterizedPropertyAccessExpression} given in the constructor).
 */
public class VisibilityAwareMemberScope extends FilterWithErrorMarkerScope {

	private final MemberVisibilityChecker checker;
	private final EObject context; // usually a ParameterizedPropertyAccessExpression
	private final TypeRef receiverType;

	private final HashMap<String, String> accessModifierSuggestionStore = new HashMap<>();

	/**
	 * Creates a new scope instance.
	 *
	 * @param context
	 *            usually a ParameterizedPropertyAccessExpression.
	 */
	public VisibilityAwareMemberScope(IScope parent, MemberVisibilityChecker checker, TypeRef receiverType,
			EObject context) {
		super(parent);
		this.checker = checker;
		this.receiverType = receiverType;
		this.context = context;
	}

	@Override
	protected IEObjectDescriptionWithError wrapFilteredDescription(IEObjectDescription result) {
		return new InvisibleMemberDescription(result, accessModifierSuggestionStore.get(result.getEObjectURI().toString()));
	}

	@Override
	protected boolean isAccepted(IEObjectDescription description) {
		EObject proxyOrInstance = description.getEObjectOrProxy();
		if (proxyOrInstance != null && !proxyOrInstance.eIsProxy()) {
			if (proxyOrInstance instanceof TMember) {
				TMember member = (TMember) proxyOrInstance;
				MemberVisibility result = checker.isVisible(context, receiverType, member);

				if (!result.visibility)
					this.accessModifierSuggestionStore.put(description.getEObjectURI().toString(), result.accessModifierSuggestion);

				return result.visibility;
			} else if (proxyOrInstance instanceof TEnumLiteral) {
				return checker.isEnumLiteralVisible(context, receiverType);
			}
		}
		return true;
	}

}
