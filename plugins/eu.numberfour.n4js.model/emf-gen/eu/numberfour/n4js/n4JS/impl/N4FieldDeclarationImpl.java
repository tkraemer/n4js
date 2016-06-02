/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS.impl;

import com.google.common.base.Objects;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.N4FieldDeclaration;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.N4MemberDeclaration;
import eu.numberfour.n4js.n4JS.N4Modifier;
import eu.numberfour.n4js.n4JS.PropertyNameKind;
import eu.numberfour.n4js.n4JS.PropertyNameOwner;
import eu.numberfour.n4js.n4JS.ThisArgProvider;
import eu.numberfour.n4js.n4JS.TypedElement;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TMember;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>N4 Field Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldDeclarationImpl#getDeclaredTypeRef <em>Declared Type Ref</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldDeclarationImpl#getBogusTypeRef <em>Bogus Type Ref</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldDeclarationImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldDeclarationImpl#getDefinedField <em>Defined Field</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldDeclarationImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class N4FieldDeclarationImpl extends AnnotableN4MemberDeclarationImpl implements N4FieldDeclaration {
	/**
	 * The cached value of the '{@link #getDeclaredTypeRef() <em>Declared Type Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredTypeRef()
	 * @generated
	 * @ordered
	 */
	protected TypeRef declaredTypeRef;

	/**
	 * The cached value of the '{@link #getBogusTypeRef() <em>Bogus Type Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBogusTypeRef()
	 * @generated
	 * @ordered
	 */
	protected TypeRef bogusTypeRef;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final PropertyNameKind KIND_EDEFAULT = PropertyNameKind.IDENTIFIER;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected PropertyNameKind kind = KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefinedField() <em>Defined Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinedField()
	 * @generated
	 * @ordered
	 */
	protected TField definedField;

	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression expression;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected N4FieldDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSPackage.Literals.N4_FIELD_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getDeclaredTypeRef() {
		return declaredTypeRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaredTypeRef(TypeRef newDeclaredTypeRef, NotificationChain msgs) {
		TypeRef oldDeclaredTypeRef = declaredTypeRef;
		declaredTypeRef = newDeclaredTypeRef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF, oldDeclaredTypeRef, newDeclaredTypeRef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredTypeRef(TypeRef newDeclaredTypeRef) {
		if (newDeclaredTypeRef != declaredTypeRef) {
			NotificationChain msgs = null;
			if (declaredTypeRef != null)
				msgs = ((InternalEObject)declaredTypeRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF, null, msgs);
			if (newDeclaredTypeRef != null)
				msgs = ((InternalEObject)newDeclaredTypeRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF, null, msgs);
			msgs = basicSetDeclaredTypeRef(newDeclaredTypeRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF, newDeclaredTypeRef, newDeclaredTypeRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getBogusTypeRef() {
		return bogusTypeRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBogusTypeRef(TypeRef newBogusTypeRef, NotificationChain msgs) {
		TypeRef oldBogusTypeRef = bogusTypeRef;
		bogusTypeRef = newBogusTypeRef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF, oldBogusTypeRef, newBogusTypeRef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBogusTypeRef(TypeRef newBogusTypeRef) {
		if (newBogusTypeRef != bogusTypeRef) {
			NotificationChain msgs = null;
			if (bogusTypeRef != null)
				msgs = ((InternalEObject)bogusTypeRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF, null, msgs);
			if (newBogusTypeRef != null)
				msgs = ((InternalEObject)newBogusTypeRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF, null, msgs);
			msgs = basicSetBogusTypeRef(newBogusTypeRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF, newBogusTypeRef, newBogusTypeRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyNameKind getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(PropertyNameKind newKind) {
		PropertyNameKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_DECLARATION__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_DECLARATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TField getDefinedField() {
		if (definedField != null && definedField.eIsProxy()) {
			InternalEObject oldDefinedField = (InternalEObject)definedField;
			definedField = (TField)eResolveProxy(oldDefinedField);
			if (definedField != oldDefinedField) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, N4JSPackage.N4_FIELD_DECLARATION__DEFINED_FIELD, oldDefinedField, definedField));
			}
		}
		return definedField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TField basicGetDefinedField() {
		return definedField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinedField(TField newDefinedField) {
		TField oldDefinedField = definedField;
		definedField = newDefinedField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_DECLARATION__DEFINED_FIELD, oldDefinedField, definedField));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs) {
		Expression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_DECLARATION__EXPRESSION, oldExpression, newExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		if (newExpression != expression) {
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_DECLARATION__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_DECLARATION__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_DECLARATION__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMember getDefinedTypeElement() {
		return this.getDefinedField();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConst() {
		EList<N4Modifier> _declaredModifiers = this.getDeclaredModifiers();
		return _declaredModifiers.contains(N4Modifier.CONST);
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
	public boolean isValid() {
		String _name = this.getName();
		boolean _equals = Objects.equal("prototype", _name);
		if (_equals) {
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isValidName() {
		String _name = this.getName();
		boolean _equals = Objects.equal("prototype", _name);
		if (_equals) {
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF:
				return basicSetDeclaredTypeRef(null, msgs);
			case N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF:
				return basicSetBogusTypeRef(null, msgs);
			case N4JSPackage.N4_FIELD_DECLARATION__EXPRESSION:
				return basicSetExpression(null, msgs);
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
			case N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF:
				return getDeclaredTypeRef();
			case N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF:
				return getBogusTypeRef();
			case N4JSPackage.N4_FIELD_DECLARATION__KIND:
				return getKind();
			case N4JSPackage.N4_FIELD_DECLARATION__NAME:
				return getName();
			case N4JSPackage.N4_FIELD_DECLARATION__DEFINED_FIELD:
				if (resolve) return getDefinedField();
				return basicGetDefinedField();
			case N4JSPackage.N4_FIELD_DECLARATION__EXPRESSION:
				return getExpression();
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
			case N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF:
				setDeclaredTypeRef((TypeRef)newValue);
				return;
			case N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF:
				setBogusTypeRef((TypeRef)newValue);
				return;
			case N4JSPackage.N4_FIELD_DECLARATION__KIND:
				setKind((PropertyNameKind)newValue);
				return;
			case N4JSPackage.N4_FIELD_DECLARATION__NAME:
				setName((String)newValue);
				return;
			case N4JSPackage.N4_FIELD_DECLARATION__DEFINED_FIELD:
				setDefinedField((TField)newValue);
				return;
			case N4JSPackage.N4_FIELD_DECLARATION__EXPRESSION:
				setExpression((Expression)newValue);
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
			case N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF:
				setDeclaredTypeRef((TypeRef)null);
				return;
			case N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF:
				setBogusTypeRef((TypeRef)null);
				return;
			case N4JSPackage.N4_FIELD_DECLARATION__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case N4JSPackage.N4_FIELD_DECLARATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case N4JSPackage.N4_FIELD_DECLARATION__DEFINED_FIELD:
				setDefinedField((TField)null);
				return;
			case N4JSPackage.N4_FIELD_DECLARATION__EXPRESSION:
				setExpression((Expression)null);
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
			case N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF:
				return declaredTypeRef != null;
			case N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF:
				return bogusTypeRef != null;
			case N4JSPackage.N4_FIELD_DECLARATION__KIND:
				return kind != KIND_EDEFAULT;
			case N4JSPackage.N4_FIELD_DECLARATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case N4JSPackage.N4_FIELD_DECLARATION__DEFINED_FIELD:
				return definedField != null;
			case N4JSPackage.N4_FIELD_DECLARATION__EXPRESSION:
				return expression != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == TypedElement.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF: return N4JSPackage.TYPED_ELEMENT__DECLARED_TYPE_REF;
				case N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF: return N4JSPackage.TYPED_ELEMENT__BOGUS_TYPE_REF;
				default: return -1;
			}
		}
		if (baseClass == ThisArgProvider.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.N4_FIELD_DECLARATION__KIND: return N4JSPackage.PROPERTY_NAME_OWNER__KIND;
				case N4JSPackage.N4_FIELD_DECLARATION__NAME: return N4JSPackage.PROPERTY_NAME_OWNER__NAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == TypedElement.class) {
			switch (baseFeatureID) {
				case N4JSPackage.TYPED_ELEMENT__DECLARED_TYPE_REF: return N4JSPackage.N4_FIELD_DECLARATION__DECLARED_TYPE_REF;
				case N4JSPackage.TYPED_ELEMENT__BOGUS_TYPE_REF: return N4JSPackage.N4_FIELD_DECLARATION__BOGUS_TYPE_REF;
				default: return -1;
			}
		}
		if (baseClass == ThisArgProvider.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (baseFeatureID) {
				case N4JSPackage.PROPERTY_NAME_OWNER__KIND: return N4JSPackage.N4_FIELD_DECLARATION__KIND;
				case N4JSPackage.PROPERTY_NAME_OWNER__NAME: return N4JSPackage.N4_FIELD_DECLARATION__NAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == N4MemberDeclaration.class) {
			switch (baseOperationID) {
				case N4JSPackage.N4_MEMBER_DECLARATION___GET_DEFINED_TYPE_ELEMENT: return N4JSPackage.N4_FIELD_DECLARATION___GET_DEFINED_TYPE_ELEMENT;
				case N4JSPackage.N4_MEMBER_DECLARATION___IS_STATIC: return N4JSPackage.N4_FIELD_DECLARATION___IS_STATIC;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == TypedElement.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == ThisArgProvider.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (baseOperationID) {
				case N4JSPackage.PROPERTY_NAME_OWNER___GET_DEFINED_MEMBER: return N4JSPackage.N4_FIELD_DECLARATION___GET_DEFINED_MEMBER;
				case N4JSPackage.PROPERTY_NAME_OWNER___IS_VALID_NAME: return N4JSPackage.N4_FIELD_DECLARATION___IS_VALID_NAME;
				default: return -1;
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
			case N4JSPackage.N4_FIELD_DECLARATION___GET_DEFINED_TYPE_ELEMENT:
				return getDefinedTypeElement();
			case N4JSPackage.N4_FIELD_DECLARATION___IS_CONST:
				return isConst();
			case N4JSPackage.N4_FIELD_DECLARATION___IS_STATIC:
				return isStatic();
			case N4JSPackage.N4_FIELD_DECLARATION___IS_VALID:
				return isValid();
			case N4JSPackage.N4_FIELD_DECLARATION___IS_VALID_NAME:
				return isValidName();
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
		result.append(" (kind: ");
		result.append(kind);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //N4FieldDeclarationImpl
