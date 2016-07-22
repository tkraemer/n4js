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

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.validation.Issue;

/**
 * Matches against an instance of {@link Issue}.
 */
public class IssueMatcher {
	/**
	 * A matching specification for a particular attribute of an issue.
	 */
	private static class Spec<T> {
		private final boolean ignore;
		private T value;

		/**
		 * Creates a matching specification that matches against the given attribute value.
		 *
		 * @param value
		 *            the value to match
		 */
		Spec(T value) {
			this.ignore = false;
			this.value = value;
		}

		/**
		 * Creates a matching specification that ignores all attribute values.
		 */
		Spec() {
			this.ignore = true;
		}

		/**
		 * Matches the given candidate value against this specification.
		 *
		 * @param candidate
		 *            the candidate value to match against
		 * @return true if this specification is ignored or if the given value matches the value that this specification
		 *         was initialized with
		 */
		boolean match(T candidate) {
			if (ignore)
				return true;
			return doMatch(value, candidate);
		}

		protected boolean doMatch(T expected, T actual) {
			return actual.equals(expected);
		}
	}

	private static class URISpec extends Spec<URI> {
		URISpec(URI uri) {
			super(uri);
		}

		URISpec() {
			super();
		}

		@Override
		protected boolean doMatch(URI expected, URI actual) {
			List<String> expectedSegments = expected.segmentsList();
			List<String> actualSegments = actual.segmentsList();
			if (expectedSegments.size() > actualSegments.size())
				return false;

			for (int i = 0; i < expectedSegments.size(); i++) {
				String actualSegment = actualSegments.get(actualSegments.size() - i - 1);
				String expectedSegment = expectedSegments.get(expectedSegments.size() - i - 1);
				if (!actualSegment.equals(expectedSegment))
					return false;
			}

			return true;
		}
	}

	private Spec<Severity> severitySpec = new Spec<>();
	private Spec<String> messageSpec = new Spec<>();
	private Spec<String> codeSpec = new Spec<>();
	private Spec<CheckType> typeSpec = new Spec<>();
	private Spec<URI> uriToProblemSpec = new Spec<>();
	private URISpec fileSpec = new URISpec();
	private Spec<Integer> lineNumberSpec = new Spec<>();
	private Spec<Integer> columnSpec = new Spec<>();
	private Spec<Integer> offsetSpec = new Spec<>();
	private Spec<Integer> lengthSpec = new Spec<>();
	private Spec<Boolean> syntaxErrorSpec = new Spec<>();

	/**
	 * Configures this issue matcher to match the given severity.
	 *
	 * @param severity
	 *            the severity to match
	 * @return this issue matcher
	 */
	public IssueMatcher severity(Severity severity) {
		this.severitySpec = new Spec<>(severity);
		return this;
	}

	/**
	 * Configures this issue matcher to match the given message.
	 *
	 * @param message
	 *            the message to match
	 * @return this issue matcher
	 */
	public IssueMatcher message(String message) {
		this.messageSpec = new Spec<>(message);
		return this;
	}

	/**
	 * Configures this issue matcher to match the given code.
	 *
	 * @param code
	 *            the code to match
	 * @return this issue matcher
	 */
	public IssueMatcher code(String code) {
		this.codeSpec = new Spec<>(code);
		return this;
	}

	/**
	 * Configures this issue matcher to match the given check type.
	 *
	 * @param checkType
	 *            the check type to match
	 * @return this issue matcher
	 */
	public IssueMatcher type(CheckType checkType) {
		this.typeSpec = new Spec<>(checkType);
		return this;
	}

	/**
	 * Configures this issue matcher to match the given URI.
	 *
	 * @param uriToProblem
	 *            the URI to match
	 * @return this issue matcher
	 */
	public IssueMatcher uriToProblem(URI uriToProblem) {
		this.uriToProblemSpec = new Spec<>(uriToProblem);
		return this;
	}

	/**
	 * Configures this issue matcher to match the given file path.
	 *
	 * @param file
	 *            the file path to match
	 * @return this issue matcher
	 */
	public IssueMatcher file(Path file) {
		this.fileSpec = new URISpec(URI.createFileURI(file.toString()));
		return this;
	}

	/**
	 * Configures this issue matcher to match the given line number.
	 *
	 * @param lineNumber
	 *            the line number to match
	 * @return this issue matcher
	 */
	public IssueMatcher lineNumber(Integer lineNumber) {
		this.lineNumberSpec = new Spec<>(lineNumber);
		return this;
	}

	/**
	 * Configures this issue matcher to match the given column
	 *
	 * @param column
	 *            the column to match
	 * @return this issue matcher
	 */
	public IssueMatcher column(Integer column) {
		this.columnSpec = new Spec<>(column);
		return this;
	}

	/**
	 * Configures this issue matcher to match the given offset.
	 *
	 * @param offset
	 *            the offset to match
	 * @return this issue matcher
	 */
	public IssueMatcher offset(Integer offset) {
		this.offsetSpec = new Spec<>(offset);
		return this;
	}

	/**
	 * Configures this issue matcher to match the given length.
	 *
	 * @param length
	 *            the length to match
	 * @return this issue matcher
	 */
	public IssueMatcher length(Integer length) {
		this.lengthSpec = new Spec<>(length);
		return this;
	}

	/**
	 * Configures this issue matcher to match the given syntax error boolean value.
	 *
	 * @param syntaxError
	 *            the boolean value to match
	 * @return this issue matcher
	 */
	public IssueMatcher syntaxError(Boolean syntaxError) {
		this.syntaxErrorSpec = new Spec<>(syntaxError);
		return this;
	}

	/**
	 * Configures this issue matcher to match the given line number and column.
	 *
	 * @param lineNumber
	 *            the line number to match
	 * @param column
	 *            the column to match
	 * @return this issue matcher
	 */
	public IssueMatcher at(Integer lineNumber, Integer column, String file) {
		return this.lineNumber(lineNumber).column(column).file(new File(file).toPath());
	}

	/**
	 * Matches this issue matcher against the given issue.
	 *
	 * @param issue
	 *            the issue to match against
	 * @return <code>true</code> if this matcher matches the given issue or <code>false</code> otherwise
	 */
	public boolean matches(Issue issue) {
		if (!severitySpec.match(issue.getSeverity()))
			return false;
		if (!typeSpec.match(issue.getType()))
			return false;
		if (!lineNumberSpec.match(issue.getLineNumber()))
			return false;
		if (!columnSpec.match(issue.getColumn()))
			return false;
		if (!offsetSpec.match(issue.getOffset()))
			return false;
		if (!lengthSpec.match(issue.getLength()))
			return false;
		if (!syntaxErrorSpec.match(issue.isSyntaxError()))
			return false;
		if (!messageSpec.match(issue.getMessage()))
			return false;
		if (!codeSpec.match(issue.getCode()))
			return false;
		if (!uriToProblemSpec.match(issue.getUriToProblem()))
			return false;
		if (!fileSpec.match(issue.getUriToProblem()))
			return false;
		return true;
	}
}
