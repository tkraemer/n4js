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
package eu.numberfour.n4js.ui.utils;

import com.google.inject.Injector;

import eu.numberfour.n4js.ui.internal.N4JSActivator;
import eu.numberfour.n4js.utils.injector.AbstractGuiceUIPlugin;

/**
 * N4JS specific Guice aware UI plug-in.
 */
public class N4JSGuiceUIPlugin extends AbstractGuiceUIPlugin {

	@Override
	protected Injector getParentInjector(final String id) {
		return N4JSActivator.getInstance().getInjector(id);
	}

}
