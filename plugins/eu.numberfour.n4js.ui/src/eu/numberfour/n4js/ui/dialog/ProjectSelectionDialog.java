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
package eu.numberfour.n4js.ui.dialog;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Shell;

import eu.numberfour.n4js.ui.dialog.virtualresource.WrappingVirtualContainer;

/**
 * Browse dialog to select a IProject of the current workspace.
 *
 * Returns values of type {@link IProject}
 */
public class ProjectSelectionDialog extends WorkspaceElementSelectionDialog {

	/**
	 * @param parent
	 *            Parent shell
	 */
	public ProjectSelectionDialog(Shell parent) {
		super(parent, false);
		this.addFilter(new ProjectFilter());
		WrappingVirtualContainer virtualRoot = new WrappingVirtualContainer(ResourcesPlugin.getWorkspace().getRoot());
		this.setInput(virtualRoot);
	}

	/**
	 * Filter to only show Projects in the dialog
	 */
	private static final class ProjectFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (!(element instanceof IProject)) {
				return false;
			}
			return true;
		}
	}
}
