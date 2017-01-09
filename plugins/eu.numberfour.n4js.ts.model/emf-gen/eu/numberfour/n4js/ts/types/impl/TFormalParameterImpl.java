/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types.impl;

import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;

import eu.numberfour.n4js.ts.types.SyntaxRelatedTElement;
import eu.numberfour.n4js.ts.types.TAnnotableElement;
import eu.numberfour.n4js.ts.types.TAnnotation;
import eu.numberfour.n4js.ts.types.TFormalParameter;
import eu.numberfour.n4js.ts.types.TFunction;
import eu.numberfour.n4js.ts.types.TypesPackage;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;
import java.util.ListIterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TFormal Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TFormalParameterImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TFormalParameterImpl#getAstElement <em>Ast Element</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TFormalParameterImpl#isVariadic <em>Variadic</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TFormalParameterImpl#isHasInitializer <em>Has Initializer</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TFormalParameterImpl#isHasInitializerAssignment <em>Has Initializer Assignment</em>}</li>
 *   <li>{@link eu.numberfour.n4js.ts.types.impl.TFormalParameterImpl#getTypeRef <em>Type Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TFormalParameterImpl extends IdentifiableElementImpl implements TFormalParameter {
	/**
	 * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<TAnnotation> annotations;

	/**
	 * The cached value of the '{@link #getAstElement() <em>Ast Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAstElement()
	 * @generated
	 * @ordered
	 */
	protected EObject astElement;

	/**
	 * The default value of the '{@link #isVariadic() <em>Variadic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVariadic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VARIADIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isVariadic() <em>Variadic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isVariadic()
	 * @generated
	 * @ordered
	 */
	protected boolean variadic = VARIADIC_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasInitializer() <em>Has Initializer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasInitializer()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_INITIALIZER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasInitializer() <em>Has Initializer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasInitializer()
	 * @generated
	 * @ordered
	 */
	protected boolean hasInitializer = HAS_INITIALIZER_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasInitializerAssignment() <em>Has Initializer Assignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasInitializerAssignment()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_INITIALIZER_ASSIGNMENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasInitializerAssignment() <em>Has Initializer Assignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasInitializerAssignment()
	 * @generated
	 * @ordered
	 */
	protected boolean hasInitializerAssignment = HAS_INITIALIZER_ASSIGNMENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTypeRef() <em>Type Ref</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeRef()
	 * @generated
	 * @ordered
	 */
	protected TypeRef typeRef;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TFormalParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackage.Literals.TFORMAL_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TAnnotation> getAnnotations() {
		if (annotations == null) {
			annotations = new EObjectContainmentEList<TAnnotation>(TAnnotation.class, this, TypesPackage.TFORMAL_PARAMETER__ANNOTATIONS);
		}
		return annotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getAstElement() {
		if (astElement != null && astElement.eIsProxy()) {
			InternalEObject oldAstElement = (InternalEObject)astElement;
			astElement = eResolveProxy(oldAstElement);
			if (astElement != oldAstElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypesPackage.TFORMAL_PARAMETER__AST_ELEMENT, oldAstElement, astElement));
			}
		}
		return astElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetAstElement() {
		return astElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAstElement(EObject newAstElement) {
		EObject oldAstElement = astElement;
		astElement = newAstElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TFORMAL_PARAMETER__AST_ELEMENT, oldAstElement, astElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVariadic() {
		return variadic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariadic(boolean newVariadic) {
		boolean oldVariadic = variadic;
		variadic = newVariadic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TFORMAL_PARAMETER__VARIADIC, oldVariadic, variadic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasInitializer() {
		return hasInitializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasInitializer(boolean newHasInitializer) {
		boolean oldHasInitializer = hasInitializer;
		hasInitializer = newHasInitializer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TFORMAL_PARAMETER__HAS_INITIALIZER, oldHasInitializer, hasInitializer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasInitializerAssignment() {
		return hasInitializerAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasInitializerAssignment(boolean newHasInitializerAssignment) {
		boolean oldHasInitializerAssignment = hasInitializerAssignment;
		hasInitializerAssignment = newHasInitializerAssignment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TFORMAL_PARAMETER__HAS_INITIALIZER_ASSIGNMENT, oldHasInitializerAssignment, hasInitializerAssignment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRef getTypeRef() {
		return typeRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeRef(TypeRef newTypeRef, NotificationChain msgs) {
		TypeRef oldTypeRef = typeRef;
		typeRef = newTypeRef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TypesPackage.TFORMAL_PARAMETER__TYPE_REF, oldTypeRef, newTypeRef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeRef(TypeRef newTypeRef) {
		if (newTypeRef != typeRef) {
			NotificationChain msgs = null;
			if (typeRef != null)
				msgs = ((InternalEObject)typeRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TypesPackage.TFORMAL_PARAMETER__TYPE_REF, null, msgs);
			if (newTypeRef != null)
				msgs = ((InternalEObject)newTypeRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TypesPackage.TFORMAL_PARAMETER__TYPE_REF, null, msgs);
			msgs = basicSetTypeRef(newTypeRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TypesPackage.TFORMAL_PARAMETER__TYPE_REF, newTypeRef, newTypeRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOptional() {
		EList<TFormalParameter> fpars = null;
		EObject _eContainer = this.eContainer();
		if ((_eContainer instanceof TFunction)) {
			EObject _eContainer_1 = this.eContainer();
			EList<TFormalParameter> _fpars = ((TFunction) _eContainer_1).getFpars();
			fpars = _fpars;
		}
		EObject _eContainer_2 = this.eContainer();
		if ((_eContainer_2 instanceof FunctionTypeExprOrRef)) {
			EObject _eContainer_3 = this.eContainer();
			EList<TFormalParameter> _fpars_1 = ((FunctionTypeExprOrRef) _eContainer_3).getFpars();
			fpars = _fpars_1;
		}
		if ((fpars == null)) {
			return false;
		}
		int idx = fpars.indexOf(this);
		ListIterator<TFormalParameter> iter = fpars.listIterator(idx);
		while (iter.hasNext()) {
			{
				final TFormalParameter fpar = iter.next();
				if (((!fpar.isVariadic()) && (!fpar.isHasInitializerAssignment()))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isVariadicOrOptional() {
		return (this.isVariadic() || this.isOptional());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFormalParameterAsString() {
		final StringBuilder strb = new StringBuilder();
		boolean _isVariadic = this.isVariadic();
		if (_isVariadic) {
			strb.append("...");
		}
		String _name = this.getName();
		strb.append(_name);
		TypeRef _typeRef = this.getTypeRef();
		boolean _tripleNotEquals = (_typeRef != null);
		if (_tripleNotEquals) {
			StringBuilder _append = strb.append(": ");
			TypeRef _typeRef_1 = this.getTypeRef();
			String _typeRefAsString = _typeRef_1.getTypeRefAsString();
			_append.append(_typeRefAsString);
		}
		boolean _isOptional = this.isOptional();
		if (_isOptional) {
			strb.append(" = undefined");
		}
		return strb.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypesPackage.TFORMAL_PARAMETER__ANNOTATIONS:
				return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
			case TypesPackage.TFORMAL_PARAMETER__TYPE_REF:
				return basicSetTypeRef(null, msgs);
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
			case TypesPackage.TFORMAL_PARAMETER__ANNOTATIONS:
				return getAnnotations();
			case TypesPackage.TFORMAL_PARAMETER__AST_ELEMENT:
				if (resolve) return getAstElement();
				return basicGetAstElement();
			case TypesPackage.TFORMAL_PARAMETER__VARIADIC:
				return isVariadic();
			case TypesPackage.TFORMAL_PARAMETER__HAS_INITIALIZER:
				return isHasInitializer();
			case TypesPackage.TFORMAL_PARAMETER__HAS_INITIALIZER_ASSIGNMENT:
				return isHasInitializerAssignment();
			case TypesPackage.TFORMAL_PARAMETER__TYPE_REF:
				return getTypeRef();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TypesPackage.TFORMAL_PARAMETER__ANNOTATIONS:
				getAnnotations().clear();
				getAnnotations().addAll((Collection<? extends TAnnotation>)newValue);
				return;
			case TypesPackage.TFORMAL_PARAMETER__AST_ELEMENT:
				setAstElement((EObject)newValue);
				return;
			case TypesPackage.TFORMAL_PARAMETER__VARIADIC:
				setVariadic((Boolean)newValue);
				return;
			case TypesPackage.TFORMAL_PARAMETER__HAS_INITIALIZER:
				setHasInitializer((Boolean)newValue);
				return;
			case TypesPackage.TFORMAL_PARAMETER__HAS_INITIALIZER_ASSIGNMENT:
				setHasInitializerAssignment((Boolean)newValue);
				return;
			case TypesPackage.TFORMAL_PARAMETER__TYPE_REF:
				setTypeRef((TypeRef)newValue);
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
			case TypesPackage.TFORMAL_PARAMETER__ANNOTATIONS:
				getAnnotations().clear();
				return;
			case TypesPackage.TFORMAL_PARAMETER__AST_ELEMENT:
				setAstElement((EObject)null);
				return;
			case TypesPackage.TFORMAL_PARAMETER__VARIADIC:
				setVariadic(VARIADIC_EDEFAULT);
				return;
			case TypesPackage.TFORMAL_PARAMETER__HAS_INITIALIZER:
				setHasInitializer(HAS_INITIALIZER_EDEFAULT);
				return;
			case TypesPackage.TFORMAL_PARAMETER__HAS_INITIALIZER_ASSIGNMENT:
				setHasInitializerAssignment(HAS_INITIALIZER_ASSIGNMENT_EDEFAULT);
				return;
			case TypesPackage.TFORMAL_PARAMETER__TYPE_REF:
				setTypeRef((TypeRef)null);
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
			case TypesPackage.TFORMAL_PARAMETER__ANNOTATIONS:
				return annotations != null && !annotations.isEmpty();
			case TypesPackage.TFORMAL_PARAMETER__AST_ELEMENT:
				return astElement != null;
			case TypesPackage.TFORMAL_PARAMETER__VARIADIC:
				return variadic != VARIADIC_EDEFAULT;
			case TypesPackage.TFORMAL_PARAMETER__HAS_INITIALIZER:
				return hasInitializer != HAS_INITIALIZER_EDEFAULT;
			case TypesPackage.TFORMAL_PARAMETER__HAS_INITIALIZER_ASSIGNMENT:
				return hasInitializerAssignment != HAS_INITIALIZER_ASSIGNMENT_EDEFAULT;
			case TypesPackage.TFORMAL_PARAMETER__TYPE_REF:
				return typeRef != null;
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
		if (baseClass == TAnnotableElement.class) {
			switch (derivedFeatureID) {
				case TypesPackage.TFORMAL_PARAMETER__ANNOTATIONS: return TypesPackage.TANNOTABLE_ELEMENT__ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == SyntaxRelatedTElement.class) {
			switch (derivedFeatureID) {
				case TypesPackage.TFORMAL_PARAMETER__AST_ELEMENT: return TypesPackage.SYNTAX_RELATED_TELEMENT__AST_ELEMENT;
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
		if (baseClass == TAnnotableElement.class) {
			switch (baseFeatureID) {
				case TypesPackage.TANNOTABLE_ELEMENT__ANNOTATIONS: return TypesPackage.TFORMAL_PARAMETER__ANNOTATIONS;
				default: return -1;
			}
		}
		if (baseClass == SyntaxRelatedTElement.class) {
			switch (baseFeatureID) {
				case TypesPackage.SYNTAX_RELATED_TELEMENT__AST_ELEMENT: return TypesPackage.TFORMAL_PARAMETER__AST_ELEMENT;
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
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case TypesPackage.TFORMAL_PARAMETER___IS_OPTIONAL:
				return isOptional();
			case TypesPackage.TFORMAL_PARAMETER___IS_VARIADIC_OR_OPTIONAL:
				return isVariadicOrOptional();
			case TypesPackage.TFORMAL_PARAMETER___GET_FORMAL_PARAMETER_AS_STRING:
				return getFormalParameterAsString();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (variadic: ");
		result.append(variadic);
		result.append(", hasInitializer: ");
		result.append(hasInitializer);
		result.append(", hasInitializerAssignment: ");
		result.append(hasInitializerAssignment);
		result.append(')');
		return result.toString();
	}

} //TFormalParameterImpl
