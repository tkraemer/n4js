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

/**
 * Functional interface for commands encapsulating process execution.
 */
@FunctionalInterface
public interface ProcessExecutionCommand {
	/**
	 * @return result of the process execution
	 */
	public ProcessResult execute();
}
