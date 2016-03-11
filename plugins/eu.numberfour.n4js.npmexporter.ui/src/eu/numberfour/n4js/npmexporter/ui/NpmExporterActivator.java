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
package eu.numberfour.n4js.npmexporter.ui;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.BundleContext;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import eu.numberfour.n4js.ui.internal.N4JSActivator;

/**
 * Activator for JSDoc2Spec in order to set up injector correctly.
 */
public class NpmExporterActivator extends AbstractUIPlugin {

	private static NpmExporterActivator INSTANCE;

	/** global id */
	public static final String PLUGIN_ID = "eu.numberfour.n4js.npmexporter.ui";

	private static final Logger logger = Logger.getLogger(NpmExporterActivator.class);

	private final Map<String, Injector> injectors = Collections.synchronizedMap(Maps
			.<String, Injector> newHashMapWithExpectedSize(1));

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		INSTANCE = null;
		super.stop(context);
	}

	/**
	 * Returns this activator singleton.
	 */
	public static NpmExporterActivator getInstance() {
		return INSTANCE;
	}

	/**
	 * Creates injector, cf. {@link N4JSActivator#createInjector(String)}
	 */
	@SuppressWarnings("javadoc")
	protected Injector createInjector(String language) {
		try {
			Module runtimeModule = getRuntimeModule(language);
			Module sharedStateModule = getSharedStateModule();
			Module envModule = getEnvModule(language);
			Module uiModule = getUiModule(language);
			Module mergedModule = Modules2.mixin(runtimeModule, sharedStateModule, envModule, uiModule);
			return Guice.createInjector(mergedModule);
		} catch (Exception e) {
			logger.error("Failed to create injector for " + language);
			logger.error(e.getMessage(), e);
			throw new RuntimeException("Failed to create injector for " + language, e);
		}
	}

	private Module getEnvModule(String grammar) {
		if (N4JSActivator.EU_NUMBERFOUR_N4JS_N4JS.equals(grammar)) {
			return new eu.numberfour.n4js.environments.ContributingModule();
		}

		throw new IllegalArgumentException(grammar);
	}

	/**
	 * Returns injector for given language, cf. {@link N4JSActivator#getInjector(String)}
	 */
	public Injector getInjector(String language) {
		synchronized (injectors) {
			Injector injector = injectors.get(language);
			if (injector == null) {
				injectors.put(language, injector = createInjector(language));
			}
			return injector;
		}
	}

	private Module getRuntimeModule(String grammar) {
		if (N4JSActivator.EU_NUMBERFOUR_N4JS_N4JS.equals(grammar)) {
			return new eu.numberfour.n4js.N4JSRuntimeModule();
		}

		throw new IllegalArgumentException(grammar);
	}

	private Module getUiModule(String grammar) {
		if (N4JSActivator.EU_NUMBERFOUR_N4JS_N4JS.equals(grammar)) {
			return new eu.numberfour.n4js.ui.N4JSUiModule(this);
		}

		throw new IllegalArgumentException(grammar);
	}

	private Module getSharedStateModule() {
		return new SharedStateModule();
	}

}
