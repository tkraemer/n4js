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

import java.util.Collection;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.google.inject.Inject;

import eu.numberfour.n4js.ui.ImageDescriptorCache.ImageRef;
import eu.numberfour.n4js.ui.workingsets.WorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.WorkingSetManagerBroker;

/**
 * A drop down action for selecting and activating registered working set managers.
 */
public class SelectWorkingSetDropDownAction extends DropDownAction {

	@Inject
	private WorkingSetManagerBroker workingSetManagerBroker;

	/**
	 * Creates a new drop down working set action.
	 */
	public SelectWorkingSetDropDownAction() {
		super(ImageRef.WORKING_SET.asImageDescriptor().orNull());
	}

	@Override
	protected void createMenuItems(final Menu parent) {
		final Collection<WorkingSetManager> managers = workingSetManagerBroker.getWorkingSetManagers();
		for (final WorkingSetManager manager : managers) {
			final MenuItem item = new MenuItem(parent, SWT.CHECK);
			item.setText(manager.getLabel());
			item.setImage(manager.getImage().orNull());
			item.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					workingSetManagerBroker.setActive(manager);
				}
			});
			item.setSelection(workingSetManagerBroker.isActive(manager));

		}

		final WorkingSetManager activeManager = workingSetManagerBroker.getActive();
		if (null != activeManager) {
			createSeparator(parent);
			final MenuItem item = new MenuItem(parent, SWT.CHECK);
			item.setText("Configure " + activeManager.getLabel() + "...");
			item.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					activeManager.configure();
				}
			});
		}

	}

	private MenuItem createSeparator(final Menu parent) {
		return new MenuItem(parent, SWT.SEPARATOR);
	}

}
