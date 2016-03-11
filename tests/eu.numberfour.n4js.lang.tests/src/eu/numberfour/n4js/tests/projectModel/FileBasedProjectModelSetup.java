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
package eu.numberfour.n4js.tests.projectModel;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.emf.common.util.URI;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.google.common.io.OutputSupplier;

import eu.numberfour.n4js.internal.FileBasedWorkspace;
import eu.numberfour.n4js.projectModel.IN4JSProject;

/**
 */
public class FileBasedProjectModelSetup extends AbstractProjectModelSetup {

	private File workspaceRoot;
	private final FileBasedWorkspace workspace;

	/***/
	protected FileBasedProjectModelSetup(AbstractProjectModelTest host, FileBasedWorkspace workspace) {
		super(host);
		this.workspace = workspace;
	}

	@Override
	protected void deleteTempProjects() {
		try {
			deleteRecursively(workspaceRoot);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	/*
	 * deprecated in guava but save to use here
	 */
	private void deleteDirectoryContents(File directory) throws IOException {
		File[] files = directory.listFiles();
		if (files == null) {
			throw new IOException("Error listing files for " + directory);
		}
		for (File file : files) {
			deleteRecursively(file);
		}
	}

	/*
	 * deprecated in guava but save to use here
	 */
	private void deleteRecursively(File file) throws IOException {
		if (file.isDirectory()) {
			deleteDirectoryContents(file);
		}
		if (!file.delete()) {
			throw new IOException("Failed to delete " + file);
		}
	}

	@Override
	protected void createTempProjects() {
		try {
			workspaceRoot = Files.createTempDir();
			URI myProjectURI = createTempProject(host.myProjectName);
			host.setMyProjectURI(myProjectURI);
			createManifest(myProjectURI, "ArtifactId: " + host.myProjectName + "\n" +
					"VendorId: eu.numberfour\n" +
					"ProjectName: \"N4JS Tests\"\n" +
					"VendorName: \"NumberFour AG\"\n" +
					"ProjectType: lib\n" +
					"ProjectVersion: 0.0.1-SNAPSHOT\n" +
					"Libraries { \"" + LIB_FOLDER_NAME + "\"\n }\n" +
					"Output: \"src-gen\"\n" +
					"Sources {\n" +
					"	source {" +
					"		\"src\"\n" +
					"	}\n" +
					"}" +
					"ProjectDependencies { " + host.libProjectName + ", " + host.archiveProjectName + " }\n");
			createArchive(myProjectURI);
			URI libProjectURI = createTempProject(host.libProjectName);
			host.setLibProjectURI(libProjectURI);
			createManifest(libProjectURI, "ArtifactId: " + host.libProjectName + "\n" +
					"VendorId: eu.numberfour\n" +
					"ProjectName: \"N4JS Tests\"\n" +
					"VendorName: \"NumberFour AG\"\n" +
					"ProjectType: lib\n" +
					"ProjectVersion: 0.0.1-SNAPSHOT\n" +
					"Libraries { \"" + LIB_FOLDER_NAME + "\"\n }\n" +
					"Output: \"src-gen\"\n" +
					"Sources {\n" +
					"	source {" +
					"		\"src\"\n" +
					"	}\n" +
					"}");
			workspace.registerProject(myProjectURI);
			workspace.registerProject(libProjectURI);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void createArchive(URI baseDir) throws IOException {
		File directory = new File(java.net.URI.create(baseDir.toString()));
		File lib = new File(directory, "lib");
		assertTrue(lib.mkdir());
		File nfar = new File(lib, host.archiveProjectName + ".nfar");
		final ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(nfar));
		zipOutputStream.putNextEntry(new ZipEntry("src/A.js"));
		zipOutputStream.putNextEntry(new ZipEntry("src/B.js"));
		zipOutputStream.putNextEntry(new ZipEntry("src/sub/B.js"));
		zipOutputStream.putNextEntry(new ZipEntry("src/sub/C.js"));
		zipOutputStream.putNextEntry(new ZipEntry("src/sub/leaf/D.js"));

		zipOutputStream.putNextEntry(new ZipEntry(IN4JSProject.N4MF_MANIFEST));
		// this will close the stream
		CharStreams.write("ArtifactId: " + host.archiveProjectName + "\n" +
				"VendorId: eu.numberfour\n" +
				"ProjectName: \"N4JS Tests\"\n" +
				"VendorName: \"NumberFour AG\"\n" +
				"ProjectType: lib\n" +
				"ProjectVersion: 0.0.1-SNAPSHOT\n" +
				"Output: \"src-gen\"\n" +
				"Sources {\n" +
				"	source {" +
				"		\"src\"\n" +
				"	}\n" +
				"}",
				CharStreams.newWriterSupplier(new OutputSupplier<ZipOutputStream>() {
					@Override
					public ZipOutputStream getOutput() throws IOException {
						return zipOutputStream;
					}
				}, Charsets.UTF_8));
		host.setArchiveFileURI(URI.createURI(nfar.toURI().toString()));
	}

	private void createManifest(URI projectDir, String string) throws IOException {
		File directory = new File(java.net.URI.create(projectDir.toString()));
		Files.write(string, new File(directory, IN4JSProject.N4MF_MANIFEST), Charsets.UTF_8);
		File src = new File(directory, "src");
		assertTrue(src.mkdir());
		File sub = new File(src, "sub");
		assertTrue(sub.mkdir());
		File leaf = new File(sub, "leaf");
		assertTrue(leaf.mkdir());
		assertTrue(new File(src, "A.js").createNewFile());
		assertTrue(new File(src, "B.js").createNewFile());
		assertTrue(new File(sub, "B.js").createNewFile());
		assertTrue(new File(sub, "C.js").createNewFile());
		assertTrue(new File(leaf, "D.js").createNewFile());
	}

	/***/
	protected URI createTempProject(String projectName) {
		File myProjectDir = new File(workspaceRoot, projectName);
		if (!myProjectDir.mkdir()) {
			throw new RuntimeException();
		}
		return URI.createURI(myProjectDir.toURI().toString()).trimSegments(1);
	}

}
