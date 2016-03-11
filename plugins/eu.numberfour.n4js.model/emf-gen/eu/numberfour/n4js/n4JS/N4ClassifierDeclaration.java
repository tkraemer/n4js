/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TypingStrategy;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>N4 Classifier Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.N4ClassifierDeclaration#getTypingStrategy <em>Typing Strategy</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4ClassifierDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface N4ClassifierDeclaration extends N4TypeDeclaration, N4ClassifierDefinition, GenericDeclaration, ThisTarget {
	/**
	 * Returns the value of the '<em><b>Typing Strategy</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.numberfour.n4js.ts.types.TypingStrategy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typing Strategy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Typing Strategy</em>' attribute.
	 * @see eu.numberfour.n4js.ts.types.TypingStrategy
	 * @see #setTypingStrategy(TypingStrategy)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4ClassifierDeclaration_TypingStrategy()
	 * @model unique="false"
	 * @generated
	 */
	TypingStrategy getTypingStrategy();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.N4ClassifierDeclaration#getTypingStrategy <em>Typing Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Typing Strategy</em>' attribute.
	 * @see eu.numberfour.n4js.ts.types.TypingStrategy
	 * @see #getTypingStrategy()
	 * @generated
	 */
	void setTypingStrategy(TypingStrategy value);

} // N4ClassifierDeclaration
