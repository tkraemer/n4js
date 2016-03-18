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
package eu.numberfour.n4js.n4mf.ui.wizard;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_UNDERSCORE;
import static com.google.common.collect.Lists.newArrayList;
import static eu.numberfour.n4js.n4mf.ProjectType.API;
import static eu.numberfour.n4js.n4mf.ProjectType.LIBRARY;
import static java.lang.String.valueOf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.xtext.ui.wizard.DefaultProjectInfo;

import eu.numberfour.n4js.n4mf.ProjectType;

/**
 * Simple POJO model for storing the N4 project setup configuration.
 */
public class N4MFProjectInfo extends DefaultProjectInfo {

	/** The name of the project location property name. Used by JFace data binding. */
	public static final String PROJECT_LOCATION_PROP_NAME = "projectLocation";

	/** The name of the project type property name. Used by JFace data binding. */
	public static final String PROJECT_TYPE_PROP_NAME = "projectType";

	/** Name of the implementation ID property. Used by SWT data binding. */
	public static final String IMPLEMENTATION_ID_PROP_NAME = "implementationId";

	/** Name of the implementation ID property. Used by SWT data binding. */
	public static final String IMPLEMENTED_APIS_PROP_NAME = "implementedProjects";

	/** Tested Project of a test project */
	public static final String TESTED_PROJECT_PROP_NAME = "testedProjects";

	/** Property to specify whether a test project should have an additional normal source folder */
	public static final String ADDITIONAL_NORMAL_SOURCE_FOLDER_PROP_NAME = "additionalSourceFolder";

	/** The custom project location. {@code null} if there is not custom project location set. */
	private IPath projectLocation;

	/** The type of the N4 project. By default: {@link ProjectType#LIBRARY system}. */
	private ProjectType projectType = LIBRARY;

	/** The optional implementation ID. */
	private String implementationId;

	/** The implemented API project IDs. */
	private List<String> implementedProjects = newArrayList();

	/** WorkingSets the new Project will be included to. */
	private IWorkingSet[] selectedWorkingSets;

	/** The tested project in case of a test project */
	private List<String> testedProjects = new ArrayList<>();

	/** Specifies whether a test project should have an additional normal source folder */
	private boolean additionalSourceFolder;

	/** The list of project dependencies */
	private List<String> projectDependencies = new ArrayList<>();

	/** The output folder of the project */
	private String outputFolder;

	/** Different source folders of the project */
	private List<String> sourceFolders = new ArrayList<>();
	private List<String> externalSourceFolders = new ArrayList<>();
	private List<String> testSourceFolders = new ArrayList<>();

	/**
	 * Returns with the project type as a lower camel case formatted string. This can be used directly for the N4
	 * manifest.
	 *
	 * @return the project type for the manifest file.
	 */
	public String getProjectTypeForManifest() {
		if (API.equals(projectType)) {
			return API.getLiteral();
		}
		return UPPER_UNDERSCORE.to(LOWER_CAMEL, valueOf(projectType));
	}

	/**
	 * Returns with the project location path. Can be {@code null}.
	 *
	 * @return the project location path.
	 */
	public IPath getProjectLocation() {
		return projectLocation;
	}

	/**
	 * Counterpart of {@link #getProjectLocation()}. Set the project location as the given argument.
	 *
	 * @param projectLocation
	 *            the project location to set.
	 */
	public void setProjectLocation(IPath projectLocation) {
		this.projectLocation = projectLocation;
	}

	/**
	 * Returns with the {@link ProjectType project type}.
	 *
	 * @return the project type.
	 */
	public ProjectType getProjectType() {
		return projectType;
	}

	/**
	 * Counterpart of the {@link #getProjectType()}. Sets the project type for the desired argument value.
	 *
	 * @param projectType
	 *            the project type.
	 */
	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	/** */
	public IWorkingSet[] getSelectedWorkingSets() {
		return selectedWorkingSets;
	}

	/** */
	public void setSelectedWorkingSets(IWorkingSet[] selectedWorkingSets) {
		this.selectedWorkingSets = selectedWorkingSets;
	}

	/**
	 * Returns with the implementation ID. Optional, can be {@code null}.
	 *
	 * @return the implementation ID, or {@code null} if not specified.
	 */
	public String getImplementationId() {
		return implementationId;
	}

	/**
	 * Counterpart of the {@link #getImplementationId()}.
	 *
	 * @param implementationId
	 *            the desired implementation ID value. Optional, can be {@code null}.
	 */
	public void setImplementationId(String implementationId) {
		this.implementationId = implementationId;
	}

	/**
	 * Returns with a list of API projects IDs to implement.
	 *
	 * @return a list of API project artifact IDs.
	 */
	public List<String> getImplementedProjects() {
		return implementedProjects;
	}

	/**
	 * Counterpart of {@link #getImplementedProjects()}.
	 *
	 * @param implementedApis
	 *            the list of implemented API project IDs to set.
	 */
	public void setImplementedProjects(List<String> implementedApis) {
		this.implementedProjects = implementedApis;
	}

	/**
	 * Returns the tested project for a test project
	 */
	public List<String> getTestedProjects() {
		return this.testedProjects;
	}

	/**
	 * Sets the tested project for a test project
	 */
	public void setTestedProjects(List<String> testedProject) {
		this.testedProjects = testedProject;
	}

	/**
	 * Returns whether a test project should have an additional normal source folder.
	 *
	 * Does not hold any useful information if project type is not test.
	 */
	public boolean getAdditionalSourceFolder() {
		return additionalSourceFolder;
	}

	/**
	 * Sets whether a test project should have an additional normal source folder.
	 */
	public void setAdditionalSourceFolder(boolean additionalSourceFolder) {
		this.additionalSourceFolder = additionalSourceFolder;
	}

	/**
	 * Returns the project dependencies of the project.
	 *
	 * Note: The return value is a mutable reference.
	 */
	public List<String> getProjectDependencies() {
		return projectDependencies;
	}

	/**
	 * Sets the project dependencies of the project.
	 */
	public void setProjectDependencies(List<String> projectDependencies) {
		this.projectDependencies = projectDependencies;
	}

	/**
	 * Returns the output folder of the project.
	 */
	public String getOutputFolder() {
		return outputFolder;
	}

	/**
	 * Sets the output folder of the project.
	 */
	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	/**
	 * Returns the source folders of the project.
	 *
	 * Note: The return value is a mutable reference.
	 */
	public List<String> getSourceFolders() {
		return sourceFolders;
	}

	/**
	 * Sets the source folders of the project
	 */
	public void setSourceFolders(List<String> sourceFolders) {
		this.sourceFolders = sourceFolders;
	}

	/**
	 * Returns the external source folders of the project.
	 *
	 * Note: The return value is a mutable reference.
	 *
	 */
	public List<String> getExternalSourceFolders() {
		return externalSourceFolders;
	}

	/**
	 * Sets the source folders of the project
	 *
	 */
	public void setExternalSourceFolders(List<String> externalSourceFolders) {
		this.externalSourceFolders = externalSourceFolders;
	}

	/**
	 * Returns the test source folders of the project
	 *
	 * Note: The return value is a mutable reference.
	 */
	public List<String> getTestSourceFolders() {
		return testSourceFolders;
	}

	/**
	 * Sets the test source folders of the project
	 */
	public void setTestSourceFolders(List<String> testSourceFolders) {
		this.testSourceFolders = testSourceFolders;
	}

}
