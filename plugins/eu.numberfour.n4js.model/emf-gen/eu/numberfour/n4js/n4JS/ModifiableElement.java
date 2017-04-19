/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.n4JS;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modifiable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Abstract base class for elements that may have declared modifiers.
 * See {@link N4Modifier}.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.ModifiableElement#getDeclaredModifiers <em>Declared Modifiers</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getModifiableElement()
 * @model abstract="true"
 * @generated
 */
public interface ModifiableElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Declared Modifiers</b></em>' attribute list.
	 * The list contents are of type {@link eu.numberfour.n4js.n4JS.N4Modifier}.
	 * The literals are from the enumeration {@link eu.numberfour.n4js.n4JS.N4Modifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Modifiers</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Modifiers</em>' attribute list.
	 * @see eu.numberfour.n4js.n4JS.N4Modifier
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getModifiableElement_DeclaredModifiers()
	 * @model unique="false"
	 * @generated
	 */
	EList<N4Modifier> getDeclaredModifiers();

} // ModifiableElement
