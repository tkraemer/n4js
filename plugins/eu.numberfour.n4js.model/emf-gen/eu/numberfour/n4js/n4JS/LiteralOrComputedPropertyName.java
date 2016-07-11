/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal Or Computed Property Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName#getLiteralName <em>Literal Name</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName#getComputedName <em>Computed Name</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getLiteralOrComputedPropertyName()
 * @model
 * @generated
 */
public interface LiteralOrComputedPropertyName extends EObject {
	/**
	 * Returns the value of the '<em><b>Literal Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Literal Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Literal Name</em>' attribute.
	 * @see #setLiteralName(String)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getLiteralOrComputedPropertyName_LiteralName()
	 * @model unique="false"
	 * @generated
	 */
	String getLiteralName();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName#getLiteralName <em>Literal Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Literal Name</em>' attribute.
	 * @see #getLiteralName()
	 * @generated
	 */
	void setLiteralName(String value);

	/**
	 * Returns the value of the '<em><b>Computed Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Computed Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Computed Name</em>' attribute.
	 * @see #setComputedName(String)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getLiteralOrComputedPropertyName_ComputedName()
	 * @model unique="false" transient="true"
	 * @generated
	 */
	String getComputedName();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName#getComputedName <em>Computed Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Computed Name</em>' attribute.
	 * @see #getComputedName()
	 * @generated
	 */
	void setComputedName(String value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getLiteralOrComputedPropertyName_Expression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _elvis = null;\n<%java.lang.String%> _literalName = this.getLiteralName();\nif (_literalName != null)\n{\n\t_elvis = _literalName;\n} else\n{\n\t<%java.lang.String%> _computedName = this.getComputedName();\n\t_elvis = _computedName;\n}\nreturn _elvis;'"
	 * @generated
	 */
	String getName();

} // LiteralOrComputedPropertyName
