/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TStructMethod;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Method Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyMethodDeclaration()
 * @model
 * @generated
 */
public interface PropertyMethodDeclaration extends AnnotablePropertyAssignment, MethodDeclaration, TypeProvidingElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.types.Type%> _definedType = this.getDefinedType();\nreturn ((<%eu.numberfour.n4js.ts.types.TStructMethod%>) _definedType);'"
	 * @generated
	 */
	TStructMethod getDefinedMember();

} // PropertyMethodDeclaration
