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
package eu.numberfour.n4js.n4JS.impl;

import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.ThisTarget;

import eu.numberfour.n4js.utils.emf.ProxyResolvingEObjectImpl;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>This Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class ThisTargetImpl extends ProxyResolvingEObjectImpl implements ThisTarget {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ThisTargetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSPackage.Literals.THIS_TARGET;
	}

} //ThisTargetImpl
