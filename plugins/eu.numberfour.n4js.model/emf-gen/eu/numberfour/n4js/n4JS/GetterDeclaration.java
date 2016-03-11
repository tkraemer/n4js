/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TGetter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Getter Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for getters, of either object literals (PropertyGetterDeclaration) or classes (N4GetterDeclaration).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.GetterDeclaration#getDefinedGetter <em>Defined Getter</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getGetterDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface GetterDeclaration extends FieldAccessor, TypedElement {
	/**
	 * Returns the value of the '<em><b>Defined Getter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defined Getter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defined Getter</em>' reference.
	 * @see #setDefinedGetter(TGetter)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getGetterDeclaration_DefinedGetter()
	 * @model transient="true"
	 * @generated
	 */
	TGetter getDefinedGetter();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.GetterDeclaration#getDefinedGetter <em>Defined Getter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defined Getter</em>' reference.
	 * @see #getDefinedGetter()
	 * @generated
	 */
	void setDefinedGetter(TGetter value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getDefinedGetter();'"
	 * @generated
	 */
	TGetter getDefinedAccessor();

} // GetterDeclaration
