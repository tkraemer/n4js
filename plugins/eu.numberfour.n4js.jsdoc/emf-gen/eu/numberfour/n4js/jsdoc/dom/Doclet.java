/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.jsdoc.dom;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Doclet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.jsdoc.dom.Doclet#getLineTags <em>Line Tags</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.jsdoc.dom.DomPackage#getDoclet()
 * @model
 * @generated
 */
public interface Doclet extends Composite {
	/**
	 * Returns the value of the '<em><b>Line Tags</b></em>' containment reference list.
	 * The list contents are of type {@link eu.numberfour.n4js.jsdoc.dom.LineTag}.
	 * It is bidirectional and its opposite is '{@link eu.numberfour.n4js.jsdoc.dom.LineTag#getDoclet <em>Doclet</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Tags</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Tags</em>' containment reference list.
	 * @see eu.numberfour.n4js.jsdoc.dom.DomPackage#getDoclet_LineTags()
	 * @see eu.numberfour.n4js.jsdoc.dom.LineTag#getDoclet
	 * @model opposite="doclet" containment="true"
	 * @generated
	 */
	EList<LineTag> getLineTags();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false" titleUnique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt;&gt; _lineTags = this.getLineTags();\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt; it)\n\t{\n\t\t&lt;%eu.numberfour.n4js.jsdoc.dom.TagTitle%&gt; _title = it.getTitle();\n\t\t&lt;%java.lang.String%&gt; _title_1 = _title.getTitle();\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf(&lt;%com.google.common.base.Objects%&gt;.equal(_title_1, title));\n\t}\n};\n&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt; _findFirst = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt;&gt;findFirst(_lineTags, _function);\nreturn (_findFirst != null);'"
	 * @generated
	 */
	boolean hasLineTag(String title);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model unique="false" titleUnique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt;&gt; _lineTags = this.getLineTags();\nfinal &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt;, &lt;%java.lang.Boolean%&gt;&gt; _function = new &lt;%org.eclipse.xtext.xbase.lib.Functions.Function1%&gt;&lt;&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt;, &lt;%java.lang.Boolean%&gt;&gt;()\n{\n\tpublic &lt;%java.lang.Boolean%&gt; apply(final &lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt; it)\n\t{\n\t\t&lt;%eu.numberfour.n4js.jsdoc.dom.TagTitle%&gt; _title = it.getTitle();\n\t\t&lt;%java.lang.String%&gt; _title_1 = _title.getTitle();\n\t\treturn &lt;%java.lang.Boolean%&gt;.valueOf(&lt;%com.google.common.base.Objects%&gt;.equal(_title_1, title));\n\t}\n};\n&lt;%java.lang.Iterable%&gt;&lt;&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt;&gt; _filter = &lt;%org.eclipse.xtext.xbase.lib.IterableExtensions%&gt;.&lt;&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt;&gt;filter(_lineTags, _function);\nreturn &lt;%org.eclipse.emf.common.util.ECollections%&gt;.&lt;&lt;%eu.numberfour.n4js.jsdoc.dom.LineTag%&gt;&gt;toEList(_filter);'"
	 * @generated
	 */
	EList<LineTag> lineTags(String title);

} // Doclet
