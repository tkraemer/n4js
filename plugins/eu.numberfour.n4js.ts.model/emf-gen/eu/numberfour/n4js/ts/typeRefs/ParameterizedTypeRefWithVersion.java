/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs;

import java.math.BigDecimal;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameterized Type Ref With Version</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Type reference with explicitly defined version.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefWithVersion#getDeclaredVersion <em>Declared Version</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getParameterizedTypeRefWithVersion()
 * @model
 * @generated
 */
public interface ParameterizedTypeRefWithVersion extends ParameterizedTypeRef {
	/**
	 * Returns the value of the '<em><b>Declared Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The declared version of the reference, this is only available in languages derived from N4JS.
	 * Note that this is the declared version of the reference, not of the declared type itself!
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Declared Version</em>' attribute.
	 * @see #setDeclaredVersion(BigDecimal)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getParameterizedTypeRefWithVersion_DeclaredVersion()
	 * @model unique="false"
	 * @generated
	 */
	BigDecimal getDeclaredVersion();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefWithVersion#getDeclaredVersion <em>Declared Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Version</em>' attribute.
	 * @see #getDeclaredVersion()
	 * @generated
	 */
	void setDeclaredVersion(BigDecimal value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Overrides method declared in TypeRef, returns the declared version instead of the default value.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.math.BigDecimal%> _declaredVersion = this.getDeclaredVersion();\nboolean _tripleEquals = (_declaredVersion == null);\nif (_tripleEquals)\n{\n\treturn 0;\n}\n<%java.math.BigDecimal%> _declaredVersion_1 = this.getDeclaredVersion();\nreturn _declaredVersion_1.intValue();'"
	 * @generated
	 */
	int getReferencedVersion();

} // ParameterizedTypeRefWithVersion
