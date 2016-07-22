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

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

/**
 */
public class N4JSCompileException extends Exception {

	/**
	 *
	 */
	public N4JSCompileException() {
	}

	/**
	 */
	public N4JSCompileException(String message) {
		super(message);
	}

	/**
	 */
	public N4JSCompileException(Throwable cause) {
		super(cause);
	}

	/**
	 */
	public N4JSCompileException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 */
	public N4JSCompileException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param stream
	 *            stream to print to
	 */
	public void userDump(PrintStream stream) {
		stream.println(getMessage());
	}

	/**
	 * Check whether any of the given issue matchers matches at least one issue that caused this exception.
	 *
	 * @param matcher
	 *            the matcher to match against the issues
	 * @return <code>true</code> if one of the issues matches the given issue and <code>false</code> otherwise
	 */
	public final boolean hasIssue(IssueMatcher matcher) {
		return hasAnyIssueOf(Arrays.asList(matcher));
	}

	/**
	 * Check whether any of the given issue matchers matches at least one issue that caused this exception. The order of
	 * the given matchers does not matter.
	 *
	 * This method can be used to check whether a compilation produced one of a set of issues, but possibly more.
	 *
	 * @param matchers
	 *            the matchers to match against the issues
	 * @return <code>true</code> if one of the given matchers matches against at least one issue and <code>false</code>
	 *         otherwise
	 */
	public boolean hasAnyIssueOf(Collection<IssueMatcher> matchers) {
		return false;
	}

	/**
	 * Check whether each of the given issue matchers matches at least one issue that caused this exception. Note that,
	 * even if this method returns <code>true</code>, there may be more issues that do not match against any of the
	 * given matchers. The order of the given matchers does not matter.
	 *
	 * This method can be used to check whether a compilation produced at least a set of issues, but possibly more.
	 *
	 * @param matchers
	 *            the matchers to match against the issues
	 * @return <code>true</code> if each of the given matchers matches against at least one issue and <code>false</code>
	 *         otherwise
	 */
	public boolean hasAllIssuesOf(Collection<IssueMatcher> matchers) {
		return false;
	}

	/**
	 * Check whether there was exactly one issue that caused this exception.
	 *
	 * @param matcher
	 *            the matcher to match against the issues
	 * @return <code>true</code> if this exception had one issue that matched the given matcher and <code>false</code>
	 *         otherwise
	 */
	public final boolean hasExactly(IssueMatcher matcher) {
		return hasExactly(Arrays.asList(matcher));
	}

	/**
	 * Check whether each of the given issue matchers matches exactly one issue that caused this exception and vice
	 * versa. If this method returns <code>true</code>, then all issues have been matched by exactly one matcher, and
	 * each matcher has matched exactly one issue. The order of the given matchers does not matter.
	 *
	 * This method can be used to check whether a compilation had an exact set of issues, and no other issues.
	 *
	 * @param matchers
	 *            the matchers to match against the issues
	 * @return <code>true</code> if each of the given matchers matches against exactly one issue and vice versa, and
	 *         <code>false</code> otherwise
	 */
	public boolean hasExactly(Collection<IssueMatcher> matchers) {
		return false;
	}
}
