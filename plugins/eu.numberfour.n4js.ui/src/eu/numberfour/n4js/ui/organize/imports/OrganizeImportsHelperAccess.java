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
package eu.numberfour.n4js.ui.organize.imports;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.xtext.ui.editor.XtextEditor;

import eu.numberfour.n4js.utils.languages.N4LanguageUtils;

/**
 * Utility for proper access to the organize imports helper. Since helper needs be injected in with injector for the
 * language of the file that it will be processing, it cannot be injected into the caller up front. Instead callers
 * should call this class that will perform lookup of the proper injector and use it to create helper on the fly.
 */
public class OrganizeImportsHelperAccess {

	private static final Logger LOGGER = Logger.getLogger(OrganizeImportsHelperAccess.class);

	/** Organize provided file. */
	public static void organizeImportsInFile(SubMonitor subMon, IFile currentFile, final Interaction interaction)
			throws CoreException {
		SubMonitor subSubMon = subMon.split(1, SubMonitor.SUPPRESS_NONE);
		subSubMon.setTaskName(currentFile.getName());
		N4JSOrganizeImportsHelper organizeImportsHelper = getOrganizeImports(currentFile);
		organizeImportsHelper.doOrganizeImports(currentFile, interaction, subSubMon);
	}

	// TODO IDE-2520 parameters order
	/** Organize provided file. */
	public static void organizeImportsInFile(IProgressMonitor mon, IFile currentFile, final Interaction interaction)
			throws CoreException {
		N4JSOrganizeImportsHelper organizeImportsHelper = getOrganizeImports(currentFile);
		organizeImportsHelper.doOrganizeImports(currentFile, interaction, mon);
	}

	/** Organize provided editor. */
	public static void organizeImportsInEditor(XtextEditor editor, final Interaction interaction) {
		try {
			IResource resource = editor.getResource();
			N4JSOrganizeImportsHelper organizeImportsHelper = getOrganizeImports(resource);
			organizeImportsHelper.doOrganizeImports(editor.getDocument(), interaction);
		} catch (RuntimeException re) {
			if (re.getCause() instanceof BreakException) {
				LOGGER.debug("user canceled");
			} else {
				LOGGER.warn("Unrecognized RT-exception", re);
			}

		}
	}

	private static N4JSOrganizeImportsHelper getOrganizeImports(IFile ifile) {
		return N4LanguageUtils.getServiceForContext(ifile, N4JSOrganizeImportsHelper.class).get();
	}

	private static N4JSOrganizeImportsHelper getOrganizeImports(IResource iresource) {
		return N4LanguageUtils.getServiceForContext(iresource, N4JSOrganizeImportsHelper.class).get();
	}

}
