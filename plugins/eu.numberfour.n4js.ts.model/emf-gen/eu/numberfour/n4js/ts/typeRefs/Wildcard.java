/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wildcard</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Note: Wildcard has special copy-semantics to preserve the implicit upper bound returned by method
 * {@link Wildcard#getDeclaredOrImplicitUpperBounds()}. When copied, the implicit upper bound is set
 * as declared upper bound in the copy. For details see TypeUtils#copy().
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.Wildcard#getDeclaredUpperBound <em>Declared Upper Bound</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.Wildcard#getDeclaredLowerBound <em>Declared Lower Bound</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getWildcard()
 * @model
 * @generated
 */
public interface Wildcard extends TypeArgument {
	/**
	 * Returns the value of the '<em><b>Declared Upper Bound</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Upper Bound</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Upper Bound</em>' containment reference.
	 * @see #setDeclaredUpperBound(TypeRef)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getWildcard_DeclaredUpperBound()
	 * @model containment="true"
	 * @generated
	 */
	TypeRef getDeclaredUpperBound();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.Wildcard#getDeclaredUpperBound <em>Declared Upper Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Upper Bound</em>' containment reference.
	 * @see #getDeclaredUpperBound()
	 * @generated
	 */
	void setDeclaredUpperBound(TypeRef value);

	/**
	 * Returns the value of the '<em><b>Declared Lower Bound</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Lower Bound</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Lower Bound</em>' containment reference.
	 * @see #setDeclaredLowerBound(TypeRef)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getWildcard_DeclaredLowerBound()
	 * @model containment="true"
	 * @generated
	 */
	TypeRef getDeclaredLowerBound();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.Wildcard#getDeclaredLowerBound <em>Declared Lower Bound</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Lower Bound</em>' containment reference.
	 * @see #getDeclaredLowerBound()
	 * @generated
	 */
	void setDeclaredLowerBound(TypeRef value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An unbounded wildcard given as type argument to a bounded type parameter is implicitly bounded.
	 * For example:
	 * <pre>
	 * class A {}
	 * class G&lt;T extends A> {}
	 * 	 * var G&lt;?> g; // "?" is actually "? extends A"
	 * class H extends G&lt;?> {} // "?" is actually "? extends A"
	 * </pre>
	 * This method returns the declared upper bound of the receiving wildcard or, if the wildcard is unbounded,
	 * tries to obtain the implicit upper bound from the corresponding type parameter of the containing
	 * ParameterizedTypeRef's declared type (if any).
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final <%eu.numberfour.n4js.ts.typeRefs.TypeRef%> declUB = this.getDeclaredUpperBound();\nif ((declUB != null))\n{\n\treturn <%org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals%>.<<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>>newImmutableEList(declUB);\n}\n<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _declaredLowerBound = this.getDeclaredLowerBound();\nboolean _tripleNotEquals = (_declaredLowerBound != null);\nif (_tripleNotEquals)\n{\n\treturn <%org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals%>.<<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>>emptyEList();\n}\nfinal <%org.eclipse.emf.ecore.EObject%> parent = this.eContainer();\nif ((parent instanceof <%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>))\n{\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeArgument%>> _typeArgs = ((<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>)parent).getTypeArgs();\n\tfinal int typeArgIndex = _typeArgs.indexOf(this);\n\tif ((typeArgIndex >= 0))\n\t{\n\t\t<%org.eclipse.emf.ecore.EReference%> _parameterizedTypeRef_DeclaredType = <%eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage%>.eINSTANCE.getParameterizedTypeRef_DeclaredType();\n\t\tfinal <%java.lang.Object%> declType = ((<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>)parent).eGet(_parameterizedTypeRef_DeclaredType, false);\n\t\tif ((declType instanceof <%eu.numberfour.n4js.ts.types.ContainerType%><?>))\n\t\t{\n\t\t\tboolean _eIsProxy = ((<%eu.numberfour.n4js.ts.types.ContainerType%><?>)declType).eIsProxy();\n\t\t\tboolean _not = (!_eIsProxy);\n\t\t\tif (_not)\n\t\t\t{\n\t\t\t\tfinal <%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.types.TypeVariable%>> typeVars = ((<%eu.numberfour.n4js.ts.types.ContainerType%><?>)declType).getTypeVars();\n\t\t\t\t<%eu.numberfour.n4js.ts.types.TypeVariable%> _xifexpression = null;\n\t\t\t\tint _size = typeVars.size();\n\t\t\t\tboolean _lessThan = (typeArgIndex < _size);\n\t\t\t\tif (_lessThan)\n\t\t\t\t{\n\t\t\t\t\t_xifexpression = typeVars.get(typeArgIndex);\n\t\t\t\t}\n\t\t\t\telse\n\t\t\t\t{\n\t\t\t\t\t_xifexpression = null;\n\t\t\t\t}\n\t\t\t\tfinal <%eu.numberfour.n4js.ts.types.TypeVariable%> typeVar = _xifexpression;\n\t\t\t\tif ((typeVar != null))\n\t\t\t\t{\n\t\t\t\t\tfinal <%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>> implicitUBs = typeVar.getDeclaredUpperBounds();\n\t\t\t\t\treturn <%org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals%>.<<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>>newImmutableEList(((<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>[])org.eclipse.xtext.xbase.lib.Conversions.unwrapArray(implicitUBs, <%eu.numberfour.n4js.ts.typeRefs.TypeRef%>.class)));\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t}\n}\nreturn <%org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals%>.<<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>>emptyEList();'"
	 * @generated
	 */
	EList<TypeRef> getDeclaredOrImplicitUpperBounds();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns the type expression, usually the type name, as a string. Basically used for testing.
	 * See {@link TypeRef#getTypeRefAsString()}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final <%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>> upperBounds = this.getDeclaredOrImplicitUpperBounds();\n<%java.lang.String%> _xifexpression = null;\nboolean _isEmpty = upperBounds.isEmpty();\nboolean _not = (!_isEmpty);\nif (_not)\n{\n\tfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>, <%java.lang.String%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>, <%java.lang.String%>>()\n\t{\n\t\tpublic <%java.lang.String%> apply(final <%eu.numberfour.n4js.ts.typeRefs.TypeRef%> it)\n\t\t{\n\t\t\treturn it.getTypeRefAsString();\n\t\t}\n\t};\n\t<%org.eclipse.emf.common.util.EList%><<%java.lang.String%>> _map = <%org.eclipse.emf.ecore.xcore.lib.XcoreEListExtensions%>.<<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>, <%java.lang.String%>>map(upperBounds, _function);\n\t<%java.lang.String%> _join = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.join(_map, \" & \");\n\t_xifexpression = (\" extends \" + _join);\n}\nelse\n{\n\t<%java.lang.String%> _xifexpression_1 = null;\n\t<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _declaredLowerBound = this.getDeclaredLowerBound();\n\tboolean _tripleNotEquals = (_declaredLowerBound != null);\n\tif (_tripleNotEquals)\n\t{\n\t\t<%eu.numberfour.n4js.ts.typeRefs.TypeRef%> _declaredLowerBound_1 = this.getDeclaredLowerBound();\n\t\t<%java.lang.String%> _typeRefAsString = _declaredLowerBound_1.getTypeRefAsString();\n\t\t_xifexpression_1 = (\" super \" + _typeRefAsString);\n\t}\n\telse\n\t{\n\t\t_xifexpression_1 = \"\";\n\t}\n\t_xifexpression = _xifexpression_1;\n}\nreturn (\"?\" + _xifexpression);'"
	 * @generated
	 */
	String getTypeRefAsString();

} // Wildcard
