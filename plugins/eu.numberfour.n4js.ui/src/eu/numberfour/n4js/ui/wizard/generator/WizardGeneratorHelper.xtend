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
package eu.numberfour.n4js.ui.wizard.generator

import com.google.common.base.Optional
import com.google.inject.Inject
import eu.numberfour.n4js.n4mf.ProjectDescription
import eu.numberfour.n4js.n4mf.ProjectType
import eu.numberfour.n4js.projectModel.IN4JSCore
import eu.numberfour.n4js.projectModel.IN4JSProject
import eu.numberfour.n4js.ui.changes.ChangeManager
import eu.numberfour.n4js.ui.changes.IAtomicChange
import eu.numberfour.n4js.ui.changes.ManifestChangeProvider
import eu.numberfour.n4js.ui.organize.imports.Interaction
import eu.numberfour.n4js.ui.organize.imports.N4JSOrganizeImportsHandler
import eu.numberfour.n4js.ui.wizard.generator.N4JSImportRequirementResolver.ImportRequirement
import eu.numberfour.n4js.ui.wizard.workspace.WorkspaceWizardModel
import java.util.ArrayList
import java.util.Collection
import java.util.HashSet
import java.util.List
import java.util.Scanner
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.jface.text.BadLocationException
import org.eclipse.ui.part.FileEditorInput
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.ui.editor.model.IXtextDocument
import org.eclipse.xtext.ui.editor.model.XtextDocumentProvider
import org.eclipse.xtext.util.concurrent.IUnitOfWork

/**
 * This class contains commonly used methods when writing wizard generators.
 */
class WizardGeneratorHelper {
	
	@Inject
	private IN4JSCore n4jsCore;
	
	@Inject
	private ChangeManager changeManager;
	
	@Inject
	private N4JSOrganizeImportsHandler organizeImportsHandler;
	
	@Inject
	private XtextDocumentProvider docProvider;
	
	@Inject
	private N4JSImportRequirementResolver requirementResolver;
	
	
	/**
	 * Return the last character of a given file.
	 */
	public def String lastCharacterInFile(IFile file) {
		try {
			val scanner = new Scanner(file.contents)
			scanner.useDelimiter("");
			var line = "";
			while (scanner.hasNext) {
				line = scanner.next;
			}
			scanner.close();
			return line;
		} catch (CoreException exc) {
			return "";
		};
	}
	
	/**
	 * Inserts the corresponding import statements for the given model into an existing file.
	 * 
	 * <p>The method tries to find the files import region and append the import statements to it</p> 
	 */
	public def void insertImportStatements(XtextResource moduleResource, List<ImportRequirement> importRequirements ) {
		val importReplacement = requirementResolver.getImportStatementChanges(moduleResource, importRequirements);
		moduleResource.applyChanges(#[importReplacement]);
	}
	
	
	
	/**
	 * Returns true if the given path exists in the workspace.
	 * 
	 * Note that the path must contain a project segment and at least one additional segment.
	 */
	public def boolean exists(IPath path) {
		if (null === path) {
			return false;
		}
		val member = ResourcesPlugin.workspace.root.findMember(path)
		if (null === member) {
			return false;
		}
		member.exists
	}
	
	/**
	 * Load and return the {@link XtextResource} at the given URI
	 */
	public def XtextResource getResource(URI moduleURI) {
		val resourceSet = n4jsCore.createResourceSet(Optional.fromNullable(null));
		val moduleResource = resourceSet.getResource(moduleURI, true);
		if (moduleResource instanceof XtextResource ) {
			return moduleResource
		}
		null
	}
	
	/** 
	 * Retrieve the XtextDocument for the given resource and apply the changes
	 *	
	 * @param resource The XtextResource to modify
	 * @param changes The changes to apply
	 * */
	public def boolean applyChanges(XtextResource resource,Collection<? extends IAtomicChange> changes){
		val IPath resourcePath = new Path(resource.getURI.toString).makeRelativeTo(new Path("platform:/resource/"));
		val IFile resourceFile = ResourcesPlugin.workspace.root.getFile(resourcePath);
		if ( resourceFile.exists ) {
			try {
				val FileEditorInput fileInput = new FileEditorInput(resourceFile);
				docProvider.connect(fileInput);
				val IXtextDocument document = docProvider.getDocument(fileInput) as IXtextDocument;
				docProvider.aboutToChange(fileInput);
				
				document.modify(
					new IUnitOfWork.Void<XtextResource>() {
						public override void process(XtextResource state) throws Exception {
							try {
								// Sort changes by descending offset to avoid conflicts
								changeManager.applyAllInSameDocument(changes.sortBy[offset].reverse(), document);
							} catch (BadLocationException e) {
								return;
							}
						}
					});
				
				docProvider.saveDocument(null, fileInput, document, true);
				docProvider.changed(fileInput);
				docProvider.disconnect(fileInput);
				
			} catch ( Exception all ) {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Run organize import on the given file and save it. 
	 * 
	 * This method works in the background without opening the graphical editor.
	 */
	public def void organizeImports(IFile file, IProgressMonitor mon) throws CoreException {

		val FileEditorInput fei = new FileEditorInput(file);

		docProvider.connect(fei); // without connecting no document will be provided
		val IXtextDocument document = (docProvider.getDocument(fei) as IXtextDocument);

		docProvider.aboutToChange(fei);

		organizeImportsHandler.doOrganizeImports(document, Interaction.takeFirst);
	
		docProvider.saveDocument(null, fei, document, true);

		docProvider.changed(fei);
		docProvider.disconnect(fei);
	}
	
	/**
	 * Return the project name of the containing project of the given uri.
	 */
	public def IN4JSProject projectOfUri(URI uri) {
		val projectOptional = n4jsCore.findProject(uri);
		if ( projectOptional.present ) {
			return projectOptional.get();
		}
		return null;
	}
	
	/**
	 * Return the manifest changes which need to be applied in order to allow referencing of the given projects/runtime libraries.
	 * Also add a new source folder if the module happens to be in a non-source-folder location.
	 * 
	 * @param manifest The manifest resource
	 * @param model The workspace wizard model
	 * @param referencedProjects A list of the projects to be referenced
	 * @param moduleURI The platform uri of the target module
	 * 
	 * @returns A list of {@link IAtomicChange}s for the manifest resource. 
	 */
	public def Collection<IAtomicChange> manifestChanges(Resource manifest, WorkspaceWizardModel model, Collection<IN4JSProject> referencedProjects, URI moduleURI) {
		// Remove the containing project from the dependencies
		referencedProjects.removeIf[ it.artifactId.equals(model.project.lastSegment) ];
		
		//Remove duplicates
		val referencedProjectsSet = new HashSet<IN4JSProject>();
		referencedProjectsSet.addAll(referencedProjects);
		
		var List<IAtomicChange> manifestChanges = new ArrayList<IAtomicChange>();
		
		val projectDescription = manifest.allContents.filter(ProjectDescription).head;
		
		//Add project dependency changes
		val dependencyChange = ManifestChangeProvider.insertProjectDependencies(manifest, 
																				referencedProjectsSet.filter[projectType != ProjectType.RUNTIME_LIBRARY].map[artifactId].toList,
																				projectDescription)
		//Add required runtime library changes
		val runtimeLibraryChange = ManifestChangeProvider.insertRequiredRuntimeLibraries(manifest, 
																				referencedProjectsSet.filter[projectType == ProjectType.RUNTIME_LIBRARY].map[artifactId].toList,
																				projectDescription)
		if (dependencyChange !== null) {
			manifestChanges.add(dependencyChange);
		}
		if (runtimeLibraryChange !== null) {
			manifestChanges.add(runtimeLibraryChange);
		}
		
		return manifestChanges;
	}
}