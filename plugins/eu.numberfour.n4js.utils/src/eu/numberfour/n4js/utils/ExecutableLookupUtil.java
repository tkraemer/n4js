/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.utils;

import static eu.numberfour.n4js.utils.OSInfo.isWindows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

/**
 * Utility which is asking underlying OS to locate executable specified by the provided name. Note that this operates on
 * assumptions about underlying OS. Results will depend on OS behavior.
 *
 */
public class ExecutableLookupUtil {

	private static String[] WIN_SHELL_COMAMNDS = { "cmd", "/c" };
	private static String[] NIX_SHELL_COMAMNDS = { "sh", "-c" };
	private static String WIN_FINDER = "where";
	private static String NIX_FINDER = "which";
	private static String PATH = "PATH";

	/**
	 * Creates process builder that when invoked will use OS specific tool to locate desired binary.
	 *
	 * <p/>
	 * For *nix family, OS is queried with {@code which} command.
	 * <ul>
	 * </ul>
	 *
	 * For MS family, OS is queried with {@code where} command.
	 * <ul>
	 * <li>for server family OS needs to be Win2K3 or newer</li>
	 * <li>for desktop family OS needs to be Win7 or newer</li>
	 * <li>does not work with %PATH$% quoted values</li>
	 * <li>does not take %PATHEXT% into account</li>
	 * <li>%windir%\system32 needs to be in the %PATH% ({@code where} is not shell built in).</li>
	 * </ul>
	 *
	 * @see <a href="http://ss64.com/bash/which.html">which</a>
	 * @see <a href="http://ss64.com/nt/where.html">where</a>
	 *
	 */
	public static ProcessBuilder getExebutableLookupProcessBuilder(String execName) {
		final Builder<String> builder = ImmutableList.<String> builder();
		if (isWindows()) {
			builder.add(WIN_SHELL_COMAMNDS);
			builder.add(WIN_FINDER);
			builder.add(execName);
		} else {
			builder.add(NIX_SHELL_COMAMNDS);
			builder.add(NIX_FINDER + " " + execName);
		}

		final ProcessBuilder processBuilder = new ProcessBuilder(builder.build());
		processBuilder.redirectErrorStream(false);
		return processBuilder;
	}

	/**
	 * Simple lookup that will examine system {@code PATH} variable to find desired executable. Will return string with
	 * first matching path or {@code null}.
	 */
	public static String findInPath(String exec) {
		return Stream.of(System.getenv(PATH).split(Pattern.quote(File.pathSeparator)))
				.map(Paths::get)
				.filter(path -> Files.exists(path.resolve(exec)))
				.findFirst()
				.map(p -> p.toString())
				.orElse(null);
	}
}