/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.BooleanLiteral#isTrue <em>True</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getBooleanLiteral()
 * @model
 * @generated
 */
public interface BooleanLiteral extends Literal {
	/**
	 * Returns the value of the '<em><b>True</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>True</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>True</em>' attribute.
	 * @see #setTrue(boolean)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getBooleanLiteral_True()
	 * @model unique="false"
	 * @generated
	 */
	boolean isTrue();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.BooleanLiteral#isTrue <em>True</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>True</em>' attribute.
	 * @see #isTrue()
	 * @generated
	 */
	void setTrue(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _isTrue = this.isTrue();\nreturn <%java.lang.Boolean%>.toString(_isTrue);'"
	 * @generated
	 */
	String getValueAsString();

} // BooleanLiteral
