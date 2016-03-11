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
package eu.numberfour.n4js.tests.outline

import eu.numberfour.n4js.dirtystate.testdata.TestFiles
import org.eclipse.xtext.ui.editor.outline.IOutlineNode
import org.eclipse.xtext.ui.editor.outline.IOutlineTreeProvider
import org.junit.Test

/**
 */
class N4JSOutlineWorkbenchPluginUITest extends AbstractOutlineWorkbenchTest {

	override protected shouldCreateProjectStructure() {
		true
	}

	override protected getFileName() {
		"Child"
	}

	override protected getModelAsText() {
		String.valueOf(TestFiles.classChild)
	}

	override protected getModuleFolder() {
		TestFiles.moduleFolder
	}

	override protected getProjectName() {
		"SimpleOutlineTest"
	}

	@Test
	def void testOutlineContribution() {
		editor.editorSite.page.activate(editor)
		assertEquals("First opened editor doesn't became active", editor, editor.editorSite.page.activeEditor)
		treeViewer.expandAll
		assertEquals("Initially the tree view should contain one item.", 1,
			treeViewer.tree.getItem(0).itemCount)
		treeViewer.expandAll
		assertEquals("Filter action does not work.", 1, treeViewer.tree.getItem(0).itemCount)
	}

	@Test
	def void testNoNPEs() throws Exception {
		assertNoException("xyz")
		assertNoException("a = ;")
	}

	@Test
	def void testClassWithTypedField() throws Exception {
		val model = '''
				class A {
					field1 : string;
				}
			'''
		val rootNode = assertNoException(model)
		assertEquals(1, rootNode.children.size)
		val IOutlineNode moduleNode = rootNode.children.get(0)
		assertNode(moduleNode, "pr0_0pa0/Child", 1)
		val classNode = moduleNode.children.get(0)
		assertNode(classNode, "A", 1)
		val fieldNode = classNode.children.get(0)
		assertNode(fieldNode, "field1 : string", 0)
		assertEquals(model.lastIndexOf("field1 : string"), fieldNode.getFullTextRegion.getOffset)
		assertEquals("field1 : string;".length, fieldNode.getFullTextRegion.getLength)
		assertEquals(model.lastIndexOf("field1"), fieldNode.getSignificantTextRegion.getOffset)
		assertEquals("field1".length, fieldNode.getSignificantTextRegion.getLength)
	}

	protected def void assertNode(IOutlineNode node, String text, int numChildren) {
		assertEquals(numChildren, node.getChildren.size)
		assertEquals(text, node.getText.toString)
	}

	protected def IOutlineNode assertNoException(String model) throws Exception {
		return try {
			val document = editor.document => [ set(model) ]
			val treeProvider = getInstance(IOutlineTreeProvider)
			treeProvider.createRoot(document) => [ traverseChildren ]
		} catch (Exception exc) {
			exc.printStackTrace
			fail("Exception in outline tree construction")
			return null // unreachable
		}
	}

	private def void traverseChildren(IOutlineNode node) {
		node.children.forEach[traverseChildren]
	}
}
