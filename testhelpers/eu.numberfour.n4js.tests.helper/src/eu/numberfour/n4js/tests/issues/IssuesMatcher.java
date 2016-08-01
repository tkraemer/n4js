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
package eu.numberfour.n4js.tests.issues;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.xtext.validation.Issue;

/**
 * Matches a collection of {@link IssueMatcher}s against a collection of errors.
 */
public class IssuesMatcher {
	private final Collection<IssueMatcher> issueMatchers = new LinkedList<>();

	/**
	 * Creates a new issue matcher and adds it to this matcher.
	 *
	 * @return the newly created issue matcher
	 */
	public IssueMatcher add() {
		IssueMatcher issueMatcher = new IssueMatcher();
		issueMatchers.add(issueMatcher);
		return issueMatcher;
	}

	/**
	 * Matches the expectations in the added issues matchers against the given issues.
	 *
	 * @param issues
	 *            the issues to match the expectations against
	 * @param messages
	 *            if this parameter is not <code>null</code>, this method will add an explanatory message for each
	 *            mismatch
	 * @return <code>true</code> if and only if every expectation was matched against an issue and every issue in the
	 *         given collection was matched by an expectation
	 */
	public boolean matchesExactly(Collection<Issue> issues, List<String> messages) {
		Collection<Issue> issueCopy = new LinkedList<>(issues);
		Collection<IssueMatcher> matcherCopy = new LinkedList<>(issueMatchers);

		performMatching(issueCopy, matcherCopy, messages);
		explainUnmatchedIssues(issueCopy, messages);
		explainUnmatchedExpectations(matcherCopy, messages);
		return issueCopy.isEmpty() && matcherCopy.isEmpty();
	}

	/**
	 * Matches the expectations in the added issues matchers against the given issues.
	 *
	 * @param issues
	 *            the issues to match the expectations against
	 * @param messages
	 *            if this parameter is not <code>null</code>, this method will add an explanatory message for each
	 *            mismatch
	 * @return <code>true</code> if and only if every expectation was matched against an issue
	 */
	public boolean matchesAllExpectations(Collection<Issue> issues, List<String> messages) {
		Collection<Issue> issueCopy = new LinkedList<>(issues);
		Collection<IssueMatcher> matcherCopy = new LinkedList<>(issueMatchers);

		performMatching(issueCopy, matcherCopy, messages);
		explainUnmatchedExpectations(matcherCopy, messages);
		return matcherCopy.isEmpty();
	}

	/**
	 * Matches the expectations in the added issues matchers against the given issues.
	 *
	 * @param issues
	 *            the issues to match the expectations against
	 * @param messages
	 *            if this parameter is not <code>null</code>, this method will add an explanatory message for each
	 *            mismatch
	 * @return <code>true</code> if and only if every issue in the given collection was matched by an expectation
	 */
	public boolean matchesAllIssues(Collection<Issue> issues, List<String> messages) {
		Collection<Issue> issueCopy = new LinkedList<>(issues);
		Collection<IssueMatcher> matcherCopy = new LinkedList<>(issueMatchers);

		performMatching(issueCopy, matcherCopy, messages);
		explainUnmatchedIssues(issueCopy, messages);
		return issueCopy.isEmpty();
	}

	private void explainUnmatchedIssues(Collection<Issue> unmatchedIssues, List<String> messages) {
		if (messages != null) {
			for (Issue issue : unmatchedIssues) {
				messages.add("Unexpected issue: " + issue);
			}
		}
	}

	private void explainUnmatchedExpectations(Collection<IssueMatcher> unmatchedMatchers, List<String> messages) {
		if (messages != null) {
			for (IssueMatcher matcher : unmatchedMatchers) {
				messages.add("Unmatched expectation: " + matcher.getDescription());
			}
		}
	}

	private void performMatching(Collection<Issue> issues, Collection<IssueMatcher> matchers, List<String> messages) {
		Iterator<Issue> issueIt = issues.iterator();
		while (issueIt.hasNext() && !matchers.isEmpty()) {
			Issue issue = issueIt.next();

			Iterator<IssueMatcher> matcherIt = matchers.iterator();
			while (matcherIt.hasNext()) {
				IssueMatcher matcher = matcherIt.next();
				if (matcher.matches(issue)) {
					issueIt.remove();
					matcherIt.remove();
					break;
				} else if (messages != null) {
					messages.addAll(matcher.explainMismatch(issue));
				}
			}
		}
	}
}
