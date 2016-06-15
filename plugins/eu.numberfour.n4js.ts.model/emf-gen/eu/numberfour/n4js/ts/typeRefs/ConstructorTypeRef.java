/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constructor Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * *
 * A reference to the constructor of a type. The constructor
 * can be used in a NewExpression. It is a subtype of the equivalent
 * ClassifierTypeRef.
 * <!-- end-model-doc -->
 *
 *
 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getConstructorTypeRef()
 * @model
 * @generated
 */
public interface ConstructorTypeRef extends ClassifierTypeRef {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Overrides {@link TypeRef#getTypeRefAsString()}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _xifexpression = null;\n<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _typeRef = this.getTypeRef();\nboolean _tripleEquals = (null == _typeRef);\nif (_tripleEquals)\n{\n\t_xifexpression = \"\";\n}\nelse\n{\n\t<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _typeRef_1 = this.getTypeRef();\n\t_xifexpression = _typeRef_1.getTypeRefAsString();\n}\nfinal <%java.lang.String%> refName = _xifexpression;\n<%java.lang.String%> _modifiersAsString = this.getModifiersAsString();\nreturn (((\"constructor{\" + refName) + \"}\") + _modifiersAsString);'"
	 * @generated
	 */
	String getTypeRefAsString();

} // ConstructorTypeRef
