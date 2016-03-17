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
package eu.numberfour.n4js.ui.wizard.workspacewizard;

/**
 * A wizard which provides the ability to create a new element inside an existing module.
 */
public interface InlineWorkspaceWizard {
	/**
	 * Sets whether the wizard fills the module file from the initial selection.
	 *
	 * If this option is disabled, only the module container of the given initial selection is used to fill in initial
	 * values.
	 *
	 * @param fillModuleFile
	 *            The new option value
	 */
	void setFillModuleFile(boolean fillModuleFile);
}
