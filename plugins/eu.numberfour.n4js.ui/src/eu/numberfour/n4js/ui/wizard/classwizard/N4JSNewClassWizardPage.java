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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.ui.dialog.InterfacesSelectionDialog;
import eu.numberfour.n4js.ui.dialog.ModuleSpecifierSelectionDialog;
import eu.numberfour.n4js.ui.dialog.ProjectSelectionDialog;
import eu.numberfour.n4js.ui.dialog.SingleClassSelectionDialog;
import eu.numberfour.n4js.ui.dialog.SourceFolderSelectionDialogProvider;
import eu.numberfour.n4js.ui.dialog.WorkspaceElementSelectionDialog;
import eu.numberfour.n4js.ui.dialog.virtualresource.VirtualResource;
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSClassWizardModel.AccessModifier;
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSClassWizardModel.ClassifierReference;
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSClassWizardModelValidator.ValidationResult;
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSNewClassWizardConverters.ConditionalConverter;
import eu.numberfour.n4js.ui.wizard.classwizard.N4JSNewClassWizardConverters.StringToPathConverter;

/**
 * A wizard page to allow the user to specify the informations about the creation of a new class.
 *
 * <p>
 * Note: The wizard page is not meant to be used without setting a {@link N4JSClassWizardModel}. To accomplish this use
 * the {@link N4JSNewClassWizardPage#setModel(N4JSClassWizardModel)} method.
 * </p>
 */
public class N4JSNewClassWizardPage extends WizardPage {

	N4JSClassWizardModel model = null;
	DataBindingContext databindingContext;

	// Available after invocation of #createControl
	N4JSNewClassMainForm wizardForm = null;

	IObservableList interfacesValue = null;
	IObservableValue observableValidationValue = null;

	@Inject
	N4JSClassWizardModelValidator validator;

	// Browse dialogs
	@Inject
	SourceFolderSelectionDialogProvider sourceFolderSelectionDialogProvider;
	@Inject
	Provider<SingleClassSelectionDialog> superClassSelectionDialogProvider;
	@Inject
	Provider<InterfacesSelectionDialog> interfacesSelectionDialogProvider;

	/**
	 * Instantiates a New N4JS Class wizard main page
	 */
	public N4JSNewClassWizardPage() {
		super("Create a new N4JS Class");

		this.setTitle("New N4JS class");
		this.setMessage("Create a new N4JS Class");
		this.setPageComplete(false);
	}

	/**
	 * @param model
	 *            N4JSClassWizardModel to use
	 */
	public void setModel(N4JSClassWizardModel model) {
		this.model = model;
		this.validator.setModel(this.model);
		this.model.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				validator.validate();
			}
		});
	}

	/**
	 * Invoked when the validation result of the model has changed.
	 *
	 * @param result
	 *            The most recent validation result
	 */
	private void onValidationChange(ValidationResult result) {
		if (result.valid) {
			this.setPageComplete(true);
			this.setMessage("Press finish to create the new class");
			this.setErrorMessage(null);
		} else {
			this.setPageComplete(false);
			this.setErrorMessage(result.errorMessage);
		}
	}

	@Override
	public void createControl(Composite parent) {

		wizardForm = new N4JSNewClassMainForm(parent, SWT.FILL);

		setupBindings(wizardForm);
		setupInterfacesTable(wizardForm);
		setupBrowseDialogs(wizardForm);

		setControl(wizardForm);

	}

	/**
	 * Set the initial focus when the pages is visible
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);

		if (visible) {
			this.setInitialFocus(this.wizardForm);
		}

	}

	/**
	 * Set the input focus depending on the initially given model
	 *
	 * @param mainForm
	 *            The wizard form
	 */
	private void setInitialFocus(N4JSNewClassMainForm mainForm) {
		// Set the focus to the first empty field beginning with project
		if (model.getProject().toString().equals("")) {
			mainForm.getProjectText().setFocus();
		} else if (model.getSourceFolder().toString().equals("")) {
			mainForm.getSourceFolderText().setFocus();
		} else if (model.getClassName().equals("")) {
			mainForm.getClassNameText().setFocus();
		} else {
			mainForm.getModuleSpecifierText().setFocus();
		}
	}

	private void setupBrowseDialogs(N4JSNewClassMainForm mainForm) {
		mainForm.getProjectBrowseButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ProjectSelectionDialog dialog = new ProjectSelectionDialog(mainForm.getShell());
				dialog.open();

				Object firstResult = dialog.getFirstResult();

				if (firstResult instanceof IProject) {
					model.setProject(((IProject) firstResult).getFullPath());
				}

			}
		});

		mainForm.getSourceFolderBrowseButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				WorkspaceElementSelectionDialog dialog = sourceFolderSelectionDialogProvider.createDialog(
						mainForm.getShell(),
						model.getProject().toString(),
						model.getSourceFolder());
				dialog.open();

				Object firstResult = dialog.getFirstResult();

				if (firstResult instanceof IContainer) {
					model.setSourceFolder(((IContainer) firstResult).getProjectRelativePath().append("/"));
				} else if (firstResult instanceof VirtualResource) {
					model.setSourceFolder(new Path(((VirtualResource) firstResult).getName()).append("/"));
				}
			}
		});

		mainForm.getModuleSpecifierBrowseButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ModuleSpecifierSelectionDialog dialog = new ModuleSpecifierSelectionDialog(mainForm.getShell(),
						model.getProject().append(model.getSourceFolder()));

				if (!model.getEffectiveModuleSpecifier().isEmpty()) {
					String initialSelectionSpecifier = model.getEffectiveModuleSpecifier();

					String fileExtension = model.computeFileLocation().getFileExtension();
					if (fileExtension != null) {
						dialog.setDefaultFileExtension(fileExtension);
					}

					Object initialSelection = dialog.computeInitialSelection(initialSelectionSpecifier);
					dialog.setInitialSelection(initialSelection);
				}

				dialog.open();

				Object result = dialog.getFirstResult();

				if (result instanceof String) {

					IPath specifierPath = new Path((String) result);

					String fileExtension = specifierPath.getFileExtension();

					// If the selected module specifier is a file
					if (fileExtension != null && !fileExtension.isEmpty()) {
						// and its file extension suggests a different external value than the model
						if (fileExtension.equals(N4JSGlobals.N4JSD_FILE_EXTENSION) != model.isExternal()) {
							// toggle the external value of the model
							model.setExternal(!model.isExternal());
						}
						specifierPath = specifierPath.removeFileExtension();
					}

					// If the last segment corresponds with the non-empty class name remove it
					if (specifierPath.segmentCount() > 0
							&& specifierPath.removeFileExtension().lastSegment().equals(model.getClassName())
							&& !model.getClassName().isEmpty()) {
						if (specifierPath.segmentCount() > 1) {
							specifierPath = specifierPath.removeLastSegments(1).addTrailingSeparator();
						} else {
							specifierPath = specifierPath.removeLastSegments(1);
						}
					}

					model.setModuleSpecifier(specifierPath.toString());
				}
			}
		});

		mainForm.getSuperClassBrowseButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SingleClassSelectionDialog dialog = superClassSelectionDialogProvider.get();
				dialog.setInitialPattern(model.getSuperClass().getFullSpecifier());

				dialog.open();

				if (dialog.getReturnCode() == Window.CANCEL) {
					return;
				}

				Object result = dialog.getFirstResult();

				if (result instanceof IEObjectDescription) {
					IEObjectDescription objectDescription = (IEObjectDescription) result;

					URI objectUri = ((IEObjectDescription) result).getEObjectURI();

					model.setSuperClass(new ClassifierReference(objectDescription.getQualifiedName(), objectUri));
				}

			}
		});
	}

	private void setupInterfacesTable(N4JSNewClassMainForm mainForm) {
		// interfaces property binding

		Table interfacesTable = mainForm.getInterfacesTable();

		interfacesValue = BeanProperties.list(N4JSClassWizardModel.class, N4JSClassWizardModel.INTERFACES_PROPERTY)
				.observe(model);

		// Update SWT Table on model interfaces list update
		interfacesValue.addListChangeListener(new IListChangeListener() {

			@Override
			public void handleListChange(ListChangeEvent event) {
				int itemCount = event.getObservableList().size();
				interfacesTable.setItemCount(itemCount);

				// Find the minimum index to refresh from, to update the Table
				int min_index = itemCount - 1;
				for (ListDiffEntry entry : event.diff.getDifferences()) {
					if (min_index > entry.getPosition()) {
						min_index = entry.getPosition();
					}
				}
				if (itemCount > 0) {
					interfacesTable.clear(min_index, itemCount - 1);
				}

				for (ListDiffEntry diff : event.diff.getDifferences()) {
					interfacesTable.clear(diff.getPosition(), itemCount - 1);
				}
				// If no interfaces are contained disable the remove button
				if (itemCount < 1) {
					mainForm.getInterfacesRemoveButton().setEnabled(false);
				}
			}
		});

		// Set data of the interfaces table
		interfacesTable.addListener(SWT.SetData, new Listener() {

			@Override
			public void handleEvent(Event event) {
				TableItem item = (TableItem) event.item;
				int index = interfacesTable.indexOf(item);
				if (index < model.getInterfaces().size()) {
					item.setText(model.getInterfaces().get(index).getFullSpecifier());
				} else {
					item.setText("");
				}

			}
		});

		mainForm.getInterfacesRemoveButton().setEnabled(false);

		final TableEditor editor = new TableEditor(interfacesTable);

		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;

		/**
		 * Add in table editing
		 */
		interfacesTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// No mouse up action
			}

			@Override
			public void mouseDown(MouseEvent e) {
				if (editor.getItem() != null) {
					if (editor.getItem() != interfacesTable.getItem(new Point(e.x, e.y))) {
						editor.getEditor().dispose();
						interfacesTable.setItemCount(model.getInterfaces().size());
					}
				}
			}

			/**
			 * Create an editor for the selected item if double clicked
			 */
			@Override
			public void mouseDoubleClick(MouseEvent e) {

				TableItem item = interfacesTable.getItem(new Point(e.x, e.y));

				// If nothing is selected create a new empty TableItem at the end of the
				// list
				if (item == null) {
					interfacesTable.setItemCount(interfacesTable.getItemCount() + 1);
					item = interfacesTable.getItem(interfacesTable.getItemCount() - 1);
				}

				Control oldEditor = editor.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}

				Text newEditor = new Text(interfacesTable, SWT.NONE);
				newEditor.setText(item.getText(0));
				newEditor.setToolTipText("Enter the interface specifier");

				// Disable key event propagation to not finish the wizard when pressing enter in the table editor
				newEditor.addTraverseListener(new TraverseListener() {

					@Override
					public void keyTraversed(TraverseEvent event) {
						event.doit = false;

					}
				});

				final int fSelectionIndex = interfacesTable.indexOf(item);

				// If the edited item is an existing item
				if (fSelectionIndex < model.getInterfaces().size()) {
					newEditor.addKeyListener(new KeyListener() {

						@Override
						public void keyReleased(KeyEvent event) {
							if (event.keyCode == SWT.CR) {
								ClassifierReference ref = model.getInterfaces().get(fSelectionIndex);

								if (newEditor.getText().isEmpty()) {
									model.removeInterfaceURI(ref);
									newEditor.dispose();
									return;
								}

								// Convert input to classifier reference using the converter
								ClassifierReference editedRef = ((ClassifierReference) new N4JSNewClassWizardConverters.StringToClassifierReferenceConverter()
										.convert(newEditor.getText()));
								ref.classifierModuleSpecifier = editedRef.classifierModuleSpecifier;
								ref.classifierName = editedRef.classifierName;

								ref.uri = null; // Set the uri to null to indicate the validator to recompute it
								newEditor.dispose();
								interfacesTable.clear(fSelectionIndex);
								validator.validate();

							} else if (event.keyCode == SWT.ESC) {
								newEditor.dispose();
							}

						}

						@Override
						public void keyPressed(KeyEvent event) {
							// Only handle key released events

						}
					});
				} else {

					newEditor.addKeyListener(new KeyListener() {

						@Override
						public void keyReleased(KeyEvent event) {
							if (event.keyCode == SWT.CR) {
								String userInput = newEditor.getText();

								model.addInterface(
										new ClassifierReference(N4JSNewClassWizardUtils.frontDotSegments(userInput),
												N4JSNewClassWizardUtils.lastDotSegment(userInput)));
								newEditor.dispose();
							} else if (event.keyCode == SWT.ESC) {
								newEditor.dispose();
								// Reset the item count to delete the newly created item as the user aborted its
								// creation
								interfacesTable.setItemCount(model.getInterfaces().size());
							}
						}

						@Override
						public void keyPressed(KeyEvent event) {
							// Only handle key released events

						}
					});
				}

				newEditor.selectAll();
				newEditor.setFocus();
				editor.grabHorizontal = true;
				editor.setEditor(newEditor, item, 0);
			}
		});

		// Enable remove button when the user has selected a table element
		interfacesTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mainForm.getInterfacesRemoveButton().setEnabled(interfacesTable.getSelectionIndex() != -1
						&& interfacesTable.getSelectionIndex() < model.getInterfaces().size());
			}
		});

		// Remove button functionality
		mainForm.getInterfacesRemoveButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = interfacesTable.getSelectionIndex();
				// Return if nothing is selected
				if (selectionIndex == -1) {
					return;
				}
				ClassifierReference itemToRemove = model.getInterfaces().get(selectionIndex);
				// Return if index is invalid in model
				if (itemToRemove == null) {
					return;
				}
				model.removeInterfaceURI(itemToRemove);
				mainForm.getInterfacesRemoveButton().setEnabled(interfacesTable.getSelectionIndex() != -1);
			}
		});

		// Add button functionality
		mainForm.getInterfacesAddButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InterfacesSelectionDialog dialog = interfacesSelectionDialogProvider.get();
				dialog.open();

				if (dialog.getReturnCode() == Window.CANCEL) {
					return;
				}

				Object[] results = dialog.getResult();

				for (Object result : results) {
					if (result instanceof IEObjectDescription) {
						IEObjectDescription objectDescription = (IEObjectDescription) result;

						URI objectUri = ((IEObjectDescription) result).getEObjectURI();

						model.addInterface(new ClassifierReference(objectDescription.getQualifiedName(), objectUri));
					}
				}
			}
		});
	}

	private void setupBindings(N4JSNewClassMainForm mainForm) {
		databindingContext = new DataBindingContext();

		// Project property binding
		IObservableValue projectModelValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.PROJECT_PROPERTY)
				.observe(model);
		IObservableValue projectUI = WidgetProperties.text(SWT.Modify).observe(mainForm.getProjectText());

		// Note: No model to UI conversation here as IPath is castable to String (default behaviour)
		databindingContext.bindValue(projectUI, projectModelValue, new StringToPathConverter().updatingValueStrategy(),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE));

		// Source folder property binding
		IObservableValue sourceFolderModelValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.SOURCE_FOLDER_PROPERTY)
				.observe(model);
		IObservableValue sourceFolderUI = WidgetProperties.text(SWT.Modify).observe(mainForm.getSourceFolderText());

		// Note: No model to UI conversation (see above)
		databindingContext.bindValue(sourceFolderUI, sourceFolderModelValue,
				new StringToPathConverter().updatingValueStrategy(),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE));

		IObservableValue projectValidModelValue = BeanProperties
				.value(N4JSClassWizardModelValidator.class, N4JSClassWizardModelValidator.PROJECT_PROPERTY_VALID)
				.observe(this.validator);
		IObservableValue sourceFolderBrowseEnabled = WidgetProperties.enabled()
				.observe(mainForm.getSourceFolderBrowseButton());

		databindingContext.bindValue(sourceFolderBrowseEnabled, projectValidModelValue,
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE));

		// Module specifier property binding
		IObservableValue moduleSpecifierModelValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.MODULE_SPECIFIER_PROPERTY)
				.observe(model);
		IObservableValue moduleSpecifierUI = BeanProperties
				.value(SuffixText.class, SuffixText.TEXT_PROPERTY)
				.observe(mainForm.getModuleSpecifierText());
		databindingContext.bindValue(moduleSpecifierUI, moduleSpecifierModelValue);

		// Class name property binding
		IObservableValue classNameModelValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.CLASS_NAME_PROPERTY)
				.observe(model);
		IObservableValue classNameUI = WidgetProperties.text(SWT.Modify)
				.observe(mainForm.getClassNameText());
		databindingContext.bindValue(classNameUI, classNameModelValue);

		//// Class name to module specifier suffix binding
		IObservableValue greySuffixValue = BeanProperties
				.value(SuffixText.class, SuffixText.SUFFIX_PROPERTY)
				.observe(mainForm.getModuleSpecifierText());
		databindingContext.bindValue(greySuffixValue, classNameModelValue,
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_UPDATE));

		//// Only show the suffix on input values ending with a '/' character or empty module specifiers.
		mainForm.getModuleSpecifierText().addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				SuffixText input = mainForm.getModuleSpecifierText();
				String inputText = input.getText();
				if (inputText.equals("") || inputText.charAt(inputText.length() - 1) == '/') {
					input.setSuffixVisible(true);
				} else {
					input.setSuffixVisible(false);
				}

			}
		});

		IObservableValue moduleSpecifierBrowseEnabled = WidgetProperties.enabled()
				.observe(mainForm.getModuleSpecifierBrowseButton());
		IObservableValue sourceFolderValidValue = BeanProperties.value(N4JSClassWizardModelValidator.class,
				N4JSClassWizardModelValidator.SOURCE_FOLDER_PROPERTY_VALID).observe(this.validator);
		IObservableValue projectValidValue = BeanProperties.value(N4JSClassWizardModelValidator.class,
				N4JSClassWizardModelValidator.PROJECT_PROPERTY_VALID).observe(this.validator);

		ConditionalConverter moduleSpecifierBrowseableConverter = new ConditionalConverter() {

			@Override
			public boolean validate(Object object) {
				return validator.getSourceFolderValid() && validator.getProjectValid();
			}
		};

		// Bind model changes of project or source folder property to the enabled state of the module specifier browse
		// button.
		databindingContext.bindValue(moduleSpecifierBrowseEnabled, projectValidValue,
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				moduleSpecifierBrowseableConverter.updatingValueStrategy());
		databindingContext.bindValue(moduleSpecifierBrowseEnabled, sourceFolderValidValue,
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				moduleSpecifierBrowseableConverter.updatingValueStrategy());

		// External property binding (definition file)

		IObservableValue externalValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.EXTERNAL_PROPERTY)
				.observe(model);
		IObservableValue externalUI = WidgetProperties.selection().observe(mainForm.getDefinitionFileBox());
		databindingContext.bindValue(externalUI, externalValue);

		// Access modifier property binding

		IObservableValue publicButtonSelection = WidgetProperties.selection()
				.observe(mainForm.getPublicAccessModifierBox());
		IObservableValue projectButtonSelection = WidgetProperties.selection()
				.observe(mainForm.getProjectAccessModifierBox());
		IObservableValue privateButtonSelection = WidgetProperties.selection()
				.observe(mainForm.getPrivateAccessModifierBox());

		SelectObservableValue accessModifierSelectObservable = new SelectObservableValue();
		accessModifierSelectObservable.addOption(N4JSClassWizardModel.AccessModifier.PUBLIC, publicButtonSelection);
		accessModifierSelectObservable.addOption(N4JSClassWizardModel.AccessModifier.PROJECT, projectButtonSelection);
		accessModifierSelectObservable.addOption(N4JSClassWizardModel.AccessModifier.PRIVATE, privateButtonSelection);

		IObservableValue accessModifierProperty = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.ACCESS_MODIFIER_PROPERTY).observe(model);

		databindingContext.bindValue(accessModifierSelectObservable, accessModifierProperty);

		// Internal property binding

		IObservableValue internalValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.INTERNAL_PROPERTY)
				.observe(model);
		IObservableValue internalUI = WidgetProperties.selection().observe(mainForm.getInternalAnnotationBox());
		databindingContext.bindValue(internalUI, internalValue);

		IObservableValue internalEnabledValue = WidgetProperties.enabled().observe(mainForm.getInternalAnnotationBox());

		// One way binding of the access modifiers to the enabled state of internal checkbox
		databindingContext.bindValue(internalEnabledValue, accessModifierSelectObservable,
				new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
				new N4JSNewClassWizardConverters.ConditionalConverter() {

					@Override
					public boolean validate(Object object) {
						if (object instanceof AccessModifier) {
							if (((AccessModifier) object) == AccessModifier.PROJECT ||
									((AccessModifier) object) == AccessModifier.PUBLIC) {
								return true;
							}
						}
						return false;
					}
				}.updatingValueStrategy());

		// Final property binding

		IObservableValue finalValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.FINAL_PROPERTY)
				.observe(model);
		IObservableValue finalUI = WidgetProperties.selection().observe(mainForm.getFinalAnnotationBox());
		databindingContext.bindValue(finalUI, finalValue);

		// n4js annotation property binding

		IObservableValue n4jsValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.N4JS_PROPERTY)
				.observe(model);
		IObservableValue n4jsUI = WidgetProperties.selection().observe(mainForm.getN4jsAnnotationBox());

		databindingContext.bindValue(n4jsUI, n4jsValue);

		// super class property binding

		IObservableValue superClassValue = BeanProperties
				.value(N4JSClassWizardModel.class, N4JSClassWizardModel.SUPER_CLASS_PROPERTY).observe(model);
		IObservableValue superClassUI = WidgetProperties.text(SWT.Modify).observe(mainForm.getSuperClassText());

		databindingContext.bindValue(superClassUI, superClassValue,
				new N4JSNewClassWizardConverters.StringToClassifierReferenceConverter().updatingValueStrategy(),
				new N4JSNewClassWizardConverters.ClassifierReferenceToStringConverter().updatingValueStrategy());

		//// Enable n4js <-> Definition value(external) is selected
		IObservableValue n4jsUIEnabled = WidgetProperties.enabled().observe(mainForm.getN4jsAnnotationBox());
		databindingContext.bindValue(n4jsUIEnabled, externalValue);

		// Validation property result binding

		observableValidationValue = BeanProperties.value(N4JSClassWizardModelValidator.VALIDATION_RESULT)
				.observe(this.validator);
		observableValidationValue.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				onValidationChange((ValidationResult) event.diff.getNewValue());
			}
		});

	}

}
