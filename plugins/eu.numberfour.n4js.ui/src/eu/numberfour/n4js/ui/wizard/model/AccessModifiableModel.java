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
package eu.numberfour.n4js.ui.wizard.model;

/**
 * A wizard model containing access modifier information
 *
 * @author luca.beurer-kellner - Initial contribution and API
 */
public interface AccessModifiableModel extends PropertyChangeListenable {
	/**
	 * Helper type for access modifiers
	 */
	public static enum AccessModifier {
		/** For public visibility */
		PUBLIC,
		/** For project visibility */
		PROJECT,
		/** For private visibility */
		PRIVATE
	}

	/** The property name for the access modifier property when using databinding */
	public static final String ACCESS_MODIFIER_PROPERTY = "accessModifier";
	/** The property name for the internal property when using databinding */
	public static final String INTERNAL_PROPERTY = "internal";

	/**
	 * The saved access modifier
	 */
	public AccessModifier getAccessModifier();

	/**
	 * @param accessModifier
	 *            The new access modifier
	 */
	public void setAccessModifier(AccessModifier accessModifier);

	/**
	 * The state of the internal property
	 */
	public boolean isInternal();

	/**
	 * @param internal
	 *            The new internal property state
	 */
	public void setInternal(boolean internal);
}
