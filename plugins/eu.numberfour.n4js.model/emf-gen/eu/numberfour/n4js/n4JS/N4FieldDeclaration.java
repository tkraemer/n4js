/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TMember;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>N4 Field Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.N4FieldDeclaration#getDefinedField <em>Defined Field</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.N4FieldDeclaration#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4FieldDeclaration()
 * @model
 * @generated
 */
public interface N4FieldDeclaration extends AnnotableN4MemberDeclaration, TypedElement, ThisArgProvider, PropertyNameOwner {
	/**
	 * Returns the value of the '<em><b>Defined Field</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defined Field</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defined Field</em>' reference.
	 * @see #setDefinedField(TField)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4FieldDeclaration_DefinedField()
	 * @model transient="true"
	 * @generated
	 */
	TField getDefinedField();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.N4FieldDeclaration#getDefinedField <em>Defined Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defined Field</em>' reference.
	 * @see #getDefinedField()
	 * @generated
	 */
	void setDefinedField(TField value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Initializer expression.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4FieldDeclaration_Expression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.N4FieldDeclaration#getExpression <em>Expression</em>}' containment reference.
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getDefinedField();'"
	 * @generated
	 */
	TMember getDefinedTypeElement();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns true if the field is declared as const.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.n4JS.N4Modifier%>> _declaredModifiers = this.getDeclaredModifiers();\nreturn _declaredModifiers.contains(<%eu.numberfour.n4js.n4JS.N4Modifier%>.CONST);'"
	 * @generated
	 */
	boolean isConst();

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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _name = this.getName();\nboolean _equals = <%com.google.common.base.Objects%>.equal(\"prototype\", _name);\nif (_equals)\n{\n\treturn false;\n}\nreturn true;'"
	 * @generated
	 */
	boolean isValid();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _name = this.getName();\nboolean _equals = <%com.google.common.base.Objects%>.equal(\"prototype\", _name);\nif (_equals)\n{\n\treturn false;\n}\nreturn true;'"
	 * @generated
	 */
	boolean isValidName();

} // N4FieldDeclaration
