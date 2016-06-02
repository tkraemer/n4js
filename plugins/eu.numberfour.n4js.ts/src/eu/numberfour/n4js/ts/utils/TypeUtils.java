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
package eu.numberfour.n4js.ts.utils;

import static com.google.common.collect.Iterables.concat;
import static java.util.Collections.singletonList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.xtext.EcoreUtil2;

import com.google.common.collect.Iterables;

import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope;
import eu.numberfour.n4js.ts.scoping.builtin.N4Scheme;
import eu.numberfour.n4js.ts.typeRefs.BaseTypeRef;
import eu.numberfour.n4js.ts.typeRefs.BoundThisTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ConstructorTypeRef;
import eu.numberfour.n4js.ts.typeRefs.DeferredTypeRef;
import eu.numberfour.n4js.ts.typeRefs.EnumTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ExistentialTypeRef;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeRef;
import eu.numberfour.n4js.ts.typeRefs.IntersectionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefStructural;
import eu.numberfour.n4js.ts.typeRefs.StaticBaseTypeRef;
import eu.numberfour.n4js.ts.typeRefs.StructuralTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRefStructural;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;
import eu.numberfour.n4js.ts.typeRefs.TypeVariableMapping;
import eu.numberfour.n4js.ts.typeRefs.UnionTypeExpression;
import eu.numberfour.n4js.ts.typeRefs.Wildcard;
import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.PrimitiveType;
import eu.numberfour.n4js.ts.types.TClass;
import eu.numberfour.n4js.ts.types.TClassifier;
import eu.numberfour.n4js.ts.types.TEnum;
import eu.numberfour.n4js.ts.types.TEnumLiteral;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TFormalParameter;
import eu.numberfour.n4js.ts.types.TFunction;
import eu.numberfour.n4js.ts.types.TGetter;
import eu.numberfour.n4js.ts.types.TInterface;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TMethod;
import eu.numberfour.n4js.ts.types.TObjectPrototype;
import eu.numberfour.n4js.ts.types.TSetter;
import eu.numberfour.n4js.ts.types.TStructMember;
import eu.numberfour.n4js.ts.types.TStructuralType;
import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.TypeVariable;
import eu.numberfour.n4js.ts.types.TypesFactory;
import eu.numberfour.n4js.ts.types.TypingStrategy;
import eu.numberfour.n4js.ts.types.UndefModifier;
import eu.numberfour.n4js.ts.types.VoidType;
import eu.numberfour.n4js.utils.RecursionGuard;

/**
 * Static utility methods for type and type ref handling, for non-static utility methods see {@link TypeHelper}.
 */
public class TypeUtils {

	/**
	 * Creates a TSetter. If 'fparName' or 'fparTypeRef' is non-null, a TFormalParameter will be created as well. All
	 * arguments may be <code>null</code>.
	 */
	public static TSetter createTSetter(String name, String fparName, TypeRef fparTypeRef) {
		final TSetter ph = TypesFactory.eINSTANCE.createTSetter();
		ph.setName(name);
		if (fparName != null || fparTypeRef != null) {
			final TFormalParameter fpar = TypesFactory.eINSTANCE.createTFormalParameter();
			fpar.setName(fparName);
			fpar.setTypeRef(TypeUtils.copyIfContained(TypeRefsFactory.eINSTANCE.createUnknownTypeRef()));
			ph.setFpar(fpar);
		}
		return ph;
	}

	/**
	 * Same as {@link #wrapTypeInTypeRef(Type, TypeArgument...)}, but can handle one more special case (creating a
	 * {@link ParameterizedTypeRefStructural} from a {@link TStructuralType}) because it is supplied with a built-in
	 * type scope.
	 */
	public static TypeRef wrapTypeInTypeRef(BuiltInTypeScope builtinTypeScope, Type type, TypeArgument... typeArgs) {
		if (type instanceof TStructuralType) {
			return createParameterizedTypeRefStructural(builtinTypeScope.getObjectType(), TypingStrategy.STRUCTURAL,
					(TStructuralType) type);
		}
		return wrapTypeInTypeRef(type, typeArgs);
	}

	/**
	 * Creates a type reference by delegating to one of the specific <code>#createXYZTypeRef()</code> methods, depending
	 * on the given type. For example, if given a {@link TEnum}, an {@link EnumTypeRef} will be created. By default, a
	 * {@link ParameterizedTypeRef} will be created.
	 * <p>
	 * Note that this method does not cover all special cases, e.g. creating union or intersection types or creating
	 * {@link BoundThisTypeRef}s.
	 */
	public static TypeRef wrapTypeInTypeRef(Type type, TypeArgument... typeArgs) {
		if (type instanceof TEnum) {
			return createEnumTypeRef((TEnum) type);
		} else if (type instanceof TEnumLiteral) {
			return createEnumTypeRef((TEnum) type.eContainer());
		} else if (type instanceof TClass) {
			final TClass declaredTypeCasted = (TClass) type;
			if (declaredTypeCasted.isAbstract()) {
				return createClassifierTypeRef(declaredTypeCasted, typeArgs);
			} else {
				return createConstructorTypeRef(declaredTypeCasted, typeArgs);
			}
		} else if (type instanceof TInterface) {
			return createClassifierTypeRef(type, typeArgs);
		} else if (type instanceof TObjectPrototype) {
			return createConstructorTypeRef(type, typeArgs);
		} else {
			return createTypeRef(type, typeArgs);
		}
	}

	/**
	 * Creates a new type reference (without a container) referencing given declared type and with (optional) type
	 * arguments. The type arguments are copied if they have a container in order to avoid containment problems. If the
	 * declared type is a TFunction, a FunctionTypeRef is created.
	 */
	// public static ParameterizedTypeRef createTypeRef(@Nonnull Type declaredType, @Nonnull TypeArgument... typeArgs) {
	public static ParameterizedTypeRef createTypeRef(Type declaredType, TypeArgument... typeArgs) {
		return createTypeRef(declaredType, TypingStrategy.DEFAULT, typeArgs);
	}

	/**
	 * Same as {@link #createTypeRef(Type, TypeArgument...)}, but will create a {@link ParameterizedTypeRefStructural}
	 * in case parameter <code>useSiteTypingStrategy</code> is set to a different value than
	 * {@link TypingStrategy#DEFAULT DEFAULT}.
	 */
	// TODO after java update bring back nullness analysis
	// public static ParameterizedTypeRef createTypeRef(@Nonnull Type declaredType,
	// @Nonnull TypingStrategy useSiteTypingStrategy, @Nonnull TypeArgument... typeArgs) {
	public static ParameterizedTypeRef createTypeRef(Type declaredType,
			TypingStrategy typingStrategy, TypeArgument... typeArgs) {
		final ParameterizedTypeRef ref;
		if (declaredType instanceof TFunction) {
			ref = TypeRefsFactory.eINSTANCE.createFunctionTypeRef();
			// } else if (declaredType instanceof TStructuralType) {
			// throw new IllegalArgumentException("a TStructuralType should not be used as declared type of a TypeRef");
		} else if (typingStrategy != TypingStrategy.DEFAULT && typingStrategy != TypingStrategy.NOMINAL) {
			ref = TypeRefsFactory.eINSTANCE.createParameterizedTypeRefStructural();
		} else {
			ref = TypeRefsFactory.eINSTANCE.createParameterizedTypeRef();
		}
		ref.setDefinedTypingStrategy(typingStrategy);
		ref.setDeclaredType(declaredType);
		for (TypeArgument typeArg : typeArgs) {
			ref.getTypeArgs().add(TypeUtils.copyIfContained(typeArg));
		}
		return ref;
	}

	/**
	 * Creates a structural type reference for the given TStructuralType, e.g. <code>~Object with { ... }</code>.
	 * <p>
	 * For important details on structural type references, see {@link StructuralTypeRef}.
	 */
	public static ParameterizedTypeRefStructural createParameterizedTypeRefStructural(Type declaredType,
			TypingStrategy typingStrategy, TStructuralType structuralType) {
		final ParameterizedTypeRefStructural ref = TypeRefsFactory.eINSTANCE.createParameterizedTypeRefStructural();
		ref.setDeclaredType(declaredType);
		ref.setDefinedTypingStrategy(typingStrategy);
		ref.setStructuralType(structuralType);
		return ref;
	}

	/**
	 * Creates a structural type reference for the given, programmatically generated members. Use this method only as a
	 * last resort, as explained {@link StructuralTypeRef here}.
	 * <p>
	 * For important details on structural type references, see {@link StructuralTypeRef}.
	 */
	public static ParameterizedTypeRefStructural createParameterizedTypeRefStructural(Type declaredType,
			TypingStrategy typingStrategy, TStructMember... members) {
		final ParameterizedTypeRefStructural ref = TypeRefsFactory.eINSTANCE.createParameterizedTypeRefStructural();
		ref.setDeclaredType(declaredType);
		ref.setDefinedTypingStrategy(typingStrategy);
		ref.getGenStructuralMembers().addAll(Arrays.asList(members));
		return ref;
	}

	/**
	 * Creates new type reference for classifier. if the declared type is TFunction a FunctionTypeRef is created. If
	 * declared type is TClassifier than ClassifierTypeRef is created (i.e. <code>type{A}</code> in N4JS code)
	 */
	// TODO after java update bring back nullness analysis
	// public static TypeRef createClassifierTypeRef(@Nonnull Type declaredType, @Nonnull TypeArgument... typeArgs) {
	public static TypeRef createClassifierTypeRef(Type declaredType, TypeArgument... typeArgs) {
		TypeRef typeRef = null;
		if (declaredType instanceof TFunction) {
			// TODO is this correct
			FunctionTypeRef ref = TypeRefsFactory.eINSTANCE.createFunctionTypeRef();
			ref.setDeclaredType(declaredType);
			for (TypeArgument typeArg : typeArgs) {
				ref.getTypeArgs().add(TypeUtils.copyIfContained(typeArg));
			}
			typeRef = ref;
		} else if (declaredType instanceof TClassifier) {
			TClassifier tClassifier = (TClassifier) declaredType;
			ClassifierTypeRef ref = TypeRefsFactory.eINSTANCE.createClassifierTypeRef();
			ref.setStaticTypeRef(createTypeRef(tClassifier, typeArgs));
			typeRef = ref;
		}
		return typeRef;
	}

	/**
	 * Creates new type reference for constructor. if the declared type is TFunction a FunctionTypeRedf is created. If
	 * declared type is TClassifier than ConstructorTypeRef is created (i.e. <code>constructor{A}</code> in N4JS code)
	 */
	// TODO after java update bring back nullness analysis
	// public static TypeRef createConstructorTypeRef(@Nonnull Type declaredType, @Nonnull TypeArgument... typeArgs) {
	public static TypeRef createConstructorTypeRef(Type declaredType, TypeArgument... typeArgs) {
		TypeRef typeRef = null;
		if (declaredType instanceof TFunction) {
			// TODO is this correct?
			FunctionTypeRef ref = TypeRefsFactory.eINSTANCE.createFunctionTypeRef();
			ref.setDeclaredType(declaredType);
			for (TypeArgument typeArg : typeArgs) {
				ref.getTypeArgs().add(TypeUtils.copyIfContained(typeArg));
			}
			typeRef = ref;
		} else if (declaredType instanceof TClassifier) {
			TClassifier tClassifier = (TClassifier) declaredType;
			ConstructorTypeRef ref = TypeRefsFactory.eINSTANCE.createConstructorTypeRef();
			ref.setStaticTypeRef(createTypeRef(tClassifier, typeArgs));
			typeRef = ref;
		}
		return typeRef;
	}

	/**
	 * Creates EnumTypeRef
	 */
	// TODO after java update bring back nullness analysis
	// public static TypeRef createEnumTypeRef(@Nonnull TEnum enumType) {
	public static TypeRef createEnumTypeRef(TEnum enumType) {
		if (enumType == null) {
			throw new NullPointerException("enumType");
		}
		EnumTypeRef typeRef = TypeRefsFactory.eINSTANCE.createEnumTypeRef();
		typeRef.setEnumType(enumType);
		return typeRef;
	}

	/**
	 * Creates a new union type with the given elements. The elements are copied if they have a container. The created
	 * union type may contain duplicates or nested union types, that is, it is not simplified! Thus, the returned type
	 * is expected to be processed further!
	 *
	 * @see eu.numberfour.n4js.typesystem.TypeSystemHelper.simplify(UnionTypeExpression)
	 */
	@SuppressWarnings("javadoc")
	public static UnionTypeExpression createNonSimplifiedUnionType(Iterable<? extends TypeRef> elements) {
		UnionTypeExpression unionType = TypeRefsFactory.eINSTANCE.createUnionTypeExpression();
		EList<TypeRef> unionElements = unionType.getTypeRefs();

		for (TypeRef e : elements) {
			unionElements.add(TypeUtils.copyIfContained(e));
		}
		return unionType;
	}

	/**
	 * Convenience method, delegates to {@link #createNonSimplifiedUnionType(Iterable)}
	 */
	public static UnionTypeExpression createNonSimplifiedUnionType(TypeRef... elements) {
		return createNonSimplifiedUnionType(Arrays.asList(elements));
	}

	/**
	 * Creates a new ExistentialTypeRef.
	 */
	public static ExistentialTypeRef createExistentialTypeRef(TypeVariable typeVar, Wildcard wildcard) {
		final ExistentialTypeRef etr = TypeRefsFactory.eINSTANCE.createExistentialTypeRef();
		etr.setWildcard(wildcard);
		etr.setBoundTypeVariable(typeVar);
		return etr;
	}

	/**
	 * If the given type argument is a {@link Wildcard}, then a new {@link ExistentialTypeRef} will be created and
	 * returned; otherwise the given type argument will be returned without change.
	 */
	public static TypeRef resolveWildcard(TypeVariable typeVar, TypeArgument typeArg) {
		if (typeArg instanceof Wildcard)
			return createExistentialTypeRef(typeVar, (Wildcard) typeArg);
		else
			// note: typeArg must be a TypeRef now, because Wildcard was the only other option below TypeArgument
			return (TypeRef) typeArg;
	}

	/**
	 * Creates a new this type reference bound to the given actual type. This method also sets the typing strategy and
	 * structural members. The returned type is normalized in terms of only the actual type is never structurally typed.
	 * E.g., {@code ~this[~C]} is normalized to {@code ~this[C]}.
	 *
	 * cf. N4JS Spec, Constraints 63 (Normalized This Type)
	 *
	 * @param actualThisTypeRef
	 *            must not be null
	 */
	public static BoundThisTypeRef createBoundThisTypeRef(ParameterizedTypeRef actualThisTypeRef) {
		if (actualThisTypeRef == null) {
			throw new NullPointerException("Actual this type must not be null!");
		}
		BoundThisTypeRef boundThisTypeRef = TypeRefsFactory.eINSTANCE.createBoundThisTypeRef();
		ParameterizedTypeRef clonedActualThisType = TypeUtils.copy(actualThisTypeRef);
		boundThisTypeRef.setActualThisTypeRef(clonedActualThisType);
		boundThisTypeRef.setDefinedTypingStrategy(TypingStrategy.NOMINAL); // IDEBUG-161: Constraints 66 (Type Inference
		// Heuristic for This-Keyword)
		if (actualThisTypeRef instanceof ParameterizedTypeRefStructural) {
			// set structural typing info
			copyStructuralTypingInfo(boundThisTypeRef, (ParameterizedTypeRefStructural) actualThisTypeRef);
			// and set clonedActualThisType to nominal type
			((ParameterizedTypeRefStructural) clonedActualThisType).getAstStructuralMembers().clear();
			((ParameterizedTypeRefStructural) clonedActualThisType).getGenStructuralMembers().clear();
			((ParameterizedTypeRefStructural) clonedActualThisType).setStructuralType(null);
			((ParameterizedTypeRefStructural) clonedActualThisType).setDefinedTypingStrategy(TypingStrategy.NOMINAL);
		}

		return boundThisTypeRef;
	}

	/**
	 * Introducing a bound this-typeref for an enumTypeRef
	 *
	 * @param actualThisTypeRef
	 *            the EnumTypeRef
	 * @return a bound type ref version
	 */
	public static BoundThisTypeRef createBoundThisTypeRef(EnumTypeRef actualThisTypeRef) { // added with IDEBUG-330
		if (actualThisTypeRef == null) {
			throw new NullPointerException("Actual this type must not be null!");
		}
		BoundThisTypeRef boundThisTypeRef = TypeRefsFactory.eINSTANCE.createBoundThisTypeRef();
		ParameterizedTypeRef parameterizedEnumTypeRef = createTypeRef(actualThisTypeRef.getEnumType());
		boundThisTypeRef.setActualThisTypeRef(parameterizedEnumTypeRef);
		boundThisTypeRef.setDefinedTypingStrategy(TypingStrategy.NOMINAL);

		return boundThisTypeRef;
	}

	/**
	 * Creates a new this type reference bound to the given actual type.
	 *
	 * @param actualThisTypeRef
	 *            must not be null
	 */
	public static BoundThisTypeRef createBoundThisTypeRefStructural(ParameterizedTypeRef actualThisTypeRef,
			ThisTypeRefStructural thisTypeStructural) {
		if (actualThisTypeRef == null) {
			throw new NullPointerException("Actual this type must not be null!");
		}
		BoundThisTypeRef boundThisTypeRef = TypeRefsFactory.eINSTANCE.createBoundThisTypeRef();
		boundThisTypeRef.setActualThisTypeRef(TypeUtils.copyIfContained(actualThisTypeRef));
		// set structural typing info
		copyStructuralTypingInfo(boundThisTypeRef, thisTypeStructural);
		return boundThisTypeRef;
	}

	/**
	 * from type{S} to type{this[S]}, as Part of IDE-785
	 *
	 * @param actualThisTypeRef
	 *            must not be null, must not contain a this unbound-this-type-ref.
	 */
	public static ClassifierTypeRef createClassifierBoundThisTypeRef(ClassifierTypeRef actualThisTypeRef) {
		if (actualThisTypeRef == null) {
			throw new NullPointerException("Actual this type must not be null!");
		}
		ClassifierTypeRef classifierBoundThisTypeRef = TypeRefsFactory.eINSTANCE.createClassifierTypeRef();
		StaticBaseTypeRef staticTypeRef = actualThisTypeRef.getStaticTypeRef();
		final BoundThisTypeRef boundThisTypeRef;
		if (staticTypeRef instanceof ParameterizedTypeRef) {
			boundThisTypeRef = createBoundThisTypeRef((ParameterizedTypeRef) staticTypeRef);
		} else if (staticTypeRef instanceof BoundThisTypeRef) {
			boundThisTypeRef = (BoundThisTypeRef) staticTypeRef;
		} else {
			// invalid use
			throw new IllegalArgumentException(
					"Cannot turn unbound type{this} into type{this[X]}, must be called with type{X}!");
		}
		classifierBoundThisTypeRef.setStaticTypeRef(boundThisTypeRef);
		// TODO is there anything else to copy ?
		return classifierBoundThisTypeRef;
	}

	/**
	 * Creates the corresponding plain {@link ParameterizedTypeRef} for a given {@link BoundThisTypeRef}. For example,
	 * <code>~~this[C]</code> will be turned into <code>~~C</code>.
	 */
	public static ParameterizedTypeRef createResolvedThisTypeRef(BoundThisTypeRef boundThisTypeRef) {
		if (boundThisTypeRef == null) {
			throw new NullPointerException("Bound this type must not be null!");
		}
		if (boundThisTypeRef.getActualThisTypeRef() == null) {
			throw new NullPointerException("Actual this type of the provided bound this type must not be null!");
		}
		final List<TypeArgument> targsAsList = boundThisTypeRef.getActualThisTypeRef().getTypeArgs();
		final TypeArgument[] targs = targsAsList.toArray(new TypeArgument[targsAsList.size()]);
		final ParameterizedTypeRef resolvedTypeRef = createTypeRef(
				boundThisTypeRef.getActualThisTypeRef().getDeclaredType(),
				boundThisTypeRef.getTypingStrategy(),
				targs);
		if (!boundThisTypeRef.getTypeArgs().isEmpty()) {
			resolvedTypeRef.getTypeArgs().clear();
			resolvedTypeRef.getTypeArgs().addAll(TypeUtils.copyAll(boundThisTypeRef.getTypeArgs()));
		}
		// set structural typing info
		if (resolvedTypeRef instanceof ParameterizedTypeRefStructural)
			copyStructuralTypingInfo((ParameterizedTypeRefStructural) resolvedTypeRef, boundThisTypeRef);
		// copy other properties
		copyTypeModifiers(resolvedTypeRef, boundThisTypeRef);
		return resolvedTypeRef;
	}

	/**
	 * @param ct
	 *            referencing a BoundThisTypeRef
	 * @return copy with staticTypeRef pointing to the upper bound of the former ct.staticTypeRef
	 */
	public static ClassifierTypeRef createResolvedClassifierTypeRef(ClassifierTypeRef ct) {
		// as part of IDE-785
		StaticBaseTypeRef replacement = ct.getStaticTypeRef();
		if (ct.getStaticTypeRef() instanceof BoundThisTypeRef) {
			replacement = createResolvedThisTypeRef((BoundThisTypeRef) ct.getStaticTypeRef());
		}

		ClassifierTypeRef resolved = TypeRefsFactory.eINSTANCE.createClassifierTypeRef();
		resolved.setStaticTypeRef(replacement);
		return resolved;
	}

	/**
	 * Enforces nominal typing if possible.
	 */
	public static TypeRef enforceNominalTyping(TypeRef rawT) {
		if (rawT.getTypingStrategy() == TypingStrategy.NOMINAL || rawT.isUseSiteStructuralTyping()) {
			return rawT;
		} else {
			TypeRef T = TypeUtils.copy(rawT);
			if (T instanceof StructuralTypeRef) {
				((StructuralTypeRef) T).setTypingStrategy(TypingStrategy.NOMINAL);
			} else if (T instanceof ParameterizedTypeRef) {
				((ParameterizedTypeRef) T).setDefinedTypingStrategy(TypingStrategy.NOMINAL);
			}
			return T;
		}
	}

	/**
	 * Copies all properties related to structural typing from 'src' to 'dest', taking care of the special handling
	 * required for 'astStructuralTypeRef'.
	 * <p>
	 * See {@link StructuralTypeRef} for details on the special handling of property 'astStructuralMembers'.
	 */
	private static void copyStructuralTypingInfo(StructuralTypeRef dest, StructuralTypeRef src) {
		dest.setTypingStrategy(src.getTypingStrategy());
		dest.getAstStructuralMembers().clear(); // need not copy those (if 'src' is an AST node, it will have a
		// TStructuralType containing the same members!)
		dest.setStructuralType(src.getStructuralType()); // don't copy because 'structuralType' is a cross-reference
		dest.getGenStructuralMembers().clear();
		dest.getGenStructuralMembers().addAll(TypeUtils.copyAll(src.getGenStructuralMembers()));
	}

	/**
	 * Copies additional type modifiers such as undefModifier, nullModifier and dynamic. This is usually not required as
	 * whole references are copied, but in case of type variable substitution or similar cases, this might be necessary.
	 */
	public static void copyTypeModifiers(TypeRef target, TypeRef source) {
		target.setUndefModifier(source.getUndefModifier());
		target.setNullModifier(source.getNullModifier());

		if (target instanceof BaseTypeRef) {
			((BaseTypeRef) target).setDynamic(target.isDynamic() || source.isDynamic());
		}
	}

	/**
	 * Creates a new {@link DeferredTypeRef}.
	 */
	public static DeferredTypeRef createDeferredTypeRef() {
		return TypeRefsFactory.eINSTANCE.createDeferredTypeRef();
	}

	/**
	 * Tells if the given object contains a {@link DeferredTypeRef}.
	 */
	public static boolean containsDeferredTypeRefs(EObject object) {
		final Iterator<EObject> i = object.eAllContents();
		while (i.hasNext()) {
			Object local = i.next();
			if (local instanceof DeferredTypeRef) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the given object contains a {@link DeferredTypeRef} and, if so, throws an exception.
	 */
	public static void assertNoDeferredTypeRefs(EObject object) {
		if (containsDeferredTypeRefs(object)) {
			throw new IllegalStateException("found a DeferredTypeRef in " + object.eResource().getURI());
		}
	}

	/**
	 * Creates a new intersection type with the given elements. The elements are copied if they have a container. The
	 * created intersection type may contain duplicates or nested intersection types, that is, it is not simplified!
	 * Thus, the returned type is expected to be processed further!
	 *
	 * @see eu.numberfour.n4js.typesystem.TypeSystemHelper.simplify(UnionTypeExpression)
	 */
	@SuppressWarnings("javadoc")
	public static IntersectionTypeExpression createNonSimplifiedIntersectionType(Iterable<? extends TypeRef> elements) {
		IntersectionTypeExpression intersectionType = TypeRefsFactory.eINSTANCE.createIntersectionTypeExpression();
		EList<TypeRef> intersectionElements = intersectionType.getTypeRefs();

		for (TypeRef e : elements) {
			intersectionElements.add(TypeUtils.copyIfContained(e));
		}
		return intersectionType;
	}

	/**
	 * Convenience method, delegates to {@link #createNonSimplifiedIntersectionType(Iterable)}.
	 */
	public static IntersectionTypeExpression createNonSimplifiedIntersectionType(TypeRef... elements) {
		return createNonSimplifiedIntersectionType(Arrays.asList(elements));
	}

	/**
	 * Creates a new function type expression with the given attribtues, attributes are copied if contained.
	 */
	public static FunctionTypeExpression createFunctionTypeExpression(
			TypeRef declaredThisType, List<TypeVariable> ownedTypeVars,
			List<TFormalParameter> fpars, TypeRef returnTypeRef) {
		final FunctionTypeExpression f = TypeRefsFactory.eINSTANCE.createFunctionTypeExpression();

		f.setDeclaredThisType(TypeUtils.copyIfContained(declaredThisType));
		ownedTypeVars.stream().forEachOrdered(tv -> f.getOwnedTypeVars().add(TypeUtils.copyIfContained(tv)));
		fpars.stream().forEachOrdered(tp -> f.getFpars().add(TypeUtils.copyIfContained(tp)));
		f.setReturnTypeRef(TypeUtils.copyIfContained(returnTypeRef));
		return f;
	}

	/**
	 * Creates a new type variable mapping with the given type variable and argument. The type argument is copied if
	 * (and only if) it is contained.
	 */
	public static TypeVariableMapping createTypeVariableMapping(TypeVariable typeVar, TypeArgument typeArg) {
		final TypeVariableMapping result = TypeRefsFactory.eINSTANCE.createTypeVariableMapping();
		result.setTypeVar(typeVar);
		result.setTypeArg(TypeUtils.copyIfContained(typeArg));
		return result;
	}

	/**
	 * Type references may be nested within other type references, e.g. the members of a union type. This method will
	 * return the outermost type reference that contains the given type reference but is not itself contained in another
	 * type reference, or the given type reference if it is not contained in another type reference.
	 * <p>
	 * Returns <code>null</code> if given <code>null</code>.
	 */
	public static TypeRef getRootTypeRef(TypeRef typeRef) {
		if (typeRef != null) {
			while (true) {
				final TypeRef nextOuter = EcoreUtil2.getContainerOfType(typeRef.eContainer(), TypeRef.class);
				if (nextOuter == null)
					break;
				typeRef = nextOuter;
			}
		}
		return typeRef;
	}

	/**
	 * Convenience method, returns directly declared super types (class, role, interface) of a classifier. May return an
	 * empty list but never null. Order is always super class, super roles, super interfaces. For all non-classifiers
	 * this method returns an empty list.
	 */
	// TODO after java update bring back nullness analysis
	// @Nonnull
	// public static Iterable<ParameterizedTypeRef> declaredSuperTypes(@Nullable final Type type) {
	@SuppressWarnings("unchecked")
	public static Iterable<? extends ParameterizedTypeRef> declaredSuperTypes(final Type type) {
		if (type instanceof TClass) {
			final TClass c = (TClass) type;
			if (c.getSuperClassRef() != null) {
				return Iterables.concat(concat(singletonList(c.getSuperClassRef()),
						c.getImplementedInterfaceRefs()));
			} else {
				return c.getImplementedInterfaceRefs();
			}
		}
		if (type instanceof TInterface) {
			final TInterface r = (TInterface) type;
			return r.getSuperInterfaceRefs();
		}
		if (type instanceof TypeVariable) {
			return ((TypeVariable) type).getDeclaredUpperBounds();
		}
		if (type instanceof PrimitiveType) {
			PrimitiveType assignmentCompatible = ((PrimitiveType) type).getAssignmentCompatible();
			if (assignmentCompatible != null) {
				ParameterizedTypeRef typeRef = TypeRefsFactory.eINSTANCE.createParameterizedTypeRef();
				typeRef.setDeclaredType(assignmentCompatible);
				return Collections.singletonList(typeRef);
			}
		}
		if (type instanceof TObjectPrototype) {
			// IDE-1221 string based enums: traversing super types for object prototypes as well
			TObjectPrototype tObjectPrototype = (TObjectPrototype) type;
			if (tObjectPrototype.getSuperType() != null) {
				return singletonList(tObjectPrototype.getSuperType());
			}
		}
		return Collections.emptyList();
	}

	/**
	 * Check if superTypeCandidate is raw super type of Type
	 */
	public static boolean isRawSuperType(Type type, Type superTypeCandidate) {
		return isRawSuperType(type, superTypeCandidate, new RecursionGuard<Type>());
	}

	private static boolean isRawSuperType(Type type, Type superTypeCandidate, RecursionGuard<Type> guard) {
		if (type == superTypeCandidate) {
			return true;
		}
		if (type == null) {
			return false;
		}
		if (guard.tryNext(type)) {
			if (type instanceof TClass) {
				final TClass c = (TClass) type;
				if (isRawSuperType(c.getSuperClassRef(), superTypeCandidate, guard)) {
					return true;
				}
				if (isRawSuperType(c.getImplementedInterfaceRefs(), superTypeCandidate, guard)) {
					return true;
				}
				return false;
			}
			if (type instanceof TInterface) {
				final TInterface r = (TInterface) type;
				if (isRawSuperType(r.getSuperInterfaceRefs(), superTypeCandidate, guard)) {
					return true;
				}
				return false;
			}

			if (type instanceof TypeVariable) {
				TypeVariable v = (TypeVariable) type;
				if (isRawSuperType(v.getDeclaredUpperBounds(), superTypeCandidate, guard)) {
					return true;
				}
				return false;
			}
			if (type instanceof PrimitiveType) {
				PrimitiveType assignmentCompatible = ((PrimitiveType) type).getAssignmentCompatible();
				if (isRawSuperType(assignmentCompatible, superTypeCandidate, guard)) {
					return true;
				}
			}
		}
		return false;

	}

	private static boolean isRawSuperType(Iterable<? extends TypeRef> types, Type superTypeCandidate,
			RecursionGuard<Type> guard) {
		for (TypeRef type : types) {
			if (isRawSuperType(type, superTypeCandidate, guard)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isRawSuperType(TypeRef type, Type superTypeCandidate, RecursionGuard<Type> guard) {
		if (type == null)
			return false;
		return isRawSuperType(type.getDeclaredType(), superTypeCandidate, guard);
	}

	/**
	 * Convenience method that returns the declared or implicit upper bounds of a Wildcard as returned by
	 * {@link Wildcard#getDeclaredOrImplicitUpperBounds()} as a single TypeRef. If there is more than one upper bound
	 * (occurs only in the case of implicit upper bounds), then an intersection type is created.
	 */
	public static TypeRef getDeclaredOrImplicitUpperBound(Wildcard w) {
		final EList<TypeRef> implicitUBs = w.getDeclaredOrImplicitUpperBounds();
		final int count = implicitUBs.size();
		if (count == 1) {
			return implicitUBs.get(0);
		} else if (count >= 2) {
			return createNonSimplifiedIntersectionType(implicitUBs);
		}
		return null; // no implicit upper bound
	}

	/**
	 * Convenience method returning the type of the given member. Returns the return type for getters and methods and
	 * the type of the single fpar for setters.
	 */
	public static TypeRef getMemberTypeRef(TMember m) {
		if (m == null)
			return null;
		else if (m instanceof TField)
			return ((TField) m).getTypeRef();
		else if (m instanceof TGetter)
			return ((TGetter) m).getDeclaredTypeRef(); // TODO should use actual(!) type here, but not available yet!
		else if (m instanceof TSetter)
			return ((TSetter) m).getDeclaredTypeRef(); // note: this returns typeRef of the fpar
		else if (m instanceof TMethod)
			return ((TMethod) m).getReturnTypeRef();
		else
			throw new IllegalArgumentException("unknown sub-class of TMember: " + m.getClass().getName());
	}

	/**
	 * Convenience method setting the type of the given member. Sets the return type in case of getters and methods and
	 * the type of the single fpar in case of setters. If the setter does not have an fpar yet, it will be created.
	 */
	public static void setMemberTypeRef(TMember m, TypeRef typeRef) {
		typeRef = TypeUtils.copyIfContained(typeRef);
		if (m instanceof TField)
			((TField) m).setTypeRef(typeRef);
		else if (m instanceof TGetter)
			((TGetter) m).setDeclaredTypeRef(typeRef);
		else if (m instanceof TSetter) {
			final TSetter s = (TSetter) m;
			if (s.getFpar() == null)
				s.setFpar(TypesFactory.eINSTANCE.createTFormalParameter());
			s.getFpar().setTypeRef(typeRef);
		} else if (m instanceof TMethod)
			((TMethod) m).setReturnTypeRef(typeRef);
		else if (m != null)
			throw new IllegalArgumentException("unknown sub-class of TMember: " + m.getClass().getName());
	}

	/**
	 * Returns true if the (unparameterized) declared type of the given type ref or, in case of a union or intersection,
	 * the declared type of a contained element type ref is the same as the given declared type. In all cases,
	 * parameterization is ignored and the types are compared via identity (not equals!).
	 *
	 * @param typeRef
	 *            the typeRef in which the declared type of elementTypeRef is searched for
	 * @param declaredType
	 *            the type for which to look for
	 */
	public static boolean isOrContainsType(TypeRef typeRef, Type declaredType) {
		if (typeRef == null) {
			return false;
		}
		if (typeRef instanceof ComposedTypeRef) {
			return ((ComposedTypeRef) typeRef).getTypeRefs().stream()
					.anyMatch(element -> isOrContainsType(element, declaredType));
		}
		if (typeRef.getDeclaredType() == null) {
			return false;
		}
		return typeRef.getDeclaredType() == declaredType;

	}

	/**
	 * Returns true iff the first argument (or one of its components) is a {@link TypeRef} of the class given by the
	 * second argument (or is a subclass thereof).
	 *
	 * @param typeRef
	 *            the typeRef to be searched for (along with its components if any)
	 * @param typeOfTypeRef
	 *            the class representing the {@link TypeRef} to look for
	 */
	public static boolean isOrContainsTypeRefOfType(TypeRef typeRef, final Class<? extends TypeRef> typeOfTypeRef) {
		if (typeRef == null) {
			return false;
		}
		if (typeOfTypeRef.isInstance(typeRef)) {
			return true;
		}
		if (typeRef instanceof ComposedTypeRef) {
			return ((ComposedTypeRef) typeRef).getTypeRefs().stream()
					.anyMatch(element -> isOrContainsTypeRefOfType(element, typeOfTypeRef));
		}
		return false;
	}

	/**
	 * Returns true iff the given typeRef references one of the given type variables or contains a reference to one of
	 * the given type variables.
	 * <p>
	 * If no type variables are given, then this method checks for <em>any</em> type variables, i.e. it returns true iff
	 * the given typeRef references a type variable or contains a reference to a type variable.
	 */
	public static boolean isOrContainsRefToTypeVar(EObject obj, TypeVariable... typeVars) {
		if (obj == null)
			return false;
		if (isRefToTypeVar(obj, typeVars))
			return true;
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(obj.eAllContents(), 0), false).anyMatch(
				child -> {
					return isRefToTypeVar(child, typeVars);
				});
	}

	/**
	 * Returns true iff the given typeRef references one of the given type variables.
	 * <p>
	 * If no type variables are given, then this method checks for <em>any</em> type variables, i.e. it returns true iff
	 * the given typeRef references a type variable.
	 */
	private static boolean isRefToTypeVar(EObject obj, TypeVariable... typeVars) {
		// special case: for StructuralTypeRef we have to consider the TStructuralType as well
		if (obj instanceof StructuralTypeRef
				// FIXME should better use #getStructuralMembers() in next line???
				&& isOrContainsRefToTypeVar(((StructuralTypeRef) obj).getStructuralType(), typeVars))
			return true;
		return obj instanceof TypeRef
				&& ((TypeRef) obj).getDeclaredType() instanceof TypeVariable
				&& (typeVars.length == 0 || org.eclipse.xtext.util.Arrays.contains(
						typeVars, ((TypeRef) obj).getDeclaredType()));
	}

	/**
	 * Returns all type variables referenced by the given object or its contents.
	 */
	public static Set<TypeVariable> getReferencedTypeVars(EObject obj) {
		return collectReferencedTypeVars(obj, true, new LinkedHashSet<>());
	}

	private static Set<TypeVariable> collectReferencedTypeVars(EObject obj, boolean includeChildren,
			Set<TypeVariable> addHere) {
		final Type declType = obj instanceof TypeRef ? ((TypeRef) obj).getDeclaredType() : null;
		if (declType instanceof TypeVariable) {
			addHere.add((TypeVariable) declType);
		}
		if (obj instanceof StructuralTypeRef) {
			for (TStructMember m : ((StructuralTypeRef) obj).getStructuralMembers()) {
				collectReferencedTypeVars(m, true, addHere);
			}
		}
		if (includeChildren) {
			final Iterator<EObject> iter = obj.eAllContents();
			while (iter.hasNext()) {
				collectReferencedTypeVars(iter.next(), false, addHere);
			}
		}
		return addHere;
	}

	/**
	 * Returns true if one of the members is a getter and the other one is a setter. No further validation such as name
	 * or owner comparison is done as this is expected to be done in the client already.
	 */
	public static boolean isAccessorPair(TMember member, TMember member2) {
		return (member instanceof TGetter && member2 instanceof TSetter)
				|| (member instanceof TSetter && member2 instanceof TGetter);
	}

	/**
	 * Returns true if the overrideCandidate may override the overriddenCandidate, solely be the meta type.
	 */
	public static boolean mayOverrideOrImplementByMetaType(MemberType overrideCandidate,
			MemberType overriddenCandidate) {
		if (overriddenCandidate == overrideCandidate) {
			return true;
		}
		switch (overrideCandidate) {
		case FIELD:
			return overriddenCandidate != MemberType.METHOD;
		case SETTER: // fall through
		case GETTER:
			return overriddenCandidate == MemberType.FIELD;
		case METHOD:
		}
		return false;
	}

	/**
	 * Returns true iff the given type reference is "raw", i.e. if it points to a generic type and has fewer type
	 * arguments than the generic type has type parameters.
	 */
	public static boolean isRawTypeRef(TypeRef typeRef) {
		if (typeRef instanceof ParameterizedTypeRef) {
			final Type declType = typeRef.getDeclaredType();
			return declType != null && declType.getTypeVars().size() > typeRef.getTypeArgs().size();
		}
		return false;
	}

	/**
	 * Turns a {@link #isRawTypeRef(TypeRef) raw type reference} into a non-raw type reference by adding
	 * {@link Wildcard}s for missing type arguments (will modify the given type reference!).
	 */
	public static void sanitizeRawTypeRef(TypeRef typeRef) {
		if (typeRef instanceof ParameterizedTypeRef) {
			final Type type = typeRef.getDeclaredType();
			if (type != null) {
				final int n = type.getTypeVars().size();
				if (n > 0) {
					final List<TypeArgument> l = typeRef.getTypeArgs();
					while (l.size() < n) {
						l.add(TypeRefsFactory.eINSTANCE.createWildcard());
					}
				}
			}
		}
	}

	/**
	 * Returns the set of type variables used in the structural members of 'typeRef'.
	 */
	public static Set<TypeVariable> getTypeVarsInStructMembers(StructuralTypeRef typeRef) {
		final Set<TypeVariable> result = new HashSet<>();
		primCollectTypeVarsInStructMembers(typeRef, result);
		return result;
	}

	private static void primCollectTypeVarsInStructMembers(StructuralTypeRef typeRef, Set<TypeVariable> addHere) {
		typeRef.getStructuralMembers().forEach(currM -> {
			currM.eAllContents().forEachRemaining(currObj -> {
				if (currObj instanceof ParameterizedTypeRef
						&& ((ParameterizedTypeRef) currObj).getDeclaredType() instanceof TypeVariable) {
					final TypeVariable tv = (TypeVariable) ((ParameterizedTypeRef) currObj).getDeclaredType();
					addHere.add(tv);
				}
				if (currObj instanceof StructuralTypeRef) {
					primCollectTypeVarsInStructMembers((StructuralTypeRef) currObj, addHere);
				}
			});
		});
	}

	/**
	 * Same as {@link EcoreUtil#copy(EObject)}, but takes care of special copy semantics for TypeRefs. Always use this
	 * method instead of the standard EMF method if the source object might be or might contain a Type or TypeRef.
	 */
	public static final <T extends EObject> T copy(T source) {
		return copy(source, true, false);
	}

	/**
	 * Same as {@link EcoreUtil2#cloneIfContained(EObject)}, but takes care of special copy semantics for TypeRefs. See
	 * {@link #copy(EObject)}.
	 */
	public static final <T extends EObject> T copyIfContained(T source) {
		return copy(source, true, true);
	}

	/**
	 * Same as {@link EcoreUtil2#cloneWithProxies(EObject)}, but takes care of special copy semantics for TypeRefs. See
	 * {@link #copy(EObject)}.
	 */
	public static final <T extends EObject> T copyWithProxies(T source) {
		return copy(source, false, false);
	}

	/**
	 * Same as {@link EcoreUtil2#cloneWithProxies(EObject)}, but does not copy the given references.
	 */
	public static final <T extends EObject> T copyPartial(T source, EReference... eRefsToIgnore) {
		return copy(source, true, false, eRefsToIgnore);
	}

	private static final <T extends EObject> T copy(T source, boolean resolveProxies, boolean onlyIfContained,
			EReference... eRefsToIgnore) {
		if (source == null)
			return null;
		if (onlyIfContained && !(source.eContainer() != null || source.eResource() != null))
			return source;
		final TypeCopier copier = new TypeCopier(resolveProxies, eRefsToIgnore);
		@SuppressWarnings("unchecked")
		final T result = (T) copier.copy(source);
		copier.copyReferences();
		return result;
	}

	/**
	 * Same as {@link EcoreUtil#copyAll(Collection)}, but takes care of special copy semantics for TypeRefs. See
	 * {@link #copy(EObject)}.
	 */
	public static <T> Collection<T> copyAll(Collection<? extends T> sources) {
		final TypeCopier copier = new TypeCopier(true);
		final Collection<T> result = copier.copyAll(sources);
		copier.copyReferences();
		return result;
	}

	private static final class TypeCopier extends Copier {
		private static final EReference eRef_StructuralTypeRef_astStructuralMembers = TypeRefsPackage.eINSTANCE
				.getStructuralTypeRef_AstStructuralMembers();
		private static final EReference eRef_Wildcard_declaredUpperBound = TypeRefsPackage.eINSTANCE
				.getWildcard_DeclaredUpperBound();

		private final EReference[] eRefsToIgnore;

		public TypeCopier(boolean resolveProxies, EReference... eRefsToIgnore) {
			super(resolveProxies);
			this.eRefsToIgnore = eRefsToIgnore;
		}

		@Override
		protected void copyContainment(EReference eReference, EObject eObject, EObject copyEObject) {
			if (eReference == eRef_StructuralTypeRef_astStructuralMembers)
				return; // do not copy 'astStructuralMembers' of StructuralTypeRefs
			if (eReference == eRef_Wildcard_declaredUpperBound) {
				((Wildcard) copyEObject).setDeclaredUpperBound(
						TypeUtils.copyWithProxies(getDeclaredOrImplicitUpperBound((Wildcard) eObject)));
				return;
			}
			if (org.eclipse.xtext.util.Arrays.contains(eRefsToIgnore, eReference))
				return; // do not copy ignored references
			super.copyContainment(eReference, eObject, copyEObject);
		}

		@Override
		protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject) {
			if (org.eclipse.xtext.util.Arrays.contains(eRefsToIgnore, eReference))
				return; // do not copy ignored references
			super.copyReference(eReference, eObject, copyEObject);
		}
	}

	/**
	 * Returns true iff the argument is non-null and refers to the built-in type 'void'.
	 */
	public static boolean isVoid(TypeArgument ref0) {
		if (ref0 instanceof TypeRef) {
			TypeRef ref = (TypeRef) ref0;
			return (ref.getDeclaredType() instanceof VoidType);
		}
		return false;
	}

	/**
	 * Returns true iff the given function type has a return type of void.
	 */
	public static boolean isVoidReturnType(FunctionTypeExprOrRef funTypeRef) {
		if (funTypeRef instanceof FunctionTypeExpression && funTypeRef.getReturnTypeRef() == null) {
			// special case: FunctionTypeExpression may have null as returnTypeRef and that means void!
			return true;
		} else {
			return funTypeRef != null ? isVoid(funTypeRef.getReturnTypeRef()) : false;
		}
	}

	/**
	 * Does the argument carry the {@link UndefModifier#OPTIONAL} modifier?
	 */
	public static boolean isOptional(TypeRef typeRef) {
		return (typeRef != null) && (UndefModifier.OPTIONAL == typeRef.getUndefModifier());
	}

	/**
	 * Returns true iff the {@link TypeRef} is a promise.
	 */
	public static boolean isPromise(TypeRef ref, BuiltInTypeScope scope) {
		if (ref instanceof ParameterizedTypeRef) {
			return ref.getDeclaredType() == scope.getPromiseType();
		}
		return false;
	}

	/**
	 * For the given type-ref, this method returns a Promise<R,?> type-ref.
	 */
	public static ParameterizedTypeRef createPromiseTypeRef(BuiltInTypeScope scope, TypeArgument successType,
			TypeArgument failureTypeOrNull) {
		Objects.requireNonNull(successType);
		return createTypeRef(
				scope.getPromiseType(),
				TypeUtils.copyWithProxies(successType),
				failureTypeOrNull != null ? TypeUtils.copyWithProxies(failureTypeOrNull)
						: TypeRefsFactory.eINSTANCE.createWildcard());
	}

	/**
	 * Returns a Promise<any,?> type-ref.
	 */
	public static ParameterizedTypeRef createPromiseOfAny(BuiltInTypeScope scope) {
		return createPromiseTypeRef(scope, scope.getAnyTypeRef(), null);
	}

	/**
	 * Returns a Promise<void,?> type-ref.
	 */
	public static ParameterizedTypeRef createPromiseOfVoid(BuiltInTypeScope scope) {
		return createPromiseTypeRef(scope, scope.getVoidTypeRef(), null);
	}

	/**
	 * Returns true iff the argument (or one of its components) is a {@link ThisTypeRef}.
	 *
	 * @param typeRef
	 *            the typeRef to be searched for (along with its components if any).
	 */
	public static boolean isOrContainsThisType(TypeRef typeRef) {
		return isOrContainsTypeRefOfType(typeRef, ThisTypeRef.class);
	}

	/**
	 * Returns true iff given type is a built-in type, i.e. resides in a resource with scheme "n4scheme". It is safe to
	 * call this with a proxy (won't be resolved and returns proper result).
	 */
	public static boolean isBuiltIn(Type type) {
		return N4Scheme.isFromResourceWithN4Scheme(type);
	}
}
