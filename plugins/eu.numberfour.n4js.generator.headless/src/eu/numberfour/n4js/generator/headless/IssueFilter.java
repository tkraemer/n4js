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

import org.eclipse.xtext.validation.Issue;

/**
 * Filters Xtext validation issues.
 */
public interface IssueFilter {
	/**
	 * Matches the given issue against this filter.
	 *
	 * @param issue
	 *            the issue to match
	 * @return <code>true</code> if the given issue matches this filter and <code>false</code> otherwise
	 */
	public boolean matches(Issue issue);
}
