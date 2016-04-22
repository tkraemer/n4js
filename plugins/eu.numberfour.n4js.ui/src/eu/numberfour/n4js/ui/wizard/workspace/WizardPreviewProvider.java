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
package eu.numberfour.n4js.ui.wizard.workspace;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.xtext.resource.XtextResource;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;

/**
 * A preview window for wizards which shows a preview of the created class.
 */
public class WizardPreviewProvider {

	private static final Color INACTIVE_COLOR = Display.getCurrent()
			.getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND);

	private static final Color INACTIVE_BACKGROUND = Display.getCurrent()
			.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND);

	@Inject
	@SuppressWarnings("restriction")
	private org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory editorFactory;

	@Inject
	private IN4JSCore n4jsCore;

	/**
	 * Opens a new wizard with the give shell as parent.
	 *
	 * A wizard preview can be of style {@code SWT.LEFT} or {@code SWT.LEFT}
	 *
	 * @param parent
	 *            The parent shell
	 * @param style
	 *            The preview style
	 * @return The preview control
	 */
	public WizardPreview create(Composite parent, int style) {
		return new WizardPreview(parent, style);
	}

	/**
	 * A preview window which attaches to the side of a given shell.
	 */
	public class WizardPreview extends Composite {

		private Document editorDocument;
		private SourceViewer sourceViewer;

		// Info label
		private Label infoLabel;

		// Content blocks
		private ContentBlock[] contentBlocks;

		/**
		 * Creates a new wizard preview for a given shell.
		 *
		 * Using the style parameter the direction of the preview shell can be set.
		 */
		public WizardPreview(Composite parent, int style) {
			super(parent, style);
			this.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(0, 0, 0, 0).create());

			createEditor(this);
			createInfoBar(this);

			// Enable editor theming by assigning a CSS class
			this.sourceViewer.getTextWidget().setData("org.eclipse.e4.ui.css.CssClassName", "MPart active");
		}

		private void createInfoBar(Composite parent) {
			Composite infoComposite = new Composite(parent, SWT.BORDER);
			infoComposite
					.setLayoutData(GridDataFactory.fillDefaults()
							.grab(true, false)
							.create());

			infoComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());

			infoLabel = new Label(infoComposite, SWT.NONE);
			infoLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		}

		@SuppressWarnings("restriction")
		private void createEditor(Composite parent) {
			org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor editor = editorFactory.newEditor(
					() -> {
						return (XtextResource) n4jsCore.createResourceSet(Optional.absent())
								// Use a non-existing invalid URI to prevent conflicts with existing workspace resources
								.createResource(URI.createPlatformResourceURI("/1TempProject/temp.n4js", true));
					})
					.withParent(parent);

			editor.getViewer().getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

			// Initialize the document
			editor.createPartialEditor(true);

			editorDocument = editor.getDocument();
			sourceViewer = editor.getViewer();

			configureSourceViewer(sourceViewer);

			setContent("");
		}

		private void configureSourceViewer(SourceViewer viewer) {
			viewer.setEditable(false);

			viewer.getTextWidget().addModifyListener(modifyEvent -> {
				System.out.println("Text changed");
			});

			viewer.addTextListener(new ITextListener() {
				@Override
				public void textChanged(TextEvent event) {
					updateHighlighting();
				}
			});

			viewer.getTextWidget().setAlwaysShowScrollBars(false);
		}

		/**
		 * Unhighlights all text in the editor.
		 */
		private void unhighlightAll() {
			for (StyleRange range : sourceViewer.getTextWidget().getStyleRanges()) {
				if (range.foreground != INACTIVE_COLOR) {
					range.foreground = INACTIVE_COLOR;
					range.fontStyle = SWT.NORMAL;
					sourceViewer.getTextWidget().setStyleRange(range);
				}
			}
		}

		/**
		 * Updates the syntax highlighting.
		 *
		 * Disables highlighting for inactive blocks, or all blocks if the editor is disabled.
		 */
		private void updateHighlighting() {
			if (!getEnabled()) {
				unhighlightAll();
			} else {
				int accumulatedOffset = 0;
				for (ContentBlock block : contentBlocks) {
					if (!block.active) {
						StyleRange range = new StyleRange(accumulatedOffset, block.content.length(), INACTIVE_COLOR,
								null);
						sourceViewer.getTextWidget().setStyleRange(range);
					}
					accumulatedOffset += block.content.length();
				}
			}
		}

		@Override
		public void setEnabled(boolean enabled) {
			super.setEnabled(enabled);
			if (!enabled) {
				unhighlightAll();
			}
		}

		/**
		 * Sets the content of the preview.
		 *
		 * The content is parsed and highlighted as n4js code
		 *
		 *
		 * @param content
		 *            The preview content.
		 */
		public void setContent(String content) {
			if (null != editorDocument) {
				this.setContent(
						new ContentBlock[] { ContentBlock.inactive("class B { }\n"), ContentBlock.active(content) });
			}
		}

		/**
		 *
		 */
		public void setContent(ContentBlock[] blocks) {
			if (null == editorDocument) {
				return;
			}

			this.contentBlocks = blocks;

			String joinedContent = "";
			for (ContentBlock block : blocks) {
				joinedContent += block.content;
			}
			editorDocument.set(joinedContent);

			updateHighlighting();
		}

		/**
		 * Returns the current content of the preview.
		 *
		 */
		public String getContent() {
			if (null != editorDocument) {
				return editorDocument.get();
			}
			return "";
		}

		/**
		 * Sets the new info bar content
		 *
		 * @param info
		 *            The new info
		 */
		public void setInfo(String info) {
			this.infoLabel.setText(info);
		}

		@Override
		protected void checkSubclass() {
			// Allow subclassing
			return;
		}
	}
}