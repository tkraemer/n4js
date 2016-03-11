/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4mf;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Init Modules</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * *
 * Class for encapsulating a collection of initializer bootstrap modules for the current project.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4mf.InitModules#getInitModules <em>Init Modules</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4mf.N4mfPackage#getInitModules()
 * @model
 * @generated
 */
public interface InitModules extends EObject {
	/**
	 * Returns the value of the '<em><b>Init Modules</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.n4mf.BootstrapModule}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Modules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init Modules</em>' containment reference list.
	 * @see eu.numberfour.n4js.n4mf.N4mfPackage#getInitModules_InitModules()
	 * @model containment="true"
	 * @generated
	 */
	EList<BootstrapModule> getInitModules();

} // InitModules
