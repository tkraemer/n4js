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
package eu.numberfour.n4js.ui.workingsets.internal;

import org.eclipse.jdt.ui.actions.ImportActionGroup;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.internal.navigator.resources.actions.EditActionGroup;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * Common Navigator Framework (CNF) action provider contribution for N4JS working sets.
 */
@SuppressWarnings("restriction")
public class N4JSNavigatorActionProvider extends CommonActionProvider {

	private EditActionGroup editGroup;
	private N4JSNewWizardsActionGroup newWizardsGroup;
	private ImportActionGroup importExportGroup;
	private N4JSProjectActionGroup projectGroup;

	private boolean inViewPart = false;

	@Override
	public void fillActionBars(final IActionBars actionBars) {
		if (inViewPart) {
			editGroup.fillActionBars(actionBars);
			newWizardsGroup.fillActionBars(actionBars);
			importExportGroup.fillActionBars(actionBars);
			projectGroup.fillActionBars(actionBars);
		}
	}

	@Override
	public void fillContextMenu(final IMenuManager menu) {
		if (inViewPart) {
			editGroup.fillContextMenu(menu);
			newWizardsGroup.fillContextMenu(menu);
			importExportGroup.fillContextMenu(menu);
			projectGroup.fillContextMenu(menu);
		}
	}

	@Override
	public void init(final ICommonActionExtensionSite site) {

		ICommonViewerWorkbenchSite workbenchSite = null;
		if (site.getViewSite() instanceof ICommonViewerWorkbenchSite)
			workbenchSite = (ICommonViewerWorkbenchSite) site.getViewSite();

		if (workbenchSite != null) {
			if (workbenchSite.getPart() != null && workbenchSite.getPart() instanceof IViewPart) {
				final IViewPart viewPart = (IViewPart) workbenchSite.getPart();

				editGroup = new EditActionGroup(viewPart.getSite().getShell());
				newWizardsGroup = new N4JSNewWizardsActionGroup(viewPart.getSite());
				importExportGroup = new ImportActionGroup(viewPart);
				projectGroup = new N4JSProjectActionGroup(viewPart);
				inViewPart = true;
			}
		}
	}

	@Override
	public void setContext(final ActionContext context) {
		super.setContext(context);
		if (inViewPart) {
			editGroup.setContext(context);
			newWizardsGroup.setContext(context);
			importExportGroup.setContext(context);
			projectGroup.setContext(context);
		}
	}

	@Override
	public void dispose() {
		if (inViewPart) {
			editGroup.dispose();
			newWizardsGroup.dispose();
			importExportGroup.dispose();
			projectGroup.dispose();
		}
		super.dispose();
	}
}
