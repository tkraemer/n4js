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
package eu.numberfour.n4js.generator.headless.tests;

import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import eu.numberfour.n4js.hlc.AbstractN4jscTest;
import eu.numberfour.n4js.tests.codegen.Class;
import eu.numberfour.n4js.tests.codegen.Classifier;
import eu.numberfour.n4js.tests.codegen.Field;
import eu.numberfour.n4js.tests.codegen.Member;
import eu.numberfour.n4js.tests.codegen.Module;
import eu.numberfour.n4js.tests.codegen.Project;

/**
 *
 */
@RunWith(Parameterized.class)
public class AccessControlTest extends AbstractN4jscTest {
	private static enum Scenario {
		EXTENSION
	}

	/**
	 * The location of the client attempting to access a member of the supplier.
	 */
	private static enum ClientLocation {
		/**
		 * Client and supplier are the same type.
		 */
		SAME_TYPE,
		/**
		 * Client and supplier are in the same module, but not in the same type.
		 */
		SAME_MODULE,
		/**
		 * Client and supplier are in the same project, but not in the same module.
		 */
		SAME_PROJECT,
		/**
		 * Client and supplier have the same vendor, but are in different projects.
		 */
		SAME_VENDOR,
		/**
		 * Client and supplier have different vendors and are thus in different projects.
		 */
		OTHER_VENDOR
	}

	private static class TestSpecification {
		private final Scenario scenario;
		private final ClientLocation clientLocation;
		private final Classifier.Visibility supplierVisibility;
		private final Member.Visibility memberVisibility;

		/**
		 * Creates a new instance with the given parameters.
		 *
		 * @param scenario
		 *            the test scenario
		 * @param clientLocation
		 *            the location of the generated client in relation to the generated supplier
		 * @param supplierVisibility
		 *            the visibility of the generated supplier type
		 * @param memberVisibility
		 *            the visibility of the generated member the client is attempting to access
		 */
		public TestSpecification(Scenario scenario, ClientLocation clientLocation,
				Classifier.Visibility supplierVisibility,
				Member.Visibility memberVisibility) {
			this.scenario = scenario;
			this.clientLocation = clientLocation;
			this.supplierVisibility = supplierVisibility;
			this.memberVisibility = memberVisibility;
		}

		/**
		 * Returns the scenario
		 *
		 * @return the scenario
		 */
		public Scenario getScenario() {
			return scenario;
		}

		/**
		 * Returns the location of the generated client in relation to the generated supplier.
		 *
		 * @return the client location
		 */
		public ClientLocation getClientLocation() {
			return clientLocation;
		}

		/**
		 * Returns the visibility of the generated supplier type.
		 *
		 * @return the supplier visibility
		 */
		public Classifier.Visibility getSupplierVisibility() {
			return supplierVisibility;
		}

		/**
		 * Returns the visibility of the generated member that the client is attempting to access.
		 *
		 * @return the member visibility
		 */
		public Member.Visibility getMemberVisibility() {
			return memberVisibility;
		}

	}

	/**
	 * Returns the parameters for this test by parsing the appropriate CSV file.
	 *
	 * @return the parameters
	 */
	@Parameters
	public static Iterable<? extends Object> data() {
		List<TestSpecification> result = new LinkedList<>();

		return result;
	}

	private final TestSpecification specification;

	/**
	 * Creates a new instance of this test that runs the given test specification.
	 *
	 * @param specification
	 *            the specification to run
	 */
	public AccessControlTest(TestSpecification specification) {
		this.specification = specification;
	}

	/**
	 * Performs the actual test according to the specification.
	 */
	@Test
	public void test() {

	}

	private void generateScenario() {
		switch (specification.getScenario()) {
		case EXTENSION:
			generateExtensionScenario();
			break;

		default:
			break;
		}
	}

	private static String FIXTURE_ROOT = "target";

	private void generateExtensionScenario() {
		Class classA = new Class("A").setVisibility(specification.getSupplierVisibility());
		classA.addMember(new Field("field").setVisibility(specification.getMemberVisibility()));

		Class classB = new Class("B");
		classB.addMember(new Field("field").setVisibility(specification.getMemberVisibility()));

		Project project = new Project("supplier", "vendorA", "Vendor A");
		Project.SourceFolder supplierSourceFolder = new Project.SourceFolder("src");
		Module supplierModule = new Module("Supplier").addClassifier(classA);

		switch (specification.getClientLocation()) {
		case SAME_TYPE:
		case SAME_MODULE:
			supplierModule.addClassifier(classB);
			supplierSourceFolder.addModule(supplierModule);
			project.addSourceFolder(supplierSourceFolder);
			break;
		case SAME_PROJECT:
			Module clientModule = new Module("Client");
			clientModule.addImport(classA, supplierModule);
			clientModule.addClassifier(classB);

			supplierSourceFolder.addModule(supplierModule);
			supplierSourceFolder.addModule(clientModule);
			project.addSourceFolder(supplierSourceFolder);
			break;
		case SAME_VENDOR:
			break;
		case OTHER_VENDOR:
			break;
		default:
			break;
		}

		project.create(Paths.get(FIXTURE_ROOT, "SupplierProject"));
	}
}
