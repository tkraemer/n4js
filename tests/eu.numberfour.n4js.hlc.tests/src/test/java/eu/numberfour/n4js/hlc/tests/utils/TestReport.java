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
package eu.numberfour.n4js.hlc.tests.utils;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.parseInt;

import java.util.Collection;

import org.xml.sax.Attributes;

/**
 * Representation of a test report. Consist of some noteworthy general information about the test suite and all the
 * failed test cases.
 */
public class TestReport {

	private final Collection<TestCase> failureCases;
	private final String timestamp;
	private final int tests;
	private final int errors;
	private final int skipped;
	private final int failures;

	/**
	 * Creates a new test report instance with the {@link Attributes attributes} of the test suit element.
	 *
	 * @param attributes
	 *            the attributes of the element.
	 */
	/* default */ TestReport(final Attributes attributes) {
		this.timestamp = attributes.getValue("timestamp");
		this.tests = parseInt(attributes.getValue("tests"));
		this.errors = parseInt(attributes.getValue("errors"));
		this.skipped = parseInt(attributes.getValue("skipped"));
		this.failures = parseInt(attributes.getValue("failures"));
		failureCases = newArrayList();
	}

	/**
	 * Appends a new failure test case to the report.
	 *
	 * @param tc
	 *            the failed case to append to the report.
	 * @return this.
	 */
	/* default */ TestReport appendFailure(final TestCase tc) {
		failureCases.add(tc);
		return this;
	}

	/**
	 * Returns with {@code true} if the report contains any failure test cases.
	 *
	 * @return {@code true} if failed, otherwise {@code false}.
	 */
	public boolean isFailed() {
		checkState(failures == failureCases.size(), "Failure test case count mismatch. Failures: " + failures
				+ " | Collected failure cases: " + failureCases.size());
		return !failureCases.isEmpty();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Tests: ");
		sb.append(tests);
		sb.append(" Errors: ");
		sb.append(errors);
		sb.append(" Skipped: ");
		sb.append(skipped);
		sb.append(" Failures: ");
		sb.append(failures);
		sb.append("\nTimestamp: ");
		sb.append(timestamp);
		for (final TestCase tc : failureCases) {
			sb.append("\n");
			sb.append(tc);
		}
		return sb.toString();
	}

}
