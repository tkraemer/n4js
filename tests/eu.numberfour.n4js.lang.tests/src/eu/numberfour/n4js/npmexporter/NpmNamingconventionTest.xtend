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
package eu.numberfour.n4js.npmexporter

import eu.numberfour.n4js.N4JSInjectorProviderWithMockProject
import javax.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProviderWithMockProject)
public class NpmNamingconventionTest {

	@Inject
	private NpmExporter npmExporter;

	@Test
	def void namePatterns() {
		assertTrue( "simple name", npmExporter.holdsConsistentArtifactID("abc", [] )  );
//		assertTrue( "prefixed name", npmExporter.holdsConsistentArtifactID("@x/abc")  ); // allow ?

		assertFalse( "No uppercase",npmExporter.holdsConsistentArtifactID("Abc",[] ) );
		assertFalse( "No uppercase", npmExporter.holdsConsistentArtifactID("abC",[] ) );

		assertFalse( "leading underscore",npmExporter.holdsConsistentArtifactID("_abc",[] ) );
		assertFalse( "leading dot", npmExporter.holdsConsistentArtifactID(".abc",[] ) );

		assertFalse( "funny german letter",npmExporter.holdsConsistentArtifactID("abßc",[] ) );
		assertFalse( "question mark upside-down",npmExporter.holdsConsistentArtifactID("ab¿c",[] ) );

		assertFalse( "question mark", npmExporter.holdsConsistentArtifactID("ab?c",[] ) );
//		assertFalse( "asterix",npmExporter.holdsConsistentArtifactID("ab*c",[] ) ); // allow ?
		assertFalse( "slash",npmExporter.holdsConsistentArtifactID("ab/c",[] ) );

		assertFalse( "tab",npmExporter.holdsConsistentArtifactID("ab\tc",[] ) );
		assertFalse( "new line",npmExporter.holdsConsistentArtifactID("ab\nc",[] ) );

		assertFalse( "hash mark",npmExporter.holdsConsistentArtifactID("ab#c",[] ) );

		assertTrue( npmExporter.holdsConsistentArtifactID("a23456789_",[] ) );

		assertTrue( "length of 124 char",npmExporter.holdsConsistentArtifactID(
			"a23456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_"+ // 10x10
			"123456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_"+ // 10x10
			"123456789_1234"
		,[] ) );

		assertFalse( "length of 125 char",npmExporter.holdsConsistentArtifactID(
			"a23456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_"+ // 10x10
			"123456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_123456789_"+ // 10x10
			"123456789_12345"
		,[] ) );

	}

}
