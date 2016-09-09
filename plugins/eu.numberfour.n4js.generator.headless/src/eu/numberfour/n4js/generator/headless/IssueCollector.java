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
package eu.numberfour.n4js.generator.headless;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;

import org.eclipse.xtext.validation.Issue;

/**
 * Collects issues during a compilation. The collected issues can be accessed using the {@link #getCollectedIssues()}
 * method. An issue collector can optionally be configured with an instance of {@link IssueFilter} to filter the issues
 * before collecting them. By default, the issue collector will collect all issues.
 */
public class IssueCollector implements IssueAcceptor {

	private final IssueFilter issueFilter;
	private final Collection<Issue> collectedIssues;

	/**
	 * Creates a new issue collector instance that accepts only those issues that match the given issue filter.
	 *
	 * @param issueFilter
	 *            the issue filter to use, which must not be <code>null</code>
	 */
	public IssueCollector(IssueFilter issueFilter) {
		this.issueFilter = Objects.requireNonNull(issueFilter);
		this.collectedIssues = new LinkedList<>();
	}

	/**
	 * Creates a new issue collector instance that accepts all issues.
	 */
	public IssueCollector() {
		this(new AcceptingIssueFilter());
	}

	@Override
	public void accept(Issue issue) {
		if (issueFilter.matches(issue))
			collectedIssues.add(issue);
	}

	/**
	 * Returns the issues collected during compilation.
	 *
	 * @return a collection containing the collected issues
	 */
	public Collection<Issue> getCollectedIssues() {
		return collectedIssues;
	}
}
