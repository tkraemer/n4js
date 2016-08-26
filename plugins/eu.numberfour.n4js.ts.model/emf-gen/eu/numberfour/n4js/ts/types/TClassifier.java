/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;

import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;

import java.lang.Iterable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TClassifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for N4 specific classifiers, i.e., class, interface, or role.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.TClassifier#isDeclaredCovariantConstructor <em>Declared Covariant Constructor</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTClassifier()
 * @model abstract="true"
 * @generated
 */
public interface TClassifier extends ContainerType<TMember>, SyntaxRelatedTElement {
	/**
	 * Returns the value of the '<em><b>Declared Covariant Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tells if this classifier is itself annotated with <code>@CovariantConstructor</code> or if it has an owned
	 * constructor annotated with <code>@CovariantConstructor</code>.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Declared Covariant Constructor</em>' attribute.
	 * @see #setDeclaredCovariantConstructor(boolean)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTClassifier_DeclaredCovariantConstructor()
	 * @model unique="false"
	 * @generated
	 */
	boolean isDeclaredCovariantConstructor();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TClassifier#isDeclaredCovariantConstructor <em>Declared Covariant Constructor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declared Covariant Constructor</em>' attribute.
	 * @see #isDeclaredCovariantConstructor()
	 * @generated
	 */
	void setDeclaredCovariantConstructor(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Convenience method, returns all super classes and implemented or extended interfaces as classifiers.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.ts.types.IterableOfTClassifier" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.Iterable%><<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>> _superClassifierRefs = this.getSuperClassifierRefs();\n<%java.lang.Iterable%><<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>> _filterNull = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.<<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>>filterNull(_superClassifierRefs);\nfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>, <%eu.numberfour.n4js.ts.types.Type%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>, <%eu.numberfour.n4js.ts.types.Type%>>()\n{\n\tpublic <%eu.numberfour.n4js.ts.types.Type%> apply(final <%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%> it)\n\t{\n\t\treturn it.getDeclaredType();\n\t}\n};\n<%java.lang.Iterable%><<%eu.numberfour.n4js.ts.types.Type%>> _map = <%org.eclipse.xtext.xbase.lib.IterableExtensions%>.<<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>, <%eu.numberfour.n4js.ts.types.Type%>>map(_filterNull, _function);\nreturn <%com.google.common.collect.Iterables%>.<<%eu.numberfour.n4js.ts.types.TClassifier%>>filter(_map, <%eu.numberfour.n4js.ts.types.TClassifier%>.class);'"
	 * @generated
	 */
	Iterable<? extends TClassifier> getSuperClassifiers();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Convenience method, returns all super classes and implemented or extended interfaces as type references.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefIterable" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return <%org.eclipse.xtext.xbase.lib.CollectionLiterals%>.<<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>>emptyList();'"
	 * @generated
	 */
	Iterable<ParameterizedTypeRef> getSuperClassifierRefs();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Convenience method, returns all implemented (or extended) interfaces
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRefIterable" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return <%org.eclipse.xtext.xbase.lib.CollectionLiterals%>.<<%eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef%>>emptyList();'"
	 * @generated
	 */
	Iterable<ParameterizedTypeRef> getImplementedOrExtendedInterfaceRefs();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Classifiers are usually not final, unless they have a special modifier set.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isFinal();

} // TClassifier
