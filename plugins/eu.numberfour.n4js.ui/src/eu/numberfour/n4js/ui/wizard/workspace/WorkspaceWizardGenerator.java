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
}
