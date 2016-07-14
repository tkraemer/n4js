/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS.impl;

import com.google.common.base.Objects;

import eu.numberfour.n4js.n4JS.Annotation;
import eu.numberfour.n4js.n4JS.Block;
import eu.numberfour.n4js.n4JS.FunctionOrFieldAccessor;
import eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName;
import eu.numberfour.n4js.n4JS.LocalArgumentsVariable;
import eu.numberfour.n4js.n4JS.N4FieldAccessor;
import eu.numberfour.n4js.n4JS.N4InterfaceDeclaration;
import eu.numberfour.n4js.n4JS.N4JSFactory;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.N4MemberDeclaration;
import eu.numberfour.n4js.n4JS.N4Modifier;
import eu.numberfour.n4js.n4JS.NamedElement;
import eu.numberfour.n4js.n4JS.PropertyNameKind;
import eu.numberfour.n4js.n4JS.PropertyNameOwner;
import eu.numberfour.n4js.n4JS.ThisArgProvider;
import eu.numberfour.n4js.n4JS.TypeProvidingElement;
import eu.numberfour.n4js.n4JS.VariableEnvironmentElement;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;

import eu.numberfour.n4js.ts.types.FieldAccessor;

import eu.numberfour.n4js.utils.EcoreUtilN4;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xtext.xbase.lib.Functions.Function1;

import org.eclipse.xtext.xbase.lib.IterableExtensions;

import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>N4 Field Accessor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldAccessorImpl#getBody <em>Body</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldAccessorImpl#get_lok <em>lok</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.N4FieldAccessorImpl#getDeclaredName <em>Declared Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class N4FieldAccessorImpl extends AnnotableN4MemberDeclarationImpl implements N4FieldAccessor {
	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected Block body;

	/**
	 * The cached value of the '{@link #get_lok() <em>lok</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #get_lok()
	 * @generated
	 * @ordered
	 */
	protected LocalArgumentsVariable _lok;

	/**
	 * The cached value of the '{@link #getDeclaredName() <em>Declared Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaredName()
	 * @generated
	 * @ordered
	 */
	protected LiteralOrComputedPropertyName declaredName;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected N4FieldAccessorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSPackage.Literals.N4_FIELD_ACCESSOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBody() {
		return body;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(Block newBody, NotificationChain msgs) {
		Block oldBody = body;
		body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__BODY, oldBody, newBody);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(Block newBody) {
		if (newBody != body) {
			NotificationChain msgs = null;
			if (body != null)
				msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_ACCESSOR__BODY, null, msgs);
			if (newBody != null)
				msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_ACCESSOR__BODY, null, msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__BODY, newBody, newBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalArgumentsVariable get_lok() {
		return _lok;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSet_lok(LocalArgumentsVariable new_lok, NotificationChain msgs) {
		LocalArgumentsVariable old_lok = _lok;
		_lok = new_lok;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__LOK, old_lok, new_lok);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void set_lok(LocalArgumentsVariable new_lok) {
		if (new_lok != _lok) {
			NotificationChain msgs = null;
			if (_lok != null)
				msgs = ((InternalEObject)_lok).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_ACCESSOR__LOK, null, msgs);
			if (new_lok != null)
				msgs = ((InternalEObject)new_lok).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_ACCESSOR__LOK, null, msgs);
			msgs = basicSet_lok(new_lok, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__LOK, new_lok, new_lok));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiteralOrComputedPropertyName getDeclaredName() {
		return declaredName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclaredName(LiteralOrComputedPropertyName newDeclaredName, NotificationChain msgs) {
		LiteralOrComputedPropertyName oldDeclaredName = declaredName;
		declaredName = newDeclaredName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME, oldDeclaredName, newDeclaredName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaredName(LiteralOrComputedPropertyName newDeclaredName) {
		if (newDeclaredName != declaredName) {
			NotificationChain msgs = null;
			if (declaredName != null)
				msgs = ((InternalEObject)declaredName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME, null, msgs);
			if (newDeclaredName != null)
				msgs = ((InternalEObject)newDeclaredName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME, null, msgs);
			msgs = basicSetDeclaredName(newDeclaredName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME, newDeclaredName, newDeclaredName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAbstract() {
		return ((((this.eContainer() instanceof N4InterfaceDeclaration) && (this.getBody() == null)) && 
			(!IterableExtensions.<Annotation>exists(this.getAnnotations(), new Function1<Annotation, Boolean>() {
				public Boolean apply(final Annotation it) {
					String _name = it.getName();
					return Boolean.valueOf(Objects.equal(_name, "ProvidesDefaultImplementation"));
				}
			}))) || 
			this.getDeclaredModifiers().contains(N4Modifier.ABSTRACT));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isValidName() {
		String _name = this.getName();
		boolean _equals = Objects.equal("prototype", _name);
		if (_equals) {
			return false;
		}
		boolean _and = false;
		String _name_1 = this.getName();
		boolean _equals_1 = Objects.equal("constructor", _name_1);
		if (!_equals_1) {
			_and = false;
		} else {
			LiteralOrComputedPropertyName _declaredName = this.getDeclaredName();
			PropertyNameKind _kind = null;
			if (_declaredName!=null) {
				_kind=_declaredName.getKind();
			}
			boolean _tripleNotEquals = (_kind != PropertyNameKind.COMPUTED);
			_and = _tripleNotEquals;
		}
		if (_and) {
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getDeclaredTypeRef() {
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldAccessor getDefinedAccessor() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		LiteralOrComputedPropertyName _declaredName = this.getDeclaredName();
		String _name = null;
		if (_declaredName!=null) {
			_name=_declaredName.getName();
		}
		return _name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalArgumentsVariable getLocalArgumentsVariable() {
		LocalArgumentsVariable __lok = this.get_lok();
		boolean _tripleEquals = (__lok == null);
		if (_tripleEquals) {
			final LocalArgumentsVariable newLok = N4JSFactory.eINSTANCE.createLocalArgumentsVariable();
			newLok.setName("arguments");
			final Procedure0 _function = new Procedure0() {
				public void apply() {
					N4FieldAccessorImpl.this.set_lok(newLok);
				}
			};
			EcoreUtilN4.doWithDeliver(false, _function, this);
		}
		return this.get_lok();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAsync() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean appliesOnlyToBlockScopedElements() {
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case N4JSPackage.N4_FIELD_ACCESSOR__BODY:
				return basicSetBody(null, msgs);
			case N4JSPackage.N4_FIELD_ACCESSOR__LOK:
				return basicSet_lok(null, msgs);
			case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME:
				return basicSetDeclaredName(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case N4JSPackage.N4_FIELD_ACCESSOR__BODY:
				return getBody();
			case N4JSPackage.N4_FIELD_ACCESSOR__LOK:
				return get_lok();
			case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME:
				return getDeclaredName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case N4JSPackage.N4_FIELD_ACCESSOR__BODY:
				setBody((Block)newValue);
				return;
			case N4JSPackage.N4_FIELD_ACCESSOR__LOK:
				set_lok((LocalArgumentsVariable)newValue);
				return;
			case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME:
				setDeclaredName((LiteralOrComputedPropertyName)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case N4JSPackage.N4_FIELD_ACCESSOR__BODY:
				setBody((Block)null);
				return;
			case N4JSPackage.N4_FIELD_ACCESSOR__LOK:
				set_lok((LocalArgumentsVariable)null);
				return;
			case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME:
				setDeclaredName((LiteralOrComputedPropertyName)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case N4JSPackage.N4_FIELD_ACCESSOR__BODY:
				return body != null;
			case N4JSPackage.N4_FIELD_ACCESSOR__LOK:
				return _lok != null;
			case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME:
				return declaredName != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == VariableEnvironmentElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ThisArgProvider.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == FunctionOrFieldAccessor.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.N4_FIELD_ACCESSOR__BODY: return N4JSPackage.FUNCTION_OR_FIELD_ACCESSOR__BODY;
				case N4JSPackage.N4_FIELD_ACCESSOR__LOK: return N4JSPackage.FUNCTION_OR_FIELD_ACCESSOR__LOK;
				default: return -1;
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME: return N4JSPackage.PROPERTY_NAME_OWNER__DECLARED_NAME;
				default: return -1;
			}
		}
		if (baseClass == eu.numberfour.n4js.n4JS.FieldAccessor.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == VariableEnvironmentElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ThisArgProvider.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == FunctionOrFieldAccessor.class) {
			switch (baseFeatureID) {
				case N4JSPackage.FUNCTION_OR_FIELD_ACCESSOR__BODY: return N4JSPackage.N4_FIELD_ACCESSOR__BODY;
				case N4JSPackage.FUNCTION_OR_FIELD_ACCESSOR__LOK: return N4JSPackage.N4_FIELD_ACCESSOR__LOK;
				default: return -1;
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (baseFeatureID) {
				case N4JSPackage.PROPERTY_NAME_OWNER__DECLARED_NAME: return N4JSPackage.N4_FIELD_ACCESSOR__DECLARED_NAME;
				default: return -1;
			}
		}
		if (baseClass == eu.numberfour.n4js.n4JS.FieldAccessor.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass) {
		if (baseClass == TypeProvidingElement.class) {
			switch (baseOperationID) {
				case N4JSPackage.TYPE_PROVIDING_ELEMENT___GET_DECLARED_TYPE_REF: return N4JSPackage.N4_FIELD_ACCESSOR___GET_DECLARED_TYPE_REF;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseOperationID) {
				case N4JSPackage.NAMED_ELEMENT___GET_NAME: return N4JSPackage.N4_FIELD_ACCESSOR___GET_NAME;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == N4MemberDeclaration.class) {
			switch (baseOperationID) {
				case N4JSPackage.N4_MEMBER_DECLARATION___GET_NAME: return N4JSPackage.N4_FIELD_ACCESSOR___GET_NAME;
				case N4JSPackage.N4_MEMBER_DECLARATION___GET_DECLARED_TYPE_REF: return N4JSPackage.N4_FIELD_ACCESSOR___GET_DECLARED_TYPE_REF;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == VariableEnvironmentElement.class) {
			switch (baseOperationID) {
				case N4JSPackage.VARIABLE_ENVIRONMENT_ELEMENT___APPLIES_ONLY_TO_BLOCK_SCOPED_ELEMENTS: return N4JSPackage.N4_FIELD_ACCESSOR___APPLIES_ONLY_TO_BLOCK_SCOPED_ELEMENTS;
				default: return -1;
			}
		}
		if (baseClass == ThisArgProvider.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == FunctionOrFieldAccessor.class) {
			switch (baseOperationID) {
				case N4JSPackage.FUNCTION_OR_FIELD_ACCESSOR___GET_NAME: return N4JSPackage.N4_FIELD_ACCESSOR___GET_NAME;
				case N4JSPackage.FUNCTION_OR_FIELD_ACCESSOR___GET_LOCAL_ARGUMENTS_VARIABLE: return N4JSPackage.N4_FIELD_ACCESSOR___GET_LOCAL_ARGUMENTS_VARIABLE;
				case N4JSPackage.FUNCTION_OR_FIELD_ACCESSOR___IS_ASYNC: return N4JSPackage.N4_FIELD_ACCESSOR___IS_ASYNC;
				default: return -1;
			}
		}
		if (baseClass == PropertyNameOwner.class) {
			switch (baseOperationID) {
				case N4JSPackage.PROPERTY_NAME_OWNER___GET_NAME: return N4JSPackage.N4_FIELD_ACCESSOR___GET_NAME;
				case N4JSPackage.PROPERTY_NAME_OWNER___IS_VALID_NAME: return N4JSPackage.N4_FIELD_ACCESSOR___IS_VALID_NAME;
				default: return -1;
			}
		}
		if (baseClass == eu.numberfour.n4js.n4JS.FieldAccessor.class) {
			switch (baseOperationID) {
				case N4JSPackage.FIELD_ACCESSOR___GET_DECLARED_TYPE_REF: return N4JSPackage.N4_FIELD_ACCESSOR___GET_DECLARED_TYPE_REF;
				case N4JSPackage.FIELD_ACCESSOR___GET_DEFINED_ACCESSOR: return N4JSPackage.N4_FIELD_ACCESSOR___GET_DEFINED_ACCESSOR;
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_ABSTRACT:
				return isAbstract();
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_VALID_NAME:
				return isValidName();
			case N4JSPackage.N4_FIELD_ACCESSOR___GET_DECLARED_TYPE_REF:
				return getDeclaredTypeRef();
			case N4JSPackage.N4_FIELD_ACCESSOR___GET_DEFINED_ACCESSOR:
				return getDefinedAccessor();
			case N4JSPackage.N4_FIELD_ACCESSOR___GET_NAME:
				return getName();
			case N4JSPackage.N4_FIELD_ACCESSOR___GET_LOCAL_ARGUMENTS_VARIABLE:
				return getLocalArgumentsVariable();
			case N4JSPackage.N4_FIELD_ACCESSOR___IS_ASYNC:
				return isAsync();
			case N4JSPackage.N4_FIELD_ACCESSOR___APPLIES_ONLY_TO_BLOCK_SCOPED_ELEMENTS:
				return appliesOnlyToBlockScopedElements();
		}
		return super.eInvoke(operationID, arguments);
	}

} //N4FieldAccessorImpl
