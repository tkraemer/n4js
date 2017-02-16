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
 * A representation of the model object '<em><b>TFormal Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFormalParameter#isVariadic <em>Variadic</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFormalParameter#getAstInitializer <em>Ast Initializer</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFormalParameter#isHasInitializerAssignment <em>Has Initializer Assignment</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TFormalParameter#getTypeRef <em>Type Ref</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFormalParameter()
 * @model
 * @generated
 */
public interface TFormalParameter extends IdentifiableElement, TAnnotableElement, SyntaxRelatedTElement {
	/**
	 * Returns the value of the '<em><b>Variadic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variadic</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variadic</em>' attribute.
	 * @see #setVariadic(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFormalParameter_Variadic()
	 * @model unique="false"
	 * @generated
	 */
	boolean isVariadic();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TFormalParameter#isVariadic <em>Variadic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variadic</em>' attribute.
	 * @see #isVariadic()
	 * @generated
	 */
	void setVariadic(boolean value);

	/**
	 * Returns the value of the '<em><b>Ast Initializer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If and only if the TFormalParameter is used as an AST node, this property holds the initializer
	 * type reference. This occurs in a situation like this:
	 * <pre>
	 * val fn : function(p : int = undefined) => void;
	 * </pre>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ast Initializer</em>' attribute.
	 * @see #setAstInitializer(String)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFormalParameter_AstInitializer()
	 * @model unique="false"
	 * @generated
	 */
	String getAstInitializer();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TFormalParameter#getAstInitializer <em>Ast Initializer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ast Initializer</em>' attribute.
	 * @see #getAstInitializer()
	 * @generated
	 */
	void setAstInitializer(String value);

	/**
	 * Returns the value of the '<em><b>Has Initializer Assignment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Initializer Assignment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Initializer Assignment</em>' attribute.
	 * @see #setHasInitializerAssignment(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFormalParameter_HasInitializerAssignment()
	 * @model unique="false"
	 * @generated
	 */
	boolean isHasInitializerAssignment();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TFormalParameter#isHasInitializerAssignment <em>Has Initializer Assignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Initializer Assignment</em>' attribute.
	 * @see #isHasInitializerAssignment()
	 * @generated
	 */
	void setHasInitializerAssignment(boolean value);

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
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTFormalParameter_TypeRef()
	 * @model containment="true"
	 * @generated
	 */
	TypeRef getTypeRef();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TFormalParameter#getTypeRef <em>Type Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Ref</em>' containment reference.
	 * @see #getTypeRef()
	 * @generated
	 */
	void setTypeRef(TypeRef value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  Convenience method, returns true if this fparam has an initializer.
	 * <!-- end-model-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.lang.String%&gt; _astInitializer = this.getAstInitializer();\nreturn (_astInitializer != null);'"
	 * @generated
	 */
	boolean hasASTInitializer();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  Convenience method, returns true if this or one of the preceding parameters have an initializer.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;&gt; _switchResult = null;\n&lt;%org.eclipse.emf.ecore.EObject%&gt; _eContainer = this.eContainer();\nboolean _matched = false;\nif (_eContainer instanceof &lt;%eu.numberfour.n4js.ts.types.TFunction%&gt;)\n{\n\t_matched=true;\n\t&lt;%org.eclipse.emf.ecore.EObject%&gt; _eContainer_1 = this.eContainer();\n\t_switchResult = ((&lt;%eu.numberfour.n4js.ts.types.TFunction%&gt;) _eContainer_1).getFpars();\n}\nif (!_matched)\n{\n\tif (_eContainer instanceof &lt;%eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef%&gt;)\n\t{\n\t\t_matched=true;\n\t\t&lt;%org.eclipse.emf.ecore.EObject%&gt; _eContainer_1 = this.eContainer();\n\t\t_switchResult = ((&lt;%eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef%&gt;) _eContainer_1).getFpars();\n\t}\n}\nif (!_matched)\n{\n\treturn false;\n}\nfinal &lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;&gt; fpars = _switchResult;\nfor (int i = fpars.indexOf(this); (i &gt;= 0); i--)\n{\n\t{\n\t\tfinal &lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt; fpar = fpars.get(i);\n\t\tif ((fpar.isVariadic() || fpar.isHasInitializerAssignment()))\n\t\t{\n\t\t\treturn true;\n\t\t}\n\t}\n}\nreturn false;'"
	 * @generated
	 */
	boolean isOptional();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  Convenience method, returns true if typeRef undef modifier is optional or variadic.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return (this.isVariadic() || this.isOptional());'"
	 * @generated
	 */
	boolean isVariadicOrOptional();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns string representation of formal parameter according to syntax definition, including
	 * preceding variadic modifier and type. The parameter name is omitted. Use when displaying the
	 * parameter as part of an type or a type expression.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final &lt;%java.lang.StringBuilder%&gt; strb = new &lt;%java.lang.StringBuilder%&gt;();\nboolean _isVariadic = this.isVariadic();\nif (_isVariadic)\n{\n\tstrb.append(\"...\");\n}\n&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _typeRef = this.getTypeRef();\nboolean _tripleNotEquals = (_typeRef != null);\nif (_tripleNotEquals)\n{\n\t&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _typeRef_1 = this.getTypeRef();\n\t&lt;%java.lang.String%&gt; _typeRefAsString = _typeRef_1.getTypeRefAsString();\n\tstrb.append(_typeRefAsString);\n}\nelse\n{\n\tstrb.append(\"null\");\n}\nboolean _isHasInitializerAssignment = this.isHasInitializerAssignment();\nif (_isHasInitializerAssignment)\n{\n\tstrb.append(\"=\\u2026\");\n}\nreturn strb.toString();'"
	 * @generated
	 */
	String getFormalParameterAsTypesString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns string representation of formal parameter according to syntax definition, including
	 * preceding variadic modifier and colon separated type if declared.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final &lt;%java.lang.StringBuilder%&gt; strb = new &lt;%java.lang.StringBuilder%&gt;();\nboolean _isVariadic = this.isVariadic();\nif (_isVariadic)\n{\n\tstrb.append(\"...\");\n}\n&lt;%java.lang.String%&gt; _name = this.getName();\nstrb.append(_name);\n&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _typeRef = this.getTypeRef();\nboolean _tripleNotEquals = (_typeRef != null);\nif (_tripleNotEquals)\n{\n\t&lt;%java.lang.StringBuilder%&gt; _append = strb.append(\": \");\n\t&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _typeRef_1 = this.getTypeRef();\n\t&lt;%java.lang.String%&gt; _typeRefAsString = _typeRef_1.getTypeRefAsString();\n\t_append.append(_typeRefAsString);\n}\nboolean _isHasInitializerAssignment = this.isHasInitializerAssignment();\nif (_isHasInitializerAssignment)\n{\n\tstrb.append(\"=\\u2026\");\n}\nreturn strb.toString();'"
	 * @generated
	 */
	String getFormalParameterAsString();

} // TFormalParameter
