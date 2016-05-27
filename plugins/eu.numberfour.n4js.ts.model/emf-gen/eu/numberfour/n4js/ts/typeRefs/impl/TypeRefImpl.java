/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs.impl;

import com.google.common.base.Objects;

import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;

import eu.numberfour.n4js.ts.types.AnyType;
import eu.numberfour.n4js.ts.types.NullModifier;
import eu.numberfour.n4js.ts.types.PrimitiveType;
import eu.numberfour.n4js.ts.types.TClass;
import eu.numberfour.n4js.ts.types.TEnum;
import eu.numberfour.n4js.ts.types.TObjectPrototype;
import eu.numberfour.n4js.ts.types.TStructMember;
import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.TypingStrategy;
import eu.numberfour.n4js.ts.types.UndefModifier;
import eu.numberfour.n4js.ts.types.UndefinedType;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.TypeRefImpl#getNullModifier <em>Null Modifier</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.impl.TypeRefImpl#getUndefModifier <em>Undef Modifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TypeRefImpl extends TypeArgumentImpl implements TypeRef {
	/**
	 * The default value of the '{@link #getNullModifier() <em>Null Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNullModifier()
	 * @generated
	 * @ordered
	 */
	protected static final NullModifier NULL_MODIFIER_EDEFAULT = NullModifier.NA;

	/**
	 * The cached value of the '{@link #getNullModifier() <em>Null Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNullModifier()
	 * @generated
	 * @ordered
	 */
	protected NullModifier nullModifier = NULL_MODIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getUndefModifier() <em>Undef Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUndefModifier()
	 * @generated
	 * @ordered
	 */
	protected static final UndefModifier UNDEF_MODIFIER_EDEFAULT = UndefModifier.NA;

	/**
	 * The cached value of the '{@link #getUndefModifier() <em>Undef Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUndefModifier()
	 * @generated
	 * @ordered
	 */
	protected UndefModifier undefModifier = UNDEF_MODIFIER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeRefsPackage.Literals.TYPE_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NullModifier getNullModifier() {
		return nullModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullModifier(NullModifier newNullModifier) {
		NullModifier oldNullModifier = nullModifier;
		nullModifier = newNullModifier == null ? NULL_MODIFIER_EDEFAULT : newNullModifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeRefsPackage.TYPE_REF__NULL_MODIFIER, oldNullModifier, nullModifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UndefModifier getUndefModifier() {
		return undefModifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUndefModifier(UndefModifier newUndefModifier) {
		UndefModifier oldUndefModifier = undefModifier;
		undefModifier = newUndefModifier == null ? UNDEF_MODIFIER_EDEFAULT : newUndefModifier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypeRefsPackage.TYPE_REF__UNDEF_MODIFIER, oldUndefModifier, undefModifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModifiersAsString() {
		String _switchResult = null;
		NullModifier _nullModifier = this.getNullModifier();
		if (_nullModifier != null) {
			switch (_nullModifier) {
				case NA:
					_switchResult = "";
					break;
				case NULLABLE:
					_switchResult = "";
					break;
				case NOTNULL:
					_switchResult = "!";
					break;
				case ISNULL:
					_switchResult = "";
					break;
				default:
					break;
			}
		}
		String _switchResult_1 = null;
		UndefModifier _undefModifier = this.getUndefModifier();
		if (_undefModifier != null) {
			switch (_undefModifier) {
				case NA:
					_switchResult_1 = "";
					break;
				case MANDATORY:
					_switchResult_1 = "";
					break;
				case OPTIONAL:
					_switchResult_1 = "?";
					break;
				case ISUNDEFINED:
					_switchResult_1 = "";
					break;
				default:
					break;
			}
		}
		return (_switchResult + _switchResult_1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinalByType() {
		final Type dtype = this.getDeclaredType();
		if ((dtype == null)) {
			return false;
		}
		return dtype.isFinal();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTreeHierarchyType() {
		final Type dtype = this.getDeclaredType();
		if ((dtype == null)) {
			return false;
		}
		return ((((dtype instanceof TClass) || (dtype instanceof PrimitiveType)) || (dtype instanceof TObjectPrototype)) || 
			(dtype instanceof TEnum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDynamic() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExistential() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isGeneric() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isParameterized() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRaw() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterizedTypeRef getDeclaredUpperBound() {
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterizedTypeRef getDeclaredLowerBound() {
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeArgument> getTypeArgs() {
		return XcoreCollectionLiterals.<TypeArgument>emptyEList();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean undefinedOrNull() {
		return (Objects.equal(this.getNullModifier(), NullModifier.ISNULL) || Objects.equal(this.getUndefModifier(), UndefModifier.ISUNDEFINED));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeRefAsString() {
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		return this.getTypeRefAsString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getDeclaredType() {
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTopType() {
		Type _declaredType = this.getDeclaredType();
		return (_declaredType instanceof AnyType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBottomType() {
		Type _declaredType = this.getDeclaredType();
		return (_declaredType instanceof UndefinedType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypingStrategy getTypingStrategy() {
		return TypingStrategy.NOMINAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TStructMember> getStructuralMembers() {
		return XcoreCollectionLiterals.<TStructMember>emptyEList();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUseSiteStructuralTyping() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDefSiteStructuralTyping() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypeRefsPackage.TYPE_REF__NULL_MODIFIER:
				return getNullModifier();
			case TypeRefsPackage.TYPE_REF__UNDEF_MODIFIER:
				return getUndefModifier();
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
			case TypeRefsPackage.TYPE_REF__NULL_MODIFIER:
				setNullModifier((NullModifier)newValue);
				return;
			case TypeRefsPackage.TYPE_REF__UNDEF_MODIFIER:
				setUndefModifier((UndefModifier)newValue);
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
			case TypeRefsPackage.TYPE_REF__NULL_MODIFIER:
				setNullModifier(NULL_MODIFIER_EDEFAULT);
				return;
			case TypeRefsPackage.TYPE_REF__UNDEF_MODIFIER:
				setUndefModifier(UNDEF_MODIFIER_EDEFAULT);
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
			case TypeRefsPackage.TYPE_REF__NULL_MODIFIER:
				return nullModifier != NULL_MODIFIER_EDEFAULT;
			case TypeRefsPackage.TYPE_REF__UNDEF_MODIFIER:
				return undefModifier != UNDEF_MODIFIER_EDEFAULT;
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
			case TypeRefsPackage.TYPE_REF___GET_MODIFIERS_AS_STRING:
				return getModifiersAsString();
			case TypeRefsPackage.TYPE_REF___IS_FINAL_BY_TYPE:
				return isFinalByType();
			case TypeRefsPackage.TYPE_REF___IS_TREE_HIERARCHY_TYPE:
				return isTreeHierarchyType();
			case TypeRefsPackage.TYPE_REF___IS_DYNAMIC:
				return isDynamic();
			case TypeRefsPackage.TYPE_REF___IS_EXISTENTIAL:
				return isExistential();
			case TypeRefsPackage.TYPE_REF___IS_GENERIC:
				return isGeneric();
			case TypeRefsPackage.TYPE_REF___IS_PARAMETERIZED:
				return isParameterized();
			case TypeRefsPackage.TYPE_REF___IS_RAW:
				return isRaw();
			case TypeRefsPackage.TYPE_REF___GET_DECLARED_UPPER_BOUND:
				return getDeclaredUpperBound();
			case TypeRefsPackage.TYPE_REF___GET_DECLARED_LOWER_BOUND:
				return getDeclaredLowerBound();
			case TypeRefsPackage.TYPE_REF___GET_TYPE_ARGS:
				return getTypeArgs();
			case TypeRefsPackage.TYPE_REF___UNDEFINED_OR_NULL:
				return undefinedOrNull();
			case TypeRefsPackage.TYPE_REF___GET_TYPE_REF_AS_STRING:
				return getTypeRefAsString();
			case TypeRefsPackage.TYPE_REF___TO_STRING:
				return toString();
			case TypeRefsPackage.TYPE_REF___GET_DECLARED_TYPE:
				return getDeclaredType();
			case TypeRefsPackage.TYPE_REF___IS_TOP_TYPE:
				return isTopType();
			case TypeRefsPackage.TYPE_REF___IS_BOTTOM_TYPE:
				return isBottomType();
			case TypeRefsPackage.TYPE_REF___GET_TYPING_STRATEGY:
				return getTypingStrategy();
			case TypeRefsPackage.TYPE_REF___GET_STRUCTURAL_MEMBERS:
				return getStructuralMembers();
			case TypeRefsPackage.TYPE_REF___IS_USE_SITE_STRUCTURAL_TYPING:
				return isUseSiteStructuralTyping();
			case TypeRefsPackage.TYPE_REF___IS_DEF_SITE_STRUCTURAL_TYPING:
				return isDefSiteStructuralTyping();
		}
		return super.eInvoke(operationID, arguments);
	}

} //TypeRefImpl
