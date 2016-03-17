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

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import eu.numberfour.n4js.n4mf.ProjectType;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;

/**
 * This wizard page provides controls for tested projects selection.
 *
 * See {@link N4MFProjectInfo#getTestedProjects()}
 */
public class N4MFWizardTestedProjectPage extends WizardPage {

	private final IN4JSCore n4jsCore;
	private final N4MFProjectInfo projectInfo;

	/**
	 * Create a new tested project wizard page.
	 */
	public N4MFWizardTestedProjectPage(N4MFProjectInfo projectInfo, IN4JSCore n4jsCore) {
		super("Select projects to be tested");
		this.n4jsCore = n4jsCore;
		this.projectInfo = projectInfo;

		this.setTitle("Select projects to be tested");
		this.setMessage("Select one or multiple projects to be tested in your new test project");
	}

	@Override
	public void createControl(Composite parent) {
		Composite listComposite = new Composite(parent, NONE);
		listComposite.setLayout(new FillLayout());

		ListViewer projectListViewer = new ListViewer(listComposite, SWT.BORDER | SWT.MULTI);
		projectListViewer.setContentProvider(ArrayContentProvider.getInstance());
		projectListViewer.setInput(getNonTestProjects());

		// Data binding

		DataBindingContext databindingContext = new DataBindingContext();
		parent.addDisposeListener(e -> databindingContext.dispose());

		databindingContext.bindList(ViewersObservables.observeMultiSelection(projectListViewer),
				PojoProperties.list(N4MFProjectInfo.class, N4MFProjectInfo.TESTED_PROJECT).observe(projectInfo));

		setControl(listComposite);
	}

	/**
	 * Returns all non-test type N4JS projects in the workspace
	 *
	 */
	private String[] getNonTestProjects() {
		Stream<IN4JSProject> projects = StreamSupport.stream(n4jsCore.findAllProjects().spliterator(), false);
		return projects.filter(p -> null != p && p.exists() && !p.isExternal())
				.filter(p -> !ProjectType.TEST.equals(p.getProjectType()))
				.map(pt -> pt.getProjectName()).toArray(String[]::new);
	}

}
