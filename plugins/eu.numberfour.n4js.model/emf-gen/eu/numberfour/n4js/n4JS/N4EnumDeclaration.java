/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TEnum;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>N4 Enum Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.N4EnumDeclaration#getLiterals <em>Literals</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4EnumDeclaration()
 * @model
 * @generated
 */
public interface N4EnumDeclaration extends N4TypeDeclaration {
	/**
	 * Returns the value of the '<em><b>Literals</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.n4JS.N4EnumLiteral}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Literals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Literals</em>' containment reference list.
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4EnumDeclaration_Literals()
	 * @model containment="true"
	 * @generated
	 */
	EList<N4EnumLiteral> getLiterals();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns {@link #getDefinedType()} casted to {@link TEnum}.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%eu.numberfour.n4js.ts.types.Type%&gt; _definedType = this.getDefinedType();\nreturn ((&lt;%eu.numberfour.n4js.ts.types.TEnum%&gt;) _definedType);'"
	 * @generated
	 */
	TEnum getDefinedTypeAsEnum();

} // N4EnumDeclaration
