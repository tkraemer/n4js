/**
 */
package eu.numberfour.n4js.regex.regularExpression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Character Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.CharacterClass#isNegated <em>Negated</em>}</li>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.CharacterClass#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getCharacterClass()
 * @model
 * @generated
 */
public interface CharacterClass extends Pattern
{
  /**
   * Returns the value of the '<em><b>Negated</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Negated</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Negated</em>' attribute.
   * @see #setNegated(boolean)
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getCharacterClass_Negated()
   * @model
   * @generated
   */
  boolean isNegated();

  /**
   * Sets the value of the '{@link eu.numberfour.n4js.regex.regularExpression.CharacterClass#isNegated <em>Negated</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Negated</em>' attribute.
   * @see #isNegated()
   * @generated
   */
  void setNegated(boolean value);

  /**
   * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
   * The list contents are of type {@link eu.numberfour.n4js.regex.regularExpression.CharacterClassElement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements</em>' containment reference list.
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getCharacterClass_Elements()
   * @model containment="true"
   * @generated
   */
  EList<CharacterClassElement> getElements();

} // CharacterClass
