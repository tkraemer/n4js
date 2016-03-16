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
package eu.numberfour.n4js.ui.wizard.components;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Composite;

/**
 * A container for wizard components.
 *
 * Wizard component containers are 3-column grid layouted swt composites. A component is meant to use one row of such a
 * layout.
 *
 */
public interface WizardComponentContainer {

	/**
	 * Get the global data binding context
	 */
	public DataBindingContext getDataBindingContext();

	/**
	 * Get the SWT composite which is used as component composite.
	 *
	 * <p>
	 * This is always a 3 column grid layouted composite
	 * </p>
	 */
	public Composite getComposite();

}
