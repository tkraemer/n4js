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
package eu.numberfour.n4js.ui.internal;

import static eu.numberfour.n4js.ui.internal.N4JSActivator.EU_NUMBERFOUR_N4JS_N4JS;
import static org.eclipse.core.runtime.Status.OK_STATUS;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

import com.google.inject.Inject;
import com.google.inject.Injector;

import eu.numberfour.n4js.external.ExternalLibrariesReloadHelper;
import eu.numberfour.n4js.external.ExternalLibraryWorkspace;
import eu.numberfour.n4js.ui.resource.IResourceDescriptionPersisterContribution;
import eu.numberfour.n4js.utils.StatusHelper;
import eu.numberfour.n4js.utils.resources.DelegatingWorkspace;

/**
 * Resource description persister contribution to restore the state of the Xtext index for the external libraries on
 * application startup.
 */
public class N4JSExternalLibraryResourceDescriptionsPersisterContribution
		implements IResourceDescriptionPersisterContribution {

	private static final Logger LOGGER = Logger
			.getLogger(N4JSExternalLibraryResourceDescriptionsPersisterContribution.class);

	@Inject
	private ExternalLibraryWorkspace externalLibraryWorkspace;

	@Inject
	private ExternalLibrariesReloadHelper externalLibrariesReloadHelper;

	@Inject
	private StatusHelper statusHelper;

	@Override
	public void scheduleRecoveryBuild() {

		final Job job = new Job("Initializing N4JS Built-ins...") {

			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				try {
					externalLibraryWorkspace.updateState();
					externalLibrariesReloadHelper.reloadLibraries(false, monitor);
				} catch (final InvocationTargetException e) {
					final String message = "Unexpected error occurred while initializing N4JS Built-ins.";
					LOGGER.error(message, e);
					return statusHelper.createError(message, e);
				}
				return OK_STATUS;
			}

		};

		final IResourceRuleFactory ruleFactory = new DelegatingWorkspace().getRuleFactory();
		if (null != ruleFactory) {
			job.setRule(ruleFactory.buildRule());
		}

		job.setUser(true);
		job.setSystem(false);
		job.schedule();

	}

	@Override
	public Injector getInjector() {
		return N4JSActivator.getInstance().getInjector(EU_NUMBERFOUR_N4JS_N4JS);
	}

}
