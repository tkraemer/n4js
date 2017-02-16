/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;&gt; _switchResult = null;\nboolean _matched = false;\nif (this instanceof &lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;)\n{\n\t_matched=true;\n\t_switchResult = &lt;%org.eclipse.emf.common.util.ECollections%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;&gt;toEList(java.util.Collections.&lt;&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;&gt;unmodifiableList(org.eclipse.xtext.xbase.lib.CollectionLiterals.&lt;&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;&gt;newArrayList(((&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;) this))));\n}\nif (!_matched)\n{\n\tif (this instanceof &lt;%eu.numberfour.n4js.n4JS.VariableBinding%&gt;)\n\t{\n\t\t_matched=true;\n\t\t&lt;%org.eclipse.emf.common.util.TreeIterator%&gt;&lt;&lt;%org.eclipse.emf.ecore.EObject%&gt;&gt; _eAllContents = this.eAllContents();\n\t\t&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;&gt; _filter = &lt;%com.google.common.collect.Iterators%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;&gt;filter(_eAllContents, &lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;.class);\n\t\t_switchResult = &lt;%org.eclipse.emf.common.util.ECollections%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;&gt;toEList(_filter);\n\t}\n}\nif (!_matched)\n{\n\t_switchResult = &lt;%org.eclipse.emf.common.util.ECollections%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;&gt;toEList(java.util.Collections.&lt;&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;&gt;unmodifiableList(org.eclipse.xtext.xbase.lib.CollectionLiterals.&lt;&lt;%eu.numberfour.n4js.n4JS.VariableDeclaration%&gt;&gt;newArrayList()));\n}\nreturn _switchResult;'"
	 * @generated
	 */
	EList<VariableDeclaration> getVariableDeclarations();

} // VariableDeclarationOrBinding
