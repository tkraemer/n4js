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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.linking.impl.IllegalNodeException;
import org.eclipse.xtext.linking.impl.XtextLinkingDiagnostic;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.google.inject.Inject;

import eu.numberfour.n4js.n4JS.DefaultImportSpecifier;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.scoping.utils.UnresolvableObjectDescription;
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.xtext.scoping.IEObjectDescriptionWithError;

/**
 * Additional to {@link DefaultLinkingService} it handles {@link IEObjectDescription} typed as
 * {@link IEObjectDescriptionWithError} to produce linking diagnostics (i.e. error messages for not resolvable links)
 * for them. This is only done, when validation is enabled.
 */
public class ErrorAwareLinkingService extends DefaultLinkingService {

	private static final EReference PARAMETERIZED_TYPE_REF__DECLARED_TYPE = TypeRefsPackage.eINSTANCE
			.getParameterizedTypeRef_DeclaredType();
	private static final EReference NAMED_IMPORT_SPECIFIER__IMPORTED_ELEMENT = N4JSPackage.eINSTANCE
			.getNamedImportSpecifier_ImportedElement();

	@Inject
	private IQualifiedNameConverter qualifiedNameConverter;

	@Inject
	private IN4JSCore n4jsCore;

	@Override
	public List<EObject> getLinkedObjects(EObject context, EReference ref, INode node) throws IllegalNodeException {
		final EClass requiredType = ref.getEReferenceType();
		if (requiredType == null)
			return Collections.<EObject> emptyList();

		final String crossRefString = getCrossRefNodeAsString(context, ref, node);
		if (crossRefString != null && !crossRefString.equals("")) {
			final IScope scope = getScope(context, ref);
			QualifiedName qualifiedLinkName = qualifiedNameConverter.toQualifiedName(crossRefString);
			IEObjectDescription eObjectDescription = scope.getSingleElement(qualifiedLinkName);
			if (IEObjectDescriptionWithError.isErrorDescription(eObjectDescription) && context.eResource() != null
					&& !n4jsCore.isNoValidate(context.eResource().getURI())) {
				addError(context, node, IEObjectDescriptionWithError.getDescriptionWithError(eObjectDescription));
			} else if (eObjectDescription instanceof UnresolvableObjectDescription) {
				return Collections.<EObject> singletonList((EObject) context.eGet(ref, false));
			}
			if (eObjectDescription != null) {
				EObject candidate = eObjectDescription.getEObjectOrProxy();
				if (!candidate.eIsProxy() && candidate.eResource() == null) {
					// TODO remove following exception case after resolving IDE-1253
					final boolean isCaseOfCachedComposedMemberHack = candidate instanceof TMember
							&& candidate.eContainer() instanceof ComposedTypeRef
							&& candidate.eContainmentFeature() == TypeRefsPackage.eINSTANCE
									.getComposedTypeRef_CachedComposedMembers();
					if (!isCaseOfCachedComposedMemberHack) {
						// Error is necessary since EMF catches all exceptions in EcoreUtil#resolve
						throw new AssertionError("Found an instance without resource and without URI");
					}
				}
				return Collections.singletonList(candidate);
			}
		}
		return Collections.emptyList();
	}

	/**
	 * As {@link #getCrossRefNodeAsString(INode)}, but gets more information passed in to decide if some special
	 * handling is required. By default, simply delegates to {@code #getCrossRefNodeAsString(INode)}.
	 */
	protected String getCrossRefNodeAsString(EObject context, EReference ref, INode node) {
		if (ref == NAMED_IMPORT_SPECIFIER__IMPORTED_ELEMENT && context instanceof DefaultImportSpecifier) {
			// special case: we got a default import of the form: import localName from "some/module"
			return "default";
		}
		// standard cases:
		String result = getCrossRefNodeAsString(node);
		if (ref == PARAMETERIZED_TYPE_REF__DECLARED_TYPE && context instanceof ParameterizedTypeRef) {
			// special case: we might have a reference to a type C imported via namespace import: NS.C
			// -> replace '.' by '/' to make it a valid qualified name
			result = result != null ? result.replace('.', '/') : null;
		}
		return result;
	}

	/**
	 * Add the error to the resource of the given {@code context} if it does support validation.
	 *
	 * @param context
	 *            the context object that caused the error.
	 * @param node
	 *            the error location.
	 * @param error
	 *            the actual error description.
	 */
	protected void addError(EObject context, INode node, IEObjectDescriptionWithError error) {
		N4JSResource resource = (N4JSResource) context.eResource();
		if (resource.isValidationDisabled())
			return;

		List<Diagnostic> list = resource.getErrors();

		// Convert key value user data to String array
		String[] userData = null;
		if (error.getUserDataKeys() != null) {
			ArrayList<String> userDataList = new ArrayList<>(error.getUserDataKeys().length * 2);
			for (String userDataKey : error.getUserDataKeys()) {
				final String userDataValue = error.getUserData(userDataKey);
				if (userDataValue != null) {
					userDataList.add(userDataKey);
					userDataList.add(userDataValue);
				}
			}
			userData = userDataList.toArray(new String[userDataList.size()]);
		}

		Diagnostic diagnostic = new XtextLinkingDiagnostic(node, error.getMessage(), error.getIssueCode(), userData);

		if (!list.contains(diagnostic))
			list.add(diagnostic);
	}
}
