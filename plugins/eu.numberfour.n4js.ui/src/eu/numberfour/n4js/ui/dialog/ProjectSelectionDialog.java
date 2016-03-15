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
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.ui.dialog.virtualresource.WrappingVirtualContainer;

/**
 * Browse dialog to select a IProject of the current workspace.
 *
 * Returns values of type {@link IProject}
 */
public class ProjectSelectionDialog extends WorkspaceElementSelectionDialog {

	@Inject
	IN4JSCore n4jsCore;

	/**
	 * Create a new project selection dialog
	 */
	public ProjectSelectionDialog() {
		super(Display.getCurrent().getActiveShell(), false);
		this.addFilter(new ProjectFilter());
		WrappingVirtualContainer virtualRoot = new WrappingVirtualContainer(
				ResourcesPlugin.getWorkspace().getRoot());
		this.setInput(virtualRoot);
	}

	/**
	 * Filter to only show N4JS Projects in the dialog
	 */
	private final class ProjectFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			// Filter for open existing n4js projects only
			if (element instanceof IProject) {
				final URI uri = URI.createPlatformResourceURI(((IProject) element).getFullPath().toString(), true);
				final Optional<? extends IN4JSProject> project = n4jsCore.findProject(uri);
				return project.isPresent() && project.get().exists() && ((IProject) element).isOpen();
			}
			return false;
		}
	}
}
