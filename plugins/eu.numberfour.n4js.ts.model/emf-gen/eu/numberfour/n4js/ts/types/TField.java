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
 * A representation of the model object '<em><b>TField</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Field of a class, optionally complemented by getter(s) and setter(s).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.TField#isHasExpression <em>Has Expression</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TField#getTypeRef <em>Type Ref</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTField()
 * @model
 * @generated
 */
public interface TField extends TMemberWithAccessModifier, TConstableElement {
	/**
	 * Returns the value of the '<em><b>Has Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Expression</em>' attribute.
	 * @see #setHasExpression(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTField_HasExpression()
	 * @model unique="false"
	 * @generated
	 */
	boolean isHasExpression();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TField#isHasExpression <em>Has Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Expression</em>' attribute.
	 * @see #isHasExpression()
	 * @generated
	 */
	void setHasExpression(boolean value);

	/**
	 * Returns the value of the '<em><b>Type Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Ref</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Ref</em>' containment reference.
	 * @see #setTypeRef(TypeRef)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTField_TypeRef()
	 * @model containment="true"
	 * @generated
	 */
	TypeRef getTypeRef();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TField#getTypeRef <em>Type Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Ref</em>' containment reference.
	 * @see #getTypeRef()
	 * @generated
	 */
	void setTypeRef(TypeRef value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  Convenience method, returns true if typeRef undef modifier is optional. If type ref is not set, false is returned.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return ((this.getTypeRef() != null) && (this.getTypeRef().getUndefModifier() == <%eu.numberfour.n4js.ts.types.UndefModifier%>.OPTIONAL));'"
	 * @generated
	 */
	boolean isOptional();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return (this.isDeclaredStatic() || this.isConst());'"
	 * @generated
	 */
	boolean isStatic();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return true;'"
	 * @generated
	 */
	boolean isReadable();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return (!(this.isConst() || this.isFinal()));'"
	 * @generated
	 */
	boolean isWriteable();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * * Always returns FIELD
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return <%eu.numberfour.n4js.ts.types.MemberType%>.FIELD;'"
	 * @generated
	 */
	MemberType getMemberType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns string representation of field according to syntax definition, including
	 * colon separated type if declared. Overrides TMember's method.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final <%java.lang.StringBuilder%> strb = new <%java.lang.StringBuilder%>();\n<%java.lang.String%> _name = this.getName();\nstrb.append(_name);\n<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _typeRef = this.getTypeRef();\nboolean _tripleNotEquals = (_typeRef != null);\nif (_tripleNotEquals)\n{\n\t<%java.lang.StringBuilder%> _append = strb.append(\": \");\n\t<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _typeRef_1 = this.getTypeRef();\n\t<%java.lang.String%> _typeRefAsString = _typeRef_1.getTypeRefAsString();\n\t_append.append(_typeRefAsString);\n}\nreturn strb.toString();'"
	 * @generated
	 */
	String getMemberAsString();

} // TField
