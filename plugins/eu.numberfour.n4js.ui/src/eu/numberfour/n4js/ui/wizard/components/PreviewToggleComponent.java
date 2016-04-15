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
package eu.numberfour.n4js.ui.wizard.components;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * A simple component which shows a right aligned toggle button to toggle the visibility of the content preview.
 *
 * The actual content preview hiding should be handled by the specified selection listener.
 */
public class PreviewToggleComponent extends WizardComponent {

	private static final String SHOW_PREVIEW_TEXT = "Show preview >>";
	private static final String HIDE_PREVIEW_TEXT = "<< Hide preview";

	private final Button showPreviewButton;
	private SelectionListener selectionListener;

	/**
	 * Creates a new preview toggle component
	 *
	 * @param container
	 *            The parent component container
	 */
	public PreviewToggleComponent(WizardComponentContainer container) {
		super(container);

		Composite parent = container.getComposite();

		WizardComponentUtils.emptyGridCell(parent);

		showPreviewButton = new Button(parent, SWT.TOGGLE);
		showPreviewButton.setText(SHOW_PREVIEW_TEXT);

		showPreviewButton.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).align(SWT.RIGHT, SWT.BOTTOM).create());

		showPreviewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// Notify listener
				if (null != selectionListener) {
					selectionListener.widgetDefaultSelected(e);
				}
				super.widgetDefaultSelected(e);
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean state = showPreviewButton.getSelection();

				// Notify listener
				if (null != selectionListener) {
					selectionListener.widgetSelected(e);
				}

				// Change the button text
				if (state) {
					showPreviewButton.setText(HIDE_PREVIEW_TEXT);
				} else {
					showPreviewButton.setText(SHOW_PREVIEW_TEXT);
				}
			}
		});
	}

	/**
	 * Sets the selection listener for the toggle button
	 */
	public void setSelectionListener(SelectionListener selectionListener) {
		this.selectionListener = selectionListener;
	}

	/**
	 * Sets the the preview state.
	 *
	 * @param displayed
	 *            {@code true} if the preview is displayed, {@code false} otherwise
	 */
	public void setPreviewState(boolean displayed) {
		showPreviewButton.setSelection(displayed);
	}

	/**
	 * Returns the current state of the show preview button
	 *
	 */
	public boolean getPreviewSate() {
		return showPreviewButton.getSelection();
	}

	@Override
	public void setFocus() {
		showPreviewButton.setFocus();
	}

}
