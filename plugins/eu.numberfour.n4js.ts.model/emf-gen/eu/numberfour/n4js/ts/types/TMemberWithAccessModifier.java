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
 * A representation of the model object '<em><b>TMember With Access Modifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * *
 * Base class for members with declared access modifiers.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.TMemberWithAccessModifier#isHasNoBody <em>Has No Body</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TMemberWithAccessModifier#getDeclaredMemberAccessModifier <em>Declared Member Access Modifier</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTMemberWithAccessModifier()
 * @model abstract="true"
 * @generated
 */
public interface TMemberWithAccessModifier extends TMember {
	/**
	 * Returns the value of the '<em><b>Has No Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * * TODO mover to TMethod and accessors (not done yet due to 500 named elements restriction)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Has No Body</em>' attribute.
	 * @see #setHasNoBody(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTMemberWithAccessModifier_HasNoBody()
	 * @model unique="false"
	 * @generated
	 */
	boolean isHasNoBody();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TMemberWithAccessModifier#isHasNoBody <em>Has No Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has No Body</em>' attribute.
	 * @see #isHasNoBody()
	 * @generated
	 */
	void setHasNoBody(boolean value);

	/**
	 * Returns the value of the '<em><b>Declared Member Access Modifier</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.numberfour.n4js.ts.types.MemberAccessModifier}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Member Access Modifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Member Access Modifier</em>' attribute.
	 * @see eu.numberfour.n4js.ts.types.MemberAccessModifier
	 * @see #setDeclaredMemberAccessModifier(MemberAccessModifier)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTMemberWithAccessModifier_DeclaredMemberAccessModifier()
	 * @model unique="false"
	 * @generated
	 */
	MemberAccessModifier getDeclaredMemberAccessModifier();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TMemberWithAccessModifier#getDeclaredMemberAccessModifier <em>Declared Member Access Modifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Member Access Modifier</em>' attribute.
	 * @see eu.numberfour.n4js.ts.types.MemberAccessModifier
	 * @see #getDeclaredMemberAccessModifier()
	 * @generated
	 */
	void setDeclaredMemberAccessModifier(MemberAccessModifier value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns either the declared access modifier (if possible) or
	 * derives the access modifier from the type access modifier of the containing type.
	 * <p>
	 * Note that the modifier is calculated in case no declared modifier is present, but it is not "fixed" (
	 * cf. eu.numberfour.n4js.ts.model.util.AccessModifiers.fixed(TMember)).
	 * Also see [N4JSSpec] Constraints 2 (Default Member Access Modifiers)
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.types.MemberAccessModifier%> _declaredMemberAccessModifier = this.getDeclaredMemberAccessModifier();\nboolean _tripleEquals = (_declaredMemberAccessModifier == <%eu.numberfour.n4js.ts.types.MemberAccessModifier%>.UNDEFINED);\nif (_tripleEquals)\n{\n\tfinal <%org.eclipse.emf.ecore.EObject%> parent = this.eContainer();\n\tif ((parent instanceof <%eu.numberfour.n4js.ts.types.TInterface%>))\n\t{\n\t\t<%eu.numberfour.n4js.ts.types.TypeAccessModifier%> _typeAccessModifier = ((<%eu.numberfour.n4js.ts.types.Type%>) parent).getTypeAccessModifier();\n\t\tfinal <%eu.numberfour.n4js.ts.types.MemberAccessModifier%> modifierDerivedFromType = <%eu.numberfour.n4js.ts.types.util.AccessModifiers%>.toMemberModifier(_typeAccessModifier);\n\t\tif ((modifierDerivedFromType != <%eu.numberfour.n4js.ts.types.MemberAccessModifier%>.PRIVATE))\n\t\t{\n\t\t\treturn modifierDerivedFromType;\n\t\t}\n\t}\n\treturn <%eu.numberfour.n4js.ts.types.MemberAccessModifier%>.PROJECT;\n}\nreturn this.getDeclaredMemberAccessModifier();'"
	 * @generated
	 */
	MemberAccessModifier getMemberAccessModifier();

} // TMemberWithAccessModifier
