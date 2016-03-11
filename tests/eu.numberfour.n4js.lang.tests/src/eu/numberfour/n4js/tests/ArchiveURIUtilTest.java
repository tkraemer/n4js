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
package eu.numberfour.n4js.tests;

import java.util.zip.ZipEntry;

import org.eclipse.emf.common.util.URI;
import org.junit.Assert;
import org.junit.Test;

import eu.numberfour.n4js.ArchiveURIUtil;
import eu.numberfour.n4js.projectModel.IN4JSProject;

/**
 */
public class ArchiveURIUtilTest {

	@SuppressWarnings("javadoc")
	@Test
	public void testArchiveURIForSingleSegment() {
		URI base = URI.createURI("file:/a/b/c.nfar");
		URI manifestURI = ArchiveURIUtil.createURI(base, IN4JSProject.N4MF_MANIFEST);
		Assert.assertEquals("archive:file:/a/b/c.nfar!/manifest.n4mf", manifestURI.toString());
	}

	@SuppressWarnings("javadoc")
	@Test
	public void testArchiveURIForZipEntry() {
		ZipEntry entry = new ZipEntry("a/b/c.js");
		URI base = URI.createURI("file:/a/b/c.nfar");
		URI manifestURI = ArchiveURIUtil.createURI(base, entry);
		Assert.assertEquals("archive:file:/a/b/c.nfar!/a/b/c.js", manifestURI.toString());
	}
}
