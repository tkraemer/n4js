package eu.numberfour.n4js.ui.wizard.contentproposal;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * A simple content proposal label provider to show the same image for all proposals
 */
public class SimpleImageContentProposalLabelProvider extends LabelProvider {

	private final Image image;

	/**
	 * Creates a new SimpleImageContentProposalLabelProvider with a given image.
	 *
	 *
	 * @param imageDescriptor
	 *            The descriptor of the image which should be used
	 */
	public SimpleImageContentProposalLabelProvider(ImageDescriptor imageDescriptor) {
		this.image = imageDescriptor.createImage();
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IContentProposal) {
			return ((IContentProposal) element).getLabel();
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		return image;
	}

	@Override
	public void dispose() {
		super.dispose();
		image.dispose();
	}
}