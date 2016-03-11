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
package eu.numberfour.n4js.xpect.ui.results;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.junit.runner.Description;

import eu.numberfour.n4js.xpect.ui.N4IDEXpectUIPlugin;
import eu.numberfour.n4js.xpect.ui.runner.N4IDEXpectFileNameUtil;

/**
 * When called will check if provided data contains {@link Description} of test and will check with Execution status
 * stored in test view ({@link N4IDEXpectView}) if test failed. If that is the case, will generate data for bug report
 * in a console view.
 */
public class XpectGenerateBugReportCommandHandler extends AbstractHandler {

	private N4IDEXpectView view;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelectionChecked(event);

		IWorkbenchWindow[] windows = N4IDEXpectUIPlugin.getDefault().getWorkbench().getWorkbenchWindows();
		try {
			view = (N4IDEXpectView) windows[0].getActivePage().showView(
					N4IDEXpectView.ID);
		} catch (PartInitException e) {
			N4IDEXpectUIPlugin.logError("cannot refresh test view window", e);
		}

		Description desc = (Description) selection.getFirstElement();

		// handle failed suite
		if (desc.isSuite()) {
			final N4IDEXpectView finalview = view;

			boolean suitePassed = desc.getChildren().stream()
					.noneMatch(childDescription -> finalview.testsExecutionStatus.hasFailed(childDescription));

			if (suitePassed) {
				XpectFileContentsUtil.getXpectFileContentAccess(desc).ifPresent(
						xpectFielContentAccess -> {
							if (xpectFielContentAccess.containsFixme()) {
								XpectBugReportUtil.displayGeneratedBugConsole(
										N4IDEXpectFileNameUtil.getSuiteName(desc),
										xpectFielContentAccess.getContetns());
							}
						});

			} else {
				XpectConsole console = ConsoleDisplayMgr.getOrCreate("generated bug for "
						+ N4IDEXpectFileNameUtil.getSuiteName(desc));
				console.clear();
				String ls = System.lineSeparator();
				console.log("Suite must be passing and contain XPECT FIXME marker to be submited bug report. Please :"
						+ ls + " - fix failing tests" + ls + " - mark test in question with XPECT FIXME");
			}
		}

		return null;
	}

}
