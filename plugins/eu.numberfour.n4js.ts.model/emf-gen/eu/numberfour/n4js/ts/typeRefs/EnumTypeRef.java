/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs;

import eu.numberfour.n4js.ts.types.TEnum;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * *
 * A reference to the type of an enumeration. This type can be used
 * to access the literals of an enum.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.EnumTypeRef#getEnumType <em>Enum Type</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getEnumTypeRef()
 * @model
 * @generated
 */
public interface EnumTypeRef extends BaseTypeRef {
	/**
	 * Returns the value of the '<em><b>Enum Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enum Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enum Type</em>' reference.
	 * @see #setEnumType(TEnum)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getEnumTypeRef_EnumType()
	 * @model
	 * @generated
	 */
	TEnum getEnumType();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.EnumTypeRef#getEnumType <em>Enum Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enum Type</em>' reference.
	 * @see #getEnumType()
	 * @generated
	 */
	void setEnumType(TEnum value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Overrides {@link TypeRef#getTypeRefAsString()}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.types.TEnum%> _enumType = this.getEnumType();\n<%java.lang.String%> _name = null;\nif (_enumType!=null)\n{\n\t_name=_enumType.getName();\n}\n<%java.lang.String%> _plus = (\"type{\" + _name);\n<%java.lang.String%> _plus_1 = (_plus + \"}\");\n<%java.lang.String%> _modifiersAsString = this.getModifiersAsString();\nreturn (_plus_1 + _modifiersAsString);'"
	 * @generated
	 */
	String getTypeRefAsString();

} // EnumTypeRef
