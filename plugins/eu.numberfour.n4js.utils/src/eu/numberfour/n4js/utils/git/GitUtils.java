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
package eu.numberfour.n4js.utils.git;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Iterables.size;
import static com.google.common.collect.Iterables.toArray;
import static eu.numberfour.n4js.utils.Arrays2.isEmpty;
import static java.nio.file.Files.createDirectories;
import static org.apache.log4j.Logger.getLogger;
import static org.eclipse.jgit.api.Git.cloneRepository;
import static org.eclipse.jgit.api.Git.open;
import static org.eclipse.jgit.api.ListBranchCommand.ListMode.REMOTE;
import static org.eclipse.jgit.api.ResetCommand.ResetType.HARD;
import static org.eclipse.jgit.lib.Constants.HEAD;
import static org.eclipse.jgit.lib.Constants.MASTER;
import static org.eclipse.jgit.lib.Constants.R_REMOTES;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.ProgressMonitor;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.transport.SshTransport;

import com.google.common.base.Joiner;
import com.google.common.base.Throwables;

import eu.numberfour.n4js.utils.io.FileDeleter;

/**
 * Contains a bunch of utility methods for performing Git operations.
 */
public abstract class GitUtils {

	/**
	 * Shared logger instance.
	 */
	private static final Logger LOGGER = getLogger(GitUtils.class);

	/** Callback that configures the SSH factory for the transport if possible, otherwise does nothing at all. */
	private static final TransportConfigCallback TRANSPORT_CALLBACK = transport -> {
		if (transport instanceof SshTransport) {
			((SshTransport) transport).setSshSessionFactory(new SshSessionFactory());
		}
	};

	/**
	 * Hard resets the {@code HEAD} of the reference in the locally cloned Git repository. If the repository does not
	 * exists yet at the given local clone path, then it also clones it, iff the {@code cloneIfMissing} argument is
	 * {@code true}.
	 *
	 * @param remoteUri
	 *            the URI of the remote repository. Could be omitted if the {@code cloneIfMissing} is {@code false}.
	 * @param localClonePath
	 *            the local path of the cloned repository.
	 * @param branch
	 *            the name of the branch to reset the {@code HEAD} pointer.
	 * @param cloneIfMissing
	 *            {@code true} if the repository has to be cloned in case if its absence.
	 */
	public static void hardReset(final String remoteUri, final Path localClonePath, final String branch,
			final boolean cloneIfMissing) {

		LOGGER.info("Performing hard reset... [Local repository: " + localClonePath + ", remote URI: " + remoteUri
				+ ", branch: " + branch + "]");

		checkNotNull(localClonePath, "localClonePath");
		if (cloneIfMissing) {
			checkNotNull(remoteUri, "remoteUri");
			clone(remoteUri, localClonePath, branch);
		}

		try (final Git git = open(localClonePath.toFile())) {
			if (!branch.equals(git.getRepository().getBranch())) {
				LOGGER.info("Current branch is: '" + git.getRepository().getBranch() + "'.");
				LOGGER.info("Switching to desired '" + branch + "' branch...");
				git.pull().setProgressMonitor(createMonitor()).call();
				final Iterable<Ref> localBranchRefs = git.branchList().call();
				final boolean createLocalBranch = !from(localBranchRefs).transform(ref -> ref.getName()).toSet()
						.contains(branch);
				LOGGER.info("Creating local branch '" + branch + "'? --> " + (createLocalBranch ? "yes" : "no"));
				git.checkout().setCreateBranch(createLocalBranch).setName(branch)
						.setStartPoint(R_REMOTES + "origin/" + branch).call();
				checkState(branch.equals(git.getRepository().getBranch()),
						"Error when checking out '" + branch + "' branch.");
				LOGGER.info("Switched to '" + git.getRepository().getBranch() + "' branch.");
				git.pull().setProgressMonitor(createMonitor()).call();
			}
			LOGGER.info("Hard resetting local repository HEAD of the '" + branch + "' in '" + remoteUri + "'...");
			LOGGER.info("Local repository location: " + localClonePath + ".");
			final ResetCommand resetCommand = git.reset().setMode(HARD).setRef(HEAD);
			final Ref ref = resetCommand.call();
			LOGGER.info("Repository content has been successfully reset to '" + ref + "'.");
			final Collection<String> deletedFiles = git.clean().setCleanDirectories(true).call();
			LOGGER.info("Cleaned up " + deletedFiles.size() + " files:\n" + Joiner.on(",\n").join(deletedFiles));
		} catch (final RepositoryNotFoundException e) {
			if (cloneIfMissing) {
				Throwables.propagate(e);
			} else {
				final String message = "Git repository does not exist at " + localClonePath
						+ ". Git repository should be cloned manually.";
				throw new RuntimeException(message, e);
			}
		} catch (final Exception e) {
			LOGGER.error("Error when trying to hard reset to HEAD on '" + branch + "' branch in " + localClonePath
					+ " repository.");
			Throwables.propagate(e);
		}
	}

	/**
	 * Sugar for {@link #hardReset(String, Path, String, boolean)} with multiple remote git URIs and local paths.
	 *
	 * @param remoteUris
	 *            the URI of the remote repository. Could be omitted if the {@code cloneIfMissing} is {@code false}.
	 * @param localClonePaths
	 *            the local path of the cloned repository.
	 * @param branch
	 *            the name of the branch to reset the {@code HEAD} pointer.
	 * @param cloneIfMissing
	 *            {@code true} if the repository has to be cloned in case if its absence.
	 */
	public static void hardReset(final Iterable<String> remoteUris, final Iterable<Path> localClonePaths,
			final String branch, final boolean cloneIfMissing) {

		checkNotNull(remoteUris, "remoteUris");
		checkNotNull(localClonePaths, "localClonePaths");
		checkArgument(size(remoteUris) == size(localClonePaths), "Remote URI - local clone path mismatch.");

		final Path[] paths = toArray(localClonePaths, Path.class);
		int i = 0;
		for (final String remoteUri : remoteUris) {
			hardReset(remoteUri, paths[i++], branch, cloneIfMissing);
		}

	}

	/**
	 * Performs a git {@code pull} in a local git repository given as repository root path argument.
	 */
	public static void pull(final Path localClonePath) {
		pull(localClonePath, null);
	}

	/**
	 * Sugar for {@link #pull(Path)} with progress monitor support. Performs a git {@code pull} in a local git
	 * repository. If the {@code monitor} argument is optional, hence it can be {@code null}.
	 */
	public static void pull(final Path localClonePath, final IProgressMonitor monitor) {

		@SuppressWarnings("restriction")
		final ProgressMonitor gitMonitor = null == monitor ? createMonitor()
				: new org.eclipse.egit.core.EclipseGitProgressTransformer(monitor);

		if (null == localClonePath) {
			LOGGER.warn("Local clone path should be specified for the git clone operation.");
			return;
		}

		final File localCloneRoot = localClonePath.toFile();
		if (!localCloneRoot.exists()) {
			LOGGER.warn("Local git repository clone root does not exist: " + localCloneRoot + ".");
			return;
		}

		if (!localCloneRoot.isDirectory()) {
			LOGGER.warn("Expecting a directory as the local git repository clone. Was a file: " + localCloneRoot + ".");
			return;
		}

		try (final Git git = open(localCloneRoot)) {
			try {
				git.pull().setProgressMonitor(gitMonitor).setTransportConfigCallback(TRANSPORT_CALLBACK).call();
			} catch (final GitAPIException e) {
				LOGGER.error("Error when trying to pull on repository  '" + localClonePath + ".");
				Throwables.propagate(e);
			}
		} catch (final IOException e) {
			LOGGER.warn("Git repository does not exists at " + localCloneRoot + ". Aborting git pull.");
			LOGGER.warn("Perform git clone first, then try to pull from remote.");
			if (LOGGER.isDebugEnabled()) {
				LOGGER.error("Error when trying to open repository  '" + localClonePath + ".");
			}
		}
	}

	/**
	 * Returns with the name of the master branch.
	 *
	 * @return the name of the master branch.
	 */
	public static String getMasterBranch() {
		return MASTER;
	}

	/**
	 * Clones a branch of a remote Git repository to the local file system.
	 *
	 * @param remoteUri
	 *            the remote Git repository URI as String.
	 * @param localClonePath
	 *            the local file system path.
	 * @param branch
	 *            the name of the branch to be cloned.
	 */
	private static void clone(final String remoteUri, final Path localClonePath, final String branch) {

		checkNotNull(remoteUri, "remoteUri");
		checkNotNull(localClonePath, "clonePath");
		checkNotNull(branch, "branch");

		final File destinationFolder = localClonePath.toFile();
		if (!destinationFolder.exists()) {
			try {
				createDirectories(localClonePath);
			} catch (final IOException e) {
				final String message = "Error while creating directory for local repository under " + localClonePath
						+ ".";
				LOGGER.error(message, e);
				throw new RuntimeException(message, e);
			}
			LOGGER.info("Creating folder for repository at '" + localClonePath + "'.");
		}
		checkState(destinationFolder.exists(),
				"Repository folder does not exist folder does not exist: " + destinationFolder.getAbsolutePath());

		final File[] existingFiles = destinationFolder.listFiles();
		if (!isEmpty(existingFiles)) {
			LOGGER.info("Repository already exists. Aborting clone phase. Files in " + destinationFolder + " are: "
					+ Joiner.on(',').join(existingFiles));
			try (final Git git = open(localClonePath.toFile())) {
				final String currentBranch = git.getRepository().getBranch();
				if (!currentBranch.equals(branch)) {
					LOGGER.info("Desired branch differs from the current one. Desired: '" + branch + "' current: '"
							+ currentBranch + "'.");
					git.pull().setProgressMonitor(createMonitor()).call();
					// check if the remote desired branch exists or not.
					final Ref remoteBranchRef = from(git.branchList().setListMode(REMOTE).call())
							.firstMatch(ref -> ref.getName().equals(R_REMOTES + "origin/" + branch)).orNull();
					// since repository might be cloned via --depth 1 (aka shallow clone) we cannot just switch to
					// any remote branch those ones do not exist. and we cannot run 'pull --unshallow' either.
					// we have to delete the repository content and run a full clone from scratch.
					if (null == remoteBranchRef) {
						LOGGER.info("Cleaning up current git clone and running clone phase from scratch.");
						deleteRecursively(destinationFolder);
						clone(remoteUri, localClonePath, currentBranch);
					} else {
						git.pull().setProgressMonitor(createMonitor()).call();
						LOGGER.info("Pulled from upstream.");
					}
				}
			} catch (final Exception e) {
				final String msg = "Error when performing git pull in " + localClonePath + " from " + remoteUri + ".";
				LOGGER.error(msg, e);
				throw new RuntimeException(
						"Error when performing git pull in " + localClonePath + " from " + remoteUri + ".", e);
			}
			return;
		}

		LOGGER.info("Cloning repository from '" + remoteUri + "'...");
		final CloneCommand cloneCommand = cloneRepository()
				.setURI(remoteUri)
				.setDirectory(destinationFolder)
				.setBranch(branch)
				.setProgressMonitor(createMonitor())
				.setTransportConfigCallback(TRANSPORT_CALLBACK);

		try (final Git git = cloneCommand.call()) {
			LOGGER.info(
					"Repository content has been successfully cloned to '" + git.getRepository().getDirectory() + "'.");
		} catch (final GitAPIException e) {
			final String message = "Error while cloning repository.";
			LOGGER.error(message, e);
			LOGGER.info("Trying to clean up local repository content: " + destinationFolder + ".");
			deleteRecursively(destinationFolder);
			LOGGER.info("Inconsistent checkout directory was successfully cleaned up.");
			throw new RuntimeException(message, e);
		}
	}

	private static TextProgressMonitor createMonitor() {
		return new TextProgressMonitor(new OutputStreamWriter(System.out));
	}

	/**
	 * Recursively cleans up the resource given as a file. If the file represents a directory, this method will be
	 * called with each files contained by the given argument.
	 *
	 * @param file
	 *            the resource to delete.
	 */
	private static void deleteRecursively(final File file) {
		try {
			FileDeleter.delete(file.toPath());
		} catch (IOException e) {
			throw new RuntimeException("Error while recursively cleaning up content of " + file + ".");
		}
	}

}
