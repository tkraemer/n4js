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

import eu.numberfour.n4js.ts.types.TMember;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getMethodDeclaration()
 * @model abstract="true"
 * @generated
 */
public interface MethodDeclaration extends FunctionDefinition, GenericDeclaration, TypedElement, PropertyNameOwner {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Convenience method, returns true if an explicit call to the super constructor is found.
	 * Of course, this is only allowed in constructors. Note that this method searches for ALL
	 * statements in the body, not only the first one (which is the only valid place).
	 * <!-- end-model-doc -->
	 * @model unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.n4JS.Block%> _body = this.getBody();\n<%org.eclipse.emf.common.util.TreeIterator%><<%eu.numberfour.n4js.n4JS.Statement%>> _allDirectlyFoundContentsOfType = <%eu.numberfour.n4js.utils.EcoreUtilN4%>.<<%eu.numberfour.n4js.n4JS.Statement%>>getAllDirectlyFoundContentsOfType(_body, <%eu.numberfour.n4js.n4JS.Statement%>.class);\n<%java.util.Iterator%><<%eu.numberfour.n4js.n4JS.ExpressionStatement%>> _filter = <%com.google.common.collect.Iterators%>.<<%eu.numberfour.n4js.n4JS.ExpressionStatement%>>filter(_allDirectlyFoundContentsOfType, <%eu.numberfour.n4js.n4JS.ExpressionStatement%>.class);\nfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.n4JS.ExpressionStatement%>, <%eu.numberfour.n4js.n4JS.Expression%>> _function = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.n4JS.ExpressionStatement%>, <%eu.numberfour.n4js.n4JS.Expression%>>()\n{\n\tpublic <%eu.numberfour.n4js.n4JS.Expression%> apply(final <%eu.numberfour.n4js.n4JS.ExpressionStatement%> it)\n\t{\n\t\treturn it.getExpression();\n\t}\n};\n<%java.util.Iterator%><<%eu.numberfour.n4js.n4JS.Expression%>> _map = <%org.eclipse.xtext.xbase.lib.IteratorExtensions%>.<<%eu.numberfour.n4js.n4JS.ExpressionStatement%>, <%eu.numberfour.n4js.n4JS.Expression%>>map(_filter, _function);\n<%java.util.Iterator%><<%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%>> _filter_1 = <%com.google.common.collect.Iterators%>.<<%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%>>filter(_map, <%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%>.class);\nfinal <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%>, <%java.lang.Boolean%>> _function_1 = new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%>, <%java.lang.Boolean%>>()\n{\n\tpublic <%java.lang.Boolean%> apply(final <%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%> it)\n\t{\n\t\t<%eu.numberfour.n4js.n4JS.Expression%> _target = it.getTarget();\n\t\treturn <%java.lang.Boolean%>.valueOf((_target instanceof <%eu.numberfour.n4js.n4JS.SuperLiteral%>));\n\t}\n};\nfinal boolean existsSuperCall = <%org.eclipse.xtext.xbase.lib.IteratorExtensions%>.<<%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%>>exists(_filter_1, _function_1);\nreturn existsSuperCall;'"
	 * @generated
	 */
	boolean existsExplicitSuperCall();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%eu.numberfour.n4js.ts.types.TMember%> _xifexpression = null;\n<%eu.numberfour.n4js.ts.types.Type%> _definedType = this.getDefinedType();\nboolean _tripleEquals = (_definedType == null);\nif (_tripleEquals)\n{\n\t_xifexpression = null;\n}\nelse\n{\n\t<%eu.numberfour.n4js.ts.types.TMember%> _xifexpression_1 = null;\n\t<%eu.numberfour.n4js.ts.types.Type%> _definedType_1 = this.getDefinedType();\n\tif ((_definedType_1 instanceof <%eu.numberfour.n4js.ts.types.TMember%>))\n\t{\n\t\t<%eu.numberfour.n4js.ts.types.Type%> _definedType_2 = this.getDefinedType();\n\t\t_xifexpression_1 = ((<%eu.numberfour.n4js.ts.types.TMember%>) _definedType_2);\n\t}\n\telse\n\t{\n\t\tthrow new <%java.lang.IllegalArgumentException%>(\n\t\t\t\"\");\n\t}\n\t_xifexpression = _xifexpression_1;\n}\nreturn _xifexpression;'"
	 * @generated
	 */
	TMember getDefinedTypeElement();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return false;'"
	 * @generated
	 */
	boolean isStatic();

} // MethodDeclaration
