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
package eu.numberfour.n4jsx.runner.nodejs.ui;

import static eu.numberfour.n4jsx.runner.ui.RunnerUiModule.RUNNER_UI_MODULE_ID;

import com.google.inject.Injector;

import eu.numberfour.n4js.utils.N4ExecutableExtensionFactory;
import eu.numberfour.n4jsx.runner.ui.RunnerUiModule;

/**
 * Executable extension factory for the runner Node.js UI module. This class provides the right dependency injection
 * modules for N4JSX
 */
public class RunnerNodejsUiExecutableExtensionFactory extends N4ExecutableExtensionFactory {

	@Override
	protected ClassLoader getClassLoader() {
		return this.getClass().getClassLoader();
	}

	@Override
	protected Injector getInjector() {
		return RunnerUiModule.getInjector(RUNNER_UI_MODULE_ID);
	}

	@Override
	protected String getBunleId() {
		return NodejsRunnerUiActivator.PLUGIN_ID;
	}

}
