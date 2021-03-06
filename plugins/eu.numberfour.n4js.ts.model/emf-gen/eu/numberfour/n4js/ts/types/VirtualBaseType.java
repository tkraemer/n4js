/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Virtual Base Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Used for predefining members shared by all types of a certain meta-tyes, such as
 * enumerations all providing a getter method value. It sole purpose is to mix in the defined members here into the
 * actual types transparently inheriting from that type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.VirtualBaseType#getDeclaredOwnedMembers <em>Declared Owned Members</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getVirtualBaseType()
 * @model
 * @generated
 */
public interface VirtualBaseType extends ContainerType<TMember> {
	/**
	 * Returns the value of the '<em><b>Declared Owned Members</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.ts.types.TMember}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Directly contained members, that is predefined members of all enums
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Declared Owned Members</em>' containment reference list.
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getVirtualBaseType_DeclaredOwnedMembers()
	 * @model containment="true"
	 * @generated
	 */
	EList<TMember> getDeclaredOwnedMembers();

} // VirtualBaseType
