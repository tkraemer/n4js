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

import static eu.numberfour.n4js.N4JSGlobals.JS_FILE_EXTENSION;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
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
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.XtextDocumentProvider;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;

import eu.numberfour.n4js.documentation.N4JSDocumentationProvider;
import eu.numberfour.n4js.parser.InternalSemicolonInjectingParser;
import eu.numberfour.n4js.resource.N4JSResource;
import eu.numberfour.n4js.ts.services.TypeExpressionsGrammarAccess;
import eu.numberfour.n4js.ui.changes.ChangeManager;
import eu.numberfour.n4js.ui.changes.ChangeProvider;
import eu.numberfour.n4js.ui.changes.IAtomicChange;
import eu.numberfour.n4js.ui.changes.IChange;
import eu.numberfour.n4js.ui.changes.Replacement;
import eu.numberfour.n4js.utils.UtilN4;

/**
 * Handler used for two cases: Mass updates on files/folders in selection or organizing the current N4JS Editor.
 */
public class N4JSOrganizeImportsHandler extends AbstractHandler {

	private static final Logger LOGGER = Logger.getLogger(N4JSOrganizeImportsHandler.class);

	// list of FileExtensions to exclude:
	private static final List<String> RAW_JS_FILES = ImmutableList.of(JS_FILE_EXTENSION);

	@Inject
	private N4JSOrganizeImports organizeImports;

	@Inject
	private ChangeManager changeManager;

	@Inject
	private XtextDocumentProvider docProvider;

	// IDEBUG-239 we have to limit here to N4JS & N4JSD only. (see above of things to remove.)
	@Inject
	private FileExtensionProvider fileExtensionsProvider;

	// cleaned version of extensions got from fileExtensions
	private Collection<String> n4FileExtensions;

	@Inject
	private TypeExpressionsGrammarAccess typeExpressionsGrammarAccess;

	@Inject
	private N4JSDocumentationProvider n4JSDocumentationProvider;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

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
			organizeEditor(editor);
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

			// Query unsaved
			IWorkbench wbench = PlatformUI.getWorkbench();
			IWorkbenchWindow activeWorkbenchWindow = wbench.getActiveWorkbenchWindow();
			boolean allSaved = wbench.saveAll(activeWorkbenchWindow, activeWorkbenchWindow, null, true);
			if (!allSaved) {
				return null;
			}

			Shell shell = HandlerUtil.getActiveShell(event);

			IRunnableWithProgress op = new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor mon) throws InvocationTargetException, InterruptedException {
					int totalWork = filesAsList.size();
					mon.beginTask("Organize imports.", totalWork);
					for (int i = 0; !mon.isCanceled() && i < filesAsList.size(); i++) {
						IFile currentFile = filesAsList.get(i);
						mon.setTaskName("Organize imports." + " - File (" + (i + 1) + " of " + totalWork + ")");
						try {
							mon.subTask(currentFile.getName());
							doOrganizeImports(currentFile, new SubProgressMonitor(mon, 1));

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
					if (mon.isCanceled()) {
						throw new InterruptedException();
					}
				}
			};

			try {
				new ProgressMonitorDialog(shell).run(true, true, op);
			} catch (InvocationTargetException e) {
				throw new ExecutionException("Error during organizing imports", e);
			} catch (InterruptedException e) {
				// user cancelled, ok
			}

		}

		return null;
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
		// Lazily obtain list of valid extensions:
		if (n4FileExtensions == null) {
			n4FileExtensions = new HashSet<>(fileExtensionsProvider.getFileExtensions());
			n4FileExtensions.removeAll(RAW_JS_FILES);
		}
		return n4FileExtensions;
	}

	private void organizeEditor(XtextEditor editor) {
		try {
			IXtextDocument document = editor.getDocument();
			doOrganizeImports(document, Interaction.queryUser);
		} catch (RuntimeException re) {
			if (re.getCause() instanceof BreakException) {
				LOGGER.debug("user canceled");
			} else {
				LOGGER.warn("Unrecognized RT-exception", re);
			}

		}
	}

	private void doOrganizeImports(IFile file, IProgressMonitor mon) throws CoreException {

		mon.beginTask("Organizing " + file.getName(), IProgressMonitor.UNKNOWN);

		FileEditorInput fei = new FileEditorInput(file);

		docProvider.connect(fei); // without connecting no document will be provided
		IXtextDocument document = (IXtextDocument) docProvider.getDocument(fei);

		docProvider.aboutToChange(fei);

		doOrganizeImports(document, Interaction.breakBuild);

		mon.subTask("Saving " + file.getName());

		docProvider.saveDocument(new SubProgressMonitor(mon, 0), fei, document, true);

		docProvider.changed(fei);
		docProvider.disconnect(fei);

		mon.done();
	}

	/**
	 * Organize the imports in the N4JS document.
	 *
	 * @param document
	 *            N4JS document
	 * @throws RuntimeException
	 *             wrapping a BreakException in case of user-abortion ({@link Interaction#queryUser}) or
	 *             resolution-failure({@link Interaction#breakBuild} )
	 */
	public void doOrganizeImports(final IXtextDocument document, final Interaction interaction) {
		// trigger Linking
		document.readOnly((XtextResource p) -> {
			N4JSResource.postProcess(p);
			return null;
		});

		List<IChange> result = document.readOnly(
				new IUnitOfWork<List<IChange>, XtextResource>() {

					@Override
					public List<IChange> exec(XtextResource xtextResource) throws Exception {
						// Position, length 0
						InsertionPoint insertionPoint = organizeImports.getImportRegion(xtextResource);

						if (insertionPoint.offset != -1) {
							List<IChange> changes = new ArrayList<>();
							try {
								final String NL = ChangeProvider.lineDelimiter(document,
										insertionPoint.offset);

								final String organizedImportSection = organizeImports
										.getOrganizedImportSection(xtextResource, NL, interaction);
								if (organizedImportSection != null) { // remove old imports
									changes.addAll(organizeImports.getCleanupChanges(xtextResource, document));
									if (!organizedImportSection.isEmpty()) {
										// advance ImportRegion-offset if not nil:
										int offset = insertionPoint.offset;
										if (offset != 0) {
											offset += NL.length();
										}
										// if the line above is part of a ML-comment, then line-break:
										IRegion lineRegion = document.getLineInformationOfOffset(offset);

										ILeafNode leafNodeAtBeginOfLine = NodeModelUtils.findLeafNodeAtOffset(
												xtextResource.getParseResult().getRootNode(), lineRegion.getOffset());

										if (leafNodeAtBeginOfLine.getGrammarElement() == typeExpressionsGrammarAccess
												.getML_COMMENTRule() // plain ML
										) {

											int insertOffset = insertionPoint.offset;
											// it is ML, so we need to insert a line-break;
											String finalText = NL + organizedImportSection + NL;
											changes.add(new Replacement(xtextResource.getURI().trimFragment(),
													insertOffset, 0, finalText));

										} else if (UtilN4.isIgnoredSyntaxErrorNode(leafNodeAtBeginOfLine,
												InternalSemicolonInjectingParser.SEMICOLON_INSERTED)
												// ASI overlapping something
												&& (leafNodeAtBeginOfLine.getTotalOffset() < lineRegion.getOffset()
										// this ASI something starts before the beginning of the line
										)) {
											int insertOffset = insertionPoint.offset; // concrete
																						// position

											if ((!insertionPoint.isBeforeDocumentation) &&
											// if this was an ASI case shadowing a jsdoc-/**-style comment
											// we should insert before this comment. Still need to double-check the
											// concrete content:
													n4JSDocumentationProvider.isDocumentationStyle(
															NodeModelUtils.getTokenText(leafNodeAtBeginOfLine))) {
												// it's an active jsdoc comment, shadowed by ASI-insertions
												insertOffset = leafNodeAtBeginOfLine.getTotalOffset();

											}
											// it is ML, so we need to insert a line-break;
											String finalText = NL + organizedImportSection + NL;
											changes.add(new Replacement(xtextResource.getURI().trimFragment(),
													insertOffset, 0, finalText));

										} else {
											// The line above is not part of a ML-comment, so do this:
											changes.add(ChangeProvider.insertLineAbove(document,
													offset, organizedImportSection, false)); // indentation doesn't work
																								// with multiple lines
										}
									}
									return changes;
								}
							} catch (BreakException e) {
								LOGGER.warn("Organize imports broke:", e);
								throw new RuntimeException(e);
							}
						}
						return null;
					}
				});

		if (result != null && !result.isEmpty()) {
			// do the changes really modify anything?
			ChangeAnalysis changeAnalysis = condense(result);
			if (changeAnalysis.noRealChanges) {
				// verify again:
				String del = document.get().substring(changeAnalysis.deletion.getOffset(),
						changeAnalysis.deletion.getOffset() + changeAnalysis.deletion.getLength());
				if (changeAnalysis.newText.getText().equals(del)) {
					return;
				}
			}
			document.modify(
					new IUnitOfWork.Void<XtextResource>() {
						@Override
						public void process(XtextResource state) throws Exception {
							try {
								EcoreUtil.resolveAll(state);
								changeManager.applyAllInSameDocument(changeAnalysis.changes, document);
							} catch (BadLocationException e) {
								LOGGER.error(e);
							}
						}
					});

		}

	}

	/**
	 * Very specific to the generator: One has a text with nonzero length, all others are deletions an have zero-length
	 * texts.
	 *
	 * Find the one with text, try to condense the other into one atomic change.
	 *
	 *
	 * @param changes
	 *            list of Changes to process
	 * @return Pair of Changes, flag if nothing changes.
	 */
	private ChangeAnalysis condense(List<IChange> changes) {
		List<IAtomicChange> atomicResult = changeManager.flattenAndOrganized(changes);
		if (atomicResult.isEmpty()) {
			return new ChangeAnalysis(atomicResult, true);
		}
		// if all are from same uri and type of Replacement, then it will be condensed.
		URI uri = atomicResult.get(0).getURI();
		if (!(atomicResult.get(0) instanceof Replacement)) {
			return new ChangeAnalysis(atomicResult, false);
		}

		// Pre condition: find the one with text != ø && other have no text.
		// Pre uris must match.
		Replacement rText = null;
		for (IAtomicChange nxt : atomicResult) {
			if (!(nxt instanceof Replacement) || !uri.equals(nxt.getURI())) {
				return new ChangeAnalysis(atomicResult, false);
			}
			Replacement rplc = (Replacement) nxt;
			if (rplc.getText() != null && rplc.getText().length() > 0) {
				if (rText == null) {
					rText = rplc;
				} else {
					return new ChangeAnalysis(atomicResult, false); // more then one text-addition, pre doesn't hold
				}
			}
		}

		Replacement current = null;
		// Back to front iteration
		for (int i = atomicResult.size() - 1; i >= 0; i--) {
			IAtomicChange nxt = atomicResult.get(i);
			if (nxt == rText) {
				continue;
			}
			Replacement rplc = (Replacement) nxt;
			if (current == null) {
				current = rplc;
				continue;
			}
			// all Texts are
			if (current.getOffset() + current.getLength() == rplc.getOffset()) {
				// possible to concatenate.
				current = new Replacement(uri, current.getOffset(), current.getLength() + rplc.getLength(), "");
			} else {
				// cannot merge
				return new ChangeAnalysis(atomicResult, false);
			}
		}
		// compare length:
		if (current == null || rText == null || current.getLength() != rText.getText().length()) {
			return new ChangeAnalysis(atomicResult, false);
		}

		// keep correct order.
		List<IAtomicChange> orderedChanges = null;
		if (rText == atomicResult.get(0)) {
			orderedChanges = Arrays.asList(rText, current);
		} else if (rText == atomicResult.get(atomicResult.size() - 1)) {
			orderedChanges = Arrays.asList(current, rText);
		} else {
			// something is wrong here ?!
			System.out.println("XXX");
			return new ChangeAnalysis(atomicResult, false);
		}

		ChangeAnalysis result = new ChangeAnalysis(orderedChanges, true);
		result.deletion = current;
		result.newText = rText;
		return result;
	}

	static class ChangeAnalysis {
		public ChangeAnalysis(List<IAtomicChange> changes, boolean noRealChanges) {
			super();
			this.changes = changes;
			this.noRealChanges = noRealChanges;
		}

		List<IAtomicChange> changes;
		boolean noRealChanges;
		Replacement newText = null;
		Replacement deletion = null;
	}

	/**
	 * Shows JFace ErrorDialog but improved by constructing full stack trace in detail area.
	 *
	 * @return true if OK was pressed
	 */
	public static boolean errorDialogWithStackTrace(String msg, Throwable t) {

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
