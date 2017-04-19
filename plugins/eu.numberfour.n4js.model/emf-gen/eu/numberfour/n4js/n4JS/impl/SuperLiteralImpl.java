/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.n4JS.impl;

import eu.numberfour.n4js.n4JS.IndexedAccessExpression;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression;
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression;
import eu.numberfour.n4js.n4JS.SuperLiteral;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Super Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SuperLiteralImpl extends PrimaryExpressionImpl implements SuperLiteral {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SuperLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSPackage.Literals.SUPER_LITERAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSuperConstructorAccess() {
		EObject _eContainer = this.eContainer();
		return (_eContainer instanceof ParameterizedCallExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSuperMemberAccess() {
		return ((this.eContainer() instanceof ParameterizedPropertyAccessExpression) || (this.eContainer() instanceof IndexedAccessExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case N4JSPackage.SUPER_LITERAL___IS_SUPER_CONSTRUCTOR_ACCESS:
				return isSuperConstructorAccess();
			case N4JSPackage.SUPER_LITERAL___IS_SUPER_MEMBER_ACCESS:
				return isSuperMemberAccess();
		}
		return super.eInvoke(operationID, arguments);
	}

} //SuperLiteralImpl
