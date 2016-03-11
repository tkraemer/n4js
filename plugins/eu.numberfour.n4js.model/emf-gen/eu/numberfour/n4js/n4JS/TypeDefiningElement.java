/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TypableElement;
import eu.numberfour.n4js.ts.types.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Defining Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Elements such as type or functions definitions defining a {@link  eu.numberfour.n4js.ts.model.Type}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.TypeDefiningElement#getDefinedType <em>Defined Type</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getTypeDefiningElement()
 * @model abstract="true"
 * @generated
 */
public interface TypeDefiningElement extends TypableElement {
	/**
	 * Returns the value of the '<em><b>Defined Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defined Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defined Type</em>' reference.
	 * @see #setDefinedType(Type)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getTypeDefiningElement_DefinedType()
	 * @model transient="true"
	 * @generated
	 */
	Type getDefinedType();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.TypeDefiningElement#getDefinedType <em>Defined Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defined Type</em>' reference.
	 * @see #getDefinedType()
	 * @generated
	 */
	void setDefinedType(Type value);

} // TypeDefiningElement
