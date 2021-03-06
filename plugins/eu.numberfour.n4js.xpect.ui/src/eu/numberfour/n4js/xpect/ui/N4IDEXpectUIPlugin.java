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
package eu.numberfour.n4js.xpect.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class N4IDEXpectUIPlugin extends AbstractUIPlugin {

	// The plug-in ID
	@SuppressWarnings("javadoc")
	public static final String PLUGIN_ID = "eu.numberfour.n4js.xpect.ui"; //$NON-NLS-1$

	// The shared instance
	private static N4IDEXpectUIPlugin plugin;

	/**
	 * The constructor
	 */
	public N4IDEXpectUIPlugin() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static N4IDEXpectUIPlugin getDefault() {
		return plugin;
	}

	/**
	 * Prints given message to eclipse error log
	 *
	 * @param msg
	 *            the message
	 * @param ex
	 *            the exception, may be null
	 */
	public static void logError(String msg, Throwable ex) {
		getDefault().getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, IStatus.OK, msg, ex));
	}
}
