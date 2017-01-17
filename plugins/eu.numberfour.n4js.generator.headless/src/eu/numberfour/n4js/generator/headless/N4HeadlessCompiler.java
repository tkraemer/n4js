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
package eu.numberfour.n4js.generator.headless;

import static eu.numberfour.n4js.utils.UtilN4.concatUnique;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.containers.DelegatingIAllContainerAdapter;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsData;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.OnChangeEvictingCache;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import eu.numberfour.n4js.generator.CompositeGenerator;
import eu.numberfour.n4js.generator.common.CompilerDescriptor;
import eu.numberfour.n4js.generator.common.GeneratorException;
import eu.numberfour.n4js.internal.FileBasedWorkspace;
import eu.numberfour.n4js.internal.N4FilebasedWorkspaceResourceSetContainerState;
import eu.numberfour.n4js.internal.N4JSBrokenProjectException;
import eu.numberfour.n4js.internal.N4JSModel;
import eu.numberfour.n4js.internal.N4JSProject;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.projectModel.IN4JSSourceContainer;
import eu.numberfour.n4js.resource.N4JSCache;
import eu.numberfour.n4js.resource.N4JSResource;
import eu.numberfour.n4js.resource.OrderedResourceDescriptionsData;
import eu.numberfour.n4js.utils.ResourceType;

/**
 * Entry for headless compilation.
 *
 * This class has three ways of operation which all map down to a single algorithm implemented in
 * {@link #compileProjects(List, List, List, IssueAcceptor)}. All other compileXXXX methods call this algorithm
 * providing the correct content of the arguments.
 *
 * <ol>
 * <li>compile "single file" takes a (list of) source-file(s) to compile and just compiles these if possible
 * {@link #compileSingleFile(File)}, {@link #compileSingleFiles(List, IssueAcceptor)},
 * {@link #compileSingleFiles(List, List, IssueAcceptor)}
 * <li>compile "projects" takes a list of project-location and compiles exactly them.
 * {@link #compileProjects(List, IssueAcceptor)}, {@link #compileProjects(List, List, IssueAcceptor)}
 * <li>compile "all project" takes a list of folders and compiles each project found as direct content of one of the
 * folders. {@link #compileAllProjects(List, IssueAcceptor)}
 * </ol>
 *
 * The way how the compiler behaves can be configured through flags like {@link #keepOnCompiling},
 * {@link #processTestCode}, {@link #compileSourceCode}
 */
public class N4HeadlessCompiler {

	/** The Generator to compile with */
	private final CompositeGenerator compositeGenerator;

	/** Abstraction to the filesystem, used by the Generator */
	private final JavaIoFileSystemAccess fsa;

	/** N4JS-Implementation of a workspace without OSGI */
	@Inject
	private FileBasedWorkspace fbWorkspace;

	@Inject
	private N4JSModel n4jsModel;

	@Inject
	private IN4JSCore n4jsCore;

	@Inject
	private N4FilebasedWorkspaceResourceSetContainerState rsbAcs;

	/** provider to create correct ResourceSet instances */
	@Inject
	private Provider<XtextResourceSet> xtextResourceSetProvider;

	@Inject
	private ClassLoader classLoader;

	/**
	 * original outputConfiguration, possibly requires a reconfiguration based on the project-path.
	 * {@link JavaIoFileSystemAccess#getFile} relies on the assumption, that the basepath (for new File) is the current
	 * project. If that assumption doesn't hold, we need to be creative with the output-configuration.
	 */
	private final Map<String, OutputConfiguration> outputs;

	/** if set to true should try to compile even if errors are in some projects */
	private boolean keepOnCompiling = false;

	/** if set to false all source-containers of type 'test' are not passed to the generator */
	private boolean processTestCode = true;

	/** if set to false all source-containers of type 'source' are not passed to the generator */
	private boolean compileSourceCode = true;

	/** if set to true prints out processed files to standard out */
	private boolean verbose = false;

	/** if set to true suppresses all output to standard out */
	private boolean suppressOutput = false;

	/** if set to true prints to standard out inform about what is currently processed. */
	private boolean createDebugOutput = false;

	/** if set additional log will be written to this filename */
	private String logFile = null;

	/**
	 * Reference to the injector creating this instance. Will be set when an instance of this class is created by
	 * calling {@link #injectAndSetup(Properties)}
	 */
	private Injector injector;

	/**
	 * Compiles a single n4js/js file
	 *
	 * @param modelFile
	 *            source to compile
	 * @param properties
	 *            optional Project-Settings loaded into Properties.
	 * @throws N4JSCompileException
	 *             in compile errors
	 */
	public static void doMain(File modelFile, Properties properties) throws N4JSCompileException {
		doMain(modelFile, properties, new DismissingIssueAcceptor());
	}

	/**
	 * Compiles a single n4js/js file
	 *
	 * @param modelFile
	 *            source to compile
	 * @param properties
	 *            optional Project-Settings loaded into Properties.
	 * @param issueAcceptor
	 *            the issue acceptor that can be used to collect or evaluate the issues occurring during compilation
	 * @throws N4JSCompileException
	 *             in compile errors
	 */
	public static void doMain(File modelFile, Properties properties, IssueAcceptor issueAcceptor)
			throws N4JSCompileException {
		N4HeadlessCompiler hlc = injectAndSetup(properties);
		hlc.compileSingleFile(modelFile, issueAcceptor);
	}

	/**
	 * Construct a {@link N4HeadlessCompiler} instance based on preferences stored in the given properties.
	 *
	 * @param properties
	 *            preferences.
	 */
	public static N4HeadlessCompiler injectAndSetup(Properties properties) {
		Injector localinjector = new N4JSHeadlessStandaloneSetup(properties).createInjectorAndDoEMFRegistration();
		N4HeadlessCompiler instance = localinjector.getInstance(N4HeadlessCompiler.class);
		instance.injector = localinjector;
		return instance;
	}

	/**
	 * Private constructor to prevent accidental instantiation. Use
	 * {@link N4HeadlessCompiler#injectAndSetup(Properties)} to create instances.
	 */
	@Inject
	private N4HeadlessCompiler(CompositeGenerator compositeGenerator, JavaIoFileSystemAccess fsa) {
		this.compositeGenerator = compositeGenerator;
		this.fsa = fsa;

		outputs = new HashMap<>();
		for (CompilerDescriptor desc : compositeGenerator.getCompilerDescriptors()) {
			outputs.put(desc.getIdentifier(), desc.getOutputConfiguration());
		}
		fsa.setOutputConfigurations(outputs);
	}

	/**
	 * Compile one single source file.
	 *
	 * @param singleSourceFile
	 *            if non-empty limit compilation to the sources files listed here
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileSingleFile(File singleSourceFile) throws N4JSCompileException {
		compileSingleFile(singleSourceFile, new DismissingIssueAcceptor());
	}

	/**
	 * Compile one single source file.
	 *
	 * @param singleSourceFile
	 *            if non-empty limit compilation to the sources files listed here
	 * @param issueAcceptor
	 *            the issue acceptor that can be used to collect or evaluate the issues occurring during compilation
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileSingleFile(File singleSourceFile, IssueAcceptor issueAcceptor) throws N4JSCompileException {
		compileSingleFiles(Arrays.asList(singleSourceFile), issueAcceptor);
	}

	/**
	 * Compile multiple single source files.
	 *
	 * @param singleSourceFiles
	 *            if non-empty limit compilation to the sources files listed here
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileSingleFiles(List<File> singleSourceFiles) throws N4JSCompileException {
		compileSingleFiles(singleSourceFiles, new DismissingIssueAcceptor());
	}

	/**
	 * Compile multiple single source files.
	 *
	 * @param singleSourceFiles
	 *            if non-empty limit compilation to the sources files listed here
	 * @param issueAcceptor
	 *            the issue acceptor that can be used to collect or evaluate the issues occurring during compilation
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileSingleFiles(List<File> singleSourceFiles, IssueAcceptor issueAcceptor)
			throws N4JSCompileException {
		compileSingleFiles(Collections.emptyList(), singleSourceFiles, issueAcceptor);
	}

	/**
	 * Compile multiple single source files.
	 *
	 * @param searchPaths
	 *            where to search for dependent projects.
	 * @param singleSourceFiles
	 *            if non-empty limit compilation to the sources files listed here
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileSingleFiles(List<File> searchPaths, List<File> singleSourceFiles)
			throws N4JSCompileException {
		compileSingleFiles(searchPaths, singleSourceFiles, new DismissingIssueAcceptor());
	}

	/**
	 * Compile multiple single source files.
	 *
	 * @param searchPaths
	 *            where to search for dependent projects.
	 * @param singleSourceFiles
	 *            if non-empty limit compilation to the sources files listed here
	 * @param issueAcceptor
	 *            the issue acceptor that can be used to collect or evaluate the issues occurring during compilation
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileSingleFiles(List<File> searchPaths, List<File> singleSourceFiles, IssueAcceptor issueAcceptor)
			throws N4JSCompileException {
		compileProjects(searchPaths, Collections.emptyList(), singleSourceFiles, issueAcceptor);
	}

	/**
	 * Compile a list of projects. Main algorithm.
	 *
	 * @param searchPaths
	 *            where to search for the projects to compile.
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileAllProjects(List<File> searchPaths) throws N4JSCompileException {
		compileAllProjects(searchPaths, new DismissingIssueAcceptor());
	}

	/**
	 * Compile a list of projects. Main algorithm.
	 *
	 * @param searchPaths
	 *            where to search for the projects to compile.
	 * @param issueAcceptor
	 *            the issue acceptor that can be used to collect or evaluate the issues occurring during compilation
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileAllProjects(List<File> searchPaths, IssueAcceptor issueAcceptor) throws N4JSCompileException {
		// make absolute, since downstream URI conversion doesn't work if relative directory only.
		List<File> absProjectPaths = HeadlessHelper.toAbsoluteFileList(searchPaths);

		// Collect all projects in first Level.
		List<File> projectPaths = HeadlessHelper.collectAllProjectPaths(absProjectPaths);

		compileProjects(searchPaths, projectPaths, Collections.emptyList(), issueAcceptor);
	}

	/**
	 * Compile a list of projects. Main algorithm.
	 *
	 * @param searchPaths
	 *            where to search for dependent projects.
	 * @param projectPaths
	 *            the projects to compile. the base folder of each project must be provided.
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileProjects(List<File> searchPaths, List<File> projectPaths)
			throws N4JSCompileException {
		compileProjects(searchPaths, projectPaths, new DismissingIssueAcceptor());
	}

	/**
	 * Compile a list of projects. Main algorithm.
	 *
	 * @param searchPaths
	 *            where to search for dependent projects.
	 * @param projectPaths
	 *            the projects to compile. the base folder of each project must be provided.
	 * @param issueAcceptor
	 *            the issue acceptor that can be used to collect or evaluate the issues occurring during compilation
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileProjects(List<File> searchPaths, List<File> projectPaths, IssueAcceptor issueAcceptor)
			throws N4JSCompileException {
		compileProjects(searchPaths, projectPaths, Collections.emptyList(), issueAcceptor);
	}

	/**
	 * Compile a list of projects. Main algorithm.
	 *
	 * @param searchPaths
	 *            where to search for dependent projects.
	 * @param projectPaths
	 *            the projects to compile. the base folder of each project must be provided.
	 * @param singleSourceFiles
	 *            if non-empty limit compilation to the sources files listed here
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileProjects(List<File> searchPaths, List<File> projectPaths, List<File> singleSourceFiles)
			throws N4JSCompileException {
		compileProjects(searchPaths, projectPaths, singleSourceFiles, new DismissingIssueAcceptor());
	}

	private static class BuildSet {
		public final List<N4JSProject> requestedProjects;
		public final List<N4JSProject> discoveredProjects;
		public final Predicate<URI> projectFilter;

		public BuildSet(List<N4JSProject> requestedProjects, List<N4JSProject> discoveredProjects,
				Predicate<URI> projectFilter) {
			super();
			this.requestedProjects = requestedProjects;
			this.discoveredProjects = discoveredProjects;
			this.projectFilter = projectFilter;
		}

	}

	/**
	 * Compile a list of projects. Main algorithm.
	 *
	 * @param searchPaths
	 *            where to search for dependent projects.
	 * @param projectPaths
	 *            the projects to compile. the base folder of each project must be provided.
	 * @param singleSourceFiles
	 *            if non-empty limit compilation to the sources files listed here
	 * @param issueAcceptor
	 *            the issue acceptor that can be used to collect or evaluate the issues occurring during compilation
	 * @throws N4JSCompileException
	 *             if one or multiple errors occur during compilation
	 */
	public void compileProjects(List<File> searchPaths, List<File> projectPaths, List<File> singleSourceFiles,
			IssueAcceptor issueAcceptor)
			throws N4JSCompileException {

		printCompileArguments(searchPaths, projectPaths, singleSourceFiles);

		BuildSet buildSet = collectAndRegisterProjects(searchPaths, projectPaths, singleSourceFiles);

		List<N4JSProject> allProjects = concatUnique(buildSet.discoveredProjects, buildSet.requestedProjects);
		List<N4JSProject> requestedProjects = buildSet.requestedProjects;
		Predicate<URI> singleSourceFilter = buildSet.projectFilter;

		configureResourceSetContainerState(allProjects);

		final List<MarkedProject> buildOrder = computeBuildOrder(allProjects, requestedProjects);
		printBuildOrder(buildOrder);

		processProjects(buildOrder, singleSourceFilter, issueAcceptor);
	}

	/*
	 * ===============================================================================================================
	 *
	 * COLLECTING PROJECTS FROM USER-PROVIDED ARGUMENTS
	 *
	 * ===============================================================================================================
	 */

	/**
	 * Collects the projects to compile and finds their dependencies in the given search paths and registers them with
	 * the file-based workspace.
	 *
	 * @param searchPaths
	 *            where to search for dependent projects.
	 * @param projectPaths
	 *            the projects to compile. the base folder of each project must be provided.
	 * @param singleSourceFiles
	 *            if non-empty limit compilation to the sources files listed here
	 * @return an instance of {@link BuildSet} containing the collected projects and a filter to apply if single source
	 *         files were requested to be compiled
	 * @throws N4JSCompileException
	 *             if an error occurs while registering the projects
	 */
	private BuildSet collectAndRegisterProjects(List<File> searchPaths, List<File> projectPaths,
			List<File> singleSourceFiles) throws N4JSCompileException {

		// Make absolute, since downstream URI conversion doesn't work if relative dir only.
		List<File> absSearchPaths = HeadlessHelper.toAbsoluteFileList(searchPaths);
		List<File> absProjectPaths = HeadlessHelper.toAbsoluteFileList(projectPaths);
		List<File> absSingleSourceFiles = HeadlessHelper.toAbsoluteFileList(singleSourceFiles);

		// Discover projects in search paths.
		List<File> discoveredProjectLocations = HeadlessHelper.collectAllProjectPaths(absSearchPaths);

		// Discover projects for single source files.
		List<File> singleSourceProjectLocations = findProjectsForSingleFiles(absSingleSourceFiles);

		// Register single-source projects as to be compiled as well.
		List<File> absRequestedProjectLocations = concatUnique(absProjectPaths,
				singleSourceProjectLocations);

		// Convert absolute locations to file URIs
		List<URI> requestedProjectURIs = createFileURIs(absRequestedProjectLocations);
		List<URI> discoveredProjectURIs = createFileURIs(discoveredProjectLocations);

		// Obtain the projects and store them.
		List<N4JSProject> requestedProjects = getN4JSProjects(requestedProjectURIs);
		List<N4JSProject> discoveredProjects = getN4JSProjects(discoveredProjectURIs);

		// Register all projects with the file based workspace.
		for (URI projectURI : Iterables.concat(requestedProjectURIs, discoveredProjectURIs)) {
			try {
				fbWorkspace.registerProject(projectURI);
			} catch (N4JSBrokenProjectException e) {
				throw new N4JSCompileException("Unable to register project '" + projectURI + "'", e);
			}
		}

		// Create a filter that applies only to the given single source files if any were requested to be compiled.
		Predicate<URI> compileFilter;
		if (absSingleSourceFiles.isEmpty()) {
			compileFilter = u -> true;
		} else {
			Set<URI> singleSourceURIs = new HashSet<>(createFileURIs(absSingleSourceFiles));
			compileFilter = u -> singleSourceURIs.contains(u);
		}

		return new BuildSet(requestedProjects, discoveredProjects, compileFilter);
	}

	/**
	 * Collects the projects containing the given single source files.
	 *
	 * @param sourceFiles
	 *            the list of single source files
	 * @return list of N4JS project locations
	 * @throws N4JSCompileException
	 *             if no project cannot be found for one of the given files
	 */
	private List<File> findProjectsForSingleFiles(List<File> sourceFiles)
			throws N4JSCompileException {

		Set<URI> result = Sets.newLinkedHashSet();

		for (File sourceFile : sourceFiles) {
			URI sourceFileURI = URI.createFileURI(sourceFile.toString());
			URI projectURI = fbWorkspace.findProjectWith(sourceFileURI);
			if (projectURI == null) {
				throw new N4JSCompileException("No project for file '" + sourceFile.toString() + "' found.");
			}
			result.add(projectURI);
		}

		// convert back to Files:
		return result.stream().map(u -> new File(u.toFileString())).collect(Collectors.toList());
	}

	/**
	 * Convert the given list of files to a list of URIs. Each file is converted to a URI by means of
	 * {@link URI#createFileURI(String)}.
	 *
	 * @param files
	 *            the files to convert
	 * @return the list of URIs
	 */
	private List<URI> createFileURIs(List<File> files) {
		return files.stream().map(f -> URI.createFileURI(f.toString())).collect(Collectors.toList());
	}

	/**
	 * Returns a list of {@link N4JSProject} instances representing the projects at the given locations.
	 *
	 * @param projectURIs
	 *            the URIs to process
	 * @return a list of projects at the given URIs
	 */
	private List<N4JSProject> getN4JSProjects(List<URI> projectURIs) {
		return projectURIs.stream().map(u -> n4jsModel.getN4JSProject(u)).collect(Collectors.toList());
	}

	private void configureResourceSetContainerState(final List<N4JSProject> allProjects) {
		// a container is a project.
		List<String> containers = new LinkedList<>();
		BiMap<String, N4JSProject> container2project = HashBiMap.create();

		// the URIs of all resources directly contained in a project/container.
		Multimap<String, URI> container2Uris = HashMultimap.create();

		for (N4JSProject project : allProjects) {
			String container = FileBasedWorkspace.N4FBPRJ + project.getLocation();
			container2project.put(container, project);
			containers.add(container);

			for (IN4JSSourceContainer sourceContainer : project.getSourceContainers()) {
				Iterables.addAll(container2Uris.get(container), sourceContainer);
			}
		}

		// Define the Mapping of Resources (URIs to Container === Projects),
		rsbAcs.configure(containers, container2Uris);
	}

	/**
	 * Sort in build-order. Wraps each element of {@code toSort} with {@link MarkedProject} and applies all
	 * {@code buildMarker} for which the element is a (transitively) declared dependency
	 *
	 * @param allProjectsToCompile
	 *            unsorted projects, these include the dependencies as well as the projects to compile
	 * @param requestedProjects
	 *            only the projects which were requested to be compiled
	 * @return sorted projects: earlier projects don't depend on later
	 */
	private static List<MarkedProject> computeBuildOrder(List<? extends IN4JSProject> allProjectsToCompile,
			List<? extends IN4JSProject> requestedProjects) {

		// This algorithm only operates on the following map of marked projects.
		Map<IN4JSProject, MarkedProject> markedProjects = new HashMap<>();
		allProjectsToCompile.stream().forEach(project -> markedProjects.put(project, new MarkedProject(project)));

		// Maps a project to the projects that depend on it.
		HashMultimap<IN4JSProject, IN4JSProject> pendencies = HashMultimap.create();

		// Maps a project to the projects it depends on.
		HashMultimap<IN4JSProject, IN4JSProject> dependencies = HashMultimap.create();

		// List of projects without dependencies (starting points).
		LinkedList<IN4JSProject> independentProjects = new LinkedList<>();

		// Initialize preconditions, dependencies, and independent projects.
		computeDependencyGraph(markedProjects.keySet(), pendencies, dependencies, independentProjects);

		// Mark the projects to build, using a set to remove duplicates.
		for (IN4JSProject project : new HashSet<>(requestedProjects))
			markDependencies(project, project, markedProjects, dependencies);

		return computeBuildOrderDepthFirst(markedProjects, pendencies, dependencies, independentProjects);
		// return computeBuildOrderBreadthFirst(markedProjects, pendencies, dependencies, independentProjects);
	}

	/**
	 * Recursively marks the dependency subgraph by applying the given marker to each transitive dependency of the given
	 * markee. Assumes that there are no cyclic dependencies in the given dependency map.
	 *
	 * @param marker
	 *            the marker to apply
	 * @param markee
	 *            the project to be marked
	 * @param markables
	 *            lookup map for the markable projects
	 * @param dependencies
	 *            maps a project to the projects it depends on
	 */
	private static void markDependencies(IN4JSProject marker, IN4JSProject markee,
			Map<IN4JSProject, MarkedProject> markables,
			Multimap<IN4JSProject, IN4JSProject> dependencies) {

		// Set the mark
		markables.get(markee).markWith(marker);

		// Recursively apply to all dependencies of the given markee
		for (IN4JSProject dependency : dependencies.get(markee))
			markDependencies(marker, dependency, markables, dependencies);
	}

	/**
	 * Computes a dependency graph for the given projects and stores the results in the given data structures.
	 *
	 * @param projects
	 *            the projects to compute the dependency graph for
	 * @param pendencies
	 *            maps projects to the projects that depend on them
	 * @param dependencies
	 *            maps projects to the projects they depend on
	 * @param independent
	 *            projects without dependencies
	 */
	private static void computeDependencyGraph(Set<IN4JSProject> projects,
			Multimap<IN4JSProject, IN4JSProject> pendencies,
			Multimap<IN4JSProject, IN4JSProject> dependencies, List<IN4JSProject> independent) {

		// already processed projects
		Set<IN4JSProject> visited = new HashSet<>(projects.size());

		// Populate dependencies
		for (IN4JSProject project : projects)
			computeDependencyGraph(project, visited, pendencies, dependencies, independent);
	}

	/**
	 * Recursive part of {@link #computeDependencyGraph(Set, Multimap, Multimap, List)}. The given project is processed
	 * only if it has not been processed already. If that is the case, it will either be added to the given list of
	 * independent projects, or a dependency from the given project to each of the projects it depends on is added.
	 * Finally, the algorithm calls itself for each dependency.
	 *
	 * @param project
	 *            the project being processed
	 * @param visitedProjects
	 *            set of projects already processed
	 * @param pendencies
	 *            maps projects to the projects that depend on them
	 * @param dependencies
	 *            maps projects to the projects they depend on
	 * @param independent
	 *            projects without dependencies
	 */
	private static void computeDependencyGraph(IN4JSProject project, Set<IN4JSProject> visitedProjects,
			Multimap<IN4JSProject, IN4JSProject> pendencies,
			Multimap<IN4JSProject, IN4JSProject> dependencies, List<IN4JSProject> independent) {

		if (!visitedProjects.add(project))
			return;

		ImmutableList<? extends IN4JSProject> pendingProjects = project.getDependenciesAndImplementedApis();
		if (pendingProjects.isEmpty()) {
			independent.add(project);
		} else {
			for (IN4JSProject pendingProject : pendingProjects) {
				pendencies.put(pendingProject, project);
				dependencies.put(project, pendingProject);

				computeDependencyGraph(pendingProject, visitedProjects, pendencies, dependencies, independent);
			}
		}
	}

	/**
	 * Compute the build order by processing the dependency graph in a breadth first manner.
	 *
	 * @param markedProjects
	 *            the projects to be compiled
	 * @param pendencies
	 *            maps projects to the projects that depend on them
	 * @param dependencies
	 *            maps projects to the projects they depend on
	 * @param independentProjects
	 *            projects without dependencies
	 * @return a build order in which each project only depends on projects to its left
	 */
	@SuppressWarnings("unused")
	private static List<MarkedProject> computeBuildOrderBreadthFirst(Map<IN4JSProject, MarkedProject> markedProjects,
			HashMultimap<IN4JSProject, IN4JSProject> pendencies, HashMultimap<IN4JSProject, IN4JSProject> dependencies,
			Queue<IN4JSProject> independentProjects) {

		List<MarkedProject> result = new LinkedList<>();

		while (!independentProjects.isEmpty()) {
			IN4JSProject currentProject = independentProjects.poll();
			result.add(markedProjects.get(currentProject));

			// The projects that depend on the current project.
			Set<IN4JSProject> dependentProjects = pendencies.removeAll(currentProject);
			for (IN4JSProject dependentProject : dependentProjects) {
				// The preconditions of the current dependent project; this contains the current project itself.
				Set<IN4JSProject> currentDependencies = dependencies.get(dependentProject);
				currentDependencies.remove(currentProject);

				// All dependencies of the current dependent project are now processed, so it is ready to be processed.
				if (currentDependencies.isEmpty())
					independentProjects.offer(dependentProject);
			}
		}

		return result;
	}

	/**
	 * Compute the build order by processing the dependency graph in a depth first manner.
	 *
	 * @param markedProjects
	 *            the projects to be compiled
	 * @param pendencies
	 *            maps projects to the projects that depend on them
	 * @param dependencies
	 *            maps projects to the projects they depend on
	 * @param rootProjects
	 *            projects without dependencies
	 * @return a build order in which each project only depends on projects to its left
	 */
	private static List<MarkedProject> computeBuildOrderDepthFirst(Map<IN4JSProject, MarkedProject> markedProjects,
			Multimap<IN4JSProject, IN4JSProject> pendencies, Multimap<IN4JSProject, IN4JSProject> dependencies,
			List<IN4JSProject> rootProjects) {
		List<MarkedProject> result = new LinkedList<>();

		for (IN4JSProject rootProject : rootProjects)
			computeBuildOrderDepthFirst(rootProject, markedProjects, pendencies, dependencies, result);

		return result;
	}

	/**
	 * Recursive part of {@link #computeBuildOrderDepthFirst(Map, Multimap, Multimap, List)}. If all dependencies of the
	 * given project have already been processed, it is added to the build order. Then, all projects that depend on the
	 * given project are processed recursively.
	 *
	 * @param project
	 *            the project to process
	 * @param markedProjects
	 *            the marked projects
	 * @param pendencies
	 *            maps projects to the projects that depend on them
	 * @param dependencies
	 *            maps projects to the projects they depend on
	 * @param result
	 *            the build order being computed
	 */
	private static void computeBuildOrderDepthFirst(IN4JSProject project,
			Map<IN4JSProject, MarkedProject> markedProjects, Multimap<IN4JSProject, IN4JSProject> pendencies,
			Multimap<IN4JSProject, IN4JSProject> dependencies, List<MarkedProject> result) {

		// once all dependencies of the current project have been processed, we can add it to the build and
		// process its children.
		if (dependencies.get(project).isEmpty()) {
			// The current project is ready to be processed.
			result.add(markedProjects.get(project));

			// Remove this project from the dependencies of all pending projects.
			for (IN4JSProject dependentProject : pendencies.get(project)) {
				dependencies.get(dependentProject).remove(project);

				// Now process the pending project itself.
				computeBuildOrderDepthFirst(dependentProject, markedProjects, pendencies, dependencies, result);
			}
		}
	}

	/*
	 * ===============================================================================================================
	 *
	 * PROJECT PROCESSING
	 *
	 * ===============================================================================================================
	 */

	/**
	 * Process the given projects in the given order. Processing entails the following steps.
	 *
	 * <ul>
	 * <li>Create a resource set to host all resources during compilation.</li>
	 * <li>For each project to compile:
	 * <ul>
	 * <li>Load the project.</li>
	 * <li>Validate and compile the project.</li>
	 * <li>Unload the ASTs and resource caches of every resource in the project.</li>
	 * <li>Unload every project whose pending projects have all been processed already.</li>
	 * </ul>
	 * </li>
	 * </ul>
	 *
	 * If an error occurs during compilation, it is either throw immediately or it is thrown after the project has been
	 * processed in full, depending on the value of {@link #isKeepOnCompiling()}.
	 *
	 * @param projects
	 *            the projects to compile. This list contains both the projects passed in by the user and the discovered
	 *            dependencies of those projects
	 * @param filter
	 *            a filter to decide whether or not a given resource should be compiled
	 * @param issueAcceptor
	 *            the issue acceptor that can be used to collect or evaluate the issues occurring during compilation
	 * @throws N4JSCompileException
	 *             if an error occurs during compilation
	 */
	private void processProjects(List<MarkedProject> projects, final Predicate<URI> filter,
			IssueAcceptor issueAcceptor)
			throws N4JSCompileException {

		ResourceSet resourceSet = createResourceSet();

		N4JSCompoundCompileException collectedErrors = null;

		N4ProgressStateRecorder rec = new N4ProgressStateRecorder();

		// List for Tracking of loaded Projects.
		List<MarkedProject> loadedProjects = new LinkedList<>();

		for (MarkedProject markedProject : projects) {
			// Only load a project if it was requested to be compile or if other requested projects depend on it.
			if (markedProject.hasMarkers()) {
				rec.markProcessing(markedProject.project);
				configureFSA(markedProject.project);

				try {
					// load
					loadProject(markedProject, resourceSet, rec, issueAcceptor);
					loadedProjects.add(markedProject);

					// compile only if it has itself as marker and non-external
					if (markedProject.hasMarker(markedProject.project) && !markedProject.project.isExternal())
						compileProject(markedProject, resourceSet, filter, rec);

					// once compiled, we can unload the AST
					unloadASTs(markedProject.resources);

					// remove marker from loaded projects and unload if no longer in use
					ListIterator<MarkedProject> loadedIter = loadedProjects.listIterator();
					while (loadedIter.hasNext()) {
						MarkedProject loaded = loadedIter.next();
						loaded.remove(markedProject.project);

						if (!loaded.hasMarkers()) {
							// TODO IDE-2479: Restore this
							// if (createDebugOutput)
							println("# unloading " + loaded.project);

							loaded.unload(resourceSet, rec);
							loadedIter.remove();
						}
					}
				} catch (N4JSCompileErrorException e) {
					rec.compileException(e);
					if (keepOnCompiling) {
						if (collectedErrors == null)
							collectedErrors = new N4JSCompoundCompileException("Errors during compiling.", e);
						else {
							collectedErrors.add(e);
						}
					} else {
						// fail fast
						throw e;
					}
				} finally {
					resetFSA();
				}
				rec.markEndProcessing(markedProject.project);
			}

			System.out.println("Still loaded: " + loadedProjects);
		}

		rec.dumpToLogfile(logFile);

		if (collectedErrors != null) {
			throw collectedErrors;
		}
	}

	/**
	 * Creates the common resource set to use during compilation. Installs a light weight index.
	 *
	 * @return the resource set
	 */
	private ResourceSet createResourceSet() {
		XtextResourceSet resourceSet = xtextResourceSetProvider.get();
		resourceSet.setClasspathURIContext(classLoader);

		// Install containerState as adapter.
		resourceSet.eAdapters().add(new DelegatingIAllContainerAdapter(rsbAcs));

		// Install a lightweight index.
		OrderedResourceDescriptionsData index = new OrderedResourceDescriptionsData(Collections.emptyList());
		ResourceDescriptionsData.ResourceSetAdapter.installResourceDescriptionsData(resourceSet, index);

		return resourceSet;
	}

	/**
	 * Setting the compile output-configurations to contain path-locations relative to the user.dir: Wrapper function
	 * written against Xtext 2.7.1.
	 *
	 * In Eclipse-compile mode there are "projects" and the FSA is configured relative to these projects. In this
	 * filebasedWorkspace here there is no "project"-concept for the generator. So the paths of the FSA need to be
	 * reconfigured to contain the navigation to the IN4JSProject-root.
	 *
	 * @param in4jsProject
	 *            project to be compiled
	 */
	private void configureFSA(IN4JSProject in4jsProject) {
		File userdir = new File(".");
		File prjdir = new File(in4jsProject.getLocation().toFileString());
		// compute relative path, if project is not in a sub directory of user directory an absolute
		// path is computed.
		java.net.URI relativize = userdir.toURI().relativize(prjdir.toURI());
		final String relativePrjReference = relativize.getPath();
		if (relativePrjReference.length() == 0) {
			// same directory, NTD
			return;
		}
		// set different output configuration.
		fsa.setOutputConfigurations(transformedOutputConfiguration(relativePrjReference));
	}

	/**
	 * Wraps the output-configurations {@link #outputs} with a delegator transparently injecting the relative path to
	 * the project-root.
	 *
	 * @param pathToProjectRoot
	 *            relative path to the project-root
	 * @return wrapped configurations.
	 */
	private Map<String, OutputConfiguration> transformedOutputConfiguration(String pathToProjectRoot) {
		Map<String, OutputConfiguration> ret = new HashMap<>();

		for (Entry<String, OutputConfiguration> pair : outputs.entrySet()) {
			final OutputConfiguration input = pair.getValue();
			OutputConfiguration transOC = new WrappedOutputConfiguration(input, pathToProjectRoot);
			ret.put(pair.getKey(), transOC);
		}
		return ret;
	}

	/**
	 * Reset output configuration to initial settings stored in {@link #outputs}.
	 *
	 * @see #configureFSA(IN4JSProject) how to set to specific project.
	 */
	private void resetFSA() {
		fsa.setOutputConfigurations(outputs);
	}

	/**
	 * Compiles all files in project.
	 *
	 * FileSystemAccess has to be correctly configured, see {@link #configureFSA(IN4JSProject)} and {@link #resetFSA()}
	 *
	 * @param markedProject
	 *            project to compile.
	 * @param resSet
	 *            outer resource set
	 * @param rec
	 *            failure-recording
	 * @param issueAcceptor
	 *            the issue acceptor that can be used to collect or evaluate the issues occurring during compilation
	 * @throws N4JSCompileErrorException
	 *             in case of compile-problems.
	 */
	private void loadProject(MarkedProject markedProject, ResourceSet resSet, N4ProgressStateRecorder rec,
			IssueAcceptor issueAcceptor)
			throws N4JSCompileErrorException {

		rec.markStartLoading(markedProject);

		// TODO: IDE-2479 Restore this
		// if (createDebugOutput)
		println("# loading " + markedProject.project);

		collectResources(markedProject, resSet, rec);
		loadResources(markedProject, rec);
		installIndex(resSet, markedProject);

		List<Issue> allErrorsAndWarnings = validateProject(markedProject, issueAcceptor, rec);
		dumpAllIssues(allErrorsAndWarnings);

		// Projects should not compile if there are severe errors:
		if (!keepOnCompiling) {
			failOnErrors(allErrorsAndWarnings, markedProject.project.getProjectId());
		}
	}

	private void collectResources(MarkedProject markedProject, ResourceSet resourceSet, N4ProgressStateRecorder rec) {
		markedProject.clearResources();

		// TODO try to reuse code from IN4JSCore.createResourceSet
		for (IN4JSSourceContainer container : markedProject.project.getSourceContainers()) {
			// Conditionally filter test-resources if not desired
			if (shouldReadResources(container)) {
				container.forEach(uri -> {
					Resource resource = resourceSet.createResource(uri);
					if (resource != null) {
						if (createDebugOutput) {
							println("Collecting resources from source container: " + resource.getURI());
						}
						markedProject.resources.add(resource);
						if (container.isExternal())
							markedProject.externalResources.add(resource); // register externals.
						if (container.isTest())
							markedProject.testResources.add(resource); // register tests.
					} else {
						rec.markFailedCreateResource(uri);
						warn("Skipped file: could not create resource for URI=" + uri);
					}
				});
			}
		}
	}

	private void loadResources(MarkedProject markedProject, N4ProgressStateRecorder rec)
			throws N4JSCompileErrorException {
		for (Resource res : markedProject.resources) {
			try {
				res.load(Collections.EMPTY_MAP);
			} catch (IOException e) {
				rec.markLoadResourceFailed(res);
				String message = "Cannot load resource=" + res.getURI();
				if (!keepOnCompiling) {
					throw new N4JSCompileErrorException(message,
							markedProject.project.getProjectId(), e);
				}
				warn(message);
			}
		}
	}

	private List<Issue> validateProject(MarkedProject markedProject, IssueAcceptor issueAcceptor,
			N4ProgressStateRecorder rec) {
		List<Issue> allErrorsAndWarnings = new LinkedList<>();

		// validation TODO see IDE-1426 redesign validation calls with generators
		for (Resource resource : markedProject.resources) {
			// TODO enable if fabelhaft code doesn't contain *.xt files any more.
			/*-
			if (isXpectFile(resource.getURI())) {
				IssueImpl i = new IssueImpl();
				i.setMessage("Xpect files are not allowed in headless compilation. (They may contain unrecognizable errros.)");
				i.setUriToProblem(resource.getURI());
				i.setLength(0);
				i.setLineNumber(0);
				i.setOffset(0);
				i.setSeverity(Severity.ERROR);
				i.setType(CheckType.NORMAL);
				i.setSyntaxError(false);
				// create error for invalid xpect-files
				allErrorsAndWarnings.add(i);
				rec.markResourceIssues(resource, Arrays.asList(i));
			} */
			if (resource instanceof XtextResource && // is Xtext resource
					(!n4jsCore.isNoValidate(resource.getURI())) && // is validating
					(!markedProject.externalResources.contains(resource)) // not in external folder
			) {
				XtextResource xtextResource = (XtextResource) resource;
				IResourceValidator validator = xtextResource.getResourceServiceProvider().getResourceValidator();
				List<Issue> issues = validator.validate(xtextResource, CheckMode.ALL, CancelIndicator.NullImpl);

				if (!issues.isEmpty()) {
					rec.markResourceIssues(resource, issues);
					for (Issue issue : issues) {
						allErrorsAndWarnings.add(issue);
						issueAcceptor.accept(issue);
					}
				}
			}
		}

		return allErrorsAndWarnings;
	}

	/**
	 * TODO try to reuse code from IN4JSCore.createResourceSet
	 */
	private void installIndex(ResourceSet resourceSet, MarkedProject markedProject) {
		ResourceDescriptionsData index = ResourceDescriptionsData.ResourceSetAdapter
				.findResourceDescriptionsData(resourceSet);

		for (Resource resource : markedProject.resources)
			index(resource, index);

		// Create index for N4 manifest as well. Index artifact names among project types and library dependencies.
		Optional<URI> manifestUri = markedProject.project.getManifestLocation();
		if (manifestUri.isPresent()) {
			final Resource manifestResource = resourceSet.getResource(manifestUri.get(), true);
			if (null != manifestResource)
				index(manifestResource, index);
		}
	}

	/**
	 * Installing the ResourceDescription of a resource into the index. Raw JS-files will not be indexed.
	 */
	private void index(Resource resource, ResourceDescriptionsData index) {
		final URI uri = resource.getURI();

		if (isJsFile(uri)) {
			IN4JSSourceContainer sourceContainer = n4jsCore.findN4JSSourceContainer(uri).orNull();
			if (null == sourceContainer)
				return; // We do not want to index resources that are not in source containers.
		}

		IResourceServiceProvider serviceProvider = IResourceServiceProvider.Registry.INSTANCE
				.getResourceServiceProvider(uri);
		if (serviceProvider != null) {
			IResourceDescription.Manager resourceDescriptionManager = serviceProvider.getResourceDescriptionManager();
			IResourceDescription resourceDescription = resourceDescriptionManager.getResourceDescription(resource);

			if (resourceDescription != null) {
				if (createDebugOutput)
					println("Adding resource description for resource '" + uri + "' to index.");

				index.addDescription(uri, resourceDescription);
			}
		}
	}

	/**
	 * Check for raw JS-files
	 *
	 * @param uri
	 *            to test
	 * @boolean if ends in .js or .js.xt
	 */
	private boolean isJsFile(URI uri) {
		ResourceType resourceType = ResourceType.getResourceType(uri);
		return (resourceType.equals(ResourceType.JS));
	}

	/**
	 * Helper logic if resources should be loaded.
	 *
	 * @param container
	 *            Source-container to decide on.
	 */
	boolean shouldReadResources(IN4JSSourceContainer container) {

		return (processTestCode || !container.isTest()) // no testcode if processtestcode is false
		;
	}

	/**
	 * In case of errors: throw exception
	 *
	 * @param allErrorsAndWarnings
	 *            list of issues and warnings
	 * @param projectId
	 *            projectId of the bad project.
	 * @throws N4JSCompileErrorException
	 *             in case of any issues of type Severity.ERROR
	 */
	private void failOnErrors(List<Issue> allErrorsAndWarnings, String projectId)
			throws N4JSCompileErrorException {

		ArrayList<Issue> errors = new ArrayList<>();
		Iterables.addAll(errors, Iterables.filter(allErrorsAndWarnings, e -> e.getSeverity() == Severity.ERROR));

		if (errors.size() != 0) {
			// dump other issues beforehand.
			allErrorsAndWarnings
					.stream()
					.filter(e -> e.getSeverity() != Severity.ERROR)
					.forEach(i -> println(issueLine(i)));
			String msg = "ERROR: cannot compile project " + projectId + " due to " + errors.size() + " errors.";
			for (Issue err : errors) {
				msg = msg + "\n  " + err;
			}
			throw new N4JSCompileErrorException(msg, projectId);
		}

	}

	/**
	 * Compiles all files in project.
	 *
	 * FileSystemAccess has to be correctly configured, see {@link #configureFSA(IN4JSProject)} and {@link #resetFSA()}
	 *
	 * @param markedProject
	 *            project to compile.
	 * @param resSet
	 *            outer resource set
	 * @param compileFilter
	 *            if not empty limit to this.
	 * @param rec
	 *            state reporter
	 * @throws N4JSCompileException
	 *             in case of compile-problems. Possibly wrapping other N4SJCompileExceptions.
	 */
	private void compileProject(MarkedProject markedProject, ResourceSet resSet, Predicate<URI> compileFilter,
			N4ProgressStateRecorder rec)
			throws N4JSCompileException {
		rec.markStartCompiling(markedProject);

		if (createDebugOutput) {
			println("# compiling " + markedProject.project);
		}

		N4JSCompoundCompileException collectedErrors = null;

		// then compile each file.
		for (Resource input : markedProject.resources) {
			if (compileFilter.test(input.getURI())) {
				boolean isTest = markedProject.isTest(input);
				boolean compile = (isTest && processTestCode) || (!isTest && compileSourceCode);
				if (compile) {
					try {
						rec.markStartCompile(input);
						if (verbose)
							info("compiling " + markedProject.project.getProjectId() + ": " + input.getURI());
						compositeGenerator.doGenerate(input, fsa);
						rec.markEndCompile(input);
					} catch (GeneratorException e) {
						rec.markBrokenCompile(e);

						if (keepOnCompiling) {
							if (collectedErrors == null) {
								collectedErrors = new N4JSCompoundCompileException("Errors during compiling project "
										+ markedProject.project.getProjectId() + ".");
							}
							collectedErrors.add(new N4JSCompileErrorException(e.getMessage(), markedProject.project
									.getProjectId(), e));
							if (verbose) {
								error(e.getMessage());
							}
						} else {
							// fail fast
							throw e;

						}
					}
				} else {
					rec.markSkippedCompile(input);
				}
			}
		}

		rec.markEndCompiling(markedProject);

		if (collectedErrors != null)
			throw collectedErrors;

	}

	@Inject
	public N4JSCache resourceScopeCache;

	private void unloadASTs(Collection<Resource> resources) {
		// Unload all ASTs
		for (Resource resource : resources) {
			if (resource instanceof N4JSResource) {
				N4JSResource n4jsResource = (N4JSResource) resource;

				// Make sure the resource is fully postprocessed before unloading the AST. Otherwise, resolving cross
				// references to the elements inside the resources from dependent projects will fail.
				n4jsResource.performPostProcessing();
				n4jsResource.unloadAST();
				resourceScopeCache.clear(resource);
			}

			for (Adapter adapter : resource.eAdapters()) {
				if (adapter instanceof OnChangeEvictingCache.CacheAdapter) {
					OnChangeEvictingCache.CacheAdapter cacheAdapter = (OnChangeEvictingCache.CacheAdapter) adapter;
					cacheAdapter.clearValues();
				}
			}
		}
	}

	/**
	 * @param allErrorsAndWarnings
	 *            list of issues and warnings
	 */
	private void dumpAllIssues(List<Issue> allErrorsAndWarnings) {
		// TODO IDE-2479: Restore this

		// for (Issue issue : allErrorsAndWarnings) {
		// println(issueLine(issue));
		// }
	}

	private String issueLine(Issue issue) {
		return "@issue = " + issue;
	}

	/**
	 * user-feedback
	 *
	 * @param message
	 *            warning
	 */
	private void warn(String message) {
		println("WARN:  " + message);
	}

	/**
	 * user-feedback if {@link #verbose}.
	 *
	 * @param message
	 *            info
	 */
	private void info(String message) {
		if (verbose) {
			println(message);
		}
	}

	/**
	 * user-feedback
	 *
	 * @param message
	 *            error
	 */
	private void error(String message) {
		println("ERROR: " + message);
	}

	private void println(String message) {
		if (!suppressOutput)
			System.out.println(message);
	}

	/**
	 * Only if {@link #createDebugOutput} is true, creates output to standard.out about the current build order. Does
	 * nothing otherwise.
	 *
	 * @param sortedProjects
	 *            list of topological sorted projects.
	 *
	 */
	private void printBuildOrder(List<MarkedProject> sortedProjects) {
		// TODO: IDE-2479: Restore this
		// if (!createDebugOutput)
		// return;

		int i = 1;
		for (MarkedProject mp : sortedProjects) {
			boolean build = mp.hasMarkers();
			println(" " + (build ? i : "-") + ". Project " + mp.project + " used by ["
					+ Joiner.on(", ").join(mp.markers)
					+ "] ");
			if (build) {
				i++;
			}
		}
	}

	/**
	 * Compile a list of projects.
	 *
	 * @param projects
	 *            the projects to compile. usually the base folder of the project is provided.
	 * @throws N4JSCompileException
	 *             in case of compile problems
	 */
	public void compileProjects(List<File> projects) throws N4JSCompileException {

		// use user.dir of caller as projects-root.
		compileProjects(Arrays.asList(new File(".")), projects, Collections.emptyList(), new DismissingIssueAcceptor());
	}

	/**
	 * Compile a list of projects.
	 *
	 * @param projects
	 *            the projects to compile. usually the base folder of the project is provided.
	 * @param issueAcceptor
	 *            the issue acceptor that can be used to collect or evaluate the issues occurring during compilation
	 * @throws N4JSCompileException
	 *             in case of compile problems
	 */
	public void compileProjects(List<File> projects, IssueAcceptor issueAcceptor) throws N4JSCompileException {

		// use user.dir of caller as projects-root.
		compileProjects(Arrays.asList(new File(".")), projects, Collections.emptyList(), issueAcceptor);
	}

	/*
	 * ===============================================================================================================
	 *
	 * PRINT DEBUG INFORMATION
	 *
	 * ===============================================================================================================
	 */

	/**
	 * Prints out some debug information about the user-provided compilation arguments.
	 *
	 * @param searchPaths
	 *            where to search for dependent projects.
	 * @param projectPaths
	 *            the projects to compile. the base folder of each project must be provided.
	 * @param singleSourceFiles
	 *            if non-empty limit compilation to the sources files listed here
	 */
	private void printCompileArguments(List<File> searchPaths, List<File> projectPaths, List<File> singleSourceFiles) {
		if (createDebugOutput) {
			println("### compileProjects(List,List,List) ");
			println("  # projectRoots = " + Joiner.on(", ").join(searchPaths));
			println("  # projects     = " + Joiner.on(", ").join(projectPaths));
			println("  # sources      = " + Joiner.on(", ").join(singleSourceFiles));
		}
	}

	/**
	 *
	 * @return if compile should proceed as far as possible
	 */
	public boolean isKeepOnCompiling() {
		return keepOnCompiling;
	}

	/**
	 * @param keepOnCompiling
	 *            true - keep compiling even if there are errors.
	 */
	public void setKeepOnCompiling(boolean keepOnCompiling) {
		this.keepOnCompiling = keepOnCompiling;
	}

	/**
	 * Marker-carrying wrapper around projects. As markers dependent projects still to be build are registered. This
	 * class is used in the build-process to compute the state of which projects to be loaded / can be unloaded.
	 *
	 * Capable of referencing resources for load-state tracking.
	 *
	 * Capable of storing a set of test-resources to quickly query the code-nature of resource (test / source)
	 */
	static class MarkedProject {
		/** the wrapped project */
		final IN4JSProject project;
		/**
		 * list of active markers: Other projects that depend on this one, are part of the current build & have not yet
		 * been build.
		 */
		final LinkedHashSet<IN4JSProject> markers = new LinkedHashSet<>();
		/** pointer to a List of loaded resource (all types) */
		List<Resource> resources = new LinkedList<>();
		/** Set of external-resources. This must be a subset of resources. */
		Set<Resource> externalResources = new HashSet<>();
		/** Set of test-resources. This must be a subset of resources. */
		Set<Resource> testResources = new HashSet<>();

		/**
		 * Create a wrapper around a project;
		 */
		public MarkedProject(IN4JSProject project) {
			this.project = project;
		}

		/**
		 * Tell if it is an external source.
		 *
		 * @param input
		 *            element of {@link #resources} to query for external / not external source
		 * @return if {@code input} is contained in {@link #externalResources}
		 */
		public boolean isExternal(Resource input) {
			return externalResources.contains(input);
		}

		/**
		 * Tell if it is a test.
		 *
		 * @param input
		 *            element of {@link #resources} to query for test / not test
		 * @return if {@code input} is contained in {@link #testResources}
		 */
		public boolean isTest(Resource input) {
			return testResources.contains(input);
		}

		public void markWith(IN4JSProject marker) {
			markers.add(marker);
		}

		public boolean hasMarker(IN4JSProject marker) {
			return markers.contains(marker);
		}

		public boolean hasMarkers() {
			return !markers.isEmpty();
		}

		/**
		 * Remove a marker (e.g. when project was build)
		 *
		 * @param marker
		 *            dependent project to be removed from makerlist.
		 * @return true if marker was in markerlsit
		 */
		public boolean remove(IN4JSProject marker) {
			return markers.remove(marker);
		}

		/**
		 * Unload all resources associated with this marked project and remove them from the given resource set.
		 *
		 * @param resourceSet
		 *            the resource set containing the resources of this project
		 * @param recorder
		 *            the progress state recorder
		 */
		public void unload(ResourceSet resourceSet, N4ProgressStateRecorder recorder) {
			recorder.markStartUnloading(this);

			ResourceDescriptionsData index = ResourceDescriptionsData.ResourceSetAdapter
					.findResourceDescriptionsData(resourceSet);

			unloadResources(resourceSet, index, recorder);
			unloadManifestResource(resourceSet, index, recorder);
			clearResources();

			recorder.markFinishedUnloading(this);
		}

		private void unloadResources(ResourceSet resourceSet, ResourceDescriptionsData index,
				N4ProgressStateRecorder recorder) {
			for (Resource res : resources)
				unloadResource(res, resourceSet, index, recorder);
		}

		private void unloadManifestResource(ResourceSet resourceSet, ResourceDescriptionsData index,
				N4ProgressStateRecorder recorder) {
			Optional<URI> manifestLocation = project.getManifestLocation();
			if (manifestLocation.isPresent()) {
				Resource resource = resourceSet.getResource(manifestLocation.get(), false);
				if (resource != null)
					unloadResource(resource, resourceSet, index, recorder);
			}
		}

		private void unloadResource(Resource resource, ResourceSet resourceSet, ResourceDescriptionsData index,
				N4ProgressStateRecorder recorder) {
			recorder.markUnloadingOf(resource);
			if (index != null)
				index.removeDescription(resource.getURI());
			resource.unload();
			resourceSet.getResources().remove(resource);
		}

		public void clearResources() {
			resources.clear();
			externalResources.clear();
			testResources.clear();
		}

		@Override
		public String toString() {
			return project.toString();
		}
	}

	/**
	 *
	 * @return if test-content should be considered
	 */
	public boolean isProcessTestCode() {
		return processTestCode;
	}

	/**
	 * @param processTestCode
	 *            true (default) test-content should be considered
	 */
	public void setProcessTestCode(boolean processTestCode) {
		this.processTestCode = processTestCode;
	}

	/**
	 *
	 * @return if sources should be transpiled
	 */
	public boolean isCompileSourceCode() {
		return compileSourceCode;
	}

	/**
	 *
	 * @param compileSourceCode
	 *            (default true) if Sources should be transpiled.
	 */
	public void setCompileSourceCode(boolean compileSourceCode) {
		this.compileSourceCode = compileSourceCode;
	}

	/**
	 *
	 * @return if debug messages should be printed
	 */
	public boolean isCreateDebugOutput() {
		return createDebugOutput;
	}

	/**
	 *
	 * @param createDebugOutput
	 *            (default false) if debug messages should be printed
	 */
	public void setCreateDebugOutput(boolean createDebugOutput) {
		this.createDebugOutput = createDebugOutput;
	}

	/**
	 * Indicates whether all output to standard out is suppressed.
	 *
	 * @return <code>true</code> if output to standard out is suppressed and <code>false</code> otherwise
	 */
	public boolean isOutputSuppressed() {
		return suppressOutput;
	}

	/**
	 * Set whether or not to suppress all output to standard out.
	 *
	 * @param suppressOutput
	 *            whether or not to suppress the output
	 */
	public void setOutputSuppressed(boolean suppressOutput) {
		this.suppressOutput = suppressOutput;
	}

	/**
	 * @param logFile
	 *            filname to write progress log to
	 */
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	/**
	 * Access to log file name
	 *
	 * @return null if not set, else file to write to.
	 */
	public String getLogFile() {
		return logFile;
	}

	/**
	 *
	 * @return true if verbose enabled
	 */
	public boolean isVerbose() {
		return verbose;
	}

	/**
	 *
	 * @param verbose
	 *            true to enable
	 */
	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	/**
	 * Reference to the creating Injector.
	 */
	public Injector getInjector() {
		return injector;
	}
}
