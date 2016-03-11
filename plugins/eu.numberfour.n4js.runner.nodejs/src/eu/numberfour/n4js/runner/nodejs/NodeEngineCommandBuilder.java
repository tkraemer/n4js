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
package eu.numberfour.n4js.runner.nodejs;

import static com.google.common.base.CharMatcher.BREAKING_WHITESPACE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Splitter;
import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.binaries.nodejs.NodeJsBinary;
import eu.numberfour.n4js.runner.DefaultBootstrap;
import eu.numberfour.n4js.runner.SystemLoaderInfo;

/**
 * Builds command line command for executing the node.js engine. Clients must call command on their own. Clients must
 * configure runtime environment on their own.
 */
public class NodeEngineCommandBuilder {

	private static final String OPT_HARMONY = "--harmony";

	/** Command line option to signal COMMON_JS */
	private static final String CJS_COMMAND = "cjs";

	@Inject
	private Provider<NodeJsBinary> nodeJsBinary;

	/**
	 * Creates commands for calling Node.js on command line. Data wrapped in passed parameter is used to configure node
	 * itself, and to generate file that will be executed by Node.
	 */
	public String[] createCmds(NodeRunOptions nodeRunOptions) throws IOException {

		final ArrayList<String> commands = new ArrayList<>();

		commands.add(nodeJsBinary.get().getCommandWithAbsolutePath());

		// brute force harmony
		commands.add(OPT_HARMONY);
		// allow user flags
		final String nodeOptions = nodeRunOptions.getEngineOptions();
		if (nodeOptions != null) {
			for (String nodeOption : Splitter.on(BREAKING_WHITESPACE).omitEmptyStrings().split(nodeOptions)) {
				commands.add(nodeOption);
			}
		}

		final StringBuilder elfData = getELFCode(nodeRunOptions.getInitModules(),
				nodeRunOptions.getExecModule(), nodeRunOptions.getExecutionData());

		final File elf = createTempFileFor(elfData.toString());

		commands.add(elf.getCanonicalPath());

		if (nodeRunOptions.getSystemLoader() == SystemLoaderInfo.COMMON_JS) {
			commands.add(CJS_COMMAND);
		}

		return commands.toArray(new String[] {});
	}

	/**
	 * generates ELF code, according to N4JSDesign document, chap. 15 : Execution, section 15.2 N4JS Execution And
	 * Linking File
	 *
	 * @param list
	 *            of runtime modules to be bootstrapped
	 * @param entryPoint
	 *            of the code to be executed
	 * @param executionData
	 *            that is expected by execution module
	 * @return elf data in format for used JS engine
	 */
	private StringBuilder getELFCode(List<String> list, String entryPoint, String executionData) {
		final StringBuilder elfCode = new StringBuilder();
		elfCode.append(generateExecutionData(executionData)).append("\n");
		elfCode.append(generateNativeLoad(entryPoint)).append("\n");
		return elfCode;
	}

	/**
	 * This is contract between concrete execution environment and run/test environment.
	 */
	private String generateExecutionData(String data) {
		/*
		 * In this form execution module needs to read prop '_executionData' from global scope (also would be good idea
		 * to remove it). It would be possible that execution module exports function that takes this data as parameter
		 * but then we need to change order of things in ELF file, that is execution module has to be loaded, its export
		 * function assigned to variable and called with this data below.
		 *
		 * keep it in sync
		 */
		return "GLOBAL.$executionData = " + data + ";";
	}

	private String generateNativeLoad(String moduleName) {
		if (null == moduleName) {
			moduleName = DefaultBootstrap.getDefaultNodeEnvPath() + "/n4js-node/n4js-cli.js";
		}
		return "require('" + moduleName + "');";
	}

	/**
	 * Writes a file to a temporary file, returning the file path.
	 *
	 * @param content
	 *            file content
	 * @return file
	 */
	private static File createTempFileFor(String content) throws IOException {
		final File temp = File.createTempFile("n4jsnode", "." + N4JSGlobals.JS_FILE_EXTENSION);
		final BufferedWriter writer = new BufferedWriter(new FileWriter(temp));

		try {
			writer.write(content);
		} finally {
			writer.close();
		}

		temp.deleteOnExit();

		return temp;
	}
}
