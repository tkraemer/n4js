package eu.numberfour.n4js.ui.wizard.contentproposal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;

/**
 * A content proposal provider to propose source folders in a given project.
 */
public class SourceFolderContentProposalProvider implements IContentProposalProvider {

	@Inject
	private IN4JSCore n4jsCore;

	private final List<String> availableSourceFolders = new ArrayList<>();

	/** The project for which the source folders are proposed */
	private IProject contextProject;

	private void updateSourceFolders() {
		availableSourceFolders.clear();

		if (null == contextProject) {
			return;
		}

		URI projectURI = URI.createPlatformResourceURI(contextProject.getName(), true);
		IN4JSProject n4Project = n4jsCore
				.findProject(projectURI)
				.orNull();
		if (n4Project == null) {
			return;
		}
		// Add all source folder paths to the list
		availableSourceFolders
				.addAll(n4Project.getSourceContainers().stream().map(src -> src.getRelativeLocation())
						.collect(Collectors.toList()));
	}

	@Override
	public IContentProposal[] getProposals(String contents, int position) {
		return availableSourceFolders.stream()
				.filter(src -> src.startsWith(contents))
				.map(src -> new ContentProposal(src)).collect(Collectors.toList())
				.stream().toArray(IContentProposal[]::new);
	}

	/**
	 * Returns the project context.
	 *
	 * Counterpart of {@link #contextProject}
	 */
	public IProject getContextProject() {
		return contextProject;
	}

	/**
	 * Sets the project context.
	 *
	 * The context project is the project in which the proposal looks for source folders. If null no proposals are
	 * given.
	 *
	 * @param contextProject
	 *            The project to look for source folders. May be null.
	 */
	public void setContextProject(IProject contextProject) {
		this.contextProject = contextProject;
		updateSourceFolders();
	}
}