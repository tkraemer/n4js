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

import com.google.inject.Inject
import eu.numberfour.n4js.projectModel.IN4JSCore
import eu.numberfour.n4js.ui.wizard.generator.N4JSImportRequirementResolver
import eu.numberfour.n4js.ui.wizard.generator.N4JSImportRequirementResolver.ImportAnalysis
import eu.numberfour.n4js.ui.wizard.generator.N4JSImportRequirementResolver.ImportRequirement
import eu.numberfour.n4js.ui.wizard.model.AccessModifiableModel.AccessModifier
import eu.numberfour.n4js.ui.wizard.model.ClassifierReference
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets
import java.util.ArrayList
import java.util.List
import java.util.Map
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.emf.common.util.URI
import eu.numberfour.n4js.projectModel.IN4JSProject
import eu.numberfour.n4js.ui.wizard.generator.WizardGeneratorHelper

/**
 * A file generator for {@link N4JSClassWizardModel}
 * 
 */
class N4JSNewClassWizardGenerator {

	@Inject
	private IN4JSCore n4jsCore;

	@Inject
	private extension WizardGeneratorHelper generatorUtils;
	
	@Inject
	private N4JSImportRequirementResolver requirementResolver;

	/**
	 *  Generate the class code
	 */
	private def String generateClass(N4JSClassWizardModel model, Map<URI,String> aliasBindings) {
		'''
		«IF model.isFinal»@Final«ENDIF»
		«IF model.n4jsAnnotated»@N4JS«ENDIF»
		«IF model.isInternal»@Internal «ENDIF
		»«model.accessModifier.exportDeclaration
		»«IF model.isDefinitionFile»external «ENDIF
		»«accessModifierString(model.accessModifier)»class «model.name»«
		IF model.superClass.isComplete» extends «model.superClass.realOrAliasName(aliasBindings)»«ENDIF»«
		IF model.interfaces.length > 0 » implements «ENDIF»«FOR iface : model.interfaces  SEPARATOR ", " »«
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
		if (aliasBindings !== null && aliasBindings.containsKey(reference.uri)) {
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

			if (moduleFile.exists) {
				insertIntoFile(moduleFile, model);
			} else {
				//Collect the import requirements
				val importRequirements = model.importRequirements;
				
				//Resolve occurring name conflicts
				val aliasBindings = requirementResolver.resolveImportNameConflicts(importRequirements, null)

				//Generate import statements
				var importStatements = requirementResolver.generateImportStatements(importRequirements)
				//If non empty import Statements add line break after statements and an additional empty line to have some space to the code
				if (!importRequirements.empty)
					importStatements = importStatements + "\n\n";

				//Generate class code
				val classCode = generateClass(model, aliasBindings);

				//Write to file
				val classTextStream = new ByteArrayInputStream((importStatements+classCode).getBytes(StandardCharsets.UTF_8));
				moduleFile.create(classTextStream, true, null);
			}
		} catch (CoreException e) {
			return false;
		}

		return true;
	}

	private def void insertIntoFile(IFile file, N4JSClassWizardModel model) throws CoreException {
		//Retrieve XtextResource
		val moduleURI = URI.createPlatformResourceURI(model.computeFileLocation.toString, true);

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

		if (project.present) {
			val manifestLocation = project.get().manifestLocation;

			val manifest = getResource(manifestLocation.get());
			
			// Gather referenced projects
			var referencedProjects = new ArrayList<IN4JSProject>(model.interfaces.map[uri.projectOfUri]);

			if (model.superClass.isComplete) {
				referencedProjects.add(model.superClass.uri.projectOfUri);
			}
			
			// Create manifest changes
			val moduleURI = URI.createPlatformResourceURI(model.computeFileLocation.toString,true);
			val manifestChanges = manifest.manifestChanges(model, referencedProjects, moduleURI);
			
			//Only perform non-empty changes. (To prevent useless history entries)
			if (manifestChanges.length > 0) {
				manifest.applyChanges(manifestChanges);
			}
		}
	}
	/**
	 * Return the export statement if the access modifier requires it. Return an empty string otherwise.
	 */
	private def String exportDeclaration(AccessModifier modifier) {
		if (modifier == AccessModifier.PROJECT || modifier == AccessModifier.PUBLIC) {
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
	/** Return the import requirements of a N4JSClassWizardModel */
	private def List<ImportRequirement> importRequirements(N4JSClassWizardModel model) {
		var demandedImports = new ArrayList<ImportRequirement>();

		if (model.superClass.complete) {
			demandedImports.add(N4JSImportRequirementResolver.classifierReferenceToImportRequirement(model.superClass));
		}
		if (!model.interfaces.empty) {
			demandedImports.addAll(N4JSImportRequirementResolver.classifierReferencesToImportRequirements(model.interfaces));
		}
		demandedImports
	}
}
