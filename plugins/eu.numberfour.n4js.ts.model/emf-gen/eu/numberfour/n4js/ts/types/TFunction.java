/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TFunction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a function declaration. In JavaScript, functions are first-class objects, hence
 * a function declaration also is a type declaration.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFunction#isExternal <em>External</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFunction#getFpars <em>Fpars</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFunction#getReturnTypeRef <em>Return Type Ref</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFunction#getTypeVars <em>Type Vars</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFunction#getDeclaredThisType <em>Declared This Type</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFunction#isDeclaredAsync <em>Declared Async</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFunction#isConstructor <em>Constructor</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFunction()
 * @model
 * @generated
 */
public interface TFunction extends DeclaredTypeWithAccessModifier, SyntaxRelatedTElement {
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
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFunction_External()
	 * @model unique="false"
	 * @generated
	 */
	boolean isExternal();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TFunction#isExternal <em>External</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External</em>' attribute.
	 * @see #isExternal()
	 * @generated
	 */
	void setExternal(boolean value);

	/**
	 * Returns the value of the '<em><b>Fpars</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.ts.types.TFormalParameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Formal parameters
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Fpars</em>' containment reference list.
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFunction_Fpars()
	 * @model containment="true"
	 * @generated
	 */
	EList<TFormalParameter> getFpars();

	/**
	 * Returns the value of the '<em><b>Return Type Ref</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Return type
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Return Type Ref</em>' containment reference.
	 * @see #setReturnTypeRef(TypeRef)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFunction_ReturnTypeRef()
	 * @model containment="true"
	 * @generated
	 */
	TypeRef getReturnTypeRef();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TFunction#getReturnTypeRef <em>Return Type Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type Ref</em>' containment reference.
	 * @see #getReturnTypeRef()
	 * @generated
	 */
	void setReturnTypeRef(TypeRef value);

	/**
	 * Returns the value of the '<em><b>Type Vars</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.ts.types.TypeVariable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Type parameters of a generic function or method. Do not confuse this with the formal parameters.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Vars</em>' containment reference list.
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFunction_TypeVars()
	 * @model containment="true"
	 * @generated
	 */
	EList<TypeVariable> getTypeVars();

	/**
	 * Returns the value of the '<em><b>Declared This Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * optional thisType declaration (@This)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Declared This Type</em>' containment reference.
	 * @see #setDeclaredThisType(TypeRef)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFunction_DeclaredThisType()
	 * @model containment="true"
	 * @generated
	 */
	TypeRef getDeclaredThisType();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TFunction#getDeclaredThisType <em>Declared This Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared This Type</em>' containment reference.
	 * @see #getDeclaredThisType()
	 * @generated
	 */
	void setDeclaredThisType(TypeRef value);

	/**
	 * Returns the value of the '<em><b>Declared Async</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * optional async modifier
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Declared Async</em>' attribute.
	 * @see #setDeclaredAsync(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFunction_DeclaredAsync()
	 * @model unique="false"
	 * @generated
	 */
	boolean isDeclaredAsync();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TFunction#isDeclaredAsync <em>Declared Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Async</em>' attribute.
	 * @see #isDeclaredAsync()
	 * @generated
	 */
	void setDeclaredAsync(boolean value);

	/**
	 * Returns the value of the '<em><b>Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Whether this function is intentionally to be used as a constructor
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Constructor</em>' attribute.
	 * @see #setConstructor(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFunction_Constructor()
	 * @model unique="false"
	 * @generated
	 */
	boolean isConstructor();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TFunction#isConstructor <em>Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constructor</em>' attribute.
	 * @see #isConstructor()
	 * @generated
	 */
	void setConstructor(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Tells whether this function is a method that represents a callable constructor.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final <%org.eclipse.emf.ecore.EObject%> parent = this.eContainer();\nboolean _xifexpression = false;\nif ((parent instanceof <%eu.numberfour.n4js.ts.types.ContainerType%><?>))\n{\n\t<%eu.numberfour.n4js.ts.types.TMethod%> _callableCtor = ((<%eu.numberfour.n4js.ts.types.ContainerType%><?>)parent).getCallableCtor();\n\t_xifexpression = (_callableCtor == this);\n}\nelse\n{\n\t_xifexpression = false;\n}\nreturn _xifexpression;'"
	 * @generated
	 */
	boolean isCallableConstructor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns the formal parameter corresponding to the argument at index 'argIndex' in a function call
	 * or 'null' if 'argIndex' is invalid. This method takes into account optional and variadic parameters.
	 * <!-- end-model-doc -->
	 * @model unique="false" argIndexUnique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>> _fpars = this.getFpars();\nfinal int fparsSize = _fpars.size();\nif (((argIndex >= 0) && (argIndex < fparsSize)))\n{\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>> _fpars_1 = this.getFpars();\n\treturn _fpars_1.get(argIndex);\n}\nelse\n{\n\tif ((((argIndex >= fparsSize) && (fparsSize > 0)) && this.getFpars().get((fparsSize - 1)).isVariadic()))\n\t{\n\t\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>> _fpars_2 = this.getFpars();\n\t\treturn _fpars_2.get((fparsSize - 1));\n\t}\n}\nreturn null;'"
	 * @generated
	 */
	TFormalParameter getFparForArgIdx(int argIndex);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns string representation of this function similar according to the N4JS syntax.
	 * This includes formal parameters and return type (if declared), but excludes annotations.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final <%java.lang.StringBuilder%> strb = new <%java.lang.StringBuilder%>();\nboolean _isGeneric = this.isGeneric();\nif (_isGeneric)\n{\n\t<%java.lang.StringBuilder%> _append = strb.append(\"<\");\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TypeVariable%>> _typeVars = this.getTypeVars();\n\tfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.types.TypeVariable%>, <%java.lang.String%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.types.TypeVariable%>, <%java.lang.String%>>()\n\t{\n\t\tpublic <%java.lang.String%> apply(final <%eu.numberfour.n4js.ts.types.TypeVariable%> it)\n\t\t{\n\t\t\treturn it.getTypeAsString();\n\t\t}\n\t};\n\t<%org.eclipse.emf.common.util.EList%><<%java.lang.String%>> _map = <%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%>.<<%eu.numberfour.n4js.ts.types.TypeVariable%>, <%java.lang.String%>>map(_typeVars, _function);\n\t<%java.lang.String%> _join = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.join(_map, \",\");\n\t<%java.lang.StringBuilder%> _append_1 = _append.append(_join);\n\t_append_1.append(\"> \");\n}\nboolean _isDeclaredAsync = this.isDeclaredAsync();\nif (_isDeclaredAsync)\n{\n\tstrb.append(\"async \");\n}\nstrb.append(\"function \");\n<%java.lang.String%> _name = this.getName();\n<%java.lang.StringBuilder%> _append_2 = strb.append(_name);\n<%java.lang.StringBuilder%> _append_3 = _append_2.append(\"(\");\n<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>> _fpars = this.getFpars();\nfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>, <%java.lang.String%>> _function_1 = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>, <%java.lang.String%>>()\n{\n\tpublic <%java.lang.String%> apply(final <%eu.numberfour.n4js.ts.types.TFormalParameter%> it)\n\t{\n\t\treturn it.getFormalParameterAsString();\n\t}\n};\n<%org.eclipse.emf.common.util.EList%><<%java.lang.String%>> _map_1 = <%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%>.<<%eu.numberfour.n4js.ts.types.TFormalParameter%>, <%java.lang.String%>>map(_fpars, _function_1);\n<%java.lang.String%> _join_1 = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.join(_map_1, \", \");\n<%java.lang.StringBuilder%> _append_4 = _append_3.append(_join_1);\n_append_4.append(\")\");\n<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _returnTypeRef = this.getReturnTypeRef();\nboolean _tripleNotEquals = (_returnTypeRef != null);\nif (_tripleNotEquals)\n{\n\t<%java.lang.StringBuilder%> _append_5 = strb.append(\": \");\n\t<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _returnTypeRef_1 = this.getReturnTypeRef();\n\t<%java.lang.String%> _typeRefAsString = _returnTypeRef_1.getTypeRefAsString();\n\t_append_5.append(_typeRefAsString);\n}\nreturn strb.toString();'"
	 * @generated
	 */
	String getFunctionAsString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * There may be sub-types of a function type unless explicitly stated differently
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isFinal();

} // TFunction
