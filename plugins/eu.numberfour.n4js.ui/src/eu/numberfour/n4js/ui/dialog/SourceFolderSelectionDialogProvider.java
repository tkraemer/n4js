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

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSSourceContainer;
import eu.numberfour.n4js.ui.dialog.virtualresource.VirtualResource;
import eu.numberfour.n4js.ui.dialog.virtualresource.WrappingVirtualContainer;

/**
 * Provides {@link SourceFolderSelectionDialog}s. Use the createDialog method to instantiate a new dialog.
 */
public class SourceFolderSelectionDialogProvider {

	@Inject
	private IN4JSCore n4jsCore;

	/**
	 * Filter to only let valid n4js source folders pass.
	 */
	public class SourceFolderFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof VirtualResource) {
				return true;
			}
			if (element instanceof IResource) {

				URI resourceURI = URI.createPlatformResourceURI(((IResource) element).getFullPath().toString(), true);
				Optional<? extends IN4JSSourceContainer> sourceContainer = n4jsCore
						.findN4JSSourceContainer(resourceURI);

				if (sourceContainer.isPresent() && sourceContainer.get().getLocation().equals(resourceURI)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Browse dialog to select a N4JSSourceFolder in a project.
	 *
	 * Use {@link SourceFolderSelectionDialogProvider} to instantiate this dialog.
	 *
	 * Returns values of type {@link IContainer} or {@link VirtualResource}s for virtually created source folders.
	 */
	public class SourceFolderSelectionDialog extends WorkspaceElementSelectionDialog {

		private final WrappingVirtualContainer treeRoot;
		private final IProject project;

		private final SourceFolderFilter sourceFolderFilter = new SourceFolderFilter();

		/**
		 * @param parent
		 *            The parent shell
		 * @param projectName
		 *            The name of the project to browse in for source folders
		 */
		public SourceFolderSelectionDialog(Shell parent, String projectName, IPath initialSelection) {
			super(parent, true);

			this.project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
			treeRoot = new WrappingVirtualContainer(this.project);

			if (initialSelection != null && !initialSelection.toString().equals("")) {
				IFolder folder = this.project.getFolder(initialSelection);

				// If the element passes the filter select it
				if (this.sourceFolderFilter.select(this.getTreeViewer(), null, folder)) {
					this.setInitialSelection(this.project.getFolder(initialSelection));
				}
				// If the element doesn't exist create a virtual resource representing it
				else if (!folder.exists()) {
					VirtualResource virtualSourceFolder = createVirtualSourceFolder(folder.getName());
					this.setInitialSelection(virtualSourceFolder);
				}
			}

			this.setInput(treeRoot);
			this.addFilter(sourceFolderFilter);
			this.setTitle("Select a source folder in project " + treeRoot.getName());

		}

		/**
		 * Creates a new virtual source folder and inserts it into the tree structure.
		 *
		 * @param name
		 *            The name of the new virtual folder
		 * @return The virtual resource
		 *
		 *         <p>
		 *         Note: This method also automatically inserts the resource into the tree. Do not try to insert it
		 *         again as this may lead to duplicates.
		 *         </p>
		 */
		private VirtualResource createVirtualSourceFolder(String name) {
			// ImageDescriptor sourceFolderImage = ResourceManager.getPluginImageDescriptor("eu.numberfour.n4js.ui",
			// "icons/new_source_folder.png");
			ImageDescriptor sourceFolderImage = PlatformUI.getWorkbench().getSharedImages()
					.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER);

			VirtualResource sourceFolder = new VirtualResource(name, sourceFolderImage);
			this.treeRoot.addChild(sourceFolder);
			sourceFolder.setParent(this.treeRoot);

			if (this.getTreeViewer() != null) {
				this.getTreeViewer().refresh();
			}

			return sourceFolder;
		}

		@Override
		public void createPressed() {
			IInputValidator sourceFolderValidator = new IInputValidator() {

				@Override
				public String isValid(String newText) {
					if (newText.equals("")) {
						return "The name must not be empty";
					}

					IFolder newFolder = project.getFolder(newText);
					if (newFolder.exists()) {
						return "A folder with this name already exists in the project directory";
					}

					return null;
				}
			};

			InputDialog dialog = new InputDialog(this.getShell(), "Create a new source folder",
					"Enter a new source folder name", "", sourceFolderValidator);
			dialog.open();

			for (VirtualResource resource : treeRoot.getVirtualChildren()) {
				treeRoot.removeChild(resource);
			}

			String result = dialog.getValue();

			if (sourceFolderValidator.isValid(result) == null) {
				VirtualResource createdSourceFolder = createVirtualSourceFolder(result);

				IStructuredSelection selection = new StructuredSelection(createdSourceFolder);
				this.getTreeViewer().setSelection(selection, true);
			}
		}

	}

	/**
	 * Create a new {@link SourceFolderSelectionDialog}.
	 *
	 * @param parent
	 *            Parent shell
	 * @param projectName
	 *            Name of the project to browse in for source folders
	 *
	 * @param initialSelection
	 *            IPath of the initially selected source folder
	 * @return The created dialog
	 */
	public WorkspaceElementSelectionDialog createDialog(Shell parent, String projectName, IPath initialSelection) {
		return new SourceFolderSelectionDialog(parent, projectName, initialSelection);
	}

}
