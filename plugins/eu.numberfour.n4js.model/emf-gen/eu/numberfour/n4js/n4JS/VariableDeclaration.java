/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.VariableDeclaration#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.VariableDeclaration#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getVariableDeclaration()
 * @model
 * @generated
 */
public interface VariableDeclaration extends VariableDeclarationOrBinding, AnnotableElement, Variable {
	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.n4JS.Annotation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotations</em>' containment reference list.
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getVariableDeclaration_Annotations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Annotation> getAnnotations();

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(Expression)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getVariableDeclaration_Expression()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.VariableDeclaration#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns true if variable is declared as const.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.ecore.EObject%&gt; _eContainer = this.eContainer();\nif ((_eContainer instanceof &lt;%eu.numberfour.n4js.n4JS.VariableStatement%&gt;))\n{\n\t&lt;%org.eclipse.emf.ecore.EObject%&gt; _eContainer_1 = this.eContainer();\n\t&lt;%eu.numberfour.n4js.n4JS.VariableStatementKeyword%&gt; _varStmtKeyword = ((&lt;%eu.numberfour.n4js.n4JS.VariableStatement%&gt;) _eContainer_1).getVarStmtKeyword();\n\treturn (_varStmtKeyword == &lt;%eu.numberfour.n4js.n4JS.VariableStatementKeyword%&gt;.CONST);\n}\n&lt;%org.eclipse.emf.ecore.EObject%&gt; _eContainer_2 = this.eContainer();\nif ((_eContainer_2 instanceof &lt;%eu.numberfour.n4js.n4JS.ForStatement%&gt;))\n{\n\t&lt;%org.eclipse.emf.ecore.EObject%&gt; _eContainer_3 = this.eContainer();\n\t&lt;%eu.numberfour.n4js.n4JS.VariableStatementKeyword%&gt; _varStmtKeyword_1 = ((&lt;%eu.numberfour.n4js.n4JS.ForStatement%&gt;) _eContainer_3).getVarStmtKeyword();\n\treturn (_varStmtKeyword_1 == &lt;%eu.numberfour.n4js.n4JS.VariableStatementKeyword%&gt;.CONST);\n}\nreturn false;'"
	 * @generated
	 */
	boolean isConst();

} // VariableDeclaration
