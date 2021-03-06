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

import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.hyperlink.IHyperlink;
import org.eclipse.jface.text.hyperlink.IHyperlinkDetector;
import org.eclipse.xtext.junit4.ui.ContentAssistProcessorTestBuilder;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.XtextHyperlink;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.xpect.expectation.CommaSeparatedValuesExpectation;
import org.xpect.expectation.ICommaSeparatedValuesExpectation;
import org.xpect.runner.Xpect;
import org.xpect.xtext.lib.setup.ThisResource;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import eu.numberfour.n4js.n4JS.NamedElement;
import eu.numberfour.n4js.resource.N4JSResource;
import eu.numberfour.n4js.ts.types.IdentifiableElement;
import eu.numberfour.n4js.ts.types.SyntaxRelatedTElement;

/**
 */
@SuppressWarnings("restriction")
public class HyperlinkXpectMethod {

	@Inject
	private IHyperlinkDetector hyperlinkDetector;

	@Inject
	private N4ContentAssistProcessorTestBuilderHelper n4ContentAssistProcessorTestBuilderHelper;

	/**
	 * @param expectation
	 *            a list of expected URIs to jump to by this hyperlink
	 * @param resource
	 *            the resource under test
	 * @param region
	 *            the offset where hyperlinking should be invoked
	 * @throws Exception
	 *             some exceptions
	 */
	@ParameterParser2(syntax = "('at' arg2=OFFSET)?")
	@Xpect
	public void hyperlinks(
			@CommaSeparatedValuesExpectation ICommaSeparatedValuesExpectation expectation, // arg0
			@ThisResource XtextResource resource, // arg1
			RegionWithCursor region // arg2
	) throws Exception {
		ContentAssistProcessorTestBuilder fixture = n4ContentAssistProcessorTestBuilderHelper
				.createTestBuilderForResource(resource);

		String currentModelToParse = resource.getParseResult().getRootNode().getText();
		IXtextDocument xtextDocument = fixture.getDocument(resource, currentModelToParse);
		// in case of cross-file hyperlinks, we have to make sure the target resources are fully resolved
		final ResourceSet resSet = resource.getResourceSet();
		for (Resource currRes : new ArrayList<>(resSet.getResources()))
			N4JSResource.postProcess(currRes);

		ITextViewer sourceViewer = fixture.getSourceViewer(currentModelToParse, xtextDocument);
		IHyperlink[] hyperlinks = hyperlinkDetector.detectHyperlinks(sourceViewer,
				new Region(region.getGlobalCursorOffset(), 0),
				true);

		// cleaned up resource, otherwise #createTestBuilder() above will fail next time this method is called
		XtextResourceCleanUtil.cleanXtextResource(resource);

		ArrayList<String> result = Lists.newArrayList();
		if (hyperlinks != null) {
			for (IHyperlink hyperlink : hyperlinks) {
				result.add(getTargetDescription(resource, hyperlink));
			}
		}

		expectation.assertEquals(result);
	}

	private String getTargetDescription(XtextResource resource, IHyperlink hyperlink) {
		final StringBuffer sb = new StringBuffer();
		// append hyperlink text
		final String hyperlinkText = hyperlink.getHyperlinkText();
		if (hyperlinkText != null)
			sb.append(hyperlinkText);
		else
			sb.append("<no hyperlink text>");
		// append description of target element (path from the element to the root of the AST)
		final EObject target = getTarget(resource, hyperlink);
		if (target != null) {
			// build chain of ancestor AST elements
			sb.append(": ");
			final int startLen = sb.length();
			EObject currTarget = target;
			while (currTarget != null) {
				if (currTarget instanceof NamedElement || currTarget instanceof IdentifiableElement) {
					if (sb.length() > startLen)
						sb.append(" in ");
					String name = currTarget instanceof NamedElement ? ((NamedElement) currTarget).getName()
							: ((IdentifiableElement) currTarget).getName();
					if (name == null || name.trim().length() == 0)
						name = "<unnamed>";
					else
						name = "\"" + name + "\"";
					sb.append(name + "(" + currTarget.eClass().getName() + ")");
				}
				currTarget = currTarget.eContainer();
			}
			// add URI of resource
			final URI targetResURI = target.eResource() != null ? target.eResource().getURI() : null;
			final String fname = targetResURI != null ? targetResURI.lastSegment() : null;
			if (fname != null && fname.trim().length() > 0) {
				sb.append(" in file ");
				sb.append(fname);
			}
		}
		return sb.toString();
	}

	private EObject getTarget(XtextResource resource, IHyperlink hyperlink) {
		final ResourceSet resourceSet = resource != null ? resource.getResourceSet() : null;
		final URI uri = hyperlink instanceof XtextHyperlink ? ((XtextHyperlink) hyperlink).getURI() : null;
		final EObject target = resourceSet != null && uri != null ? resourceSet.getEObject(uri, true) : null;
		if (target instanceof SyntaxRelatedTElement)
			return ((SyntaxRelatedTElement) target).getAstElement();
		return target;
	}

}
