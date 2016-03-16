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
package eu.numberfour.n4js.ui.wizard.classwizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.conversion.IdentifierValueConverter;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.ts.types.TypesPackage;
import eu.numberfour.n4js.ui.wizard.model.AccessModifiableModel.AccessModifier;
import eu.numberfour.n4js.ui.wizard.model.ClassifierReference;
import eu.numberfour.n4js.ui.wizard.workspacewizard.WorkspaceWizardModel;
import eu.numberfour.n4js.ui.wizard.workspacewizard.WorkspaceWizardModelValidator;

/**
 * A validator for a {@link N4JSClassWizardModel}
 */
public class N4JSClassWizardModelValidator extends WorkspaceWizardModelValidator {

	@Inject
	IN4JSCore n4jsCore;

	private IResourceDescriptions descriptions;

	/**
	 * Error Messages for model validation of the {@link N4JSClassWizardModel}
	 */
	public static class ErrorMessages {

		// Class name errors
		private static final String CLASS_NAME_MUST_NOT_BE_EMPTY = "The class name field must not be empty.";
		private static final String INVALID_CLASS_NAME = "Invalid class name ";

		// Super class errors
		private static final String THE_SUPER_CLASS_CANNOT_BE_FOUND = "The super class cannot be found";

		// Interfaces errors
		private static final String THE_INTERFACE_CANNOT_BE_FOUND = "The interface %s cannot be found";

		// Definition file collision errors
		private static final String THE_NEW_SOURCE_MODULE_COLLIDES_WITH_THE_DEFINITION_FILE = "The new source module collides with the definition file ";
		private static final String THE_NEW_DEFINITION_MODULE_COLLIDES_WITH_THE_SOURCE_FILE = "The new definition module collides with the source file ";
	}

	/**
	 * Class name specifier property constraints
	 */
	private void validateClassName() throws ValidationException {

		// 1. The class name must not be empty
		if (getModel().getName().trim().length() < 1) {
			throw new ValidationException(ErrorMessages.CLASS_NAME_MUST_NOT_BE_EMPTY);
		}

		String className = getModel().getName();

		// 2. The class name is a valid n4js class identifier
		String validatedClassName = "";
		String[] classNameLetters = className.split("");
		char firstCharacter = classNameLetters[0].charAt(0);
		if (!IdentifierValueConverter.isValidIdentifierStart(firstCharacter) || firstCharacter == ' ') {
			throw new ValidationException(ErrorMessages.INVALID_CLASS_NAME
					+ errorPointer(className, validatedClassName));
		}
		validatedClassName += classNameLetters[0];
		for (int i = 1; i < classNameLetters.length; i++) {
			char character = classNameLetters[i].charAt(0);
			if (!IdentifierValueConverter.isValidIdentifierPart(character) || character == ' ') {
				throw new ValidationException(ErrorMessages.INVALID_CLASS_NAME
						+ errorPointer(className, validatedClassName));
			}
			validatedClassName += classNameLetters[i];
		}
	}

	/**
	 * Module specifier specifier property constraints
	 */
	private void validateModuleSpecifier() throws ValidationException {

		String effectiveModuleSpecifier = getModel().getEffectiveModuleSpecifier();

		/*
		 * If the specifier is computed (with a class name suffix) the workspace validator doesn't validate the class
		 * name part of the specifier path.
		 */
		if (!effectiveModuleSpecifier.equals(getModel().getModuleSpecifier()) &&
				!isValidFolderName(getModel().getName())) {
			throw new ValidationException(
					WorkspaceWizardModelValidator.ErrorMessages.INVALID_MODULE_SPECIFIER_INVALID_SEGMENT
							+ errorPointer(effectiveModuleSpecifier, getModel().getModuleSpecifier()));
		}

		/* Check for potential file collisions */
		if (fileSpecifyingModuleSpecifier(effectiveModuleSpecifier)) {
			IProject moduleProject = ResourcesPlugin.getWorkspace().getRoot()
					.getProject(getModel().getProject().toString());
			IPath effectiveModulePath = new Path(getModel().getEffectiveModuleSpecifier());

			IPath n4jsdPath = getModel().getSourceFolder()
					.append(effectiveModulePath.addFileExtension(N4JSGlobals.N4JSD_FILE_EXTENSION));
			IPath n4jsPath = getModel().getSourceFolder()
					.append(effectiveModulePath.addFileExtension(N4JSGlobals.N4JS_FILE_EXTENSION));

			if (getModel().isDefinitionFile() && moduleProject.exists(n4jsPath)) {
				throw new ValidationException(ErrorMessages.THE_NEW_DEFINITION_MODULE_COLLIDES_WITH_THE_SOURCE_FILE
						+ n4jsPath);
			} else if (!getModel().isDefinitionFile() && moduleProject.exists(n4jsdPath)) {
				throw new ValidationException(ErrorMessages.THE_NEW_SOURCE_MODULE_COLLIDES_WITH_THE_DEFINITION_FILE
						+ n4jsdPath);
			}
		}

	}

	/**
	 * True if the given module specifier is specifying a file.
	 *
	 * Returns false for empty specifiers.
	 *
	 * @param specifier
	 *            The module specifier
	 */
	private boolean fileSpecifyingModuleSpecifier(String specifier) {
		return specifier.length() > 0 && specifier.charAt(specifier.length() - 1) != IPath.SEPARATOR;
	}

	/**
	 * Super class specifier property constraints
	 */
	private void validateSuperClass() throws ValidationException {

		ClassifierReference superClassRef = getModel().getSuperClass();

		if (!superClassRef.getFullSpecifier().isEmpty())

		{
			if (!isValidClass(superClassRef)) {
				throw new ValidationException(ErrorMessages.THE_SUPER_CLASS_CANNOT_BE_FOUND);
			} else if (superClassRef.uri == null) {
				IEObjectDescription classDescription = classifierObjectDescriptionForAbsoluteSpecifier(
						superClassRef.getFullSpecifier());
				if (classDescription != null) {
					superClassRef.uri = classDescription.getEObjectURI();
				}
			}
		}
	}

	/**
	 * Interfaces specifier property constraints
	 */
	private void validateInterfaces() throws ValidationException {
		// ---------------------------------------
		// Interfaces property constraints
		// ---------------------------------------

		ArrayList<ClassifierReference> interfaces = new ArrayList<>(getModel().getInterfaces());

		for (int i = 0; i < interfaces.size(); i++) {
			ClassifierReference iface = interfaces.get(i);
			if (!isValidInterface(iface)) {
				throw new ValidationException(
						String.format(ErrorMessages.THE_INTERFACE_CANNOT_BE_FOUND, iface.getFullSpecifier())
								.toString());
			} else if (iface.uri == null) {
				IEObjectDescription interfaceDescription = classifierObjectDescriptionForAbsoluteSpecifier(
						iface.getFullSpecifier());
				if (interfaceDescription != null) {
					iface.uri = interfaceDescription.getEObjectURI();
				}
			}
		}

		getModel().setInterfaces(interfaces);
	}

	@Override
	protected void prepare() {
		// ---------------------------------------
		// Automatic regulative constraints
		// ---------------------------------------

		// Auto disable the N4JS annotation field when definition file (external) is unselected.
		if (!getModel().isDefinitionFile() && getModel().isN4jsAnnotated()) {
			getModel().setN4jsAnnotated(false);
		}
		// Auto disable the Internal annotation for the private access modifier
		if (getModel().getAccessModifier() == AccessModifier.PRIVATE && getModel().isInternal()) {
			getModel().setInternal(false);
		}

		// Remove interfaces duplicate entries
		ArrayList<ClassifierReference> interfaces = new ArrayList<>(getModel().getInterfaces());
		Map<String, Boolean> duplicatesMap = new HashMap<>();
		for (int i = interfaces.size() - 1; i >= 0; i--) {
			ClassifierReference iface = interfaces.get(i);
			if (duplicatesMap.get(iface.getFullSpecifier()) != null) {
				interfaces.remove(i);
			} else {
				duplicatesMap.put(iface.getFullSpecifier(), true);
			}
		}
		getModel().setInterfaces(interfaces);
	}

	@Override
	protected void run() throws ValidationException {
		super.run();

		validateModuleSpecifier();
		validateClassName();
		validateSuperClass();
		validateInterfaces();
	}

	private String errorPointer(String original, String validated) {
		if (validated.length() > original.length() - 1) {
			return validated + "̫";
		}
		return validated + original.charAt(validated.length()) + "̫" + original.substring(validated.length() + 1);
	}

	private boolean isValidReferenceOfType(String absoluteSpecifier, EClass type) {
		if (descriptions == null) {
			ResourceSet set = n4jsCore.createResourceSet(Optional.fromNullable(null));
			this.descriptions = n4jsCore.getXtextIndex(set);
		}

		String[] segments = absoluteSpecifier.split("\\.");
		QualifiedName name = QualifiedName.create(Arrays.asList(segments));
		Iterable<IEObjectDescription> foundObjects = descriptions.getExportedObjects(
				type,
				name, false);
		return foundObjects.iterator().hasNext();
	}

	private boolean isValidClass(ClassifierReference ref) {
		return isValidReferenceOfType(ref.getFullSpecifier(), TypesPackage.eINSTANCE.getTClass());
	}

	private boolean isValidInterface(ClassifierReference ref) {
		return isValidReferenceOfType(ref.getFullSpecifier(), TypesPackage.eINSTANCE.getTInterface());
	}

	private IEObjectDescription classifierObjectDescriptionForAbsoluteSpecifier(String absoluteSpecifier) {
		String[] segments = absoluteSpecifier.split("\\.");
		QualifiedName name = QualifiedName.create(Arrays.asList(segments));
		Iterable<IEObjectDescription> foundObjects = descriptions.getExportedObjects(
				TypesPackage.eINSTANCE.getTClassifier(),
				name, false);
		if (foundObjects.iterator().hasNext()) {
			return foundObjects.iterator().next();
		}
		return null;
	}

	/**
	 * @return The model currently validated
	 */
	@Override
	public N4JSClassWizardModel getModel() {
		return (N4JSClassWizardModel) super.getModel();
	}

	/**
	 * Set the model to validate
	 *
	 * @param model
	 *            The new model to validate
	 */
	public void setModel(N4JSClassWizardModel model) {
		super.setModel(model);
		this.validate();
	}

	@Override
	public void setModel(WorkspaceWizardModel model) {
		throw new UnsupportedOperationException("Only N4JSClassWizardModels can be validated");
	}

	/**
	 * Check whether name is a valid folder name.
	 *
	 * For now this means: Letter or underscore in the beginning, no dot at the end or beginning
	 *
	 * @param name
	 *            Name to check
	 * @return valid state
	 */
	public static boolean isValidFolderName(String name) {
		return name.matches("[a-zA-z_](([\\.][a-zA-z_0-9])|[a-zA-z_0-9])*");
	}

}
