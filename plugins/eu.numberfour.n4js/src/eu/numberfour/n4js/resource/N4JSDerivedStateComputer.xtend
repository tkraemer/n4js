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
package eu.numberfour.n4js.resource;

import com.google.inject.Inject
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.types.TModule
import eu.numberfour.n4js.typesbuilder.N4JSTypesBuilder
import eu.numberfour.n4js.utils.Log
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.resource.DerivedStateAwareResource
import org.eclipse.xtext.resource.IDerivedStateComputer

/**
 * Derives the types model from the AST and stores it at the second index of the resource.
 * See {@link N4JSTypesBuilder}.
 */
@Log
public class N4JSDerivedStateComputer implements IDerivedStateComputer {

	@Inject extension N4JSUnloader
	@Inject private N4JSPreProcessor preProcessor;
	@Inject private N4JSTypesBuilder typesBuilder;


	/**
	 * Creates an {@link TModule} on the second slot of the resource. when the resource contents is not empty.
	 */
	override installDerivedState(DerivedStateAwareResource resource, boolean preLinkingPhase) {
		if (resource.contents.nullOrEmpty) {
			throw new IllegalStateException
		} else if (! resource.contents.empty) {
			performPreProcessing(resource, preLinkingPhase);
			typesBuilder.createTModuleFromSource(resource, preLinkingPhase);
		}
	}

	def private void performPreProcessing(Resource resource, boolean preLinkingPhase) {
		if (resource instanceof N4JSResource) {
			val builtInTypes = BuiltInTypeScope.get(resource.resourceSet);
			for (node : resource.script.eAllContents.toIterable) {
				preProcessor.process(node, resource, builtInTypes);
			}
		}
	}

	/**
	 * Calls {@link N4JSUnloader#unloadRoot(EObject} for the second slot root.
	 * Then all contents of the resource are cleared.
	 */
	override void discardDerivedState(DerivedStateAwareResource resource) {

		// resource.getContents().get(1-n)clear
		if (resource.contents.empty) {
			return;
		}

		// other resources may hold references to the derived state thus we
		// have to unload (proxify) it explicitly before it is removed from the resource
		val resourcesContentsList = resource.contents.subList(1, resource.contents.size)
		resourcesContentsList.forEach[unloadRoot]
		resourcesContentsList.clear
	}
}
