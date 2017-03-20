/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.ui.preferences.external;

import static eu.numberfour.n4js.ui.utils.UIUtils.getDisplay;
import static org.eclipse.jface.dialogs.MessageDialog.openError;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.xtext.xbase.lib.StringExtensions;

import eu.numberfour.n4js.ui.internal.N4JSActivator;
import eu.numberfour.n4js.ui.utils.UIUtils;
import eu.numberfour.n4js.utils.StatusHelper;

/**
 * Button selection listener for opening up an {@link InputDialog input dialog}, where user can specify npm package name
 * that will be downloaded and installed to the external libraries.
 *
 * Note: this class is not static, so it will hold reference to all services. Make sure to dispose it.
 *
 */
public class InstallNpmDependencyButtonListener extends SelectionAdapter {

	final private Supplier<IInputValidator> validator;
	final private StatusHelper statusHelper;
	final private BiFunction<Collection<String>, IProgressMonitor, IStatus> installAction;
	final private String initalValue;

	InstallNpmDependencyButtonListener(BiFunction<Collection<String>, IProgressMonitor, IStatus> installAction,
			Supplier<IInputValidator> validator,
			StatusHelper statusHelper, String initalValue) {
		this.installAction = installAction;
		this.validator = validator;
		this.statusHelper = statusHelper;
		this.initalValue = initalValue;
	}

	@Override
	public void widgetSelected(final SelectionEvent e) {
		final MultiStatus multistatus = statusHelper.createMultiStatus("Status of installing npm dependencies.");
		final InputDialog dialog = new InputDialog(UIUtils.getShell(), "npm Install",
				"Specify an npm package name to download and install:", initalValue, validator.get());

		dialog.open();
		final String packageName = dialog.getValue();
		if (!StringExtensions.isNullOrEmpty(packageName) && dialog.getReturnCode() == Window.OK) {
			try {
				new ProgressMonitorDialog(UIUtils.getShell()).run(true, false, monitor -> {
					multistatus.merge(installAction.apply(Arrays.asList(packageName), monitor));
				});
			} catch (final InvocationTargetException | InterruptedException exc) {
				multistatus.merge(
						statusHelper.createError("Error while installing npm dependency: '" + packageName + "'.",
								exc));
			} finally {
				if (!multistatus.isOK()) {
					N4JSActivator.getInstance().getLog().log(multistatus);
					getDisplay().asyncExec(() -> openError(
							UIUtils.getShell(),
							"npm Install Failed",
							"Error while installing '" + packageName
									+ "' npm package.\nPlease check your Error Log view for the detailed npm log about the failure."));
				}
			}
		}
	}
}