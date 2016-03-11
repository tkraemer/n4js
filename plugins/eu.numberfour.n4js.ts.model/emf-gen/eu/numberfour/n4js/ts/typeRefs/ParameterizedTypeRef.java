/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs;

import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.TypingStrategy;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameterized Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * *
 * Type reference to declared types which may be parameterized. This is
 * probably the most often used type reference. This base version always
 * uses nominal typing on use site, there is a sub class
 * {@link ParameterizedTypeRefStructural} which support structural typing
 * on use site. However, the latter is not allowed everywhere.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef#getDeclaredType <em>Declared Type</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef#getTypeArgs <em>Type Args</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef#getDefinedTypingStrategy <em>Defined Typing Strategy</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getParameterizedTypeRef()
 * @model
 * @generated
 */
public interface ParameterizedTypeRef extends BaseTypeRef {
	/**
	 * Returns the value of the '<em><b>Declared Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Type</em>' reference.
	 * @see #setDeclaredType(Type)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getParameterizedTypeRef_DeclaredType()
	 * @model
	 * @generated
	 */
	Type getDeclaredType();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef#getDeclaredType <em>Declared Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Type</em>' reference.
	 * @see #getDeclaredType()
	 * @generated
	 */
	void setDeclaredType(Type value);

	/**
	 * Returns the value of the '<em><b>Type Args</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.ts.typeRefs.TypeArgument}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Args</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Args</em>' containment reference list.
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getParameterizedTypeRef_TypeArgs()
	 * @model containment="true"
	 * @generated
	 */
	EList<TypeArgument> getTypeArgs();

	/**
	 * Returns the value of the '<em><b>Defined Typing Strategy</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.numberfour.n4js.ts.types.TypingStrategy}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The defined (declared or inferred) typing strategy on use site, nominal typing by default. Could be changed to structural or structural
	 * field (via tilde or tilde-tilde operators).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Defined Typing Strategy</em>' attribute.
	 * @see eu.numberfour.n4js.ts.types.TypingStrategy
	 * @see #setDefinedTypingStrategy(TypingStrategy)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getParameterizedTypeRef_DefinedTypingStrategy()
	 * @model unique="false"
	 * @generated
	 */
	TypingStrategy getDefinedTypingStrategy();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef#getDefinedTypingStrategy <em>Defined Typing Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Defined Typing Strategy</em>' attribute.
	 * @see eu.numberfour.n4js.ts.types.TypingStrategy
	 * @see #getDefinedTypingStrategy()
	 * @generated
	 */
	void setDefinedTypingStrategy(TypingStrategy value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns the actual typing strategy, that is either the defined typing strategy, or the typing strategy of the declared type.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.types.TypingStrategy%> _definedTypingStrategy = this.getDefinedTypingStrategy();\nboolean _tripleEquals = (_definedTypingStrategy == <%eu.numberfour.n4js.ts.types.TypingStrategy%>.DEFAULT);\nif (_tripleEquals)\n{\n\tboolean _isDefSiteStructuralTyping = this.isDefSiteStructuralTyping();\n\tif (_isDefSiteStructuralTyping)\n\t{\n\t\treturn <%eu.numberfour.n4js.ts.types.TypingStrategy%>.STRUCTURAL;\n\t}\n\telse\n\t{\n\t\treturn <%eu.numberfour.n4js.ts.types.TypingStrategy%>.NOMINAL;\n\t}\n}\nreturn this.getDefinedTypingStrategy();'"
	 * @generated
	 */
	TypingStrategy getTypingStrategy();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  @see TypeArgument#containsWildcard()
	 * <!-- end-model-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _or = false;\nboolean _and = false;\n<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>> _typeArgs = this.getTypeArgs();\nboolean _isEmpty = _typeArgs.isEmpty();\nif (!_isEmpty)\n{\n\t_and = false;\n} else\n{\n\t<%eu.numberfour.n4js.ts.types.Type%> _declaredType = this.getDeclaredType();\n\tboolean _isGeneric = _declaredType.isGeneric();\n\tboolean _not = (!_isGeneric);\n\t_and = _not;\n}\nif (_and)\n{\n\t_or = true;\n} else\n{\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>> _typeArgs_1 = this.getTypeArgs();\n\tfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>, <%java.lang.Boolean%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>, <%java.lang.Boolean%>>()\n\t{\n\t\tpublic <%java.lang.Boolean%> apply(final <%eu.numberfour.n4js.ts.typeRefs.TypeArgument%> it)\n\t\t{\n\t\t\treturn <%java.lang.Boolean%>.valueOf(it.containsWildcards());\n\t\t}\n\t};\n\tboolean _exists = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.<<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>>exists(_typeArgs_1, _function);\n\t_or = _exists;\n}\nreturn _or;'"
	 * @generated
	 */
	boolean containsWildcards();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Overrides {@link TypeRef#getTypeRefAsString()}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.types.Type%> _declaredType = this.getDeclaredType();\n<%java.lang.String%> _rawTypeAsString = null;\nif (_declaredType!=null)\n{\n\t_rawTypeAsString=_declaredType.getRawTypeAsString();\n}\n<%java.lang.String%> _xifexpression = null;\n<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>> _typeArgs = this.getTypeArgs();\nboolean _isEmpty = _typeArgs.isEmpty();\nif (_isEmpty)\n{\n\t_xifexpression = \"\";\n}\nelse\n{\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>> _typeArgs_1 = this.getTypeArgs();\n\tfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>, <%java.lang.String%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>, <%java.lang.String%>>()\n\t{\n\t\tpublic <%java.lang.String%> apply(final <%eu.numberfour.n4js.ts.typeRefs.TypeArgument%> it)\n\t\t{\n\t\t\treturn it.getTypeRefAsString();\n\t\t}\n\t};\n\t<%org.eclipse.emf.common.util.EList%><<%java.lang.String%>> _map = <%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%>.<<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>, <%java.lang.String%>>map(_typeArgs_1, _function);\n\t<%java.lang.String%> _join = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.join(_map, \",\");\n\t<%java.lang.String%> _plus = (\"<\" + _join);\n\t_xifexpression = (_plus + \">\");\n}\n<%java.lang.String%> _plus_1 = (_rawTypeAsString + _xifexpression);\n<%java.lang.String%> _modifiersAsString = this.getModifiersAsString();\nreturn (_plus_1 + _modifiersAsString);'"
	 * @generated
	 */
	String getTypeRefAsString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns true if type reference contains type arguments.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>> _typeArgs = this.getTypeArgs();\nboolean _isEmpty = _typeArgs.isEmpty();\nreturn (!_isEmpty);'"
	 * @generated
	 */
	boolean isParameterized();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Delegates to {@link Type.isGeneric()}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _and = false;\n<%eu.numberfour.n4js.ts.types.Type%> _declaredType = this.getDeclaredType();\nboolean _tripleNotEquals = (_declaredType != null);\nif (!_tripleNotEquals)\n{\n\t_and = false;\n} else\n{\n\t<%eu.numberfour.n4js.ts.types.Type%> _declaredType_1 = this.getDeclaredType();\n\tboolean _isGeneric = _declaredType_1.isGeneric();\n\t_and = _isGeneric;\n}\nreturn _and;'"
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _and = false;\nboolean _isGeneric = this.isGeneric();\nif (!_isGeneric)\n{\n\t_and = false;\n} else\n{\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>> _typeArgs = this.getTypeArgs();\n\tint _size = _typeArgs.size();\n\t<%eu.numberfour.n4js.ts.types.Type%> _declaredType = this.getDeclaredType();\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TypeVariable%>> _typeVars = _declaredType.getTypeVars();\n\tint _size_1 = _typeVars.size();\n\tboolean _lessThan = (_size < _size_1);\n\t_and = _lessThan;\n}\nreturn _and;'"
	 * @generated
	 */
	boolean isRaw();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @see TypeArgument#containsUnboundTypeVariables()
	 * <!-- end-model-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _or = false;\nboolean _or_1 = false;\n<%eu.numberfour.n4js.ts.types.Type%> _declaredType = this.getDeclaredType();\nif ((_declaredType instanceof <%eu.numberfour.n4js.ts.types.TypeVariable%>))\n{\n\t_or_1 = true;\n} else\n{\n\tboolean _and = false;\n\tboolean _isParameterized = this.isParameterized();\n\tboolean _not = (!_isParameterized);\n\tif (!_not)\n\t{\n\t\t_and = false;\n\t} else\n\t{\n\t\t<%eu.numberfour.n4js.ts.types.Type%> _declaredType_1 = this.getDeclaredType();\n\t\tboolean _isGeneric = _declaredType_1.isGeneric();\n\t\t_and = _isGeneric;\n\t}\n\t_or_1 = _and;\n}\nif (_or_1)\n{\n\t_or = true;\n} else\n{\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>> _typeArgs = this.getTypeArgs();\n\tfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>, <%java.lang.Boolean%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>, <%java.lang.Boolean%>>()\n\t{\n\t\tpublic <%java.lang.Boolean%> apply(final <%eu.numberfour.n4js.ts.typeRefs.TypeArgument%> it)\n\t\t{\n\t\t\treturn <%java.lang.Boolean%>.valueOf(it.containsUnboundTypeVariables());\n\t\t}\n\t};\n\tboolean _exists = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.<<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>>exists(_typeArgs, _function);\n\t_or = _exists;\n}\nreturn _or;'"
	 * @generated
	 */
	boolean containsUnboundTypeVariables();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns true if typingStrategy is neither NOMINAL nor DEFAULT, and if is is not
	 * definition site.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _and = false;\n<%eu.numberfour.n4js.ts.types.TypingStrategy%> _definedTypingStrategy = this.getDefinedTypingStrategy();\nboolean _tripleNotEquals = (_definedTypingStrategy != <%eu.numberfour.n4js.ts.types.TypingStrategy%>.NOMINAL);\nif (!_tripleNotEquals)\n{\n\t_and = false;\n} else\n{\n\t<%eu.numberfour.n4js.ts.types.TypingStrategy%> _definedTypingStrategy_1 = this.getDefinedTypingStrategy();\n\tboolean _tripleNotEquals_1 = (_definedTypingStrategy_1 != <%eu.numberfour.n4js.ts.types.TypingStrategy%>.DEFAULT);\n\t_and = _tripleNotEquals_1;\n}\nreturn _and;'"
	 * @generated
	 */
	boolean isUseSiteStructuralTyping();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns true if typingStrategy of the declared type is STRUCTURAL or in case of Object literals.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.types.Type%> _declaredType = this.getDeclaredType();\nif ((_declaredType instanceof <%eu.numberfour.n4js.ts.types.TN4Classifier%>))\n{\n\t<%eu.numberfour.n4js.ts.types.Type%> _declaredType_1 = this.getDeclaredType();\n\t<%eu.numberfour.n4js.ts.types.TypingStrategy%> _typingStrategy = ((<%eu.numberfour.n4js.ts.types.TN4Classifier%>) _declaredType_1).getTypingStrategy();\n\treturn (_typingStrategy == <%eu.numberfour.n4js.ts.types.TypingStrategy%>.STRUCTURAL);\n}\n<%eu.numberfour.n4js.ts.types.Type%> _declaredType_2 = this.getDeclaredType();\nif ((_declaredType_2 instanceof <%eu.numberfour.n4js.ts.types.TStructuralType%>))\n{\n\treturn true;\n}\nreturn false;'"
	 * @generated
	 */
	boolean isDefSiteStructuralTyping();

} // ParameterizedTypeRef
