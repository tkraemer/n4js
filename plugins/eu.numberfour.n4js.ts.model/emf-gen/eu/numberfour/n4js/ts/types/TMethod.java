/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TMethod</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Method of a class.
 *  * Attention: Order of super types matters, see https://bugs.eclipse.org/bugs/show_bug.cgi?id=421592
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.TMethod#isDeclaredAbstract <em>Declared Abstract</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TMethod#isLacksThisOrSuperUsage <em>Lacks This Or Super Usage</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTMethod()
 * @model
 * @generated
 */
public interface TMethod extends TFunction, TMemberWithAccessModifier {
	/**
	 * Returns the value of the '<em><b>Declared Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Abstract</em>' attribute.
	 * @see #setDeclaredAbstract(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTMethod_DeclaredAbstract()
	 * @model unique="false"
	 * @generated
	 */
	boolean isDeclaredAbstract();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TMethod#isDeclaredAbstract <em>Declared Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Abstract</em>' attribute.
	 * @see #isDeclaredAbstract()
	 * @generated
	 */
	void setDeclaredAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Lacks This Or Super Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Knowing whether the body of the linked method contains occurrences of ThisLiteral or SuperLiteral
	 * is useful in cross-resource scenarios (ie, so as to avoid parsing) when checking the (restrictive) conditions
	 * under which a method reference can be assigned to a variable (IDE-1048, see implementation in N4JSExpressionValidator).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lacks This Or Super Usage</em>' attribute.
	 * @see #setLacksThisOrSuperUsage(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTMethod_LacksThisOrSuperUsage()
	 * @model unique="false"
	 * @generated
	 */
	boolean isLacksThisOrSuperUsage();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TMethod#isLacksThisOrSuperUsage <em>Lacks This Or Super Usage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lacks This Or Super Usage</em>' attribute.
	 * @see #isLacksThisOrSuperUsage()
	 * @generated
	 */
	void setLacksThisOrSuperUsage(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns true if the method is either declared abstract or it is implicitly abstract, i.e. it is declared in a role and has no body.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return (this.isDeclaredAbstract() || ((this.eContainer() instanceof &lt;%eu.numberfour.n4js.ts.types.TInterface%&gt;) &amp;&amp; this.isHasNoBody()));'"
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * * Always returns METHOD
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return &lt;%eu.numberfour.n4js.ts.types.MemberType%&gt;.METHOD;'"
	 * @generated
	 */
	MemberType getMemberType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return (&lt;%com.google.common.base.Objects%&gt;.equal(this.getName(), \"constructor\") &amp;&amp; (!this.isStatic()));'"
	 * @generated
	 */
	boolean isConstructor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns string representation of this function similar according to the N4JS syntax.
	 * This includes formal parameters and return type (if declared), but excludes annotations.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final &lt;%java.lang.StringBuilder%&gt; strb = new &lt;%java.lang.StringBuilder%&gt;();\nboolean _isGeneric = this.isGeneric();\nif (_isGeneric)\n{\n\t&lt;%java.lang.StringBuilder%&gt; _append = strb.append(\"&lt;\");\n\t&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt;&gt; _typeVars = this.getTypeVars();\n\tfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt;, &lt;%java.lang.String%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt;, &lt;%java.lang.String%&gt;&gt;()\n\t{\n\t\tpublic &lt;%java.lang.String%&gt; apply(final &lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt; it)\n\t\t{\n\t\t\treturn it.getTypeAsString();\n\t\t}\n\t};\n\t&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%java.lang.String%&gt;&gt; _map = &lt;%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt;, &lt;%java.lang.String%&gt;&gt;map(_typeVars, _function);\n\t&lt;%java.lang.String%&gt; _join = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.join(_map, \",\");\n\t&lt;%java.lang.StringBuilder%&gt; _append_1 = _append.append(_join);\n\t_append_1.append(\"&gt; \");\n}\nboolean _isDeclaredAsync = this.isDeclaredAsync();\nif (_isDeclaredAsync)\n{\n\tstrb.append(\"async \");\n}\n&lt;%java.lang.String%&gt; _name = this.getName();\n&lt;%java.lang.StringBuilder%&gt; _append_2 = strb.append(_name);\n&lt;%java.lang.StringBuilder%&gt; _append_3 = _append_2.append(\"(\");\n&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;&gt; _fpars = this.getFpars();\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;, &lt;%java.lang.String%&gt;&gt; _function_1 = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;, &lt;%java.lang.String%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.String%&gt; apply(final &lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt; it)\n\t{\n\t\treturn it.getFormalParameterAsString();\n\t}\n};\n&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%java.lang.String%&gt;&gt; _map_1 = &lt;%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;, &lt;%java.lang.String%&gt;&gt;map(_fpars, _function_1);\n&lt;%java.lang.String%&gt; _join_1 = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.join(_map_1, \", \");\n&lt;%java.lang.StringBuilder%&gt; _append_4 = _append_3.append(_join_1);\n_append_4.append(\")\");\n&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _returnTypeRef = this.getReturnTypeRef();\nboolean _tripleNotEquals = (_returnTypeRef != null);\nif (_tripleNotEquals)\n{\n\t&lt;%java.lang.StringBuilder%&gt; _append_5 = strb.append(\": \");\n\t&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _returnTypeRef_1 = this.getReturnTypeRef();\n\t&lt;%java.lang.String%&gt; _typeRefAsString = _returnTypeRef_1.getTypeRefAsString();\n\t_append_5.append(_typeRefAsString);\n}\nreturn strb.toString();'"
	 * @generated
	 */
	String getFunctionAsString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Overrides TMember's method
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getFunctionAsString();'"
	 * @generated
	 */
	String getMemberAsString();

} // TMethod
