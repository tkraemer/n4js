package eu.numberfour.n4js.ui.wizard.contentproposal;

import java.util.Arrays;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Content proposal provider for module specifiers.
 *
 * The proposal lets the user choose from the list of file system children for the already inserted text content.
 */
public class ModuleSpecifierContentProvider implements IContentProposalProvider {

	private final IContentProposal[] EMPTY_PROPOSAL = new IContentProposal[] {};

	/**
	 * Custom proposal type which also holds information about the proposal module type. See {@link Type}
	 */
	private static final class ModuleSpecifierProposal extends ContentProposal {

		public enum Type {
			FOLDER, MODULE
		}

		private final Type moduleType;
		private final IPath path;

		/**
		 * @param path
		 *            The path of the module specifier proposal
		 */
		public ModuleSpecifierProposal(IPath path, Type moduleType) {
			// Remove file extension for content
			super(path.removeFileExtension().toString());
			this.path = path;
			this.moduleType = moduleType;
		}

		/**
		 * Returns the type of this module specifier proposal
		 */
		public Type getModuleType() {
			return moduleType;
		}

		/**
		 * Returns the path of this module specifier proposal
		 */
		public IPath getPath() {
			return path;
		}

	}

	private IPath proposalRoot;

	/**
	 * A label provider for {@link ModuleSpecifierContentProvider}s proposals
	 */
	public static final class ModuleSpecifierProposalLabelProvider extends LabelProvider {

		private final Image FOLDER_SYMBOL = PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_OBJ_FOLDER);
		private final Image FILE_SYMBOL = PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_OBJ_FILE);

		@Override
		public Image getImage(Object element) {
			if (element instanceof ModuleSpecifierProposal) {
				ModuleSpecifierProposal.Type type = ((ModuleSpecifierProposal) element).getModuleType();
				return type == ModuleSpecifierProposal.Type.FOLDER ? FOLDER_SYMBOL : FILE_SYMBOL;
			}
			return super.getImage(element);
		}

		@Override
		public String getText(Object element) {
			if (element instanceof ModuleSpecifierProposal) {
				return ((ModuleSpecifierProposal) element).getPath().toString();
			}
			return super.getText(element);
		}

		@Override
		public void dispose() {
			super.dispose();

			FOLDER_SYMBOL.dispose();
			FILE_SYMBOL.dispose();
		}
	}

	@Override
	public IContentProposal[] getProposals(String contents, int position) {
		IContainer proposalRootFolder;

		if (null == proposalRoot) {
			return EMPTY_PROPOSAL;
		}

		if (proposalRoot.isEmpty()) {
			proposalRootFolder = ResourcesPlugin.getWorkspace().getRoot();
		} else if (proposalRoot.segmentCount() == 1) {
			proposalRootFolder = ResourcesPlugin.getWorkspace().getRoot().getProject(proposalRoot.segment(0));
		} else {
			proposalRootFolder = findContainerForPath(proposalRoot);
		}

		if (null == proposalRootFolder || !proposalRootFolder.exists()) {
			return EMPTY_PROPOSAL;
		}

		IPath contentsPath = new Path(contents);

		String prefix = "";
		IPath workingDirectoryPath;

		if (!contentsPath.hasTrailingSeparator()) {
			if (contentsPath.lastSegment() != null) {
				prefix = contentsPath.lastSegment();
			}
			workingDirectoryPath = contentsPath.removeLastSegments(1);
		} else {
			prefix = "";
			workingDirectoryPath = contentsPath;
		}

		final String finalPrefix = prefix;

		IContainer workingDirectory;
		if (workingDirectoryPath.segmentCount() > 0) {
			workingDirectory = proposalRootFolder.getFolder(workingDirectoryPath);
		} else {
			workingDirectory = proposalRootFolder;
		}
		if (null == workingDirectory || !workingDirectory.exists()) {
			return EMPTY_PROPOSAL;
		}
		try {
			return Arrays.asList(workingDirectory.members()).stream()
					// Only work with files and folders
					.filter(r -> (r instanceof IFile || r instanceof IFolder))
					// Filter by prefix matching
					.filter(resource -> resource.getName().startsWith(finalPrefix))
					// Transform to a ModuleSpecifierProposal
					.map(resource -> {
						// Create proposal path
						IPath proposalPath = resource.getFullPath().makeRelativeTo(proposalRootFolder.getFullPath());
						// Set the proposal type
						ModuleSpecifierProposal.Type type = resource instanceof IFile
								? ModuleSpecifierProposal.Type.MODULE
								: ModuleSpecifierProposal.Type.FOLDER;
						// Remove trailing separator for modules
						if (type == ModuleSpecifierProposal.Type.MODULE) {
							proposalPath = proposalPath.removeTrailingSeparator();
						}
						// Create a new module specifier proposal
						return new ModuleSpecifierProposal(proposalPath, type);
					})
					.toArray(IContentProposal[]::new);
		} catch (CoreException e) {
			return EMPTY_PROPOSAL;
		}

	}

	/**
	 * Helper method to return the workspace container at the given path.
	 *
	 * Returns null when not found or the element is not of type {@link IContainer}.
	 *
	 * @param path
	 *            workspace absolute path
	 */
	private IContainer findContainerForPath(IPath path) {
		IResource containerResource = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(path);
		if (containerResource instanceof IContainer) {
			return (IContainer) containerResource;
		}
		return null;
	}

	/**
	 * Returns the proposal root.
	 *
	 * Counterpart of {@link #setProposalRoot(IPath)}
	 */
	public IPath getProposalRoot() {
		return proposalRoot;
	}

	/**
	 * Sets the proposal root.
	 *
	 * The proposal root is the absolute workspace path of the root folder in which the proposals should be given.
	 *
	 * May be null to disable proposals.
	 *
	 * @param proposalRoot
	 *            An absolute workspace path of the proposal root.
	 */
	public void setProposalRoot(IPath proposalRoot) {
		this.proposalRoot = proposalRoot;
	}

}