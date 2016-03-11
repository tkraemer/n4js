/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unknown Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Unknown type references are the result of an unresolved link.
 * Unresolved bindings may be ok due to the dynamic nature of Java script
 * or the result of an unresolved binding. No follow up errors should be
 * produced for these cases.
 * <p>
 * var x = doesNotExist // error here
 * x.doesNotExistEither // no error here
 * var String s = x // no error here, too
 * x = s // or here
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef#getTypeName <em>Type Name</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getUnknownTypeRef()
 * @model
 * @generated
 */
public interface UnknownTypeRef extends TypeRef {
	/**
	 * Returns the value of the '<em><b>Type Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The optionally known name of the unknown type, e.g.
	 * var UnknownThing x; // UnknownThing is the name of the type
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Name</em>' attribute.
	 * @see #setTypeName(String)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getUnknownTypeRef_TypeName()
	 * @model unique="false"
	 * @generated
	 */
	String getTypeName();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef#getTypeName <em>Type Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Name</em>' attribute.
	 * @see #getTypeName()
	 * @generated
	 */
	void setTypeName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Overrides {@link TypeRef#getTypeRefAsString()}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _elvis = null;\n<%java.lang.String%> _typeName = this.getTypeName();\nif (_typeName != null)\n{\n\t_elvis = _typeName;\n} else\n{\n\t_elvis = \"[unknown]\";\n}\nreturn _elvis;'"
	 * @generated
	 */
	String getTypeRefAsString();

} // UnknownTypeRef
