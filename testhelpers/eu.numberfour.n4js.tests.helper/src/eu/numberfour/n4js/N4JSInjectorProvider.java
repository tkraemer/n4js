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

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.junit4.GlobalRegistries;
import org.eclipse.xtext.junit4.GlobalRegistries.GlobalStateMemento;
import org.eclipse.xtext.junit4.IInjectorProvider;
import org.eclipse.xtext.junit4.IRegistryConfigurator;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.util.ResourceHelper;
import org.eclipse.xtext.service.SingletonBinding;
import org.eclipse.xtext.util.LazyStringInputStream;
import org.eclipse.xtext.validation.DiagnosticConverterImpl;

import com.google.common.collect.Sets;
import com.google.common.io.CharStreams;
import com.google.inject.Guice;
import com.google.inject.Injector;

import eu.numberfour.n4js.n4JS.Script;

/***/
public class N4JSInjectorProvider implements IInjectorProvider, IRegistryConfigurator {

	/***/
	protected GlobalStateMemento stateBeforeInjectorCreation;
	/***/
	protected GlobalStateMemento stateAfterInjectorCreation;
	/***/
	protected Injector injector;

	static {
		GlobalRegistries.initializeDefaults();
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
	protected Injector internalCreateInjector() {
		return new N4JSStandaloneSetup() {
			@Override
			public Injector createInjector() {
				return Guice.createInjector(new N4JSTestRuntimeModule());
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
	public static class N4JSTestRuntimeModule extends N4JSRuntimeModule {
		/***/
		public Class<? extends ParseHelper<Script>> bindParseHelper() {
			return SmokeTestWriter.class;
		}

		/***/
		public Class<? extends N4JSParseHelper> bindN4JSParseHelper() {
			return SmokeTestWriter.class;
		}

		/***/
		@SingletonBinding
		public Class<? extends ResourceHelper> bindResourceHelper() {
			return ResourceHelper.class;
		}

		/***/
		public Class<? extends DiagnosticConverterImpl> bindDiagnosticConverter() {
			return ExceptionAwareDiagnosticConverter.class;
		}
	}

	/***/
	public static class SmokeTestWriter extends N4JSParseHelper {

		/***/
		public static boolean active = Boolean.getBoolean("SmokeTestWriter");

		static int counter = 1;

		static Set<String> seen = Sets.newHashSet();

		@Override
		public Script parse(InputStream in, URI uriToUse, Map<?, ?> options, ResourceSet resourceSet) {
			if (active) {
				if (in instanceof LazyStringInputStream) {
					try {
						String string = ((LazyStringInputStream) in).getString();
						if (string.length() < 1000 && seen.add(string)) {
							List<String> lines = CharStreams.readLines(new StringReader(string));
							if (lines.size() < 50) {
								System.out.println("\t@Test");
								System.out.format("\tdef void test_%04d() {", counter++);
								System.out.println();
								System.out.println("\t\t'''");
								for (String s : lines) {
									System.out.print("\t\t\t");
									System.out.println(s);
								}
								System.out.println("\t\t'''.assertNoException");
								System.out.println("\t}");
								System.out.println();
							}
						}
					} catch (IOException e) {
						// ignore
					}
				}
			}
			return super.parse(in, uriToUse, options, resourceSet);
		}
	}
}
