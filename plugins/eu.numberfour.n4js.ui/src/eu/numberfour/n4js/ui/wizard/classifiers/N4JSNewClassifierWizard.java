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
package eu.numberfour.n4js.ui.wizard.classifiers;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.xtext.ui.editor.LanguageSpecificURIEditorOpener;
import org.eclipse.xtext.xbase.lib.StringExtensions;

import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.ui.wizard.workspace.WorkspaceWizardModel;

/**
 * Generic interface for creating new N4JS classifiers. For instance classes and interfaces.
 */
public abstract class N4JSNewClassifierWizard<M extends N4JSClassifierWizardModel> extends Wizard
		implements INewWizard {

	@Inject
	private IN4JSCore n4jsCore;

	@Inject
	private LanguageSpecificURIEditorOpener uriOpener;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.setNeedsProgressMonitor(false);
		this.setWindowTitle("New N4JS " + StringExtensions.toFirstUpper(getModel().getClassifierName()));
		parseIntialSelection(selection);
	}

	private void parseIntialSelection(IStructuredSelection selection) {
		WorkspaceWizardModel.fillModelFromInitialSelection(getModel(), selection, n4jsCore);
		getPage().setModel(getModel());
	}

	@Override
	public boolean performFinish() {

		IPath fileLocation = getModel().computeFileLocation();

		// Create missing module folders
		if (!ResourcesPlugin.getWorkspace().getRoot().getFile(fileLocation).exists()) {

			IContainer parent = ResourcesPlugin.getWorkspace().getRoot().getProject(getModel().getProject().toString());

			try { // Iterate through remaining segments but the file segment
				for (String segment : fileLocation.makeRelativeTo(getModel().getProject()).removeLastSegments(1)
						.segments()) {
					IFolder subfolder = parent.getFolder(new Path(segment));
					if (!subfolder.exists()) {
						subfolder.create(true, true, null);
					}
					parent = subfolder;
				}
			} catch (CoreException e) {
				return false;
			}
		}

		doGenerateClassifier();

		// Open the written file
		uriOpener.open(URI.createPlatformResourceURI(fileLocation.toString(), true), true);

		return true;

	}

	@Override
	public void addPages() {
		this.addPage(getPage());
	}

	/**
	 * Returns with the model used for data binding purposes during the life of the wizard.
	 */
	protected abstract M getModel();

	/**
	 * Performs the actual generation of {@link #performFinish()} call.
	 */
	protected abstract void doGenerateClassifier();

	/**
	 * Returns with the one single {@link N4JSNewClassifierWizardPage classifier wizard page}.
	 */
	protected abstract N4JSNewClassifierWizardPage<M> getPage();
}
