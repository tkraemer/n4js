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
package eu.numberfour.n4js.ui.navigator.internal;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.internal.navigator.resources.actions.WorkingSetActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

import com.google.inject.Inject;

import eu.numberfour.n4js.ui.workingsets.TopLevelElementChangedListener;
import eu.numberfour.n4js.ui.workingsets.WorkingSetManagerBroker;

/**
 * Customized working set provider for the CNF (Common Navigator Framework).
 */
@SuppressWarnings("restriction")
public class N4JSWorkingSetActionProvider extends WorkingSetActionProvider implements TopLevelElementChangedListener {

	@Inject
	private SelectWorkingSetDropDownAction selectWorkingSetAction;

	@Inject
	private SelectTopLevelElementActionGroup selectTopLevelElementAction;

	@Inject
	private WorkingSetManagerBroker workingSetManagerBroker;

	private IActionBars actionBars;
	private boolean alreadyContributedToViewer;
	private ActionContributionItem selectWorkingSetDelegate;

	@Override
	public void init(final ICommonActionExtensionSite site) {
		selectWorkingSetDelegate = new ActionContributionItem(selectWorkingSetAction);
		workingSetManagerBroker.addTopLevelElementChangedListener(this);
	}

	@Override
	public void fillActionBars(@SuppressWarnings("hiding") final IActionBars actionBars) {
		if (!alreadyContributedToViewer) {
			this.actionBars = actionBars;
			try {
				topLevelElementChanged(workingSetManagerBroker.isWorkingSetTopLevel());
			} finally {
				alreadyContributedToViewer = true;
			}
		}
	}

	@Override
	public void fillContextMenu(final IMenuManager menu) {
		// Disables the default one.
	}

	@Override
	public void restoreState(final IMemento aMemento) {
		// Nothing to restore.
	}

	@Override
	public void saveState(final IMemento aMemento) {
		// Nothing to save.
	}

	@Override
	public void dispose() {
		if (selectWorkingSetAction != null) {
			selectWorkingSetAction.dispose();
		}
		workingSetManagerBroker.removeTopLevelElementChangedListener(this);
	}

	@Override
	public void topLevelElementChanged(final boolean workingSetTopLevel) {
		if (selectWorkingSetDelegate != null && actionBars != null) {
			final IToolBarManager toolBarManager = actionBars.getToolBarManager();
			toolBarManager.remove(selectWorkingSetDelegate);
			if (workingSetManagerBroker.isWorkingSetTopLevel()) {
				toolBarManager.add(selectWorkingSetDelegate);
			}
			selectTopLevelElementAction.fillActionBars(actionBars);
			actionBars.updateActionBars();
		}
	}

}
