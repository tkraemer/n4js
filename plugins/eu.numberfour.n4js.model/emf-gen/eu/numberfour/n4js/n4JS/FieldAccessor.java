/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Accessor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for getters and setter, of either object literals (PropertyG/SetterDeclaration) or classes (N4G/SetterDeclaration).
 * <!-- end-model-doc -->
 *
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getFieldAccessor()
 * @model abstract="true"
 * @generated
 */
public interface FieldAccessor extends FunctionOrFieldAccessor, TypeProvidingElement, PropertyNameOwner {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The declared type of the field accessor, which is either the return type of the getter or the type of the formal parameter in case of a setter
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return null;'"
	 * @generated
	 */
	TypeRef getDeclaredTypeRef();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the defined FieldAccessor in the TModule, either a TGetter or a TSetter.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	eu.numberfour.n4js.ts.types.FieldAccessor getDefinedAccessor();

} // FieldAccessor
