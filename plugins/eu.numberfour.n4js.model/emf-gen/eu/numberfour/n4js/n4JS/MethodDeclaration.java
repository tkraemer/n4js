/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%eu.numberfour.n4js.n4JS.Block%&gt; _body = this.getBody();\n&lt;%org.eclipse.emf.common.util.TreeIterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.Statement%&gt;&gt; _allDirectlyFoundContentsOfType = &lt;%eu.numberfour.n4js.utils.EcoreUtilN4%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.Statement%&gt;&gt;getAllDirectlyFoundContentsOfType(_body, &lt;%eu.numberfour.n4js.n4JS.Statement%&gt;.class);\n&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ExpressionStatement%&gt;&gt; _filter = &lt;%com.google.common.collect.Iterators%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.ExpressionStatement%&gt;&gt;filter(_allDirectlyFoundContentsOfType, &lt;%eu.numberfour.n4js.n4JS.ExpressionStatement%&gt;.class);\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ExpressionStatement%&gt;, &lt;%eu.numberfour.n4js.n4JS.Expression%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ExpressionStatement%&gt;, &lt;%eu.numberfour.n4js.n4JS.Expression%&gt;&gt;()\n{\n\tpublic &lt;%eu.numberfour.n4js.n4JS.Expression%&gt; apply(final &lt;%eu.numberfour.n4js.n4JS.ExpressionStatement%&gt; it)\n\t{\n\t\treturn it.getExpression();\n\t}\n};\n&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.Expression%&gt;&gt; _map = &lt;%org.eclipse.xtext.xbase.lib.IteratorExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.ExpressionStatement%&gt;, &lt;%eu.numberfour.n4js.n4JS.Expression%&gt;&gt;map(_filter, _function);\n&lt;%java.util.Iterator%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%&gt;&gt; _filter_1 = &lt;%com.google.common.collect.Iterators%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%&gt;&gt;filter(_map, &lt;%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%&gt;.class);\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function_1 = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%&gt; it)\n\t{\n\t\t&lt;%eu.numberfour.n4js.n4JS.Expression%&gt; _target = it.getTarget();\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf((_target instanceof &lt;%eu.numberfour.n4js.n4JS.SuperLiteral%&gt;));\n\t}\n};\nfinal boolean existsSuperCall = &lt;%org.eclipse.xtext.xbase.lib.IteratorExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.n4JS.ParameterizedCallExpression%&gt;&gt;exists(_filter_1, _function_1);\nreturn existsSuperCall;'"
	 * @generated
	 */
	boolean existsExplicitSuperCall();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%eu.numberfour.n4js.ts.types.TMember%&gt; _xifexpression = null;\n&lt;%eu.numberfour.n4js.ts.types.Type%&gt; _definedType = this.getDefinedType();\nboolean _tripleEquals = (_definedType == null);\nif (_tripleEquals)\n{\n\t_xifexpression = null;\n}\nelse\n{\n\t&lt;%eu.numberfour.n4js.ts.types.TMember%&gt; _xifexpression_1 = null;\n\t&lt;%eu.numberfour.n4js.ts.types.Type%&gt; _definedType_1 = this.getDefinedType();\n\tif ((_definedType_1 instanceof &lt;%eu.numberfour.n4js.ts.types.TMember%&gt;))\n\t{\n\t\t&lt;%eu.numberfour.n4js.ts.types.Type%&gt; _definedType_2 = this.getDefinedType();\n\t\t_xifexpression_1 = ((&lt;%eu.numberfour.n4js.ts.types.TMember%&gt;) _definedType_2);\n\t}\n\telse\n\t{\n\t\tthrow new &lt;%java.lang.IllegalArgumentException%&gt;(\n\t\t\t\"\");\n\t}\n\t_xifexpression = _xifexpression_1;\n}\nreturn _xifexpression;'"
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
