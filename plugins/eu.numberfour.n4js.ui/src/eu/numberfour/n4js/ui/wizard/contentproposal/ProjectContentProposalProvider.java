package eu.numberfour.n4js.ui.wizard.contentproposal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;

/**
 * A content proposal provider for workspace projects
 */
public class ProjectContentProposalProvider implements IContentProposalProvider {

	private final IWorkspaceRoot workspaceRoot;
	private final List<String> workspaceProjectsNames;

	/** Creates a new ProjectcontentProposalProvider */
	@Inject
	public ProjectContentProposalProvider(IN4JSCore n4jsCore) {
		workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

		workspaceProjectsNames = Arrays.asList(workspaceRoot.getProjects()).stream()
				.map(p -> URI.createPlatformResourceURI(p.getName(), true)) // map to uri
				.map(uri -> n4jsCore.findProject(uri).orNull()) // map to project
				.filter(n4Project -> (null != n4Project)) // remove null projects
				.filter(n4Project -> n4Project.exists())
				.map(n4Project -> n4Project.getProjectName()) // map to name
				.collect(Collectors.toList());
	}

	@Override
	public IContentProposal[] getProposals(String contents, int position) {
		return workspaceProjectsNames.stream()
				.filter(p -> p.startsWith(contents))
				.map(p -> new ContentProposal(p))
				.toArray(IContentProposal[]::new);
	}
}