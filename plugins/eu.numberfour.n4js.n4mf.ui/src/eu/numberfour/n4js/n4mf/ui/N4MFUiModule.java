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
package eu.numberfour.n4js.n4mf.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.DirtyStateEditorSupport;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.shared.Access;

import com.google.inject.Provider;

import eu.numberfour.n4js.n4mf.ui.editor.hyperlinking.N4MFHyperlinker;
import eu.numberfour.n4js.n4mf.ui.internal.N4MFDirtyStateEditorSupport;
import eu.numberfour.n4js.n4mf.ui.wizard.N4JSProjectCreator;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.ui.projectModel.IN4JSEclipseCore;

/**
 * Use this class to register components to be used within the IDE.
 */
public class N4MFUiModule extends eu.numberfour.n4js.n4mf.ui.AbstractN4MFUiModule {
	/**
	 * Create a new UIModule in the given plugin.
	 */
	public N4MFUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	/**
	 * Returns the type of {@link N4JSProjectCreator}.
	 */
	public Class<? extends org.eclipse.xtext.ui.wizard.IProjectCreator> bindIProjectCreator() {
		return N4JSProjectCreator.class;
	}

	/**
	 * Returns the type of {@link N4MFHyperlinker}, supports hyperlinking in manifest editor.
	 */
	public Class<? extends IHyperlinkHelper> bindIHyperlinkHelper() {
		return N4MFHyperlinker.class;
	}

	/**
	 * Returns with the type of {@link N4MFDirtyStateEditorSupport}, support interactive validation in manifest editors.
	 */
	public Class<? extends DirtyStateEditorSupport> bindDirtyStateEditorSupport() {
		return N4MFDirtyStateEditorSupport.class;
	}

	/**
	 * Bind custom XtextEditor.
	 */
	public Class<? extends XtextEditor> bindXtextEditor() {
		return N4MFEditor.class;
	}

	/**
	 * Configure the IN4JSCore instance to use the implementation that is backed by the Eclipse workspace.
	 */
	public Class<? extends IN4JSCore> bindIN4JSCore() {
		return IN4JSEclipseCore.class;
	}

	/**
	 * Configure the IN4JSCore instance to use the implementation that is backed by the Eclipse workspace.
	 */
	public Provider<IN4JSEclipseCore> provideIN4JSEclipseCore() {
		return Access.contributedProvider(IN4JSEclipseCore.class);
	}

}
