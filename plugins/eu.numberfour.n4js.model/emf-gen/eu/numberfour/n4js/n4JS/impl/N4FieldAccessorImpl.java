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
import eu.numberfour.n4js.n4JS.AnnotableN4MemberDeclaration;
import eu.numberfour.n4js.n4JS.Annotation;
import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.FieldAccessor;
import eu.numberfour.n4js.n4JS.ModifiableElement;
import eu.numberfour.n4js.n4JS.N4ClassifierDefinition;
import eu.numberfour.n4js.n4JS.N4FieldAccessor;
import eu.numberfour.n4js.n4JS.N4InterfaceDeclaration;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.N4MemberAnnotationList;
import eu.numberfour.n4js.n4JS.N4MemberDeclaration;
import eu.numberfour.n4js.n4JS.N4Modifier;
import eu.numberfour.n4js.n4JS.PropertyNameKind;
import eu.numberfour.n4js.n4JS.PropertyNameOwner;

import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TStructMember;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals;

import org.eclipse.xtext.xbase.lib.Functions.Function1;

import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>N4 Field Accessor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldAccessorImpl#getDeclaredModifiers <em>Declared Modifiers</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldAccessorImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldAccessorImpl#getAnnotationList <em>Annotation List</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldAccessorImpl#getComputeNameFrom <em>Compute Name From</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class N4FieldAccessorImpl extends FieldAccessorImpl implements N4FieldAccessor {
	/**
	 * The cached value of the '{@link #getDeclaredModifiers() <em>Declared Modifiers</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredModifiers()
	 * @generated
	 * @ordered
	 */
	protected EList<N4Modifier> declaredModifiers;

	/**
	 * The cached value of the '{@link #getAnnotationList() <em>Annotation List</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotationList()
	 * @generated
	 * @ordered
	 */
	protected N4MemberAnnotationList annotationList;

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
	protected N4FieldAccessorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSPackage.Literals.N4_FIELD_ACCESSOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<N4Modifier> getDeclaredModifiers() {
		if (declaredModifiers == null) {
			declaredModifiers = new EDataTypeEList<N4Modifier>(N4Modifier.class, this, N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_MODIFIERS);
		}
		return declaredModifiers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public N4ClassifierDefinition getOwner() {
		if (eContainerFeatureID() != N4JSPackage.N4_FIELD_ACCESSOR__OWNER) return null;
		return (N4ClassifierDefinition)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public N4ClassifierDefinition basicGetOwner() {
		if (eContainerFeatureID() != N4JSPackage.N4_FIELD_ACCESSOR__OWNER) return null;
		return (N4ClassifierDefinition)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwner(N4ClassifierDefinition newOwner, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOwner, N4JSPackage.N4_FIELD_ACCESSOR__OWNER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwner(N4ClassifierDefinition newOwner) {
		if (newOwner != eInternalContainer() || (eContainerFeatureID() != N4JSPackage.N4_FIELD_ACCESSOR__OWNER && newOwner != null)) {
			if (EcoreUtil.isAncestor(this, newOwner))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwner != null)
				msgs = ((InternalEObject)newOwner).eInverseAdd(this, N4JSPackage.N4_CLASSIFIER_DEFINITION__OWNED_MEMBERS_RAW, N4ClassifierDefinition.class, msgs);
			msgs = basicSetOwner(newOwner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__OWNER, newOwner, newOwner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public N4MemberAnnotationList getAnnotationList() {
		return annotationList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAnnotationList(N4MemberAnnotationList newAnnotationList, NotificationChain msgs) {
		N4MemberAnnotationList oldAnnotationList = annotationList;
		annotationList = newAnnotationList;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST, oldAnnotationList, newAnnotationList);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnnotationList(N4MemberAnnotationList newAnnotationList) {
		if (newAnnotationList != annotationList) {
			NotificationChain msgs = null;
			if (annotationList != null)
				msgs = ((InternalEObject)annotationList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST, null, msgs);
			if (newAnnotationList != null)
				msgs = ((InternalEObject)newAnnotationList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST, null, msgs);
			msgs = basicSetAnnotationList(newAnnotationList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST, newAnnotationList, newAnnotationList));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__COMPUTE_NAME_FROM, oldComputeNameFrom, newComputeNameFrom);
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
				msgs = ((InternalEObject)computeNameFrom).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_ACCESSOR__COMPUTE_NAME_FROM, null, msgs);
			if (newComputeNameFrom != null)
				msgs = ((InternalEObject)newComputeNameFrom).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_ACCESSOR__COMPUTE_NAME_FROM, null, msgs);
			msgs = basicSetComputeNameFrom(newComputeNameFrom, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__COMPUTE_NAME_FROM, newComputeNameFrom, newComputeNameFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return ((((this.eContainer() instanceof N4InterfaceDeclaration) && (this.getBody() == null)) && 
			(!IterableExtensions.<Annotation>exists(this.getAnnotations(), new Function1<Annotation, Boolean>() {
				public Boolean apply(final Annotation it) {
					String _name = it.getName();
					return Boolean.valueOf(Objects.equal(_name, "ProvidesDefaultImplementation"));
				}
			}))) || 
			this.getDeclaredModifiers().contains(N4Modifier.ABSTRACT));
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
		if ((Objects.equal("constructor", this.getName()) && (this.getKind() != PropertyNameKind.COMPUTED_FROM_STRING_LITERAL))) {
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
		N4MemberAnnotationList _annotationList = this.getAnnotationList();
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
	public TStructMember getDefinedMember() {
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMember getDefinedTypeElement() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeclaredStatic() {
		EList<N4Modifier> _declaredModifiers = this.getDeclaredModifiers();
		return _declaredModifiers.contains(N4Modifier.STATIC);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic() {
		return this.isDeclaredStatic();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeclaredFinal() {
		EList<Annotation> _annotations = this.getAnnotations();
		final Function1<Annotation, Boolean> _function = new Function1<Annotation, Boolean>() {
			public Boolean apply(final Annotation it) {
				String _name = it.getName();
				return Boolean.valueOf(Objects.equal(_name, "Final"));
			}
		};
		return IterableExtensions.<Annotation>exists(_annotations, _function);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinal() {
		return this.isDeclaredFinal();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConstructor() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCallableConstructor() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case N4JSPackage.N4_FIELD_ACCESSOR__OWNER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwner((N4ClassifierDefinition)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case N4JSPackage.N4_FIELD_ACCESSOR__OWNER:
				return basicSetOwner(null, msgs);
			case N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST:
				return basicSetAnnotationList(null, msgs);
			case N4JSPackage.N4_FIELD_ACCESSOR__COMPUTE_NAME_FROM:
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case N4JSPackage.N4_FIELD_ACCESSOR__OWNER:
				return eInternalContainer().eInverseRemove(this, N4JSPackage.N4_CLASSIFIER_DEFINITION__OWNED_MEMBERS_RAW, N4ClassifierDefinition.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_MODIFIERS:
				return getDeclaredModifiers();
			case N4JSPackage.N4_FIELD_ACCESSOR__OWNER:
				if (resolve) return getOwner();
				return basicGetOwner();
			case N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST:
				return getAnnotationList();
			case N4JSPackage.N4_FIELD_ACCESSOR__COMPUTE_NAME_FROM:
				return getComputeNameFrom();
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
			case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_MODIFIERS:
				getDeclaredModifiers().clear();
				getDeclaredModifiers().addAll((Collection<? extends N4Modifier>)newValue);
				return;
			case N4JSPackage.N4_FIELD_ACCESSOR__OWNER:
				setOwner((N4ClassifierDefinition)newValue);
				return;
			case N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST:
				setAnnotationList((N4MemberAnnotationList)newValue);
				return;
			case N4JSPackage.N4_FIELD_ACCESSOR__COMPUTE_NAME_FROM:
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
			case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_MODIFIERS:
				getDeclaredModifiers().clear();
				return;
			case N4JSPackage.N4_FIELD_ACCESSOR__OWNER:
				setOwner((N4ClassifierDefinition)null);
				return;
			case N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST:
				setAnnotationList((N4MemberAnnotationList)null);
				return;
			case N4JSPackage.N4_FIELD_ACCESSOR__COMPUTE_NAME_FROM:
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
			case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_MODIFIERS:
				return declaredModifiers != null && !declaredModifiers.isEmpty();
			case N4JSPackage.N4_FIELD_ACCESSOR__OWNER:
				return basicGetOwner() != null;
			case N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST:
				return annotationList != null;
			case N4JSPackage.N4_FIELD_ACCESSOR__COMPUTE_NAME_FROM:
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
		if (baseClass == ModifiableElement.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_MODIFIERS: return N4JSPackage.MODIFIABLE_ELEMENT__DECLARED_MODIFIERS;
				default: return -1;
			}
		}
		if (baseClass == N4MemberDeclaration.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.N4_FIELD_ACCESSOR__OWNER: return N4JSPackage.N4_MEMBER_DECLARATION__OWNER;
				default: return -1;
			}
		}
		if (baseClass == AnnotableN4MemberDeclaration.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST: return N4JSPackage.ANNOTABLE_N4_MEMBER_DECLARATION__ANNOTATION_LIST;
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
		if (baseClass == ModifiableElement.class) {
			switch (baseFeatureID) {
				case N4JSPackage.MODIFIABLE_ELEMENT__DECLARED_MODIFIERS: return N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_MODIFIERS;
				default: return -1;
			}
		}
		if (baseClass == N4MemberDeclaration.class) {
			switch (baseFeatureID) {
				case N4JSPackage.N4_MEMBER_DECLARATION__OWNER: return N4JSPackage.N4_FIELD_ACCESSOR__OWNER;
				default: return -1;
			}
		}
		if (baseClass == AnnotableN4MemberDeclaration.class) {
			switch (baseFeatureID) {
				case N4JSPackage.ANNOTABLE_N4_MEMBER_DECLARATION__ANNOTATION_LIST: return N4JSPackage.N4_FIELD_ACCESSOR__ANNOTATION_LIST;
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
				case N4JSPackage.ANNOTABLE_ELEMENT___GET_ANNOTATIONS: return N4JSPackage.N4_FIELD_ACCESSOR___GET_ANNOTATIONS;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (baseOperationID) {
				case N4JSPackage.PROPERTY_NAME_OWNER___GET_DEFINED_MEMBER: return N4JSPackage.N4_FIELD_ACCESSOR___GET_DEFINED_MEMBER;
				case N4JSPackage.PROPERTY_NAME_OWNER___IS_VALID_NAME: return N4JSPackage.N4_FIELD_ACCESSOR___IS_VALID_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == FieldAccessor.class) {
			switch (baseOperationID) {
				case N4JSPackage.FIELD_ACCESSOR___GET_DEFINED_MEMBER: return N4JSPackage.N4_FIELD_ACCESSOR___GET_DEFINED_MEMBER;
				case N4JSPackage.FIELD_ACCESSOR___IS_VALID_NAME: return N4JSPackage.N4_FIELD_ACCESSOR___IS_VALID_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == ModifiableElement.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == N4MemberDeclaration.class) {
			switch (baseOperationID) {
				case N4JSPackage.N4_MEMBER_DECLARATION___GET_DEFINED_TYPE_ELEMENT: return N4JSPackage.N4_FIELD_ACCESSOR___GET_DEFINED_TYPE_ELEMENT;
				case N4JSPackage.N4_MEMBER_DECLARATION___IS_DECLARED_STATIC: return N4JSPackage.N4_FIELD_ACCESSOR___IS_DECLARED_STATIC;
				case N4JSPackage.N4_MEMBER_DECLARATION___IS_STATIC: return N4JSPackage.N4_FIELD_ACCESSOR___IS_STATIC;
				case N4JSPackage.N4_MEMBER_DECLARATION___IS_DECLARED_FINAL: return N4JSPackage.N4_FIELD_ACCESSOR___IS_DECLARED_FINAL;
				case N4JSPackage.N4_MEMBER_DECLARATION___IS_FINAL: return N4JSPackage.N4_FIELD_ACCESSOR___IS_FINAL;
				case N4JSPackage.N4_MEMBER_DECLARATION___IS_CONSTRUCTOR: return N4JSPackage.N4_FIELD_ACCESSOR___IS_CONSTRUCTOR;
				case N4JSPackage.N4_MEMBER_DECLARATION___IS_CALLABLE_CONSTRUCTOR: return N4JSPackage.N4_FIELD_ACCESSOR___IS_CALLABLE_CONSTRUCTOR;
				default: return -1;
			}
		}
		if (baseClass == AnnotableN4MemberDeclaration.class) {
			switch (baseOperationID) {
				case N4JSPackage.ANNOTABLE_N4_MEMBER_DECLARATION___GET_ANNOTATIONS: return N4JSPackage.N4_FIELD_ACCESSOR___GET_ANNOTATIONS;
				case N4JSPackage.ANNOTABLE_N4_MEMBER_DECLARATION___GET_DEFINED_MEMBER: return N4JSPackage.N4_FIELD_ACCESSOR___GET_DEFINED_MEMBER;
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
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_ABSTRACT:
				return isAbstract();
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_VALID_NAME:
				return isValidName();
			case N4JSPackage.N4_FIELD_ACCESSOR___GET_ANNOTATIONS:
				return getAnnotations();
			case N4JSPackage.N4_FIELD_ACCESSOR___GET_DEFINED_MEMBER:
				return getDefinedMember();
			case N4JSPackage.N4_FIELD_ACCESSOR___GET_DEFINED_TYPE_ELEMENT:
				return getDefinedTypeElement();
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_DECLARED_STATIC:
				return isDeclaredStatic();
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_STATIC:
				return isStatic();
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_DECLARED_FINAL:
				return isDeclaredFinal();
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_FINAL:
				return isFinal();
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_CONSTRUCTOR:
				return isConstructor();
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_CALLABLE_CONSTRUCTOR:
				return isCallableConstructor();
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
		result.append(" (declaredModifiers: ");
		result.append(declaredModifiers);
		result.append(')');
		return result.toString();
	}

} //N4FieldAccessorImpl
