/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.jsdoc.dom;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Type Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.jsdoc.dom.SimpleTypeReference#getTypeName <em>Type Name</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.jsdoc.dom.DomPackage#getSimpleTypeReference()
 * @model
 * @generated
 */
public interface SimpleTypeReference extends JSDocNode, ContentNode {
	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Name</em>' attribute.
	 * @see #setTypeName(String)
	 * @see eu.numberfour.n4js.jsdoc.dom.DomPackage#getSimpleTypeReference_TypeName()
	 * @model unique="false"
	 * @generated
	 */
	String getTypeName();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.jsdoc.dom.SimpleTypeReference#getTypeName <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' attribute.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _and = false;\n<%java.lang.String%> _typeName = this.getTypeName();\nboolean _tripleNotEquals = (_typeName != null);\nif (!_tripleNotEquals)\n{\n\t_and = false;\n} else\n{\n\t<%java.lang.String%> _typeName_1 = this.getTypeName();\n\tboolean _isEmpty = _typeName_1.isEmpty();\n\tboolean _not = (!_isEmpty);\n\t_and = _not;\n}\nreturn _and;'"
	 * @generated
	 */
	boolean typeNameSet();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getTypeName();'"
	 * @generated
	 */
	String toString();

} // SimpleTypeReference
