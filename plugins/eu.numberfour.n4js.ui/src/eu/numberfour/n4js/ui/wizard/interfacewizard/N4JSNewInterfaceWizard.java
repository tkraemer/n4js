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
package eu.numberfour.n4js.ui.wizard.interfacewizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
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

import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.ui.ImageDescriptorCache.ImageRef;
import eu.numberfour.n4js.ui.wizard.workspacewizard.InlineWorkspaceWizard;
import eu.numberfour.n4js.ui.wizard.workspacewizard.WorkspaceWizardModel;

/**
 * A New N4JS Interface wizard
 *
 */
public class N4JSNewInterfaceWizard extends Wizard implements INewWizard, InlineWorkspaceWizard {

	@Inject
	private N4JSInterfaceWizardModel model;
	@Inject
	private IN4JSCore n4jsCore;
	@Inject
	private LanguageSpecificURIEditorOpener uriOpener;

	@Inject
	private N4JSNewInterfaceWizardGenerator generator;

	@Inject
	private N4JSNewInterfaceWizardPage wizardPage;

	private boolean fillInModuleFile = false;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.setNeedsProgressMonitor(false);
		this.setWindowTitle("New N4JS Interface");

		setDefaultPageImageDescriptor(ImageRef.NEW_INTERFACE_WIZBAN.asImageDescriptor().orNull());

		parseIntialSelection(selection);

		if (fillInModuleFile && selection.getFirstElement() instanceof IResource) {
			String moduleSpecifier = model.getModuleSpecifier();
			IResource selectionResource = (IResource) selection.getFirstElement();

			model.setModuleSpecifier(
					new Path(moduleSpecifier + selectionResource.getName()).removeFileExtension().toString());
		}
	}

	@Override
	public void setFillModuleFile(boolean fillModuleFile) {
		this.fillInModuleFile = fillModuleFile;
	}

	private void parseIntialSelection(IStructuredSelection selection) {
		WorkspaceWizardModel.fillModelFromInitialSelection(model, selection, n4jsCore);
		wizardPage.setModel(model);
	}

	@Override
	public boolean performFinish() {

		IPath fileLocation = this.model.computeFileLocation();

		// Create missing module folders
		if (!ResourcesPlugin.getWorkspace().getRoot().getFile(fileLocation).exists()) {

			IContainer parent = ResourcesPlugin.getWorkspace().getRoot().getProject(model.getProject().toString());

			try { // Iterate through remaining segments but the file segment
				for (String segment : fileLocation.makeRelativeTo(model.getProject()).removeLastSegments(1)
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

		generator.performManifestChanges(this.model);
		generator.writeToFile(this.model);

		// Open the written file
		uriOpener.open(URI.createPlatformResourceURI(fileLocation.toString(), true), true);

		return true;

	}

	@Override
	public void addPages() {
		this.addPage(wizardPage);
	}
}
