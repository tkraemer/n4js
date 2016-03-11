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

import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.util.Modules2;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;

/***/
public class N4JSInjectorProviderWithIndex extends N4JSInjectorProvider {

	@Override
	protected Injector internalCreateInjector() {
		return new N4JSTestsStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(Modules2.mixin(new N4JSTestRuntimeModule() {
					@Override
					public void configureIResourceDescriptions(Binder binder) {
						binder.bind(IResourceDescriptions.class).to(EagerResourceSetBasedResourceDescriptions.class);
					}
				}, new N4JSTestsModule()));
			}
		}.createInjectorAndDoEMFRegistration();
	}

}
