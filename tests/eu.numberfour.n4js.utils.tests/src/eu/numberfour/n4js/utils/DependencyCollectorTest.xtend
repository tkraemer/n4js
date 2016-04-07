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
package eu.numberfour.n4js.utils

import org.junit.Assert
import org.junit.Test

/**
 * Tests for the dependency collector.
 */
class DependencyCollectorTest {

	@Test
	def void checkNoDependencies() {
		val graph = #{
			1 -> #[]
		};
		new DependencyCollector(1, [graph.get(it)]).collectDependencies.assertEquals('');
	}
	
	@Test
	def void checkDirectDependencies() {
		val graph = #{
			1 -> #[2, 3]
		};
		new DependencyCollector(1, [graph.get(it)]).collectDependencies.assertEquals('2, 3');
	}
	
	@Test
	def void checkTransitiveDependencies() {
		val graph = #{
			1 -> #[2],
			2 -> #[3]
		};
		new DependencyCollector(1, [graph.get(it)]).collectDependencies.assertEquals('2, 3');
	}
	
	@Test(expected = NullPointerException)
	def void checkNullRootNode_ExpectNPE() {
		val graph = #{
			1 -> #[2],
			2 -> #[3]
		};
		new DependencyCollector(null as Integer, [graph.get(it)]).collectDependencies.assertEquals('1, 2, 3');
	}
	
	def private <T extends Comparable<T>> assertEquals(Iterable<? extends T> actual, String expected) {
		val actualToString = actual.toList.sort.join(', ');
		val msg = '''Expected: «expected», but got «actualToString» instead.''';
		Assert.assertEquals(msg, expected, actualToString);
	}
	
}