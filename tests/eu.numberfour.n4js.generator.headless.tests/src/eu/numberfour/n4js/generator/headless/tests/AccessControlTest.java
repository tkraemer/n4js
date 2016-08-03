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

// @formatter:off
/**
 * A parameterized test that reads its parameters from a CSV export of the <a href=
 * "https://docs.google.com/a/numberfour.eu/spreadsheets/d/1u8c61A3bqqApD6pd8u8XRAdMbTvEUFOtJLkCgtn0JbE/edit?usp=sharing">this
 * Google Sheets table</a>.
 *
 * The test reads the table in the {@link #data()} method. See the javadoc comment for that method for more details on
 * the expected structure of the table.
 *
 * This test generates a large number of test cases from the table. For each expectation in the table, four tests are
 * performed; one for each member type (field, getter, setter, and method). In each test, two classifiers (a classifier is either a class or an interface) are generated: A <b>supplier</b> contains a member that a <b>client</b> is attempting to access. Thereby, accessing a member means that the member is being read, written, called, or overridden.
 * The details of each specific tests are specified using the following parameters:
 *
 * <ul>
 *     <li>
 *         <b>Scenario:</b> one of
 *         <ul>
 *             <li><b>Extends:</b> The client extends the supplier, which implies that the supplier and the client are either both classes or both interfaces.</li>
 *             <li><b>Implements:</b>: The client implements the supplier, which implies that the supplier is an interface and the client is a class.</li>
 *             <li><b>References:</b>: The client has a reference to an instance of the supplier. Here, the client must be a class while the supplier can be either a class or an interface. If the supplier is abstract, then an implementing class is automatically generated.</li>
 *         </ul>
 *     </li>
 *     <li>
 *         <b>Supplier Type:</b> The actual type of the supplier, which is one of
 *         <ul>
 *             <li><b>Class:</b> A concrete, instantiable class.</li>
 *             <li><b>Abstract Class:</b> An abstract class. The generated member is also abstract unless it's a field.</li>
 *             <li><b>Interface:</b> An abstract interface. The generated member is also abstract unless it's a field.</li>
 *             <li><b>Default Interface:</b> An interface where all members have default implementations.</li>
 *         </ul>
 *     </li>
 *     <li>
 *         <b>Client Type:</b> The actual type of the client, which has the same possible values as <b>Supplier Type</b>.
 *     </li>
 *     <li>
 *         <b>Client Location:</b> The location of the client in relation to the supplier, one of
 *         <ul>
 *             <li><b>Same Type:</b> Client and supplier are the same type.</li>
 *             <li><b>Same Module:</b> Client and supplier are generated into the same module. This implies that only one project has to be generated.</li>
 *             <li><b>Same Project:</b> Client and supplier are in separate modules in the same project. Again, only one project is generated.</li>
 *             <li><b>Same Vendor:</b> Client and supplier are in different projects that share the same vendor ID.</li>
 *             <li><b>Other:</b> Client and supplier are in different projects with different vendor IDs.</li>
 *         </ul>
 *     </li>
 *     <li>
 *         <b>Member Static:</b> Whether or not the member being accessed is an instance member or a static member, one of <b>Instance</b> or <b>Static</b></li>
 *     </li>
 *     <li>
 *         <b>Usage Type:</b> The type of usage that the client is attempting, one of
 *         <ul>
 *             <li><b>Access:</b> The client is attempting to read, write, or call a member of the supplier.</li>
 *             <li><b>Override:</b> The client is attempting to override a member of the supplier.</li>
 *         </ul>
 *     </li>
 *     <li>
 *         <b>Supplier Visibility:</b> The top level visibility modifier for the supplier classifier, one of
 *         <ul>
 *             <li><b>pub:</b> public visibility</li>
 *             <li><b>pub@:</b> public internal visibility</li>
 *             <li><b>proj:</b> project visibility</li>
 *             <li><b>priv:</b> (module-) private visibility (no modifier added)</li>
 *         </ul>
 *         For the first three visibilities, the keyword <code>export</code> is automatically added in the generated code.
 *     </li>
 *     <li>
 *         <b>Member Visibility:</b> The visibility of the generated member of the supplier that is being accessed by the client, one of
 *         <ul>
 *             <li><b>pub:</b> public visibility</li>
 *             <li><b>pub@:</b> public internal visibility</li>
 *             <li><b>prot:</b> protected visibility</li>
 *             <li><b>prot@:</b> protected internal visibility</li>
 *             <li><b>proj:</b> project visibility</li>
 *             <li><b>priv:</b> private visibility</li>
 *         </ul>
 *     </li>
 *     <li>
 *         <b>Expectation:</b> The expectation of the test's outcome, one of
 *         <ul>
 *             <li><b>y</b>: The test is expected to succeed without warnings or errors.</li>
 *             <li><b>n</b>: The test is expected to fail with an error</li>
 *             <li>
 *                 <b>u</b>: The test is expected to fail with two errors:
 *                 <ul>
 *                     <li>One error at the client classifier level regarding the unusability of the supplier.</li>
 *                     <li>Another error at the member level of the client.</li>
 *             </li>
 *         </ul>
 *     </li>
 * </ul>
 */
// TODO: actually support the "Same Type" client location
// @formatter:on
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
		OK, FAIL, UNUSABLE, SKIP;

		public static Expectation parse(String str) {
			switch (str) {
			case "y":
				return OK;
			case "n":
				return FAIL;
			case "u":
				return UNUSABLE;
			case "":
			case "#":
				return SKIP;
			default:
				throw new IllegalArgumentException("Unexpected expectation: '" + str + "'");
			}
		}
	}

	private static class TestSpecification {
		private final int row;
		private final int column;
		private final Scenario scenario;
		private final ClassifierType supplierType;
		private final ClassifierType clientType;
		private final ClientLocation clientLocation;
		private final MemberType memberType;
		private final UsageType usageType;
		private final Classifier.Visibility supplierVisibility;
		private final Member.Visibility memberVisibility;
		private final Member.Static memberStatic;
		private final Expectation expectation;

		/**
		 * Creates a new instance with the given parameters.
		 *
		 * @param row
		 *            the table row containing this specification
		 * @param column
		 *            the table column containing this specification
		 * @param scenario
		 *            the test scenario
		 * @param supplierType
		 *            the type of the supplier
		 * @param clientType
		 *            the type of the client
		 * @param clientLocation
		 *            the location of the generated client in relation to the generated supplier
		 * @param memberType
		 *            the type of the member being accessed by the client
		 * @param usageType
		 *            the type of usage intended by the client
		 * @param supplierVisibility
		 *            the visibility of the generated supplier type
		 * @param memberVisibility
		 *            the visibility of the generated member the client is attempting to access
		 * @param memberStatic
		 *            whether or not the accessed member is static
		 * @param expectation
		 *            the expected test result
		 */
		public TestSpecification(int row, int column, Scenario scenario, ClassifierType supplierType,
				ClassifierType clientType,
				ClientLocation clientLocation, MemberType memberType,
				UsageType usageType,
				Classifier.Visibility supplierVisibility,
				Member.Visibility memberVisibility, Member.Static memberStatic, Expectation expectation) {
			this.row = row;
			this.column = column;
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
		 * Returns the usage type by which the client intends to access the generated supplier's members.
		 *
		 * @return the usage type
		 */
		public UsageType getUsageType() {
			return usageType;
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
			return "[" + (row + 1) + ", " + (column + 1) + "]: " + scenario + " scenario with supplier "
					+ supplierType
					+ " and client "
					+ clientType
					+ " at " + clientLocation
					+ ": "
					+ usageType + " to "
					+ memberVisibility + (memberStatic == Member.Static.YES ? " STATIC " : " INSTANCE ")
					+ memberType + " with expectation: "
					+ expectation;
		}
	}

	// @formatter:off
	/**
	 * Returns the parameters for this test by parsing the appropriate CSV file. Expects the following structure of the table in the CSV:
	 *
	 * <br /><br />
	 * <table border="1">
	 *     <tr>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center" colspan="48"><b>Instance | Static</b></td>
	 *         <td align="center" colspan="48"><b>Instance | Static</b></td>
	 *     </tr>
	 *     <tr>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center" colspan="24"><b>Override | Access</b></td>
	 *         <td align="center" colspan="24"><b>Override | Access</b></td>
	 *         <td align="center" colspan="24"><b>Override | Access</b></td>
	 *         <td align="center" colspan="24"><b>Override | Access</b></td>
	 *     </tr>
	 *     <tr>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center" colspan="6"><b>pub</b></td>
	 *         <td align="center" colspan="6"><b>pub@</b></td>
	 *         <td align="center" colspan="6"><b>proj</b></td>
	 *         <td align="center" colspan="6"><b>priv</b></td>
	 *         <td align="center" colspan="6"><b>pub</b></td>
	 *         <td align="center" colspan="6"><b>pub@</b></td>
	 *         <td align="center" colspan="6"><b>proj</b></td>
	 *         <td align="center" colspan="6"><b>priv</b></td>
	 *         <td align="center" colspan="6"><b>pub</b></td>
	 *         <td align="center" colspan="6"><b>pub@</b></td>
	 *         <td align="center" colspan="6"><b>proj</b></td>
	 *         <td align="center" colspan="6"><b>priv</b></td>
	 *         <td align="center" colspan="6"><b>pub</b></td>
	 *         <td align="center" colspan="6"><b>pub@</b></td>
	 *         <td align="center" colspan="6"><b>proj</b></td>
	 *         <td align="center" colspan="6"><b>priv</b></td>
	 *     </tr>
	 *     <tr>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *         <td><b>pub</b></td>
	 *         <td><b>pub@</b></td>
	 *         <td><b>prot</b></td>
	 *         <td><b>prot@</b></td>
	 *         <td><b>proj</b></td>
	 *         <td><b>priv</b></td>
	 *     </tr>
	 *     <tr>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center"><i>ignored</i></td>
	 *         <td align="center" colspan="96"><i>ignored</i></td>
	 *     </tr>
	 *     <tr>
	 *         <td align="center" rowspan="5"><b>Extends&nbsp;| Implements&nbsp;| References</b></td>
	 *         <td align="center" rowspan="5"><b>Class&nbsp;| Abstract&nbsp;Class&nbsp;| Interface&nbsp;| Default&nbsp;Interface</b></td>
	 *         <td align="center" rowspan="5"><b>Class&nbsp;| Abstract&nbsp;Class&nbsp;| Interface&nbsp;| Default&nbsp;Interface</b></td>
	 *         <td align="center"><b>Same&nbsp;Type</b></td>
	 *         <td align="center">y|n|u</td>
	 *         <td align="center" colspan="95">...</td>
	 *     </tr>
	 *     <tr>
	 *         <td align="center"><b>Same&nbsp;Module</b></td>
	 *         <td align="center" rowspan="4">...</td>
	 *         <td align="center" rowspan="4" colspan="95">...</td>
	 *     </tr>
	 *     <tr>
	 *         <td align="center"><b>Same&nbsp;Project</b></td>
	 *     </tr>
	 *     <tr>
	 *         <td align="center"><b>Same&nbsp;Vendor</b></td>
	 *     </tr>
	 *     <tr>
	 *         <td align="center"><b>Other</b></td>
	 *     </tr>
	 *     <tr>
	 *         <td align="center">...</td>
	 *         <td align="center">...</td>
	 *         <td align="center">...</td>
	 *         <td align="center">...</td>
	 *         <td align="center" colspan="96">...</td>
	 *     </tr>
	 * </table>
	 * <br />
	 *
	 * Note each cell within the range between rows 6,...,10 and the columns 5,... corresponds to four scenarios that only differ by the actual member type being generated in the supplier type, however the expectation is the same in each case. More test cases can be specified with different parameters for the scenario, supplier type, and client type. Each combination of these three parameters is followed by 5 x 96 cells of expectations that correspond to the different client location, visibilities, usage type and static specifiers.
	 * @return the parameters
	 */
    // @formatter:on
	@Parameters(name = "{0}")
	public static Iterable<? extends Object> data() throws IOException {
		CSVParser parser = new CSVParser("testdata/accesscontrol/ClassVisibility.csv", StandardCharsets.UTF_8);
		CSVData csvData = parser.getData();

		List<TestSpecification> result = new LinkedList<>();

		CSVData accessSpec = csvData.getRange(0, 4, 4, -1);
		CSVData scenarios = csvData.getRange(5, 0, -1, -1);

		for (int row = 0; row < scenarios.getSize() / 5; row++) {
			CSVData scenario = scenarios.getRange(row * 5, 0, 5, -1);
			result.addAll(createScenario(accessSpec, scenario, row * 5, 0));
		}

		return result;
	}

	private static List<TestSpecification> createScenario(CSVData accessSpec, CSVData data, int rowOffset,
			int columnOffset) {
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
						TestSpecification specification = new TestSpecification(row + rowOffset, col + columnOffset,
								scenario, supplierType, clientType,
								clientLocation, memberType,
								usageType, supplierVisibility, memberVisibility, memberStatic, expectation);
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

	/**
	 * Generates a test scenario according to the parameters specified in {@link #specification}.
	 *
	 * @return a list files representing the root directories of the created projects
	 */
	private List<File> generateScenario() {
		List<File> result = new LinkedList<>();

		// Create the required classifiers for the scenario.
		ScenarioResult scenario = createScenario();
		Classifier<?> supplier = scenario.supplier;
		Classifier<?> client = scenario.client;
		Class factory = scenario.factory;
		Class implementer = scenario.implementer;

		// Create a member for the supplier according the specified visibility and "abstract-ness".
		switch (specification.getSupplierType()) {
		case CLASS:
		case DEFAULT_INTERFACE:
			supplier.addMember(createMember("member", specification.getMemberVisibility()));
			break;
		case ABSTRACT_CLASS:
		case INTERFACE:
			supplier.addMember(createMember("member", specification.getMemberVisibility()).makeAbstract());
			break;
		}

		// Create members that actually use the supplier's member that was just
		// created. Depending on the scenario and the usage type, such a member may override the supplier's member
		// or it may attempt to access it by reading or writing it or calling it if it is a method.
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
				if (specification.getSupplierType() != ClassifierType.INTERFACE)
					break;
				// We want to fall through here because in the case of implementing an abstract interface, we need to
				// implement the abstract member.
				// $FALL-THROUGH$
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
			switch (specification.getSupplierType()) {
			case CLASS:
			case DEFAULT_INTERFACE:
				break;
			case ABSTRACT_CLASS:
			case INTERFACE:
				implementer.addMember(createMember("member", specification.getMemberVisibility()).makeOverride());
				break;
			}

			// Create a method that accesses the supplier's member via an instance created by the factory.
			client.addMember(createAccess("member", "new GetS().getS()"));
			break;
		}
		}

		// Now create the actual modules, source folders and projects that contain the classifiers that were just
		// created, according to the client location in the test specification.
		switch (specification.getClientLocation()) {
		case SAME_TYPE:
		case SAME_MODULE: {
			Module module = new Module("SameModule");
			module.addClassifier(supplier);
			if (implementer != null)
				module.addClassifier(implementer);
			if (factory != null)
				module.addClassifier(factory);
			module.addClassifier(client);

			Project project = new Project("SameModule", "sameVendor", "SameVendor");
			project.createSourceFolder("src").addModule(module);
			result.add(project.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case SAME_PROJECT: {
			Module supplierModule = createSupplierModule(supplier, factory, implementer);
			Module clientModule = createClientModule(client, supplier, factory, supplierModule);

			Project project = new Project("SameProject", "sameVendor", "SameVendor");
			project.createSourceFolder("src").addModule(supplierModule).addModule(clientModule);
			result.add(project.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case SAME_VENDOR: {
			Module supplierModule = createSupplierModule(supplier, factory, implementer);
			Module clientModule = createClientModule(client, supplier, factory, supplierModule);

			Project supplierProject = createSupplierProject(supplierModule, "sameVendor");
			Project clientProject = createClientProject(clientModule, "sameVendor", supplierProject);

			result.add(supplierProject.create(Paths.get(FIXTURE_ROOT)));
			result.add(clientProject.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		case OTHER_VENDOR: {
			Module supplierModule = createSupplierModule(supplier, factory, implementer);
			Module clientModule = createClientModule(client, supplier, factory, supplierModule);

			Project supplierProject = createSupplierProject(supplierModule, "vendorA");
			Project clientProject = createClientProject(clientModule, "vendorB", supplierProject);

			result.add(supplierProject.create(Paths.get(FIXTURE_ROOT)));
			result.add(clientProject.create(Paths.get(FIXTURE_ROOT)));
			break;
		}
		default:
			break;
		}

		return result;
	}

	/**
	 * Represents the result of creating the required classifiers for a scenario. In the least, the result consists of a
	 * supplier and a client classifier. For the {@link Scenario#REFERENCE} scenario, the result will also contain a
	 * factory class that creates an instance of the supplier and returns it via the method <code>getS()</code> and
	 * optionally an implementer that extends or implements the supplier classifier if it is abstract or an interface.
	 */
	private static class ScenarioResult {
		public Classifier<?> supplier;
		public Classifier<?> client;
		public Class factory;
		public Class implementer;

		/**
		 * Creates a new result that consists only of a supplier and a client.
		 *
		 * @param supplier
		 *            the supplier
		 * @param client
		 *            the client
		 */
		public ScenarioResult(Classifier<?> supplier, Classifier<?> client) {
			this.supplier = Objects.requireNonNull(supplier);
			this.client = Objects.requireNonNull(client);
			this.factory = null;
			this.implementer = null;
		}

		/**
		 * Creates a new result that consists of a supplier, a client, and a factory.
		 *
		 * @param supplier
		 *            the supplier
		 * @param client
		 *            the client
		 * @param factory
		 *            the factory
		 */
		public ScenarioResult(Classifier<?> supplier, Class client, Class factory) {
			this(supplier, client);
			this.factory = Objects.requireNonNull(factory);
			this.implementer = null;
		}

		/**
		 * Creates a new result that consists of a supplier, a client, a factory, and an implementer.
		 *
		 * @param supplier
		 *            the supplier
		 * @param client
		 *            the client
		 * @param factory
		 *            the factory
		 * @param implementer
		 *            the implementer
		 */
		public ScenarioResult(Classifier<?> supplier, Class client, Class factory, Class implementer) {
			this(supplier, client, factory);
			this.implementer = Objects.requireNonNull(implementer);
		}
	}

	/**
	 * Creates the scenario. To be more precise, this method creates the classifiers that participate in the scenario,
	 * but not the required members.
	 *
	 * @return the created classifiers wrapped in an instance of {@link ScenarioResult}.
	 */
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

	/**
	 * Creates an instance of the extension scenario where either one class extends another or where one interface
	 * extends another.
	 *
	 * @return the created classifiers wrapped in an instance of {@link ScenarioResult}.
	 *
	 * @throws IllegalArgumentException
	 *             if the supplier and client types do not agree with this scenario, e.g., if the supplier is a class
	 *             and the client is an interface
	 */
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

	/**
	 * Creates an instance of the implementation scenario where the supplier is an interface and the client is a class
	 * implementing that interface.
	 *
	 * @return the created classifiers wrapped in an instance of {@link ScenarioResult}.
	 *
	 * @throws IllegalArgumentException
	 *             if the supplier and client types do not agree with this scenario, e.g., if the supplier is a class
	 */
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

	/**
	 * Creates an instance of the reference scenario where the supplier and client can be any type. Additionally creates
	 * a factory and, optionally, an implementer if the supplier is an abstract class or an interface. In this scenario,
	 * the client must be a class.
	 *
	 * @return the created classifiers wrapped in an instance of {@link ScenarioResult}
	 *
	 * @throws IllegalArgumentException
	 *             if the supplier and client types do not agree with this scenario, e.g., if the supplier is a class
	 *             and the client is an interface
	 */
	private ScenarioResult createReferenceScenario() {
		switch (specification.getSupplierType()) {
		case CLASS: {
			Class supplier = new Class("S").setVisibility(specification.getSupplierVisibility());
			Class factory = new Class("GetS").setVisibility(Classifier.Visibility.PUBLIC);
			factory.addMember(new Method("getS").setVisibility(Member.Visibility.PUBLIC).setReturnType("S")
					.setBody("return new S();"));
			switch (specification.getClientType()) {
			case CLASS:
				return new ScenarioResult(supplier, new Class("C"), factory);
			case ABSTRACT_CLASS:
			case INTERFACE:
			case DEFAULT_INTERFACE:
				throw new IllegalArgumentException(
						"Invalid Scenario: Cannot instantiate abstract classes or interfaces for reference scenario");
			}
			break;
		}
		case ABSTRACT_CLASS: {
			Class supplier = new Class("S").setVisibility(specification.getSupplierVisibility()).makeAbstract();
			Class implementer = new Class("SImpl").setSuperClass(supplier).setVisibility(Classifier.Visibility.PUBLIC);
			Class factory = new Class("GetS").setVisibility(Classifier.Visibility.PUBLIC);
			factory.addMember(new Method("getS").setVisibility(Member.Visibility.PUBLIC).setReturnType("S")
					.setBody("return new SImpl();"));
			switch (specification.getClientType()) {
			case CLASS:
				return new ScenarioResult(supplier, new Class("C"), factory, implementer);
			case ABSTRACT_CLASS:
			case INTERFACE:
			case DEFAULT_INTERFACE:
				throw new IllegalArgumentException(
						"Invalid Scenario: Cannot instantiate abstract classes or interfaces for reference scenario");
			}
			break;
		}
		case INTERFACE: {
			Interface supplier = new Interface("S").setVisibility(specification.getSupplierVisibility());
			Class implementer = new Class("SImpl").addInterface(supplier).setVisibility(Classifier.Visibility.PUBLIC);
			Class factory = new Class("GetS").setVisibility(Classifier.Visibility.PUBLIC);
			factory.addMember(new Method("getS").setVisibility(Member.Visibility.PUBLIC).setReturnType("S")
					.setBody("return new SImpl();"));
			switch (specification.getClientType()) {
			case CLASS:
				return new ScenarioResult(supplier, new Class("C"), factory, implementer);
			case ABSTRACT_CLASS:
			case INTERFACE:
			case DEFAULT_INTERFACE:
				throw new IllegalArgumentException(
						"Invalid Scenario: Cannot instantiate abstract classes or interfaces for reference scenario");
			}
			break;
		}
		case DEFAULT_INTERFACE: {
			Interface supplier = new Interface("S").setVisibility(specification.getSupplierVisibility());
			Class implementer = new Class("SImpl").addInterface(supplier).setVisibility(Classifier.Visibility.PUBLIC);
			Class factory = new Class("GetS").setVisibility(Classifier.Visibility.PUBLIC);
			factory.addMember(new Method("getS").setVisibility(Member.Visibility.PUBLIC).setReturnType("S")
					.setBody("return new SImpl();"));
			switch (specification.getClientType()) {
			case CLASS:
				return new ScenarioResult(supplier, new Class("C"), factory, implementer);
			case ABSTRACT_CLASS:
			case INTERFACE:
			case DEFAULT_INTERFACE:
				throw new IllegalArgumentException(
						"Invalid Scenario: Cannot instantiate abstract classes or interfaces for reference scenario");
			}
			break;
		}
		}

		throw new IllegalArgumentException("Unexpected supplier type: " + specification.getSupplierType());
	}

	/**
	 * Creates a new member with the given name and visibility. The type of member depends on the value returned by
	 * {@link TestSpecification#getMemberType()}. The member is also set to static according to the value returned by
	 * {@link TestSpecification#getMemberStatic()}.
	 *
	 * @param name
	 *            the name of the created member
	 * @param visibility
	 *            the visibility of the created member
	 *
	 * @return the newly created member
	 */
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

	/**
	 * Creates a method in which the supplier's member with the given name is accessed. The given expression will be
	 * used to retrieve the subject of access, i.e., the supplier. In most cases, we will pass <code>this</code> as the
	 * subject expression.
	 *
	 * @param memberName
	 *            the name of the member being accessed
	 * @param subjectExpression
	 *            the expression used to retrieve the subject of access
	 *
	 * @return the newly created method
	 */
	private Method createAccess(String memberName, String subjectExpression) {
		Method result = null;
		switch (specification.getMemberType()) {
		case FIELD:
			result = new Method("accessor").setBody("var t = " + subjectExpression + "." + memberName + ";");
			break;
		case GETTER:
			result = new Method("accessor").setBody("var t = " + subjectExpression + "." + memberName + ";");
			break;
		case SETTER:
			result = new Method("accessor").setBody("" + subjectExpression + "." + memberName + " = null;");
			break;
		case METHOD:
			result = new Method("accessor").setBody("" + subjectExpression + "." + memberName + "();");
			break;
		default:
			throw new IllegalArgumentException("Unknown member type: " + specification.getMemberType());
		}

		if (specification.getMemberStatic() == Member.Static.YES)
			result.makeStatic();

		return result;
	}

	/**
	 * Creates the supplier module with the given supplier classifier, factory, and implementer classes. While the
	 * supplier classifier must not be <code>null</code>, the factory and / or the implementer may be <code>null</code>.
	 * The newly created module will contain the supplier classifier and the given factory and / or implementer if they
	 * are not <code>null</code>.
	 *
	 * @param supplier
	 *            the supplier classifier
	 * @param factory
	 *            the factory class
	 * @param implementer
	 *            the implementer class
	 * @return the newly created module
	 */
	private Module createSupplierModule(Classifier<?> supplier, Class factory, Class implementer) {
		Module supplierModule = new Module("SupplierModule");
		supplierModule.addClassifier(supplier);
		if (implementer != null)
			supplierModule.addClassifier(implementer);
		if (factory != null)
			supplierModule.addClassifier(factory);
		return supplierModule;
	}

	/**
	 * Creates the client module with the given client classifier. If the given factory is not <code>null</code>, the
	 * newly created module will import the given supplier classifier from the given supplier module. Otherwise, it will
	 * import the given factory from the given supplier module, but not the given supplier itself.
	 *
	 * @param client
	 *            the client classifier
	 * @param supplier
	 *            the supplier classifier
	 * @param supplierFactory
	 *            the supplier factory
	 * @param supplierModule
	 *            the supplier module containing the supplier classifier and factory
	 *
	 * @return the newly created module
	 */
	private Module createClientModule(Classifier<?> client, Classifier<?> supplier, Class supplierFactory,
			Module supplierModule) {
		Module clientModule = new Module("ClientModule");
		if (supplierFactory != null)
			clientModule.addImport(supplierFactory, supplierModule);
		else
			clientModule.addImport(supplier, supplierModule);
		clientModule.addClassifier(client);
		return clientModule;
	}

	/**
	 * Creates the supplier project containing the given module and vendor ID.
	 *
	 * @param supplierModule
	 *            the supplier module to add the project
	 * @param vendorId
	 *            the vendor ID
	 *
	 * @return the newly created project
	 */
	private Project createSupplierProject(Module supplierModule, String vendorId) {
		Project supplierProject = new Project("SupplierProject", vendorId, vendorId + "_name");
		supplierProject.createSourceFolder("src").addModule(supplierModule);
		return supplierProject;
	}

	/**
	 * Creates the client project containing the given module and vendor ID with a dependency on the given supplier
	 * project.
	 *
	 * @param clientModule
	 *            the client module to add to the project
	 * @param vendorId
	 *            the vendor ID
	 * @param supplierProject
	 *            the supplier project which the newly created project should depend on
	 *
	 * @return the newly created project
	 */
	private Project createClientProject(Module clientModule, String vendorId, Project supplierProject) {
		Project clientProject = new Project("ClientProject", vendorId, vendorId + "_name");
		clientProject.addProjectDependency(supplierProject);
		clientProject.createSourceFolder("src").addModule(clientModule);
		return clientProject;
	}

	/**
	 * Creates the issues according to the expectation returned by {@link TestSpecification#getExpectation()}.
	 *
	 * @return an instance of {@link IssuesMatcher} that represents the expectations
	 */
	private IssuesMatcher createIssues() {
		IssuesMatcher result = new IssuesMatcher();

		switch (specification.getExpectation()) {
		case OK:
			break;
		case FAIL:
			result.add().error();
			break;
		case UNUSABLE:
			result.add().error();
			if (specification.getMemberType() != MemberType.FIELD) // fields cannot be abstract
				result.add().error().message().startsWith("Cannot use");
			break;
		default:
			throw new IllegalArgumentException("Unexpected test expectation: " + specification.getExpectation());
		}

		return result;
	}

	private static N4HeadlessCompiler hlc = N4HeadlessCompiler.injectAndSetup(null);

	/**
	 * Compiles the projects at the given root paths, which in this test case the projects representing the currently
	 * tested scenario (created by {@link #generateScenario()}) and asserts the expectations represented by the given
	 * instance of {@link IssuesMatcher}.
	 *
	 * @param projectRoots
	 *            the root paths of the projects to be compiled
	 * @param matcher
	 *            the test expectations
	 */
	private void compileAndAssert(List<File> projectRoots, IssuesMatcher matcher) {
		IssueCollector issueCollector = new IssueCollector();
		try {
			hlc.compileAllProjects(projectRoots, issueCollector);
		} catch (N4JSCompileException e) {
			// nothing to do
		}

		assertIssues(issueCollector.getCollectedIssues(), matcher);
	}

	/**
	 * Asserts that the given issues are matched exactly by the given expectations.
	 *
	 * @see IssuesMatcher#matchesExactly(Collection, List)
	 *
	 * @param issues
	 *            the issues to match
	 * @param matchers
	 *            the expectations
	 */
	private void assertIssues(Collection<Issue> issues, IssuesMatcher matchers) {
		List<String> messages = new LinkedList<>();
		boolean result = matchers.matchesExactly(issues, messages);
		assertTrue(Joiner.on(", ").join(messages), result);
	}

	/**
	 * Recreates the test fixture root directory. If the directory exists, it will be deleted before it is created
	 * again.
	 *
	 * @throws IOException
	 *             if the directory cannot be created
	 */
	private void createFixtureDirectory() throws IOException {
		File file = new File(FIXTURE_ROOT);
		if (file.exists())
			deleteFixtureDirectory();
		file.mkdir();
	}

	/**
	 * Deletes the test fixture root directory.
	 *
	 * @throws IOException
	 *             if the directory cannot be deleted
	 */
	private void deleteFixtureDirectory() throws IOException {
		FileDeleter.delete(new File(FIXTURE_ROOT));
	}
}
