/**
 */
package eu.numberfour.n4js.regex.regularExpression.impl;

import eu.numberfour.n4js.regex.regularExpression.Quantifier;
import eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quantifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.impl.QuantifierImpl#isNonGreedy <em>Non Greedy</em>}</li>
 * </ul>
 *
 * @generated
 */
public class QuantifierImpl extends MinimalEObjectImpl.Container implements Quantifier
{
  /**
   * The default value of the '{@link #isNonGreedy() <em>Non Greedy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNonGreedy()
   * @generated
   * @ordered
   */
  protected static final boolean NON_GREEDY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isNonGreedy() <em>Non Greedy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNonGreedy()
   * @generated
   * @ordered
   */
  protected boolean nonGreedy = NON_GREEDY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected QuantifierImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return RegularExpressionPackage.Literals.QUANTIFIER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isNonGreedy()
  {
    return nonGreedy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNonGreedy(boolean newNonGreedy)
  {
    boolean oldNonGreedy = nonGreedy;
    nonGreedy = newNonGreedy;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RegularExpressionPackage.QUANTIFIER__NON_GREEDY, oldNonGreedy, nonGreedy));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case RegularExpressionPackage.QUANTIFIER__NON_GREEDY:
        return isNonGreedy();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case RegularExpressionPackage.QUANTIFIER__NON_GREEDY:
        setNonGreedy((Boolean)newValue);
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
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case RegularExpressionPackage.QUANTIFIER__NON_GREEDY:
        setNonGreedy(NON_GREEDY_EDEFAULT);
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
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case RegularExpressionPackage.QUANTIFIER__NON_GREEDY:
        return nonGreedy != NON_GREEDY_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (nonGreedy: ");
    result.append(nonGreedy);
    result.append(')');
    return result.toString();
  }

} //QuantifierImpl
