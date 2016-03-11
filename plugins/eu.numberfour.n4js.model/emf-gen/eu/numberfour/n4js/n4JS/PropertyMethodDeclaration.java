/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TStructMethod;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Method Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.PropertyMethodDeclaration#getDefinedMethod <em>Defined Method</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyMethodDeclaration()
 * @model
 * @generated
 */
public interface PropertyMethodDeclaration extends AnnotablePropertyAssignment, MethodDeclaration, TypeProvidingElement {
	/**
	 * Returns the value of the '<em><b>Defined Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defined Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defined Method</em>' reference.
	 * @see #setDefinedMethod(TStructMethod)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyMethodDeclaration_DefinedMethod()
	 * @model
	 * @generated
	 */
	TStructMethod getDefinedMethod();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.PropertyMethodDeclaration#getDefinedMethod <em>Defined Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defined Method</em>' reference.
	 * @see #getDefinedMethod()
	 * @generated
	 */
	void setDefinedMethod(TStructMethod value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getDefinedMethod();'"
	 * @generated
	 */
	TStructMethod getDefinedMember();

} // PropertyMethodDeclaration
