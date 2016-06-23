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

import org.eclipse.xtext.validation.IResourceValidator;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * A runtime module which combines {@link N4JSInjectorProviderWithMockProject.N4JSTestRuntimeModuleWithMockProject} and
 * additionally binds {@link N4JSInjectorProviderWithSuppressedValidator.FilteredResourceValidator} to
 * {@link IResourceValidator}.
 */
public class N4JSInjectorProviderMockProjectSuppressedValidator extends N4JSInjectorProvider {
	@Override
	protected Injector internalCreateInjector() {
		return new N4JSStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(
						new N4JSInjectorProviderWithMockProject.N4JSTestRuntimeModuleWithMockProject() {
							@Override
							public Class<? extends IResourceValidator> bindIResourceValidator() {
								return N4JSInjectorProviderWithSuppressedValidator.FilteredResourceValidator.class;
							}
						});
			}
		}.createInjectorAndDoEMFRegistration();
	}
}
