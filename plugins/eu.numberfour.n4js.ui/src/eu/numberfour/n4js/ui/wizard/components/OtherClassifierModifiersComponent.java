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

import eu.numberfour.n4js.ui.wizard.classes.N4JSClassWizardModel;
import eu.numberfour.n4js.ui.wizard.classifiers.N4JSClassifierWizardModel;

/**
 * A component which provides control over the annotations of a N4JS class
 *
 */
public class OtherClassifierModifiersComponent extends WizardComponent {

	/** The Final annotation box. */
	private final Button finalAnnotationBox;

	/** The N4JS annotation box. */
	private final Button n4jsAnnotationBox;

	/** Model for the data binding. */
	private final N4JSClassifierWizardModel model;

	/**
	 * Creates a new OtherClassModifiers component.
	 *
	 * @param model
	 *            The model the bind it to
	 * @param container
	 *            The container to put it in
	 */
	public OtherClassifierModifiersComponent(N4JSClassifierWizardModel model, WizardComponentContainer container,
			boolean createFinalButton) {

		super(container);
		this.model = model;

		Label otherModifiersLabel = new Label(getParentComposite(), SWT.NONE);
		otherModifiersLabel.setText("Other modifiers:");

		Composite otherModifierComposite = new Composite(this.getParentComposite(), SWT.NONE);
		otherModifierComposite.setLayout(new RowLayout(SWT.HORIZONTAL));

		if (createFinalButton) {
			finalAnnotationBox = new Button(otherModifierComposite, SWT.CHECK);
			finalAnnotationBox.setText("@Final");
		} else {
			finalAnnotationBox = null;
		}

		n4jsAnnotationBox = new Button(otherModifierComposite, SWT.CHECK);
		getN4jsAnnotationBox().setText("@N4JS");

		WizardComponentUtils.emptyGridCell(getParentComposite());

		setupBindings();
	}

	private void setupBindings() {
		// Final property binding

		if (null != finalAnnotationBox) {
			IObservableValue finalValue = BeanProperties
					.value(N4JSClassWizardModel.class, N4JSClassWizardModel.FINAL_ANNOTATED_PROPERTY)
					.observe(model);
			IObservableValue finalUI = WidgetProperties.selection().observe(finalAnnotationBox);
			getDataBindingContext().bindValue(finalUI, finalValue);
		}

		// n4js annotation property binding

		IObservableValue n4jsValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassifierWizardModel.N4JS_ANNOTATED_PROPERTY)
				.observe(model);
		IObservableValue n4jsUI = WidgetProperties.selection().observe(n4jsAnnotationBox);

		getDataBindingContext().bindValue(n4jsUI, n4jsValue);
	}

	@Override
	public void setFocus() {
		if (null != finalAnnotationBox) {
			this.finalAnnotationBox.setFocus();
		} else {
			this.n4jsAnnotationBox.setFocus();
		}
	}

	/**
	 * Returns the N4JS annotation box widget
	 */
	public Button getN4jsAnnotationBox() {
		return n4jsAnnotationBox;
	}
}