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
package eu.numberfour.n4js.ui.textzoom;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.FontData;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * Handler for zooming in and out in the text editor, that is resizing the font size via hotkey (by default Ctrl/M1+
 * +/-).
 */
public class TextZoomHandler extends AbstractHandler {

	final static String QUALIFIER = "org.eclipse.ui.workbench";
	final static String ZOOM_IN_ID = "eu.numberfour.n4js.ui.TextZoomIn";
	final static String ZOOM_OUT_ID = "eu.numberfour.n4js.ui.TextZoomOut";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		boolean zoomIn = ZOOM_IN_ID.equals(event.getCommand().getId());
		int delta = zoomIn ? +1 : -1;

		IPreferencesService preferencesService = Platform.getPreferencesService();
		String value = preferencesService.getString(QUALIFIER, JFaceResources.TEXT_FONT, null, null);
		FontData fontData[] = PreferenceConverter.basicGetFontData(value);
		FontData fontdata = fontData[0];
		fontdata.setHeight(fontdata.getHeight() + delta);
		Preferences preferences = preferencesService.getRootNode().node("/instance/" + QUALIFIER);
		preferences.put(JFaceResources.TEXT_FONT, fontdata.toString());
		try {
			preferences.flush();
		} catch (BackingStoreException e) {
			// ignore
		}

		return null;
	}

}
