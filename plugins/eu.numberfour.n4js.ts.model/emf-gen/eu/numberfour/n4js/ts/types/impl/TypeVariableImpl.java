/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types.impl;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.TypeVariable;
import eu.numberfour.n4js.ts.types.TypesPackage;

import eu.numberfour.n4js.ts.types.util.Variance;

import java.lang.Iterable;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals;

import org.eclipse.xtext.xbase.lib.Functions.Function1;

import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TypeVariableImpl#isDeclaredCovariant <em>Declared Covariant</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TypeVariableImpl#isDeclaredContravariant <em>Declared Contravariant</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TypeVariableImpl#getDeclaredUpperBounds <em>Declared Upper Bounds</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TypeVariableImpl extends TypeImpl implements TypeVariable {
	/**
	 * The default value of the '{@link #isDeclaredCovariant() <em>Declared Covariant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeclaredCovariant()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DECLARED_COVARIANT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeclaredCovariant() <em>Declared Covariant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeclaredCovariant()
	 * @generated
	 * @ordered
	 */
	protected boolean declaredCovariant = DECLARED_COVARIANT_EDEFAULT;

	/**
	 * The default value of the '{@link #isDeclaredContravariant() <em>Declared Contravariant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeclaredContravariant()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DECLARED_CONTRAVARIANT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeclaredContravariant() <em>Declared Contravariant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeclaredContravariant()
	 * @generated
	 * @ordered
	 */
	protected boolean declaredContravariant = DECLARED_CONTRAVARIANT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDeclaredUpperBounds() <em>Declared Upper Bounds</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredUpperBounds()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeRef> declaredUpperBounds;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeVariableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.TYPE_VARIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeclaredCovariant() {
		return declaredCovariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredCovariant(boolean newDeclaredCovariant) {
		boolean oldDeclaredCovariant = declaredCovariant;
		declaredCovariant = newDeclaredCovariant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TYPE_VARIABLE__DECLARED_COVARIANT, oldDeclaredCovariant, declaredCovariant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeclaredContravariant() {
		return declaredContravariant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredContravariant(boolean newDeclaredContravariant) {
		boolean oldDeclaredContravariant = declaredContravariant;
		declaredContravariant = newDeclaredContravariant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TYPE_VARIABLE__DECLARED_CONTRAVARIANT, oldDeclaredContravariant, declaredContravariant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeRef> getDeclaredUpperBounds() {
		if (declaredUpperBounds == null) {
			declaredUpperBounds = new EObjectContainmentEList<TypeRef>(TypeRef.class, this, TypesPackage.TYPE_VARIABLE__DECLARED_UPPER_BOUNDS);
		}
		return declaredUpperBounds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variance getVariance() {
		final boolean co = this.isDeclaredCovariant();
		final boolean contra = this.isDeclaredContravariant();
		if ((co && (!contra))) {
			return Variance.CO;
		}
		else {
			if ((contra && (!co))) {
				return Variance.CONTRA;
			}
			else {
				return Variance.INV;
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeVariable> getTypeVars() {
		return XcoreCollectionLiterals.<TypeVariable>emptyEList();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeAsString() {
		EList<TypeRef> _declaredUpperBounds = this.getDeclaredUpperBounds();
		return this.getTypeVariableAsString(_declaredUpperBounds);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeVariableAsString(final Collection<? extends TypeRef> upperBounds) {
		String _xifexpression = null;
		boolean _isDeclaredCovariant = this.isDeclaredCovariant();
		if (_isDeclaredCovariant) {
			_xifexpression = "out ";
		}
		else {
			String _xifexpression_1 = null;
			boolean _isDeclaredContravariant = this.isDeclaredContravariant();
			if (_isDeclaredContravariant) {
				_xifexpression_1 = "in ";
			}
			else {
				_xifexpression_1 = "";
			}
			_xifexpression = _xifexpression_1;
		}
		String _name = this.getName();
		String _plus = (_xifexpression + _name);
		String _xifexpression_2 = null;
		boolean _isEmpty = upperBounds.isEmpty();
		boolean _not = (!_isEmpty);
		if (_not) {
			final Function1<TypeRef, String> _function = new Function1<TypeRef, String>() {
				public String apply(final TypeRef it) {
					return it.getTypeRefAsString();
				}
			};
			Iterable<String> _map = IterableExtensions.map(upperBounds, _function);
			String _join = IterableExtensions.join(_map, " & ");
			_xifexpression_2 = (" extends " + _join);
		}
		else {
			_xifexpression_2 = "";
		}
		return (_plus + _xifexpression_2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypesPackage.TYPE_VARIABLE__DECLARED_UPPER_BOUNDS:
				return ((InternalEList<?>)getDeclaredUpperBounds()).basicRemove(otherEnd, msgs);
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
			case TypesPackage.TYPE_VARIABLE__DECLARED_COVARIANT:
				return isDeclaredCovariant();
			case TypesPackage.TYPE_VARIABLE__DECLARED_CONTRAVARIANT:
				return isDeclaredContravariant();
			case TypesPackage.TYPE_VARIABLE__DECLARED_UPPER_BOUNDS:
				return getDeclaredUpperBounds();
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
			case TypesPackage.TYPE_VARIABLE__DECLARED_COVARIANT:
				setDeclaredCovariant((Boolean)newValue);
				return;
			case TypesPackage.TYPE_VARIABLE__DECLARED_CONTRAVARIANT:
				setDeclaredContravariant((Boolean)newValue);
				return;
			case TypesPackage.TYPE_VARIABLE__DECLARED_UPPER_BOUNDS:
				getDeclaredUpperBounds().clear();
				getDeclaredUpperBounds().addAll((Collection<? extends TypeRef>)newValue);
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
			case TypesPackage.TYPE_VARIABLE__DECLARED_COVARIANT:
				setDeclaredCovariant(DECLARED_COVARIANT_EDEFAULT);
				return;
			case TypesPackage.TYPE_VARIABLE__DECLARED_CONTRAVARIANT:
				setDeclaredContravariant(DECLARED_CONTRAVARIANT_EDEFAULT);
				return;
			case TypesPackage.TYPE_VARIABLE__DECLARED_UPPER_BOUNDS:
				getDeclaredUpperBounds().clear();
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
			case TypesPackage.TYPE_VARIABLE__DECLARED_COVARIANT:
				return declaredCovariant != DECLARED_COVARIANT_EDEFAULT;
			case TypesPackage.TYPE_VARIABLE__DECLARED_CONTRAVARIANT:
				return declaredContravariant != DECLARED_CONTRAVARIANT_EDEFAULT;
			case TypesPackage.TYPE_VARIABLE__DECLARED_UPPER_BOUNDS:
				return declaredUpperBounds != null && !declaredUpperBounds.isEmpty();
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
		if (baseClass == Type.class) {
			switch (baseOperationID) {
				case TypesPackage.TYPE___GET_TYPE_VARS: return TypesPackage.TYPE_VARIABLE___GET_TYPE_VARS;
				case TypesPackage.TYPE___GET_TYPE_AS_STRING: return TypesPackage.TYPE_VARIABLE___GET_TYPE_AS_STRING;
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
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case TypesPackage.TYPE_VARIABLE___GET_VARIANCE:
				return getVariance();
			case TypesPackage.TYPE_VARIABLE___GET_TYPE_VARS:
				return getTypeVars();
			case TypesPackage.TYPE_VARIABLE___GET_TYPE_AS_STRING:
				return getTypeAsString();
			case TypesPackage.TYPE_VARIABLE___GET_TYPE_VARIABLE_AS_STRING__COLLECTION:
				return getTypeVariableAsString((Collection<? extends TypeRef>)arguments.get(0));
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
		result.append(" (declaredCovariant: ");
		result.append(declaredCovariant);
		result.append(", declaredContravariant: ");
		result.append(declaredContravariant);
		result.append(')');
		return result.toString();
	}

} //TypeVariableImpl
