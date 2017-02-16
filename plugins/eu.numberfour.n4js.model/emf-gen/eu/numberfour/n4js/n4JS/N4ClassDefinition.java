/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;

import eu.numberfour.n4js.ts.types.TClass;

import java.lang.Iterable;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>N4 Class Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * *
 * Base class for {@link N4ClassDeclaration} and {@link N4ClassExpression}
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.N4ClassDefinition#getSuperClassRef <em>Super Class Ref</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.N4ClassDefinition#getSuperClassExpression <em>Super Class Expression</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.N4ClassDefinition#getImplementedInterfaceRefs <em>Implemented Interface Refs</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4ClassDefinition()
 * @model abstract="true"
 * @generated
 */
public interface N4ClassDefinition extends N4ClassifierDefinition, ThisTarget {
	/**
	 * Returns the value of the '<em><b>Super Class Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Class Ref</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Class Ref</em>' containment reference.
	 * @see #setSuperClassRef(ParameterizedTypeRef)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4ClassDefinition_SuperClassRef()
	 * @model containment="true"
	 * @generated
	 */
	ParameterizedTypeRef getSuperClassRef();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.N4ClassDefinition#getSuperClassRef <em>Super Class Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Class Ref</em>' containment reference.
	 * @see #getSuperClassRef()
	 * @generated
	 */
	void setSuperClassRef(ParameterizedTypeRef value);

	/**
	 * Returns the value of the '<em><b>Super Class Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Class Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Class Expression</em>' containment reference.
	 * @see #setSuperClassExpression(Expression)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4ClassDefinition_SuperClassExpression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getSuperClassExpression();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.N4ClassDefinition#getSuperClassExpression <em>Super Class Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Class Expression</em>' containment reference.
	 * @see #getSuperClassExpression()
	 * @generated
	 */
	void setSuperClassExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Implemented Interface Refs</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implemented Interface Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implemented Interface Refs</em>' containment reference list.
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4ClassDefinition_ImplementedInterfaceRefs()
	 * @model containment="true"
	 * @generated
	 */
	EList<ParameterizedTypeRef> getImplementedInterfaceRefs();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns {@link #getDefinedType()} casted to {@link TClass}.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.types.Type%> _definedType = this.getDefinedType();\nreturn ((<%eu.numberfour.n4js.ts.types.TClass%>) _definedType);'"
	 * @generated
	 */
	TClass getDefinedTypeAsClass();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefIterable" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%> _superClassRef = this.getSuperClassRef();\n<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>> _implementedInterfaceRefs = this.getImplementedInterfaceRefs();\nreturn <%com.google.common.collect.Iterables%>.<<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>>concat(java.util.Collections.<<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>>unmodifiableList(org.eclipse.xtext.xbase.lib.CollectionLiterals.<<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>>newArrayList(_superClassRef)), _implementedInterfaceRefs);'"
	 * @generated
	 */
	Iterable<ParameterizedTypeRef> getSuperClassifierRefs();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefIterable" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getImplementedInterfaceRefs();'"
	 * @generated
	 */
	Iterable<ParameterizedTypeRef> getImplementedOrExtendedInterfaceRefs();

} // N4ClassDefinition
