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
package eu.numberfour.n4js.ui.handler;

import static eu.numberfour.n4js.ui.internal.N4JSActivator.EU_NUMBERFOUR_N4JS_N4JS;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.wizards.IWizardDescriptor;

import eu.numberfour.n4js.ui.N4JSEditor;
import eu.numberfour.n4js.ui.internal.N4JSActivator;
import eu.numberfour.n4js.ui.utils.UIUtils;
import eu.numberfour.n4js.ui.wizard.workspacewizard.InlineWorkspaceWizard;

/**
 * Handler for the "Create New N4JS Element in module" command.
 *
 * Opens the given wizard and if supported makes it use the currently open module file as initial selection.
 *
 */
public class CreateNewN4JSElementInModuleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String wizardId = event
				.getParameter("eu.numberfour.n4js.ui.wizard.CreateNewN4JSElementInModule.wizardId");
		if (wizardId == null) {
			return null;
		}

		IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();

		if (activeEditor instanceof N4JSEditor) {
			IEditorInput input = activeEditor.getEditorInput();
			if (input instanceof FileEditorInput) {
				openWizardForModule(wizardId, ((FileEditorInput) input).getFile());
			}
		}

		return null;
	}

	/**
	 * Opens the wizard with the given id and passes it the module as initial selection.
	 *
	 * @param wizardId
	 *            The wizard id of the eclipse newWizard registry
	 * @param module
	 *            The module resource
	 */
	private void openWizardForModule(String wizardId, IResource module) {
		IWizardDescriptor wizardDescriptor = PlatformUI.getWorkbench().getNewWizardRegistry().findWizard(wizardId);

		if (wizardDescriptor == null || !module.exists()) {
			return;
		}

		try {
			IWorkbenchWizard wizard = wizardDescriptor.createWizard();

			// Inject wizard members
			N4JSActivator.getInstance().getInjector(EU_NUMBERFOUR_N4JS_N4JS).injectMembers(wizard);

			// Create and open a new wizard dialog
			WizardDialog wizardDialog = new WizardDialog(UIUtils.getShell(), wizard);

			// If the wizard supports it, enable module file filling
			if (wizard instanceof InlineWorkspaceWizard) {
				((InlineWorkspaceWizard) wizard).setFillModuleFile(true);
			}
			// Pass it the initial selection
			wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(module));

			wizardDialog.setTitle(wizard.getWindowTitle());
			wizardDialog.open();

		} catch (CoreException e) {
			/** Failed to create the wizard */
			return;
		}

	}

}
