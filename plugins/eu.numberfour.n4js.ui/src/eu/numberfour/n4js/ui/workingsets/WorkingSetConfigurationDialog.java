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
package eu.numberfour.n4js.ui.workingsets;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.SelectionDialog;

import eu.numberfour.n4js.ui.utils.UIUtils;

/**
 * Dialog for configuring the currently active working set for the navigator.
 */
public class WorkingSetConfigurationDialog extends SelectionDialog {

	private List<WorkingSet> allWorkingSets;
	private CheckboxTableViewer viewer;

	private Button newButton;
	private Button editButton;
	private Button removeButton;
	private Button upButton;
	private Button downButton;
	private Button selectAll;
	private Button deselectAll;

	private WorkingSet[] result;
	private List<WorkingSet> addedWorkingSets;
	private List<WorkingSet> removedWorkingSets;
	private Map<WorkingSet, WorkingSet> editedWorkingSets;

	private int nextButtonId = IDialogConstants.CLIENT_ID + 1;

	private final WorkingSetManager manager;

	/**
	 * Creates a new dialog for the configuration of the working set manager argument.
	 *
	 * @param manager
	 *            the manager that has to be configured.
	 */
	public WorkingSetConfigurationDialog(final WorkingSetManager manager) {
		super(UIUtils.getShell());
		this.manager = manager;
		setTitle("Configure Working Sets");
		setMessage("Select and sort &working sets visible in Project Explorer:");
		allWorkingSets = newArrayList(manager.getAllWorkingSets());
	}

	/**
	 * Returns the selected working sets
	 *
	 * @return the selected working sets
	 */
	public WorkingSet[] getSelection() {
		return result;
	}

	/**
	 * Sets the initial selection
	 *
	 * @param workingSets
	 *            the initial selection
	 */
	public void setSelection(final WorkingSet[] workingSets) {
		result = workingSets;
		setInitialSelections(workingSets);
	}

	@Override
	protected Control createContents(final Composite parent) {
		final Control control = super.createContents(parent);
		setInitialSelection();
		updateButtonAvailability();
		return control;
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite composite = (Composite) super.createDialogArea(parent);

		createMessageArea(composite);
		final Composite inner = new Composite(composite, SWT.NONE);
		inner.setLayoutData(new GridData(GridData.FILL_BOTH));
		final GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		inner.setLayout(layout);
		createTableViewer(inner);
		createOrderButtons(inner);
		// New, Edit and Remove is allowed only for mutable managers.
		if (manager instanceof MutableWorkingSetManager) {
			createModifyButtons(composite);
		}
		viewer.setInput(allWorkingSets);
		applyDialogFont(composite);

		return composite;
	}

	private void createTableViewer(final Composite parent) {
		viewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER | SWT.MULTI);
		viewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(final CheckStateChangedEvent event) {
				updateButtonAvailability();
			}
		});
		final GridData data = new GridData(GridData.FILL_BOTH);
		data.heightHint = convertHeightInCharsToPixels(20);
		data.widthHint = convertWidthInCharsToPixels(50);
		viewer.getTable().setLayoutData(data);

		viewer.setLabelProvider(WorkingSetLabelProvider.INSTANCE);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				handleSelectionChanged();
			}
		});
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(final DoubleClickEvent event) {
				if (editButton.isEnabled())
					editSelectedWorkingSet();
			}
		});
	}

	private void createModifyButtons(final Composite composite) {
		final Composite buttonComposite = new Composite(composite, SWT.RIGHT);
		final GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		buttonComposite.setLayout(layout);
		final GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		composite.setData(data);

		newButton = createButton(buttonComposite, nextButtonId++,
				"New...", false);
		newButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				createWorkingSet();
			}
		});

		editButton = createButton(buttonComposite, nextButtonId++,
				"Edit...", false);
		editButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				editSelectedWorkingSet();
			}
		});

		removeButton = createButton(buttonComposite, nextButtonId++,
				"Remove", false);
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				removeSelectedWorkingSets();
			}
		});
	}

	private void createOrderButtons(final Composite parent) {
		final Composite buttons = new Composite(parent, SWT.NONE);
		buttons.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		final GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttons.setLayout(layout);

		upButton = new Button(buttons, SWT.PUSH);
		upButton.setText("Up");
		setButtonLayoutData(upButton);
		upButton.addSelectionListener(new SelectionAdapter() {
			@Override
			@SuppressWarnings("unchecked")
			public void widgetSelected(final SelectionEvent e) {
				moveUp(((IStructuredSelection) viewer.getSelection()).toList());
			}
		});

		downButton = new Button(buttons, SWT.PUSH);
		downButton.setText("Down");
		setButtonLayoutData(downButton);
		downButton.addSelectionListener(new SelectionAdapter() {
			@Override
			@SuppressWarnings("unchecked")
			public void widgetSelected(final SelectionEvent e) {
				moveDown(((IStructuredSelection) viewer.getSelection()).toList());
			}
		});

		selectAll = new Button(buttons, SWT.PUSH);
		selectAll.setText("Select All");
		setButtonLayoutData(selectAll);
		selectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				selectAll();
			}
		});

		deselectAll = new Button(buttons, SWT.PUSH);
		deselectAll.setText("Deselect All");
		setButtonLayoutData(deselectAll);
		deselectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				deselectAll();
			}
		});
	}

	@Override
	protected void okPressed() {
		final List<WorkingSet> newResult = getResultWorkingSets();
		result = newResult.toArray(new WorkingSet[newResult.size()]);
		setResult(newResult);
		super.okPressed();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<WorkingSet> getResultWorkingSets() {
		final Object[] checked = viewer.getCheckedElements();
		return new ArrayList(Arrays.asList(checked));
	}

	@Override
	protected void cancelPressed() {
		restoreAddedWorkingSets();
		restoreChangedWorkingSets();
		restoreRemovedWorkingSets();
		super.cancelPressed();
	}

	private void setInitialSelection() {
		@SuppressWarnings("unchecked")
		final List<Object[]> selections = getInitialElementSelections();
		if (!selections.isEmpty()) {
			viewer.setCheckedElements(selections.toArray());
		}
	}

	private void createWorkingSet() {
		// WorkingSetNewWizard wizard = manager.createWorkingSetNewWizard(new String[] { WorkingSetIDs.JAVA });
		// // the wizard can't be null since we have at least the Java working set.
		// WizardDialog dialog = new WizardDialog(getShell(), wizard);
		// dialog.create();
		// if (dialog.open() == Window.OK) {
		// WorkingSet workingSet = wizard.getSelection();
		// if (WorkingSetModel.isSupportedAsTopLevelElement(workingSet)) {
		// allWorkingSets.add(workingSet);
		// viewer.add(workingSet);
		// viewer.setSelection(new StructuredSelection(workingSet), true);
		// viewer.setChecked(workingSet, true);
		// manager.addWorkingSet(workingSet);
		// fAddedWorkingSets.add(workingSet);
		// }
		// }
	}

	private void editSelectedWorkingSet() {
		// WorkingSet editWorkingSet = (WorkingSet) ((IStructuredSelection) viewer.getSelection()).getFirstElement();
		// WorkingSetEditWizard wizard = manager.createWorkingSetEditWizard(editWorkingSet);
		// WizardDialog dialog = new WizardDialog(getShell(), wizard);
		// WorkingSet originalWorkingSet = fEditedWorkingSets.get(editWorkingSet);
		// boolean firstEdit = originalWorkingSet == null;
		//
		// // save the original working set values for restoration when selection
		// // dialog is cancelled.
		// if (firstEdit) {
		// originalWorkingSet = PlatformUI.getWorkbench().getWorkingSetManager()
		// .createWorkingSet(editWorkingSet.getLabel(), editWorkingSet.getElements());
		// } else {
		// fEditedWorkingSets.remove(editWorkingSet);
		// }
		// dialog.create();
		// if (dialog.open() == Window.OK) {
		// editWorkingSet = wizard.getSelection();
		// if (fIsSortingEnabled)
		// viewer.refresh();
		// else
		// viewer.update(editWorkingSet, null);
		//
		// // make sure ok button is enabled when the selected working set
		// // is edited. Fixes bug 33386.
		// updateButtonAvailability();
		// }
		// fEditedWorkingSets.put(editWorkingSet, originalWorkingSet);
	}

	/**
	 * Called when the selection has changed.
	 */
	private void handleSelectionChanged() {
		updateButtonAvailability();
	}

	@Override
	public int open() {
		addedWorkingSets = newArrayList();
		removedWorkingSets = newArrayList();
		editedWorkingSets = newHashMap();
		return super.open();
	}

	/**
	 * Removes the selected working sets from the workbench.
	 */
	private void removeSelectedWorkingSets() {
		// ISelection selection = viewer.getSelection();
		// if (selection instanceof IStructuredSelection) {
		// Iterator<?> iter = ((IStructuredSelection) selection).iterator();
		// while (iter.hasNext()) {
		// WorkingSet workingSet = (WorkingSet) iter.next();
		// if (fAddedWorkingSets.contains(workingSet)) {
		// fAddedWorkingSets.remove(workingSet);
		// } else {
		// WorkingSet[] recentWorkingSets = manager.getRecentWorkingSets();
		// for (int i = 0; i < recentWorkingSets.length; i++) {
		// if (workingSet.equals(recentWorkingSets[i])) {
		// fRemovedMRUWorkingSets.add(workingSet);
		// break;
		// }
		// }
		// fRemovedWorkingSets.add(workingSet);
		// }
		// allWorkingSets.remove(workingSet);
		// manager.removeWorkingSet(workingSet);
		// }
		// viewer.remove(((IStructuredSelection) selection).toArray());
		// }
	}

	/**
	 * Removes newly created working sets from the working set manager.
	 */
	private void restoreAddedWorkingSets() {
		// Iterator<WorkingSet> iterator = fAddedWorkingSets.iterator();
		//
		// while (iterator.hasNext()) {
		// manager.removeWorkingSet(iterator.next());
		// }
	}

	/**
	 * Rolls back changes to working sets.
	 */
	private void restoreChangedWorkingSets() {
		// Iterator<WorkingSet> iterator = fEditedWorkingSets.keySet().iterator();
		//
		// while (iterator.hasNext()) {
		// WorkingSet editedWorkingSet = iterator.next();
		// WorkingSet originalWorkingSet = fEditedWorkingSets.get(editedWorkingSet);
		//
		// if (editedWorkingSet.getLabel().equals(originalWorkingSet.getLabel()) == false) {
		// editedWorkingSet.setName(originalWorkingSet.getLabel());
		// }
		// if (editedWorkingSet.getElements().equals(originalWorkingSet.getElements()) == false) {
		// editedWorkingSet.setElements(originalWorkingSet.getElements());
		// }
		// }
	}

	/**
	 * Adds back removed working sets to the working set manager.
	 */
	private void restoreRemovedWorkingSets() {
		// WorkingSetManager manager = PlatformUI.getWorkbench().getWorkingSetManager();
		// Iterator<WorkingSet> iterator = fRemovedWorkingSets.iterator();
		//
		// while (iterator.hasNext()) {
		// manager.addWorkingSet(iterator.next());
		// }
		// iterator = fRemovedMRUWorkingSets.iterator();
		// while (iterator.hasNext()) {
		// manager.addRecentWorkingSet(iterator.next());
		// }
	}

	/**
	 * Updates the modify buttons' enabled state based on the current selection.
	 */
	private void updateButtonAvailability() {
		final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		final boolean hasSelection = !selection.isEmpty();
		final boolean hasSingleSelection = selection.size() == 1;

		if (manager instanceof MutableWorkingSetManager) {
			removeButton.setEnabled(hasSelection && areAllGlobalWorkingSets(selection));
			editButton.setEnabled(hasSingleSelection);
		}
		if (upButton != null) {
			upButton.setEnabled(canMoveUp());
		}
		if (downButton != null) {
			downButton.setEnabled(canMoveDown());
		}
	}

	private boolean areAllGlobalWorkingSets(final IStructuredSelection selection) {
		final Set<WorkingSet> globals = newHashSet(manager.getAllWorkingSets());
		for (final Iterator<?> iter = selection.iterator(); iter.hasNext();) {
			if (!globals.contains(iter.next()))
				return false;
		}
		return true;
	}

	private void moveUp(final List<WorkingSet> toMoveUp) {
		if (toMoveUp.size() > 0) {
			setElements(moveUp(allWorkingSets, toMoveUp));
			viewer.reveal(toMoveUp.get(0));
		}
	}

	private void moveDown(final List<WorkingSet> toMoveDown) {
		if (toMoveDown.size() > 0) {
			setElements(reverse(moveUp(reverse(allWorkingSets), toMoveDown)));
			viewer.reveal(toMoveDown.get(toMoveDown.size() - 1));
		}
	}

	private void setElements(final List<WorkingSet> elements) {
		allWorkingSets = elements;
		viewer.setInput(allWorkingSets);
		updateButtonAvailability();
	}

	private List<WorkingSet> moveUp(final List<WorkingSet> elements, final List<WorkingSet> move) {
		final int nElements = elements.size();
		final List<WorkingSet> res = newArrayList();
		WorkingSet floating = null;
		for (int i = 0; i < nElements; i++) {
			final WorkingSet curr = elements.get(i);
			if (move.contains(curr)) {
				res.add(curr);
			} else {
				if (floating != null) {
					res.add(floating);
				}
				floating = curr;
			}
		}
		if (floating != null) {
			res.add(floating);
		}
		return res;
	}

	private List<WorkingSet> reverse(final List<WorkingSet> p) {
		final List<WorkingSet> copy = newArrayList(p);
		Collections.reverse(copy);
		return copy;
	}

	private boolean canMoveUp() {
		final int[] indc = viewer.getTable().getSelectionIndices();
		for (int i = 0; i < indc.length; i++) {
			if (indc[i] != i) {
				return true;
			}
		}
		return false;
	}

	private boolean canMoveDown() {
		final int[] indc = viewer.getTable().getSelectionIndices();
		int k = allWorkingSets.size() - 1;
		for (int i = indc.length - 1; i >= 0; i--, k--) {
			if (indc[i] != k) {
				return true;
			}
		}
		return false;
	}

	// ---- select / deselect --------------------------------------------------

	private void selectAll() {
		viewer.setAllChecked(true);
	}

	private void deselectAll() {
		viewer.setAllChecked(false);
	}

	/**
	 * Returns the list of newly added working sets through this dialog.
	 *
	 * @return the list of newly added working sets
	 *
	 */
	public List<WorkingSet> getNewlyAddedWorkingSets() {
		return addedWorkingSets;

	}

	/**
	 * Returns all the working sets.
	 *
	 * @return all the working sets
	 */
	public WorkingSet[] getAllWorkingSets() {
		return allWorkingSets.toArray(new WorkingSet[allWorkingSets.size()]);
	}
}
