/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Like</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * *
 * 'Trait' that allows to mark a certain type as array-like. If an elementType is
 * provided, that one is assumed to be the return type of indexed access expressions.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.ArrayLike#getDeclaredElementType <em>Declared Element Type</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getArrayLike()
 * @model abstract="true"
 * @generated
 */
public interface ArrayLike extends EObject {
	/**
	 * Returns the value of the '<em><b>Declared Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * The locally declared element type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Declared Element Type</em>' containment reference.
	 * @see #setDeclaredElementType(TypeRef)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getArrayLike_DeclaredElementType()
	 * @model containment="true"
	 * @generated
	 */
	TypeRef getDeclaredElementType();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.ArrayLike#getDeclaredElementType <em>Declared Element Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Element Type</em>' containment reference.
	 * @see #getDeclaredElementType()
	 * @generated
	 */
	void setDeclaredElementType(TypeRef value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns the declared element type or the inherited information
	 * iff no locally declared information is available.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	TypeRef getElementType();

} // ArrayLike
