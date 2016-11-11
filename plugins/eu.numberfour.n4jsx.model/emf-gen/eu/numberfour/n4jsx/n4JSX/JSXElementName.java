/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4jsx.n4JSX;

import eu.numberfour.n4js.n4JS.IdentifierRef;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JSX Element Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4jsx.n4JSX.JSXElementName#getIdentifierRef <em>Identifier Ref</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4jsx.n4JSX.N4JSXPackage#getJSXElementName()
 * @model
 * @generated
 */
public interface JSXElementName extends EObject {
	/**
	 * Returns the value of the '<em><b>Identifier Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier Ref</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier Ref</em>' containment reference.
	 * @see #setIdentifierRef(IdentifierRef)
	 * @see eu.numberfour.n4jsx.n4JSX.N4JSXPackage#getJSXElementName_IdentifierRef()
	 * @model containment="true"
	 * @generated
	 */
	IdentifierRef getIdentifierRef();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4jsx.n4JSX.JSXElementName#getIdentifierRef <em>Identifier Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier Ref</em>' containment reference.
	 * @see #getIdentifierRef()
	 * @generated
	 */
	void setIdentifierRef(IdentifierRef value);

} // JSXElementName
