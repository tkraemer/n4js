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
package eu.numberfour.n4js.ui.organize.imports;

/**
 * Exception in Case the user canceled, or an automatic resolution was not possible
 */
public class BreakException extends Exception {

	/**
	 *
	 */
	public BreakException() {
	}

	/**
	 */
	public BreakException(String message) {
		super(message);
	}

	/**
	 */
	public BreakException(Throwable cause) {
		super(cause);
	}

	/**
	 */
	public BreakException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 */
	public BreakException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
