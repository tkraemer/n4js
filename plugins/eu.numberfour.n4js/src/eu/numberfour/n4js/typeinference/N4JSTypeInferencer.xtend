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
package eu.numberfour.n4js.typeinference

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.numberfour.n4js.n4JS.ArrowFunction
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeArgument
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TypableElement
import eu.numberfour.n4js.ts.types.Type
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.utils.TypeUtils
import it.xsemantics.runtime.Result
import it.xsemantics.runtime.RuleEnvironment
import java.util.List
import org.eclipse.emf.ecore.resource.Resource

/**
 * Contains the logic to calculate the type (and create the type reference) for a given N4JS element.
 *
 * TODO this is likely to be rewritten and therefore currently scarcely documented.
 */
@Singleton
class N4JSTypeInferencer {

	@Inject
	protected TypeSystemHelper typeSystemHelper;

	@Inject
	protected N4JSTypeSystem xsemanticsSystem;


	/**
	 * Calls the type system's rule and creates a new RuleEnvironment with the resource from the types refs.
	 */
	def Result<Boolean> subtypeTypeRef(TypeArgument left, TypeArgument right) {
		val context = if (left?.eResource?.resourceSet !== null) left else right;
		val G = RuleEnvironmentExtensions.newRuleEnvironment(context);
		xsemanticsSystem.subtype(G, left, right);
	}

	/**
	 * Infers type of <code>object</code> using the {@link N4JSTypeSystem}.
	 * The given object must be contained in a ResourceSet (required for creating a
	 * RuleEnvironment).
	 * <p>
	 * IMPORTANT: this method does not handle generics and the binding of type parameters/
	 * arguments; if you need this behavior, use method {@link #tau(TypableElement,TypeRef)} instead!
	 */
	def TypeRef tau(TypableElement object) {
		val G = RuleEnvironmentExtensions.newRuleEnvironment(object);
		return tau(G, object);
	}
	/**
	 * Same as {@link #tau(TypableElement)}, but <code>object</code> need not be contained in a
	 * ResourceSet; instead, a RuleEnvironment is provided as argument.
	 */
	def TypeRef tau(RuleEnvironment G, TypableElement object) {
		return tau(G, object, false, true);
	}

	/**
	 * Convenience method, calls {@link #tau(TypableElement,TypeRef} with type wrapped into a a reference (without any
	 * further type arguments).
	 */
	def TypeRef tau(TypableElement object, Type context) {
		return tau(object, TypeUtils.createTypeRef(context))
	}

	/**
	 * This is the generics-aware version of method {@link #tau(TypableElement)}; if you do not
	 * care about generics and the binding of type variables, use plain {@link #tau(TypableElement)}
	 * instead (it is less expensive + caching)!<p>
	 * Parameter <code>context</code> defines if and how type variables are bound.
	 * Consider the following example:
	 * <pre>
	 * class A&lt;T> {
	 *     T f;
	 * }
	 * class B&lt;S> extends A&lt;S> {}
	 * class C extends B&lt;string> {}
	 * </pre>
	 * If you call this method passing in the TField instance representing field 'f'
	 * as <code>object</code>, the type inferred by this method will depend on parameter
	 * <code>context</code> as follows:
	 * <ul>
	 * <li>passing in A as the context will return type variable T as type of field 'f'.
	 * <li>passing in B as the context will return type variable B as type of field 'f'
	 *     (note: here type variable T was bound but type variable S remained unbound).
	 * <li>passing in C as the context will return built-in type 'string' as type of field 'f'
	 *     (now both type variables S and T have been bound).
	 * </ul>
	 * Furthermore, if the TypeRef passed in as context is a ParameterizedTypeRef, it may
	 * define additional type variable bindings (cf. ParameterizedTypeRef).
	 */
	def TypeRef tau(TypableElement object, TypeRef context) {
		return tau(object, context, true);
	}

	/**
	 * Similar as {@link #tau(TypableElement, TypeRef)}, but if {@code takeUpperBound} is {@code false} the upper bound will not be acquired for the calculated type reference.
	 * Passing {@code true} for {@code takeUpperBound} will be an identical call of {@link #tau(RuleEnvironment, TypableElement)}.
	 */
	def TypeRef tau(TypableElement object, TypeRef context, boolean takeUpperBound) {
		val G = createRuleEnvironmentForContext(context, object?.eResource);
		return tau(G, object, true, takeUpperBound);
	}

	private def TypeRef tau(RuleEnvironment G, TypableElement object, boolean substituteTypeVariables, boolean takeUpperBound) {
		val result = xsemanticsSystem.type(G, object);
		val value = result.value;
		if (value !== null) {
			// Replace bound type variables with type arguments if required. Otherwise keep working with the original value.
			val substValue = if (substituteTypeVariables) substituteTypeVariables(G, value) else value;
			if (substValue !== null) {
				// Required, because #upperBound() will return 'null' if it gets a type variable!
				if (substValue.declaredType instanceof TypeVariable) {
					return substValue;
				}
				if (takeUpperBound) {
					val upperBoundResult = xsemanticsSystem.upperBound(G, substValue);
					return upperBoundResult.value;
				} else {
					return substValue;
				}
			}
		}
		return null;
	}

	/**
	 * Creates a rule environment for the given context, i.e. it will be populated with type
	 * variable substitutions, this-bindings, etc.
	 * For details about the context argument see {@link #tau(TypableElement,TypeRef)}.
	 * Returns an empty rule environment without any bindings if context is <code>null</code>.
	 * For consistency with {@link RuleEnvironmentExtensions#newRuleEnvironment(EObject)} this
	 * will throw an exception if resourceSet is <code>null</code>.
	 */
	def RuleEnvironment createRuleEnvironmentForContext(TypeRef context, Resource resource) {
		val RuleEnvironment G = RuleEnvironmentExtensions.newRuleEnvironment(resource);
		if(context !== null) {
			typeSystemHelper.addSubstitutions(G, context);
			if(context instanceof ParameterizedTypeRef) {
				if(context.declaredType instanceof TClassifier)
					RuleEnvironmentExtensions.addThisType(G, context);
			}
		}
		return G;
	}

	/**
	 * Substitutes all bound type variables in <code>typeRef</code> based on the bindings defined
	 * in the given context.
	 * For details about the context argument see {@link #tau(TypableElement,TypeRef)}.
	 * Simply returns <code>typeRef</code> if one of the arguments is null or substitution fails.
	 */
	def TypeRef substituteTypeVariables(TypeRef typeRef, TypeRef context, Resource resource) {
		if(typeRef !== null && context !== null && resource !== null) {
			val RuleEnvironment G = createRuleEnvironmentForContext(context, resource);
			return substituteTypeVariables(G, typeRef);
		}
		return typeRef;
	}
	/**
	 * Substitutes all bound type variables in <code>typeRef</code> based on the bindings defined
	 * in the given rule environment.
	 * Simply returns <code>typeRef</code> if one of the arguments is null or substitution fails.
	 */
	def TypeRef substituteTypeVariables(RuleEnvironment G, TypeRef typeRef) {
		if (G !== null && typeRef !== null) {
			val TypeArgument result = xsemanticsSystem.substTypeVariables(G, typeRef).getValue();
			if (result instanceof TypeRef)
				return result;
		}
		return typeRef;
	}

	/**
	 * Convenience method to create a simplified union type.
	 * Returns <code>null</code> if resource set is null.
	 */
	def TypeRef createSimplifiedUnion(List<TypeRef> typeRefs, Resource resource) {
		if (typeRefs.size() > 1 && resource !== null) {
			return typeSystemHelper.createUnionType(
					RuleEnvironmentExtensions.newRuleEnvironment(resource),
					typeRefs);
		}
		else if (typeRefs.size() == 1) {
			return TypeUtils.copyIfContained(typeRefs.get(0));
		}
		else {
			return null;
		}
	}

	/**
	 * Convenience method to create a simplified intersection type.
	 * Returns <code>null</code> if resource set is null.
	 */
	def TypeRef createSimplifiedIntersection(List<TypeRef> typeRefs, Resource resource) {
		if (typeRefs.size() > 1 && resource !== null) {
			return typeSystemHelper.createIntersectionType(
					RuleEnvironmentExtensions.newRuleEnvironment(resource),
					typeRefs);
		}
		else if (typeRefs.size() == 1) {
			return TypeUtils.copyIfContained(typeRefs.get(0));
		}
		else {
			return null;
		}
	}

	/**
	 * Detect whether the {@link ArrowFunction} body consists of
	 * a lone expression, where moreover such lone expression isn't void-typed.
	 * Provided those conditions are met, the lambda is regarded as needing return insertion.
	 */
	def boolean needsReturnInsertionForBody(ArrowFunction fe) {
		if (!fe.isSingleExprImplicitReturn) {
			return false
		}
		// check if body is typed void, in which case no return will be inserted
		val singleExpr = fe.implicitReturnExpr()
		val bits = BuiltInTypeScope.get(singleExpr.eResource.resourceSet)
		val t = tau(singleExpr)
		if (t.declaredType === bits.voidType) {
			return false
		}
		return true
	}
}
