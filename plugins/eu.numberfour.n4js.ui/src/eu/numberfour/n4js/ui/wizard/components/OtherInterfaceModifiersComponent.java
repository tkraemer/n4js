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

import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import eu.numberfour.n4js.ui.wizard.interfacewizard.N4JSInterfaceWizardModel;

/**
 * A component which provides control over the annotations of a N4JS interface
 *
 * @author luca.beurer-kellner - Initial contribution and API
 */
public class OtherInterfaceModifiersComponent extends WizardComponent {

	/** The N4JS annotation box */
	private final Button n4jsAnnotationBox;

	private final N4JSInterfaceWizardModel model;

	/**
	 * Create a new OtherInterfaceModifiersComponent component
	 *
	 * @param model
	 *            The model the bind it to
	 * @param container
	 *            The container to put it in
	 */
	public OtherInterfaceModifiersComponent(N4JSInterfaceWizardModel model, WizardComponentContainer container) {
		super(container);
		this.model = model;

		Label otherModifiersLabel = new Label(getParentComposite(), SWT.NONE);
		otherModifiersLabel.setText("Other modifiers:");

		Composite otherModifierComposite = new Composite(this.getParentComposite(), SWT.NONE);
		otherModifierComposite.setLayout(new RowLayout(SWT.HORIZONTAL));

		n4jsAnnotationBox = new Button(otherModifierComposite, SWT.CHECK);
		getN4jsAnnotationBox().setText("@N4JS");

		WizardComponentUtils.emptyGridCell(getParentComposite());

		setupBindings();
	}

	private void setupBindings() {
		// Final property binding

		// n4js annotation property binding

		IObservableValue n4jsValue = BeanProperties
				.value(N4JSInterfaceWizardModel.class, N4JSInterfaceWizardModel.N4JS_PROPERTY)
				.observe(model);
		IObservableValue n4jsUI = WidgetProperties.selection().observe(n4jsAnnotationBox);

		getDataBindingContext().bindValue(n4jsUI, n4jsValue);
	}

	@Override
	public void setFocus() {
		this.n4jsAnnotationBox.setFocus();
	}

	/**
	 * Get the N4JS annotation box widget
	 */
	public Button getN4jsAnnotationBox() {
		return n4jsAnnotationBox;
	}
}