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

import static com.google.common.base.CharMatcher.BREAKING_WHITESPACE;
import static com.google.common.base.CharMatcher.JAVA_LETTER;
import static com.google.common.base.CharMatcher.is;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Strings.nullToEmpty;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static eu.numberfour.n4js.n4mf.ProjectType.API;
import static eu.numberfour.n4js.n4mf.ProjectType.LIBRARY;
import static eu.numberfour.n4js.n4mf.ProjectType.SYSTEM;
import static eu.numberfour.n4js.n4mf.resource.N4MFResourceDescriptionStrategy.getProjectId;
import static eu.numberfour.n4js.n4mf.resource.N4MFResourceDescriptionStrategy.getProjectType;
import static eu.numberfour.n4js.n4mf.ui.internal.N4MFActivator.EU_NUMBERFOUR_N4JS_N4MF_N4MF;
import static eu.numberfour.n4js.n4mf.ui.wizard.N4MFProjectInfo.IMPLEMENTATION_ID_PROP_NAME;
import static eu.numberfour.n4js.n4mf.ui.wizard.N4MFProjectInfo.IMPLEMENTED_APIS_PROP_NAME;
import static eu.numberfour.n4js.n4mf.ui.wizard.N4MFProjectInfo.PROJECT_TYPE_PROP_NAME;
import static org.eclipse.jface.databinding.viewers.ViewersObservables.observeSingleSelection;
import static org.eclipse.jface.layout.GridDataFactory.fillDefaults;
import static org.eclipse.swt.SWT.BORDER;
import static org.eclipse.swt.SWT.CENTER;
import static org.eclipse.swt.SWT.CHECK;
import static org.eclipse.swt.SWT.FILL;
import static org.eclipse.swt.SWT.MULTI;
import static org.eclipse.swt.SWT.Modify;
import static org.eclipse.swt.SWT.READ_ONLY;
import static org.eclipse.xtext.util.Strings.toFirstUpper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.xtext.resource.IResourceDescriptions;

import com.google.inject.Injector;

import eu.numberfour.n4js.n4mf.N4mfPackage;
import eu.numberfour.n4js.n4mf.ProjectType;
import eu.numberfour.n4js.n4mf.ui.internal.N4MFActivator;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.ui.dialog.ProjectSelectionDialog;

/**
 * Wizard page for configuring a new N4 project.
 */
public class N4MFWizardNewProjectCreationPage extends WizardNewProjectCreationPage {

	private final N4MFProjectInfo projectInfo;
	private final IN4JSCore n4jsCore = getN4JSCore();

	/**
	 * Creates a new wizard page to set up and create a new N4 project with the given project info model.
	 *
	 * @param projectInfo
	 *            the project info model that will be used to initialize the new N4 project.
	 */
	public N4MFWizardNewProjectCreationPage(final N4MFProjectInfo projectInfo) {
		super(N4MFWizardNewProjectCreationPage.class.getName());
		this.projectInfo = projectInfo;
		setTitle("N4JS Project");
		setDescription("Create a new N4JS project.");
	}

	@Override
	public void createControl(final Composite parent) {
		super.createControl(parent); // We need to create the UI controls from the parent class.

		final Composite control = (Composite) getControl();
		control.setLayout(GridLayoutFactory.fillDefaults().create());
		control.setLayoutData(fillDefaults().align(FILL, FILL).grab(true, true).create());

		final DataBindingContext dbc = new DataBindingContext();
		control.addDisposeListener(e -> dbc.dispose());

		final ComboViewer projectType = new ComboViewer(control, READ_ONLY);
		projectType.setLabelProvider(new ProjectTypeLabelProvider());
		projectType.setContentProvider(ArrayContentProvider.getInstance());
		projectType.getControl().setLayoutData(fillDefaults().grab(true, false).create());
		projectType.setInput(ProjectType.values());

		// A composite to hold the changin UI (additional library project options / additional test project options)
		final Composite changingComposite = new Composite(control, NONE);
		StackLayout changingStackLayout = new StackLayout();
		changingComposite.setLayout(changingStackLayout);
		changingComposite.setLayoutData(fillDefaults().align(FILL, FILL).grab(true, true).create());

		// An empty composite to show for project types without additional options
		final Composite emptyOptions = new Composite(changingComposite, NONE);

		// Additional library project options
		final Composite libraryProjectOptionsComposite = new Composite(changingComposite, NONE);
		libraryProjectOptionsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		new Label(libraryProjectOptionsComposite, SWT.NONE).setText("Implementation ID:");
		final Text implementationIdText = new Text(libraryProjectOptionsComposite, BORDER);
		implementationIdText.setLayoutData(fillDefaults().align(FILL, CENTER).grab(true, false).create());

		final ListViewer apiViewer = new ListViewer(libraryProjectOptionsComposite, BORDER | MULTI);
		apiViewer.getControl().setLayoutData(fillDefaults().align(FILL, FILL).grab(true, true).span(2, 1).create());
		apiViewer.setContentProvider(ArrayContentProvider.getInstance());
		apiViewer.setInput(getAvailableApiProjectIds());

		// Additional test project options
		final Composite testProjectOptionsComposite = new Composite(changingComposite, NONE);
		testProjectOptionsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());

		new Label(testProjectOptionsComposite, SWT.NONE).setText("Tested Project:");
		// Project text
		final Text testedProjectText = new Text(testProjectOptionsComposite, SWT.BORDER);
		testedProjectText.setLayoutData(fillDefaults().align(FILL, CENTER).grab(true, false).create());
		// Browse button
		final Button testedProjectBrowse = new Button(testProjectOptionsComposite, NONE);
		testedProjectBrowse.setText("Browse...");

		emptyPlaceholder(testProjectOptionsComposite); // Just an empty placeholder
		final Button addNormalSourceFolderButton = new Button(testProjectOptionsComposite, CHECK);
		addNormalSourceFolderButton.setText("Also create a non-test source folder");

		initProjectTypeBinding(dbc, projectType);
		initImplementationIdBinding(dbc, implementationIdText);
		initApiViewerBinding(dbc, apiViewer);
		initTestProjectBinding(dbc, testedProjectText, testedProjectBrowse, addNormalSourceFolderButton);

		projectType.addPostSelectionChangedListener(e -> {
			switch (projectInfo.getProjectType()) {
			case LIBRARY:
				changingStackLayout.topControl = libraryProjectOptionsComposite;
				break;
			case TEST:
				changingStackLayout.topControl = testProjectOptionsComposite;
				break;
			default:
				changingStackLayout.topControl = emptyOptions;
			}
			changingComposite.layout(true);
			setPageComplete(validatePage());
		});

		implementationIdText.addModifyListener(e -> {
			setPageComplete(validatePage());
		});

		apiViewer.addSelectionChangedListener(e -> {
			setPageComplete(validatePage());
		});

		// IDs from: org.eclipse.jdt.internal.ui.workingsets.IWorkingSetIDs.class
		createWorkingSetGroup(
				(Composite) getControl(),
				null,
				new String[] { "org.eclipse.ui.resourceWorkingSetPage",
						"org.eclipse.jdt.ui.JavaWorkingSetPage",
						"org.eclipse.jdt.internal.ui.OthersWorkingSet"
				}); // $NON-NLS-1$
		Dialog.applyDialogFont(getControl());

		dbc.updateTargets();
	}

	private Control emptyPlaceholder(Composite parent) {
		return new Label(parent, NONE);
	}

	@Override
	protected boolean validatePage() {
		final boolean valid = super.validatePage();

		if (valid) {

			String errorMsg = null;
			final String projectName = getProjectName();

			if (LIBRARY.equals(projectInfo.getProjectType())) {

				final String implementationId = projectInfo.getImplementationId();

				// Implementation ID is optional
				if (!isNullOrEmpty(implementationId)) {

					final List<String> implementedApis = projectInfo.getImplementedApis();
					if (null == implementedApis || implementedApis.isEmpty()) {
						errorMsg = "One or more API project should be selected for implementation when the implementation ID is specified.";
					}

					if (BREAKING_WHITESPACE.matchesAnyOf(implementationId)) {
						errorMsg = "Implementation ID should not contain any whitespace characters.";
					}

					final char leadincChar = implementationId.charAt(0);
					if (!is('_').or(JAVA_LETTER).matches(leadincChar)) {
						errorMsg = "Implementation ID should start either an upper or a lower case character "
								+ "from the Latin alphabet or with the underscore character.";
					}
				}
			}

			if (SYSTEM.equals(projectInfo.getProjectType())) {
				errorMsg = "Project type 'System' is deprecated and will be removed soon. Use either 'API' or 'Library' instead.";
			}

			if (isNullOrEmpty(projectName)) {
				errorMsg = "Project name should be specified.";
			}

			if (BREAKING_WHITESPACE.matchesAnyOf(projectName)) {
				errorMsg = "Project name should not contain any whitespace characters.";
			}

			final char leadincChar = projectName.charAt(0);
			if (!is('_').or(JAVA_LETTER).matches(leadincChar)) {
				errorMsg = "Project name should start either an upper or a lower case character "
						+ "from the Latin alphabet or with the underscore character.";
			}

			setErrorMessage(errorMsg);
			if (null == errorMsg) {
				updateModel();
			}
			return null == errorMsg;
		}

		return valid;
	}

	/**
	 * Accessible reader from outside. Call before using the project info.
	 */
	public void updateSelectedWorkingSets() {
		projectInfo.setSelectedWorkingSets(getSelectedWorkingSets());
	}

	private void updateModel() {
		projectInfo.setProjectName(getProjectName());
		if (useDefaults()) {
			projectInfo.setProjectLocation(null);
		} else {
			projectInfo.setProjectLocation(getLocationPath());
		}
	}

	private void initTestProjectBinding(DataBindingContext dbc, Text testedProjectText, Button testedProjectBrowse,
			Button addNormalSourceFolderButton) {
		dbc.bindValue(WidgetProperties.text().observe(testedProjectText),
				PojoProperties.value(N4MFProjectInfo.class, N4MFProjectInfo.TESTED_PROJECT).observe(projectInfo));
		dbc.bindValue(WidgetProperties.selection().observe(addNormalSourceFolderButton),
				PojoProperties.value(N4MFProjectInfo.class, N4MFProjectInfo.ADDITIONAL_NORMAL_SOURCE_FOLDER)
						.observe(projectInfo));
		testedProjectBrowse.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProjectSelectionDialog dialog = new ProjectSelectionDialog(getShell());
				// Filter out N4JS test projects
				dialog.addFilter(new NonTestProjectFilter());

				dialog.open();

				if (dialog.getFirstResult() == null) {
					return;
				}

				// Use the result as new value
				Object result = dialog.getFirstResult();
				if (result instanceof IProject) {
					testedProjectText.setText(((IProject) result).getName());
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				/***/
			}
		});
	}

	/**
	 * Filter out all non-test N4JS projects
	 */
	private class NonTestProjectFilter extends ViewerFilter {
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof IProject) {
				IN4JSProject n4jsProject = n4jsCore
						.findProject(URI.createPlatformResourceURI(((IProject) element).getName(), true)).orNull();
				if (null != n4jsProject && n4jsProject.getProjectType() != ProjectType.TEST) {
					return true;
				}
			}
			return false;
		}

	}

	private void initProjectTypeBinding(final DataBindingContext dbc, final ComboViewer projectType) {
		dbc.bindValue(observeSingleSelection(projectType),
				PojoProperties.value(N4MFProjectInfo.class, PROJECT_TYPE_PROP_NAME).observe(projectInfo));
	}

	private void initImplementationIdBinding(final DataBindingContext dbc, final Text text) {
		dbc.bindValue(WidgetProperties.text(Modify).observe(text),
				PojoProperties.value(N4MFProjectInfo.class, IMPLEMENTATION_ID_PROP_NAME).observe(projectInfo));
	}

	private void initApiViewerBinding(DataBindingContext dbc, ListViewer apiViewer) {
		dbc.bindList(
				ViewersObservables.observeMultiSelection(apiViewer),
				PojoProperties.list(N4MFProjectInfo.class, IMPLEMENTED_APIS_PROP_NAME).observe(projectInfo));
	}

	private Collection<String> getAvailableApiProjectIds() {
		final Collection<String> distinctIds = from(
				getResourceDescriptions().getExportedObjectsByType(N4mfPackage.eINSTANCE.getProjectDescription()))
						.filter(desc -> API.equals(getProjectType(desc))).transform(desc -> getProjectId(desc))
						.filter(id -> null != id).toSet();
		final List<String> ids = newArrayList(distinctIds);
		Collections.sort(ids);
		return ids;
	}

	private IN4JSCore getN4JSCore() {
		return getInjector().getInstance(IN4JSCore.class);
	}

	private IResourceDescriptions getResourceDescriptions() {
		return getInjector().getInstance(IResourceDescriptions.class);
	}

	private Injector getInjector() {
		return N4MFActivator.getInstance().getInjector(EU_NUMBERFOUR_N4JS_N4MF_N4MF);
	}

	/**
	 * Label provider for converting the {@link ProjectType} literal into a human readable string.
	 */
	private static final class ProjectTypeLabelProvider extends LabelProvider {
		@Override
		public String getText(final Object element) {
			if (API.equals(element)) {
				return API.getLiteral();
			}
			if (SYSTEM.equals(element)) {
				return new StringBuilder(getDefaultText(element)).append(" [Deprecated]").toString();
			}
			return getDefaultText(element);
		}

		private String getDefaultText(final Object element) {
			return toFirstUpper(nullToEmpty(super.getText(element)).replaceAll("_", " ").toLowerCase());
		}
	}

}
