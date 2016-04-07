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
package eu.numberfour.n4js.utils;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.newHashSet;
import static com.google.common.collect.Sets.newLinkedHashSet;

import java.util.Collection;
import java.util.Stack;

import org.eclipse.xtext.xbase.lib.Functions.Function1;

import com.google.common.base.Equivalence;
import com.google.common.base.Equivalence.Wrapper;
import com.google.common.base.Function;

/**
 * Class for collecting all transitive dependencies in a recursive fashion.
 */
public class DependencyCollector<T> {

	private T rootNode;
	private Function<T, Collection<? extends T>> dependenciesFunc;
	private Equivalence<T> equivalence;

	/**
	 * Creates a dependency collector with the given root node argument and a function that will be used to traverse the
	 * dependencies of the individual nodes. During the dependency traversal, {@link Equivalence#identity() object
	 * identity} will be used to check whether a node has been visited or not.
	 *
	 * @param rootNode
	 *            the entry point of the dependency graph.
	 * @param dependenciesFunc
	 *            function for traversing the dependency graph.
	 */
	public DependencyCollector(final T rootNode, final Function1<T, Collection<? extends T>> dependenciesFunc) {
		this(rootNode, new FunctionDelegate<>(dependenciesFunc));
	}

	/**
	 * Sugar for {@link #DependencyCollector(Object, Function1)} with Guava {@link Function function} support.
	 *
	 * @param rootNode
	 *            the graph entry point.
	 * @param dependenciesFunc
	 *            function for traversing the dependencies.
	 */
	@SuppressWarnings("unchecked")
	public DependencyCollector(final T rootNode, final Function<T, Collection<? extends T>> dependenciesFunc) {
		this(rootNode, (Equivalence<T>) Equivalence.identity(), dependenciesFunc);
	}

	/**
	 * Creates a dependency collector instance for gathering all dependencies of a graph node. All transitive
	 * dependencies will be collected in a recursive fashion.
	 *
	 * @param rootNode
	 *            the root node.
	 * @param equivalence
	 *            an equivalence instance to determine whether a node has been visited or not.
	 * @param dependenciesFunc
	 *            function for getting all direct dependencies of a given graph node.
	 */
	public DependencyCollector(final T rootNode, final Equivalence<T> equivalence,
			final Function<T, Collection<? extends T>> dependenciesFunc) {

		this.rootNode = checkNotNull(rootNode, "rootNode");
		this.equivalence = checkNotNull(equivalence, "equivalence");
		this.dependenciesFunc = checkNotNull(dependenciesFunc, "dependenciesFunc");
	}

	/**
	 * Collects and returns with all (transitive) dependencies of the root node. This method is an eager operation,
	 * hence the dependencies will be calculated each time this method is called.
	 *
	 * @return an iterable of all (transitive) dependencies.
	 */
	public Iterable<T> collectDependencies() {

		final Collection<T> dependencies = newLinkedHashSet();
		final Collection<Wrapper<T>> visitedNodes = newHashSet();
		final Stack<T> nodeStack = new Stack<>();
		nodeStack.push(rootNode);

		while (!nodeStack.isEmpty()) {
			final T currentNode = nodeStack.pop();
			if (visitedNodes.add(equivalence.wrap(currentNode))) {
				final Collection<? extends T> directDependencies = dependenciesFunc.apply(currentNode);
				if (null != directDependencies) {
					dependencies.addAll(directDependencies);
					for (final T directDependency : directDependencies) {
						nodeStack.push(directDependency);
					}
				}
			}
		}

		return dependencies;
	}

}
