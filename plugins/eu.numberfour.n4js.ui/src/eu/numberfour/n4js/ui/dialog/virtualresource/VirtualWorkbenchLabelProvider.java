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
package eu.numberfour.n4js.ui.dialog.virtualresource;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import eu.numberfour.n4js.ui.ImageDescriptorCache;

/**
 * WorkbenchLabelProvider with VirtualResource decoration support
 */
public class VirtualWorkbenchLabelProvider extends WorkbenchLabelProvider {

	private static ImageDescriptor PLUS_ICON = ImageDescriptorCache.ImageRef.FOLDER_DECORATOR_PLUS.asImageDescriptor()
			.orNull();

	@Override
	protected ImageDescriptor decorateImage(ImageDescriptor input, Object element) {
		if (element instanceof VirtualResource && !(element instanceof WrappingVirtualContainer)) {
			return new DecoratedImage(input, PLUS_ICON);
		}
		return super.decorateImage(input, element);
	}
}
