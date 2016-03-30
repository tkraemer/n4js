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
package eu.numberfour.n4js.ui.building;

import static eu.numberfour.n4js.ui.internal.N4JSActivator.EU_NUMBERFOUR_N4JS_N4JS;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.xtext.builder.IXtextBuilderParticipant.BuildType;
import org.eclipse.xtext.builder.impl.ToBeBuilt;
import org.eclipse.xtext.builder.impl.XtextBuilder;

import eu.numberfour.n4js.external.ExternalLibraryBuilderHelper;
import eu.numberfour.n4js.ui.internal.N4JSActivator;
import eu.numberfour.n4js.ui.internal.ProjectDescriptionLoadListener;

/**
 * A customized XtextBuilder that uses the {@link N4JSBuildTypeTracker} so other clients can get access to the build
 * type during the currently run build.
 */
@SuppressWarnings("restriction")
public class N4JSBuildTypeTrackingBuilder extends XtextBuilder {

	private final static Logger logger = Logger.getLogger(N4JSBuildTypeTrackingBuilder.class);

	@Override
	protected void doClean(ToBeBuilt toBeBuilt, IProgressMonitor monitor)
			throws CoreException {
		try {
			updateProjectReferencesIfNecessary();
			N4JSBuildTypeTracker.setBuildType(getProject(), BuildType.CLEAN);
			super.doClean(toBeBuilt, monitor);
		} finally {
			N4JSBuildTypeTracker.clearBuildType(getProject());
		}
	}

	@Override
	protected void doBuild(ToBeBuilt toBeBuilt, IProgressMonitor monitor, BuildType type) throws CoreException,
			OperationCanceledException {
		try {
			updateProjectReferencesIfNecessary();
			N4JSBuildTypeTracker.setBuildType(getProject(), type);
			super.doBuild(toBeBuilt, monitor, type);
		} catch (OperationCanceledException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Error in n4js-build", e);
			throw e;
		} finally {
			N4JSBuildTypeTracker.clearBuildType(getProject());
		}
	}

	@Override
	public ISchedulingRule getRule(int kind, Map<String, String> args) {
		if (null != args) {
			final String value = args.get(ExternalLibraryBuilderHelper.EXTERNAL_BUILD);
			boolean external = Boolean.parseBoolean(value);
			if (external) {
				return null;
			}
		}
		return super.getRule(kind, args);
	}

	private void updateProjectReferencesIfNecessary() {
		final ProjectDescriptionLoadListener loadListener = N4JSActivator.getInstance()
				.getInjector(EU_NUMBERFOUR_N4JS_N4JS)
				.getInstance(ProjectDescriptionLoadListener.class);
		loadListener.updateProjectReferencesIfNecessary(getProject());
	}
}
