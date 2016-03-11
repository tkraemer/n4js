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
package eu.numberfour.n4js.utils.process

import java.util.Collection
import org.eclipse.xtend.lib.annotations.AccessorType
import org.eclipse.xtend.lib.annotations.Accessors

import static extension org.eclipse.xtend.lib.annotations.AccessorType.*

/**
 * Representation of the result of a terminated process.
 */
@Accessors(AccessorType.PUBLIC_GETTER)
class ProcessResult {

	val static LN = System.lineSeparator;

	val int exitCode;
	val Collection<String> stdOut;
	val Collection<String> stdErr;

	package new(int exitCode, Iterable<String> stdOut, Iterable<String> stdErr) {
		this.exitCode = exitCode;
		this.stdOut = stdOut.toList.unmodifiableView;
		this.stdErr = stdErr.toList.unmodifiableView;
	}

	/** Returns with {@code true} if the exit code is {@code 0}, otherwise {@code false}. */
	def isOK() {
		return 0 === exitCode;
	}

	/** Returns with the standard output content as a string. */
	def getStdOutString() {
		stdOut.join(LN);
	}

	/** Returns with the standard error output content as a string. */
	def getStdErrString() {
		stdErr.join(LN);
	}

	@Override
	override toString() {
		"Exit code:" + exitCode + LN +"Standard out:" + LN + stdOutString + LN + "Standard error:" + LN + stdErrString;
	}

}
