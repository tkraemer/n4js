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
package eu.numberfour.n4js.ui.wizard.classes;

import org.eclipse.swt.widgets.Display;

import com.google.inject.Inject;

import eu.numberfour.n4js.ui.wizard.classifiers.N4JSNewClassifierWizardPage;
import eu.numberfour.n4js.ui.wizard.components.AccessModifierComponent;
import eu.numberfour.n4js.ui.wizard.components.EmptyComponent;
import eu.numberfour.n4js.ui.wizard.components.FileTypeComponent;
import eu.numberfour.n4js.ui.wizard.components.InterfacesComponentProvider;
import eu.numberfour.n4js.ui.wizard.components.NameComponent;
import eu.numberfour.n4js.ui.wizard.components.OtherClassifierModifiersComponent;
import eu.numberfour.n4js.ui.wizard.components.SuperClassComponentProvider;
import eu.numberfour.n4js.ui.wizard.components.WizardComponentContainer;
import eu.numberfour.n4js.ui.wizard.workspace.ContentBlock;
import eu.numberfour.n4js.ui.wizard.workspace.WizardPreviewProvider.WizardPreview;
import eu.numberfour.n4js.ui.wizard.workspace.WorkspaceWizardModelValidator;

/**
 * A wizard page to allow the user to specify the informations about the creation of a new class.
 *
 * <p>
 * Note: The wizard page is not meant to be used without setting a {@link N4JSClassWizardModel}. To accomplish this use
 * the {@link N4JSNewClassWizardPage#setModel(N4JSClassWizardModel)} method.
 * </p>
 */
public class N4JSNewClassWizardPage extends N4JSNewClassifierWizardPage<N4JSClassWizardModel> {

	@Inject
	private N4JSClassWizardModelValidator validator;

	@Inject
	private InterfacesComponentProvider interfacesComponentProvider;

	@Inject
	private SuperClassComponentProvider superClassComponentProvider;

	@Inject
	private N4JSNewClassWizardGenerator generator;

	/**
	 * Instantiates a New N4JS Class wizard main page
	 */
	public N4JSNewClassWizardPage() {
		this.setTitle("New N4JS Class");
		this.setMessage("Create a new N4JS Class");
		this.setPageComplete(false);
	}

	@Override
	protected void updateContentPreview(WizardPreview contentPreview) {
		Display.getCurrent().asyncExec(() -> {
			ContentBlock[] codeBlocks = generator.generateContentPreview(getModel());
			contentPreview.setContent(codeBlocks);

			// Reveal last content block (class code)
			contentPreview.revealContentBlock(codeBlocks[codeBlocks.length - 1]);

			// Show file location in the info bar
			contentPreview.setInfo(getModel().computeFileLocation().toString());
		});
	}

	@SuppressWarnings("unused")
	@Override
	public void createComponents(WizardComponentContainer container) {
		nameComponent = new NameComponent(getModel(), container);

		new EmptyComponent(container);

		new FileTypeComponent(getModel(), container);

		accessModifierComponent = new AccessModifierComponent(getModel(), container);
		otherClassifierModifiersComponent = new OtherClassifierModifiersComponent(getModel(), container, true);

		new EmptyComponent(container);

		superClassComponentProvider.create(getModel(), container);
		interfacesComponentProvider.create(getModel(), container);

		setupBindings();
	}

	@Override
	public WorkspaceWizardModelValidator<N4JSClassWizardModel> getValidator() {
		return validator;
	}

}
