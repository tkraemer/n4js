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
package eu.numberfour.n4js.xpect.ui.xpectmethods;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.validation.Issue;
import org.junit.ComparisonFailure;
import org.xpect.expectation.ActualCollection;
import org.xpect.expectation.ActualCollection.ActualItem;
import org.xpect.expectation.ExpectationCollection;
import org.xpect.expectation.ExpectationCollection.ExpectationItem;
import org.xpect.expectation.ILinesExpectation;
import org.xpect.expectation.LinesExpectation;
import org.xpect.expectation.LinesExpectation.LinesExpectationImpl;
import org.xpect.runner.Xpect;
import org.xpect.text.Text;
import org.xpect.xtext.lib.setup.ThisOffset;
import org.xpect.xtext.lib.setup.ThisResource;
import org.xpect.xtext.lib.tests.ValidationTestModuleSetup.IssuesByLine;
import org.xpect.xtext.lib.util.IssueFormatter;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * *** WARNING *** WARNING *** WARNING ***
 *
 * This file is a copy of (to avoid dependencies from production code to test code):
 * /eu.numberfour.n4js.tests.helper/src/eu/numberfour/n4js/xpect/validation/NoerrorsXpectMethod.java
 */
public class NoerrorsXpectMethod {

	/**
	 * Tests that NO error is issued at given position. Parameters are similar to error test.
	 */
	@Xpect
	public void noerrors(@LinesExpectation ILinesExpectation expectation, @ThisResource XtextResource resource,
			@IssuesByLine Multimap<Integer, Issue> line2issue, @ThisOffset int offset) {
		List<String> issues = format(resource, line2issue.get(offset), Severity.ERROR);
		NoerrorsExpectationDelegate noerrorsExpectationDelegate = new NoerrorsExpectationDelegate(
				(LinesExpectationImpl) expectation);
		noerrorsExpectationDelegate.assertEquals(issues);
		// expectation.assertEquals(issues);
	}

	/**
	 * Tests that NO warnings is issued at given position. Parameters are similar to error test.
	 */
	@Xpect
	public void nowarnings(@LinesExpectation ILinesExpectation expectation, @ThisResource XtextResource resource,
			@IssuesByLine Multimap<Integer, Issue> line2issue, @ThisOffset int offset) {
		List<String> issues = format(resource, line2issue.get(offset), Severity.WARNING);
		NoerrorsExpectationDelegate noerrorsExpectationDelegate = new NoerrorsExpectationDelegate(
				(LinesExpectationImpl) expectation);
		noerrorsExpectationDelegate.assertEquals(issues);
		// expectation.assertEquals(issues);
	}

	private Function<? super Issue, String> createIssueFormatter(XtextResource resource,
			@SuppressWarnings("unused") Severity severity) {
		return new IssueFormatter(resource, false);
	}

	private List<String> format(XtextResource resource, Collection<Issue> issues, Severity severity) {
		Function<? super Issue, String> formatter = createIssueFormatter(resource, severity);
		List<String> formattedIssues = newArrayList();
		for (Issue issue : issues)
			formattedIssues.add(formatter.apply(issue));
		return formattedIssues;
	}
}

class NoerrorsExpectationDelegate extends LinesExpectationImpl {

	public NoerrorsExpectationDelegate(LinesExpectationImpl delegate) {
		super(delegate.getAnnotation(), delegate.getTargetSyntax(), delegate.getRegion());
	}

	@Override
	protected void assertEquals(String message, Iterable<?> actual, boolean forceFail) {

		if (actual == null) {
			actual = new ArrayList<>();
			// Assert.assertNotNull(actual);
		}

		ExpectationCollection exp = new ExpectationCollection();
		exp.setCaseSensitive(getAnnotation().caseSensitive());
		exp.setOrdered(getAnnotation().ordered());
		exp.setQuoted(getAnnotation().quoted());
		exp.setSeparator('\n');
		exp.setWhitespaceSensitive(getAnnotation().whitespaceSensitive());
		exp.init(getExpectation());

		ActualCollection act = new ActualCollection();
		act.setTargetLiteralSupport(getTargetSyntaxLiteral());
		act.setCaseSensitive(getAnnotation().caseSensitive());
		act.setOrdered(getAnnotation().ordered());
		act.setQuoted(getAnnotation().quoted());
		act.setSeparator('\n');
		act.setWhitespaceSensitive(getAnnotation().whitespaceSensitive());
		act.init(actual, getAnnotation().itemFormatter());

		// if (!exp.matches(act)) {
		if (actual.iterator().hasNext()) { // we found an error
			List<String> expString = Lists.newArrayList();
			List<String> actString = Lists.newArrayList();
			for (Pair<Collection<ExpectationItem>, ActualItem> pair : exp.map(act)) {
				if (pair.getFirst() != null && !pair.getFirst().isEmpty()) {
					if (pair.getSecond() != null)
						expString.add("No error expected: " + pair.getSecond().getEscaped());
					else
						expString.add("No error expected: " + pair.getFirst().iterator().next().getEscaped());
				}
				if (pair.getSecond() != null)
					actString.add(pair.getSecond().getEscaped());
			}
			String nl = new Text(getRegion().getDocument()).getNL();
			String expDoc = replaceInDocument(Joiner.on(nl).join(expString));
			String actDoc = replaceInDocument(Joiner.on(nl).join(actString));

			throw new ComparisonFailure(message, expDoc, actDoc);
		}

	}

	@Override
	public void fail(String message, Iterable<?> actual) {
		assertEquals(message, actual, true);
	}

}
