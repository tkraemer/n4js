/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TModule;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Export Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.ExportDeclaration#getExportedElement <em>Exported Element</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.ExportDeclaration#getDefaultExportedExpression <em>Default Exported Expression</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.ExportDeclaration#getNamedExports <em>Named Exports</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.ExportDeclaration#isWildcardExport <em>Wildcard Export</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.ExportDeclaration#isDefaultExport <em>Default Export</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.ExportDeclaration#getReexportedFrom <em>Reexported From</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getExportDeclaration()
 * @model
 * @generated
 */
public interface ExportDeclaration extends AnnotableScriptElement {
	/**
	 * Returns the value of the '<em><b>Exported Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exported Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exported Element</em>' containment reference.
	 * @see #setExportedElement(ExportableElement)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getExportDeclaration_ExportedElement()
	 * @model containment="true"
	 * @generated
	 */
	ExportableElement getExportedElement();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.ExportDeclaration#getExportedElement <em>Exported Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exported Element</em>' containment reference.
	 * @see #getExportedElement()
	 * @generated
	 */
	void setExportedElement(ExportableElement value);

	/**
	 * Returns the value of the '<em><b>Default Exported Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Exported Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Exported Expression</em>' containment reference.
	 * @see #setDefaultExportedExpression(Expression)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getExportDeclaration_DefaultExportedExpression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getDefaultExportedExpression();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.ExportDeclaration#getDefaultExportedExpression <em>Default Exported Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Exported Expression</em>' containment reference.
	 * @see #getDefaultExportedExpression()
	 * @generated
	 */
	void setDefaultExportedExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Named Exports</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.n4JS.ExportSpecifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Named Exports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Named Exports</em>' containment reference list.
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getExportDeclaration_NamedExports()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExportSpecifier> getNamedExports();

	/**
	 * Returns the value of the '<em><b>Wildcard Export</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wildcard Export</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wildcard Export</em>' attribute.
	 * @see #setWildcardExport(boolean)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getExportDeclaration_WildcardExport()
	 * @model unique="false"
	 * @generated
	 */
	boolean isWildcardExport();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.ExportDeclaration#isWildcardExport <em>Wildcard Export</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wildcard Export</em>' attribute.
	 * @see #isWildcardExport()
	 * @generated
	 */
	void setWildcardExport(boolean value);

	/**
	 * Returns the value of the '<em><b>Default Export</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Export</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Export</em>' attribute.
	 * @see #setDefaultExport(boolean)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getExportDeclaration_DefaultExport()
	 * @model unique="false"
	 * @generated
	 */
	boolean isDefaultExport();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.ExportDeclaration#isDefaultExport <em>Default Export</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Export</em>' attribute.
	 * @see #isDefaultExport()
	 * @generated
	 */
	void setDefaultExport(boolean value);

	/**
	 * Returns the value of the '<em><b>Reexported From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reexported From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reexported From</em>' reference.
	 * @see #setReexportedFrom(TModule)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getExportDeclaration_ReexportedFrom()
	 * @model
	 * @generated
	 */
	TModule getReexportedFrom();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.ExportDeclaration#getReexportedFrom <em>Reexported From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reexported From</em>' reference.
	 * @see #getReexportedFrom()
	 * @generated
	 */
	void setReexportedFrom(TModule value);

} // ExportDeclaration
