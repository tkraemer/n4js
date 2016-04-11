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
package eu.numberfour.n4js.ui.preferences;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ProgressMonitorWrapper;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;

import eu.numberfour.n4js.utils.Cancelable;

/**
 * Progress monitor dialog implementation which cancel button state can be programmatically mutated.
 */
/* default */class MutableProgressMonitorDialog extends ProgressMonitorDialog {

	/**
	 * Creates a new dialog instance with the parent shell.
	 *
	 * @param parent
	 *            the parent of the dialog.
	 */
	public MutableProgressMonitorDialog(final Shell parent) {
		super(parent);
	}

	@Override
	public void run(final boolean fork, final boolean cancelable, final IRunnableWithProgress runnable)
			throws InvocationTargetException, InterruptedException {

		super.run(fork, cancelable,
				monitor -> runnable.run(new CancelableProgressMonitor(monitor, MutableProgressMonitorDialog.this)));
	}

	/**
	 * Cancelable progress monitor that mutates the state of the cancel button in the container
	 * {@link ProgressMonitorDialog progress monitor dialog}.
	 */
	private static final class CancelableProgressMonitor extends ProgressMonitorWrapper implements Cancelable {

		private final MutableProgressMonitorDialog dialog;

		private CancelableProgressMonitor(final IProgressMonitor monitor, final MutableProgressMonitorDialog dialog) {
			super(monitor);
			this.dialog = dialog;
		}

		@Override
		public boolean isCancelable() {
			return dialog.enableCancelButton;
		}

		@Override
		public void setCancelable(final boolean b) {
			dialog.setCancelable(b);
		}

	}

}
