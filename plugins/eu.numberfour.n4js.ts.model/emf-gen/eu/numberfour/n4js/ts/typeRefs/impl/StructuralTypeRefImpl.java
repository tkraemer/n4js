/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs.impl;

import eu.numberfour.n4js.ts.typeRefs.StructuralTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;
import eu.numberfour.n4js.ts.typeRefs.TypeVariableMapping;

import eu.numberfour.n4js.ts.types.TStructMember;
import eu.numberfour.n4js.ts.types.TStructuralType;
import eu.numberfour.n4js.ts.types.TypeVariable;
import eu.numberfour.n4js.ts.types.TypingStrategy;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xtext.xbase.lib.Functions.Function1;

import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structural Type Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.StructuralTypeRefImpl#getAstStructuralMembers <em>Ast Structural Members</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.StructuralTypeRefImpl#getStructuralType <em>Structural Type</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.StructuralTypeRefImpl#getGenStructuralMembers <em>Gen Structural Members</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.StructuralTypeRefImpl#getPostponedSubstitutions <em>Postponed Substitutions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class StructuralTypeRefImpl extends MinimalEObjectImpl.Container implements StructuralTypeRef {
	/**
	 * The cached value of the '{@link #getAstStructuralMembers() <em>Ast Structural Members</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAstStructuralMembers()
	 * @generated
	 * @ordered
	 */
	protected EList<TStructMember> astStructuralMembers;

	/**
	 * The cached value of the '{@link #getStructuralType() <em>Structural Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStructuralType()
	 * @generated
	 * @ordered
	 */
	protected TStructuralType structuralType;

	/**
	 * The cached value of the '{@link #getGenStructuralMembers() <em>Gen Structural Members</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenStructuralMembers()
	 * @generated
	 * @ordered
	 */
	protected EList<TStructMember> genStructuralMembers;

	/**
	 * The cached value of the '{@link #getPostponedSubstitutions() <em>Postponed Substitutions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostponedSubstitutions()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeVariableMapping> postponedSubstitutions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructuralTypeRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeRefsPackage.Literals.STRUCTURAL_TYPE_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TStructMember> getAstStructuralMembers() {
		if (astStructuralMembers == null) {
			astStructuralMembers = new EObjectContainmentEList<TStructMember>(TStructMember.class, this, TypeRefsPackage.STRUCTURAL_TYPE_REF__AST_STRUCTURAL_MEMBERS);
		}
		return astStructuralMembers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStructuralType getStructuralType() {
		if (structuralType != null && structuralType.eIsProxy()) {
			InternalEObject oldStructuralType = (InternalEObject)structuralType;
			structuralType = (TStructuralType)eResolveProxy(oldStructuralType);
			if (structuralType != oldStructuralType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypeRefsPackage.STRUCTURAL_TYPE_REF__STRUCTURAL_TYPE, oldStructuralType, structuralType));
			}
		}
		return structuralType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStructuralType basicGetStructuralType() {
		return structuralType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStructuralType(TStructuralType newStructuralType) {
		TStructuralType oldStructuralType = structuralType;
		structuralType = newStructuralType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeRefsPackage.STRUCTURAL_TYPE_REF__STRUCTURAL_TYPE, oldStructuralType, structuralType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TStructMember> getGenStructuralMembers() {
		if (genStructuralMembers == null) {
			genStructuralMembers = new EObjectContainmentEList<TStructMember>(TStructMember.class, this, TypeRefsPackage.STRUCTURAL_TYPE_REF__GEN_STRUCTURAL_MEMBERS);
		}
		return genStructuralMembers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeVariableMapping> getPostponedSubstitutions() {
		if (postponedSubstitutions == null) {
			postponedSubstitutions = new EObjectContainmentEList<TypeVariableMapping>(TypeVariableMapping.class, this, TypeRefsPackage.STRUCTURAL_TYPE_REF__POSTPONED_SUBSTITUTIONS);
		}
		return postponedSubstitutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypingStrategy getTypingStrategy() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypingStrategy(TypingStrategy typingStrategy) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TStructMember> getStructuralMembers() {
		EList<TStructMember> _xifexpression = null;
		TStructuralType _structuralType = this.getStructuralType();
		boolean _tripleNotEquals = (_structuralType != null);
		if (_tripleNotEquals) {
			TStructuralType _structuralType_1 = this.getStructuralType();
			_xifexpression = _structuralType_1.getOwnedMembers();
		}
		else {
			EList<TStructMember> _xifexpression_1 = null;
			EList<TStructMember> _astStructuralMembers = this.getAstStructuralMembers();
			boolean _isEmpty = _astStructuralMembers.isEmpty();
			boolean _not = (!_isEmpty);
			if (_not) {
				_xifexpression_1 = this.getAstStructuralMembers();
			}
			else {
				_xifexpression_1 = this.getGenStructuralMembers();
			}
			_xifexpression = _xifexpression_1;
		}
		return ECollections.<TStructMember>unmodifiableEList(_xifexpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean hasPostponedSubstitutionFor(final TypeVariable typeVar) {
		EList<TypeVariableMapping> _postponedSubstitutions = this.getPostponedSubstitutions();
		final Function1<TypeVariableMapping, Boolean> _function = new Function1<TypeVariableMapping, Boolean>() {
			public Boolean apply(final TypeVariableMapping m) {
				TypeVariable _typeVar = null;
				if (m!=null) {
					_typeVar=m.getTypeVar();
				}
				return Boolean.valueOf((_typeVar == typeVar));
			}
		};
		return IterableExtensions.<TypeVariableMapping>exists(_postponedSubstitutions, _function);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__AST_STRUCTURAL_MEMBERS:
				return ((InternalEList<?>)getAstStructuralMembers()).basicRemove(otherEnd, msgs);
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__GEN_STRUCTURAL_MEMBERS:
				return ((InternalEList<?>)getGenStructuralMembers()).basicRemove(otherEnd, msgs);
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__POSTPONED_SUBSTITUTIONS:
				return ((InternalEList<?>)getPostponedSubstitutions()).basicRemove(otherEnd, msgs);
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
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__AST_STRUCTURAL_MEMBERS:
				return getAstStructuralMembers();
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__STRUCTURAL_TYPE:
				if (resolve) return getStructuralType();
				return basicGetStructuralType();
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__GEN_STRUCTURAL_MEMBERS:
				return getGenStructuralMembers();
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__POSTPONED_SUBSTITUTIONS:
				return getPostponedSubstitutions();
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
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__AST_STRUCTURAL_MEMBERS:
				getAstStructuralMembers().clear();
				getAstStructuralMembers().addAll((Collection<? extends TStructMember>)newValue);
				return;
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__STRUCTURAL_TYPE:
				setStructuralType((TStructuralType)newValue);
				return;
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__GEN_STRUCTURAL_MEMBERS:
				getGenStructuralMembers().clear();
				getGenStructuralMembers().addAll((Collection<? extends TStructMember>)newValue);
				return;
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__POSTPONED_SUBSTITUTIONS:
				getPostponedSubstitutions().clear();
				getPostponedSubstitutions().addAll((Collection<? extends TypeVariableMapping>)newValue);
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
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__AST_STRUCTURAL_MEMBERS:
				getAstStructuralMembers().clear();
				return;
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__STRUCTURAL_TYPE:
				setStructuralType((TStructuralType)null);
				return;
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__GEN_STRUCTURAL_MEMBERS:
				getGenStructuralMembers().clear();
				return;
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__POSTPONED_SUBSTITUTIONS:
				getPostponedSubstitutions().clear();
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
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__AST_STRUCTURAL_MEMBERS:
				return astStructuralMembers != null && !astStructuralMembers.isEmpty();
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__STRUCTURAL_TYPE:
				return structuralType != null;
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__GEN_STRUCTURAL_MEMBERS:
				return genStructuralMembers != null && !genStructuralMembers.isEmpty();
			case TypeRefsPackage.STRUCTURAL_TYPE_REF__POSTPONED_SUBSTITUTIONS:
				return postponedSubstitutions != null && !postponedSubstitutions.isEmpty();
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
			case TypeRefsPackage.STRUCTURAL_TYPE_REF___GET_TYPING_STRATEGY:
				return getTypingStrategy();
			case TypeRefsPackage.STRUCTURAL_TYPE_REF___SET_TYPING_STRATEGY__TYPINGSTRATEGY:
				setTypingStrategy((TypingStrategy)arguments.get(0));
				return null;
			case TypeRefsPackage.STRUCTURAL_TYPE_REF___GET_STRUCTURAL_MEMBERS:
				return getStructuralMembers();
			case TypeRefsPackage.STRUCTURAL_TYPE_REF___HAS_POSTPONED_SUBSTITUTION_FOR__TYPEVARIABLE:
				return hasPostponedSubstitutionFor((TypeVariable)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //StructuralTypeRefImpl
