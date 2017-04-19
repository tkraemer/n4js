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
package eu.numberfour.n4js.ui.wizard.classes

import eu.numberfour.n4js.ui.wizard.classifiers.N4JSClassifierWizardModel
import eu.numberfour.n4js.ui.wizard.model.AccessModifiableModel
import eu.numberfour.n4js.ui.wizard.model.ClassifierReference
import eu.numberfour.n4js.ui.wizard.model.DefinitionFileModel
import eu.numberfour.n4js.ui.wizard.model.InterfacesContainingModel
import eu.numberfour.n4js.ui.wizard.model.NamedModel
import eu.numberfour.n4js.utils.beans.PropertyChangeSupport

/**
 * A data model to hold the informations of a {@link N4JSNewClassWizard}.
 */
@PropertyChangeSupport
class N4JSClassWizardModel extends N4JSClassifierWizardModel
	implements InterfacesContainingModel, AccessModifiableModel, NamedModel, DefinitionFileModel {

	var boolean finalAnnotated;
	var ClassifierReference superClass = new ClassifierReference("", "");

	@Override
	override getClassifierName() {
		return 'class';
	}

}
