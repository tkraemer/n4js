/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.n4JS;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration Or Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getVariableDeclarationOrBinding()
 * @model abstract="true"
 * @generated
 */
public interface VariableDeclarationOrBinding extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.n4JS.VariableDeclaration%>> _switchResult = null;\nboolean _matched = false;\nif (this instanceof <%eu.numberfour.n4js.n4JS.VariableDeclaration%>)\n{\n\t_matched=true;\n\t_switchResult = <%org.eclipse.emf.common.util.ECollections%>.<<%eu.numberfour.n4js.n4JS.VariableDeclaration%>>toEList(java.util.Collections.<<%eu.numberfour.n4js.n4JS.VariableDeclaration%>>unmodifiableList(org.eclipse.xtext.xbase.lib.CollectionLiterals.<<%eu.numberfour.n4js.n4JS.VariableDeclaration%>>newArrayList(((<%eu.numberfour.n4js.n4JS.VariableDeclaration%>) this))));\n}\nif (!_matched)\n{\n\tif (this instanceof <%eu.numberfour.n4js.n4JS.VariableBinding%>)\n\t{\n\t\t_matched=true;\n\t\t<%org.eclipse.emf.common.util.TreeIterator%><<%org.eclipse.emf.ecore.EObject%>> _eAllContents = this.eAllContents();\n\t\t<%java.util.Iterator%><<%eu.numberfour.n4js.n4JS.VariableDeclaration%>> _filter = <%com.google.common.collect.Iterators%>.<<%eu.numberfour.n4js.n4JS.VariableDeclaration%>>filter(_eAllContents, <%eu.numberfour.n4js.n4JS.VariableDeclaration%>.class);\n\t\t_switchResult = <%org.eclipse.emf.common.util.ECollections%>.<<%eu.numberfour.n4js.n4JS.VariableDeclaration%>>toEList(_filter);\n\t}\n}\nif (!_matched)\n{\n\t_switchResult = <%org.eclipse.emf.common.util.ECollections%>.<<%eu.numberfour.n4js.n4JS.VariableDeclaration%>>toEList(java.util.Collections.<<%eu.numberfour.n4js.n4JS.VariableDeclaration%>>unmodifiableList(org.eclipse.xtext.xbase.lib.CollectionLiterals.<<%eu.numberfour.n4js.n4JS.VariableDeclaration%>>newArrayList()));\n}\nreturn _switchResult;'"
	 * @generated
	 */
	EList<VariableDeclaration> getVariableDeclarations();

} // VariableDeclarationOrBinding
