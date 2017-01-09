/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TFormalParameter;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.FormalParameter#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.FormalParameter#isVariadic <em>Variadic</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.FormalParameter#isHasInitializerAssignment <em>Has Initializer Assignment</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.FormalParameter#getDefinedTypeElement <em>Defined Type Element</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.FormalParameter#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.FormalParameter#getBindingPattern <em>Binding Pattern</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getFormalParameter()
 * @model
 * @generated
 */
public interface FormalParameter extends AnnotableElement, Variable {
	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.n4JS.Annotation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotations</em>' containment reference list.
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getFormalParameter_Annotations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Annotation> getAnnotations();

	/**
	 * Returns the value of the '<em><b>Variadic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variadic</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variadic</em>' attribute.
	 * @see #setVariadic(boolean)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getFormalParameter_Variadic()
	 * @model unique="false"
	 * @generated
	 */
	boolean isVariadic();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.FormalParameter#isVariadic <em>Variadic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variadic</em>' attribute.
	 * @see #isVariadic()
	 * @generated
	 */
	void setVariadic(boolean value);

	/**
	 * Returns the value of the '<em><b>Has Initializer Assignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Initializer Assignment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Initializer Assignment</em>' attribute.
	 * @see #setHasInitializerAssignment(boolean)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getFormalParameter_HasInitializerAssignment()
	 * @model unique="false"
	 * @generated
	 */
	boolean isHasInitializerAssignment();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.FormalParameter#isHasInitializerAssignment <em>Has Initializer Assignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Initializer Assignment</em>' attribute.
	 * @see #isHasInitializerAssignment()
	 * @generated
	 */
	void setHasInitializerAssignment(boolean value);

	/**
	 * Returns the value of the '<em><b>Defined Type Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defined Type Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defined Type Element</em>' reference.
	 * @see #setDefinedTypeElement(TFormalParameter)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getFormalParameter_DefinedTypeElement()
	 * @model transient="true"
	 * @generated
	 */
	TFormalParameter getDefinedTypeElement();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.FormalParameter#getDefinedTypeElement <em>Defined Type Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defined Type Element</em>' reference.
	 * @see #getDefinedTypeElement()
	 * @generated
	 */
	void setDefinedTypeElement(TFormalParameter value);

	/**
	 * Returns the value of the '<em><b>Initializer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initializer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initializer</em>' containment reference.
	 * @see #setInitializer(Expression)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getFormalParameter_Initializer()
	 * @model containment="true"
	 * @generated
	 */
	Expression getInitializer();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.FormalParameter#getInitializer <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initializer</em>' containment reference.
	 * @see #getInitializer()
	 * @generated
	 */
	void setInitializer(Expression value);

	/**
	 * Returns the value of the '<em><b>Binding Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding Pattern</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding Pattern</em>' containment reference.
	 * @see #setBindingPattern(BindingPattern)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getFormalParameter_BindingPattern()
	 * @model containment="true"
	 * @generated
	 */
	BindingPattern getBindingPattern();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.FormalParameter#getBindingPattern <em>Binding Pattern</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding Pattern</em>' containment reference.
	 * @see #getBindingPattern()
	 * @generated
	 */
	void setBindingPattern(BindingPattern value);

} // FormalParameter
