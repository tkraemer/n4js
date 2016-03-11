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
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Represents a virtual resource container.
 */
public class VirtualContainer extends VirtualResource {

	/**
	 * Default folder icon of the eclipse environment
	 */
	public static ImageDescriptor FolderIcon = PlatformUI.getWorkbench().getSharedImages()
			.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER);

	/**
	 * Holds the virtual children of this container
	 */
	protected final ArrayList<VirtualResource> virtualChildren = new ArrayList<>();

	/**
	 * @param name
	 *            The name of the virtual container
	 */
	public VirtualContainer(String name) {
		super(name, FolderIcon);
	}

	/**
	 * @param resource
	 *            The new virtual child to add
	 */
	public void addChild(VirtualResource resource) {
		this.virtualChildren.add(resource);
		resource.setParent(this);
	}

	/**
	 * @param resource
	 *            The virtual child to remove
	 */
	public void removeChild(VirtualResource resource) {
		this.virtualChildren.remove(resource);
	}

	@Override
	public Object[] getChildren(Object o) {
		return this.virtualChildren.toArray();
	}

	/**
	 * @return The virtual children of this container
	 */
	public List<VirtualResource> getVirtualChildren() {
		return new ArrayList<>(this.virtualChildren);
	}

	/**
	 * Removes all virtual children. Reveals shadowed real children.
	 */
	public void clearVirtualChildren() {
		for (VirtualResource res : this.getVirtualChildren()) {
			this.removeChild(res);
		}
	}

	/**
	 * Find and return the resource with the given name in the virtual container.
	 *
	 * May be a virtual resource or a real file system resource.
	 *
	 * @param container
	 *            The container to search in
	 * @param resourceName
	 *            The resource name to search for
	 * @return The resource. May be {@link IResource} or {@link VirtualContainer}
	 */
	protected static Object findContainedResource(VirtualContainer container, String resourceName) {
		for (VirtualResource resource : container.getVirtualChildren()) {
			if (resource.getName().equals(resourceName)) {
				return resource;
			}
		}

		if (container instanceof WrappingVirtualContainer) {
			IFolder folder = ((WrappingVirtualContainer) container).getWrappedContainer()
					.getFolder(new Path("/" + resourceName));
			return folder;
		}
		return null;

	}

}
