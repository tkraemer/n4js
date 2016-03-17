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

import eu.numberfour.n4js.n4mf.ProjectType;
import eu.numberfour.n4js.n4mf.ui.internal.N4MFActivator;
import eu.numberfour.n4js.projectModel.IN4JSCore;

/**
 * Project wizard that creates a simple Eclipse project with Xtext nature, just asking for the project name.
 */
public class SimpleN4MFNewProjectWizard extends org.eclipse.xtext.ui.wizard.XtextNewProjectWizard {

	@Inject
	IN4JSCore n4jsCore;

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
		addPage(new N4MFWizardTestedProjectPage(projectInfo, n4jsCore));
	}

	@Override
	public boolean canFinish() {
		/*
		 * Can finish after first page for non-test projects or like normally if all pages are complete for test
		 * projects (super)
		 *
		 * This means that even for test projects the whole second page can be completely skipped.
		 */
		return (!ProjectType.TEST.equals(n4mfWizardNewProjectCreationPage) &&
				n4mfWizardNewProjectCreationPage.isPageComplete()) || super.canFinish();
	}

	@Override
	protected IProjectInfo getProjectInfo() {
		// update workingsets:
		n4mfWizardNewProjectCreationPage.updateSelectedWorkingSets();

		return projectInfo;
	}
}
