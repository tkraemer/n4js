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
	 * Returns all return expressions contained in this block, including those in nested blocks
	 * but without delving into nested classes, or into nested expressions including functions.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.n4JS.IteratorOfExpression" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final &lt;%com.google.common.base.Predicate%&gt;&lt;&lt;%org.eclipse.emf.ecore.EObject%&gt;&gt; _function = new &lt;%com.google.common.base.Predicate%&gt;&lt;&lt;%org.eclipse.emf.ecore.EObject%&gt;&gt;()\n{\n\tpublic boolean apply(final &lt;%org.eclipse.emf.ecore.EObject%&gt; it)\n\t{\n\t\treturn (!(it instanceof &lt;%eu.numberfour.n4js.n4JS.FunctionDefinition%&gt;));\n\t}\n};\n&lt;%org.eclipse.emf.common.util.TreeIterator%&gt;&lt;&lt;%org.eclipse.emf.ecore.EObject%&gt;&gt; _allContentsFiltered = &lt;%eu.numberfour.n4js.utils.EcoreUtilN4%&gt;.getAllContentsFiltered(this, _function);\nreturn &lt;%com.google.common.collect.Iterators%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.Expression%&gt;&gt;filter(_allContentsFiltered, &lt;%eu.numberfour.n4js.n4JS.Expression%&gt;.class);'"
	 * @generated
	 */
	Iterator<Expression> getAllExpressions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns all return yield expressions contained in this block, including those in nested blocks
	 * but without delving into nested classes, or into nested expressions including functions.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.n4JS.IteratorOfYieldExpression" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.Expression%&gt;&gt; _allExpressions = this.getAllExpressions();\nreturn &lt;%com.google.common.collect.Iterators%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;&gt;filter(_allExpressions, &lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;.class);'"
	 * @generated
	 */
	Iterator<YieldExpression> getAllYieldExpressions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns all return yield expressions contained in this block that dont't return any value, including those in nested blocks
	 * but without delving into nested classes, or into nested expressions including functions.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.n4JS.IteratorOfYieldExpression" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;&gt; _allYieldExpressions = this.getAllYieldExpressions();\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt; it)\n\t{\n\t\t&lt;%eu.numberfour.n4js.n4JS.Expression%&gt; _expression = it.getExpression();\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf(&lt;%com.google.common.base.Objects%&gt;.equal(_expression, null));\n\t}\n};\nreturn &lt;%org.eclipse.xtext.xbase.lib.IteratorExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;&gt;filter(_allYieldExpressions, _function);'"
	 * @generated
	 */
	Iterator<YieldExpression> getAllVoidYieldExpressions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns all return yield expressions contained in this block that return some value, including those in nested blocks
	 * but without delving into nested classes, or into nested expressions including functions.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.n4JS.IteratorOfYieldExpression" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;&gt; _allYieldExpressions = this.getAllYieldExpressions();\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt; it)\n\t{\n\t\t&lt;%eu.numberfour.n4js.n4JS.Expression%&gt; _expression = it.getExpression();\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf((!&lt;%com.google.common.base.Objects%&gt;.equal(_expression, null)));\n\t}\n};\nreturn &lt;%org.eclipse.xtext.xbase.lib.IteratorExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;&gt;filter(_allYieldExpressions, _function);'"
	 * @generated
	 */
	Iterator<YieldExpression> getAllNonVoidYieldExpressions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True iff one or more yield expressions exist in this block that return some value.
	 * <!-- end-model-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.YieldExpression%&gt;&gt; _allNonVoidYieldExpressions = this.getAllNonVoidYieldExpressions();\nboolean _isEmpty = &lt;%org.eclipse.xtext.xbase.lib.IteratorExtensions%&gt;.isEmpty(_allNonVoidYieldExpressions);\nreturn (!_isEmpty);'"
	 * @generated
	 */
	boolean hasNonVoidYield();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns all statements that belong to this block. This includes statements of nested blocks
	 * but excludes statements in nested classes, nested functions, etc.
	 * <!-- end-model-doc -->
	 * @model kind="operation" dataType="eu.numberfour.n4js.n4JS.IteratorOfStatement" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final &lt;%com.google.common.base.Predicate%&gt;&lt;&lt;%org.eclipse.emf.ecore.EObject%&gt;&gt; _function = new &lt;%com.google.common.base.Predicate%&gt;&lt;&lt;%org.eclipse.emf.ecore.EObject%&gt;&gt;()\n{\n\tpublic boolean apply(final &lt;%org.eclipse.emf.ecore.EObject%&gt; it)\n\t{\n\t\treturn (!((it instanceof &lt;%eu.numberfour.n4js.n4JS.Expression%&gt;) || (it instanceof &lt;%eu.numberfour.n4js.n4JS.FunctionDefinition%&gt;)));\n\t}\n};\n&lt;%org.eclipse.emf.common.util.TreeIterator%&gt;&lt;&lt;%org.eclipse.emf.ecore.EObject%&gt;&gt; _allContentsFiltered = &lt;%eu.numberfour.n4js.utils.EcoreUtilN4%&gt;.getAllContentsFiltered(this, _function);\nreturn &lt;%com.google.common.collect.Iterators%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.Statement%&gt;&gt;filter(_allContentsFiltered, &lt;%eu.numberfour.n4js.n4JS.Statement%&gt;.class);'"
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.Statement%&gt;&gt; _allStatements = this.getAllStatements();\nreturn &lt;%com.google.common.collect.Iterators%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;&gt;filter(_allStatements, &lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;.class);'"
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;&gt; _allReturnStatements = this.getAllReturnStatements();\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt; it)\n\t{\n\t\t&lt;%eu.numberfour.n4js.n4JS.Expression%&gt; _expression = it.getExpression();\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf((!&lt;%com.google.common.base.Objects%&gt;.equal(_expression, null)));\n\t}\n};\nreturn &lt;%org.eclipse.xtext.xbase.lib.IteratorExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;&gt;filter(_allReturnStatements, _function);'"
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;&gt; _allReturnStatements = this.getAllReturnStatements();\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt; it)\n\t{\n\t\t&lt;%eu.numberfour.n4js.n4JS.Expression%&gt; _expression = it.getExpression();\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf(&lt;%com.google.common.base.Objects%&gt;.equal(_expression, null));\n\t}\n};\nreturn &lt;%org.eclipse.xtext.xbase.lib.IteratorExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;&gt;filter(_allReturnStatements, _function);'"
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ReturnStatement%&gt;&gt; _allNonVoidReturnStatements = this.getAllNonVoidReturnStatements();\nboolean _isEmpty = &lt;%org.eclipse.xtext.xbase.lib.IteratorExtensions%&gt;.isEmpty(_allNonVoidReturnStatements);\nreturn (!_isEmpty);'"
	 * @generated
	 */
	boolean hasNonVoidReturn();

} // Block
