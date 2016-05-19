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
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerProblemsDecorator;
import org.eclipse.jdt.ui.ProblemsLabelDecorator;
import org.eclipse.jdt.ui.ProblemsLabelDecorator.ProblemsLabelChangedEvent;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.google.inject.Inject;

import eu.numberfour.n4js.ui.ImageDescriptorCache.ImageRef;
import eu.numberfour.n4js.ui.navigator.internal.N4JSProjectExplorerHelper;
import eu.numberfour.n4js.ui.utils.UIUtils;
import eu.numberfour.n4js.utils.Arrays2;

/**
 * Label provider extension for the N4JS specific Project Explorer view.
 */
@SuppressWarnings("restriction")
public class N4JSProjectExplorerLabelProvider extends LabelProvider {

	private static final Image SRC_FOLDER_IMG = ImageRef.SRC_FOLDER.asImage().orNull();

	@Inject
	private N4JSProjectExplorerHelper helper;

	@Inject
	private N4JSProjectExplorerContentProvider contentProvider;

	private final LabelProvider delegate;
	private final IWorkingSetManager workingSetManager;
	private final ProblemsLabelDecorator decorator;
	private final ILabelProviderListener workingSetLabelProviderListener;

	/**
	 * Sole constructor.
	 */
	public N4JSProjectExplorerLabelProvider() {
		decorator = new PackageExplorerProblemsDecorator();
		delegate = new DecoratingLabelProvider(new WorkbenchLabelProvider(), decorator);
		workingSetManager = PlatformUI.getWorkbench().getWorkingSetManager();
		workingSetLabelProviderListener = new ILabelProviderListener() {

			@Override
			public void labelProviderChanged(final LabelProviderChangedEvent event) {
				final LabelProviderChangedEvent wrapperEvent = createWorkingSetWrapperEvent(event);
				if (null != wrapperEvent) {
					UIUtils.getDisplay().asyncExec(() -> fireLabelProviderChanged(wrapperEvent));
				}
			}

		};
		delegate.addListener(workingSetLabelProviderListener);
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

	@Override
	public void dispose() {
		super.dispose();
		delegate.removeListener(workingSetLabelProviderListener);
	}

	/**
	 * Creates a wrapper label provider change event with all visible {@link IWorkingSet working sets} if the working
	 * set mode is enabled in the {@code Project Explorer}. Otherwise returns with {@code null}.
	 *
	 * @param event
	 *            the original event.
	 * @return a wrapper event with all visible working sets (if any) to trigger viewer refresh, or {@code null} if no
	 *         viewer refresh is needed.
	 */
	private LabelProviderChangedEvent createWorkingSetWrapperEvent(final LabelProviderChangedEvent event) {
		if (event instanceof ProblemsLabelChangedEvent && contentProvider.isWorkingSetsEnabled()) {
			final IWorkingSet[] workingSets = workingSetManager.getWorkingSets();
			if (!Arrays2.isEmpty(workingSets)) {
				return new LabelProviderChangedEvent(delegate, workingSets);
			}
		}
		return null;
	}

}
