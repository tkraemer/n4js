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
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.ui.ISources;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.ui.utils.HandlerServiceUtils;

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
				OpenTypeSelectionDialog typeSelectionDialog = provider.get();
				typeSelectionDialog.setInitialPattern(computeInitialPattern());
				typeSelectionDialog.open();
			} finally {
				TYPE_SEARCH_IN_USE.set(false);
			}
		}

		return null;
	}

	/**
	 * Computes and returns the initial pattern for the type search dialog.
	 *
	 * If no initial pattern can be computed, an empty string is returned.
	 */
	private String computeInitialPattern() {
		IEvaluationContext currentWorkbenchState = HandlerServiceUtils.getCurrentWorkbenchState().orNull();

		if (null != currentWorkbenchState) {
			Object activeCurrentSelection = currentWorkbenchState.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME);

			if (activeCurrentSelection instanceof TextSelection) {
				String text = ((TextSelection) activeCurrentSelection).getText();
				if (null != text && text.length() > 0) {
					return text;
				}
			}
		}
		return "";
	}

}
