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
 * A representation of the model object '<em><b>Import Specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.ImportSpecifier#isFlaggedUsedInCode <em>Flagged Used In Code</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getImportSpecifier()
 * @model abstract="true"
 * @generated
 */
public interface ImportSpecifier extends EObject {
	/**
	 * Returns the value of the '<em><b>Flagged Used In Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * * Transient flag to keep track of actual usage during scoping.
	 * See {@code eu.numberfour.n4js.scoping.imports.OriginAwareScope}
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Flagged Used In Code</em>' attribute.
	 * @see #setFlaggedUsedInCode(boolean)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getImportSpecifier_FlaggedUsedInCode()
	 * @model unique="false" transient="true"
	 * @generated
	 */
	boolean isFlaggedUsedInCode();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.ImportSpecifier#isFlaggedUsedInCode <em>Flagged Used In Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flagged Used In Code</em>' attribute.
	 * @see #isFlaggedUsedInCode()
	 * @generated
	 */
	void setFlaggedUsedInCode(boolean value);

} // ImportSpecifier
