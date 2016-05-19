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
package eu.numberfour.n4js.xpect;

import java.util.List;

import org.eclipse.xtext.formatting2.FormatterPreferenceKeys;
import org.eclipse.xtext.formatting2.FormatterRequest;
import org.eclipse.xtext.formatting2.IFormatter2;
import org.eclipse.xtext.formatting2.regionaccess.ILineRegion;
import org.eclipse.xtext.formatting2.regionaccess.ITextRegionAccess;
import org.eclipse.xtext.formatting2.regionaccess.ITextReplacement;
import org.eclipse.xtext.formatting2.regionaccess.ITextSegment;
import org.eclipse.xtext.formatting2.regionaccess.TextRegionAccessBuilder;
import org.eclipse.xtext.preferences.MapBasedPreferenceValues;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.ExceptionAcceptor;
import org.xpect.XpectImport;
import org.xpect.expectation.IStringExpectation;
import org.xpect.expectation.StringExpectation;
import org.xpect.parameter.ParameterParser;
import org.xpect.runner.Xpect;
import org.xpect.setup.XpectSetupFactory;
import org.xpect.state.Creates;
import org.xpect.xtext.lib.setup.ThisOffset;
import org.xpect.xtext.lib.setup.ThisResource;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.formatting2.N4JSFormatterPreferenceKeys;
import eu.numberfour.n4js.xpect.FormatterXpectMethod.TextRegionAccessFactory;

/**
 * @author Moritz Eysholdt
 */

@SuppressWarnings("restriction")
@XpectImport(TextRegionAccessFactory.class)
public class FormatterXpectMethod {

	/**
	 * Allow all tests per file to reuse the ITextRegionAccess-Object for performance reasons.
	 */
	@XpectSetupFactory
	public static class TextRegionAccessFactory {

		private final ITextRegionAccess regionAccess;

		/***/
		public TextRegionAccessFactory(@ThisResource XtextResource resource) {
			TextRegionAccessBuilder builder = resource.getResourceServiceProvider().get(TextRegionAccessBuilder.class);
			regionAccess = builder.forNodeModel(resource).create();
		}

		/***/
		@Creates
		public ITextRegionAccess getTextRegionAccess() {
			return regionAccess;
		}
	}

	@Inject
	private Provider<IFormatter2> formatterProvider;

	@Inject
	private Provider<FormatterRequest> formatterRequestProvider;

	/***/
	@Xpect
	@ParameterParser(syntax = "arg1=INT")
	public void formattedLines(
			@StringExpectation(whitespaceSensitive = true) IStringExpectation exp,
			int lines,
			@ThisOffset int o,
			ITextRegionAccess reg) {
		ITextSegment region = getRegionForLines(reg, o, lines);

		IFormatter2 formatter = formatterProvider.get();

		MapBasedPreferenceValues preferenceValues = new MapBasedPreferenceValues();
		preferenceValues.put(N4JSFormatterPreferenceKeys.FORMAT_PARENTHESIS, true);
		preferenceValues.put(FormatterPreferenceKeys.lineSeparator, "\n");

		FormatterRequest request = formatterRequestProvider.get();
		request.setTextRegionAccess(reg);
		request.setExceptionHandler(ExceptionAcceptor.THROWING);
		// needed in case a check like this will be implemented:
		// org.eclipse.xtext.junit4.formatter.FormatterTester.assertAllWhitespaceIsFormatted()
		request.setAllowIdentityEdits(true);
		request.setFormatUndefinedHiddenRegionsOnly(false);
		request.addRegion(region);
		request.setPreferences(preferenceValues);

		List<ITextReplacement> replacements = formatter.format(request);
		String fmt = reg.getRewriter().renderToString(replacements);
		ITextSegment doc = reg.regionForDocument();
		int endIndex = region.getEndOffset() + (fmt.length() - doc.getLength()) - 1;
		String selection = fmt.substring(region.getOffset(), endIndex);
		exp.assertEquals(selection);
	}

	private ITextSegment getRegionForLines(ITextRegionAccess regions, int offset, int lines) {
		ILineRegion firstLine = regions.regionForLineAtOffset(offset);
		ILineRegion lastLine = firstLine;
		for (int i = 1; i < lines; i++) {
			ILineRegion next = lastLine.getNextLine();
			if (next != null) {
				lastLine = next;
			} else {
				break;
			}
		}
		int firstLineOffset = firstLine.getOffset();
		ITextSegment region = regions.regionForOffset(firstLineOffset, (lastLine.getEndOffset() - firstLineOffset) + 1);
		return region;
	}

}
