/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TVariable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Used for storing information about exported variables in the Xtext index, similar
 * to Types (such as TClass, TInterface, TFunction). It references the variable declaration of the
 * export declaration (via SyntaxRelatedTElement's astElement).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.TVariable#getTypeRef <em>Type Ref</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TVariable#isConst <em>Const</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TVariable#isExternal <em>External</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTVariable()
 * @model
 * @generated
 */
public interface TVariable extends TExportableElement, SyntaxRelatedTElement, TAnnotableElement, AccessibleTypeElement {
	/**
	 * Returns the value of the '<em><b>Type Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Ref</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Ref</em>' containment reference.
	 * @see #setTypeRef(TypeRef)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTVariable_TypeRef()
	 * @model containment="true"
	 * @generated
	 */
	TypeRef getTypeRef();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TVariable#getTypeRef <em>Type Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Ref</em>' containment reference.
	 * @see #getTypeRef()
	 * @generated
	 */
	void setTypeRef(TypeRef value);

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
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTVariable_Const()
	 * @model unique="false"
	 * @generated
	 */
	boolean isConst();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TVariable#isConst <em>Const</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Const</em>' attribute.
	 * @see #isConst()
	 * @generated
	 */
	void setConst(boolean value);

	/**
	 * Returns the value of the '<em><b>External</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External</em>' attribute.
	 * @see #setExternal(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTVariable_External()
	 * @model unique="false"
	 * @generated
	 */
	boolean isExternal();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TVariable#isExternal <em>External</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External</em>' attribute.
	 * @see #isExternal()
	 * @generated
	 */
	void setExternal(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns string representation of variable according to syntax definition.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final <%java.lang.StringBuilder%> strb = new <%java.lang.StringBuilder%>();\nboolean _isConst = this.isConst();\nif (_isConst)\n{\n\tstrb.append(\"const \");\n}\nelse\n{\n\tstrb.append(\"var \");\n}\n<%java.lang.String%> _name = this.getName();\nstrb.append(_name);\n<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _typeRef = this.getTypeRef();\nboolean _tripleNotEquals = (_typeRef != null);\nif (_tripleNotEquals)\n{\n\t<%java.lang.StringBuilder%> _append = strb.append(\": \");\n\t<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _typeRef_1 = this.getTypeRef();\n\t<%java.lang.String%> _typeRefAsString = _typeRef_1.getTypeRefAsString();\n\t_append.append(_typeRefAsString);\n}\nreturn strb.toString();'"
	 * @generated
	 */
	String getVariableAsString();

} // TVariable
