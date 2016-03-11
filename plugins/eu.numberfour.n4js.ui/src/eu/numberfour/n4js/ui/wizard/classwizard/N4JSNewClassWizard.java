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
package eu.numberfour.n4js.ui.wizard.classwizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
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

import com.google.common.base.Optional;
import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.projectModel.IN4JSSourceContainer;
import eu.numberfour.n4js.ui.ImageDescriptorCache.ImageRef;

/**
 * A wizard to allow the user to create a new N4JS class.
 *
 * The wizard supports the creation of new files as well as the insertion of classes into existing modules.
 */
public class N4JSNewClassWizard extends Wizard implements INewWizard {

	@Inject
	N4JSClassWizardModel model;
	@Inject
	IN4JSCore n4jsCore;
	@Inject
	LanguageSpecificURIEditorOpener uriOpener;

	@Inject
	N4JSNewClassWizardGenerator generator;

	@Inject
	N4JSNewClassWizardPage wizardPage;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.setNeedsProgressMonitor(false);
		this.setWindowTitle("New N4JS Class");
		setDefaultPageImageDescriptor(ImageRef.NEW_CLASS_WIZBAN.asImageDescriptor().orNull());

		parseIntialSelection(selection);
	}

	private void parseIntialSelection(IStructuredSelection selection) {
		Object firstElement = selection.getFirstElement();

		if (firstElement instanceof IResource) {
			IPath path = ((IResource) firstElement).getFullPath();

			// Remove the file specifying part as this isn't used for inference
			if (firstElement instanceof IFile) {
				path = path.removeLastSegments(1);
			}

			// Find project of path
			URI pathURI = URI.createPlatformResourceURI(path.toString(), true);
			Optional<? extends IN4JSProject> n4jsProject = n4jsCore.findProject(pathURI);
			if (!n4jsProject.isPresent()) {
				return;
			} else if (!n4jsProject.get().exists()) {
				return;
			} else {
				// If project exists set the project property of the model
				URI projectUri = n4jsProject.get().getLocation();
				IPath projectPath = new Path(projectUri.deresolve(URI.createPlatformResourceURI("", true)).toString());
				model.setProject(projectPath);

				// If the path also specifies a folder inside the project, use this information as well
				if (!pathURI.equals(n4jsProject.get().getLocation())) {
					Optional<? extends IN4JSSourceContainer> sourceFolder = n4jsCore
							.findN4JSSourceContainer(pathURI);
					if (sourceFolder.isPresent()) {
						String sourceFolderPath = sourceFolder.get().getRelativeLocation();
						model.setSourceFolder(new Path(sourceFolderPath));

						// Finally parse the module specifier
						IPath sourceFolderURI = new Path(sourceFolder.get().getLocation()
								.deresolve(URI.createPlatformResourceURI("", true)).toString());
						IPath moduleSpecifierPath = new Path(
								pathURI.deresolve(URI.createPlatformResourceURI("", true)).toString());

						String moduleSpecifier = moduleSpecifierPath.makeRelativeTo(sourceFolderURI).toString();

						if (!moduleSpecifier.isEmpty()) {
							model.setModuleSpecifier(moduleSpecifier + "/");
						}
					}
				}

			}
		}

		wizardPage.setModel(model);

	}

	@Override
	public boolean performFinish() {
		IPath fileLocation = this.model.computeFileLocation();

		// Create missing module folders
		if (!ResourcesPlugin.getWorkspace().getRoot().getFile(fileLocation).exists()) {

			IContainer parent = ResourcesPlugin.getWorkspace().getRoot().getProject(model.getProject().toString());

			try {
				// Iterate through remaining segments but the file segment
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
		uriOpener.open(URI.createPlatformResourceURI(fileLocation.toString(), true),
				true);

		return true;
	}

	@Override
	public void addPages() {
		this.addPage(wizardPage);
	}

}
