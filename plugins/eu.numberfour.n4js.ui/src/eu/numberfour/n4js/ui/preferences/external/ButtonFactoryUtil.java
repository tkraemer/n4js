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

import static org.eclipse.jface.layout.GridDataFactory.fillDefaults;
import static org.eclipse.swt.SWT.CENTER;
import static org.eclipse.swt.SWT.FILL;
import static org.eclipse.swt.SWT.PUSH;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 *
 */
public class ButtonFactoryUtil {

	public static Button createPushButton(final Composite parent, final String text, final SelectionListener listener,
			boolean enabled) {
		final Button button = new Button(parent, PUSH);
		button.setLayoutData(fillDefaults().align(FILL, CENTER).create());
		button.setText(text);
		if (null != listener) {
			button.addSelectionListener(listener);
			button.addDisposeListener(e -> {
				button.removeSelectionListener(listener);
			});
		}
		button.setEnabled(enabled);
		return button;
	}

	public static Button createDisabledPushButton(final Composite parent, final String text,
			final SelectionListener listener) {
		return createPushButton(parent, text, listener, false);
	}

	public static Button createEnabledPushButton(final Composite parent, final String text,
			final SelectionListener listener) {
		return createPushButton(parent, text, listener, true);
	}

}
