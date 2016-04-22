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

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A wizard generator which allows to preview the generated file.
 *
 */
public interface WorkspaceWizardGenerator<M extends WorkspaceWizardModel> {
	/**
	 * Returns the string of code specified by the given model.
	 *
	 * For existing target modules the import statements are fully aliased
	 *
	 * @param model
	 *            The model for which a preview should be generated
	 * @return The file content
	 */
	public ContentBlock[] generateContentPreview(M model);

	/**
	 * Writes the given model to its file.
	 *
	 * This may imply the creation of a new file or insertion into an existing file.
	 *
	 * @param model
	 *            The model to write
	 * @param monitor
	 *            A monitor to report progress to
	 * @return {@code true} on success, {@code false} otherwise
	 */
	public boolean writeToFile(M model, IProgressMonitor monitor);

	/**
	 * Performs all necessary changes in the manifest file to prepare the creation of the model's workspace element.
	 *
	 * @param model
	 *            The model to change the manifest for
	 * @return {@code true} on success, {@code false} otherwise
	 */
	public boolean performManifestChanges(M model, IProgressMonitor monitor);

}
