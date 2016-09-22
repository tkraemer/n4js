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
package eu.numberfour.n4js.ui.example;

import static com.google.common.collect.FluentIterable.from;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.ui.wizard.ExampleInstallerWizard;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.google.inject.Inject;

import eu.numberfour.n4js.binaries.IllegalBinaryStateException;
import eu.numberfour.n4js.external.ExternalLibraryWorkspace;
import eu.numberfour.n4js.external.NpmManager;
import eu.numberfour.n4js.external.TargetPlatformInstallLocationProvider;

/**
 * Wizard for the {@code N4JS Tasks Example} projects.
 */
public class N4JSTasksExampleWizard extends ExampleInstallerWizard {

	/**
	 * Unique ID of the {@code N4JS Tasks Example} wizard.
	 */
	public static final String ID = N4JSTasksExampleWizard.class.getName();

	@Inject
	private NpmManager npmManager;

	@Inject
	private TargetPlatformInstallLocationProvider installLocationProvider;

	@Inject
	private ExternalLibraryWorkspace externalLibraryWorkspace;

	@Override
	public boolean performFinish() {
		if (super.performFinish()) {
			installDependencies("mongodb", "express");
			return true;
		}
		return false;
	}

	private void installDependencies(String... names) {
		Set<String> toInstall = new HashSet<>(Arrays.asList(names));
		toInstall.removeAll(getInstalledNpmPackages());

		try {
			getContainer().run(true, false, new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						for (String name : toInstall) {
							monitor.subTask("Install dependency " + name);
							npmManager.installDependency(name, monitor);
						}
					} catch (IllegalBinaryStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Collection<String> getInstalledNpmPackages() {
		final File root = new File(installLocationProvider.getTargetPlatformNodeModulesLocation());
		return from(externalLibraryWorkspace.getProjects(root.toURI())).transform(p -> p.getName()).toSet();
	}
}
