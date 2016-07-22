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
package eu.numberfour.n4js.tests.projectModel;

import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import eu.numberfour.n4js.N4JSInjectorProvider;
import eu.numberfour.n4js.internal.ClasspathPackageManager;
import eu.numberfour.n4js.internal.FileBasedWorkspace;
import eu.numberfour.n4js.internal.N4JSModel;
import eu.numberfour.n4js.internal.N4JSRuntimeCore;
import eu.numberfour.n4js.projectModel.IN4JSCore;

/**
 */
@RunWith(XtextRunner.class)
@InjectWith(N4JSInjectorProvider.class)
public class N4JSRuntimeCoreTest extends AbstractN4JSCoreTest {

	@Inject
	private Provider<XtextResourceSet> resourceSetProvider;

	@Inject
	private ClasspathPackageManager classpathPackageManager;

	@Inject
	private Injector injector;

	private FileBasedWorkspace workspace;

	private N4JSRuntimeCore testMe;

	@Override
	protected AbstractProjectModelSetup createSetup() {
		return new FileBasedProjectModelSetup(this, workspace);
	}

	@Override
	public void setUp() {
		workspace = new FileBasedWorkspace(resourceSetProvider, classpathPackageManager);
		N4JSModel model = new N4JSModel(workspace);
		injector.injectMembers(model);
		testMe = new N4JSRuntimeCore(workspace, model);
		super.setUp();
	}

	@Override
	protected IN4JSCore getN4JSCore() {
		return testMe;
	}

	@Override
	public void tearDown() {
		super.tearDown();
		workspace = null;
		testMe = null;
	}
}
