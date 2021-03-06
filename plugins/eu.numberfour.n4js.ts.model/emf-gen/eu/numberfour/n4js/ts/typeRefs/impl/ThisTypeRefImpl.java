/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs.impl;

import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;

import eu.numberfour.n4js.ts.types.TStructMember;
import eu.numberfour.n4js.ts.types.TypingStrategy;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>This Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ThisTypeRefImpl extends BaseTypeRefImpl implements ThisTypeRef {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ThisTypeRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeRefsPackage.Literals.THIS_TYPE_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeRefAsString() {
		String _modifiersAsString = this.getModifiersAsString();
		return ("this" + _modifiersAsString);
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
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == TypeArgument.class) {
			switch (baseOperationID) {
				case TypeRefsPackage.TYPE_ARGUMENT___GET_TYPE_REF_AS_STRING: return TypeRefsPackage.THIS_TYPE_REF___GET_TYPE_REF_AS_STRING;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == TypeRef.class) {
			switch (baseOperationID) {
				case TypeRefsPackage.TYPE_REF___GET_TYPE_REF_AS_STRING: return TypeRefsPackage.THIS_TYPE_REF___GET_TYPE_REF_AS_STRING;
				case TypeRefsPackage.TYPE_REF___GET_TYPING_STRATEGY: return TypeRefsPackage.THIS_TYPE_REF___GET_TYPING_STRATEGY;
				case TypeRefsPackage.TYPE_REF___GET_STRUCTURAL_MEMBERS: return TypeRefsPackage.THIS_TYPE_REF___GET_STRUCTURAL_MEMBERS;
				case TypeRefsPackage.TYPE_REF___IS_USE_SITE_STRUCTURAL_TYPING: return TypeRefsPackage.THIS_TYPE_REF___IS_USE_SITE_STRUCTURAL_TYPING;
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
			case TypeRefsPackage.THIS_TYPE_REF___GET_TYPE_REF_AS_STRING:
				return getTypeRefAsString();
			case TypeRefsPackage.THIS_TYPE_REF___GET_TYPING_STRATEGY:
				return getTypingStrategy();
			case TypeRefsPackage.THIS_TYPE_REF___GET_STRUCTURAL_MEMBERS:
				return getStructuralMembers();
			case TypeRefsPackage.THIS_TYPE_REF___IS_USE_SITE_STRUCTURAL_TYPING:
				return isUseSiteStructuralTyping();
		}
		return super.eInvoke(operationID, arguments);
	}

} //ThisTypeRefImpl
