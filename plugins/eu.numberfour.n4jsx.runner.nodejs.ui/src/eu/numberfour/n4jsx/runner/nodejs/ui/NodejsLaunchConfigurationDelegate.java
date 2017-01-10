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

import eu.numberfour.n4js.runner.nodejs.NodeRunner;
import eu.numberfour.n4js.runner.ui.IDERunnerDelegate;

/**
 * Note: This class must be triggered within n4jsx.runner.nodejs.ui in order for that dependency injection for n4jsx to
 * correctly.
 */
public class NodejsLaunchConfigurationDelegate extends IDERunnerDelegate {

	@Override
	public String getRunnerId() {
		return NodeRunner.ID;
	}
}
