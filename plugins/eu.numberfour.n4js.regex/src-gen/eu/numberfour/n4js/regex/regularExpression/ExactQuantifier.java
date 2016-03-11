/**
 */
package eu.numberfour.n4js.regex.regularExpression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exact Quantifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.ExactQuantifier#getMin <em>Min</em>}</li>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.ExactQuantifier#getMax <em>Max</em>}</li>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.ExactQuantifier#isUnboundedMax <em>Unbounded Max</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getExactQuantifier()
 * @model
 * @generated
 */
public interface ExactQuantifier extends Quantifier
{
  /**
   * Returns the value of the '<em><b>Min</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Min</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Min</em>' attribute.
   * @see #setMin(int)
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getExactQuantifier_Min()
   * @model
   * @generated
   */
  int getMin();

  /**
   * Sets the value of the '{@link eu.numberfour.n4js.regex.regularExpression.ExactQuantifier#getMin <em>Min</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Min</em>' attribute.
   * @see #getMin()
   * @generated
   */
  void setMin(int value);

  /**
   * Returns the value of the '<em><b>Max</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Max</em>' attribute.
   * @see #setMax(int)
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getExactQuantifier_Max()
   * @model
   * @generated
   */
  int getMax();

  /**
   * Sets the value of the '{@link eu.numberfour.n4js.regex.regularExpression.ExactQuantifier#getMax <em>Max</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Max</em>' attribute.
   * @see #getMax()
   * @generated
   */
  void setMax(int value);

  /**
   * Returns the value of the '<em><b>Unbounded Max</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unbounded Max</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unbounded Max</em>' attribute.
   * @see #setUnboundedMax(boolean)
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getExactQuantifier_UnboundedMax()
   * @model
   * @generated
   */
  boolean isUnboundedMax();

  /**
   * Sets the value of the '{@link eu.numberfour.n4js.regex.regularExpression.ExactQuantifier#isUnboundedMax <em>Unbounded Max</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unbounded Max</em>' attribute.
   * @see #isUnboundedMax()
   * @generated
   */
  void setUnboundedMax(boolean value);

} // ExactQuantifier
