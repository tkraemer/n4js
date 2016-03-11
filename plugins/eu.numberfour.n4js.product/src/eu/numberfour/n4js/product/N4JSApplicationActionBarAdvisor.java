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
package eu.numberfour.n4js.product;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.ide.WorkbenchActionBuilder;

/**
 * Class for configuring the action and menu bar for the N4JS IDE application.
 */
@SuppressWarnings("restriction")
public class N4JSApplicationActionBarAdvisor extends ActionBarAdvisor {

	private final WorkbenchActionBuilder delegate;

	/**
	 * Constructor for creating a new action bar advisor for the application.
	 *
	 * @param configurer
	 *            the action bar configurer argument.
	 */
	public N4JSApplicationActionBarAdvisor(final IActionBarConfigurer configurer) {
		super(configurer);
		delegate = new WorkbenchActionBuilder(configurer);
	}

	@Override
	public IStatus saveState(final IMemento memento) {
		return delegate.saveState(memento);
	}

	@Override
	public void fillActionBars(final int flags) {
		delegate.fillActionBars(flags);
	}

	@Override
	public IStatus restoreState(final IMemento memento) {
		return delegate.restoreState(memento);
	}

	@Override
	public void dispose() {
		delegate.dispose();
	}

	@Override
	public boolean isApplicationMenu(final String menuId) {
		return delegate.isApplicationMenu(menuId);
	}

}
