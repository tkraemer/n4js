/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.transpiler.im;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Symbol Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.transpiler.im.SymbolTable#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.transpiler.im.ImPackage#getSymbolTable()
 * @model
 * @generated
 */
public interface SymbolTable extends EObject {
	/**
	 * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.transpiler.im.SymbolTableEntry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entries</em>' containment reference list.
	 * @see eu.numberfour.n4js.transpiler.im.ImPackage#getSymbolTable_Entries()
	 * @model containment="true"
	 * @generated
	 */
	EList<SymbolTableEntry> getEntries();

} // SymbolTable
