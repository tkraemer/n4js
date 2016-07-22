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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.xtext.validation.Issue;

/**
 * Signaling severe Problems in Compilation
 */
public class N4JSCompileErrorException extends N4JSCompileException implements N4ProgressStateRecorder.IProgressState {

	private final String projectId;

	private List<Issue> errors;

	/**
	 * @param message
	 *            user-message
	 * @param projectId
	 *            erroneous project
	 */
	public N4JSCompileErrorException(String message, String projectId) {
		super(message);
		this.projectId = projectId;
	}

	/**
	 * @param message
	 *            user-message
	 * @param projectId
	 *            erroneous project
	 * @param t
	 *            nested cause
	 */
	public N4JSCompileErrorException(String message, String projectId, Throwable t) {
		super(message, t);
		this.projectId = projectId;
	}

	/**
	 * @param message
	 *            user-message
	 * @param projectId
	 *            erroneous project
	 * @param errors
	 *            list of compilation errors
	 */
	public N4JSCompileErrorException(String message, String projectId, List<Issue> errors) {
		super(message);
		this.projectId = projectId;
		this.errors = errors;
	}

	/**
	 * @param message
	 *            user-message
	 * @param projectId
	 *            erroneous project
	 * @param errors
	 *            list of compilation errors
	 * @param t
	 *            nested cause
	 */
	public N4JSCompileErrorException(String message, String projectId, List<Issue> errors, Throwable t) {
		super(message, t);
		this.projectId = projectId;
		this.errors = errors;
	}

	/**
	 * @return name of erroneous project
	 */
	public String getProjectId() {
		return projectId;
	}

	@Override
	public boolean hasAnyIssueOf(Collection<IssueMatcher> matchers) {
		for (IssueMatcher matcher : matchers) {
			if (matchesAny(matcher))
				return true;
		}
		return false;
	}

	@Override
	public boolean hasAllIssuesOf(Collection<IssueMatcher> matchers) {
		for (IssueMatcher matcher : matchers) {
			if (!matchesAny(matcher))
				return false;
		}
		return true;
	}

	private boolean matchesAny(IssueMatcher matcher) {
		for (Issue error : errors) {
			if (matcher.matches(error))
				return true;
		}
		return false;
	}

	@Override
	public boolean hasExactly(Collection<IssueMatcher> matchers) {
		if (errors.size() != matchers.size())
			return false;

		LinkedList<IssueMatcher> unmatchedMatchers = new LinkedList<>(matchers);
		for (Issue error : errors) {
			if (!findAndRemoveMatch(error, unmatchedMatchers))
				return false;
		}
		return unmatchedMatchers.isEmpty();
	}

	private boolean findAndRemoveMatch(Issue issue, List<IssueMatcher> matchers) {
		Iterator<IssueMatcher> matcherIt = matchers.iterator();
		while (matcherIt.hasNext()) {
			IssueMatcher matcher = matcherIt.next();
			if (matcher.matches(issue)) {
				matcherIt.remove();
				return true;
			}
		}

		return false;
	}
}
