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

import static eu.numberfour.n4js.ui.wizard.components.WizardComponentUtils.fillTextDefaults;

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import eu.numberfour.n4js.ui.wizard.model.NamedModel;

/**
 * A component which provides simple name input for {@link NamedModel}s
 *
 * @author luca.beurer-kellner - Initial contribution and API
 */
public class NameComponent extends WizardComponent {
	private final Text nameText;
	private final NamedModel model;

	/**
	 * Create a new class name component
	 *
	 * @param model
	 *            The model to bind it to
	 * @param container
	 *            The container to place it in
	 */
	public NameComponent(NamedModel model, WizardComponentContainer container) {
		super(container);
		this.model = model;

		Label classNameLabel = new Label(getParentComposite(), SWT.NONE);
		classNameLabel.setText("Name:");

		nameText = new Text(getParentComposite(), SWT.BORDER);
		nameText.setLayoutData(fillTextDefaults());

		WizardComponentUtils.emptyGridCell(container.getComposite());

		setupBindings();
	}

	private void setupBindings() {
		IObservableValue nameValue = BeanProperties
				.value(NamedModel.class, NamedModel.NAME_PROPERTY).observe(model);
		IObservableValue nameUI = WidgetProperties.text(SWT.Modify).observe(nameText);
		getDataBindingContext().bindValue(nameValue, nameUI);
	}

	@Override
	public void setFocus() {
		this.nameText.setFocus();
	}

}