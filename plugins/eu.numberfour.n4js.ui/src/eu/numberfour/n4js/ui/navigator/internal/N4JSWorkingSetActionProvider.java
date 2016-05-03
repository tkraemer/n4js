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

import static org.eclipse.ui.internal.navigator.workingsets.WorkingSetsContentProvider.SHOW_TOP_LEVEL_WORKING_SETS;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.internal.navigator.resources.actions.WorkingSetActionProvider;
import org.eclipse.ui.internal.navigator.resources.actions.WorkingSetRootModeActionGroup;
import org.eclipse.ui.internal.navigator.workingsets.WorkingSetsContentProvider;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.IExtensionStateModel;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.ui.navigator.resources.ProjectExplorer;

import com.google.inject.Inject;

/**
 * Customized working set provider for the CNF (Common Navigator Framework).
 */
@SuppressWarnings("restriction")
public class N4JSWorkingSetActionProvider extends WorkingSetActionProvider {

	@Inject
	private SelectWorkingSetDropDownAction selectWorkingSetDropDownAction;

	private CommonViewer viewer;
	private IActionBars actionBars;
	private CommonNavigator projectExplorer;
	private boolean alreadyContributedToViewer;
	private ActionContributionItem delegateAction;
	private INavigatorContentService contentService;
	private IExtensionStateModel extensionStateModel;
	private WorkingSetRootModeActionGroup workingSetRootModeActionGroup;

	private final IPropertyChangeListener rootModeListener = new IPropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			if (SHOW_TOP_LEVEL_WORKING_SETS.equals(event.getProperty())) {
				updateRootMode();
			}
		}

	};

	@Override
	public void init(ICommonActionExtensionSite site) {
		viewer = (CommonViewer) site.getStructuredViewer();
		projectExplorer = viewer.getCommonNavigator();
		contentService = site.getContentService();
		extensionStateModel = contentService.findStateModel(WorkingSetsContentProvider.EXTENSION_ID);
		extensionStateModel.addPropertyChangeListener(rootModeListener);
		workingSetRootModeActionGroup = new WorkingSetRootModeActionGroup(viewer, extensionStateModel);
		delegateAction = new ActionContributionItem(selectWorkingSetDropDownAction);
		updateRootMode();
	}

	@Override
	public void fillActionBars(@SuppressWarnings("hiding") final IActionBars actionBars) {
		this.actionBars = actionBars;
		if (!alreadyContributedToViewer) {
			try {
				final IToolBarManager toolBarManager = this.actionBars.getToolBarManager();
				if (selectWorkingSetDropDownAction != null) {
					toolBarManager.add(selectWorkingSetDropDownAction);
				}
				if (workingSetRootModeActionGroup != null) {
					workingSetRootModeActionGroup.fillActionBars(this.actionBars);
				}
				toolBarManager.update(true);
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
	public void restoreState(final IMemento memento) {

		// Need to run this asynchronously to avoid being reentered when processing a selection change
		viewer.getControl().getShell().getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				boolean showWorkingSets = false;
				if (memento != null) {
					Integer storedValue = memento.getInteger(WorkingSetsContentProvider.SHOW_TOP_LEVEL_WORKING_SETS);
					showWorkingSets = storedValue == null || storedValue.intValue() == 1;
				}
				extensionStateModel.setBooleanProperty(SHOW_TOP_LEVEL_WORKING_SETS, showWorkingSets);
				workingSetRootModeActionGroup.setShowTopLevelWorkingSets(showWorkingSets);
			}
		});
	}

	@Override
	public void saveState(IMemento memento) {
		if (memento != null) {
			int showWorkingSets = extensionStateModel.getBooleanProperty(SHOW_TOP_LEVEL_WORKING_SETS) ? 1 : 0;
			memento.putInteger(SHOW_TOP_LEVEL_WORKING_SETS, showWorkingSets);
		}
	}

	@Override
	public void dispose() {
		if (workingSetRootModeActionGroup != null) {
			workingSetRootModeActionGroup.dispose();
		}
		if (selectWorkingSetDropDownAction != null) {
			selectWorkingSetDropDownAction.dispose();
		}
		extensionStateModel.removePropertyChangeListener(rootModeListener);
	}

	/**
	 * Returns with {@code true} if the working sets are enabled in the {@code Project Explorer}. Otherwise returns with
	 * {@code false}.
	 */
	protected boolean isWorkingSetsEnabled() {
		return null != projectExplorer && ProjectExplorer.WORKING_SETS == projectExplorer.getRootMode();
	}

	private void updateRootMode() {
		if (projectExplorer == null) {
			return;
		}

		if (extensionStateModel.getBooleanProperty(SHOW_TOP_LEVEL_WORKING_SETS)) {
			projectExplorer.setRootMode(ProjectExplorer.WORKING_SETS);
		} else {
			projectExplorer.setRootMode(ProjectExplorer.PROJECTS);
		}

		if (selectWorkingSetDropDownAction != null && delegateAction != null && actionBars != null) {
			final IToolBarManager toolBarManager = actionBars.getToolBarManager();
			toolBarManager.remove(delegateAction);
			if (isWorkingSetsEnabled()) {
				toolBarManager.add(delegateAction);
			}
			actionBars.updateActionBars();
		}
	}

}
