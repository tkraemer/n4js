/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TAnnotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.TAnnotation#getName <em>Name</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.TAnnotation#getArgs <em>Args</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTAnnotation()
 * @model
 * @generated
 */
public interface TAnnotation extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTAnnotation_Name()
	 * @model unique="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.ts.types.TAnnotation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Args</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.ts.types.TAnnotationArgument}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Args</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Args</em>' containment reference list.
	 * @see eu.numberfour.n4js.ts.types.TypesPackage#getTAnnotation_Args()
	 * @model containment="true"
	 * @generated
	 */
	EList<TAnnotationArgument> getArgs();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Convenience method, returns true if an argument of type TAnnotatonStringArgument exists with given value.
	 * <!-- end-model-doc -->
	 * @model unique="false" argumentValueUnique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TAnnotationArgument%&gt;&gt; _args = this.getArgs();\n&lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TAnnotationStringArgument%&gt;&gt; _filter = &lt;%com.google.common.collect.Iterables%&gt;.&lt;&lt;%eu.numberfour.n4js.ts.types.TAnnotationStringArgument%&gt;&gt;filter(_args, &lt;%eu.numberfour.n4js.ts.types.TAnnotationStringArgument%&gt;.class);\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TAnnotationStringArgument%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TAnnotationStringArgument%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.ts.types.TAnnotationStringArgument%&gt; it)\n\t{\n\t\t&lt;%java.lang.String%&gt; _value = it.getValue();\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf(&lt;%com.google.common.base.Objects%&gt;.equal(_value, argumentValue));\n\t}\n};\nreturn &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.ts.types.TAnnotationStringArgument%&gt;&gt;exists(_filter, _function);'"
	 * @generated
	 */
	boolean hasStringArgument(String argumentValue);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final &lt;%java.lang.StringBuilder%&gt; strb = new &lt;%java.lang.StringBuilder%&gt;();\nstrb.append(\"@\");\n&lt;%java.lang.String%&gt; _name = this.getName();\nstrb.append(_name);\n&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TAnnotationArgument%&gt;&gt; _args = this.getArgs();\nint _length = ((&lt;%java.lang.Object%&gt;[])org.eclipse.xtext.xbase.lib.Conversions.unwrapArray(_args, &lt;%java.lang.Object%&gt;.class)).length;\nboolean _greaterThan = (_length &gt; 0);\nif (_greaterThan)\n{\n\tstrb.append(\"(\");\n\tfor (int i = 0; (i &lt; ((&lt;%java.lang.Object%&gt;[])org.eclipse.xtext.xbase.lib.Conversions.unwrapArray(this.getArgs(), &lt;%java.lang.Object%&gt;.class)).length); i++)\n\t{\n\t\t{\n\t\t\tif ((i &gt; 0))\n\t\t\t{\n\t\t\t\tstrb.append(\", \");\n\t\t\t}\n\t\t\t&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.ts.types.TAnnotationArgument%&gt;&gt; _args_1 = this.getArgs();\n\t\t\t&lt;%eu.numberfour.n4js.ts.types.TAnnotationArgument%&gt; _get = _args_1.get(i);\n\t\t\t&lt;%java.lang.String%&gt; _argAsString = _get.getArgAsString();\n\t\t\tstrb.append(_argAsString);\n\t\t}\n\t}\n\tstrb.append(\")\");\n}\nreturn strb.toString();'"
	 * @generated
	 */
	String getAnnotationAsString();

} // TAnnotation
