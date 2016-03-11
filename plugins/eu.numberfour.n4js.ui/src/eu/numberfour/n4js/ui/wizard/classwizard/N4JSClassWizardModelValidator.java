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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
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
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.ts.types.TypesPackage;
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSClassWizardModel.AccessModifier;
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSClassWizardModel.ClassifierReference;

/**
 * A validator for a {@link N4JSClassWizardModel}
 */
public class N4JSClassWizardModelValidator {

	private N4JSClassWizardModel model = null;

	@Inject
	IN4JSCore n4jsCore;

	private IResourceDescriptions descriptions;

	/**
	 * Error Messages for model validation of the {@link N4JSClassWizardModel}
	 */
	public static class ErrorMessages {

		// Project errors
		private static final String PROJECT_DOES_NOT_EXIST = "The given project does not exist";
		private static final String INVALID_PROJECT = "Not a valid project";
		private static final String PROJECT_MUST_NOT_BE_EMPTY = "The project field must not be empty";

		// Source folder errors

		// private static final String FOLDER_ALREADY_EXISTS = "A folder with model name already exists in model
		// project";
		private static final String SOURCE_FOLDER_MUST_NOT_BE_EMPTY = "The source folder field must not be empty";
		private static final String SOURCE_FOLDER_IS_NOT_A_VALID_FOLDER_NAME = "The source folder is not a valid folder name";

		// Module specifier errors
		private static final String MODULE_SPECIFIER_MUST_NOT_BE_EMPTY = "The module specifier field must not be empty";
		private static final String INVALID_MODULE_SPECIFIER_MUST_NOT_BEGIN_WITH = "Invalid module specifier. Must not begin with a \"/\" ";
		private static final String INVALID_MODULE_SPECIFIER_EMPTY_PATH_SEGMENT = "Invalid module specifier. Empty path segment:";
		// private static final String INVALID_MODULE_SPECIFIER_INVALID_SEGMENT_START = "Invalid module specifier.
		// Invalid segment start. ";
		private static final String INVALID_MODULE_SPECIFIER_INVALID_SEGMENT = "Invalid module specifier. Invalid segment. ";

		// Class name errors
		private static final String CLASS_NAME_MUST_NOT_BE_EMPTY = "The class name field must not be empty.";
		private static final String INVALID_CLASS_NAME = "Invalid class name ";

		// Super class errors
		private static final String THE_SUPER_CLASS_CANNOT_BE_FOUND = "The super class cannot be found";

		// Interfaces errors
		private static final String THE_INTERFACE_CANNOT_BE_FOUND = "The interface %s cannot be found";
	}

	/**
	 * Helper type for a validation results.
	 */
	public static class ValidationResult {

		/**
		 * True if model content is valid
		 */
		public final boolean valid;
		/**
		 * Contains error message if result is negative
		 */
		public final String errorMessage;

		/**
		 * Initiate a successful validation result. (No errors)
		 */
		public ValidationResult() {
			this.valid = true;
			this.errorMessage = "";
		}

		/**
		 * Initiate a invalid validation result
		 *
		 * @param errorMessage
		 *            Error message to report
		 */
		public ValidationResult(String errorMessage) {
			this.valid = false;
			this.errorMessage = errorMessage;
		}

	}

	/** The validation result property constant */
	public static final String VALIDATION_RESULT = "validationResult";
	/** The validity of the project property */
	public static final String PROJECT_PROPERTY_VALID = "projectValid";
	/** The validity of the source folder property */
	public static final String SOURCE_FOLDER_PROPERTY_VALID = "sourceFolderValid";

	private ValidationResult validationResult = null;

	private boolean projectValid = false;
	private boolean sourceFolderValid = false;

	/**
	 * PropertyChangeListenerSupport
	 */

	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	/**
	 * @param listener
	 *            listener to be called on every change of any property
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.changeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * @param listener
	 *            remove listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.changeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * @param propertyName
	 *            bean name of the property
	 * @param newValue
	 *            new value of the property
	 * @param oldValue
	 *            old value of the property
	 */
	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		this.changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	/**
	 * Run the validator and report possible errors.
	 */
	public ValidationResult validate() {

		if (this.model == null) {
			return new ValidationResult("No model set");
		}

		// ---------------------------------------
		// Automatic regulative constraints
		// ---------------------------------------

		// Auto disable the N4JS annotation field when definition file (external) is unselected.
		if (!getModel().isExternal() && getModel().isN4jsAnnotated()) {
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

		// ---------------------------------------
		// Project property constraints
		// ---------------------------------------

		this.setProjectValid(false);

		// 1. It must not be empty
		if (getModel().getProject().toString().equals("")) {
			return error(ErrorMessages.PROJECT_MUST_NOT_BE_EMPTY);
		}

		// 2. It is a path of a valid project in the current workspace
		URI projectURI = URI.createPlatformResourceURI(getModel().getProject().toString(), true);
		Optional<? extends IN4JSProject> n4jsProject = n4jsCore.findProject(projectURI);
		if (!n4jsProject.isPresent()) {
			return error(ErrorMessages.INVALID_PROJECT);
		} else if (!n4jsProject.get().exists()) {
			return error(ErrorMessages.PROJECT_DOES_NOT_EXIST);
		} else {
			// The path points to a resource inside the project
			if (!n4jsProject.get().getLocation().equals(projectURI)) {
				return error(ErrorMessages.INVALID_PROJECT + n4jsProject.get().getLocation());
			}
		}

		this.setProjectValid(true);

		// ---------------------------------------
		// Source Folder property constraints
		// ---------------------------------------

		this.setSourceFolderValid(false);

		// 1. The source folder property must not be empty
		String sourceFolder = getModel().getSourceFolder().toString();

		if (sourceFolder.equals("")) {
			return error(ErrorMessages.SOURCE_FOLDER_MUST_NOT_BE_EMPTY);
		}

		// 2. The folder must be a valid folder name
		if (!isValidFolderName(sourceFolder)) {
			return error(ErrorMessages.SOURCE_FOLDER_IS_NOT_A_VALID_FOLDER_NAME);
		}

		this.setSourceFolderValid(true);

		// ---------------------------------------
		// Class name specifier property constraints
		// ---------------------------------------

		// 1. The class name must not be empty
		if (getModel().getClassName().trim().length() < 1) {
			return error(ErrorMessages.CLASS_NAME_MUST_NOT_BE_EMPTY);
		}

		String className = getModel().getClassName();

		// 2. The class name is a valid n4js class identifier
		String validatedClassName = "";
		String[] classNameLetters = className.split("");
		if (!IdentifierValueConverter.isValidIdentifierStart(classNameLetters[0].charAt(0))) {
			return error(ErrorMessages.INVALID_CLASS_NAME
					+ errorPointer(className, validatedClassName));
		}
		validatedClassName += classNameLetters[0];
		for (int i = 1; i < classNameLetters.length; i++) {
			if (!IdentifierValueConverter.isValidIdentifierPart(classNameLetters[i].charAt(0))) {
				return error(ErrorMessages.INVALID_CLASS_NAME
						+ errorPointer(className, validatedClassName));
			}
			validatedClassName += classNameLetters[i];
		}

		// ---------------------------------------
		// Module specifier property constraints
		// ---------------------------------------

		String effectiveModuleSpecifier = getModel().getEffectiveModuleSpecifier();

		// 1. The module specifier property must not be empty
		if (effectiveModuleSpecifier.equals("")) {
			return error(ErrorMessages.MODULE_SPECIFIER_MUST_NOT_BE_EMPTY);
		}

		// 2. The module specifier is properly formed
		String[] moduleSpecifierSegments = effectiveModuleSpecifier.split("/", -1);
		String validatedSpecifier = "";
		for (String segment : moduleSpecifierSegments) {

			// First segment is empty that means the specifier begins with a '/'
			if (segment == moduleSpecifierSegments[0] && segment.equals("")) {
				return error(ErrorMessages.INVALID_MODULE_SPECIFIER_MUST_NOT_BEGIN_WITH
						+ errorPointer(effectiveModuleSpecifier,
								validatedSpecifier));
			}
			// Segment is empty
			if (segment.equals("")) {
				return error(ErrorMessages.INVALID_MODULE_SPECIFIER_EMPTY_PATH_SEGMENT
						+ errorPointer(effectiveModuleSpecifier, validatedSpecifier));
			}
			// If the current segment is an invalid folder name
			if (!isValidFolderName(segment)) {
				return error(ErrorMessages.INVALID_MODULE_SPECIFIER_INVALID_SEGMENT
						+ errorPointer(effectiveModuleSpecifier, validatedSpecifier));
			} else {
				validatedSpecifier += segment;
			}
			validatedSpecifier += "/";
		}

		IProject moduleProject = ResourcesPlugin.getWorkspace().getRoot().getProject(model.getProject().toString());
		IPath effectiveModulePath = new Path(model.getEffectiveModuleSpecifier());

		IPath n4jsdPath = model.getSourceFolder()
				.append(effectiveModulePath.addFileExtension(N4JSGlobals.N4JSD_FILE_EXTENSION));
		IPath n4jsPath = model.getSourceFolder()
				.append(effectiveModulePath.addFileExtension(N4JSGlobals.N4JS_FILE_EXTENSION));

		if (model.isExternal() && moduleProject.exists(n4jsPath)) {
			return error("The new definition module collides with the source file "
					+ n4jsPath);
		} else if (!model.isExternal() && moduleProject.exists(n4jsdPath)) {
			return error("The new source module collides with the definition file "
					+ n4jsdPath);
		}

		// ---------------------------------------
		// Super class property constraints
		// ---------------------------------------

		ClassifierReference superClassRef = getModel().getSuperClass();

		if (!superClassRef.getFullSpecifier().isEmpty())

		{
			if (!isValidClass(superClassRef)) {
				return error(ErrorMessages.THE_SUPER_CLASS_CANNOT_BE_FOUND);
			} else if (superClassRef.uri == null) {
				IEObjectDescription classDescription = classifierObjectDescriptionForAbsoluteSpecifier(
						superClassRef.getFullSpecifier());
				if (classDescription != null) {
					superClassRef.uri = classDescription.getEObjectURI();
				}
			}
		}

		// ---------------------------------------
		// Interfaces property constraints
		// ---------------------------------------

		// For declaration see above at Automatic Regulative constraints
		interfaces = new ArrayList<>(getModel().getInterfaces());

		for (int i = 0; i < interfaces.size(); i++) {
			ClassifierReference iface = interfaces.get(i);
			if (!isValidInterface(iface)) {
				return error(
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

		// No error, return positive result
		this.setValidationResult(new ValidationResult());
		return this.getValidationResult();

	}

	/**
	 * Helper method for validation. Creates a validation result with given error message and sets it as the model
	 * validation result.
	 *
	 * @param msg
	 *            The error message to display.
	 * @return The created validation result.
	 */
	private ValidationResult error(String msg) {
		ValidationResult result = new ValidationResult(msg);
		this.setValidationResult(result);
		return result;
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
	 * @return The last validation result
	 */
	public ValidationResult getValidationResult() {
		return validationResult;
	}

	private void setValidationResult(ValidationResult validationResult) {
		this.firePropertyChange(VALIDATION_RESULT, this.validationResult, this.validationResult = validationResult);
	}

	/**
	 * @return True if the project property is valid
	 */
	public boolean getProjectValid() {
		return projectValid;
	}

	/**
	 *
	 * @param projectValid
	 *            The new validity of the project property
	 */
	private void setProjectValid(boolean projectValid) {
		this.firePropertyChange(PROJECT_PROPERTY_VALID, this.projectValid, this.projectValid = projectValid);
	}

	/**
	 * @return True if the source folder property is valid
	 */
	public boolean getSourceFolderValid() {
		return sourceFolderValid;
	}

	/**
	 *
	 * @param sourceFolderValid
	 *            The new validity of the source folder property
	 */
	private void setSourceFolderValid(boolean sourceFolderValid) {
		this.firePropertyChange(SOURCE_FOLDER_PROPERTY_VALID, this.sourceFolderValid,
				this.sourceFolderValid = sourceFolderValid);
	}

	/**
	 * @return The model currently validated
	 */
	public N4JSClassWizardModel getModel() {
		return model;
	}

	/**
	 * Set the model to validate
	 *
	 * @param model
	 *            The new model to validate
	 */
	public void setModel(N4JSClassWizardModel model) {
		this.model = model;

		// Reset states and revalidate
		this.setSourceFolderValid(false);
		this.setProjectValid(false);
		this.validate();
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
