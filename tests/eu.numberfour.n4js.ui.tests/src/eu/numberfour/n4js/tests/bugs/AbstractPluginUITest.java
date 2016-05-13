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
package eu.numberfour.n4js.tests.bugs;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.eclipse.ui.PlatformUI.getWorkbench;
import static org.eclipse.ui.PlatformUI.isWorkbenchRunning;

import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.junit.BeforeClass;

/**
 * Base test class for plug-in UI tests. Does not contain any test cases rather convenient methods and assertions for
 * concrete test classes.
 */
public abstract class AbstractPluginUITest extends AbstractIDEBUG_Test {

	/**
	 * Asserts that the {@link IWorkbench workbench} is running.
	 */
	@BeforeClass
	public static void assertWorkbenchIsRunning() {
		assertTrue("Expected running workbench.", isWorkbenchRunning());
	}

	/**
	 * Returns with the {@link Display#getCurrent() current} display. Could be {@code null}.
	 *
	 * @return the current display or {@code null} if not available.
	 */
	protected Display getDisplay() {
		return Display.getCurrent();
	}

	/**
	 * Returns with the active page.
	 *
	 * @return the active page.
	 */
	protected IWorkbenchPage getPage() {
		return checkNotNull(getWindow().getActivePage(), "Active page was null.");
	}

	/**
	 * Returns with the active workbench window.
	 *
	 * @return the active window.
	 */
	protected IWorkbenchWindow getWindow() {
		return checkNotNull(
				getWorkbench().getActiveWorkbenchWindow(),
				"Active workbench window was null. Is the caller thread the UI thread?");
	}

	/**
	 * Returns with the view part with the given ID. May return with {@code null} if the view part cannot be found with
	 * the given identifier.
	 *
	 * @param id
	 *            the unique ID of the view part.
	 * @return the view part, or {@code null} if cannot be found.
	 */
	protected IViewPart findView(final String id) {
		return getPage().findView(checkNotNull(id, "id"));
	}

	/**
	 * Shows then returns with the view with the given unique view part identifier. May return with {@code null} if the
	 * view cannot be found.
	 *
	 * @param id
	 *            the unique ID of the view part to show.
	 * @return the view part or {@code null} if the view part cannot be shown.
	 */
	protected IViewPart showView(final String id) {
		try {
			return getPage().showView(checkNotNull(id, "id"));
		} catch (final PartInitException e) {
			final String message = "Error occurred while initializing view with ID: '" + id + "'.";
			LOGGER.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	/**
	 * Hides the view part argument.
	 *
	 * @param viewPart
	 *            the view part to hide. Must not be {@code null}.
	 */
	protected void hideView(final IViewPart viewPart) {
		getActivePage().hideView(checkNotNull(viewPart, "viewPart"));
	}

	/**
	 * Processes UI input but does not return for the specified time interval.
	 *
	 * @param millisToWait
	 *            the number of milliseconds.
	 */
	protected void delay(final long millisToWait) {
		checkState(millisToWait > 0, "millisToWait > 0 Was: " + millisToWait);
		final Display display = getDisplay();

		if (display != null) {
			final long endTimeMillis = System.currentTimeMillis() + millisToWait;
			while (System.currentTimeMillis() < endTimeMillis) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			display.update();
		} else {
			try {
				Thread.sleep(millisToWait);
			} catch (final InterruptedException e) {
				LOGGER.warn("Interrupted while waiting for " + millisToWait + " milliseconds.", e);
			}
		}

	}

	/**
	 * Wait until all background tasks are complete.
	 */
	protected void waitForJobs() {
		final IJobManager manager = Job.getJobManager();
		while (manager.currentJob() != null) {
			delay(100);
		}
	}

}
