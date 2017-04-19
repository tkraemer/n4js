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
package eu.numberfour.n4js.n4mf.impl;

import eu.numberfour.n4js.n4mf.N4mfPackage;
import eu.numberfour.n4js.n4mf.ProjectDependencyScope;
import eu.numberfour.n4js.n4mf.SimpleProjectDependency;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple Project Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SimpleProjectDependencyImpl extends ProjectReferenceImpl implements SimpleProjectDependency {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimpleProjectDependencyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4mfPackage.Literals.SIMPLE_PROJECT_DEPENDENCY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProjectDependencyScope getScope() {
		return ProjectDependencyScope.COMPILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case N4mfPackage.SIMPLE_PROJECT_DEPENDENCY___GET_SCOPE:
				return getScope();
		}
		return super.eInvoke(operationID, arguments);
	}

} //SimpleProjectDependencyImpl
