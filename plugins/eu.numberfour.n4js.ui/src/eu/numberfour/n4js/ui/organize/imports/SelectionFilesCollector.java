/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.ui.organize.imports;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkingSet;

import com.google.common.collect.Lists;

/**
 * Collects files from {@link IStructuredSelection}. Collects items that pass test with provided filters. Additionally
 * result is filtered for duplicates (based on {@link Set} collection).
 */
public class SelectionFilesCollector {
	private static final Logger LOGGER = Logger.getLogger(SelectionFilesCollector.class);

	private final Predicate<IFile> fileFilter;

	private Set<IFile> collected = null;

	/** Constructs collector with provided filters. */
	public SelectionFilesCollector(Predicate<IFile> fileFilter) {
		this.fileFilter = fileFilter;
	}

	/** Collects files from provided selection. */
	public List<IFile> collectFiles(IStructuredSelection structuredSelection) {
		collected = new HashSet<>();
		for (Object object : structuredSelection.toList()) {
			collectRelevantFiles(object);
		}

		Set<IFile> result = collected;
		collected = null;
		return Lists.newArrayList(result);
	}

	/** Dispatches based on provided element (can call itself recursively). */
	private void collectRelevantFiles(Object element) {
		// order of type check matters!
		if (element instanceof IWorkingSet) {
			collectIAdaptable((IWorkingSet) element);
		} else if (element instanceof IContainer) {
			collectResource((IContainer) element);
		} else if (element instanceof IFile) {
			collectIFile((IFile) element);
		} else {
			LOGGER.warn("Files collector ignores " + element.getClass().getName() + ".");
		}
	}

	private void collectIAdaptable(IWorkingSet workingSet) {
		IAdaptable[] adaptables = workingSet.getElements();
		for (IAdaptable adaptable : adaptables) {
			collectRelevantFiles(adaptable);
		}
	}

	private void collectResource(IContainer container) {
		try {
			IResource[] resources = container.members(IContainer.EXCLUDE_DERIVED);
			for (IResource resource : resources) {
				collectRelevantFiles(resource);
			}
		} catch (CoreException c) {
			LOGGER.warn("Error while collecting files", c);
		}
	}

	private void collectIFile(IFile iFile) {
		if (fileFilter.test(iFile))
			collected.add(iFile);
	}
}
