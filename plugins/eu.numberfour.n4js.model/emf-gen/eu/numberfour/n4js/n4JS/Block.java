/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.Block#getStatements <em>Statements</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getBlock()
 * @model
 * @generated
 */
public interface Block extends Statement, VariableEnvironmentElement {
	/**
	 * Returns the value of the '<em><b>Statements</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.n4JS.Statement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statements</em>' containment reference list.
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getBlock_Statements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Statement> getStatements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * * See {@link VariableEnvironmentElement#appliesOnlyToBlockScopedElements()}.
	 * <!-- end-model-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return true;'"
	 * @generated
	 */
	boolean appliesOnlyToBlockScopedElements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns all statements that belong to this block. This includes statements of nested blocks
	 * but excludes statements in nested classes, nested functions, etc.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.n4JS.IteratorOfStatement" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final <%com.google.common.base.Predicate%><<%org.eclipse.emf.ecore.EObject%>> _function = new <%com.google.common.base.Predicate%><<%org.eclipse.emf.ecore.EObject%>>()\n{\n\tpublic boolean apply(final <%org.eclipse.emf.ecore.EObject%> it)\n\t{\n\t\treturn (!((it instanceof <%eu.numberfour.n4js.n4JS.Expression%>) || (it instanceof <%eu.numberfour.n4js.n4JS.FunctionDefinition%>)));\n\t}\n};\n<%org.eclipse.emf.common.util.TreeIterator%><<%org.eclipse.emf.ecore.EObject%>> _allContentsFiltered = <%eu.numberfour.n4js.utils.EcoreUtilN4%>.getAllContentsFiltered(this, _function);\nreturn <%com.google.common.collect.Iterators%>.<<%eu.numberfour.n4js.n4JS.Statement%>>filter(_allContentsFiltered, <%eu.numberfour.n4js.n4JS.Statement%>.class);'"
	 * @generated
	 */
	Iterator<Statement> getAllStatements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns all return statements contained in this block (whether they return a value or not), including those in nested blocks
	 * but without delving into nested classes, or into nested expressions including functions.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.n4JS.IteratorOfReturnStatement" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.util.Iterator%><<%eu.numberfour.n4js.n4JS.Statement%>> _allStatements = this.getAllStatements();\nreturn <%com.google.common.collect.Iterators%>.<<%eu.numberfour.n4js.n4JS.ReturnStatement%>>filter(_allStatements, <%eu.numberfour.n4js.n4JS.ReturnStatement%>.class);'"
	 * @generated
	 */
	Iterator<ReturnStatement> getAllReturnStatements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns all return statements contained in this block that return some value, including those in nested blocks
	 * but without delving into nested classes, or into nested expressions including functions.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.n4JS.IteratorOfReturnStatement" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.util.Iterator%><<%eu.numberfour.n4js.n4JS.ReturnStatement%>> _allReturnStatements = this.getAllReturnStatements();\nfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.n4JS.ReturnStatement%>, <%java.lang.Boolean%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.n4JS.ReturnStatement%>, <%java.lang.Boolean%>>()\n{\n\tpublic <%java.lang.Boolean%> apply(final <%eu.numberfour.n4js.n4JS.ReturnStatement%> it)\n\t{\n\t\t<%eu.numberfour.n4js.n4JS.Expression%> _expression = it.getExpression();\n\t\treturn <%java.lang.Boolean%>.valueOf((!<%com.google.common.base.Objects%>.equal(_expression, null)));\n\t}\n};\nreturn <%org.eclipse.xtext.xbase.lib.IteratorExtensions%>.<<%eu.numberfour.n4js.n4JS.ReturnStatement%>>filter(_allReturnStatements, _function);'"
	 * @generated
	 */
	Iterator<ReturnStatement> getAllNonVoidReturnStatements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns all return statements contained in this block that don't return any value, including those in nested blocks
	 * but without delving into nested classes, or into nested expressions including functions.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.n4JS.IteratorOfReturnStatement" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.util.Iterator%><<%eu.numberfour.n4js.n4JS.ReturnStatement%>> _allReturnStatements = this.getAllReturnStatements();\nfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.n4JS.ReturnStatement%>, <%java.lang.Boolean%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.n4JS.ReturnStatement%>, <%java.lang.Boolean%>>()\n{\n\tpublic <%java.lang.Boolean%> apply(final <%eu.numberfour.n4js.n4JS.ReturnStatement%> it)\n\t{\n\t\t<%eu.numberfour.n4js.n4JS.Expression%> _expression = it.getExpression();\n\t\treturn <%java.lang.Boolean%>.valueOf(<%com.google.common.base.Objects%>.equal(_expression, null));\n\t}\n};\nreturn <%org.eclipse.xtext.xbase.lib.IteratorExtensions%>.<<%eu.numberfour.n4js.n4JS.ReturnStatement%>>filter(_allReturnStatements, _function);'"
	 * @generated
	 */
	Iterator<ReturnStatement> getAllVoidReturnStatements();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True iff one or more return statements exist in this block that return some value.
	 * <!-- end-model-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.util.Iterator%><<%eu.numberfour.n4js.n4JS.ReturnStatement%>> _allNonVoidReturnStatements = this.getAllNonVoidReturnStatements();\nboolean _isEmpty = <%org.eclipse.xtext.xbase.lib.IteratorExtensions%>.isEmpty(_allNonVoidReturnStatements);\nreturn (!_isEmpty);'"
	 * @generated
	 */
	boolean hasNonVoidReturn();

} // Block
