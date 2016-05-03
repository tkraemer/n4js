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

import static com.google.common.collect.FluentIterable.from;

import java.util.Collection;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolItem;

import com.google.inject.Inject;

import eu.numberfour.n4js.ui.ImageDescriptorCache.ImageRef;
import eu.numberfour.n4js.ui.workingsets.MutableWorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.WorkingSetBroker;
import eu.numberfour.n4js.ui.workingsets.WorkingSetManager;

/**
 *
 */
public class SelectWorkingSetDropDownAction extends Action implements IMenuCreator {

	@Inject
	private WorkingSetBroker workingSetBroker;

	/**
	 * Creates a new drop down working set action.
	 */
	public SelectWorkingSetDropDownAction() {
		setMenuCreator(this);
		setImageDescriptor(ImageRef.WORKING_SET.asImageDescriptor().orNull());
	}

	private Menu menu;

	@Override
	public void dispose() {
		if (menu != null) {
			menu.dispose();
			menu = null;
		}
	}

	@Override
	public void runWithEvent(Event event) {
		if (event.widget instanceof ToolItem) {
			ToolItem toolItem = (ToolItem) event.widget;
			Control control = toolItem.getParent();
			@SuppressWarnings("hiding")
			Menu menu = getMenu(control);

			Rectangle bounds = toolItem.getBounds();
			Point topLeft = new Point(bounds.x, bounds.y + bounds.height);
			menu.setLocation(control.toDisplay(topLeft));
			menu.setVisible(true);
		}
	}

	@Override
	public Menu getMenu(Control parent) {
		if (menu != null) {
			menu.dispose();
			menu = null;
		}
		menu = new Menu(parent);
		createMenuItems(menu);
		return menu;
	}

	@Override
	public Menu getMenu(Menu parent) {
		return null;
	}

	private void createMenuItems(Menu parent) {
		Collection<WorkingSetManager> managers = workingSetBroker.getWorkingSetManagers();
		for (WorkingSetManager manager : managers) {
			MenuItem item = new MenuItem(parent, SWT.CHECK);
			item.setText(manager.getLabel());
			item.setImage(manager.getImage().orNull());
			item.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					workingSetBroker.setActive(manager);
				}
			});
			item.setSelection(workingSetBroker.isActive(manager));

		}

		Collection<MutableWorkingSetManager> configurables = from(managers)
				.filter(MutableWorkingSetManager.class).toList();

		if (!configurables.isEmpty()) {
			createSeparator(parent);
		}

		for (MutableWorkingSetManager configurable : configurables) {
			final MenuItem item = new MenuItem(parent, SWT.CHECK);
			item.setText("Configure " + configurable.getLabel() + "...");
			item.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					configurable.configure();
				}
			});
		}
	}

	private MenuItem createSeparator(Menu parent) {
		return new MenuItem(parent, SWT.SEPARATOR);
	}

}
