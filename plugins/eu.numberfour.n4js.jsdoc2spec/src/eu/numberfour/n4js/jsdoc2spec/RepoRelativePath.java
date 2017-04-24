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
package eu.numberfour.n4js.jsdoc2spec;

import java.io.File;
import java.nio.file.Path;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jgit.errors.ConfigInvalidException;
import org.eclipse.jgit.lib.Config;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.Files;

import com.google.common.base.Optional;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.ts.types.SyntaxRelatedTElement;

/**
 * Value object containing information about the repository relative location of a file, optionally with linen umber to
 * create a deep link.
 */
public class RepoRelativePath {

	/**
	 * Creates the RepoRelativePath from a given resource. Returns null, if resource is not contained in a repository.
	 * If a repository is found, the simple name of the origin is used.
	 */
	public static RepoRelativePath compute(Resource resource, IN4JSCore n4jsCore) {
		URI uri = resource.getURI();
		Optional<? extends IN4JSProject> optProj = n4jsCore.findProject(uri);
		if (optProj.isPresent()) {
			IN4JSProject project = optProj.get();
			Path path = project.getLocationPath();

			String uriFileString = uri.toString();
			String uriProjString = project.getLocation().toString();
			String fileRelString = uriFileString.substring(uriProjString.length());

			String absFileName = path.toAbsolutePath() + fileRelString;
			File file = new File(absFileName);
			if (!file.exists()) {
				return null;
			}
			File f = file.getParentFile();
			while (f != null && f.isDirectory() && f.exists()) {
				File[] files = f.listFiles((File pathname) -> pathname.isDirectory()
						&& ".git".equals(pathname.getName()));
				if (files.length > 0) {
					String repoName = null;

					File config = new File(files[0], "config");
					if (config.exists()) {
						try {
							String configStr = Files.readFileIntoString(config.getAbsolutePath());
							Config cfg = new Config();

							cfg.fromText(configStr);
							String originURL = cfg.getString("remote", "origin", "url");
							if (originURL != null && !originURL.isEmpty()) {
								int lastSlash = originURL.lastIndexOf('/');
								if (lastSlash >= 0) {
									repoName = originURL.substring(lastSlash + 1);
								} else {
									repoName = originURL;
								}
								if (repoName.endsWith(".git")) {
									repoName = repoName.substring(0, repoName.length() - 4);
								}
							}
						} catch (ConfigInvalidException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (repoName == null) {
						repoName = f.getName();
					}

					String relativePath = file.getAbsolutePath().substring(f.getAbsolutePath().length());
					if (File.separatorChar != '/') {
						relativePath = relativePath.replace(File.separatorChar, '/');
					}

					return new RepoRelativePath(repoName, relativePath, -1);

				} else {
					f = f.getParentFile();
				}

			}
		}
		return null;
	}

	/**
	 * Simple name of repository.
	 */
	public final String repositoryName;
	/**
	 * Absolute path in repository (with leading slash).
	 */
	public final String pathInRepository;
	/**
	 * Optionally a line number, -1 if no line number is available.
	 */
	public final int lineNumber;

	private RepoRelativePath(String repositoryName, String pathInRepository, int lineNumber) {
		this.repositoryName = repositoryName;
		this.pathInRepository = pathInRepository;
		this.lineNumber = lineNumber;

	}

	/**
	 * Returns a (maybe new) RepoRelativePath with line number of the given test member.
	 */
	public RepoRelativePath withLine(SyntaxRelatedTElement testMember) {
		ICompositeNode node = NodeModelUtils.getNode(testMember.getAstElement());
		if (node != null) {
			return new RepoRelativePath(repositoryName, pathInRepository, node.getStartLine());
		}
		return this;
	}

	/**
	 * Compares this rrp with another one.
	 */
	public int compareTo(RepoRelativePath rrp) {
		if (rrp == this) {
			return 0;
		}
		if (rrp == null) {
			return 1;
		}
		int d = repositoryName.compareTo(rrp.repositoryName);
		if (d == 0) {
			d = pathInRepository.compareTo(rrp.pathInRepository);
			if (d == 0) {
				d = lineNumber - rrp.lineNumber;
			}
		}
		return d;
	}

	@Override
	public int hashCode() {
		return (repositoryName.hashCode() * 31 + pathInRepository.hashCode()) * 31 + lineNumber;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RepoRelativePath) {
			return compareTo((RepoRelativePath) obj) == 0;
		}
		return false;
	}

	/**
	 * <quote> A section begins with the name of the section in square brackets and continues until the next section
	 * begins. Section names are case-insensitive. Only alphanumeric characters, - and . are allowed in section names.
	 * Each variable must belong to some section, which means that there must be a section header before the first
	 * setting of a variable. Sections can be further divided into subsections. To begin a subsection put its name in
	 * double quotes, separated by space from the section name, in the section header.</quote>
	 * <a href="https://www.kernel.org/pub/software/scm/git/docs/git-config.html">[git-config]</a>
	 *
	 *
	 */

}
