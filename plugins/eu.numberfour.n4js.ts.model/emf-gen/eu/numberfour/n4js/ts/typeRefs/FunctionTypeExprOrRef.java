/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs;

import eu.numberfour.n4js.ts.types.TFormalParameter;
import eu.numberfour.n4js.ts.types.TFunction;
import eu.numberfour.n4js.ts.types.TypeVariable;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Type Expr Or Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for {@link FunctionTypeRef} and {@link FunctionTypeExpression}.
 * <!-- end-model-doc -->
 *
 *
 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getFunctionTypeExprOrRef()
 * @model abstract="true"
 * @generated
 */
public interface FunctionTypeExprOrRef extends StaticBaseTypeRef {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * optional thisType declaration e.g. for a given class A it's member-type can be specified as "{A.function():void}"
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	TypeRef getDeclaredThisType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns the declared type casted to a TFunction
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%eu.numberfour.n4js.ts.types.Type%&gt; _declaredType = this.getDeclaredType();\nreturn ((&lt;%eu.numberfour.n4js.ts.types.TFunction%&gt;) _declaredType);'"
	 * @generated
	 */
	TFunction getFunctionType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type variables as declared in the expression or of the referenced function type.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	EList<TypeVariable> getTypeVars();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the upper bounds of the given type variable (which should be one of those returned by
	 * {@link FunctionTypeExprOrRef#getTypeVars()}). Never read the upper bounds directly from the type variable in
	 * case of type variables coming from a FunctionTypeExprOrRef; for more info why this is required, see
	 * {@link FunctionTypeExpression#getTypeVarUpperBound(TypeVariable)}.
	 * <!-- end-model-doc -->
	 * @model unique="false" typeVarUnique="false"
	 * @generated
	 */
	TypeRef getTypeVarUpperBound(TypeVariable typeVar);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The formal parameters as declared in the expression or of the referenced function type.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	EList<TFormalParameter> getFpars();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The return type as declared in the expression or of the referenced function type.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	TypeRef getReturnTypeRef();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Overrides {@link TypeRef#isGeneric()}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt;&gt; _typeVars = this.getTypeVars();\nboolean _isEmpty = _typeVars.isEmpty();\nreturn (!_isEmpty);'"
	 * @generated
	 */
	boolean isGeneric();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Overrides {@link TypeRef#isRaw()}.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return (this.isGeneric() &amp;&amp; (this.getTypeArgs().size() &lt; this.getTypeVars().size()));'"
	 * @generated
	 */
	boolean isRaw();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns the formal parameter corresponding to the argument at index 'argIndex' in a function call
	 * or 'null' if 'argIndex' is invalid. This method takes into account optional and variadic parameters.
	 * <!-- end-model-doc -->
	 * @model unique="false" argIndexUnique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;&gt; _fpars = this.getFpars();\nfinal int fparsSize = _fpars.size();\nif (((argIndex &gt;= 0) &amp;&amp; (argIndex &lt; fparsSize)))\n{\n\t&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;&gt; _fpars_1 = this.getFpars();\n\treturn _fpars_1.get(argIndex);\n}\nelse\n{\n\tif ((((argIndex &gt;= fparsSize) &amp;&amp; (fparsSize &gt; 0)) &amp;&amp; this.getFpars().get((fparsSize - 1)).isVariadic()))\n\t{\n\t\t&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;&gt; _fpars_2 = this.getFpars();\n\t\treturn _fpars_2.get((fparsSize - 1));\n\t}\n}\nreturn null;'"
	 * @generated
	 */
	TFormalParameter getFparForArgIdx(int argIndex);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Overrides {@link TypeRef#getTypeRefAsString()}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.lang.String%&gt; _xifexpression = null;\n&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _declaredThisType = this.getDeclaredThisType();\nboolean _tripleNotEquals = (_declaredThisType != null);\nif (_tripleNotEquals)\n{\n\t&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _declaredThisType_1 = this.getDeclaredThisType();\n\t&lt;%java.lang.String%&gt; _typeRefAsString = _declaredThisType_1.getTypeRefAsString();\n\t&lt;%java.lang.String%&gt; _plus = (\"@This(\" + _typeRefAsString);\n\t_xifexpression = (_plus + \") \");\n}\nelse\n{\n\t_xifexpression = \"\";\n}\n&lt;%java.lang.String%&gt; _plus_1 = (\"{\" + _xifexpression);\n&lt;%java.lang.String%&gt; _plus_2 = (_plus_1 + \"function\");\n&lt;%java.lang.String%&gt; _xifexpression_1 = null;\nboolean _isGeneric = this.isGeneric();\nif (_isGeneric)\n{\n\t&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt;&gt; _typeVars = this.getTypeVars();\n\tfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt;, &lt;%java.lang.String%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt;, &lt;%java.lang.String%&gt;&gt;()\n\t{\n\t\tpublic &lt;%java.lang.String%&gt; apply(final &lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt; it)\n\t\t{\n\t\t\t&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _typeVarUpperBound = &lt;%this%&gt;.getTypeVarUpperBound(it);\n\t\t\treturn it.getTypeVariableAsString(_typeVarUpperBound);\n\t\t}\n\t};\n\t&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%java.lang.String%&gt;&gt; _map = &lt;%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.ts.types.TypeVariable%&gt;, &lt;%java.lang.String%&gt;&gt;map(_typeVars, _function);\n\t&lt;%java.lang.String%&gt; _join = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.join(_map, \",\");\n\t&lt;%java.lang.String%&gt; _plus_3 = (\"&lt;\" + _join);\n\t_xifexpression_1 = (_plus_3 + \"&gt;\");\n}\nelse\n{\n\t_xifexpression_1 = \"\";\n}\n&lt;%java.lang.String%&gt; _plus_4 = (_plus_2 + _xifexpression_1);\n&lt;%java.lang.String%&gt; _plus_5 = (_plus_4 + \"(\");\n&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;&gt; _fpars = this.getFpars();\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;, &lt;%java.lang.String%&gt;&gt; _function_1 = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;, &lt;%java.lang.String%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.String%&gt; apply(final &lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt; it)\n\t{\n\t\treturn it.getFormalParameterAsTypesString();\n\t}\n};\n&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%java.lang.String%&gt;&gt; _map_1 = &lt;%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.ts.types.TFormalParameter%&gt;, &lt;%java.lang.String%&gt;&gt;map(_fpars, _function_1);\n&lt;%java.lang.String%&gt; _join_1 = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.join(_map_1, \",\");\n&lt;%java.lang.String%&gt; _plus_6 = (_plus_5 + _join_1);\n&lt;%java.lang.String%&gt; _plus_7 = (_plus_6 + \")\");\n&lt;%java.lang.String%&gt; _xifexpression_2 = null;\n&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _returnTypeRef = this.getReturnTypeRef();\nboolean _tripleNotEquals_1 = (_returnTypeRef != null);\nif (_tripleNotEquals_1)\n{\n\t&lt;%eu.numberfour.n4js.ts.typeRefs.TypeRef%&gt; _returnTypeRef_1 = this.getReturnTypeRef();\n\t&lt;%java.lang.String%&gt; _typeRefAsString_1 = _returnTypeRef_1.getTypeRefAsString();\n\t_xifexpression_2 = (\":\" + _typeRefAsString_1);\n}\nelse\n{\n\t_xifexpression_2 = \"\";\n}\n&lt;%java.lang.String%&gt; _plus_8 = (_plus_7 + _xifexpression_2);\n&lt;%java.lang.String%&gt; _plus_9 = (_plus_8 + \"}\");\n&lt;%java.lang.String%&gt; _modifiersAsString = this.getModifiersAsString();\nreturn (_plus_9 + _modifiersAsString);'"
	 * @generated
	 */
	String getTypeRefAsString();

} // FunctionTypeExprOrRef
