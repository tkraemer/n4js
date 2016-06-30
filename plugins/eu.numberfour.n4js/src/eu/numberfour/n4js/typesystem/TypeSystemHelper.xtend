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
package eu.numberfour.n4js.typesystem

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.ReturnStatement
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef
import eu.numberfour.n4js.ts.typeRefs.ConstructorTypeRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.StructuralTypeRef
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeArgument
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TEnum
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.TObjectPrototype
import eu.numberfour.n4js.ts.types.Type
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.utils.EcoreUtilN4
import eu.numberfour.n4js.utils.Log
import eu.numberfour.n4js.utils.StructuralTypesHelper
import it.xsemantics.runtime.RuleEnvironment
import java.util.Arrays
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * Utility methods used in the XSemantics type system. Must be injected.
 *
 * <p>Simple implementations are directly contained here. Complex operations such as join or meet are to
 * be implemented in strategy classes. For those operations, this class acts as a facade.</p>
 *
 * <p>EObject Reference Note: All methods prefer using {@link TypeUtils.copyIfContained(EObject)} instead
 * of {@link TypeUtils.copy(EObject)}. So, clients should follow this pattern as well.</p>
 *
 * Bibliography:
 * <a name="Pierce02a">[Pierce02a]</a> B. C. Pierce: Types and Programming Languages. The MIT Press, 1, 2002.
 */
@Log
@Singleton
class TypeSystemHelper {

	@Inject private N4JSTypeSystem ts;

	// *****************************************************************************************************
	//   forwarding of utility methods implemented in strategy classes
	// *****************************************************************************************************

	@Inject private DerivationComputer derivationComputer;

	@Inject private GenericsComputer genericsComputer;

	@Inject private SimplifyComputer simplifyComputer;
	@Inject private JoinComputer joinComputer;
	@Inject private MeetComputer meetComputer;

	@Inject private SubtypeComputer subtypeComputer;
	@Inject private StructuralTypingComputer structuralTypingComputer;


@Inject private StructuralTypesHelper structuralTypesHelper;
def StructuralTypesHelper getStructuralTypesHelper() {
	return structuralTypesHelper;
}
def StructuralTypingComputer getStructuralTypingComputer() {
	return structuralTypingComputer;
}


	def FunctionTypeExpression createSubstitutionOfFunctionTypeExprOrRef(RuleEnvironment G, FunctionTypeExprOrRef F) {
		derivationComputer.createSubstitutionOfFunctionTypeExprOrRef(G,F);
	}
	def FunctionTypeExpression createUpperBoundOfFunctionTypeExprOrRef(RuleEnvironment G, FunctionTypeExprOrRef F) {
		derivationComputer.createUpperBoundOfFunctionTypeExprOrRef(G,F);
	}
	def FunctionTypeExpression createLowerBoundOfFunctionTypeExprOrRef(RuleEnvironment G, FunctionTypeExprOrRef F) {
		derivationComputer.createLowerBoundOfFunctionTypeExprOrRef(G,F);
	}

	def void addSubstitutions(RuleEnvironment G, TypeRef typeRef) {
		genericsComputer.addSubstitutions(G,typeRef)
	}
	def void addSubstitutions(RuleEnvironment G, ParameterizedCallExpression callExpr, TypeRef targetTypeRef) {
		genericsComputer.addSubstitutions(G,callExpr,targetTypeRef)
	}
	def void addSubstitutions(RuleEnvironment G, ParameterizedPropertyAccessExpression accessExpr) {
		genericsComputer.addSubstitutions(G,accessExpr)
	}
	def TypeRef substTypeVariablesInStructuralMembers(RuleEnvironment G, StructuralTypeRef typeRef) {
		genericsComputer.substTypeVariablesInStructuralMembers(G, typeRef)
	}
	def void storePostponedSubstitutionsIn(RuleEnvironment G, StructuralTypeRef typeRef) {
		genericsComputer.storePostponedSubstitutionsIn(G, typeRef);
	}
	def void restorePostponedSubstitutionsFrom(RuleEnvironment G, StructuralTypeRef typeRef) {
		genericsComputer.restorePostponedSubstitutionsFrom(G, typeRef);
	}

	def TypeRef createUnionType(RuleEnvironment G, TypeRef... elements) {
		simplifyComputer.createUnionType(G,elements)
	}
	def TypeRef createIntersectionType(RuleEnvironment G, TypeRef... elements) {
		simplifyComputer.createIntersectionType(G,elements)
	}
	def <T extends ComposedTypeRef> TypeRef simplify(RuleEnvironment G, T composedType) {
		simplifyComputer.simplify(G,composedType)
	}

	/**
	 * Convenience method calling {@link join(RuleEnvironment, Iterable<TypeRef>)} with
	 * type references inside an array.
	 */
	 //TODO after java update bring back nullness analysis
//	@Nonnull
//	def TypeRef join(@Nonnull RuleEnvironment G, TypeRef... typeRefs) {
	def TypeRef join(RuleEnvironment G, TypeRef... typeRefs) {
		joinComputer.join(G, Arrays.asList(typeRefs))
	}

	/**
  	 * Returns the join, sometimes called least common super type (LCST),
	 * of the given types.
	 * @see JoinComputer#join(RuleEnvironment, Iterable<? extends TypeRef>)
	 */
	 //TODO after java update bring back nullness analysis
//	@Nonnull
//	def TypeRef join(@Nonnull RuleEnvironment G, Iterable<? extends TypeRef> typeRefsToJoin) {
	def TypeRef join(RuleEnvironment G, Iterable<? extends TypeRef> typeRefsToJoin) {
		joinComputer.join(G, typeRefsToJoin)
	}

	/**
	 * Convenience method calling {@link meet(RuleEnvironment, Iterable<TypeRef>)} with
	 * type references inside an array.
	 */
	 //TODO after java update bring back nullness analysis
//	@Nonnull
//	def TypeRef meet(@Nonnull RuleEnvironment G, TypeRef... typeRefs) {
	def TypeRef meet(RuleEnvironment G, TypeRef... typeRefs) {
		meetComputer.meet(G, Arrays.asList(typeRefs))
	}

	/**
  	 * Returns the meet (first common sub type) of the given types
  	 * @see  MeetComputer#meet(RuleEnvironment, Iterable<? extends TypeRef>)
	 */
	def TypeRef meet(RuleEnvironment G, Iterable<? extends TypeRef> typeRefs) {
		meetComputer.meet(G, typeRefs)
	}

	def boolean isSubtypeFunction(RuleEnvironment G, FunctionTypeExprOrRef left, FunctionTypeExprOrRef right) {
		return subtypeComputer.isSubtypeFunction(G, left, right)
	}

	def StructuralTypingResult isStructuralSubtype(RuleEnvironment G,TypeRef left, TypeRef right) {
		return structuralTypingComputer.isStructuralSubtype(G, left, right)
	}



	// *****************************************************************************************************
	//   small utility methods that do not have their own strategy class
	// *****************************************************************************************************

	public def boolean allEqualType(RuleEnvironment G, TypeRef... typeRefs) {
		val len = typeRefs.size;
		if(len>=2) {
			val firstRef = typeRefs.head;
			for(var i=1;i<len;i++) {
				if(!ts.equaltypeSucceeded(G, firstRef, typeRefs.get(i)))
					return false;
			}
		}
		return true;
	}

	public def TypeRef sanitizeTypeOfVariableFieldProperty(RuleEnvironment G, TypeArgument typeRaw) {
		if (typeRaw===null || typeRaw instanceof UnknownTypeRef) {
			return G.anyTypeRef;
		}
		val typeUB = ts.upperBound(G, typeRaw).value; // take upper bound to get rid of ExistentialTypeRef (if any)
		val declType = typeUB.declaredType
		if (declType===G.undefinedType || declType===G.nullType || declType===G.voidType) {
			// don't use these types to type variables, fields, properties -> replace with any
			return G.anyTypeRef;
		}
		return typeUB;
	}

	public def returnStatements(FunctionDefinition definition) {
		EcoreUtilN4.getAllContentsFiltered(definition,
			[!(it instanceof Expression || it instanceof FunctionDefinition)]).filter(ReturnStatement).filter[
			it.expression !== null]
	}

	/**
	 * If possible, a dynamic version of the given type ref is returned. If the type ref is already dynamic, it is returned.
	 * This is used for making all type refs dynamic in JavaScript mode.
	 */
	public def TypeRef makeDynamic(TypeRef typeRef) {
		if (! typeRef.dynamic) {
			if (typeRef instanceof ParameterizedTypeRef) {
				val ParameterizedTypeRef dyn = TypeUtils.copyIfContained(typeRef);
				dyn.dynamic = true;
				return dyn;
			}
		}
		return typeRef;
	}

	/** Return any explicitly given  this type.
	 * @param type either subtype of TFunction or subtypes of FunctionTypeExprOrRef can have a declared this type ("@This")
	 * @return declaredThisType if any, null in other cases.
	 */
	public static def TypeRef declaredThisType(Type type) {
		return switch ( type ) {
			TFunction: {
				type.declaredThisType
			}
			FunctionTypeExprOrRef: {
				type.declaredThisType
			}
			default:
				null
		}
	}

	/**
	 * Helper Method checking existence of "@StringBased" annotation.
	 */
	public static def boolean isStringBasedEnumeration (TEnum tEnum) {
		return tEnum.annotations.exists[it.name == AnnotationDefinition.STRING_BASED.name]
	}

	/**
	 * Binds and substitutes the given {@link ThisTypeRef this type reference} after wrapping the
	 * given rule environment.
	 *
	 * <p>
	 * For instance after passing a {@code ~~this} type reference into a method in the context of container
	 * {@code class A}, the type reference argument will be bound to {@code this[A]} and finally will be substituted
	 * with {@code ~~this[A]} type reference. That will be the return value of the method.
	 *
	 * @param G
	 * 		the rule environment that will be wrapped for the operation.
	 * @param location
	 * 		location within the AST for which to create a BoundThisTypeRef. Same as the argument 'location' of
	 * 		judgment 'thisTypeRef' in Xsemantics.
	 * @param typeRef
	 * 		type reference to substitute; this can either be an unbound ThisTypeRef or any other kind of TypeRef that
	 * 		contains one or more unbound ThisTypeRefs. Need not be contained in the AST (as usual for type references).
	 */
	public def bindAndSubstituteThisTypeRef(RuleEnvironment G, EObject location, TypeRef typeRef) {
		// create a BoundThisTypeRef for given location
		val boundThisTypeRef = ts.thisTypeRef(G, location).value;
		val localG = G.wrap;
		localG.addThisType(boundThisTypeRef);
		// substitute all unbound ThisTypeRefs with the newly created BoundThisTypeRef
		return ts.substTypeVariables(localG, typeRef).value as TypeRef;
	}

	/**
	 * Checks if a value of type <code>typeRef</code> is "callable", i.e. if it can be directly invoked using a call
	 * expression.
	 */
	def boolean isCallable(TypeRef typeRef, Resource resource) {
		if(isClassConstructorFunction(typeRef)) {
			// don't allow direct invocation of class constructors
			if(getCallableClassConstructorFunction(typeRef)!==null)
				return true; // exception: this is a class that provides a callable constructor function
			return false;
		}
		if(typeRef.declaredType instanceof TFunction)
			return true;
		if(typeRef instanceof FunctionTypeExprOrRef)
			return true;
		if(resource!==null) {
			val RuleEnvironment G = RuleEnvironmentExtensions.newRuleEnvironment(resource);
			if(ts.subtypeSucceeded(G, typeRef, G.functionTypeRef))
				return true;
			if(typeRef.dynamic && ts.subtypeSucceeded(G, G.functionTypeRef, typeRef))
				return true;
		}
		return false;
	}
	/**
	 * Checks if a value of type <code>typeRef</code> is a class constructor function.
	 */
	def boolean isClassConstructorFunction(TypeRef typeRef) {
		val declaredType = typeRef.declaredType;
		if(declaredType instanceof TMethod) {
			if(declaredType.isConstructor)
				return true;
		}
		if(typeRef instanceof FunctionTypeExprOrRef) {
			val ft = typeRef.functionType;
			if(ft instanceof TMethod) {
				if(ft.isConstructor)
					return true;
			}
		}
		if(typeRef instanceof ConstructorTypeRef) {
			val cls = typeRef.staticType;
			if(cls instanceof TClass || cls instanceof TObjectPrototype)
				return true;
		}
		return false;
	}
	def public TMethod getCallableClassConstructorFunction(TypeRef typeRef) {
		var Type type = null;
		val declaredType = typeRef.declaredType;
		if(declaredType instanceof TMethod) {
			if(declaredType.isConstructor)
				type = declaredType.containingType;
		}
		if(typeRef instanceof FunctionTypeExprOrRef) {
			val ft = typeRef.functionType;
			if(ft instanceof TMethod) {
				if(ft.isConstructor)
					type = ft.containingType;
			}
		}
		if(typeRef instanceof ClassifierTypeRef) {
			val cls = typeRef.staticType;
			if(cls instanceof TClass || cls instanceof TObjectPrototype)
				type = cls;
		}
		if(type instanceof ContainerType<?>) {
			return type.callableCtor;
		}
		return null;
	}
}
