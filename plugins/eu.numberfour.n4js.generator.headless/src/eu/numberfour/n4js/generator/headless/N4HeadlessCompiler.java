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

import static com.google.common.collect.Lists.newArrayList;

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
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
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
import eu.numberfour.n4js.resource.OrderedResourceDescriptionsData;
import eu.numberfour.n4js.utils.ResourceType;

/**
 * Entry for headless compilation.
 *
 * This class has three ways of operation which all map down to a single algorithm implemented in
 * {@link #compileProjects(List, List, List)}. All other compileXXXX methods call this algorithm providing the correct
 * content of the arguments.
 *
 * <ol>
 * <li>compile "single file" takes a (list of) source-file(s) to compile and just compiles these if possible
 * {@link #compileSingleFile(File)}, {@link #compileSingleFiles(List)}, {@link #compileSingleFiles(List, List)}
 * <li>compile "projects" takes a list of porject-location and compiles exactly them. {@link #compileProjects(List)},
 * {@link #compileProjects(List, List)}
 * <li>compile "all project" takes a list of folders and compiles each project found as direct content of one of the
 * folders. {@link #compileAllProjects(List)}
 * </ol>
 *
 * The way how the compiler behaves can be configures through flags like {@link #keepOnCompiling},
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

		N4HeadlessCompiler hlc = injectAndSetup(properties);
		hlc.compileSingleFile(modelFile);
	}

	/**
	 * Construct a {@link N4HeadlessCompiler}-object based on preferences stored in properties
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
	 * Compile a single File
	 *
	 * @param modelFile
	 *            the source file to compile.
	 * @throws N4JSCompileException
	 *             due to compile errors
	 */
	public void compileSingleFile(File modelFile) throws N4JSCompileException {
		compileSingleFiles(Arrays.asList(modelFile));
	}

	/**
	 * Compile multiple Files
	 *
	 * @param modelFiles
	 *            the source files to compile.
	 * @throws N4JSCompileException
	 *             due to compile errors.
	 */
	public void compileSingleFiles(List<File> modelFiles) throws N4JSCompileException {
		compileSingleFiles(Collections.emptyList(), modelFiles);
	}

	/**
	 * Compile multiple Files
	 *
	 * @param modelFiles
	 *            the source files to compile.
	 * @param projectRoots
	 *            where to find dependencies.
	 * @throws N4JSCompileException
	 *             due to compile errors.
	 */
	public void compileSingleFiles(List<File> projectRoots, List<File> modelFiles) throws N4JSCompileException {
		compileProjects(projectRoots, Collections.emptyList(), modelFiles);
	}

	/**
	 * Starting from the ProjectRoot all Available subdirectories denoting a N4js-Project should be compiled together.
	 *
	 * @param pProjectRoots
	 *            base folders containing project at level 1
	 * @throws N4JSCompileException
	 *             in case of errros.
	 */
	public void compileAllProjects(List<File> pProjectRoots) throws N4JSCompileException {
		// make absolute, since downstream URI conversion doesn't work if relative dir only.
		List<File> absProjectRoots = HeadlessHelper.toAbsoluteFileList(pProjectRoots);

		// Collect all Projects in first Level
		ArrayList<File> pDir = HeadlessHelper.collectAllProjectPaths(absProjectRoots);

		compileProjects(pProjectRoots, pDir, Collections.emptyList());
	}

	/**
	 * Compile a list of projects.
	 *
	 * @param pProjectRoots
	 *            common workspaces for all projects to compile
	 * @param projectLocationsToCompile
	 *            the projects to compile. usually the base folder of each project is provided.
	 * @throws N4JSCompileException
	 *             signals Compile-errors
	 */
	public void compileProjects(List<File> pProjectRoots, List<File> projectLocationsToCompile)
			throws N4JSCompileException {
		compileProjects(pProjectRoots, projectLocationsToCompile, Collections.emptyList());
	}

	/**
	 * Compile a list of projects. Main algorithm.
	 *
	 * @param projectLocations
	 *            where to search for dependent projects.
	 * @param projectLocationsToCompile
	 *            the projects to compile. the base folder of each project must be provided.
	 * @param singleSourcesToCompile
	 *            if non-empty limit compilation to the sources files listed here
	 *
	 */
	@SuppressWarnings({ "unused" })
	public void compileProjects(List<File> projectLocations, List<File> projectLocationsToCompile,
			List<File> singleSourcesToCompile)
			throws N4JSCompileException {
		if (createDebugOutput) {
			System.out.println("### compileProjects(List,List,List) ");
			System.out.println("  # projectRoots = " + Joiner.on(", ").join(projectLocations));
			System.out.println("  # projects     = " + Joiner.on(", ").join(projectLocationsToCompile));
			System.out.println("  # sources      = " + Joiner.on(", ").join(singleSourcesToCompile));
		}

		// / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- /
		// collecting all available projects & corresponding uris; calculate
		// / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- /

		// make absolute, since downstream URI conversion doesn't work if relative dir only.
		List<File> absProjectRoots = HeadlessHelper.toAbsoluteFileList(projectLocations);
		List<File> absProjectLocationsToCompile = HeadlessHelper.toAbsoluteFileList(projectLocationsToCompile);
		List<File> absSingleSourcesToCompile = HeadlessHelper.toAbsoluteFileList(singleSourcesToCompile);
		// register Single-source projects as to be compiled as well:
		absProjectLocationsToCompile = combine(absProjectLocationsToCompile,
				findProjectsForSingleFiles(absSingleSourcesToCompile));
		Set<URI> compileFilter = Sets.newLinkedHashSet(
				absSingleSourcesToCompile.stream()
						.map(f -> URI.createFileURI(f.toString()))
						.collect(Collectors.toList()));

		// Collect all Projects in first Level
		ArrayList<File> pDirCollected = HeadlessHelper.collectAllProjectPaths(absProjectRoots);
		LinkedHashSet<File> pDir = new LinkedHashSet<>();
		pDir.addAll(absProjectLocationsToCompile);
		pDir.addAll(pDirCollected);

		ArrayList<URI> projectURIs = new ArrayList<>(pDir.size());
		ArrayList<URI> projectsToCompileURIs = new ArrayList<>(absProjectLocationsToCompile.size());
		for (File pdir : pDir) {
			URI puri = URI.createFileURI(pdir.toString());
			projectURIs.add(puri);
			if (absProjectLocationsToCompile.contains(pdir))
				projectsToCompileURIs.add(puri);
			try {
				fbWorkspace.registerProject(puri);
			} catch (N4JSBrokenProjectException e) {
				throw new N4JSCompileException("Unable to register project '" + puri + "'", e);
			}
		}

		// ////// Convert URI to N4JS Project.
		ArrayList<N4JSProject> projects = new ArrayList<>(projectURIs.size());
		ArrayList<N4JSProject> projectsToCompile = new ArrayList<>(projectsToCompileURIs.size());
		for (URI projectUri : projectURIs) {
			N4JSProject p = n4jsModel.getN4JSProject(projectUri);
			projects.add(p);
			if (projectsToCompileURIs.contains(projectUri)) {
				projectsToCompile.add(p);
			}
		}

		// / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- /
		// visibility management
		// / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- /

		// a container is a project.
		List<String> containers = new ArrayList<>();
		BiMap<String, N4JSProject> container2project = HashBiMap.create();

		// the Uris of all Resources directly contained in a project/container.
		Multimap<String, URI> container2Uris = HashMultimap.create();

		for (N4JSProject p : projects) {
			String container = FileBasedWorkspace.N4FBPRJ + p.getLocation();
			container2project.put(container, p);
			containers.add(container);
			// collect uris from all sources:
			for (IN4JSSourceContainer s : p.getSourceContainers()) {
				Iterables.addAll(container2Uris.get(container), s);
			}
		}

		// Define the Mapping of Resources (URIs to Container === Projects),
		rsbAcs.configure(containers, container2Uris);
		// Use one resourceSet for all projects.
		XtextResourceSet resourceSet = xtextResourceSetProvider.get();
		resourceSet.setClasspathURIContext(classLoader);
		// install containerState as adapter
		resourceSet.eAdapters().add(new DelegatingIAllContainerAdapter(rsbAcs));

		// / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- /
		// compiling
		// / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- / ----- /

		// do topological sorting according to the dependencies & mark the required projects.
		List<MarkedProject> sortedProjects = topoSort2(new ArrayList<>(projects), new ArrayList<>(projectsToCompile));
		dumpBuildorder(sortedProjects);

		// Error handling
		N4JSCompoundCompileException collectedErrors = null;
		// Extended errorhandling for keep-Failing.
		N4ProgressStateRecorder rec = new N4ProgressStateRecorder();
		// List for Tracking of loaded Projects.
		LinkedList<MarkedProject> loadedProjects = new LinkedList<>();

		for (MarkedProject mp : sortedProjects) {
			// only load if is marked.
			if (mp.hasMarkers()) {
				rec.markProcessing(mp.project);
				configureFSA(mp.project);
				try {
					// load
					doLoad(mp, resourceSet, rec);
					loadedProjects.add(mp);
					// compile only if it has itself as marker and non-external
					if (mp.hasMarker(mp.project) && !mp.project.isExternal()) {
						// compile only:
						doCompile(mp, resourceSet, compileFilter, rec);
					}
					// remove marker from loaded
					ListIterator<MarkedProject> loadedIter = loadedProjects.listIterator();
					while (loadedIter.hasNext()) {
						MarkedProject loaded = loadedIter.next();
						loaded.remove(mp.project);
						// §§ // §§§§ // §§§§ // §§§§ // §§§§ // §§§§ // §§§§ // §§§§ // §§
						// TODO BELOW are two different ways of dealing with unloading
						// they differ in the overall performance.
						// theoretically the Resources of a project could be unloaded after compilation
						// in practise letting them in memory is faster (presuming there is sufficient memory)
						// strategy 1) unload if no other project needs to access the contents of this project
						// strategy 2) unload after compilation
						// strategy 3) none of the below
						/*-*/// unload guarded // TODO experimental, remove if: unload immediately works (below)
						// Ohne unload 51-54 sec. nur im recorder
						if (true) { // mit marker-unload 55.6-56.2 sec unload
							// Strategy 1:
							if (!loaded.hasMarkers()) {
								// unload from ResourceSet
								doUnload(loaded, rec);
								loadedIter.remove();
							} /**/
						} else {

							// Strategy 2:
							// unload immediately // direkter unload nach compile 76 - 80 sec im rekorder.
							doUnload(loaded, rec);
							loadedIter.remove();
						}
						// §§ // §§§§ // §§§§ // §§§§ // §§§§ // §§§§ // §§§§ // §§§§ // §§
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
				rec.markEndProcessing(mp.project);
			}
		}

		rec.dumpToLogfile(logFile);

		if (collectedErrors != null) {
			throw collectedErrors;
		}

	}

	/**
	 * Combine listA and listB to a single List without duplicates.
	 *
	 * @param listA
	 *            a
	 * @param listB
	 *            b
	 * @return oder-preserved union of a and b
	 */
	private List<File> combine(List<File> listA, List<File> listB) {
		LinkedHashSet<File> combinedProjects = Sets.newLinkedHashSet(listA);
		combinedProjects.addAll(listB);
		return new ArrayList<>(combinedProjects);
	}

	/**
	 * Collects the projects related to source-files.
	 *
	 * @param absSingleSourcesToCompile
	 *            List of source-files.
	 * @return list of N4JS projects.
	 * @throws N4JSCompileException
	 *             if a project to a source-file cannot be found.
	 */
	private List<File> findProjectsForSingleFiles(List<File> absSingleSourcesToCompile)
			throws N4JSCompileException {

		Collection<URI> puris = Sets.newLinkedHashSet();

		for (File f : absSingleSourcesToCompile) {
			URI pUri = fbWorkspace.findProjectWith(URI.createFileURI(f.toString()));
			if (pUri == null) {
				throw new N4JSCompileException("No project for file '" + f.toString() + "' found.");
			}
			puris.add(pUri);
		}
		// convert back to Files:
		return puris.stream()
				.map(uri -> new File(uri.toFileString()))
				.collect(Collectors.toList());
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
		// compute relative path, if project is not in a subdir of userdir an absolute
		// path is computed.
		java.net.URI relativize = userdir.toURI().relativize(prjdir.toURI());
		final String relativePrjReference = relativize.getPath();
		if (relativePrjReference.length() == 0) {
			// same directory, NTD
			return;
		}
		// set different outputconfiguration.
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
	 * Reset outputconfiguration to initial settings stored in {@link #outputs}.
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
	 * @throws N4JSCompileErrorException
	 *             in case of compile-problems.
	 */
	private void doLoad(MarkedProject markedProject, ResourceSet resSet, N4ProgressStateRecorder rec)
			throws N4JSCompileErrorException {

		rec.markStartLoading(markedProject);
		if (createDebugOutput) {
			System.out.println("# loading " + markedProject.project);
		}

		// load all files into a resource set
		LinkedList<Resource> resources = new LinkedList<>();
		HashSet<Resource> externalResources = new HashSet<>();
		HashSet<Resource> testResources = Sets.newHashSet();

		// TODO try to reuse code from IN4JSCore.createResourceSet
		ImmutableList<? extends IN4JSSourceContainer> srcCont = markedProject.project.getSourceContainers();
		for (IN4JSSourceContainer container : srcCont) {
			// Conditionally filter test-resources if not desired
			if (shouldReadResources(container)) {
				container.forEach(uri -> {
					Resource resource = resSet.createResource(uri);
					if (resource != null) {
						if (createDebugOutput) {
							System.out.println("Collecting resources from source container: " + resource.getURI());
						}
						resources.add(resource);
						if (container.isExternal())
							externalResources.add(resource); // register externals.
						if (container.isTest())
							testResources.add(resource); // register tests.
					} else {
						rec.markFailedCreateResource(uri);
						warn("Skipped file: could not create resource for URI=" + uri);
					}
				});
			}
		}
		installIndex(resSet, markedProject.project.getManifestLocation());
		// Load each file into memory.
		for (Resource res : resources) {
			try {
				res.load(Collections.EMPTY_MAP);
			} catch (IOException e) {
				rec.markLoadResourceFailed(res);
				String message = "Cannot load resource=" + res.getURI();
				if (!keepOnCompiling) {
					throw new N4JSCompileErrorException(message,
							markedProject.project.getProjectName(), e);
				}
				warn(message);
			}
		}

		// store for compiling &| unloading
		markedProject.resources = resources;
		markedProject.externalResources = externalResources;
		markedProject.testResources = testResources;

		// Validate and find broken resources:
		ArrayList<Issue> allErrorsAndWarnings = newArrayList();

		// validation TODO see IDE-1426 redesign validation calls with generators
		for (Resource resource : resources) {
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
					(!externalResources.contains(resource)) // not in external folder
			) {
				XtextResource xtextResource = (XtextResource) resource;
				List<Issue> issues = xtextResource.getResourceServiceProvider().getResourceValidator()
						.validate(xtextResource, CheckMode.ALL, CancelIndicator.NullImpl);
				if (!issues.isEmpty()) {
					rec.markResourceIssues(resource, issues);
					for (Issue issue : issues) {
						allErrorsAndWarnings.add(issue);
					}
				}
			}
		}

		dumpAllIssues(allErrorsAndWarnings);

		// Projects should not compile if there are severe errors:
		if (!keepOnCompiling) {
			failOnErrors(allErrorsAndWarnings, markedProject.project.getProjectName());
		}
	}

	/**
	 * TODO try to reuse code from IN4JSCore.createResourceSet
	 */
	private void installIndex(ResourceSet resourceSet, Optional<URI> manifestUri) {
		// Fill index
		ResourceDescriptionsData index = new OrderedResourceDescriptionsData(
				Collections.<IResourceDescription> emptyList());
		List<Resource> resources = Lists.newArrayList(resourceSet.getResources());
		for (Resource resource : resources) {
			index(resource, index);
		}

		// Create index for N4 manifest as well. Index artifact names among project types and library dependencies.
		if (manifestUri.isPresent()) {
			final Resource manifestResource = resourceSet.getResource(manifestUri.get(), true);
			if (null != manifestResource) {
				index(manifestResource, index);
			}
		}

		Adapter existing = EcoreUtil.getAdapter(resourceSet.eAdapters(), ResourceDescriptionsData.class);
		if (existing != null) {
			resourceSet.eAdapters().remove(existing);
		}
		ResourceDescriptionsData.ResourceSetAdapter.installResourceDescriptionsData(resourceSet, index);
	}

	/**
	 * Installing the ResourceDescription of a resource into the index. Raw JS-files will not be indexed.
	 */
	private void index(Resource resource, ResourceDescriptionsData index) {

		final URI uri = resource.getURI();

		if (isJsFile(uri)) {
			IN4JSSourceContainer sourceContainer = n4jsCore.findN4JSSourceContainer(uri).orNull();
			if (null == sourceContainer) {
				return; // We do not want to index resources that are not in source containers.
			}
		}

		IResourceServiceProvider serviceProvider = IResourceServiceProvider.Registry.INSTANCE
				.getResourceServiceProvider(uri);
		if (serviceProvider != null) {
			IResourceDescription resourceDescription = serviceProvider.getResourceDescriptionManager()
					.getResourceDescription(resource);
			if (resourceDescription != null) {
				if (createDebugOutput) {
					System.out.println("Adding resource description for resource '" + uri + "' to index.");
				}
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
	private void doCompile(MarkedProject markedProject, ResourceSet resSet, Set<URI> compileFilter,
			N4ProgressStateRecorder rec)
			throws N4JSCompileException {
		rec.markStartCompiling(markedProject);

		if (createDebugOutput) {
			System.out.println("# compiling " + markedProject.project);
		}

		boolean unlimitedCompilation = compileFilter.isEmpty();
		N4JSCompoundCompileException collectedErrors = null;

		// then compile each file.
		for (Resource input : markedProject.resources) {
			if (unlimitedCompilation || compileFilter.contains(input.getURI())) {
				boolean isTest = markedProject.isTest(input);
				boolean compile = (isTest && processTestCode) || (!isTest && compileSourceCode);
				if (compile) {
					try {
						rec.markStartCompile(input);
						if (verbose)
							info("compiling " + markedProject.project.getProjectName() + ": " + input.getURI());
						compositeGenerator.doGenerate(input, fsa);
						rec.markEndCompile(input);
					} catch (GeneratorException e) {
						rec.markBrokenCompile(e);

						if (keepOnCompiling) {
							if (collectedErrors == null) {
								collectedErrors = new N4JSCompoundCompileException("Errors during compiling project"
										+ markedProject.project.getProjectName() + ".");
							}
							collectedErrors.add(new N4JSCompileErrorException(e.getMessage(), markedProject.project
									.getProjectName(), e));
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

	/**
	 * Unload all referenced resources.
	 *
	 * @param markedProject
	 *            carries pointer to resourcelist.
	 * @param rec
	 *            state reporting
	 */
	@SuppressWarnings("unused")
	private void doUnload(MarkedProject markedProject, N4ProgressStateRecorder rec)
			throws N4JSCompileErrorException {
		if (createDebugOutput) {
			System.out.println("# unloading " + markedProject.project);
		}
		rec.markStartUnloading(markedProject);
		// Clean resourceSet ?
		for (Resource res : markedProject.resources) {
			rec.markUnloadingOf(res);
			res.unload();
		}
		rec.markFinishedUnloading(markedProject);

	}

	/**
	 * In case of errors: throw exception
	 *
	 * @param allErrorsAndWarnings
	 *            list of issues and warnings
	 * @throws N4JSCompileErrorException
	 *             in case of any issues of type Severity.ERROR
	 */
	private void failOnErrors(ArrayList<Issue> allErrorsAndWarnings, String projectname)
			throws N4JSCompileErrorException {

		ArrayList<Issue> errors = new ArrayList<>();
		Iterables.addAll(errors, Iterables.filter(allErrorsAndWarnings, e -> e.getSeverity() == Severity.ERROR));

		if (errors.size() != 0) {
			// dump other issues beforehand.
			allErrorsAndWarnings
					.stream()
					.filter(e -> e.getSeverity() != Severity.ERROR)
					.forEach(i -> System.out.println(issueLine(i)));
			String msg = "ERROR: Cannot compile Project " + projectname + " due to " + errors.size() + " errors.";
			for (Issue err : errors) {
				msg = msg + "\n  " + err;
			}
			throw new N4JSCompileErrorException(msg, projectname);
		}

	}

	/**
	 * @param allErrorsAndWarnings
	 *            list of issues and warnings
	 */
	private void dumpAllIssues(ArrayList<Issue> allErrorsAndWarnings) {
		for (Issue issue : allErrorsAndWarnings) {
			System.out.println(issueLine(issue));
		}

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
		System.out.println("WARN:  " + message);
	}

	/**
	 * user-feedback if {@link #verbose}.
	 *
	 * @param message
	 *            info
	 */
	private void info(String message) {
		if (verbose) {
			System.out.println(message);
		}
	}

	/**
	 * user-feedback
	 *
	 * @param message
	 *            error
	 */
	private void error(String message) {

		System.out.println("ERROR: " + message);

	}

	/**
	 * Only if {@link #createDebugOutput} is true, creates output to standard.out about the current build order. Does
	 * nothing otherwise.
	 *
	 * @param sortedProjects
	 *            list of topological sorted projects.
	 *
	 */
	private void dumpBuildorder(List<MarkedProject> sortedProjects) {
		if (!createDebugOutput)
			return;
		int i = 1;
		for (MarkedProject mp : sortedProjects) {
			boolean build = mp.hasMarkers();
			System.out.println(" " + (build ? i : "-") + ". Project " + mp.project + " used by ["
					+ Joiner.on(", ").join(mp.markers)
					+ "] ");
			if (build) {
				i++;
			}
		}
	}

	/**
	 * Sort in build-order. Wraps each element of {@code toSort} with {@link MarkedProject} and applies all
	 * {@code buildMarker} for which the element is a (transitively) declared dependency
	 *
	 * @param toSort
	 *            unsorted projects.
	 * @param buildMarker
	 *            projects to build.
	 * @return sorted projects: earlier projects don't depend on later
	 */
	private static LinkedList<MarkedProject> topoSort2(ArrayList<IN4JSProject> toSort,
			ArrayList<IN4JSProject> buildMarker) {

		HashMap<IN4JSProject, MarkedProject> hmMarkables = new HashMap<>();
		// Map to Markers:
		toSort.stream()
				.forEach(p -> hmMarkables.put(p, new MarkedProject(p)));

		// Set of projects not part of the current build-action, empty if valid
		HashSet<IN4JSProject> unresolvedProjects = new HashSet<>();
		HashSet<IN4JSProject> validProjects = new HashSet<>(toSort);

		// already processed Projects
		HashSet<IN4JSProject> visited = new HashSet<>(toSort.size());
		// list of resulting ordered projects: each project depends only on projects to the left.
		LinkedList<MarkedProject> sorted = new LinkedList<>();
		// list of projects without dependency (starting points)
		LinkedList<IN4JSProject> dependencyfree = new LinkedList<>();

		// inverse dependencies
		HashMultimap<IN4JSProject, IN4JSProject> preconditionTo = HashMultimap.<IN4JSProject, IN4JSProject> create();
		HashMultimap<IN4JSProject, IN4JSProject> dependency = HashMultimap.<IN4JSProject, IN4JSProject> create();

		// Collect link model.
		for (IN4JSProject p : toSort) {
			recCollect(p, visited, validProjects, unresolvedProjects, preconditionTo, dependency, dependencyfree);
		}

		// Mark the projects to build, using a set to remove duplicates.
		new HashSet<>(buildMarker).stream().forEach(m -> markDependencies(m, m, hmMarkables, dependency));

		LinkedList<IN4JSProject> nextRoundDependencyFree = new LinkedList<>();
		// Marching front:
		while (!dependencyfree.isEmpty()) {
			// current
			IN4JSProject p = dependencyfree.pop();
			sorted.add(hmMarkables.get(p));
			// get all dependent projects:
			Set<IN4JSProject> dependent = preconditionTo.removeAll(p);
			for (IN4JSProject d : dependent) {
				// clean dependency:
				Set<IN4JSProject> d_unresolved = dependency.get(d);
				d_unresolved.remove(p);
				if (d_unresolved.isEmpty()) {
					nextRoundDependencyFree.push(d);
				}
			}
			if (dependencyfree.isEmpty()) {
				// swap lists.
				final LinkedList<IN4JSProject> swp = dependencyfree;
				dependencyfree = nextRoundDependencyFree;
				nextRoundDependencyFree = swp;
			}
		}
		// assertions here:
		// 1. preconditionTo is empty.
		// 2. dependency is empty.

		return sorted;
	}

	/**
	 * Mark the dependency subgraph of {@code tobeMarked} with {@code marker}. Calls itself recursively.
	 *
	 * @param marker
	 *            Marker to apply
	 * @param tobeMarked
	 *            Project which should be marked
	 * @param hmMarkables
	 *            lookup map for the Markables
	 * @param dependency
	 *            depdency structure to walk
	 */
	private static void markDependencies(IN4JSProject marker, IN4JSProject tobeMarked,
			HashMap<IN4JSProject, MarkedProject> hmMarkables,
			HashMultimap<IN4JSProject, IN4JSProject> dependency) {
		// get the markable
		hmMarkables.get(tobeMarked).markWith(marker);
		dependency.get(tobeMarked).stream()
				.forEach(d -> markDependencies(marker, d, hmMarkables, dependency));
	}

	/**
	 * Recursive algorithm
	 *
	 * @param p
	 *            current project
	 * @param visited
	 *            set of projects already processed.
	 * @param validProjects
	 *            set of valid projects (the ones given to be ordered)
	 * @param preconditionTo
	 *            inverse of dependency
	 * @param dependency
	 *            inverse of preconditionTo
	 * @param dependencyfree
	 *            - projects which don't depend on others.
	 */
	private static void recCollect(IN4JSProject p, HashSet<IN4JSProject> visited, HashSet<IN4JSProject> validProjects,
			HashSet<IN4JSProject> unresolvedDependencies,
			HashMultimap<IN4JSProject, IN4JSProject> preconditionTo,
			HashMultimap<IN4JSProject, IN4JSProject> dependency, LinkedList<IN4JSProject> dependencyfree) {

		// already done?
		if (visited.contains(p)) {
			// Cycle detection later ?
			return;
		}
		visited.add(p);

		// build dependencies && inverse dependencies.
		ImmutableList<? extends IN4JSProject> dependencies = p.getDependenciesAndImplementedApis();
		if (dependencies.isEmpty()) {
			dependencyfree.add(p);
		} else {
			for (IN4JSProject dep : dependencies) {
				dependency.put(p, dep);
				preconditionTo.put(dep, p);
				if (!validProjects.contains(dep)) {
					// found a dependency on a project which is not part of the build.
					unresolvedDependencies.add(dep);
				}
				// recursive call:
				recCollect(dep, visited, validProjects, unresolvedDependencies, preconditionTo, dependency,
						dependencyfree);
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
		compileProjects(Arrays.asList(new File(".")), projects, Collections.emptyList());
	}

	/**
	 * Compile all provided source-files.
	 *
	 * @param projectRoot
	 *            common workspace.
	 * @param sourceFiles
	 *            all sources to compile
	 * @throws N4JSCompileException
	 *             signals compile errors
	 */
	public void compileSourceFiles(List<File> projectRoot, List<File> sourceFiles) throws N4JSCompileException {

		compileSingleFiles(projectRoot, sourceFiles);
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
		List<Resource> resources = Collections.emptyList();
		/** Set of external-resources. This must be a subset of resources. */
		Set<Resource> externalResources = Collections.emptySet();
		/** Set of test-resources. This must be a subset of resources. */
		Set<Resource> testResources = Collections.emptySet();

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
