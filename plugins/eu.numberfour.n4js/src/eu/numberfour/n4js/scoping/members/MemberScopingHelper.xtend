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
package eu.numberfour.n4js.scoping.members

import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.scoping.accessModifiers.MemberVisibilityChecker
import eu.numberfour.n4js.scoping.accessModifiers.StaticAccessAwareMemberScope
import eu.numberfour.n4js.scoping.accessModifiers.VisibilityAwareMemberScope
import eu.numberfour.n4js.scoping.utils.DynamicPseudoScope
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef
import eu.numberfour.n4js.ts.typeRefs.EnumTypeRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeRef
import eu.numberfour.n4js.ts.typeRefs.IntersectionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefStructural
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.ts.types.PrimitiveType
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TEnum
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TN4Classifier
import eu.numberfour.n4js.ts.types.TObjectPrototype
import eu.numberfour.n4js.ts.types.TStructuralType
import eu.numberfour.n4js.ts.types.Type
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.TypingStrategy
import eu.numberfour.n4js.ts.types.UndefinedType
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.utils.EcoreUtilN4
import eu.numberfour.n4js.validation.JavaScriptVariant
import eu.numberfour.n4js.xtext.scoping.IEObjectDescriptionWithError
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.Scopes

/**
 */
class MemberScopingHelper {
	@Inject N4JSTypeSystem ts;
	@Inject MemberScope.MemberScopeFactory memberScopeFactory
	@Inject ComposedMemberScope.ComposedMemberScopeFactory composedMemberScopeFactory
	@Inject private MemberVisibilityChecker memberVisibilityChecker

	/**
	 * Create a new member scope that filters using the given criteria (visibility, static access).
	 * <p>
	 * When choosing static scope, the {@code context} is inspected to determine read/write access
	 * but only if it's a {@link ParameterizedPropertyAccessExpression} or a {@code IndexedAccessExpression}.
	 *
	 * @param receiverTypeRef
	 *               TypeRef for the value whose scope is of interest.
	 * @param context
	 *               AST node used for (a) obtaining context resource and (b) visibility checking.
	 * @param checkVisibility
	 *               if true, the member scope will be wrapped in a {@link VisibilityAwareMemberScope}; if
	 *               false, method {@link getPropertyTypeForNode(IScope,String)} will <b>never</b> return
	 *               {@link #INVISIBLE_MEMBER}.
	 * @param staticAccess
	 *               true: only static members are relevant; false: only non-static ones.
	 */
	public def IScope createMemberScopeFor(TypeRef receiverTypeRef, EObject context, boolean checkVisibility, boolean staticAccess) {
		var result = members(receiverTypeRef,context,staticAccess);
		if(checkVisibility) {
			result = new VisibilityAwareMemberScope(result, memberVisibilityChecker, receiverTypeRef, context)
		}
		if(staticAccess) {
			result = new StaticAccessAwareMemberScope(result, receiverTypeRef, context);
		}
		if(checkVisibility) {
			result = new TypingStrategyAwareMemberScope(result, receiverTypeRef);
		}
		return result;
	}

	/**
	 * Is there a member (visible or not) in the given scope matching the given (name, staticAccess) combination?
	 */
	public def boolean isNonExistentMember(IScope scope, String memberName, boolean staticAccess) {
		val descriptions = scope.getElements(QualifiedName.create(memberName))
		return descriptions.isEmpty
	}

	/**
	 * For the member given by (name, staticAccess) return the erroneous descriptions from the given scope.
	 * <p>
	 * Precondition: {@link #isNonExistentMember} has negative answer.
	 */
	public def Iterable<IEObjectDescriptionWithError> getErrorsForMember(IScope scope, String memberName, boolean staticAccess) {
		val descriptions = scope.getElements(QualifiedName.create(memberName))
		val errorsOrNulls = descriptions.map[d| IEObjectDescriptionWithError.getDescriptionWithError(d)]
		return errorsOrNulls.filterNull
	}

	/**
	 * Look up all non-erroneous {@link TMember} in the given scope having the given name.
	 */
	public def Iterable<TMember> findMembersForName(IScope scope, String memberName, boolean staticAccess) {
		val candidates = scope.getElements(QualifiedName.create(memberName)).filter[description| !(IEObjectDescriptionWithError.isErrorDescription(description))]
		val proxysOrInstances = candidates.map[description| description.getEObjectOrProxy()]
		val tmembers = proxysOrInstances.filter[proxyOrInstance| proxyOrInstance !== null && !proxyOrInstance.eIsProxy() && (proxyOrInstance instanceof TMember)]
		return tmembers.map[m| m as TMember].filter[m| m.static == staticAccess]
	}

	/**
	 * In case there's a single non-erroneous {@link TMember} in the given scope matching the given name, return it. Otherwise return null.
	 */
	public def TMember findUniqueMemberForName(IScope scope, String memberName, boolean staticAccess) {
		val candidates = findMembersForName(scope, memberName, staticAccess)
		if(candidates.size == 1) {
			return candidates.head
		}
		return null
	}

	// TODO member computation should be extracted
	private def dispatch IScope members(Type type, EObject context, boolean staticAccess) {
		if (type.eIsProxy) {
			return new DynamicPseudoScope()
		}
		if (JavaScriptVariant.getVariant(context).isECMAScript()) { // cf. sec. 13.1
			return new DynamicPseudoScope();
		}

		return IScope.NULLSCOPE
	}

	private def dispatch IScope members(UndefinedType type, EObject context, boolean staticAccess) {
		if (JavaScriptVariant.getVariant(context).isECMAScript()) { // cf. sec. 13.1
			return new DynamicPseudoScope();
		}

		return IScope.NULLSCOPE
	}

	private def dispatch IScope members(UnknownTypeRef type, EObject context, boolean staticAccess) {
		return new DynamicPseudoScope()
	}

	private def dispatch IScope members(Void type, EObject context, boolean staticAccess) {
		return new DynamicPseudoScope()
	}

	private def dispatch IScope members(Object object, EObject context, boolean staticAccess) {
		if (JavaScriptVariant.getVariant(context).isECMAScript()) { // cf. sec. 13.1
			return new DynamicPseudoScope();
		}
		return IScope.NULLSCOPE
	}

	/**
	 * Primitive types have no members, but they can be auto-boxed to their
	 * corresponding object type which then, transparently to the user, provide members.
	 */
	private def dispatch IScope members(PrimitiveType prim, EObject context, boolean staticAccess) {
		return members(prim.autoboxedType, context, staticAccess);
	}

	/**
	 * Creates member scope with parent containing members of implicit super types.
	 */
	private def dispatch IScope members(ContainerType<?> type, EObject context, boolean staticAccess) {
		val builtInTypeScope = BuiltInTypeScope.get(getResourceSet(type, context));
		var implicitSuperType = builtInTypeScope.objectType
		if (type instanceof TN4Classifier) {
			if (type.typingStrategy !== TypingStrategy.DEFAULT) {
				implicitSuperType = builtInTypeScope.objectType
			} else if (type instanceof TClass) {
				if (type.superClassRef === null || type.superClassRef.declaredType != builtInTypeScope.objectType) {
					implicitSuperType = builtInTypeScope.n4ObjectType;
				}
			} else { // TInterface
				implicitSuperType = builtInTypeScope.n4ObjectType;
			}
		}
		val implicitSuperTypeMemberScope = if (JavaScriptVariant.getVariant(context).isECMAScript()) { // cf. sec. 13.1
				memberScopeFactory.create(new DynamicPseudoScope(), implicitSuperType, context, staticAccess);
			} else {
				memberScopeFactory.create(implicitSuperType, context, staticAccess);
			}
		return memberScopeFactory.create(implicitSuperTypeMemberScope, type, context, staticAccess)

	}

	private def dispatch IScope members(EnumTypeRef etr, EObject context, boolean staticAccess) {
		val builtInScope = BuiltInTypeScope.get(getResourceSet(etr, context));
		// IDE-1221 select builtin-scope upon whether this enumration is tagged stringbased
		val enumType = if (TypeSystemHelper.isStringBasedEnumeration(etr.enumType)) builtInScope.
				n4StringBasedEnumType else builtInScope.getN4EnumType()
		val IScope staticEnumScope = memberScopeFactory.create(enumType, context, true);
		return Scopes.scopeFor(etr.enumType.literals, staticEnumScope)
	}

	/**
	 * Returns a scope of the literals, that is members such as name or value.
	 * That is, the instance members of an enumeration. The static members are made available
	 * in {@link #members(EnumTypeRef, EObject, boolean)}
	 */
	private def dispatch IScope members(TEnum enumeration, EObject context, boolean staticAccess) {
		val builtInTypeScope = BuiltInTypeScope.get(getResourceSet(enumeration, context));
		// IDE-1221 select builtin-scope upon whether this enumration is tagged stringbased
		val TObjectPrototype specificEnumType = (if (TypeSystemHelper::
				isStringBasedEnumeration(enumeration)) builtInTypeScope.n4StringBasedEnumType else builtInTypeScope.
				n4EnumType);
		val instanceEnumScope = memberScopeFactory.create(specificEnumType, context, false);
		return instanceEnumScope;
	}

	private def dispatch IScope members(TypeRef type, EObject context, boolean staticAccess) {
		return IScope.NULLSCOPE
	}

	private def dispatch IScope members(ParameterizedTypeRef ptr, EObject context, boolean staticAccess) {
		val IScope result = members(ptr.declaredType, context, staticAccess);
		if (ptr.dynamic && !(result instanceof DynamicPseudoScope)) {
			return new DynamicPseudoScope(result)
		}
		return result
	}

	private def dispatch IScope members(ParameterizedTypeRefStructural ptrs, EObject context, boolean staticAccess) {
		val IScope result = ptrs.declaredType.members(context, staticAccess);
		if (ptrs.dynamic && !(result instanceof DynamicPseudoScope)) {
			return new DynamicPseudoScope(result)
		}
		if (ptrs.structuralMembers.empty) {
			return result
		}
		if (ptrs.structuralType !== null) {
			return memberScopeFactory.create(result, ptrs.structuralType, context, staticAccess);
		}

		// note: this are not the members of the defined type
		// however, we only scope locally, so that doesn't matter
		return memberScopeFactory.create(result, ptrs.structuralMembers, context, staticAccess);
	}

	/**
	 * Note: N4JSScopeProvider already taking the upper bound before using this class (thus resolving ThisTypeRefs
	 * beforehand), so we will never enter this method from there; still provided to support uses from other code.
	 */
	private def dispatch IScope members(ThisTypeRef thisTypeRef, EObject context, boolean staticAccess) {
		// taking the upper bound to "resolve" the ThisTypeRef:
		// this[C] --> C (ParameterizedTypeRef)
		// ~~this[C] with { number prop; } --> ~~C with { number prop; } (ParameterizedTypeRefStructural)
		val ub = ts.upperBound(RuleEnvironmentExtensions.newRuleEnvironment(context), thisTypeRef);

		if (!(ub instanceof ThisTypeRef)) {
			// ThisTypeRef was resolved
			return members(ub, context, staticAccess);
		}

		// probably an unbound ThisTypeRef or some other error (reported elsewhere)
		return IScope.NULLSCOPE;
	}

	private def dispatch IScope members(ClassifierTypeRef ctr, EObject context, boolean staticAccess) {
		val IScope result = ctr.staticType.members(context, true) // staticAccess is always true in this case
		if (ctr.dynamic && !(result instanceof DynamicPseudoScope)) {
			return new DynamicPseudoScope(result)
		}
		return result
	}

	private def dispatch IScope members(ComposedTypeRef ctr, EObject context, boolean staticAccess) {
		return composedMemberScopeFactory.create(ctr, context, ctr.typeRefs.map[members(context, staticAccess)],
			ctr instanceof IntersectionTypeExpression, ts);
	}

	// TODO type variable can specify multiple upper bounds!
	private def dispatch IScope members(TypeVariable typeVar, EObject context, boolean staticAccess) {
		if (!typeVar.declaredUpperBounds.isEmpty) {
			return typeVar.declaredUpperBounds.head.members(context, staticAccess)
		} else {
			val builtInTypeScope = BuiltInTypeScope.get(getResourceSet(typeVar, context));
			val anyType = builtInTypeScope.anyType
			return members(anyType, context, staticAccess)
		}
	}

	private def dispatch IScope members(TStructuralType structType, EObject context, boolean staticAccess) {
		val IScope parent = IScope.NULLSCOPE
		if (structType.ownedMembers.empty) {
			return parent
		}
		return memberScopeFactory.create(structType, context, staticAccess)
	}

	private def dispatch IScope members(FunctionTypeRef ftExpr, EObject context, boolean staticAccess) {
		return internal_members(ftExpr, context, staticAccess)
	}

	private def dispatch IScope members(FunctionTypeExpression ftExpr, EObject context, boolean staticAccess) {
		return internal_members(ftExpr, context, staticAccess)
	}

	/** internal to avoid catch-all of ParameterizedTypeRef for FuntionTypeRefs while dispatching */
	def private IScope internal_members(FunctionTypeExprOrRef ftExpr, EObject context, boolean staticAccess) {
		val builtInTypeScope = BuiltInTypeScope.get(getResourceSet(ftExpr, context));
		val fType = builtInTypeScope.functionType
		val ret = members(fType, context, staticAccess)
		return ret;
	}

	def private ResourceSet getResourceSet(EObject type, EObject context) {
		var result = EcoreUtilN4.getResourceSet(type, context);
		if (result === null)
			throw new IllegalStateException("type or context must be contained in a ResourceSet")
		return result;
	}
}
