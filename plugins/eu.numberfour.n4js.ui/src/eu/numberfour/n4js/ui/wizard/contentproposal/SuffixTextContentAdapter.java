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
package eu.numberfour.n4js.ui.wizard.contentproposal;

import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.fieldassist.IControlContentAdapter2;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

import eu.numberfour.n4js.ui.wizard.workspace.SuffixText;

/**
 * A content adapter for content proposals for {@link SuffixText}.
 *
 * Equivalent of {@link TextContentAdapter} for {@link SuffixText}.
 *
 * The implementation is mostly taken from {@link TextContentAdapter} and adapted to work with {@link SuffixText};
 */
public class SuffixTextContentAdapter implements IControlContentAdapter, IControlContentAdapter2 {
	@Override
	public String getControlContents(Control control) {
		return ((SuffixText) control).getText();
	}

	@Override
	public void setControlContents(Control control, String text, int cursorPosition) {
		((SuffixText) control).setText(text);
		((SuffixText) control).setSelection(cursorPosition, cursorPosition);
	}

	@Override
	public void insertControlContents(Control control, String text,
			int cursorPosition) {
		Point selection = ((SuffixText) control).getSelection();
		((SuffixText) control).insert(text);

		if (cursorPosition < text.length()) {
			((SuffixText) control).setSelection(selection.x + cursorPosition,
					selection.x + cursorPosition);
		}
	}

	@Override
	public int getCursorPosition(Control control) {
		return ((SuffixText) control).getCaretPosition();
	}

	@Override
	public Rectangle getInsertionBounds(Control control) {
		SuffixText suffixText = (SuffixText) control;
		Point caretOrigin = suffixText.getCaretLocation();
		return new Rectangle(caretOrigin.x + suffixText.getClientArea().x,
				caretOrigin.y + suffixText.getClientArea().y + 3, 1, suffixText.getLineHeight());
	}

	@Override
	public void setCursorPosition(Control control, int position) {
		((SuffixText) control).setSelection(position, position);
	}

	@Override
	public Point getSelection(Control control) {
		return ((SuffixText) control).getSelection();
	}

	@Override
	public void setSelection(Control control, Point range) {
		((SuffixText) control).setSelection(range.x, range.y);
	}

}
