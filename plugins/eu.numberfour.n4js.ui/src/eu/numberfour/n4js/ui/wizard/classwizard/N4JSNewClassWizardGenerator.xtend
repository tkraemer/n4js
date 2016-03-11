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
package eu.numberfour.n4js.ui.wizard.classwizard

import com.google.common.base.Optional
import com.google.inject.Inject
import eu.numberfour.n4js.projectModel.IN4JSCore
import eu.numberfour.n4js.ui.changes.ChangeManager
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSClassWizardModel.ClassifierReference
import eu.numberfour.n4js.n4mf.ProjectDescription
import java.util.ArrayList
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.ui.editor.model.XtextDocumentProvider
import org.eclipse.xtext.ui.editor.model.IXtextDocument
import java.util.List
import org.eclipse.xtext.resource.XtextResource
import eu.numberfour.n4js.ui.changes.IAtomicChange
import java.util.Collection
import org.eclipse.core.resources.IFile
import org.eclipse.core.runtime.IPath
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.ui.part.FileEditorInput
import javax.swing.text.BadLocationException
import org.eclipse.core.runtime.Path
import org.eclipse.xtext.util.concurrent.IUnitOfWork
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import org.eclipse.core.runtime.CoreException
import eu.numberfour.n4js.ui.organize.imports.N4JSOrganizeImportsHandler
import eu.numberfour.n4js.ui.organize.imports.Interaction
import org.eclipse.core.runtime.IProgressMonitor
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSImportRequirementResolver.ImportAnalysis
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSImportRequirementResolver.ImportRequirement
import java.util.Map
import eu.numberfour.n4js.ui.changes.ManifestChangeProvider
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSImportRequirementResolver
import java.util.Scanner
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSClassWizardModel.AccessModifier

/**
 * A file generator for {@link N4JSClassWizardModel}
 */
class N4JSNewClassWizardGenerator {

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
	 *  Generate the class code
	 */
	private def String generateClass(N4JSClassWizardModel model, Map<URI,String> aliasBindings) {
'''«IF model.isFinal»@Final«ENDIF»
«IF model.n4jsAnnotated»@N4JS«ENDIF»
«IF model.isInternal»@Internal «ENDIF
»«model.accessModifier.exportDeclaration
»«IF model.isExternal»external «ENDIF
»«accessModifierString(model.accessModifier)»class «model.className»«
IF model.superClass.isComplete» extends «model.superClass.realOrAliasName(aliasBindings)»«ENDIF»«
IF model.interfaces.length > 0 » implements «ENDIF»«FOR iface : model.interfaces  SEPARATOR "," »«
iface.realOrAliasName(aliasBindings)»«ENDFOR» {

}
		'''
	}

	/**
	 * Return the real or bound name of the classifier reference.
	 *
	 * Always prioritizes alias name over real name.
	 *
	 * @param reference The classifier reference
	 * @param aliasBindings The alias bindings, may be null
	 */
	private def String realOrAliasName(ClassifierReference reference, Map<URI,String> aliasBindings) {
		if ( aliasBindings !== null && aliasBindings.containsKey(reference.uri) ) {
			return aliasBindings.get(reference.uri);
		}
		return reference.classifierName;
	}

	/**
	 * Writes the new class specified by model to a file.
	 *
	 * Depending on the model's module specifier the class will be written to a new file or inserted into an existing file.
	 *
	 * <p>Note: Make sure to only a pass a valid model, no model validation takes place. </p>
	 *
	 * @param model The class wizard model to write to file
	 *
	 */

	def boolean writeToFile(N4JSClassWizardModel model) {
		val modulePath = model.computeFileLocation();
		val moduleFile = ResourcesPlugin.workspace.root.getFile(modulePath);

		try {

			if ( moduleFile.exists ) {
				insertIntoFile(moduleFile, model);
			} else {
				//Collect the import requirements
				val importRequirements = model.importRequirements;
				//Resolve occurring name conflicts
				val aliasBindings = requirementResolver.resolveImportNameConflicts(importRequirements, null)

				//Generate import statements
				var importStatements = requirementResolver.generateImportStatements(importRequirements)
				//If non empty import Statements add line break after statements and an additional empty line to have some space to the code
				if ( !importRequirements.empty)
					importStatements = importStatements + "\n\n";

				//Generate class code
				val classCode = generateClass(model, aliasBindings);

				//Write to file
				val classTextStream = new ByteArrayInputStream((importStatements+classCode).getBytes(StandardCharsets.UTF_8));
				moduleFile.create(classTextStream, true, null);
			}
		} catch ( CoreException e ) {
			return false;
		}

		return true;
	}

	private def void insertIntoFile(IFile file, N4JSClassWizardModel model) throws CoreException {
		//Retrieve XtextResource
		val moduleURI = URI.createPlatformResourceURI(model.computeFileLocation.toString,true);
		val moduleResource = getResource(moduleURI);

		//Collect the import requirements
		var demandedImports = model.importRequirements

		//Analyze import requirements
		val ImportAnalysis importAnalysis = requirementResolver.analyzeImportRequirements(demandedImports, moduleResource);

		//Generate class code
		var classCode = generateClass(model,importAnalysis.aliasBindings);

		//Add an additional line break for non-line-break terminated files
		if (lastCharacterInFile(file) != "\n") {
			classCode = "\n"+classCode;
		}

		//Get stream for code
		val classCodeStream = new ByteArrayInputStream(classCode.getBytes(StandardCharsets.UTF_8));

		//Insert import statement at the top
		insertImportStatements(moduleResource,importAnalysis.importRequirements);


		//Append class code
		file.appendContents(classCodeStream, true, true, null);

		//Finally organize imports
		organizeImports(file, null);
	}

	private def String lastCharacterInFile(IFile file) {
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
	 */
	private def void insertImportStatements(XtextResource moduleResource, List<ImportRequirement> importRequirements ) {

		val importReplacement = requirementResolver.getImportStatementChanges(moduleResource, importRequirements);
		moduleResource.applyChanges(#[importReplacement]);
	}



	/**
	 * Return the loaded {@link XtextResource} at the given URI
	 */
	private def XtextResource getResource(URI moduleURI) {
		val resourceSet = n4jsCore.createResourceSet(Optional.fromNullable(null));
		val moduleResource = resourceSet.getResource(moduleURI, true);
		if (moduleResource instanceof XtextResource ) {
			return moduleResource
		}
		null
	}

	/**
	 * Performs the manifest changes required by the class of the model.
	 *
	 * This means for now the computation of necessary project dependencies and their addition to the project manifest file.
	 *
	 * <p> IMPORTANT: This method should always be called before invoking {@link #writeToFile(N4JSClassWizardModel)} as
	 * writeToFile may need manifest changes to resolve all imports.</p>
	 */
	def performManifestChanges(N4JSClassWizardModel model) {
		val project = n4jsCore.findProject(URI.createPlatformResourceURI(model.computeFileLocation.toString, true));

		if ( project.present ) {
			val manifestLocation = project.get().manifestLocation;

			val resourceSet = n4jsCore.createResourceSet(Optional.fromNullable(null));

			val manifest = resourceSet.getResource(manifestLocation.get(),true);

			var classDependencies = new ArrayList<String>();

			if ( model.superClass.isComplete ) {
				classDependencies.add(model.superClass.uri.projectNameOfUri);
			}
			for ( ClassifierReference ref : model.interfaces ) {
				classDependencies.add(ref.uri.projectNameOfUri);
			}

			// Remove the containing project from the dependencies
			classDependencies.removeIf[ it.equals(model.project.lastSegment) ];

			var List<IAtomicChange> manifestChanges = new ArrayList<IAtomicChange>();

			//Add project dependency changes
			val dependencyChange = ManifestChangeProvider.insertProjectDependencies(manifest as XtextResource, classDependencies, manifest.allContents.filter(ProjectDescription).head)
			if ( dependencyChange !== null )
				manifestChanges.add(dependencyChange);

			//If unknown source folder, add source folder changes
			val sourceFolder = n4jsCore.findN4JSSourceContainer(URI.createPlatformResourceURI(model.computeFileLocation.toString,true));
			if (!sourceFolder.present) {
				manifestChanges.add(ManifestChangeProvider.addSourceFoldersToManifest(manifest as XtextResource, #[model.sourceFolder.toString]))
			}

			//Only perform non-empty changes. (To prevent useless history entries)
			if (manifestChanges.length>0) {
				(manifest as XtextResource).applyChanges(manifestChanges);
			}
		}
	}


	/**
	 * Retrieve the XtextDocument for resource and apply the changes
	 *
	 * @param resource The XtextResource to modify
	 * @param changes The changes to apply
	 * */
	private def boolean applyChanges(XtextResource resource,Collection<? extends IAtomicChange> changes){
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
								changeManager.applyAllInSameDocument(changes, document);
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
	 * Return the project name of the containing project of the given uri.
	 */
	private def String projectNameOfUri(URI uri) {
		val projectOptional = n4jsCore.findProject(uri);
		if ( projectOptional.present ) {
			return projectOptional.get().projectName;
		}
		return "";
	}


	/**
	 * Return the export statement if the access modifier requires it. Return an empty string otherwise.
	 */
	private def String exportDeclaration(AccessModifier modifier) {
		if ( modifier == AccessModifier.PROJECT || modifier == AccessModifier.PUBLIC ) {
			return "export "
		}
		""
	}

	/**
	 * Return the string representation of the given access modifier
	 */
	private def String accessModifierString(AccessModifier modifier) {
		switch (modifier) {
			case PROJECT:
				return "project "
			case PUBLIC:
				return "public "
			default: //PRIVATE:
				return ""
		}
	}

	private def List<ImportRequirement> importRequirements(N4JSClassWizardModel model) {
		var demandedImports = new ArrayList<ImportRequirement>();

		if ( model.superClass.complete )
			demandedImports.add(N4JSImportRequirementResolver.classifierReferenceToImportRequirement(model.superClass));
		if ( !model.interfaces.empty)
			demandedImports.addAll(N4JSImportRequirementResolver.classifierReferencesToImportRequirements(model.interfaces)
			);

		demandedImports
	}

	private def void organizeImports(IFile file, IProgressMonitor mon) throws CoreException {

		val FileEditorInput fei = new FileEditorInput(file);

		docProvider.connect(fei); // without connecting no document will be provided
		val IXtextDocument document = (docProvider.getDocument(fei) as IXtextDocument);

		docProvider.aboutToChange(fei);

		organizeImportsHandler.doOrganizeImports(document, Interaction.takeFirst);

		docProvider.saveDocument(null, fei, document, true);

		docProvider.changed(fei);
		docProvider.disconnect(fei);
	}
}
