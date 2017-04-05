/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.ui.preferences.external;

import static org.eclipse.jface.dialogs.IDialogConstants.CANCEL_ID;
import static org.eclipse.jface.dialogs.IDialogConstants.CANCEL_LABEL;
import static org.eclipse.jface.dialogs.IDialogConstants.OK_ID;
import static org.eclipse.jface.dialogs.IDialogConstants.OK_LABEL;
import static org.eclipse.swt.SWT.BORDER;
import static org.eclipse.swt.SWT.CENTER;
import static org.eclipse.swt.SWT.CHECK;
import static org.eclipse.swt.SWT.END;
import static org.eclipse.swt.SWT.FILL;
import static org.eclipse.swt.SWT.NONE;
import static org.eclipse.swt.SWT.SHADOW_ETCHED_IN;
import static org.eclipse.swt.SWT.TOP;

import java.util.function.Consumer;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import eu.numberfour.n4js.external.version.VersionConstraintFormatUtil;
import eu.numberfour.n4js.ui.utils.DelegatingSelectionAdapter;

/**
 * Custom dialog for installing npm dependencies. Allows user to specify package name and version constraint. Uses
 * custom input validators.
 */
public class InstallNpmDependencyDialog extends TitleAreaDialog {
	private final IInputValidator packageNameValidator;
	private final IInputValidator packageVersionValidator;
	private String errPackageName = null;
	private String errLowerVersion = null;
	private String errUpperVersion = null;

	private String upperVersion;
	private String lowerVersion;
	private String packageName;
	private boolean isLowerExcluded;
	private boolean isUpperExcluded;

	/** Creates dialog with custom validators. */
	public InstallNpmDependencyDialog(Shell parentShell, IInputValidator packageNameValidator,
			IInputValidator packageVersionValidator) {
		super(parentShell);
		this.packageNameValidator = packageNameValidator;
		this.packageVersionValidator = packageVersionValidator;
	}

	/**
	 * Returns string representation of the package name specified by the user.
	 *
	 * @return validated name or {@code null}
	 */
	public String getPackageName() {
		if (hasErrors())
			return null;

		return packageName;
	}

	/**
	 * Returns string representation of the version constrained specified by the user.
	 *
	 * @return validated name or {@code null}
	 */
	public String getVersionConstraint() {
		if (hasErrors())
			return null;

		if (upperVersion == null || upperVersion.isEmpty())
			return VersionConstraintFormatUtil.npmVersionFormat(lowerVersion);
		else
			return VersionConstraintFormatUtil.npmRangeFormat(
					lowerVersion, isLowerExcluded,
					upperVersion, isUpperExcluded);

	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	public void create() {
		super.create();
		setTitle("npm dependency properties");
		setMessage("Provide properties of npm package to install.", IMessageProvider.INFORMATION);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, OK_ID, OK_LABEL, true);
		createButton(parent, CANCEL_ID, CANCEL_LABEL, false);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final Group customDialogArea = new Group(parent, SHADOW_ETCHED_IN);
		customDialogArea.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(false).create());
		customDialogArea.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(FILL, TOP).create());

		createNameArea(customDialogArea, "package name", this::handlePackageNameInput);
		createVersionArea(customDialogArea, "minimum version", this::handleLowerVersionInput,
				this::setLowerExcluded);
		createVersionArea(customDialogArea, "maximum version", this::handleUpperVersionInput,
				this::setUpperExcluded);

		return customDialogArea;
	}

	private void createVersionArea(final Group parent, String versionLabel, Consumer<String> textHandler,
			Consumer<Boolean> flagHandler) {
		final Composite area = createVersionArea(parent, versionLabel);
		final Composite textArea = createVersionInputArea(area);

		final Text txtUpperVersion = getSimpleTextArea(textArea);
		txtUpperVersion.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				textHandler.accept(textWidget.getText());
			}
		});

		createVersionInclsivnessArea(area, flagHandler);
	}

	private Text getSimpleTextArea(Composite parent) {
		final Text text = new Text(parent, BORDER);
		text.setLayoutData(new GridData(FILL, CENTER, true, false, 1, 1));
		return text;
	}

	private Composite createVersionArea(final Composite parent, final String label) {
		final Group area = new Group(parent, SHADOW_ETCHED_IN);
		area.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		area.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		area.setText(label);
		return area;
	}

	private Composite createVersionInputArea(final Composite parent) {
		final Composite textArea = new Composite(parent, NONE);
		textArea.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(false).create());
		textArea.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(FILL, CENTER).create());
		return textArea;
	}

	/**
	 * Creates area with two radio buttons for version being exclusive / inclusive. State of the buttons is mutually
	 * exclusive i.e. only one can be enabled (ensured by SWT handling of the radio button group).
	 *
	 *
	 * @param parent
	 *            the parent in which group is created with buttons is created
	 */
	private void createVersionInclsivnessArea(final Composite parent, Consumer<Boolean> setData) {
		final Group radioArea = new Group(parent, SHADOW_ETCHED_IN);
		radioArea.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(false).create());
		radioArea.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).align(END, TOP).create());

		final Button inclusive = new Button(radioArea, CHECK);
		inclusive.setText("Inclusive");
		inclusive.setSelection(true);

		// ignore events just get value from button
		inclusive.addSelectionListener(new DelegatingSelectionAdapter((SelectionEvent event) -> {
			setData.accept(inclusive.getSelection());
		}, (SelectionEvent event) -> {
			setData.accept(inclusive.getSelection());
		}));

	}

	private void createNameArea(Composite parent, String areaName, Consumer<String> textHandler) {
		final Group area = new Group(parent, SHADOW_ETCHED_IN);
		area.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
		area.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		area.setText(areaName);

		final Text txtPackageName = getSimpleTextArea(area);
		txtPackageName.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				textHandler.accept(textWidget.getText());
			}
		});
	}

	/**
	 * Toggles enabled/disabled state of the OK button based state of the error messages.
	 */
	private void toggleOK(boolean enabled) {
		Control button = getButton(OK_ID);
		if (button != null) {
			button.setEnabled(enabled);
		}
	}

	private void handlePackageNameInput(final String userText) {
		errPackageName = packageNameValidator.isValid(userText);
		packageName = userText;
		updateErrors();
	}

	private boolean hasErrors() {
		return errPackageName != null
				|| errLowerVersion != null
				|| errUpperVersion != null;
	}

	private final void updateErrors() {
		final boolean hasNoErrors = !hasErrors();
		toggleOK(hasNoErrors);
		if (hasNoErrors) {
			setErrorMessage(null);
		} else {
			StringBuilder sb = new StringBuilder();
			if (errPackageName != null)
				sb.append("\n - ").append(errPackageName);

			if (errLowerVersion != null)
				sb.append("\n - ").append(errLowerVersion);

			if (errUpperVersion != null)
				sb.append("\n - ").append(errUpperVersion);

			setErrorMessage("Please review following issues:" + sb);
		}
	}

	private void handleLowerVersionInput(final String userText) {
		errLowerVersion = null;

		// allow no value or just whitespace (which we ignore)
		String pereprocessed = userText == null ? "" : userText.trim();
		// if there is actual content do real parsing
		if (!pereprocessed.isEmpty()) {
			String validateResult = validate(pereprocessed);
			if (validateResult != null) {
				errLowerVersion = "Lower version issues:" + validateResult;
			}
		}

		this.lowerVersion = pereprocessed;
		updateErrors();
	}

	private void handleUpperVersionInput(final String userText) {
		errUpperVersion = null;

		// allow no value or just whitespace (which we ignore)
		String pereprocessed = userText == null ? "" : userText.trim();
		// if there is actual content do real parsing
		if (!pereprocessed.isEmpty()) {
			String validateResult = validate(pereprocessed);
			if (validateResult != null) {
				errUpperVersion = "Upper version issues:" + validateResult;
			}
		}

		this.upperVersion = pereprocessed;
		updateErrors();
	}

	private String validate(final String data) {
		String result = null;
		// allow no value or just whitespace (which we ignore)
		String pereprocessed = data == null ? "" : data.trim();
		// if there is actual content do real parsing
		if (!pereprocessed.isEmpty()) {
			result = packageVersionValidator.isValid(pereprocessed);
		}

		return result;
	}

	private void setLowerExcluded(boolean value) {
		isLowerExcluded = value;
	}

	private void setUpperExcluded(boolean value) {
		isUpperExcluded = value;
	}

}
