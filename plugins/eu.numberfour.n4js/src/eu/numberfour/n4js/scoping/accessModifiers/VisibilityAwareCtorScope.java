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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import eu.numberfour.n4js.n4JS.NewExpression;
import eu.numberfour.n4js.ts.typeRefs.ConstructorTypeRef;
import eu.numberfour.n4js.ts.types.TMethod;
import eu.numberfour.n4js.xtext.scoping.FilterWithErrorMarkerScope;
import eu.numberfour.n4js.xtext.scoping.IEObjectDescriptionWithError;

/**
 * Scope used to check accessibility of constructor in new-expressions.
 */
public class VisibilityAwareCtorScope extends FilterWithErrorMarkerScope {

	private final MemberVisibilityChecker checker;
	private final NewExpression context;
	private final ConstructorTypeRef receiverType;

	/**
	 * @param parent
	 *            parent scope
	 * @param checker
	 *            visibility rules for members.
	 * @param receiverType
	 *            Type which holds the constructor to call.
	 * @param context
	 *            new expression calling a constructor
	 */
	public VisibilityAwareCtorScope(IScope parent, MemberVisibilityChecker checker, ConstructorTypeRef receiverType,
			NewExpression context) {
		super(parent);
		this.checker = checker;
		this.receiverType = receiverType;
		this.context = context;
	}

	@Override
	protected IEObjectDescriptionWithError wrapFilteredDescription(IEObjectDescription originalDescr) {
		return new InvisibleCtorDescription(originalDescr, receiverType);
	}

	@Override
	protected boolean isAccepted(IEObjectDescription description) {
		EObject proxyOrInstance = description.getEObjectOrProxy();
		if (proxyOrInstance != null && !proxyOrInstance.eIsProxy()) {
			if (proxyOrInstance instanceof TMethod) { // looking for constructor method.
				TMethod method = (TMethod) proxyOrInstance;
				if (method.isConstructor()) {
					return checker.isConstructorVisible(context, receiverType, method);
				}
			}
		}
		return true;
	}

}
