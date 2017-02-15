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
package eu.numberfour.n4js.ui.organize.imports;

import static org.eclipse.core.resources.ResourcesPlugin.getWorkspace;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;

import eu.numberfour.n4js.fileextensions.FileExtensionType;
import eu.numberfour.n4js.fileextensions.FileExtensionsRegistry;
import eu.numberfour.n4js.utils.CallTraceUtil;
import eu.numberfour.n4js.utils.languages.N4LanguageUtils;

/**
 * Handler used for two cases: Mass updates on files/folders in selection or organizing the current N4JS Editor.
 */
public class N4JSOrganizeImportsHandler extends AbstractHandler {

	private static final Logger LOGGER = Logger.getLogger(N4JSOrganizeImportsHandler.class);

	@Inject
	private FileExtensionsRegistry fileExtensionsRegistry;

	@Inject
	private CallTraceUtil sysTraceUtil;

	// cleaned version of extensions got from fileExtensions
	private Collection<String> n4FileExtensions;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("\n");
		sysTraceUtil.printFullCallTrace();

		Collection<?> callingMenus = HandlerUtil.getActiveMenus(event);
		// "#TextEditorContext" is the defined plugin.xml
		boolean fromTextContext = (callingMenus != null && callingMenus.contains("#TextEditorContext"));
		boolean fromShortCut = (callingMenus == null || callingMenus.isEmpty());

		XtextEditor editor = EditorUtils.getActiveXtextEditor(event);
		boolean haveActiveEditor = editor != null;

		ISelection selection = HandlerUtil.getCurrentSelection(event);
		boolean nonEmptyStructuredSelection = (selection != null && selection instanceof IStructuredSelection
				&& !selection.isEmpty());

		if (haveActiveEditor && (fromTextContext || fromShortCut)) {
			organizeImportsInEditor(editor, Interaction.queryUser);
		} else if (nonEmptyStructuredSelection) {
			// probably called on a tree-selection in the package-manager or whatever view shows the project-structure:
			// organize files and folders:
			// for each selection entry collect files:
			Multimap<IProject, IFile> projectFiles = collectFiles((IStructuredSelection) selection);

			HashSet<IFile> filesInSet = new HashSet<>(projectFiles.values());
			List<IFile> filesAsList = new ArrayList<>(filesInSet);

			if (filesAsList.isEmpty()) {
				return null;
			}

			final boolean wasAutobuilding = getWorkspaceAutobuild();

			try {
				// avoid auto-build when modifying batch of documents
				setAutobuild(false);

				// Query unsaved
				IWorkbench wbench = PlatformUI.getWorkbench();
				IWorkbenchWindow activeWorkbenchWindow = wbench.getActiveWorkbenchWindow();
				boolean allSaved = wbench.saveAll(activeWorkbenchWindow, activeWorkbenchWindow, null, true);
				if (!allSaved) {
					return null;
				}
				final Shell shell = HandlerUtil.getActiveShell(event);

				IRunnableWithProgress op = new IRunnableWithProgress() {
					@Override
					public void run(IProgressMonitor mon) throws InvocationTargetException, InterruptedException {
						int totalWork = filesAsList.size();
						SubMonitor subMon = SubMonitor.convert(mon, "Organize imports.", totalWork);
						for (int i = 0; !subMon.isCanceled() && i < filesAsList.size(); i++) {
							IFile currentFile = filesAsList.get(i);
							subMon.setTaskName("Organize imports." + " - File (" + (i + 1) + " of " + totalWork + ")");
							try {
								organizeImportsInFile(subMon, currentFile, Interaction.breakBuild);

							} catch (CoreException | RuntimeException e) {
								String msg = "Exception in file " + currentFile.getFullPath().toString() + ".";
								LOGGER.error(msg, e);
								if (errorDialogWithStackTrace(msg + " Hit OK to continue.", e)) {
									// - logged anyway
								} else {
									throw new InvocationTargetException(e);
								}
							}
						}
						if (subMon.isCanceled()) {
							throw new InterruptedException();
						}
					}

				};

				new ProgressMonitorDialog(shell).run(true, true, op);

			} catch (InvocationTargetException e) {
				throw new ExecutionException("Error during organizing imports", e);
			} catch (InterruptedException e) {
				// user cancelled, ok
			} finally {
				// restore state of auto-build
				setAutobuild(wasAutobuilding);
			}

		}
		return null;
	}

	/**
	 * Sets workspace auto-build according to the provided flag. Thrown exceptions are handled by logging.
	 *
	 */
	private static void setAutobuild(boolean on) {
		if (on)
			try {
				final IWorkspace workspace = getWorkspace();
				final IWorkspaceDescription description = workspace.getDescription();
				description.setAutoBuilding(on);
				workspace.setDescription(description);
			} catch (CoreException e) {
				LOGGER.debug("Organize imports cannot set auto build to " + on + ".");
			}
	}

	/** returns current setting of workspace auto-build */
	private static boolean getWorkspaceAutobuild() {
		return getWorkspace().getDescription().isAutoBuilding();
	}

	/**
	 */
	public void organizeImportsInFile(SubMonitor subMon, IFile currentFile, final Interaction interaction)
			throws CoreException {
		SubMonitor subSubMon = subMon.split(1, SubMonitor.SUPPRESS_NONE);
		subSubMon.setTaskName(currentFile.getName());
		N4JSOrganizeImportsHelper organizeImportsHelper = getOrganizeImports(currentFile);
		organizeImportsHelper.doOrganizeImports(currentFile, interaction, subSubMon);
	}

	public void organizeImportsInFile(IProgressMonitor mon, IFile currentFile, final Interaction interaction)
			throws CoreException {
		N4JSOrganizeImportsHelper organizeImportsHelper = getOrganizeImports(currentFile);
		organizeImportsHelper.doOrganizeImports(currentFile, interaction, mon);
	}

	public void organizeImportsInEditor(XtextEditor editor, final Interaction interaction) {
		try {
			IResource resource = editor.getResource();
			N4JSOrganizeImportsHelper organizeImportsHelper = getOrganizeImports(resource);
			organizeImportsHelper.organizeEditor(editor, interaction);
		} catch (RuntimeException re) {
			if (re.getCause() instanceof BreakException) {
				LOGGER.debug("user canceled");
			} else {
				LOGGER.warn("Unrecognized RT-exception", re);
			}

		}
	}

	private N4JSOrganizeImportsHelper getOrganizeImports(IFile ifile) {
		return N4LanguageUtils.getServiceForContext(ifile, N4JSOrganizeImportsHelper.class).get();
	}

	private N4JSOrganizeImportsHelper getOrganizeImports(IResource iresource) {
		return N4LanguageUtils.getServiceForContext(iresource, N4JSOrganizeImportsHelper.class).get();
	}

	private Multimap<IProject, IFile> collectFiles(IStructuredSelection structuredSelection) {
		Multimap<IProject, IFile> result = HashMultimap.create();
		for (Object object : structuredSelection.toList()) {
			collectRelevantFiles(object, result);
		}
		return result;
	}

	private void collectRelevantFiles(Object element, Multimap<IProject, IFile> result) {
		if (element instanceof IWorkingSet) {
			IWorkingSet workingSet = (IWorkingSet) element;
			IAdaptable[] elements = workingSet.getElements();
			for (int j = 0; j < elements.length; j++) {
				collectRelevantFiles(elements[j], result);
			}
		} else if (element instanceof IContainer) {
			IContainer container = (IContainer) element;
			try {
				for (IResource child : container.members(IContainer.EXCLUDE_DERIVED)) {
					collectRelevantFiles(child, result);
				}
			} catch (CoreException c) {
				LOGGER.warn("Error while collecting files", c);
			}
		} else if (element instanceof IFile) {
			collectIFiles(result, new Object[] { element });
		}
	}

	private void collectIFiles(Multimap<IProject, IFile> result, Object[] nonJavaResources) {
		for (Object object : nonJavaResources) {
			if (object instanceof IFile) {
				IFile iFile = (IFile) object;
				if (shouldHandleFile(iFile))
					result.put(iFile.getProject(), iFile);
			}
		}
	}

	/**
	 * Checking the file type by getting the known extensions from the FileExtensionProvider
	 *
	 * @param object
	 *            file to judge
	 * @return true if the file is a valid file for organize import
	 */
	private boolean shouldHandleFile(IFile object) {
		String fileExtension = object.getFileExtension();
		return fileExtension != null && getN4FileExtensions().contains(fileExtension);
	}

	/**
	 * Access with lazy init to the desired file extensions to organize.
	 *
	 * @return Set of extensions for files on which organization should be applied
	 */
	private Collection<String> getN4FileExtensions() {
		if (n4FileExtensions == null) {
			n4FileExtensions = new HashSet<>(
					fileExtensionsRegistry.getFileExtensions(FileExtensionType.TYPABLE_FILE_EXTENSION));
			n4FileExtensions.removeAll(fileExtensionsRegistry.getFileExtensions(FileExtensionType.RAW_FILE_EXTENSION));

			// TODO IDE-2520 enable for N4JSX
			//
			// Once we enable Organize Imports with IDE-2520 filtering below should be removed. Populating list as above
			// will be enough.
			// n4FileExtensions.remove(N4JSGlobals.N4JSX_FILE_EXTENSION);
		}
		return n4FileExtensions;
	}

	/**
	 * Shows JFace ErrorDialog but improved by constructing full stack trace in detail area.
	 *
	 * @return true if OK was pressed
	 */
	private static boolean errorDialogWithStackTrace(String msg, Throwable t) {

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);

		final String trace = sw.toString(); // stack trace as a string

		// Temporary holder of child statuses
		List<Status> childStatuses = new ArrayList<>();

		// Split output by OS-independent new-line
		for (String line : trace.split(System.getProperty("line.separator"))) {
			// build & add status
			childStatuses.add(new Status(IStatus.ERROR, "N4js-plugin-id", line));
		}

		MultiStatus ms = new MultiStatus("N4js-plugin-id", IStatus.ERROR,
				childStatuses.toArray(new Status[] {}), // convert to array of statuses
				t.getLocalizedMessage(), t);

		final AtomicBoolean result = new AtomicBoolean(true);
		Display.getDefault()
				.syncExec(
						() -> result.set(
								ErrorDialog.openError(null, "Error occurred while organizing ", msg, ms) == Window.OK));

		return result.get();
	}
}
