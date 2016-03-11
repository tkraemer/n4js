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

import org.eclipse.core.resources.IFolder;
import org.eclipse.jdt.ui.ProblemsLabelDecorator;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.google.inject.Inject;

import eu.numberfour.n4js.ui.ImageDescriptorCache.ImageRef;
import eu.numberfour.n4js.ui.navigator.internal.N4JSProjectExplorerHelper;

/**
 * Label provider extension for the N4JS specific Project Explorer view.
 */
public class N4JSProjectExplorerLabelProvider extends LabelProvider {

	private static final Image SRC_FOLDER_IMG = ImageRef.SRC_FOLDER.asImage().orNull();

	@Inject
	private N4JSProjectExplorerHelper helper;

	private final LabelProvider delegate;
	private final ProblemsLabelDecorator decorator;

	/**
	 * Sole constructor.
	 */
	public N4JSProjectExplorerLabelProvider() {
		decorator = new ProblemsLabelDecorator();
		delegate = new DecoratingLabelProvider(new WorkbenchLabelProvider(), decorator);
	}

	@Override
	public String getText(final Object element) {
		return delegate.getText(element);
	}

	@Override
	public Image getImage(final Object element) {

		if (element instanceof IFolder) {
			final IFolder folder = (IFolder) element;
			if (helper.isSourceFolder(folder) || helper.isOutputFolder(folder)) {
				return decorator.decorateImage(SRC_FOLDER_IMG, element);
			}
		}

		return delegate.getImage(element);
	}

}
