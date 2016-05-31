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
 * A representation of the model object '<em><b>N4 Method Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.N4MethodDeclaration#getComputeNameFrom <em>Compute Name From</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4MethodDeclaration()
 * @model
 * @generated
 */
public interface N4MethodDeclaration extends AnnotableN4MemberDeclaration, MethodDeclaration {
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
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4MethodDeclaration_ComputeNameFrom()
	 * @model containment="true"
	 * @generated
	 */
	Expression getComputeNameFrom();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.N4MethodDeclaration#getComputeNameFrom <em>Compute Name From</em>}' containment reference.
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return ((((this.eContainer() instanceof <%eu.numberfour.n4js.n4JS.N4InterfaceDeclaration%>) && (this.getBody() == null)) && \n\t(!<%org.eclipse.xtext.xbase.lib.IterableExtensions%>.<<%eu.numberfour.n4js.n4JS.Annotation%>>exists(this.getAnnotations(), new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.n4JS.Annotation%>, <%java.lang.Boolean%>>()\n\t{\n\t\tpublic <%java.lang.Boolean%> apply(final <%eu.numberfour.n4js.n4JS.Annotation%> it)\n\t\t{\n\t\t\t<%java.lang.String%> _name = it.getName();\n\t\t\treturn <%java.lang.Boolean%>.valueOf(<%com.google.common.base.Objects%>.equal(_name, \"ProvidesDefaultImplementation\"));\n\t\t}\n\t}))) || this.getDeclaredModifiers().contains(<%eu.numberfour.n4js.n4JS.N4Modifier%>.ABSTRACT));'"
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return (<%com.google.common.base.Objects%>.equal(\"constructor\", this.getName()) && (!this.isStatic()));'"
	 * @generated
	 */
	boolean isConstructor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return ((null == this.getName()) && (null == this.getComputeNameFrom()));'"
	 * @generated
	 */
	boolean isCallableConstructor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.n4JS.N4Modifier%>> _declaredModifiers = this.getDeclaredModifiers();\nreturn _declaredModifiers.contains(<%eu.numberfour.n4js.n4JS.N4Modifier%>.STATIC);'"
	 * @generated
	 */
	boolean isStatic();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Methods in classes may not be called 'prototype'.
	 * Generators may not be called 'constructor' either (except for computed names).
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _name = this.getName();\nboolean _equals = <%com.google.common.base.Objects%>.equal(\"prototype\", _name);\nif (_equals)\n{\n\treturn false;\n}\nif (((<%com.google.common.base.Objects%>.equal(\"constructor\", this.getName()) && this.isGenerator()) && (this.getKind() != <%eu.numberfour.n4js.n4JS.PropertyNameKind%>.COMPUTED_FROM_STRING_LITERAL)))\n{\n\treturn false;\n}\nreturn true;'"
	 * @generated
	 */
	boolean isValidName();

} // N4MethodDeclaration
