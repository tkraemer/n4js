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
package eu.numberfour.n4js.documentation;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.documentation.impl.MultiLineCommentDocumentationProvider;
import org.eclipse.xtext.nodemodel.INode;

import eu.numberfour.n4js.n4JS.ExportDeclaration;
import eu.numberfour.n4js.n4JS.ExportableElement;
import eu.numberfour.n4js.n4JS.ExportedVariableDeclaration;
import eu.numberfour.n4js.n4JS.ExportedVariableStatement;
import eu.numberfour.n4js.n4JS.N4JSASTUtils;
import eu.numberfour.n4js.n4JS.VariableDeclaration;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;

/**
 */
public class N4JSDocumentationProvider extends MultiLineCommentDocumentationProvider {

	/**
	 * Returns documentation nodes for N4JS AST and type elements (in which case the method returns the nodes of the
	 * corresponding ast element). If the AST element has no documentation nodes itself, but is an exportable element,
	 * then the documentation of the export statement is returned if present.
	 */
	@Override
	public List<INode> getDocumentationNodes(EObject object) {
		final EObject astNode = N4JSASTUtils.getCorrespondingASTNode(object);
		if (astNode != null) {
			List<INode> nodes = super.getDocumentationNodes(astNode);
			if (nodes.isEmpty() && astNode instanceof VariableDeclaration) {
				TypeRef typeRef = ((VariableDeclaration) astNode).getDeclaredTypeRef();
				if (typeRef != null) {
					nodes = getDocumentationNodes(typeRef);
				}
			}
			if (nodes.isEmpty()) {
				if (astNode instanceof ExportedVariableDeclaration
						&& astNode.eContainer() instanceof ExportedVariableStatement) {
					EList<VariableDeclaration> decls = ((ExportedVariableStatement) astNode.eContainer()).getVarDecl();
					if (decls.size() == 1) {
						return getDocumentationNodes(astNode.eContainer());
					}
				}
				if (astNode instanceof ExportableElement && astNode.eContainer() instanceof ExportDeclaration) {
					nodes = super.getDocumentationNodes(astNode.eContainer());
				}
			}
			return nodes;
		}
		return super.getDocumentationNodes(object);
	}
}
