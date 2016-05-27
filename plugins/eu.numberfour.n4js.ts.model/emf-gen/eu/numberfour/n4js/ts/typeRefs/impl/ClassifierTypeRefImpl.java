/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs.impl;

import eu.numberfour.n4js.ts.typeRefs.BoundThisTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.StaticBaseTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;

import eu.numberfour.n4js.ts.types.Type;

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
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.ClassifierTypeRefImpl#getStaticTypeRef <em>Static Type Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClassifierTypeRefImpl extends BaseTypeRefImpl implements ClassifierTypeRef {
	/**
	 * The cached value of the '{@link #getStaticTypeRef() <em>Static Type Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticTypeRef()
	 * @generated
	 * @ordered
	 */
	protected StaticBaseTypeRef staticTypeRef;

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
	public StaticBaseTypeRef getStaticTypeRef() {
		return staticTypeRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStaticTypeRef(StaticBaseTypeRef newStaticTypeRef, NotificationChain msgs) {
		StaticBaseTypeRef oldStaticTypeRef = staticTypeRef;
		staticTypeRef = newStaticTypeRef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypeRefsPackage.CLASSIFIER_TYPE_REF__STATIC_TYPE_REF, oldStaticTypeRef, newStaticTypeRef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStaticTypeRef(StaticBaseTypeRef newStaticTypeRef) {
		if (newStaticTypeRef != staticTypeRef) {
			NotificationChain msgs = null;
			if (staticTypeRef != null)
				msgs = ((InternalEObject)staticTypeRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypeRefsPackage.CLASSIFIER_TYPE_REF__STATIC_TYPE_REF, null, msgs);
			if (newStaticTypeRef != null)
				msgs = ((InternalEObject)newStaticTypeRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypeRefsPackage.CLASSIFIER_TYPE_REF__STATIC_TYPE_REF, null, msgs);
			msgs = basicSetStaticTypeRef(newStaticTypeRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeRefsPackage.CLASSIFIER_TYPE_REF__STATIC_TYPE_REF, newStaticTypeRef, newStaticTypeRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeRefAsString() {
		StaticBaseTypeRef _staticTypeRef = this.getStaticTypeRef();
		String _typeRefAsString = null;
		if (_staticTypeRef!=null) {
			_typeRefAsString=_staticTypeRef.getTypeRefAsString();
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
	public Type staticType() {
		Type _switchResult = null;
		StaticBaseTypeRef _staticTypeRef = this.getStaticTypeRef();
		boolean _matched = false;
		if (_staticTypeRef instanceof ParameterizedTypeRef) {
			_matched=true;
			StaticBaseTypeRef _staticTypeRef_1 = this.getStaticTypeRef();
			_switchResult = _staticTypeRef_1.getDeclaredType();
		}
		if (!_matched) {
			if (_staticTypeRef instanceof BoundThisTypeRef) {
				_matched=true;
				StaticBaseTypeRef _staticTypeRef_1 = this.getStaticTypeRef();
				ParameterizedTypeRef _actualThisTypeRef = ((BoundThisTypeRef) _staticTypeRef_1).getActualThisTypeRef();
				Type _declaredType = null;
				if (_actualThisTypeRef!=null) {
					_declaredType=_actualThisTypeRef.getDeclaredType();
				}
				_switchResult = _declaredType;
			}
		}
		if (!_matched) {
			_switchResult = null;
		}
		return _switchResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypeRefsPackage.CLASSIFIER_TYPE_REF__STATIC_TYPE_REF:
				return basicSetStaticTypeRef(null, msgs);
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
			case TypeRefsPackage.CLASSIFIER_TYPE_REF__STATIC_TYPE_REF:
				return getStaticTypeRef();
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
			case TypeRefsPackage.CLASSIFIER_TYPE_REF__STATIC_TYPE_REF:
				setStaticTypeRef((StaticBaseTypeRef)newValue);
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
			case TypeRefsPackage.CLASSIFIER_TYPE_REF__STATIC_TYPE_REF:
				setStaticTypeRef((StaticBaseTypeRef)null);
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
			case TypeRefsPackage.CLASSIFIER_TYPE_REF__STATIC_TYPE_REF:
				return staticTypeRef != null;
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
			case TypeRefsPackage.CLASSIFIER_TYPE_REF___STATIC_TYPE:
				return staticType();
		}
		return super.eInvoke(operationID, arguments);
	}

} //ClassifierTypeRefImpl
