/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Accessor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for getter or setter, either of an ObjectLiteral or a Class
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.FieldAccessor#isDeclaredAbstract <em>Declared Abstract</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.FieldAccessor#getDeclaredThisType <em>Declared This Type</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getFieldAccessor()
 * @model abstract="true"
 * @generated
 */
public interface FieldAccessor extends TMemberWithAccessModifier {
	/**
	 * Returns the value of the '<em><b>Declared Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Abstract</em>' attribute.
	 * @see #setDeclaredAbstract(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getFieldAccessor_DeclaredAbstract()
	 * @model unique="false"
	 * @generated
	 */
	boolean isDeclaredAbstract();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.FieldAccessor#isDeclaredAbstract <em>Declared Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Abstract</em>' attribute.
	 * @see #isDeclaredAbstract()
	 * @generated
	 */
	void setDeclaredAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Declared This Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared This Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared This Type</em>' containment reference.
	 * @see #setDeclaredThisType(TypeRef)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getFieldAccessor_DeclaredThisType()
	 * @model containment="true"
	 * @generated
	 */
	TypeRef getDeclaredThisType();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.FieldAccessor#getDeclaredThisType <em>Declared This Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared This Type</em>' containment reference.
	 * @see #getDeclaredThisType()
	 * @generated
	 */
	void setDeclaredThisType(TypeRef value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return null;'"
	 * @generated
	 */
	TypeRef getDeclaredTypeRef();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns true if the method is either declared abstract or it is implicitly abstract, i.e. it is declared in a role and has no body.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return (this.isDeclaredAbstract() || ((this.eContainer() instanceof &lt;%eu.numberfour.n4js.ts.types.TInterface%&gt;) &amp;&amp; this.isHasNoBody()));'"
	 * @generated
	 */
	boolean isAbstract();

} // FieldAccessor
