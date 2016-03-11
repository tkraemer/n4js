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
import java.util.ListIterator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * A virtual resource is used to overlay non existent virtual resources in a workspace structure. This can be used to
 * reflect changes to a file system view without applying them.
 */
public class VirtualResource implements IWorkbenchAdapter {

	private final ImageDescriptor imageDescriptor;
	private String name;

	/** Default icon for workspace file resources */
	public static final ImageDescriptor RESOURCE_ICON = PlatformUI.getWorkbench().getSharedImages()
			.getImageDescriptor(ISharedImages.IMG_OBJ_FILE);

	private VirtualContainer parent = null;

	/**
	 * Creates a new virtual resource to insert into a workbench tree.
	 *
	 * @param name
	 *            The name of the virtual resource. Displayed as label.
	 * @param imageDescriptor
	 *            The image for the tree view.
	 */
	public VirtualResource(String name, ImageDescriptor imageDescriptor) {
		this.name = name;
		this.imageDescriptor = imageDescriptor;
	}

	@Override
	public Object[] getChildren(Object o) {
		return new Object[] {};
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object object) {
		return this.imageDescriptor;
	}

	@Override
	public String getLabel(Object o) {
		return this.name;
	}

	@Override
	public Object getParent(Object o) {
		return this.parent;
	}

	/**
	 * @param parent
	 *            The parent virtual container.
	 */
	public void setParent(VirtualContainer parent) {
		this.parent = parent;
	}

	/**
	 * @return The name of the resource
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return The path of the resource
	 */
	public IPath getFullPath() {
		IPath path = new Path("");
		ArrayList<String> pathSegments = new ArrayList<>();

		Object currentResource = this;

		while (currentResource != null && currentResource instanceof VirtualResource
				&& !(currentResource instanceof WrappingVirtualContainer)) {
			pathSegments.add(((VirtualResource) currentResource).getName());
			currentResource = ((VirtualResource) currentResource).getParent(null);
		}

		// If the loop stopped with a real resource
		if (currentResource instanceof IResource) {
			path = ((IResource) currentResource).getFullPath();
		} else if (currentResource instanceof WrappingVirtualContainer) {
			path = ((WrappingVirtualContainer) currentResource).getWrappedContainer().getFullPath();
		}
		ListIterator<String> segmentIterator = pathSegments.listIterator(pathSegments.size());
		while (segmentIterator.hasPrevious()) {
			path = path.append(new Path(segmentIterator.previous()));
		}

		return path;
	}

	/**
	 * Inserts a new virtual resource into an existing file tree. Replaces all real resources occurring above the
	 * container object with virtual containers.
	 *
	 * @param newResource
	 *            The new virtual resource
	 * @param container
	 *            The container object under which the resource should be inserted.
	 *
	 * @return The newly created resource
	 */
	public static VirtualResource insertAt(VirtualResource newResource, VirtualResource container) {

		VirtualResource containerResource = (container);
		if (!(container instanceof VirtualContainer)) {
			return insertAtResource(newResource, container);
		} else {
			VirtualContainer containerContainer = ((VirtualContainer) containerResource);

			containerContainer.addChild(newResource);
			return newResource;
		}
	}

	/**
	 * Inserts a new virtual resource into an existing file tree. Replaces all real resources occurring above the
	 * container object with virtual containers.
	 *
	 * @param newResource
	 *            The new virtual resource
	 * @param container
	 *            The container object under which the resource should be inserted.
	 *
	 * @return The newly created resource
	 */
	public static VirtualResource insertAt(VirtualResource newResource, IContainer container,
			VirtualContainer root) {
		IPath virtualPath = container.getFullPath();
		VirtualContainer currentContainer = root;

		virtualPath = virtualPath.makeRelativeTo(root.getFullPath());

		for (String segment : virtualPath.segments()) {
			Object resource = VirtualContainer.findContainedResource(currentContainer, segment);

			if (resource == null) {
				return null;
			} else if (resource instanceof VirtualContainer) {
				currentContainer = (VirtualContainer) resource;
			} else if (resource instanceof IContainer) {
				VirtualContainer wrap = new WrappingVirtualContainer((IContainer) resource);
				currentContainer.addChild(wrap);
				currentContainer = wrap;
			} else {
				return null;
			}
		}

		currentContainer.addChild(newResource);

		return newResource;
	}

	private static VirtualResource insertAtResource(VirtualResource resource, VirtualResource entryResource) {
		VirtualContainer containerParent = (VirtualContainer) entryResource.getParent(null);
		containerParent.removeChild(entryResource);

		VirtualContainer containerContainer = new VirtualContainer(entryResource.getName());
		containerParent.addChild(containerContainer);
		containerContainer.addChild(resource);
		return resource;
	}

	/**
	 * Set the name of this virtual resource.
	 *
	 * @param name
	 *            The new resource name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
