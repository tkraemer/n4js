/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arrow Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.ArrowFunction#isHasBracesAroundBody <em>Has Braces Around Body</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getArrowFunction()
 * @model
 * @generated
 */
public interface ArrowFunction extends FunctionExpression {
	/**
	 * Returns the value of the '<em><b>Has Braces Around Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Whether this arrow function has braces around its (single or multiple statements) body.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Has Braces Around Body</em>' attribute.
	 * @see #setHasBracesAroundBody(boolean)
	 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getArrowFunction_HasBracesAroundBody()
	 * @model unique="false"
	 * @generated
	 */
	boolean isHasBracesAroundBody();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.n4JS.ArrowFunction#isHasBracesAroundBody <em>Has Braces Around Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Braces Around Body</em>' attribute.
	 * @see #isHasBracesAroundBody()
	 * @generated
	 */
	void setHasBracesAroundBody(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return true;'"
	 * @generated
	 */
	boolean isArrowFunction();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * This method reports whether the body of the lambda consists of a single expression not enclosed in braces.
	 * 	 * The presence of enclosing braces implies block semantics for the lambda's body,
	 * ie a block encloses statements (even if only one, an expression statement).
	 * As usual, the block can be void-typed or some-value-typed;
	 * the latter case requires the presence of explicit return-some-value statements.
	 * 	 * An arrow function lacking braces and having a body consisting of just:
	 *   - return-some-value --- is malformed (syntax error)
	 *   - return;           --- is malformed (syntax error)
	 *   - expr              --- is ok, where expr instanceof ExpressionStatement;
	 *                           otherwise malformed and caught by the grammar.
	 * 	 * This method returns true only for the last case above.
	 * Please notice that isSingleExprImplicitReturn() === true doesn't allow drawing conclusion on the type of the lambda body,
	 * i.e. it could be either void-typed (eg, expr denotes the invocation of a void method)
	 * or some-value-typed. An implicit return is warranted only in the latter sub-case.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='boolean _and = false;\nboolean _and_1 = false;\nboolean _and_2 = false;\nboolean _and_3 = false;\nboolean _isArrowFunction = this.isArrowFunction();\nif (!_isArrowFunction)\n{\n\t_and_3 = false;\n} else\n{\n\tboolean _isHasBracesAroundBody = this.isHasBracesAroundBody();\n\tboolean _not = (!_isHasBracesAroundBody);\n\t_and_3 = _not;\n}\nif (!_and_3)\n{\n\t_and_2 = false;\n} else\n{\n\t<%eu.numberfour.n4js.n4JS.Block%> _body = this.getBody();\n\tboolean _notEquals = (!<%com.google.common.base.Objects%>.equal(_body, null));\n\t_and_2 = _notEquals;\n}\nif (!_and_2)\n{\n\t_and_1 = false;\n} else\n{\n\t<%eu.numberfour.n4js.n4JS.Block%> _body_1 = this.getBody();\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.n4JS.Statement%>> _statements = _body_1.getStatements();\n\tboolean _isEmpty = _statements.isEmpty();\n\tboolean _not_1 = (!_isEmpty);\n\t_and_1 = _not_1;\n}\nif (!_and_1)\n{\n\t_and = false;\n} else\n{\n\t<%eu.numberfour.n4js.n4JS.Block%> _body_2 = this.getBody();\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.n4JS.Statement%>> _statements_1 = _body_2.getStatements();\n\t<%eu.numberfour.n4js.n4JS.Statement%> _get = _statements_1.get(0);\n\t_and = (_get instanceof <%eu.numberfour.n4js.n4JS.ExpressionStatement%>);\n}\nreturn _and;'"
	 * @generated
	 */
	boolean isSingleExprImplicitReturn();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * The lambda's implicit return expression (precondition: isSingleExprImplicitReturn).
	 * <!-- end-model-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.n4JS.Expression%> _xifexpression = null;\nboolean _isSingleExprImplicitReturn = this.isSingleExprImplicitReturn();\nif (_isSingleExprImplicitReturn)\n{\n\t<%eu.numberfour.n4js.n4JS.Block%> _body = this.getBody();\n\t<%org.eclipse.emf.common.util.EList%><<%eu.numberfour.n4js.n4JS.Statement%>> _statements = _body.getStatements();\n\t<%eu.numberfour.n4js.n4JS.Statement%> _get = _statements.get(0);\n\t_xifexpression = ((<%eu.numberfour.n4js.n4JS.ExpressionStatement%>) _get).getExpression();\n}\nelse\n{\n\t_xifexpression = null;\n}\nreturn _xifexpression;'"
	 * @generated
	 */
	Expression implicitReturnExpr();

} // ArrowFunction
