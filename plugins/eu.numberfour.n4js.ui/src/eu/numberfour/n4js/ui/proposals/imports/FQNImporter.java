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
package eu.numberfour.n4js.ui.proposals.imports;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentRewriteSession;
import org.eclipse.jface.text.DocumentRewriteSessionType;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.ITextViewerExtension;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.link.LinkedModeModel;
import org.eclipse.jface.text.link.LinkedModeUI;
import org.eclipse.jface.text.link.LinkedPosition;
import org.eclipse.jface.text.link.LinkedPositionGroup;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ReplacementTextApplier;

import com.google.common.base.Predicate;
import com.google.inject.Inject;
import com.google.inject.MembersInjector;

import eu.numberfour.n4js.scoping.imports.PlainAccessOfNamespacedImportDescription;
import eu.numberfour.n4js.ui.proposals.linkedEditing.IdentifierExitPolicy;
import eu.numberfour.n4js.ui.proposals.linkedEditing.N4JSCompletionProposal;
import eu.numberfour.n4js.ts.scoping.N4TSQualifiedNameProvider;

/**
 * The FQNImporter can be set on a {@link ConfigurableCompletionProposal} to handle a selected proposal that inserts a
 * reference to a type. This reference will be shortened to a valid simple name. Shortening logic uses the scope
 * provider and therefore knows about existing imports. If no such import is available, a new import will be added. If
 * existing imports conflict with the selected type, an alias is inserted automatically.
 *
 * Since changes are performed at different locations in the same document, line offsets and text widget may appear to
 * flicker when a proposal is selected. This class handles these cases by disabling redraw on the text viewer and moving
 * the canvas one line up if a new line was added at the top of the document.
 *
 * Instances of this class are created by the injectable {@link FQNImporter.Factory}.
 */
public class FQNImporter extends ReplacementTextApplier {

	/**
	 * Key used for additional data in {@link ConfigurableCompletionProposal} to store the {@link QualifiedName} of the
	 * object to be imported.
	 *
	 * @see ConfigurableCompletionProposal#setAdditionalData(String, Object)
	 */
	public static final String KEY_QUALIFIED_NAME = FQNImporter.class.getName() + "#QUALIFIED_NAME";

	private static final Logger logger = Logger.getLogger(FQNImporter.class);

	@Inject
	private IQualifiedNameConverter qualifiedNameConverter;

	@Inject
	private ImportRewriter.Factory importRewriterFactory;

	/**
	 * Factory class for {@link FQNImporter}.
	 */
	public static class Factory {
		@Inject
		private MembersInjector<FQNImporter> importerInjector;

		/**
		 * Create a new {@link FQNImporter}.
		 *
		 * @param context
		 *            the resource where the imports should be added.
		 * @param scope
		 *            the scope of the type reference.
		 * @param valueConverter
		 *            the value converter for the concrete syntax at the insertion point.
		 * @param viewer
		 *            the text widget.
		 */
		public FQNImporter create(
				Resource context,
				IScope scope,
				IValueConverter<String> valueConverter,
				Predicate<IEObjectDescription> filter,
				ITextViewer viewer) {
			FQNImporter result = new FQNImporter(context, scope, valueConverter, filter, viewer);
			importerInjector.injectMembers(result);
			return result;
		}
	}

	private final IScope scope;
	private final Resource context;
	private final IValueConverter<String> valueConverter;
	private final Predicate<IEObjectDescription> filter;
	private final ITextViewer viewer;

	FQNImporter(
			Resource context,
			IScope scope,
			IValueConverter<String> valueConverter,
			Predicate<IEObjectDescription> filter,
			ITextViewer viewer) {
		this.context = context;
		this.scope = scope;
		this.valueConverter = valueConverter;
		this.filter = filter;
		this.viewer = viewer;
	}

	/**
	 * Convert the the given qualifiedName to a valid syntax in the n4js file.
	 */
	String applyValueConverter(QualifiedName qualifiedName) {
		String result = qualifiedNameConverter.toString(qualifiedName);
		result = valueConverter.toString(result);
		return result;
	}

	/**
	 * Converts the concrete syntax to a qualified name.
	 */
	QualifiedName applyValueConverter(String concreteSyntax) {
		final String semanticReplacementString = valueConverter.toValue(concreteSyntax, null);
		final QualifiedName qualifiedName = qualifiedNameConverter.toQualifiedName(semanticReplacementString);
		return qualifiedName;
	}

	/**
	 * Return the to-be-inserted string if an existing import is present.
	 */
	@Override
	public String getActualReplacementString(ConfigurableCompletionProposal proposal) {
		String syntacticReplacementString = proposal.getReplacementString();
		if (scope != null) {
			final QualifiedName qualifiedName = applyValueConverter(syntacticReplacementString);
			if (qualifiedName.getSegmentCount() == 1) {
				return syntacticReplacementString;
			}
			final IEObjectDescription element = scope.getSingleElement(qualifiedName);
			if (element != null) {
				EObject resolved = EcoreUtil.resolve(element.getEObjectOrProxy(), context);
				if (!resolved.eIsProxy()) {
					IEObjectDescription description = findApplicableDescription(resolved, qualifiedName, true);
					if (description != null) {
						String multisegmentProposal = applyValueConverter(description.getName());
						return multisegmentProposal;
					}
				}
			}
		}
		return syntacticReplacementString;
	}

	/**
	 * Search for a description in the scope that points to the given element. A valid description is considered to
	 * fulfill the {@link #filter} and the last segment of the given qualifiedName has to match
	 * {@code simpleNameMatch == true} or has to be different from the description's last name segment. If nothing can
	 * be found, {@code null} is returned.
	 */
	private IEObjectDescription findApplicableDescription(EObject objectOrProxy, QualifiedName qualifiedName,
			boolean simpleNameMatch) {
		Iterable<IEObjectDescription> lookupElements = scope.getElements(objectOrProxy);
		for (IEObjectDescription lookupElement : lookupElements) {
			if (filter.apply(lookupElement)) {
				if (simpleNameMatch == qualifiedName.getLastSegment()
						.equals(lookupElement.getName().getLastSegment())) {
					return lookupElement;
				}
			}
		}
		return null;
	}

	/**
	 * Modify the document and start linked editing if necessary.
	 *
	 * Imports will be added if necessary.
	 *
	 * @see #applyWithImport(QualifiedName, String, IDocument, ConfigurableCompletionProposal)
	 */
	@Override
	public void apply(IDocument document, ConfigurableCompletionProposal proposal) throws BadLocationException {
		final String syntacticReplacementString = proposal.getReplacementString();

		// does it even happen? check logs if first and/or second check passes
		{
			String actualSyntacticReplacementString = getActualReplacementString(proposal);
			// there is an import statement - apply computed replacementString
			if (!syntacticReplacementString.equals(actualSyntacticReplacementString)) {
				QualifiedName shortQualifiedName = applyValueConverter(actualSyntacticReplacementString);
				if (shortQualifiedName.getSegmentCount() == 1) {
					simpleApply(document, actualSyntacticReplacementString, proposal);
					return;
				}
			}
		}

		final QualifiedName qualifiedName = (QualifiedName) proposal.getAdditionalData(KEY_QUALIFIED_NAME);
		if (qualifiedName == null) {
			super.apply(document, proposal);
			return;
		}

		// we could create an import statement if there is no conflict
		if (qualifiedName.getSegmentCount() == 1) {
			// type name is a simple name - no need to hassle with imports
			simpleApply(document, syntacticReplacementString, proposal);
			return;
		}
		// Globally available elements should not generate imports
		if (qualifiedName.getSegmentCount() == 2
				&& N4TSQualifiedNameProvider.GLOBAL_NAMESPACE_SEGMENT.equals(qualifiedName.getFirstSegment())) {
			// type name is a simple name from global Namespace - no need to hassle with imports
			simpleApply(document, syntacticReplacementString, proposal);
			return;
		}
		String alias = null;
		IEObjectDescription descriptionFullQN = scope
				.getSingleElement(QualifiedName.create(qualifiedName.getLastSegment()));

		// element is imported via namespace
		// exported packages just for this, maybe we can use similar check for PlainAccessOfAliasedImportDescription
		if (descriptionFullQN instanceof PlainAccessOfNamespacedImportDescription) {
			simpleApply(document,
					((PlainAccessOfNamespacedImportDescription) descriptionFullQN).getNamespacedName(),
					proposal);
			return;
		}
		if (descriptionFullQN != null) {
			// the simple name is already reachable - another import is present
			// try to use an alias
			IEObjectDescription description = scope.getSingleElement(qualifiedName);
			IEObjectDescription existingAliased = findApplicableDescription(description.getEObjectOrProxy(),
					qualifiedName, false);

			// there is already an alias, but the FQN version was picked - insert FQN
			if (existingAliased != null) {
				simpleApply(document, syntacticReplacementString, proposal);
				return;
			}
			// trying to detect namespace access without PlainAccessOfNamespacedImportDescription being accessible

			// no alias used, yet - add an alias and insert that one
			alias = "Alias" + qualifiedName.getLastSegment();
		}

		applyWithImport(qualifiedName, alias, document, proposal);
	}

	/**
	 * Just insert the string at the replacement offset. Everything else is fine.
	 */
	private void simpleApply(IDocument document, String string, ConfigurableCompletionProposal proposal)
			throws BadLocationException {
		proposal.setCursorPosition(string.length());
		document.replace(proposal.getReplacementOffset(), proposal.getReplacementLength(),
				string);
	}

	/**
	 * Applies the proposal and imports the given qualifiedName with the given alias (may be null). Prevents flickering
	 * by temporarily disabling redraw on the text viewer widget.
	 */
	private void applyWithImport(QualifiedName qualifiedName, String alias, IDocument document,
			ConfigurableCompletionProposal proposal) throws BadLocationException {
		// Import does not introduce ambiguities - add import and insert short name
		int topPixel = -1;
		// store the pixel coordinates to prevent the ui from flickering
		StyledText widget = viewer.getTextWidget();
		if (widget != null)
			topPixel = widget.getTopPixel();
		ITextViewerExtension viewerExtension = null;
		if (viewer instanceof ITextViewerExtension) {
			viewerExtension = (ITextViewerExtension) viewer;
			viewerExtension.setRedraw(false);
		}
		DocumentRewriteSession rewriteSession = null;
		try {
			if (document instanceof IDocumentExtension4) {
				rewriteSession = ((IDocumentExtension4) document).startRewriteSession(
						DocumentRewriteSessionType.UNRESTRICTED_SMALL);
			}
			int initialCursorLine = document.getLineOfOffset(proposal.getReplacementOffset());

			// apply short proposal and obtain the new cursor position
			int newCursorPositionInDocument = doApply(qualifiedName, alias, document, proposal);

			// set the pixel coordinates of the viewer widget, e.g. move it up one line if necessary
			int newCursorLine = document.getLineOfOffset(newCursorPositionInDocument);
			if (widget != null) {
				int additionalTopPixel = (newCursorLine - initialCursorLine) * widget.getLineHeight();
				widget.setTopPixel(topPixel + additionalTopPixel);
			}
		} finally {
			if (rewriteSession != null) {
				((IDocumentExtension4) document).stopRewriteSession(rewriteSession);
			}
			if (viewerExtension != null)
				viewerExtension.setRedraw(true);
		}
	}

	/**
	 * Really do apply the proposal. Assumed to be run within the prevent flickering mode.
	 */
	private int doApply(QualifiedName qualifiedName, String alias, IDocument document,
			ConfigurableCompletionProposal proposal) throws BadLocationException {
		String shortSemanticReplacementString = alias != null ? alias : qualifiedName.getLastSegment();
		String shortSyntacticReplacementString = valueConverter.toString(shortSemanticReplacementString);

		ImportRewriter importRewriter = importRewriterFactory.create(document, context);

		ReplaceEdit replaceEdit = new ReplaceEdit(
				proposal.getReplacementOffset(),
				proposal.getReplacementLength(),
				shortSyntacticReplacementString);
		MultiTextEdit compound = new MultiTextEdit();
		AliasLocation aliasLocation = null;
		if (alias != null) {
			aliasLocation = importRewriter.addSingleImport(qualifiedName, alias, compound);
		} else {
			importRewriter.addSingleImport(qualifiedName, compound);
		}
		compound.addChild(replaceEdit);

		Position caret = new Position(proposal.getReplacementOffset(), 0);
		document.addPosition(caret);
		compound.apply(document);
		document.removePosition(caret);

		int cursorPosition = caret.getOffset();
		proposal.setReplacementOffset(cursorPosition - shortSemanticReplacementString.length());
		proposal.setCursorPosition(shortSemanticReplacementString.length());

		if (aliasLocation != null) {
			final int aliasOffset = aliasLocation.getBaseOffset() + aliasLocation.getRelativeOffset();
			final int aliasLength = shortSyntacticReplacementString.length();
			N4JSCompletionProposal castedProposal = (N4JSCompletionProposal) proposal;
			castedProposal.setLinkedModeBuilder((appliedProposal, currentDocument) -> {
				try {
					LinkedPositionGroup group = new LinkedPositionGroup();
					group.addPosition(new LinkedPosition(
							currentDocument,
							aliasOffset,
							aliasLength,
							LinkedPositionGroup.NO_STOP));
					group.addPosition(new LinkedPosition(
							currentDocument,
							proposal.getReplacementOffset(),
							proposal.getCursorPosition(),
							LinkedPositionGroup.NO_STOP));
					proposal.setSelectionStart(proposal.getReplacementOffset());
					proposal.setSelectionLength(proposal.getCursorPosition());
					LinkedModeModel model = new LinkedModeModel();
					model.addGroup(group);
					model.forceInstall();

					LinkedModeUI ui = new LinkedModeUI(model, viewer);
					ui.setExitPolicy(new IdentifierExitPolicy('\n'));
					ui.setExitPosition(
							viewer,
							proposal.getCursorPosition() + proposal.getReplacementOffset(),
							0,
							Integer.MAX_VALUE);
					ui.setCyclingMode(LinkedModeUI.CYCLE_NEVER);
					ui.enter();
				} catch (BadLocationException e) {
					logger.error(e.getMessage(), e);
				}
			});
		}
		return cursorPosition;
	}

}
