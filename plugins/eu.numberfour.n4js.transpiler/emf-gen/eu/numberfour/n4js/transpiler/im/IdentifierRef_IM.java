/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.transpiler.im;

import eu.numberfour.n4js.n4JS.IdentifierRef;

import eu.numberfour.n4js.ts.types.IdentifiableElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifier Ref IM</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SymbolTable-aware replacement for {@link IdentifierRef}.
 * Original property {@link IdentifierRef.id} is always {@code null}.
 * <!-- end-model-doc -->
 *
 *
 * @see eu.numberfour.n4js.transpiler.im.ImPackage#getIdentifierRef_IM()
 * @model
 * @generated
 */
public interface IdentifierRef_IM extends IdentifierRef, ReferencingElementExpression_IM {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getRewiredTarget();'"
	 * @generated
	 */
	SymbolTableEntry getId_IM();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model targetUnique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='this.setRewiredTarget(target);'"
	 * @generated
	 */
	void setId_IM(SymbolTableEntry target);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  overridden attribute access to always return null
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return null;'"
	 * @generated
	 */
	IdentifiableElement getId();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model ixUnique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='if ((ix != null))\n{\n\tthrow new <%java.lang.IllegalArgumentException%>(\"IDRef_IM cannot accept Identifiers. Use #id_IM.\");\n}'"
	 * @generated
	 */
	void setId(IdentifiableElement ix);

} // IdentifierRef_IM
