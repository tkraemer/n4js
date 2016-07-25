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

import java.util.Objects;

/**
 * Abstract base class for issue property matcher builders.
 */
public class IssuePropertyMatcherBuilder {
	private final IssueMatcher issueMatcher;

	/**
	 * Creates a new property matcher builder that adds the created property matchers to the given issue matcher.
	 *
	 * @param issueMatcher
	 *            the issue matcher to add the created property matchers to
	 */
	protected IssuePropertyMatcherBuilder(IssueMatcher issueMatcher) {
		this.issueMatcher = Objects.requireNonNull(issueMatcher);
	}

	/**
	 * Adds the given property matcher to the issue matcher.
	 *
	 * @param propertyMatcher
	 *            the property matcher to add
	 * @return the issue matcher
	 */
	protected IssueMatcher addPropertyMatcher(IssuePropertyMatcher propertyMatcher) {
		issueMatcher.addPropertyMatcher(Objects.requireNonNull(propertyMatcher));
		return issueMatcher;
	}
}
