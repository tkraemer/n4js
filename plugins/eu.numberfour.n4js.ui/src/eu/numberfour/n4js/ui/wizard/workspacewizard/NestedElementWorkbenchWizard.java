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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

/**
 * A wizard which provides the ability to create a new element which is nested inside an existing module
 */
public interface NestedElementWorkbenchWizard {
	/**
	 * Initializes this creation wizard using the passed workbench and object selection. The additional nested parameter
	 * can be used to specify whether the selection should be used to also infer the module file in which the new
	 * element should be created in.
	 *
	 * Also see {@link IWorkbenchWizard#init(IWorkbench, IStructuredSelection)}
	 *
	 * @param workbench
	 *            The current workbench
	 * @param selection
	 *            An object selection
	 * @param nested
	 *            Infer module file
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection, boolean nested);
}
