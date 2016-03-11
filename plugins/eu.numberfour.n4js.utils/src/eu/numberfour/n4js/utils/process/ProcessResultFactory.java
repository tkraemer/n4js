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
package eu.numberfour.n4js.utils.process;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.team.ResourceRuleFactory;

/**
 * Factory for building {@link ProcessResult result} objects from a spawned {@link Process process}.
 */
public class ProcessResultFactory {

	private static final Logger LOGGER = Logger.getLogger(ResourceRuleFactory.class);

	/**
	 * Waits until the process terminates, the returns with a result wrapping the exit code of the terminated process
	 * and the standard output and error output of the process.
	 *
	 * @param process
	 *            the process to get the result from.
	 * @return a new result object that represents the actual result of the process argument.
	 */
	public ProcessResult createNewResult(final Process process) {
		checkNotNull(process, "process");

		int exitCode;
		try {
			exitCode = process.waitFor();
		} catch (final InterruptedException e) {
			LOGGER.error("Thread was interrupted while waiting for process to end.", e);
			return new ProcessResult(-1, writeStackeTrace(e), emptyList());
		}

		try (final BufferedReader outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				final BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

			final Collection<String> stdOut = newArrayList();
			final Collection<String> stdErr = newArrayList();
			stdOut.addAll(readThrough(outReader));
			stdErr.addAll(readThrough(errReader));

			return new ProcessResult(exitCode, stdOut, stdErr);

		} catch (final IOException e) {
			LOGGER.error("Error while reading the content of the process input streams.", e);
			return new ProcessResult(exitCode, writeStackeTrace(e), emptyList());
		}
	}

	private Collection<String> readThrough(final BufferedReader r) throws IOException {
		final Collection<String> lines = newArrayList();
		String line = null;
		while (null != (line = r.readLine())) {
			lines.add(line);
		}
		return lines;
	}

	private Collection<String> writeStackeTrace(final Throwable t) {
		final Collection<String> lines = newArrayList();
		lines.add(t.getClass().getName());
		for (final StackTraceElement element : t.getStackTrace()) {
			final StringBuilder sb = new StringBuilder();
			sb.append("\tat ");
			sb.append(element.getClassName());
			sb.append(".");
			sb.append(element.getMethodName());
			sb.append("(");
			sb.append(element.getFileName());
			sb.append(":");
			sb.append(element.getLineNumber());
			sb.append(")");
			lines.add(sb.toString());
		}
		return lines;
	}

}
