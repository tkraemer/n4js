/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.ui.organize.imports

import eu.numberfour.n4js.n4JS.ImportDeclaration
import eu.numberfour.n4js.ui.changes.ChangeProvider
import eu.numberfour.n4js.ui.changes.IChange
import java.util.List
import org.eclipse.jface.text.BadLocationException
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.nodemodel.BidiTreeIterator
import org.eclipse.xtext.nodemodel.ILeafNode
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.ui.editor.model.IXtextDocument

import static org.eclipse.xtext.nodemodel.util.NodeModelUtils.*

import static extension eu.numberfour.n4js.ui.organize.imports.XtextResourceUtils.*

/**
 * Utility for analyzing potential cleanups that can be made to the import section of the {@link XtextResource}
 */
class ImportsCleanupChangesUtil {

	/**
	 * Compute all cleanup changes (removal of imports)
	 * 
	 * 
	 * @param xtextResource - the Resource to modify
	 * @param document - the document connected to the xtextResource,  for textual changes.
	 * @return list of changes to the document.
	 */
	public static def List<IChange> getCleanupChanges(XtextResource xtextResource,
		IXtextDocument document) throws BadLocationException {
		val changes = newArrayList
		val elements = xtextResource.getScript().scriptElements

//		elements.filter(ImportDeclaration).map[findActualNodeFor(it)].forEach[changes.add(document.removeNodeButKeepComments(it))]
		for (el : elements) {
			if (el instanceof ImportDeclaration) {
				val nodeToRemove = findActualNodeFor(el)
				changes.add(document.removeNodeButKeepComments(nodeToRemove))
			}
		}

		return changes
	}

	private static def IChange removeNodeButKeepComments(IXtextDocument doc,
		INode importNode) throws BadLocationException {
		if (importNode === null)
			return IChange.IDENTITY;
		// INode next = node.getNextSibling();
		val BidiTreeIterator<INode> reversed = importNode.getAsTreeIterable().iterator();
		var end = importNode.endOffset
		var done = false
		while (!done && reversed.hasPrevious()) {
			val INode prev = reversed.previous();
			if (prev instanceof ILeafNode) {
				if (!prev.isHidden()) {
					// must be semicolon
					if (!';'.equals(prev.text)) {
						val grammarElement = prev.grammarElement
						if (grammarElement instanceof RuleCall) {
							if ("STRING".equals(grammarElement.rule.name)) {
								done = true
							}
						}
						// but is comment or ASI
						while (!done && reversed.hasPrevious) {
							val sibling = reversed.previous
							if (sibling instanceof ILeafNode) {
								if (!sibling.isHidden) {
									done = true
									end = sibling.endOffset
								}
							}
						}
					}
				} else {
					while (!done && reversed.hasPrevious) {
						val sibling = reversed.previous
						if (sibling instanceof ILeafNode) {
							if (!sibling.isHidden) {
								done = true
								end = sibling.endOffset
							}
						}
					}
				}
			}
		}
		val offset = importNode.getOffset()
		return ChangeProvider.removeText(doc, offset, end - offset, true);
	}
}
