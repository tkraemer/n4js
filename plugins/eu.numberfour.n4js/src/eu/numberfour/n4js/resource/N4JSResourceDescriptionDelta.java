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
package eu.numberfour.n4js.resource;

import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.eclipse.xtext.util.Arrays;

/**
 * Customized resource description delta that always ignores the {@link UserdataMapper#USERDATA_KEY_SERIALIZED_SCRIPT
 * serialized TModule} on the {@link IEObjectDescription} when checking whether the current delta represents a change or
 * not.
 */
public class N4JSResourceDescriptionDelta extends DefaultResourceDescriptionDelta {

	/**
	 * Creates a new resource description delta with the old and the new states.
	 *
	 * @param old
	 *            the old state of resource description.
	 * @param _new
	 *            the new state of the resource description.
	 */
	public N4JSResourceDescriptionDelta(final IResourceDescription old, final IResourceDescription _new) {
		super(old, _new);
	}

	@Override
	protected boolean equals(final IEObjectDescription oldObj, final IEObjectDescription newObj) {

		if (oldObj == newObj) {
			return true;
		}
		if (oldObj.getEClass() != newObj.getEClass()) {
			return false;
		}
		if (oldObj.getName() != null && !oldObj.getName().equals(newObj.getName())) {
			return false;
		}
		if (!oldObj.getEObjectURI().equals(newObj.getEObjectURI())) {
			return false;
		}

		final String[] oldKeys = oldObj.getUserDataKeys();
		final String[] newKeys = newObj.getUserDataKeys();

		for (final String key : oldKeys) {

			if (UserdataMapper.USERDATA_KEY_SERIALIZED_SCRIPT.equals(key)) {
				continue;
			}

			if (!Arrays.contains(newKeys, key)) {
				return false;
			}

			final String oldValue = oldObj.getUserData(key);
			final String newValue = newObj.getUserData(key);
			if (oldValue == null) {
				if (newValue != null) {
					return false;
				}
			} else if (!oldValue.equals(newValue)) {
				return false;
			}

		}
		return true;
	}

}
