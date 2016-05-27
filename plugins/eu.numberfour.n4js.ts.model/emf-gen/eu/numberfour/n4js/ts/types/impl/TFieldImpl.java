/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types.impl;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

import eu.numberfour.n4js.ts.types.MemberType;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TypesPackage;
import eu.numberfour.n4js.ts.types.UndefModifier;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TField</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TFieldImpl#isConst <em>Const</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TFieldImpl#isHasExpression <em>Has Expression</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TFieldImpl#getTypeRef <em>Type Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TFieldImpl extends TMemberWithAccessModifierImpl implements TField {
	/**
	 * The default value of the '{@link #isConst() <em>Const</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConst()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONST_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConst() <em>Const</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConst()
	 * @generated
	 * @ordered
	 */
	protected boolean const_ = CONST_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasExpression() <em>Has Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasExpression()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_EXPRESSION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasExpression() <em>Has Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasExpression()
	 * @generated
	 * @ordered
	 */
	protected boolean hasExpression = HAS_EXPRESSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTypeRef() <em>Type Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeRef()
	 * @generated
	 * @ordered
	 */
	protected TypeRef typeRef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TFieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.TFIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConst() {
		return const_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConst(boolean newConst) {
		boolean oldConst = const_;
		const_ = newConst;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TFIELD__CONST, oldConst, const_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasExpression() {
		return hasExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasExpression(boolean newHasExpression) {
		boolean oldHasExpression = hasExpression;
		hasExpression = newHasExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TFIELD__HAS_EXPRESSION, oldHasExpression, hasExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getTypeRef() {
		return typeRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeRef(TypeRef newTypeRef, NotificationChain msgs) {
		TypeRef oldTypeRef = typeRef;
		typeRef = newTypeRef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypesPackage.TFIELD__TYPE_REF, oldTypeRef, newTypeRef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeRef(TypeRef newTypeRef) {
		if (newTypeRef != typeRef) {
			NotificationChain msgs = null;
			if (typeRef != null)
				msgs = ((InternalEObject)typeRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypesPackage.TFIELD__TYPE_REF, null, msgs);
			if (newTypeRef != null)
				msgs = ((InternalEObject)newTypeRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypesPackage.TFIELD__TYPE_REF, null, msgs);
			msgs = basicSetTypeRef(newTypeRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TFIELD__TYPE_REF, newTypeRef, newTypeRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOptional() {
		return ((this.getTypeRef() != null) && (this.getTypeRef().getUndefModifier() == UndefModifier.OPTIONAL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic() {
		return (this.isDeclaredStatic() || this.isConst());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReadable() {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWriteable() {
		return (!(this.isConst() || this.isFinal()));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemberType getMemberType() {
		return MemberType.FIELD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMemberAsString() {
		final StringBuilder strb = new StringBuilder();
		String _name = this.getName();
		strb.append(_name);
		TypeRef _typeRef = this.getTypeRef();
		boolean _tripleNotEquals = (_typeRef != null);
		if (_tripleNotEquals) {
			StringBuilder _append = strb.append(": ");
			TypeRef _typeRef_1 = this.getTypeRef();
			String _typeRefAsString = _typeRef_1.getTypeRefAsString();
			_append.append(_typeRefAsString);
		}
		return strb.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypesPackage.TFIELD__TYPE_REF:
				return basicSetTypeRef(null, msgs);
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
			case TypesPackage.TFIELD__CONST:
				return isConst();
			case TypesPackage.TFIELD__HAS_EXPRESSION:
				return isHasExpression();
			case TypesPackage.TFIELD__TYPE_REF:
				return getTypeRef();
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
			case TypesPackage.TFIELD__CONST:
				setConst((Boolean)newValue);
				return;
			case TypesPackage.TFIELD__HAS_EXPRESSION:
				setHasExpression((Boolean)newValue);
				return;
			case TypesPackage.TFIELD__TYPE_REF:
				setTypeRef((TypeRef)newValue);
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
			case TypesPackage.TFIELD__CONST:
				setConst(CONST_EDEFAULT);
				return;
			case TypesPackage.TFIELD__HAS_EXPRESSION:
				setHasExpression(HAS_EXPRESSION_EDEFAULT);
				return;
			case TypesPackage.TFIELD__TYPE_REF:
				setTypeRef((TypeRef)null);
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
			case TypesPackage.TFIELD__CONST:
				return const_ != CONST_EDEFAULT;
			case TypesPackage.TFIELD__HAS_EXPRESSION:
				return hasExpression != HAS_EXPRESSION_EDEFAULT;
			case TypesPackage.TFIELD__TYPE_REF:
				return typeRef != null;
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
				case TypesPackage.TMEMBER___GET_MEMBER_TYPE: return TypesPackage.TFIELD___GET_MEMBER_TYPE;
				case TypesPackage.TMEMBER___IS_OPTIONAL: return TypesPackage.TFIELD___IS_OPTIONAL;
				case TypesPackage.TMEMBER___IS_READABLE: return TypesPackage.TFIELD___IS_READABLE;
				case TypesPackage.TMEMBER___IS_WRITEABLE: return TypesPackage.TFIELD___IS_WRITEABLE;
				case TypesPackage.TMEMBER___GET_MEMBER_AS_STRING: return TypesPackage.TFIELD___GET_MEMBER_AS_STRING;
				case TypesPackage.TMEMBER___IS_STATIC: return TypesPackage.TFIELD___IS_STATIC;
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
			case TypesPackage.TFIELD___IS_OPTIONAL:
				return isOptional();
			case TypesPackage.TFIELD___IS_STATIC:
				return isStatic();
			case TypesPackage.TFIELD___IS_READABLE:
				return isReadable();
			case TypesPackage.TFIELD___IS_WRITEABLE:
				return isWriteable();
			case TypesPackage.TFIELD___GET_MEMBER_TYPE:
				return getMemberType();
			case TypesPackage.TFIELD___GET_MEMBER_AS_STRING:
				return getMemberAsString();
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
		result.append(" (const: ");
		result.append(const_);
		result.append(", hasExpression: ");
		result.append(hasExpression);
		result.append(')');
		return result.toString();
	}

} //TFieldImpl
