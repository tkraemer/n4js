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
package eu.numberfour.n4js.ui.wizard.components;

import static eu.numberfour.n4js.ui.wizard.components.WizardComponentUtils.fillLabelDefaults;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.numberfour.n4js.ui.wizard.model.DefinitionFileModel;

/**
 * A component which allows the selection of a checkbox specifying the Definition File property
 */
public class DefinitionFileComponent extends WizardComponent {

	/** The definition file checkbox */
	private final Button definitionFileBox;
	private final DefinitionFileModel model;

	/**
	 * Create a new definition file component using model in container
	 *
	 * @param model
	 *            The model to bind it to
	 * @param container
	 *            The container to create it in
	 */
	public DefinitionFileComponent(DefinitionFileModel model, WizardComponentContainer container) {
		super(container);
		this.model = model;

		Composite parent = getParentComposite();

		Label definitionFileLabel = new Label(parent, SWT.NONE);
		definitionFileLabel.setLayoutData(fillLabelDefaults());
		definitionFileLabel.setText("Definition file:");

		definitionFileBox = new Button(parent, SWT.CHECK);
		definitionFileBox.setLayoutData(fillLabelDefaults());

		WizardComponentUtils.emptyGridCell(parent);

		setupBindings();
	}

	private void setupBindings() {
		// Definition file property binding (definition file)

		IObservableValue externalValue = BeanProperties
				.value(DefinitionFileModel.class, DefinitionFileModel.DEFINITION_FILE_PROPERTY)
				.observe(model);
		IObservableValue externalUI = WidgetProperties.selection().observe(definitionFileBox);
		getDataBindingContext().bindValue(externalUI, externalValue);
	}

	@Override
	public void setFocus() {
		this.definitionFileBox.setFocus();
	}
}