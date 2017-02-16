/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.typeRefs;

import eu.numberfour.n4js.ts.types.NullModifier;
import eu.numberfour.n4js.ts.types.TStructMember;
import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.TypingStrategy;
import eu.numberfour.n4js.ts.types.UndefModifier;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for all type references
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.TypeRef#getNullModifier <em>Null Modifier</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.typeRefs.TypeRef#getUndefModifier <em>Undef Modifier</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getTypeRef()
 * @model abstract="true"
 * @generated
 */
public interface TypeRef extends TypeArgument, Versionable {
	/**
	 * Returns the value of the '<em><b>Null Modifier</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.numberfour.n4js.ts.types.NullModifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Null Modifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Null Modifier</em>' attribute.
	 * @see eu.numberfour.n4js.ts.types.NullModifier
	 * @see #setNullModifier(NullModifier)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getTypeRef_NullModifier()
	 * @model unique="false"
	 * @generated
	 */
	NullModifier getNullModifier();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.TypeRef#getNullModifier <em>Null Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Null Modifier</em>' attribute.
	 * @see eu.numberfour.n4js.ts.types.NullModifier
	 * @see #getNullModifier()
	 * @generated
	 */
	void setNullModifier(NullModifier value);

	/**
	 * Returns the value of the '<em><b>Undef Modifier</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.numberfour.n4js.ts.types.UndefModifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Undef Modifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Undef Modifier</em>' attribute.
	 * @see eu.numberfour.n4js.ts.types.UndefModifier
	 * @see #setUndefModifier(UndefModifier)
	 * @see eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage#getTypeRef_UndefModifier()
	 * @model unique="false"
	 * @generated
	 */
	UndefModifier getUndefModifier();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.typeRefs.TypeRef#getUndefModifier <em>Undef Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Undef Modifier</em>' attribute.
	 * @see eu.numberfour.n4js.ts.types.UndefModifier
	 * @see #getUndefModifier()
	 * @generated
	 */
	void setUndefModifier(UndefModifier value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns null and undef modifier strings similar to N4JS syntax, not all values are represented.
	 * This method is called by subclasses in overridden method.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.lang.String%&gt; _switchResult = null;\n&lt;%eu.numberfour.n4js.ts.types.NullModifier%&gt; _nullModifier = this.getNullModifier();\nif (_nullModifier != null)\n{\n\tswitch (_nullModifier)\n\t{\n\t\tcase NA:\n\t\t\t_switchResult = \"\";\n\t\t\tbreak;\n\t\tcase NULLABLE:\n\t\t\t_switchResult = \"\";\n\t\t\tbreak;\n\t\tcase NOTNULL:\n\t\t\t_switchResult = \"!\";\n\t\t\tbreak;\n\t\tcase ISNULL:\n\t\t\t_switchResult = \"\";\n\t\t\tbreak;\n\t\tdefault:\n\t\t\tbreak;\n\t}\n}\n&lt;%java.lang.String%&gt; _switchResult_1 = null;\n&lt;%eu.numberfour.n4js.ts.types.UndefModifier%&gt; _undefModifier = this.getUndefModifier();\nif (_undefModifier != null)\n{\n\tswitch (_undefModifier)\n\t{\n\t\tcase NA:\n\t\t\t_switchResult_1 = \"\";\n\t\t\tbreak;\n\t\tcase MANDATORY:\n\t\t\t_switchResult_1 = \"\";\n\t\t\tbreak;\n\t\tcase OPTIONAL:\n\t\t\t_switchResult_1 = \"?\";\n\t\t\tbreak;\n\t\tcase ISUNDEFINED:\n\t\t\t_switchResult_1 = \"\";\n\t\t\tbreak;\n\t\tdefault:\n\t\t\tbreak;\n\t}\n}\nreturn (_switchResult + _switchResult_1);'"
	 * @generated
	 */
	String getModifiersAsString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns true if the type referenced is either class which is declared final, an enum or a primitive. This is needed in case of type
	 * casts, for example.
	 * <p>
	 * The method is robust, if declared type is null, false is returned.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final &lt;%eu.numberfour.n4js.ts.types.Type%&gt; dtype = this.getDeclaredType();\nif ((dtype == null))\n{\n\treturn false;\n}\nreturn dtype.isFinal();'"
	 * @generated
	 */
	boolean isFinalByType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, flag indicating whether user can add properties to the instance not defined in the type.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isDynamic();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, flag indicating whether the type reference is an existential type created on the fly
	 * by the type inferencer from parameterized types with wildcards.
	 * This returns false for all type references except {@link ExistentialTypeRef}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isExistential();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns true if and only if the referenced type is generic, i.e. declared type variables.
	 * Note that it may be possible that the referenced type is generic, but the reference is not parameterized
	 * (raw reference), but not vice versa.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isGeneric();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns true if and only if the reference has type arguments. Note that it may be possible that
	 * the referenced type is generic, but the reference is not parameterized (raw reference), but not vice versa.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isParameterized();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns true if and only if the referenced type is generic <em>and</em> the reference has
	 * fewer type arguments than the number of type parameters of the referenced type.<p>
	 * Note that N4JS does not allow raw type references as in Java, for example. However, raw type references can
	 * occur due to (1) a broken AST (2) during type inference in InferenceContext (when deriving constraints from
	 * ClassifierTypeRefs) and (3) bugs in the type system and other internal code.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isRaw();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method to avoid type casts, does return null except type ref is a wildcard.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return null;'"
	 * @generated
	 */
	ParameterizedTypeRef getDeclaredUpperBound();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method to avoid type casts, does return null except type ref is a wildcard.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return null;'"
	 * @generated
	 */
	ParameterizedTypeRef getDeclaredLowerBound();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method to avoid type casts, does return unmodifiable empty list for all type references except parameterized type refs.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return &lt;%org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals%&gt;.&lt;&lt;%eu.numberfour.n4js.ts.typeRefs.TypeArgument%&gt;&gt;emptyEList();'"
	 * @generated
	 */
	EList<TypeArgument> getTypeArgs();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return (&lt;%com.google.common.base.Objects%&gt;.equal(this.getNullModifier(), &lt;%eu.numberfour.n4js.ts.types.NullModifier%&gt;.ISNULL) || &lt;%com.google.common.base.Objects%&gt;.equal(this.getUndefModifier(), &lt;%eu.numberfour.n4js.ts.types.UndefModifier%&gt;.ISUNDEFINED));'"
	 * @generated
	 */
	boolean undefinedOrNull();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns the type expression, usually the type name, as a string. The
	 * returned string representation usually reflect the N4JS syntax. Basically used for testing.
	 * As the returned string is used for comparison in tests, this method should not be changed.
	 * This method actually overrides {@link TypeArgument#getTypeRefAsString()}.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return null;'"
	 * @generated
	 */
	String getTypeRefAsString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getTypeRefAsString();'"
	 * @generated
	 */
	String toString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Convenience method, returns null for all type references except parameterized type references.
	 * Minimizes number cases in client code.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return null;'"
	 * @generated
	 */
	Type getDeclaredType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  Convenience method, returns only true for parameterized type refs if the declared type is any
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%eu.numberfour.n4js.ts.types.Type%&gt; _declaredType = this.getDeclaredType();\nreturn (_declaredType instanceof &lt;%eu.numberfour.n4js.ts.types.AnyType%&gt;);'"
	 * @generated
	 */
	boolean isTopType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  Convenience method, returns only true for parameterized type refs if the declared type is undefined
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%eu.numberfour.n4js.ts.types.Type%&gt; _declaredType = this.getDeclaredType();\nreturn (_declaredType instanceof &lt;%eu.numberfour.n4js.ts.types.UndefinedType%&gt;);'"
	 * @generated
	 */
	boolean isBottomType();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns the typing strategy, either the use or def site, usually NOMINAL.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return &lt;%eu.numberfour.n4js.ts.types.TypingStrategy%&gt;.NOMINAL;'"
	 * @generated
	 */
	TypingStrategy getTypingStrategy();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Convenience method, returns empty list for simply reference by default.
	 * Overridden in {@link ParameterizedTypeRefStructural}
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return &lt;%org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals%&gt;.&lt;&lt;%eu.numberfour.n4js.ts.types.TStructMember%&gt;&gt;emptyEList();'"
	 * @generated
	 */
	EList<TStructMember> getStructuralMembers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns true if type ref is structural. This is different from {@link #getTypingStrategy}, which
	 * returns true if either use or def site structural typing is true.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isUseSiteStructuralTyping();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns true if type is defined structurally defined. This is different from {@link #getTypingStrategy}, which
	 * returns true if either use or def site structural typing is true.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isDefSiteStructuralTyping();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Override Versioned#getVersion()
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='int _xifexpression = (int) 0;\n&lt;%eu.numberfour.n4js.ts.types.Type%&gt; _declaredType = this.getDeclaredType();\nboolean _tripleNotEquals = (_declaredType != null);\nif (_tripleNotEquals)\n{\n\t&lt;%eu.numberfour.n4js.ts.types.Type%&gt; _declaredType_1 = this.getDeclaredType();\n\t_xifexpression = _declaredType_1.getVersion();\n}\nelse\n{\n\t_xifexpression = 0;\n}\nreturn _xifexpression;'"
	 * @generated
	 */
	int getVersion();

} // TypeRef
