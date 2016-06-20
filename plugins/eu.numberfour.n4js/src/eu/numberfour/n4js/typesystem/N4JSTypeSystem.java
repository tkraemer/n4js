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
package eu.numberfour.n4js.typesystem;

import org.eclipse.emf.ecore.EObject;

import com.google.inject.Inject;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.TypableElement;
import eu.numberfour.n4js.xsemantics.InternalTypeSystem;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleEnvironment;

/**
 *
 */
public class N4JSTypeSystem {

	@Inject
	private InternalTypeSystem its;

	public Result<TypeRef> type(RuleEnvironment G, TypableElement element) {
		return its.type(G, element);
	}

	public Result<TypeRef> expectedTypeIn(RuleEnvironment G, EObject container, Expression expression) {
		return its.expectedTypeIn(G, container, expression);
	}

	public Result<Boolean> subtype(RuleEnvironment G, TypeArgument left, TypeArgument right) {
		return its.subtype(G, left, right);
	}

	public boolean subtypeSucceeded(RuleEnvironment G, TypeArgument left, TypeArgument right) {
		return its.subtypeSucceeded(G, left, right);
	}

	public Result<Boolean> supertype(RuleEnvironment G, TypeArgument left, TypeArgument right) {
		return its.supertype(G, left, right);
	}

	public Result<Boolean> equaltype(RuleEnvironment G, TypeArgument left, TypeArgument right) {
		return its.equaltype(G, left, right);
	}

	public boolean equaltypeSucceeded(RuleEnvironment G, TypeArgument left, TypeArgument right) {
		return its.equaltypeSucceeded(G, left, right);
	}

	public Result<TypeRef> upperBound(RuleEnvironment G, TypeArgument typeArgument) {
		return its.upperBound(G, typeArgument);
	}

	public Result<TypeRef> lowerBound(RuleEnvironment G, TypeArgument typeArgument) {
		return its.lowerBound(G, typeArgument);
	}

	public Result<TypeArgument> substTypeVariables(RuleEnvironment G, TypeArgument typeArgument) {
		return its.substTypeVariables(G, typeArgument);
	}

	public Result<TypeRef> thisTypeRef(RuleEnvironment G, EObject location) {
		return its.thisTypeRef(G, location);
	}
}
