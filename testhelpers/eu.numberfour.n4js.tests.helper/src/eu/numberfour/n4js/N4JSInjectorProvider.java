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

import org.eclipse.xtext.junit4.GlobalRegistries;
import org.eclipse.xtext.junit4.GlobalRegistries.GlobalStateMemento;
import org.eclipse.xtext.junit4.IInjectorProvider;
import org.eclipse.xtext.junit4.IRegistryConfigurator;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.util.ResourceHelper;
import org.eclipse.xtext.service.AbstractGenericModule;
import org.eclipse.xtext.service.SingletonBinding;
import org.eclipse.xtext.validation.IDiagnosticConverter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import eu.numberfour.n4js.n4JS.Script;

/***/
public class N4JSInjectorProvider implements IInjectorProvider, IRegistryConfigurator {

	/***/
	protected GlobalStateMemento stateBeforeInjectorCreation;
	/***/
	protected GlobalStateMemento stateAfterInjectorCreation;
	/***/
	protected Injector injector;

	private Module runtimeModule = new N4JSRuntimeModule();

	static {
		GlobalRegistries.initializeDefaults();
	}

	/** Default constructor */
	public N4JSInjectorProvider() {
		this(new DefaultTestModule());
	}

	/**
	 * Creates a new injector combining all of the given runtime modules
	 */
	public N4JSInjectorProvider(Module... modules) {
		this.runtimeModule = Modules.override(runtimeModule).with(Arrays.asList(modules));
	}

	@Override
	public Injector getInjector() {
		if (injector == null) {
			stateBeforeInjectorCreation = GlobalRegistries.makeCopyOfGlobalState();
			this.injector = internalCreateInjector();
			stateAfterInjectorCreation = GlobalRegistries.makeCopyOfGlobalState();
		}
		return injector;
	}

	/***/
	private Injector internalCreateInjector() {
		return new N4JSStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(runtimeModule);
			}
		}.createInjectorAndDoEMFRegistration();
	}

	@Override
	public void restoreRegistry() {
		stateBeforeInjectorCreation.restoreGlobalState();
	}

	@Override
	public void setupRegistry() {
		getInjector();
		stateAfterInjectorCreation.restoreGlobalState();
	}

	/***/
	public static class DefaultTestModule extends AbstractGenericModule {
		/** */
		public Class<? extends IDiagnosticConverter> bindDiagnosticConverter() {
			return ExceptionAwareDiagnosticConverter.class;
		}

		/** */
		public Class<? extends N4JSParseHelper> bindN4JSParseHelper() {
			return SmokeTestWriter.class;
		}

		/** */
		@SingletonBinding
		public Class<? extends ResourceHelper> bindResourceHelper() {
			return ResourceHelper.class;
		}

		/** */
		public Class<? extends ParseHelper<Script>> bindParseHelperScript() {
			return SmokeTestWriter.class;
		}
	}
}
