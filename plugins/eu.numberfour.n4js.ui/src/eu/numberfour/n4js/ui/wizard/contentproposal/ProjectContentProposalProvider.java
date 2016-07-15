package eu.numberfour.n4js.ui.wizard.contentproposal;

import java.util.stream.StreamSupport;

import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;

import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;

/**
 * A content proposal provider for workspace projects
 */
public class ProjectContentProposalProvider extends SimpleContentProposalProvider {

	/** Creates a new ProjectcontentProposalProvider */
	@Inject
	public ProjectContentProposalProvider(IN4JSCore n4jsCore) {
		super(StreamSupport.stream(n4jsCore.findAllProjects().spliterator(), false)
				.filter(p -> !p.isExternal())
				.filter(p -> p.exists())
				.map(p -> p.getArtifactId())
				.toArray(String[]::new));
		this.setFiltering(true);
	}
}