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
import java.beans.PropertyChangeSupport;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;

import com.google.common.primitives.Ints;

/**
 * Custom {@link org.eclipse.swt.widgets.Text} widget to optinally display a grey suffix at the end of the user input.
 */
public class SuffixText extends Composite {

	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	// Databinding properties
	/** Text property name */
	public static final String TEXT_PROPERTY = "text";
	/** Complete text property name */
	public static final String SUFFIX_PROPERTY = "suffix";

	// Color constants
	private static Color GREY = Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND);
	private final Image TRANSPARENT;

	private String suffix = "";
	private String text = "";

	private final Text userInput;
	private final Label completeLabel;

	// private final Composite packComposite;

	private boolean mousePressed = false;

	// Graphics context for text selection pixel calculation
	private final GC gc = new GC(getDisplay());

	/**
	 * Create the composite.
	 *
	 * @param parent
	 *            Parent composite
	 * @param style
	 *            additional style configuration
	 */
	public SuffixText(Composite parent, int style) {

		super(parent, style);

		// Initialize transparent image to work around theming issues
		ImageData data = new ImageData(1, 1, 24, new PaletteData(0xff0000, 0x00ff00, 0x0000ff));
		data.setAlpha(0, 0, 0);
		TRANSPARENT = new Image(Display.getCurrent(), data);

		this.setLayout(new SuffixLayout());

		userInput = new Text(this, SWT.NONE);
		userInput.setBackgroundImage(new Image(Display.getCurrent(), data));

		completeLabel = new Label(this, SWT.HORIZONTAL);
		completeLabel.setText("/Test");
		completeLabel.setForeground(GREY);
		completeLabel.setBackgroundImage(TRANSPARENT);

		MouseListener focusCatcher = new MouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				mousePressed = false;

			}

			@Override
			public void mouseDown(MouseEvent e) {
				userInput.forceFocus();
				userInput.setSelection(userInput.getText().length());
				mousePressed = true;
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				userInput.setSelection(0, userInput.getText().length());
			}
		};

		this.addMouseListener(focusCatcher);
		completeLabel.addMouseListener(focusCatcher);
		this.addMouseListener(focusCatcher);

		// Workaround theme dependent background color issues:
		// Reset the background color for every paint event
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				setBackground(userInput.getBackground());
				// setBackground(userInput.getBackground());
			}
		});

		// Update layout offset on text modification events
		this.userInput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				// updateOffset();
				layout();
				setText(userInput.getText());
			}
		});

		// Relayout when suffix label changes
		this.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == SUFFIX_PROPERTY) {
					layout();
				}
			}
		});

		// Make the graphic context use the font settings of the input
		gc.setFont(userInput.getFont());

		// Tracks dragging mouse movement in this widget and applies it to the text field userInput to
		// fake proper text selection behaviour
		MouseMoveListener mouseMoveSelectionListener = new MouseMoveListener() {
			@Override
			public void mouseMove(MouseEvent e) {
				if (mousePressed) {
					int userInputRightEdgeOffset = userInput.getBounds().x + userInput.getBounds().width;
					if (e.x < userInputRightEdgeOffset) {
						String inputString = userInput.getText();

						int selectedPixels = (userInputRightEdgeOffset - e.x);
						int i = 1;

						// Compute the index of the character the cursor is floating above and
						// adapt the text selection.
						while (inputString.length() - i >= 0
								&& gc.textExtent(userInput.getText().substring(inputString.length() - i,
										inputString.length() - 1)).x < selectedPixels) {
							i++;
						}

						int startIndex = Ints.max(0, inputString.length() - i + 1);

						userInput.setSelection(startIndex, inputString.length());
					}
				}

			}
		};
		this.addMouseMoveListener(mouseMoveSelectionListener);

		// Set text cursor
		this.setCursor(new Cursor(getDisplay(), SWT.CURSOR_IBEAM));

	}

	@Override
	public void dispose() {
		super.dispose();
		gc.dispose();
		TRANSPARENT.dispose();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * @return The auto suffix string
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * Sets the auto suffix string
	 *
	 * @param suffix
	 *            The string
	 */
	public void setSuffix(String suffix) {
		this.firePropertyChange(SUFFIX_PROPERTY, this.suffix, this.suffix = suffix);
		this.completeLabel.setText(this.suffix);
	}

	/**
	 * @return The fully merged value. User input + completed suffix.
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @param text
	 *            New text for the input
	 */
	public void setText(String text) {
		this.firePropertyChange(TEXT_PROPERTY, this.text, this.text = text);
		if (!this.userInput.getText().equals(text)) {
			this.userInput.setText(text);
		}
	}

	/**
	 * Sets the visibility of the suffix label
	 *
	 * @param state
	 *            The new state to adapt
	 */
	public void setSuffixVisible(boolean state) {
		this.completeLabel.setVisible(state);
	}

	@Override
	public boolean setFocus() {
		return this.userInput.setFocus();
	}

	/**
	 * Add a property listener
	 *
	 * @param listener
	 *            listener to be called on every change of any property
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.changeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * Remove a property listener
	 *
	 * @param listener
	 *            remove listener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.changeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * Fire a property change event
	 *
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
	 * Custom layout to position the suffix label directly after the text. The layout also makes sure that neither text
	 * nor label ever scroll horizontally
	 */
	private class SuffixLayout extends Layout {

		private Point textDimension;

		@Override
		protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
			int width = 0;
			int height = 0;
			Control[] children = composite.getChildren();
			for (Control child : children) {
				if (child instanceof Text) {
					final String textContent = ((Text) child).getText();
					textDimension = gc.textExtent(textContent);
					width += textDimension.x;
					height += textDimension.y;
				}
				if (child instanceof Label) {
					Point computedSize = child.computeSize(0, 0);
					width += computedSize.x;
				}
			}
			return new Point(width, height + 4);
		}

		@Override
		protected void layout(Composite composite, boolean flushCache) {
			Rectangle clientArea = composite.getClientArea();
			Control[] children = composite.getChildren();
			for (Control child : children) {
				if (child instanceof Text) {
					final String textContent = ((Text) child).getText();
					textDimension = gc.textExtent(textContent);
					child.setBounds(0, new Double((clientArea.height - textDimension.y) / 2.0).intValue(),
							textDimension.x + 4, textDimension.y);
				}
				if (child instanceof Label) {
					final String labelText = ((Label) child).getText();
					Point labelDimension = gc.textExtent(labelText);
					child.setBounds(textDimension.x, new Double((clientArea.height - textDimension.y) / 2.0).intValue(),
							labelDimension.x + 10, labelDimension.y);
				}
			}
		}
	}

}
