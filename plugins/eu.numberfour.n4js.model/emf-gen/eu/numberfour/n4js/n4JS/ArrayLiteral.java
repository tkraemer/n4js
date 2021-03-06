/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.ArrayLiteral#getElements <em>Elements</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.ArrayLiteral#isTrailingComma <em>Trailing Comma</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getArrayLiteral()
 * @model
 * @generated
 */
public interface ArrayLiteral extends PrimaryExpression {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.n4JS.ArrayElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getArrayLiteral_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<ArrayElement> getElements();

	/**
	 * Returns the value of the '<em><b>Trailing Comma</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trailing Comma</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trailing Comma</em>' attribute.
	 * @see #setTrailingComma(boolean)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getArrayLiteral_TrailingComma()
	 * @model unique="false"
	 * @generated
	 */
	boolean isTrailingComma();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.ArrayLiteral#isTrailingComma <em>Trailing Comma</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trailing Comma</em>' attribute.
	 * @see #isTrailingComma()
	 * @generated
	 */
	void setTrailingComma(boolean value);

} // ArrayLiteral
