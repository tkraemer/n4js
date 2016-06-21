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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.TClassifier;
import eu.numberfour.n4js.ts.types.TypableElement;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.xsemantics.InternalTypeSystem;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleEnvironment;

/**
 *
 */
@Singleton
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

	public TypeRef substTypeVariablesXXX(RuleEnvironment G, TypeRef typeRef) {
		return (TypeRef) substTypeVariables(G, typeRef).getValue();
	}

	public Result<TypeArgument> substTypeVariables(RuleEnvironment G, TypeArgument typeArgument) {
		return its.substTypeVariables(G, typeArgument);
	}

	public Result<TypeRef> thisTypeRef(RuleEnvironment G, EObject location) {
		return its.thisTypeRef(G, location);
	}

	// --------------------------------------------------------
	// FIXME:

	@Inject
	private TypeSystemHelper tsh;

	/**
	 * Convenience method to create a simplified union type. Returns <code>null</code> if resource set is null.
	 */
	public TypeRef createSimplifiedUnion(List<TypeRef> typeRefs, Resource resource) {
		if (typeRefs.size() > 1 && resource != null) {
			return tsh.createUnionType(
					RuleEnvironmentExtensions.newRuleEnvironment(resource),
					typeRefs.toArray(new TypeRef[typeRefs.size()]));
		} else if (typeRefs.size() == 1) {
			return TypeUtils.copyIfContained(typeRefs.get(0));
		} else {
			return null;
		}
	}

	/**
	 * Convenience method to create a simplified intersection type. Returns <code>null</code> if resource set is null.
	 */
	public TypeRef createSimplifiedIntersection(List<TypeRef> typeRefs, Resource resource) {
		if (typeRefs.size() > 1 && resource != null) {
			return tsh.createIntersectionType(
					RuleEnvironmentExtensions.newRuleEnvironment(resource),
					typeRefs.toArray(new TypeRef[typeRefs.size()]));
		} else if (typeRefs.size() == 1) {
			return TypeUtils.copyIfContained(typeRefs.get(0));
		} else {
			return null;
		}
	}

	/**
	 * Infers type of <code>object</code>. The given object must be contained in a ResourceSet (required for creating a
	 * RuleEnvironment).
	 * <p>
	 * IMPORTANT: this method does not handle generics and the binding of type parameters/arguments; if you need this
	 * behavior, use method {@link #tau(TypableElement,TypeRef)} instead!
	 */
	public TypeRef tau(TypableElement object) {
		final RuleEnvironment G = RuleEnvironmentExtensions.newRuleEnvironment(object);
		return type(G, object).getValue();
	}

	/**
	 * This is the generics-aware version of method {@link #tau(TypableElement)}; if you do not care about generics and
	 * the binding of type variables, use plain {@link #tau(TypableElement)} instead (it is less expensive + caching)!
	 * <p>
	 * Parameter <code>context</code> defines if and how type variables are bound. Consider the following example:
	 *
	 * <pre>
	 * class A&lt;T> {
	 * 	T f;
	 * }
	 *
	 * class B&lt;S> extends A&lt;S> {
	 * }
	 *
	 * class C extends B&lt;string> {
	 * }
	 * </pre>
	 *
	 * If you call this method passing in the TField instance representing field 'f' as <code>object</code>, the type
	 * inferred by this method will depend on parameter <code>context</code> as follows:
	 * <ul>
	 * <li>passing in A as the context will return type variable T as type of field 'f'.
	 * <li>passing in B as the context will return type variable B as type of field 'f' (note: here type variable T was
	 * bound but type variable S remained unbound).
	 * <li>passing in C as the context will return built-in type 'string' as type of field 'f' (now both type variables
	 * S and T have been bound).
	 * </ul>
	 * Furthermore, if the TypeRef passed in as context is a ParameterizedTypeRef, it may define additional type
	 * variable bindings through its type arguments (c.f. {@link ParameterizedTypeRef}).
	 */
	public TypeRef tau(TypableElement object, TypeRef context) {
		final RuleEnvironment G = createRuleEnvironmentForContext(context, object != null ? object.eResource() : null);
		final Result<TypeRef> result = type(G, object);
		final TypeRef value = result.getValue();
		if (value != null) {
			final TypeRef substValue = substTypeVariablesXXX(G, value);
			return substValue;
		}
		return null;
	}

	/**
	 * Creates a rule environment for the given context, i.e. it will be populated with type variable substitutions,
	 * this-bindings, etc. For details about the context argument see {@link #tau(TypableElement,TypeRef)}. Returns an
	 * empty rule environment without any bindings if context is <code>null</code>. For consistency with
	 * {@link RuleEnvironmentExtensions#newRuleEnvironment(EObject)} this will throw an exception if resourceSet is
	 * <code>null</code>.
	 */
	public RuleEnvironment createRuleEnvironmentForContext(TypeRef context, Resource resource) {
		final RuleEnvironment G = RuleEnvironmentExtensions.newRuleEnvironment(resource);
		if (context != null) {
			tsh.addSubstitutions(G, context);
			if (context instanceof ParameterizedTypeRef) {
				if (context.getDeclaredType() instanceof TClassifier)
					RuleEnvironmentExtensions.addThisType(G, context);
			}
		}
		return G;
	}
}
