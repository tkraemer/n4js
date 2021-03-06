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
package eu.numberfour.n4js.validation.helper;

import static com.google.common.collect.Sets.newHashSet;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.valueOf;
import static java.util.Collections.unmodifiableCollection;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import eu.numberfour.n4js.n4JS.N4JSASTUtils;
import eu.numberfour.n4js.n4mf.ModuleLoader;

/**
 * Contains constants for the N4JS language.
 */
public abstract class N4JSLanguageConstants {

	//@formatter:off

	/** The reserved {@value} keyword. */
	public static final String CONSTRUCTOR = N4JSASTUtils.CONSTRUCTOR;

	/** Direct access to the export keyword */
	public static final String EXPORT_KEYWORD = "export";
	/** Direct access to the external keyword */
	public static final String EXTERNAL_KEYWORD = "external";



	/** <a href="https://people.mozilla.org/~jorendorff/es6-draft.html#sec-keywords">ECMAScript ver. [6 11.6.2.1] Keywords</a>.*/
	public static final Collection<String> KEYWORDS = unmodifiableCollection(newHashSet(
			"break",	"do",			"in",			"typeof",
			"case",		"else",			"instanceof",	"var",
			"catch",	EXPORT_KEYWORD,	"new",			"void",
			"class",	"extends",		"return",		"while",
			"const",	"finally",		"super",		"with",
			"continue",	"for",			"switch",		"yield",
			"debugger",	"function",		"this",
			"default",	"if",			"throw",
			"delete",	"import",		"try"
			));

	//@formatter:on

	/** Base ECMAScript and N4JS primitive types and literals. */
	public static final Collection<String> BASE_TYPES = unmodifiableCollection(newHashSet(
			"boolean", "number", "string", "null", "any"));

	/** Direct access to the get keyword */
	public static final String GET_KEYWORD = "get";
	/** Direct access to the set keyword */
	public static final String SET_KEYWORD = "set";

	/** Getter and setter keywords. */
	public static final Collection<String> GETTER_SETTER = unmodifiableCollection(newHashSet(
			GET_KEYWORD, SET_KEYWORD));

	/** Boolean literals such as {@code true} and {@code false}. */
	public static final Collection<String> BOOLEAN_LITERALS = unmodifiableCollection(newHashSet(
			valueOf(TRUE), valueOf(FALSE)));

	/**
	 * Future reserved words for ECMAScript. Contains already reserved words for N4JS such as {@code enum}.
	 * <p>
	 * Although {@code let} should be a strict mode reserved word it does cause runtime error when using as a function
	 * or method name.
	 */
	public static final Collection<String> FUTURE_RESERVED_WORDS = unmodifiableCollection(newHashSet(
			"let", "enum", "await"));

	/** Access modifiers for the N4JS language. */
	public static final Collection<String> ACCESS_MODIFIERS = unmodifiableCollection(newHashSet(
			"private", "project", "protected", "public"));

	/**
	 * A map of characters (with their corresponding human readable names) that are discouraged to be used anywhere in
	 * variable names although ECMAScript allows their usage.
	 * <p>
	 * <b>Key:&nbsp;</b>The actual characters.<br>
	 * <b>Values:&nbsp;</b>The human readable names of the characters.
	 */
	public static final Map<String, String> DISCOURAGED_CHARACTERS = ImmutableMap.of(
			"$", "dollar sign");

	/**
	 * Suffix used in method compilation for the local function name as reported in error stack traces. Value:
	 * {@code "___n4"}
	 */
	public static final String METHOD_STACKTRACE_SUFFIX = "___n4";

	/**
	 * Property holding DI information used by N4JS dependency injection (in runtime).
	 */
	public static final String DI_PROP_NAME = "$di";

	/**
	 * The default module loader.
	 */
	public static final ModuleLoader MODULE_LOADER_DEFAULT = ModuleLoader.N4JS;

	/**
	 * Maps literals of {@link ModuleLoader} to prefixes that the transpiler should prepend to module specifiers. If
	 * this map does not contain an entry for a module loader type, then no prefix should be prepended.
	 */
	public static final Map<ModuleLoader, ModuleSpecifierAdjustment> MODULE_LOADER_PREFIXES = ImmutableMap.of(
			ModuleLoader.COMMONJS, new ModuleSpecifierAdjustment("@@cjs", false),
			ModuleLoader.NODE_BUILTIN, new ModuleSpecifierAdjustment("@node", true));

	/**
	 * The sub-folder in a project's output folder that contains the generated output files of the transpiler that is to
	 * be used during all testing.
	 *
	 * TODO this is temporary, should be improved (only added here to have a single point where this is defined)
	 */
	public static final String TRANSPILER_SUBFOLDER_FOR_TESTS = "es";

	private N4JSLanguageConstants() {
	}

	/**
	 * Data class for defining how the transpiler should adjust module specifiers depending on module loader supported
	 * by the target module (i.e. the module imported from).
	 */
	public static final class ModuleSpecifierAdjustment {
		/** Prefix to be added to the module specifier, e.g. "@node". */
		public final String prefix;
		/**
		 * Normally the output code contains complete module specifiers for imported modules; if this is set to
		 * <code>true</code> the transpiler will emit a plain module specifier (without project name) instead.
		 */
		public final boolean usePlainModuleSpecifier;

		/**
		 * See {@link ModuleSpecifierAdjustment} for details.
		 */
		public ModuleSpecifierAdjustment(String prefix, boolean usePlainModuleSpecifier) {
			if (prefix == null)
				throw new IllegalArgumentException("prefix may not be null (but you may use an empty string)");
			this.prefix = prefix;
			this.usePlainModuleSpecifier = usePlainModuleSpecifier;
		}
	}
}
