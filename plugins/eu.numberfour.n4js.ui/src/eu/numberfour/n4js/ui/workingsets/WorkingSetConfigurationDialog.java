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
import static com.google.common.collect.Sets.newHashSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.internal.ui.workingsets.WorkingSetComparator;
import org.eclipse.jdt.internal.ui.workingsets.WorkingSetMessages;
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
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
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
 *
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

	/**
	 * Sort working sets button.
	 *
	 * @since 3.5
	 */
	private Button fSortWorkingSet;

	private WorkingSet[] fResult;
	private List<WorkingSet> fAddedWorkingSets;
	private List<WorkingSet> fRemovedWorkingSets;
	private Map<WorkingSet, WorkingSet> fEditedWorkingSets;
	private List<WorkingSet> fRemovedMRUWorkingSets;

	private int nextButtonId = IDialogConstants.CLIENT_ID + 1;

	/**
	 * Value of sorted state of working sets.
	 *
	 * @since 3.5
	 */
	private boolean fIsSortingEnabled;

	/**
	 * The working set comparator.
	 *
	 * @since 3.5
	 */
	private WorkingSetComparator fComparator;
	private final WorkingSetManager manager;

	public WorkingSetConfigurationDialog(WorkingSetManager manager) {
		super(UIUtils.getShell());
		this.manager = manager;
		setTitle(WorkingSetMessages.WorkingSetConfigurationDialog_title);
		setMessage(WorkingSetMessages.WorkingSetConfigurationDialog_message);
		allWorkingSets = newArrayList(manager.getAllWorkingSets());
	}

	/**
	 * Returns the selected working sets
	 *
	 * @return the selected working sets
	 */
	public WorkingSet[] getSelection() {
		return fResult;
	}

	/**
	 * Sets the initial selection
	 *
	 * @param workingSets
	 *            the initial selection
	 */
	public void setSelection(WorkingSet[] workingSets) {
		fResult = workingSets;
		setInitialSelections(workingSets);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control control = super.createContents(parent);
		setInitialSelection();
		updateButtonAvailability();
		return control;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		createMessageArea(composite);
		Composite inner = new Composite(composite, SWT.NONE);
		inner.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		inner.setLayout(layout);
		createTableViewer(inner);
		createOrderButtons(inner);
		createModifyButtons(composite);
		if (fIsSortingEnabled) {
			viewer.setComparator(new ViewerComparator(getComparator()) {
				/*
				 * @see ViewerComparator#compare(Viewer, Object, Object)
				 *
				 * @since 3.5
				 */
				@Override
				public int compare(Viewer viewer, Object e1, Object e2) {
					return getComparator().compare(e1, e2);
				}
			});
		}
		viewer.setInput(allWorkingSets);
		applyDialogFont(composite);

		return composite;
	}

	private void createTableViewer(Composite parent) {
		viewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER | SWT.MULTI);
		viewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				updateButtonAvailability();
			}
		});
		GridData data = new GridData(GridData.FILL_BOTH);
		data.heightHint = convertHeightInCharsToPixels(20);
		data.widthHint = convertWidthInCharsToPixels(50);
		viewer.getTable().setLayoutData(data);

		viewer.setContentProvider(new ArrayContentProvider());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				handleSelectionChanged();
			}
		});
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (editButton.isEnabled())
					editSelectedWorkingSet();
			}
		});
	}

	private void createModifyButtons(Composite composite) {
		Composite buttonComposite = new Composite(composite, SWT.RIGHT);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		buttonComposite.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		composite.setData(data);

		newButton = createButton(buttonComposite, nextButtonId++,
				WorkingSetMessages.WorkingSetConfigurationDialog_new_label, false);
		newButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createWorkingSet();
			}
		});

		editButton = createButton(buttonComposite, nextButtonId++,
				WorkingSetMessages.WorkingSetConfigurationDialog_edit_label, false);
		editButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editSelectedWorkingSet();
			}
		});

		removeButton = createButton(buttonComposite, nextButtonId++,
				WorkingSetMessages.WorkingSetConfigurationDialog_remove_label, false);
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeSelectedWorkingSets();
			}
		});
	}

	private void createOrderButtons(Composite parent) {
		Composite buttons = new Composite(parent, SWT.NONE);
		buttons.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttons.setLayout(layout);

		upButton = new Button(buttons, SWT.PUSH);
		upButton.setText(WorkingSetMessages.WorkingSetConfigurationDialog_up_label);
		setButtonLayoutData(upButton);
		upButton.addSelectionListener(new SelectionAdapter() {
			@Override
			@SuppressWarnings("unchecked")
			public void widgetSelected(SelectionEvent e) {
				moveUp(((IStructuredSelection) viewer.getSelection()).toList());
			}
		});

		downButton = new Button(buttons, SWT.PUSH);
		downButton.setText(WorkingSetMessages.WorkingSetConfigurationDialog_down_label);
		setButtonLayoutData(downButton);
		downButton.addSelectionListener(new SelectionAdapter() {
			@Override
			@SuppressWarnings("unchecked")
			public void widgetSelected(SelectionEvent e) {
				moveDown(((IStructuredSelection) viewer.getSelection()).toList());
			}
		});

		selectAll = new Button(buttons, SWT.PUSH);
		selectAll.setText(WorkingSetMessages.WorkingSetConfigurationDialog_selectAll_label);
		setButtonLayoutData(selectAll);
		selectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectAll();
			}
		});

		deselectAll = new Button(buttons, SWT.PUSH);
		deselectAll.setText(WorkingSetMessages.WorkingSetConfigurationDialog_deselectAll_label);
		setButtonLayoutData(deselectAll);
		deselectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deselectAll();
			}
		});
		/**
		 * A check box that has persistence to sort the working sets alphabetically in the
		 * WorkingSetConfigurationDialog. It restores the unsorted order of the working sets when unchecked.
		 *
		 * @since 3.5
		 */
		fSortWorkingSet = new Button(parent, SWT.CHECK);
		fSortWorkingSet.setText(WorkingSetMessages.WorkingSetConfigurationDialog_sort_working_sets);
		fSortWorkingSet.setLayoutData(new GridData(SWT.LEAD, SWT.CENTER, true, false));
		fSortWorkingSet.setSelection(fIsSortingEnabled);
		fSortWorkingSet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fIsSortingEnabled = fSortWorkingSet.getSelection();
				updateButtonAvailability();
				if (fIsSortingEnabled) {
					viewer.setComparator(new ViewerComparator(getComparator()) {
						/*
						 * @see ViewerComparator#compare(Viewer, Object, Object)
						 *
						 * @since 3.5
						 */
						@Override
						public int compare(Viewer viewer, Object e1, Object e2) {
							return getComparator().compare(e1, e2);
						}
					});
				} else {
					viewer.setComparator(null);
				}
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void okPressed() {
		List<WorkingSet> newResult = getResultWorkingSets();
		fResult = newResult.toArray(new WorkingSet[newResult.size()]);
		if (fIsSortingEnabled) {
			// Collections.sort(allWorkingSets, getComparator());
		}
		setResult(newResult);
		super.okPressed();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<WorkingSet> getResultWorkingSets() {
		Object[] checked = viewer.getCheckedElements();
		return new ArrayList(Arrays.asList(checked));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void cancelPressed() {
		restoreAddedWorkingSets();
		restoreChangedWorkingSets();
		restoreRemovedWorkingSets();
		super.cancelPressed();
	}

	private void setInitialSelection() {
		List<Object[]> selections = getInitialElementSelections();
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
	void handleSelectionChanged() {
		updateButtonAvailability();
	}

	/**
	 * Overrides method in Dialog
	 *
	 * @see org.eclipse.jface.dialogs.Dialog#open()
	 */
	@Override
	public int open() {
		fAddedWorkingSets = newArrayList();
		fRemovedWorkingSets = newArrayList();
		fEditedWorkingSets = new HashMap<WorkingSet, WorkingSet>();
		fRemovedMRUWorkingSets = newArrayList();
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
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		boolean hasSelection = !selection.isEmpty();
		boolean hasSingleSelection = selection.size() == 1;

		removeButton.setEnabled(hasSelection && areAllGlobalWorkingSets(selection));
		editButton.setEnabled(hasSingleSelection);
		if (upButton != null) {
			upButton.setEnabled(canMoveUp());
		}
		if (downButton != null) {
			downButton.setEnabled(canMoveDown());
		}
	}

	private boolean areAllGlobalWorkingSets(IStructuredSelection selection) {
		Set<WorkingSet> globals = newHashSet(manager.getAllWorkingSets());
		for (Iterator<?> iter = selection.iterator(); iter.hasNext();) {
			if (!globals.contains(iter.next()))
				return false;
		}
		return true;
	}

	private void moveUp(List<WorkingSet> toMoveUp) {
		if (toMoveUp.size() > 0) {
			setElements(moveUp(allWorkingSets, toMoveUp));
			viewer.reveal(toMoveUp.get(0));
		}
	}

	private void moveDown(List<WorkingSet> toMoveDown) {
		if (toMoveDown.size() > 0) {
			setElements(reverse(moveUp(reverse(allWorkingSets), toMoveDown)));
			viewer.reveal(toMoveDown.get(toMoveDown.size() - 1));
		}
	}

	private void setElements(List<WorkingSet> elements) {
		allWorkingSets = elements;
		viewer.setInput(allWorkingSets);
		updateButtonAvailability();
	}

	private List<WorkingSet> moveUp(List<WorkingSet> elements, List<WorkingSet> move) {
		int nElements = elements.size();
		List<WorkingSet> res = newArrayList();
		WorkingSet floating = null;
		for (int i = 0; i < nElements; i++) {
			WorkingSet curr = elements.get(i);
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

	private List<WorkingSet> reverse(List<WorkingSet> p) {
		List<WorkingSet> reverse = newArrayList();
		for (int i = p.size() - 1; i >= 0; i--) {
			reverse.add(p.get(i));
		}
		return reverse;
	}

	private boolean canMoveUp() {
		if (!fIsSortingEnabled) {
			int[] indc = viewer.getTable().getSelectionIndices();
			for (int i = 0; i < indc.length; i++) {
				if (indc[i] != i) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean canMoveDown() {
		if (!fIsSortingEnabled) {
			int[] indc = viewer.getTable().getSelectionIndices();
			int k = allWorkingSets.size() - 1;
			for (int i = indc.length - 1; i >= 0; i--, k--) {
				if (indc[i] != k) {
					return true;
				}
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
	 * @since 3.5
	 */
	public List<WorkingSet> getNewlyAddedWorkingSets() {
		return fAddedWorkingSets;

	}

	/**
	 * Returns whether sorting is enabled for working sets.
	 *
	 * @return <code>true</code> if sorting is enabled, <code>false</code> otherwise
	 * @since 3.5
	 */
	public boolean isSortingEnabled() {
		return fIsSortingEnabled;
	}

	/**
	 * Returns the working set comparator.
	 *
	 * @return the working set comparator
	 * @since 3.5
	 */
	private WorkingSetComparator getComparator() {
		if (fComparator == null) {
			fComparator = new WorkingSetComparator(true);
		}
		return fComparator;
	}

	/**
	 * Returns all the working sets.
	 *
	 * @return all the working sets
	 * @since 3.7
	 */
	public WorkingSet[] getAllWorkingSets() {
		return allWorkingSets.toArray(new WorkingSet[allWorkingSets.size()]);
	}
}
