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
package eu.numberfour.n4js.ui.workingsets;

import static org.eclipse.swt.SWT.BORDER;
import static org.eclipse.swt.SWT.CENTER;
import static org.eclipse.swt.SWT.FILL;

import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Optional;

import eu.numberfour.n4js.ui.workingsets.ProjectNameFilterWorkingSetManager.ProjectNameFilterWorkingSet;
import eu.numberfour.n4js.utils.Arrays2;

/**
 * Wizard for creating project name filter based working sets.
 */
public class WorkingSetProjectNameFilterNewWizard extends WorkingSetNewWizard {

	private final AtomicReference<WorkingSet> workingSetRef = new AtomicReference<>();

	@Override
	public void addPages() {
		addPage(new WizardPage("") {

			private Text labelText;
			private Text filterText;

			@Override
			public void createControl(final Composite parent) {

				final Composite composite = new Composite(parent, NONE);
				composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(false).create());
				composite.setLayoutData(GridDataFactory.fillDefaults().align(FILL, FILL).grab(true, true).create());

				new Label(composite, NONE).setText("Label:");
				labelText = new Text(composite, BORDER);
				labelText.setLayoutData(GridDataFactory.fillDefaults().align(FILL, CENTER).grab(true, false).create());
				labelText.addModifyListener(e -> setPageComplete(validatePage()));

				new Label(composite, NONE).setText("Project name filter:");
				filterText = new Text(composite, BORDER);
				filterText.setLayoutData(GridDataFactory.fillDefaults().align(FILL, CENTER).grab(true, false).create());
				filterText.addModifyListener(e -> setPageComplete(validatePage()));

				setPageComplete(false);
				setControl(composite);

				composite.getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						setTitle("Project Name Filter Working Set");
						setDescription(
								"Enter a working set name and specify a project name filter using a regular expression.");
					}

				});

			}

			@SuppressWarnings("null")
			private boolean validatePage() {

				String errorMessage = null;

				final String label = labelText.getText();
				final String filter = filterText.getText();
				final WorkingSetManager manager = getManager();

				if (manager == null) {
					errorMessage = "No active working set manager is available.";
				}

				if (errorMessage == null) {
					if (label == null || label.trim().length() == 0) {
						errorMessage = "Working set name should be specified.";
					}
				}

				if (errorMessage == null) {
					if (Arrays2.transform(manager.getAllWorkingSets(), ws -> ws.getLabel()).contains(label)) {
						errorMessage = "A working set already exists with name '" + label + "'.";
					}
				}

				if (errorMessage == null) {
					if (filter == null || filter.trim().length() == 0) {
						errorMessage = "Project name filter should be specified";
					}
				}

				Pattern pattern = null;

				if (errorMessage == null) {
					try {
						pattern = Pattern.compile(filter);
					} catch (final PatternSyntaxException e) {
						errorMessage = "Invalid project name filter. " + e.getDescription() + ".";
					}
				}

				if (errorMessage != null || pattern == null) {
					workingSetRef.set(null);
				} else {
					workingSetRef.set(new ProjectNameFilterWorkingSet(pattern, label, manager));
				}

				setMessage(errorMessage, ERROR);
				return errorMessage == null;
			}

		});
	}

	@Override
	public Optional<WorkingSet> getWorkingSet() {
		return Optional.fromNullable(workingSetRef.get());
	}

	@Override
	public boolean performFinish() {
		return null != getWorkingSet();
	}

}
