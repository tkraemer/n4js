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

import eu.numberfour.n4js.ui.wizard.classwizard.N4JSClassWizardModel;

/**
 * A component which provides control over the annotations of a N4JS class
 *
 */
public class OtherClassModifiersComponent extends WizardComponent {

	/** The Final annotation box */
	private final Button finalAnnotationBox;
	/** The N4JS annotation box */
	private final Button n4jsAnnotationBox;

	private final N4JSClassWizardModel model;

	/**
	 * Creates a new OtherClassModifiers component.
	 *
	 * @param model
	 *            The model the bind it to
	 * @param container
	 *            The container to put it in
	 */
	public OtherClassModifiersComponent(N4JSClassWizardModel model, WizardComponentContainer container) {
		super(container);
		this.model = model;

		Label otherModifiersLabel = new Label(getParentComposite(), SWT.NONE);
		otherModifiersLabel.setText("Other modifiers:");

		Composite otherModifierComposite = new Composite(this.getParentComposite(), SWT.NONE);
		otherModifierComposite.setLayout(new RowLayout(SWT.HORIZONTAL));

		finalAnnotationBox = new Button(otherModifierComposite, SWT.CHECK);
		finalAnnotationBox.setText("@Final");

		n4jsAnnotationBox = new Button(otherModifierComposite, SWT.CHECK);
		getN4jsAnnotationBox().setText("@N4JS");

		WizardComponentUtils.emptyGridCell(getParentComposite());

		setupBindings();
	}

	private void setupBindings() {
		// Final property binding

		IObservableValue finalValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.FINAL_PROPERTY)
				.observe(model);
		IObservableValue finalUI = WidgetProperties.selection().observe(finalAnnotationBox);
		getDataBindingContext().bindValue(finalUI, finalValue);

		// n4js annotation property binding

		IObservableValue n4jsValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.N4JS_PROPERTY)
				.observe(model);
		IObservableValue n4jsUI = WidgetProperties.selection().observe(n4jsAnnotationBox);

		getDataBindingContext().bindValue(n4jsUI, n4jsValue);
	}

	@Override
	public void setFocus() {
		this.finalAnnotationBox.setFocus();
	}

	/**
	 * Returns the N4JS annotation box widget
	 */
	public Button getN4jsAnnotationBox() {
		return n4jsAnnotationBox;
	}
}