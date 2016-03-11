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
package eu.numberfour.n4js.ui;

import org.eclipse.ui.IStartup;

import com.google.inject.Inject;

import eu.numberfour.n4js.external.ExternalLibraryWorkspace;
import eu.numberfour.n4js.preferences.ExternalLibraryPreferenceStore;

/**
 * N4JS IDE startup hook to populate the state of the N4JS external library workspace based on the
 * {@link ExternalLibraryPreferenceStore external library preference store}.
 */
public class N4JSExternalLibraryStartup implements IStartup {

	@Inject
	private ExternalLibraryWorkspace externalLibraryWorkspace;

	@Override
	public void earlyStartup() {
		externalLibraryWorkspace.updateState();
	}

}
