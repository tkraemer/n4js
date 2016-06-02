/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs.impl;

import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;
import eu.numberfour.n4js.ts.typeRefs.Wildcard;

import eu.numberfour.n4js.ts.types.ContainerType;
import eu.numberfour.n4js.ts.types.TypeVariable;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals;
import org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions;

import org.eclipse.xtext.xbase.lib.Functions.Function1;

import org.eclipse.xtext.xbase.lib.IterableExtensions;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wildcard</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.WildcardImpl#getDeclaredUpperBound <em>Declared Upper Bound</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.WildcardImpl#getDeclaredLowerBound <em>Declared Lower Bound</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.WildcardImpl#isUsingInOutNotation <em>Using In Out Notation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WildcardImpl extends TypeArgumentImpl implements Wildcard {
	/**
	 * The cached value of the '{@link #getDeclaredUpperBound() <em>Declared Upper Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredUpperBound()
	 * @generated
	 * @ordered
	 */
	protected TypeRef declaredUpperBound;

	/**
	 * The cached value of the '{@link #getDeclaredLowerBound() <em>Declared Lower Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredLowerBound()
	 * @generated
	 * @ordered
	 */
	protected TypeRef declaredLowerBound;

	/**
	 * The default value of the '{@link #isUsingInOutNotation() <em>Using In Out Notation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUsingInOutNotation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USING_IN_OUT_NOTATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUsingInOutNotation() <em>Using In Out Notation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUsingInOutNotation()
	 * @generated
	 * @ordered
	 */
	protected boolean usingInOutNotation = USING_IN_OUT_NOTATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WildcardImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeRefsPackage.Literals.WILDCARD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getDeclaredUpperBound() {
		return declaredUpperBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaredUpperBound(TypeRef newDeclaredUpperBound, NotificationChain msgs) {
		TypeRef oldDeclaredUpperBound = declaredUpperBound;
		declaredUpperBound = newDeclaredUpperBound;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypeRefsPackage.WILDCARD__DECLARED_UPPER_BOUND, oldDeclaredUpperBound, newDeclaredUpperBound);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredUpperBound(TypeRef newDeclaredUpperBound) {
		if (newDeclaredUpperBound != declaredUpperBound) {
			NotificationChain msgs = null;
			if (declaredUpperBound != null)
				msgs = ((InternalEObject)declaredUpperBound).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypeRefsPackage.WILDCARD__DECLARED_UPPER_BOUND, null, msgs);
			if (newDeclaredUpperBound != null)
				msgs = ((InternalEObject)newDeclaredUpperBound).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypeRefsPackage.WILDCARD__DECLARED_UPPER_BOUND, null, msgs);
			msgs = basicSetDeclaredUpperBound(newDeclaredUpperBound, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeRefsPackage.WILDCARD__DECLARED_UPPER_BOUND, newDeclaredUpperBound, newDeclaredUpperBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getDeclaredLowerBound() {
		return declaredLowerBound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaredLowerBound(TypeRef newDeclaredLowerBound, NotificationChain msgs) {
		TypeRef oldDeclaredLowerBound = declaredLowerBound;
		declaredLowerBound = newDeclaredLowerBound;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypeRefsPackage.WILDCARD__DECLARED_LOWER_BOUND, oldDeclaredLowerBound, newDeclaredLowerBound);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredLowerBound(TypeRef newDeclaredLowerBound) {
		if (newDeclaredLowerBound != declaredLowerBound) {
			NotificationChain msgs = null;
			if (declaredLowerBound != null)
				msgs = ((InternalEObject)declaredLowerBound).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypeRefsPackage.WILDCARD__DECLARED_LOWER_BOUND, null, msgs);
			if (newDeclaredLowerBound != null)
				msgs = ((InternalEObject)newDeclaredLowerBound).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypeRefsPackage.WILDCARD__DECLARED_LOWER_BOUND, null, msgs);
			msgs = basicSetDeclaredLowerBound(newDeclaredLowerBound, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeRefsPackage.WILDCARD__DECLARED_LOWER_BOUND, newDeclaredLowerBound, newDeclaredLowerBound));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUsingInOutNotation() {
		return usingInOutNotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsingInOutNotation(boolean newUsingInOutNotation) {
		boolean oldUsingInOutNotation = usingInOutNotation;
		usingInOutNotation = newUsingInOutNotation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeRefsPackage.WILDCARD__USING_IN_OUT_NOTATION, oldUsingInOutNotation, usingInOutNotation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeRef> getDeclaredOrImplicitUpperBounds() {
		final TypeRef declUB = this.getDeclaredUpperBound();
		if ((declUB != null)) {
			return XcoreCollectionLiterals.<TypeRef>newImmutableEList(declUB);
		}
		TypeRef _declaredLowerBound = this.getDeclaredLowerBound();
		boolean _tripleNotEquals = (_declaredLowerBound != null);
		if (_tripleNotEquals) {
			return XcoreCollectionLiterals.<TypeRef>emptyEList();
		}
		final EObject parent = this.eContainer();
		if ((parent instanceof ParameterizedTypeRef)) {
			EList<TypeArgument> _typeArgs = ((ParameterizedTypeRef)parent).getTypeArgs();
			final int typeArgIndex = _typeArgs.indexOf(this);
			if ((typeArgIndex >= 0)) {
				EReference _parameterizedTypeRef_DeclaredType = TypeRefsPackage.eINSTANCE.getParameterizedTypeRef_DeclaredType();
				final Object declType = ((ParameterizedTypeRef)parent).eGet(_parameterizedTypeRef_DeclaredType, false);
				if ((declType instanceof ContainerType<?>)) {
					boolean _eIsProxy = ((ContainerType<?>)declType).eIsProxy();
					boolean _not = (!_eIsProxy);
					if (_not) {
						final EList<TypeVariable> typeVars = ((ContainerType<?>)declType).getTypeVars();
						TypeVariable _xifexpression = null;
						int _size = typeVars.size();
						boolean _lessThan = (typeArgIndex < _size);
						if (_lessThan) {
							_xifexpression = typeVars.get(typeArgIndex);
						}
						else {
							_xifexpression = null;
						}
						final TypeVariable typeVar = _xifexpression;
						if ((typeVar != null)) {
							final EList<ParameterizedTypeRef> implicitUBs = typeVar.getDeclaredUpperBounds();
							return XcoreCollectionLiterals.<TypeRef>newImmutableEList(((TypeRef[])org.eclipse.xtext.xbase.lib.Conversions.unwrapArray(implicitUBs, TypeRef.class)));
						}
					}
				}
			}
		}
		return XcoreCollectionLiterals.<TypeRef>emptyEList();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeRefAsString() {
		final EList<TypeRef> upperBounds = this.getDeclaredOrImplicitUpperBounds();
		String _xifexpression = null;
		boolean _isEmpty = upperBounds.isEmpty();
		boolean _not = (!_isEmpty);
		if (_not) {
			final Function1<TypeRef, String> _function = new Function1<TypeRef, String>() {
				public String apply(final TypeRef it) {
					return it.getTypeRefAsString();
				}
			};
			EList<String> _map = XcoreEListExtensions.<TypeRef, String>map(upperBounds, _function);
			String _join = IterableExtensions.join(_map, " & ");
			_xifexpression = (" extends " + _join);
		}
		else {
			String _xifexpression_1 = null;
			TypeRef _declaredLowerBound = this.getDeclaredLowerBound();
			boolean _tripleNotEquals = (_declaredLowerBound != null);
			if (_tripleNotEquals) {
				TypeRef _declaredLowerBound_1 = this.getDeclaredLowerBound();
				String _typeRefAsString = _declaredLowerBound_1.getTypeRefAsString();
				_xifexpression_1 = (" super " + _typeRefAsString);
			}
			else {
				_xifexpression_1 = "";
			}
			_xifexpression = _xifexpression_1;
		}
		return ("?" + _xifexpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypeRefsPackage.WILDCARD__DECLARED_UPPER_BOUND:
				return basicSetDeclaredUpperBound(null, msgs);
			case TypeRefsPackage.WILDCARD__DECLARED_LOWER_BOUND:
				return basicSetDeclaredLowerBound(null, msgs);
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
			case TypeRefsPackage.WILDCARD__DECLARED_UPPER_BOUND:
				return getDeclaredUpperBound();
			case TypeRefsPackage.WILDCARD__DECLARED_LOWER_BOUND:
				return getDeclaredLowerBound();
			case TypeRefsPackage.WILDCARD__USING_IN_OUT_NOTATION:
				return isUsingInOutNotation();
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
			case TypeRefsPackage.WILDCARD__DECLARED_UPPER_BOUND:
				setDeclaredUpperBound((TypeRef)newValue);
				return;
			case TypeRefsPackage.WILDCARD__DECLARED_LOWER_BOUND:
				setDeclaredLowerBound((TypeRef)newValue);
				return;
			case TypeRefsPackage.WILDCARD__USING_IN_OUT_NOTATION:
				setUsingInOutNotation((Boolean)newValue);
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
			case TypeRefsPackage.WILDCARD__DECLARED_UPPER_BOUND:
				setDeclaredUpperBound((TypeRef)null);
				return;
			case TypeRefsPackage.WILDCARD__DECLARED_LOWER_BOUND:
				setDeclaredLowerBound((TypeRef)null);
				return;
			case TypeRefsPackage.WILDCARD__USING_IN_OUT_NOTATION:
				setUsingInOutNotation(USING_IN_OUT_NOTATION_EDEFAULT);
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
			case TypeRefsPackage.WILDCARD__DECLARED_UPPER_BOUND:
				return declaredUpperBound != null;
			case TypeRefsPackage.WILDCARD__DECLARED_LOWER_BOUND:
				return declaredLowerBound != null;
			case TypeRefsPackage.WILDCARD__USING_IN_OUT_NOTATION:
				return usingInOutNotation != USING_IN_OUT_NOTATION_EDEFAULT;
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
			case TypeRefsPackage.WILDCARD___GET_DECLARED_OR_IMPLICIT_UPPER_BOUNDS:
				return getDeclaredOrImplicitUpperBounds();
			case TypeRefsPackage.WILDCARD___GET_TYPE_REF_AS_STRING:
				return getTypeRefAsString();
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
		result.append(" (usingInOutNotation: ");
		result.append(usingInOutNotation);
		result.append(')');
		return result.toString();
	}

} //WildcardImpl
