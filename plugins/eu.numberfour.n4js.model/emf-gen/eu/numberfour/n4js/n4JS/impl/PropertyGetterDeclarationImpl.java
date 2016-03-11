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
import eu.numberfour.n4js.n4JS.AnnotablePropertyAssignment;
import eu.numberfour.n4js.n4JS.Annotation;
import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.FieldAccessor;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.PropertyAssignment;
import eu.numberfour.n4js.n4JS.PropertyAssignmentAnnotationList;
import eu.numberfour.n4js.n4JS.PropertyGetterDeclaration;
import eu.numberfour.n4js.n4JS.PropertyNameOwner;

import eu.numberfour.n4js.ts.types.TGetter;
import eu.numberfour.n4js.ts.types.TStructGetter;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Getter Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.PropertyGetterDeclarationImpl#getComputeNameFrom <em>Compute Name From</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.PropertyGetterDeclarationImpl#getAnnotationList <em>Annotation List</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertyGetterDeclarationImpl extends GetterDeclarationImpl implements PropertyGetterDeclaration {
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
	 * The cached value of the '{@link #getAnnotationList() <em>Annotation List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotationList()
	 * @generated
	 * @ordered
	 */
	protected PropertyAssignmentAnnotationList annotationList;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyGetterDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSPackage.Literals.PROPERTY_GETTER_DECLARATION;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM, oldComputeNameFrom, newComputeNameFrom);
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
				msgs = ((InternalEObject)computeNameFrom).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM, null, msgs);
			if (newComputeNameFrom != null)
				msgs = ((InternalEObject)newComputeNameFrom).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM, null, msgs);
			msgs = basicSetComputeNameFrom(newComputeNameFrom, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM, newComputeNameFrom, newComputeNameFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyAssignmentAnnotationList getAnnotationList() {
		return annotationList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnnotationList(PropertyAssignmentAnnotationList newAnnotationList, NotificationChain msgs) {
		PropertyAssignmentAnnotationList oldAnnotationList = annotationList;
		annotationList = newAnnotationList;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST, oldAnnotationList, newAnnotationList);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnnotationList(PropertyAssignmentAnnotationList newAnnotationList) {
		if (newAnnotationList != annotationList) {
			NotificationChain msgs = null;
			if (annotationList != null)
				msgs = ((InternalEObject)annotationList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST, null, msgs);
			if (newAnnotationList != null)
				msgs = ((InternalEObject)newAnnotationList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST, null, msgs);
			msgs = basicSetAnnotationList(newAnnotationList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST, newAnnotationList, newAnnotationList));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStructGetter getDefinedGetter() {
		TGetter _definedGetter = super.getDefinedGetter();
		return ((TStructGetter) _definedGetter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStructGetter getDefinedMember() {
		return this.getDefinedGetter();
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
	public EList<Annotation> getAnnotations() {
		EList<Annotation> _elvis = null;
		PropertyAssignmentAnnotationList _annotationList = this.getAnnotationList();
		EList<Annotation> _annotations = null;
		if (_annotationList!=null) {
			_annotations=_annotationList.getAnnotations();
		}
		if (_annotations != null) {
			_elvis = _annotations;
		} else {
			EList<Annotation> _emptyEList = XcoreCollectionLiterals.<Annotation>emptyEList();
			_elvis = _emptyEList;
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
			case N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM:
				return basicSetComputeNameFrom(null, msgs);
			case N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST:
				return basicSetAnnotationList(null, msgs);
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
			case N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM:
				return getComputeNameFrom();
			case N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST:
				return getAnnotationList();
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
			case N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM:
				setComputeNameFrom((Expression)newValue);
				return;
			case N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST:
				setAnnotationList((PropertyAssignmentAnnotationList)newValue);
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
			case N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM:
				setComputeNameFrom((Expression)null);
				return;
			case N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST:
				setAnnotationList((PropertyAssignmentAnnotationList)null);
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
			case N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM:
				return computeNameFrom != null;
			case N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST:
				return annotationList != null;
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
		if (baseClass == PropertyAssignment.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM: return N4JSPackage.PROPERTY_ASSIGNMENT__COMPUTE_NAME_FROM;
				default: return -1;
			}
		}
		if (baseClass == AnnotablePropertyAssignment.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST: return N4JSPackage.ANNOTABLE_PROPERTY_ASSIGNMENT__ANNOTATION_LIST;
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
		if (baseClass == PropertyAssignment.class) {
			switch (baseFeatureID) {
				case N4JSPackage.PROPERTY_ASSIGNMENT__COMPUTE_NAME_FROM: return N4JSPackage.PROPERTY_GETTER_DECLARATION__COMPUTE_NAME_FROM;
				default: return -1;
			}
		}
		if (baseClass == AnnotablePropertyAssignment.class) {
			switch (baseFeatureID) {
				case N4JSPackage.ANNOTABLE_PROPERTY_ASSIGNMENT__ANNOTATION_LIST: return N4JSPackage.PROPERTY_GETTER_DECLARATION__ANNOTATION_LIST;
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
				case N4JSPackage.ANNOTABLE_ELEMENT___GET_ANNOTATIONS: return N4JSPackage.PROPERTY_GETTER_DECLARATION___GET_ANNOTATIONS;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (baseOperationID) {
				case N4JSPackage.PROPERTY_NAME_OWNER___GET_DEFINED_MEMBER: return N4JSPackage.PROPERTY_GETTER_DECLARATION___GET_DEFINED_MEMBER;
				case N4JSPackage.PROPERTY_NAME_OWNER___IS_VALID_NAME: return N4JSPackage.PROPERTY_GETTER_DECLARATION___IS_VALID_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == FieldAccessor.class) {
			switch (baseOperationID) {
				case N4JSPackage.FIELD_ACCESSOR___GET_DEFINED_MEMBER: return N4JSPackage.PROPERTY_GETTER_DECLARATION___GET_DEFINED_MEMBER;
				case N4JSPackage.FIELD_ACCESSOR___IS_VALID_NAME: return N4JSPackage.PROPERTY_GETTER_DECLARATION___IS_VALID_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == PropertyAssignment.class) {
			switch (baseOperationID) {
				case N4JSPackage.PROPERTY_ASSIGNMENT___GET_DEFINED_MEMBER: return N4JSPackage.PROPERTY_GETTER_DECLARATION___GET_DEFINED_MEMBER;
				case N4JSPackage.PROPERTY_ASSIGNMENT___IS_VALID_NAME: return N4JSPackage.PROPERTY_GETTER_DECLARATION___IS_VALID_NAME;
				default: return -1;
			}
		}
		if (baseClass == AnnotablePropertyAssignment.class) {
			switch (baseOperationID) {
				case N4JSPackage.ANNOTABLE_PROPERTY_ASSIGNMENT___GET_ANNOTATIONS: return N4JSPackage.PROPERTY_GETTER_DECLARATION___GET_ANNOTATIONS;
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
			case N4JSPackage.PROPERTY_GETTER_DECLARATION___GET_DEFINED_GETTER:
				return getDefinedGetter();
			case N4JSPackage.PROPERTY_GETTER_DECLARATION___GET_DEFINED_MEMBER:
				return getDefinedMember();
			case N4JSPackage.PROPERTY_GETTER_DECLARATION___IS_VALID_NAME:
				return isValidName();
			case N4JSPackage.PROPERTY_GETTER_DECLARATION___GET_ANNOTATIONS:
				return getAnnotations();
		}
		return super.eInvoke(operationID, arguments);
	}

} //PropertyGetterDeclarationImpl
