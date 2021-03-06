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
 * A representation of the model object '<em><b>Property Name Value Pair Single Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.PropertyNameValuePairSingleName#getIdentifierRef <em>Identifier Ref</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyNameValuePairSingleName()
 * @model
 * @generated
 */
public interface PropertyNameValuePairSingleName extends PropertyNameValuePair {
	/**
	 * Returns the value of the '<em><b>Identifier Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier Ref</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier Ref</em>' containment reference.
	 * @see #setIdentifierRef(IdentifierRef)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyNameValuePairSingleName_IdentifierRef()
	 * @model containment="true"
	 * @generated
	 */
	IdentifierRef getIdentifierRef();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.PropertyNameValuePairSingleName#getIdentifierRef <em>Identifier Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier Ref</em>' containment reference.
	 * @see #getIdentifierRef()
	 * @generated
	 */
	void setIdentifierRef(IdentifierRef value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Note: if this AST node was created by the parser, then super.getName() will always be null and we get
	 * the name from the parse tree; if this AST node is created programmatically (e.g. refactoring), then
	 * the name has to be set explicitly!
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _elvis = null;\n<%java.lang.String%> _name = super.getName();\nif (_name != null)\n{\n\t_elvis = _name;\n} else\n{\n\t<%eu.numberfour.n4js.n4JS.IdentifierRef%> _identifierRef = this.getIdentifierRef();\n\t<%java.lang.String%> _idAsText = null;\n\tif (_identifierRef!=null)\n\t{\n\t\t_idAsText=_identifierRef.getIdAsText();\n\t}\n\t_elvis = _idAsText;\n}\nreturn _elvis;'"
	 * @generated
	 */
	String getName();

} // PropertyNameValuePairSingleName
