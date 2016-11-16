/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4jsx.n4JSX;

import eu.numberfour.n4js.n4JS.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JSX Property Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute#getProperty <em>Property</em>}</li>
 *   <li>{@link eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute#getPropertyAsText <em>Property As Text</em>}</li>
 *   <li>{@link eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute#getJsxAttributeValue <em>Jsx Attribute Value</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4jsx.n4JSX.N4JSXPackage#getJSXPropertyAttribute()
 * @model
 * @generated
 */
public interface JSXPropertyAttribute extends JSXAttribute {
	/**
	 * Returns the value of the '<em><b>Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' attribute.
	 * @see #setProperty(String)
	 * @see eu.numberfour.n4jsx.n4JSX.N4JSXPackage#getJSXPropertyAttribute_Property()
	 * @model unique="false"
	 * @generated
	 */
	String getProperty();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute#getProperty <em>Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' attribute.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(String value);

	/**
	 * Returns the value of the '<em><b>Property As Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property As Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property As Text</em>' attribute.
	 * @see #setPropertyAsText(String)
	 * @see eu.numberfour.n4jsx.n4JSX.N4JSXPackage#getJSXPropertyAttribute_PropertyAsText()
	 * @model unique="false" transient="true"
	 * @generated
	 */
	String getPropertyAsText();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute#getPropertyAsText <em>Property As Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property As Text</em>' attribute.
	 * @see #getPropertyAsText()
	 * @generated
	 */
	void setPropertyAsText(String value);

	/**
	 * Returns the value of the '<em><b>Jsx Attribute Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jsx Attribute Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jsx Attribute Value</em>' containment reference.
	 * @see #setJsxAttributeValue(Expression)
	 * @see eu.numberfour.n4jsx.n4JSX.N4JSXPackage#getJSXPropertyAttribute_JsxAttributeValue()
	 * @model containment="true"
	 * @generated
	 */
	Expression getJsxAttributeValue();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute#getJsxAttributeValue <em>Jsx Attribute Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jsx Attribute Value</em>' containment reference.
	 * @see #getJsxAttributeValue()
	 * @generated
	 */
	void setJsxAttributeValue(Expression value);

} // JSXPropertyAttribute
