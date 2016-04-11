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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.projectModel.IN4JSSourceContainer;
import eu.numberfour.n4js.ui.dialog.ModuleSpecifierSelectionDialog;
import eu.numberfour.n4js.ui.dialog.ProjectSelectionDialog;
import eu.numberfour.n4js.ui.dialog.SourceFolderSelectionDialog;
import eu.numberfour.n4js.ui.wizard.components.WizardComponent;
import eu.numberfour.n4js.ui.wizard.components.WizardComponentContainer;
import eu.numberfour.n4js.ui.wizard.components.WizardComponentDataConverters.ConditionalConverter;
import eu.numberfour.n4js.ui.wizard.components.WizardComponentDataConverters.StringToPathConverter;

/**
 * An abstract wizard page for {@link WorkspaceWizardModel}s.
 *
 * This page provides controls for project, source folder, module specifier. You can use {@link WizardComponent}s and
 * the {@link #createComponents(WizardComponentContainer)} methods to add additional components to your wizard.
 *
 */
public abstract class WorkspaceWizardPage<M extends WorkspaceWizardModel> extends WizardPage
		implements WizardComponentContainer {

	private M model;
	private DataBindingContext databindingContext;

	/** Available after invocation of #createControl */
	protected WorkspaceWizardPageForm workspaceWizardForm;

	// Browse dialogs
	@Inject
	private Provider<ProjectSelectionDialog> projectSelectionDialogProvider;
	@Inject
	private Provider<SourceFolderSelectionDialog> sourceFolderSelectionDialogProvider;

	/**
	 * Sole constructor.
	 */
	protected WorkspaceWizardPage() {
		super(WorkspaceWizardPage.class.getName());
		this.setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		workspaceWizardForm = new WorkspaceWizardPageForm(parent, SWT.FILL);

		setupBindings(workspaceWizardForm);
		setupBrowseDialogs(workspaceWizardForm);

		createComponents(this);

		// Synchronize background color to work around a dark theme issue where the wizard content background appears
		// white
		workspaceWizardForm.setBackground(parent.getBackground());

		setControl(workspaceWizardForm);
	}

	/**
	 * Open the dialog to select a module specifier
	 *
	 * @param shell
	 *            The Shell to open the dialog in
	 */
	public void openModuleSpecifierDialog(Shell shell) {
		ModuleSpecifierSelectionDialog dialog = new ModuleSpecifierSelectionDialog(shell,
				model.getProject().append(model.getSourceFolder()));

		if (!model.getModuleSpecifier().isEmpty()) {
			String initialSelectionSpecifier = model.getModuleSpecifier();

			Object initialSelection = dialog.computeInitialSelection(initialSelectionSpecifier);
			dialog.setInitialSelection(initialSelection);
		}

		dialog.open();

		Object result = dialog.getFirstResult();

		if (result instanceof String) {
			IPath specifierPath = new Path((String) result);
			model.setModuleSpecifier(specifierPath.removeFileExtension().toString());
		}
	}

	/**
	 * Open the dialog to select a project
	 *
	 * @param shell
	 *            The Shell to open the dialog in
	 */
	public void openProjectDialog(Shell shell) {
		ProjectSelectionDialog dialog = projectSelectionDialogProvider.get();
		dialog.open();

		Object firstResult = dialog.getFirstResult();

		if (firstResult instanceof IProject) {
			model.setProject(new Path(((IProject) firstResult).getName()));
		}
	}

	/**
	 * Open the dialog to select a source folder
	 *
	 * @param shell
	 *            The Shell to open the dialog in
	 */
	public void openSourceFolderBrowseDialog(Shell shell) {
		SourceFolderSelectionDialog dialog = sourceFolderSelectionDialogProvider.get();

		// Get the IProject from the workspace. This is save as the validator ensures the project exists at this point
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(model.getProject().segment(0));

		dialog.setInput(project);
		dialog.setInitialSelections(new Object[] { model.getSourceFolder().toString() });

		dialog.open();

		Object firstResult = dialog.getFirstResult();

		if (firstResult instanceof IN4JSSourceContainer) {
			model.setSourceFolder(
					new Path(((IN4JSSourceContainer) firstResult).getRelativeLocation()).addTrailingSeparator());
		}
	}

	private void setupBindings(WorkspaceWizardPageForm wizardForm) {
		databindingContext = new DataBindingContext();

		WorkspaceWizardModelValidator<M> validator = getValidator();

		// Project property binding
		IObservableValue projectModelValue = BeanProperties
				.value(WorkspaceWizardModel.class, WorkspaceWizardModel.PROJECT_PROPERTY)
				.observe(model);
		IObservableValue projectUI = WidgetProperties.text(SWT.Modify).observe(wizardForm.getProjectText());

		// Note: No model to UI conversation here as IPath is castable to String (default behavior)
		databindingContext.bindValue(projectUI, projectModelValue, new StringToPathConverter().updatingValueStrategy(),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE));

		// Source folder property binding
		IObservableValue sourceFolderModelValue = BeanProperties
				.value(WorkspaceWizardModel.class, WorkspaceWizardModel.SOURCE_FOLDER_PROPERTY)
				.observe(model);
		IObservableValue sourceFolderUI = WidgetProperties.text(SWT.Modify).observe(wizardForm.getSourceFolderText());

		// Note: No model to UI conversation (see above)
		databindingContext.bindValue(sourceFolderUI, sourceFolderModelValue,
				new StringToPathConverter().updatingValueStrategy(),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE));

		IObservableValue projectValidModelValue = BeanProperties
				.value(WorkspaceWizardModelValidator.class, WorkspaceWizardModelValidator.PROJECT_PROPERTY_VALID)
				.observe(validator);
		IObservableValue sourceFolderBrowseEnabled = WidgetProperties.enabled()
				.observe(wizardForm.getSourceFolderBrowseButton());

		databindingContext.bindValue(sourceFolderBrowseEnabled, projectValidModelValue,
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE));

		// Module specifier property binding
		IObservableValue moduleSpecifierModelValue = BeanProperties
				.value(WorkspaceWizardModel.class, WorkspaceWizardModel.MODULE_SPECIFIER_PROPERTY)
				.observe(model);
		IObservableValue moduleSpecifierUI = BeanProperties
				.value(SuffixText.class, SuffixText.TEXT_PROPERTY)
				.observe(wizardForm.getModuleSpecifierText());
		databindingContext.bindValue(moduleSpecifierUI, moduleSpecifierModelValue);

		// Conditional activation of the browse buttons according to the precedent input fields validity
		IObservableValue moduleSpecifierBrowseEnabled = WidgetProperties.enabled()
				.observe(wizardForm.getModuleSpecifierBrowseButton());
		IObservableValue sourceFolderValidValue = BeanProperties.value(WorkspaceWizardModelValidator.class,
				WorkspaceWizardModelValidator.SOURCE_FOLDER_PROPERTY_VALID).observe(validator);
		IObservableValue projectValidValue = BeanProperties.value(WorkspaceWizardModelValidator.class,
				WorkspaceWizardModelValidator.PROJECT_PROPERTY_VALID).observe(validator);

		ConditionalConverter moduleSpecifierBrowseableConverter = new ConditionalConverter() {
			@Override
			public boolean validate(Object object) {
				return validator.getSourceFolderValid() && validator.getProjectValid();
			}
		};

		// Bind model changes of project or source folder property to the enabled state of the module specifier browse
		// button.
		databindingContext.bindValue(moduleSpecifierBrowseEnabled, projectValidValue,
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				moduleSpecifierBrowseableConverter.updatingValueStrategy());
		databindingContext.bindValue(moduleSpecifierBrowseEnabled, sourceFolderValidValue,
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				moduleSpecifierBrowseableConverter.updatingValueStrategy());
	}

	private void setupBrowseDialogs(WorkspaceWizardPageForm wizardForm) {
		wizardForm.getProjectBrowseButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openProjectDialog(wizardForm.getShell());
			}
		});

		wizardForm.getSourceFolderBrowseButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openSourceFolderBrowseDialog(wizardForm.getShell());
			}
		});

		wizardForm.getModuleSpecifierBrowseButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				openModuleSpecifierDialog(wizardForm.getShell());
			}
		});
	}

	/**
	 * @param model
	 *            WorkspaceWizardModel to use
	 */
	public void setModel(M model) {
		this.model = model;
		getValidator().setModel(this.model);
		this.model.addPropertyChangeListener(evt -> getValidator().validate());
	}

	/**
	 * Returns with the underlying module instance.
	 *
	 * @return the model instance used for data binding.
	 */
	public M getModel() {
		return model;
	}

	/**
	 * Set the initial focus when the pages is visible
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);

		if (visible) {
			this.setInitialFocus();
		}

	}

	/**
	 * Set the input focus depending on the initially given model.
	 *
	 * This method is only invoked when all ui is initialized and visible.
	 *
	 * @return True if the focus was claimed.
	 */
	protected boolean setInitialFocus() {
		// Set the focus to the first empty field beginning with project
		if (model.getProject().toString().isEmpty()) {
			workspaceWizardForm.getProjectText().setFocus();
		} else if (model.getSourceFolder().toString().isEmpty()) {
			workspaceWizardForm.getSourceFolderText().setFocus();
		} else {
			return false;
		}
		return true;
	}

	@Override
	public DataBindingContext getDataBindingContext() {
		return databindingContext;
	}

	@Override
	public void dispose() {
		super.dispose();
		if (databindingContext != null) {
			databindingContext.dispose();
		}
	}

	@Override
	public Composite getComposite() {
		return workspaceWizardForm;
	}

	/**
	 * Get the validator for this wizard page.
	 *
	 * Subclasses need to provide their own ModelValidator through this method and configure it to work with the model.
	 *
	 * @return A {@link WorkspaceWizardModelValidator}
	 */
	public abstract WorkspaceWizardModelValidator<M> getValidator();

	/**
	 * Implement this method to add custom form components.
	 * <p>
	 * Note that the parent composite is a 3 column grid.
	 * </p>
	 *
	 * @param parent
	 *            The parent composite
	 */
	public abstract void createComponents(WizardComponentContainer parent);

}
