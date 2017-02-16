/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.typeRefs.Versionable;

import eu.numberfour.n4js.ts.types.IdentifiableElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifier Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.IdentifierRef#getId <em>Id</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.IdentifierRef#getIdAsText <em>Id As Text</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getIdentifierRef()
 * @model
 * @generated
 */
public interface IdentifierRef extends PrimaryExpression, StrictModeRelevant, Versionable {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' reference.
	 * @see #setId(IdentifiableElement)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getIdentifierRef_Id()
	 * @model
	 * @generated
	 */
	IdentifiableElement getId();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.IdentifierRef#getId <em>Id</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' reference.
	 * @see #getId()
	 * @generated
	 */
	void setId(IdentifiableElement value);

	/**
	 * Returns the value of the '<em><b>Id As Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id As Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id As Text</em>' attribute.
	 * @see #setIdAsText(String)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getIdentifierRef_IdAsText()
	 * @model unique="false" transient="true"
	 * @generated
	 */
	String getIdAsText();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.IdentifierRef#getIdAsText <em>Id As Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id As Text</em>' attribute.
	 * @see #getIdAsText()
	 * @generated
	 */
	void setIdAsText(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * IdentifierReference : Identifier
	 * 1. If this IdentifierReference is contained in strict mode code and StringValue of Identifier is "eval" or "arguments", return false.
	 * 2. Return true.
	 * IdentifierReference : yield
	 * 1. Return true.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _isStrictMode = this.isStrictMode();\nif (_isStrictMode)\n{\n\treturn (((this.getIdAsText() != null) &amp;&amp; (!&lt;%com.google.common.base.Objects%&gt;.equal(\"arguments\", this.getIdAsText()))) &amp;&amp; (!&lt;%com.google.common.base.Objects%&gt;.equal(\"eval\", this.getIdAsText())));\n}\nreturn true;'"
	 * @generated
	 */
	boolean isValidSimpleAssignmentTarget();

} // IdentifierRef
