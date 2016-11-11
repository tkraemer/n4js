/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4jsx.n4JSX.impl;

import eu.numberfour.n4js.n4JS.IdentifierRef;

import eu.numberfour.n4js.utils.emf.ProxyResolvingEObjectImpl;

import eu.numberfour.n4jsx.n4JSX.JSXElementName;
import eu.numberfour.n4jsx.n4JSX.N4JSXPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JSX Element Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4jsx.n4JSX.impl.JSXElementNameImpl#getIdentifierRef <em>Identifier Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JSXElementNameImpl extends ProxyResolvingEObjectImpl implements JSXElementName {
	/**
	 * The cached value of the '{@link #getIdentifierRef() <em>Identifier Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifierRef()
	 * @generated
	 * @ordered
	 */
	protected IdentifierRef identifierRef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JSXElementNameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSXPackage.Literals.JSX_ELEMENT_NAME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentifierRef getIdentifierRef() {
		return identifierRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIdentifierRef(IdentifierRef newIdentifierRef, NotificationChain msgs) {
		IdentifierRef oldIdentifierRef = identifierRef;
		identifierRef = newIdentifierRef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSXPackage.JSX_ELEMENT_NAME__IDENTIFIER_REF, oldIdentifierRef, newIdentifierRef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentifierRef(IdentifierRef newIdentifierRef) {
		if (newIdentifierRef != identifierRef) {
			NotificationChain msgs = null;
			if (identifierRef != null)
				msgs = ((InternalEObject)identifierRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSXPackage.JSX_ELEMENT_NAME__IDENTIFIER_REF, null, msgs);
			if (newIdentifierRef != null)
				msgs = ((InternalEObject)newIdentifierRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSXPackage.JSX_ELEMENT_NAME__IDENTIFIER_REF, null, msgs);
			msgs = basicSetIdentifierRef(newIdentifierRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSXPackage.JSX_ELEMENT_NAME__IDENTIFIER_REF, newIdentifierRef, newIdentifierRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case N4JSXPackage.JSX_ELEMENT_NAME__IDENTIFIER_REF:
				return basicSetIdentifierRef(null, msgs);
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
			case N4JSXPackage.JSX_ELEMENT_NAME__IDENTIFIER_REF:
				return getIdentifierRef();
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
			case N4JSXPackage.JSX_ELEMENT_NAME__IDENTIFIER_REF:
				setIdentifierRef((IdentifierRef)newValue);
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
			case N4JSXPackage.JSX_ELEMENT_NAME__IDENTIFIER_REF:
				setIdentifierRef((IdentifierRef)null);
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
			case N4JSXPackage.JSX_ELEMENT_NAME__IDENTIFIER_REF:
				return identifierRef != null;
		}
		return super.eIsSet(featureID);
	}

} //JSXElementNameImpl
