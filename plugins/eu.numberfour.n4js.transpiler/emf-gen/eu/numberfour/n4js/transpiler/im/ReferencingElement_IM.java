/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.transpiler.im;

import eu.numberfour.n4js.ts.types.IdentifiableElement;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Referencing Element IM</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An element of the intermediate model that refers to some other element. The other element may either be an element
 * within the same resource (AST or TModule) OR an element in a remote resource (TModule). The reference is not pointing
 * to the original target element directly, but instead to a {@link SymbolTableEntry} (and is hence called a "rewired"
 * reference).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.transpiler.im.ReferencingElement_IM#getRewiredTarget <em>Rewired Target</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.transpiler.im.ImPackage#getReferencingElement_IM()
 * @model abstract="true"
 * @generated
 */
public interface ReferencingElement_IM extends EObject {
	/**
	 * Returns the value of the '<em><b>Rewired Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eu.numberfour.n4js.transpiler.im.SymbolTableEntry#getReferencingElements <em>Referencing Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rewired Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rewired Target</em>' reference.
	 * @see #setRewiredTarget(SymbolTableEntry)
	 * @see eu.numberfour.n4js.transpiler.im.ImPackage#getReferencingElement_IM_RewiredTarget()
	 * @see eu.numberfour.n4js.transpiler.im.SymbolTableEntry#getReferencingElements
	 * @model opposite="referencingElements"
	 * @generated
	 */
	SymbolTableEntry getRewiredTarget();

	/**
	 * Sets the value of the '{@link eu.numberfour.n4js.transpiler.im.ReferencingElement_IM#getRewiredTarget <em>Rewired Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rewired Target</em>' reference.
	 * @see #getRewiredTarget()
	 * @generated
	 */
	void setRewiredTarget(SymbolTableEntry value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Convenience method. If the rewiredTarget is a SymbolTableEntryOriginal, returns its original target, otherwise
	 * <code>null</code>.
	 * <!-- end-model-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='final <%eu.numberfour.n4js.transpiler.im.SymbolTableEntry%> declaredTypeSTE = this.getRewiredTarget();\nif ((declaredTypeSTE instanceof <%eu.numberfour.n4js.transpiler.im.SymbolTableEntryOriginal%>))\n{\n\treturn ((<%eu.numberfour.n4js.transpiler.im.SymbolTableEntryOriginal%>)declaredTypeSTE).getOriginalTarget();\n}\nreturn null;'"
	 * @generated
	 */
	IdentifiableElement getOriginalTargetOfRewiredTarget();

} // ReferencingElement_IM
