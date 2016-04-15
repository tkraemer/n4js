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
import org.eclipse.jface.text.source.LineNumberRulerColumn;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
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

	private final Color LIGHT_TEXT_COLOR = Display.getCurrent()
			.getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND);

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
		}

		private void createInfoBar(Composite parent) {
			Composite infoComposite = new Composite(parent, SWT.BORDER);
			infoComposite
					.setLayoutData(GridDataFactory.fillDefaults()
							.grab(true, false)
							.indent(new Point(12, 0)) // Extra indent to align with source viewer
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
								.createResource(URI.createPlatformResourceURI("/TempProject/temp.n4js", true));
					})
					.showErrorAndWarningAnnotations() // To make the vertical ruler visible
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

			LineNumberRulerColumn lineNumberRuler = new LineNumberRulerColumn();
			// lineNumberRuler.setForeground(LIGHT_TEXT_COLOR);
			viewer.addVerticalRulerColumn(lineNumberRuler);
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
				editorDocument.set(content);
			}

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
		public void setEnabled(boolean enabled) {
			super.setEnabled(enabled);
			if (!enabled) {
				sourceViewer.getTextWidget().setStyleRange(new StyleRange(0, getContent().length(), LIGHT_TEXT_COLOR,
						sourceViewer.getTextWidget().getBackground()));
			}
		}

		@Override
		protected void checkSubclass() {
			// Allow subclassing
			return;
		}
	}
}