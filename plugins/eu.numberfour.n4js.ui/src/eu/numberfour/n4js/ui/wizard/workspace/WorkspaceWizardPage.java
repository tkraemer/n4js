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
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.ui.ImageDescriptorCache;
import eu.numberfour.n4js.ui.dialog.ModuleSpecifierSelectionDialog;
import eu.numberfour.n4js.ui.dialog.ProjectSelectionDialog;
import eu.numberfour.n4js.ui.dialog.SourceFolderSelectionDialogProvider;
import eu.numberfour.n4js.ui.dialog.WorkspaceElementSelectionDialog;
import eu.numberfour.n4js.ui.dialog.virtualresource.VirtualResource;
import eu.numberfour.n4js.ui.wizard.components.WizardComponent;
import eu.numberfour.n4js.ui.wizard.components.WizardComponentContainer;
import eu.numberfour.n4js.ui.wizard.components.WizardComponentDataConverters.ConditionalConverter;
import eu.numberfour.n4js.ui.wizard.components.WizardComponentDataConverters.StringToPathConverter;
import eu.numberfour.n4js.ui.wizard.contentproposal.ModuleSpecifierContentProposalProviderFactory;
import eu.numberfour.n4js.ui.wizard.contentproposal.ModuleSpecifierContentProposalProviderFactory.ModuleSpecifierProposalLabelProvider;
import eu.numberfour.n4js.ui.wizard.contentproposal.ProjectContentProposalProvider;
import eu.numberfour.n4js.ui.wizard.contentproposal.SimpleImageContentProposalLabelProvider;
import eu.numberfour.n4js.ui.wizard.contentproposal.SourceFolderContentProposalProviderFactory;

/**
 * An abstract wizard page for {@link WorkspaceWizardModel}s.
 *
 * This page provides controls for project, source folder, module specifier. You can use {@link WizardComponent}s and
 * the {@link #createComponents(WizardComponentContainer)} methods to add additional components to your wizard.
 *
 */
public abstract class WorkspaceWizardPage<M extends WorkspaceWizardModel> extends WizardPage
		implements WizardComponentContainer {

	private static final String CONTENT_ASSIST_ECLIPSE_COMMAND_ID = "org.eclipse.ui.edit.text.contentAssist.proposals";

	private M model;
	private DataBindingContext databindingContext;

	/** Available after invocation of #createControl */
	protected WorkspaceWizardPageForm workspaceWizardForm;

	/** Available after the invocation of {@link #setupResources(Device)} */
	private Image contentProposalDecorationImage = null;

	// Browse dialogs
	@Inject
	private Provider<ProjectSelectionDialog> projectSelectionDialogProvider;
	@Inject
	private SourceFolderSelectionDialogProvider sourceFolderSelectionDialogProvider;

	@Inject
	private ProjectContentProposalProvider projectContentProposalProvider;
	@Inject
	private SourceFolderContentProposalProviderFactory sourceFolderContentProviderFactory;
	@Inject
	private ModuleSpecifierContentProposalProviderFactory moduleSpecifierContentProviderFactory;

	// Content proposal adapters
	private ContentProposalAdapter sourceFolderContentProposalAdapter;
	private ContentProposalAdapter moduleSpecifierContentProposalAdapter;

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

		setupResources(parent.getDisplay());

		setupBindings(workspaceWizardForm);
		setupBrowseDialogs(workspaceWizardForm);
		setupContentProposal(workspaceWizardForm);

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
		WorkspaceElementSelectionDialog dialog = sourceFolderSelectionDialogProvider.createDialog(
				shell,
				model.getProject().toString(),
				model.getSourceFolder());
		dialog.open();

		Object firstResult = dialog.getFirstResult();

		if (firstResult instanceof IContainer) {
			model.setSourceFolder(((IContainer) firstResult).getProjectRelativePath().append("/"));
		} else if (firstResult instanceof VirtualResource) {
			model.setSourceFolder(new Path(((VirtualResource) firstResult).getName()).append("/"));
		}
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
		contentProposalDecorationImage.dispose();
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

	/**
	 * Initializes the resources for the given device
	 *
	 * @param device
	 *            The device
	 */
	private void setupResources(Device device) {
		Image originalImage = ImageDescriptorCache.ImageRef.SMART_LIGHTBULB.asImage().orNull();

		if (null != originalImage) {
			contentProposalDecorationImage = new Image(device, originalImage.getImageData().scaledTo(10, 10));
			originalImage.dispose();
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

	private void setupContentProposal(WorkspaceWizardPageForm wizardForm) {
		// Get the active binding's content assist key strokes
		KeyStroke keyInitiator = getActiveContentAssistBinding();

		// If unbound don't configure the content proposal
		if (null == keyInitiator) {
			return;
		}

		// Setup project content proposal
		ContentProposalAdapter projectAdapter = new ContentProposalAdapter(wizardForm.getProjectText(),
				new TextContentAdapter(), projectContentProposalProvider,
				keyInitiator, null);
		projectAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);

		ImageDescriptor projectSymbol = PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(org.eclipse.ui.ide.IDE.SharedImages.IMG_OBJ_PROJECT);
		projectAdapter.setLabelProvider(
				new SimpleImageContentProposalLabelProvider(projectSymbol));

		createContentProposalDecoration(wizardForm.getProjectText());

		sourceFolderContentProposalAdapter = new ContentProposalAdapter(
				wizardForm.getSourceFolderText(),
				new TextContentAdapter(), null,
				keyInitiator, null);
		sourceFolderContentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);

		sourceFolderContentProposalAdapter.setLabelProvider(
				new SimpleImageContentProposalLabelProvider(
						ImageDescriptorCache.ImageRef.SRC_FOLDER.asImageDescriptor().orNull()));

		createContentProposalDecoration(wizardForm.getSourceFolderText());

		moduleSpecifierContentProposalAdapter = new ContentProposalAdapter(
				wizardForm.getModuleSpecifierText().getInternalText(),
				new TextContentAdapter(), null,
				keyInitiator, null);

		createContentProposalDecoration(wizardForm.getModuleSpecifierText());

		// Update proposal context whenever the model changes
		model.addPropertyChangeListener(evt -> {
			if (evt.getPropertyName() == WorkspaceWizardModel.PROJECT_PROPERTY ||
					evt.getPropertyName() == WorkspaceWizardModel.SOURCE_FOLDER_PROPERTY) {
				updateProposalContext();
			}
		});
		updateProposalContext();

		moduleSpecifierContentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
		moduleSpecifierContentProposalAdapter
				.setLabelProvider(new ModuleSpecifierProposalLabelProvider());
	}

	/**
	 * Returns the active key binding for content assist.
	 *
	 * If no binding is set null is returned.
	 */
	private KeyStroke getActiveContentAssistBinding() {
		IBindingService bindingService = PlatformUI.getWorkbench().getService(IBindingService.class);
		TriggerSequence[] activeBindingsFor = bindingService
				.getActiveBindingsFor(CONTENT_ASSIST_ECLIPSE_COMMAND_ID);

		if (activeBindingsFor.length > 0 && activeBindingsFor[0] instanceof KeySequence) {
			KeyStroke[] strokes = ((KeySequence) activeBindingsFor[0]).getKeyStrokes();
			if (strokes.length == 1) {
				return strokes[0];
			}
		}
		return null;
	}

	/**
	 * Adds the content proposal field decoration to a given control.
	 *
	 * @param control
	 *            The control to decorate
	 */
	private ControlDecoration createContentProposalDecoration(Control control) {
		ControlDecoration projectDecoration = new ControlDecoration(control, SWT.TOP | SWT.LEFT);
		projectDecoration.setImage(contentProposalDecorationImage);
		projectDecoration.setShowOnlyOnFocus(true);
		return projectDecoration;
	}

	/**
	 * This method should be invoked whenever source folder or project value change, to update the proposal contexts for
	 * the field source folder and module specifier
	 */
	private void updateProposalContext() {
		IPath projectPath = model.getProject();
		IPath sourceFolderPath = model.getSourceFolder();

		// Early exit for empty project value
		if (projectPath.isEmpty()) {
			sourceFolderContentProposalAdapter.setContentProposalProvider(null);
			moduleSpecifierContentProposalAdapter.setContentProposalProvider(null);
			return;
		}

		IProject project = ResourcesPlugin.getWorkspace().getRoot()
				.getProject(projectPath.toString());

		if (null == project || !project.exists()) {
			// Disable source folder and module specifier proposals
			sourceFolderContentProposalAdapter.setContentProposalProvider(null);
			moduleSpecifierContentProposalAdapter.setContentProposalProvider(null);
		} else {
			// Try to retrieve the source folder and if not specified set it to null
			IContainer sourceFolder = sourceFolderPath.segmentCount() != 0 ? project.getFolder(sourceFolderPath) : null;

			// If the project exists, enable source folder proposals
			sourceFolderContentProposalAdapter
					.setContentProposalProvider(sourceFolderContentProviderFactory.createProviderForProject(project));

			if (null != sourceFolder && sourceFolder.exists()) {
				// If source folder exists as well enable module specifier proposal
				moduleSpecifierContentProposalAdapter.setContentProposalProvider(
						moduleSpecifierContentProviderFactory.createProviderForPath(sourceFolder.getFullPath()));
			} else {
				// Otherwise disable module specifier proposals
				moduleSpecifierContentProposalAdapter.setContentProposalProvider(null);
			}
		}
	}

}
