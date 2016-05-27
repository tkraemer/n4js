/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS.impl;

import com.google.common.collect.Iterators;

import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.VariableBinding;
import eu.numberfour.n4js.n4JS.VariableDeclaration;
import eu.numberfour.n4js.n4JS.VariableDeclarationOrBinding;

import java.lang.reflect.InvocationTargetException;

import java.util.Iterator;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration Or Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class VariableDeclarationOrBindingImpl extends MinimalEObjectImpl.Container implements VariableDeclarationOrBinding {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableDeclarationOrBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSPackage.Literals.VARIABLE_DECLARATION_OR_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariableDeclaration> getVariableDeclarations() {
		EList<VariableDeclaration> _switchResult = null;
		boolean _matched = false;
		if (this instanceof VariableDeclaration) {
			_matched=true;
			_switchResult = ECollections.<VariableDeclaration>toEList(java.util.Collections.<VariableDeclaration>unmodifiableList(org.eclipse.xtext.xbase.lib.CollectionLiterals.<VariableDeclaration>newArrayList(((VariableDeclaration) this))));
		}
		if (!_matched) {
			if (this instanceof VariableBinding) {
				_matched=true;
				TreeIterator<EObject> _eAllContents = this.eAllContents();
				Iterator<VariableDeclaration> _filter = Iterators.<VariableDeclaration>filter(_eAllContents, VariableDeclaration.class);
				_switchResult = ECollections.<VariableDeclaration>toEList(_filter);
			}
		}
		if (!_matched) {
			_switchResult = ECollections.<VariableDeclaration>toEList(java.util.Collections.<VariableDeclaration>unmodifiableList(org.eclipse.xtext.xbase.lib.CollectionLiterals.<VariableDeclaration>newArrayList()));
		}
		return _switchResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case N4JSPackage.VARIABLE_DECLARATION_OR_BINDING___GET_VARIABLE_DECLARATIONS:
				return getVariableDeclarations();
		}
		return super.eInvoke(operationID, arguments);
	}

} //VariableDeclarationOrBindingImpl
