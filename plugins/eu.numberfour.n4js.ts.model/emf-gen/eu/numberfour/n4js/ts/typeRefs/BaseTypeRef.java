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
 * A representation of the model object '<em><b>Base Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.BaseTypeRef#isDynamic <em>Dynamic</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getBaseTypeRef()
 * @model abstract="true"
 * @generated
 */
public interface BaseTypeRef extends StaticBaseTypeRef {
	/**
	 * Returns the value of the '<em><b>Dynamic</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Flag indicating that new properties can be dynamically added to the type. Default value is false.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dynamic</em>' attribute.
	 * @see #setDynamic(boolean)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getBaseTypeRef_Dynamic()
	 * @model default="false" unique="false"
	 * @generated
	 */
	boolean isDynamic();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.BaseTypeRef#isDynamic <em>Dynamic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic</em>' attribute.
	 * @see #isDynamic()
	 * @generated
	 */
	void setDynamic(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns dynamic, null, undef modifier strings similar to N4JS syntax, not all values are represented.
	 * This method is called by subclasses in overridden method.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _xifexpression = null;\nboolean _isDynamic = this.isDynamic();\nif (_isDynamic)\n{\n\t_xifexpression = \"+\";\n}\nelse\n{\n\t_xifexpression = \"\";\n}\n<%java.lang.String%> _modifiersAsString = super.getModifiersAsString();\nreturn (_xifexpression + _modifiersAsString);'"
	 * @generated
	 */
	String getModifiersAsString();

} // BaseTypeRef
