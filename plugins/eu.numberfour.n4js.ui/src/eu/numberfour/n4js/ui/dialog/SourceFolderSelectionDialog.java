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
package eu.numberfour.n4js.ui.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.dialogs.ListDialog;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.projectModel.IN4JSSourceContainer;
import eu.numberfour.n4js.ui.ImageDescriptorCache;
import eu.numberfour.n4js.ui.utils.UIUtils;

/**
 * Provides {@link SourceFolderSelectionDialog}s. Use the createDialog method to instantiate a new dialog.
 */
public class SourceFolderSelectionDialog extends ListDialog {

	@Inject
	private IN4JSCore n4jsCore;

	// The input of this dialog
	private IProject inputProject;

	// The list data
	private final List<IN4JSSourceContainer> sourceFolders = new ArrayList<>();

	/**
	 * Create a new source folder selection dialog
	 */
	public SourceFolderSelectionDialog() {
		super(UIUtils.getShell());

		this.setLabelProvider(new N4JSSourceFolderLabelProvider());
		this.setContentProvider(new ArrayContentProvider());
		this.setHelpAvailable(false);
	}

	/**
	 * Sets the input of the dialog.
	 *
	 * Only objects of type {@link IProject} are allowed as input.
	 */
	@Override
	public void setInput(Object input) {
		if (!(input instanceof IProject)) {
			throw new IllegalArgumentException("Only inputs of type IProject are allowed");
		}
		// Save inputProject in private field to maintain access
		inputProject = (IProject) input;

		// Adapt title
		this.setTitle("Select a source folder in the project " + ((IProject) input).getName());

		// Collect source folders
		collectSourceFolders();

		super.setInput(this.sourceFolders.toArray());
	}

	private void collectSourceFolders() {
		sourceFolders.clear();

		if (null == inputProject) {
			return;
		}

		// Collect source folders for the given project
		URI uri = URI.createPlatformResourceURI(inputProject.getName(), true);
		IN4JSProject n4Project = n4jsCore.findProject(uri).orNull();

		if (null == n4Project) {
			return;
		}

		sourceFolders.addAll(n4Project.getSourceContainers().stream()
				.filter(src -> (src.isTest() || src.isSource())) // Filter out external and library folders
				.collect(Collectors.toList()));
	}

	/**
	 * Returns the selected source container or {@code null} if the wizard didn't complete successfully.
	 */
	public IN4JSSourceContainer getFirstResult() {
		Object[] results = getResult();

		if (null != results && results.length > 0) {
			return (IN4JSSourceContainer) results[0];
		}

		return null;
	}

	@Override
	public int open() {
		Object initialSelection = getInitialElementSelections().get(0);

		// Preprocess the initial selection
		if (initialSelection instanceof String &&
				null != inputProject) {

			IN4JSSourceContainer matchingItem = sourceFolders.stream()
					.filter(src -> src.getRelativeLocation().equals(initialSelection))
					.findAny().orElse(null);

			if (null != matchingItem) {
				setInitialSelections(new IN4JSSourceContainer[] { matchingItem });
			}
		}

		return super.open();
	}

	private boolean isN4JSSourceContainer(IResource resource) {
		URI resourceURI = URI.createPlatformResourceURI(resource.getFullPath().toString(), true);
		Optional<? extends IN4JSSourceContainer> sourceContainer = n4jsCore
				.findN4JSSourceContainer(resourceURI);

		return (sourceContainer.isPresent() && sourceContainer.get().getLocation().equals(resourceURI));
	}

	/**
	 * Filter to only let valid n4js source folders pass.
	 */
	public class SourceFolderFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof IResource) {
				return isN4JSSourceContainer((IResource) element);
			}
			return false;
		}
	}

	private static class N4JSSourceFolderLabelProvider extends LabelProvider {

		private final Image sourceFolderImage = ImageDescriptorCache.ImageRef.SRC_FOLDER.asImage().orNull();

		@Override
		public void dispose() {
			sourceFolderImage.dispose();
			super.dispose();
		}

		@Override
		public String getText(Object element) {
			if (element instanceof IN4JSSourceContainer) {
				return ((IN4JSSourceContainer) element).getRelativeLocation();
			}
			return null;
		}

		@Override
		public Image getImage(Object element) {
			return sourceFolderImage;
		}
	}
}
