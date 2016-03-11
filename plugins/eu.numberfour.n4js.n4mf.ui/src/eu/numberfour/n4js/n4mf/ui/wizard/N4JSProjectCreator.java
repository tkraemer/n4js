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
package eu.numberfour.n4js.n4mf.ui.wizard;

import static java.lang.String.valueOf;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.eclipse.core.resources.IResource.DEPTH_INFINITE;
import static org.eclipse.xtext.ui.XtextProjectHelper.BUILDER_ID;
import static org.eclipse.xtext.ui.XtextProjectHelper.NATURE_ID;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xpand2.XpandExecutionContextImpl;
import org.eclipse.xpand2.output.Outlet;
import org.eclipse.xpand2.output.OutputImpl;
import org.eclipse.xtend.type.impl.java.JavaBeansMetaModel;
import org.eclipse.xtext.ui.util.ProjectFactory;
import org.eclipse.xtext.ui.wizard.AbstractProjectCreator;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.numberfour.n4js.n4mf.utils.N4MFConstants;

/**
 * Creates a Eclipse project with Xtext nature, a src and a src-gen folder and generating an example N4JS file inside
 * the src folder and a manifest.n4mf file in the project root. The name of the project as well as the contents of the
 * manifest.n4mf are derived from the project name given by the user in the new project wizard.
 */
public class N4JSProjectCreator extends AbstractProjectCreator {

	private static Logger LOGGER = Logger.getLogger(N4JSProjectCreator.class);

	private static final String SRC_ROOT = "src";
	private static final String SRC_GEN = "src-gen";
	private static final String[] BUILDERS = { BUILDER_ID };
	private static final String[] NATURES = { NATURE_ID };
	private static final List<String> SRC_FOLDER_LIST = ImmutableList.of(SRC_ROOT, SRC_GEN);

	@Inject
	private Provider<ProjectFactory> projectFactoryProvider;

	@Override
	protected ProjectFactory createProjectFactory() {
		return projectFactoryProvider.get();
	}

	@Override
	protected String getModelFolderName() {
		return SRC_ROOT;
	}

	@Override
	protected List<String> getAllFolders() {
		return SRC_FOLDER_LIST;
	}

	@Override
	protected String[] getProjectNatures() {
		return NATURES;
	}

	@Override
	protected String[] getBuilders() {
		return BUILDERS;
	}

	@Override
	protected ProjectFactory configureProjectFactory(final ProjectFactory factory) {
		final ProjectFactory projectFactory = super.configureProjectFactory(factory);
		final N4MFProjectInfo projectInfo = (N4MFProjectInfo) getProjectInfo();
		if (null != projectInfo.getProjectLocation()) {
			projectFactory.setLocation(projectInfo.getProjectLocation());
		}
		return projectFactory;
	}

	@Override
	protected void enhanceProject(final IProject project, final IProgressMonitor monitor) throws CoreException {
		final OutputImpl output = new OutputImpl();
		output.addOutlet(
				new Outlet(false, getEncoding(), null, true, project.getLocation().makeAbsolute().toOSString()));

		final XpandExecutionContextImpl execCtx = new XpandExecutionContextImpl(output, null);
		execCtx.getResourceManager().setFileEncoding(valueOf(UTF_8));
		execCtx.registerMetaModel(new JavaBeansMetaModel());

		final N4MFProjectInfo pi = (N4MFProjectInfo) getProjectInfo();

		IWorkbench wb = PlatformUI.getWorkbench();
		wb.getWorkingSetManager().addToWorkingSets(project, pi.getSelectedWorkingSets());

		// IDEBUG-844 project name based string token used for generated files
		String projectName = pi.getProjectName();

		// folders in SRC_FOLDER_LIST are already created by the super class
		List<String> otherFolders = Arrays.asList();
		// create other folders
		for (Iterator<String> iterator = otherFolders.iterator(); iterator.hasNext();) {
			String folderName = iterator.next();
			IFolder folder = project.getFolder(folderName);
			folder.create(false, true, null);
		}

		Charset charset = getWorkspaceCharsetOrUtf8();

		String safeProjectName = projectName.replaceAll("\\.", "_").replaceAll("-", "_").trim();
		// create files
		List<String> files = Arrays.asList(SRC_ROOT + "/" + "GreeterModule_" + safeProjectName + ".n4js");
		// create other folders
		for (Iterator<String> iterator = files.iterator(); iterator.hasNext();) {
			String folderName = iterator.next();
			IFile file = project.getFile(folderName);
			file.create(FileContentUtil.from(NewN4JSProjectFileTemplates
					.getSourceFileWithGreeterClass(projectName, safeProjectName), charset), false, null);
		}

		List<String> sources = Arrays.asList(SRC_ROOT);
		List<String> externals = Arrays.asList();
		List<String> tests = Arrays.asList();

		// TODO refactor N4MFProjectInfo to contain all data needed by project template / generation

		// create manifest
		IFile manifest = project.getFile(N4MFConstants.N4MF_MANIFEST);
		manifest.create(FileContentUtil.from(NewN4JSProjectFileTemplates
				.getManifestContents(projectName, pi.getProjectTypeForManifest(), sources, externals, tests, SRC_GEN,
						pi.getImplementationId(), pi.getImplementedApis()),
				charset),
				false, null);

		project.refreshLocal(DEPTH_INFINITE, monitor);
	}

	/**
	 * Tries to get {@link org.eclipse.core.resources.IWorkspaceRoot#getDefaultCharset() workspace default charset}. In
	 * case of errors, will return {@link StandardCharsets#UTF_8}
	 *
	 * @return workspace charset or default one
	 */
	private Charset getWorkspaceCharsetOrUtf8() {
		try {
			return Charset.forName(super.getEncoding());
		} catch (CoreException | UnsupportedCharsetException e) {
			LOGGER.error("Cannot obtain workspace charset", e);
			LOGGER.info("Exceptions when obtaining workspace default charset, fall back to the "
					+ StandardCharsets.UTF_8.name(), e);
			return StandardCharsets.UTF_8;
		}
	}
}
