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
package eu.numberfour.n4js.ui.organize.imports;

import com.google.common.collect.LinkedHashMultimap
import com.google.common.collect.Multimap
import com.google.inject.Inject
import eu.numberfour.n4js.documentation.N4JSDocumentationProvider
import eu.numberfour.n4js.n4JS.ExpressionStatement
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.ImportDeclaration
import eu.numberfour.n4js.n4JS.ImportSpecifier
import eu.numberfour.n4js.n4JS.N4JSFactory
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.NamedImportSpecifier
import eu.numberfour.n4js.n4JS.NamespaceImportSpecifier
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.ScriptElement
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.organize.imports.ImportStateCalculator
import eu.numberfour.n4js.scoping.N4JSScopeProvider
import eu.numberfour.n4js.ts.services.TypeExpressionsGrammarAccess
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.types.TypesFactory
import eu.numberfour.n4js.ui.changes.ChangeProvider
import eu.numberfour.n4js.ui.changes.IChange
import eu.numberfour.n4js.ui.contentassist.N4JSCandidateFilter
import eu.numberfour.n4js.utils.UtilN4
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator
import java.util.List
import java.util.Set
import org.eclipse.emf.common.notify.Adapter
import org.eclipse.emf.common.notify.impl.AdapterImpl
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.jface.text.BadLocationException
import org.eclipse.jface.viewers.ILabelProvider
import org.eclipse.jface.window.Window
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.TerminalRule
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.nodemodel.BidiTreeIterator
import org.eclipse.xtext.nodemodel.ILeafNode
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.ui.editor.model.IXtextDocument

import static eu.numberfour.n4js.parser.InternalSemicolonInjectingParser.SEMICOLON_INSERTED

import static extension eu.numberfour.n4js.organize.imports.RefNameUtil.*
import static extension org.eclipse.xtext.nodemodel.util.NodeModelUtils.*
import static extension eu.numberfour.n4js.n4JS.N4JSASTUtils.*
import org.eclipse.xtext.nodemodel.impl.LeafNode
import eu.numberfour.n4js.parser.InternalSemicolonInjectingParser

/**
 */
public class N4JSOrganizeImports {

	@Inject
	private ImportStateCalculator importStateCalculator;

	@Inject
	private N4JSScopeProvider scopeProvider;

	@Inject
	private ImportProvidedElementLabelprovider importProvidedElementLabelprovider;

	// Adapter used to mark programmatically created AST-Elements without a corresponding parse tree node.
	private final Adapter nodelessMarker = new AdapterImpl();
	
	@Inject 
	private N4JSDocumentationProvider documentationProvider;
	
	@Inject
	private TypeExpressionsGrammarAccess typeExpressionGrammmarAccess;
	
	/**
	 * Calculate destination region in Document for imports. If the offset is not 0,
	 * then it has to be advanced by current line feed length
	 *
	 * Using first position after script-annotation ("@@") and after any directive in the prolog section, that is 
	 * just before the first statement or, in cases where the first statement is js-style documented, before the jsdoc-style 
	 * documentation.
	 * 
	 * Examples:
	 * <pre>
	 *  // (A)
	 *  // (B)
	 *  &#64;&#64;StaticPolyfillAware
	 *  // (C)
	 *  "use strict";
	 *  // (D)
	 *  /&#42; non-jsdoc comment (E) &#42;/
	 *  /&#42;&#42; jsdoc comment (F) &#42;/
	 *  /&#42; non-jsdoc comment (G) &#42;/
	 *  // (H)
	 *  export public class A { // (I)
	 *     method(): B { 
	 *        return new B(); // requires import
	 *    } 
	 *  } 
	 * </pre>
	 * Will put the insertion-point in front of line (F), since this is the active jsdoc for class A. 
	 * {@link InsertionPoint#isBeforeJsdocDocumentation} will be set to true. Lowest possible insertion 
	 * is the begin of line (D), stored in {@link InsertionPoint#notBeforeTotalOffset}. If the directive <code>"use strict";</code> 
	 * between lines (C) and (D) is omitted, then the lowest insertion point would be in front of line (C). In any case the insertion of 
	 * the import must be in front of the <code>export</code> keyword line (I).
	 * 
	 * <p>Region has length 0.
	 *
	 * @param xtextResource
	 *            n4js resource
	 * @return region for import statements, length 0
	 */
	public def InsertionPoint getImportRegion(XtextResource xtextResource) {

		// In N4js imports can appear anywhere in the Script as top-level elements. So even as a last
		// statement and more importantly scattered around.
		val InsertionPoint insertionPoint = new InsertionPoint;

		// First Position
		var int begin = -1;

		val Script script = getScript(xtextResource);
		if (script !== null) {
			// if there is a script, we can insert in first position.
			begin = 0;
			val List<INode> scriptAnnos = findNodesForFeature(script, N4JSPackage.Literals.SCRIPT__ANNOTATIONS);
			if (!scriptAnnos.isEmpty()) {
				val INode lastAnno = scriptAnnos.get(scriptAnnos.size() - 1);
				begin = lastAnno.getTotalEndOffset();
				insertionPoint.notBeforeTotalOffset = begin;
			// advance over line feed, since newlines are in the hidden token
			// channel of the following element
			// ChangeManager has information of current line feed length.
			}
	
			// Searching for directives (string-statements at the beginning, e.g. "use strict") 
			// and the first real statement, ignoring imports:
			val elements = script.scriptElements;
			var lastSeenDirective = -1;
			var idxNondirectiveStatemnt = -1; 
			for( var i=0; i< elements.size && idxNondirectiveStatemnt === -1; i++ ) {
				val curr = elements.get(i)					
				if( curr.isStringLiteralExpression ) {
					lastSeenDirective = i;
				} else if( curr instanceof ImportDeclaration ){
					// ignore import declarations, will be removed anyway.
				} else	{
					idxNondirectiveStatemnt = i;
				}
			}			
			
			
			// Conditionally calculate begin-position
			if( idxNondirectiveStatemnt !== -1 ) {
				// Standard case, a normal statement encountered.
				
				// get documentation of that statement:
				val realScriptElement = elements.get(idxNondirectiveStatemnt);
				val realScriptElementNode = findActualNodeFor( realScriptElement );
				
				// find doc, looks for nearest.
				val docuNodes = documentationProvider.getDocumentationNodes(realScriptElement);
				
				if( !docuNodes.isEmpty ) {
					// docu found
					begin = docuNodes.get(0).totalOffset;
					insertionPoint.isBeforeJsdocDocumentation = true;
					
				} else {
					// no documentation, go before the statement:
					var listLeafNodes = realScriptElementNode.leafNodes.toList;
					
					// looking for a position right after the last comment and before (WS+EOL)* NOT_HIDDEN_LEAF
					{
						val iterLeaves = listLeafNodes.iterator;
						var ILeafNode curr; 
						var ILeafNode firstEOL;
						var ILeafNode afterFirstEOL;
						var ILeafNode lastNode;
						var boolean sawComment = false;
						// got to first non-hidden:
						while( iterLeaves.hasNext && ( (curr = iterLeaves.next).isHidden) ) {  
							if( curr.grammarElement instanceof TerminalRule ){
								if( curr.grammarElement == typeExpressionGrammmarAccess.ML_COMMENTRule )
								{
									// reset EOLs
									firstEOL = null; afterFirstEOL = null; sawComment = true;
								} else if( 	curr.grammarElement == typeExpressionGrammmarAccess.SL_COMMENTRule 	) {
									// reset EOLs
									firstEOL = null;afterFirstEOL = null; sawComment = true;
								} else if( 	curr.grammarElement == typeExpressionGrammmarAccess.EOLRule 	) {
									if( firstEOL === null ) {
										// store 
										firstEOL = curr; 
									} else if( afterFirstEOL === null ) {
										// store insertion point.
										afterFirstEOL = curr;
									} 
								} else if( 	curr.grammarElement == typeExpressionGrammmarAccess.WSRule 	) {
									// keep going
									if( firstEOL !== null && afterFirstEOL === null ) afterFirstEOL = curr;
								} else 
								{
									firstEOL = null; afterFirstEOL = null;
								}
							}
							lastNode = curr;
						}
						if( curr === null || curr.isHidden ) {
							// Something is wrong here.
							throw new RuntimeException("Expected at least one non-hidden element.");
						}
						
						insertionPoint.notAfterTotalOffset = curr.totalOffset;
						
						var begin2 = if( afterFirstEOL !== null && sawComment ) {
							afterFirstEOL.totalOffset;
						} else {
							if( hasNoCommentUpTo(curr) ) {
								0; // all the imports before will be removed, so put at the beginning.
							} else {
								// make the comments above the import.
								curr.totalOffset;
							}
						}
						begin = Math.max(begin,begin2);
					}
					
					if( lastSeenDirective > -1 ) {
						// have directive, so insert not before last directive.
						val lastDirectiveNode = elements.get(lastSeenDirective).findActualNodeFor;
						val lastDirectiveEndOffset = lastDirectiveNode.totalEndOffset;
						// update not before,
						insertionPoint.notBeforeTotalOffset = Math.max( lastDirectiveEndOffset, insertionPoint.notBeforeTotalOffset );
						// update begin
						begin = Math.max( begin, lastDirectiveEndOffset ); 
					}
					
				}
				
				
			} else {
				// no normal statement encountered.
				if( lastSeenDirective > -1 ) {
					// have directive, so insert after last directive.
					val lastDirectiveNode = elements.get(lastSeenDirective).findActualNodeFor;
					
					// update begin:
					begin = lastDirectiveNode.totalEndOffset
					insertionPoint.notBeforeTotalOffset = Math.max( begin, insertionPoint.notBeforeTotalOffset );
					 
				} else {
					// no directive
					// --> just leave begin as is, after the "@@"
				}
			}

			// create new insertion point.
			insertionPoint.offset = begin;
		}

		return insertionPoint;
	}
	
	/** Goes from the beginning of the RootNode up to the passed in node. Looks only at hidden leafs and at ASI-LeafNodes. 
	 * @return {@code false} if any comment is encountered on the way.
	 */
	private def boolean hasNoCommentUpTo(ILeafNode node) {
		if( node === null ) return true;
		val iter = node.rootNode.asTreeIterable.iterator
		while( iter.hasNext )
		{
			val curr = iter.next;
			// exit case:
			if( curr == node ) return true;
			
			if( curr instanceof LeafNode ) {
				if( curr.isHidden || UtilN4.isIgnoredSyntaxErrorNode(curr,  InternalSemicolonInjectingParser.SEMICOLON_INSERTED ) ) {
					// hidden OR ASI
					if( ! curr.text.trim.isEmpty ) {
						// token-text contains not only whitespace --> there must be a comment.
						return false;
					}
				}
			}
		}
		// should never be reached. 
		throw new IllegalStateException("Iteration over-stepped the passed in node.");
	}
	
	


	/**
	 * @param xtextResource
	 *            the resource to process.
	 * @return Script instance or null
	 */
	private def Script getScript(XtextResource xtextResource) {
		if (!xtextResource.getContents().isEmpty()) {
			val EObject eo = xtextResource.getContents().get(0);
			if (eo instanceof Script) {
				return eo;
			}
		}
		return null;
	}

	/**
	 * Calculate the real content of the new import-section in the file header
	 *
	 * @param xtextResource
	 *            to organize
	 * @param lineEnding - current active line-Ending in file
	 * @param interaction Mode how to handle ambiguous situations
	 * @return new import section, might be an empty String.
	 */
	public def String getOrganizedImportSection(XtextResource xtextResource, String lineEnding,
		Interaction interaction) throws BreakException {

		val StringBuilder sb = new StringBuilder();
		val Script script = getScript(xtextResource);

		// Use original import list as base for result of organizing
		val resultingImports = script.getScriptElements().filter(ImportDeclaration).toList

		// use validation algorithm to check for problems.
		val reg = importStateCalculator.calculateImportstate(script);

		// Strict&Naive : remove all problems
		reg.removeDuplicatedImportDeclarations(resultingImports)
		reg.removeLocalNameCollisions(resultingImports, nodelessMarker)
		reg.removeDuplicatedImportsOfSameelement(resultingImports, nodelessMarker)
		reg.removeBrokenImports(resultingImports, nodelessMarker)
		reg.removeUnusedImports(resultingImports, nodelessMarker)

		//collect names for which we have removed imports
		val brokenNames = reg.calcRemovedImportedNames()

//		// determine things to import (unresolved imports and things we broke)
		val additional = resolveMissingImports(script, brokenNames, interaction)
		resultingImports += additional

		// resolve all resulting.
		resultingImports.forEach[EcoreUtil.resolveAll(it)]

		// Sort all import
		resultingImports.sortByImport

		// Add to output.
		resultingImports.forEach [
			val text = extractPureText;
			sb.append(text).append(lineEnding);
		]

		// remove last linefeed:
		val length = sb.length
		if (length > lineEnding.length) {

			// ret.deleteCharAt(length-1)
			sb.delete(length - lineEnding.length, length)
		}

		return sb.toString();
	}

	/** Calculate new Imports. */
	private def ArrayList<ImportDeclaration> resolveMissingImports(Script script, Set<String> namesThatWeBroke, Interaction interaction) throws BreakException {

		// val scope = scopeProvider.getScope(script, N4JSPackage.Literals.PARAMETERIZED_CALL_EXPRESSION__TARGET)
		val scopeTypeRef = scopeProvider.getScope(script,
			TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE)
		val scopeIdRef = scopeProvider.getScope(script, N4JSPackage.Literals.IDENTIFIER_REF__ID)

		// the following are named imports, that did not resolve. The issue lies in the Project-configuration and
		// cannot be solved here. Candidate for quickfix.
		// val unresolved = state.unresolved
		val unres = EcoreUtil.UnresolvedProxyCrossReferencer.find(script)

		val resolution = LinkedHashMultimap.create
		val alreadyProcessedIdRef = <String>newHashSet()
		val alreadyProcessedTypeRef = <String>newHashSet()

		val N4JSCandidateFilter candidateFilter = new N4JSCandidateFilter();

		// Some of the ParemterizedTypeRefs might come from the types computer and
		// don't have a name in the script --> usedName might be null and the possible
		unres.forEach [ proxiedEobject, settings |
			settings.forEach [
				val obj = it.EObject
				val String usedName = switch (obj) {
					IdentifierRef:
						obj.findIdentifierName
					ParameterizedTypeRef:
						obj.findTypeName
					default:
						obj.node.tokenText
				}
				// int situations like "new A()" at the position of A an IdentifierRef is unresolved.
				// The solution is provided as a TypeRef. So TypeRef-solutions can be used in places where an IDref is desired.
				// --> obj Idref :  scopeIdRef & scopeTypeRef
				// --> obj TypeRef : only TypeRef
				if (obj instanceof IdentifierRef) {
					if (alreadyProcessedIdRef.add(usedName)) {
						scopeIdRef.allElements.filter[candidateFilter.apply(it)].
							filter[it.name.lastSegment == usedName].forEach[resolution.put(usedName, it)]
					}
				}
				{ // Query for idref and TypeRef
					if (alreadyProcessedTypeRef.add(usedName)) {
						scopeTypeRef.allElements.filter[candidateFilter.apply(it)].filter [
							it.name.lastSegment == usedName
						].forEach[resolution.put(usedName, it)]
					}
				}
			]
		]

		val solutions = resolution.asMap.filter[p1, p2|p2.size == 1]

		val ret = <ImportDeclaration>newArrayList();

		solutions.forEach [ name, collIEObj |
			ret.add(createNamedImport(name, collIEObj.head.qualifiedName.skipLast(1)))
		]

		//ignore broken names, for which imports will be added due to unresolved refs
		namesThatWeBroke.removeAll(solutions.keySet)

		// Ask user for disambigous things:
		val ambiguousSolution = resolution.asMap.filter[p1, p2|p2.size > 1]
		val forDisambigue = LinkedHashMultimap.create
		ambiguousSolution.forEach[p1, p2|forDisambigue.putAll(p1, p2)]

		//add potential solutions for still broken names
		namesThatWeBroke.forEach[brokeName|
			//if there is disambiguation pending for a given name, don't duplicate solutions
			if(!forDisambigue.keySet.contains(brokeName)){
				val idSolutions = newArrayList()
				//TODO how to decide on which we use?
				/*
				 * Should we add both, or pick one? Or add other if the first one did not find anything?
				 */
				idSolutions.addAll(scopeIdRef.allElements.filter[candidateFilter.apply(it)].filter[it.name.lastSegment == brokeName])
				idSolutions.addAll(scopeTypeRef.allElements.filter[candidateFilter.apply(it)].filter[it.name.lastSegment == brokeName])
				forDisambigue.putAll(brokeName, idSolutions)
			}
		]

		val chosenSolutions = disambigue(forDisambigue, interaction);

		chosenSolutions.forEach[ret.add(createNamedImport(name.lastSegment, it.qualifiedName.skipLast(1)))]

		return ret;
	}

	/** For each name in names create a new ImportDeclaration using the Module from declaration. */
	def createNamedImports(ImportDeclaration declaration, Iterable<String> names) {
		names.map[createNamedImport(it, declaration.module.qualifiedName)]
	}

	/** Creates a new named import of 'name' from 'module'*/
	private def ImportDeclaration createNamedImport(String name, QualifiedName module) {
		return createNamedImport(name, module.toString)
	}

	/** Creates a new named import of 'name' from 'moduleName'*/
	private def ImportDeclaration createNamedImport(String name, String moduleName) {

		val ret = N4JSFactory::eINSTANCE.createImportDeclaration

		val namedImportSpec = N4JSFactory::eINSTANCE.createNamedImportSpecifier
		val tmodule = TypesFactory.eINSTANCE.createTModule
		tmodule.qualifiedName = moduleName
		val idfEle = TypesFactory.eINSTANCE.createTExportableElement
		idfEle.name = name
		namedImportSpec.importedElement = idfEle

		ret.importSpecifiers.add(namedImportSpec)
		ret.eAdapters.add(nodelessMarker)
		ret.module = tmodule

		return ret
	}

	/**
	 * Sorting a List of import declarations (mixed content Named / Namespace)
	 * Order is: First all Named imports, then all Namespace imports.
	 */
	private def sortByImport(List<ImportDeclaration> declarations) {
		declarations.sort(new Comparator<ImportDeclaration>() {
			override compare(ImportDeclaration o1, ImportDeclaration o2) {
				switch ( o1.importSpecifiers.get(0) ) {
					NamespaceImportSpecifier: {
						if (o2.importSpecifiers.get(0) instanceof NamespaceImportSpecifier) {
							compModules(o1, o2)

						} else {
							1; // positive, since wildcards are lasts
						}
					}
					NamedImportSpecifier: {
						if (o2.importSpecifiers.get(0) instanceof NamespaceImportSpecifier) {
							-1; // neg, wildcard last.
						} else {
							var cmp = compModules(o1, o2)
							if (cmp === 0) {
								compNamedImports(o1.importSpecifiers, o2.importSpecifiers)
							} else
								cmp
						}
					}
					default:
						throw new UnsupportedOperationException("Unknown ImportSpecifier")
				}
			}
		}) // end sort.
	}

	private def sortByName(List<ImportSpecifier> list) {
		Collections.sort(list, new Comparator<ImportSpecifier>() {
			override compare(ImportSpecifier o1, ImportSpecifier o2) {
				if (o1 instanceof NamespaceImportSpecifier) {
					return 1;
				} else if (o2 instanceof NamespaceImportSpecifier) {
					return -1;
				} else {
					return compNamedImport(o1 as NamedImportSpecifier, o2 as NamedImportSpecifier);
				}
			}
		})
	}

	/** Compare list of NamedImports.
	 *  Comparing elements in sequential order until inequality is found.
	 */
	final static private def int compNamedImports(EList<ImportSpecifier> l1, EList<ImportSpecifier> l2) {
		val comparable_elements = Math.min(l1.size, l2.size)
		for (var int i = 0; i < comparable_elements; i++) {
			val cmp = compNamedImport(l1.get(i) as NamedImportSpecifier, l2.get(i) as NamedImportSpecifier)
			if (cmp !== 0) return cmp
		}

		// longer list below:
		return l1.size - l2.size
	}

	/** Compares two NamedImport specifier: eg. "Z as A" <--> "X as B" */
	final static private def int compNamedImport(NamedImportSpecifier o1, NamedImportSpecifier o2) {

		// o1.findActualNodeFor().tokenText. compareTo( o2.findActualNodeFor.tokenText )
		var name1 = o1?.importedElement?.name
		var name2 = o2?.importedElement?.name
		val cmp1 = (name1 ?: "").compareTo(name2 ?: "")
		if (cmp1 == 0) {
			(o1.alias ?: "").compareTo(o2.alias ?: "")
		} else
			cmp1
	}

	/** compare based on Qualified name */
	final static private def int compModules(ImportDeclaration o1, ImportDeclaration o2) {
		(o1.module.qualifiedName ?: "").compareTo(o2.module.qualifiedName ?: "")
	}

	private def String extractPureText(ImportDeclaration declaration) {
		if (declaration.eAdapters.contains(nodelessMarker)) {
			val impSpec = declaration.importSpecifiers
			val module = declaration.module.moduleSpecifier
			if (impSpec.size === 1) {

				// create own string. from single Named Adapter:
				val namedSpec = impSpec.get(0) as NamedImportSpecifier

				'''import { «namedSpec.importedElement.name»«
					IF (namedSpec.alias !== null)» as «namedSpec.alias»«ENDIF» } from "«module»"'''
			} else {
				// more then one, sort them:
				impSpec.
					sortByName()

				'''import { «FOR a : impSpec SEPARATOR ', '»«(a as NamedImportSpecifier).importedElement.name»«
					IF ((a as NamedImportSpecifier).alias !== null)» as « (a as NamedImportSpecifier).alias »«ENDIF»«ENDFOR» } from "«module»"'''
			}
		} else {
			val importNode = findActualNodeFor(declaration);
			return UtilN4.getTokenText(importNode, SEMICOLON_INSERTED);
		}
	}

	/**
	 * Compute all cleanup changes (removal of imports)
	 *
	 *
	 * @param xtextResource - the Resource to modify
	 * @param document - the document connected to the xtextResource,  for textual changes.
	 * @return list of changes to the document.
	 */
	public def List<IChange> getCleanupChanges(XtextResource xtextResource,
		IXtextDocument document) throws BadLocationException {

		val changes = newArrayList

		// Remove nodes in all places.
		val elements = xtextResource.getScript().scriptElements

		for (var i = 0; i < elements.size; i++) {
			val el = elements.get(i)
			if (el instanceof ImportDeclaration) {
				val nodeToRemove = findActualNodeFor(el)
				changes.add(document.removeNodeButKeepComments(nodeToRemove))
			}
		}

		return changes
	}


	private def IChange removeNodeButKeepComments(IXtextDocument doc, INode importNode)	throws BadLocationException {
		if (importNode === null)
			return IChange.IDENTITY;
		// INode next = node.getNextSibling();
		val BidiTreeIterator<INode> reversed = importNode.getAsTreeIterable().iterator();
		var end = importNode.endOffset
		var done = false
		while(!done && reversed.hasPrevious()) {
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
						while(!done && reversed.hasPrevious) {
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
					while(!done && reversed.hasPrevious) {
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
		//println(doc.get(offset, end-offset))
		return ChangeProvider.removeText(doc, offset, end - offset, true);
	}

	def Shell getShell() { return Display.getCurrent.activeShell }

	private def <T> List<T> takefirst(Multimap<String, T> multimap) {
		val result = <T>newArrayList();

		for (name : multimap.keySet) {

			// TODO the first must be actually determined from the error-state-list given by the scoping, see {@link ImportProvidedElement#ambiguityList}
			// The first Identifiable in there is bound to the first thing the scoping encountered. For the time beeing, lets take any:
			result += multimap.get(name).get(0)
		}

		return result;
	}

	/**
	 * Depending on mode:
	 * Present a user dialog and let him choose a distinct import for each unresolved problem.
	 *
	 */
	private def <T> List<T> disambigue(Multimap<String, T> multimap, Interaction interaction) throws BreakException {

		// def List<ImportProvidedElement> disambigue(Multimap<String, ImportProvidedElement> multimap, Interaction interaction) throws BreakException {
		// for each name exactly one solution must be picked:
		val result = <T>newArrayList();
		if (multimap.empty) return result;

		switch (interaction) {
			case breakBuild: {
				throw new BreakException("Cannot automatically disambiguate the imports of " + multimap.keySet.toList)
			}
			case takeFirst: {
				return takefirst(multimap);
			}
			case queryUser: {
			} // follows
		}

		// ISelection sel= editor.getSelectionProvider().getSelection();
		// ILabelProvider labelProvider= new TypeNameMatchLabelProvider(TypeNameMatchLabelProvider.SHOW_FULLYQUALIFIED);
		val ILabelProvider labelProvider = importProvidedElementLabelprovider;

		val Object[][] openChoices = N4JSOrganizeImportsHelper.createOptions(multimap);

		val MultiElementListSelectionDialog dialog = new MultiElementListSelectionDialog(null, labelProvider) {
			@Override
			override protected void handleSelectionChanged() {
				super.handleSelectionChanged();

			// show choices in editor
			// doListSelectionChanged(getCurrentPage(), ranges, editor);
			}
		}

		// fIsQueryShowing= true;
		dialog.setTitle("Organize Imports");
		dialog.setMessage("Choose type to import:");
		dialog.setElements(openChoices);

		// dialog.setComparator(ORGANIZE_IMPORT_COMPARATOR);
		if (dialog.open() == Window.OK) {
			val Object[] res = dialog.getResult();

			// result= new TypeNameMatch[res.length];
			for (var int i = 0; i < res.length; i++) {
				val Object[] array = res.get(i) as Object[];
				if (array.length > 0) {

					// result[i]= (TypeNameMatch) array[0];
					result.add(array.get(0) as T)

				// QualifiedTypeNameHistory.remember(result[i].getFullyQualifiedName());
				}
			}
		} else {
			throw new BreakException("User canceled.");
		}

		// // restore selection
		// if (sel instanceof ITextSelection) {
		// ITextSelection textSelection= (ITextSelection) sel;
		// editor.selectAndReveal(textSelection.getOffset(), textSelection.getLength());
		// }
		// fIsQueryShowing= false;
		return result;
	}

//	def private void doListSelectionChanged(int page /* , ISourceRange[] ranges*/ /*, JavaEditor editor */) {
//		if (ranges != null && page >= 0 && page < ranges.length) {
//			// ISourceRange range= ranges[page];
//			// editor.selectAndReveal(range.getOffset(), range.getLength());
//		}
//	}
//	}
}
