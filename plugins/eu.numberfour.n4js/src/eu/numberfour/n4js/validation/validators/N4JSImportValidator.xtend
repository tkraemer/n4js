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
package eu.numberfour.n4js.validation.validators

import eu.numberfour.n4js.n4JS.EmptyStatement
import eu.numberfour.n4js.n4JS.ExportDeclaration
import eu.numberfour.n4js.n4JS.ImportDeclaration
import eu.numberfour.n4js.n4JS.ImportSpecifier
import eu.numberfour.n4js.n4JS.NamedImportSpecifier
import eu.numberfour.n4js.n4JS.NamespaceImportSpecifier
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.organize.imports.ImportProvidedElement
import eu.numberfour.n4js.organize.imports.ImportStateCalculator
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4js.validation.IssueCodes
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.types.ModuleNamespaceVirtualType
import eu.numberfour.n4js.ts.types.TModule
import eu.numberfour.n4js.utils.Log
import java.util.List
import java.util.Map
import javax.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

import static eu.numberfour.n4js.validation.IssueCodes.*
import static extension eu.numberfour.n4js.n4JS.N4JSASTUtils.*
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.utils.ResourceType

/**
 */
@Log
class N4JSImportValidator extends AbstractN4JSDeclarativeValidator {

	@Inject
	ImportStateCalculator importStateCalculator;

	/**
	 * NEEEDED
	 *
	 * when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	override register(EValidatorRegistrar registrar) {
		// nop
	}

	@Check
	def checkMultipleDefaultExports(Script script) {
		val defaultExports = script.eAllContents.filter(ExportDeclaration).filter[isDefaultExport].toList;
		defaultExports.tail.forEach[
			addIssue(getMessageForIMP_DUPLICATE_DEFAULT_EXPORT, it, N4JSPackage.eINSTANCE.exportDeclaration_DefaultExport, IMP_DUPLICATE_DEFAULT_EXPORT);
		]
	}

	@Check
	def checkConflictingImports(Script script) {
		val eObjectToIssueCode = newHashMap
		analyzeAndcheckConflictingImports(script, eObjectToIssueCode)
		// regardless of other issues, add markers for scattered imports
		markScatteredImports(script)
	}

	@Check
	def checkStaticVsDynamicImport(NamespaceImportSpecifier importSpecifier) {
		val type = importSpecifier.definedType;
		if (type instanceof ModuleNamespaceVirtualType) {
			if (type.module !== null) {
				val resType = ResourceType.getResourceType(type.module);

				if (importSpecifier.declaredDynamic) {
					if (resType===ResourceType.N4JS) {
						addIssue(
							getMessageForIMP_DYNAMIC_NAMESPACE_IMPORT_N4JS(type.module.moduleSpecifier),
							importSpecifier, IMP_DYNAMIC_NAMESPACE_IMPORT_N4JS);
					} else if (resType===ResourceType.N4JSD) {
						addIssue(
							getMessageForIMP_DYNAMIC_NAMESPACE_IMPORT_N4JSD(type.module.moduleSpecifier),
							importSpecifier, IMP_DYNAMIC_NAMESPACE_IMPORT_N4JSD);
					}
				} else {
					if (resType===ResourceType.JS) {
						addIssue(
							getMessageForIMP_STATIC_NAMESPACE_IMPORT_PLAIN_JS(type.module.moduleSpecifier),
							importSpecifier, IMP_STATIC_NAMESPACE_IMPORT_PLAIN_JS);
					}
				}
			}
		}
	}

	/**
	 * Algorithm to check the Model for Issues with Imports.
	 */
	private def analyzeAndcheckConflictingImports(Script script, Map<EObject, String> eObjectToIssueCode) {
		val reg = importStateCalculator.calculateImportstate(script);

		reg.duplicatedImportDeclarations.forEach[handleDuplicatedImportDeclarations(eObjectToIssueCode)]

		handleNameCollisions(reg.localNameCollision, eObjectToIssueCode)

		handleTypeCollisions(reg.duplicateImportsOfSameElement, eObjectToIssueCode)

		handleBrokenImports(reg.brokenImports, eObjectToIssueCode)

		handleUnusedImports(reg.unusedImports, eObjectToIssueCode)

		handleNotImportedTypeRefs(script, eObjectToIssueCode.keySet.filter(ImportSpecifier).toList, eObjectToIssueCode)
	}

	private def importedElementName(NamedImportSpecifier it) {
		if (importedElement !== null && !importedElement.eIsProxy) {
			importedElement?.name ?: "<unknown>"
		} else
			"<unknown (proxy)>"
	}

	private def usedName(NamedImportSpecifier it) {
		alias ?: importedElementName
	}

	def private importedModule(ImportSpecifier it) {
		(eContainer as ImportDeclaration).module
	}

	/** Create issue (warning) 'unused import' if  import doesn't contribute to referenced Types.
	 * @param specifier import to be marked
	 * */
	private def void handleBrokenImports(List<ImportSpecifier> importSpecifiers,
		Map<EObject, String> eObjectToIssueCode) {
		for (is : importSpecifiers) {
			if (!eObjectToIssueCode.keySet.contains(is)) {
				addIssueUnresolved(is, eObjectToIssueCode)
			}
		}
	}

	/** Create issue (warning) 'unused import' if  import doesn't contribute to referenced Types.
	 * @param specifier import to be marked
	 * */
	private def void handleUnusedImports(List<ImportSpecifier> importSpecifiers,
		Map<EObject, String> eObjectToIssueCode) {
		for (is : importSpecifiers) {
			if (!eObjectToIssueCode.keySet.contains(is)) {
				addIssueUnusedImport(is, eObjectToIssueCode);
			}
		}
	}

	/**
	 * @param spec
	 * @return name from NamedImportSpecifier or AST-text if unresolved.
	 */
	private def String importedElementErrorName(NamedImportSpecifier spec) {
		if (isUnresolvedImport(spec)) {
			// find AST for Message:
			NodeModelUtils.findActualNodeFor(spec).text.trim
		} else
			return spec.importedElementName
	}

	/**
	 * @param spec - the ImportSpecifier to investigate
	 * @return true if linker failed to resolve
	 * */
	private def isUnresolvedImport(ImportSpecifier spec) {
		if (spec.importedModule === null || spec.importedModule.eIsProxy) {
			return true
		} else {
			return spec.importedModule.qualifiedName === null && if (spec instanceof NamedImportSpecifier) {
				spec.importedElement === null || spec.importedElement.name === null
			} else
				return true;
		}
	}

	/** Mark all imports that don't appear in the header.
	 * @param script the script
	 */
	private def markScatteredImports(Script script) {
		var boolean stillInHeader = true
		for (se : script.scriptElements) {
			if (stillInHeader) {
				if (! ( se instanceof ImportDeclaration || se instanceof EmptyStatement || se.isStringLiteralExpression )) stillInHeader = false;
			} else {
				if (se instanceof ImportDeclaration) handleScatteredImport(se)
			}
		}
	}

	/**
	 * Mark with discouraged_placement it not yet marked with other error.
	 * @param importDecl - statement to mark
	 */
	private def handleScatteredImport(ImportDeclaration importDecl) {
		addIssueScatteredImport(importDecl)
	}

	private def handleDuplicatedImportDeclarations(Pair<ImportDeclaration, List<ImportDeclaration>> duplicatePair,
		Map<EObject, String> eObjectToIssueCode) {
		val specifier = duplicatePair.key.importSpecifiers.head
		val duplicates = duplicatePair.value
		duplicates.forEach [ duplicateImportDeclaration |
			val duplicate = duplicateImportDeclaration.importSpecifiers.head
			switch (specifier) {
				NamespaceImportSpecifier: {
					switch (duplicate) {
						NamespaceImportSpecifier: {
							addIssueDuplicateNamespaceImportDeclaration(specifier, duplicate,
								duplicateImportDeclaration, eObjectToIssueCode)
						}
						default:
							logger.error(
								"cannot register duplicate import declaration for different kinds of specifiers")
					}
				}
				NamedImportSpecifier: {
					switch (duplicate) {
						NamedImportSpecifier: {
							addIssueDuplicateNamedImportDeclaration(specifier, duplicate, duplicateImportDeclaration,
								eObjectToIssueCode)
						}
						default:
							logger.error(
								"cannot register duplicate import declaration for different kinds of specifiers")
					}
				}
				default:
					logger.error("cannot register duplicate import declaration for  specifiers")
			}
		]
	}

	private def handleNameCollisions(List<Pair<String, List<ImportProvidedElement>>> multimap,
		Map<EObject, String> eObjectToIssueCode) {
		multimap.forEach [ pair |
			val name = pair.key
			val providers = pair.value
			// assuming they came in order
			val first = providers.head.importSpec
			val name2reason = switch (first) {
				NamespaceImportSpecifier: {
					name -> "namespace name for " + first.importedModule.qualifiedName.toString
				}
				NamedImportSpecifier: {
					if (first.alias !== null) {
						name -> "alias name for named import " + first.importedElement.name + " from " +
							first.importedModule.qualifiedName.toString
					} else {
						name -> "name for named import " + first.importedElement.name + " from " +
							first.importedModule.qualifiedName.toString
					}
				}
				default: {
					logger.error("unhandled type of " + ImportSpecifier.name);
					throw new RuntimeException("Cannot validate local name collisions");
				}
			}
			providers.tail.forEach [ importProvidedElement |
				addLocalNameCollision(importProvidedElement.importSpec, name2reason.key, name2reason.value,
					eObjectToIssueCode)
			]
		]
	}

	private def handleTypeCollisions(List<Pair<Pair<String, TModule>, List<ImportProvidedElement>>> duplicateslist,
		Map<EObject, String> eObjectToIssueCode) {
		duplicateslist.forEach [ duplicateEntry |
			val entry = duplicateEntry.key
			val entryName = entry.key
			val entryModule = entry.value
			val imports = duplicateEntry.value
			val firstImportSpecifier = imports.head.importSpec
			val firstImportName = switch (firstImportSpecifier) {
				NamespaceImportSpecifier: {
					firstImportSpecifier.alias + "." + entryName
				}
				NamedImportSpecifier: {
					firstImportSpecifier.alias ?: entryName
				}
			}
			val firstImportIsDefault = if(firstImportSpecifier instanceof NamedImportSpecifier) {
				firstImportSpecifier.isDefaultImport
			};

			imports.tail.forEach [ dupe |
				val duplicateImportSpecifier = dupe.importSpec
				val isLegalCombinationDefaultNamespaceImport =
					firstImportIsDefault && duplicateImportSpecifier instanceof NamespaceImportSpecifier;
				if (isLegalCombinationDefaultNamespaceImport) {
					return;
				}
				if (firstImportSpecifier instanceof NamespaceImportSpecifier &&
					duplicateImportSpecifier instanceof NamespaceImportSpecifier) {
					addIssueDuplicateNamespace(duplicateImportSpecifier as NamespaceImportSpecifier,
						firstImportSpecifier as NamespaceImportSpecifier, eObjectToIssueCode)
				} else {
					addIssueDuplicate(dupe.importSpec, entryName, entryModule, firstImportName, eObjectToIssueCode)
				}
			]
		]
	}

	private def regUnresolvedImport(ParameterizedTypeRef ref, String name, Map<EObject, String> eObjectToIssueCode) {
		val issueCode = IssueCodes.IMP_UNRESOLVED
		if (eObjectToIssueCode.put(ref, issueCode) === null) {
			val message = IssueCodes.getMessageForIMP_UNRESOLVED(name)
			addIssue(message, ref, issueCode)
		}
	}

	def private handleNotImportedTypeRefs(Script script, List<ImportSpecifier> specifiersWithIssues,
		Map<EObject, String> eObjectToIssueCode) {
		val importedProvidedElementsWithIssuesByModule = specifiersWithIssues.mapToImportProvidedElements.groupBy [
			tmodule
		]
		val potentiallyAffectedTypeRefs = script.eAllContents.filter(ParameterizedTypeRef).filter [
			declaredType !== null && declaredType.containingModule !== null
		].groupBy[declaredType.containingModule]

		potentiallyAffectedTypeRefs.forEach [ module, typeRefs |
			val conflict = importedProvidedElementsWithIssuesByModule.get(module);
			if (conflict !== null) {
				typeRefs.forEach [ typeRef |
					val typeRefName = typeRef.typeRefUsedName;
					if (conflict.exists[ipe|ipe.localname == typeRefName]) {
						regUnresolvedImport(typeRef, typeRefName, eObjectToIssueCode);
					}
				]
			}
		]
	}

	private def String typeRefUsedName(ParameterizedTypeRef ref) {
		NodeModelUtils.getTokenText(NodeModelUtils.findActualNodeFor(ref))
	}

	/** TODO refactor
	 * COPY FROM ImportStateCalculator, refactor
	 */
	private def List<ImportProvidedElement> mapToImportProvidedElements(List<ImportSpecifier> importSpecifiers) {
		importSpecifiers.map(
			specifier |
				switch (specifier) {
					NamespaceImportSpecifier:
						if (specifier.importedModule !== null) {
							val importProvidedElements = newArrayList
							// add import provided element for a namespace itself
							importProvidedElements.add(
								new ImportProvidedElement(specifier.alias, computeNamespaceActualName(specifier),
									specifier))

							val topIdentifiables = specifier.importedModule.topLevelTypes.filter[isExported] +
								specifier.importedModule.variables.filter[it.isExported];

							topIdentifiables.forEach [ type |
								importProvidedElements.add(
									new ImportProvidedElement(specifier.alias + "." + type.name, type.name,
										specifier as ImportSpecifier))
							]
							return importProvidedElements
						} else {
							emptyList
						}
					NamedImportSpecifier:
						newArrayList(
							new ImportProvidedElement(specifier.usedName, specifier.importedElementName,
								specifier as ImportSpecifier))
					default:
						emptyList
				}
		).flatten.toList
	}

	/** TODO refactor
	 * COPY FROM ImportStateCalculator, refactor
	 *
	 * Computes 'actual' name of the namespace for {@link ImportProvidedElement} entry.
	 * If processed namespace refers to unresolved module, will return dummy name,
	 * otherwise returns artificial name composed of prefix and target module qualified name
	 *
	 */
	private def String computeNamespaceActualName(NamespaceImportSpecifier specifier) {
		if (specifier.importedModule.eIsProxy) {
			ImportProvidedElement.NAMESPACE_PREFIX + specifier.hashCode
		} else {
			ImportProvidedElement.NAMESPACE_PREFIX + specifier.importedModule.qualifiedName.toString
		}
	}

	/**
	 * Notice, that unlike other issues {@link IssueCodes#IMP_DISCOURAGED_PLACEMENT} is always added, even if there are other issues
	 * reported for given import declaration.
	 */
	private def addIssueScatteredImport(ImportDeclaration importDecl) {
		val issueCode = IssueCodes.IMP_DISCOURAGED_PLACEMENT
		val message = IssueCodes.getMessageForIMP_DISCOURAGED_PLACEMENT
		addIssue(message, importDecl, issueCode)
	}

	private def addLocalNameCollision(ImportSpecifier duplicate, String name, String reason,
		Map<EObject, String> eObjectToIssueCode) {
		val issueCode = IssueCodes.IMP_LOCAL_NAME_CONFLICT
		if (eObjectToIssueCode.get(duplicate) === null) {
			val message = IssueCodes.getMessageForIMP_LOCAL_NAME_CONFLICT(name, reason)
			addIssue(message, duplicate, issueCode)
			eObjectToIssueCode.put(duplicate, issueCode)
		}
	}

	private def addIssueDuplicateNamespaceImportDeclaration(NamespaceImportSpecifier specifier,
		NamespaceImportSpecifier duplicate, ImportDeclaration duplicateImportDeclaration,
		Map<EObject, String> eObjectToIssueCode) {
		val String issueCode = IssueCodes.IMP_STMT_DUPLICATE_NAMESPACE
		if (eObjectToIssueCode.get(specifier) === null) {
			val message = IssueCodes.getMessageForIMP_STMT_DUPLICATE_NAMESPACE(specifier.alias,
				duplicate.importedModule.qualifiedName)
			addIssue(message, duplicateImportDeclaration, issueCode)
		}

		duplicateImportDeclaration.importSpecifiers.forEach [ is |
			eObjectToIssueCode.put(specifier, issueCode)
		]
	}

	private def addIssueDuplicateNamedImportDeclaration(NamedImportSpecifier specifier,
		NamedImportSpecifier duplicate, ImportDeclaration duplicateImportDeclaration,
		Map<EObject, String> eObjectToIssueCode) {
			val String issueCode = IssueCodes.IMP_STMT_DUPLICATE_NAMED
			if (eObjectToIssueCode.get(specifier) === null) {
				val message = IssueCodes.getMessageForIMP_STMT_DUPLICATE_NAMED(specifier.usedName,
					duplicate.importedModule.qualifiedName)
				addIssue(message, duplicateImportDeclaration, issueCode)
			}

			duplicateImportDeclaration.importSpecifiers.forEach [ is |
				eObjectToIssueCode.put(specifier, issueCode)
			]
	}

	private def addIssueDuplicateNamespace(NamespaceImportSpecifier duplicateImportSpecifier,
		NamespaceImportSpecifier firstImportSpecifier, Map<EObject, String> eObjectToIssueCode) {
		if (eObjectToIssueCode.get(duplicateImportSpecifier) === null) {
			val issueCode = IssueCodes.IMP_DUPLICATE_NAMESPACE
			val msg = IssueCodes.getMessageForIMP_DUPLICATE_NAMESPACE(
				firstImportSpecifier.importedModule.qualifiedName, firstImportSpecifier.alias)
			addIssue(msg, duplicateImportSpecifier, issueCode)
			eObjectToIssueCode.put(duplicateImportSpecifier, issueCode)
		}
	}

	private def addIssueDuplicate(ImportSpecifier specifier, String actualName, TModule module,
		String firstImportName, Map<EObject, String> eObjectToIssueCode) {
		var String issueCode = IssueCodes.IMP_DUPLICATE
		if (eObjectToIssueCode.get(specifier) === null) {
			val message = IssueCodes.getMessageForIMP_DUPLICATE(actualName, module.qualifiedName,
				firstImportName)
			addIssue(message, specifier, issueCode)
			eObjectToIssueCode.put(specifier, issueCode)
		}
	}

	private def addIssueUnresolved(ImportSpecifier specifier, Map<EObject, String> eObjectToIssueCode) {
		var String issueCode = IssueCodes.IMP_UNRESOLVED
		if (eObjectToIssueCode.get(specifier) === null) {
			val message = IssueCodes.getMessageForIMP_UNRESOLVED(computeUnusedOrUnresolvedMessage(specifier))
			addIssue(message, specifier, issueCode)
			eObjectToIssueCode.put(specifier, issueCode)
		}
	}

	private def addIssueUnusedImport(ImportSpecifier specifier, Map<EObject, String> eObjectToIssueCode) {
		val issueCode = IssueCodes.IMP_UNUSED_IMPORT
		if (eObjectToIssueCode.get(specifier) === null) {
			val message = IssueCodes.getMessageForIMP_UNUSED_IMPORT(computeUnusedOrUnresolvedMessage(specifier))
			addIssue(message, specifier, issueCode)
			eObjectToIssueCode.put(specifier, issueCode)
		}
	}

	private def String computeUnusedOrUnresolvedMessage(ImportSpecifier specifier) {
		switch (specifier) {
			NamedImportSpecifier: specifier.importedElementErrorName
			NamespaceImportSpecifier: "* as " + specifier.alias + " from " + computeModuleSpecifier(specifier)
		}
	}

	private def String computeModuleSpecifier(NamespaceImportSpecifier specifier) {
		val importedModule = specifier.importedModule
		if (importedModule !== null && !importedModule.eIsProxy) {
			importedModule.moduleSpecifier
		} else {
			"module was a proxy"
		}
	}

}
