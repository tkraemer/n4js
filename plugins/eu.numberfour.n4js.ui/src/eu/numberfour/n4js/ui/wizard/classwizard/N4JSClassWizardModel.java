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
import java.util.List;

import org.eclipse.core.runtime.IPath;

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.ui.wizard.model.AccessModifiableModel;
import eu.numberfour.n4js.ui.wizard.model.ClassifierReference;
import eu.numberfour.n4js.ui.wizard.model.DefinitionFileModel;
import eu.numberfour.n4js.ui.wizard.model.InterfacesContainingModel;
import eu.numberfour.n4js.ui.wizard.model.NamedModel;
import eu.numberfour.n4js.ui.wizard.workspacewizard.WorkspaceWizardModel;

/**
 * A data model to hold the informations of a {@link N4JSNewClassWizard}.
 */
public class N4JSClassWizardModel extends WorkspaceWizardModel
		implements InterfacesContainingModel, AccessModifiableModel, NamedModel, DefinitionFileModel {

	/** The class name property constant */
	public static final String NAME_PROPERTY = NamedModel.NAME_PROPERTY;
	/** The external property constant */
	public static final String DEFINITION_FILE_PROPERTY = DefinitionFileModel.DEFINITION_FILE_PROPERTY;
	/** The access modifier property constant */
	public static final String ACCESS_MODIFIER_PROPERTY = AccessModifiableModel.ACCESS_MODIFIER_PROPERTY;
	/** The internal annotation property property constant */
	public static final String INTERNAL_PROPERTY = AccessModifiableModel.INTERNAL_PROPERTY;
	/** The final annotations property property constant */
	public static final String FINAL_PROPERTY = "final";
	/** The n4js annotation property constant */
	public static final String N4JS_PROPERTY = "n4jsAnnotated";
	/** The super class property constant */
	public static final String SUPER_CLASS_PROPERTY = "superClass";
	/** The interfaces property constant */
	public static final String INTERFACES_PROPERTY = InterfacesContainingModel.INTERFACES_PROPERTY;

	/**
	 * Property storage
	 */

	private String className = "";
	private boolean external = false;

	private AccessModifier accessModifier = AccessModifier.PUBLIC;
	private boolean internal = false;

	private boolean finalAnnotation = false;
	private boolean n4jsAnnotation = false;

	private ClassifierReference superClass = new ClassifierReference("", "");
	private ArrayList<ClassifierReference> interfacesURIs = new ArrayList<>();

	/**
	 * Sets the class name.
	 *
	 */
	@Override
	public void setName(String className) {
		this.firePropertyChange(NAME_PROPERTY, this.className, this.className = className);
	}

	/**
	 * Returns the class name.
	 *
	 */
	@Override
	public String getName() {
		return this.className;
	}

	/**
	 * Returns the effective module specifier.
	 *
	 * Dependent on the set module specifier this method returns its identity or the module specifier concatenated with
	 * the class name. (set module specifiers ending with a '/' character are interpreted as base path)
	 *
	 * @return The effective module specifier.
	 */
	public String getEffectiveModuleSpecifier() {
		String effectiveModuleSpecifier = super.getModuleSpecifier();
		// If Separator at the end or empty specifier auto attach class name
		if (effectiveModuleSpecifier.isEmpty()
				|| (!effectiveModuleSpecifier.isEmpty()
						&& effectiveModuleSpecifier.lastIndexOf('/') == effectiveModuleSpecifier.length() - 1)) {
			effectiveModuleSpecifier = effectiveModuleSpecifier + this.getName();
		}
		return effectiveModuleSpecifier;
	}

	@Override
	public String getModuleSpecifier() {
		return getEffectiveModuleSpecifier();
	}

	/**
	 * Computes the file location in which the class is going to be created according to the current model.
	 *
	 * @return path of the location
	 */
	public IPath computeFileLocation() {
		return this.getProject().append(this.getSourceFolder()).append(this.getEffectiveModuleSpecifier())
				.addFileExtension(
						this.isDefinitionFile() ? N4JSGlobals.N4JSD_FILE_EXTENSION : N4JSGlobals.N4JS_FILE_EXTENSION);
	}

	/**
	 * Returns if the model class is meant to be in a definition file.
	 *
	 * @return True if external flag is set
	 */
	@Override
	public boolean isDefinitionFile() {
		return external;
	}

	/**
	 * Sets if the model class is meant to be in a definition file.
	 *
	 * @param definitionFile
	 *            The new external state
	 */
	@Override
	public void setDefinitionFile(boolean definitionFile) {
		this.firePropertyChange(DEFINITION_FILE_PROPERTY, this.external, this.external = definitionFile);
	}

	/**
	 * Returns the model class access modifier.
	 *
	 * @return The saved access modifier
	 */
	@Override
	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	/**
	 * Set the model class access modifier.
	 *
	 * @param accessModifier
	 *            The new access modifier
	 */
	@Override
	public void setAccessModifier(AccessModifier accessModifier) {
		this.firePropertyChange(ACCESS_MODIFIER_PROPERTY, this.accessModifier, this.accessModifier = accessModifier);
	}

	/**
	 * Returns if the model class is internal.
	 *
	 * @return The state of the saved internal annotation
	 */
	@Override
	public boolean isInternal() {
		return internal;
	}

	/**
	 * Sets the model class internal annotation.
	 *
	 * @param internalAnnotation
	 *            The new internal annotation
	 */
	@Override
	public void setInternal(boolean internalAnnotation) {
		this.firePropertyChange(INTERNAL_PROPERTY, this.internal, this.internal = internalAnnotation);
	}

	/**
	 * Returns if the model class is final.
	 *
	 * @return The final annotation state
	 */
	public boolean isFinal() {
		return finalAnnotation;
	}

	/**
	 * Sets the the model class.
	 *
	 * @param finalAnnotation
	 *            The new final annotation state
	 */
	public void setFinal(boolean finalAnnotation) {
		this.firePropertyChange(FINAL_PROPERTY, this.finalAnnotation, this.finalAnnotation = finalAnnotation);
	}

	/**
	 * Returns if the model class is n4js annotated.
	 *
	 * @return The N4JS annotation state
	 */
	public boolean isN4jsAnnotated() {
		return n4jsAnnotation;
	}

	/**
	 * Sets the model class n4js annotation.
	 *
	 * @param n4jsAnnotation
	 *            The new N4JS annotation state
	 */
	public void setN4jsAnnotated(boolean n4jsAnnotation) {
		this.firePropertyChange(N4JS_PROPERTY, this.n4jsAnnotation, this.n4jsAnnotation = n4jsAnnotation);
	}

	/**
	 * Returns the model class super class.
	 * <p>
	 * Note: May be null for no super class
	 * </p>
	 *
	 * @return The uri to the specified super class
	 */
	public ClassifierReference getSuperClass() {
		return superClass;
	}

	/**
	 * Set the new super class uri. May be null to specify no super class.
	 *
	 * @param superClass
	 *            The new super class uri
	 */
	public void setSuperClass(ClassifierReference superClass) {
		this.firePropertyChange(SUPER_CLASS_PROPERTY, this.superClass, this.superClass = superClass);
	}

	/**
	 * Returns a reference to the internal interface list.
	 *
	 * @return The list of saved interfaces
	 */
	@Override
	public List<ClassifierReference> getInterfaces() {
		return this.interfacesURIs;
	}

	/**
	 * Sets the model class implemented interfaces.
	 *
	 * @param interfaces
	 *            New list of interfaces
	 */
	@Override
	public void setInterfaces(List<ClassifierReference> interfaces) {
		firePropertyChange(INTERFACES_PROPERTY, new ArrayList<>(this.interfacesURIs),
				this.interfacesURIs = new ArrayList<>(interfaces));
	}

	/**
	 * Adds a new implemented interface.
	 *
	 * @param iface
	 *            The new interface
	 */
	@Override
	public void addInterface(ClassifierReference iface) {
		firePropertyChange(INTERFACES_PROPERTY, new ArrayList<>(this.interfacesURIs),
				this.interfacesURIs.add(iface) ? this.interfacesURIs : this.interfacesURIs);
	}

	/**
	 * Removes the given interface from the implemented interfaces.
	 *
	 *
	 * @param iface
	 *            interface to remove
	 */
	@Override
	public void removeInterface(ClassifierReference iface) {
		firePropertyChange(INTERFACES_PROPERTY, new ArrayList<>(this.interfacesURIs),
				this.interfacesURIs.remove(iface) ? this.interfacesURIs : this.interfacesURIs);
	}
}
