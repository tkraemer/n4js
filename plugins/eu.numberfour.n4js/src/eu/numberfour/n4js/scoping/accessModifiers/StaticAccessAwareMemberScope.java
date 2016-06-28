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
package eu.numberfour.n4js.scoping.accessModifiers;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.IdentifierRef;
import eu.numberfour.n4js.n4JS.ImportDeclaration;
import eu.numberfour.n4js.n4JS.IndexedAccessExpression;
import eu.numberfour.n4js.n4JS.NamedImportSpecifier;
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression;
import eu.numberfour.n4js.n4JS.Script;
import eu.numberfour.n4js.n4JS.extensions.ExpressionExtensions;
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.ContainerType;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.xtext.scoping.FilterWithErrorMarkerScope;
import eu.numberfour.n4js.xtext.scoping.IEObjectDescriptionWithError;

/**
 * Filters static members that are not accessible from the given context.
 */
public class StaticAccessAwareMemberScope extends FilterWithErrorMarkerScope {

	private final EObject context; // usually a ParameterizedPropertyAccessExpression
	private final TypeRef receiverType;
	private String memberTypeName = "-not initialised-";

	private String memberTypeAlias = null;

	/**
	 * Creates a new scope instance filtering parent.
	 *
	 * @param context
	 *            usually a ParameterizedPropertyAccessExpression.
	 */
	public StaticAccessAwareMemberScope(IScope parent, TypeRef receiverType,
			EObject context) {
		super(parent);
		this.receiverType = receiverType;
		this.context = context;
	}

	@Override
	protected IEObjectDescriptionWithError wrapFilteredDescription(IEObjectDescription result) {
		return new InvalidStaticMemberDescription(result, memberTypeName, memberTypeAlias);
	}

	@Override
	protected boolean isAccepted(IEObjectDescription description) {
		EObject proxyOrInstance = description.getEObjectOrProxy();
		if (proxyOrInstance != null && !proxyOrInstance.eIsProxy()) {
			if (proxyOrInstance instanceof TMember) {
				TMember member = (TMember) proxyOrInstance;

				// Checks Constraints 61 (write-access to static data field and static setter)

				// check correct static access of fields or setters.
				if (member.isStatic() && (member.isField() || member.isSetter())) {
					ContainerType<?> memberType = member.getContainingType();
					memberTypeName = memberType.getName();
					if ((receiverType instanceof ClassifierTypeRef) && canAppearInLHSPosition() && isWriteAccess()) {
						// Access only allowed for Direct access, so AST must be IdentifierRef.
						final boolean isTargetGivenByIdentifier = getTarget() instanceof IdentifierRef;
						if (!isTargetGivenByIdentifier) {
							// Not an IdentifierRef --> disallowed for write access.
							return false;
						}

						IdentifierRef idref = (IdentifierRef) getTarget();
						// this also covers aliased imports:
						if (idref.getId().getName().equals(memberTypeName)) {
							// correct name.
							return true;
						} else {
							// wrong name, disallowed
							// search for alias, for better error reporting.
							Script sc = EcoreUtil2.getContainerOfType(context, Script.class);
							Optional<NamedImportSpecifier> namedImport = sc.getScriptElements().stream()
									.filter(se -> se instanceof ImportDeclaration)
									.map(se -> (ImportDeclaration) se)
									.flatMap(idecl -> {
										return idecl.getImportSpecifiers().stream()
												.filter(is -> is instanceof NamedImportSpecifier)
												.map(is -> (NamedImportSpecifier) is);
									})
									.filter(s -> s.getImportedElement() == memberType)
									.findFirst();
							if (namedImport.isPresent()) {
								// if alias is present assign, otherwise null will be passed through
								memberTypeAlias = namedImport.get().getAlias();
							}

							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private Expression getTarget() {
		if (context instanceof ParameterizedPropertyAccessExpression) {
			ParameterizedPropertyAccessExpression ppae = (ParameterizedPropertyAccessExpression) context;
			return ppae.getTarget();
		}
		if (context instanceof IndexedAccessExpression) {
			IndexedAccessExpression iae = (IndexedAccessExpression) context;
			return iae.getTarget();
		}
		return null;
	}

	private boolean canAppearInLHSPosition() {
		return (context instanceof ParameterizedPropertyAccessExpression)
				|| (context instanceof IndexedAccessExpression);
	}

	private boolean isWriteAccess() {
		// problems occur if on LHS of assignmentExpression or in writing unary expression
		return ExpressionExtensions.isLeftHandSide(context) || ExpressionExtensions.isIncOrDecTarget(context);
	}

}
