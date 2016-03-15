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
package eu.numberfour.n4js.ui.wizard.interfacewizard;

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
 * A data model to hold the information of a {@link N4JSNewInterfaceWizard}
 *
 * @author luca.beurer-kellner - Initial contribution and API
 */
public class N4JSInterfaceWizardModel extends WorkspaceWizardModel
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
	public static final String N4JS_PROPERTY = "n4jsAnnotated";
	/** The super class property constant */
	public static final String INTERFACES_PROPERTY = InterfacesContainingModel.INTERFACES_PROPERTY;

	/**
	 * Property storage
	 */
	private String interfaceName = "";
	private boolean definitionFile = false;

	private AccessModifier accessModifier = AccessModifier.PUBLIC;
	private boolean internal = false;

	private boolean n4jsAnnotation = false;

	private ArrayList<ClassifierReference> interfaces = new ArrayList<>();

	@Override
	public boolean isDefinitionFile() {
		return definitionFile;
	}

	@Override
	public void setDefinitionFile(boolean definitionFile) {
		this.firePropertyChange(DEFINITION_FILE_PROPERTY, this.definitionFile, this.definitionFile = definitionFile);
	}

	@Override
	public String getName() {
		return interfaceName;
	}

	@Override
	public void setName(String name) {
		this.firePropertyChange(NAME_PROPERTY, this.interfaceName, this.interfaceName = name);
	}

	@Override
	public AccessModifier getAccessModifier() {
		return this.accessModifier;
	}

	@Override
	public void setAccessModifier(AccessModifier accessModifier) {
		this.firePropertyChange(ACCESS_MODIFIER_PROPERTY, this.accessModifier, this.accessModifier = accessModifier);
	}

	@Override
	public boolean isInternal() {
		return internal;
	}

	@Override
	public void setInternal(boolean internalAnnotation) {
		this.firePropertyChange(INTERNAL_PROPERTY, this.internal, this.internal = internalAnnotation);
	}

	@Override
	public void setInterfaces(List<ClassifierReference> interfaces) {
		firePropertyChange(INTERFACES_PROPERTY, new ArrayList<>(this.interfaces),
				this.interfaces = new ArrayList<>(interfaces));
	}

	@Override
	public void addInterface(ClassifierReference iface) {
		firePropertyChange(INTERFACES_PROPERTY, new ArrayList<>(this.interfaces),
				this.interfaces.add(iface) ? this.interfaces : this.interfaces);

	}

	@Override
	public void removeInterface(ClassifierReference iface) {
		firePropertyChange(INTERFACES_PROPERTY, new ArrayList<>(this.interfaces),
				this.interfaces.remove(iface) ? this.interfaces : this.interfaces);
	}

	@Override
	public List<ClassifierReference> getInterfaces() {
		return this.interfaces;
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
	 * Dependent on the set module specifier this method returns its identity or the module specifier concatenated with
	 * the class name. (set module specifiers ending with a '/' character are interpreted as base path)
	 *
	 * @return The effective module specifier.
	 */
	public String getEffectiveModuleSpecifier() {
		String effectiveModuleSpecifier = super.getModuleSpecifier();
		// If Separator at the end or empty specifier auto attach class name
		if (effectiveModuleSpecifier.length() < 1
				|| (effectiveModuleSpecifier.length() > 0
						&& effectiveModuleSpecifier.lastIndexOf('/') == effectiveModuleSpecifier.length() - 1)) {
			effectiveModuleSpecifier = effectiveModuleSpecifier + this.getName();
		}
		return effectiveModuleSpecifier;
	}

	/**
	 * @return True if the @N4JS annotation is set
	 */
	public boolean isN4jsAnnotated() {
		return n4jsAnnotation;
	}

	/**
	 * @param n4jsAnnotation
	 *            The new N4JS annotation state
	 */
	public void setN4jsAnnotated(boolean n4jsAnnotation) {
		this.firePropertyChange(N4JS_PROPERTY, this.n4jsAnnotation, this.n4jsAnnotation = n4jsAnnotation);
	}

}
