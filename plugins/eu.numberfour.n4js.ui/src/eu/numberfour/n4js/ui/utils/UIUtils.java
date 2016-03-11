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
package eu.numberfour.n4js.ui.utils;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

/**
 * Collection of convenient SWT and JFace utility methods.
 */
public abstract class UIUtils {

	/**
	 * Disposes the resource argument. Has no effect if the resource argument is either {@code null} or already
	 * {@link Resource#isDisposed() disposed}.
	 *
	 * @param resource
	 *            the resource to dispose. Optional, can be {@code null}.
	 */
	public static void dispose(Resource resource) {
		if (null != resource && !resource.isDisposed()) {
			resource.dispose();
		}
	}

	/**
	 * Disposes the widget argument. Has no effect if the widget argument is either {@code null} or already
	 * {@link Widget#isDisposed() disposed}.
	 *
	 * @param widget
	 *            the widget to dispose. Optional, can be {@code null}.
	 */
	public static void dispose(Widget widget) {
		if (null != widget && !widget.isDisposed()) {
			widget.dispose();
		}
	}

	/**
	 * Returns with the display. If the current display is not available, returns with the default one.
	 *
	 * @return the display between SWT and the OS.
	 */
	public static Display getDisplay() {
		return null == Display.getCurrent()
				? Display.getDefault()
				: Display.getCurrent();
	}

	/**
	 * Returns with the active {@link Shell shell} from the {@link #getDisplay() display}. May return with {@code null}
	 * if called from non-UI thread.
	 *
	 * @return the active shell, or {@code null} if invoked from non-UI thread.
	 */
	public static Shell getShell() {
		final Display display = getDisplay();
		return null == display ? null : display.getActiveShell();
	}

	/**
	 * Returns the matching standard color for the given constant, which should be one of the color constants specified
	 * in class SWT. Any value other than one of the SWT color constants which is passed in will result in the color
	 * black. This color must not be disposed/released because it was allocated by the system, not the application.
	 *
	 * @param swtColorConstantId
	 *            the SWT color constant.
	 * @return the system color for the given color constant ID or black.
	 */
	public static Color getSystemColor(final int swtColorConstantId) {
		return getDisplay().getSystemColor(swtColorConstantId);
	}

	private UIUtils() {
		//
	}

}
