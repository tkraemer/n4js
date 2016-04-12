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
package eu.numberfour.n4js.ui.wizard.workspace;

import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;

/**
 *
 */
public class WorkspaceWizardValidatorUtils {

	private static final Pattern VALID_FOLDER_NAME_PATTERN = Pattern
			.compile("[a-zA-z_](([\\.][a-zA-z_0-9\\-])|[a-zA-z_0-9\\-])*");

	/**
	 * Check whether name is a valid folder name.
	 *
	 * @param name
	 *            Name to check
	 * @return valid state
	 */
	public static boolean isValidFolderName(String name) {
		return VALID_FOLDER_NAME_PATTERN.matcher(name).matches();
	}

	/**
	 * Returns {@code true} if path is a valid folder path.
	 *
	 * That means that every segment needs to be a valid folder name.
	 */
	public static boolean isValidFolderPath(IPath path) {
		// TODO revise for Windows-systems, c.f. Path.isValidPath() and the Separator handling there.
		for (String segment : path.segments()) {
			if (!isValidFolderName(segment)) {
				return false;
			}
		}
		return true;
	}

	private WorkspaceWizardValidatorUtils() {
		// Make it non instantiable
	}
}
