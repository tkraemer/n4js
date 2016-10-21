/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs.impl;

import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefWithVersion;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameterized Type Ref With Version</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.ParameterizedTypeRefWithVersionImpl#getDeclaredRefVersion <em>Declared Ref Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParameterizedTypeRefWithVersionImpl extends ParameterizedTypeRefImpl implements ParameterizedTypeRefWithVersion {
	/**
	 * The default value of the '{@link #getDeclaredRefVersion() <em>Declared Ref Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredRefVersion()
	 * @generated
	 * @ordered
	 */
	protected static final int DECLARED_REF_VERSION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDeclaredRefVersion() <em>Declared Ref Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredRefVersion()
	 * @generated
	 * @ordered
	 */
	protected int declaredRefVersion = DECLARED_REF_VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterizedTypeRefWithVersionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF_WITH_VERSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDeclaredRefVersion() {
		return declaredRefVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredRefVersion(int newDeclaredRefVersion) {
		int oldDeclaredRefVersion = declaredRefVersion;
		declaredRefVersion = newDeclaredRefVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeRefsPackage.PARAMETERIZED_TYPE_REF_WITH_VERSION__DECLARED_REF_VERSION, oldDeclaredRefVersion, declaredRefVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getReferencedVersion() {
		return this.getDeclaredRefVersion();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypeRefsPackage.PARAMETERIZED_TYPE_REF_WITH_VERSION__DECLARED_REF_VERSION:
				return getDeclaredRefVersion();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TypeRefsPackage.PARAMETERIZED_TYPE_REF_WITH_VERSION__DECLARED_REF_VERSION:
				setDeclaredRefVersion((Integer)newValue);
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
			case TypeRefsPackage.PARAMETERIZED_TYPE_REF_WITH_VERSION__DECLARED_REF_VERSION:
				setDeclaredRefVersion(DECLARED_REF_VERSION_EDEFAULT);
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
			case TypeRefsPackage.PARAMETERIZED_TYPE_REF_WITH_VERSION__DECLARED_REF_VERSION:
				return declaredRefVersion != DECLARED_REF_VERSION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == TypeRef.class) {
			switch (baseOperationID) {
				case TypeRefsPackage.TYPE_REF___GET_REFERENCED_VERSION: return TypeRefsPackage.PARAMETERIZED_TYPE_REF_WITH_VERSION___GET_REFERENCED_VERSION;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case TypeRefsPackage.PARAMETERIZED_TYPE_REF_WITH_VERSION___GET_REFERENCED_VERSION:
				return getReferencedVersion();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (declaredRefVersion: ");
		result.append(declaredRefVersion);
		result.append(')');
		return result.toString();
	}

} //ParameterizedTypeRefWithVersionImpl
