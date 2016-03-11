/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.jsdoc.dom;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.jsdoc.dom.Composite#getContents <em>Contents</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.jsdoc.dom.DomPackage#getComposite()
 * @model abstract="true"
 * @generated
 */
public interface Composite extends JSDocNode {
	/**
	 * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.jsdoc.dom.ContentNode}.
	 * It is bidirectional and its opposite is '{@link eu.numberfour.n4js.jsdoc.dom.ContentNode#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contents</em>' containment reference list.
	 * @see eu.numberfour.n4js.jsdoc.dom.DomPackage#getComposite_Contents()
	 * @see eu.numberfour.n4js.jsdoc.dom.ContentNode#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<ContentNode> getContents();

} // Composite
