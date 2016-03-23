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
package eu.numberfour.n4js.ui.wizard.interfaces;

import eu.numberfour.n4js.ui.wizard.classifiers.N4JSClassifierWizardModelValidator;
import eu.numberfour.n4js.ui.wizard.model.AccessModifier;

/**
 * A validator for {@link N4JSInterfaceWizardModel interface wizard model} instances.
 */
public class N4JSInterfaceWizardModelValidator extends N4JSClassifierWizardModelValidator<N4JSInterfaceWizardModel> {

	@Override
	protected void prepare() {
		super.prepare();
		if (getModel().getAccessModifier() == AccessModifier.PROJECT && getModel().isInternal()) {
			getModel().setInternal(false);
		}
	}

}
