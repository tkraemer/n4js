/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types.impl;

import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TypesPackage;
import eu.numberfour.n4js.ts.types.VirtualBaseType;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Virtual Base Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.VirtualBaseTypeImpl#getDeclaredOwnedMembers <em>Declared Owned Members</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VirtualBaseTypeImpl extends ContainerTypeImpl<TMember> implements VirtualBaseType {
	/**
	 * The cached value of the '{@link #getDeclaredOwnedMembers() <em>Declared Owned Members</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredOwnedMembers()
	 * @generated
	 * @ordered
	 */
	protected EList<TMember> declaredOwnedMembers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VirtualBaseTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.VIRTUAL_BASE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TMember> getDeclaredOwnedMembers() {
		if (declaredOwnedMembers == null) {
			declaredOwnedMembers = new EObjectContainmentEList<TMember>(TMember.class, this, TypesPackage.VIRTUAL_BASE_TYPE__DECLARED_OWNED_MEMBERS);
		}
		return declaredOwnedMembers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypesPackage.VIRTUAL_BASE_TYPE__DECLARED_OWNED_MEMBERS:
				return ((InternalEList<?>)getDeclaredOwnedMembers()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypesPackage.VIRTUAL_BASE_TYPE__DECLARED_OWNED_MEMBERS:
				return getDeclaredOwnedMembers();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TypesPackage.VIRTUAL_BASE_TYPE__DECLARED_OWNED_MEMBERS:
				getDeclaredOwnedMembers().clear();
				getDeclaredOwnedMembers().addAll((Collection<? extends TMember>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TypesPackage.VIRTUAL_BASE_TYPE__DECLARED_OWNED_MEMBERS:
				getDeclaredOwnedMembers().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TypesPackage.VIRTUAL_BASE_TYPE__DECLARED_OWNED_MEMBERS:
				return declaredOwnedMembers != null && !declaredOwnedMembers.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //VirtualBaseTypeImpl
