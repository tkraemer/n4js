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

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * Holds the ui swt code to instantiate and layout the wizard interface.
 *
 * All of the interface logic is performed in {@link N4JSNewClassWizardPage}.
 */
public class N4JSNewClassMainForm extends Composite {

	private final Text projectText;
	private final Text sourceFolderText;
	private final Text classNameText;
	private final Button definitionFileBox;
	private final Button projectBrowseButton;
	private final Button sourceFolderBrowseButton;
	private final Button moduleSpecifierBrowseButton;
	private final Button privateAccessModifierBox;
	private final Button projectAccessModifierBox;
	private final Button internalAnnotationBox;
	private final Button publicAccessModifierBox;
	private final Button n4jsAnnotationBox;
	private final Button finalAnnotationBox;
	private final Text superClassText;
	private final Button superClassBrowseButton;
	private final Table interfacesTable;
	private final Button interfacesAddButton;
	private final Button interfacesRemoveButton;
	private final SuffixText moduleSpecifierSuffixText;

	/**
	 * Create the composite.
	 *
	 */
	public N4JSNewClassMainForm(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite mainGrid = new Composite(this, SWT.NONE);
		mainGrid.setLayout(new GridLayout(3, false));

		Label projectLabel = new Label(mainGrid, SWT.NONE);
		projectLabel.setText("Project:");
		projectLabel.setLayoutData(GridDataFactory.swtDefaults().create());

		projectText = new Text(mainGrid, SWT.BORDER);
		projectText.setLayoutData(fillTextDefaults());

		projectBrowseButton = new Button(mainGrid, SWT.NONE);
		projectBrowseButton.setToolTipText("Opens a dialog to choose the project");
		projectBrowseButton.setText("Browse...");

		Label sourceFolderLabel = new Label(mainGrid, SWT.NONE);
		sourceFolderLabel.setText("Source folder:");
		sourceFolderLabel.setLayoutData(fillLabelDefaults());

		sourceFolderText = new Text(mainGrid, SWT.BORDER);
		sourceFolderText.setLayoutData(fillTextDefaults());

		sourceFolderBrowseButton = new Button(mainGrid, SWT.NONE);
		sourceFolderBrowseButton.setToolTipText("Opens a dialog to choose the source folder");
		sourceFolderBrowseButton.setText("Browse...");

		Label moduleSpecifierLabel = new Label(mainGrid, SWT.NONE);
		moduleSpecifierLabel.setLayoutData(fillLabelDefaults());
		moduleSpecifierLabel.setText("Module specifier:");

		moduleSpecifierSuffixText = new SuffixText(mainGrid, SWT.BORDER);
		getModuleSpecifierText().setLayoutData(fillTextDefaults());

		moduleSpecifierBrowseButton = new Button(mainGrid, SWT.NONE);
		moduleSpecifierBrowseButton.setToolTipText("Opens a dialog to choose the module specifier");
		moduleSpecifierBrowseButton.setText("Browse...");

		insertHorizontalSeparator(mainGrid);

		Label classNameLabel = new Label(mainGrid, SWT.NONE);
		classNameLabel.setText("Name:");

		classNameText = new Text(mainGrid, SWT.BORDER);
		classNameText.setLayoutData(fillTextDefaults());

		emptyGridCell(mainGrid);
		emptyGridCell(mainGrid);

		emptyGridCell(mainGrid);
		emptyGridCell(mainGrid);

		Label definitionFileLabel = new Label(mainGrid, SWT.NONE);
		GridData gd_lblExterna = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblExterna.heightHint = 15;
		definitionFileLabel.setLayoutData(gd_lblExterna);
		definitionFileLabel.setText("Definition file:");

		definitionFileBox = new Button(mainGrid, SWT.CHECK);
		definitionFileBox.setLayoutData(fillLabelDefaults());

		emptyGridCell(mainGrid);

		Label accessModifierLabel = new Label(mainGrid, SWT.NONE);
		accessModifierLabel.setLayoutData(fillLabelDefaults());
		accessModifierLabel.setText("Access modifier:");

		Composite accessModifiersComposite = new Composite(mainGrid, SWT.NONE);
		accessModifiersComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData accessModifiersCompositeLayoutData = fillTextDefaults();
		accessModifiersCompositeLayoutData.horizontalSpan = 2;
		accessModifiersComposite.setLayoutData(accessModifiersCompositeLayoutData);

		publicAccessModifierBox = new Button(accessModifiersComposite, SWT.RADIO);
		publicAccessModifierBox.setText("public");

		projectAccessModifierBox = new Button(accessModifiersComposite, SWT.RADIO);
		projectAccessModifierBox.setText("project");

		privateAccessModifierBox = new Button(accessModifiersComposite, SWT.RADIO);
		privateAccessModifierBox.setText("private");

		internalAnnotationBox = new Button(accessModifiersComposite, SWT.CHECK);
		internalAnnotationBox.setText("@Internal");

		Label otherModifiersLabel = new Label(mainGrid, SWT.NONE);
		otherModifiersLabel.setText("Other modifiers:");

		Composite otherModifierComposite = new Composite(mainGrid, SWT.NONE);
		otherModifierComposite.setLayout(new RowLayout(SWT.HORIZONTAL));

		finalAnnotationBox = new Button(otherModifierComposite, SWT.CHECK);
		finalAnnotationBox.setText("@Final");

		n4jsAnnotationBox = new Button(otherModifierComposite, SWT.CHECK);
		n4jsAnnotationBox.setText("@N4JS");

		emptyGridCell(mainGrid);
		emptyGridCell(mainGrid);

		emptyGridCell(mainGrid);
		emptyGridCell(mainGrid);

		Label superClassLabel = new Label(mainGrid, SWT.NONE);
		superClassLabel.setText("Super class:");

		superClassText = new Text(mainGrid, SWT.BORDER);
		superClassText.setLayoutData(fillTextDefaults());

		superClassBrowseButton = new Button(mainGrid, SWT.NONE);
		superClassBrowseButton.setToolTipText("Opens a dialog to choose the super class");
		superClassBrowseButton.setText("Browse...");

		Label interfacesLabel = new Label(mainGrid, SWT.NONE);
		interfacesLabel.setLayoutData(fillLabelDefaults());
		interfacesLabel.setText("Interfaces:");

		interfacesTable = new Table(mainGrid, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
		interfacesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite interfacesButtonsComposite = new Composite(mainGrid, SWT.NONE);
		interfacesButtonsComposite.setLayout(new RowLayout(SWT.VERTICAL));

		interfacesAddButton = new Button(interfacesButtonsComposite, SWT.NONE);
		interfacesAddButton.setLayoutData(new RowData(69, -1));
		interfacesAddButton.setText("Add...");

		interfacesRemoveButton = new Button(interfacesButtonsComposite, SWT.NONE);
		interfacesRemoveButton.setLayoutData(new RowData(68, -1));
		interfacesRemoveButton.setText("Remove");
	}

	/** Default GridData for text widgets */
	private GridData fillTextDefaults() {
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = false;
		data.horizontalAlignment = SWT.FILL;
		data.verticalAlignment = SWT.CENTER;
		return data;
	}

	/** Default GridData for all labels */
	private GridData fillLabelDefaults() {
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = false;
		data.grabExcessVerticalSpace = false;
		data.horizontalAlignment = SWT.LEFT;
		data.verticalAlignment = SWT.CENTER;
		return data;
	}

	/** Horizontal line separator */
	private Label insertHorizontalSeparator(Composite parent) {
		Label label = new Label(parent, SWT.SEPARATOR | SWT.WRAP | SWT.HORIZONTAL | SWT.SHADOW_NONE);
		GridData separatorLayoutData = new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1);
		separatorLayoutData.heightHint = 25;
		label.setLayoutData(separatorLayoutData);
		return label;
	}

	private Label emptyGridCell(Composite parent) {
		return new Label(parent, SWT.NONE);
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/** The project text input */
	public Text getProjectText() {
		return projectText;
	}

	/** The project browse button */
	public Button getProjectBrowseButton() {
		return projectBrowseButton;
	}

	/** The source folder text input */
	public Text getSourceFolderText() {
		return sourceFolderText;
	}

	/** The source folder browse button */
	public Button getSourceFolderBrowseButton() {
		return sourceFolderBrowseButton;
	}

	/** The module specifier text input */
	public SuffixText getModuleSpecifierText() {
		return moduleSpecifierSuffixText;
	}

	/** The module specifier browse button */
	public Button getModuleSpecifierBrowseButton() {
		return moduleSpecifierBrowseButton;
	}

	/** The class name text input */
	public Text getClassNameText() {
		return classNameText;
	}

	/** The definition file box */
	public Button getDefinitionFileBox() {
		return definitionFileBox;
	}

	/** The private access modifier checkbox */
	public Button getPrivateAccessModifierBox() {
		return privateAccessModifierBox;
	}

	/** The project access modifier checkbox */
	public Button getProjectAccessModifierBox() {
		return projectAccessModifierBox;
	}

	/** The internal annotation checkbox */
	public Button getInternalAnnotationBox() {
		return internalAnnotationBox;
	}

	/** The public access modifier checkbox */
	public Button getPublicAccessModifierBox() {
		return publicAccessModifierBox;
	}

	/** The N4JS annotation checkbox */
	public Button getN4jsAnnotationBox() {
		return n4jsAnnotationBox;
	}

	/** The final annotation checkbox */
	public Button getFinalAnnotationBox() {
		return finalAnnotationBox;
	}

	/** The private access modifier checkbox */
	public Text getSuperClassText() {
		return superClassText;
	}

	/** The super class browse button */
	public Button getSuperClassBrowseButton() {
		return superClassBrowseButton;
	}

	/** The interfaces table */
	public Table getInterfacesTable() {
		return interfacesTable;
	}

	/** The interfaces add button */
	public Button getInterfacesAddButton() {
		return interfacesAddButton;
	}

	/** The interfaces remove button */
	public Button getInterfacesRemoveButton() {
		return interfacesRemoveButton;
	}
}
