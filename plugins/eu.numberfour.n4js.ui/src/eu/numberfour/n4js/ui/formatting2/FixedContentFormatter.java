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

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.RewriteSessionEditProcessor;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.xtext.ui.editor.formatting2.ContentFormatter;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;

/**
 * wrap edits in a session for performance reasons
 *
 * https://github.com/eclipse/xtext-core/issues/14
 */
@SuppressWarnings("restriction")
public class FixedContentFormatter extends ContentFormatter {
	@Override
	public void format(IDocument document, IRegion region) {
		IXtextDocument doc = (IXtextDocument) document;
		TextEdit r = doc.priorityReadOnly(new FormattingUnitOfWork(doc, region));
		int f = TextEdit.CREATE_UNDO | TextEdit.UPDATE_REGIONS;
		// RewriteSessionEditProcessor ensures that all edits are applied in the same session aka transaction
		RewriteSessionEditProcessor processor = new RewriteSessionEditProcessor(document, r, f);
		try {
			processor.performEdits();
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}
}
