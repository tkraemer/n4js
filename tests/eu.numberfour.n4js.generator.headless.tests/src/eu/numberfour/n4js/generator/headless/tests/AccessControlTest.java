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

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.eclipse.xtext.validation.Issue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Joiner;

import eu.numberfour.n4js.csv.CSVData;
import eu.numberfour.n4js.csv.CSVParser;
import eu.numberfour.n4js.csv.CSVRecord;
import eu.numberfour.n4js.generator.headless.IssueCollector;
import eu.numberfour.n4js.generator.headless.N4HeadlessCompiler;
import eu.numberfour.n4js.generator.headless.N4JSCompileException;
import eu.numberfour.n4js.hlc.AbstractN4jscTest;
import eu.numberfour.n4js.tests.codegen.Class;
import eu.numberfour.n4js.tests.codegen.Classifier;
import eu.numberfour.n4js.tests.codegen.Field;
import eu.numberfour.n4js.tests.codegen.Getter;
import eu.numberfour.n4js.tests.codegen.Interface;
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
		EXTENSION, IMPLEMENTATION, REFERENCE;

		public static Scenario parse(String str) {
			switch (str) {
			case "extends":
				return EXTENSION;
			case "implements":
				return IMPLEMENTATION;
			case "references":
				return REFERENCE;
			default:
				throw new IllegalArgumentException("Unexpected scenario: '" + str + "'");
			}
		}
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
		OTHER_VENDOR;

		public static ClientLocation parse(String str) {
			switch (str) {
			case "Same Type":
				return SAME_TYPE;
			case "Same Module":
				return SAME_MODULE;
			case "Same Project":
				return SAME_PROJECT;
			case "Same Vendor":
				return SAME_VENDOR;
			case "Other":
				return OTHER_VENDOR;
			default:
				throw new IllegalArgumentException("Unexpected client location: '" + str + "'");
			}
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
		OVERRIDE;

		public static UsageType parse(String str) {
			switch (str) {
			case "Access":
				return ACCESS;
			case "Override":
				return OVERRIDE;
			default:
				throw new IllegalArgumentException("Unexpected usage type: '" + str + "'");
			}
		}
	}

	/**
	 * The type of the member being accessed by the client.
	 */
	private static enum MemberType {
		FIELD, GETTER, SETTER, METHOD;
	}

	private static enum ClassifierType {
		CLASS, ABSTRACT_CLASS, INTERFACE, DEFAULT_INTERFACE;

		public static ClassifierType parse(String str) {
			switch (str) {
			case "class":
				return CLASS;
			case "abstract class":
				return ABSTRACT_CLASS;
			case "interface":
				return INTERFACE;
			case "default interface":
				return DEFAULT_INTERFACE;
			default:
				throw new IllegalArgumentException("Unexpected classifier type: '" + str + "'");
			}
		}
	}

	private static enum Expectation {
		OK, FAIL, SKIP;

		public static Expectation parse(String str) {
			switch (str) {
			case "y":
				return OK;
			case "n":
				return FAIL;
			case "":
			case "#":
				return SKIP;
			default:
				throw new IllegalArgumentException("Unexpected expectation: '" + str + "'");
			}
		}
	}

	private static class TestSpecification {
		private final Scenario scenario;
		private final ClassifierType supplierType;
		private final ClassifierType clientType;
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
		 * @param supplierType
		 *            the type of the supplier
		 * @param clientType
		 *            the type of the client
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
		public TestSpecification(Scenario scenario, ClassifierType supplierType, ClassifierType clientType,
				ClientLocation clientLocation, UsageType usageType,
				MemberType memberType,
				Classifier.Visibility supplierVisibility,
				Member.Visibility memberVisibility, Member.Static memberStatic, Expectation expectation) {
			this.scenario = scenario;
			this.supplierType = supplierType;
			this.clientType = clientType;
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
		 * Returns the type of the supplier.
		 *
		 * @return the type of the supplier
		 */
		public ClassifierType getSupplierType() {
			return supplierType;
		}

		/**
		 * Returns the type of the client.
		 *
		 * @return the type of the client
		 */
		public ClassifierType getClientType() {
			return clientType;
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
			return scenario + " scenario with " + clientLocation + " client " + clientType + " attempting to "
					+ usageType + " a "
					+ memberVisibility + (memberStatic == Member.Static.YES ? " STATIC " : " INSTANCE ")
					+ memberType + " of a " + supplierVisibility + " supplier " + supplierType + " (expectation: "
					+ expectation + ")";
		}
	}

	/**
	 * Returns the parameters for this test by parsing the appropriate CSV file.
	 *
	 * @return the parameters
	 */
	@Parameters(name = "{0}")
	public static Iterable<? extends Object> data() throws IOException {
		CSVParser parser = new CSVParser("testdata/accesscontrol/ClassVisibility.csv", StandardCharsets.UTF_8);
		CSVData csvData = parser.getData();

		List<TestSpecification> result = new LinkedList<>();

		CSVData accessSpec = csvData.getRange(0, 4, 4, -1);
		CSVData scenarios = csvData.getRange(5, 0, -1, -1);

		for (int row = 0; row < scenarios.getSize() / 5; row++) {
			CSVData scenario = scenarios.getRange(row * 5, 0, 5, -1);
			result.addAll(createScenario(accessSpec, scenario));
		}

		return result;
	}

	private static List<TestSpecification> createScenario(CSVData accessSpec, CSVData data) {
		List<TestSpecification> result = new LinkedList<>();

		CSVData testSpec = data.getRange(0, 0, -1, 4);

		Scenario scenario = Scenario.parse(testSpec.get(0, 0));
		ClassifierType supplierType = ClassifierType.parse(testSpec.get(0, 1));
		ClassifierType clientType = ClassifierType.parse(testSpec.get(0, 2));

		for (int row = 0; row < testSpec.getSize(); row++) {
			ClientLocation clientLocation = ClientLocation.parse(testSpec.get(row, 3));
			CSVRecord expectations = data.get(row).getRange(4, -1);

			for (int col = 0; col < expectations.getSize(); col++) {
				Member.Static memberStatic = parseStatic(accessSpec.get(0, getFieldIndex(col, 2 * 4 * 6)));
				UsageType usageType = UsageType.parse(accessSpec.get(1, getFieldIndex(col, 4 * 6)));
				Classifier.Visibility supplierVisibility = parseClassifierVisibility(
						accessSpec.get(2, getFieldIndex(col, 6)));
				Member.Visibility memberVisibility = parseMemberVisibility(accessSpec.get(3, col));
				Expectation expectation = Expectation.parse(expectations.get(col));

				if (expectation != Expectation.SKIP) {
					for (MemberType memberType : MemberType.values()) {
						TestSpecification specification = new TestSpecification(scenario, supplierType, clientType,
								clientLocation, usageType,
								memberType, supplierVisibility, memberVisibility, memberStatic, expectation);
						result.add(specification);
					}
				}
			}
		}

		return result;
	}

	private static int getFieldIndex(int index, int colSpan) {
		return (index / colSpan) * colSpan;
	}

	private static Member.Static parseStatic(String str) {
		switch (str) {
		case "Instance":
			return Member.Static.NO;
		case "Static":
			return Member.Static.YES;
		default:
			throw new IllegalArgumentException("Unexpected member type: '" + str + "'");
		}
	}

	private static Classifier.Visibility parseClassifierVisibility(String str) {
		switch (str) {
		case "pub":
			return Classifier.Visibility.PUBLIC;
		case "pub@":
			return Classifier.Visibility.PUBLIC_INTERNAL;
		case "proj":
			return Classifier.Visibility.PROJECT;
		case "priv":
			return Classifier.Visibility.PRIVATE;
		default:
			throw new IllegalArgumentException("Unexpected type visibility: '" + str + "'");
		}
	}

	private static Member.Visibility parseMemberVisibility(String str) {
		switch (str) {
		case "pub":
			return Member.Visibility.PUBLIC;
		case "pub@":
			return Member.Visibility.PUBLIC_INTERNAL;
		case "prot":
			return Member.Visibility.PROTECTED;
		case "prot@":
			return Member.Visibility.PROTECTED_INTERNAL;
		case "proj":
			return Member.Visibility.PROJECT;
		case "priv":
			return Member.Visibility.PRIVATE;
		default:
			throw new IllegalArgumentException("Unexpected member visibility: '" + str + "'");
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
		List<File> result = new LinkedList<>();

		ScenarioResult scenario = createScenario();
		Classifier<?> supplier = scenario.supplier;
		Classifier<?> client = scenario.client;
		Class getSupplier = null;

		supplier.addMember(createMember("member", specification.getMemberVisibility()));

		switch (specification.getScenario()) {
		case EXTENSION:
		case IMPLEMENTATION: {
			switch (specification.getUsageType()) {
			case ACCESS:
				switch (specification.getMemberStatic()) {
				case YES:
					client.addMember(createAccess("member", "S"));
					break;
				case NO:
					client.addMember(createAccess("member", "this"));
					break;
				}
				break;
			case OVERRIDE:
				client.addMember(createMember("member", specification.getMemberVisibility()).makeOverride());
				break;
			default:
				throw new IllegalArgumentException("Unexpected usage type: " + specification.getUsageType());
			}
			break;
		}
		case REFERENCE: {
			if (specification.getUsageType() == UsageType.OVERRIDE)
				throw new IllegalArgumentException("Cannot override in reference scenario");
			if (specification.getSupplierType() != ClassifierType.CLASS)
				throw new IllegalArgumentException("Cannot use non-instantiable supplier type in reference scenario");

			getSupplier = new Class("GetS").setVisibility(Classifier.Visibility.PUBLIC);
			getSupplier.addMember(new Method("getS").setVisibility(Member.Visibility.PUBLIC).setReturnType("S")
					.setBody("return new S();"));
			client.addMember(createAccess("member", "new GetS().getS()"));
			break;
		}
		}

		switch (specification.getClientLocation()) {
		case SAME_TYPE:
		case SAME_MODULE: {
			Module module = new Module("SameModule");
			module.addClassifier(supplier);
			if (getSupplier != null)
				module.addClassifier(getSupplier);
			module.addClassifier(client);

			Project project = new Project("SameModule", "sameVendor", "SameVendor");
			project.createSourceFolder("src").addModule(module);
			result.add(project.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case SAME_PROJECT: {
			Module supplierModule = new Module("SupplierModule");
			supplierModule.addClassifier(supplier);
			if (getSupplier != null)
				supplierModule.addClassifier(getSupplier);

			Module clientModule = new Module("ClientModule");
			if (getSupplier != null)
				clientModule.addImport(getSupplier, supplierModule);
			else
				clientModule.addImport(supplier, supplierModule);
			clientModule.addClassifier(client);

			Project project = new Project("SameProject", "sameVendor", "SameVendor");
			project.createSourceFolder("src").addModule(supplierModule).addModule(clientModule);
			result.add(project.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case SAME_VENDOR: {
			Module supplierModule = new Module("SupplierModule");
			supplierModule.addClassifier(supplier);
			if (getSupplier != null)
				supplierModule.addClassifier(getSupplier);

			Module clientModule = new Module("ClientModule");
			if (getSupplier != null)
				clientModule.addImport(getSupplier, supplierModule);
			else
				clientModule.addImport(supplier, supplierModule);
			clientModule.addClassifier(client);

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
			supplierModule.addClassifier(supplier);
			if (getSupplier != null)
				supplierModule.addClassifier(getSupplier);

			Module clientModule = new Module("ClientModule");
			if (getSupplier != null)
				clientModule.addImport(getSupplier, supplierModule);
			else
				clientModule.addImport(supplier, supplierModule);
			clientModule.addClassifier(client);

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

	private static class ScenarioResult {
		public Classifier<?> supplier;
		public Classifier<?> client;

		public ScenarioResult(Classifier<?> supplier, Classifier<?> client) {
			this.supplier = Objects.requireNonNull(supplier);
			this.client = Objects.requireNonNull(client);
		}
	}

	private ScenarioResult createScenario() {
		switch (specification.getScenario()) {
		case EXTENSION:
			return createExtensionScenario();
		case IMPLEMENTATION:
			return createImplementationScenario();
		case REFERENCE:
			return createReferenceScenario();
		}

		throw new IllegalArgumentException("Unexpected scenario: " + specification.getScenario());
	}

	private ScenarioResult createExtensionScenario() {
		switch (specification.getSupplierType()) {
		case CLASS: {
			Class supplier = new Class("S").setVisibility(specification.getSupplierVisibility());
			switch (specification.getClientType()) {
			case CLASS:
				return new ScenarioResult(supplier, new Class("C").setSuperClass(supplier));
			case ABSTRACT_CLASS:
				return new ScenarioResult(supplier, new Class("C").setSuperClass(supplier).makeAbstract());
			case INTERFACE:
			case DEFAULT_INTERFACE:
				throw new IllegalArgumentException("Invalid Scenario: An interface cannot extend a class");
			}
			break;
		}
		case ABSTRACT_CLASS: {
			Class supplier = new Class("S").setVisibility(specification.getSupplierVisibility()).makeAbstract();
			switch (specification.getClientType()) {
			case CLASS:
				return new ScenarioResult(supplier, new Class("C").setSuperClass(supplier));
			case ABSTRACT_CLASS:
				return new ScenarioResult(supplier, new Class("C").setSuperClass(supplier).makeAbstract());
			case INTERFACE:
			case DEFAULT_INTERFACE:
				throw new IllegalArgumentException("Invalid Scenario: An interface cannot extend a class");
			}
			break;
		}
		case INTERFACE:
		case DEFAULT_INTERFACE: {
			Interface supplier = new Interface("S").setVisibility(specification.getSupplierVisibility());
			switch (specification.getClientType()) {
			case CLASS:
			case ABSTRACT_CLASS:
				throw new IllegalArgumentException("Invalid Scenario: A class cannot extend an interface");
			case INTERFACE:
			case DEFAULT_INTERFACE:
				return new ScenarioResult(supplier, new Interface("C").addSuperInterface(supplier));
			}
			break;
		}
		}

		throw new IllegalArgumentException("Unexpected supplier type: " + specification.getSupplierType());
	}

	private ScenarioResult createImplementationScenario() {
		switch (specification.getSupplierType()) {
		case CLASS:
		case ABSTRACT_CLASS:
			throw new IllegalArgumentException("Invalid Scenario: Classes cannot be implemented");
		case INTERFACE:
		case DEFAULT_INTERFACE: {
			Interface supplier = new Interface("S").setVisibility(specification.getSupplierVisibility());
			switch (specification.getClientType()) {
			case CLASS:
				return new ScenarioResult(supplier, new Class("C").addInterface(supplier));
			case ABSTRACT_CLASS:
				return new ScenarioResult(supplier, new Class("C").makeAbstract().addInterface(supplier));
			case INTERFACE:
			case DEFAULT_INTERFACE:
				throw new IllegalArgumentException("Invalid Scenario: A class cannot extend an interface");
			}
			break;
		}
		}

		throw new IllegalArgumentException("Unexpected supplier type: " + specification.getSupplierType());
	}

	private ScenarioResult createReferenceScenario() {
		switch (specification.getSupplierType()) {
		case CLASS: {
			Class supplier = new Class("S").setVisibility(specification.getSupplierVisibility());
			switch (specification.getClientType()) {
			case CLASS:
				return new ScenarioResult(supplier, new Class("C"));
			case ABSTRACT_CLASS:
			case INTERFACE:
			case DEFAULT_INTERFACE:
				throw new IllegalArgumentException(
						"Invalid Scenario: Cannot instantiate abstract classes or interfaces for reference scenario");
			}
			break;
		}
		case ABSTRACT_CLASS:
			throw new IllegalArgumentException("Invalid Scenario: Abstract classes cannot be instantiated");
		case INTERFACE:
		case DEFAULT_INTERFACE:
			throw new IllegalArgumentException("Invalid Scenario: Interfaces cannot be instantiated");
		}

		throw new IllegalArgumentException("Unexpected supplier type: " + specification.getSupplierType());
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
