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
package eu.numberfour.n4js.ui.wizard.workspace;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;

import com.google.common.primitives.Ints;

import eu.numberfour.n4js.ui.ImageDescriptorCache;

/**
 * Custom {@link org.eclipse.swt.widgets.Text} widget to optionally display a grey suffix at the end of the user input.
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
		TRANSPARENT = ImageDescriptorCache.ImageRef.TRANSPARENT.asImage().orNull();

		this.setLayout(new SuffixLayout());

		userInput = new Text(this, SWT.NONE);
		userInput.setBackgroundImage(TRANSPARENT);

		completeLabel = new Label(this, SWT.HORIZONTAL);
		completeLabel.setText("/Test");
		completeLabel.setForeground(GREY);

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

		// Whenever focus is put on suffix text redirect it to the userInput
		this.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// Do nothing
			}

			@Override
			public void focusGained(FocusEvent e) {
				userInput.setFocus();
			}
		});

		// Make tab traversal skip the suffix text and traverse the userInput instead
		this.userInput.addListener(SWT.Traverse, traverseEvent -> {
			if (traverseEvent.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
				traverse(traverseEvent.detail);
				traverseEvent.doit = false;
			}
		});

		// Workaround theme dependent background color issues:
		// Reset the background color for every paint event
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				setBackground(userInput.getBackground());
				completeLabel.setBackground(userInput.getBackground());
			}
		});

		userInput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				layout();
				setText(userInput.getText());
			}
		});

		// Redirect key events from user input to this
		// to make content proposal activation work
		userInput.addListener(SWT.KeyDown, e -> {
			notifyListeners(SWT.KeyDown, e);
		});

		// Relayout when suffix label changes
		this.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getPropertyName() == SUFFIX_PROPERTY) {
					layout(true);
				}
			}
		});

		// Make the graphic context use the font settings of the input
		gc.setFont(userInput.getFont());

		// Tracks dragging mouse movement in this widget and applies it to the text field userInput to
		// fake proper text selection behavior
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
	 * Sets the selection to the range specified by the given start and end indices.
	 *
	 * See {@link Text#setSelection(int, int)}
	 *
	 * @param start
	 *            the start of the range
	 * @param end
	 *            the end of the range
	 */
	public void setSelection(int start, int end) {
		this.userInput.setSelection(start, end);
	}

	/**
	 * Returns the suffix text selection.
	 *
	 * See {@link Text#getSelection()}
	 */
	public Point getSelection() {
		return this.userInput.getSelection();
	}

	/**
	 * Inserts a string.
	 * <p>
	 * The old selection is replaced with the new text.
	 * </p>
	 *
	 * See {@link Text#insert(String)}
	 *
	 * @param string
	 *            The new text
	 */
	public void insert(String string) {
		this.userInput.insert(string);
	}

	/**
	 * Returns the character position of the caret.
	 * <p>
	 * Indexing is zero based.
	 * </p>
	 *
	 * See {@link Text#getCaretPosition()}
	 *
	 * @return the position of the caret
	 */
	public int getCaretPosition() {
		return this.userInput.getCaretPosition();
	}

	/**
	 * Returns a point describing the location of the caret relative to the receiver.
	 *
	 * See {@link Text#getCaretLocation()}
	 */
	public Point getCaretLocation() {
		return this.userInput.getCaretLocation();
	}

	/**
	 * Returns the height of a line.
	 *
	 * See {@link Text#getLineHeight()}
	 *
	 * @return the height of a row of text
	 */
	public int getLineHeight() {
		return this.userInput.getLineHeight();
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

		// Negative vertical spacing to imitate seamless character flow
		private static final int NEGATIVE_VERTICAL_SPACING = 8;

		// Amount of additional pixels for text dimensions to avoid clipping
		private static final int AVOID_CLIPPING_PADDING = 8;

		@Override
		protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
			int width = 0;
			int height = 0;
			Point textDimension = textDimensions();
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

		private Point textDimensions() {
			Point tDimensions = gc.textExtent(userInput.getText());
			tDimensions.x += AVOID_CLIPPING_PADDING;
			return tDimensions;
		}

		private Point labelDimensions() {
			Point labelDimensions = gc.textExtent(suffix);
			labelDimensions.x += AVOID_CLIPPING_PADDING;
			return labelDimensions;
		}

		/**
		 * Returns the top margin to vertically center an element of given height in an area with given total height.
		 */
		private int marginTopCenter(int height, int totalHeight) {
			return new Double(Math.floor((totalHeight - height) / 2.0)).intValue();
		}

		@Override
		protected void layout(Composite composite, boolean flushCache) {
			Rectangle clientArea = composite.getClientArea();
			Control[] children = composite.getChildren();

			Point labelDimension = labelDimensions();
			Point textDimension = textDimensions();

			for (Control child : children) {

				if (child instanceof Text) {

					int verticalCenterY = marginTopCenter(textDimension.y, clientArea.height);
					child.setBounds(0, verticalCenterY, textDimension.x, textDimension.y);
				}
				if (child instanceof Label) {

					int verticalCenterY = marginTopCenter(textDimension.y, clientArea.height);
					child.setBounds(textDimension.x - NEGATIVE_VERTICAL_SPACING, verticalCenterY,
							labelDimension.x, labelDimension.y);
				}

			}
		}
	}

}
