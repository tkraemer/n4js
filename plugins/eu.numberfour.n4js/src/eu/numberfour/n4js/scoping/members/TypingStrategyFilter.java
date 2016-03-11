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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.MemberAccessModifier;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TMethod;
import eu.numberfour.n4js.ts.types.TypingStrategy;

/**
 * Filter used in {@link TypingStrategyAwareMemberScope} to filter out results not available for a given typing
 * strategy.
 */
class TypingStrategyFilter implements Predicate<IEObjectDescription> {

	final TypingStrategy typingStrategy;

	TypingStrategyFilter(TypeRef receiverType) {
		typingStrategy = retrieveTypingStrategy(receiverType);
	}

	TypingStrategy retrieveTypingStrategy(TypeRef receiverType) {
		if (receiverType != null && !receiverType.eIsProxy()) {
			return receiverType.getTypingStrategy();
		}
		return TypingStrategy.DEFAULT;
	}

	/**
	 * Returns the typing strategy of this filter, set in the constructor.
	 */
	public TypingStrategy getTypingStrategy() {
		return typingStrategy;
	}

	@Override
	public boolean apply(IEObjectDescription description) {
		if (typingStrategy == TypingStrategy.DEFAULT || typingStrategy == TypingStrategy.NOMINAL) {
			return true;
		}

		EObject proxyOrInstance = description.getEObjectOrProxy();
		if (proxyOrInstance != null && !proxyOrInstance.eIsProxy()) {
			if (proxyOrInstance instanceof TMember) {
				TMember member = (TMember) proxyOrInstance;
				if (member.isStatic() || member.getMemberAccessModifier() != MemberAccessModifier.PUBLIC) {
					return false;
				}
				if (member instanceof TMethod) {
					return typingStrategy == TypingStrategy.STRUCTURAL;
				}
			}
		}
		return true;
	}

}
