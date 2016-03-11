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
import java.util.List;
import java.util.StringJoiner;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.naming.QualifiedName;

import eu.numberfour.n4js.N4JSGlobals;

/**
 * A data model to hold the informations of a {@link N4JSNewClassWizard}
 */
public class N4JSClassWizardModel {

	/**
	 * Helper type for access modifiers
	 */
	public enum AccessModifier {
		/** For public visibility */
		PUBLIC,
		/** For project visibility */
		PROJECT,
		/** For private visibility */
		PRIVATE
	}

	/**
	 * Helper type to represent classifier references in a wizard. ClassifierReferences may be resolved or unresolved. *
	 * <p>
	 * Unresolved classifier references do not have an URI. They are meant to mainly exist in an intermediate step
	 * between user input and validation. The validation process should add the corresponding uri or leave it empty if
	 * the specifier is invalid. Invalid specifiers are reported as an error.
	 * </p>
	 */
	public static class ClassifierReference {
		/**
		 * Absolute specifier of the module containing the classifier
		 */
		public String classifierModuleSpecifier;

		/**
		 * Name of the classifier
		 */
		public String classifierName;

		/**
		 * Platform uri of the classifier declaration.
		 */
		public URI uri;

		/**
		 * Creates a unresolved classifier reference.
		 *
		 * @param classifierModuleSpecifier
		 *            Absolute specifier of the classifier
		 */
		public ClassifierReference(String classifierModuleSpecifier, String classifierName) {
			this.classifierModuleSpecifier = classifierModuleSpecifier;
			this.classifierName = classifierName;
			this.uri = null;
		}

		/**
		 * Creates a resolved classifier reference. This means a classifier with a valid uri of its declaration.
		 *
		 * @param qualifiedName
		 *            QualifiedName of the classifier
		 * @param uri
		 *            Platform uri of the classifier declaration
		 */
		public ClassifierReference(QualifiedName qualifiedName, URI uri) {
			this.classifierName = qualifiedName.getLastSegment();
			List<String> frontSegments = qualifiedName.getSegments();
			if (frontSegments.size() > 0) {
				StringJoiner joiner = new StringJoiner(".");
				for (String segment : frontSegments.subList(0, frontSegments.size() - 1)) {
					joiner.add(segment);
				}
				this.classifierModuleSpecifier = joiner.toString();
			} else {
				this.classifierModuleSpecifier = "";
			}
			this.uri = uri;

		}

		/**
		 * Return the full classifier specifier. For existing classifiers this is a string representation of its
		 * qualified name.
		 *
		 * @return The full classifier specifier
		 */
		public String getFullSpecifier() {
			if (!this.classifierName.isEmpty())
				return (this.classifierModuleSpecifier.isEmpty() ? "" : this.classifierModuleSpecifier + ".")
						+ this.classifierName;
			return "";
		}

		/**
		 * Return true if this ClassifierReference is a complete classifier reference data structure.
		 *
		 * This method helps to determine if the reference is in a valid or an intermediate state where it still needs
		 * to be processed by the model validator.
		 *
		 * <p>
		 * Note: No existence/visibility validation takes place as this method only assures the data structure is
		 * complete.
		 * </p>
		 *
		 * @return True on completeness, false otherwise
		 */
		public boolean isComplete() {
			if (!this.classifierName.isEmpty() && !this.classifierModuleSpecifier.isEmpty() && this.uri != null) {
				return true;
			}
			return false;
		}

	}

	// Property names for databinding

	/** The project property constant */
	public static final String PROJECT_PROPERTY = "project";
	/** The source folder property constant */
	public static final String SOURCE_FOLDER_PROPERTY = "sourceFolder";
	/** The module specifier property constant */
	public static final String MODULE_SPECIFIER_PROPERTY = "moduleSpecifier";
	/** The class name property constant */
	public static final String CLASS_NAME_PROPERTY = "className";
	/** The external property constant */
	public static final String EXTERNAL_PROPERTY = "external";
	/** The access modifier property constant */
	public static final String ACCESS_MODIFIER_PROPERTY = "accessModifier";
	/** The internal annotation property property constant */
	public static final String INTERNAL_PROPERTY = "internal";
	/** The final annotations property property constant */
	public static final String FINAL_PROPERTY = "final";
	/** The n4js annotation property constant */
	public static final String N4JS_PROPERTY = "n4jsAnnotated";
	/** The super class property constant */
	public static final String SUPER_CLASS_PROPERTY = "superClass";
	/** The interfaces property constant */
	public static final String INTERFACES_PROPERTY = "interfaces";

	/**
	 * Property storage
	 */

	private IPath project = new Path("");
	private IPath sourceFolder = new Path("");
	private String moduleSpecifier = "";

	private String className = "";
	private boolean external = false;

	private AccessModifier accessModifier = AccessModifier.PUBLIC;
	private boolean internal = false;

	private boolean finalAnnotation = false;
	private boolean n4jsAnnotation = false;

	private ClassifierReference superClass = new ClassifierReference("", "");
	private ArrayList<ClassifierReference> interfacesURIs = new ArrayList<>();

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
	 * Sets the class name
	 *
	 */
	public void setClassName(String className) {
		this.firePropertyChange(CLASS_NAME_PROPERTY, this.className, this.className = className);
	}

	/**
	 * Returns the class name
	 *
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * @return the saved project path
	 */
	public IPath getProject() {
		return project;
	}

	/**
	 * Sets the project path.
	 *
	 * @param project
	 *            new project path
	 */
	public void setProject(IPath project) {
		this.firePropertyChange(PROJECT_PROPERTY, this.project, this.project = project);
	}

	/**
	 * @return The saved source folder path
	 */
	public IPath getSourceFolder() {
		return sourceFolder;
	}

	/**
	 * Set a new source folder and use sender as sender for listeners
	 *
	 * @param sourceFolder
	 *            The new source folder
	 */
	public void setSourceFolder(IPath sourceFolder) {
		this.firePropertyChange(SOURCE_FOLDER_PROPERTY, this.sourceFolder, this.sourceFolder = sourceFolder);
	}

	/**
	 * @return the saved module specifier
	 */
	public String getModuleSpecifier() {
		return moduleSpecifier;
	}

	/**
	 * @return The effective module specifier. Dependent on the set module specifier this method returns its identity or
	 *         the module specifier concatenated with the class name. (set module specifiers ending with a '/' character
	 *         are interpreted as base path)
	 */
	public String getEffectiveModuleSpecifier() {
		String effectiveModuleSpecifier = moduleSpecifier;
		// If Separator at the end or empty specifier auto attach class name
		if (moduleSpecifier.length() < 1
				|| (moduleSpecifier.length() > 0 && moduleSpecifier.lastIndexOf('/') == moduleSpecifier.length() - 1)) {
			effectiveModuleSpecifier = moduleSpecifier + this.getClassName();
		}
		return effectiveModuleSpecifier;
	}

	/**
	 * Computes the file location in which the class is going to be created according to the current model.
	 *
	 * @return path of the location
	 */
	public IPath computeFileLocation() {
		return this.getProject().append(this.getSourceFolder()).append(this.getEffectiveModuleSpecifier())
				.addFileExtension(
						this.isExternal() ? N4JSGlobals.N4JSD_FILE_EXTENSION : N4JSGlobals.N4JS_FILE_EXTENSION);
	}

	/**
	 * @param moduleSpecifier
	 *            The new module specifier
	 */
	public void setModuleSpecifier(String moduleSpecifier) {
		this.firePropertyChange(MODULE_SPECIFIER_PROPERTY, this.moduleSpecifier,
				this.moduleSpecifier = moduleSpecifier);
	}

	/**
	 * @return True if external flag is set
	 */
	public boolean isExternal() {
		return external;
	}

	/**
	 * @param external
	 *            The new external state
	 */
	public void setExternal(boolean external) {
		this.firePropertyChange(EXTERNAL_PROPERTY, this.external, this.external = external);
	}

	/**
	 * @return The saved access modifier
	 */
	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	/**
	 * @param accessModifier
	 *            The new access modifier
	 */
	public void setAccessModifier(AccessModifier accessModifier) {
		this.firePropertyChange(ACCESS_MODIFIER_PROPERTY, this.accessModifier, this.accessModifier = accessModifier);
	}

	/**
	 * @return The state of the saved internal annotation
	 */
	public boolean isInternal() {
		return internal;
	}

	/**
	 * @param internalAnnotation
	 *            The new internal annotation
	 */
	public void setInternal(boolean internalAnnotation) {
		this.firePropertyChange(INTERNAL_PROPERTY, this.internal, this.internal = internalAnnotation);
	}

	/**
	 * @return The saved final annotation state
	 */
	public boolean isFinal() {
		return finalAnnotation;
	}

	/**
	 * @param finalAnnotation
	 *            The new final annotation state
	 */
	public void setFinal(boolean finalAnnotation) {
		this.firePropertyChange(FINAL_PROPERTY, this.finalAnnotation, this.finalAnnotation = finalAnnotation);
	}

	/**
	 * @return The saved @N4JS annotation state
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

	/**
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
	 * <p>
	 * Returns a reference to the internal interface list
	 * </p>
	 *
	 * @return The list of saved interfaces
	 */
	public ArrayList<ClassifierReference> getInterfaces() {
		return this.interfacesURIs;
	}

	/**
	 * @param interfaces
	 *            New list of interfaces
	 */
	public void setInterfaces(ArrayList<ClassifierReference> interfaces) {
		firePropertyChange(INTERFACES_PROPERTY, new ArrayList<>(this.interfacesURIs),
				this.interfacesURIs = interfaces);
	}

	/**
	 * Add a new interface uri to the list.
	 *
	 * @param iface
	 *            The new interface
	 */
	public void addInterface(ClassifierReference iface) {
		firePropertyChange(INTERFACES_PROPERTY, new ArrayList<>(this.interfacesURIs),
				this.interfacesURIs.add(iface) ? this.interfacesURIs : this.interfacesURIs);
	}

	/**
	 * Remove the given interface from the list.
	 *
	 *
	 * @param iface
	 *            interface to remove
	 */
	public void removeInterfaceURI(ClassifierReference iface) {
		firePropertyChange(INTERFACES_PROPERTY, new ArrayList<>(this.interfacesURIs),
				this.interfacesURIs.remove(iface) ? this.interfacesURIs : this.interfacesURIs);
	}
}
