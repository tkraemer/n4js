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
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.wizards.IWizardDescriptor;

import eu.numberfour.n4js.ui.internal.N4JSActivator;
import eu.numberfour.n4js.ui.utils.UIUtils;
import eu.numberfour.n4js.ui.wizard.workspacewizard.NestedElementWorkbenchWizard;

/**
 * Handler for the "Create New N4JS Element in module" command.
 *
 * Opens the given wizard and if supported makes it use the currently open module file as initial selection.
 *
 */
public class CreateNewN4JSElementInModuleHandler extends AbstractHandler {

	private static final String WIZARD_ID_PARAMETER_ID = "eu.numberfour.n4js.ui.wizard.CreateNewN4JSElementInModule.wizardId";
	private static final String NESTED_PARAMETER_ID = "eu.numberfour.n4js.ui.wizard.CreateNewN4JSElementInModule.nested";

	/**
	 * Returns the file which is currently opened in the active editor.
	 *
	 * Returns null if no editor is open.
	 */
	private static IFile getOpenEditorFile() {
		IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();

		if (null == activeEditor) {
			return null;
		}

		IEditorInput input = activeEditor.getEditorInput();

		if (input instanceof FileEditorInput) {
			return ((FileEditorInput) input).getFile();
		} else {
			return null;
		}

	}

	/**
	 * Returns the selection file if the active selection is of type {@link TreeSelection}.
	 *
	 * Returns null if it fails.
	 *
	 */
	private static IResource getTreeSelection() {
		ISelection activeSelection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()
				.getSelection();

		if (activeSelection instanceof TreeSelection) {
			Object firstElement = ((TreeSelection) activeSelection).getFirstElement();

			if (firstElement instanceof IResource) {
				return (IResource) firstElement;
			}
		}
		return null;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String wizardId = event.getParameter(WIZARD_ID_PARAMETER_ID);
		String nested = event.getParameter(NESTED_PARAMETER_ID);

		boolean isNested = false;

		if (wizardId == null) {
			return null;
		}

		if (nested != null) {
			isNested = nested.equals("true");
		}

		IStructuredSelection selection;

		// Try to get editor file
		IFile editorFile = getOpenEditorFile();
		// Try to get the selection from the project explorer tree view
		IResource resourceTreeFile = getTreeSelection();

		if (null != editorFile) {
			selection = new StructuredSelection(editorFile);
		} else if (null != resourceTreeFile) {
			// If it's a tree selection set nested to false to stay consistent
			isNested = false;
			selection = new StructuredSelection(resourceTreeFile);
		} else {
			selection = new StructuredSelection();
		}

		openWizardForModule(wizardId, selection, isNested);

		return null;
	}

	/**
	 * Opens the wizard with the given id and passes it the module as initial selection.
	 *
	 * @param wizardId
	 *            The wizard id of the eclipse newWizard registry
	 * @param selection
	 *            The module resource
	 */
	private void openWizardForModule(String wizardId, IStructuredSelection selection, boolean nested) {
		IWizardDescriptor wizardDescriptor = PlatformUI.getWorkbench().getNewWizardRegistry().findWizard(wizardId);

		if (wizardDescriptor == null) {
			return;
		}

		try {
			IWorkbenchWizard wizard = wizardDescriptor.createWizard();

			// Inject wizard members
			N4JSActivator.getInstance().getInjector(EU_NUMBERFOUR_N4JS_N4JS).injectMembers(wizard);

			// Create and open a new wizard dialog
			WizardDialog wizardDialog = new WizardDialog(UIUtils.getShell(), wizard);

			// If the wizard supports it, enable in module option
			if (wizard instanceof NestedElementWorkbenchWizard) {
				((NestedElementWorkbenchWizard) wizard).init(PlatformUI.getWorkbench(), selection, nested);
			} else {
				// Otherwise just pass it the initial selection
				wizard.init(PlatformUI.getWorkbench(), selection);
			}

			wizardDialog.setTitle(wizard.getWindowTitle());
			wizardDialog.open();

		} catch (CoreException e) {
			/** Failed to create the wizard */
			return;
		}

	}

}
