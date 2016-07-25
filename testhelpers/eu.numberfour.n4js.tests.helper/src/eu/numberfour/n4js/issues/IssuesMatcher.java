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
package eu.numberfour.n4js.issues;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.xtext.validation.Issue;

/**
 *
 */
public class IssuesMatcher {
	private Collection<IssueMatcher> issueMatchers;

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
	 * @return <code>true</code> if and only if every expectation was matched against an issue and every issue in the
	 *         given collection was matched by an expectation
	 */
	public boolean matchesExactly(Collection<Issue> issues) {
		Collection<Issue> issueCopy = new LinkedList<>(issues);
		Collection<IssueMatcher> matcherCopy = new LinkedList<>(issueMatchers);

		performMatching(issueCopy, matcherCopy);
		return issueCopy.isEmpty() && matcherCopy.isEmpty();
	}

	/**
	 * Matches the expectations in the added issues matchers against the given issues.
	 *
	 * @param issues
	 *            the issues to match the expectations against
	 * @return <code>true</code> if and only if every expectation was matched against an issue
	 */
	public boolean matchesAllExpectations(Collection<Issue> issues) {
		Collection<Issue> issueCopy = new LinkedList<>(issues);
		Collection<IssueMatcher> matcherCopy = new LinkedList<>(issueMatchers);

		performMatching(issueCopy, matcherCopy);
		return matcherCopy.isEmpty();
	}

	/**
	 * Matches the expectations in the added issues matchers against the given issues.
	 *
	 * @param issues
	 *            the issues to match the expectations against
	 * @return <code>true</code> if and only if every issue in the given collection was matched by an expectation
	 */
	public boolean matchesAllIssues(Collection<Issue> issues) {
		Collection<Issue> issueCopy = new LinkedList<>(issues);
		Collection<IssueMatcher> matcherCopy = new LinkedList<>(issueMatchers);

		performMatching(issueCopy, matcherCopy);
		return issueCopy.isEmpty();
	}

	private void performMatching(Collection<Issue> issues, Collection<IssueMatcher> matchers) {
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
				}
			}
		}
	}
}
