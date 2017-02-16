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
 * A representation of the model object '<em><b>Exported Variable Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see eu.numberfour.n4js.n4JS.N4JSPackage#getExportedVariableStatement()
 * @model
 * @generated
 */
public interface ExportedVariableStatement extends VariableStatement, ExportableElement, AnnotableScriptElement, ModifiableElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Returns true if type is declared as external.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='&lt;%org.eclipse.emf.common.util.EList%&gt;&lt;&lt;%eu.numberfour.n4js.n4JS.N4Modifier%&gt;&gt; _declaredModifiers = this.getDeclaredModifiers();\nreturn _declaredModifiers.contains(&lt;%eu.numberfour.n4js.n4JS.N4Modifier%&gt;.EXTERNAL);'"
	 * @generated
	 */
	boolean isExternal();

} // ExportedVariableStatement
