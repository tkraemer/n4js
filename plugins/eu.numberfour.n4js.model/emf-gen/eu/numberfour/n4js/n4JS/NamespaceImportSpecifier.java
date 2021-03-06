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
 * A representation of the model object '<em><b>Namespace Import Specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.NamespaceImportSpecifier#isDeclaredDynamic <em>Declared Dynamic</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.NamespaceImportSpecifier#getAlias <em>Alias</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getNamespaceImportSpecifier()
 * @model
 * @generated
 */
public interface NamespaceImportSpecifier extends ImportSpecifier, TypeDefiningElement {
	/**
	 * Returns the value of the '<em><b>Declared Dynamic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Dynamic</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Dynamic</em>' attribute.
	 * @see #setDeclaredDynamic(boolean)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getNamespaceImportSpecifier_DeclaredDynamic()
	 * @model unique="false"
	 * @generated
	 */
	boolean isDeclaredDynamic();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.NamespaceImportSpecifier#isDeclaredDynamic <em>Declared Dynamic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Dynamic</em>' attribute.
	 * @see #isDeclaredDynamic()
	 * @generated
	 */
	void setDeclaredDynamic(boolean value);

	/**
	 * Returns the value of the '<em><b>Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alias</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alias</em>' attribute.
	 * @see #setAlias(String)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getNamespaceImportSpecifier_Alias()
	 * @model unique="false"
	 * @generated
	 */
	String getAlias();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.NamespaceImportSpecifier#getAlias <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alias</em>' attribute.
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

} // NamespaceImportSpecifier
