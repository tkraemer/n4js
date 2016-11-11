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
package eu.numberfour.n4js.ui.formatting2;

import java.util.List;

import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.formatting2.regionaccess.ITextReplacement;
import org.eclipse.xtext.ui.editor.formatting2.ContentFormatter;

/**
 * Use a single {@link ReplaceEdit} instead of a {@link MultiTextEdit} for performance reasons.
 *
 * See https://github.com/NumberFour/n4js/issues/246 for details.
 *
 */
@SuppressWarnings("restriction")
public class FixedContentFormatter extends ContentFormatter {

	@Override
	protected TextEdit createTextEdit(List<ITextReplacement> replacements) {
		if (replacements.isEmpty()) {
			// return new ReplaceEdit(0, 0, "");
			return null; // Return null, otherwise even if there are no changes, e.g. formatting, editor is in dirty
							// state
		}
		ITextRegionAccess regionAccess = replacements.get(0).getTextRegionAccess();
		String newDocument = regionAccess.getRewriter().renderToString(replacements);
		return new ReplaceEdit(0, regionAccess.regionForDocument().getLength(), newDocument);
	}
}
