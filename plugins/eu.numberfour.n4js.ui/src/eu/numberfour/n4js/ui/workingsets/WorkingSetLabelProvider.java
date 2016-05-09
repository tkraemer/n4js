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

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.numberfour.n4js.ui.ImageDescriptorCache.ImageRef;

/**
 * Label provider for working sets.
 */
public class WorkingSetLabelProvider extends LabelProvider {

	/**
	 * Shared label provider instance for working sets.
	 */
	public static LabelProvider INSTANCE = new WorkingSetLabelProvider();

	private WorkingSetLabelProvider() {
		// Singleton.
	}

	@Override
	public Image getImage(final Object element) {
		if (element instanceof WorkingSet) {
			return ImageRef.WORKING_SET.asImage().orNull();
		}
		return super.getImage(element);
	}

	@Override
	public String getText(final Object element) {
		if (element instanceof WorkingSet) {
			return ((WorkingSet) element).getName();
		}
		return super.getText(element);
	}

}
