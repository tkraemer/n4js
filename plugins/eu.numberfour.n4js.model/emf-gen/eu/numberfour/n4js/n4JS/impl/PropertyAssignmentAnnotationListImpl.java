/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS.impl;

import com.google.common.base.Objects;

import eu.numberfour.n4js.n4JS.AnnotableElement;
import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.NamedElement;
import eu.numberfour.n4js.n4JS.PropertyAssignment;
import eu.numberfour.n4js.n4JS.PropertyAssignmentAnnotationList;
import eu.numberfour.n4js.n4JS.PropertyNameKind;
import eu.numberfour.n4js.n4JS.PropertyNameOwner;
import eu.numberfour.n4js.n4JS.VariableEnvironmentElement;

import eu.numberfour.n4js.ts.types.TStructMember;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Assignment Annotation List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.PropertyAssignmentAnnotationListImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.PropertyAssignmentAnnotationListImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.PropertyAssignmentAnnotationListImpl#getComputeNameFrom <em>Compute Name From</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertyAssignmentAnnotationListImpl extends AbstractAnnotationListImpl implements PropertyAssignmentAnnotationList {
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
	 * The cached value of the '{@link #getComputeNameFrom() <em>Compute Name From</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComputeNameFrom()
	 * @generated
	 * @ordered
	 */
	protected Expression computeNameFrom;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyAssignmentAnnotationListImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSPackage.Literals.PROPERTY_ASSIGNMENT_ANNOTATION_LIST;
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
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__KIND, oldKind, kind));
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
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getComputeNameFrom() {
		return computeNameFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComputeNameFrom(Expression newComputeNameFrom, NotificationChain msgs) {
		Expression oldComputeNameFrom = computeNameFrom;
		computeNameFrom = newComputeNameFrom;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM, oldComputeNameFrom, newComputeNameFrom);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComputeNameFrom(Expression newComputeNameFrom) {
		if (newComputeNameFrom != computeNameFrom) {
			NotificationChain msgs = null;
			if (computeNameFrom != null)
				msgs = ((InternalEObject)computeNameFrom).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM, null, msgs);
			if (newComputeNameFrom != null)
				msgs = ((InternalEObject)newComputeNameFrom).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM, null, msgs);
			msgs = basicSetComputeNameFrom(newComputeNameFrom, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM, newComputeNameFrom, newComputeNameFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStructMember getDefinedMember() {
		final EObject c = this.eContainer();
		if ((c instanceof PropertyAssignment)) {
			return ((PropertyAssignment)c).getDefinedMember();
		}
		return null;
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
		boolean _and = false;
		String _name_1 = this.getName();
		boolean _equals_1 = Objects.equal("constructor", _name_1);
		if (!_equals_1) {
			_and = false;
		} else {
			PropertyNameKind _kind = this.getKind();
			boolean _tripleNotEquals = (_kind != PropertyNameKind.COMPUTED_FROM_STRING_LITERAL);
			_and = _tripleNotEquals;
		}
		if (_and) {
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
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM:
				return basicSetComputeNameFrom(null, msgs);
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
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__KIND:
				return getKind();
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__NAME:
				return getName();
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM:
				return getComputeNameFrom();
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
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__KIND:
				setKind((PropertyNameKind)newValue);
				return;
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__NAME:
				setName((String)newValue);
				return;
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM:
				setComputeNameFrom((Expression)newValue);
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
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM:
				setComputeNameFrom((Expression)null);
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
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__KIND:
				return kind != KIND_EDEFAULT;
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM:
				return computeNameFrom != null;
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
		if (baseClass == AnnotableElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == VariableEnvironmentElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__KIND: return N4JSPackage.PROPERTY_NAME_OWNER__KIND;
				case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__NAME: return N4JSPackage.PROPERTY_NAME_OWNER__NAME;
				default: return -1;
			}
		}
		if (baseClass == PropertyAssignment.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM: return N4JSPackage.PROPERTY_ASSIGNMENT__COMPUTE_NAME_FROM;
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
		if (baseClass == AnnotableElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == VariableEnvironmentElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (baseFeatureID) {
				case N4JSPackage.PROPERTY_NAME_OWNER__KIND: return N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__KIND;
				case N4JSPackage.PROPERTY_NAME_OWNER__NAME: return N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__NAME;
				default: return -1;
			}
		}
		if (baseClass == PropertyAssignment.class) {
			switch (baseFeatureID) {
				case N4JSPackage.PROPERTY_ASSIGNMENT__COMPUTE_NAME_FROM: return N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST__COMPUTE_NAME_FROM;
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
		if (baseClass == AnnotableElement.class) {
			switch (baseOperationID) {
				case N4JSPackage.ANNOTABLE_ELEMENT___GET_ANNOTATIONS: return N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST___GET_ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == VariableEnvironmentElement.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseOperationID) {
				case N4JSPackage.NAMED_ELEMENT___GET_NAME: return N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST___GET_NAME;
				default: return -1;
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (baseOperationID) {
				case N4JSPackage.PROPERTY_NAME_OWNER___GET_DEFINED_MEMBER: return N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST___GET_DEFINED_MEMBER;
				case N4JSPackage.PROPERTY_NAME_OWNER___IS_VALID_NAME: return N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST___IS_VALID_NAME;
				default: return -1;
			}
		}
		if (baseClass == PropertyAssignment.class) {
			switch (baseOperationID) {
				case N4JSPackage.PROPERTY_ASSIGNMENT___GET_DEFINED_MEMBER: return N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST___GET_DEFINED_MEMBER;
				case N4JSPackage.PROPERTY_ASSIGNMENT___IS_VALID_NAME: return N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST___IS_VALID_NAME;
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
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST___GET_DEFINED_MEMBER:
				return getDefinedMember();
			case N4JSPackage.PROPERTY_ASSIGNMENT_ANNOTATION_LIST___IS_VALID_NAME:
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

} //PropertyAssignmentAnnotationListImpl
