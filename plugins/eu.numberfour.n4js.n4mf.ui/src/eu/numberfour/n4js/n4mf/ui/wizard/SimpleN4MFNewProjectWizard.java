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
package eu.numberfour.n4js.n4mf.ui.wizard;

import static org.eclipse.ui.plugin.AbstractUIPlugin.imageDescriptorFromPlugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.xtext.ui.wizard.IProjectCreator;
import org.eclipse.xtext.ui.wizard.IProjectInfo;

import com.google.inject.Inject;

import eu.numberfour.n4js.n4mf.ui.internal.N4MFActivator;

/**
 * Project wizard that creates a simple Eclipse project with Xtext nature, just asking for the project name.
 */
public class SimpleN4MFNewProjectWizard extends org.eclipse.xtext.ui.wizard.XtextNewProjectWizard {

	private static final String FILE_PATH = "icons/newprj_wiz.png";
	private static final String PLUGIN_ID = N4MFActivator.getInstance().getBundle().getSymbolicName();
	private static final ImageDescriptor NEW_PROJECT_WIZBAN_DESC = imageDescriptorFromPlugin(PLUGIN_ID, FILE_PATH);

	private final N4MFProjectInfo projectInfo;
	private N4MFWizardNewProjectCreationPage n4mfWizardNewProjectCreationPage;

	/**
	 * Creates a new wizard container for creating and initializing a new N4 project into the workspace.
	 *
	 * @param projectCreator
	 *            the project creation logic to be triggered when finishing this wizard.
	 */
	@Inject
	public SimpleN4MFNewProjectWizard(final IProjectCreator projectCreator) {
		super(projectCreator);
		setWindowTitle("New N4JS Project");
		setNeedsProgressMonitor(true);
		setDefaultPageImageDescriptor(NEW_PROJECT_WIZBAN_DESC);
		projectInfo = new N4MFProjectInfo();
	}

	@Override
	public void addPages() {
		n4mfWizardNewProjectCreationPage = new N4MFWizardNewProjectCreationPage(projectInfo);
		addPage(n4mfWizardNewProjectCreationPage);
	}

	@Override
	protected IProjectInfo getProjectInfo() {
		// update workingsets:
		n4mfWizardNewProjectCreationPage.updateSelectedWorkingSets();

		return projectInfo;
	}
}
