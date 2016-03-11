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
package eu.numberfour.n4js.antlr.generator;

import org.eclipse.xpand2.output.FileHandle;
import org.eclipse.xpand2.output.PostProcessor;
import org.eclipse.xtext.generator.LineSeparatorHarmonizer;

/**
 * A post processor for Xpand that replaces all line separators in generated code by unix style {@code "\n"}. Also
 * usable as a small utility to {@link #toUnixLineDelimiter(CharSequence) convert line breaks} without an Xpand context.
 */
public class NewlineNormalizer extends LineSeparatorHarmonizer implements PostProcessor {

	/**
	 * A unix line delimiter {@code '\n'}.
	 */
	public static final String UNIX_LINE_DELIMITER = "\n";

	@Override
	public void beforeWriteAndClose(FileHandle impl) {
		CharSequence content = impl.getBuffer();
		String replaced = toUnixLineDelimiter(content);
		impl.setBuffer(replaced);
	}

	@Override
	public void afterClose(FileHandle impl) {
		// nothing to do
	}

	/**
	 * Converts the given content into a {@link CharSequence} that uses only {@link #UNIX_LINE_DELIMITER}.
	 */
	public String toUnixLineDelimiter(CharSequence content) {
		return replaceLineSeparators(content, UNIX_LINE_DELIMITER);
	}

}
