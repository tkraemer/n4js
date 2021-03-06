/**
 */
package eu.numberfour.n4js.regex.regularExpression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.Group#isNonCapturing <em>Non Capturing</em>}</li>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.Group#getPattern <em>Pattern</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getGroup()
 * @model
 * @generated
 */
public interface Group extends Pattern
{
  /**
   * Returns the value of the '<em><b>Non Capturing</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Non Capturing</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Non Capturing</em>' attribute.
   * @see #setNonCapturing(boolean)
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getGroup_NonCapturing()
   * @model
   * @generated
   */
  boolean isNonCapturing();

  /**
   * Sets the value of the '{@link eu.numberfour.n4js.regex.regularExpression.Group#isNonCapturing <em>Non Capturing</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Non Capturing</em>' attribute.
   * @see #isNonCapturing()
   * @generated
   */
  void setNonCapturing(boolean value);

  /**
   * Returns the value of the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pattern</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pattern</em>' containment reference.
   * @see #setPattern(Pattern)
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getGroup_Pattern()
   * @model containment="true"
   * @generated
   */
  Pattern getPattern();

  /**
   * Sets the value of the '{@link eu.numberfour.n4js.regex.regularExpression.Group#getPattern <em>Pattern</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pattern</em>' containment reference.
   * @see #getPattern()
   * @generated
   */
  void setPattern(Pattern value);

} // Group
