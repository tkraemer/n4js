/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types.impl;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

import eu.numberfour.n4js.ts.types.FieldAccessor;
import eu.numberfour.n4js.ts.types.TInterface;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TypesPackage;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field Accessor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.FieldAccessorImpl#isOptional_NEW_SYNTAX <em>Optional NEW SYNTAX</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.FieldAccessorImpl#isDeclaredAbstract <em>Declared Abstract</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.FieldAccessorImpl#getDeclaredThisType <em>Declared This Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class FieldAccessorImpl extends TMemberWithAccessModifierImpl implements FieldAccessor {
	/**
	 * The default value of the '{@link #isOptional_NEW_SYNTAX() <em>Optional NEW SYNTAX</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional_NEW_SYNTAX()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_NEW_SYNTAX_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOptional_NEW_SYNTAX() <em>Optional NEW SYNTAX</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional_NEW_SYNTAX()
	 * @generated
	 * @ordered
	 */
	protected boolean optional_NEW_SYNTAX = OPTIONAL_NEW_SYNTAX_EDEFAULT;

	/**
	 * The default value of the '{@link #isDeclaredAbstract() <em>Declared Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeclaredAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DECLARED_ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeclaredAbstract() <em>Declared Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeclaredAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean declaredAbstract = DECLARED_ABSTRACT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDeclaredThisType() <em>Declared This Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredThisType()
	 * @generated
	 * @ordered
	 */
	protected TypeRef declaredThisType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldAccessorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.FIELD_ACCESSOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOptional_NEW_SYNTAX() {
		return optional_NEW_SYNTAX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptional_NEW_SYNTAX(boolean newOptional_NEW_SYNTAX) {
		boolean oldOptional_NEW_SYNTAX = optional_NEW_SYNTAX;
		optional_NEW_SYNTAX = newOptional_NEW_SYNTAX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.FIELD_ACCESSOR__OPTIONAL_NEW_SYNTAX, oldOptional_NEW_SYNTAX, optional_NEW_SYNTAX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeclaredAbstract() {
		return declaredAbstract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredAbstract(boolean newDeclaredAbstract) {
		boolean oldDeclaredAbstract = declaredAbstract;
		declaredAbstract = newDeclaredAbstract;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.FIELD_ACCESSOR__DECLARED_ABSTRACT, oldDeclaredAbstract, declaredAbstract));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getDeclaredThisType() {
		return declaredThisType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaredThisType(TypeRef newDeclaredThisType, NotificationChain msgs) {
		TypeRef oldDeclaredThisType = declaredThisType;
		declaredThisType = newDeclaredThisType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypesPackage.FIELD_ACCESSOR__DECLARED_THIS_TYPE, oldDeclaredThisType, newDeclaredThisType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredThisType(TypeRef newDeclaredThisType) {
		if (newDeclaredThisType != declaredThisType) {
			NotificationChain msgs = null;
			if (declaredThisType != null)
				msgs = ((InternalEObject)declaredThisType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypesPackage.FIELD_ACCESSOR__DECLARED_THIS_TYPE, null, msgs);
			if (newDeclaredThisType != null)
				msgs = ((InternalEObject)newDeclaredThisType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypesPackage.FIELD_ACCESSOR__DECLARED_THIS_TYPE, null, msgs);
			msgs = basicSetDeclaredThisType(newDeclaredThisType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.FIELD_ACCESSOR__DECLARED_THIS_TYPE, newDeclaredThisType, newDeclaredThisType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getDeclaredTypeRef() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOptional() {
		return (this.isOptional_NEW_SYNTAX() || ((this.getDeclaredTypeRef() != null) && this.getDeclaredTypeRef().isOptional_OLD_SYNTAX()));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return (this.isDeclaredAbstract() || ((this.eContainer() instanceof TInterface) && this.isHasNoBody()));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypesPackage.FIELD_ACCESSOR__DECLARED_THIS_TYPE:
				return basicSetDeclaredThisType(null, msgs);
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
			case TypesPackage.FIELD_ACCESSOR__OPTIONAL_NEW_SYNTAX:
				return isOptional_NEW_SYNTAX();
			case TypesPackage.FIELD_ACCESSOR__DECLARED_ABSTRACT:
				return isDeclaredAbstract();
			case TypesPackage.FIELD_ACCESSOR__DECLARED_THIS_TYPE:
				return getDeclaredThisType();
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
			case TypesPackage.FIELD_ACCESSOR__OPTIONAL_NEW_SYNTAX:
				setOptional_NEW_SYNTAX((Boolean)newValue);
				return;
			case TypesPackage.FIELD_ACCESSOR__DECLARED_ABSTRACT:
				setDeclaredAbstract((Boolean)newValue);
				return;
			case TypesPackage.FIELD_ACCESSOR__DECLARED_THIS_TYPE:
				setDeclaredThisType((TypeRef)newValue);
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
			case TypesPackage.FIELD_ACCESSOR__OPTIONAL_NEW_SYNTAX:
				setOptional_NEW_SYNTAX(OPTIONAL_NEW_SYNTAX_EDEFAULT);
				return;
			case TypesPackage.FIELD_ACCESSOR__DECLARED_ABSTRACT:
				setDeclaredAbstract(DECLARED_ABSTRACT_EDEFAULT);
				return;
			case TypesPackage.FIELD_ACCESSOR__DECLARED_THIS_TYPE:
				setDeclaredThisType((TypeRef)null);
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
			case TypesPackage.FIELD_ACCESSOR__OPTIONAL_NEW_SYNTAX:
				return optional_NEW_SYNTAX != OPTIONAL_NEW_SYNTAX_EDEFAULT;
			case TypesPackage.FIELD_ACCESSOR__DECLARED_ABSTRACT:
				return declaredAbstract != DECLARED_ABSTRACT_EDEFAULT;
			case TypesPackage.FIELD_ACCESSOR__DECLARED_THIS_TYPE:
				return declaredThisType != null;
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
		if (baseClass == TMember.class) {
			switch (baseOperationID) {
				case TypesPackage.TMEMBER___IS_OPTIONAL: return TypesPackage.FIELD_ACCESSOR___IS_OPTIONAL;
				case TypesPackage.TMEMBER___IS_ABSTRACT: return TypesPackage.FIELD_ACCESSOR___IS_ABSTRACT;
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
			case TypesPackage.FIELD_ACCESSOR___GET_DECLARED_TYPE_REF:
				return getDeclaredTypeRef();
			case TypesPackage.FIELD_ACCESSOR___IS_OPTIONAL:
				return isOptional();
			case TypesPackage.FIELD_ACCESSOR___IS_ABSTRACT:
				return isAbstract();
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
		result.append(" (optional_NEW_SYNTAX: ");
		result.append(optional_NEW_SYNTAX);
		result.append(", declaredAbstract: ");
		result.append(declaredAbstract);
		result.append(')');
		return result.toString();
	}

} //FieldAccessorImpl
