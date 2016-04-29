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

import com.google.inject.Injector;

import eu.numberfour.n4js.ui.internal.N4JSActivator;
import eu.numberfour.n4js.utils.injector.AbstractGuiceUIPlugin;

/**
 * Activator for the N4JS {@code npm} exporter UI plug-in in order to set up injector correctly.
 */
public class NpmExporterActivator extends AbstractGuiceUIPlugin {

	/** The unique global identifier of the plug-in. */
	public static final String PLUGIN_ID = "eu.numberfour.n4js.npmexporter.ui";

	/**
	 * Returns with the shared activator singleton.
	 *
	 * @return the shared activator instance.
	 */
	public static NpmExporterActivator getInstance() {
		return (NpmExporterActivator) INSTANCE;
	}

	@Override
	protected Injector getParentInjector(final String id) {
		return N4JSActivator.getInstance().getInjector(id);
	}
}
