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
package eu.numberfour.n4js.ui.changes

import java.util.List
import eu.numberfour.n4js.n4mf.ProjectDescription
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.nodemodel.INode
import eu.numberfour.n4js.n4mf.SourceFragment
import java.util.ArrayList
import eu.numberfour.n4js.n4mf.SourceFragmentType
import org.eclipse.emf.ecore.resource.Resource
import java.util.Collection

/**
 * This class provides basic change functionality for N4JS manifest files.
 */
class ManifestChangeProvider {
	public static def IAtomicChange addSourceFoldersToManifest(Resource manifestResource, List<String> sourceFolders) {
		val manifestSourceFolders = new ArrayList<String>(sourceFolders);

		val sourceFragment = manifestResource.allContents.filter(SourceFragment).filter[ fragment | fragment.sourceFragmentType == SourceFragmentType.SOURCE].head;
		manifestSourceFolders.addAll(sourceFragment.paths);

		val sourceFragmentNode = NodeModelUtils.findActualNodeFor(sourceFragment);

		val newSourceFolderFragment =
		'''
		source {
		«FOR path : manifestSourceFolders SEPARATOR ','»
		«"\t\t"»"«path»"
		«ENDFOR»
			}
		''';

		return new Replacement(manifestResource.URI, sourceFragmentNode.offset, sourceFragmentNode.length, newSourceFolderFragment);
	}

	/**
	 *  Insert given project dependency into manifest file
	 *
	 * @param resourceUri The URI of the manifest resource
	 * @param dependencies List of project identifiers
	 * @param description The ProjectDescription AST element of the manifest to modify.
	 */
	public static def IAtomicChange insertProjectDependencies(Resource manifestResource, Collection<String> dependencies, ProjectDescription description) {
		var offset = -1;
		var length = 0;
		var withFrame = false;

		// Remove all dependencies, that are already part of the list
		if (description.projectDependencies !== null) {
			dependencies.removeIf [ description.projectDependencies.projectDependencies.map[project.artifactId].contains(it) ]
		}

		if (dependencies.length < 1) {
			return null;
		}


		var StringBuilder textToInsert = new StringBuilder();
		if (description.projectDependencies === null) { //If no dependency list (frame), create one.
			textToInsert.append("\n"+"ProjectDependencies {");
			val INode globalDescriptionNode = NodeModelUtils.findActualNodeFor(description);
			offset = globalDescriptionNode.offset + globalDescriptionNode.length;
			withFrame = true;
		} else if (description.projectDependencies.projectDependencies.length > 0) { //If existing dependency list, append after the last one
			val INode lastDep = NodeModelUtils.findActualNodeFor(description.projectDependencies.projectDependencies.last);
			offset = lastDep.offset+lastDep.length;
			textToInsert.append(",");
		} else { //If empty dependency list, replace the whole empty block
			textToInsert.append("ProjectDependencies {");
			withFrame = true;
			val INode depsNode = NodeModelUtils.findActualNodeFor(description.projectDependencies);
			offset = depsNode.offset;
			length = depsNode.length;
		}

		textToInsert.append('''«FOR dep : dependencies SEPARATOR ","»«"\n\t"+dep»«ENDFOR»''')

		if (withFrame) {
			textToInsert.append("\n}");
		}

		new Replacement(manifestResource.URI, offset, length, textToInsert.toString);
	}
}
