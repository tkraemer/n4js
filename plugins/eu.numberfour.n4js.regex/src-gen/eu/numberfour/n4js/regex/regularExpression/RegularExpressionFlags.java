/**
 */
package eu.numberfour.n4js.regex.regularExpression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flags</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link eu.numberfour.n4js.regex.regularExpression.RegularExpressionFlags#getFlags <em>Flags</em>}</li>
 * </ul>
 *
 * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getRegularExpressionFlags()
 * @model
 * @generated
 */
public interface RegularExpressionFlags extends EObject
{
  /**
   * Returns the value of the '<em><b>Flags</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Flags</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Flags</em>' attribute list.
   * @see eu.numberfour.n4js.regex.regularExpression.RegularExpressionPackage#getRegularExpressionFlags_Flags()
   * @model unique="false"
   * @generated
   */
  EList<String> getFlags();

} // RegularExpressionFlags
