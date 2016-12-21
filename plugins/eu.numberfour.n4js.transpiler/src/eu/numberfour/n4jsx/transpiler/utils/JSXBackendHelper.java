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
package eu.numberfour.n4jsx.transpiler.utils;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.scoping.impl.DefaultGlobalScopeProvider;

import com.google.common.base.Optional;
import com.google.inject.Inject;

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.n4JS.ImportDeclaration;
import eu.numberfour.n4js.n4JS.ImportSpecifier;
import eu.numberfour.n4js.naming.ModuleNameComputer;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.projectModel.ProjectUtils;
import eu.numberfour.n4js.ts.types.TModule;
import eu.numberfour.n4jsx.N4JSXGlobals;

/**
 * Helper for working with JSX backends, e.g. Ract, Preact, etc. Internally it supports only React not, but API wise
 * should work for other backends once their support is added.
 */
public final class JSXBackendHelper {
	/** Local cache of JSX backends. */
	private final Map<String, IResourceDescription> jsxBackends = new ConcurrentHashMap<>();

	@Inject
	private DefaultGlobalScopeProvider globalScope;

	@Inject
	private IN4JSCore n4jsCore;
	@Inject
	private IQualifiedNameConverter qualifiedNameConverter;
	@Inject
	private ModuleNameComputer nameComputer;

	@Inject
	ProjectUtils projectUtils;

	private static String JSX_BACKEND_MODULE_NAME = "react";
	private static String JSX_BACKEND_FACADE_NAME = "React";
	private static String JSX_BACKEND_ELEMENT_FACTORY_NAME = "createElement";

	/** @return name of the JSX backend module, i.e. "react" */
	public String getBackendModuleName() {
		return JSX_BACKEND_MODULE_NAME;
	}

	/** @return name of the JSX backend facade, i.e "React" */
	public String getBackendFacadeName() {
		return JSX_BACKEND_FACADE_NAME;
	}

	/** @return name of the JSX element factory name, i.e "createElement" */
	public String getBackendElementFactoryMethodName() {
		return JSX_BACKEND_ELEMENT_FACTORY_NAME;
	}

	/** Checks if given module looks like JSX backend module, e.g. "react" */
	public static boolean isJsxBackendModule(TModule module) {
		if (module == null) {
			return false;
		}
		String qualifiedName = module.getQualifiedName();
		return qualifiedName.endsWith(JSX_BACKEND_MODULE_NAME);
	}

	/** Checks if given import declaration looks like JSX backend import, e.g. "(...) from "react" */
	public static boolean isJsxBackendImportDeclaration(ImportDeclaration declaration) {
		return isJsxBackendModule(declaration.getModule());
	}

	/** Checks if given import specifier looks like JSX backend import, e.g. "import * as React from "react" */
	public static boolean isJsxBackendImportSpecifier(ImportSpecifier specifier) {
		return isJsxBackendImportDeclaration((ImportDeclaration) specifier.eContainer());
	}

	/**
	 * Similar to {@link eu.numberfour.n4js.naming.QualifiedNameComputer#getCompleteModuleSpecifier(TModule)} but for
	 * artificial modules that were patched in by the transpiler for JSX backend.
	 */
	public String jsxBackendModuleSpecifier(TModule module, Resource resource) {

		String qualifiedName = module.getQualifiedName();
		URI uri = getOrFindJSXBackend(resource, qualifiedName);

		Optional<? extends IN4JSProject> optionalProject = n4jsCore.findProject(uri);
		if (!optionalProject.isPresent()) {
			throw new RuntimeException(
					"Cannot handle resource without containing project. Resource URI was: «n4jsSourceURI».");
		}
		return ProjectUtils.formatDescriptor(optionalProject.get(),
				module.getModuleSpecifier(), "-", ".", "/", false);
	}

	/**
	 * Similar to
	 * {@link eu.numberfour.n4js.naming.QualifiedNameComputer#getCompleteModuleSpecifierAsIdentifier(TModule)} but for
	 * artificial modules that were patched in by the transpiler for JSX backend.
	 */
	public String getJsxBackendCompleteModuleSpecifierAsIdentifier(TModule module) {
		String qualifiedName = module.getQualifiedName();
		URI uri = getOrFindJSXBackend(module.eResource(), qualifiedName);

		IN4JSProject resolveProject = projectUtils.resolveProject(uri);

		return projectUtils.getValidJavascriptIdentifierName(projectUtils.formatDescriptorAsIdentifier(
				resolveProject, module.getModuleSpecifier(), "_", "_", "_", false));
	}

	/**
	 * Searches through scope of the provided resource for the JSX backend. When found returns its module qualified
	 * name, other throws {@link NoSuchElementException}.
	 */
	public String jsxBackendModuleQualifiedName(Resource resource) {

		/* if this doesn't work try DefaultGlobalScopeProvider#getVisibleContainers(resource); */
		globalScope.getResourceDescriptions(resource).getAllResourceDescriptions().spliterator()
				.forEachRemaining(r -> {
					String uriVal = r.getURI().toString();
					if (looksLikeReactFile(uriVal)) {
						jsxBackends.put(
								qualifiedNameConverter.toString(nameComputer.getQualifiedModuleName(r)), r);
					}
				});
		// TODO pick in a smarter way
		java.util.Optional<String> ores = jsxBackends.keySet().stream().findAny();

		return ores.get();
	}

	/**
	 * Provides URI for JSX Backend. URI is cached in local map {@link JSXBackendHelper#jsxBackends}. If there is no URI
	 * for given QN, performs lookup via scope of the provided resource. and returned.
	 *
	 */
	private URI getOrFindJSXBackend(Resource resource, String qualifiedName) {
		IResourceDescription iResourceDescription = jsxBackends.get(qualifiedName);
		if (iResourceDescription == null) {
			iResourceDescription = jsxBackends.get(jsxBackendModuleQualifiedName(resource));
		}
		URI uri = iResourceDescription.getURI();
		return uri;
	}

	private boolean looksLikeReactFile(String qn) {
		String definition = JSX_BACKEND_MODULE_NAME + "." + N4JSGlobals.N4JSD_FILE_EXTENSION;
		String typed = JSX_BACKEND_MODULE_NAME + "." + N4JSGlobals.N4JS_FILE_EXTENSION;
		String typedX = JSX_BACKEND_MODULE_NAME + "." + N4JSXGlobals.N4JSX_FILE_EXTENSION;
		String raw = JSX_BACKEND_MODULE_NAME + "." + N4JSGlobals.JS_FILE_EXTENSION;
		String rawX = JSX_BACKEND_MODULE_NAME + "." + N4JSXGlobals.JSX_FILE_EXTENSION;
		return qn != null && (qn.endsWith(definition)
				|| qn.endsWith(typed)
				|| qn.endsWith(raw)
				|| qn.endsWith(typedX)
				|| qn.endsWith(rawX));
	}
}
