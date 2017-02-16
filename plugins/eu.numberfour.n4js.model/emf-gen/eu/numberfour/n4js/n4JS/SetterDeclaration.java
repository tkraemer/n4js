/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

import eu.numberfour.n4js.ts.types.TSetter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Setter Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for setters, of either object literals (PropertySetterDeclaration) or classes (N4SetterDeclaration).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.SetterDeclaration#getDefinedSetter <em>Defined Setter</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.SetterDeclaration#getFpar <em>Fpar</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getSetterDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface SetterDeclaration extends FieldAccessor {
	/**
	 * Returns the value of the '<em><b>Defined Setter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defined Setter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defined Setter</em>' reference.
	 * @see #setDefinedSetter(TSetter)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getSetterDeclaration_DefinedSetter()
	 * @model transient="true"
	 * @generated
	 */
	TSetter getDefinedSetter();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.SetterDeclaration#getDefinedSetter <em>Defined Setter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defined Setter</em>' reference.
	 * @see #getDefinedSetter()
	 * @generated
	 */
	void setDefinedSetter(TSetter value);

	/**
	 * Returns the value of the '<em><b>Fpar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fpar</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fpar</em>' containment reference.
	 * @see #setFpar(FormalParameter)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getSetterDeclaration_Fpar()
	 * @model containment="true"
	 * @generated
	 */
	FormalParameter getFpar();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.SetterDeclaration#getFpar <em>Fpar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fpar</em>' containment reference.
	 * @see #getFpar()
	 * @generated
	 */
	void setFpar(FormalParameter value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getDefinedSetter();'"
	 * @generated
	 */
	TSetter getDefinedAccessor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the declared type of the formal parameter
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%eu.numberfour.n4js.n4JS.FormalParameter%&gt; _fpar = this.getFpar();\n&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _declaredTypeRef = null;\nif (_fpar!=null)\n{\n\t_declaredTypeRef=_fpar.getDeclaredTypeRef();\n}\nreturn _declaredTypeRef;'"
	 * @generated
	 */
	TypeRef getDeclaredTypeRef();

} // SetterDeclaration
