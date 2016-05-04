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
package eu.numberfour.n4js.ui.navigator;

import static org.eclipse.jdt.ui.JavaElementImageDescriptor.BUILDPATH_ERROR;
import static org.eclipse.jdt.ui.JavaElementImageDescriptor.ERROR;
import static org.eclipse.jdt.ui.JavaElementImageDescriptor.WARNING;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.internal.ui.viewsupport.TreeHierarchyLayoutProblemsDecorator;

import eu.numberfour.n4js.ui.workingsets.WorkingSet;

/**
 * Enables decoration for the customized {@link WorkingSet working sets} in the navigator.
 */
@SuppressWarnings("restriction")
public class N4JSProjectExplorerProblemsDecorator extends TreeHierarchyLayoutProblemsDecorator {

	@Override
	protected int computeAdornmentFlags(final Object obj) {
		if (!(obj instanceof WorkingSet)) {
			return super.computeAdornmentFlags(obj);
		}

		final WorkingSet workingSet = (WorkingSet) obj;
		final IAdaptable[] elements = workingSet.getElements();
		int result = 0;
		for (int i = 0; i < elements.length; i++) {
			final IAdaptable element = elements[i];
			final int flags = super.computeAdornmentFlags(element);
			if ((flags & BUILDPATH_ERROR) != 0) {
				return BUILDPATH_ERROR;
			}
			result |= flags;
		}
		if ((result & ERROR) != 0) {
			return ERROR;
		} else if ((result & WARNING) != 0) {
			return WARNING;
		} else {
			return 0;
		}
	}

}
