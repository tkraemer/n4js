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
package eu.numberfour.n4js.tester.ui;

import static eu.numberfour.n4js.tester.ui.N4TesterUiModule.N4_TESTER_UI_MODULE_ID;
import static eu.numberfour.n4js.tester.ui.TesterUiActivator.PLUGIN_ID;

import com.google.inject.Injector;

import eu.numberfour.n4js.utils.N4ExecutableExtensionFactory;

/**
 * Executable extension factory for the N4 tester UI component.
 */
public class N4TesterUiExecutableExtensionFactory extends N4ExecutableExtensionFactory {

	@Override
	protected ClassLoader getClassLoader() {
		return TesterUiActivator.getDefault().getClass().getClassLoader();
	}

	@Override
	protected Injector getInjector() {
		return N4TesterUiModule.getInjector(N4_TESTER_UI_MODULE_ID);
	}

	@Override
	protected String getBunleId() {
		return PLUGIN_ID;
	}

}
