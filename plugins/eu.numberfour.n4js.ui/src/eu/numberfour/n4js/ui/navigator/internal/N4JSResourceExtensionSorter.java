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

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.ui.internal.navigator.resources.workbench.ResourceExtensionSorter;

/**
 * Viewer sorter extension for the {@code Project Explorer} with N4JS content. Uses the default behavior when dealing
 * with Eclipse {@link IResource resource} instances and also handles {@link ResourceNode resource node} elements.
 */
@SuppressWarnings("restriction")
public class N4JSResourceExtensionSorter extends ResourceExtensionSorter {

	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	protected int compareClass(Object e1, Object e2) {
		if (e1 instanceof ResourceNode && e2 instanceof ResourceNode) {
			final File f1 = ((ResourceNode) e1).getResource();
			final File f2 = ((ResourceNode) e2).getResource();
			if (f1.isFile() == f2.isFile()) {
				return getComparator().compare(f1.getName(), f2.getName());
			} else if (f1.isDirectory() && f2.isFile()) {
				return -1;
			} else if (f1.isFile() && f2.isDirectory()) {
				return 1;
			}
		}
		return super.compareClass(e1, e2);
	}

}
