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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>N4 Field Accessor</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getN4FieldAccessor()
 * @model abstract="true"
 * @generated
 */
public interface N4FieldAccessor extends AnnotableN4MemberDeclaration, FieldAccessor {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return ((((this.eContainer() instanceof <%eu.numberfour.n4js.n4JS.N4InterfaceDeclaration%>) && (this.getBody() == null)) && \n\t(!<%org.eclipse.xtext.xbase.lib.IterableExtensions%>.<<%eu.numberfour.n4js.n4JS.Annotation%>>exists(this.getAnnotations(), new <%org.eclipse.xtext.xbase.lib.Functions.Function1%><<%eu.numberfour.n4js.n4JS.Annotation%>, <%java.lang.Boolean%>>()\n\t{\n\t\tpublic <%java.lang.Boolean%> apply(final <%eu.numberfour.n4js.n4JS.Annotation%> it)\n\t\t{\n\t\t\t<%java.lang.String%> _name = it.getName();\n\t\t\treturn <%java.lang.Boolean%>.valueOf(<%com.google.common.base.Objects%>.equal(_name, \"ProvidesDefaultImplementation\"));\n\t\t}\n\t}))) || \n\tthis.getDeclaredModifiers().contains(<%eu.numberfour.n4js.n4JS.N4Modifier%>.ABSTRACT));'"
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Field accessors in classes may not be called 'prototype' or 'constructor' (except for computed names).
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _name = this.getName();\nboolean _equals = <%com.google.common.base.Objects%>.equal(\"prototype\", _name);\nif (_equals)\n{\n\treturn false;\n}\nboolean _and = false;\n<%java.lang.String%> _name_1 = this.getName();\nboolean _equals_1 = <%com.google.common.base.Objects%>.equal(\"constructor\", _name_1);\nif (!_equals_1)\n{\n\t_and = false;\n} else\n{\n\t<%eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName%> _declaredName = this.getDeclaredName();\n\t<%eu.numberfour.n4js.n4JS.PropertyNameKind%> _kind = null;\n\tif (_declaredName!=null)\n\t{\n\t\t_kind=_declaredName.getKind();\n\t}\n\tboolean _tripleNotEquals = (_kind != <%eu.numberfour.n4js.n4JS.PropertyNameKind%>.COMPUTED);\n\t_and = _tripleNotEquals;\n}\nif (_and)\n{\n\treturn false;\n}\nreturn true;'"
	 * @generated
	 */
	boolean isValidName();

} // N4FieldAccessor
