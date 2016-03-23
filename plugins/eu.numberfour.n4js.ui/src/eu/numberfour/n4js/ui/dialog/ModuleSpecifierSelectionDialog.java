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

import java.util.Arrays;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.IWorkbenchAdapter;

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.ui.dialog.virtualresource.VirtualContainer;
import eu.numberfour.n4js.ui.dialog.virtualresource.VirtualResource;
import eu.numberfour.n4js.ui.dialog.virtualresource.WrappingVirtualContainer;
import eu.numberfour.n4js.ui.wizard.classifiers.N4JSClassifierWizardModelValidator;

/**
 * Browse dialog to select and create module folders inside of a given source folder location.
 *
 * Offers create functionality what means that the user is able to create new module folders. No changes are made to the
 * file system only virtual folders are displayed to reflect the changes.
 *
 * Returns values of type {@link String} that are a representing of the path of the selected module folder. The path may
 * not exist in case that the user has created new folders.
 */
public class ModuleSpecifierSelectionDialog extends CustomElementSelectionDialog {

	private final static String MODULE_ELEMENT_NAME = "Module:";
	private final static String CREATE_FOLDER_LABEL = "Create Folder";

	private final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
	private final VirtualContainer sourceFolder;
	private final WrappingVirtualContainer treeRoot;

	private final ModuleFileValidator inputValidator = new ModuleFileValidator();

	private String defaultFileExtension = N4JSGlobals.N4JS_FILE_EXTENSION;

	/**
	 * Create the dialog.
	 *
	 * <p>
	 * Note: The model should have a valid source folder path as this is the root folder for this browse dialog. If the
	 * source folder path doesn't exist yet this dialog will handle it as a to be created location and will still allow
	 * module folder creation.
	 * </p>
	 *
	 * @param parent
	 *            Parent Shell  
	 * @param sourceFolder
	 *            The source folder to browse in for modules and module folders
	 *
	 *
	 */
	public ModuleSpecifierSelectionDialog(Shell parent, IPath sourceFolder) {
		super(parent, MODULE_ELEMENT_NAME, CREATE_FOLDER_LABEL);
		this.setTitle("Select a module");

		this.setInputValidator(inputValidator);

		IPath parentPath = sourceFolder.removeLastSegments(1);
		IContainer sourceFolderParent = containerForPath(parentPath);
		IFolder workspaceSourceFolder = workspaceRoot
				.getFolder(sourceFolder);

		// Use parent of source folder as root to show source folder itself in the tree
		this.treeRoot = new WrappingVirtualContainer(sourceFolderParent);

		if (workspaceSourceFolder.exists()) {
			this.sourceFolder = new WrappingVirtualContainer(workspaceSourceFolder);
			this.addFilter(new ModuleFolderFilter(this.sourceFolder.getFullPath()));
		} else {
			// If the source folder doesn't exist, show it as a virtual resource
			this.sourceFolder = new VirtualContainer(sourceFolder.lastSegment());
			this.addFilter(new ModuleFolderFilter(parentPath.append(this.sourceFolder.getFullPath())));
		}

		this.setAutoExpandLevel(2);
		// Show the status line above the buttons
		this.setStatusLineAboveButtons(true);
	}

	/** Return the workspace container with the path */
	private IContainer containerForPath(IPath path) {
		if (path.segmentCount() == 1) {
			return workspaceRoot.getProject(path.segment(0));
		} else {
			return workspaceRoot.getFolder(path);
		}
	}

	/**
	 * Convenience method to get the basepath of a tree element.
	 *
	 * The basepath is the full path for containers and the full path of their parent for resources.
	 *
	 * @param treeElement
	 *            A tree element
	 * @return The basepath of the element.
	 */
	private String basepathForTreeElement(Object treeElement) {
		if (treeElement instanceof IContainer) {
			return ((IContainer) treeElement).getFullPath().toString();
		} else if (treeElement instanceof IResource) {
			return ((IResource) treeElement).getParent().getFullPath().toString();
		} else if (treeElement instanceof VirtualContainer) {
			return ((VirtualContainer) treeElement).getFullPath().toString();
		} else if (treeElement instanceof VirtualResource) {
			return ((VirtualResource) ((VirtualResource) treeElement).getParent(treeElement))
					.getFullPath().toString();
		} else {
			return null;
		}
	}

	/**
	 * Set the selected element of the tree view.
	 *
	 * @param resource
	 *            The virtual resource to select
	 */
	private void setSelectedElement(VirtualResource resource) {
		this.treeViewer.setSelection(new StructuredSelection(resource));
	}

	/**
	 * Set the selected element. Clears all virtual children from the tree.
	 *
	 * @param resource
	 *            The existing resource to select
	 */
	private void setSelectedElement(IResource resource) {
		this.treeRoot.clearVirtualChildren();
		this.treeViewer.refresh();
		this.treeViewer.setSelection(new StructuredSelection(resource));
	}

	/**
	 * Insert a virtual resource at given container for the actual tree root.
	 *
	 * @param name
	 *            The name of the new virtual resource
	 * @param container
	 *            The existing container of the new resource
	 * @return The new virtual resource
	 */
	private VirtualResource insertVirtualResourceAt(String name, IContainer container) {
		return VirtualResource.insertAt(
				new VirtualResource(name, VirtualResource.RESOURCE_ICON),
				container, this.treeRoot);
	}

	/**
	 * Insert a virtual resource at given container for the actual tree root.
	 *
	 * @param name
	 *            The name of the new virtual resource
	 * @param container
	 *            The virtual container of the new resource
	 * @return The new virtual resource
	 */
	private VirtualResource insertVirtualResourceAt(String name, VirtualContainer container) {
		container.clearVirtualChildren();
		return VirtualResource.insertAt(
				new VirtualResource(name, VirtualResource.RESOURCE_ICON),
				container);
	}

	@Override
	protected void elementInputChanged() {
		String elementName = elementNameInput.getText() + elementNameInput.getSuffix();
		Object treeViewSelection = treeViewer.getStructuredSelection().getFirstElement();

		boolean isValidName = inputValidator.isValid(elementName) == null;
		String basepath = basepathForTreeElement(treeViewSelection);
		boolean isExistingSelection = (treeViewSelection instanceof IResource);

		if (!isValidName) { // Do nothing on invalid input
			return;
		}

		if (basepath == null) { // basepath shouldn't be null.
			return;
		}

		IResource workspaceModuleResource = workspaceRoot.findMember(new Path(basepath).append(elementName));

		// The basepath + module name points to an existing file
		if (workspaceModuleResource != null && workspaceModuleResource.exists()) {
			if (isExistingSelection) { // An existing element is selected in the tree view
				if (treeViewSelection instanceof IContainer) {
					setSelectedElement(workspaceModuleResource); // Select the existing child of the container
					return;
				} else if (treeViewSelection instanceof IResource) {
					// The basepath + module name points to the already selected element
					if (((IResource) treeViewSelection).getName().equals(elementName)) {
						this.treeRoot.clearVirtualChildren();
						this.treeViewer.refresh();
						return;
					} else { // The selected element is different from the basepath + module name
						setSelectedElement(workspaceModuleResource);
						return;
					}
				} else {
					// Encountered unknown tree element type
					return;
				}
			} else { // An non-existent element is selected in the tree view
				setSelectedElement(workspaceModuleResource);
			}
		} else { // The basepath + module name points to an non-existent file
			if (isExistingSelection) { // An existing element is selected in the tree view
				if (treeViewSelection instanceof IContainer) { // An existing container is selected
					VirtualResource res = insertVirtualResourceAt(elementName, (IContainer) treeViewSelection);
					this.treeViewer.refresh();
					this.setSelectedElement(res);
					return;
				} else if (treeViewSelection instanceof IResource) { // An existing resource is selected
					VirtualResource res = insertVirtualResourceAt(elementName,
							((IResource) treeViewSelection).getParent());
					this.treeViewer.refresh();
					this.setSelectedElement(res);
					return;
				}
			} else { // A non-existent element is selected in the tree view
				if (treeViewSelection instanceof VirtualContainer) { // A virtual container is selected.
					VirtualResource res = insertVirtualResourceAt(elementName, (VirtualContainer) treeViewSelection);
					this.treeViewer.refresh();
					this.setSelectedElement(res);
				} else if (treeViewSelection instanceof VirtualResource) { // A virtual resource is selected
					// If the names are equal don't do anything
					if (((VirtualResource) treeViewSelection).getName().equals(elementName)) {
						return;
					} else { // Otherwise rename the selected virtual resource
						((VirtualResource) treeViewSelection).setName(elementName);
						this.treeViewer.refresh();
						return;
					}
				}
			}
		}
	}

	private VirtualContainer appendVirtualContainer(VirtualContainer tail, String containerName) {
		VirtualContainer container = new VirtualContainer(containerName);
		tail.addChild(container);
		return container;
	}

	private VirtualContainer appendWrappedContainer(VirtualContainer tail, IContainer container) {
		VirtualContainer wrappedContainer = new WrappingVirtualContainer(container);
		tail.addChild(wrappedContainer);
		return wrappedContainer;
	}

	private VirtualResource appendVirtualResource(VirtualContainer tail, String resourceName) {
		VirtualResource resource = new VirtualResource(resourceName, VirtualResource.RESOURCE_ICON);
		tail.addChild(resource);
		return resource;
	}

	/**
	 * Compute the initial selection object from a given module specifier.
	 *
	 * @param initialModuleSpecifier
	 *            The module specifier
	 * @return A {@link IWorkbenchAdapter} adaptable object or null on failure
	 */
	public Object computeInitialSelection(String initialModuleSpecifier) {

		IPath projectFolderPath = sourceFolder.getFullPath().removeLastSegments(1);
		IPath sourceFolderPath = sourceFolder.getFullPath();

		if (initialModuleSpecifier.isEmpty()) {
			return this.sourceFolder;
		}

		IPath fullModuleSpecifierPath = sourceFolderPath.append(initialModuleSpecifier);

		// If the initialModuleSpecifier is a valid module path
		IResource existingSelection = computeExistingModuleSpecifierSelection(fullModuleSpecifierPath);

		if (existingSelection != null) {
			return existingSelection;
		} else { // Otherwise resolve path and create virtual resources

			VirtualContainer parent = this.treeRoot; // Is a wrapping container

			IPath rootRelativePath = fullModuleSpecifierPath.makeRelativeTo(projectFolderPath);

			String[] segments = rootRelativePath.segments();

			/*
			 * Iterate through all specifier segements. If a segment points to an existing location, replace it with a
			 * WrappingVirtualContainer. If a segment has no file system equivalent, create a new virtual resource.
			 */
			for (int i = 0; i < segments.length; i++) {
				final String segment = segments[i];

				// For the last segment if it represents a file not a folder
				if (i == segments.length - 1 && isModuleFileSpecifier(rootRelativePath)) {
					return appendVirtualResource(parent, segment);
				} else { // For the other segments
					// parent is a wrapping container
					if (parent instanceof WrappingVirtualContainer) {
						IContainer wrappedContainer = ((WrappingVirtualContainer) parent).getWrappedContainer();
						IResource segmentResource = wrappedContainer.findMember(segment);

						// If the segment represents an existing container in the file system
						if (segmentResource != null && segmentResource.exists()) {

							if (segmentResource instanceof IContainer) {
								// Create a new wrapping container and continue with the next segment
								parent = appendWrappedContainer(parent, (IContainer) segmentResource);
								continue;
							} else { // Otherwise the module specifier is invalid, as it contains a folder segment which
										// is
										// a file on the file system
								return parent;
							}
						}
					}
					// parent is a purely virtual container
					parent = appendVirtualContainer(parent, segment);

				}

			}

			return parent;
		}

	}

	private IResource computeExistingModuleSpecifierSelection(IPath moduleSpecifierPath) {
		if (isModuleFileSpecifier(moduleSpecifierPath)) {
			IFile existingFile = workspaceRoot.getFile(moduleSpecifierPath);
			return existingFile.exists() ? existingFile : null;
		} else {
			IContainer container = workspaceRoot.getFolder(moduleSpecifierPath);
			if (container.exists()) {
				return container;
			}
		}
		return null;
	}

	/**
	 * Returns true if the path is a module file specifier.
	 *
	 * <p>
	 * Note: A module file specifier doesn't only specify the container of the new class file but also the file in which
	 * the class is contained in.
	 * </p>
	 *
	 * @param path
	 *            The path to examine
	 * @return True if the path also specifies the file
	 */
	private static boolean isModuleFileSpecifier(IPath path) {
		if (path.segmentCount() < 1) {
			return false;
		}
		String stringPath = path.toString();
		return stringPath.charAt(stringPath.length() - 1) != '/';
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		// If the initial selection is empty select the root source folder
		if (getInitialElementSelections().isEmpty()) {
			// Handle case of a non-existing virtual source folder
			if (this.sourceFolder instanceof WrappingVirtualContainer) {
				setInitialSelection(((WrappingVirtualContainer) this.sourceFolder).getWrappedContainer());
			} else {
				setInitialSelection(this.sourceFolder);
				// Add the virtual source folder resource to the tree root
				this.treeRoot.addChild(this.sourceFolder);
			}
		}

		Control dialog = super.createDialogArea(parent);

		elementNameInput.setSuffix("." + this.defaultFileExtension);

		this.setInput(this.treeRoot);

		// Sync the tree view selection with the element name input
		this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				syncElementInput(treeViewer.getStructuredSelection().getFirstElement());
			}
		});

		// Refire initial selection change
		this.treeViewer.setSelection(this.treeViewer.getSelection());

		// Make initial sync between the tree view and the text input
		Object selectedElement = this.treeViewer.getStructuredSelection().getFirstElement();
		if (selectedElement instanceof IFile) {
			updateElementNameInput(((IResource) selectedElement).getName());
		} else if (selectedElement instanceof VirtualResource && !(selectedElement instanceof VirtualContainer)) {
			updateElementNameInput(((VirtualResource) selectedElement).getName());
		} else {
			validateElementInput();
		}

		elementNameInput.setFocus();

		return dialog;
	}

	/**
	 * Sync the element name input with the tree view selection.
	 *
	 * @param selection
	 *            The selected element of the tree view
	 */
	private void syncElementInput(Object selection) {
		if (selection == null) {
			return;
		}
		if (selection instanceof IFile) {
			String selectedFileExtension = ((IFile) selection).getFileExtension();
			if (!selectedFileExtension.equals(this.defaultFileExtension)) {
				elementNameInput.setSuffix("." + selectedFileExtension);
			} else {
				elementNameInput.setSuffix("." + this.defaultFileExtension);
			}

			updateElementNameInput(((IFile) selection).getFullPath().removeFileExtension().lastSegment());
		} else if (selection instanceof IContainer) {
			treeRoot.clearVirtualChildren();
			elementInputChanged();
		} else {
			elementInputChanged();
		}
	}

	private void updateElementNameInput(String value) {
		if (!elementNameInput.getText().equals(value)) {
			elementNameInput.setText(value);
		}
	}

	/**
	 * Return the source folder relative path of given resource
	 *
	 * @param resource
	 *            The file system resource
	 * @return The systems path relative to the source folder
	 */
	private IPath sourceFolderRelativePath(IResource resource) {
		return resource.getFullPath().makeRelativeTo(this.sourceFolder.getFullPath());
	}

	/**
	 * Return the source folder relative path of given virtual resource
	 *
	 * @param resource
	 *            The virtual resource
	 * @return The resources path relative to the source folder
	 */
	private IPath sourceFolderRelativePath(VirtualResource resource) {
		return resource.getFullPath().makeRelativeTo(this.sourceFolder.getFullPath());
	}

	/**
	 * Open a dialog to create a new module folder
	 */
	@Override
	protected void createPressed() {
		InputDialog dialog = new InputDialog(this.getShell(), "Create a new module folder",
				"Enter the module folder name.", "",
				new ModuleFolderValidator());
		dialog.open();

		if (dialog.getReturnCode() == CANCEL) {
			return;
		}

		Object firstElement = this.treeViewer.getStructuredSelection().getFirstElement();

		// If nothing is selected use the root source folder
		if (firstElement == null) {
			firstElement = this.sourceFolder;
		}

		if (firstElement instanceof VirtualContainer) {
			((VirtualContainer) firstElement).clearVirtualChildren();
		}

		if (firstElement instanceof VirtualResource && !(firstElement instanceof VirtualContainer)) {
			firstElement = ((VirtualResource) firstElement).getParent(firstElement);
			((VirtualContainer) firstElement).clearVirtualChildren();
		}
		if (firstElement instanceof IFile) {
			firstElement = ((IFile) firstElement).getParent();
		}

		VirtualResource newModuleContainer = insertVirtualContainerAtObject(dialog.getValue(), firstElement);

		this.treeViewer.refresh();
		this.treeViewer.setSelection(new StructuredSelection(newModuleContainer), true);
	}

	private VirtualContainer insertVirtualContainerAtObject(String name, Object container) {
		if (container instanceof IContainer) {
			return (VirtualContainer) VirtualResource.insertAt(
					new VirtualContainer(name),
					(IContainer) container, this.treeRoot);
		} else if (container instanceof VirtualResource) {
			return (VirtualContainer) VirtualResource.insertAt(
					new VirtualContainer(name),
					(VirtualResource) container);
		} else {
			return null;
		}
	}

	/**
	 * Filter to only show module containers (=folders) with a specified base path.
	 *
	 * <p>
	 * Note: The prefix can be used to limit the scope of the selection. E.g. the prefix "/project/src" would limit the
	 * selection only to elements like "/project/src/A","/project/src/B","/project/src/A/C" etc.
	 *
	 * Note that also the prefix folder itself will be shown. In this example this means that there is a root folder
	 * called "src"
	 * </p>
	 */
	public static class ModuleFolderFilter extends ViewerFilter {

		private final IPath prefix;

		/**
		 * Create a filter for given prefix path condition
		 *
		 * @param prefix
		 *            Path prefix
		 */
		public ModuleFolderFilter(IPath prefix) {
			this.prefix = prefix;
		}

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof IContainer) {
				IPath p = ((IContainer) element).getFullPath();
				return this.prefix.isPrefixOf(p);
			}
			if (element instanceof VirtualResource) {
				IPath p = ((VirtualResource) element).getFullPath();
				return this.prefix.isPrefixOf(p);
			}
			if (element instanceof IFile) {
				IPath p = ((IFile) element).getFullPath();
				return isN4JSResource(p) && this.prefix.isPrefixOf(p);
			}
			return false;
		}

		private boolean isN4JSResource(IPath path) {
			String fileExtension = path.getFileExtension();
			return null != fileExtension &&
					(fileExtension.equals(N4JSGlobals.N4JS_FILE_EXTENSION)
							|| fileExtension.equals(N4JSGlobals.N4JSD_FILE_EXTENSION));
		}

	}

	@Override
	protected void computeResult() {

		Object selection = treeViewer.getStructuredSelection().getFirstElement();

		if (selection == null) {
			return;
		}

		// Handle real folders as well as virtual folders as result values
		// Return them as source folder relative string paths
		if (selection instanceof IContainer) {
			/*
			 * Don't add the slash for an empty result
			 */
			if (sourceFolderRelativePath((IContainer) selection).segmentCount() < 1) {
				this.setResult(Arrays.asList(""));
				return;
			}
			this.setResult(Arrays.asList(sourceFolderRelativePath((IContainer) selection).toString() + "/"));
			return;
		} else if (selection instanceof IFile) {
			IPath fileSpec = sourceFolderRelativePath((IResource) selection);
			this.setResult(Arrays.asList(fileSpec.toString()));
			return;
		} else if (selection instanceof VirtualContainer) {
			IPath containerPath = sourceFolderRelativePath((VirtualContainer) selection);
			if (containerPath.isEmpty() || containerPath.equals("/")) {
				this.setResult(Arrays.asList(""));
				return;
			} else {
				this.setResult(Arrays.asList(containerPath.toString() + "/"));
				return;
			}
		} else if (selection instanceof VirtualResource) {
			this.setResult(Arrays.asList(sourceFolderRelativePath((VirtualResource) selection).toString()));
			return;
		}
		updateError("Invalid selection type.");

	}

	/**
	 * Set the default file extension which is used for newly created files.
	 *
	 * @param defaultFileExtension
	 *            The extension to use by default
	 */
	public void setDefaultFileExtension(String defaultFileExtension) {
		this.defaultFileExtension = defaultFileExtension;
	}

	/**
	 * An input validator to validate module folder names
	 */
	public static final class ModuleFolderValidator implements IInputValidator {
		@Override
		public String isValid(String newText) {
			if (newText.isEmpty()) {
				return "The module folder must not be empty";
			}
			if (!N4JSClassifierWizardModelValidator.isValidFolderName(newText)) {
				return "The module name is not a valid file system name";
			}
			return null;
		}
	}

	/**
	 * An input validator to validate module file names
	 */
	public static final class ModuleFileValidator implements IInputValidator {
		@Override
		public String isValid(String newText) {
			IPath path = new Path(newText);
			String fileExtension = path.getFileExtension();
			String moduleName = path.removeFileExtension().lastSegment();

			if (path.removeFileExtension().segmentCount() < 1 || moduleName.isEmpty()) {
				return "The module name must not be empty.";
			}

			if (!N4JSClassifierWizardModelValidator.isValidFolderName(newText)) {
				return "The module name is not a valid file system name.";
			}

			if (fileExtension == null) {
				return "The module name needs to have a valid N4JS file extension.";
			}
			if (!(fileExtension.equals(N4JSGlobals.N4JS_FILE_EXTENSION) ||
					fileExtension.equals(N4JSGlobals.N4JSD_FILE_EXTENSION))) {
				return "Invalid file extension.";
			}
			if (!isModuleFileSpecifier(path)) {
				return "Invalid module file specifier.";
			}
			if (path.segmentCount() > 1) {
				return IPath.SEPARATOR + " is not allowed in a module file specifier.";
			}

			return null;
		}
	}

}
