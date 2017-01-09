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
package eu.numberfour.n4jsx.runner.ui;

import static com.google.common.cache.CacheBuilder.newBuilder;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;

import eu.numberfour.n4jsx.ui.internal.N4JSXActivator;

/**
 * Module for the N4 runner UI.
 */
public class RunnerUiModule extends AbstractModule {

	/**
	 * The ID of the runner UI module.
	 */
	public static final String RUNNER_UI_MODULE_ID = RunnerUiModule.class.getName();

	private static final LoadingCache<String, Injector> INJECTORS = newBuilder().build(
			new CacheLoader<String, Injector>() {
				@Override
				public Injector load(final String moduleId) throws Exception {
					if (RUNNER_UI_MODULE_ID.equals(moduleId)) {
						final Injector parentInjector = N4JSXActivator.getInstance().getInjector(
								N4JSXActivator.EU_NUMBERFOUR_N4JSX_N4JSX);
						return parentInjector.createChildInjector(new RunnerUiModule());
					}
					throw new IllegalArgumentException("Unknown module ID: " + moduleId);
				}
			});

	/**
	 * Returns with the injector instance for a particular module grammar given as the unique module ID argument.
	 *
	 * @param moduleId
	 *            the unique module ID.
	 * @return the injector instance. Never {@code null}.
	 */
	public static Injector getInjector(final String moduleId) {
		return INJECTORS.getUnchecked(moduleId);
	}

	@Override
	protected void configure() {
		// Does nothing specific.
	}

}
