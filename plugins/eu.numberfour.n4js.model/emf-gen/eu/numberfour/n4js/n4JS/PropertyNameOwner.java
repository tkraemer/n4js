/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TStructMember;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Name Owner</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.PropertyNameOwner#getKind <em>Kind</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.PropertyNameOwner#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyNameOwner()
 * @model abstract="true"
 * @generated
 */
public interface PropertyNameOwner extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.numberfour.n4js.n4JS.PropertyNameKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is set by the IAstFactory while parsing a property assignment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see eu.numberfour.n4js.n4JS.PropertyNameKind
	 * @see #setKind(PropertyNameKind)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyNameOwner_Kind()
	 * @model unique="false" transient="true"
	 * @generated
	 */
	PropertyNameKind getKind();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.PropertyNameOwner#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see eu.numberfour.n4js.n4JS.PropertyNameKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(PropertyNameKind value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyNameOwner_Name()
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.PropertyNameOwner#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	TStructMember getDefinedMember();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Used to detect early errors according the the ES6 spec.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	boolean isValidName();

} // PropertyNameOwner
