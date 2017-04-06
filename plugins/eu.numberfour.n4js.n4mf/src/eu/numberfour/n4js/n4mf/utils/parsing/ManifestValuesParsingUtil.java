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
package eu.numberfour.n4js.n4mf.utils.parsing;

import static java.util.logging.Level.SEVERE;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.LazyStringInputStream;

import eu.numberfour.n4js.n4mf.DeclaredVersion;
import eu.numberfour.n4js.n4mf.ProjectDependency;
import eu.numberfour.n4js.n4mf.ProjectDescription;
import eu.numberfour.n4js.n4mf.VersionConstraint;
import eu.numberfour.n4js.n4mf.utils.N4MFConstants;
import eu.numberfour.n4js.n4mf.utils.content.ManifestContentFactory;
import eu.numberfour.n4js.utils.languages.N4LanguageUtils;

/**
 * Utility for creating N4MF model elements from string values. Reuses actual N4MF parser by creating artificial N4MF
 * resource and parsing with normal production parser. Provided string values are inserted into artificial resource by
 * combining manifest template with provided values. After resource is parsed its contents are retrieved and returned to
 * the caller. Note that returned elements are created in artificial way and may not be valid according to N4MF
 * validations or other expectations for proper manifest files.
 *
 * Use with care.
 *
 */
public class ManifestValuesParsingUtil {
	private final static Logger LOGGER = Logger.getLogger(ManifestValuesParsingUtil.class.getName());

	/**
	 * Creates instance of {@link ProjectDependency} from provided value or null if it cannot be created.
	 *
	 * @throws ParseException
	 *             when provided data has parse error
	 */
	public static ParseResult<ProjectDependency> parseDependency(String projectDependency) throws Exception {

		String manifestContent = ManifestContentFactory.n4Content(
				n4mf -> n4mf.projectDependencies = Arrays.asList(projectDependency));

		ParseResult<ProjectDescription> parseResult = parse(manifestContent);

		// map project description to the DeclaredVersion
		ParseResult<ProjectDependency> result = new ParseResult<>();
		ProjectDescription description = parseResult.data;
		if (description != null) {
			List<ProjectDependency> deps = description.getAllProjectDependencies();
			if (deps != null && !deps.isEmpty()) {
				result.data = deps.get(0);
			}
		}
		result.addErrors(parseResult.errors);
		return result;
	}

	/**
	 * Creates instance of {@link DeclaredVersion} from provided value or null if it cannot be created.
	 */
	public static ParseResult<DeclaredVersion> parseDeclaredVersion(String declaredVersion) {

		String manifestContent = ManifestContentFactory.n4Content(
				n4mf -> n4mf.projectDependencies = Arrays.asList("_syntheticDependency " + declaredVersion));

		ParseResult<ProjectDescription> parseResult = parse(manifestContent);

		// map project description to the DeclaredVersion
		ParseResult<DeclaredVersion> result = new ParseResult<>();
		ProjectDescription description = parseResult.data;
		if (description != null) {
			List<ProjectDependency> deps = description.getAllProjectDependencies();
			if (deps != null && !deps.isEmpty()) {
				ProjectDependency dependency = deps.get(0);
				if (dependency != null) {
					VersionConstraint versionConstraint = dependency.getVersionConstraint();
					result.data = versionConstraint != null ? versionConstraint.getLowerVersion() : null;
				}
			}
		}

		result.addErrors(parseResult.errors);
		return result;
	}

	/**
	 * Parsing based on approach of xtext parser helper for junit. Specialized for N4MF, full of assumptions, takes
	 * shortcuts.
	 *
	 * @param manifestContent
	 *            text to be parsed as N4MF content
	 * @return project description created as parsing result or null it cannot be obtained
	 *
	 * @see <a href=
	 *      "http://download.eclipse.org/modeling/tmf/xtext/javadoc/2.3/org/eclipse/xtext/junit4/util/ParseHelper.html">org.eclipse.xtext.junit4.util.ParseHelper<T></a>
	 */
	private static ParseResult<ProjectDescription> parse(CharSequence manifestContent) {
		final URI uriToUse = getRandomURI();
		final IResourceFactory resourceFactory = getService(IResourceFactory.class, uriToUse);

		ParseResult<ProjectDescription> res = new ParseResult<>();
		try (final InputStream textToParse = getAsStream(manifestContent);) {
			Resource resource = resource(resourceFactory, textToParse, uriToUse);

			if (!resource.getErrors().isEmpty()) {
				List<Diagnostic> errors = resource.getErrors();
				errors.forEach((Diagnostic d) -> res.addErrors(d.getMessage()));
			}

			res.data = resource.getContents().isEmpty()
					? null
					: (ProjectDescription) (resource.getContents().get(0));
		} catch (IOException e) {
			res.errors.add(e.getMessage());
			LOGGER.log(SEVERE, "Cannot parse >>>\n" + manifestContent + "\n<<<", e);
		}
		return res;
	}

	private static URI getRandomURI() {
		String name = "__synthetic_" + new Random().nextInt(Integer.MAX_VALUE) + "."
				+ N4MFConstants.N4MF_FILE_EXTENSION;
		return URI.createURI(name);
	}

	private static InputStream getAsStream(CharSequence text) {
		return new LazyStringInputStream(text == null ? "" : text.toString());
	}

	/** Creates artificial resource with provided data or {@code null} if it cannot be created. */
	private static Resource resource(IResourceFactory resourceFactory, InputStream in, URI uriToUse) {
		try {
			Resource resource = resourceFactory.createResource(uriToUse);
			// unlike for N4JS files use simple resource set
			XtextResourceSet resourceSet = new XtextResourceSet();
			// there should be no collisions
			resourceSet.getResources().add(resource);
			// no specific load options
			resource.load(in, null);
			return resource;
		} catch (IOException e) {
			return null;
		}
	}

	private static <S> S getService(Class<S> serviceType, URI uri) {
		return N4LanguageUtils
				.getServiceForContext(uri, serviceType)
				.orElseThrow(
						() -> new RuntimeException("Cannot obtain " + serviceType + " for syntactic URI :" + uri));
	}
}
