/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4jsx.validation

import eu.numberfour.n4js.utils.Log
import eu.numberfour.n4js.validation.AbstractMessageAdjustingN4JSValidator.MethodWrapperCancelable
import eu.numberfour.n4js.validation.validators.IDEBUGValidator
import eu.numberfour.n4js.validation.validators.N4JSAccessModifierValidator
import eu.numberfour.n4js.validation.validators.N4JSAnnotationValidator
import eu.numberfour.n4js.validation.validators.N4JSClassValidator
import eu.numberfour.n4js.validation.validators.N4JSClassifierValidator
import eu.numberfour.n4js.validation.validators.N4JSDeclaredNameValidator
import eu.numberfour.n4js.validation.validators.N4JSDependencyInjectionValidator
import eu.numberfour.n4js.validation.validators.N4JSDestructureValidator
import eu.numberfour.n4js.validation.validators.N4JSEnumValidator
import eu.numberfour.n4js.validation.validators.N4JSExpressionValidator
import eu.numberfour.n4js.validation.validators.N4JSExternalValidator
import eu.numberfour.n4js.validation.validators.N4JSFunctionValidator
import eu.numberfour.n4js.validation.validators.N4JSImportValidator
import eu.numberfour.n4js.validation.validators.N4JSInjectorCallsitesValidator
import eu.numberfour.n4js.validation.validators.N4JSInterfaceValidator
import eu.numberfour.n4js.validation.validators.N4JSLambdaValidator
import eu.numberfour.n4js.validation.validators.N4JSMemberRedefinitionValidator
import eu.numberfour.n4js.validation.validators.N4JSMemberValidator
import eu.numberfour.n4js.validation.validators.N4JSModuleValidator
import eu.numberfour.n4js.validation.validators.N4JSNameValidator
import eu.numberfour.n4js.validation.validators.N4JSStatementValidator
import eu.numberfour.n4js.validation.validators.N4JSSuperValidator
import eu.numberfour.n4js.validation.validators.N4JSSyntaxValidator
import eu.numberfour.n4js.validation.validators.N4JSTypeValidator
import eu.numberfour.n4js.validation.validators.N4JSVariableValidator
import eu.numberfour.n4js.validation.validators.UnsupportedFeatureValidator
import eu.numberfour.n4js.xsemantics.validation.InternalTypeSystemValidator
import java.lang.reflect.Method
import java.util.List
import org.eclipse.emf.common.util.BasicDiagnostic
import org.eclipse.emf.common.util.Diagnostic
import org.eclipse.emf.ecore.EPackage
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.AbstractDeclarativeValidator.State
import org.eclipse.xtext.validation.ComposedChecks

//import org.eclipse.xtext.validation.Check
/**
 * This class contains custom validation rules.
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@ComposedChecks(validators=#[
	IDEBUGValidator,
	// N4JSStrictValidator,
	N4JSNameValidator,
	N4JSClassifierValidator,
	N4JSMemberRedefinitionValidator,
	N4JSClassValidator,
	N4JSInterfaceValidator,
	N4JSFunctionValidator,
	N4JSImportValidator,
	N4JSTypeValidator,
	N4JSExpressionValidator,
	N4JSMemberValidator,
	N4JSExternalValidator,
	N4JSAccessModifierValidator,
	N4JSSuperValidator,
	N4JSLambdaValidator,
	N4JSVariableValidator,
	N4JSDeclaredNameValidator,
	N4JSStatementValidator,
	N4JSAnnotationValidator,
	N4JSEnumValidator,
	N4JSSyntaxValidator,
	N4JSDependencyInjectionValidator,
	N4JSInjectorCallsitesValidator,
	N4JSModuleValidator,
	N4JSDestructureValidator,
	UnsupportedFeatureValidator,
	N4JSXReactBindingValidator
])
@Log
class N4JSXValidator extends InternalTypeSystemValidator {

	// validations are defined in composed validator classes
	/**
	 * Override to improve error message in case of abnormal termination of validation.
	 */
	override MethodWrapperCancelable createMethodWrapper(AbstractDeclarativeValidator instanceToUse, Method method) {
		return new MethodWrapperCancelable(instanceToUse, method) {

			override handleInvocationTargetException(Throwable targetException, State state) {

				// ignore GuardException, check is just not evaluated if guard is false
				// ignore NullPointerException, as not having to check for NPEs all the time is a convenience feature
				super.handleInvocationTargetException(targetException, state);
				if (targetException instanceof NullPointerException) {
					Exceptions.sneakyThrow(targetException)
				}
			}

			// catch exceptions and create better error message as org.eclipse.xtext.validation.CompositeEValidator.validate(EClass, EObject, DiagnosticChain, Map<Object, Object>)
			// note: cannot override validate method directly because it is final
			override void invoke(State state) {
				try {
					if (!isCanceled(state)) {
						super.invoke(state);
					}
				} catch (Exception e) {
					logger.error("Error executing EValidator", e);
					state.chain.add(
						new BasicDiagnostic(Diagnostic.ERROR, state.currentObject.toString(), 0, e.message, #[e]));
				}
			}
		};
	}

	override protected List<EPackage> getEPackages() {
		val List<EPackage> result = super.EPackages
		result.add(EPackage.Registry.INSTANCE.getEPackage("http://www.numberfour.eu/ide/n4jsx/N4JSX"));
		return result;
	}
}
