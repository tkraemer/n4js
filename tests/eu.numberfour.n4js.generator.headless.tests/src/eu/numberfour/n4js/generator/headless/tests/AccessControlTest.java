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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.xtext.validation.Issue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Joiner;

import eu.numberfour.n4js.csv.CSVParser;
import eu.numberfour.n4js.generator.headless.IssueCollector;
import eu.numberfour.n4js.generator.headless.N4HeadlessCompiler;
import eu.numberfour.n4js.generator.headless.N4JSCompileException;
import eu.numberfour.n4js.hlc.AbstractN4jscTest;
import eu.numberfour.n4js.tests.codegen.Class;
import eu.numberfour.n4js.tests.codegen.Classifier;
import eu.numberfour.n4js.tests.codegen.Field;
import eu.numberfour.n4js.tests.codegen.Getter;
import eu.numberfour.n4js.tests.codegen.Member;
import eu.numberfour.n4js.tests.codegen.Method;
import eu.numberfour.n4js.tests.codegen.Module;
import eu.numberfour.n4js.tests.codegen.Project;
import eu.numberfour.n4js.tests.codegen.Setter;
import eu.numberfour.n4js.tests.issues.IssuesMatcher;
import eu.numberfour.n4js.utils.io.FileDeleter;

/**
 *
 */
@RunWith(Parameterized.class)
public class AccessControlTest extends AbstractN4jscTest {
	private static enum Scenario {
		EXTENSION, REFERENCE
	}

	/**
	 * The location of the client attempting to access a member of the supplier.
	 */
	private static enum ClientLocation {
		/**
		 * Client and supplier are the same type.
		 */
		SAME_TYPE(0),
		/**
		 * Client and supplier are in the same module, but not in the same type.
		 */
		SAME_MODULE(1),
		/**
		 * Client and supplier are in the same project, but not in the same module.
		 */
		SAME_PROJECT(2),
		/**
		 * Client and supplier have the same vendor, but are in different projects.
		 */
		SAME_VENDOR(3),
		/**
		 * Client and supplier have different vendors and are thus in different projects.
		 */
		OTHER_VENDOR(4);

		private int rowIndex;

		ClientLocation(int rowIndex) {
			this.rowIndex = rowIndex;
		}

		/**
		 * Returns the index of the row that corresponds to this client location in the access control matrix.
		 *
		 * @return the row index
		 */
		public int getRowIndex() {
			return rowIndex;
		}
	}

	/**
	 * The type of usage intended when accessing a member of the supplier.
	 */
	private static enum UsageType {
		/**
		 * Client intends to access (read / write, call) a member (field, getter, setter, method) of the supplier.
		 */
		ACCESS,
		/**
		 * Client intends to override a member of the supplier.
		 */
		OVERRIDE
	}

	/**
	 * The type of the member being accessed by the client.
	 */
	private static enum MemberType {
		FIELD, GETTER, SETTER, METHOD;
	}

	private static enum Expectation {
		OK, FAIL, SKIP
	}

	private static class TestSpecification {
		private final Scenario scenario;
		private final ClientLocation clientLocation;
		private final UsageType usageType;
		private final MemberType memberType;
		private final Classifier.Visibility supplierVisibility;
		private final Member.Visibility memberVisibility;
		private final Member.Static memberStatic;
		private final Expectation expectation;

		/**
		 * Creates a new instance with the given parameters.
		 *
		 * @param scenario
		 *            the test scenario
		 * @param clientLocation
		 *            the location of the generated client in relation to the generated supplier
		 * @param usageType
		 *            the type of usage intended by the client
		 * @param memberType
		 *            the type of the member being accessed by the client
		 * @param supplierVisibility
		 *            the visibility of the generated supplier type
		 * @param memberVisibility
		 *            the visibility of the generated member the client is attempting to access
		 * @param memberStatic
		 *            whether or not the accessed member is static
		 * @param expectation
		 *            the expected test result
		 */
		public TestSpecification(Scenario scenario, ClientLocation clientLocation, UsageType usageType,
				MemberType memberType,
				Classifier.Visibility supplierVisibility,
				Member.Visibility memberVisibility, Member.Static memberStatic, Expectation expectation) {
			this.scenario = scenario;
			this.clientLocation = clientLocation;
			this.usageType = usageType;
			this.memberType = memberType;
			this.supplierVisibility = supplierVisibility;
			this.memberVisibility = memberVisibility;
			this.memberStatic = memberStatic;
			this.expectation = expectation;
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
		 * Returns the usage type by which the client intends to access the generated supplier's members.
		 *
		 * @return the usage type
		 */
		public UsageType getUsageType() {
			return usageType;
		}

		/**
		 * Returns the type of the member being accessed by the client.
		 *
		 * @return the member type
		 */
		public MemberType getMemberType() {
			return memberType;
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

		/**
		 * Returns whether or not the generated member that the client is attempting to access is static.
		 *
		 * @return whether or not the member is static
		 */
		public Member.Static getMemberStatic() {
			return memberStatic;
		}

		/**
		 * Returns the test expectation.
		 *
		 * @return the expectation
		 */
		public Expectation getExpectation() {
			return expectation;
		}

		@Override
		public String toString() {
			return scenario + " scenario with " + clientLocation + " client attempting to " + usageType + " a "
					+ memberVisibility + (memberStatic == Member.Static.YES ? " STATIC " : " INSTANCE ")
					+ memberType + " of a " + supplierVisibility + " supplier";
		}
	}

	private static int COL_COUNT = 4 * 6;
	private static int ACCESS_COL_OFFSET = 3;
	private static int OVERRIDE_COL_OFFSET = 3 + COL_COUNT;
	private static int STATIC_OFFSET = 2 * COL_COUNT;
	private static int VISIBILITY_ROW_OFFSET = 3;
	private static int EXPECTATION_ROW_OFFSET = 6;

	/**
	 * Returns the parameters for this test by parsing the appropriate CSV file.
	 *
	 * @return the parameters
	 */
	@Parameters(name = "{0}")
	public static Iterable<? extends Object> data() throws IOException {
		CSVParser parser = new CSVParser("testdata/accesscontrol/ClassVisibility.csv", StandardCharsets.UTF_8);

		List<TestSpecification> result = new LinkedList<>();
		// Extension scenario
		addAccessTests(parser, 0, 0, Scenario.EXTENSION, Member.Static.NO, result);
		addOverrideTests(parser, 0, 0, Scenario.EXTENSION, Member.Static.NO, result);
		addAccessTests(parser, 0, STATIC_OFFSET, Scenario.EXTENSION, Member.Static.YES, result);
		addOverrideTests(parser, 0, STATIC_OFFSET, Scenario.EXTENSION, Member.Static.YES, result);

		// Reference scenario
		addAccessTests(parser, 5, 0, Scenario.REFERENCE, Member.Static.NO, result);
		addOverrideTests(parser, 5, 0, Scenario.REFERENCE, Member.Static.NO, result);
		addAccessTests(parser, 5, STATIC_OFFSET, Scenario.REFERENCE, Member.Static.YES, result);
		addOverrideTests(parser, 5, STATIC_OFFSET, Scenario.REFERENCE, Member.Static.YES, result);

		return result;
	}

	private static void addAccessTests(CSVParser parser, int rowOffset, int columnOffset, Scenario scenario,
			Member.Static memberStatic,
			List<TestSpecification> result) {
		ArrayList<ArrayList<String>> visibilities = parser.getRange(VISIBILITY_ROW_OFFSET, 2,
				columnOffset + ACCESS_COL_OFFSET,
				COL_COUNT);
		ArrayList<ArrayList<String>> expectations = parser.getRange(rowOffset + EXPECTATION_ROW_OFFSET, 5,
				columnOffset + ACCESS_COL_OFFSET,
				COL_COUNT);
		addTests(visibilities, expectations, scenario, memberStatic, UsageType.ACCESS, result);
	}

	private static void addOverrideTests(CSVParser parser, int rowOffset, int columnOffset, Scenario scenario,
			Member.Static memberStatic,
			List<TestSpecification> result) {
		ArrayList<ArrayList<String>> visibilities = parser.getRange(VISIBILITY_ROW_OFFSET, 2,
				columnOffset + OVERRIDE_COL_OFFSET,
				COL_COUNT);
		ArrayList<ArrayList<String>> expectations = parser.getRange(rowOffset + EXPECTATION_ROW_OFFSET, 5,
				columnOffset + OVERRIDE_COL_OFFSET,
				COL_COUNT);
		addTests(visibilities, expectations, scenario, memberStatic, UsageType.OVERRIDE, result);
	}

	private static void addTests(ArrayList<ArrayList<String>> visibilities, ArrayList<ArrayList<String>> expectations,
			Scenario scenario,
			Member.Static memberStatic,
			UsageType usageType, List<TestSpecification> result) {
		for (ClientLocation location : ClientLocation.values()) {
			for (int col = 0; col < COL_COUNT; col++) {
				final Expectation expectation = getExpectation(expectations, location.getRowIndex(), col);
				if (expectation == Expectation.SKIP)
					continue;

				final Classifier.Visibility typeVisibility = getTypeVisibility(visibilities, col);
				final Member.Visibility memberVisibility = getMemberVisibility(visibilities, col);

				for (MemberType memberType : MemberType.values()) {
					result.add(new TestSpecification(scenario, location, usageType, memberType,
							typeVisibility,
							memberVisibility, memberStatic, expectation));
				}
			}
		}
	}

	private static Classifier.Visibility getTypeVisibility(final ArrayList<ArrayList<String>> visibilities, int col) {
		final int index = (col / 6) * 6;
		final String name = visibilities.get(0).get(index);
		switch (name) {
		case "pub":
			return Classifier.Visibility.PUBLIC;
		case "pub@":
			return Classifier.Visibility.PUBLIC_INTERNAL;
		case "proj":
			return Classifier.Visibility.PROJECT;
		case "priv":
			return Classifier.Visibility.PRIVATE;
		default:
			throw new IllegalArgumentException("Unknown type visibility name '" + name + "' at index " + index);
		}
	}

	private static Member.Visibility getMemberVisibility(final ArrayList<ArrayList<String>> visibilities, int col) {
		final int index = col;
		final String name = visibilities.get(1).get(index);
		switch (name) {
		case "pub":
			return Member.Visibility.PUBLIC;
		case "pub@":
			return Member.Visibility.PUBLIC_INTERNAL;
		case "prot":
			return Member.Visibility.PROJECT;
		case "prot@":
			return Member.Visibility.PROTECTED_INTERNAL;
		case "proj":
			return Member.Visibility.PROJECT;
		case "priv":
			return Member.Visibility.PRIVATE;
		default:
			throw new IllegalArgumentException("Unknown member visibility name '" + name + "' at index " + index);
		}
	}

	private static Expectation getExpectation(final ArrayList<ArrayList<String>> expectations, int row, int col) {
		final String name = expectations.get(row).get(col);
		switch (name) {
		case "y":
			return Expectation.OK;
		case "n":
			return Expectation.FAIL;
		case "#":
			return Expectation.SKIP;
		default:
			throw new IllegalArgumentException(
					"Unknown expectation name '" + name + "' at row " + row + " and column " + col);
		}
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

	private static String FIXTURE_ROOT = "accesscontrol-tests-temp";

	/**
	 * Performs the actual test according to the specification.
	 */
	@Test
	public void test() throws IOException {
		createFixtureDirectory();
		try {
			generateScenario();
			final IssuesMatcher matcher = createIssues();
			compileAndAssert(Arrays.asList(new File(FIXTURE_ROOT)), matcher);
		} finally {
			deleteFixtureDirectory();
		}
	}

	private List<File> generateScenario() {
		switch (specification.getScenario()) {
		case EXTENSION:
			return generateExtensionScenario();
		case REFERENCE:
			return generateReferenceScenario();
		default:
			fail("Unknown test scenario: " + specification.getScenario());
			return null; // Can never happen
		}
	}

	private List<File> generateExtensionScenario() {
		List<File> result = new LinkedList<>();

		Class classA = new Class("A").setVisibility(specification.getSupplierVisibility());
		classA.addMember(createMember("member", specification.getMemberVisibility()));

		Class classB = new Class("B");
		classB.setSuperClass(classA);

		switch (specification.getUsageType()) {
		case ACCESS:
			classB.addMember(createAccess("member", "this"));
			break;
		case OVERRIDE:
			classB.addMember(createMember("member", specification.getMemberVisibility()).makeOverride());
			break;
		default:
			throw new IllegalArgumentException("Unexpected usage type: " + specification.getUsageType());
		}

		switch (specification.getClientLocation()) {
		case SAME_TYPE:
		case SAME_MODULE: {
			Module module = new Module("SameModule");
			module.addClassifier(classA);
			module.addClassifier(classB);

			Project project = new Project("SameModule", "sameVendor", "SameVendor");
			project.createSourceFolder("src").addModule(module);
			result.add(project.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case SAME_PROJECT: {
			Module supplierModule = new Module("SupplierModule");
			supplierModule.addClassifier(classA);

			Module clientModule = new Module("ClientModule");
			clientModule.addImport(classA, supplierModule);
			clientModule.addClassifier(classB);

			Project project = new Project("SameProject", "sameVendor", "SameVendor");
			project.createSourceFolder("src").addModule(supplierModule).addModule(clientModule);
			result.add(project.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case SAME_VENDOR: {
			Module supplierModule = new Module("SupplierModule");
			supplierModule.addClassifier(classA);

			Module clientModule = new Module("ClientModule");
			clientModule.addImport(classA, supplierModule);
			clientModule.addClassifier(classB);

			Project supplierProject = new Project("SupplierProject", "sameVendor", "SameVendor");
			supplierProject.createSourceFolder("src").addModule(supplierModule);
			result.add(supplierProject.create(Paths.get(FIXTURE_ROOT)));

			Project clientProject = new Project("ClientProject", "sameVendor", "SameVendor");
			clientProject.addProjectDependency(supplierProject);
			clientProject.createSourceFolder("src").addModule(clientModule);
			result.add(clientProject.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case OTHER_VENDOR: {
			Module supplierModule = new Module("SupplierModule");
			supplierModule.addClassifier(classA);

			Module clientModule = new Module("ClientModule");
			clientModule.addImport(classA, supplierModule);
			clientModule.addClassifier(classB);

			Project supplierProject = new Project("SupplierProject", "vendorA", "VendorA");
			supplierProject.createSourceFolder("src").addModule(supplierModule);
			result.add(supplierProject.create(Paths.get(FIXTURE_ROOT)));

			Project clientProject = new Project("ClientProject", "vendorB", "VendorB");
			clientProject.addProjectDependency(supplierProject);
			clientProject.createSourceFolder("src").addModule(clientModule);
			result.add(clientProject.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		default:
			break;
		}

		return result;
	}

	private List<File> generateReferenceScenario() {
		List<File> result = new LinkedList<>();

		Class classA = new Class("A").setVisibility(specification.getSupplierVisibility());
		classA.addMember(createMember("member", specification.getMemberVisibility()));

		Class getA = new Class("GetA").setVisibility(Classifier.Visibility.PUBLIC);
		getA.addMember(new Method("getA").setVisibility(Member.Visibility.PUBLIC).setReturnType("A")
				.setBody("return new A();"));

		Class classB = new Class("B");
		classB.addMember(createAccess("member", "new GetA().getA()"));

		switch (specification.getClientLocation()) {
		case SAME_TYPE:
		case SAME_MODULE: {
			Module module = new Module("SameModule");
			module.addClassifier(classA);
			module.addClassifier(getA);
			module.addClassifier(classB);

			Project project = new Project("SameModule", "sameVendor", "SameVendor");
			project.createSourceFolder("src").addModule(module);
			result.add(project.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case SAME_PROJECT: {
			Module supplierModule = new Module("SupplierModule");
			supplierModule.addClassifier(classA);
			supplierModule.addClassifier(getA);

			Module clientModule = new Module("ClientModule");
			clientModule.addImport(getA, supplierModule);
			clientModule.addClassifier(classB);

			Project project = new Project("SameProject", "sameVendor", "SameVendor");
			project.createSourceFolder("src").addModule(supplierModule).addModule(clientModule);
			result.add(project.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case SAME_VENDOR: {
			Module supplierModule = new Module("SupplierModule");
			supplierModule.addClassifier(classA);
			supplierModule.addClassifier(getA);

			Module clientModule = new Module("ClientModule");
			clientModule.addImport(getA, supplierModule);
			clientModule.addClassifier(classB);

			Project supplierProject = new Project("SupplierProject", "sameVendor", "SameVendor");
			supplierProject.createSourceFolder("src").addModule(supplierModule);
			result.add(supplierProject.create(Paths.get(FIXTURE_ROOT)));

			Project clientProject = new Project("ClientProject", "sameVendor", "SameVendor");
			clientProject.addProjectDependency(supplierProject);
			clientProject.createSourceFolder("src").addModule(clientModule);
			result.add(clientProject.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case OTHER_VENDOR: {
			Module supplierModule = new Module("SupplierModule");
			supplierModule.addClassifier(classA);
			supplierModule.addClassifier(getA);

			Module clientModule = new Module("ClientModule");
			clientModule.addImport(getA, supplierModule);
			clientModule.addClassifier(classB);

			Project supplierProject = new Project("SupplierProject", "vendorA", "VendorA");
			supplierProject.createSourceFolder("src").addModule(supplierModule);
			result.add(supplierProject.create(Paths.get(FIXTURE_ROOT)));

			Project clientProject = new Project("ClientProject", "vendorB", "VendorB");
			clientProject.addProjectDependency(supplierProject);
			clientProject.createSourceFolder("src").addModule(clientModule);
			result.add(clientProject.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		default:
			break;
		}

		return result;
	}

	private Member<?> createMember(String name, Member.Visibility visibility) {
		Member<?> result = null;
		switch (specification.getMemberType()) {
		case FIELD:
			result = new Field(name).setVisibility(visibility);
			break;
		case GETTER:
			result = new Getter(name).setVisibility(visibility);
			break;
		case SETTER:
			result = new Setter(name).setVisibility(visibility);
			break;
		case METHOD:
			result = new Method(name).setVisibility(visibility);
			break;
		default:
			throw new IllegalArgumentException("Unknown member type: " + specification.getMemberType());
		}

		if (specification.getMemberStatic() == Member.Static.YES)
			result.makeStatic();

		return result;
	}

	private Method createAccess(String memberName, String subject) {
		Method result = null;
		switch (specification.getMemberType()) {
		case FIELD:
			result = new Method("accessor").setBody("var t = " + subject + "." + memberName + ";");
			break;
		case GETTER:
			result = new Method("accessor").setBody("var t = " + subject + "." + memberName + ";");
			break;
		case SETTER:
			result = new Method("accessor").setBody("" + subject + "." + memberName + " = null;");
			break;
		case METHOD:
			result = new Method("accessor").setBody("" + subject + "." + memberName + "();");
			break;
		default:
			throw new IllegalArgumentException("Unknown member type: " + specification.getMemberType());
		}

		if (specification.getMemberStatic() == Member.Static.YES)
			result.makeStatic();

		return result;
	}

	private IssuesMatcher createIssues() {
		IssuesMatcher result = new IssuesMatcher();

		switch (specification.getExpectation()) {
		case OK:
			break;
		case FAIL:
			result.add().error();
			break;
		default:
			throw new IllegalArgumentException("Unexpected test expectation: " + specification.getExpectation());
		}

		return result;
	}

	private static N4HeadlessCompiler hlc = N4HeadlessCompiler.injectAndSetup(null);

	private void compileAndAssert(List<File> projectRoots, IssuesMatcher matcher) {
		IssueCollector issueCollector = new IssueCollector();
		try {
			hlc.compileAllProjects(projectRoots, issueCollector);
		} catch (N4JSCompileException e) {
			// nothing to do
		}

		assertIssues(issueCollector.getCollectedIssues(), matcher);
	}

	private void assertIssues(Collection<Issue> issues, IssuesMatcher matchers) {
		List<String> messages = new LinkedList<>();
		boolean result = matchers.matchesExactly(issues, messages);
		assertTrue(Joiner.on(", ").join(messages), result);
	}

	private void createFixtureDirectory() throws IOException {
		File file = new File(FIXTURE_ROOT);
		if (file.exists())
			deleteFixtureDirectory();
		file.mkdir();
	}

	private void deleteFixtureDirectory() throws IOException {
		FileDeleter.delete(new File(FIXTURE_ROOT));
	}
}
