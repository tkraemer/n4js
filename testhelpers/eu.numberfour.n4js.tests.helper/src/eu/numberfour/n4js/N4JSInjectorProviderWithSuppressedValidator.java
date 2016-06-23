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

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Guice;
import com.google.inject.Injector;

import eu.numberfour.n4js.validation.IssueCodes;
import eu.numberfour.n4js.validation.N4JSResourceValidator;

/**
 * A N4JSInjectorProvider which injects a delegating IResourceValidator which filters out issues based on their issue
 * codes.
 */
public class N4JSInjectorProviderWithSuppressedValidator extends N4JSInjectorProvider {
	private static final List<String> SUPPRESSED_ISSUE_CODES = Arrays.asList(IssueCodes.AST_LOCAL_VAR_UNUSED);

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
	 * A test runtime module which binds a specific filtering resource validator.
	 */
	public static class N4JSSuppressedValidatorRuntimeModule extends N4JSTestRuntimeModule {
		@Override
		public Class<? extends IResourceValidator> bindIResourceValidator() {
			return FilteredResourceValidator.class;
		}
	}

	/**
	 * A resource validator which filters all suppressed issues before returning them.
	 *
	 */
	public static class FilteredResourceValidator extends N4JSResourceValidator {
		@Override
		protected void validate(Resource resource, CheckMode mode, CancelIndicator monitor, IAcceptor<Issue> acceptor) {
			super.validate(resource, mode, monitor, new IAcceptor<Issue>() {
				@Override
				public void accept(Issue t) {
					if (!SUPPRESSED_ISSUE_CODES.contains(t.getCode())) {
						acceptor.accept(t);
					}
				}
			});
		}
	}
}
