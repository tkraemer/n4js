/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.n4JS.impl;

import com.google.common.base.Objects;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.IdentifierRef;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.n4JS.StrictModeRelevant;

import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;
import eu.numberfour.n4js.ts.typeRefs.Versionable;

import eu.numberfour.n4js.ts.types.IdentifiableElement;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Identifier Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.IdentifierRefImpl#isStrictMode <em>Strict Mode</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.IdentifierRefImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.numberfour.n4js.n4JS.impl.IdentifierRefImpl#getIdAsText <em>Id As Text</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IdentifierRefImpl extends PrimaryExpressionImpl implements IdentifierRef {
	/**
	 * The default value of the '{@link #isStrictMode() <em>Strict Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStrictMode()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STRICT_MODE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStrictMode() <em>Strict Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStrictMode()
	 * @generated
	 * @ordered
	 */
	protected boolean strictMode = STRICT_MODE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected IdentifiableElement id;

	/**
	 * The default value of the '{@link #getIdAsText() <em>Id As Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdAsText()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_AS_TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdAsText() <em>Id As Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdAsText()
	 * @generated
	 * @ordered
	 */
	protected String idAsText = ID_AS_TEXT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdentifierRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return N4JSPackage.Literals.IDENTIFIER_REF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStrictMode() {
		return strictMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrictMode(boolean newStrictMode) {
		boolean oldStrictMode = strictMode;
		strictMode = newStrictMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.IDENTIFIER_REF__STRICT_MODE, oldStrictMode, strictMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentifiableElement getId() {
		if (id != null && id.eIsProxy()) {
			InternalEObject oldId = (InternalEObject)id;
			id = (IdentifiableElement)eResolveProxy(oldId);
			if (id != oldId) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, N4JSPackage.IDENTIFIER_REF__ID, oldId, id));
			}
		}
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentifiableElement basicGetId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(IdentifiableElement newId) {
		IdentifiableElement oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.IDENTIFIER_REF__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdAsText() {
		return idAsText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdAsText(String newIdAsText) {
		String oldIdAsText = idAsText;
		idAsText = newIdAsText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, N4JSPackage.IDENTIFIER_REF__ID_AS_TEXT, oldIdAsText, idAsText));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isValidSimpleAssignmentTarget() {
		boolean _isStrictMode = this.isStrictMode();
		if (_isStrictMode) {
			return (((this.getIdAsText() != null) && (!Objects.equal("arguments", this.getIdAsText()))) && (!Objects.equal("eval", this.getIdAsText())));
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getVersion() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case N4JSPackage.IDENTIFIER_REF__STRICT_MODE:
				return isStrictMode();
			case N4JSPackage.IDENTIFIER_REF__ID:
				if (resolve) return getId();
				return basicGetId();
			case N4JSPackage.IDENTIFIER_REF__ID_AS_TEXT:
				return getIdAsText();
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
			case N4JSPackage.IDENTIFIER_REF__STRICT_MODE:
				setStrictMode((Boolean)newValue);
				return;
			case N4JSPackage.IDENTIFIER_REF__ID:
				setId((IdentifiableElement)newValue);
				return;
			case N4JSPackage.IDENTIFIER_REF__ID_AS_TEXT:
				setIdAsText((String)newValue);
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
			case N4JSPackage.IDENTIFIER_REF__STRICT_MODE:
				setStrictMode(STRICT_MODE_EDEFAULT);
				return;
			case N4JSPackage.IDENTIFIER_REF__ID:
				setId((IdentifiableElement)null);
				return;
			case N4JSPackage.IDENTIFIER_REF__ID_AS_TEXT:
				setIdAsText(ID_AS_TEXT_EDEFAULT);
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
			case N4JSPackage.IDENTIFIER_REF__STRICT_MODE:
				return strictMode != STRICT_MODE_EDEFAULT;
			case N4JSPackage.IDENTIFIER_REF__ID:
				return id != null;
			case N4JSPackage.IDENTIFIER_REF__ID_AS_TEXT:
				return ID_AS_TEXT_EDEFAULT == null ? idAsText != null : !ID_AS_TEXT_EDEFAULT.equals(idAsText);
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
		if (baseClass == StrictModeRelevant.class) {
			switch (derivedFeatureID) {
				case N4JSPackage.IDENTIFIER_REF__STRICT_MODE: return N4JSPackage.STRICT_MODE_RELEVANT__STRICT_MODE;
				default: return -1;
			}
		}
		if (baseClass == Versionable.class) {
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
		if (baseClass == StrictModeRelevant.class) {
			switch (baseFeatureID) {
				case N4JSPackage.STRICT_MODE_RELEVANT__STRICT_MODE: return N4JSPackage.IDENTIFIER_REF__STRICT_MODE;
				default: return -1;
			}
		}
		if (baseClass == Versionable.class) {
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
		if (baseClass == Expression.class) {
			switch (baseOperationID) {
				case N4JSPackage.EXPRESSION___IS_VALID_SIMPLE_ASSIGNMENT_TARGET: return N4JSPackage.IDENTIFIER_REF___IS_VALID_SIMPLE_ASSIGNMENT_TARGET;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == StrictModeRelevant.class) {
			switch (baseOperationID) {
				default: return -1;
			}
		}
		if (baseClass == Versionable.class) {
			switch (baseOperationID) {
				case TypeRefsPackage.VERSIONABLE___GET_VERSION: return N4JSPackage.IDENTIFIER_REF___GET_VERSION;
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
			case N4JSPackage.IDENTIFIER_REF___IS_VALID_SIMPLE_ASSIGNMENT_TARGET:
				return isValidSimpleAssignmentTarget();
			case N4JSPackage.IDENTIFIER_REF___GET_VERSION:
				return getVersion();
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
		result.append(" (strictMode: ");
		result.append(strictMode);
		result.append(", idAsText: ");
		result.append(idAsText);
		result.append(')');
		return result.toString();
	}

} //IdentifierRefImpl
