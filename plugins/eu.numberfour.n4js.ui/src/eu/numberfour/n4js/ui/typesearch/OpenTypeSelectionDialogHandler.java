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
package eu.numberfour.n4js.ui.typesearch;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Handler for opening the N4JS type selection dialog.
 */
public class OpenTypeSelectionDialogHandler extends AbstractHandler {

	private static final AtomicBoolean TYPE_SEARCH_IN_USE = new AtomicBoolean();

	@Inject
	private Provider<OpenTypeSelectionDialog> provider;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (TYPE_SEARCH_IN_USE.compareAndSet(false, true)) {
			try {
				provider.get().open();
			} finally {
				TYPE_SEARCH_IN_USE.set(false);
			}
		}

		return null;
	}

}
