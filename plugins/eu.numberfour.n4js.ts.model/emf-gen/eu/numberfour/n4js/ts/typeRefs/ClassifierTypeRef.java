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
 * A representation of the model object '<em><b>Classifier Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * *
 * A reference to the type of a type. This type can be used
 * to access the static members of a type.
 * It can either be a ParameterizedTypeRefNominal or a ThisTypeNominal
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef#getTypeArg <em>Type Arg</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getClassifierTypeRef()
 * @model
 * @generated
 */
public interface ClassifierTypeRef extends BaseTypeRef {
	/**
	 * Returns the value of the '<em><b>Type Arg</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Arg</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Arg</em>' containment reference.
	 * @see #setTypeArg(TypeArgument)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getClassifierTypeRef_TypeArg()
	 * @model containment="true"
	 * @generated
	 */
	TypeArgument getTypeArg();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef#getTypeArg <em>Type Arg</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Arg</em>' containment reference.
	 * @see #getTypeArg()
	 * @generated
	 */
	void setTypeArg(TypeArgument value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Overrides {@link TypeRef#getTypeRefAsString()}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%> _typeArg = this.getTypeArg();\n<%java.lang.String%> _typeRefAsString = null;\nif (_typeArg!=null)\n{\n\t_typeRefAsString=_typeArg.getTypeRefAsString();\n}\nfinal <%java.lang.String%> refName = _typeRefAsString;\n<%java.lang.String%> _modifiersAsString = this.getModifiersAsString();\nreturn (((\"type{\" + refName) + \"}\") + _modifiersAsString);'"
	 * @generated
	 */
	String getTypeRefAsString();

} // ClassifierTypeRef
