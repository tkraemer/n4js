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
package eu.numberfour.n4js.ui.wizard.workspacewizard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.google.common.base.Optional;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.projectModel.IN4JSSourceContainer;

/**
 * An abstract model for workspace wizard elements.
 *
 */
public class WorkspaceWizardModel {

	// Property names for databinding

	/** The project property constant */
	public static final String PROJECT_PROPERTY = "project";
	/** The source folder property constant */
	public static final String SOURCE_FOLDER_PROPERTY = "sourceFolder";
	/** The module specifier property constant */
	public static final String MODULE_SPECIFIER_PROPERTY = "moduleSpecifier";

	/**
	 * Property storage
	 */

	private IPath project = new Path("");
	private IPath sourceFolder = new Path("");
	private String moduleSpecifier = "";

	/**
	 * PropertyChangeListenerSupport
	 */

	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	/**
	 * @param listener
	 *            listener to be called on every change of any property
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.changeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @param listener
	 *            remove listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.changeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * @param propertyName
	 *            bean name of the property
	 * @param newValue
	 *            new value of the property
	 * @param oldValue
	 *            old value of the property
	 */
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		this.changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * @return the saved project path
	 */
	public IPath getProject() {
		return project;
	}

	/**
	 * Sets the project path.
	 *
	 * @param project
	 *            new project path
	 */
	public void setProject(IPath project) {
		this.firePropertyChange(PROJECT_PROPERTY, this.project, this.project = project);
	}

	/**
	 * @return The saved source folder path
	 */
	public IPath getSourceFolder() {
		return sourceFolder;
	}

	/**
	 * Set a new source folder and use sender as sender for listeners
	 *
	 * @param sourceFolder
	 *            The new source folder
	 */
	public void setSourceFolder(IPath sourceFolder) {
		this.firePropertyChange(SOURCE_FOLDER_PROPERTY, this.sourceFolder, this.sourceFolder = sourceFolder);
	}

	/**
	 * @return the saved module specifier
	 */
	public String getModuleSpecifier() {
		return moduleSpecifier;
	}

	/**
	 * @param moduleSpecifier
	 *            The new module specifier
	 */
	public void setModuleSpecifier(String moduleSpecifier) {
		this.firePropertyChange(MODULE_SPECIFIER_PROPERTY, this.moduleSpecifier,
				this.moduleSpecifier = moduleSpecifier);
	}

	/**
	 * Tries to extract as much information possible from a structured selection.
	 *
	 * @param model
	 *            The model
	 * @param selection
	 *            The selection
	 * @param n4jsCore
	 *            IN4JSCore implementation
	 */
	public static void fillModelFromInitialSelection(WorkspaceWizardModel model, IStructuredSelection selection,
			IN4JSCore n4jsCore) {
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
	}

}
