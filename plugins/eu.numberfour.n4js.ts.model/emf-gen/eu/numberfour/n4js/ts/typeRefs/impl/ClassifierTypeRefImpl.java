/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs.impl;

import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier Type Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.ClassifierTypeRefImpl#getTypeArg <em>Type Arg</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassifierTypeRefImpl extends BaseTypeRefImpl implements ClassifierTypeRef {
	/**
	 * The cached value of the '{@link #getTypeArg() <em>Type Arg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeArg()
	 * @generated
	 * @ordered
	 */
	protected TypeArgument typeArg;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClassifierTypeRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeRefsPackage.Literals.CLASSIFIER_TYPE_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeArgument getTypeArg() {
		return typeArg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeArg(TypeArgument newTypeArg, NotificationChain msgs) {
		TypeArgument oldTypeArg = typeArg;
		typeArg = newTypeArg;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypeRefsPackage.CLASSIFIER_TYPE_REF__TYPE_ARG, oldTypeArg, newTypeArg);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeArg(TypeArgument newTypeArg) {
		if (newTypeArg != typeArg) {
			NotificationChain msgs = null;
			if (typeArg != null)
				msgs = ((InternalEObject)typeArg).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypeRefsPackage.CLASSIFIER_TYPE_REF__TYPE_ARG, null, msgs);
			if (newTypeArg != null)
				msgs = ((InternalEObject)newTypeArg).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypeRefsPackage.CLASSIFIER_TYPE_REF__TYPE_ARG, null, msgs);
			msgs = basicSetTypeArg(newTypeArg, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeRefsPackage.CLASSIFIER_TYPE_REF__TYPE_ARG, newTypeArg, newTypeArg));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeRefAsString() {
		TypeArgument _typeArg = this.getTypeArg();
		String _typeRefAsString = null;
		if (_typeArg!=null) {
			_typeRefAsString=_typeArg.getTypeRefAsString();
		}
		final String refName = _typeRefAsString;
		String _modifiersAsString = this.getModifiersAsString();
		return ((("type{" + refName) + "}") + _modifiersAsString);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypeRefsPackage.CLASSIFIER_TYPE_REF__TYPE_ARG:
				return basicSetTypeArg(null, msgs);
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
			case TypeRefsPackage.CLASSIFIER_TYPE_REF__TYPE_ARG:
				return getTypeArg();
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
			case TypeRefsPackage.CLASSIFIER_TYPE_REF__TYPE_ARG:
				setTypeArg((TypeArgument)newValue);
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
			case TypeRefsPackage.CLASSIFIER_TYPE_REF__TYPE_ARG:
				setTypeArg((TypeArgument)null);
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
			case TypeRefsPackage.CLASSIFIER_TYPE_REF__TYPE_ARG:
				return typeArg != null;
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
		if (baseClass == TypeArgument.class) {
			switch (baseOperationID) {
				case TypeRefsPackage.TYPE_ARGUMENT___GET_TYPE_REF_AS_STRING: return TypeRefsPackage.CLASSIFIER_TYPE_REF___GET_TYPE_REF_AS_STRING;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == TypeRef.class) {
			switch (baseOperationID) {
				case TypeRefsPackage.TYPE_REF___GET_TYPE_REF_AS_STRING: return TypeRefsPackage.CLASSIFIER_TYPE_REF___GET_TYPE_REF_AS_STRING;
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
			case TypeRefsPackage.CLASSIFIER_TYPE_REF___GET_TYPE_REF_AS_STRING:
				return getTypeRefAsString();
		}
		return super.eInvoke(operationID, arguments);
	}

} //ClassifierTypeRefImpl
