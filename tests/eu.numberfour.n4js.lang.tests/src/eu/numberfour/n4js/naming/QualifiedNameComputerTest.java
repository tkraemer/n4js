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
package eu.numberfour.n4js.naming;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.N4JSInjectorProviderWithMockProject;
import eu.numberfour.n4js.n4JS.Script;
import eu.numberfour.n4js.ts.types.TModule;
import eu.numberfour.n4js.ts.types.Type;

/**
 */
@InjectWith(N4JSInjectorProviderWithMockProject.class)
@RunWith(XtextRunner.class)
public class QualifiedNameComputerTest {

	@Inject
	ParseHelper<Script> parserHelper;
	private Script script;
	private TModule module;
	private Type type;

	@Inject
	QualifiedNameComputer qnc;

	@Inject
	private Provider<XtextResourceSet> resourceSetProvider;

	@SuppressWarnings("javadoc")
	@Before
	public void prepare() throws Exception {
		this.script = parserHelper.parse("class C{}", URI.createFileURI("p/C.n4js"), resourceSetProvider.get());
		this.module = script.getModule();
		this.type = module.getTopLevelTypes().get(0);
	}

	/**
	 * Test method for
	 * {@link eu.numberfour.n4js.naming.QualifiedNameComputer#getQualifiedModuleName(eu.numberfour.n4js.n4JS.Script)} .
	 */
	@Test
	public void testGetQualifiedModuleName() {
		assertEquals("p/C", qnc.getQualifiedModuleName(module));
		assertEquals("p/C", qnc.getQualifiedModuleName(script));
	}

	/**
	 * Test method for
	 * {@link eu.numberfour.n4js.naming.QualifiedNameComputer#getSimpleTypeName(eu.numberfour.n4js.ts.types.Type)}.
	 */
	@Test
	public void testGetSimpleTypeName() {
		assertEquals("C", qnc.getSimpleTypeName(type));
	}

	/**
	 * Test method for
	 * {@link eu.numberfour.n4js.naming.QualifiedNameComputer#getFullyQualifiedTypeName(eu.numberfour.n4js.ts.types.Type)}
	 * .
	 */
	@Test
	public void testGetQualifiedTypeName() {
		assertEquals("p/C/C", qnc.getFullyQualifiedTypeName(type));
	}

	/**
	 * Test method for
	 * {@link eu.numberfour.n4js.naming.QualifiedNameComputer#getModuleSpecifier(eu.numberfour.n4js.n4JS.Script)} .
	 */
	@Test
	public void testGetModuleSpecifier() {
		assertEquals("p/C", qnc.getModuleSpecifier(script));
		assertEquals("p/C", qnc.getModuleSpecifier(module));

	}

	/**
	 * Test method for
	 * {@link eu.numberfour.n4js.naming.QualifiedNameComputer#getCompleteModuleSpecifier(eu.numberfour.n4js.ts.types.TModule)}
	 * .
	 */
	@Test
	public void testGetCompleteModuleSpecifier() {
		assertEquals("testproject/p/C", qnc.getCompleteModuleSpecifier(module));
	}

	/**
	 * Test method for
	 * {@link eu.numberfour.n4js.naming.QualifiedNameComputer#getCompleteModuleSpecifierAsIdentifier(eu.numberfour.n4js.ts.types.TModule)}
	 * .
	 */
	@Test
	public void testGetCompleteModuleSpecifierAsIdentifier() {
		assertEquals("testproject_p_u002fC", qnc.getCompleteModuleSpecifierAsIdentifier(module));
	}

	/**
	 * Test method for
	 * {@link eu.numberfour.n4js.naming.QualifiedNameComputer#getCompleteTypeSpecifier(eu.numberfour.n4js.ts.types.Type)}
	 * .
	 */
	@Test
	public void testGetCompleteTypeSpecifier() {
		assertEquals("testproject/p/C/C", qnc.getCompleteTypeSpecifier(type));
	}

}
