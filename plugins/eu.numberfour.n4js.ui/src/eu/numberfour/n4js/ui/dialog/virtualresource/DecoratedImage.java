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

import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

/**
 * Helper class to decorate image descriptors.
 */
public class DecoratedImage extends CompositeImageDescriptor {

	private final ImageData data;
	private final ImageData decoratorData;
	private final Point size;

	/**
	 * Creates a new decorated image descriptor
	 *
	 * @param imageDescriptor
	 *            The source image
	 * @param decoratorImage
	 *            The decorator image
	 */
	public DecoratedImage(ImageDescriptor imageDescriptor, ImageDescriptor decoratorImage) {
		this.data = imageDescriptor.getImageData();
		this.size = new Point(this.data.width, this.data.height);
		this.decoratorData = decoratorImage.getImageData().scaledTo(new Double(this.size.x / 1.5).intValue(),
				new Double(this.size.y / 1.5).intValue());
	}

	@Override
	protected void drawCompositeImage(int width, int height) {
		drawImage(this.data, 0, 0);
		drawImage(this.decoratorData, 0, this.size.y - this.decoratorData.height);
	}

	@Override
	protected Point getSize() {
		return this.size;
	}

}
