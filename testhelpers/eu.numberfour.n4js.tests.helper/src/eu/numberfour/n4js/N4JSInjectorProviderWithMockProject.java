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

import com.google.inject.Guice;
import com.google.inject.Injector;

import eu.numberfour.n4js.internal.InternalN4JSWorkspace;
import eu.numberfour.n4js.internal.N4JSModel;
import eu.numberfour.n4js.projectModel.IN4JSCore;

/**
 */
public class N4JSInjectorProviderWithMockProject extends N4JSInjectorProvider {

	@Override
	protected Injector internalCreateInjector() {
		return new N4JSStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(new N4JSTestRuntimeModuleWithMockProject());
			}
		}.createInjectorAndDoEMFRegistration();
	}

	/***/
	public static class N4JSTestRuntimeModuleWithMockProject extends N4JSRuntimeModule {
		/**
		 * Configure the IN4JSCore instance to use the implementation that is backed by {@link java.io.File files}.
		 */
		@Override
		public Class<? extends IN4JSCore> bindN4JSCore() {
			return N4JSRuntimeCoreWithMockProject.class;
		}

		/**
		 * Configure the IN4JSCore instance to use the implementation that is backed by {@link java.io.File files}.
		 */
		@Override
		public Class<? extends InternalN4JSWorkspace> bindInternalN4JSWorkspace() {
			return MockWorkspace.class;
		}

		/***/
		public Class<? extends N4JSModel> bindN4JSModel() {
			return MockN4JSModel.class;
		}

	}
}
