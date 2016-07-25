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

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.validation.Issue;

/**
 * Matches an issue against an expectation for a URI property. URI expectations can test for prefix, suffix, and equals.
 *
 * @see URI
 */
public class URIPropertyMatcher implements IssuePropertyMatcher {

	/**
	 * The matching mode.
	 */
	public static enum Mode {
		/**
		 * Prefix matching mode.
		 */
		StartsWith,
		/**
		 * Suffix matching mode.
		 */
		EndsWith,
		/**
		 * Equals matching mode.
		 */
		Equals
	}

	private final Mode mode;
	private final URI expectedPattern;
	private final Function<Issue, URI> getActualValue;

	/**
	 * Creates a new URI property matcher.
	 *
	 * @param mode
	 *            the matching mode
	 * @param expectedPattern
	 *            the expected pattern (the actual interpretation depends on the matching mode)
	 * @param getActualValue
	 *            a function to obtain the actual value of an URI property from an instance of {@link Issue}
	 */
	protected URIPropertyMatcher(Mode mode, URI expectedPattern, Function<Issue, URI> getActualValue) {
		this.mode = mode;
		this.expectedPattern = Objects.requireNonNull(expectedPattern);
		this.getActualValue = Objects.requireNonNull(getActualValue);
	}

	@Override
	public boolean matches(Issue issue) {
		URI actualValue = getActualValue.apply(issue);
		if (actualValue == null)
			return false;

		List<String> actualSegments = actualValue.segmentsList();
		List<String> expectedSegments = expectedPattern.segmentsList();

		switch (mode) {
		case StartsWith:
			return Collections.indexOfSubList(actualSegments, expectedSegments) == 0;
		case EndsWith:
			return Collections.lastIndexOfSubList(actualSegments, expectedSegments) == actualSegments.size()
					- expectedSegments.size();
		case Equals:
			return actualSegments.equals(expectedSegments);
		}

		throw new RuntimeException("Unhandled enum value: " + mode);
	}

}
