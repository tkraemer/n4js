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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.databinding.swt.WidgetProperties;

import com.google.inject.Inject;

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.ui.dialog.ModuleSpecifierSelectionDialog;
import eu.numberfour.n4js.ui.wizard.components.AccessModifierComponent;
import eu.numberfour.n4js.ui.wizard.components.DefinitionFileComponent;
import eu.numberfour.n4js.ui.wizard.components.EmptyComponent;
import eu.numberfour.n4js.ui.wizard.components.InterfacesComponentProvider;
import eu.numberfour.n4js.ui.wizard.components.NameComponent;
import eu.numberfour.n4js.ui.wizard.components.OtherClassModifiersComponent;
import eu.numberfour.n4js.ui.wizard.components.SuperClassComponentProvider;
import eu.numberfour.n4js.ui.wizard.components.WizardComponentContainer;
import eu.numberfour.n4js.ui.wizard.components.WizardComponentDataConverters;
import eu.numberfour.n4js.ui.wizard.model.AccessModifiableModel;
import eu.numberfour.n4js.ui.wizard.model.AccessModifiableModel.AccessModifier;
import eu.numberfour.n4js.ui.wizard.workspacewizard.SuffixText;
import eu.numberfour.n4js.ui.wizard.workspacewizard.WorkspaceWizardModel;
import eu.numberfour.n4js.ui.wizard.workspacewizard.WorkspaceWizardModelValidator;
import eu.numberfour.n4js.ui.wizard.workspacewizard.WorkspaceWizardModelValidator.ValidationResult;
import eu.numberfour.n4js.ui.wizard.workspacewizard.WorkspaceWizardPage;

/**
 * A wizard page to allow the user to specify the informations about the creation of a new class.
 *
 * <p>
 * Note: The wizard page is not meant to be used without setting a {@link N4JSClassWizardModel}. To accomplish this use
 * the {@link N4JSNewClassWizardPage#setModel(N4JSClassWizardModel)} method.
 * </p>
 */
public class N4JSNewClassWizardPage extends WorkspaceWizardPage {

	N4JSClassWizardModel model = null;

	@Inject
	N4JSClassWizardModelValidator validator;

	// Components
	@Inject
	private InterfacesComponentProvider interfacesComponentProvider;
	@Inject
	private SuperClassComponentProvider superClassComponentProvider;

	private OtherClassModifiersComponent otherClassModifiersComponent;
	private NameComponent classNameComponent;

	private AccessModifierComponent accessModifierComponent;

	/**
	 * Instantiates a New N4JS Class wizard main page
	 */
	public N4JSNewClassWizardPage() {
		super("Create a new N4JS Class");

		this.setTitle("New N4JS Class");
		this.setMessage("Create a new N4JS Class");
		this.setPageComplete(false);
	}

	/**
	 * Sets the model to use with this wizard page.
	 *
	 * @param model
	 *            N4JSClassWizardModel to use
	 */
	public void setModel(N4JSClassWizardModel model) {
		super.setModel(model);

		this.model = model;
		this.validator.setModel(this.model);
		this.model.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				validator.validate();
			}
		});
	}

	@Override
	public void setModel(WorkspaceWizardModel model) {
		throw new IllegalArgumentException("The model is too generic");
	}

	/**
	 * Invoked when the validation result of the model has changed.
	 *
	 * @param result
	 *            The most recent validation result
	 */
	private void onValidationChange(ValidationResult result) {
		if (result.valid) {
			this.setPageComplete(true);
			this.setMessage("Press finish to create the new class");
			this.setErrorMessage(null);
		} else {
			this.setPageComplete(false);
			this.setErrorMessage(result.errorMessage);
		}
	}

	@Override
	public void openModuleSpecifierDialog(org.eclipse.swt.widgets.Shell shell) {

		ModuleSpecifierSelectionDialog dialog = new ModuleSpecifierSelectionDialog(shell,
				model.getProject().append(model.getSourceFolder()));

		if (!model.getEffectiveModuleSpecifier().isEmpty()) {
			String initialSelectionSpecifier = model.getEffectiveModuleSpecifier();

			String fileExtension = model.computeFileLocation().getFileExtension();
			if (fileExtension != null) {
				dialog.setDefaultFileExtension(fileExtension);
			}

			Object initialSelection = dialog.computeInitialSelection(initialSelectionSpecifier);
			dialog.setInitialSelection(initialSelection);
		}

		dialog.open();

		Object result = dialog.getFirstResult();

		if (result instanceof String) {

			IPath specifierPath = new Path((String) result);

			String fileExtension = specifierPath.getFileExtension();

			// If the selected module specifier is a file
			if (fileExtension != null && !fileExtension.isEmpty()) {
				// and its file extension suggests a different external value than the model
				if (fileExtension.equals(N4JSGlobals.N4JSD_FILE_EXTENSION) != model.isDefinitionFile()) {
					// toggle the external value of the model
					model.setDefinitionFile(!model.isDefinitionFile());
				}
				specifierPath = specifierPath.removeFileExtension();
			}

			// If the last segment corresponds with the non-empty class name remove it
			if (specifierPath.segmentCount() > 0
					&& specifierPath.removeFileExtension().lastSegment().equals(model.getName())
					&& !model.getName().isEmpty()) {
				if (specifierPath.segmentCount() > 1) {
					specifierPath = specifierPath.removeLastSegments(1).addTrailingSeparator();
				} else {
					specifierPath = specifierPath.removeLastSegments(1);
				}
			}

			model.setModuleSpecifier(specifierPath.toString());
		}
	}

	private void updateSuffixVisibility() {
		SuffixText input = workspaceWizardForm.getModuleSpecifierText();
		String inputText = input.getText();
		if (inputText.isEmpty() || inputText.charAt(inputText.length() - 1) == '/') {
			input.setSuffixVisible(true);
		} else {
			input.setSuffixVisible(false);
		}
	}

	/**
	 * Setups additional non-component contained bindings.
	 */
	private void setupBindings() {

		DataBindingContext dataBindingContext = this.getDataBindingContext();

		IObservableValue moduleSpecifierValue = BeanProperties
				.value(N4JSClassWizardModel.class, WorkspaceWizardModel.MODULE_SPECIFIER_PROPERTY).observe(model);

		//// Only show the suffix on input values ending with a '/' character or empty module specifiers.
		moduleSpecifierValue.addValueChangeListener(new IValueChangeListener() {
			@Override
			public void handleValueChange(ValueChangeEvent event) {
				updateSuffixVisibility();
			}
		});
		// Initial suffix visibility update
		updateSuffixVisibility();

		//// Class name to module specifier suffix binding
		IObservableValue classNameModelValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.NAME_PROPERTY).observe(model);
		IObservableValue greySuffixValue = BeanProperties.value(SuffixText.class, SuffixText.SUFFIX_PROPERTY)
				.observe(workspaceWizardForm.getModuleSpecifierText());
		dataBindingContext.bindValue(greySuffixValue,
				classNameModelValue, new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE));

		//// Enable n4js <-> Definition value(external) is selected
		IObservableValue externalValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.DEFINITION_FILE_PROPERTY)
				.observe(model);
		IObservableValue n4jsEnabled = WidgetProperties.enabled()
				.observe(otherClassModifiersComponent.getN4jsAnnotationBox());
		dataBindingContext.bindValue(n4jsEnabled, externalValue);

		// One way binding of the access modifiers to the enabled state of the internal checkbox
		IObservableValue internalEnabledValue = WidgetProperties.enabled()
				.observe(accessModifierComponent.getInternalAnnotationBox());
		IObservableValue accessModifierSelectObservable = BeanProperties
				.value(N4JSClassWizardModel.class, AccessModifiableModel.ACCESS_MODIFIER_PROPERTY).observe(model);

		dataBindingContext.bindValue(internalEnabledValue, accessModifierSelectObservable,
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				new WizardComponentDataConverters.ConditionalConverter() {

					@Override
					public boolean validate(Object object) {
						if (object instanceof AccessModifier) {
							if (((AccessModifier) object) == AccessModifier.PROJECT ||
									((AccessModifier) object) == AccessModifier.PUBLIC) {
								return true;
							}
						}
						return false;
					}
				}.updatingValueStrategy());

		// Refresh wizard state on validatation change
		IObservableValue observableValidationValue = BeanProperties
				.value(WorkspaceWizardModelValidator.VALIDATION_RESULT)
				.observe(this.validator);
		observableValidationValue.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				onValidationChange((ValidationResult) event.diff.getNewValue());
			}
		});

	}

	@Override
	protected boolean setInitialFocus() {
		if (!super.setInitialFocus()) {
			this.classNameComponent.setFocus();
		}
		return false;
	}

	@SuppressWarnings("unused")
	@Override
	public void createComponents(WizardComponentContainer container) {

		classNameComponent = new NameComponent(model, container);

		new EmptyComponent(container);

		new DefinitionFileComponent(model, container);

		accessModifierComponent = new AccessModifierComponent(model, container);

		otherClassModifiersComponent = new OtherClassModifiersComponent(model, container);

		new EmptyComponent(container);

		superClassComponentProvider.create(model, container);

		interfacesComponentProvider.create(model, container);

		setupBindings();
	}

	@Override
	public WorkspaceWizardModelValidator getValidator() {
		return this.validator;
	}

}
