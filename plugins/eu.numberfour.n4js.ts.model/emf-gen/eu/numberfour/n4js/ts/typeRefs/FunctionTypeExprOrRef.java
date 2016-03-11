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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.types.Type%> _declaredType = this.getDeclaredType();\nreturn ((<%eu.numberfour.n4js.ts.types.TFunction%>) _declaredType);'"
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
	 * {@link FunctionTypeExpression#getTypeVarUpperBounds(TypeVariable)}.
	 * <!-- end-model-doc -->
	 * @model unique="false" typeVarUnique="false"
	 * @generated
	 */
	EList<TypeRef> getTypeVarUpperBounds(TypeVariable typeVar);

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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TypeVariable%>> _typeVars = this.getTypeVars();\nboolean _isEmpty = _typeVars.isEmpty();\nreturn (!_isEmpty);'"
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _and = false;\nboolean _isGeneric = this.isGeneric();\nif (!_isGeneric)\n{\n\t_and = false;\n} else\n{\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>> _typeArgs = this.getTypeArgs();\n\tint _size = _typeArgs.size();\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TypeVariable%>> _typeVars = this.getTypeVars();\n\tint _size_1 = _typeVars.size();\n\tboolean _lessThan = (_size < _size_1);\n\t_and = _lessThan;\n}\nreturn _and;'"
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>> _fpars = this.getFpars();\nfinal int fparsSize = _fpars.size();\nif (((argIndex >= 0) && (argIndex < fparsSize)))\n{\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>> _fpars_1 = this.getFpars();\n\treturn _fpars_1.get(argIndex);\n}\nelse\n{\n\tboolean _and = false;\n\tif (!((argIndex >= fparsSize) && (fparsSize > 0)))\n\t{\n\t\t_and = false;\n\t} else\n\t{\n\t\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>> _fpars_2 = this.getFpars();\n\t\t<%eu.numberfour.n4js.ts.types.TFormalParameter%> _get = _fpars_2.get((fparsSize - 1));\n\t\tboolean _isVariadic = _get.isVariadic();\n\t\t_and = _isVariadic;\n\t}\n\tif (_and)\n\t{\n\t\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>> _fpars_3 = this.getFpars();\n\t\treturn _fpars_3.get((fparsSize - 1));\n\t}\n}\nreturn null;'"
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _xifexpression = null;\n<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _declaredThisType = this.getDeclaredThisType();\nboolean _tripleNotEquals = (_declaredThisType != null);\nif (_tripleNotEquals)\n{\n\t<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _declaredThisType_1 = this.getDeclaredThisType();\n\t<%java.lang.String%> _typeRefAsString = _declaredThisType_1.getTypeRefAsString();\n\t<%java.lang.String%> _plus = (\"@This(\" + _typeRefAsString);\n\t_xifexpression = (_plus + \") \");\n}\nelse\n{\n\t_xifexpression = \"\";\n}\n<%java.lang.String%> _plus_1 = (\"{\" + _xifexpression);\n<%java.lang.String%> _plus_2 = (_plus_1 + \"function\");\n<%java.lang.String%> _xifexpression_1 = null;\nboolean _isGeneric = this.isGeneric();\nif (_isGeneric)\n{\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TypeVariable%>> _typeVars = this.getTypeVars();\n\tfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.types.TypeVariable%>, <%java.lang.String%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.types.TypeVariable%>, <%java.lang.String%>>()\n\t{\n\t\tpublic <%java.lang.String%> apply(final <%eu.numberfour.n4js.ts.types.TypeVariable%> it)\n\t\t{\n\t\t\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>> _typeVarUpperBounds = <%this%>.getTypeVarUpperBounds(it);\n\t\t\treturn it.getTypeVariableAsString(_typeVarUpperBounds);\n\t\t}\n\t};\n\t<%org.eclipse.emf.common.util.EList%><<%java.lang.String%>> _map = <%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%>.<<%eu.numberfour.n4js.ts.types.TypeVariable%>, <%java.lang.String%>>map(_typeVars, _function);\n\t<%java.lang.String%> _join = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.join(_map, \",\");\n\t<%java.lang.String%> _plus_3 = (\"<\" + _join);\n\t_xifexpression_1 = (_plus_3 + \">\");\n}\nelse\n{\n\t_xifexpression_1 = \"\";\n}\n<%java.lang.String%> _plus_4 = (_plus_2 + _xifexpression_1);\n<%java.lang.String%> _plus_5 = (_plus_4 + \"(\");\n<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>> _fpars = this.getFpars();\nfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>, <%java.lang.String%>> _function_1 = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.types.TFormalParameter%>, <%java.lang.String%>>()\n{\n\tpublic <%java.lang.String%> apply(final <%eu.numberfour.n4js.ts.types.TFormalParameter%> it)\n\t{\n\t\t<%java.lang.String%> _xifexpression = null;\n\t\t<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _typeRef = it.getTypeRef();\n\t\tboolean _tripleNotEquals = (_typeRef != null);\n\t\tif (_tripleNotEquals)\n\t\t{\n\t\t\t<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _typeRef_1 = it.getTypeRef();\n\t\t\t_xifexpression = _typeRef_1.getTypeRefAsString();\n\t\t}\n\t\telse\n\t\t{\n\t\t\t_xifexpression = \"null\";\n\t\t}\n\t\treturn _xifexpression;\n\t}\n};\n<%org.eclipse.emf.common.util.EList%><<%java.lang.String%>> _map_1 = <%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%>.<<%eu.numberfour.n4js.ts.types.TFormalParameter%>, <%java.lang.String%>>map(_fpars, _function_1);\n<%java.lang.String%> _join_1 = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.join(_map_1, \",\");\n<%java.lang.String%> _plus_6 = (_plus_5 + _join_1);\n<%java.lang.String%> _plus_7 = (_plus_6 + \")\");\n<%java.lang.String%> _xifexpression_2 = null;\n<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _returnTypeRef = this.getReturnTypeRef();\nboolean _tripleNotEquals_1 = (_returnTypeRef != null);\nif (_tripleNotEquals_1)\n{\n\t<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _returnTypeRef_1 = this.getReturnTypeRef();\n\t<%java.lang.String%> _typeRefAsString_1 = _returnTypeRef_1.getTypeRefAsString();\n\t_xifexpression_2 = (\":\" + _typeRefAsString_1);\n}\nelse\n{\n\t_xifexpression_2 = \"\";\n}\n<%java.lang.String%> _plus_8 = (_plus_7 + _xifexpression_2);\n<%java.lang.String%> _plus_9 = (_plus_8 + \"}\");\n<%java.lang.String%> _modifiersAsString = this.getModifiersAsString();\nreturn (_plus_9 + _modifiersAsString);'"
	 * @generated
	 */
	String getTypeRefAsString();

} // FunctionTypeExprOrRef
