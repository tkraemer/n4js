/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.transpiler.im.impl;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.N4JSPackage;

import eu.numberfour.n4js.transpiler.im.ImPackage;
import eu.numberfour.n4js.transpiler.im.ReferencingElementExpression_IM;

import eu.numberfour.n4js.ts.types.TypableElement;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Referencing Element Expression IM</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class ReferencingElementExpression_IMImpl extends ReferencingElement_IMImpl implements ReferencingElementExpression_IM {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReferencingElementExpression_IMImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ImPackage.Literals.REFERENCING_ELEMENT_EXPRESSION_IM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isValidSimpleAssignmentTarget() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == TypableElement.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == Expression.class) {
			switch (baseOperationID) {
				case N4JSPackage.EXPRESSION___IS_VALID_SIMPLE_ASSIGNMENT_TARGET: return ImPackage.REFERENCING_ELEMENT_EXPRESSION_IM___IS_VALID_SIMPLE_ASSIGNMENT_TARGET;
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
			case ImPackage.REFERENCING_ELEMENT_EXPRESSION_IM___IS_VALID_SIMPLE_ASSIGNMENT_TARGET:
				return isValidSimpleAssignmentTarget();
		}
		return super.eInvoke(operationID, arguments);
	}

} //ReferencingElementExpression_IMImpl
