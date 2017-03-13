/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TConstable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An element that may be declared 'const', i.e. a variable or field.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.TConstableElement#isConst <em>Const</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TConstableElement#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTConstableElement()
 * @model abstract="true"
 * @generated
 */
public interface TConstableElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Const</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Const</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Const</em>' attribute.
	 * @see #setConst(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTConstableElement_Const()
	 * @model unique="false"
	 * @generated
	 */
	boolean isConst();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TConstableElement#isConst <em>Const</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Const</em>' attribute.
	 * @see #isConst()
	 * @generated
	 */
	void setConst(boolean value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Iff this element (variable or field) is declared 'const' AND has an initializer expression, then this property
	 * gives the serialized compile-time value of the element's initializer expression, as returned by method
	 * {@code CompileTimeValue#serialize()}; otherwise this property will be <code>null</code>.
	 * <p>
	 * If the initializer expression is not a valid compile-time expression, this property will hold an invalid
	 * CompileTimeValue (cf. {@code CompileTimeValue#isValid()}), which may provide error messages.
	 * <p>
	 * This field is set by {@code CompileTimeExpressionProcessor#evaluateCompileTimeExpression()} and will be undefined
	 * until the first phase of post-processing has completed, see {@code CompileTimeExpressionProcessor}.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTConstableElement_Value()
	 * @model unique="false"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TConstableElement#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // TConstableElement
