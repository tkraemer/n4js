/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;

import java.lang.Iterable;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>N4 Classifier Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Abstract base class for n4 classifiers, that is types containing members such as fields or methods.
 * Note that not all types can contain any members, e.g., interfaces must not contain fields.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.N4ClassifierDefinition#getOwnedMembersRaw <em>Owned Members Raw</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4ClassifierDefinition()
 * @model abstract="true"
 * @generated
 */
public interface N4ClassifierDefinition extends N4TypeDefinition {
	/**
	 * Returns the value of the '<em><b>Owned Members Raw</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.n4JS.N4MemberDeclaration}.
	 * It is bidirectional and its opposite is '{@link eu.numberfour.n4js.n4JS.N4MemberDeclaration#getOwner <em>Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Members directly defined in this classifier, i.e. w/o inherited members.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Members Raw</em>' containment reference list.
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4ClassifierDefinition_OwnedMembersRaw()
	 * @see eu.numberfour.n4js.n4JS.N4MemberDeclaration#getOwner
	 * @model opposite="owner" containment="true"
	 * @generated
	 */
	EList<N4MemberDeclaration> getOwnedMembersRaw();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns a view on ownedMembersRaw filtering out non-methods.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt; _ownedMembersRaw = this.getOwnedMembersRaw();\n&lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt; _filter = &lt;%com.google.common.collect.Iterables%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt;filter(_ownedMembersRaw, &lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;.class);\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt; it)\n\t{\n\t\tboolean _isCallableConstructor = it.isCallableConstructor();\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf((!_isCallableConstructor));\n\t}\n};\nfinal &lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt; methods = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt;filter(_filter, _function);\n&lt;%java.util.List%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt; _list = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt;toList(methods);\nreturn new &lt;%org.eclipse.emf.common.util.BasicEList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt;(_list);'"
	 * @generated
	 */
	EList<N4MemberDeclaration> getOwnedMembers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns explicitly defined constructor of receiving class or <code>null</code> if none was defined.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt; _ownedMembersRaw = this.getOwnedMembersRaw();\n&lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt; _filter = &lt;%com.google.common.collect.Iterables%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt;filter(_ownedMembersRaw, &lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;.class);\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt; it)\n\t{\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf(it.isConstructor());\n\t}\n};\nreturn &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt;findFirst(_filter, _function);'"
	 * @generated
	 */
	N4MethodDeclaration getOwnedCtor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns explicitly defined callable constructor of receiving class or <code>null</code> if none was defined.
	 * This is *not* the actual constructor but instead the function used for direct invocations in call expressions.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt; _ownedMembersRaw = this.getOwnedMembersRaw();\n&lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt; _filter = &lt;%com.google.common.collect.Iterables%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt;filter(_ownedMembersRaw, &lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;.class);\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt; it)\n\t{\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf(it.isCallableConstructor());\n\t}\n};\nreturn &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt;findFirst(_filter, _function);'"
	 * @generated
	 */
	N4MethodDeclaration getOwnedCallableCtor();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns a view on ownedMembersRaw filtering out non-methods.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt; _ownedMembersRaw = this.getOwnedMembersRaw();\n&lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt; _filter = &lt;%com.google.common.collect.Iterables%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt;filter(_ownedMembersRaw, &lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;.class);\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt; it)\n\t{\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf(((!it.isConstructor()) &amp;&amp; (!it.isCallableConstructor())));\n\t}\n};\nfinal &lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt; methods = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt;filter(_filter, _function);\n&lt;%java.util.List%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt; _list = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt;toList(methods);\nreturn new &lt;%org.eclipse.emf.common.util.BasicEList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MethodDeclaration%&gt;&gt;(_list);'"
	 * @generated
	 */
	EList<N4MethodDeclaration> getOwnedMethods();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns a view on ownedMembersRaw filtering out non-fields.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt; _ownedMembersRaw = this.getOwnedMembersRaw();\nfinal &lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4FieldDeclaration%&gt;&gt; fields = &lt;%com.google.common.collect.Iterables%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4FieldDeclaration%&gt;&gt;filter(_ownedMembersRaw, &lt;%eu.numberfour.n4js.n4JS.N4FieldDeclaration%&gt;.class);\n&lt;%java.util.List%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4FieldDeclaration%&gt;&gt; _list = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4FieldDeclaration%&gt;&gt;toList(fields);\nreturn new &lt;%org.eclipse.emf.common.util.BasicEList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4FieldDeclaration%&gt;&gt;(_list);'"
	 * @generated
	 */
	EList<N4FieldDeclaration> getOwnedFields();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns a view on ownedMembersRaw filtering out non-getters.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt; _ownedMembersRaw = this.getOwnedMembersRaw();\nfinal &lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4GetterDeclaration%&gt;&gt; getters = &lt;%com.google.common.collect.Iterables%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4GetterDeclaration%&gt;&gt;filter(_ownedMembersRaw, &lt;%eu.numberfour.n4js.n4JS.N4GetterDeclaration%&gt;.class);\n&lt;%java.util.List%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4GetterDeclaration%&gt;&gt; _list = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4GetterDeclaration%&gt;&gt;toList(getters);\nreturn new &lt;%org.eclipse.emf.common.util.BasicEList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4GetterDeclaration%&gt;&gt;(_list);'"
	 * @generated
	 */
	EList<N4GetterDeclaration> getOwnedGetters();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns a view on ownedMembersRaw filtering out non-setters.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4MemberDeclaration%&gt;&gt; _ownedMembersRaw = this.getOwnedMembersRaw();\nfinal &lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4SetterDeclaration%&gt;&gt; setters = &lt;%com.google.common.collect.Iterables%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4SetterDeclaration%&gt;&gt;filter(_ownedMembersRaw, &lt;%eu.numberfour.n4js.n4JS.N4SetterDeclaration%&gt;.class);\n&lt;%java.util.List%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4SetterDeclaration%&gt;&gt; _list = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.N4SetterDeclaration%&gt;&gt;toList(setters);\nreturn new &lt;%org.eclipse.emf.common.util.BasicEList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4SetterDeclaration%&gt;&gt;(_list);'"
	 * @generated
	 */
	EList<N4SetterDeclaration> getOwnedSetters();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Derived, returns extended class (if any) and implemented or extended interfaces.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefIterable" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return &lt;%java.util.Collections%&gt;.&lt;&lt;%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%&gt;&gt;emptyList();'"
	 * @generated
	 */
	Iterable<ParameterizedTypeRef> getSuperClassifierRefs();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Derived, returns implemented or extended interfaces.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefIterable" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return &lt;%java.util.Collections%&gt;.&lt;&lt;%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%&gt;&gt;emptyList();'"
	 * @generated
	 */
	Iterable<ParameterizedTypeRef> getImplementedOrExtendedInterfaceRefs();

} // N4ClassifierDefinition
