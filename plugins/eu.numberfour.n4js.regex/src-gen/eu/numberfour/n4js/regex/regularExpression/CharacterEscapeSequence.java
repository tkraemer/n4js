/**
 */
package eu.numberfour.n4js.regex.regularExpression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Character Escape Sequence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.CharacterEscapeSequence#getSequence <em>Sequence</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getCharacterEscapeSequence()
 * @model
 * @generated
 */
public interface CharacterEscapeSequence extends AtomEscape, EscapedCharacterClassAtom
{
  /**
   * Returns the value of the '<em><b>Sequence</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sequence</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sequence</em>' attribute.
   * @see #setSequence(String)
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getCharacterEscapeSequence_Sequence()
   * @model
   * @generated
   */
  String getSequence();

  /**
   * Sets the value of the '{@link eu.numberfour.n4js.regex.regularExpression.CharacterEscapeSequence#getSequence <em>Sequence</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sequence</em>' attribute.
   * @see #getSequence()
   * @generated
   */
  void setSequence(String value);

} // CharacterEscapeSequence
