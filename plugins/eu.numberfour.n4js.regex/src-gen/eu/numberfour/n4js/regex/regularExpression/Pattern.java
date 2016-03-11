/**
 */
package eu.numberfour.n4js.regex.regularExpression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.Pattern#getQuantifier <em>Quantifier</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getPattern()
 * @model
 * @generated
 */
public interface Pattern extends EObject
{
  /**
   * Returns the value of the '<em><b>Quantifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Quantifier</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Quantifier</em>' containment reference.
   * @see #setQuantifier(Quantifier)
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getPattern_Quantifier()
   * @model containment="true"
   * @generated
   */
  Quantifier getQuantifier();

  /**
   * Sets the value of the '{@link eu.numberfour.n4js.regex.regularExpression.Pattern#getQuantifier <em>Quantifier</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Quantifier</em>' containment reference.
   * @see #getQuantifier()
   * @generated
   */
  void setQuantifier(Quantifier value);

} // Pattern
