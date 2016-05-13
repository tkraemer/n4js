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
	 * Returns with the {@link Display#getCurrent() current} display.
	 *
	 * @return the current display.
	 */
	protected Display getDisplay() {
		return checkNotNull(Display.getCurrent(), "Not on UI thread.");
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
	 * Processes UI input and does not return until there are things to do on the UI thread.
	 */
	protected void waitForUiThread() {
		final Display display = getDisplay();
		while (display.readAndDispatch()) {
			// Wait until there is anything to process.
		}
		display.update();
	}

	/**
	 * Wait until all background tasks are complete then makes sure that the UI thread is idle as well.
	 */
	protected void waitIdle() {
		final IJobManager manager = Job.getJobManager();
		while (manager.currentJob() != null) {
			waitForUiThread();
		}
		waitForUiThread();
	}

}
