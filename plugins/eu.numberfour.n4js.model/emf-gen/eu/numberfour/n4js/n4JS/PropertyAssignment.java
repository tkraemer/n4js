/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TStructMember;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A PropertyAssignment is either a simple {@link PropertyNameValuePair},
 * or a property accessor, that is a {@link PropertyGetterDeclaration} or
 * {@link PropertySetterDeclaration}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.PropertyAssignment#getComputeNameFrom <em>Compute Name From</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyAssignment()
 * @model abstract="true"
 * @generated
 */
public interface PropertyAssignment extends AnnotableElement, VariableEnvironmentElement, PropertyNameOwner {
	/**
	 * Returns the value of the '<em><b>Compute Name From</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compute Name From</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compute Name From</em>' containment reference.
	 * @see #setComputeNameFrom(Expression)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyAssignment_ComputeNameFrom()
	 * @model containment="true"
	 * @generated
	 */
	Expression getComputeNameFrom();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.PropertyAssignment#getComputeNameFrom <em>Compute Name From</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compute Name From</em>' containment reference.
	 * @see #getComputeNameFrom()
	 * @generated
	 */
	void setComputeNameFrom(Expression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	TStructMember getDefinedMember();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Properties may not be called 'prototype' or 'constructor' (except for computed names).
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _name = this.getName();\nboolean _equals = <%com.google.common.base.Objects%>.equal(\"prototype\", _name);\nif (_equals)\n{\n\treturn false;\n}\nboolean _and = false;\n<%java.lang.String%> _name_1 = this.getName();\nboolean _equals_1 = <%com.google.common.base.Objects%>.equal(\"constructor\", _name_1);\nif (!_equals_1)\n{\n\t_and = false;\n} else\n{\n\t<%eu.numberfour.n4js.n4JS.PropertyNameKind%> _kind = this.getKind();\n\tboolean _tripleNotEquals = (_kind != <%eu.numberfour.n4js.n4JS.PropertyNameKind%>.COMPUTED_FROM_STRING_LITERAL);\n\t_and = _tripleNotEquals;\n}\nif (_and)\n{\n\treturn false;\n}\nreturn true;'"
	 * @generated
	 */
	boolean isValidName();

} // PropertyAssignment
