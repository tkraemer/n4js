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

import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * Virtual wrapping resource container is useful to overlay workspace file structures. A wrapping resource container
 * shadows an existing container. This means it only returns the original children of the wrapped container if they are
 * not shadowed by any virtual child. Other than that it tries to imitate the properties of the wrapped container.
 */
public class WrappingVirtualContainer extends VirtualContainer {

	private final IWorkbenchAdapter containerAdapter;
	private final IContainer container;

	/**
	 * Creates a new virtual containerAdapter wrapping the given containerAdapter.
	 *
	 * @param wrappedContainer
	 *            Container to wrap
	 */
	public WrappingVirtualContainer(IContainer wrappedContainer) {
		super(wrappedContainer.getName());
		this.containerAdapter = Platform.getAdapterManager().getAdapter(wrappedContainer, IWorkbenchAdapter.class);
		this.container = wrappedContainer;
	}

	@Override
	public Object[] getChildren(Object o) {
		ArrayList<Object> children = new ArrayList<>(this.virtualChildren);
		Object[] wrappedChildren = this.containerAdapter.getChildren(this.getWrappedContainer());

		// Ensure not to add real elements that are shadowed by virtual containers
		for (int i = 0; i < wrappedChildren.length; i++) {
			Object wrappedChild = wrappedChildren[i];
			boolean isShadowed = false;
			for (VirtualResource virtualChild : virtualChildren) {
				if ((virtualChild instanceof WrappingVirtualContainer) && wrappedChild instanceof IResource) {
					if (((WrappingVirtualContainer) virtualChild)
							.shadows(wrappedChild)) {
						isShadowed = true;
					}
				}
			}
			if (!isShadowed) {
				children.add(wrappedChild);
			}
		}

		return children.toArray();
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object object) {
		return containerAdapter.getImageDescriptor(this.container);
	}

	@Override
	public String getLabel(Object o) {
		return containerAdapter.getLabel(this.container);
	}

	/**
	 * @param o
	 *            The object to test shadowing for
	 * @return True if this container shadows the given object. This means that it replaces it in the tree structure.
	 */
	public boolean shadows(Object o) {
		if (o instanceof IResource) {
			return ((IResource) o).getFullPath().equals(this.container.getFullPath());
		}
		return o == this.container;
	}

	/**
	 * @return The wrapped container
	 */
	public IContainer getWrappedContainer() {
		return container;
	}

	// Disable name setter as the underlying resource should not be changed
	@Override
	public void setName(String name) {
		throw new UnsupportedOperationException("Name change of a wrapping virtual container is not allowed");
	}

}
