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
 * A representation of the model object '<em><b>Function Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * *
 * Function declarations should only be contained in the script or functions, that is, they should not be nested in blocks.
 * This is ensured by the ASTStructureValidator.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.FunctionDeclaration#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getFunctionDeclaration()
 * @model
 * @generated
 */
public interface FunctionDeclaration extends AnnotableScriptElement, ModifiableElement, Statement, FunctionDefinition, GenericDeclaration, ExportableElement, NamedElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Function declarations are not treated as identifiable elements, as the binding should
	 * refer to the inferred TFunction rather than this declaration. The name is mandatory.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getFunctionDeclaration_Name()
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.FunctionDeclaration#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns true if type is declared as external.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.n4JS.N4Modifier%>> _declaredModifiers = this.getDeclaredModifiers();\nreturn _declaredModifiers.contains(<%eu.numberfour.n4js.n4JS.N4Modifier%>.EXTERNAL);'"
	 * @generated
	 */
	boolean isExternal();

} // FunctionDeclaration
