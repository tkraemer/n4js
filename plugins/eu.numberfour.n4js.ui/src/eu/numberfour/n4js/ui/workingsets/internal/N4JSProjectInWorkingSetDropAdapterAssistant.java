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

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static eu.numberfour.n4js.ui.workingsets.WorkingSet.OTHERS_WORKING_SET_LABEL;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;

import eu.numberfour.n4js.ui.workingsets.ManualAssociationAwareWorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.ManualAssociationAwareWorkingSetManager.ManualAssociationWorkingSet;
import eu.numberfour.n4js.ui.workingsets.WorkingSet;
import eu.numberfour.n4js.ui.workingsets.WorkingSetDiffBuilder;
import eu.numberfour.n4js.ui.workingsets.WorkingSetManager;
import eu.numberfour.n4js.ui.workingsets.WorkingSetManagerBroker;
import eu.numberfour.n4js.utils.Diff;
import eu.numberfour.n4js.utils.StatusHelper;

/**
 * Allow dragging projects between N4JS working sets in the {@code Project Explorer}.
 */
public class N4JSProjectInWorkingSetDropAdapterAssistant extends CommonDropAdapterAssistant {

	@Inject
	private StatusHelper statusHelper;

	@Inject
	private WorkingSetManagerBroker workingSetManagerBroker;

	@Override
	public IStatus validateDrop(Object target, int operation, TransferData transferType) {

		// We don't currently support COPY or LINK
		if (operation != DND.DROP_MOVE) {
			return statusHelper.cancel();
		}

		WorkingSet targetWorkingSet = null;
		if (target instanceof WorkingSet) {
			targetWorkingSet = (WorkingSet) target;
		}

		if (targetWorkingSet == null) {
			return statusHelper.cancel();
		}

		if (!LocalSelectionTransfer.getTransfer().isSupportedType(transferType)) {
			return statusHelper.cancel();
		}

		// Verify that we have at least one project not already in the target
		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
		if (!selection.isEmpty() && selection instanceof IStructuredSelection) {
			for (Object item : ((IStructuredSelection) selection).toArray()) {
				if (item instanceof IAdaptable) {
					IProject project = ((IAdaptable) item).getAdapter(IProject.class);
					if (project != null && !workingSetContains(targetWorkingSet, project)) {
						WorkingSetManager manager = ((WorkingSet) target).getWorkingSetManager();
						if (ManualAssociationAwareWorkingSetManager.class.getName().equals(manager.getId())) {
							return statusHelper.OK();
						}
					}
				}
			}
		}

		return statusHelper.cancel();
	}

	@Override
	public IStatus handleDrop(CommonDropAdapter dropAdapter, DropTargetEvent dropTargetEvent,
			Object target) {

		if (!(target instanceof ManualAssociationWorkingSet)) {
			return statusHelper.cancel();
		}

		ManualAssociationWorkingSet oldTarget = (ManualAssociationWorkingSet) target;
		WorkingSetManager manager = oldTarget.getWorkingSetManager();
		if (!ManualAssociationAwareWorkingSetManager.class.getName().equals(manager.getId())) {
			return statusHelper.cancel();
		}

		List<WorkingSet> allItems = newArrayList(manager.getAllWorkingSets());
		List<WorkingSet> visibleItems = newArrayList(manager.getWorkingSets());
		WorkingSetDiffBuilder diffBuilder = new WorkingSetDiffBuilder(manager);

		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
		if (selection instanceof ITreeSelection) {
			for (TreePath path : ((ITreeSelection) selection).getPaths()) {
				IProject project = ((IAdaptable) path.getLastSegment()).getAdapter(IProject.class);
				if (project != null) {
					if (!workingSetContains(oldTarget, project)
							&& !OTHERS_WORKING_SET_LABEL.equals(oldTarget.getName())) {

						Collection<String> projectNames = newHashSet(oldTarget.getAssociatedProjectNames());
						projectNames.add(project.getName());
						ManualAssociationWorkingSet newTarget = new ManualAssociationWorkingSet(projectNames,
								oldTarget.getName(), manager);

						int allIndex = indexOfByName(oldTarget, allItems);
						allItems.remove(allIndex);
						allItems.add(allIndex, newTarget);

						int visibleIndex = indexOfByName(oldTarget, visibleItems);
						if (visibleIndex >= 0) {
							visibleItems.remove(visibleIndex);
							visibleItems.add(visibleIndex, newTarget);
						}

						diffBuilder.edit(oldTarget, newTarget);
					}

					// Check if our top-level element is a working set so that we can perform a move
					if (path.getFirstSegment() instanceof ManualAssociationWorkingSet) {
						ManualAssociationWorkingSet oldSource = ((ManualAssociationWorkingSet) path
								.getFirstSegment());

						if (oldSource != null && !OTHERS_WORKING_SET_LABEL.equals(oldSource.getName())) {

							Collection<String> projectNames = newHashSet(oldSource.getAssociatedProjectNames());
							projectNames.remove(project.getName());
							ManualAssociationWorkingSet newSource = new ManualAssociationWorkingSet(projectNames,
									oldSource.getName(), manager);

							int allIndex = indexOfByName(oldSource, allItems);
							allItems.remove(allIndex);
							allItems.add(allIndex, newSource);

							int visibleIndex = indexOfByName(oldSource, visibleItems);
							if (visibleIndex >= 0) {
								visibleItems.remove(visibleIndex);
								visibleItems.add(visibleIndex, newSource);
							}

							diffBuilder.edit(oldSource, newSource);
						}
					}
				}
			}

		} else if (selection instanceof IStructuredSelection) {
			for (Object item : ((IStructuredSelection) selection).toArray()) {
				IProject project = ((IAdaptable) item).getAdapter(IProject.class);
				if (project != null && !workingSetContains(oldTarget, project)
						&& !OTHERS_WORKING_SET_LABEL.equals(oldTarget.getName())) {

					Collection<String> projectNames = newHashSet(oldTarget.getAssociatedProjectNames());
					projectNames.add(project.getName());
					ManualAssociationWorkingSet newTarget = new ManualAssociationWorkingSet(projectNames,
							oldTarget.getName(), manager);

					allItems.remove(oldTarget);
					allItems.add(newTarget);
					if (visibleItems.remove(oldTarget)) {
						visibleItems.add(newTarget);
					}

					diffBuilder.edit(oldTarget, newTarget);

				}
			}
		}

		WorkingSet[] newItems = Iterables.toArray(visibleItems, WorkingSet.class);
		WorkingSet[] newAllItems = Iterables.toArray(allItems, WorkingSet.class);
		Diff<WorkingSet> diff = diffBuilder.build(newItems, newAllItems);
		if (!diff.isEmpty()) {
			manager.updateState(diff);
			manager.saveState(new NullProgressMonitor());
			workingSetManagerBroker.refreshNavigator();
		}

		return statusHelper.OK();
	}

	private int indexOfByName(WorkingSet element, List<WorkingSet> items) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getName().equals(element.getName())) {
				return i;
			}
		}
		return -1;
	}

	private boolean workingSetContains(WorkingSet workingSet, IProject project) {
		for (IAdaptable element : workingSet.getElements()) {
			if (project.equals(element.getAdapter(IProject.class))) {
				return true;
			}
		}
		return false;
	}

}
