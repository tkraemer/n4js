/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS.impl;

import eu.numberfour.n4js.n4JS.BindingElement;
import eu.numberfour.n4js.n4JS.BindingProperty;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.VariableDeclaration;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binding Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.BindingPropertyImpl#getDeclaredName <em>Declared Name</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.BindingPropertyImpl#getValue <em>Value</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.BindingPropertyImpl#getVarDecl <em>Var Decl</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BindingPropertyImpl extends MinimalEObjectImpl.Container implements BindingProperty {
	/**
	 * The default value of the '{@link #getDeclaredName() <em>Declared Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredName()
	 * @generated
	 * @ordered
	 */
	protected static final String DECLARED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeclaredName() <em>Declared Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredName()
	 * @generated
	 * @ordered
	 */
	protected String declaredName = DECLARED_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected BindingElement value;

	/**
	 * The cached value of the '{@link #getVarDecl() <em>Var Decl</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVarDecl()
	 * @generated
	 * @ordered
	 */
	protected VariableDeclaration varDecl;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BindingPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSPackage.Literals.BINDING_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDeclaredName() {
		return declaredName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredName(String newDeclaredName) {
		String oldDeclaredName = declaredName;
		declaredName = newDeclaredName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.BINDING_PROPERTY__DECLARED_NAME, oldDeclaredName, declaredName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BindingElement getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(BindingElement newValue, NotificationChain msgs) {
		BindingElement oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.BINDING_PROPERTY__VALUE, oldValue, newValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(BindingElement newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.BINDING_PROPERTY__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.BINDING_PROPERTY__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.BINDING_PROPERTY__VALUE, newValue, newValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclaration getVarDecl() {
		return varDecl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVarDecl(VariableDeclaration newVarDecl, NotificationChain msgs) {
		VariableDeclaration oldVarDecl = varDecl;
		varDecl = newVarDecl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.BINDING_PROPERTY__VAR_DECL, oldVarDecl, newVarDecl);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarDecl(VariableDeclaration newVarDecl) {
		if (newVarDecl != varDecl) {
			NotificationChain msgs = null;
			if (varDecl != null)
				msgs = ((InternalEObject)varDecl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.BINDING_PROPERTY__VAR_DECL, null, msgs);
			if (newVarDecl != null)
				msgs = ((InternalEObject)newVarDecl).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.BINDING_PROPERTY__VAR_DECL, null, msgs);
			msgs = basicSetVarDecl(newVarDecl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.BINDING_PROPERTY__VAR_DECL, newVarDecl, newVarDecl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		String _elvis = null;
		String _declaredName = this.getDeclaredName();
		if (_declaredName != null) {
			_elvis = _declaredName;
		} else {
			BindingElement _value = this.getValue();
			VariableDeclaration _varDecl = null;
			if (_value!=null) {
				_varDecl=_value.getVarDecl();
			}
			String _name = null;
			if (_varDecl!=null) {
				_name=_varDecl.getName();
			}
			_elvis = _name;
		}
		return _elvis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case N4JSPackage.BINDING_PROPERTY__VALUE:
				return basicSetValue(null, msgs);
			case N4JSPackage.BINDING_PROPERTY__VAR_DECL:
				return basicSetVarDecl(null, msgs);
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
			case N4JSPackage.BINDING_PROPERTY__DECLARED_NAME:
				return getDeclaredName();
			case N4JSPackage.BINDING_PROPERTY__VALUE:
				return getValue();
			case N4JSPackage.BINDING_PROPERTY__VAR_DECL:
				return getVarDecl();
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
			case N4JSPackage.BINDING_PROPERTY__DECLARED_NAME:
				setDeclaredName((String)newValue);
				return;
			case N4JSPackage.BINDING_PROPERTY__VALUE:
				setValue((BindingElement)newValue);
				return;
			case N4JSPackage.BINDING_PROPERTY__VAR_DECL:
				setVarDecl((VariableDeclaration)newValue);
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
			case N4JSPackage.BINDING_PROPERTY__DECLARED_NAME:
				setDeclaredName(DECLARED_NAME_EDEFAULT);
				return;
			case N4JSPackage.BINDING_PROPERTY__VALUE:
				setValue((BindingElement)null);
				return;
			case N4JSPackage.BINDING_PROPERTY__VAR_DECL:
				setVarDecl((VariableDeclaration)null);
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
			case N4JSPackage.BINDING_PROPERTY__DECLARED_NAME:
				return DECLARED_NAME_EDEFAULT == null ? declaredName != null : !DECLARED_NAME_EDEFAULT.equals(declaredName);
			case N4JSPackage.BINDING_PROPERTY__VALUE:
				return value != null;
			case N4JSPackage.BINDING_PROPERTY__VAR_DECL:
				return varDecl != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case N4JSPackage.BINDING_PROPERTY___GET_NAME:
				return getName();
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
		result.append(" (declaredName: ");
		result.append(declaredName);
		result.append(')');
		return result.toString();
	}

} //BindingPropertyImpl
