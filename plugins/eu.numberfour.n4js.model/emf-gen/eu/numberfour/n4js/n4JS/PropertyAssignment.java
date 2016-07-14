/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS;

import eu.numberfour.n4js.ts.types.TStructMember;
import eu.numberfour.n4js.ts.types.TypableElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A PropertyAssignment is either a simple {@link PropertyNameValuePair},
 * or a property accessor, that is a {@link PropertyGetterDeclaration} or
 * {@link PropertySetterDeclaration}.
 * <!-- end-model-doc -->
 *
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getPropertyAssignment()
 * @model abstract="true"
 * @generated
 */
public interface PropertyAssignment extends AnnotableElement, VariableEnvironmentElement, PropertyNameOwner, TypableElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 * @generated
	 */
	TStructMember getDefinedMember();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Properties may not be called 'prototype' or 'constructor' (except for computed names).
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='<%java.lang.String%> _name = this.getName();\nboolean _equals = <%com.google.common.base.Objects%>.equal(\"prototype\", _name);\nif (_equals)\n{\n\treturn false;\n}\nboolean _and = false;\n<%java.lang.String%> _name_1 = this.getName();\nboolean _equals_1 = <%com.google.common.base.Objects%>.equal(\"constructor\", _name_1);\nif (!_equals_1)\n{\n\t_and = false;\n} else\n{\n\t<%eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName%> _declaredName = this.getDeclaredName();\n\t<%eu.numberfour.n4js.n4JS.PropertyNameKind%> _kind = null;\n\tif (_declaredName!=null)\n\t{\n\t\t_kind=_declaredName.getKind();\n\t}\n\tboolean _tripleNotEquals = (_kind != <%eu.numberfour.n4js.n4JS.PropertyNameKind%>.COMPUTED);\n\t_and = _tripleNotEquals;\n}\nif (_and)\n{\n\treturn false;\n}\nreturn true;'"
	 * @generated
	 */
	boolean isValidName();

} // PropertyAssignment
