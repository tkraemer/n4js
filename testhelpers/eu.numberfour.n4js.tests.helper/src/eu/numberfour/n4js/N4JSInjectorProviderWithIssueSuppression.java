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
package eu.numberfour.n4js;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.DiagnosticConverterImpl;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Guice;
import com.google.inject.Injector;

import eu.numberfour.n4js.validation.helper.N4JSLanguageConstants;

/**
 * A N4JSInjectorProvider which binds a custom {@link IResourceValidator} which filters the diagnosed issues.
 *
 * This can be used to suppress certain issues based on their issue codes. See
 * {@code N4JSLanguageConstants.DEFAULT_SUPPRESSED_ISSUE_CODES_FOR_TESTING} for configuration.
 *
 */
public class N4JSInjectorProviderWithIssueSuppression extends N4JSInjectorProvider {

	@Override
	protected Injector internalCreateInjector() {
		return new N4JSStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(new N4JSSuppressedValidatorRuntimeModule());
			}
		}.createInjectorAndDoEMFRegistration();
	}

	/**
	 * A test runtime module which binds a custom filtering resource validator.
	 */
	public static class N4JSSuppressedValidatorRuntimeModule extends N4JSTestRuntimeModule {
		@Override
		public Class<? extends DiagnosticConverterImpl> bindDiagnosticConverter() {
			return FilteringDiagnosticConverter.class;
		}
	}

	/**
	 * A diagnostic converter which filters incoming diagnostics before passing them to the super implementations.
	 */
	public static class FilteringDiagnosticConverter extends DiagnosticConverterImpl {
		@Override
		public void convertValidatorDiagnostic(Diagnostic diagnostic, IAcceptor<Issue> acceptor) {
			super.convertValidatorDiagnostic(diagnostic, new IAcceptor<Issue>() {
				@Override
				public void accept(Issue t) {
					if (!N4JSLanguageConstants.DEFAULT_SUPPRESSED_ISSUE_CODES_FOR_TESTS.contains(t.getCode())) {
						acceptor.accept(t);
					}
				}
			});
		}
	}
}
