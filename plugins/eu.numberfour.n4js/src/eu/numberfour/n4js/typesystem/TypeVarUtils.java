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

import java.util.Objects;
import java.util.stream.Stream;

import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.TypeVariable;
import eu.numberfour.n4js.ts.utils.TypeUtils;

/**
 */
public class TypeVarUtils {

	/**
	 * Returns (as a stream) all type variables mentioned in the argument.
	 */
	public static Stream<TypeVariable> collectedTypeVars(TypeArgument obj) {
		return TypeUtils.getReferencedTypeVars(obj).stream();
		// if (obj instanceof Wildcard) {
		// Wildcard w = (Wildcard) obj;
		// Stream<TypeVariable> sLower = TypeVarUtils.collectedTypeVars(w.getDeclaredLowerBound());
		// Stream<TypeVariable> sUpper = TypeVarUtils.collectedTypeVars(w.getDeclaredUpperBound());
		// return Stream.concat(sLower, sUpper);
		// }
		// if (obj instanceof TypeRef) {
		// return TypeVarUtils.collectedTypeVars((TypeRef) obj);
		// }
		//
		// return Stream.empty();
	}

	// /**
	// * Returns (as a stream) all type variables mentioned in the argument.
	// */
	// public static Stream<TypeVariable> collectedTypeVars(TypeRef t) {
	// if (null == t) {
	// return Stream.empty();
	// }
	// if (t.getDeclaredType() instanceof TypeVariable) {
	// return Stream.of((TypeVariable) t.getDeclaredType());
	// }
	// if (t instanceof ExistentialTypeRef) {
	// ExistentialTypeRef e = (ExistentialTypeRef) t;
	// return Stream.concat(collectedTypeVars(e.getWildcard()), Stream.of(e.getBoundTypeVariable()));
	// }
	// if (t instanceof ComposedTypeRef) {
	// ComposedTypeRef c = (ComposedTypeRef) t;
	// return TypeVarUtils.collectedTypeVars(c.getTypeRefs());
	// }
	// if (t instanceof FunctionTypeExprOrRef) {
	// FunctionTypeExprOrRef f = (FunctionTypeExprOrRef) t;
	// Stream<TypeVariable> paramStream = f.getFpars().stream().flatMap(p -> collectedTypeVars(p.getTypeRef()));
	// Stream<TypeVariable> signatureSream = Stream.concat(paramStream, collectedTypeVars(f.getReturnTypeRef()));
	// return Stream.concat(signatureSream, f.getTypeVars().stream());
	// }
	// if (t instanceof BaseTypeRef) {
	// return TypeVarUtils.collectedTypeVars(t);
	// }
	// return Stream.empty();
	// }
	//
	// private static Stream<TypeVariable> collectedTypeVars(EList<? extends TypeArgument> typeRefs) {
	// return typeRefs.stream().flatMap(tr -> collectedTypeVars(tr));
	// }
	//
	// private static Stream<TypeVariable> collectedTypeVars(BaseTypeRef b) {
	// if (b instanceof ClassifierTypeRef) {
	// ClassifierTypeRef c = (ClassifierTypeRef) b;
	// return collectedTypeVars(c.getStaticTypeRef());
	// }
	// if (b instanceof EnumTypeRef) {
	// EnumTypeRef e = (EnumTypeRef) b;
	// return collectedTypeVars(e.getEnumType());
	// }
	// if (b instanceof ParameterizedTypeRef) {
	// ParameterizedTypeRef p = (ParameterizedTypeRef) b;
	// Stream<TypeVariable> declStream = p.getDeclaredType() != null // null check important for broken AST
	// ? TypeVarUtils.collectedTypeVars(p.getDeclaredType()) : Stream.empty();
	// return Stream.concat(declStream, collectedTypeVars(p.getTypeArgs()));
	// }
	// if (b instanceof ThisTypeRef) {
	// // FIXME collect type vars mentioned in ThisTypeRef
	// }
	// return Stream.empty();
	// }
	//
	// private static Stream<TypeVariable> collectedTypeVars(Type t) {
	// if (t instanceof TypeVariable) {
	// return Stream.of((TypeVariable) t);
	// }
	// return t.getTypeVars().stream();
	// }

	/**
	 * @return the argument wrapped in a {@link ParameterizedTypeRef}
	 */
	public static ParameterizedTypeRef typeRef(TypeVariable infVar) {
		return TypeUtils.createTypeRef(infVar, new TypeArgument[0]);
	}

	/**
	 * Does typeRef denote the given type variable?
	 */
	public static boolean denotesVar(TypeRef typeRef, TypeVariable variable) {
		Objects.requireNonNull(typeRef);
		Objects.requireNonNull(variable);
		return (typeRef.getDeclaredType() == variable);
	}

	/**
	 * Does the second argument mention the variable?
	 */
	public static boolean occursIn(TypeVariable alpha, TypeArgument targ) {
		return collectedTypeVars(targ).anyMatch(v -> alpha == v);
	}

}
