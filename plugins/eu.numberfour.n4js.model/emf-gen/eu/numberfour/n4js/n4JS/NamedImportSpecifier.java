/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TExportableElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Import Specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.NamedImportSpecifier#getImportedElement <em>Imported Element</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.NamedImportSpecifier#getImportedElementAsText <em>Imported Element As Text</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.NamedImportSpecifier#getAlias <em>Alias</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getNamedImportSpecifier()
 * @model
 * @generated
 */
public interface NamedImportSpecifier extends ImportSpecifier {
	/**
	 * Returns the value of the '<em><b>Imported Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imported Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imported Element</em>' reference.
	 * @see #setImportedElement(TExportableElement)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getNamedImportSpecifier_ImportedElement()
	 * @model
	 * @generated
	 */
	TExportableElement getImportedElement();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.NamedImportSpecifier#getImportedElement <em>Imported Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imported Element</em>' reference.
	 * @see #getImportedElement()
	 * @generated
	 */
	void setImportedElement(TExportableElement value);

	/**
	 * Returns the value of the '<em><b>Imported Element As Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imported Element As Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imported Element As Text</em>' attribute.
	 * @see #setImportedElementAsText(String)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getNamedImportSpecifier_ImportedElementAsText()
	 * @model unique="false" transient="true"
	 * @generated
	 */
	String getImportedElementAsText();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.NamedImportSpecifier#getImportedElementAsText <em>Imported Element As Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Imported Element As Text</em>' attribute.
	 * @see #getImportedElementAsText()
	 * @generated
	 */
	void setImportedElementAsText(String value);

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
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getNamedImportSpecifier_Alias()
	 * @model unique="false"
	 * @generated
	 */
	String getAlias();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.NamedImportSpecifier#getAlias <em>Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alias</em>' attribute.
	 * @see #getAlias()
	 * @generated
	 */
	void setAlias(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isDefaultImport();

} // NamedImportSpecifier
