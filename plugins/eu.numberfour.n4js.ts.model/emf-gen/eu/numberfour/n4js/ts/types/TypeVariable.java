/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;

import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;

import eu.numberfour.n4js.ts.types.util.Variance;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.TypeVariable#isDeclaredCovariant <em>Declared Covariant</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TypeVariable#isDeclaredContravariant <em>Declared Contravariant</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TypeVariable#getDeclaredUpperBounds <em>Declared Upper Bounds</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTypeVariable()
 * @model
 * @generated
 */
public interface TypeVariable extends Type {
	/**
	 * Returns the value of the '<em><b>Declared Covariant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Covariant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Covariant</em>' attribute.
	 * @see #setDeclaredCovariant(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTypeVariable_DeclaredCovariant()
	 * @model unique="false"
	 * @generated
	 */
	boolean isDeclaredCovariant();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TypeVariable#isDeclaredCovariant <em>Declared Covariant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Covariant</em>' attribute.
	 * @see #isDeclaredCovariant()
	 * @generated
	 */
	void setDeclaredCovariant(boolean value);

	/**
	 * Returns the value of the '<em><b>Declared Contravariant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Contravariant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Contravariant</em>' attribute.
	 * @see #setDeclaredContravariant(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTypeVariable_DeclaredContravariant()
	 * @model unique="false"
	 * @generated
	 */
	boolean isDeclaredContravariant();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TypeVariable#isDeclaredContravariant <em>Declared Contravariant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Contravariant</em>' attribute.
	 * @see #isDeclaredContravariant()
	 * @generated
	 */
	void setDeclaredContravariant(boolean value);

	/**
	 * Returns the value of the '<em><b>Declared Upper Bounds</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declared Upper Bounds</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declared Upper Bounds</em>' containment reference list.
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTypeVariable_DeclaredUpperBounds()
	 * @model containment="true"
	 * @generated
	 */
	EList<ParameterizedTypeRef> getDeclaredUpperBounds();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns this type variable's {@link eu.numberfour.n4js.ts.types.util.Variance variance}. Always returns
	 * invariant, unless the type variable was explicitly declared on definition site to be co- or contravariant.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.ts.types.Variance" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final boolean co = this.isDeclaredCovariant();\nfinal boolean contra = this.isDeclaredContravariant();\nif ((co && (!contra)))\n{\n\treturn <%eu.numberfour.n4js.ts.types.util.Variance%>.CO;\n}\nelse\n{\n\tif ((contra && (!co)))\n\t{\n\t\treturn <%eu.numberfour.n4js.ts.types.util.Variance%>.CONTRA;\n\t}\n\telse\n\t{\n\t\treturn <%eu.numberfour.n4js.ts.types.util.Variance%>.INV;\n\t}\n}'"
	 * @generated
	 */
	Variance getVariance();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return <%org.eclipse.emf.ecore.xcore.lib.XcoreCollectionLiterals%>.<<%eu.numberfour.n4js.ts.types.TypeVariable%>>emptyEList();'"
	 * @generated
	 */
	EList<TypeVariable> getTypeVars();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>> _declaredUpperBounds = this.getDeclaredUpperBounds();\nreturn this.getTypeVariableAsString(_declaredUpperBounds);'"
	 * @generated
	 */
	String getTypeAsString();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false" upperBoundsDataType="eu.numberfour.n4js.ts.types.CollectionOfTypeRef" upperBoundsUnique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _name = this.getName();\n<%java.lang.String%> _xifexpression = null;\nboolean _isEmpty = upperBounds.isEmpty();\nboolean _not = (!_isEmpty);\nif (_not)\n{\n\tfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>, <%java.lang.String%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.TypeRef%>, <%java.lang.String%>>()\n\t{\n\t\tpublic <%java.lang.String%> apply(final <%eu.numberfour.n4js.ts.typeRefs.TypeRef%> it)\n\t\t{\n\t\t\treturn it.getTypeRefAsString();\n\t\t}\n\t};\n\t<%java.lang.Iterable%><<%java.lang.String%>> _map = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.map(upperBounds, _function);\n\t<%java.lang.String%> _join = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.join(_map, \" & \");\n\t_xifexpression = (\" extends \" + _join);\n}\nelse\n{\n\t_xifexpression = \"\";\n}\nreturn (_name + _xifexpression);'"
	 * @generated
	 */
	String getTypeVariableAsString(Collection<? extends TypeRef> upperBounds);

} // TypeVariable
