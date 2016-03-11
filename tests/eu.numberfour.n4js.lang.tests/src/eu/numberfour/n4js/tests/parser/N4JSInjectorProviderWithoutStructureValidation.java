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
package eu.numberfour.n4js.tests.parser;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.diagnostics.IDiagnosticConsumer;

import com.google.inject.Guice;
import com.google.inject.Injector;

import eu.numberfour.n4js.N4JSInjectorProvider;
import eu.numberfour.n4js.N4JSRuntimeModule;
import eu.numberfour.n4js.N4JSStandaloneSetup;
import eu.numberfour.n4js.validation.ASTStructureValidator;

/**
 * Disables the structure validation to allow testing the semicolon injection with minimal overhead
 */
public class N4JSInjectorProviderWithoutStructureValidation extends N4JSInjectorProvider {

	@Override
	protected Injector internalCreateInjector() {
		return new N4JSStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(new N4JSRuntimeModule() {
					@SuppressWarnings("unused")
					public Class<? extends ASTStructureValidator> bindASTStructureValidator() {
						return NullASTStructureValidator.class;
					}
				});
			}
		}.createInjectorAndDoEMFRegistration();
	}

	/***/
	public static class NullASTStructureValidator extends ASTStructureValidator {
		@Override
		public void validate(EObject model, IDiagnosticConsumer consumer) {
			// no-op
		}
	}

}
