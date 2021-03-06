/**
 */
package eu.numberfour.n4js.regex.regularExpression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quantifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.Quantifier#isNonGreedy <em>Non Greedy</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getQuantifier()
 * @model
 * @generated
 */
public interface Quantifier extends EObject
{
  /**
   * Returns the value of the '<em><b>Non Greedy</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Non Greedy</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Non Greedy</em>' attribute.
   * @see #setNonGreedy(boolean)
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getQuantifier_NonGreedy()
   * @model
   * @generated
   */
  boolean isNonGreedy();

  /**
   * Sets the value of the '{@link eu.numberfour.n4js.regex.regularExpression.Quantifier#isNonGreedy <em>Non Greedy</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Non Greedy</em>' attribute.
   * @see #isNonGreedy()
   * @generated
   */
  void setNonGreedy(boolean value);

} // Quantifier
