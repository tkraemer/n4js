/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.fileextensions;

/**
 * The type of file extension, e.g. runnable file extension.
 */
public enum FileExtensionType {
	/**
	 * Transpilable file extension
	 */
	TRANSPILABLE_FILE_EXTENSION,
	/**
	 * Testable file extension
	 */
	TESTABLE_FILE_EXTENSION,
	/**
	 * Runnable file extension
	 */
	RUNNABLE_FILE_EXTENSION,
	/**
	 * Files affect type resolution. Note that this might include some surprising entries, e.g. JS or N4JSD. In general
	 * expect here all file extensions for all supported languages that you can encounter as files in the N4IDE. This
	 * information may be used in various context, e.g. calculating changes in source containers, organizing imports is
	 * source files, e tc.
	 *
	 * Some unexpected entries here would include {@code MANIFEST} files, {@code TS} files.
	 */
	TYPABLE_FILE_EXTENSION
}