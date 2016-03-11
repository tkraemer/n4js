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
package eu.numberfour.n4js.runner.chrome.ui;

import eu.numberfour.n4js.runner.chrome.ChromeRunner;
import eu.numberfour.n4js.runner.ui.IDERunnerDelegate;

/**
 */
public class ChromeLaunchConfigurationDelegate extends IDERunnerDelegate {

	@Override
	public String getRunnerId() {
		return ChromeRunner.ID;
	}
}
